package com.cnu.wlx.formbean;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

public class BaseForm {
	/*当前页*/
	private int page = 1;  
	/**
	 * 每页显示最大数，默认10
	 */
	private int maxresult=3;
	/**
	 * 栏目id
	 */
	private String columnId;
	/**
	 * 栏目名称
	 */
	private String columnName;
	/**
	 * 存放表单校验后的结果
	 */
	private Map<String,String> result=new HashMap<String,String>();

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
}
