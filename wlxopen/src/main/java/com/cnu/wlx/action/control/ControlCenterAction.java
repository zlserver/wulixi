package com.cnu.wlx.action.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cnu.wlx.utils.SiteUtils;

/**
* @author 周亮 
* @version 创建时间：2016年4月13日 上午11:38:08
* 类说明：管理员控制中心管理
*/
@Controller
@RequestMapping(value="/control/")
public class ControlCenterAction {
	
	/**
	 * 主界面
	 * @return
	 */
	@RequestMapping(value="controlCenter")
	public String mainUi(){
		
		return SiteUtils.getSite("admin.controlcenter");
	}
}
