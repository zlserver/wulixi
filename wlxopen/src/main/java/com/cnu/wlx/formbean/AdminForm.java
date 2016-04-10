package com.cnu.wlx.formbean;

import javax.naming.spi.DirStateFactory.Result;

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
	public boolean validate(){
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
}
