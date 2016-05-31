package com.cnu.wlx.formbean;
/**
* @author 周亮 
* @version 创建时间：2016年5月26日 上午9:14:48
* 类说明
*/
public class HomeForm extends BaseForm {
	//学工新闻id、
	private String xueClassCode;
	//下载专区id、
	private String xiaClassCode;
	//通知公告id、
	private String tongClassCode;
	//就业实习信息id、
	private String jobClassCode;
	//活动剪影id、
	private String huoClassCode;
	//校园风光id、
	private String fengClassCode;
	//学习标兵id
	private String biaoClassCode;
	
	
	public HomeForm(String xueClassCode, String xiaClassCode, String tongClassCode, String jobClassCode,
			String huoClassCode, String fengClassCode, String biaoClassCode) {
		super();
		this.xueClassCode = xueClassCode;
		this.xiaClassCode = xiaClassCode;
		this.tongClassCode = tongClassCode;
		this.jobClassCode = jobClassCode;
		this.huoClassCode = huoClassCode;
		this.fengClassCode = fengClassCode;
		this.biaoClassCode = biaoClassCode;
	}
	public String getXueClassCode() {
		return xueClassCode;
	}
	public void setXueClassCode(String xueClassCode) {
		this.xueClassCode = xueClassCode;
	}
	public String getXiaClassCode() {
		return xiaClassCode;
	}
	public void setXiaClassCode(String xiaClassCode) {
		this.xiaClassCode = xiaClassCode;
	}
	public String getTongClassCode() {
		return tongClassCode;
	}
	public void setTongClassCode(String tongClassCode) {
		this.tongClassCode = tongClassCode;
	}
	public String getJobClassCode() {
		return jobClassCode;
	}
	public void setJobClassCode(String jobClassCode) {
		this.jobClassCode = jobClassCode;
	}
	public String getHuoClassCode() {
		return huoClassCode;
	}
	public void setHuoClassCode(String huoClassCode) {
		this.huoClassCode = huoClassCode;
	}
	public String getFengClassCode() {
		return fengClassCode;
	}
	public void setFengClassCode(String fengClassCode) {
		this.fengClassCode = fengClassCode;
	}
	public String getBiaoClassCode() {
		return biaoClassCode;
	}
	public void setBiaoClassCode(String biaoClassCode) {
		this.biaoClassCode = biaoClassCode;
	}
	
}
