package io.nuite.modules.small_integral.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.nuite.common.utils.R;
import io.nuite.modules.small_integral.entity.AuthorizeUserEntity;
import io.nuite.modules.small_integral.entity.IntegralEntity;
import io.nuite.modules.small_integral.service.AuthorizeUserService;
import io.nuite.modules.small_integral.service.IntegralService;

@RestController
@RequestMapping(value="/integral")
public class SmallIntegralController {
	
	@Autowired
	private IntegralService integralService;

	@Autowired
	private AuthorizeUserService authorizeUserService;
	
	@RequestMapping(value = "/getIntegral")
	public R getIntegral( HttpServletResponse response,
		   String dateTime,Integer userSeq) {
		AuthorizeUserEntity authorizeUserEntity=authorizeUserService.selectById(userSeq);
		//通过授权Id获取用户userId
		Integer userId=authorizeUserEntity.getUserId();
		
		//2.获取今日积分
		Integer todayIntegral=integralService.getCountByTime(userId, dateTime);
		if(todayIntegral==null) {
			todayIntegral=0;
		}
		//3.本周积分 
		Integer weekIntegral=integralService.getCountByWeek(userId, dateTime);
		if(weekIntegral==null) {
			weekIntegral=0;
		}
		//4.本月积分
		Integer monthIntegral=integralService.getCountByMonth(userId, dateTime);
		if(monthIntegral==null) {
			monthIntegral=0;
		}
		//5.累计积分
		Integer integralCount=integralService.getCount(userId);
		if(integralCount==null) {
			integralCount=0;
		}
		//6.今日积分详情
		List<IntegralEntity> integrals=integralService.getListByTime(userId, dateTime);
		R r=R.ok();
		r.put("todayIntegral", todayIntegral);
		r.put("weekIntegral", weekIntegral);
		r.put("monthIntegral", monthIntegral);
		r.put("integrals", integrals);
		r.put("integralCount", integralCount);
		return r;
	}
}
