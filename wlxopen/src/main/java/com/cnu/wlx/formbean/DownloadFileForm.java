package com.cnu.wlx.formbean;

import java.util.List;

import com.cnu.wlx.bean.DownloadFile;

/**
* @author 周亮 
* @version 创建时间：2016年5月23日 下午2:43:21
* 类说明
*/

public class DownloadFileForm extends BaseForm {

	private DownloadFile downloadFile;
	
	private String  state;
	
	private String titleColor;
	/**
	 * 添加的文件的id
	 */
	private List<String> fileIds;
    
	private List<String> states;
	
	private List<Integer>  sequences;
	
	private List<Integer> suggests;
	
	private List<Integer> checkeds;
	

	public List<Integer> getCheckeds() {
		return checkeds;
	}

	public void setCheckeds(List<Integer> checkeds) {
		this.checkeds = checkeds;
	}

	public List<String> getStates() {
		return states;
	}

	public void setStates(List<String> states) {
		this.states = states;
	}

	public List<Integer> getSequences() {
		return sequences;
	}

	public void setSequences(List<Integer> sequences) {
		this.sequences = sequences;
	}

	public List<Integer> getSuggests() {
		return suggests;
	}

	public void setSuggests(List<Integer> suggests) {
		this.suggests = suggests;
	}

	public List<String> getFileIds() {
		return fileIds;
	}

	public void setFileIds(List<String> fileIds) {
		this.fileIds = fileIds;
	}

	public String getTitleColor() {
		return titleColor;
	}

	public void setTitleColor(String titleColor) {
		this.titleColor = titleColor;
	}

	public DownloadFile getDownloadFile() {
		return downloadFile;
	}

	public void setDownloadFile(DownloadFile downloadFile) {
		this.downloadFile = downloadFile;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	
}