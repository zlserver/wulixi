package com.cnu.wlx.myenum;
/**
* @author 周亮 
* @version 创建时间：2016年5月3日 下午2:05:38
* 类说明
*/
public enum ColorEnum {
   RED("red"),BLACK("black"),BLUE("blue");
	
	private ColorEnum(String color){
		this.color = color;
	}
	private final String color;

	public String getColor() {
		return color;
	}
}
