package com.cnu.wlx.formbean;

import com.cnu.wlx.bean.ColumnType;

/**
* @author 周亮 
* @version 创建时间：2016年4月13日 下午3:52:16
* 类说明
*/
public class ColumnTypeForm extends BaseForm {

	/**
	 * 类别类
	 */
	private ColumnType column;
	/**
	 * 父类id
	 */
	private String parentId;
	public ColumnType getColumn() {
		return column;
	}
	public void setColumn(ColumnType column) {
		this.column = column;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	
}
