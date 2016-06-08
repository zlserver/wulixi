package com.cnu.wlx.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
* @author 周亮 
* @version 创建时间：2016年6月7日 下午2:41:08
* 类说明:校验字符串是否为有效字符串，有效字符串：非空且不是空字符串,如果是有效字符串则执行标签体内容
*/
public class ValidateStrTag extends SimpleTagSupport {

	/**
	 * 校验的字符串
	 */
	private String value;
	
	@Override
	public void doTag() throws JspException, IOException {
		if( value !=null && !value.trim().equals("") ){
			this.getJspBody().invoke(null);
		}
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

	
}
