package com.cnu.wlx.myenum;
/**
* @author 周亮 
* @version 创建时间：2016年5月4日 上午9:05:37
* 类说明:新闻状态
*/
public enum NewsStateEnum {
	
	PUBLISH("publish"),WAITING("waiting"),CLOSE("close");
	
	private NewsStateEnum(String name){
		this.name = name;
	}
	private final String name;

	public String getName() {
		return name;
	}
}
