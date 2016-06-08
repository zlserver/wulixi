package com.cnu.wlx.service;

import java.util.LinkedHashMap;
import java.util.List;

import com.cnu.wlx.bean.Question;
import com.cnu.wlx.bean.base.QueryResult;

/**
* @author 周亮 
* @version 创建时间：2016年5月26日 下午2:46:57
* 类说明
*/
public interface QuestionService {

	public Question find(String id);
	
	public void delete(String... ids);
	
	public void save(Question question);
	public void update(Question question);
	
	public List<Question> getHomeData(int firstindex, int maxresult);
	/**
	 * 根据条件分页查询，结果根据条件排序
	 * @param firstindex 开始查询位置从0开始
	 * @param maxresult 一页的最大记录数
	 * @param wherejpql 查询条件  "o.email=? and o.account like ?"
	 * @param queryParams  查询条件占位符对应的参数值，
	 * @param orderby 排序条件  Key为属性,Value为asc/desc
	 * @return 查询结果类
	 */
	public QueryResult<Question> getScrollData(int firstindex, int maxresult, String wherejpql, Object[] queryParams,LinkedHashMap<String, String> orderby);
	/**
	 * 根据条件分页查询
	 * @param firstindex 开始查询位置从0开始
	 * @param maxresult 一页的最大记录数
	 * @param wherejpql 查询条件  "o.email=? and o.account like ?"
	 * @param queryParams  查询条件占位符对应的参数值，
	 * @return 查询结果类
	 */
	public QueryResult<Question> getScrollData(int firstindex, int maxresult, String wherejpql, Object[] queryParams);
}
