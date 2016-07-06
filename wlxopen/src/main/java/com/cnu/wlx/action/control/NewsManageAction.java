package com.cnu.wlx.action.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

import com.cnu.wlx.bean.Admin;
import com.cnu.wlx.bean.ColumnType;
import com.cnu.wlx.bean.DownloadFile;
import com.cnu.wlx.bean.News;
import com.cnu.wlx.bean.NewsFile;
import com.cnu.wlx.bean.Question;
import com.cnu.wlx.bean.base.MyStatus;
import com.cnu.wlx.bean.base.PageView;
import com.cnu.wlx.bean.base.QueryResult;
import com.cnu.wlx.formbean.BaseForm;
import com.cnu.wlx.formbean.NewsFileForm;
import com.cnu.wlx.formbean.NewsForm;
import com.cnu.wlx.myenum.ColorEnum;
import com.cnu.wlx.myenum.FileStateEnum;
import com.cnu.wlx.myenum.FileTypeEnum;
import com.cnu.wlx.myenum.NewsStateEnum;
import com.cnu.wlx.myenum.StateEnum;
import com.cnu.wlx.service.AdminService;
import com.cnu.wlx.service.ColumnTypeService;
import com.cnu.wlx.service.FileService;
import com.cnu.wlx.service.NewsFileService;
import com.cnu.wlx.service.NewsPictureService;
import com.cnu.wlx.service.NewsService;
import com.cnu.wlx.utils.SiteUtils;
import com.cnu.wlx.utils.WebUtils;

import net.sf.json.JSONArray;
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
public class NewsManageAction {

	private NewsService newsService;
	private ColumnTypeService columnTypeService;
	private NewsPictureService newsPictureService;
	 /**
	  * 文件服务
	  */
	 private FileService fileService;
	 private AdminService adminService;
	 /**
	  * 新闻附件服务类
	  */
	 private NewsFileService newsFileService;
     
	 @RequestMapping(value="menu")
	 public String menu(String newsId,String columnId,Model model){
		 model.addAttribute("newsId", newsId);
		 model.addAttribute("columnId", columnId);
		 return SiteUtils.getPage("control.news.menu");
	 }
	 
