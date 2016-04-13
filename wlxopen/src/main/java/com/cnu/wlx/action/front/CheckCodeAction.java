package com.cnu.wlx.action.front;

import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cnu.wlx.utils.CheckCodeGenerator;
import com.cnu.wlx.utils.CheckCodeGenerator.CheckCode;

/**
* @author 周亮 
* @version 创建时间：2016年4月12日 下午8:16:14
* 类说明:验证码生成类，负责处理刷新验证码请求
*/
@Controller
@RequestMapping("/common/*")
public class CheckCodeAction {
	private Logger log = LogManager.getLogger(CheckCodeAction.class);
	/**
	 * 验证码宽，默认120
	 */
	private int width=120;
	/**
	 * 验证码高，默认20
	 */
	private int hight=30;	
	/**
	 * 生成校验码
	 * @param response
	 * @param request
	 */
	@RequestMapping(value="checkCode")
	public void generatorCheckCode(HttpServletResponse response,HttpServletRequest request){
		
		CheckCodeGenerator codeGenerator = new CheckCodeGenerator(hight, width);
		
		CheckCode checkCode =codeGenerator.getCheckCode();
		
		// 保存到session中
		request.getSession().setAttribute("checkCode",checkCode.getCheckCode() );
		//设置发送的内容
		response.setContentType("image/jpeg");
		//发头控制浏览器不要缓存
		response.setHeader("expires", "-1");    
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		// 输出到response.getOutputStream()中
		try {
			ImageIO.write(checkCode.getImage(), "jpg", response.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("生成校验码出错:"+e.getMessage());
		}
		// 释放资源
		//g.dispose();
		
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHight() {
		return hight;
	}
	public void setHight(int hight) {
		this.hight = hight;
	}
	
}
