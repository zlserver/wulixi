package com.cnu.wlx.formbean;

import java.util.List;

import com.cnu.wlx.bean.Question;

/**
* @author 周亮 
* @version 创建时间：2016年5月26日 下午3:01:14
* 类说明
*/
public class QuestionForm extends BaseForm {

	private String questionId;
	private Question question;
	/**
	 * 是否热点
	 */
	private String hot;
	/**
	 * 是否可见
	 */
	private String visible;
	/**
	 * 是否回答
	 */
	private String handle;
	
	private List<String> ids;
	private List<String> hots;
	
	private List<String>  visibles;
	
	private List<String> handles;
	
	private List<Integer> checkeds;
	
	private List<String> answers;

	
	public String getQuestionId() {
		return questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}

	public String getHot() {
		return hot;
	}

	public void setHot(String hot) {
		this.hot = hot;
	}

	public String getVisible() {
		return visible;
	}

	public void setVisible(String visible) {
		this.visible = visible;
	}

	public String getHandle() {
		return handle;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public void setHandle(String handle) {
		this.handle = handle;
	}

	public List<String> getIds() {
		return ids;
	}

	public void setIds(List<String> ids) {
		this.ids = ids;
	}

	public List<String> getHots() {
		return hots;
	}

	public void setHots(List<String> hots) {
		this.hots = hots;
	}

	public List<String> getVisibles() {
		return visibles;
	}

	public void setVisibles(List<String> visibles) {
		this.visibles = visibles;
	}

	public List<String> getHandles() {
		return handles;
	}

	public void setHandles(List<String> handles) {
		this.handles = handles;
	}

	public List<Integer> getCheckeds() {
		return checkeds;
	}

	public void setCheckeds(List<Integer> checkeds) {
		this.checkeds = checkeds;
	}

	public List<String> getAnswers() {
		return answers;
	}

	public void setAnswers(List<String> answers) {
		this.answers = answers;
	}
	
	
}
