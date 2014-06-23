package com.xy.common.model;

import com.xy.common.struct.IdBean;

public class LoginInfo extends IdBean {

	private static final long serialVersionUID = 1L;
	private long id;
	private String name;
	private String psw; // √‹¬Î
	

	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public String getPsw() {
		return psw;
	}

	public void setPsw(String psw) {
		this.psw = psw;
	}

}
