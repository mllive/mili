package com.robot.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.robot.httpModel.SessionInfo;
import com.robot.util.HttpRequestDeviceUtils;
import com.robot.util.IpUtil;
import com.robot.util.RequestUtil;
import com.robot.util.ResourceUtil;

/**
 * 权限拦截器
 */
public class AuthInterceptor implements HandlerInterceptor {

	private static final Logger logger = Logger.getLogger(AuthInterceptor.class);

	/**
	 * 完成页面的render后调用
	 */
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object,
			Exception exception) throws Exception {
	}

	/**
	 * 在调用controller具体方法后拦截
	 */
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object,
			ModelAndView modelAndView) throws Exception {

	}

	/**
	 * 在调用controller具体方法前拦截
	 */
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {

		String contextPath = request.getContextPath();// 去掉项目路径
		int contextPort = request.getServerPort();
		String requestPath = RequestUtil.getRequestPath(request);// 用户访问的资源地址
		boolean isMobile = HttpRequestDeviceUtils.isMobileDevice(request);

		// IP+访问地址
		logger.info(IpUtil.getIpAddr(request) + ":" + contextPort + contextPath + requestPath);

		// 登录无需验证
		if (requestPath.startsWith("/resources")) {
			return true;
		}
		if (requestPath.startsWith("/user/reg")) {
			return true;
		}
		if (requestPath.startsWith("/user/tologin")) {
			return true;
		}
		if (requestPath.startsWith("/user/login")) {
			return true;
		}

		SessionInfo sessionInfo = (SessionInfo) request.getSession().getAttribute(ResourceUtil.getSessionInfoName());
		if (sessionInfo == null) {// 没有登录系统，或登录超时
			request.getRequestDispatcher("/user/tologin").forward(request, response);
			return false;
		}
		return true;
	}
}
