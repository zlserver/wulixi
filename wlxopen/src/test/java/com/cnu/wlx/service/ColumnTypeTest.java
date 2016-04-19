package com.cnu.wlx.service;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cnu.wlx.bean.ColumnType;
import com.cnu.wlx.myenum.ColumnTypeDesEnum;

/**
* @author ���� 
* @version ����ʱ�䣺2016��4��19�� ����11:09:14
* ��˵��
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
		//����
		ColumnType ct = new ColumnType("ϵͳ����", "XT01", ColumnTypeDesEnum.SYSTEM_TYPE);
		//����
		ColumnType ctChildren = new ColumnType("��Ŀ����", "XT0101");
		//���ù�ϵ
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
