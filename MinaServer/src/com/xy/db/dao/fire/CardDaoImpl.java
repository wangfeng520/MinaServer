package com.xy.db.dao.fire;

import java.util.List;

import zuojie.esql.util.QueryBuilder;

import com.xy.common.model.card.Card;
import com.xy.common.model.card.UserCard;
import com.xy.db.esql.BaseDao;

public class CardDaoImpl extends BaseDao implements CardDao {

	@Override
	public long addCard(Card card) throws Exception {
		esql.helper().insert(card, "t_card", null, "id");
		return getGeneratedId("t_card");
	}

	@Override
	public long addUserCard(UserCard userCard) throws Exception {
		esql.helper().insert(userCard, "t_user_card", null, "id");
		return getGeneratedId("t_user_card");
	}

	@Override
	public UserCard getUserCardById(long id) throws Exception {
		return esql.helper().query(UserCard.class, "t_user_card", null, null, "id = ?", id);
	}

	@Override
	public void updateUserCard(UserCard userCard) throws Exception {
		esql.helper().update(userCard, "t_user_card", null, "id", "id = ?", userCard.getId());
	}

	@Override
	public List<UserCard> listUserCard(long userId, Boolean enable) throws Exception {
		QueryBuilder qb = new QueryBuilder("select * from t_user_card");
		QueryBuilder where = new QueryBuilder(" where  user_id = ?", userId);
		if(enable != null) where.append(" and enable = ?", enable);
		qb.append(where);
		
		return qb.list(esql, UserCard.class);
	}

}
