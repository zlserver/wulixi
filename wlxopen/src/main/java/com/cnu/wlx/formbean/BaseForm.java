package com.cnu.wlx.formbean;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
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
