package com.cnu.wlx.action.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cnu.wlx.formbean.ColumnTypeForm;

/**
* @author 周亮 
* @version 创建时间：2016年4月13日 下午3:29:20
* 类说明
*/
@Controller
@RequestMapping(value="/control/column/*")
public class ColumnTypeManageAction {

	
	public String addColumn(ColumnTypeForm formbean){
		
		//1.校验
		
		return "";
	}
}
