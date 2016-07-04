package com.cnu.wlx.formbean;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.cnu.wlx.utils.SiteUtils;

public class BaseForm {
	/*当前页*/
	private int page = 1;  
	/**
	 * 每页显示最大数，默认10
	 */
	private int maxresult=8;
	/**
	 * 左边导航栏，标识第一次进入开始为右边左上角进行导航
	 */
	private boolean navigation=false;
	/**
	 * 栏目id
	 */
	private String columnId;
	/**
	 * 栏目名称
	 */
	private String columnName;
	/**
	 * 状态，true：管理状态，false：显示状态
	 */
	private boolean editState=false;
	/**
	 * 编辑框被选中的序号
	 */
	private List<Integer> checkeds;
	/**
	 * 是否推荐
	 */
	private int suggest;
	
	/**
	 * 存放表单校验后的结果
	 */
	private Map<String,String> result=new HashMap<String,String>();

	public boolean getEditState() {
		return editState;
	}
	public List<Integer> getCheckeds() {
		return checkeds;
	}

	public boolean getNavigation() {
		return navigation;
	}
	public void setNavigation(boolean navigation) {
		this.navigation = navigation;
	}
	public int getSuggest() {
		return suggest;
	}
	public void setSuggest(int suggest) {
		this.suggest = suggest;
	}
	public void setCheckeds(List<Integer> checkeds) {
		this.checkeds = checkeds;
	}
	public void setEditState(boolean editState) {
		this.editState = editState;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page < 1 ? 1 : page;
	}
	public int getMaxresult() {
		return maxresult;
	}
	public void setMaxresult(int maxresult) {
		this.maxresult = maxresult;
	}
	public void setResult(Map<String, String> result) {
		this.result = result;
	}
	public Map<String, String> getResult() {
		return result;
	}
	
	public String getColumnId() {
		return columnId;
	}
	public void setColumnId(String columnId) {
		this.columnId = columnId;
	}
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	/**
	 * 将orig中数据拷贝到dest中
	 * @param dest
	 * @param orig
	 * @throws Exception
	 */
	public static void copy(Object dest,Object orig) throws Exception{
		
		BeanUtils.copyProperties(dest, orig);
	}
	/**
	 * 判断是否是有效字符串。null、空字符串、空格字符串都是无效字符串；
	 * 无效字符串返回false,有效返回true
	 * @param str
	 * @return
	 */
	public static boolean validateStr(String str) {
		if( null ==str || "".equals(str.trim())){
			return false;
		}
		return true;
	}
	/**
	 * 验证上传文件类型是否属于图片格式
	 * @param fileFileName  文件名
	 * @param fileContentType 文件类型
	 * @return true表示属于图片，false：有误
	 */
	public static boolean validateImageFileType(String fileFileName,String fileContentType){
		
			List<String> arrowType = Arrays.asList("image/bmp","image/png","image/gif","image/jpg","image/jpeg","image/pjpeg");
			List<String> arrowExtension = Arrays.asList("gif","jpg","bmp","png","jpeg");
			String ext = getExt(fileFileName);
			return arrowType.contains(fileContentType.toLowerCase()) && arrowExtension.contains(ext);
		
	}

