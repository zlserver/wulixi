package com.cnu.wlx.formbean;

import com.cnu.wlx.bean.ColumnType;
import com.cnu.wlx.myenum.ColumnTypeDesEnum;

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
	 * 类别码,0,1,2,3。默认0
	 * 0:LIST_TYPE("列表类"),1:DES_TYPE("介绍类"),2:SYSTEM_TYPE("系统类"),3:OTHER_TYPE("其它类");
	 */
	private int typeDes=0;
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
	public int getTypeDes() {
		return typeDes;
	}
	public void setTypeDes(int typeDes) {
		this.typeDes = typeDes;
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
	/**
	 * 对添加方法进行校验
	 * @return
	 */
	public boolean validateAdd(){
		
		if( typeDes<0 || typeDes>ColumnTypeDesEnum.values().length)
			this.getResult().put("error", "分类说明有误!");
		if( column==null)
			this.getResult().put("error", "请填写信息!");
		else{
			if(!BaseForm.validateStr(column.getClassCode())|| !BaseForm.validateStr(column.getName())) {
				this.getResult().put("error", "名称和分类码必须填写!");
			}
		}
		if( this.getResult().isEmpty())
			return true;
		
		return false;
	}
	
}
