package com.cnu.wlx.action.front;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cnu.wlx.bean.HotInform;
import com.cnu.wlx.service.HotInformService;
import com.cnu.wlx.utils.SiteUtils;

/**
* @author 周亮 
* @version 创建时间：2016年6月8日 下午9:07:56
* 类说明
*/
@Controller
@RequestMapping(value="/front/center/")
public class FrontCenterAction {

	private HotInformService hotInformService;
	@RequestMapping(value="main")
	public String main(Model model){

		//重要公告
		HotInform inform= hotInformService.getUniqueHot();
		if(inform !=null){
			model.addAttribute("inform", inform);
			model.addAttribute("show",true);
		}else
			model.addAttribute("show", false);
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
	public HotInformService getHotInformService() {
		return hotInformService;
	}
	@Resource
	public void setHotInformService(HotInformService hotInformService) {
		this.hotInformService = hotInformService;
	}
	
}
