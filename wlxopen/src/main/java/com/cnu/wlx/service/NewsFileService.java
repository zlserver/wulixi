package com.cnu.wlx.service;

import com.cnu.wlx.bean.NewsFile;

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
	 * 保存文件
	 * @param newsFile 被保存文件实体
	 */
	public void save(NewsFile newsFile);
}
