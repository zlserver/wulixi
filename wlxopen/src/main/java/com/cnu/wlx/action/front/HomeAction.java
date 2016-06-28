package com.cnu.wlx.action.front;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cnu.wlx.bean.ColumnType;
import com.cnu.wlx.bean.DownloadFile;
import com.cnu.wlx.bean.News;
import com.cnu.wlx.bean.NewsFile;
import com.cnu.wlx.bean.Question;
import com.cnu.wlx.bean.base.PageView;
import com.cnu.wlx.bean.base.QueryResult;
import com.cnu.wlx.formbean.BaseForm;
import com.cnu.wlx.formbean.HomeForm;
import com.cnu.wlx.myenum.FileStateEnum;
import com.cnu.wlx.myenum.FileTypeEnum;
import com.cnu.wlx.myenum.NewsStateEnum;
import com.cnu.wlx.service.ColumnTypeService;
import com.cnu.wlx.service.DownloadFileService;
import com.cnu.wlx.service.NewsFileService;
import com.cnu.wlx.service.NewsService;
import com.cnu.wlx.service.QuestionService;
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
	private NewsFileService newsFileService;
	private ColumnTypeService columnTypeService;
	private DownloadFileService downloadFileService;
	private QuestionService questionService;
	/**
	 * 获取首页内容:学工新闻、下载专区、通知公告、就业实习信息、回音壁、校园文化【活动剪影、校园风光】、学习标兵、荣誉表彰。
	 * @return
	 */
	@RequestMapping(value="home")
	public String home(HomeForm formbean,Model model){
		formbean=new HomeForm("xue", "xia", "tong", "job", "huo", "feng", "biao","biaozhang");
		//学工新闻
		ColumnType xueCt = columnTypeService.findByClassCode(formbean.getXueClassCode());
		if( xueCt!=null){
			PageView<News> xuePV = new PageView<News>(10, 0);
			QueryResult<News> xueNews=newsService.getHomeScrollData(xuePV.getFirstResult(), xuePV.getMaxresult(), xueCt.getId());
			List<News> xuesNews =xueNews.getResultlist();
			model.addAttribute("xueNews",xuesNews);
			//获取第一个新闻的预览图
			if( xuesNews!=null &&xuesNews.size()>0)
			{
				News xueNew = xuesNews.get(0);
				//查询条件：所属新闻，文件类型
				NewsFile xuePic=newsFileService.getHomeData(xueNew.getId(), FileTypeEnum.IMAGE);
				model.addAttribute("xuePic", xuePic);
			}
		}
		//下载专区
		PageView<DownloadFile> xiaPv= new PageView<>(10, 0);
		QueryResult<DownloadFile> xiaFiles= downloadFileService.getHomeScrollData(xiaPv.getFirstResult(), xiaPv.getMaxresult(), null);
		model.addAttribute("xiaFiles", xiaFiles.getResultlist());
		
		//通知公告
		ColumnType tongCt = columnTypeService.findByClassCode(formbean.getTongClassCode());
		if(tongCt!=null)
		{
			PageView<News> tongPV = new PageView<News>(6, 0);
			QueryResult<News> tongNews=newsService.getHomeScrollData(tongPV.getFirstResult(), tongPV.getMaxresult(),tongCt.getId());
			model.addAttribute("tongNews", tongNews.getResultlist());
		}
		//就业实习
		ColumnType jobCt = columnTypeService.findByClassCode(formbean.getJobClassCode());
		if( jobCt !=null){
			PageView<News> jobPV = new PageView<News>(6, 0);
			QueryResult<News> jobNews=newsService.getHomeScrollData(jobPV.getFirstResult(), jobPV.getMaxresult(),jobCt.getId());
			model.addAttribute("jobNews", jobNews.getResultlist());
		}
		//校园文化--》活动剪影
		ColumnType huoCt = columnTypeService.findByClassCode(formbean.getHuoClassCode());
		if( huoCt!=null){
			PageView<DownloadFile> huoPv= new PageView<>(2, 0);
			QueryResult<DownloadFile> huoFiles= downloadFileService.getHomeScrollData(huoPv.getFirstResult(), huoPv.getMaxresult(),huoCt.getId());
			model.addAttribute("huoFiles", huoFiles.getResultlist());
		}
		//校园文化--》校园风光
		ColumnType fengCt = columnTypeService.findByClassCode(formbean.getFengClassCode());
		if( fengCt!=null){
			PageView<DownloadFile> fengPv= new PageView<>(2, 0);
			QueryResult<DownloadFile> fengFiles= downloadFileService.getHomeScrollData(fengPv.getFirstResult(), fengPv.getMaxresult(),fengCt.getId());
			model.addAttribute("fengFiles", fengFiles.getResultlist());
		}
		//学习标兵
		ColumnType biaoCt = columnTypeService.findByClassCode(formbean.getBiaoClassCode());
		if( biaoCt!=null){
			PageView<News> biaoPV = new PageView<News>(1, 0);
			QueryResult<News> biaoNews=newsService.getHomeScrollData(biaoPV.getFirstResult(), biaoPV.getMaxresult(),biaoCt.getId());
			
			if(biaoNews!=null &&biaoNews.getTotalrecord()>0){
				News newb = biaoNews.getResultlist().get(0);
				model.addAttribute("biaoNew", newb);
				//查询条件：所属新闻，文件类型
				NewsFile biaoPic=newsFileService.getHomeData(newb.getId(), FileTypeEnum.IMAGE);
				model.addAttribute("biaoPic", biaoPic);
			}
		}
		//荣誉表彰
		ColumnType biaozhangCt = columnTypeService.findByClassCode(formbean.getBiaozhangClassCode());
		if( biaozhangCt!=null){
			PageView<News> biaozhangPV = new PageView<News>(1, 0);
			QueryResult<News> biaozhangNews=newsService.getHomeScrollData(biaozhangPV.getFirstResult(), biaozhangPV.getMaxresult(),biaozhangCt.getId());
			
			if(biaozhangNews!=null &&biaozhangNews.getTotalrecord()>0){
				News biaozhangNew = biaozhangNews.getResultlist().get(0);
				model.addAttribute("biaozhangNew", biaozhangNew);
				//查询条件：所属新闻，文件类型
				NewsFile biaoPic=newsFileService.getHomeData(biaozhangNew.getId(), FileTypeEnum.IMAGE);
				model.addAttribute("biaozhangPic", biaoPic);
			}
		}
		//回音壁
		PageView<Question> huiyinPV = new PageView<Question>(5, 0);
		List<Question> questions=questionService.getHomeData(huiyinPV.getFirstResult(), huiyinPV.getMaxresult());
		if( questions!=null)
			model.addAttribute("questions", questions);
		return SiteUtils.getPage("front.body");
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
	public NewsFileService getNewsFileService() {
		return newsFileService;
	}
	@Resource
	public void setNewsFileService(NewsFileService newsFileService) {
		this.newsFileService = newsFileService;
	}
	public QuestionService getQuestionService() {
		return questionService;
	}
	@Resource
	public void setQuestionService(QuestionService questionService) {
		this.questionService = questionService;
	}
	
}
