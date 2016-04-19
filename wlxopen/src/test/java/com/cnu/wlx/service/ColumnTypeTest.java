package com.cnu.wlx.service;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cnu.wlx.bean.ColumnType;
import com.cnu.wlx.myenum.ColumnTypeDesEnum;

/**
* @author 周亮 
* @version 创建时间：2016年4月19日 上午11:09:14
* 类说明
*/
public class ColumnTypeTest {

	private static ApplicationContext ac ;
	@Before
	public void init() {
		ac = new ClassPathXmlApplicationContext("wlx-service.xml");
		
	}

	@Test
	public void addColumnType(){
		ColumnTypeService ctService=ac.getBean(ColumnTypeService.class);
		//父类
		ColumnType ct = new ColumnType("系统管理", "XT01", ColumnTypeDesEnum.SYSTEM_TYPE);
		//子类
		ColumnType ctChildren = new ColumnType("栏目管理", "XT0101");
		//设置关系
		ctChildren.setParent(ct);
  		ctService.addColumnType(ct);
		ctService.addColumnType(ctChildren);
	}
	
	@Test
	public void getTopColumn(){
		ColumnTypeService ctService=ac.getBean(ColumnTypeService.class);
		List<ColumnType> list= ctService.getTopColumns();
		System.out.println(list.toString());
	}
	
}
