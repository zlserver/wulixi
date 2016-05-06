package com.cnu.wlx.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

/**
* @author 周亮 
* @version 创建时间：2016年5月6日 上午10:33:57
* 类说明
*/
@Entity
@Table(name="t_newsPicture")
@GenericGenerator(name="uuidGenderator",strategy="uuid")
public class NewsPicture {
	
	private String id;
	/**
	 * 保存名称
	 */
	private String saveName;
	/**
	 * 原名称
	 */
	private String originName;
	/**
	 * 大小
	 */
	private long size;
	/**
	 * 后缀名
	 */
	private String ext;
	/**
	 * 像素
	 * 300*280
	 */
	private String pixel;
	/**
	 * 类型
	 */
	private String contentType;
	
	/**
	 * 保存相对路径,相对文件系统的根目录
	 */
	private String relativePath;

	@Id @GeneratedValue(generator="uuidGenderator")
	@Column(length=32)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	@Column(nullable=false,length=100)
	public String getSaveName() {
		return saveName;
	}

	public void setSaveName(String saveName) {
		this.saveName = saveName;
	}
	@Column(nullable=false,length=100)
	public String getOriginName() {
		return originName;
	}

	public void setOriginName(String originName) {
		this.originName = originName;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}
	@Column(nullable=false,length=20)
	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	@Column(nullable=false,length=15)
	public String getExt() {
		return ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}
	@Column(length=20)
	public String getPixel() {
		return pixel;
	}

	public void setPixel(String pixel) {
		this.pixel = pixel;
	}
	@Column(nullable=false,length=150)
	public String getRelativePath() {
		return relativePath;
	}

	public void setRelativePath(String relativePath) {
		this.relativePath = relativePath;
	}
	
	
	
	
}
