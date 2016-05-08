package com.cnu.wlx.action.control;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.cnu.wlx.bean.ColumnType;
import com.cnu.wlx.bean.News;
import com.cnu.wlx.bean.NewsPicture;
import com.cnu.wlx.bean.base.MyStatus;
import com.cnu.wlx.bean.base.PageView;
import com.cnu.wlx.bean.base.QueryResult;
import com.cnu.wlx.dao.NewsPictureDao;
import com.cnu.wlx.formbean.BaseForm;
import com.cnu.wlx.formbean.NewsForm;
import com.cnu.wlx.myenum.ColorEnum;
import com.cnu.wlx.myenum.NewsStateEnum;
import com.cnu.wlx.service.ColumnTypeService;
import com.cnu.wlx.service.FileService;
import com.cnu.wlx.service.NewsPictureService;
import com.cnu.wlx.service.NewsService;
import com.cnu.wlx.utils.SiteUtils;
import com.cnu.wlx.utils.WebUtils;

import net.sf.json.JSONObject;

/**
 * 
 * @author liang
 * @version 创建时间 2016年5月2日
 * 说明:新闻管理类
 *
 */
@Controller
@RequestMapping(value="/control/news/*")
public class NewsManageAction  implements ServletContextAware{

	private NewsService newsService;
	private ColumnTypeService columnTypeService;
	 //应用对象
	 private ServletContext servletContext;
	 
