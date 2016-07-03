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
	
	
	/**
	 * 添加问题
	 * @param formbean
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "add",method=RequestMethod.POST)
	public String add(QuestionForm formbean,String classCode,Model model){
		
		if( formbean.getQuestion()!=null){
			questionService.save(formbean.getQuestion());
			System.out.println("ok ");
		}
		
		return "redirect:/front/question/list.uhtml?page=1&classCode="+classCode;

	}
	/**
	 * 问题列表
	 * @param formbean:page
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "list",method=RequestMethod.GET)
	public String list(QuestionForm formbean,String classCode,Model model){
		
		//根据时间分页查询问题
		PageView<Question> pageView = new PageView<Question>(formbean.getMaxresult(), formbean.getPage());
		//排序：时间
		LinkedHashMap<String, String> orderby = new LinkedHashMap<>();
		orderby.put("createTime", "desc");
		//查询条件：是否可见，是否回答,是否是热点
		StringBuffer wherejpql=new StringBuffer("");
		List<Object> params = new ArrayList<Object>();
		wherejpql.append(" o.visible = ?");
		params.add(StateEnum.YES);
		/*wherejpql.append("  and o.handle = ?");
		params.add(StateEnum.YES);*/
		
		QueryResult<Question> queryResult= questionService.getScrollData(pageView.getFirstResult(), pageView.getMaxresult(), wherejpql.toString(), params.toArray(), orderby);
		pageView.setQueryResult(queryResult);
		
		//传输到页面
		model.addAttribute("pageView", pageView);

		model.addAttribute("formbean",formbean);
		
		//查询10个热点问题
		wherejpql.append("  and o.hot = ?");
		params.add(StateEnum.YES);
		PageView<Question> pv = new PageView<Question>(10, 1);
		
		QueryResult<Question> queryHotResult=questionService.getScrollData(pv.getFirstResult(), pv.getMaxresult(), wherejpql.toString(), params.toArray(), orderby);

		List<Question> hotQuestions=queryHotResult.getResultlist();
		//传输到页面
		model.addAttribute("hotQuestions", hotQuestions);

		model.addAttribute("classCode",classCode);
		return SiteUtils.getPage("front.question.list");
	}
	/**
	 * 查询一条热点话题
	 * @param formbean:questionId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "hot",method=RequestMethod.GET)
	public String hot(QuestionForm formbean,String classCode,Model model){
		
		//根据时间分页查询问题
		PageView<Question> pageView = new PageView<Question>(10, 1);
		//排序：时间
		LinkedHashMap<String, String> orderby = new LinkedHashMap<>();
		orderby.put("createTime", "desc");
		//查询条件：是否可见，是否是热点
		StringBuffer wherejpql=new StringBuffer("");
		List<Object> params = new ArrayList<Object>();
		wherejpql.append(" o.visible = ?");
		params.add(StateEnum.YES);
		/*wherejpql.append("  and o.handle = ?");
		params.add(StateEnum.YES);*/

		wherejpql.append("  and o.hot = ?");
		params.add(StateEnum.YES);
		
		//查询10个热点问题
		PageView<Question> pv = new PageView<Question>(10, 1);
		
		QueryResult<Question> queryHotResult=questionService.getScrollData(pv.getFirstResult(), pv.getMaxresult(), wherejpql.toString(), params.toArray(), orderby);

		List<Question> hotQuestions=queryHotResult.getResultlist();
		//传输到页面
		model.addAttribute("hotQuestions", hotQuestions);
		
		//具体热点话题的id
		wherejpql.append("  and o.id = ?");
		params.add(formbean.getQuestionId());
		
		QueryResult<Question> queryResult= questionService.getScrollData(pageView.getFirstResult(), pageView.getMaxresult(), wherejpql.toString(), params.toArray(), orderby);
		pageView.setQueryResult(queryResult);
		
		//传输到页面
		model.addAttribute("pageView", pageView);

		model.addAttribute("formbean",formbean);
		model.addAttribute("classCode",classCode);
		
		return SiteUtils.getPage("front.question.list");
	}
	public QuestionService getQuestionService() {
		return questionService;
	}
	@Resource
	public void setQuestionService(QuestionService questionService) {
		this.questionService = questionService;
	}
	
}
