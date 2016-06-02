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
	/**
	 * 状态，FileStateEnum
	 */
	private String  state;
	/**
	 * 文件类型FileTypeEnum
	 */
	private String type;
	
	/**
	 * 下载文件题目颜色
	 */
	private String titleColor;
	/**
	 * 文件的id
	 */
	private List<String> fileIds;
	/**
	 * 新添加文件的id
	 */
	private List<String> nfileIds;
    /**
     * 状态：FileStateEnum中的值
     */
	private List<String> states;
	/**
	 * 序号
	 */
	private List<Integer>  sequences;
	/**
	 * 是否推荐
	 */
	private List<Integer> suggests;


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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	
}