	/**
	 *  保存文件
	 * @param relativedir 文件的相对路径：即相对于文件系统根目录的路径
	 * @param savefilename 文件保存名称
	 * @param file  保存的文件
	 * @return 文件相对路径+保存文件名
	 * @throws Exception
	 */
	public static  void saveFile(String relativedir,String savefilename, CommonsMultipartFile file) throws Exception{

	   //获取保存的绝对路径
	   String absolutepath=null;
	   if( relativedir != null && !relativedir.equals("")){
		   //文件系统实际根目录
		   String fileSystemDir=SiteUtils.getFileSystemDir();
		   
		   absolutepath=fileSystemDir+relativedir;
		   //生成保存路径文件
		   File filedir = new File(absolutepath);
		   //不存在生成
		   if(!filedir.exists()){
			   filedir.mkdirs();
		   }
		   File savefile = new File(filedir,savefilename); //新建保存的文件 
		   try {
			   //将上传的文件写入  新建的文件中
			    file.getFileItem().write(savefile); 
		   }catch(FileNotFoundException fe){
			   throw new RuntimeException("文件不存在！");
		   }catch (Exception e) {
			    e.printStackTrace();
			    throw new RuntimeException("保存图片操作文件出错！");
		   }
	   }else{
		  throw new RuntimeException("保存路径未设置！");
	   }
    }
	/**
	 *  保存文件
	 * @param relativedir 文件的相对路径：即相对于文件系统根目录的路径
	 * @param savefilename 文件保存名称
	 * @param file  保存的文件
	 * @return 文件相对路径+保存文件名
	 * @throws Exception
	 */
	public static  void saveFile(String relativedir,String savefilename, MultipartFile file) throws Exception{

	   //获取保存的绝对路径
	   String absolutepath=null;
	   if( relativedir != null && !relativedir.equals("")){
		   //文件系统实际根目录
		   String fileSystemDir=SiteUtils.getFileSystemDir();
		   
		   absolutepath=fileSystemDir+relativedir;
		   //生成保存路径文件
		   File filedir = new File(absolutepath);
		   //不存在生成
		   if(!filedir.exists()){
			   filedir.mkdirs();
		   }
		   File savefile = new File(filedir,savefilename); //新建保存的文件 
		   try {
			   //将上传的文件写入  新建的文件中
			   file.transferTo(savefile);
			   
		   }catch(FileNotFoundException fe){
			   throw new RuntimeException("文件不存在！");
		   }catch (Exception e) {
			    e.printStackTrace();
			    throw new RuntimeException("保存图片操作文件出错！");
		   }
	   }else{
		  throw new RuntimeException("保存路径未设置！");
	   }
    }
	/**
	 * 下载文件/图片
	 * @param response  响应请求
	 * @param fileResource  文件资源
	 * @param isLoad 下载还是查看模式 ,false:查看模式，true:下载模式
	 */
	private static void loadFile(HttpServletResponse response,Resource fileResource,boolean isLoad){
		
		loadFile(response, fileResource, null, isLoad);
	}
	/**
	 * 下载文件/图片
	 * @param response  响应请求
	 * @param fileResource  文件资源
	 * @param isLoad 下载还是查看模式 ,false:查看模式，true:下载模式
	 */
	private static void loadFile(HttpServletResponse response,Resource fileResource,String originName,boolean isLoad){
		
		response.setCharacterEncoding("utf-8");
       // 
        if( isLoad){
        	if( BaseForm.validateStr(originName) ){
        		response.setContentType("multipart/form-data");
        		response.setHeader("Content-Disposition", "attachment;fileName="+ originName);
        	}else{
        		response.setContentType("multipart/form-data");
        		response.setHeader("Content-Disposition", "attachment;fileName="+ fileResource.getFilename());
        	}
        }
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
	}
	/**
	 * 下载文件
	 * @param response 响应请求
	 * @param fileResource  文件资源
	 */
	public static void loadFile(HttpServletResponse response,Resource fileResource){
		loadFile( response, fileResource,true);
	}
	/**
	 * 下载文件
	 * @param response 响应请求
	 * @param fileResource  文件资源
	 */
	public static void loadFile(HttpServletResponse response,Resource fileResource,String originName){
		loadFile( response, fileResource,originName,true);
	}
	/**
	 * 查看图片
	 * @param response 响应请求
	 * @param fileResource  图片资源
	 */
	public static void lookImage(HttpServletResponse response,Resource fileResource){
		loadFile( response, fileResource,false);
	}
	/**
	 * 下载图片
	 * @param response
	 * @param fileResource
	 */
	public static void loadImage(HttpServletResponse response,Resource fileResource){
		loadFile( response, fileResource,true);
	}
	/**
	 * 得到文件后缀名
	 * @param fileFileName
	 * @return
	 */
	public static String getExt(String fileFileName){
		return fileFileName.substring(fileFileName.lastIndexOf('.')+1).toLowerCase();
	}
	/**
	 * 校验字符串str的有效长度不小于min且不大于max。
	 * @param str,字符串，如果str两端有空格会去掉然后进行判断
	 * @param min
	 * @param max
	 * @return
	 */
	protected boolean validateLen(String str,int min,int max) {
		//正则表达式
		String regex ="\\S{"+min+","+max+"}";
		if(!str.matches(regex))
			return false;
		return true;
	}
	
	/**
	 * 生成导航信息
	 * @param formbean
	 * @param request
	 */
	public static  void navigationColumn(BaseForm formbean, HttpServletRequest request) {
		//从 左边导航条过来的进行导航
		if( formbean.getNavigation()){
			HttpSession session = request.getSession();
			session.setAttribute("navigationColumnId", formbean.getColumnId());
			session.setAttribute("navigationColumnName", formbean.getColumnName());
			session.setAttribute("navigationColumnEditState", formbean.getEditState());
		}
	}
}
