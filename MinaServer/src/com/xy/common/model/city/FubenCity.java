package com.xy.common.model.city;

import com.xy.common.struct.IdBean;

public class FubenCity extends IdBean {

	private static final long serialVersionUID = 1L;
	private long id;
	private long fubenId;
	private long cityId;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getFubenId() {
		return fubenId;
	}

	public void setFubenId(long fubenId) {
		this.fubenId = fubenId;
	}

	public long getCityId() {
		return cityId;
	}

	public void setCityId(long cityId) {
		this.cityId = cityId;
	}
}
