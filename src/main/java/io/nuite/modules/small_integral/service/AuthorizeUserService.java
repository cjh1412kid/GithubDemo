package io.nuite.modules.small_integral.service;

import java.util.Map;

import com.baomidou.mybatisplus.service.IService;

import io.nuite.common.utils.PageUtils;
import io.nuite.modules.small_integral.entity.AuthorizeUserEntity;


public interface AuthorizeUserService extends IService<AuthorizeUserEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
    AuthorizeUserEntity getOneByUnionId(String unionId);
    
    Integer addUser(AuthorizeUserEntity authorizeUserEntity);
    
    
}

