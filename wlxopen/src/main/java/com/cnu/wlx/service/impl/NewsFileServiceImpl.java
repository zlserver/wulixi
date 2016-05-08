package com.cnu.wlx.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cnu.wlx.bean.NewsFile;
import com.cnu.wlx.dao.NewsFileDao;
import com.cnu.wlx.formbean.BaseForm;
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
		if( newsFile!=null)
			newsFileDao.save(newsFile);
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
	
	
}