	 @RequestMapping(value="detailmain")
	 public String detailmain(String id,String columnId,Model model){
		 model.addAttribute("id", id);
		 model.addAttribute("columnId", columnId);
		 return SiteUtils.getPage("control.news.detailmain");
	 }
	 /**
	  * 上传图片
	  * @param request
	  * @param response
	  * @param upload 上传文件
	  * @return
	  * @throws IOException
	  */
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
        if(upload.getSize()> 5*1024*1024){  
        	result="window.parent.CKEDITOR.tools.callFunction(" + callback + ",'" + "图片大小不得大于5M','');";
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
      	//返回图片访问路径
        result="window.parent.CKEDITOR.tools.callFunction(" + callback + ",'"+basePath +"front/news/lookImage.uhtml?savePath=/" +relativeSavePath+ saveFileName + "','')";
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
	/**
	 * 后期上传新闻附件
	 * @param formbean
	 * @return
	 */
	@RequestMapping(value="saveNewsFile")
	public String saveNewsFile(NewsFileForm formbean){
		
		News news = newsService.find(formbean.getNewsId());
		//附件类型
		FileTypeEnum type=FileTypeEnum.valueOf(formbean.getType());
		//保存附件
		if( formbean.getNfileIds()!=null){
			for( int i =0;i <formbean.getNfileIds().size();i++){
				String fileid= formbean.getNfileIds().get(i);
				//保存附件
				NewsFile newsFile=newsFileService.find(fileid);
				newsFile.setType(type);
				newsFile.setNews(news);
				//更新
				newsFileService.update(newsFile);
			}
		}
		
		return "redirect:/control/news/listfile.action?newsId="+formbean.getNewsId()+"&type="+formbean.getType()+"&page="+formbean.getPage();
	}
	
	/**
	 * 上传附件
	 * @return
	 * {
	 *   "status":1,
	 *    "message":"ok",
	 *    "data":[
	 *     {"fileId":"20164225567979423"}
	 *     {"fileId":"20164225567979423"}
	 *     {"fileId":"20164225567979423"}
	 *     ...
	 *    ]
	 * }
	 */
	@RequestMapping(value="ajaxuploadFile", method=RequestMethod.POST)
	public String ajaxuploadFile(MultipartHttpServletRequest request,Model model){
	   
	   MyStatus status = new MyStatus();
	   JSONObject json= new JSONObject();
	   
	   Iterator<String> iterator = request.getFileNames();
	   //遍历所有上传文件
	   JSONArray jsonArray = new JSONArray();
		while (iterator.hasNext()) {
				String fileName = iterator.next();
				MultipartFile multipartFile = request.getFile(fileName);
				String originName=multipartFile.getOriginalFilename();
				
				//保存文件相对路径:files/
				String relativedir= SiteUtils.getRelativeSavePath("news.file");
				//保存文件名称
		        String saveFileName = WebUtils.getFileSaveName(originName);
				try {
			        //保存文件
			        BaseForm.saveFile(relativedir, saveFileName, multipartFile);
			        //构造文件实体
			        NewsFile newsFile = new NewsFile();
			        newsFile.setOriginName(originName);
			        newsFile.setSaveName(saveFileName);
			        newsFile.setSavePath(relativedir+saveFileName);
			        newsFile.setExt(WebUtils.getExtFromFilename(saveFileName));
			        newsFile.setSize(multipartFile.getSize());
			        newsFileService.save(newsFile);
			        //构造json
			        JSONObject fileJson = new JSONObject();
			        fileJson.put("fileId", newsFile.getId());
			        jsonArray.add(fileJson);
				} catch (Exception e) {
					e.printStackTrace();
					status.setStatus(0);
					status.setMessage(e.getMessage());
					break;
				}
		}
		//返回json数据
		json.put("status", status.getStatus());
		json.put("message", status.getMessage());
		json.put("data", jsonArray);
		model.addAttribute("json", json.toString());
		return SiteUtils.getPage("json");
	}
	
	/**
	 * 查询新闻列表
	 * @param formbean
	 * @return
	 */
	@RequestMapping(value="list")
	public String list(NewsForm formbean,Model model,HttpServletRequest request){
		//校验栏目是否为null
		if( formbean.validateList()){
			//生成导航信息
			BaseForm.navigationColumn(formbean, request);
			//状态PUBLISH("publish"),WAITING("waiting"),CLOSE("close");
			//页面类
			PageView<News> pageView = new PageView<News>(formbean.getMaxresult(), formbean.getPage());
			
			//结果集根据栏目的顺序升序,时间降序来排列
			LinkedHashMap<String,String> orderby=new LinkedHashMap<String,String>();
			orderby.put("sequence", "asc");
			orderby.put("createTime", "desc");
			//父类不为null
			if( BaseForm.validateStr(formbean.getColumnId())){
				String wherejpql="o.column.id = ? ";
				List<Object> params = new ArrayList<Object>();
				params.add(formbean.getColumnId());
				if( BaseForm.validateStr(formbean.getState())){
					wherejpql+=" and o.state= ?";
					NewsStateEnum st = NewsStateEnum.valueOf(formbean.getState());
					params.add(st);
				}
				if( formbean.getSuggest()==1|| formbean.getSuggest()==2)
				{
					wherejpql+=" and o.suggest= ?";
					params.add(formbean.getSuggest());
				}
				QueryResult<News> queryResult= 	newsService.getScrollData(pageView.getFirstResult(),pageView.getMaxresult(), wherejpql, params.toArray(), orderby);
				pageView.setQueryResult(queryResult);
				//传输到页面
				model.addAttribute("pageView", pageView);
			}
			model.addAttribute("formbean",formbean);
		}
 		return SiteUtils.getPage("control.news.list");
	}
	
    
	/**
	 * 查看新闻相关文件
	 * @param newsId 新闻id
	 * @param type 查看文件类型,IMAGE:图片类型 ;  NO_IMAGE:非图片类型
	 * @return
	 */
	@RequestMapping(value="listfile")
	public String listNewsFile(NewsFileForm formbean,Model model){
		//返回界面
		String page= "control.news.newfile";
		
		PageView<NewsFile> pageView = new PageView<NewsFile>(formbean.getMaxresult(), formbean.getPage());
		//排序：时间
		LinkedHashMap<String, String> orderby = new LinkedHashMap<>();
		orderby.put("createTime", "desc");
		//查询条件：所属新闻，文件类型
		StringBuffer wherejpql=new StringBuffer("");
		List<Object> params = new ArrayList<Object>();
		if( BaseForm.validateStr(formbean.getNewsId())){
			wherejpql.append(" o.news.id = ?");
			params.add(formbean.getNewsId());
		}
		if( BaseForm.validateStr(formbean.getType())){
			if( params.size()>0)
				wherejpql.append(" and ");
			wherejpql.append(" o.type = ?");
			params.add(FileTypeEnum.valueOf(formbean.getType()));
			if( formbean.getType().equals(FileTypeEnum.IMAGE.toString()))
				page="control.news.newpicture";
		}
		
		QueryResult<NewsFile> queryResult= newsFileService.getScrollData(pageView.getFirstResult(), pageView.getMaxresult(), wherejpql.toString(), params.toArray(), orderby);
		pageView.setQueryResult(queryResult);

		model.addAttribute("pageView", pageView);
		model.addAttribute("formbean", formbean);
		
		return SiteUtils.getPage(page);
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
	
	/**
	 * 
	 * 编辑界面
	 * @param id 新闻id
	 * @param columnId 所属栏目id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="editUi")
	public String editUi(String id,String columnId,Model model){
		News news=null;
		if( BaseForm.validateStr(id)){
			 news= newsService.find(id);
		}
		NewsForm formbean = new NewsForm();
		formbean.setNews(news);
		formbean.setColumnId(columnId);
		formbean.setTitleColor(news.getTitleColor().toString());
		model.addAttribute("formbean", formbean);
		
		return SiteUtils.getPage("control.news.edit");
	}
	/**
	 * 编辑新闻包括新闻标题，题目颜色，内容，附件
	 * @param formbean
	 * @param model
	 * @return
	 */
	@RequestMapping(value="edit")
	public String edit(NewsForm formbean,Model model){
		boolean flage = false;
		//校验
		if(formbean.validateAdd()){
			//查找要修改的新闻类
			News news =newsService.find(formbean.getNews().getId());
			if(news!=null){
				//修改
				news.setTitle(formbean.getNews().getTitle());
				//设置颜色
				ColorEnum color = ColorEnum.valueOf(formbean.getTitleColor());
				news.setTitleColor(color);
				news.setContext(formbean.getNews().getContext());
				//添加新的附件
				if( formbean.getFileIds()!=null){
					for( int i =0;i <formbean.getFileIds().size();i++){
						String fileid= formbean.getFileIds().get(i);
						//保存附件
						NewsFile newsFile=newsFileService.find(fileid);
						newsFile.setType(FileTypeEnum.NO_IMAGE);
						newsFile.setNews(news);
						//更新
						newsFileService.update(newsFile);
					}
				}
				newsService.update(news);
				formbean.setNews(news);
				flage = true;
			}
		}
		/*if( flage )//添加成功
		{
			return "redirect:/control/news/list.action?columnId="+formbean.getColumnId()+"&editState=true"+"&page="+formbean.getPage();
		}else{//添加失败
*/			model.addAttribute("formbean", formbean);
			return SiteUtils.getPage("control.news.edit");
		//}
		 
	}
	/**
	 * 添加新闻
	 * @param formbean
	 * @param model
	 * @return
	 */
	@RequestMapping(value="add")
	public String add(NewsForm formbean,Model model,HttpServletRequest request){
		
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
					//设置作者

					HttpSession session= request.getSession();
					Admin admin =(Admin)session.getAttribute("admin");
					news.setAuthor(admin.getAccount());
					//throw new RuntimeException("测试");
					newsService.save(news);
					//保存附件
					if( formbean.getFileIds()!=null){
						for( int i =0;i <formbean.getFileIds().size();i++){
							String fileid= formbean.getFileIds().get(i);
							//保存附件
							NewsFile newsFile=newsFileService.find(fileid);
							newsFile.setType(FileTypeEnum.NO_IMAGE);
							newsFile.setNews(news);
							//更新
							newsFileService.update(newsFile);
						}
					}
					//作者发文章
					admin=adminService.find(admin.getAccount());
					admin.setPublishCount(admin.getPublishCount()+1);
					adminService.update(admin);
					session.setAttribute("admin", admin);
					flage = true;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		if( flage )//添加成功
		{
			return  "redirect:/control/news/list.action?columnId="+formbean.getColumnId()+"&editState=true";
		}else{//添加失败
			model.addAttribute("formbean", formbean);
			return SiteUtils.getPage("control.news.addUi");
		}
	}
	/**
	 * 通过ajax删除暂时上传附件记录（）
	 * @param fileId
	 * @return
	 */
	@RequestMapping(value="ajaxdeleteFile")
	public String ajaxdeleteNewsFile(String fileId,Model model){
		
		MyStatus status = new MyStatus();
		try {
			if( BaseForm.validateStr(fileId)){
				newsFileService.delete(fileId);
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
	
	/**
	 * 批量更新新闻的附件
	 * @return
	 */
	@RequestMapping(value="updateFile")
	public String updateFile(NewsFileForm formbean){
		
		//获取批量id
		//获取批量state
		if( formbean.getCheckeds()!=null){
			for(int j=0;j < formbean.getCheckeds().size();j++){
				int i = formbean.getCheckeds().get(j);
				String id= formbean.getFileIds().get(i);
				NewsFile newsFile =newsFileService.find(id);
				String state = formbean.getStates().get(i);
				newsFile.setState(FileStateEnum.valueOf(state));
				
				newsFileService.update(newsFile);
				
			}
		}
		
		return "redirect:/control/news/listfile.action?newsId="+formbean.getNewsId()+"&type="+formbean.getType()+"&page="+formbean.getPage();
	} 
	
	/**
	 * 批量更新新闻的附件
	 * @return
	 */
	@RequestMapping(value="deleteFile")
	public String deleteFile(NewsFileForm formbean){
		
		//获取批量id
		if( formbean.getCheckeds()!=null){
			String[] ids = new String[formbean.getCheckeds().size()];
			for(int j=0;j < formbean.getCheckeds().size();j++){
				int i = formbean.getCheckeds().get(j);
				ids[j]=formbean.getFileIds().get(i);
			}
			newsFileService.delete(ids);
		}
		return "redirect:/control/news/listfile.action?newsId="+formbean.getNewsId()+"&type="+formbean.getType()+"&page="+formbean.getPage();
	} 
	/**
	 * 批量更新新闻的状态信息，不包含内容，题目，附件
	 * @return
	 */
	@RequestMapping(value="update")
	public String updateNews(NewsForm formbean){
		
		//获取批量id
		
		//获取批量state
		
		//获取批量sequence
		if( formbean.getCheckeds()!=null)
		if( formbean.getCheckeds()!=null){
			for(int j=0;j < formbean.getCheckeds().size();j++){
				int i = formbean.getCheckeds().get(j);
				String id= formbean.getColumnIds().get(i);
				String state = formbean.getStates().get(i);
				Integer sequence = formbean.getSequences().get(i);
				Integer suggest = formbean.getSuggests().get(i);
				
				News news = newsService.find(id);
				news.setSequence(sequence);
				news.setState(NewsStateEnum.valueOf(state));
				news.setSuggest(suggest);
				
				newsService.update(news);
				
			}
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
	
		if( formbean.getCheckeds()!=null){
			String[] ids = new String[formbean.getCheckeds().size()];
			for(int j=0;j < formbean.getCheckeds().size();j++){
				int i = formbean.getCheckeds().get(j);
				ids[j]=formbean.getColumnIds().get(i);
			}
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
	public NewsFileService getNewsFileService() {
		return newsFileService;
	}
	@Autowired
	public void setNewsFileService(NewsFileService newsFileService) {
		this.newsFileService = newsFileService;
	}
	public AdminService getAdminService() {
		return adminService;
	}

	@Autowired
	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}


	
}
