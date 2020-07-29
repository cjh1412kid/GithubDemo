package io.nuite.modules.small_integral.task;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import io.nuite.modules.small_integral.entity.CheckInOutEntity;
import io.nuite.modules.small_integral.entity.IntegralEntity;
import io.nuite.modules.small_integral.service.CheckInOutService;
import io.nuite.modules.small_integral.service.IntegralService;

@Component("TodayIntegralTask") 
@EnableScheduling
public class TodayIntegralTask {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private CheckInOutService checkInOutService;
	
	@Autowired
	private IntegralService integralService;
	
	/**
	 * 早上准时打卡记录积分同步
	 */
	@Scheduled(cron = "0 30 08 ? * *" )
	public void getTodayCheckIntegral() {
		logger.info("进入方法");
		Date date=new Date();
		Calendar c=Calendar.getInstance();
		c.setTime(date);
		Integer week=c.get(Calendar.DAY_OF_WEEK);
		//获取今日在8:30前打卡的记录
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String dateTime=sdf.format(date);
		String beginTime="00:00";
		String endTime="08:30";
		List<CheckInOutEntity> checkInOuts=checkInOutService.getTodayCheckInList(dateTime, beginTime, endTime);
		for (CheckInOutEntity checkInOutEntity : checkInOuts) {
			Integer userId=checkInOutEntity.getUserId();
			 //判断今天是否为星期一
			IntegralEntity integralEntity=new IntegralEntity();
			if(week==2) {
				//如果为星期一则获取上周五的连续签到次数
				c.add(Calendar.DATE, -3);
				Date d=c.getTime();
				dateTime=sdf.format(d);
			}else {
				//根据userId获取昨日连续次数   
				c.add(Calendar.DATE, -1);
				Date d=c.getTime();
				dateTime=sdf.format(d);				
			}
			 integralEntity=integralService.getOnebyTime(userId, dateTime);
			//判断是否为空，为空设为1次，不为空在前一个基础加1次，并进行积分规则计算积分
			 Integer countinueDays=0;
			 Integer integralTotal=0;
			if(integralEntity==null) {//未连续签到
				countinueDays=1;
				integralTotal=10;
			}else {
				countinueDays=integralEntity.getContinuous()+1;
				if(integralEntity.getContinuous()==1) {//连续签到1天
					integralTotal=15;
				}else if(integralEntity.getContinuous()==2){//连续签到2天
					integralTotal=20;
				}else {//其他连续签到
					integralTotal=25;
				}
			}
			 IntegralEntity inEntity=new IntegralEntity();
			 inEntity.setContinuous(countinueDays);
			 inEntity.setDel(0);
			 inEntity.setIntegralTotal(integralTotal);
			 inEntity.setIntegralType(1);
			 inEntity.setUserId(userId);
			 inEntity.setFinishTime(checkInOutEntity.getCheckTime());
			 integralService.insert(inEntity);
		}
	}
	

	
	
	
	/**
	 * 晚上准时打卡积分同步
	 */
	@Scheduled(cron = "0 0 0 ? * *" )
	public void getTodayCheckOuttegral() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -1); //得到前一天
		Date date = calendar.getTime();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String dateTime=sdf.format(date);
		//获取今日最后一次打卡的记录
		List<CheckInOutEntity> checkInOuts=checkInOutService.getTodayCheckOutList(dateTime);
		for (CheckInOutEntity checkInOutEntity : checkInOuts) {
			Date checkTime=checkInOutEntity.getCheckTime();
			calendar.set(Calendar.HOUR_OF_DAY, 17);
			calendar.set(Calendar.MINUTE, 30);
			Date checkOutTime=calendar.getTime();
			//判断最后一次打卡是否小于5点半,添加早退记录积分
			if(checkTime.getTime()<checkOutTime.getTime()) {
				 IntegralEntity integralEntity=new IntegralEntity();
				 integralEntity.setContinuous(0);
				 integralEntity.setDel(0);
				 integralEntity.setIntegralTotal(-15);
				 integralEntity.setIntegralType(4);
				 integralEntity.setFinishTime(checkInOutEntity.getCheckTime());
				 integralService.insert(integralEntity);
			}
		}
		
		
	}
}
