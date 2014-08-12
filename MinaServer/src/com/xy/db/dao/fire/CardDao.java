package com.xy.db.dao.fire;

import java.util.List;

import com.xy.common.model.card.Card;
import com.xy.common.model.card.UserCard;


public interface CardDao {
		
	public long addCard(Card card) throws Exception;

	
	// ============ 以下UserCard
	public long addUserCard(UserCard userCard) throws Exception;
	
	public UserCard getUserCardById(long id) throws Exception;
	
	public void updateUserCard(UserCard userCard) throws Exception;
	
	public List<UserCard> listUserCard(long userId, Boolean enable) throws Exception;
}
