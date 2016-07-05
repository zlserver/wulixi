package com.cnu.wlx.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
* @author 周亮 
* @version 创建时间：2015年12月16日 下午1:47:30
* 类说明；编码过滤器
*/
public class EncodeFilter implements Filter {
	

	/**
	 * 日志输出
	 */
	private Logger log = LogManager.getLogger(EncodeFilter.class);
	
	private String charset = "UTF-8";
	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		response.setCharacterEncoding(charset);
		HttpServletRequest req = (HttpServletRequest) request;
		//System.out.println(req.getMethod()+"请求");
		log.info(req.getMethod()+"请求");
		if(req.getMethod().equalsIgnoreCase("GET")) {
			if(!(req instanceof CharSetRequest)) {
				req = new CharSetRequest(req, charset);//处理get请求编码
				
			}
		} else {
			req.setCharacterEncoding(charset);//处理post请求编码
		}
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
