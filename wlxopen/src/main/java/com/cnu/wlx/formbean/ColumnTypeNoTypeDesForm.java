package com.cnu.wlx.formbean;

import com.cnu.wlx.bean.ColumnType;
import com.cnu.wlx.myenum.ColumnTypeDesEnum;

/**
* @author 周亮 
* @version 创建时间：2016年4月13日 下午3:52:16
* 类说明
*/
public class ColumnTypeNoTypeDesForm extends BaseForm {

	/**
	 * 类别类
	 */
	private ColumnType column;
	/**
	 * 父类id
	 */
	private String parentId;
	/**
	 * 父类名称
	 */
	private String parentName;
	/**
	 * 父类的父类id
	 */
	private String doubleParentId;
	/**
	 * 父类的父类名称
	 */
	private String doubleParentName;
	
	
	@Override
	public int getMaxresult() {
		// TODO Auto-generated method stub
		return 7;
	}
	public String getDoubleParentId() {
		return doubleParentId;
	}
	public void setDoubleParentId(String doubleParentId) {
		this.doubleParentId = doubleParentId;
	}
	public String getDoubleParentName() {
		return doubleParentName;
	}
	public void setDoubleParentName(String doubleParentName) {
		this.doubleParentName = doubleParentName;
	}
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
	
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	
}
