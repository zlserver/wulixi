package com.cnu.wlx.action.control;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cnu.wlx.bean.Admin;
import com.cnu.wlx.bean.DownloadFile;
import com.cnu.wlx.bean.Question;
import com.cnu.wlx.bean.base.PageView;
import com.cnu.wlx.bean.base.QueryResult;
import com.cnu.wlx.formbean.BaseForm;
import com.cnu.wlx.formbean.QuestionForm;
import com.cnu.wlx.myenum.FileStateEnum;
import com.cnu.wlx.myenum.StateEnum;
import com.cnu.wlx.service.AdminService;
import com.cnu.wlx.service.ColumnTypeService;
import com.cnu.wlx.service.QuestionService;
import com.cnu.wlx.utils.SiteUtils;

/**
* @author 周亮 
* @version 创建时间：2016年5月26日 下午2:56:18
* 类说明
*/
@Controller
@RequestMapping(value="/control/question/")
public class QuestionManageAction {
	
	private QuestionService questionService;
	private AdminService adminService;
	
	private ColumnTypeService columnTypeService;
	
	@RequestMapping(value="list")
	public String list(QuestionForm formbean,Model model){
		
		PageView<Question> pageView = new PageView<Question>(formbean.getMaxresult(), formbean.getPage());
		//排序：时间
		LinkedHashMap<String, String> orderby = new LinkedHashMap<>();
		orderby.put("createTime", "desc");
		//查询条件：是否可见，是否回答,是否是热点
		StringBuffer wherejpql=new StringBuffer("");
		List<Object> params = new ArrayList<Object>();
		if( BaseForm.validateStr(formbean.getVisible())){
			wherejpql.append(" o.visible = ?");
			params.add(StateEnum.valueOf(formbean.getVisible()));
		}
		if( BaseForm.validateStr(formbean.getHandle())){
			if( params.size()>0)
				wherejpql.append(" and ");
			wherejpql.append(" o.handle = ?");
			params.add(StateEnum.valueOf(formbean.getHandle()));
		}

		if( BaseForm.validateStr(formbean.getHot())){
			if( params.size()>0)
				wherejpql.append(" and ");
			wherejpql.append(" o.hot = ?");
			params.add(StateEnum.valueOf(formbean.getHot()));
		}
		QueryResult<Question> queryResult= questionService.getScrollData(pageView.getFirstResult(), pageView.getMaxresult(), wherejpql.toString(), params.toArray(), orderby);
		pageView.setQueryResult(queryResult);
		
		//传输到页面
		model.addAttribute("pageView", pageView);

		model.addAttribute("formbean",formbean);
		return SiteUtils.getPage("control.question.list");
	}
	
	@RequestMapping(value="update")
	public String update(QuestionForm formbean,HttpServletRequest request){
		//获取批量id
		//获取批量state
		//获取批量sequence
		if( formbean.getCheckeds()!=null){
			for(int j=0;j < formbean.getCheckeds().size();j++){
				int i = formbean.getCheckeds().get(j);
				String id= formbean.getIds().get(i);
				String hot = formbean.getHots().get(i);
				String visible = formbean.getVisibles().get(i);
				String answer = "";
				if( formbean.getAnswers()!=null && formbean.getAnswers().size()>=i){
				    answer = formbean.getAnswers().get(i);
				}
				Question question = questionService.find(id);
				question.setHot(StateEnum.valueOf(hot));
				if( BaseForm.validateStr(answer)){
					question.setHandle(StateEnum.YES);
					question.setAnswer(answer);
					Admin admin=(Admin) request.getSession().getAttribute("admin");
					question.setAccount(admin.getAccount());
				}else{
					question.setAnswer(answer);
				}
				question.setVisible(StateEnum.valueOf(visible));
				questionService.update(question);
			}
		}
		
		return "redirect:/control/question/list.action?columnId="+formbean.getColumnId()+"&editState="+formbean.getEditState()+"&page="+formbean.getPage();
	}
	@RequestMapping(value="delete")
	public String delete(QuestionForm formbean,HttpServletRequest request){
		//获取批量id
		//获取批量state
		//获取批量sequence
		if( formbean.getCheckeds()!=null){
			String[] ids = new String[formbean.getCheckeds().size()];
			
			for(int j=0;j < formbean.getCheckeds().size();j++){
				int i = formbean.getCheckeds().get(j);
				ids[j]= formbean.getIds().get(i);
			}
			questionService.delete(ids);
		}
		
		return "redirect:/control/question/list.action?columnId="+formbean.getColumnId()+"&editState="+formbean.getEditState()+"&page="+formbean.getPage();
	}
	
	
	public QuestionService getQuestionService() {
		return questionService;
	}
	@Resource
	public void setQuestionService(QuestionService questionService) {
		this.questionService = questionService;
	}

	public AdminService getAdminService() {
		return adminService;
	}
	@Resource
	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}

	public ColumnTypeService getColumnTypeService() {
		return columnTypeService;
	}
	@Resource
	public void setColumnTypeService(ColumnTypeService columnTypeService) {
		this.columnTypeService = columnTypeService;
	}
	
	
	
}
