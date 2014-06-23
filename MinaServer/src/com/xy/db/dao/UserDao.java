package com.xy.db.dao;

import com.xy.common.model.User;

public interface UserDao {
	
	public User getUser(String login, String psw) throws Exception;
}
