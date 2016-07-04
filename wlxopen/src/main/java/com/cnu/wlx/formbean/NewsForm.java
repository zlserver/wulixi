package com.cnu.wlx.formbean;

import java.util.List;

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
	 * 标题颜色,BLACK ,RED,BLUE
	 */
	private String titleColor;
	/**
	 * 新闻状态:待发布，已发布，屏蔽
	 */
	private String state;
	/**
	 * IMAGE,NO_IMAGE
	 */
	private String type;
	
	
	/**
	 * 批量更新
	 */
	private List<String> columnIds;
	
	private List<String> states;
	
	private List<Integer>  sequences;
	
	private List<Integer> suggests;
	/**
	 * 添加的附件文件的id
	 */
	private List<String> fileIds;
	
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<String> getColumnIds() {
		return columnIds;
	}

	public void setColumnIds(List<String> columnIds) {
		this.columnIds = columnIds;
	}

	public List<String> getStates() {
		return states;
	}

	public void setStates(List<String> states) {
		this.states = states;
	}

	public List<Integer> getSequences() {
		return sequences;
	}

	public void setSequences(List<Integer> sequences) {
		this.sequences = sequences;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

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

	public List<String> getFileIds() {
		return fileIds;
	}

	public void setFileIds(List<String> fileIds) {
		this.fileIds = fileIds;
	}

	public List<Integer> getSuggests() {
		return suggests;
	}

	public void setSuggests(List<Integer> suggests) {
		this.suggests = suggests;
	}

	/**
	 * 在list方法之前进行基础校验
	 * @return
	 */
	public boolean validateList(){
		if( !validateLen(this.getColumnId(), 1, 32)){
			getResult().put("error", "查询栏目有误!");
			return false;
		}
		return true;
	}
	/**
	 * 校验添加方法,题目，标题颜色，内容不能为空
	 * @return
	 */
	public boolean validateAdd() {
		// TODO Auto-generated method stub
		if( news==null)
			return false;
		if( !validateStr(news.getTitle()) || !validateStr(news.getContext())|| !validateStr(titleColor))
			return false;
		return true;
	}
	
}
