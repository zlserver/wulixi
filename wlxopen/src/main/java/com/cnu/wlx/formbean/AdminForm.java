package com.cnu.wlx.formbean;

import javax.naming.spi.DirStateFactory.Result;
import javax.servlet.http.HttpServletRequest;

import com.cnu.wlx.bean.Admin;
/**
 * 
 * @author liang
 * @version 创建时间 2016年4月7日
 * 说明:
 * admin类的表单类
 */
public class AdminForm  extends BaseForm{
	private Admin admin;
	/**
	 * 校验码
	 */
	private String checkCode;
	
	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	/**
	 * 校验账号和密码是否合理
	 * @return
	 */
	public boolean validateAccountAndPass(){
		if( admin==null || !validateStr(admin.getAccount()) || !validateStr(admin.getPassword()) ){
			this.getResult().put("error", "账号密码有误!");
			return false;
		}
		//标识
		boolean flage = true;
		if(!validateLen(admin.getAccount(), 3, 20))
		{
			this.getResult().put("account", "账号长度在3-20之间!");
			flage = false;
		}
		if(!validateLen(admin.getPassword(), 3, 20))
		{
			this.getResult().put("password", "密码长度在3-20之间!");
			flage = false;
		}
		return flage;
	}
	/**
	 * 校验码是否匹配
	 * @param request
	 * @return
	 */
	public boolean validateCheckCode(HttpServletRequest request){
		
		//1.取出session中校验码
		String chCode =(String) request.getSession().getAttribute("checkCode");
		//有效字符串
		if( validateStr(chCode)&& validateStr(checkCode)){
			//2.比较客户端发来的校验码
			if( chCode.equalsIgnoreCase(checkCode.trim())){
				return true;
			}
		}
		getResult().put("checkCode", "校验码不正确");
		return false;
	}

	public String getCheckCode() {
		return checkCode;
	}

	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}
	
}
