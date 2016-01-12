package com.robot.dao;

import com.robot.entity.User;

public interface UserDaoI {
	public Long getUserNewCode();

	public User getUserByName(String username);

	public User getUserByNameAndPassWord(String username, String password);
}
