package com.cnu.wlx.filter;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.ConvertUtils;

import com.cnu.wlx.converter.DateConverter;

/**
* @author 周亮 
* @version 创建时间：2015年12月16日 下午1:47:30
* 类说明；编码过滤器
*/
public class EncodeFilter implements Filter {
	private String charset = "UTF-8";
	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

	HttpServletRequest req = (HttpServletRequest) request;
		req.setCharacterEncoding("UTF-8");
		chain.doFilter(req, response);
	}

	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		//获取编码
		String charset = fConfig.getInitParameter("charset");
		if(charset != null && !charset.isEmpty()) {
			this.charset = charset;
		}
		/*//初始化转换器
		ConvertUtils.register(new DateConverter(), Date.class);
		System.out.println("初始化好了。。。。");*/
	}
}
