package com.cnu.wlx.action.front;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cnu.wlx.bean.ColumnType;
import com.cnu.wlx.bean.DownloadFile;
import com.cnu.wlx.bean.News;
import com.cnu.wlx.bean.base.PageView;
import com.cnu.wlx.bean.base.QueryResult;
import com.cnu.wlx.formbean.HomeForm;
import com.cnu.wlx.myenum.NewsStateEnum;
import com.cnu.wlx.service.ColumnTypeService;
import com.cnu.wlx.service.DownloadFileService;
import com.cnu.wlx.service.NewsService;
import com.cnu.wlx.utils.SiteUtils;

/**
* @author 周亮 
* @version 创建时间：2016年5月26日 上午8:32:57
* 类说明：首页控制类
*/
@Controller
@RequestMapping(value="/front/")
public class HomeAction {
	
	private NewsService newsService;
	private ColumnTypeService columnTypeService;
	private DownloadFileService downloadFileService;
	/**
	 * 获取首页内容:学工新闻、下载专区、通知公告、就业实习信息、回音壁、校园文化【活动剪影、校园风光】、学习标兵。
	 * @return
	 */
	@RequestMapping(value="home")
	public String home(HomeForm formbean,Model model){
		formbean=new HomeForm("xue", "xia", "tong", "job", "huo", "feng", "biao");
		//学工新闻
		ColumnType xueCt = columnTypeService.find(formbean.getXueClassCode());
		PageView<News> xuePV = new PageView<News>(10, 0);
		QueryResult<News> xueNews=newsService.getHomeScrollData(xuePV.getFirstResult(), xuePV.getMaxresult(), xueCt.getId());
		//下载专区
		ColumnType xiaCt = columnTypeService.find(formbean.getXiaClassCode());
		PageView<DownloadFile> xiaPv= new PageView<>(10, 0);
		QueryResult<DownloadFile> xiaFile= downloadFileService.getHomeScrollData(xiaPv.getFirstResult(), xiaPv.getMaxresult(), xiaCt.getId());
		//通知公告
		ColumnType tongCt = columnTypeService.find(formbean.getTongClassCode());
		PageView<News> tongPV = new PageView<News>(10, 0);
		QueryResult<News> tongNews=newsService.getHomeScrollData(tongPV.getFirstResult(), tongPV.getMaxresult(),tongCt.getId());
		//就业实习
		ColumnType jobCt = columnTypeService.find(formbean.getJobClassCode());
		PageView<News> jobPV = new PageView<News>(10, 0);
		QueryResult<News> jobNews=newsService.getHomeScrollData(jobPV.getFirstResult(), jobPV.getMaxresult(),jobCt.getId());
		//校园文化--》活动剪影
		ColumnType huoCt = columnTypeService.find(formbean.getHuoClassCode());
		PageView<DownloadFile> huoPv= new PageView<>(10, 0);
		QueryResult<DownloadFile> huoFile= downloadFileService.getHomeScrollData(huoPv.getFirstResult(), huoPv.getMaxresult(),huoCt.getId());
		//校园文化--》校园风光
		ColumnType fengCt = columnTypeService.find(formbean.getFengClassCode());
		PageView<DownloadFile> fengPv= new PageView<>(10, 0);
		QueryResult<DownloadFile> fengFile= downloadFileService.getHomeScrollData(fengPv.getFirstResult(), fengPv.getMaxresult(),fengCt.getId());
		//学习标兵
		ColumnType biaoCt = columnTypeService.find(formbean.getBiaoClassCode());
		PageView<News> biaoPV = new PageView<News>(10, 0);
		QueryResult<News> biaoNews=newsService.getHomeScrollData(biaoPV.getFirstResult(), biaoPV.getMaxresult(),biaoCt.getId());

		model.addAttribute("xueNews", xueNews.getResultlist());
		model.addAttribute("xiaFile", xiaFile.getResultlist());
		model.addAttribute("tongNews", tongNews.getResultlist());
		model.addAttribute("jobNews", jobNews.getResultlist());
		model.addAttribute("huoFile", huoFile.getResultlist());
		model.addAttribute("fengFile", fengFile.getResultlist());
		model.addAttribute("biaoNews", biaoNews.getResultlist());
		
		return SiteUtils.getPage("front.home");
	}
	public NewsService getNewsService() {
		return newsService;
	}
	@Resource
	public void setNewsService(NewsService newsService) {
		this.newsService = newsService;
	}
	public DownloadFileService getDownloadFileService() {
		return downloadFileService;
	}
	@Resource
	public void setDownloadFileService(DownloadFileService downloadFileService) {
		this.downloadFileService = downloadFileService;
	}
	public ColumnTypeService getColumnTypeService() {
		return columnTypeService;
	}
	@Resource
	public void setColumnTypeService(ColumnTypeService columnTypeService) {
		this.columnTypeService = columnTypeService;
	}
	
}
