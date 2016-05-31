package com.cnu.wlx.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
* @author 周亮 
* @version 创建时间：2016年5月25日 上午10:50:46
* 类说明
*/
public class ChooseTag extends SimpleTagSupport {
	//作为标志，如果isDo为false则when和false执行。
	private boolean isDo;
	
	public boolean isDo() {
		return isDo;
	}
	public void setDo(boolean isDo) {
		this.isDo = isDo;
	}

	@Override
	public void doTag() throws JspException, IOException {

		JspFragment jf =this.getJspBody();
		jf.invoke(null);
	}
}
