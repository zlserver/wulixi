package com.cnu.wlx.action.front;

import java.util.LinkedHashMap;
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
import com.cnu.wlx.myenum.NewsStateEnum;
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
	/**
	 * 更多新闻
	 * @param classCode
	 * @param model
	 * @return
	 */
	/*@RequestMapping(value="morenews")
	public String moreNews(String classCode,Model model){
		model.addAttribute("classCode",classCode);
		return SiteUtils.getPage("front.news.main");
	}*/
	/**
	 * 新闻列表
	 * @param classCode
	 * @param model
	 * @return
	 */
	/*@RequestMapping(value="newslist")
	public String newList(String classCode ,Model model){
		//新闻列表
		ColumnType ct = columnTypeService.findByClassCode(classCode);
		if( ct!=null){
			String wherejpql = " o.column.id = ?  and o.state= ? ";
			Object[] queryParams= new Object[]{ct.getId(),NewsStateEnum.PUBLISH};

			LinkedHashMap<String,String> orderby=new LinkedHashMap<String,String>();
			orderby.put("sequence", "asc");
			orderby.put("createTime", "desc");
			List<News> listnews =newsService.getAll( wherejpql, queryParams,orderby);
			model.addAttribute("listnews",listnews);
		}
		return SiteUtils.getPage("front.news.newslist");
	}*/
	
	/**
	 * 从首页查看单个新闻
	 * @param classCode
	 * @param newsId
	 * @param model
	 * @return
	 */
	/*@RequestMapping(value="news")
	public String news(String classCode,String newsId,Model model){
		
		model.addAttribute("classCode",classCode);
		model.addAttribute("newsId",newsId);
		
		return SiteUtils.getPage("front.news.main");
	}*/
	
	/**
	 * 从首页查看单个新闻
	 * @param classCode
	 * @param newsId
	 * @param model
	 * @return
	 */
	@RequestMapping(value="siglenews")
	public String siglenews(String classCode,String newsId,Model model){
		
		//新闻列表
		ColumnType ct = columnTypeService.findByClassCode(classCode);
		if( ct!=null){
			String wherejpql = " o.column.id = ?  and o.state= ? ";
			Object[] queryParams= new Object[]{ct.getId(),NewsStateEnum.PUBLISH};

			LinkedHashMap<String,String> orderby=new LinkedHashMap<String,String>();
			orderby.put("sequence", "asc");
			orderby.put("createTime", "desc");
			List<News> listnews =newsService.getAll( wherejpql, queryParams,orderby);
			model.addAttribute("listnews",listnews);
		}
		News news =newsService.find(newsId);
		if( news!=null){
			news.setReadCount(news.getReadCount()+1);
			newsService.update(news);
			List<NewsFile> newsFiles =newsFileService.getAllFile(news.getId(), FileTypeEnum.NO_IMAGE);

			 model.addAttribute("newsFiles",newsFiles);
			model.addAttribute("news",news);
		}

		  model.addAttribute("classCode",classCode);
		return SiteUtils.getPage("front.news.siglenews");
	}
	/**
	 * 下载新闻附件
	 * @param savePath 文件保存路径
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="download")
	public String download(String savePath,HttpServletRequest request,HttpServletResponse response){
		//
		NewsFile newsFile= newsFileService.findByPath(savePath);
		String originName = newsFile.getOriginName();
		//1.获取文件系统的根路径:D:/Soft/wlxopensystem/
		String fileSystemRoot = SiteUtils.getFileSystemDir();
		//2.生成文件的绝对路径:D:/Soft/wlxopensystem/news/files/报名表.doc
		String fileSavePath = fileSystemRoot+savePath;
		
		//2.1获取文件资源
		Resource fileResource =fileService.getFileResource("file:"+fileSavePath);
		//查看图片
		BaseForm.loadFile(response, fileResource,originName);
		return null;
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
