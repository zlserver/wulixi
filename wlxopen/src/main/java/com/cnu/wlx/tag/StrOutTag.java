
package com.cnu.wlx.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
* @author 周亮 
* @version 创建时间：2016年6月4日 下午2:31:25
* 类说明 ：字符显示标签，显示的字符不超过设定长度的大小，可以给字符后面加上后缀
*/
public class StrOutTag extends SimpleTagSupport{
	/**
	 * 要显示的字符串
	 */
	private String value;
	/**
	 * 字符串显示的最大长度，不设置表示显示全部
	 */
	private int length;
	/**
	 * 后缀字符，只有要显示的字符串不为空时才显示后缀
	 */
	private String suffix;
	
	@Override
	public void doTag() throws JspException, IOException {
		StringBuffer result = new StringBuffer();
		if( value !=null ){
			int actLength = value.length();
			
			if( length >0 && length< actLength)
			  result.append(value.substring(0, length));
			else
				result.append(value);	
			
			if( suffix!=null)
				result.append(suffix);
		}
		
        this.getJspContext().getOut().print(result);
	}

	
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	
	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}


	public String getValue() {
		return value;
	}


	public void setValue(String value) {
		this.value = value;
	}
	
	
	
}
