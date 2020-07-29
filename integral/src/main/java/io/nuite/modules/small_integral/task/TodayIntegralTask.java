package io.nuite.modules.small_integral.task;

import java.text.ParseException;
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
import io.nuite.modules.small_integral.entity.UserInfoEntity;
import io.nuite.modules.small_integral.entity.UserIntegralEntity;
import io.nuite.modules.small_integral.service.CheckInOutService;
import io.nuite.modules.small_integral.service.UserInfoService;
import io.nuite.modules.small_integral.service.UserIntegralService;

@Component("TodayIntegralTask") 
@EnableScheduling
public class TodayIntegralTask {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private CheckInOutService checkInOutService;
	
	@Autowired
	private UserIntegralService userIntegralService;
	
	@Autowired
	private UserInfoService userInfoService;
	
//	/**
//	 * 早上准时打卡记录积分同步
//	 */
//	@Scheduled(cron = "0 35 08 ? * *" )
//	public void getTodayCheckIntegral() {
//		logger.info("进入方法");
//		Date date=new Date();
//		Calendar c=Calendar.getInstance();
//		c.setTime(date);
//		Integer week=c.get(Calendar.DAY_OF_WEEK);
//		//获取今日在8:30前打卡的记录
//		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
//		String dateTime=sdf.format(date);
//		String beginTime="00:00";
//		String endTime="08:30";
//		List<CheckInOutEntity> checkInOuts=checkInOutService.getTodayCheckInList(dateTime, beginTime, endTime);
//		for (CheckInOutEntity checkInOutEntity : checkInOuts) {
//			Integer userId=checkInOutEntity.getUserId();
//			 //判断今天是否为星期一
//			UserIntegralEntity integralEntity=new UserIntegralEntity();
//			if(week==2) {
//				//如果为星期一则获取上周五的连续签到次数
//				c.add(Calendar.DATE, -3);
//				Date d=c.getTime();
//				dateTime=sdf.format(d);
//			}else {
//				//根据userId获取昨日连续次数   
//				c.add(Calendar.DATE, -1);
//				Date d=c.getTime();
//				dateTime=sdf.format(d);				
//			}
//			 integralEntity=userIntegralService.getOnebyTime(userId, dateTime);
//			//判断是否为空，为空设为1次，不为空在前一个基础加1次，并进行积分规则计算积分
//			 Integer countinueIntegral=0;
//			 Integer integralTotal=0;
//			if(integralEntity==null) {//未连续签到
//				integralTotal=5;
//			}else {
//				countinueIntegral=integralEntity.getIntegral();
//				if(countinueIntegral==5) {//连续签到1天
//					integralTotal=8;
//				}else if(countinueIntegral==8){//连续签到2天
//					integralTotal=10;
//				}else if(countinueIntegral==10){//其他连续签到
//					integralTotal=20;
//				}else if(countinueIntegral==25) {
//					integralTotal=25;
//				}
//			}
//				UserIntegralEntity inEntity=new UserIntegralEntity();
//			 inEntity.setDel(0);
//			 inEntity.setIntegral(integralTotal);
//			 inEntity.setIntegralType(1);
//			 inEntity.setUserSeq(userId);
//			 inEntity.setRemark("早上准点打卡得积分"+integralTotal+"分");
//			 inEntity.setInputTime(checkInOutEntity.getCheckTime());
//			 userIntegralService.insert(inEntity);
//		}
//	}
//	
//	
//	/**
//	 * 早上准时打卡记录积分同步
//	 */
//	@Scheduled(cron = "0 50 08 ? * *" )
//	public void getTodayCheckItegral() {
//		logger.info("进入方法");
//		Date date=new Date();
//		Calendar c=Calendar.getInstance();
//		c.setTime(date);
//		//获取今日在8:30前打卡的记录
//		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
//		String dateTime=sdf.format(date);
//		String beginTime="00:00";
//		String endTime="08:45";
//		List<CheckInOutEntity> checkInOuts=checkInOutService.getTodayCheckInList(dateTime, beginTime, endTime);
//		List<UserInfoEntity> userList =userInfoService.getAllList();
//		for (UserInfoEntity userInfoEntity : userList) {
//			Boolean flag=false;
//			Integer userSeq=userInfoEntity.getUserId();
//			for (CheckInOutEntity checkInOutEntity : checkInOuts) {
//				Integer checkUserSeq=checkInOutEntity.getUserId();
//				if(userSeq==checkUserSeq) {
//					flag=true;
//					break;
//				}
//			}
//			if(!flag) {
//				//查看当月是否有迟到
//			Integer lateCount=userIntegralService.getLateCount(userSeq, dateTime);
//			 Integer integralTotal=0;
//			if(lateCount>0) {
//				integralTotal=-50;
//			}else {
//				integralTotal=-10;
//			}
//			UserIntegralEntity inEntity=new UserIntegralEntity();
//			 inEntity.setDel(0);
//			 inEntity.setIntegral(integralTotal);
//			 inEntity.setIntegralType(1);
//			 inEntity.setUserSeq(userSeq);
//			 inEntity.setRemark("早上迟到扣除"+integralTotal+"分");
//			 inEntity.setInputTime(new Date());
//			 userIntegralService.insert(inEntity);
//			}
//			
//			
//		}
//	}

	
	/**
	 * 晚上准时打卡积分同步
	 * @throws ParseException 
	 */
	@Scheduled(cron = "0 0 0 ? * *" )
	public void getTodayCheckOuttegral() throws ParseException {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -1); //得到前一天
		Date date = calendar.getTime();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String dateTime=sdf.format(date);
		Integer week=calendar.get(Calendar.DAY_OF_WEEK);
		if(week==Calendar.SATURDAY||week==Calendar.SUNDAY) {
			return;
		}
		//获取今日最后一次打卡的记录
		List<CheckInOutEntity> checkInOuts=checkInOutService.getTodayCheckOutList(dateTime);
		for (CheckInOutEntity checkInOutEntity : checkInOuts) {
			Date checkTime=checkInOutEntity.getCheckTime();
			calendar.set(Calendar.HOUR_OF_DAY, 17);
			calendar.set(Calendar.MINUTE, 30);
			Date checkOutTime=calendar.getTime();
			//判断最后一次打卡是否小于5点半,添加早退记录积分
			if(checkTime.getTime()<checkOutTime.getTime()) {
				UserIntegralEntity integralEntity=new UserIntegralEntity();
				integralEntity.setDel(0);
				Integer todayIntegral=userIntegralService.getCountByOnTime(checkInOutEntity.getUserId(), dateTime);
				integralEntity.setIntegral(0-todayIntegral);
				integralEntity.setIntegralType(1);
				integralEntity.setUserSeq(checkInOutEntity.getUserId());
				integralEntity.setRemark("早退扣除积分"+todayIntegral+"分");
				integralEntity.setInputTime(checkInOutEntity.getCheckTime());
				 userIntegralService.insert(integralEntity);
			}
		}
		List<UserInfoEntity> userList =userInfoService.getAllList();
		for (UserInfoEntity userInfoEntity : userList) {
			Boolean flag=false;
			Integer userSeq=userInfoEntity.getUserId();
			for (CheckInOutEntity checkInOutEntity : checkInOuts) {
				Integer checkUserSeq=checkInOutEntity.getUserId();
				if(userSeq==checkUserSeq) {
					flag=true;
					break;
				}
			}
			if(!flag) {
				//查看当月是否有迟到
			Integer lateCount=userIntegralService.getLateCount(userSeq, dateTime);
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
			 inEntity.setUserSeq(userSeq);
			 inEntity.setRemark("今日未打卡"+integralTotal+"分");
			 inEntity.setInputTime(sdf.parse(dateTime));
			 userIntegralService.insert(inEntity);
			}
		}
		
		
	}

	public void getCheckIntegralAll() throws ParseException, InterruptedException {
		String newDate="2019-05";
		for (int i = 1; i <=27; i++) {
		String	dateTime=newDate+"-"+i;
	
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Calendar c=Calendar.getInstance();
		c.setTime(sdf.parse(dateTime));
		Integer week=c.get(Calendar.DAY_OF_WEEK);
		if(week==Calendar.SATURDAY||week==Calendar.SUNDAY) {
			continue;
		}
		String beginTime="00:00";
		String endTime="24:00";
		List<CheckInOutEntity> checkInOuts=checkInOutService.getTodayCheckInList(dateTime, beginTime, endTime);
		for (CheckInOutEntity checkInOutEntity : checkInOuts) {
			Integer userId=checkInOutEntity.getUserId();
			Date date=checkInOutEntity.getCheckTime();
			c.setTime(date);
			c.set(Calendar.HOUR_OF_DAY, 8);
			c.set(Calendar.MINUTE, 30);
			if(date.getTime()<=c.getTime().getTime()) {
				 //判断今天是否为星期一
				UserIntegralEntity integralEntity=null;
				if(week==Calendar.MONDAY) {
					//如果为星期一
				
				}else {
					//根据userId获取昨日连续次数   
					c.add(Calendar.DATE, -1);
					Date d=c.getTime();
					dateTime=sdf.format(d);		
					 integralEntity=userIntegralService.getOnebyTime(userId, dateTime);
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
						integralTotal=25;
					}else if(countinueIntegral==25) {
						integralTotal=25;
					}else{
						integralTotal=5;
					}
				}
					UserIntegralEntity inEntity=new UserIntegralEntity();
				 inEntity.setDel(0);
				 inEntity.setIntegral(integralTotal);
				 inEntity.setIntegralType(1);
				 inEntity.setUserSeq(userId);
				 inEntity.setRemark("早上准点打卡得积分"+integralTotal+"分");
				 inEntity.setInputTime(checkInOutEntity.getCheckTime());
				 UserIntegralEntity inUserIntegralEntity=userIntegralService.getByParam(inEntity);
				 if(inUserIntegralEntity==null) {
					 userIntegralService.insert(inEntity); 
				 }
				 
			}
			Calendar c2=Calendar.getInstance();
			c2.setTime(date);
			c2.set(Calendar.HOUR_OF_DAY, 8);
			c2.set(Calendar.MINUTE, 45);
			logger.info(date.getTime()+"-----------"+c.getTime().getTime()+(date.getTime()>c2.getTime().getTime()));
			if(date.getTime()>c2.getTime().getTime()) {
				Integer lateCount=userIntegralService.getLateCount(userId, dateTime);
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
				 inEntity.setUserSeq(userId);
				 inEntity.setRemark("早上迟到扣除"+integralTotal+"分");
				 inEntity.setInputTime(checkInOutEntity.getCheckTime());
				 UserIntegralEntity inUserIntegralEntity=userIntegralService.getByParam(inEntity);
				 if(inUserIntegralEntity==null) {
					 userIntegralService.insert(inEntity); 
				 }
			}
			Thread.sleep(50);
		}
		List<UserInfoEntity> userList =userInfoService.getAllList();
		for (UserInfoEntity userInfoEntity : userList) {
			Boolean flag=false;
			Integer userSeq=userInfoEntity.getUserId();
			for (CheckInOutEntity checkInOutEntity : checkInOuts) {
				Integer checkUserSeq=checkInOutEntity.getUserId();
				if(userSeq==checkUserSeq) {
					flag=true;
					break;
				}
			}
			if(!flag) {
				//查看当月是否有迟到
			Integer lateCount=userIntegralService.getLateCount(userSeq, dateTime);
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
			 inEntity.setUserSeq(userSeq);
			 inEntity.setRemark("早上未打卡"+integralTotal+"分");
			 inEntity.setInputTime(sdf.parse(dateTime));
			 UserIntegralEntity inUserIntegralEntity=userIntegralService.getByParam(inEntity);
			 if(inUserIntegralEntity==null) {
				 userIntegralService.insert(inEntity); 
			 }
			}
			Thread.sleep(50);
		}
		
		List<CheckInOutEntity> checkInOutList=checkInOutService.getTodayCheckOutList(dateTime);
		for (CheckInOutEntity checkInOutEntity : checkInOutList) {
			Date checkTime=checkInOutEntity.getCheckTime();
			c.set(Calendar.HOUR_OF_DAY, 17);
			c.set(Calendar.MINUTE, 30);
			Date checkOutTime=c.getTime();
			//判断最后一次打卡是否小于5点半,添加早退记录积分
			if(checkTime.getTime()<checkOutTime.getTime()) {
				UserIntegralEntity integralEntity=new UserIntegralEntity();
				integralEntity.setDel(0);
				Integer todayIntegral=userIntegralService.getCountByOnTime(checkInOutEntity.getUserId(), dateTime);
				if(todayIntegral==null) {
					integralEntity.setIntegral(0);
					integralEntity.setRemark("早退扣除积分"+0+"分");
				}else {
					integralEntity.setIntegral(0-todayIntegral);	
					integralEntity.setRemark("早退扣除积分"+todayIntegral+"分");
				}
				integralEntity.setIntegralType(1);
				integralEntity.setUserSeq(checkInOutEntity.getUserId());
			
				integralEntity.setInputTime(checkInOutEntity.getCheckTime());
				 UserIntegralEntity inUserIntegralEntity=userIntegralService.getByParam(integralEntity);
				 if(inUserIntegralEntity==null) {
					 userIntegralService.insert(integralEntity); 
				 }
			}
			Thread.sleep(50);
		}
	}
	} 
}
