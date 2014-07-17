package com.xy.db.dao.city;

import java.util.List;

import zuojie.esql.util.QueryBuilder;

import com.xy.common.model.city.Fuben;
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

	@Override
	public List<Fuben> ListFubens(Integer type) throws Exception{		
		QueryBuilder qb = new QueryBuilder("select * from t_fuben where 1 = 1");
		if (type != null) qb.append(" and type = ?", type);
		qb.append(" order by id");
		
		return qb.list(esql, Fuben.class);		
	}
	
	
}
