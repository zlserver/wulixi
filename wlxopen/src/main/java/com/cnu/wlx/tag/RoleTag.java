package com.cnu.wlx.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class RoleTag extends SimpleTagSupport  {

	/**
	 * 校验的角色代号
	 */
	private int role;
	
	@Override
	public void doTag() throws JspException, IOException {
		String roleStr ="普通管理员";
		String color="black";
		StringBuffer sb = new StringBuffer();
		sb.append("<font color=\"");
		if(role==1){
			roleStr="超级管理员";
			color = "blue";
		}
		sb.append(color).append("\">").append(roleStr).append("</font>");
		this.getJspContext().getOut().print(sb.toString());
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}


}
