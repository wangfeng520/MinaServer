package com.xy.common.model.user;

import com.xy.common.struct.IdBean;

public class Account extends IdBean {

	private static final long serialVersionUID = 1L;
	private long id;
	private String name;
	private String login;
	private String psw;
	private String qq;
	

	

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}



	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPsw() {
		return psw;
	}

	public void setPsw(String psw) {
		this.psw = psw;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


}
