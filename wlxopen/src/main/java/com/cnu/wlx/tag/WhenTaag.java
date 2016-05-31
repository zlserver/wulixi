package com.cnu.wlx.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
* @author 周亮 
* @version 创建时间：2016年5月25日 上午11:33:03
* 类说明
*/
public class WhenTaag extends SimpleTagSupport {
	//test属性
	private Object test;

	@Override
	public void doTag() throws JspException, IOException {
		ChooseTag parent = (ChooseTag) this.getParent();
		//处理boolean值
		if( test instanceof Boolean){
			Boolean tes = (Boolean)test;
			if( tes && !parent.isDo()){
				this.getJspBody().invoke(null);
				parent.setDo(true);
			}
		}
		//处理String值
		if( test instanceof String){
			String tes = (String) test;
			if( !tes.trim().equals("")){
				this.getJspBody().invoke(null);
				parent.setDo(true);
			}
		}
	}
	public Object getTest() {
		return test;
	}
	public void setTest(Object test) {
		this.test = test;
	}
}
