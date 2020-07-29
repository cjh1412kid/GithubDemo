package io.nuite.modules.small_integral.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;

import io.nuite.common.utils.PageUtils;
import io.nuite.modules.small_integral.entity.CheckInOutEntity;


public interface CheckInOutService extends IService<CheckInOutEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
	//获取今日积分
	Integer getTodyIntegral(String date,Integer userId);
	
	List<CheckInOutEntity> getTodayCheckInList(String dateTime,String beginTime,String endTime);
	
	List<CheckInOutEntity> getTodayCheckOutList(String dateTime);
	
	CheckInOutEntity getLastOne();
	
	Integer getCount();
	
	Integer delCheckInOuts(Date startTime);
	
	Integer insertCheckInout(CheckInOutEntity checkInOutEntity);
}

