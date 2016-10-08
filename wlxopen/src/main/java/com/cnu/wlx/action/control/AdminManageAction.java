package com.cnu.wlx.action.control;

import java.util.LinkedHashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cnu.wlx.bean.Admin;
import com.cnu.wlx.bean.base.PageView;
import com.cnu.wlx.bean.base.QueryResult;
import com.cnu.wlx.formbean.AdminForm;
import com.cnu.wlx.formbean.BaseForm;
import com.cnu.wlx.myenum.StateEnum;
import com.cnu.wlx.service.AdminService;
import com.cnu.wlx.utils.SiteUtils;
import com.cnu.wlx.utils.WebUtils;

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
	

	@RequestMapping(value="addUi")
	public String addUi(){
		
		return SiteUtils.getPage("control.employee.addUi");
	}
	
	@RequestMapping(value="update")
	public String update(AdminForm formbean){
		
		if( formbean.getCheckeds()!=null){
			for(int j=0;j < formbean.getCheckeds().size();j++){
				int i = formbean.getCheckeds().get(j);
				String account= formbean.getAccounts().get(i);
				String password = formbean.getPasswords().get(i);
				String state = formbean.getStates().get(i);
				Admin admin =adminService.find(account);
				admin.setPassword(password);
				admin.setState(StateEnum.valueOf(state));
				
				adminService.update(admin);
			}
		}
		return "redirect:/control/employee/list.action?editState="+formbean.getEditState()+"&page="+formbean.getPage();
		
	}
	
	/**
	 * 添加管理员
	 * @param formbean
	 * @param model
	 * @return
	 */
	@RequestMapping(value="save")
	public String add(AdminForm formbean, Model model) {
		// 1.校验用户名密码
		String message="";
		if (!formbean.validateAccountAndPass()) {
			model.addAttribute("formbean", formbean);
			// 无效返回添加界面
			message= "账号密码不符合要求!";
			formbean.getResult().put("error", message);
			return SiteUtils.getPage("control.employee.addUi");
		}
		// 2.判断账号是否存在
		Admin ad = adminService.find(formbean.getAccount());
		// 存在怎返回
		if (ad != null) {
			model.addAttribute("formbean", formbean);
			message= "账号已存在!";
			formbean.getResult().put("error", message);

			return SiteUtils.getPage("control.employee.addUi");
		}
		Admin admin = new Admin();
		WebUtils.copyBean(admin, formbean);
		boolean res =adminService.add(admin);
		// 3.添加
		if (res) { // 成功
			return "redirect:/control/employee/list.action?&editState="+formbean.getEditState();
			
		} else { // 失败
			model.addAttribute("formbean", formbean);

			message= "添加失败!";
			formbean.getResult().put("error", message);
			return SiteUtils.getPage("control.employee.addUi");
		}
	}
	
	
	@RequestMapping(value = "list")
	public String list(AdminForm formbean, Model model,HttpServletRequest request) {
		//生成导航信息
		BaseForm.navigationColumn(formbean, request);
		//页面类
		PageView<Admin> pageView = new PageView<Admin>(formbean.getMaxresult(), formbean.getPage());
		
		//结果集根据栏目的顺序升序,时间降序来排列
		LinkedHashMap<String,String> orderby=new LinkedHashMap<String,String>();
		orderby.put("createTime", "desc");
		
		QueryResult<Admin> queryResult= adminService.getScrollData(pageView.getFirstResult(),pageView.getMaxresult(),orderby);
		pageView.setQueryResult(queryResult);
		//传输到页面
		model.addAttribute("pageView", pageView);

		model.addAttribute("formbean",formbean);
 		return SiteUtils.getPage("control.employee.list");
	}

	@RequestMapping(value = "delete")
	public String delete(AdminForm formbean){
		if( formbean.getCheckeds()!=null){
			String[] ids = new String[formbean.getCheckeds().size()];
			for(int j=0;j < formbean.getCheckeds().size();j++){
				int i = formbean.getCheckeds().get(j);
				ids[j]=formbean.getAccounts().get(i);
			}
			adminService.delete(ids);
		}
		return "redirect:/control/employee/list.action?columnId="+formbean.getColumnId()+"&editState="+formbean.getEditState()+"&page="+formbean.getPage();
	}
	
	
	public AdminService getAdminService() {
		return adminService;
	}

	@Resource
	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}

}
