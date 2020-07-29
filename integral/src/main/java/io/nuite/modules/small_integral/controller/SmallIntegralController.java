package io.nuite.modules.small_integral.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.nuite.common.utils.R;
import io.nuite.modules.small_integral.entity.UserIntegralEntity;
import io.nuite.modules.small_integral.entity.UserUserEntity;
import io.nuite.modules.small_integral.service.UserIntegralService;
import io.nuite.modules.small_integral.service.UserUserService;

@RestController
@RequestMapping(value="/integral")
public class SmallIntegralController {
	
	@Autowired
	private UserIntegralService userIntegralService;

	@Autowired
	private UserUserService userUserService;
	
	@RequestMapping(value = "/getIntegral")
	public R getIntegral( HttpServletResponse response,
		   String dateTime,Integer userSeq) {
		UserUserEntity userUserEntity=userUserService.selectById(userSeq);
		//通过授权Id获取用户userId
		Integer userId=userUserEntity.getZKTimeUserId();
		
		//2.获取今日积分
		Integer todayIntegral=userIntegralService.getCountByTime(userId, dateTime);
		if(todayIntegral==null) {
			todayIntegral=0;
		}
		//3.本周积分 
		Integer weekIntegral=userIntegralService.getCountByWeek(userId, dateTime);
		if(weekIntegral==null) {
			weekIntegral=0;
		}
		//4.本月积分
		Integer monthIntegral=userIntegralService.getCountByMonth(userId, dateTime);
		if(monthIntegral==null) {
			monthIntegral=0;
		}
		//5.累计积分
		Integer integralCount=userIntegralService.getCount(userId);
		if(integralCount==null) {
			integralCount=0;
		}
		//6.今日积分详情
		List<UserIntegralEntity> integrals=userIntegralService.getListByTime(userId, dateTime);
		R r=R.ok();
		r.put("todayIntegral", todayIntegral);
		r.put("weekIntegral", weekIntegral);
		r.put("monthIntegral", monthIntegral);
		r.put("integrals", integrals);
		r.put("integralCount", integralCount);
		return r;
	}
}
