package com.cnu.wlx.service;

import java.util.LinkedHashMap;

import com.cnu.wlx.bean.NewsFile;
import com.cnu.wlx.bean.base.QueryResult;
import com.cnu.wlx.myenum.FileTypeEnum;

/**
* @author 周亮 
* @version 创建时间：2016年5月8日 下午1:28:24
* 类说明
*/
public interface NewsFileService {

	/**
	 * 查找文件
	 * @param fileId 文件id
	 * @return
	 */
	public NewsFile find(String fileId);
	/**
	 * 获取一个数据
	 * @param newsId
	 * @return
	 */
	public NewsFile getHomeData(String newsId,FileTypeEnum type);
	/**
	 * 保存文件
	 * @param newsFile 被保存文件实体
	 */
	public void save(NewsFile newsFile);
	/**
	 * 更新
	 * @param newsFile
	 */
	public void update(NewsFile newsFile);
	/**
	 * 根据id删除附件
	 * @param ids
	 */
	public void delete(String... ids);
	/**
	 * 更加条件查询
	 * @param wheresql
	 * @param queryParams
	 * @return
	 */
	public NewsFile find(String wheresql,Object[] queryParams);
	
	/**
	 * 根据条件分页查询，结果根据条件排序
	 * @param firstindex 开始查询位置从0开始
	 * @param maxresult 一页的最大记录数
	 * @param wherejpql 查询条件  "o.email=? and o.account like ?"
	 * @param queryParams  查询条件占位符对应的参数值，
	 * @param orderby 排序条件  Key为属性,Value为asc/desc
	 * @return 查询结果类
	 */
	public QueryResult<NewsFile> getScrollData(int firstindex, int maxresult, String wherejpql, Object[] queryParams,LinkedHashMap<String, String> orderby);
	
}
