package com.cnu.wlx.service.impl;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import com.cnu.wlx.service.FileService;

/**
* @author 周亮 
* @version 创建时间：2016年5月6日 上午11:10:42
* 类说明
*/
@Service("fileService")
public class FileServiceImpl implements FileService {
	  //资源加载器
		private ResourceLoader resourceLoader;
		
		@Override
		public void setResourceLoader(ResourceLoader resourceLoader) {
			// TODO Auto-generated method stub
			this.resourceLoader = resourceLoader;
		}
		/**
		 * 获取资源接口
		 * @param savePath 资源路径：资源前缀+资源路径
		 * file:c:/shop/logo.img
		 * classpath:banner.txt
		 * http://www.apress.com/hehe.mp4
		 * @return
		 */
		public Resource getFileResource(String savePath){
			return resourceLoader.getResource(savePath);
		}
}
