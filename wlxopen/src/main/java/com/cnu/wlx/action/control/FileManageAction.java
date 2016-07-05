package com.cnu.wlx.action.control;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sound.midi.Sequence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.cnu.wlx.bean.Admin;
import com.cnu.wlx.bean.ColumnType;
import com.cnu.wlx.bean.DownloadFile;
import com.cnu.wlx.bean.News;
import com.cnu.wlx.bean.NewsFile;
import com.cnu.wlx.bean.base.MyStatus;
import com.cnu.wlx.bean.base.PageView;
import com.cnu.wlx.bean.base.QueryResult;
import com.cnu.wlx.formbean.BaseForm;
import com.cnu.wlx.formbean.DownloadFileForm;
import com.cnu.wlx.formbean.NewsForm;
import com.cnu.wlx.myenum.ColorEnum;
import com.cnu.wlx.myenum.FileStateEnum;
import com.cnu.wlx.myenum.FileTypeEnum;
import com.cnu.wlx.myenum.NewsStateEnum;
import com.cnu.wlx.service.ColumnTypeService;
import com.cnu.wlx.service.DownloadFileService;
import com.cnu.wlx.service.FileService;
import com.cnu.wlx.utils.SiteUtils;
import com.cnu.wlx.utils.WebUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
* @author 周亮 
* @version 创建时间：2016年5月23日 下午2:41:16
* 类说明:下载文件管理
*/

@Controller
@RequestMapping(value="/control/download/*")
public class FileManageAction {
	
