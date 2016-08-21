package com.cnu.wlx.action.control;

import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.cnu.wlx.bean.Admin;
import com.cnu.wlx.bean.HotInform;
import com.cnu.wlx.bean.News;
import com.cnu.wlx.bean.NewsFile;
import com.cnu.wlx.bean.Question;
import com.cnu.wlx.bean.base.PageView;
import com.cnu.wlx.bean.base.QueryResult;
import com.cnu.wlx.formbean.BaseForm;
import com.cnu.wlx.formbean.HotInformForm;
import com.cnu.wlx.formbean.NewsForm;
import com.cnu.wlx.myenum.StateEnum;
import com.cnu.wlx.service.AdminService;
import com.cnu.wlx.service.HotInformService;
import com.cnu.wlx.utils.SiteUtils;
import com.cnu.wlx.utils.WebUtils;

@Controller
@RequestMapping(value="/control/hotinform/*")
public class HotInformAction {

	private HotInformService hotInformService;
	private AdminService adminService;
	

	@RequestMapping(value="addUi")
	public String addUi(){
		
		return SiteUtils.getPage("control.hotinform.addUi");
	}

	@RequestMapping(value="editUi")
	public String editUI(String id,Model model){
		HotInform inform= hotInformService.find(id);
		model.addAttribute("inform", inform);
		return SiteUtils.getPage("control.hotinform.editUi");
	}
	@RequestMapping(value="update")
	public String update(HotInformForm formbean){
		
		if( formbean.getCheckeds()!=null){
			for(int j=0;j < formbean.getCheckeds().size();j++){
				int i = formbean.getCheckeds().get(j);
				String id= formbean.getIds().get(i);
				String link = formbean.getLinks().get(i);
				String title = formbean.getTitles().get(i);
				String state = formbean.getStates().get(i);
				HotInform inform =hotInformService.find(id);
				inform.setTitle(title);
				inform.setState(StateEnum.valueOf(state));
				inform.setLink(link);
				hotInformService.update(inform);
			}
		}
		return "redirect:/control/hotinform/list.action?editState="+formbean.getEditState()+"&page="+formbean.getPage();
		
	}
	
	@RequestMapping(value="delete")
	public String delete(HotInformForm formbean){
	
		if( formbean.getCheckeds()!=null){
			String[] ids = new String[formbean.getCheckeds().size()];
			for(int j=0;j < formbean.getCheckeds().size();j++){
				int i = formbean.getCheckeds().get(j);
				ids[j]=formbean.getIds().get(i);
			}
			hotInformService.delete(ids);
		}
		return "redirect:/control/hotinform/list.action?&editState="+formbean.getEditState()+"&page="+formbean.getPage();
	}
	
	/**
	 * 保存图片并设置inform的pic字段的值
	 * @param multipartFile
	 * @param inform
	 * @return 1：保存成，2：上传的文件不是图片，3：没上传文件
	 */
	private int saveImage(MultipartFile multipartFile,HotInform inform){
		
		if( multipartFile!=null && multipartFile.getSize()>0){
			String type=multipartFile.getContentType();
			
			String originName=multipartFile.getOriginalFilename();
			//验证图片
			if(	BaseForm.validateImageFileType(originName, type)){
				//保存文件相对路径:files/
				String relativedir= SiteUtils.getRelativeSavePath("hotinform.pic");
				//保存文件名称
		        String saveFileName = WebUtils.getFileSaveName(originName);
				
		        //保存文件
		        try {
					BaseForm.saveFile(relativedir, saveFileName, multipartFile);
				} catch (Exception e) {
					e.printStackTrace();
				}
		        inform.setPicPath(relativedir+saveFileName);
			}else
				return 2;
		}else
			return 3;
		
		return 1;
	}
	
	@RequestMapping(value="save")
	public String save(HotInformForm formbean,HttpServletRequest request,Model model){
		HotInform inform = new HotInform();
		WebUtils.copyBean(inform, formbean);
		MultipartFile multipartFile = formbean.getPic();
		int res=saveImage(multipartFile,inform);
		String message =null;
		if(res==2){
			message= "请上传图片";
			formbean.getResult().put("error", message);
			model.addAttribute("formbean", formbean);
			return SiteUtils.getPage("control.hotinform.addUi");
		}else{
			Admin admin=(Admin) request.getSession().getAttribute("admin");
			inform.setAccount(admin.getAccount());
			hotInformService.save(inform);
		}
		return "redirect:/control/hotinform/list.action?&editState="+formbean.getEditState();
	}
   
	@RequestMapping(value="list")
	public String list(HotInformForm formbean,Model model,HttpServletRequest request){
		//生成导航信息
		BaseForm.navigationColumn(formbean, request);
		//页面类
		PageView<HotInform> pageView = new PageView<HotInform>(formbean.getMaxresult(), formbean.getPage());
		//结果集根据栏目的顺序升序,时间降序来排列
		LinkedHashMap<String,String> orderby=new LinkedHashMap<String,String>();
		orderby.put("state", "asc");
		orderby.put("createTime", "desc");
		
		QueryResult<HotInform> queryResult= hotInformService.getScrollData(pageView.getFirstResult(),pageView.getMaxresult(), null,null,orderby);
		pageView.setQueryResult(queryResult);
		//传输到页面
		model.addAttribute("pageView", pageView);
	
		model.addAttribute("formbean",formbean);

		return SiteUtils.getPage("control.hotinform.list");
		
	}
	public HotInformService getHotInformService() {
		return hotInformService;
	}
	@Autowired
	public void setHotInformService(HotInformService hotInformService) {
		this.hotInformService = hotInformService;
	}

	public AdminService getAdminService() {
		return adminService;
	}
	@Autowired
	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}
	
}
