package io.nuite.modules.small_integral.service;

import java.util.Map;

import com.baomidou.mybatisplus.service.IService;

import io.nuite.common.utils.PageUtils;
import io.nuite.modules.small_integral.entity.UserInfoEntity;


public interface UserInfoService extends IService<UserInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
    
    UserInfoEntity getOneByPhone(String phone);
}

