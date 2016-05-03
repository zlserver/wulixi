package com.cnu.wlx.action.control;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cnu.wlx.bean.ColumnType;
import com.cnu.wlx.bean.News;
import com.cnu.wlx.bean.base.PageView;
import com.cnu.wlx.bean.base.QueryResult;
import com.cnu.wlx.formbean.BaseForm;
import com.cnu.wlx.formbean.NewsForm;
import com.cnu.wlx.myenum.ColorEnum;
import com.cnu.wlx.service.ColumnTypeService;
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
	private ColumnTypeService columnTypeService;
	
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
			QueryResult<News> queryResult= newsService.getScrollData(pageView.getFirstResult(),pageView.getMaxresult(), formbean.getColumnId());
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
		model.addAttribute("columnId", formbean.getColumnId());
		return SiteUtils.getPage("control.news.addUi");
	}

	@RequestMapping(value="detail")
	public String detail(String id,Model model){
		
		if( BaseForm.validateStr(id)){
			News news =newsService.find(id);
			model.addAttribute("news", news);
		}
		return  SiteUtils.getPage("control.news.detail");
	}
	
	@RequestMapping(value="add")
	public String add(NewsForm formbean){
		//校验

		News news = new News();
		try {
			//基本值设置
			formbean.copy(news, formbean.getNews());
			//设置时间
			news.setCreateTime(new Date());
			//设置栏目
			ColumnType column =columnTypeService.find(formbean.getColumnId());
			if( column!=null ){
				
				news.setColumn(column);
				//设置颜色
				ColorEnum color = ColorEnum.valueOf(formbean.getTitleColor());
				news.setTitleColor(color);
				newsService.save(news);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return  "redirect:/control/news/list.action?columnId="+formbean.getColumnId();
	}
	public NewsService getNewsService() {
		return newsService;
	}
	@Resource
	public void setNewsService(NewsService newsService) {
		this.newsService = newsService;
	}

	public ColumnTypeService getColumnTypeService() {
		return columnTypeService;
	}
	@Resource
	public void setColumnTypeService(ColumnTypeService columnTypeService) {
		this.columnTypeService = columnTypeService;
	}
	
}
