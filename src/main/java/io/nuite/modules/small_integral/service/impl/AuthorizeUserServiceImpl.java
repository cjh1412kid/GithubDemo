package io.nuite.modules.small_integral.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import io.nuite.common.utils.PageUtils;
import io.nuite.common.utils.Query;
import io.nuite.modules.small_integral.dao.AuthorizeUserDao;
import io.nuite.modules.small_integral.entity.AuthorizeUserEntity;
import io.nuite.modules.small_integral.service.AuthorizeUserService;


@Service
public class AuthorizeUserServiceImpl extends ServiceImpl<AuthorizeUserDao, AuthorizeUserEntity> implements AuthorizeUserService {

	@Autowired
	private AuthorizeUserDao authorizeUserDao;
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<AuthorizeUserEntity> page = this.selectPage(
                new Query<AuthorizeUserEntity>(params).getPage(),
                new EntityWrapper<AuthorizeUserEntity>()
        );

        return new PageUtils(page);
    }

	@Override
	public AuthorizeUserEntity getOneByUnionId(String unionId) {
		AuthorizeUserEntity authorizeUserEntity=new AuthorizeUserEntity();
		authorizeUserEntity.setUnionId(unionId);
		authorizeUserEntity=authorizeUserDao.selectOne(authorizeUserEntity);
		return authorizeUserEntity;
	}

	@Override
	public Integer addUser(AuthorizeUserEntity authorizeUserEntity) {
		Integer id=authorizeUserDao.insert(authorizeUserEntity);
		return id;
	}

}
