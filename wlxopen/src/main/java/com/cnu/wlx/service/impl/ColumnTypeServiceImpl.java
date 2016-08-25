package com.cnu.wlx.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cnu.wlx.bean.ColumnType;
import com.cnu.wlx.bean.base.QueryResult;
import com.cnu.wlx.dao.ColumnTypeDao;
import com.cnu.wlx.formbean.BaseForm;
import com.cnu.wlx.service.ColumnTypeService;

/**
* @author 周亮 
* @version 创建时间：2016年4月13日 下午4:01:09
* 类说明
*/
@Service("ColumnTypeService")    
public class ColumnTypeServiceImpl implements ColumnTypeService {
	
	private ColumnTypeDao columnTypeDao;
	
	public void addColumnType(ColumnType ct){
		if( ct!=null)
			columnTypeDao.save(ct);
		else
			throw new RuntimeException("添加栏目为空!");
	}
	
	public ColumnTypeDao getColumnTypeDao() {
		return columnTypeDao;
	}
	@Resource
	public void setColumnTypeDao(ColumnTypeDao columnTypeDao) {
		this.columnTypeDao = columnTypeDao;
	}

	
	@Override
	public List<ColumnType> getTopColumns() {
		// TODO Auto-generated method stub
		String wherejpql="o.parent is NULL";
		//结果集根据栏目的顺序升序排列
		LinkedHashMap<String,String> orderby=new LinkedHashMap<String,String>();
		orderby.put("sequence", "asc");

		//查询父类为null的所有栏目
		return columnTypeDao.getAllData(wherejpql, null,orderby);
	}

	@Override
	public QueryResult<ColumnType> getScrollData(int firstindex, int maxresult, String parentId) {

		//结果集根据栏目的顺序升序排列
		LinkedHashMap<String,String> orderby=new LinkedHashMap<String,String>();
		orderby.put("visible", "desc");
		orderby.put("sequence", "asc");

		//父类不为null
		if( BaseForm.validateStr(parentId)){
			String wherejpql="o.parent.id = ? ";
			List<Object> params = new ArrayList<Object>();
			params.add(parentId);
			return columnTypeDao.getScrollData(firstindex, maxresult, wherejpql, params.toArray(), orderby);
		}else{
			//父类为null
			String wherejpql="o.parent is NULL";
			return columnTypeDao.getScrollData(firstindex, maxresult, wherejpql, null, orderby);
		}
	}

	@Override
	public ColumnType find(String id) {
		// TODO Auto-generated method stub
		if( BaseForm.validateStr(id))
			return columnTypeDao.find(id);
		return null;
	}

	@Override
	public void delete(String id) {
		if( BaseForm.validateStr(id))
		  columnTypeDao.delete(id);
		
	}

	@Override
	public void updateColumnType(ColumnType column) {
		// TODO Auto-generated method stub
		 columnTypeDao.update(column);
	}

	@Override
	public ColumnType findByClassCode(String classCode) {
		// TODO Auto-generated method stub
		return columnTypeDao.find("o.classCode=? ", classCode);
	}

	@Override
	public List<ColumnType> getAllData(String wherejpql, Object[] queryParams, LinkedHashMap<String, String> orderby) {
		return columnTypeDao.getAllData(wherejpql, queryParams, orderby);
	}

	@Override
	public void setVisible(String id, Boolean visible) {
		// TODO Auto-generated method stub
		ColumnType columnType = columnTypeDao.find(id);
		if( columnType!=null){
			columnType.setVisible(visible);
			columnTypeDao.update(columnType);
		}
	}

	@Override
	public QueryResult<ColumnType> getScrollData(int firstindex, int maxresult, String wherejpql, Object[] queryParams,
			LinkedHashMap<String, String> orderby) {
		return columnTypeDao.getScrollData(firstindex, maxresult, wherejpql, queryParams,orderby);
	}
	
}
