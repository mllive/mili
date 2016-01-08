package com.robot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.robot.exception.UserException;
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
	public String save(UserModel user, Model model) throws UserException {
		userService.save(user);
		return "/user/save";
//		Map<String,Object> map1 = new HashMap<String,Object>();
//		  map1.put("pid", pro.getPid());
//		   
//		  ModelAndView mav=new ModelAndView("products/list",map1);
//		   
//		  return mav;
	}

	@RequestMapping(value = "/i/{username}")
	public String detail(@PathVariable String username, Model model) {
		model.addAttribute("username", username);
		return "/user/user";
	}
}
