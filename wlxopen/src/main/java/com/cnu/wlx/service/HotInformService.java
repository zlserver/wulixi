package com.cnu.wlx.service;

import java.io.Serializable;
import java.util.LinkedHashMap;

import com.cnu.wlx.bean.HotInform;
import com.cnu.wlx.bean.base.QueryResult;

public interface HotInformService {

	public void save(HotInform inform);
	
	public void update(HotInform inform);
	
	/**
	 * 根据条件分页查询，结果根据条件排序
	 * @param firstindex 开始查询位置从0开始
	 * @param maxresult 一页的最大记录数
	 * @param wherejpql 查询条件  "o.email=? and o.account like ?"
	 * @param queryParams  查询条件占位符对应的参数值，  
	 * @param orderby 排序条件  Key为属性,Value为asc/desc
	 * @return 查询结果类
	 */
	public QueryResult<HotInform> getScrollData(int firstindex, int maxresult, String wherejpql, Object[] queryParams,LinkedHashMap<String, String> orderby);
	/**
	 * 得到唯一的一条热点
	 * @return，如果有多条取最新的一条
	 */
	public HotInform getUniqueHot();
	
	public HotInform find(String id);
	public void delete(Serializable... id);
}
