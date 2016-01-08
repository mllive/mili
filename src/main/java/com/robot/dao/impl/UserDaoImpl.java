package com.robot.dao.impl;

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

}
