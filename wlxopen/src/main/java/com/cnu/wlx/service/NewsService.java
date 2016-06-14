package com.cnu.wlx.service;

import java.io.Serializable;
import java.util.List;

import com.cnu.wlx.bean.News;
import com.cnu.wlx.bean.base.QueryResult;

/**
 * 新闻服务类
 * @author liang
 * @version 创建时间 2016年5月2日
 * 说明:
 *
 */
public interface NewsService {
	/**
	 * 根据栏目id查询新闻
	 * @param firstindex 开始查询位置从0开始
	 * @param maxresult 一页的最大记录数
	 * @param columnId 栏目分类id
	 * @param state 新闻状态
	 * @return
	 */
	QueryResult<News> getScrollData(int firstResult, int maxresult, String columnId,String state);
	/**
	 * 获取首页新闻
	 * @param firstResult 开始查询位置从0开始
	 * @param maxresult 一页的最大记录数
	 * @param columnId  栏目分类id
	 * @return
	 */
	public QueryResult<News> getHomeScrollData(int firstResult, int maxresult,String columnId); 
	/**
	 * 获取所有数据
	 * @return
	 */
	public List<News> getAll();
	/**
	 * 保存新闻
	 * @param news
	 */
	void save(News news);
	/**
	 * 根据id查找新闻
	 * @param id
	 * @return
	 */
	News find(String id);
	/**
	 * 更新
	 * @param news
	 */
	void update(News news);
	/**
	 * 删除新闻
	 * @param entityids
	 */
	void delete(Serializable[] entityids);

}
