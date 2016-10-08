package com.cnu.wlx.formbean;

import java.util.List;

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
	//private Admin admin;
	/**
	 * 校验码
	 */
	private String checkCode;

	private String account;
	private String password;
	private int role;
	/**
	 * 批量操作
	 */
	private List<String> accounts;
	private List<String> passwords;
	private List<String>  states;
	
	public List<String> getStates() {
		return states;
	}

	public void setStates(List<String> states) {
		this.states = states;
	}

	public List<String> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<String> accounts) {
		this.accounts = accounts;
	}

	public String getAccount() {
		return account;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<String> getPasswords() {
		return passwords;
	}

	public void setPasswords(List<String> passwords) {
		this.passwords = passwords;
	}

	
	/**
	 * 校验账号和密码是否合理
	 * @return
	 */
	public boolean validateAccountAndPass(){
		//标识
		boolean flage = true;
		if(!validateLen(account, 3, 20)||!validateLen(password, 3, 20))
		{
			this.getResult().put("error", "账号或密码有误!");//账号长度在3-20之间!
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
		getResult().put("error", "校验码不正确");
		return false;
	}

	public String getCheckCode() {
		return checkCode;
	}

	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}
	
}
