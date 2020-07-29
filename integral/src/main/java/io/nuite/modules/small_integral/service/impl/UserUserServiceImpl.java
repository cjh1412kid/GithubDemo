package io.nuite.modules.small_integral.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import io.nuite.common.utils.PageUtils;
import io.nuite.common.utils.Query;
import io.nuite.modules.small_integral.dao.UserUserDao;
import io.nuite.modules.small_integral.entity.UserUserEntity;
import io.nuite.modules.small_integral.service.UserUserService;


@Service
public class UserUserServiceImpl extends ServiceImpl<UserUserDao, UserUserEntity> implements UserUserService {

	@Autowired
	private UserUserDao userUserDao;
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<UserUserEntity> page = this.selectPage(
                new Query<UserUserEntity>(params).getPage(),
                new EntityWrapper<UserUserEntity>()
        );

        return new PageUtils(page);
    }

	@Override
	public UserUserEntity getOneByUnionId(String unionId) {
		UserUserEntity authorizeUserEntity=new UserUserEntity();
		authorizeUserEntity.setUnionId(unionId);
		authorizeUserEntity=userUserDao.selectOne(authorizeUserEntity);
		return authorizeUserEntity;
	}

	@Override
	public Integer addUser(UserUserEntity authorizeUserEntity) {
		Integer id=userUserDao.insert(authorizeUserEntity);
		return id;
	}

}
