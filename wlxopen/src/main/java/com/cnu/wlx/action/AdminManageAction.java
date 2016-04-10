package com.cnu.wlx.action;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cnu.wlx.bean.Admin;
import com.cnu.wlx.formbean.AdminForm;
import com.cnu.wlx.service.AdminService;
/**
 * 
 * @author liang
 * @version 创建时间 2016年4月8日
 * 说明:管理员管理类。
 *
 */
@Controller
@RequestMapping(value="/common/employee/")
public class AdminManageAction {

	private AdminService adminService;
	@RequestMapping(value="add")
	public String add(AdminForm formbean,Model model){
		//1.校验
		if(!formbean.validate())
		{
			model.addAttribute("formbean", formbean);
			//无效返回添加界面
			return "index";
		}
		//2.判断账号是否存在
		Admin ad= adminService.find(formbean.getAdmin().getAccount());
		//存在怎返回
		if( ad!=null){
			model.addAttribute("formbean", formbean);
			return "index";
		}
		
		//3.添加
		if(adminService.add(formbean))
		{  //成功
			return "index";
		}else{ //失败
			model.addAttribute("formbean", formbean);
			return "success";
		}
	}

	public AdminService getAdminService() {
		return adminService;
	}
	@Resource
	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}
	
}
