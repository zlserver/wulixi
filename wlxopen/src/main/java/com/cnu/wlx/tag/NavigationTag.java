package com.cnu.wlx.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 * 导航标签
 * @author liang
 * @version 创建时间 2016年9月26日
 * 说明:
 *
 */
public class NavigationTag extends SimpleTagSupport {
	/**
	 * 栏目id
	 */
	private String columnId;
	/**
	 * 编辑状态
	 */
	private String editState;
	/**
	 * 栏目名称
	 */
	private String columnName;
	/**
	 * 控制模块名称
	 */
	private String model;
	/**
	 * 非必要属性，文件类型，针对下载文件有图片和文件两种类型，
	 */
	private String type;
	
	@Override
	public void doTag() throws JspException, IOException {
		//生成导航链接 <a href="control/employee/list.action?columnId=${navigationColumnId}&editState=${navigationColumnEditState}&columnName=${navigationColumnName}">${navigationColumnName}</a>
		//         <a href="control/download/list.action?columnId=${navigationColumnId}&editState=${navigationColumnEditState}&columnName=${navigationColumnName}&type=${navigationType}">
	  	//${navigationColumnName}
	   // </a>
		StringBuffer sb = new StringBuffer();
		sb.append("<a href=\"control/").append(model).append("/list.action")
		  .append("?columnId=").append(columnId)
		  .append("&editState=").append(editState)
		  .append("&columnName=").append(columnName);
		if( type!=null && !type.trim().equals(""))
			sb.append("&type=").append(type);
		sb.append("\">").append(columnName).append("</a>");
		
		 this.getJspContext().getOut().print(sb.toString());
	}
	public String getColumnId() {
		return columnId;
	}
	public void setColumnId(String columnId) {
		this.columnId = columnId;
	}
	public String getEditState() {
		return editState;
	}
	public void setEditState(String editState) {
		this.editState = editState;
	}
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
