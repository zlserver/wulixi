package com.cnu.wlx.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
* @author 周亮 
* @version 创建时间：2016年5月29日 下午3:01:38
* 类说明,用于将long类型值显示成带大小单位（G,M,KB,B）的字符串
*/
public class SizeTag  extends SimpleTagSupport{
		//size属性
		private Long size;

		@Override
		public void doTag() throws JspException, IOException {
			 long kb = 1024;
	        long mb = kb * 1024;
	        long gb = mb * 1024;
	        String result="";

	        if (size >= gb) {
	        	result= String.format("%.1f GB", (float) size / gb);
	        } else if (size >= mb) {
	            float f = (float) size / mb;
	            result= String.format(f > 100 ? "%.0f MB" : "%.1f MB", f);
	        } else if (size >= kb) {
	            float f = (float) size / kb;
	            result= String.format(f > 100 ? "%.0f KB" : "%.1f KB", f);
	        } else
	        	result= String.format("%d B", size);
	        this.getJspContext().getOut().print(result);
		}

		public Long getSize() {
			return size;
		}

		public void setSize(Long size) {
			this.size = size;
		}

		
}
