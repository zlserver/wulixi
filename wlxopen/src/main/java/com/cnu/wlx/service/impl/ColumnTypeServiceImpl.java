package com.cnu.wlx.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cnu.wlx.dao.ColumnTypeDao;
import com.cnu.wlx.service.ColumnTypeService;

/**
* @author 周亮 
* @version 创建时间：2016年4月13日 下午4:01:09
* 类说明
*/
@Service("ColumnTypeService")    
public class ColumnTypeServiceImpl implements ColumnTypeService {

	private ColumnTypeDao columnTypeDao;
	
	
	
	public ColumnTypeDao getColumnTypeDao() {
		return columnTypeDao;
	}
	@Resource
	public void setColumnTypeDao(ColumnTypeDao columnTypeDao) {
		this.columnTypeDao = columnTypeDao;
	}
	
}
