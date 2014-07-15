package com.xy.common.model.card;

import com.xy.common.struct.IdBean;

public class UserCard extends IdBean {

	private static final long serialVersionUID = 1L;
	private long id;
	private long userId;
	private long cardId;
	private boolean leader; // ÊÇ·ñ¶Ó³¤
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public long getCardId() {
		return cardId;
	}
	public void setCardId(long cardId) {
		this.cardId = cardId;
	}
	public boolean isLeader() {
		return leader;
	}
	public void setLeader(boolean leader) {
		this.leader = leader;
	}
}
