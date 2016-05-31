package com.cnu.wlx.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
* @author 周亮 
* @version 创建时间：2016年5月25日 下午1:45:11
* 类说明
*/
public class OtherwiseTag extends SimpleTagSupport {

	@Override
	public void doTag() throws JspException, IOException {
		ChooseTag parent = (ChooseTag) this.getParent();
		if(!parent.isDo()){
			this.getJspBody().invoke(null);
		}
	}
}
