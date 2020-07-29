package io.nuite.modules.small_integral.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import io.nuite.common.utils.PageUtils;
import io.nuite.common.utils.Query;
import io.nuite.modules.small_integral.dao.UserInfoDao;
import io.nuite.modules.small_integral.entity.UserInfoEntity;
import io.nuite.modules.small_integral.service.UserInfoService;


@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoDao, UserInfoEntity> implements UserInfoService {

	@Autowired
	private UserInfoDao userInfoDao;
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<UserInfoEntity> page = this.selectPage(
                new Query<UserInfoEntity>(params).getPage(),
                new EntityWrapper<UserInfoEntity>()
        );

        return new PageUtils(page);
    }

	@Override
	public UserInfoEntity getOneByPhone(String phone) {
		UserInfoEntity userInfoEntity=new UserInfoEntity();
		userInfoEntity.setFphone(phone);
		userInfoEntity=userInfoDao.selectOne(userInfoEntity);
		return userInfoEntity;
	}

}
