package com.cnu.wlx.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.cnu.wlx.myenum.FileStateEnum;

/**
* @author 周亮 
* @version 创建时间：2016年5月8日 下午1:13:42
* 类说明,新闻附件
*/
@Entity
@Table(name="t_newsfile")
@GenericGenerator(name="uuidGenderator",strategy="uuid")
public class NewsFile {
	private String id;
	
	/**
	 * 附件原名称
	 */
	private String originName;
	/**
	 * 保存名称
	 */
	private String saveName;
	
	/**
	 * 保存相对路径，包含文件名
	 */
	private String savePath;
	/**
	 * 大小
	 */
	private long size;
	/**
	 * 后缀
	 */
	private String ext;
	/**
	 * 状态
	 */
	private FileStateEnum state=FileStateEnum.VALIDATE;
	/**
	 * 创建时间
	 */
	private Date createTime=new Date();
	/**
	 * 所属新闻,多对一，
	 */
	private News news;

	@Id @GeneratedValue(generator="uuidGenderator")
	@Column(length=32)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Column(nullable=false,length=100)
	public String getOriginName() {
		return originName;
	}
	public void setOriginName(String originName) {
		this.originName = originName;
	}
	@Column(nullable=false,length=100)
	public String getSaveName() {
		return saveName;
	}
	public void setSaveName(String saveName) {
		this.saveName = saveName;
	}
	@Column(nullable=false,length=150)
	public String getSavePath() {
		return savePath;
	}
	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
	@Column(nullable=false,length=10)
	public String getExt() {
		return ext;
	}
	public void setExt(String ext) {
		this.ext = ext;
	}
	@Enumerated(EnumType.STRING)
	@Column(nullable=false)
	public FileStateEnum getState() {
		return state;
	}
	public void setState(FileStateEnum state) {
		this.state = state;
	}
	public Date getCreateTime() {
		return createTime;
	}
	@Temporal(TemporalType.TIMESTAMP)
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	//可选
	@ManyToOne(optional=true)
	@JoinColumn(name="newsId")
	public News getNews() {
		return news;
	}
	public void setNews(News news) {
		this.news = news;
	}

	
}
