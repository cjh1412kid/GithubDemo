package io.nuite.modules.small_integral.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import io.nuite.common.utils.PageUtils;
import io.nuite.common.utils.Query;
import io.nuite.modules.small_integral.dao.CheckInOutDao;
import io.nuite.modules.small_integral.entity.CheckInOutEntity;
import io.nuite.modules.small_integral.service.CheckInOutService;


@Service
public class CheckInOutServiceImpl extends ServiceImpl<CheckInOutDao, CheckInOutEntity> implements CheckInOutService {

	@Autowired
	private CheckInOutDao checkInOutDao;
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<CheckInOutEntity> page = this.selectPage(
                new Query<CheckInOutEntity>(params).getPage(),
                new EntityWrapper<CheckInOutEntity>()
        );

        return new PageUtils(page);
    }
    
	@Override
	public Integer getTodyIntegral(String date, Integer userId) {
		return null;
	}

	@Override
	public List<CheckInOutEntity> getTodayCheckInList(String dateTime, String beginTime, String endTime) {
		List<CheckInOutEntity> checkInOuts=checkInOutDao.getTodayCheckInList(dateTime, beginTime, endTime);
		return checkInOuts;
	}

	@Override
	public List<CheckInOutEntity> getTodayCheckOutList(String dateTime) {
		List<CheckInOutEntity> checkInOuts=checkInOutDao.getTodayCheckOutList(dateTime);
		return checkInOuts;
	}

}
