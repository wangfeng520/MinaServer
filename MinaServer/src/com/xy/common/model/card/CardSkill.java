package com.xy.common.model.card;

import com.xy.common.struct.IdBean;

public class CardSkill extends IdBean {

	private static final long serialVersionUID = 1L;
	private long id;
	private long cardId;
	private long skillId;	
	private String name;
	private String des;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getCardId() {
		return cardId;
	}

	public void setCardId(long cardId) {
		this.cardId = cardId;
	}

	public long getSkillId() {
		return skillId;
	}

	public void setSkillId(long skillId) {
		this.skillId = skillId;
	}
	
}
