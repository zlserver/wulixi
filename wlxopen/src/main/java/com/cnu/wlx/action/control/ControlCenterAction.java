package com.cnu.wlx.action.control;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cnu.wlx.bean.Admin;
import com.cnu.wlx.bean.ColumnType;
import com.cnu.wlx.service.ColumnTypeService;
import com.cnu.wlx.utils.SiteUtils;

/**
* @author 周亮 
* @version 创建时间：2016年4月13日 上午11:38:08
* 类说明：管理员控制中心管理
*/
@Controller
@RequestMapping(value="/control/center/")
public class ControlCenterAction {
	/**
	 * 栏目服务类
	 */
	private ColumnTypeService columnTypeService;
	/**
	 * 主界面
	 * @return
	 */
	@RequestMapping(value="controlCenter")
	public String mainUi(){
		
		return SiteUtils.getPage("control.center.controlcenter");
	}
	
	@RequestMapping(value="top",method=RequestMethod.GET)
	public String top(){
		return SiteUtils.getPage("control.center.top");
	}
	@RequestMapping(value="left",method=RequestMethod.GET)
	public String left(Model model,HttpServletRequest request){//获取顶级父类栏目
		List<ColumnType> topColumns= columnTypeService.getTopColumns();
		//查询到“导航管理”顶级栏目，将其子栏目按照visible降序排列，使visible为true的在前面
		Admin admin=(Admin) request.getSession().getAttribute("admin");
		if( admin.getRole()!=1){
			//除去员工管理、系统管理栏目
			ColumnType  syscol = new ColumnType();
			syscol.setClassCode("XTGL01");
			ColumnType  emplcol = new ColumnType();
			emplcol.setClassCode("YGGL01");

			topColumns.remove(syscol);
			topColumns.remove(emplcol);
		}
		//保存到
		model.addAttribute("topColumns", topColumns);
		return SiteUtils.getPage("control.center.left");
	}
	@RequestMapping(value="main",method=RequestMethod.GET)
	public String content(){
		return SiteUtils.getPage("control.center.main");
	}
	public ColumnTypeService getColumnTypeService() {
		return columnTypeService;
	}
	@Resource
	public void setColumnTypeService(ColumnTypeService columnTypeService) {
		this.columnTypeService = columnTypeService;
	}
	
}
