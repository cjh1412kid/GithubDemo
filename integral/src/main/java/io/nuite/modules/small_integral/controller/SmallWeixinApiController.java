package io.nuite.modules.small_integral.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

import cn.jiguang.common.utils.StringUtils;
import io.nuite.common.utils.R;
import io.nuite.config.HttpRequestUtil;
import io.nuite.modules.small_integral.entity.UserInfoEntity;
import io.nuite.modules.small_integral.entity.UserUserEntity;
import io.nuite.modules.small_integral.service.UserInfoService;
import io.nuite.modules.small_integral.service.UserUserService;
import io.nuite.weixin.AesCbcUtil;


@RestController
@RequestMapping(value = "/weixin/api")
public class SmallWeixinApiController  {
	   
	@Value("${wxspAppid}")private String wxspAppid;
	    
	@Value("${wxspSecret}")private String wxspSecret;
	
	@Autowired
	private UserUserService userUserService;
	
	@Autowired
	private UserInfoService userInfoService;
	
	 protected static final Log logger = LogFactory.getLog(SmallWeixinApiController.class);
	
  @RequestMapping(value = "/decodeUserInfo")
  public R decodeUserInfo(String encryptedData, String iv, String code, HttpServletResponse response
      ) throws Exception {
    Map<String, Object> map = new HashMap<String, Object>();

    //登录凭证不能为空
    if (code == null || code.length() == 0) {
      return R.error("code 不能为空");
    }
    //授权（必填）
    String grant_type = "authorization_code";

    ////////////////1、向微信服务器 使用登录凭证 code 获取 session_key 和 openid ////////////////
    //请求参数
    String params = "appid=" + wxspAppid + "&secret=" + wxspSecret + "&js_code=" + code + "&grant_type=" + grant_type;
    //发送请求
    String sr = HttpRequestUtil.sendGet("https://api.weixin.qq.com/sns/jscode2session", params);
    if(StringUtils.isEmpty(sr)){
    	map.put("status", 0);
        return  R.error(); 
    }
    //解析相应内容（转换成json对象）
    JSONObject json = JSONObject.parseObject(sr);
    //获取会话密钥（session_key）
    String session_key = "";
    try {
      session_key = json.get("session_key") + "";
    } catch (Exception e) {
      System.out.println(json.get("session_key").toString());
    }

    //////////////// 2、对encryptedData加密数据进行AES解密 ////////////////
    try {
      String result = AesCbcUtil.decrypt(encryptedData, session_key, iv, "UTF-8");
      if (null != result && result.length() > 0) {
        map.put("status", 1);
        map.put("msg", "解密成功");
        JSONObject userInfoJSON = JSONObject.parseObject(result);
       logger.info(userInfoJSON);
       String unionId=userInfoJSON.getString("unionId");
       map.putAll(userInfoJSON);
     //根据unionId判断数据库是否存在该数据
     UserUserEntity userUserEntity=userUserService.getOneByUnionId(unionId);
       
       if(userUserEntity!=null){
    	   map.put("userStatus", userUserEntity.getDel());
           map.put("seq", userUserEntity.getSeq());
           map.put("loginPhone", userUserEntity.getTelephone());
           map.put("name", userUserEntity.getName());
           if(userUserEntity.getTelephone()!=null) {
        	   UserInfoEntity userInfoEntity=userInfoService.getOneByPhone(userUserEntity.getTelephone());
        	   if(userInfoEntity!=null) {
        		   map.put("name", userInfoEntity.getName());
        	   }else {
        		   map.put("userHave",1);
        	   }
           }
       }else {
    	  //插入一条数据
    	   userUserEntity=new UserUserEntity();
    	   userUserEntity.setDel(0);
    	   userUserEntity.setGender(userInfoJSON.getInteger("gender"));
    	   userUserEntity.setNickName(userInfoJSON.getString("nickName"));
    	   userUserEntity.setOpenId(userInfoJSON.getString("openId"));
    	   userUserEntity.setPicUrl(userInfoJSON.getString("avatarUrl"));
    	   userUserEntity.setUnionId(userInfoJSON.getString("unionId"));
    	   userUserEntity.setInputTime(new Date());
    	  Integer id=userUserService.addUser(userUserEntity);
    	   map.put("userStatus", 0);
           map.put("seq", id);
           map.put("loginPhone", null);
       }
        return R.ok(map);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return R.error("解密失败");
  }
  
  

  @RequestMapping(value="getPhone")
  public R getPhone(String encryptedData, String iv,String code,String session_key, HttpServletResponse response,
	      HttpServletRequest request) throws Exception{
	    Map<String, Object> map = new HashMap<String, Object>();
	      String userStr = request.getParameter("user");
	      JSONObject jsonObj = JSONObject.parseObject(userStr);
	    if(StringUtils.isEmpty(session_key)&&(code == null || code.length() == 0)){
	        return  R.error("session_key或code 不能为空"); 
	    }
	    if(StringUtils.isEmpty(session_key)){
	    	
	        //授权（必填）
	        String grant_type = "authorization_code";

	        ////////////////1、向微信服务器 使用登录凭证 code 获取 session_key 和 openid ////////////////
	        //请求参数
	        String params = "appid=" + wxspAppid + "&secret=" + wxspSecret + "&js_code=" + code + "&grant_type=" + grant_type;
	        //发送请求
	        String sr = HttpRequestUtil.sendGet("https://api.weixin.qq.com/sns/jscode2session", params);
	        //解析相应内容（转换成json对象）
	        JSONObject json = JSONObject.parseObject(sr);
	        //获取会话密钥（session_key）
	        try {
	          session_key = json.get("session_key") + "";
	        } catch (Exception e) {
	          System.out.println(json.get("session_key").toString());
	        }
	    }
	    String result = AesCbcUtil.decrypt(encryptedData, session_key, iv, "UTF-8");
	    String phone=JSONObject.parseObject(result).toString();
	    Integer seq=jsonObj.getInteger("seq");
	    //根据seq修改phone
	    UserUserEntity userUserEntity=userUserService.selectById(seq);
	    userUserEntity.setTelephone(phone);
	    //根据手机号查询userId
	    UserInfoEntity userInfoEntity=userInfoService.getOneByPhone(phone);
	    Integer userId=userInfoEntity.getUserId();
	    userUserEntity.setZKTimeUserId(userId);
	    userUserService.insertOrUpdate(userUserEntity);
	    map.put("phone", JSONObject.parseObject(result));
	    map.put("name", userInfoEntity.getName());
	    map.putAll(jsonObj);
	    map.put("session_key", session_key);
		return R.ok(map);
  }
}
