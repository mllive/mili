package com.robot.weixin.service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.robot.weixin.menu.Constants;
import com.robot.weixin.message.resp.Article;
import com.robot.weixin.message.resp.NewsMessage;
import com.robot.weixin.message.resp.TextMessage;
import com.robot.weixin.util.MessageUtil;

/**
 * 核心服务类
 */
@Service("weChatService")
public class WeChatService {

	private static Logger log = LoggerFactory.getLogger(WeChatService.class);

	/**
	 * 处理微信发来的请求
	 * 
	 * @param request
	 * @return
	 */
	public String processRequest(HttpServletRequest request) {
		String respMessage = null;
		try {
			// xml请求解析
			Map<String, String> requestMap = MessageUtil.parseXml(request);

			// 发送方帐号（open_id）
			String fromUserName = requestMap.get("FromUserName");
			// 公众帐号
			String toUserName = requestMap.get("ToUserName");
			// 消息类型
			String msgType = requestMap.get("MsgType");

			// TODO 增加到用户访问菜单的个数统计

			// 文本消息
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
				String reqContent = requestMap.get("Content");
				// TODO 判断用户发来的是否以意见或建议开头，如果是，则进行相关处理
				respMessage = handleTextRequest(fromUserName, toUserName, reqContent);
			}
			// 图片消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
			}
			// 链接消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
			}
			// 音频消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
			}
			// 事件推送
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				// 事件类型
				String eventType = requestMap.get("Event");
				respMessage = handelEventRequest(fromUserName, toUserName, eventType, requestMap);
			}
			if (null == respMessage) {
				respMessage = getTextMessage(fromUserName, toUserName, "");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(respMessage);
		return respMessage;
	}

	private String handleTextRequest(String fromUserName, String toUserName, String reqContent) {
		String respMessage = "";
		// 查询指定城市天气
		if (reqContent.startsWith("城市")) {
			String city = reqContent.replace("城市", "");
			// 返回消息内容
			//respMessage = getWeatherNewsMessage(fromUserName, toUserName, city, null, 1);
		} else {
			if (reqContent.equals("2")) {
				
				//respMessage = getWeatherNewsMessage(fromUserName, toUserName, city, location, 1);
			} else if (reqContent.equals("3")) {
				
			} else if (reqContent.equals("4")) {
				
			} else if (reqContent.equals("5")) {
				
			} else {
				
			}
		}
		return respMessage;
	}

	private String handelEventRequest(String fromUserName, String toUserName, String eventType,
			Map<String, String> requestMap) throws IllegalAccessException, InvocationTargetException {
		String respMessage = "";
		// 订阅
		if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
			
		}
		// 取消订阅
		else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
			
		}
		// 自定义菜单点击事件
		else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
			// 事件KEY值，与创建自定义菜单时指定的KEY值对应
			String eventKey = requestMap.get("EventKey");
			
		}
		// 地理位置消息
		else if (eventType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
			String latitude = requestMap.get("Latitude");
			String longitude = requestMap.get("Longitude");
			
		}
		return respMessage;
	}

	private String getTextMessage(String fromUserName, String toUserName, String respContent) {
		// 默认回复此文本消息
		TextMessage textMessage = new TextMessage();
		textMessage.setToUserName(fromUserName);
		textMessage.setFromUserName(toUserName);
		textMessage.setCreateTime(new Date().getTime());
		textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
		textMessage.setContent(respContent);
		// 将文本消息对象转换成xml字符串
		return MessageUtil.textMessageToXml(textMessage);
	}

	private String getNewsMessage(String fromUserName, String toUserName, List<Map<String, Object>> submenus) {
		// 创建图文消息
		NewsMessage newsMessage = new NewsMessage();
		newsMessage.setToUserName(fromUserName);
		newsMessage.setFromUserName(toUserName);
		newsMessage.setCreateTime(new Date().getTime());
		newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
		List<Article> articleList = new ArrayList<Article>();
		for (Map<String, Object> submenu : submenus) {
			Article article = new Article();
			article.setTitle(submenu.get("TITLE").toString());
			article.setDescription(submenu.get("DESCRIPTION").toString());
			article.setPicUrl(Constants.WEBURL_KEY + submenu.get("SAVEPATH").toString());
			// String url = Constants.getConfig(Constants.WEBURL_KEY) +
			// "/weixin/webViewController.do?message&keyid="
			// + submenu.get("KEYID").toString();
			String url = submenu.get("URL").toString();
			if (!url.equals("")) {
				article.setUrl(url);
			}
			articleList.add(article);
		}
		newsMessage.setArticleCount(articleList.size());
		newsMessage.setArticles(articleList);
		return MessageUtil.newsMessageToXml(newsMessage);
	}
}