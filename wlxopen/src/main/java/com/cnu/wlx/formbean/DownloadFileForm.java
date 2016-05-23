package com.cnu.wlx.formbean;

import com.cnu.wlx.bean.DownloadFile;

/**
* @author 周亮 
* @version 创建时间：2016年5月23日 下午2:43:21
* 类说明
*/

public class DownloadFileForm extends BaseForm {

	private DownloadFile downloadFile;
	
	private String  state;

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