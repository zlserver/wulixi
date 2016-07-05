package com.cnu.wlx.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/**
* @author 周亮 
* @version 创建时间：2016年7月5日 下午5:21:15
* 类说明:访问记录，只有访问首页时才认为进行一次访问
*/
@Entity
@Table(name="t_vistrecord")

@GenericGenerator(name="uuidGenderator",strategy="uuid")
public class VistRecord {

	
	private String id;
	//访问ip
	private String ip;
	//访问时间
	private Date createTime=new Date();
	
	
	public VistRecord(String ip, Date createTime) {
		super();
		this.ip = ip;
		this.createTime = createTime;
	}
	public VistRecord() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Id @GeneratedValue(generator="uuidGenderator")
	@Column(length=32)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
	
}
