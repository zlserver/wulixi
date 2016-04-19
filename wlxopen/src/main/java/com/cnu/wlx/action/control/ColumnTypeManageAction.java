package com.cnu.wlx.action.control;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.cnu.wlx.bean.ColumnType;
import com.cnu.wlx.bean.base.PageView;
import com.cnu.wlx.bean.base.QueryResult;
import com.cnu.wlx.formbean.BaseForm;
import com.cnu.wlx.formbean.ColumnTypeForm;
import com.cnu.wlx.myenum.ColumnTypeDesEnum;
import com.cnu.wlx.service.ColumnTypeService;
import com.cnu.wlx.utils.SiteUtils;

/**
* @author 周亮 
* @version 创建时间：2016年4月13日 下午3:29:20
* 类说明
*/
@Controller
@RequestMapping(value="/control/column/*")
public class ColumnTypeManageAction {

	/**
	 * 栏目服务类
	 */
	private ColumnTypeService columnTypeService;
	
	public String addColumn(ColumnTypeForm formbean){
		
		//1.校验
		
		return "";
	}
	@RequestMapping(value="list")
	public String list(ColumnTypeForm formbean,Model model){
		//页面类
		PageView<ColumnType> pageView = new PageView<ColumnType>(formbean.getMaxresult(), formbean.getPage());
		QueryResult<ColumnType> queryResult= columnTypeService.getScrollData(pageView.getFirstResult(),pageView.getMaxresult(), formbean.getParentId());
		pageView.setQueryResult(queryResult);
		//传输到页面
		model.addAttribute("pageView", pageView);

		//构造导航栏
		Map<String,String> urlParams =generatorNavigation(formbean.getParentId());
		//2.传回页面端，导航连接
		model.addAttribute("urlParams", urlParams);
		
		return SiteUtils.getSite("admin.column.list");
	}
	public ColumnTypeService getColumnTypeService() {
		return columnTypeService;
	}
	@Resource
	public void setColumnTypeService(ColumnTypeService columnTypeService) {
		this.columnTypeService = columnTypeService;
	}
	
	/**
	 * 从当前传入的栏目开始，生成其父类导航。
	 * @param id 栏目id
	 * @return Map，里面存放了父类的id和内容，并且最大的父类在第一位
	 */
	private LinkedHashMap<String ,String> generatorNavigation(String id){
		
		LinkedHashMap<String ,String> orderurlParams=new LinkedHashMap<>();
		//存放栏目id
		List<String>  listIds = new ArrayList<>();
		//存放栏目名称
		List<String> listNames= new ArrayList<>();
		if( BaseForm.validateStr(id)){
			//遍历得到所有经过的栏目
			ColumnType ct =columnTypeService.find(id);
			while( ct!=null){
				listNames.add(ct.getName());
				listIds.add(id);
				//得到父类
				ct = ct.getParent();
			}
			//将存储顺序反过来，第一个存放最大的父类
			for(int i = listIds.size()-1;i>=0;i--){
				orderurlParams.put(listIds.get(i), listNames.get(i));
			}
		}
		return orderurlParams;
	}
}
