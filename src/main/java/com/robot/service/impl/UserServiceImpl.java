package com.robot.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.robot.dao.BaseDaoI;
import com.robot.dao.UserDaoI;
import com.robot.entity.User;
import com.robot.exception.UserException;
import com.robot.httpModel.vo.UserModel;
import com.robot.service.UserServiceI;
import com.robot.util.MD5;

@Service("usersService")
public class UserServiceImpl implements UserServiceI {

	@Autowired
	private BaseDaoI<User> baseDao;

	@Autowired
	private UserDaoI userDao;

	public Long save(UserModel user) throws UserException {
		try {
			User u = new User();
			u.setUsername(user.getUsername());
			MD5 md5 = new MD5();
			u.setPassword(md5.getEncodeString(user.getPassword()));
			u.setCode(userDao.getUserNewCode());
			u.setCode(userDao.getUserNewCode());
			return baseDao.save(u);
		} catch (Exception e) {
			throw new UserException("保存用户失败" + e.getMessage(), e);
		}
	}

	public void update(UserModel user) throws UserException {
		try {
			User u = new User();
			BeanUtils.copyProperties(user, u);
			baseDao.update(u);
		} catch (Exception e) {
			throw new UserException("修改用户失败" + e.getMessage(), e);
		}
	}

	public List<Map<String, Object>> getUsersList(String username) {
		return null;
	}
}