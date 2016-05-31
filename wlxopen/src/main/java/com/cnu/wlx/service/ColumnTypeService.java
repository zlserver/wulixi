package com.cnu.wlx.service;

import java.util.LinkedHashMap;
import java.util.List;

import com.cnu.wlx.bean.ColumnType;
import com.cnu.wlx.bean.base.QueryResult;

/**
* @author 周亮 
* @version 创建时间：2016年4月13日 下午4:00:46
* 类说明：栏目服务类
*/
public interface ColumnTypeService {
	/**
	 * 添加栏目
	 * @param ct
	 */
	public void addColumnType(ColumnType ct);
	/**
	 * 获取顶级栏目
	 * @return
	 */
	public List<ColumnType> getTopColumns();
	
	/**
	 * 根据条件分页查询栏目，结果根据栏目的顺序排序
	 * @param firstindex 开始查询位置从0开始
	 * @param maxresult 一页的最大记录数
	 * @param parentId  父类id，如果父类为空，则设置成null
	 * @return 查询结果类
	 */
	public QueryResult<ColumnType> getScrollData(int firstindex, int maxresult, String parentId);
	/**
	 * 根据id查询栏目
	 * @param id
	 * @return
	 */
	public ColumnType find(String id);	
	/**
	 * 根据classCode（分类码）查询栏目
	 * @param id
	 * @return
	 */
	public ColumnType findByClassCode(String classCode);
	/**
	 * 删除栏目
	 * @param id
	 */
	public void delete(String id);
	/**
	 * 更新栏目
	 * @param column
	 */
	public void updateColumnType(ColumnType column);
}
