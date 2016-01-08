package com.robot.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;

import com.robot.interceptors.AuthInterceptor;
import com.robot.util.ExceptionUtil;

/**
 * 基础控制器，其他控制器需extends此控制器获得initBinder自动转换的功能
 */
public class BaseController {
	protected final Logger logger = Logger.getLogger(AuthInterceptor.class);

	/**
	 * 将前台传递过来的日期格式的字符串，自动转化为Date类型
	 * 
	 * @param binder
	 * 
	 */
	@InitBinder
	public void initBinder(ServletRequestDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
		// binder.registerCustomEditor(String.class, new
		// StringTrimmerEditor(false));
	}

	/**
	 * 处理运行时异常
	 * @param request
	 * @param ex
	 * @return
	 */
	@ExceptionHandler
	public String exp(Model model, Exception ex) {
		String errorpage = "/error/exception";

		// 记录日志
		logger.error(ex.getMessage(), ex);

		model.addAttribute("ex", ExceptionUtil.getExceptionMessage(ex));
		return errorpage;
	}
}
