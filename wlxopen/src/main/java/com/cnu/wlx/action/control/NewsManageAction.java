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

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.cnu.wlx.bean.ColumnType;
import com.cnu.wlx.bean.News;
import com.cnu.wlx.bean.NewsPicture;
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
		
		response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();  
        String uploadFileName= upload.getOriginalFilename();
        String uploadContentType=upload.getContentType();
        
        //设置返回“图像”选项卡  
        String callback = request.getParameter("CKEditorFuncNum"); 
        
      //对文件进行校验  
        if(upload==null || uploadContentType==null || uploadFileName==null){  

            out.println("<script type=\"text/javascript\">");  
            out.print("<font color=\"red\" size=\"2\">*请选择上传文件</font>");  
            out.println("</script>");
            return null;  
        }  
          
        System.out.println(uploadFileName+":"+uploadContentType+BaseForm.validateImageFileType(uploadFileName, uploadContentType));
        if(!BaseForm.validateImageFileType(uploadFileName, uploadContentType)){
           /* out.print("<font color=\"red\" size=\"2\">*文件格式不正确（必须为.jpg/.gif/.bmp/.png文件）</font>");  
           */

            out.println("<script type=\"text/javascript\">");  
        	out.println("window.parent.CKEDITOR.tools.callFunction(" + callback + ",'" + "文件格式不正确（必须为.jpg/.gif/.bmp/.png文件）','');"); 
        	out.println("</script>");
        	return null;  
        }  
          
        if(upload.getSize()> 600*1024){  
            /*out.print("<font color=\"red\" size=\"2\">*文件大小不得大于600k</font>");  */
        	
            out.println("<script type=\"text/javascript\">");  
        	out.println("window.parent.CKEDITOR.tools.callFunction(" + callback + ",'" + "文件格式不正确（必须为.jpg/.gif/.bmp/.png文件）','');"); 
        	out.println("</script>");
            return null;  
        }  
          
        //将文件保存到项目目录下  
        InputStream is = upload.getInputStream(); 
        
        String uploadPath = SiteUtils.getSavePath("news.image");   //设置保存绝对目录  
        String relativeSavePath= SiteUtils.getRelativeSavePath("news.image");//相对路径，在数据表中存储
        File dir = new File(uploadPath);
        if( !dir.exists())
        	dir.mkdirs();
        String fileName = WebUtils.getFileSaveName(); //采用当前日期+4位随机数的方式随机命名  
        fileName += uploadFileName.substring(uploadFileName.length() - 4);  
        
        File toFile = new File(dir, fileName);  
        OutputStream os = new FileOutputStream(toFile);     
        byte[] buffer = new byte[1024];     
        int length = 0;  
        while ((length = is.read(buffer)) > 0) {     
            os.write(buffer, 0, length);     
        }     
        is.close();  
        os.close();  
           
        String path = request.getContextPath();
      	String basePath = request.getScheme() + "://"
      			+ request.getServerName() + ":" + request.getServerPort()
      			+ path + "/";
      	
      	//保存文件记录
      	/*NewsPicture picture = new NewsPicture();
      	picture.setContentType(uploadContentType);
      	picture.setExt(BaseForm.getExt(uploadFileName));
      	picture.setOriginName(uploadFileName);
      	picture.setSaveName(fileName);
      	picture.setSize(upload.getSize());
      	picture.setRelativePath(relativeSavePath);
      	
      	newsPictureService.save(picture);*/
      	
        out.println("<script type=\"text/javascript\">");    
        /*out.println("window.parent.CKEDITOR.tools.callFunction(" + callback + ",'"+basePath +"images/group/" + fileName + "','')");    
        */
        System.out.println(basePath);
        out.println("window.parent.CKEDITOR.tools.callFunction(" + callback + ",'"+basePath +"control/news/lookImage.action?savePath=/" +relativeSavePath+ fileName + "','')");    
        
        out.println("</script>");  
        return null;  
	}
	/**
	 * 查看图片
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
			
			response.setCharacterEncoding("utf-8");
	        response.setContentType("multipart/form-data");
	       /* response.setHeader("Content-Disposition", "attachment;fileName="
	                + fileResource.getFilename());*/
			//3.建立文件
			try {
				//4.建立字节读取流
				InputStream is = fileResource.getInputStream();
				//5.建立缓冲流
				BufferedInputStream bis = new BufferedInputStream(is);
				//6.获取response的输出流
				OutputStream os = response.getOutputStream();
				//7.缓冲器2kb
				byte[] buf= new byte[2048];
				//8.进行传输
				int len=0;
				int count=0;
				while( (len=bis.read(buf))!=-1)
				{
					count+=len;
					os.write(buf, 0, len);
				}
				//9.刷新缓冲器
				os.flush();
				os.close();
				bis.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return null;
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
