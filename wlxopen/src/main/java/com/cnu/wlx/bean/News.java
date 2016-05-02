package com.cnu.wlx.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="t_news")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
@GenericGenerator(name="uuidGenderator",strategy="uuid")
public class News implements Serializable{
	
	/**
	 * 标识
	 */
	private String id;
	/**
	 * 题目,100个字
	 */
	
	private String title;
	/**
	 * 所属栏目分类,多对一，多个新闻对应一条栏目
	 */
	private ColumnType column;
	/**
	 * 阅读量
	 */
	private long readCount;
	
	/**
	 * 内容
	 */
	private String context;
	/**
	 * 发表时间
	 */
	private Date createTime;
	
	/**
	 * 状态
	 */
	private int state;
	/**
	 * 顺序
	 */
	private int sequence=0;
	
	@Id @GeneratedValue(generator="uuidGenderator")
	@Column(length=32)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	@Column(nullable=false,length=100)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	

	public long getReadCount() {
		return readCount;
	}
	public void setReadCount(long readCount) {
		this.readCount = readCount;
	}
	
	@ManyToOne(optional=false)
	@JoinColumn(name="classCode",referencedColumnName="classCode")
	public ColumnType getColumn() {
		return column;
	}

	public void setColumn(ColumnType column) {
		this.column = column;
	}

	@Column(length=5000)
	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Column(nullable=false)
	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}
	

}
