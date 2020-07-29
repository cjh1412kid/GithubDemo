package io.nuite.modules.small_integral.dao;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;

import io.nuite.modules.small_integral.entity.UserInfoEntity;

/**
 * ${comments}
 * 
 * @author wanghao
 * @email barryhippo@163.com
 * @date 2019-05-08 18:15:01
 */
@Mapper
public interface UserInfoDao extends BaseMapper<UserInfoEntity> {
	
}