	private DownloadFileService downloadFileService;
	private ColumnTypeService columnTypeService;
	/**
	  * 文件服务
	  */
	 private FileService fileService;
	@RequestMapping(value="list")
	public String list(DownloadFileForm formbean,Model model,HttpServletRequest request){
		String page ="control.download.list";
		
		//生成导航信息
		formbean.navigationColumn(formbean, request);
		
		//页面类
		PageView<DownloadFile> pageView = new PageView<DownloadFile>(formbean.getMaxresult(), formbean.getPage());
		
		//结果集根据时间降序来排列
		LinkedHashMap<String,String> orderby=new LinkedHashMap<String,String>();
		//orderby.put("sequence", "desc");
		orderby.put("createTime", "desc");
		
		String columnId = formbean.getColumnId();
		FileStateEnum state= null;
		if( BaseForm.validateStr(formbean.getState()) )
		    state = FileStateEnum.valueOf(formbean.getState());

		StringBuffer wherejpql=new StringBuffer("");
		List<Object> params = new ArrayList<Object>();
		//父类不为null
		if( BaseForm.validateStr(columnId)){
			wherejpql.append("o.column.id = ?");
			params.add(columnId);
			if(state!=null){
				wherejpql.append(" and o.state= ?");
				params.add(state);
			}
			if( BaseForm.validateStr(formbean.getType())){
				if( params.size()>0)
					wherejpql.append(" and ");
				wherejpql.append(" o.type = ?");
				params.add(FileTypeEnum.valueOf(formbean.getType()));
				if( formbean.getType().equals(FileTypeEnum.IMAGE.toString()))
					page="control.download.listpic";
			}
			if( formbean.getSuggest()==1|| formbean.getSuggest()==2)
			{
				wherejpql.append(" and o.suggest= ?");
				params.add(formbean.getSuggest());
			}
		}
		QueryResult<DownloadFile> queryResult= downloadFileService.getScrollData(pageView.getFirstResult(), pageView.getMaxresult(), wherejpql.toString(), params.toArray(), orderby);
		pageView.setQueryResult(queryResult);
		//传输到页面
		model.addAttribute("pageView", pageView);
		
		model.addAttribute("formbean",formbean);
		
		return SiteUtils.getPage(page);
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
		//
		DownloadFile downFile= downloadFileService.findByPath(savePath);
		String originName = downFile.getOriginName();
		
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
	@RequestMapping(value="addUi")
	public String addUi(String columnId,Model model){
		model.addAttribute("columnId", columnId);
		return SiteUtils.getPage("control.download.addUi");
	}
	@RequestMapping(value="update")
	public String update(DownloadFileForm formbean){
		
		//获取批量id
		//获取批量state
		//获取批量sequence
		if( formbean.getCheckeds()!=null){
			for(int j=0;j < formbean.getCheckeds().size();j++){
				int i = formbean.getCheckeds().get(j);
				String id= formbean.getFileIds().get(i);
				String state = formbean.getStates().get(i);
				int suggest = formbean.getSuggests().get(i);
				DownloadFile downloadFile = downloadFileService.find(id);
				
				downloadFile.setState(FileStateEnum.valueOf(state));
				downloadFile.setSuggest(suggest);
				downloadFileService.update(downloadFile);
				
			}
		}
		
		return "redirect:/control/download/list.action?columnId="+formbean.getColumnId()+"&type="+formbean.getType()+"&editState="+formbean.getEditState()+"&page="+formbean.getPage();
	}
	/**
	 * 删除文件
	 * @param fileId
	 * @return
	 */
	@RequestMapping(value="delete")
	public String deleteFile(DownloadFileForm formbean,Model model){
	
		try {
			if( formbean.getCheckeds()!=null){
				String[] ids = new String[formbean.getCheckeds().size()];
				for(int j=0;j < formbean.getCheckeds().size();j++){
					int i = formbean.getCheckeds().get(j);
					ids[j]=formbean.getFileIds().get(i);
				}
				downloadFileService.delete(ids);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/control/download/list.action?columnId="+formbean.getColumnId()+"&type="+formbean.getType()+"&editState="+formbean.getEditState()+"&page="+formbean.getPage();
		
	}
	/**
	 * 删除预上传的文件
	 * @param fileId
	 * @return
	 */
	@RequestMapping(value="ajaxdeleteFile")
	public String ajaxdeleteNewsFile(String fileId,Model model){
		
		MyStatus status = new MyStatus();
		try {
			if( BaseForm.validateStr(fileId)){
				downloadFileService.delete(fileId);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			status.setStatus(0);
			status.setMessage(e.getMessage());
		}
		JSONObject json = JSONObject.fromObject(status);
		model.addAttribute("json", json.toString());
		return SiteUtils.getPage("json");
	}
	@RequestMapping(value="add")
	public String add(DownloadFileForm formbean,Model model,HttpServletRequest request){
		
		try {
				//设置栏目
				ColumnType column =columnTypeService.find(formbean.getColumnId());
				if( column!=null ){
					FileTypeEnum type = FileTypeEnum.NO_IMAGE;
					if(BaseForm.validateStr(formbean.getType())){
						 type = FileTypeEnum.valueOf(formbean.getType());
					}
					//保存附件
					if( formbean.getNfileIds()!=null){
						for( int i =0;i <formbean.getNfileIds().size();i++){
							String fileid= formbean.getNfileIds().get(i);
							//保存附件
							DownloadFile downloadFile=downloadFileService.find(fileid);
							downloadFile.setColumn(column);
							downloadFile.setType(type);
							//更新
							downloadFileService.update(downloadFile);
						}
					}
				}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return  "redirect:/control/download/list.action?columnId="+formbean.getColumnId()+"&type="+formbean.getType()+"&editState="+formbean.getEditState()+"&page="+formbean.getPage();
	
	}
	@RequestMapping(value="ajaxuploadFile", method=RequestMethod.POST)
	public String ajaxuploadFile(MultipartHttpServletRequest request,Model model,DownloadFileForm formbean){
	   
	   MyStatus status = new MyStatus();
	   JSONObject json= new JSONObject();
	   
	   Iterator<String> iterator = request.getFileNames();
	   //遍历所有上传文件
	   JSONArray jsonArray = new JSONArray();
	   try {
				while (iterator.hasNext()) {
						String fileName = iterator.next();
						MultipartFile multipartFile = request.getFile(fileName);
						String originName=multipartFile.getOriginalFilename();
						//替换掉原文件名称中的中文空格
						originName=originName.replace(" ","");
						//保存文件相对路径:download/
						String relativedir= SiteUtils.getRelativeSavePath("download.file");
						//保存文件名称
				        String saveFileName = WebUtils.getFileSaveName(originName);
						
				        //保存文件
				        BaseForm.saveFile(relativedir, saveFileName, multipartFile);
				        //构造文件实体
				        Admin admin= (Admin) request.getSession().getAttribute("admin");
				        DownloadFile downloadFile = new DownloadFile(originName, ColorEnum.BLACK, saveFileName, admin.getAccount(), relativedir+saveFileName, multipartFile.getSize(), WebUtils.getExtFromFilename(saveFileName), null);		
				        downloadFileService.save(downloadFile);
				        //构造json
				        JSONObject fileJson = new JSONObject();
				        fileJson.put("fileId", downloadFile.getId());
				        jsonArray.add(fileJson);
						
				}
	   } catch (Exception e) {
			e.printStackTrace();
			status.setStatus(0);
			status.setMessage(e.getMessage());   
		}
		//返回json数据
		json.put("status", status.getStatus());
		json.put("message", status.getMessage());
		json.put("data", jsonArray);
		model.addAttribute("json", json.toString());
		return SiteUtils.getPage("json");
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
	public FileService getFileService() {
		return fileService;
	}
	@Autowired
	public void setFileService(FileService fileService) {
		this.fileService = fileService;
	}
}
