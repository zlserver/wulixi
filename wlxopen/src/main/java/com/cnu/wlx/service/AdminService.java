package com.cnu.wlx.service;

import com.cnu.wlx.bean.Admin;
import com.cnu.wlx.formbean.AdminForm;

public interface AdminService {
	
	/**
	 * 根据用户名密码登陆，登陆成功返回用户，登陆失败返回null
	 * @param account
	 * @param password
	 * @return
	 */
	public Admin login(String account,String password);
	
	/**
	 * 添加管理员,添加成功返回true,管理员已存在返回false
	 * @param formbean
	 * @return
	 */
	public boolean add(AdminForm formbean);
	/**
	 * 更加账号查找用户.
	 * @param account
	 * @return
	 */
	public Admin find(String account);
}
