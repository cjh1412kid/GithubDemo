package io.nuite.modules.small_integral.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;

import io.nuite.modules.small_integral.entity.CheckInOutEntity;;

/**
 * ${comments}
 * 
 * @author wanghao
 * @email barryhippo@163.com
 * @date 2019-05-08 18:14:43
 */
@Mapper
public interface CheckInOutDao extends BaseMapper<CheckInOutEntity> {
	
	List<CheckInOutEntity> getTodayCheckInList(@Param("dateTime")String dateTime, @Param("beginTime")String beginTime,@Param("endTime") String endTime);
	
	List<CheckInOutEntity> getTodayCheckOutList(@Param("dateTime")String dateTime);
}
