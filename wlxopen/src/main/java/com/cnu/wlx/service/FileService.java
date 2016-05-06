package com.cnu.wlx.service;

import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;

/**
* @author 周亮 
* @version 创建时间：2016年5月6日 上午11:09:54
* 类说明
*/
public interface FileService  extends ResourceLoaderAware {
	/**
	 * 获取资源接口
	 * @param savePath 资源路径：资源前缀+资源路径
	 * file:c:/shop/logo.img
	 * classpath:banner.txt
	 * http://www.apress.com/hehe.mp4
	 * @return
	 */
	public Resource getFileResource(String savePath);
}
