package com.cnu.wlx.bean.base;


/**
* @author 周亮 
* @version 创建时间：2015年11月7日 下午8:05:08
* * 用于描述传给手机端的信息的状态
 * status：状态字
 * message：结果说明
*/
public class MyStatus {


	/**
	 * status为1表示传过来的数据有效，0为无效,默认为1
	 */
	private int status ;
	/**
	 * message如果传输成功为“ok",如果失败记录失败说明,默认为ok
	 */
	private String message;
	
	
	public MyStatus(int status, String message) {
		super();
		this.status = status;
		this.message = message;
	}
	/**
	 * 默认构造函数，默认status=1,message="ok"
	 */
	public MyStatus() {
		super();
		this.status=1;
		this.message="ok";
	}
	/**
	 * status为1表示传过来的数据有效，-1为无效,默认为1
	 */
	public int getStatus() {    
		return status;
	}
	/**
	 * status为1表示传过来的数据有效，-1为无效,默认为1
	 */
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}
