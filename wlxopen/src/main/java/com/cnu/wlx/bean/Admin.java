package com.cnu.wlx.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
* @author 周亮 
* @version 创建时间：2016年3月28日 下午1:49:12
* 类说明
*/
@Entity
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
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
	 * 启用状态，true:启用,默认启用
	 */
	private Boolean visible=true;
	
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
	public Boolean getVisible() {
		return visible;
	}
	public void setVisible(Boolean visible) {
		this.visible = visible;
	}
	
}
