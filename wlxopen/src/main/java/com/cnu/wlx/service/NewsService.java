package com.cnu.wlx.service;

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
	 * @return
	 */
	QueryResult<News> getScrollData(int firstResult, int maxresult, String columnId);

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

}
