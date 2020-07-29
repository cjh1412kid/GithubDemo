package io.nuite.modules.small_integral.service;

import java.util.Map;

import com.baomidou.mybatisplus.service.IService;

import io.nuite.common.utils.PageUtils;
import io.nuite.modules.small_integral.entity.UserUserEntity;


public interface UserUserService extends IService<UserUserEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
    UserUserEntity getOneByUnionId(String unionId);
    
    Integer addUser(UserUserEntity authorizeUserEntity);
    
    
}

