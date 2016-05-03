/**
 * 
 */
package com.cnu.wlx.filter;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * @author zhouliang
 * @2015年4月25日
 */
public class CharSetRequest extends HttpServletRequestWrapper{
	private HttpServletRequest request;
	private String charset;
	/**
	 * @param request
	 */
	public CharSetRequest(HttpServletRequest request, String charset) {
		super(request);
		this.request = request;
		this.charset = charset;
	}
	/**
	 * 增强getParameter方法
	 */

	@Override
	public String getParameter(String name) {
		// 获取参数
		String value = request.getParameter(name);
		if(value == null) return null;//如果为null，直接返回null
		try {
			// 对参数进行编码处理后返回
			return new String(value.getBytes("ISO-8859-1"), charset);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Map getParameterMap() {
		Map<String,String[]> map = request.getParameterMap();
		if(map == null) return map;
		// 遍历map，对每个值进行编码处理
		for(String key : map.keySet()) {
			String[] values = map.get(key);
			for(int i = 0; i < values.length; i++) {
				try {
					values[i] = new String(values[i].getBytes("ISO-8859-1"), charset);
					
				} catch (UnsupportedEncodingException e) {
					throw new RuntimeException(e);
				}
			}
		}
		// 处理后返回
		return map;
	}
	
	@Override
	public String[] getParameterValues(String name) {
		String[] values = super.getParameterValues(name);
		if( values!=null)
		for(int i = 0; i < values.length; i++) {
			try {
				values[i] = new String(values[i].getBytes("ISO-8859-1"), charset);
			} catch (UnsupportedEncodingException e) {
				throw new RuntimeException(e);
			}
		}
		return values;
	}
}
