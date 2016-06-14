package com.cnu.wlx.action.front;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cnu.wlx.utils.SiteUtils;

/**
* @author 周亮 
* @version 创建时间：2016年6月8日 下午9:07:56
* 类说明
*/
@Controller
@RequestMapping(value="/front/center/")
public class FrontCenterAction {

	@RequestMapping(value="main")
	public String main(){
		return SiteUtils.getPage("front.center");
	}
	@RequestMapping(value="top")
	public String top(){
		
		return SiteUtils.getPage("front.top");
	}
	
	@RequestMapping(value="bottom")
	public String bottom(){
		return SiteUtils.getPage("front.bottom");
	}
}
