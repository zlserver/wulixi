package com.cnu.wlx.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cnu.wlx.bean.Admin;

public class PermissInterceptor implements HandlerInterceptor{

	/**
	 * action执行之前
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		/*
		 * 1. 获取session中的admin
		 * 2. 判断是否为null
		 *   > 如果不为null：放行
		 */
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		System.out.println("之前进行拦截："+admin+request.getRequestURI());
		if(admin == null) {
			String path = request.getContextPath();
			String basePath = request.getScheme() + "://"
					+ request.getServerName() + ":" + request.getServerPort()
					+ path + "/";
			response.sendRedirect(basePath+"login.jsp");
			return false;
		} 
		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("action之后");
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("action之后 ,视图之前");
	}

}
