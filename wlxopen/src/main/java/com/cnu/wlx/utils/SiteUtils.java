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
	 * 文件系统根目录
	 */
	private final static String FILE_SYSTEM_DIR="filesystemdir";
	/**
	 * 管理员管理中心界面
	 */
	private static Logger log = LogManager.getLogger(SiteUtils.class);
	/**
	 * 存放所有网页路径的配置信息
	 */
	private static Properties properties = new Properties();
	/**
	 * 文件保存路径
	 */
	private static Properties savePathProperties = new Properties();
	/**
	 * 加载存放网页地址的文件
	 */
	static{
		//获取文件
		InputStream is=SiteUtils.class.getClassLoader().getResourceAsStream("site.properties");
		InputStream saveIs=SiteUtils.class.getClassLoader().getResourceAsStream("savepath.properties");
		try {
			properties.load(is);
			savePathProperties.load(saveIs);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("加载网页路径配置文件site.properties出错："+e.getMessage());
		}finally {
			try {
				is.close();
				saveIs.close();
			} catch (IOException e) {
				e.printStackTrace();
				log.error("关闭页路径配置文件site.properties读取流出错："+e.getMessage());
			}
		}
		
	}
	/**
	 * 根据网页key在site.properties文件中读取网页。记录：admin.controlcenter=admincenter/main
	 * @param key 网页路径的key值，admin.controlcenter
	 * @return key值对应的路径，比如：admincenter/main
	 */
	public static String getPage(String key){
		
		if( properties.containsKey(key)){
			return (String) properties.get(key);
		}else
			throw new RuntimeException("网页路径key值不存在!");
		
	}
	/**
	 * 获取文件保存绝对路径
	 * @param key 取值： news.image
	 * @return  返回带文件系统根目录的绝对路径，D:/Soft/wlxopenfilesystem/images/
	 */
	public static String getSavePath(String key){
		
		if( savePathProperties.containsKey(key)){
			return (String)savePathProperties.getProperty(SiteUtils.FILE_SYSTEM_DIR)+savePathProperties.getProperty(key);
		}else
			throw new RuntimeException("文件路径key值不存在!");
		
	}
	
	/**
	 * 获取文件系统跟路径
	 * @return
	 */
	public static String getFileSystemDir(){
		return (String)savePathProperties.getProperty(SiteUtils.FILE_SYSTEM_DIR);
	}
	/**
	 * 
	 *  获取文件保存相对对路径
	 * @param key 取值： news.image
	 * @return  返回相对于文件系统根目录的相对路径，images/
	 */
	public static String getRelativeSavePath(String key){
		
		if( savePathProperties.containsKey(key)){
			return (String)savePathProperties.getProperty(key);
		}else
			throw new RuntimeException("文件路径key值不存在!");
		
	}
}
