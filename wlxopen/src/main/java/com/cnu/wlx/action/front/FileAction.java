package com.cnu.wlx.action.front;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cnu.wlx.formbean.BaseForm;
import com.cnu.wlx.service.FileService;
import com.cnu.wlx.utils.SiteUtils;

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
	 
	@RequestMapping(value="lookImage")
	public String lookImage(String savePath,HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException{
		//1.获取文件系统的根路径:D:/Soft/wlxopensystem/
		String fileSystemRoot = SiteUtils.getFileSystemDir();
		//2.生成文件的绝对路径:D:/Soft/wlxopensystem/news/files/报名表.doc
		String fileSavePath = fileSystemRoot+savePath;
		//2.1获取文件资源
		Resource fileResource =fileService.getFileResource("file:"+fileSavePath);
		//查看图片
		BaseForm.lookImage(response, fileResource);
		return null;
	}
	public FileService getFileService() {
		return fileService;
	}
	@Autowired
	public void setFileService(FileService fileService) {
		this.fileService = fileService;
	}
	
}
