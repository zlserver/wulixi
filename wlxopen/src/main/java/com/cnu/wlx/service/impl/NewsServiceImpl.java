package com.cnu.wlx.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cnu.wlx.bean.News;
import com.cnu.wlx.bean.base.QueryResult;
import com.cnu.wlx.dao.NewsDao;
import com.cnu.wlx.formbean.BaseForm;
import com.cnu.wlx.myenum.NewsStateEnum;
import com.cnu.wlx.service.NewsService;

@Service("newsService")
public class NewsServiceImpl implements NewsService {

	private NewsDao newsDao;
	@Override
	public QueryResult<News> getScrollData(int firstResult, int maxresult, String getColumnId,String state) {
		// TODO Auto-generated method stub
		//结果集根据栏目的顺序升序,时间降序来排列
		LinkedHashMap<String,String> orderby=new LinkedHashMap<String,String>();
		orderby.put("sequence", "asc");
		orderby.put("createTime", "desc");
		//父类不为null
		if( BaseForm.validateStr(getColumnId)){
			String wherejpql="o.column.id = ? ";
			List<Object> params = new ArrayList<Object>();
			params.add(getColumnId);
			if( BaseForm.validateStr(state)){
				wherejpql+=" and o.state= ?";
				NewsStateEnum st = NewsStateEnum.valueOf(state);
				params.add(st);
			}
			return newsDao.getScrollData(firstResult, maxresult, wherejpql, params.toArray(), orderby);
		}
		return null;
	}
	public NewsDao getNewsDao() {
		return newsDao;
	}
	@Resource
	public void setNewsDao(NewsDao newsDao) {
		this.newsDao = newsDao;
	}
	@Override
	public void save(News news) {
		// TODO Auto-generated method stub
		if( news!=null)
		  newsDao.save(news);
	}
	@Override
	public News find(String id) {
		if( BaseForm.validateStr(id))
			return newsDao.find(id);
		return null;
	}
	@Override
	public void update(News news) {
		// TODO Auto-generated method stub
		newsDao.update(news);
	}
	@Override
	public void delete(Serializable ...entityids){
		if( entityids!=null)
		newsDao.delete(entityids);
	}
}
