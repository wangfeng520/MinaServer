package com.xy.common.model.city;

import com.xy.common.struct.IdBean;

public class UserCity extends IdBean {

	private static final long serialVersionUID = 1L;
	private long id;
	private long userId;
	private long cityId;
	private int star;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public long getCityId() {
		return cityId;
	}
	public void setCityId(long cityId) {
		this.cityId = cityId;
	}
	public int getStar() {
		return star;
	}
	public void setStar(int star) {
		this.star = star;
	}
}
