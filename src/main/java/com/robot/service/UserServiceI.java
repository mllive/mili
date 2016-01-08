package com.robot.service;

import java.util.List;
import java.util.Map;

import com.robot.entity.User;
import com.robot.exception.UserException;
import com.robot.httpModel.vo.UserModel;

public interface UserServiceI {
	public int save(UserModel user) throws UserException;

	public void update(UserModel user) throws UserException;

	public User getUserByName(String username);

	public List<Map<String, Object>> getUsersList(String username);
}
