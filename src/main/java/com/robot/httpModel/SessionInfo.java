package com.robot.httpModel;

import com.robot.httpModel.vo.UserModel;

/**
 * session信息模型
 */
public class SessionInfo implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserModel user;

	public UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		this.user = user;
	}

}
