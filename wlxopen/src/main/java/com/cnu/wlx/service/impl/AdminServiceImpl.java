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
		if( BaseForm.validateStr(account)&& BaseForm.validateStr(password)){
			String md5ps = WebUtils.MD5Encode(password.trim());
			
			Admin  ad =adminDao.find(account.trim());
			if( ad!=null && ad.getPassword().equals(md5ps))
			 return ad;
		}
			return null;
	}
	public boolean add(Admin admin ) {
		if( admin !=null){
			String pas = WebUtils.MD5Encode(admin.getPassword());
			admin.setPassword(pas);
			try {
				adminDao.save(admin);
			} catch (Exception e) {
				e.printStackTrace();
				log.error(e.getMessage());
				return false;
			}
			return true;
		}else{
			throw new RuntimeException("保存管理员不能为null");
		}
			
		
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
			return adminDao.find(account.trim());
		}
		return null;
	}
	public void update(Admin admin) {
		// TODO Auto-generated method stub
		if( admin!=null){
			adminDao.update(admin);
		}else{
			throw new RuntimeException("更新用户为null");
		}
	}
}
