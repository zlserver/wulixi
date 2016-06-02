package com.cnu.wlx.service;

import java.util.LinkedHashMap;

import com.cnu.wlx.bean.DownloadFile;
import com.cnu.wlx.bean.News;
import com.cnu.wlx.bean.base.QueryResult;
import com.cnu.wlx.myenum.FileStateEnum;

/**
* @author 周亮 
* @version 创建时间：2016年5月23日 下午2:36:08
* 类说明
*/
public interface DownloadFileService {
	
	/**
	 * 根据条件分页查询，结果根据条件排序
	 * @param firstindex 开始查询位置从0开始
	 * @param maxresult 一页的最大记录数
	 * @param wherejpql 查询条件  "o.email=? and o.account like ?"
	 * @param queryParams  查询条件占位符对应的参数值，
	 * @param orderby 排序条件  Key为属性,Value为asc/desc
	 * @return 查询结果类
	 */
	public QueryResult<DownloadFile> getScrollData(int firstindex, int maxresult, String wherejpql, Object[] queryParams,LinkedHashMap<String, String> orderby);
	
	/**
	 * 分页查询下载文件
	 * @param firstResult 第一个数据的位置
	 * @param maxresult 一页显示最大数
	 * @param columnId 栏目id
	 * @param state  文件状态
	 * @return
	 */
	public QueryResult<DownloadFile> getScrollData(int firstResult, int maxresult, String columnId, FileStateEnum state);
	/**
	 * 获取首页下载文件
	 * @param firstResult 开始查询位置从0开始
	 * @param maxresult 一页的最大记录数
	 * @param columnId  栏目分类id
	 * @return
	 */
	public QueryResult<DownloadFile> getHomeScrollData(int firstResult, int maxresult,String columnId); 
	/**
	 * 保存
	 * @param downloadFile
	 */
	public void save(DownloadFile downloadFile);
	/**
	 * 查询
	 * @param fileid
	 * @return
	 */
	public DownloadFile find(String fileid);
	/**
	 * 更新
	 * @param downloadFile
	 */
	public void update(DownloadFile downloadFile);
	/**
	 * 删除
	 * @param ids
	 */
	public void delete(String ...ids);
}
