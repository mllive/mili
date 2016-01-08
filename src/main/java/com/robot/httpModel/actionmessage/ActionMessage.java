package com.robot.httpModel.actionmessage;

/**
 * JSON模型
 */
public class ActionMessage implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean success = false;// 是否成功
	private Object msg = "";// 提示信息
	private Object obj = null;// 其他信息

	public Object getMsg() {
		return msg;
	}

	public void setMsg(Object msg) {
		this.msg = msg;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

}
