package com.xy.common.model.info;

import java.util.List;

import com.xy.common.model.city.Fuben;

public class UserFubenInfo{

	private long userId;
	private int type;  // 副本类别:精英副本、普通副本、活动副本
	private List<Fuben> fubens;  
	
	public UserFubenInfo(long userId){
		this.userId = userId;
	}
		
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public List<Fuben> getFubens() {
		return fubens;
	}

	public void setFubens(List<Fuben> fubens) {
		this.fubens = fubens;
	}
}
