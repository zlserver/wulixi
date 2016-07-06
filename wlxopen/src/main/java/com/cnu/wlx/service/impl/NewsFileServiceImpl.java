package com.cnu.wlx.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cnu.wlx.bean.News;
import com.cnu.wlx.bean.NewsFile;
import com.cnu.wlx.bean.base.QueryResult;
import com.cnu.wlx.dao.NewsDao;
import com.cnu.wlx.dao.NewsFileDao;
import com.cnu.wlx.formbean.BaseForm;
import com.cnu.wlx.myenum.FileStateEnum;
import com.cnu.wlx.myenum.FileTypeEnum;
import com.cnu.wlx.service.NewsFileService;

/**
* @author 周亮 
* @version 创建时间：2016年5月8日 下午1:28:45
* 类说明
*/
@Service("newsFileService")
public class NewsFileServiceImpl implements NewsFileService {
	
	private NewsFileDao newsFileDao;
	public NewsFile find(String fileId){
		if( BaseForm.validateStr(fileId))
		 return newsFileDao.find(fileId);
		return null;
	}
	
	public void save(NewsFile newsFile){
		if( newsFile!=null){
			//替换掉原文件名称中的中文空格
			String originName= newsFile.getOriginName();
			originName=originName.replace(" ","");
			newsFile.setOriginName(originName);
			newsFileDao.save(newsFile);
		}
		else
			throw new RuntimeException("保存文件不能为null");
		
	}
	public NewsFileDao getNewsFileDao() {
		return newsFileDao;
	}
	@Resource
	public void setNewsFileDao(NewsFileDao newsFileDao) {
		this.newsFileDao = newsFileDao;
	}

	@Override
	public void update(NewsFile newsFile) {
		// TODO Auto-generated method stub
		if( newsFile!=null)
		  newsFileDao.update(newsFile);
		 else
			  throw new RuntimeException("更新文件不能为null");
	}

	@Override
	public void delete(String... fileId) {
		 newsFileDao.delete(fileId);
	}

	@Override
	public QueryResult<NewsFile> getScrollData(int firstindex, int maxresult, String wherejpql, Object[] queryParams,
			LinkedHashMap<String, String> orderby) {
		
		return newsFileDao.getScrollData(firstindex, maxresult, wherejpql, queryParams, orderby);
	}

	@Override
	public NewsFile find(String wheresql, Object[] queryParams) {
		// TODO Auto-generated method stub
		List<NewsFile> list =newsFileDao.find(wheresql, queryParams);
		if( list!=null &&list.size()>0)
		   return list.get(0);
		return null;
	}

	@Override
	public NewsFile getHomeData(String newsId, FileTypeEnum type) {
			
			//查询条件：所属新闻，文件类型
			StringBuffer wherejpql=new StringBuffer("");
			List<Object> params = new ArrayList<Object>();
			wherejpql.append(" o.news.id = ? and o.type = ? and  o.state = ? ");
			params.add(newsId);
			params.add(type);
			params.add(FileStateEnum.VALIDATE);
			List<NewsFile> list =newsFileDao.find(wherejpql.toString(), params.toArray());
			if( list!=null &&list.size()>0)
			   return list.get(0);
			return null;
		
	}

	@Override
	public List<NewsFile> getAllFile(String newsId, FileTypeEnum type) {
		StringBuffer wherejpql=new StringBuffer("");
		List<Object> params = new ArrayList<Object>();
		wherejpql.append(" o.news.id = ? and o.type = ? and  o.state = ? ");
		params.add(newsId);
		params.add(type);
		params.add(FileStateEnum.VALIDATE);
		List<NewsFile> list =newsFileDao.find(wherejpql.toString(), params.toArray());
		
		return list;
	}

	@Override
	public NewsFile findByPath(String savePath) {
		// TODO Auto-generated method stub
		return newsFileDao.find("o.savePath = ?" , savePath);
	}

	
	
}
