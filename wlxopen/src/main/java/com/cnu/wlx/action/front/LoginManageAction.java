package com.cnu.wlx.action.front;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cnu.wlx.action.control.AdminManageAction;
import com.cnu.wlx.bean.Admin;
import com.cnu.wlx.bean.ColumnType;
import com.cnu.wlx.formbean.AdminForm;
import com.cnu.wlx.formbean.HomeForm;
import com.cnu.wlx.service.AdminService;
import com.cnu.wlx.service.ColumnTypeService;
import com.cnu.wlx.utils.SiteUtils;

/**
* @author 周亮 
* @version 创建时间：2016年4月13日 上午10:17:51
* 类说明:负责处理管理员登录
*/
@Controller
@RequestMapping(value="/common/*")
public class LoginManageAction {

	private Logger log = LogManager.getLogger(AdminManageAction.class);
	private AdminService adminService;
	private  ColumnTypeService columnTypeService;

	/**
	 * 初始化，加入两个管理员,和栏目列表表
	 */
	@RequestMapping(value="systeminit")
	public String init(){
		Admin admin= new Admin();
		admin.setAccount("admin");
		admin.setPassword("Admin68902660");
		admin.setRole(1);
		if(adminService.find(admin.getAccount())==null){
			adminService.add(admin);
		}
		
		Admin comm= new Admin();
		comm.setAccount("student");
		comm.setPassword("wlx6890");
		comm.setRole(2);
		if(adminService.find(comm.getAccount())==null){
			adminService.add(comm);
		}
		
		//栏目
		//formbean=new HomeForm("xue", "down", "tong", "job","xy_huo", "xy_feng", "biao","biaozhang");
		
		if( columnTypeService.findByClassCode("1system")==null){
			
			//系统管理及子栏目
			ColumnType systemColumn =  new ColumnType("系统管理", "1system");
			ColumnType sysc  =new ColumnType("栏目管理", "sys_lmgl");
			List<ColumnType> sysChilds = new ArrayList<>();
			sysChilds.add(sysc);
			saveColumn(systemColumn, sysChilds,1);
			
			//新闻管理及子栏目
			ColumnType newsColumn =  new ColumnType("新闻管理", "1newsgl");
			List<ColumnType> newsChilds = new ArrayList<>();
			
			ColumnType xue =  new ColumnType("学工新闻", "xue");
			ColumnType tong =  new ColumnType("通知公告", "tong");
			ColumnType job =  new ColumnType("就业实习", "job");
			ColumnType biao =  new ColumnType("学习标兵", "biao");
			ColumnType biaozhang =  new ColumnType("荣誉表彰", "biaozhang");
			
			ColumnType xszz =  new ColumnType("学生组织", "xszz");
			ColumnType gzzd =  new ColumnType("规章制度", "gzzd");
			ColumnType sxjy =  new ColumnType("思想教育", "sxjy");
			ColumnType zzgl =  new ColumnType("资助管理", "zzgl");
			ColumnType xlzx =  new ColumnType("心理咨询", "xlzx");
			ColumnType jygz =  new ColumnType("就业工作", "jygz");
			ColumnType gfjy =  new ColumnType("国防教育", "gfjy");
			ColumnType yjsy =  new ColumnType("研究生院", "yjsy");
			newsChilds.add(xue);
			newsChilds.add(tong);
			newsChilds.add(job);
			newsChilds.add(biao);
			newsChilds.add(biaozhang);
			newsChilds.add(xszz);
			newsChilds.add(gzzd);
			newsChilds.add(sxjy);
			newsChilds.add(zzgl);
			newsChilds.add(xlzx);
			newsChilds.add(jygz);
			newsChilds.add(gfjy);
			newsChilds.add(yjsy);
			
			saveColumn(newsColumn, newsChilds,2);
			//下载管理
			ColumnType downColumn = new ColumnType("下载管理","1downgl");
			ColumnType sidown  =new ColumnType("思政下载", "down_sz");
			List<ColumnType> downChilds = new ArrayList<>();
			downChilds.add(sidown);
			saveColumn(downColumn, downChilds,4);
			
			//思政管理及子栏目

			ColumnType szColumn = new ColumnType("思政管理","1sigl");
			columnTypeService.addColumnType(szColumn);
			
			ColumnType xyColumn = new ColumnType("校园文化","2xyculture");
			xyColumn.setParent(szColumn);
			
			List<ColumnType> xyChilds = new ArrayList<>();
			ColumnType xy_huo =  new ColumnType("活动掠影", "xy_huo");
			ColumnType xy_feng =  new ColumnType("校园风光", "xy_feng");
			xyChilds.add(xy_huo);
			xyChilds.add(xy_feng);
			
			saveColumn(xyColumn, xyChilds,3);
			
			//留言管理
			ColumnType liuColumn = new ColumnType("留言管理", "1liuyan");

			List<ColumnType> liuChilds = new ArrayList<>();
			//回音壁
			ColumnType huiColu= new ColumnType("回音壁", "1huiyinbi");
			liuChilds.add(huiColu);
			saveColumn(liuColumn, liuChilds,5);
			
		}
		
		return "redirect:/"+SiteUtils.getPage("control.admin.login")+".jsp";
	}
	
	
	public void saveColumn(ColumnType parent,List<ColumnType> childs){
		columnTypeService.addColumnType(parent);
		for( ColumnType child : childs){
			child.setParent(parent);
			columnTypeService.addColumnType(child);
		}
	}
	/**
	 * 
	 * @param parent
	 * @param childs
	 * @param type 1:栏目管理类，2:新闻类 3：图片类 4：文件下载类 5:回音壁
	 */
	public void saveColumn(ColumnType parent,List<ColumnType> childs,int type){
		String  readurl = "";
		String manageurl = "";
		switch (type) {
		case 1:
			readurl="control/column/list.action?";
			break;
		case 2:
			readurl="control/news/list.action?";
			manageurl = readurl;
			break;
		case 3:
			readurl="control/download/list.action?type=IMAGE&";
			manageurl = readurl;
			break;
		case 4:
			readurl="control/download/list.action?type=NO_IMAGE&";
			manageurl = readurl;
			break;
		case 5:
			readurl="control/question/list.action?";
			manageurl = readurl;
			break;
		default:
			break;
		}
		columnTypeService.addColumnType(parent);
		for( ColumnType child : childs){
			child.setParent(parent);
			child.setReadUrl(readurl);
			child.setManageUrl(manageurl);
			columnTypeService.addColumnType(child);
		}
	}
	
