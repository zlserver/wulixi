package com.cnu.wlx.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

/**
* @author 周亮 
* @version 创建时间：2016年4月11日 下午10:26:51
* 类说明：网页路径读取工具，所有页面的的路径都通过该类获取
*/
public class SiteUtils {
	/**
	 * 管理员管理中心界面
	 */
	private static Logger log = LogManager.getLogger(SiteUtils.class);
	/**
	 * 存放所有网页路径的配置信息
	 */
	private static Properties properties = new Properties();
	/**
	 * 加载存放网页地址的文件
	 */
	static{
		//获取文件
		InputStream is=SiteUtils.class.getClassLoader().getResourceAsStream("site.properties");
		try {
			properties.load(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("加载网页路径配置文件site.properties出错："+e.getMessage());
		}finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
				log.error("关闭页路径配置文件site.properties读取流出错："+e.getMessage());
			}
		}
		
	}
	/**
	 * 根据网页key读取网页路径
	 * @param key
	 * @return
	 */
	public static String getSite(String key){
		
		if( properties.containsKey(key)){
			return (String) properties.get(key);
		}else
			throw new RuntimeException("网页路径key值不存在!");
		
	}
}
