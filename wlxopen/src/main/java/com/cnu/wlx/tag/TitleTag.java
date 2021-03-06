package com.cnu.wlx.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
* @author 周亮 
* @version 创建时间：2016年6月15日 下午3:30:54
* 类说明:根据新闻分类码显示新闻标题
*/
public class TitleTag extends SimpleTagSupport {
	/**
	 * 分类码
	 */
	private String classCode;

	@Override
	public void doTag() throws JspException, IOException {
		String title = "";
		
		if( classCode!=null &&!classCode.trim().equals("")){
			if( classCode.equals("xue"))
				title="学工新闻";
			if( classCode.equals("xia"))
				title="下载专区";
			if( classCode.equals("tong"))
				title="通知公告";
			if( classCode.equals("job"))
				title="就业实习信息";
			if( classCode.equals("huiying"))
				title="回音壁";
			if( classCode.equals("xy_"))
				title="校园文化";
			if( classCode.equals("biao"))
				title="学习标兵";
			if( classCode.equals("biaozhang"))
				title="荣誉表彰";
			if( classCode.equals("down_"))
				title="下载专区";
			if( classCode.equals("huiyin"))
				title="回音壁热点问题";
			
			
			

			if( classCode.equals("xszz"))
				title="学生组织";
			if( classCode.equals("gzzd"))
				title="规章制度";
			if( classCode.equals("sxjy"))
				title="思想教育";
			if( classCode.equals("zzgl"))
				title="资助管理";
			if( classCode.equals("xlzx"))
				title="心理咨询";
			if( classCode.equals("jiuye"))
				title="就业工作";
			if( classCode.equals("gfjy"))
				title="国防教育";
			if( classCode.equals("yjsy"))
				title="研究生院";
			
			
		}
		this.getJspContext().getOut().print(title);
	}

	public String getClassCode() {
		return classCode;
	}

	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}
	
}
