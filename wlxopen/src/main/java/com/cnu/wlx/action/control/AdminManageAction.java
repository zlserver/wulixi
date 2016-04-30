package com.cnu.wlx.action.control;

import java.io.IOException;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cnu.wlx.bean.Admin;
import com.cnu.wlx.formbean.AdminForm;
import com.cnu.wlx.service.AdminService;
import com.cnu.wlx.utils.CheckCodeGenerator;
import com.cnu.wlx.utils.CheckCodeGenerator.CheckCode;
import com.cnu.wlx.utils.SiteUtils;

/**
 * 
 * @author liang
 * @version 创建时间 2016年4月8日 说明:管理员管理类。
 *
 */
@Controller
@RequestMapping(value = "/control/employee/*")
public class AdminManageAction {

	private Logger log = LogManager.getLogger(AdminManageAction.class);
	private AdminService adminService;
	
	
	/**
	 * 添加管理员
	 * @param formbean
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "add")
	public String add(AdminForm formbean, Model model) {
		// 1.校验用户名密码
		if (!formbean.validateAccountAndPass()) {
			model.addAttribute("formbean", formbean);
			// 无效返回添加界面
			return "index";
		}
		// 2.判断账号是否存在
		Admin ad = adminService.find(formbean.getAdmin().getAccount());
		// 存在怎返回
		if (ad != null) {
			model.addAttribute("formbean", formbean);
			return "index";
		}
		// 3.添加
		if (adminService.add(formbean)) { // 成功
			return "index";
		} else { // 失败
			model.addAttribute("formbean", formbean);
			return "success";
		}
	}
	
	public String login(AdminForm formbean, HttpServletRequest request) {
		// 1.进行校验码验证、进行用户名密码校验
		if (formbean.validateCheckCode(request)&&formbean.validateAccountAndPass()) {
			// 2.根据用户名和密码登录
			String ac = formbean.getAdmin().getAccount();
			String pa = formbean.getAdmin().getPassword();
			Admin ad = adminService.login(ac, pa);
			if (ad != null) {
				// 4.登录成功
				request.getSession().setAttribute("admin", ad);
				return SiteUtils.getPage("admin.controlcenter");
			}
			// 3.用户名或者密码有误
			formbean.getResult().put("account", "用户名或者密码有误!");
		}
		// 登录出错返回
		request.setAttribute("formbean", formbean);
		return SiteUtils.getPage("admin.login");
		
	}
	/**
	 * 登录界面
	 * @return
	 */
	@RequestMapping(value="loginUI")
	public String loginUI(){
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
