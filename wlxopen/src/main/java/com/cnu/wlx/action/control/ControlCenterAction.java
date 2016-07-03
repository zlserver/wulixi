package com.cnu.wlx.action.control;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	public String left(Model model){//获取顶级父类栏目
		List<ColumnType> topColumns= columnTypeService.getTopColumns();
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
