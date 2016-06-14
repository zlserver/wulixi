package com.cnu.wlx.action.front;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cnu.wlx.bean.ColumnType;
import com.cnu.wlx.bean.News;
import com.cnu.wlx.bean.NewsFile;
import com.cnu.wlx.bean.base.PageView;
import com.cnu.wlx.bean.base.QueryResult;
import com.cnu.wlx.formbean.BaseForm;
import com.cnu.wlx.myenum.FileTypeEnum;
import com.cnu.wlx.service.ColumnTypeService;
import com.cnu.wlx.service.FileService;
import com.cnu.wlx.service.NewsFileService;
import com.cnu.wlx.service.NewsService;
import com.cnu.wlx.utils.SiteUtils;

/**
* @author 周亮 
* @version 创建时间：2016年6月4日 下午6:43:18
* 类说明
*/

@Controller
@RequestMapping(value="/front/news/")
public class NewsAction {
	 /**
	  * 文件服务
	  */
	 private FileService fileService;
	private NewsService newsService;
	private NewsFileService newsFileService;
	private ColumnTypeService columnTypeService;
	/**
	 * 根据文件保存的相对路径查看图片
	 * @param savePath 文件保存的相对路径
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="lookImage")
	public String lookImage(String savePath,HttpServletRequest request,HttpServletResponse response){
			
		//1.获取文件系统的根路径:D:/Soft/wlxopensystem/
			String fileSystemRoot = SiteUtils.getFileSystemDir();
			//2.生成文件的绝对路径:D:/Soft/wlxopensystem/news/images/201604225555556984.jpg
			String fileSavePath = fileSystemRoot+savePath;
			
			//2.1获取文件资源
			Resource fileResource =fileService.getFileResource("file:"+fileSavePath);
			//查看图片
			BaseForm.lookImage(response, fileResource);
			return null;
	}
	
	@RequestMapping(value="newsdetail")
	public String newsdetail(String classCode,String newsId,Model model){
		//新闻列表
		ColumnType ct = columnTypeService.findByClassCode(classCode);
		if( ct!=null){
			List<News> listnews =newsService.getAll();
			model.addAttribute("list",listnews);
		}
		News news =newsService.find(newsId);
		model.addAttribute("news",news);
		
		return SiteUtils.getPage("front.newsdetail");
	}
	public FileService getFileService() {
		return fileService;
	}
	@Autowired
	public void setFileService(FileService fileService) {
		this.fileService = fileService;
	}

	public NewsService getNewsService() {
		return newsService;
	}
	@Autowired
	public void setNewsService(NewsService newsService) {
		this.newsService = newsService;
	}

	public NewsFileService getNewsFileService() {
		return newsFileService;
	}
	@Autowired
	public void setNewsFileService(NewsFileService newsFileService) {
		this.newsFileService = newsFileService;
	}

	public ColumnTypeService getColumnTypeService() {
		return columnTypeService;
	}
	@Autowired
	public void setColumnTypeService(ColumnTypeService columnTypeService) {
		this.columnTypeService = columnTypeService;
	}
	
}
