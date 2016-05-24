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
	public void save(DownloadFile downloadFile) {
		// TODO Auto-generated method stub
		downloadFileDao.save(downloadFile);
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
}
