package com.xy.db.dao;

import java.util.List;

import com.xy.common.model.city.UserCity;

public interface CityDao {
	
	public List<UserCity> getUserCitys(long userId) throws Exception;
	
	public long addUserCity(UserCity userCity) throws Exception;
	
}
