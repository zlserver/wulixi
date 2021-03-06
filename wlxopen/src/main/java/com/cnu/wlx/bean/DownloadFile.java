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

import com.cnu.wlx.myenum.ColorEnum;
import com.cnu.wlx.myenum.FileStateEnum;
import com.cnu.wlx.myenum.FileTypeEnum;

/**
* @author 周亮 
* @version 创建时间：2016年5月23日 下午2:13:37
* 类说明:下载文件
*/

@Entity
@Table(name="t_downloadfile")
@GenericGenerator(name="uuidGenderator",strategy="uuid")
public class DownloadFile {

	private String id;
	/**
	 * 附件原名称
	 */
	private String originName;
	/**
	 * 标题颜色
	 */
	private ColorEnum titleColor=ColorEnum.BLACK;
	/**
	 * 保存名称
	 */
	private String saveName;
	
	/**
	 * 上传者
	 */
	private String author;
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
	 * 文件描述
	 */
	private String des;
	/**
	 * 文件类型
	 */
	private FileTypeEnum type;
	/**
	 * 状态
	 */
	private FileStateEnum state=FileStateEnum.VALIDATE;
	/**
	 * 创建时间
	 */
	private Date createTime=new Date();
	/**
	 * 所属栏目分类,多对一，多个新闻对应一条栏目
	 */
	private ColumnType column;
	/**
	 * 推荐1：推荐，2：不推荐
	 */
	private int suggest=2;
	/**
	 * 下载量
	 */
	private int downloadCount=0;
	/**
	 * 顺序
	 */
	private int sequence=0;
	
	public DownloadFile() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public DownloadFile(String id, String originName, ColorEnum titleColor, String saveName, String author,
			String savePath, long size, String ext, FileStateEnum state, Date createTime, ColumnType column,
			int suggest, int downloadCount) {
		super();
		this.id = id;
		this.originName = originName;
		this.titleColor = titleColor;
		this.saveName = saveName;
		this.author = author;
		this.savePath = savePath;
		this.size = size;
		this.ext = ext;
		this.state = state;
		this.createTime = createTime;
		this.column = column;
		this.suggest = suggest;
		this.downloadCount = downloadCount;
	}

	public DownloadFile(String originName, ColorEnum titleColor, String saveName, String author, String savePath,
			long size, String ext, ColumnType column) {
		super();
		this.originName = originName;
		this.titleColor = titleColor;
		this.saveName = saveName;
		this.author = author;
		this.savePath = savePath;
		this.size = size;
		this.ext = ext;
		this.column = column;
	}
	@Id @GeneratedValue(generator="uuidGenderator")
	@Column(length=32)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	@Column(nullable=false,length=100)
	public String getOriginName() {
		return originName;
	}
	public void setOriginName(String originName) {
		this.originName = originName;
	}
	@Enumerated(EnumType.STRING)
	public FileTypeEnum getType() {
		return type;
	}
	public void setType(FileTypeEnum type) {
		this.type = type;
	}


	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
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
	public String getAuthor() {
		return author;
	}
	@Column(nullable=false)
	public void setAuthor(String author) {
		this.author = author;
	}

	@ManyToOne(optional=true)
	@JoinColumn(name="columnId")
	public ColumnType getColumn() {
		return column;
	}
	public void setColumn(ColumnType column) {
		this.column = column;
	}
	public int getSuggest() {
		return suggest;
	}
	public void setSuggest(int suggest) {
		this.suggest = suggest;
	}
	@Enumerated(EnumType.STRING)
	@Column(nullable=false,length=10)
	public ColorEnum getTitleColor() {
		return titleColor;
	}

	public void setTitleColor(ColorEnum titleColor) {
		this.titleColor = titleColor;
	}
	public int getDownloadCount() {
		return downloadCount;
	}
	public void setDownloadCount(int downloadCount) {
		this.downloadCount = downloadCount;
	}

}
