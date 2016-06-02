package com.cnu.wlx.action.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 郭明丽 
 * @version 创建时间：2016年6月2日 下午4:25:13 
 * 类说明 
*/

@Controller
@RequestMapping(value="/ui/")
public class TestAction {

	@RequestMapping(value="test")
	public String ui(){
		return "front/index";
	}
}
