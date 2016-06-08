package com.cnu.wlx.service;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cnu.wlx.bean.ColumnType;
import com.cnu.wlx.bean.News;
import com.cnu.wlx.bean.base.PageView;
import com.cnu.wlx.bean.base.QueryResult;
import com.cnu.wlx.formbean.HomeForm;

/**
* @author 周亮 
* @version 创建时间：2016年6月4日 下午2:11:40
* 类说明
*/
public class NewsServiceTest {
	private static ApplicationContext ac ;
	@Before
	public void init() {
		ac = new ClassPathXmlApplicationContext("wlx-service.xml");
		
	}
	@Test
	public void test(){
		ColumnTypeService columnTypeService=ac.getBean(ColumnTypeService.class);
		NewsService newsService=ac.getBean(NewsService.class);
		HomeForm formbean=new HomeForm("xue", "xia", "tong", "job", "huo", "feng", "biao","biaozhang");
		
		//学工新闻
		ColumnType xueCt = columnTypeService.findByClassCode(formbean.getXueClassCode());
		if( xueCt!=null){
			PageView<News> xuePV = new PageView<News>(10, 0);
			QueryResult<News> xueNews=newsService.getHomeScrollData(xuePV.getFirstResult(), xuePV.getMaxresult(), xueCt.getId());
			System.out.println(xueNews.getTotalrecord());
		}
	}
}
