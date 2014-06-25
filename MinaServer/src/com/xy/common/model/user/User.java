package com.xy.common.model.user;

import com.xy.common.struct.IdBean;

public class User extends IdBean {

	private static final long serialVersionUID = 1L;
	private long id;
	private String name;  // 玩家昵称
	private int level;	  // 当前等级
	private int exp;	  // 当前经验
 	private int maxexp;   // 当前等级最大经验值
	private int gold;     // 金币
	private int silver;	  // 银币
	private int power;    // 体力
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
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
	public int getExp() {
		return exp;
	}
	public void setExp(int exp) {
		this.exp = exp;
	}
	public int getMaxexp() {
		return maxexp;
	}
	public void setMaxexp(int maxexp) {
		this.maxexp = maxexp;
	}
	public int getGold() {
		return gold;
	}
	public void setGold(int gold) {
		this.gold = gold;
	}
	public int getSilver() {
		return silver;
	}
	public void setSilver(int silver) {
		this.silver = silver;
	}
	public int getPower() {
		return power;
	}
	public void setPower(int power) {
		this.power = power;
	}
}
