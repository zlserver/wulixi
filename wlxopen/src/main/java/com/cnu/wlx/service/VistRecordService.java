package com.cnu.wlx.service;

import com.cnu.wlx.bean.VistRecord;

/**
* @author 周亮 
* @version 创建时间：2016年7月5日 下午8:39:55
* 类说明
*/
public interface VistRecordService {

	/**
	 * 保存
	 * @param vistRecord
	 */
	public void  save(VistRecord vistRecord);
	/**
	 * 获得总记录数
	 * @return
	 */
	public long getCount();
}
