package com.cnu.wlx.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cnu.wlx.bean.VistRecord;
import com.cnu.wlx.dao.VistRecordDao;
import com.cnu.wlx.service.VistRecordService;

/**
* @author 周亮 
* @version 创建时间：2016年7月5日 下午8:40:28
* 类说明
*/
@Service("vistRecordService")
public class VistRecordServiceImpl implements VistRecordService {

	private VistRecordDao vistRecordDao;

	@Override
	public void save(VistRecord vistRecord) {
		// TODO Auto-generated method stub
		vistRecordDao.save(vistRecord);
	}
	@Override
	public long getCount() {
		
		return vistRecordDao.getCount();
	}
	
	
	public VistRecordDao getVistRecordDao() {
		return vistRecordDao;
	}
	@Resource
	public void setVistRecordDao(VistRecordDao vistRecordDao) {
		this.vistRecordDao = vistRecordDao;
	}
	
}
