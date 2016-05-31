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
* @author 周亮 
* @version 创建时间：2016年5月26日 下午2:34:02
* 类说明:问题
*/
@Entity
@Table(name="t_question")
@GenericGenerator(name="uuidGenderator",strategy="uuid")
public class Question {

	private String id;
	/**
	 * 身份
	 */
	private String identity;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 内容
	 */
	private String content;
	
	/**
	 * 时间
	 */
	private Date createTime=new Date();
	
	/**
	 * 是否设置为热点问题
	 */
	private StateEnum hot=StateEnum.NO;
	/**
	 * 是否可见
	 */
	private StateEnum visible=StateEnum.NO;
	/**
	 * 是否回答
	 */
	private StateEnum handle=StateEnum.NO;
	/**
	 * 回答者账号
	 */
	private String account;
	/**
	 * 回答内容
	 */
	private String answer;
	/**
	 * 回答时间
	 */
	private Date answerTime;
	

	@Id @GeneratedValue(generator="uuidGenderator")
	@Column(length=32)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Column(nullable=true,length=15)
	public String getIdentity() {
		return identity;
	}
	public void setIdentity(String identity) {
		this.identity = identity;
	}
	@Column(nullable=false,length=35)
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Column(nullable=false,length=255)
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	

	@Column(nullable=true,length=20)
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	@Column(nullable=true,length=255)
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	@Temporal(TemporalType.TIMESTAMP)
	public Date getAnswerTime() {
		return answerTime;
	}
	public void setAnswerTime(Date answerTime) {
		this.answerTime = answerTime;
	}
	@Enumerated(EnumType.STRING)
	public StateEnum getHot() {
		return hot;
	}
	public void setHot(StateEnum hot) {
		this.hot = hot;
	}
	@Enumerated(EnumType.STRING)
	public StateEnum getVisible() {
		return visible;
	}
	public void setVisible(StateEnum visible) {
		this.visible = visible;
	}
	@Enumerated(EnumType.STRING)
	public StateEnum getHandle() {
		return handle;
	}
	public void setHandle(StateEnum handle) {
		this.handle = handle;
	}
	
	
	
}
