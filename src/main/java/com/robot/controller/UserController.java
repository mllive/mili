package com.robot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.robot.exception.UserException;
import com.robot.httpModel.actionmessage.ActionMessage;
import com.robot.httpModel.vo.UserModel;
import com.robot.service.UserServiceI;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

	@Autowired
	private UserServiceI userService;

	@RequestMapping(value = "/new")
	public String add() throws UserException {
		return "/user/save";
	}

	@RequestMapping(value = "/save")
	@ResponseBody
	public ActionMessage save(UserModel user, Model model) throws UserException {
		int result = userService.save(user);
		ActionMessage msg = new ActionMessage();
		msg.setSuccess(true);
		msg.setMsg(result);
		msg.setObj(user);
		return msg;
	}

	@RequestMapping(value = "/i/{username}")
	public String detail(@PathVariable String username, Model model) {
		model.addAttribute("username", username);
		return "/user/user";
	}
}
