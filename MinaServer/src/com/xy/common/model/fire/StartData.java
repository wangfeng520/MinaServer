package com.xy.common.model.fire;

/** 开始的卡牌位置等信息*/
public class StartData {
  	private int pos; // 敌我双方武将所在九宫格位置
  	private int direction;  // 阵营1下2上
  	private int level;      // 等级
  	private  String icon;    // 图标
   
  	private String name;    // 名称
  	private  int totalHp;    // 总血量
  	private  int currentHp;  // 当前血量
  	private  long chaId;      // 角色Id
  	private  long skillId;    // 技能Id
  	private  int quality;     // 品质
  	
	public int getPos() {
		return pos;
	}
	public void setPos(int pos) {
		this.pos = pos;
	}
	public int getDirection() {
		return direction;
	}
	public void setDirection(int direction) {
		this.direction = direction;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getTotalHp() {
		return totalHp;
	}
	public void setTotalHp(int totalHp) {
		this.totalHp = totalHp;
	}
	public int getCurrentHp() {
		return currentHp;
	}
	public void setCurrentHp(int currentHp) {
		this.currentHp = currentHp;
	}
	public long getChaId() {
		return chaId;
	}
	public void setChaId(long chaId) {
		this.chaId = chaId;
	}
	public long getSkillId() {
		return skillId;
	}
	public void setSkillId(long skillId) {
		this.skillId = skillId;
	}
	public int getQuality() {
		return quality;
	}
	public void setQuality(int quality) {
		this.quality = quality;
	}
}
