package com.xy.common.model.city;

import com.xy.common.struct.IdBean;

/** 游戏关卡*/
public class City extends IdBean{

	private static final long serialVersionUID = 1L;
	private long id;
	private String name;
	private String icon;
	private int level;
	private int quality; // 品质,难度
	private int tili;    // 消耗体力
	private int exp;	 // 通关奖励经验
	private int coin;	 // 通关奖励金币
	private String des; //描述
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getQuality() {
		return quality;
	}

	public void setQuality(int quality) {
		this.quality = quality;
	}

	public int getTili() {
		return tili;
	}

	public void setTili(int tili) {
		this.tili = tili;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public int getCoin() {
		return coin;
	}

	public void setCoin(int coin) {
		this.coin = coin;
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

}
