package com.cnu.wlx.action.front;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cnu.wlx.bean.Question;
import com.cnu.wlx.bean.base.PageView;
import com.cnu.wlx.bean.base.QueryResult;
import com.cnu.wlx.formbean.BaseForm;
import com.cnu.wlx.formbean.QuestionForm;
import com.cnu.wlx.myenum.StateEnum;
import com.cnu.wlx.service.QuestionService;
import com.cnu.wlx.utils.SiteUtils;

/**
* @author 周亮 
* @version 创建时间：2016年5月26日 下午4:15:24
* 类说明
*/
@Controller
@RequestMapping(value="/front/question/")
public class QuestionAction {

	private QuestionService questionService;
	@RequestMapping(value = "add",method=RequestMethod.POST)
	public String add(QuestionForm formbean,Model model){
		
		if( formbean.getQuestion()!=null){
			questionService.save(formbean.getQuestion());
			System.out.println("ok ");
		}
		
		return SiteUtils.getPage("json");
	}
	
	public String list(QuestionForm formbean,Model model){
		PageView<Question> pageView = new PageView<Question>(formbean.getMaxresult(), formbean.getPage());
		//排序：时间
		LinkedHashMap<String, String> orderby = new LinkedHashMap<>();
		orderby.put("createTime", "desc");
		//查询条件：是否可见，是否回答,是否是热点
		StringBuffer wherejpql=new StringBuffer("");
		List<Object> params = new ArrayList<Object>();
		wherejpql.append(" o.visible = ?");
		params.add(StateEnum.YES);
		wherejpql.append("  and o.handle = ?");
		params.add(StateEnum.YES);
		
		QueryResult<Question> queryResult= questionService.getScrollData(pageView.getFirstResult(), pageView.getMaxresult(), wherejpql.toString(), params.toArray(), orderby);
		pageView.setQueryResult(queryResult);
		
		//传输到页面
		model.addAttribute("pageView", pageView);

		model.addAttribute("formbean",formbean);
		return SiteUtils.getPage("json");
	}
	public QuestionService getQuestionService() {
		return questionService;
	}
	@Resource
	public void setQuestionService(QuestionService questionService) {
		this.questionService = questionService;
	}
	
}
