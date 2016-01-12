package com.robot.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ParameterMode;

import org.hibernate.procedure.ProcedureCall;
import org.springframework.stereotype.Repository;

import com.robot.dao.UserDaoI;
import com.robot.entity.User;

@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDaoI {

	public Long getUserNewCode() {
		ProcedureCall procedureCall = this.getCurrentSession().createStoredProcedureCall("getusernewcode");
		procedureCall.registerParameter("newcode", Long.class, ParameterMode.INOUT).bindValue(0l);
		Long newcode = (Long) procedureCall.getOutputs().getOutputParameterValue("newcode");
		return newcode;
	}

	public User getUserByName(String username) {
		List<Object> param = new ArrayList<Object>();
		param.add(username);
		List<User> userList = this.find(" from users where username=?", param);
		if (null != userList && userList.size() > 0)
			return userList.get(0);
		else
			return null;
	}

	public User getUserByNameAndPassWord(String username, String password) {
		List<Object> param = new ArrayList<Object>();
		param.add(username);
		param.add(password);
		List<User> userList = this.find(" from users where username=? and password=?", param);
		if (null != userList && userList.size() > 0)
			return userList.get(0);
		else
			return null;
	}
}
