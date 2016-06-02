package com.cnu.wlx.formbean;

import java.util.List;

/**
* @author 周亮 
* @version 创建时间：2016年5月27日 下午9:59:17
* 类说明
*/
public class NewsFileForm extends BaseForm {
	/**
	 * 附件id
	 */
	private List<String> fileIds;
	/**
	 *在新闻附件页面，新添加的附件id
	 */
	private List<String> nfileIds;	
	/**
	 * 所属新闻
	 */
	private String newsId;
	/**
	 * 附件类型,值去FileTypeEnum
	 */
	private String type;
	
	/**
     * 状态：FileStateEnum中的值
     */
	private List<String> states;
	
	
	public List<String> getStates() {
		return states;
	}
	public void setStates(List<String> states) {
		this.states = states;
	}
	
	public List<String> getNfileIds() {
		return nfileIds;
	}
	public void setNfileIds(List<String> nfileIds) {
		this.nfileIds = nfileIds;
	}
	public List<String> getFileIds() {
		return fileIds;
	}
	public void setFileIds(List<String> fileIds) {
		this.fileIds = fileIds;
	}
	
	public String getNewsId() {
		return newsId;
	}
	public void setNewsId(String newsId) {
		this.newsId = newsId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
