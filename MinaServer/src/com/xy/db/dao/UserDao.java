package com.xy.db.dao;

import com.xy.common.model.user.Account;
import com.xy.common.model.user.User;

public interface UserDao {
	
	public User getUserByLogin(String login, String psw) throws Exception;
	
	public long addUser(User user) throws Exception;
	
	
	// ============ “‘œ¬account
	public long addAccount(Account account)throws Exception;
	
	public Account getAccountByUserId(long userId) throws Exception;
	
	public void updateAccount(Account account) throws Exception;
}
