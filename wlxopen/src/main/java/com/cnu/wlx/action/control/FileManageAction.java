package com.cnu.wlx.action.control;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cnu.wlx.bean.DownloadFile;
import com.cnu.wlx.bean.News;
import com.cnu.wlx.bean.base.PageView;
import com.cnu.wlx.bean.base.QueryResult;
import com.cnu.wlx.formbean.DownloadFileForm;
import com.cnu.wlx.myenum.FileStateEnum;
import com.cnu.wlx.service.DownloadFileService;
import com.cnu.wlx.utils.SiteUtils;

/**
* @author 周亮 
* @version 创建时间：2016年5月23日 下午2:41:16
* 类说明:下载文件管理
*/

@Controller
@RequestMapping(value="/control/download/*")
public class FileManageAction {
	
	private DownloadFileService downloadFileService;
	
	@RequestMapping(value="list")
	public String list(DownloadFileForm formbean,Model model){
		
		//页面类
		PageView<DownloadFile> pageView = new PageView<DownloadFile>(formbean.getMaxresult(), formbean.getPage());
		FileStateEnum state= null;
		if( formbean.getState()!=null)
		    state = FileStateEnum.valueOf(formbean.getState());
		QueryResult<DownloadFile> queryResult= downloadFileService.getScrollData(pageView.getFirstResult(),pageView.getMaxresult(), formbean.getColumnId(),state);
		pageView.setQueryResult(queryResult);
		//传输到页面
		model.addAttribute("pageView", pageView);
		
		model.addAttribute("formbean",formbean);
		
		
		return SiteUtils.getPage("control.download.list");
	}
	
	
	public String add(){
		
		return null;
	}
	
	public DownloadFileService getDownloadFileService() {
		return downloadFileService;
	}
	@Resource
	public void setDownloadFileService(DownloadFileService downloadFileService) {
		this.downloadFileService = downloadFileService;
	}
	
}
