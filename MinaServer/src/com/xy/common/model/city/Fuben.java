package com.xy.common.model.city;

import com.xy.common.struct.IdBean;

/** 游戏关卡*/
public class Fuben extends IdBean{

	private static final long serialVersionUID = 1L;
	private long id;
	private String name;
	private String icon;
	private int type; // 类型 1：普通副本 2：精英副本 3:活动副本
	private int level;
	private int quality; // 品质
	
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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

}
