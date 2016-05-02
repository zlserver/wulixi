package com.cnu.wlx.action.control;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cnu.wlx.bean.ColumnType;
import com.cnu.wlx.bean.News;
import com.cnu.wlx.bean.base.PageView;
import com.cnu.wlx.bean.base.QueryResult;
import com.cnu.wlx.formbean.NewsForm;
import com.cnu.wlx.service.NewsService;
import com.cnu.wlx.utils.SiteUtils;

/**
 * 
 * @author liang
 * @version 创建时间 2016年5月2日
 * 说明:新闻管理类
 *
 */
@Controller
@RequestMapping(value="/control/news/*")
public class NewsManageAction {

	private NewsService newsService;
	
	/**
	 * 查询新闻列表
	 * @param formbean
	 * @return
	 */
	@RequestMapping(value="list")
	public String list(NewsForm formbean,Model model){
		
		//校验栏目是否为null
		if( formbean.validateList()){
			//页面类
			PageView<News> pageView = new PageView<News>(formbean.getMaxresult(), formbean.getPage());
			QueryResult<News> queryResult= newsService.getScrollData(pageView.getFirstResult(),pageView.getMaxresult(), formbean.getClassCode());
			pageView.setQueryResult(queryResult);
			//传输到页面
			model.addAttribute("pageView", pageView);
			
			model.addAttribute("formbean",formbean);
		}
		
		return SiteUtils.getPage("control.news.list");
	}

	/**
	 * 转发到添加新闻界面
	 * @return
	 */
	@RequestMapping(value="addUi")
	public String addUi(NewsForm formbean,Model model){
		model.addAttribute("classCode", formbean.getClassCode());
		return SiteUtils.getPage("control.news.addUi");
	}

	@RequestMapping(value="detail")
	public String detail(){
		
		return  SiteUtils.getPage("control.news.detail");
	}
	
	@RequestMapping(value="add")
	public String add(NewsForm formbean){
		
		return  SiteUtils.getPage("control.news.manage");
	}
	public NewsService getNewsService() {
		return newsService;
	}
	@Resource
	public void setNewsService(NewsService newsService) {
		this.newsService = newsService;
	}
	
}
