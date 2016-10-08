package com.cnu.wlx.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.cnu.wlx.bean.Admin;

public class AdminLoginInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object arg2) throws Exception {
		/*
		 * 1. 获取session中的admin
		 * 2. 判断是否为null
		 *   > 如果不为null：放行
		 */
		Admin admin = (Admin) req.getSession().getAttribute("admin");
		
		if(admin == null) {
			String path = req.getContextPath();
			String basePath = req.getScheme() + "://"
					+ req.getServerName() + ":" + req.getServerPort()
					+ path + "/relogin.jsp";
			resp.sendRedirect(basePath);
			return false;
			//System.out.println(basePath);
			//req.getRequestDispatcher(basePath).forward(req, response);
		} 
		return true;
	}

}
