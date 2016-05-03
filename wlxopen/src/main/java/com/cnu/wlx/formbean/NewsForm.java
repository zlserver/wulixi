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
	private String columnId;
	/**
	 * 标题颜色
	 */
	private String titleColor;
	
	

	public String getTitleColor() {
		return titleColor;
	}

	public void setTitleColor(String titleColor) {
		this.titleColor = titleColor;
	}

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

	public String getColumnId() {
		return columnId;
	}

	public void setColumnId(String columnId) {
		this.columnId = columnId;
	}

	/**
	 * 在list方法之前进行基础校验
	 * @return
	 */
	public boolean validateList(){
		if( !validateLen(columnId, 1, 32)){
			getResult().put("error", "查询栏目有误!");
			return false;
		}
		return true;
	}
	
}
