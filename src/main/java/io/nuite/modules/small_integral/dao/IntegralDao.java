package io.nuite.modules.small_integral.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;

import io.nuite.modules.small_integral.entity.IntegralEntity;

/**
 * ${comments}
 * 
 * @author wanghao
 * @email barryhippo@163.com
 * @date 2019-05-10 09:24:44
 */
@Mapper
public interface IntegralDao extends BaseMapper<IntegralEntity> {
	
	   IntegralEntity getOnebyTime(@Param("userId")Integer userId,@Param("date")String date);
	   
	   List<IntegralEntity> getListByTime(@Param("userId")Integer userId,@Param("date")String date);
	   
	   Integer getCountByTime(@Param("userId")Integer userId,@Param("date")String date);
	   
	   Integer getCountByWeek(@Param("userId")Integer userId,@Param("date")String date);
	   
	   Integer getCountByMonth(@Param("userId")Integer userId,@Param("date")String date);
	   
	   Integer getCount(@Param("userId")Integer userId);
}
