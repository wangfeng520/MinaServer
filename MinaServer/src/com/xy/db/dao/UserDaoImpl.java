package com.xy.db.dao;

import zuojie.esql.util.QueryBuilder;

import com.xy.common.model.user.Account;
import com.xy.common.model.user.User;
import com.xy.db.esql.BaseDao;

public class UserDaoImpl extends BaseDao implements UserDao{

	@Override
	public User getUserByLogin(String login, String psw) throws Exception {
		QueryBuilder select = new QueryBuilder("select u.*");
		QueryBuilder from = new QueryBuilder(" from t_user u, t_account a");
		QueryBuilder where = new QueryBuilder(" where u.id = a.user_id");
		
		where.append(" and a.login = ?", login);
		where.append(" and a.psw = ?", psw);
		select.append(from).append(where);
		
		return select.query(esql, User.class);
	}

	@Override
	public long addUser(User user) throws Exception {
		esql.helper().insert(user, "t_user", null, "id");
		return getGeneratedId("t_user");
	}

	@Override
	public long addAccount(Account account) throws Exception {
		esql.helper().insert(account, "t_account", null, "id");
		return getGeneratedId("t_account");
	}

	@Override
	public Account getAccountByUserId(long userId) throws Exception {
		return esql.helper().query(Account.class, "t_account", null	, null, "user_id = ?", userId);
	}
	
	@Override
	public void updateAccount(Account account) throws Exception {
		esql.helper().update(account, "t_account", null, "id", "id = ?", account.getId());	
	}


}
