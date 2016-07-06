package com.cnu.wlx.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cnu.wlx.bean.DownloadFile;
import com.cnu.wlx.bean.base.QueryResult;
import com.cnu.wlx.dao.DownloadFileDao;
import com.cnu.wlx.formbean.BaseForm;
import com.cnu.wlx.myenum.FileStateEnum;
import com.cnu.wlx.myenum.FileTypeEnum;
import com.cnu.wlx.myenum.NewsStateEnum;
import com.cnu.wlx.service.DownloadFileService;

/**
* @author 周亮 
* @version 创建时间：2016年5月23日 下午2:36:32
* 类说明
*/
@Service("downloadFileService")
public class DownloadFileServiceImpl implements DownloadFileService {
	
	private DownloadFileDao downloadFileDao;
	
	
	
	public DownloadFileDao getDownloadFileDao() {
		return downloadFileDao;
	}
	@Resource
	public void setDownloadFileDao(DownloadFileDao downloadFileDao) {
		this.downloadFileDao = downloadFileDao;
	}
	@Override
	public QueryResult<DownloadFile> getScrollData(int firstResult, int maxresult, String columnId,
			FileStateEnum state) {
		// TODO Auto-generated method stub
				//结果集根据时间降序来排列
				LinkedHashMap<String,String> orderby=new LinkedHashMap<String,String>();
				orderby.put("sequence", "desc");
				orderby.put("createTime", "desc");
				//父类不为null
				if( BaseForm.validateStr(columnId)){
					String wherejpql="o.column.id = ? ";
					List<Object> params = new ArrayList<Object>();
					params.add(columnId);
					if(state!=null){
						wherejpql+=" and o.state= ?";
						params.add(state);
					}
					return downloadFileDao.getScrollData(firstResult, maxresult, wherejpql, params.toArray(), orderby);
				}
				return null;
	}
	@Override
	public QueryResult<DownloadFile> getHomeScrollData(int firstResult, int maxresult, String columnId) {
		//结果集根据时间降序来排列
		LinkedHashMap<String,String> orderby=new LinkedHashMap<String,String>();
		orderby.put("sequence", "desc");
		orderby.put("createTime", "desc");
		//父类不为null
		if( BaseForm.validateStr(columnId)){//查询图片
			String wherejpql="o.column.id = ? and o.suggest=? and o.state=? and o.type= ? ";
			List<Object> params = new ArrayList<Object>();
			params.add(columnId);
			params.add(1);
			params.add(FileStateEnum.VALIDATE);
			params.add(FileTypeEnum.IMAGE);
			return downloadFileDao.getScrollData(firstResult, maxresult, wherejpql, params.toArray(), orderby);
		}else{//查询文件
			String wherejpql="o.suggest=? and o.state=? and o.type= ? ";
			List<Object> params = new ArrayList<Object>();
			params.add(1);
			params.add(FileStateEnum.VALIDATE);
			params.add(FileTypeEnum.NO_IMAGE);
			return downloadFileDao.getScrollData(firstResult, maxresult, wherejpql, params.toArray(), orderby);
		
		}
	}
	@Override
	public void save(DownloadFile downloadFile) {
		// TODO Auto-generated method stub
		if( downloadFile!=null){
			//替换掉原文件名称中的中文空格
			String originName= downloadFile.getOriginName();
			originName=originName.replace(" ","");
			downloadFile.setOriginName(originName);
			downloadFileDao.save(downloadFile);
		}
		else
			throw new RuntimeException("保存文件不能为null");
		
	}
	@Override
	public DownloadFile find(String fileid) {
		// TODO Auto-generated method stub
		return downloadFileDao.find(fileid);
	}
	@Override
	public void update(DownloadFile downloadFile) {
		// TODO Auto-generated method stub
		downloadFileDao.update(downloadFile);
	}
	@Override
	public void delete(String... ids) {
		// TODO Auto-generated method stub
		downloadFileDao.delete(ids);
	}
	@Override
	public QueryResult<DownloadFile> getScrollData(int firstindex, int maxresult, String wherejpql,
			Object[] queryParams, LinkedHashMap<String, String> orderby) {
		// TODO Auto-generated method stub
		return downloadFileDao.getScrollData(firstindex, maxresult, wherejpql, queryParams, orderby);
	}
	@Override
	public DownloadFile findByPath(String savePath) {
		// TODO Auto-generated method stub
		
		return downloadFileDao.find("o.savePath = ?" , savePath);
	}
	
}
