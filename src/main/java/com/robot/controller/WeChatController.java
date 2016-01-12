package com.robot.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.robot.weixin.service.WeChatService;
import com.robot.weixin.util.SignUtil;

/**
 * 主控制器，接收微信所有请求
 */
@Controller
@RequestMapping("/wechat")
public class WeChatController {

	private static final Logger log = Logger.getLogger(WeChatController.class);

	@Autowired
	private WeChatService weChatService;

	/**
	 * 主方法，需区分get和post请求
	 * 
	 * @param req
	 * @param resp
	 * @throws Exception
	 * @throws Exception
	 */
	@RequestMapping("/")
	public void weChat(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// get方法处理token验证
		if ("GET".equalsIgnoreCase(req.getMethod())) {
			doGet(req, resp);
		}
		// post方法处理其它所有请求
		else {
			doPost(req, resp);
		}
	}

	/**
	 * get方法返回echostr参数值
	 * 
	 * @param req
	 * @param resp
	 */
	private void doGet(HttpServletRequest req, HttpServletResponse resp) {
		String signature = req.getParameter("signature");
		String timestamp = req.getParameter("timestamp");
		String nonce = req.getParameter("nonce");
		String echostr = req.getParameter("echostr");
		log.info(signature + " : " + timestamp + " : " + nonce + " : " + echostr);
		PrintWriter out = null;
		try {
			out = resp.getWriter();
			// 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
			if (SignUtil.checkSignature(signature, timestamp, nonce)) {
				out.print(echostr);
			}
		} catch (IOException e) {
			log.info(e.getMessage() + e.getStackTrace());
			e.printStackTrace();
		} finally {
			out.close();
			out = null;
		}
	}

	/**
	 * 处理接收消息并回复
	 * 
	 * @param req
	 * @param resp
	 */
	private void doPost(HttpServletRequest req, HttpServletResponse resp) {
		PrintWriter out = null;
		try {
			out = resp.getWriter();
			String respMessage = weChatService.processRequest(req);
			out.print(respMessage);
		} catch (IOException e) {
			log.info(e.getMessage() + e.getStackTrace());
			e.printStackTrace();
		} finally {
			out.close();
			out = null;
		}
	}
}
