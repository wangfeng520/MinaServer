package com.xy.db.dao;

import com.xy.common.model.User;
import com.xy.db.esql.BaseDao;

public class UserDaoImpl extends BaseDao implements UserDao{

	@Override
	public User getUser(String login, String psw) throws Exception {
		return esql.helper().query(User.class, "t_user", null, null, "login = ? and psw = ?",login, psw );
	}

}
