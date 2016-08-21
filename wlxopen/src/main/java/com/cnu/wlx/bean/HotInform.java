package com.cnu.wlx.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.cnu.wlx.myenum.StateEnum;

/**
 * 热点通知，会显示在网站的右下角
 * @author liang
 * @version 创建时间 2016年8月19日
 * 说明:
 *
 */
@Entity
@Table(name="t_hotinform")
@GenericGenerator(name="uuidGenderator",strategy="uuid")
public class HotInform {
	private String id;
	/**
	 * 通知名称
	 */
	private String title;
	/**
	 * 通知消息
	 */
	private String message;
	/**
	 * 通知图片
	 */
	private String picPath;
	/**
	 * 通知链接
	 */
	private String link;
	/**
	 * 是否可见
	 */
	private StateEnum state =StateEnum.NO;
	/**
	 * 发布者账号
	 */
	private String account;
	/**
	 * 创建时间
	 */
	private Date createTime=new Date();

	@Id @GeneratedValue(generator="uuidGenderator")
	@Column(length=32)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getPicPath() {
		return picPath;
	}
	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	@Enumerated(EnumType.STRING)
	public StateEnum getState() {
		return state;
	}
	public void setState(StateEnum state) {
		this.state = state;
	}
	@Column(nullable=false)
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	
}
