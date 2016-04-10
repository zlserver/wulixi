package com.cnu.wlx.bean;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.cnu.wlx.myenum.ColumnTypeDesEnum;

/**
* @author 周亮 
* @version 创建时间：2016年4月5日 下午5:52:57
* 类说明：栏目类别表
*/
@Entity
@Table(name="t_columnType")
@GenericGenerator(name="uuidGenderator",strategy="uuid")
public class ColumnType {
	/**
	 * 标识id，uuid
	 */
	private String id;
	/**
	 * 栏目名称小于15个字符
	 */
	private String name;
	/**
	 * 分类码，6个字符,唯一
	 */
	private String classCode;
	/**
	 * 分类说明，默认列表类，保存枚举类型的字符串,长度最大10字符
	 */
	private ColumnTypeDesEnum typeDes=ColumnTypeDesEnum.LIST_TYPE;
	/**
	 * 组别，不同顶级栏目下有很多子栏目，如果想查找所有子栏目下的“新闻栏目”，就按照组别标识来查，将同一类的栏目设置一个组别。
	 */
	private String groupType;
	/**
	 * 浏览程序地址，最大100字符
	 */
	private String readUrl;
	/**
	 * 管理程序地址，最大100字符
	 */
	private String manageUrl;
	/**
	 * 顺序，默认0
	 */
	private Integer sequence=0;
	/**
	 * 父类栏目，多对一
	 */
	private ColumnType parent;
	/**
	 * 子类栏目，一对多
	 */
	private Set<ColumnType> childrens = new HashSet<ColumnType>();
	
	
	
	public ColumnType(String name, String classCode, ColumnTypeDesEnum typeDes, String groupType) {
		super();
		this.name = name;
		this.classCode = classCode;
		this.typeDes = typeDes;
		this.groupType = groupType;
	}
	public ColumnType(String name, String classCode) {
		super();
		this.name = name;
		this.classCode = classCode;
	}
	public ColumnType() {
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
	@Column(nullable=false,length=15)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(unique=true,length=6)
	public String getClassCode() {
		return classCode;
	}
	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}
	@Enumerated(value=EnumType.STRING)
	@Column(nullable=false,length=10)
	public ColumnTypeDesEnum getTypeDes() {
		return typeDes;
	}
	public void setTypeDes(ColumnTypeDesEnum typeDes) {
		this.typeDes = typeDes;
	}
	@Column(length=20)
	public String getGroupType() {
		return groupType;
	}
	public void setGroupType(String groupType) {
		this.groupType = groupType;
	}
	
	@Column(length=100)
	public String getReadUrl() {
		return readUrl;
	}
	public void setReadUrl(String readUrl) {
		this.readUrl = readUrl;
	}
	@Column(length=100)
	public String getManageUrl() {
		return manageUrl;
	}
	public void setManageUrl(String manageUrl) {
		this.manageUrl = manageUrl;
	}
	public Integer getSequence() {
		return sequence;
	}
	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}
	@ManyToOne(optional=true)
	@JoinColumn(name="parentid")
	public ColumnType getParent() {
		return parent;
	}
	public void setParent(ColumnType parent) {
		this.parent = parent;
	}
	@OneToMany(mappedBy="parent")
	public Set<ColumnType> getChildrens() {
		return childrens;
	}
	public void setChildrens(Set<ColumnType> childrens) {
		this.childrens = childrens;
	}
	
}
