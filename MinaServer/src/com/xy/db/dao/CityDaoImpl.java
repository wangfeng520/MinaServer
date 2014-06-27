package com.xy.db.dao;

import java.util.List;

import com.xy.common.model.city.UserCity;
import com.xy.db.esql.BaseDao;

public class CityDaoImpl extends BaseDao implements CityDao{	
	@Override
	public long addUserCity(UserCity userCity) throws Exception {
		esql.helper().insert(userCity, "t_user_city", null, "id");
		return getGeneratedId("t_user_city");
	}

	@Override
	public List<UserCity> getUserCitys(long userId) throws Exception {
		String sql = "select * from t_user_city where user_id = ? order by id";
		return esql.list(UserCity.class, sql, userId);
	}

}
