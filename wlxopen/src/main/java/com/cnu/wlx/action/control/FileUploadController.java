package com.cnu.wlx.action.control;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.cnu.wlx.bean.base.MyStatus;
import com.cnu.wlx.utils.SiteUtils;

import net.sf.json.JSON;
import net.sf.json.JSONObject;

@Controller
@RequestMapping(value="/control/file/")
public class FileUploadController {

       @RequestMapping(value="upload", method=RequestMethod.POST)
		public String handleFileUpload(MultipartHttpServletRequest request,Model model,String name,String testName){
    	   
    	   
    	   MyStatus status = new MyStatus();
    		System.out.println(testName+"-建立了--"+name);
			Iterator<String> iterator = request.getFileNames();
			
			while (iterator.hasNext()) {
					String fileName = iterator.next();
					MultipartFile multipartFile = request.getFile(fileName);
					String originName=multipartFile.getOriginalFilename();
					System.out.println(originName+"----");
					try {
						//保存
						
						//byte[] file = multipartFile.getBytes();
						String savePath= SiteUtils.getSavePath("news.file");
						File saveFile = new File(savePath,originName);
						if( !saveFile.exists())
							saveFile.mkdirs();
						multipartFile.transferTo(saveFile);
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					// do stuff...
					
			}
			
			// do stuff...
			JSONObject json= JSONObject.fromObject(status);
			model.addAttribute("json", json.toString());
		return SiteUtils.getPage("json");
		}
}