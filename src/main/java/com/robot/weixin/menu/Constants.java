package com.robot.weixin.menu;

public class Constants {

	public final static String APPID_KEY = "appid";
	public final static String APPSECRET_KEY = "appsecret";
	public final static String WEBURL_KEY = "系统URL地址";
	public final static String TOKEN = "token";

	public static String ACCESS_TOKEN = "FsfIE3Rfojsfv5ymH90Q8YVgIiLdTGdD2DJAuMdclRPFzi5DqZ4460JNkU9iG3xVfMF763F4kf5-VcDduZWKGA";

	public final static String WEIXIN_URL_ACCESS_TOKEN = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid={1}&secret={2}";
	// 清空自定义菜单
	public final static String WEIXIN_URL_MENU_DEL = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token={1}";
	// 创建自定义菜单
	public final static String WEIXIN_URL_MENU_CREATE = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token={1}";
	// 粉丝列表
	public final static String WEIXIN_URL_FANS_GET = "https://api.weixin.qq.com/cgi-bin/user/get?access_token={1}&next_openid={2}";
	// 粉丝信息
	public final static String WEIXIN_URL_FANS_GETINFO = "https://api.weixin.qq.com/cgi-bin/user/info?access_token={1}&openid={2}&lang=zh_CN";
	// 粉丝备注
	public final static String WEIXIN_URL_FANS_UPDATEREMARK = "https://api.weixin.qq.com/cgi-bin/user/info/updateremark?access_token={1}";
	// 创建二维码
	public final static String WEIXIN_URL_QRCODE_CREATE = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token={1}";
	// 二维码图片
	public final static String WEIXIN_URL_QRCODE_GET = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket={1}";
	// 上传多媒体文件
	public final static String WEIXIN_URL_UPLOAD = "http://file.api.weixin.qq.com/cgi-bin/media/upload?access_token={1}&type={2}";
	// 预览接口
	public final static String WEIXIN_URL_PREVIEW = "https://api.weixin.qq.com/cgi-bin/message/mass/preview?access_token={1}";
	// 上传图文消息
	public final static String WEIXIN_URL_UPLOADNEWS = "https://api.weixin.qq.com/cgi-bin/media/uploadnews?access_token={1}";
	// 群发接口
	public final static String WEIXIN_URL_SENDALL = "https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token={1}";
}