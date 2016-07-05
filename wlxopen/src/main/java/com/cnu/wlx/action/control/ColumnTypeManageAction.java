package com.cnu.wlx.action.control;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cnu.wlx.bean.ColumnType;
import com.cnu.wlx.bean.base.MyStatus;
import com.cnu.wlx.bean.base.PageView;
import com.cnu.wlx.bean.base.QueryResult;
import com.cnu.wlx.formbean.BaseForm;
import com.cnu.wlx.formbean.ColumnTypeForm;
import com.cnu.wlx.formbean.ColumnTypeNoTypeDesForm;
import com.cnu.wlx.myenum.ColumnTypeDesEnum;
import com.cnu.wlx.service.ColumnTypeService;
import com.cnu.wlx.service.impl.AdminServiceImpl;
import com.cnu.wlx.utils.SiteUtils;

import net.sf.json.JSONObject;

/**
* @author 周亮 
* @version 创建时间：2016年4月13日 下午3:29:20
* 类说明
*/
@Controller
@RequestMapping(value="/control/column/*")
public class ColumnTypeManageAction {

	/**
	 * 日志输出
	 */
	private Logger log = LogManager.getLogger(ColumnTypeManageAction.class);
	/**
	 * 栏目服务类
	 */
	private ColumnTypeService columnTypeService;
	
	
	@RequestMapping(value="ajaxEdit")
	public String  ajaxEdit(ColumnTypeForm formbean,HttpServletRequest request){
		boolean flage = false;
		MyStatus status = new MyStatus();
		if(formbean.validateAdd()){
			//获取修改的栏目
			ColumnType column=columnTypeService.find(formbean.getColumn().getId());
			if( column!=null){
				try {
					String classCode = column.getClassCode();
						//非系统栏目才可以修改
						if(!classCode.equalsIgnoreCase("system")){
							column.setClassCode(formbean.getColumn().getClassCode());
							column.setName(formbean.getColumn().getName());
							column.setGroupType(formbean.getColumn().getGroupType());
							column.setManageUrl(formbean.getColumn().getManageUrl());
							column.setReadUrl(formbean.getColumn().getReadUrl());
							column.setSequence(formbean.getColumn().getSequence());
							//2.设置分类说明
							column.setTypeDes(ColumnTypeDesEnum.values()[formbean.getTypeDes()]);
							/*//设置父类
							ColumnType parent =columnTypeService.find(formbean.getParentId());
							if( parent !=null)
							  column.setParent(parent);
							*/
							columnTypeService.updateColumnType(column);
							//成功
							flage = true;
						}else{
							formbean.getResult().put("error", "不可修改");
						}
						
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		//失败
		if(!flage){
			status.setStatus(0);
			status.setMessage(formbean.getResult().get("error"));
		}
		JSONObject json = JSONObject.fromObject(status);
		request.setAttribute("json", json.toString());
		return  SiteUtils.getPage("json");
	}
	@RequestMapping(value="delete")
	public String deleteColumn(ColumnTypeNoTypeDesForm formbean){
		
		if( formbean.getColumn()!=null){
			ColumnType column= columnTypeService.find(formbean.getColumn().getId());
			if( column!=null)
				columnTypeService.delete(column.getId());
		}
		return "redirect:/control/column/list.action?parentId="+formbean.getParentId()+"&page="+formbean.getPage();
	}
	/**
	 * 添加栏目
	 * @param formbean
	 * @return
	 */
	@RequestMapping(value="add")
	public String addColumn(ColumnTypeForm formbean){
		
		//1.校验
		if(formbean.validateAdd()){
			ColumnType column = new ColumnType();
			//2.设置分类说明
			column.setTypeDes(ColumnTypeDesEnum.values()[formbean.getTypeDes()]);
			try {
				formbean.copy(column, formbean.getColumn()); 
				ColumnType parent =columnTypeService.find(formbean.getParentId());
				//设置父类
				if( parent !=null)
				  column.setParent(parent);
				
				columnTypeService.addColumnType(column);
			} catch (Exception e) { 
				e.printStackTrace(); 
				formbean.getResult().put("error","添加失败"); 
			} 
		} 
		
		return "redirect:/control/column/list.action?parentId="+formbean.getParentId();
	}
	@RequestMapping(value="list")
	public String list(ColumnTypeNoTypeDesForm formbean,Model model,HttpServletRequest request){
		log.info("查询栏目");
		//页面类
		PageView<ColumnType> pageView = new PageView<ColumnType>(formbean.getMaxresult(), formbean.getPage());
		
		QueryResult<ColumnType> queryResult= columnTypeService.getScrollData(pageView.getFirstResult(),pageView.getMaxresult(), formbean.getParentId());
		pageView.setQueryResult(queryResult);
		//传输到页面
		model.addAttribute("pageView", pageView);
		
		//构造导航栏
		generatorNavigation(request, formbean);
		model.addAttribute("page",formbean.getPage());
		return SiteUtils.getPage("control.column.list");
	}
	public ColumnTypeService getColumnTypeService() {
		return columnTypeService;
	}
	@Resource
	public void setColumnTypeService(ColumnTypeService columnTypeService) {
		this.columnTypeService = columnTypeService;
	}
	
	/**
	 * 动态改变session中的导航条
	 * @param request  
	 * @param formbean 
	 */
	private void generatorNavigation(HttpServletRequest request,ColumnTypeNoTypeDesForm formbean){
		//从session中获取栏目导航信息
		HttpSession session =request.getSession();
		LinkedHashMap<String,String> columnNavigation= (LinkedHashMap<String, String>)session.getAttribute("columnNavigation");
		//如果不存在，第一次访问栏目,则新建
		if(columnNavigation==null)
			columnNavigation=new LinkedHashMap<String, String>();
		//父类id
		String id= formbean.getParentId();
		//父类name
		String name = formbean.getParentName();
		//父类的父类id
		String doubleParentId= formbean.getDoubleParentId();
		//父类的父类name
		String doubleParentName = formbean.getDoubleParentName();
		
		
		//构造导航栏：顶层栏目》新闻栏目》学工新闻》院系新闻
		if( BaseForm.validateStr(id)){
			//id不为null
			//如果id不存在navigation则新添加
			if( !columnNavigation.containsKey(id)){
				//如果父类的父类id不为空则先添加父类的父类
				if(BaseForm.validateStr(doubleParentId))
					columnNavigation.put(doubleParentId, doubleParentName);
				columnNavigation.put(id, name);
			}else{
			  //如果id存在则从后往前删除子栏目，直到当前访问的栏目。
				boolean flage = false;
				for(String key: columnNavigation.keySet()){
					if( flage )
						columnNavigation.remove(key);
					if( key.equals(id))
						flage = true;
				}
			}
		}else{
			//访问顶层父类，则取消navigation中的其它栏目
			columnNavigation.clear();
			columnNavigation.put(" ","顶层栏目");
			
		}
		//保存到session中
		session.setAttribute("columnNavigation", columnNavigation);
	}
	
}
