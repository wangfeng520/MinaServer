package com.xy.db.dao.city;

import java.util.List;

import com.xy.common.model.city.Fuben;
import com.xy.common.model.city.UserCity;

public interface CityDao {
	
	public List<UserCity> getUserCitys(long userId) throws Exception;
	
	public long addUserCity(UserCity userCity) throws Exception;
		
	public List<Fuben> ListFubens(Integer type) throws Exception;
	
}
