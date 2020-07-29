package io.nuite.modules.small_integral.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import io.nuite.modules.online_sales_app.annotation.XcxLogin;
import io.nuite.modules.small_integral.entity.CheckInOutEntity;
import io.nuite.modules.small_integral.entity.UserIntegralEntity;
import io.nuite.modules.small_integral.service.CheckInOutService;
import io.nuite.modules.small_integral.service.UserIntegralService;



@RestController
@RequestMapping(value="/integralRequest")
public class SmallRequestController {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private CheckInOutService checkInOutService;
	
	@Autowired
	private UserIntegralService userIntegralService;
	
    @XcxLogin
	@RequestMapping("getCheckInOuts")	
	public Map<String,Object> getCheckInOuts(HttpServletResponse response,HttpServletRequest request){
		CheckInOutEntity checkInOutEntity=checkInOutService.getLastOne();
		Integer count=checkInOutService.getCount();
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("count", count);
		if(checkInOutEntity!=null) {
			map.put("lastTime", checkInOutEntity.getCheckTime());
		}else {
			map.put("lastTime","");
		}
		return map;
	}
    @XcxLogin
	@RequestMapping("insertCheckInOuts")	
	public void insertCheckInOuts(HttpServletResponse response,@RequestParam(value = "checkInOuts") String checkInOuts,@RequestParam(value = "startTime",required = false) Date startTime) throws ParseException{
		if(startTime!=null) {
			checkInOutService.delCheckInOuts(startTime);
		}	
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		JSONArray checkInOutArray=JSONArray.parseArray(checkInOuts);
			for (Object checkInOutObject : checkInOutArray) {
				JSONObject checkInOutJson=JSONObject.parseObject(checkInOutObject.toString());
				Integer USERID=checkInOutJson.getInteger("USERID");
				String CHECKTIME=checkInOutJson.getString("CHECKTIME");
				String CHECKTYPE=checkInOutJson.getString("CHECKTYPE");
				Integer VERIFYCODE=checkInOutJson.getInteger("VERIFYCODE");
				String SENSORID=checkInOutJson.getString("SENSORID");
				String Memoinfo=checkInOutJson.getString("Memoinfo");
				String WorkCode=checkInOutJson.getString("WorkCode");
				String sn=checkInOutJson.getString("sn");
				Integer UserExtFmt=checkInOutJson.getInteger("UserExtFmt");
				CheckInOutEntity checkInOutEntity=new CheckInOutEntity();
				checkInOutEntity.setCheckTime(sdf.parse(CHECKTIME));
				checkInOutEntity.setCheckType(CHECKTYPE);
				checkInOutEntity.setMemoinfo(Memoinfo);
				checkInOutEntity.setSensorId(SENSORID);
				checkInOutEntity.setSn(sn);
				checkInOutEntity.setUserExtFmt(UserExtFmt);
				checkInOutEntity.setUserId(USERID);
				checkInOutEntity.setVerifyCode(VERIFYCODE);
				checkInOutEntity.setWorkCode(WorkCode);
				checkInOutService.insert(checkInOutEntity);
				
				Calendar c=Calendar.getInstance();
				c.setTime(sdf.parse(CHECKTIME));
				Integer week=c.get(Calendar.DAY_OF_WEEK);
				//判断是否为周六
				if(week!=Calendar.SATURDAY&&week!=Calendar.SUNDAY) {
					Integer count=userIntegralService.getCheckInByTime(USERID, CHECKTIME);
					//判断今日是否保存数据
					if(count==0||count==null) {
						c.set(Calendar.HOUR_OF_DAY, 8);
						c.set(Calendar.MINUTE, 30);
						String	dateTime=CHECKTIME;
						//判断是否为8:30之前
						if(sdf.parse(CHECKTIME).getTime()<=c.getTime().getTime()) {
							 //判断今天是否为星期一
							UserIntegralEntity integralEntity=new UserIntegralEntity();
							if(week==Calendar.MONDAY) {
								//如果为星期一则获取上周五的连续签到次数
								c.add(Calendar.DATE, -3);
								Date d=c.getTime();
								dateTime=sdf.format(d);
							}else {
								//根据userId获取昨日连续次数   
								c.add(Calendar.DATE, -1);
								Date d=c.getTime();
								dateTime=sdf.format(d);	
								 integralEntity=userIntegralService.getOnebyTime(USERID, dateTime);
							}
							//判断是否为空，为空设为1次，不为空在前一个基础加1次，并进行积分规则计算积分
							 Integer countinueIntegral=0;
							 Integer integralTotal=0;
							if(integralEntity==null) {//未连续签到
								integralTotal=5;
							}else {
								countinueIntegral=integralEntity.getIntegral();
								if(countinueIntegral==5) {//连续签到1天
									integralTotal=8;
								}else if(countinueIntegral==8){//连续签到2天
									integralTotal=10;
								}else if(countinueIntegral==10){//其他连续签到
									integralTotal=20;
								}else if(countinueIntegral==20) {
									integralTotal=30;
								}else if(countinueIntegral==30) {
									integralTotal=30;
								}else{
									integralTotal=5;
								}
							}
								UserIntegralEntity inEntity=new UserIntegralEntity();
							 inEntity.setDel(0);
							 inEntity.setIntegral(integralTotal);
							 inEntity.setIntegralType(1);
							 inEntity.setUserSeq(USERID);
							 inEntity.setRemark("早上准点打卡得积分"+integralTotal+"分");
							 inEntity.setInputTime(checkInOutEntity.getCheckTime());
							 userIntegralService.insert(inEntity);
						}
						Calendar c2=Calendar.getInstance();
						c2.setTime(sdf.parse(CHECKTIME));
						c2.set(Calendar.HOUR_OF_DAY, 8);
						c2.set(Calendar.MINUTE, 45);
						//判断是否为8:45之后
						if(sdf.parse(CHECKTIME).getTime()>c2.getTime().getTime()) {
							Integer lateCount=userIntegralService.getLateCount(USERID, dateTime);
							 Integer integralTotal=0;
							if(lateCount>0) {
								integralTotal=-50;
							}else {
								integralTotal=-10;
							}
							UserIntegralEntity inEntity=new UserIntegralEntity();
							 inEntity.setDel(0);
							 inEntity.setIntegral(integralTotal);
							 inEntity.setIntegralType(1);
							 inEntity.setUserSeq(USERID);
							 inEntity.setRemark("早上迟到扣除"+integralTotal+"分");
							 inEntity.setInputTime(checkInOutEntity.getCheckTime());
							 userIntegralService.insert(inEntity);
						}
						
						
					}
				}
			}
			
	}
	
	
}
