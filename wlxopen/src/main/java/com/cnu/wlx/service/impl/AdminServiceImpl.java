package com.cnu.wlx.service.impl;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.cnu.wlx.bean.Admin;
import com.cnu.wlx.dao.AdminDao;
import com.cnu.wlx.formbean.AdminForm;
import com.cnu.wlx.formbean.BaseForm;
import com.cnu.wlx.service.AdminService;
import com.cnu.wlx.utils.WebUtils;

@Service("adminService")
public class AdminServiceImpl implements AdminService{
	/**
	 * 日志输出
	 */
	private Logger log = LogManager.getLogger(AdminServiceImpl.class);
	private AdminDao adminDao;
	
	
	public Admin login(String account, String password) {
		String md5ps = WebUtils.MD5Encode(password.trim());
		
		Admin  ad =adminDao.find(account);
		if( ad.equals(md5ps)){
			return ad;
		}else
			return null;
	}
	public boolean add(AdminForm formbean) {
		String md5ps = WebUtils.MD5Encode(formbean.getAdmin().getPassword().trim());
		Admin ad = new Admin(formbean.getAdmin().getAccount().trim(), md5ps);
		try {
			adminDao.save(ad);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return false;
		}
		return true;
	}
	public AdminDao getAdminDao() {
		return adminDao;
	}
	@Resource
	public void setAdminDao(AdminDao adminDao) {
		this.adminDao = adminDao;
	}
	public Admin find(String account) {
		if( BaseForm.validateStr(account) ){
			return adminDao.find(account);
		}
		return null;
	}
}