	/**
	 * 登录
	 * @param formbean
	 * @param request
	 * @return
	 */
	@RequestMapping(value="login")
	public String login(AdminForm formbean, HttpServletRequest request) {
		// 1.进行校验码验证、进行用户名密码校验
		if (formbean.validateCheckCode(request)&&formbean.validateAccountAndPass()) {
			// 2.根据用户名和密码登录
			String ac = formbean.getAdmin().getAccount();
			String pa = formbean.getAdmin().getPassword();
			Admin ad = adminService.login(ac, pa);
			if (ad != null) {
				// 4.登录成功,更新登录次数
				ad.updateLoginCount();
				ad.updateLoginTime();
				adminService.update(ad);
				//5.进入管理中心。
				request.getSession().setAttribute("admin", ad);
				//log.info(ac+"登录");
				return "redirect:/"+"control/center/controlCenter.action";
				//return SiteUtils.getSite("admin.controlcenter");
			}
			// 3.用户名或者密码有误
			formbean.getResult().put("error", "用户名或者密码有误!");
		}
		// 登录出错返回
		request.setAttribute("formbean", formbean);
		return SiteUtils.getPage("control.admin.login");
		
	}
	/**
	 * 用户退出
	 * @param request
	 * @return
	 */
	@RequestMapping(value="exit")
	public String exit(HttpServletRequest request){
		request.getSession().invalidate();
		return SiteUtils.getPage("control.admin.login");
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
