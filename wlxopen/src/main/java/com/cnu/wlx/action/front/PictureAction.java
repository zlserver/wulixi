package com.cnu.wlx.action.front;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cnu.wlx.bean.ColumnType;
import com.cnu.wlx.bean.DownloadFile;
import com.cnu.wlx.bean.base.PageView;
import com.cnu.wlx.bean.base.QueryResult;
import com.cnu.wlx.formbean.DownloadFileForm;
import com.cnu.wlx.myenum.FileStateEnum;
import com.cnu.wlx.myenum.FileTypeEnum;
import com.cnu.wlx.service.ColumnTypeService;
import com.cnu.wlx.service.DownloadFileService;
import com.cnu.wlx.service.FileService;
import com.cnu.wlx.utils.SiteUtils;

/**
 * 处理图片新闻的action
 * @author liang
 * @version 创建时间 2016年7月2日
 * 说明:
 *
 */

@Controller
@RequestMapping(value="/front/picture/")
public class PictureAction {

	/**
	  * 文件服务
	  */
	private FileService fileService;
	private DownloadFileService downloadFileService;
	private ColumnTypeService columnTypeService;
	
	
	
	/**
	 * 从首页查看校园文化，点击其中一个栏目
	 * @param preClassCode  前缀,根据前缀查询栏目
	 * @param columnId  栏目id，用于获取属于该栏目的图片
	 * @param model 
	 * @return
	 */
	@RequestMapping(value="pictureList")
	public String pictureList(DownloadFileForm formbean,String preClassCode ,Model model){
		//根据前缀preClassCode查询出“活动掠影”、“校园风光”两个栏目
		String wherejpql = " o.classCode like ? ";
		LinkedHashMap<String,String> orderby=new LinkedHashMap<String,String>();
		orderby.put("sequence", "asc");
		
		List<Object> params = new ArrayList<Object>();
		String qpreClassCode=preClassCode.replace("_", "\\_");
		System.out.println("前缀："+qpreClassCode);
		params.add(qpreClassCode+"%");
		
		List<ColumnType>  columns = columnTypeService.getAllData(wherejpql, params.toArray(), orderby);
		model.addAttribute("listColumn",columns);
		model.addAttribute("preClassCode", preClassCode);
		
		//根据具体的栏目查询图片
		//查询某个类别下的下载列表
		PageView<DownloadFile> pageView = new PageView<DownloadFile>(formbean.getMaxresult(), formbean.getPage());
		//结果集根据时间降序来排列
		LinkedHashMap<String,String> orderby2=new LinkedHashMap<String,String>();
		orderby.put("createTime", "desc");
		
		StringBuffer jpql=new StringBuffer("");
		List<Object> pars = new ArrayList<Object>();
		
		jpql.append("o.column.id = ?");
		pars.add(formbean.getColumnId());
		//有效
		jpql.append(" and o.state= ?");
		pars.add(FileStateEnum.VALIDATE);
		//文件
		jpql.append("and o.type = ?");
		pars.add(FileTypeEnum.IMAGE);
		
		QueryResult<DownloadFile> queryResult=downloadFileService.getScrollData(pageView.getFirstResult(), pageView.getMaxresult(), jpql.toString(), pars.toArray(), orderby2);
		pageView.setQueryResult(queryResult);
		//传输到页面
		model.addAttribute("pageView", pageView);
		
		model.addAttribute("formbean",formbean);
		
		
		return SiteUtils.getPage("front.picture.more");
	}
	public FileService getFileService() {
		return fileService;
	}
	@Resource
	public void setFileService(FileService fileService) {
		this.fileService = fileService;
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
