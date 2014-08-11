package com.xy.common.model.city;

import com.xy.common.struct.IdBean;

public class CityCard extends IdBean {

	private static final long serialVersionUID = 1L;
	private long id;
	private long cityId;    // �ؿ�Id
	private long cardId;    // ����Id
	private int pos;    // ����λ��
	
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

	public long getCityId() {
		return cityId;
	}
	public void setCityId(long cityId) {
		this.cityId = cityId;
	}
	public int getPos() {
		return pos;
	}
	public void setPos(int pos) {
		this.pos = pos;
	}
}
