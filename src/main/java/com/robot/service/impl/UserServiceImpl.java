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

	public User getLoginUser(UserModel user) throws UserException {
		MD5 md5 = new MD5();
		return userDao.getUserByNameAndPassWord(user.getUsername(), md5.getEncodeString(user.getPassword()));
	}

	public int save(UserModel user) throws UserException {
		int result = 0;
		try {
			// 检查用户名是否存在
			User u = userDao.getUserByName(user.getUsername());
			if (null != u) {
				result = 1;
			} else {
				u = new User();
				u.setUsername(user.getUsername());
				u.setState(1);
				MD5 md5 = new MD5();
				u.setPassword(md5.getEncodeString(user.getPassword()));
				u.setCode(userDao.getUserNewCode());
				baseDao.save(u);
			}
		} catch (Exception e) {
			throw new UserException("保存用户失败" + e.getMessage(), e);
		}
		return result;
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

	public User getUserByName(String username) {
		return userDao.getUserByName(username);
	}

	public List<Map<String, Object>> getUsersList(String username) {
		return null;
	}
}