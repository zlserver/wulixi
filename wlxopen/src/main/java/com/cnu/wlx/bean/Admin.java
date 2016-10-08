package com.cnu.wlx.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.cnu.wlx.myenum.StateEnum;

/**
* @author 周亮 
* @version 创建时间：2016年3月28日 下午1:49:12
* 类说明
*/
@Entity
@Table(name="t_admin")
public class Admin {
	/**
	 * 账号,20字符之内
	 */
	private String account;
	/**
	 * 密码，md5加密32位
	 */
	private String password;
	/**
	 *登录次数
	 */
	private Integer loginCount=0;
	/**
	 * 发稿篇数
	 */
	private Integer publishCount=0;
	/**
	 * 创建时间
	 */
	private Date createTime=new Date();
	/**
	 * 角色：1.超级管理员，2：普通管理员
	 */
	private int role=2;
	/**
	 * 登录时间
	 */
	private Date loginTime ;
	
	/**
	 * 是否可见
	 */
	private StateEnum state =StateEnum.YES;
	public Admin(String account, String password) {
		super();
		this.account = account;
		this.password = password;
	}
	
	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Id 
	@Column(nullable=false,length=20)
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	@Column(nullable=false,length=32)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getLoginCount() {
		return loginCount;
	}
	public void setLoginCount(Integer loginCount) {
		this.loginCount = loginCount;
	}
	public Integer getPublishCount() {
		return publishCount;
	}
	public void setPublishCount(Integer publishCount) {
		this.publishCount = publishCount;
	}

	@Enumerated(EnumType.STRING)
	public StateEnum getState() {
		return state;
	}

	public void setState(StateEnum state) {
		this.state = state;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	
	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	/**
	 * 登录次数加1
	 */
	public void updateLoginCount(){
		this.loginCount+=1;
	}
	/**
	 * 登录时间设为当前时间
	 */
	public void updateLoginTime(){
		this.loginTime = new Date();
	}
}
