package com.robot.service;

import java.util.List;
import java.util.Map;

import com.robot.exception.UserException;
import com.robot.httpModel.vo.UserModel;

public interface UserServiceI {
	public Long save(UserModel user) throws UserException;

	public void update(UserModel user) throws UserException;

	public List<Map<String, Object>> getUsersList(String username);
}
