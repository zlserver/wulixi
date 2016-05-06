package com.cnu.wlx.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cnu.wlx.bean.NewsPicture;
import com.cnu.wlx.dao.NewsPictureDao;
import com.cnu.wlx.service.NewsPictureService;

/**
* @author 周亮 
* @version 创建时间：2016年5月6日 上午10:56:26
* 类说明
*/
@Service("newsPictureService")
public class NewsPictureServiceImpl implements NewsPictureService {

	private NewsPictureDao newsPictureDao;
	@Override
	public void save(NewsPicture picture) {
		// TODO Auto-generated method stub
		if( picture!=null)
		 newsPictureDao.save(picture);
	}
	public NewsPictureDao getNewsPictureDao() {
		return newsPictureDao;
	}
	@Resource
	public void setNewsPictureDao(NewsPictureDao newsPictureDao) {
		this.newsPictureDao = newsPictureDao;
	}

}
