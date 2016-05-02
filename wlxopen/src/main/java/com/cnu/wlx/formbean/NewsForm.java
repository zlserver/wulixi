package com.cnu.wlx.formbean;

import com.cnu.wlx.bean.News;
/**
 * 新闻表单类
 * @author liang
 * @version 创建时间 2016年5月2日
 * 说明:
 *
 */
public class NewsForm extends BaseForm {

	private News news;
	/**
	 * 新闻所属栏目分类
	 */
	private String classCode;
	public News getNews() {
		return news;
	}

	public void setNews(News news) {
		this.news = news;
	}

	@Override
	public int getMaxresult() {
		// TODO Auto-generated method stub
		return 10;
	}

	public String getClassCode() {
		return classCode;
	}

	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}

	/**
	 * 在list方法之前进行基础校验
	 * @return
	 */
	public boolean validateList(){
		if( validateLen(classCode, 1, 10)){
			getResult().put("error", "查询栏目有误!");
			return false;
		}
		return true;
	}
	
}
