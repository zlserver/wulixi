package com.cnu.wlx.formbean;
/**
* @author 周亮 
* @version 创建时间：2016年5月26日 上午9:14:48
* 类说明
*/
public class HomeForm extends BaseForm {
	//学工新闻
	private String xueClassCode;
	//下载专区
	private String xiaClassCode;
	//通知公告
	private String tongClassCode;
	//就业实习信息
	private String jobClassCode;
	//活动剪影
	private String huoClassCode;
	//校园风光
	private String fengClassCode;
	//学习标兵
	private String biaoClassCode;
	//荣誉表彰
	private String biaozhangClassCode;
	
	public HomeForm() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public HomeForm(String xueClassCode, String xiaClassCode, String tongClassCode, String jobClassCode,
			String huoClassCode, String fengClassCode, String biaoClassCode, String biaozhangClassCode) {
		super();
		this.xueClassCode = xueClassCode;
		this.xiaClassCode = xiaClassCode;
		this.tongClassCode = tongClassCode;
		this.jobClassCode = jobClassCode;
		this.huoClassCode = huoClassCode;
		this.fengClassCode = fengClassCode;
		this.biaoClassCode = biaoClassCode;
		this.biaozhangClassCode = biaozhangClassCode;
	}

	public String getBiaozhangClassCode() {
		return biaozhangClassCode;
	}
	public void setBiaozhangClassCode(String biaozhangClassCode) {
		this.biaozhangClassCode = biaozhangClassCode;
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
	/**
	 * 新闻预览图片Vo
	 * @author liang
	 * @version 创建时间 2016年10月8日
	 * 说明:
	 *
	 */
	public static class NewsPreview{
		private String title;
		private String classCode;
		private String newsId;
		private String savePath;
		public NewsPreview(String title, String classCode, String newsId, String savePath) {
			super();
			this.title = title;
			this.classCode = classCode;
			this.newsId = newsId;
			this.savePath = savePath;
		}
		public String getTitle() {
			return title;
		}
		public String getClassCode() {
			return classCode;
		}
		public String getNewsId() {
			return newsId;
		}
		public String getSavePath() {
			return savePath;
		}
	}
}
