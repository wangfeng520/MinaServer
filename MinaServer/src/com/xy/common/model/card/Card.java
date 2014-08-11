package com.xy.common.model.card;

import com.xy.common.struct.IdBean;

/** ��Ϸ����*/
public class Card extends IdBean{

	private static final long serialVersionUID = 1L;
	private long id;
	private String name;
	private int level;
	private String icon;
	private int quality;  // Ʒ��
	private int actionId;  // ������ʽ
	private int totalHp; // ��Ѫ��
	private int attack;  // ������
	private int fangyu;  // ������
	private String des;  // ����

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}


	public int getActionId() {
		return actionId;
	}

	public void setActionId(int actionId) {
		this.actionId = actionId;
	}

	public int getTotalHp() {
		return totalHp;
	}

	public void setTotalHp(int totalHp) {
		this.totalHp = totalHp;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public int getFangyu() {
		return fangyu;
	}

	public void setFangyu(int fangyu) {
		this.fangyu = fangyu;
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

	public int getQuality() {
		return quality;
	}

	public void setQuality(int quality) {
		this.quality = quality;
	}
	
}
