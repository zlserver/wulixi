package com.cnu.wlx.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
	
	@Test
	public void readFile(){
		//small money.mp3  me.jpg
		String fileName="small money.mp3";
		String sfileName="smallmoney.mp3";
		try {
			InputStream is = ColumnTypeTest.class.getClassLoader().getResourceAsStream(fileName);
			
		BufferedInputStream bis = new BufferedInputStream(is);
		    File mp = new File("D:/",sfileName);
			OutputStream os = new FileOutputStream(mp);
			BufferedOutputStream bos = new BufferedOutputStream(os);
			byte[] buff = new byte[1024];
			
			int len =0;
			while( (len=bis.read(buff))!=-1){
				bos.write(buff);
				bos.flush();
			}
			bos.close();
			bis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	@Test
	public void readMap(){
		Map<String,String> order =new LinkedHashMap<String, String>();

		order.put("zhou", "z");
		order.put("lisi", "l");
		order.put("ali", "a");
		order.put("bai", "b");
		
		boolean flage = false;
		for( String key :order.keySet()){
			System.out.print(key+" ");
			if( flage )
				order.remove(key);
			if( key.equals("ali"))
				flage = true;
			
		}
		System.out.println("-----");
		for( String key :order.keySet()){
			System.out.print(key+" ");
		}
	}

	
}
