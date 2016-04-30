package com.cnu.wlx.action.front;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cnu.wlx.action.control.AdminManageAction;
import com.cnu.wlx.bean.Admin;
import com.cnu.wlx.formbean.AdminForm;
import com.cnu.wlx.service.AdminService;
import com.cnu.wlx.utils.SiteUtils;

/**
* @author 周亮 
* @version 创建时间：2016年4月13日 上午10:17:51
* 类说明:负责处理管理员登录
*/
@Controller
@RequestMapping(value="/common/*")
public class LoginManageAction {

	private Logger log = LogManager.getLogger(AdminManageAction.class);
	private AdminService adminService;

	/**
	 * 登录
	 * @param formbean
	 * @param request
	 * @return
	 */
	@RequestMapping(value="login")
	public String login(AdminForm formbean, HttpServletRequest request) {
		// 1.进行校验码验证、进行用户名密码校验
		if (formbean.validateCheckCode(request)&&formbean.validateAccountAndPass()) {
			// 2.根据用户名和密码登录
			String ac = formbean.getAdmin().getAccount();
			String pa = formbean.getAdmin().getPassword();
			Admin ad = adminService.login(ac, pa);
			if (ad != null) {
				// 4.登录成功,更新登录次数
				ad.updateLoginCount();
				ad.updateLoginTime();
				adminService.update(ad);
				//5.进入管理中心。
				request.getSession().setAttribute("admin", ad);
				return "redirect:/"+"control/controlCenter.html";
				//return SiteUtils.getSite("admin.controlcenter");
			}
			// 3.用户名或者密码有误
			formbean.getResult().put("error", "用户名或者密码有误!");
		}
		// 登录出错返回
		request.setAttribute("formbean", formbean);
		return SiteUtils.getPage("admin.login");
		
	}
	/**
	 * 用户退出
	 * @param request
	 * @return
	 */
	@RequestMapping(value="exit")
	public String exit(HttpServletRequest request){
		request.getSession().invalidate();
		return SiteUtils.getPage("admin.login");
	}

	public AdminService getAdminService() {
		return adminService;
	}
	@Resource
	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}
	
}
