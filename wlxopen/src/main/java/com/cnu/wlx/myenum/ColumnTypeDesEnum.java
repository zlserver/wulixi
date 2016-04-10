package com.cnu.wlx.myenum;
/**
* @author 周亮 
* @version 创建时间：2016年4月5日 下午5:57:21
* 类说明：栏目类的分类说明
* 分类说明 分为四类：列表类，介绍类，系统类和其它类
* 列表类：栏目管理时链接页面为内容列表，应用于类似“企业动态”等经常有更新的信息管理；
介绍类：栏目管理时链接页面为排版好的内容介绍。应用于类似“公司简介”等只有一条内容的信息管理；
系统类：此栏目为系统类管理模块，设成此类的栏目将不能删除，此栏目也将只能由管理员管理。
其它类：其它类别的管理模块挂接到后台管理时使用，应用于类似“留言管理”，“论坛管理”等
*/
public enum ColumnTypeDesEnum {

	LIST_TYPE("列表类"),DES_TYPE("介绍类"),SYSTEM_TYPE("系统类"),OTHER_TYPE("其它类");
	
	private ColumnTypeDesEnum(String name){
		this.name = name;
	}
	private final String name;

	public String getName() {
		return name;
	}
	
}
