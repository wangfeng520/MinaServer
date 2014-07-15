package com.xy.common.model.user;

import com.xy.common.struct.IdBean;

public class LevelExp extends IdBean {
	
	private static final long serialVersionUID = 1L;
	private long id;
	private int level;
	private int exp;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getExp() {
		return exp;
	}
	public void setExp(int exp) {
		this.exp = exp;
	}
}
