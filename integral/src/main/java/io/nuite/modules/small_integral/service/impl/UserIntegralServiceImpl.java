package io.nuite.modules.small_integral.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import io.nuite.common.utils.PageUtils;
import io.nuite.common.utils.Query;
import io.nuite.modules.small_integral.dao.UserIntegralDao;
import io.nuite.modules.small_integral.entity.UserIntegralEntity;
import io.nuite.modules.small_integral.service.UserIntegralService;


@Service
public class UserIntegralServiceImpl extends ServiceImpl<UserIntegralDao, UserIntegralEntity> implements UserIntegralService {

	@Autowired
	private UserIntegralDao userIntegralDao;
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<UserIntegralEntity> page = this.selectPage(
                new Query<UserIntegralEntity>(params).getPage(),
                new EntityWrapper<UserIntegralEntity>()
        );

        return new PageUtils(page);
    }

	@Override
	public UserIntegralEntity getOnebyTime(Integer userId, String date) {
		return userIntegralDao.getOnebyTime(userId, date);
	}

	@Override
	public List<UserIntegralEntity> getListByTime(Integer userId, String date) {
		return userIntegralDao.getListByTime(userId, date);
	}

	@Override
	public Integer getCountByTime(Integer userId, String date) {
		return userIntegralDao.getCountByTime(userId, date);
	}

	@Override
	public Integer getCountByWeek(Integer userId, String date) {
		return userIntegralDao.getCountByWeek(userId, date);
	}

	@Override
	public Integer getCountByMonth(Integer userId, String date) {
		return userIntegralDao.getCountByMonth(userId, date);
	}

	@Override
	public Integer getCount(Integer userId) {
		return userIntegralDao.getCount(userId);
	}

	@Override
	public Integer getLateCount(Integer userId, String date) {
		
		return userIntegralDao.getLate(userId, date);
	}

	@Override
	public Integer getCountByOnTime(Integer userId, String date) {
		return userIntegralDao.getCountByOnTime(userId, date);
	}

	@Override
	public Integer getCheckInByTime(Integer userId, String date) {
		return userIntegralDao.getCheckInByTime(userId, date);
	}

	@Override
	public UserIntegralEntity getByParam(UserIntegralEntity userIntegralEntity) {
		return userIntegralDao.getByParam(userIntegralEntity);
	}



}
