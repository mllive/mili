package com.robot.controller;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.robot.entity.User;
import com.robot.exception.UserException;
import com.robot.httpModel.SessionInfo;
import com.robot.httpModel.actionmessage.ActionMessage;
import com.robot.httpModel.vo.UserModel;
import com.robot.service.UserServiceI;
import com.robot.util.ResourceUtil;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

	@Autowired
	private UserServiceI userService;

	@RequestMapping(value = "/new")
	public String add() throws UserException {
		return "/user/save";
	}

	@RequestMapping(value = "/tologin")
	public String tologin() throws UserException {
		return "/user/login";
	}

	@RequestMapping(value = "/login")
	@ResponseBody
	public ActionMessage login(UserModel user, HttpSession session) throws UserException {
		User loginUser = userService.getLoginUser(user);
		ActionMessage msg = new ActionMessage();
		msg.setSuccess(true);
		if (null == loginUser) {
			msg.setMsg("1");
		} else {
			SessionInfo sessionInfo = new SessionInfo();
			sessionInfo.setUser(loginUser);
			session.setAttribute(ResourceUtil.getSessionInfoName(), sessionInfo);
			msg.setMsg("0");
		}
		msg.setObj(user);
		return msg;
	}

	@RequestMapping(value = "/reg")
	@ResponseBody
	public ActionMessage reg(UserModel user, HttpSession session) throws UserException, IllegalAccessException, InvocationTargetException {
		int result = userService.save(user);
		SessionInfo sessionInfo = new SessionInfo();
		User u = new User();
		BeanUtils.copyProperties(u, user);
		sessionInfo.setUser(u);
		session.setAttribute(ResourceUtil.getSessionInfoName(), sessionInfo);
		ActionMessage msg = new ActionMessage();
		msg.setSuccess(true);
		msg.setMsg(result);
		msg.setObj(user);
		return msg;
	}

	@RequestMapping(value = "/i/{username}")
	public String i(@PathVariable String username, Model model) {
		User user = userService.getUserByName(username);
		model.addAttribute("user", user);
		return "/user/user";
	}
}
