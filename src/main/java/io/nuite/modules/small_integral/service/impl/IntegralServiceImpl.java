package io.nuite.modules.small_integral.service.impl;

import java.util.Date;
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
import io.nuite.modules.small_integral.dao.IntegralDao;
import io.nuite.modules.small_integral.entity.IntegralEntity;
import io.nuite.modules.small_integral.service.IntegralService;


@Service
public class IntegralServiceImpl extends ServiceImpl<IntegralDao, IntegralEntity> implements IntegralService {

	@Autowired
	private IntegralDao integralDao;
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<IntegralEntity> page = this.selectPage(
                new Query<IntegralEntity>(params).getPage(),
                new EntityWrapper<IntegralEntity>()
        );

        return new PageUtils(page);
    }

	@Override
	public IntegralEntity getOnebyTime(Integer userId, String date) {
		return integralDao.getOnebyTime(userId, date);
	}

	@Override
	public List<IntegralEntity> getListByTime(Integer userId, String date) {
		return integralDao.getListByTime(userId, date);
	}

	@Override
	public Integer getCountByTime(Integer userId, String date) {
		return integralDao.getCountByTime(userId, date);
	}

	@Override
	public Integer getCountByWeek(Integer userId, String date) {
		return integralDao.getCountByWeek(userId, date);
	}

	@Override
	public Integer getCountByMonth(Integer userId, String date) {
		return integralDao.getCountByMonth(userId, date);
	}

	@Override
	public Integer getCount(Integer userId) {
		return integralDao.getCount(userId);
	}



}
