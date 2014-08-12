package com.xy.common.model.card;

import com.xy.common.struct.IdBean;

public class UserCard extends IdBean {

	private static final long serialVersionUID = 1L;
	private long id;
	private long userId;    // 用户Id
	private long cardId;    // 卡牌Id
	private boolean leader; // 是否队长
	private boolean enable; // 是否可用
	private Integer pos;    // 阵型位置
	
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
	public boolean isEnable() {
		return enable;
	}
	public void setEnable(boolean enable) {
		this.enable = enable;
	}
	public Integer getPos() {
		return pos;
	}
	public void setPos(Integer pos) {
		this.pos = pos;
	}
}
