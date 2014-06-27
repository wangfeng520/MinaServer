package com.xy.common.model.city;

import java.util.List;

public class UserCityInfo{

	private long userId;
	private List<UserCity> userCitylists;  
	
	public UserCityInfo(long userId){
		this.userId = userId;
	}
	
	
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public List<UserCity> getUserCitylists() {
		return userCitylists;
	}
	public void setUserCitylists(List<UserCity> userCitylists) {
		this.userCitylists = userCitylists;
	}
}
