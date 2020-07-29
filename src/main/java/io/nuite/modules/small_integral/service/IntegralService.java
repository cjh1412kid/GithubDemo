package io.nuite.modules.small_integral.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;

import io.nuite.common.utils.PageUtils;
import io.nuite.modules.small_integral.entity.IntegralEntity;

public interface IntegralService extends IService<IntegralEntity> {

	PageUtils queryPage(Map<String, Object> params);

	IntegralEntity getOnebyTime(Integer userId, String date);

	List<IntegralEntity> getListByTime(Integer userId, String date);

	Integer getCountByTime(Integer userId, String date);

	Integer getCountByWeek(Integer userId, String date);

	Integer getCountByMonth(Integer userId, String date);

	Integer getCount(Integer userId);
}
