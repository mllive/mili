package com.robot.weixin.menu;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;

import com.robot.util.HttpUtil;
import com.robot.weixin.menu.model.Menu;

import net.sf.json.JSONObject;

public class WeixinHttpUtil {
	public static void getAccessToken() {
		try {
			String appid = Constants.APPID_KEY;
			String appsecret = Constants.APPSECRET_KEY;
			String url = Constants.WEIXIN_URL_ACCESS_TOKEN;
			url = url.replace("{1}", appid).replace("{2}", appsecret);
			GetMethod get = new GetMethod(url);
			HttpClient client = new HttpClient();
			int status = client.executeMethod(get);
			if (status == HttpStatus.SC_OK) {
				String res = get.getResponseBodyAsString();
				JSONObject response = JSONObject.fromObject(res);
				System.out.println(response);
				Constants.ACCESS_TOKEN = (String) response.get("access_token");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 清空菜单，重新创建菜单前的操作
	 */
	public static boolean clearMenu() {
		try {
			String url = Constants.WEIXIN_URL_MENU_DEL;
			String accessToken = Constants.ACCESS_TOKEN;
			url = url.replace("{1}", accessToken);
			GetMethod get = new GetMethod(url);
			HttpClient client = new HttpClient();
			int status = client.executeMethod(get);
			if (status == HttpStatus.SC_OK) {
				System.out.println(get.getResponseBodyAsString());
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 创建菜单
	 * 
	 * @return 0表示成功，其他值表示失败
	 */
	public static boolean createMenu(Menu formatMenu) {
		try {
			if (clearMenu()) {
				int result = 0;
				// 拼装创建菜单的url
				String url = Constants.WEIXIN_URL_MENU_CREATE;
				url = url.replace("{1}", Constants.ACCESS_TOKEN);
				// 将菜单对象转换成json字符串
				String jsonMenu = JSONObject.fromObject(formatMenu).toString();
				System.out.println(jsonMenu);
				// 调用接口创建菜单
				JSONObject jsonObject = HttpUtil.httpRequestSSL(url, "POST", jsonMenu);
				System.out.println(jsonObject);
				if (null != jsonObject) {
					if (0 != jsonObject.getInt("errcode")) {
						result = jsonObject.getInt("errcode");
						System.out.println(result);
					}
					return true;
				} else {
					return false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static void main(String[] args) {
		getAccessToken();
		Menu formatMenu = MenuManager.getMenu();
		createMenu(formatMenu);
	}
}