	 private NewsPictureService newsPictureService;
	 /**
	  * 文件服务
	  */
	 private FileService fileService;
     //上传图片
	@RequestMapping(value="uploadImage")
	public String uploadImage(HttpServletRequest request,HttpServletResponse response, @RequestParam(value="upload")CommonsMultipartFile upload) throws IOException{
	   
		String uploadFileName= upload.getOriginalFilename();
        String uploadContentType=upload.getContentType();
      
        //设置返回“图像”选项卡  
        String callback = request.getParameter("CKEditorFuncNum"); 
        String result = "";
      //对文件进行校验  
        if(upload==null || uploadContentType==null || uploadFileName==null){  
            result="<font color=\"red\" size=\"2\">*请选择上传文件</font>";
            returnResult(response, result);
            return null;  
        }  
        if(!BaseForm.validateImageFileType(uploadFileName, uploadContentType)){
        	result="window.parent.CKEDITOR.tools.callFunction(" + callback + ",'" + "文件格式不正确（必须为.jpg/.gif/.bmp/.png文件）','');";
        	returnResult(response, result);
        	return null;  
        }  
        if(upload.getSize()> 600*1024){  
        	result="window.parent.CKEDITOR.tools.callFunction(" + callback + ",'" + "文件大小不得大于600k','');";
        	returnResult(response, result);
            return null;  
        }  
          
        //文件保存相对路径
        String relativeSavePath= SiteUtils.getRelativeSavePath("news.image");//相对路径，在数据表中存储
        //保存文件名称
        String saveFileName = WebUtils.getFileSaveName(uploadFileName);
        try {
        	//保存文件
			BaseForm.saveFile(relativeSavePath, saveFileName, upload);
		} catch (Exception e) {
			e.printStackTrace();
        	result="window.parent.CKEDITOR.tools.callFunction(" + callback + ",'" + "上传失败','');";
        	returnResult(response, result);
            return null;  
		}
        
        String path = request.getContextPath();
      	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
      	
        result="window.parent.CKEDITOR.tools.callFunction(" + callback + ",'"+basePath +"control/news/lookImage.action?savePath=/" +relativeSavePath+ saveFileName + "','')";
        returnResult(response, result);
        return null;  
	}
	/**
	 * 上传图片返回结果
	 * @param response
	 * @param result 返回结果
	 * @throws IOException
	 */
	private void returnResult( HttpServletResponse response,String result) throws IOException{
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter(); 
		out.println("<script type=\"text/javascript\">");  
    	out.println(result); 
    	out.println("</script>");
	}
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
	 * 上传新闻附件
	 * @return
	 */
	@RequestMapping(value="upload", method=RequestMethod.POST)
	public String handleFileUpload(MultipartHttpServletRequest request,Model model,String name,String testName){
	   
	   MyStatus status = new MyStatus();
	   Iterator<String> iterator = request.getFileNames();
		while (iterator.hasNext()) {
				String fileName = iterator.next();
				MultipartFile multipartFile = request.getFile(fileName);
				String originName=multipartFile.getOriginalFilename();
				
				try {
					//保存文件相对路径
					String relativedir= SiteUtils.getRelativeSavePath("news.file");
					//保存文件名
					   //保存文件名称
			        String saveFileName = WebUtils.getFileSaveName(originName);
			        //保存文件
			        BaseForm.saveFile(relativedir, saveFileName, multipartFile);
			        
				} catch (IOException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
		}
		// do stuff...
		JSONObject json= JSONObject.fromObject(status);
		model.addAttribute("json", json.toString());
	return SiteUtils.getPage("json");
	}
	
	/**
	 * 查询新闻列表
	 * @param formbean
	 * @return
	 */
	@RequestMapping(value="list")
	public String list(NewsForm formbean,Model model){
		//校验栏目是否为null
		if( formbean.validateList()){
			//页面类
			PageView<News> pageView = new PageView<News>(formbean.getMaxresult(), formbean.getPage());
			QueryResult<News> queryResult= newsService.getScrollData(pageView.getFirstResult(),pageView.getMaxresult(), formbean.getColumnId());
			pageView.setQueryResult(queryResult);
			//传输到页面
			model.addAttribute("pageView", pageView);
			
			model.addAttribute("formbean",formbean);
		}
		
		return SiteUtils.getPage("control.news.list");
	}

	/**
	 * 转发到添加新闻界面
	 * @return
	 */
	@RequestMapping(value="addUi")
	public String addUi(NewsForm formbean,Model model){
		model.addAttribute("columnId", formbean.getColumnId());
		return SiteUtils.getPage("control.news.addUi");
	}

	@RequestMapping(value="detail")
	public String detail(String id,Model model){
		
		if( BaseForm.validateStr(id)){
			News news =newsService.find(id);
			model.addAttribute("news", news);
		}
		return  SiteUtils.getPage("control.news.detail");
	}
	
	
	@RequestMapping(value="add")
	public String add(NewsForm formbean,Model model){
		
		boolean flage = false;
		//校验
		
		News news = new News();
		try {
			if(formbean.validateAdd()){
				//基本值设置
				formbean.copy(news, formbean.getNews());
				//设置时间
				news.setCreateTime(new Date());
				//设置栏目
				ColumnType column =columnTypeService.find(formbean.getColumnId());
				if( column!=null ){
					
					news.setColumn(column);
					//设置颜色
					ColorEnum color = ColorEnum.valueOf(formbean.getTitleColor());
					news.setTitleColor(color);
					//设置新闻状态
					NewsStateEnum state = NewsStateEnum.valueOf(formbean.getState());
					news.setState(state);
					//throw new RuntimeException("测试");
					newsService.save(news);
					flage = true;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		if( flage )//添加成功
		{
			return  "redirect:/control/news/list.action?columnId="+formbean.getColumnId();
		}else{//添加失败
			model.addAttribute("formbean", formbean);
			return SiteUtils.getPage("control.news.addUi");
		}
	}
	
	/**
	 * 批量更新
	 * @return
	 */
	@RequestMapping(value="update")
	public String updateNews(NewsForm formbean){
		
		//获取批量id
		
		//获取批量state
		
		//获取批量sequence
		if( formbean.getColumnIds()!=null)
		for(int i=0;i < formbean.getColumnIds().size();i++){
			String id= formbean.getColumnIds().get(i);
			String state = formbean.getStates().get(i);
			Integer sequence = formbean.getSequences().get(i);
			
			
			News news = newsService.find(id);
			news.setSequence(sequence);
			news.setState(NewsStateEnum.valueOf(state));
			newsService.update(news);
			
		}
		return "redirect:/control/news/list.action?columnId="+formbean.getColumnId()+"&editState="+formbean.getEditState()+"&page="+formbean.getPage();
	}
	/**
	 * 删除选中新闻
	 * @param formbean
	 * @return
	 */
	@RequestMapping(value="delete")
	public String delete(NewsForm formbean){
		if( formbean.getColumnIds()!=null){
			String[] ids = new String[formbean.getColumnIds().size()];
			for(int i = 0;i < formbean.getColumnIds().size();i++)
				ids[i]=formbean.getColumnIds().get(i);
			
			newsService.delete(ids);
		}
		
		return "redirect:/control/news/list.action?columnId="+formbean.getColumnId()+"&editState="+formbean.getEditState()+"&page="+formbean.getPage();
	}

	
	public NewsService getNewsService() {
		return newsService;
	}
	@Autowired
	public void setNewsService(NewsService newsService) {
		this.newsService = newsService;
	}

	public ColumnTypeService getColumnTypeService() {
		return columnTypeService;
	}
	@Autowired
	public void setColumnTypeService(ColumnTypeService columnTypeService) {
		this.columnTypeService = columnTypeService;
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		// TODO Auto-generated method stub
		this.servletContext = servletContext;
	}


	public NewsPictureService getNewsPictureService() {
		return newsPictureService;
	}

	@Autowired
	public void setNewsPictureService(NewsPictureService newsPictureService) {
		this.newsPictureService = newsPictureService;
	}

	public FileService getFileService() {
		return fileService;
	}
	@Autowired
	public void setFileService(FileService fileService) {
		this.fileService = fileService;
	}


	
}
