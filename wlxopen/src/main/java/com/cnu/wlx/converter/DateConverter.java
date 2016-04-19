package com.cnu.wlx.converter;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.beanutils.Converter;

/**
* @author 周亮 
* @version 创建时间：2016年4月19日 下午2:05:16
* 类说明:日期类型在界面和后台交互的数据转换器，
*/
public class DateConverter implements Converter {

	@Override
	public  Object convert(Class clazz, Object value) {
		System.out.println("------------------------------抓换了1");
		  if(clazz==String.class){
				System.out.println("------------------------------抓换了2");
	            Date date = (Date) value;
	            SimpleDateFormat sdf = new SimpleDateFormat("mm-dd HH");
	            return sdf.format(date);
	        }
	        if(clazz==Date.class){
	           return value;
	        }
		return null;
	}

}
