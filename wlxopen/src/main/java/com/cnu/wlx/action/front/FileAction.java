package com.cnu.wlx.action.front;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
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
import com.cnu.wlx.bean.DownloadFile;
import com.cnu.wlx.bean.News;
import com.cnu.wlx.bean.base.PageView;
import com.cnu.wlx.bean.base.QueryResult;
import com.cnu.wlx.formbean.BaseForm;
import com.cnu.wlx.formbean.DownloadFileForm;
import com.cnu.wlx.myenum.FileStateEnum;
import com.cnu.wlx.myenum.FileTypeEnum;
import com.cnu.wlx.service.ColumnTypeService;
import com.cnu.wlx.service.DownloadFileService;
import com.cnu.wlx.service.FileService;
import com.cnu.wlx.utils.SiteUtils;

import net.sf.json.JSONObject;

/**
* @author 周亮 
* @version 创建时间：2016年6月4日 下午6:39:02
* 类说明
*/
@Controller
@RequestMapping(value="/front/download/")
public class FileAction {
	
	/**
	  * 文件服务
	  */
	private FileService fileService;
	private DownloadFileService downloadFileService;
	private ColumnTypeService columnTypeService;
	
	
	@RequestMapping(value="lookImage")
	public void lookImage(String savePath,HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException{
		//1.获取文件系统的根路径:D:/Soft/wlxopensystem/
		String fileSystemRoot = SiteUtils.getFileSystemDir();
		//2.生成文件的绝对路径:D:/Soft/wlxopensystem/news/files/报名表.doc
		String fileSavePath = fileSystemRoot+savePath;
		//2.1获取文件资源
		Resource fileResource =fileService.getFileResource("file:"+fileSavePath);
		//查看图片
		BaseForm.lookImage(response, fileResource);
		
	}
	/**
	 * 下载文件
	 * @param savePath 文件保存路径
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value="down")
	public String download(String savePath,HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException{
		//下载次数加1
		DownloadFile downFile= downloadFileService.findByPath(savePath);
		String originName = downFile.getOriginName();
		downFile.setDownloadCount(downFile.getDownloadCount()+1);
		downloadFileService.update(downFile);
		
		//1.获取文件系统的根路径:D:/Soft/wlxopensystem/
		String fileSystemRoot = SiteUtils.getFileSystemDir();
		//2.生成文件的绝对路径:D:/Soft/wlxopensystem/news/files/报名表.doc
		String fileSavePath = fileSystemRoot+savePath;
		//originName = URLEncoder.encode(originName, "utf-8");
		//2.1获取文件资源
		Resource fileResource =fileService.getFileResource("file:"+fileSavePath);
		//查看图片
		BaseForm.loadFile(response, fileResource,originName);
		return null;
	}
	
	/**
	 * 从首页查看更多下载
	 * @param classCode  前缀 ,定为down
	 * @param model 
	 * @return
	 */
	@RequestMapping(value="more")
	public String more(String classCode ,Model model){
		String wherejpql = " o.classCode like ? ";
		LinkedHashMap<String,String> orderby=new LinkedHashMap<String,String>();
		orderby.put("sequence", "asc");
		
		List<Object> params = new ArrayList<Object>();
		
		params.add(classCode+"%");
		
		List<ColumnType>  columns = columnTypeService.getAllData(wherejpql, params.toArray(), orderby);
		model.addAttribute("listColumn",columns);
		model.addAttribute("classCode", classCode);
		
		return SiteUtils.getPage("front.download.more");
	}
	
	
	/**
	 * 查看一个栏目的所有下载文件
	 * @param columnId
	 * @param model
	 * @return
	 */
	@RequestMapping(value="single")
	public String singleColumn(DownloadFileForm formbean,String classCode,Model model){
		
		//查询下载类别
		String jpql = " o.classCode like ? ";
		LinkedHashMap<String,String> order=new LinkedHashMap<String,String>();
		order.put("sequence", "asc");
		
		List<Object> param = new ArrayList<Object>();
		
		param.add(classCode+"%");
		
		List<ColumnType>  columns = columnTypeService.getAllData(jpql, param.toArray(), order);
		model.addAttribute("listColumn",columns);
		
		//查询某个类别下的下载列表
		PageView<DownloadFile> pageView = new PageView<DownloadFile>(formbean.getMaxresult(), formbean.getPage());
		//结果集根据时间降序来排列
		LinkedHashMap<String,String> orderby=new LinkedHashMap<String,String>();
		orderby.put("createTime", "desc");
		
		StringBuffer wherejpql=new StringBuffer("");
		List<Object> params = new ArrayList<Object>();
		
		wherejpql.append("o.column.id = ?");
		params.add(formbean.getColumnId());
		//有效
		wherejpql.append(" and o.state= ?");
		params.add(FileStateEnum.VALIDATE);
		//文件
		wherejpql.append("and o.type = ?");
		params.add(FileTypeEnum.NO_IMAGE);
		
		QueryResult<DownloadFile> queryResult=downloadFileService.getScrollData(pageView.getFirstResult(), pageView.getMaxresult(), wherejpql.toString(), params.toArray(), orderby);
		pageView.setQueryResult(queryResult);
		//传输到页面
		model.addAttribute("pageView", pageView);
		
		model.addAttribute("formbean",formbean);

		model.addAttribute("classCode", classCode);
		return SiteUtils.getPage("fornt.download.single");
	}
	
	public FileService getFileService() {
		return fileService;
	}
	@Autowired
	public void setFileService(FileService fileService) {
		this.fileService = fileService;
	}
	public ColumnTypeService getColumnTypeService() {
		return columnTypeService;
	}
	@Autowired
	public void setColumnTypeService(ColumnTypeService columnTypeService) {
		this.columnTypeService = columnTypeService;
	}
	public DownloadFileService getDownloadFileService() {
		return downloadFileService;
	}
	@Autowired
	public void setDownloadFileService(DownloadFileService downloadFileService) {
		this.downloadFileService = downloadFileService;
	}
	
}
