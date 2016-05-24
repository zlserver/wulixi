package com.cnu.wlx.service;

import com.cnu.wlx.bean.DownloadFile;
import com.cnu.wlx.bean.base.QueryResult;
import com.cnu.wlx.myenum.FileStateEnum;

/**
* @author 周亮 
* @version 创建时间：2016年5月23日 下午2:36:08
* 类说明
*/
public interface DownloadFileService {
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
