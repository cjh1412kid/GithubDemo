package io.nuite.modules.small_integral.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;

import io.nuite.common.utils.PageUtils;
import io.nuite.modules.small_integral.entity.UserIntegralEntity;

public interface UserIntegralService extends IService<UserIntegralEntity> {

	PageUtils queryPage(Map<String, Object> params);

	UserIntegralEntity getOnebyTime(Integer userId, String date);

	List<UserIntegralEntity> getListByTime(Integer userId, String date);

	Integer getCountByTime(Integer userId, String date);

	Integer getCountByWeek(Integer userId, String date);

	Integer getCountByMonth(Integer userId, String date);

	Integer getCount(Integer userId);
	
	Integer getLateCount(Integer userId, String date);
	
	Integer getCountByOnTime(Integer userId, String date);
	
	Integer getCheckInByTime(Integer userId, String date);
	
	UserIntegralEntity getByParam(UserIntegralEntity userIntegralEntity);
}
