package com.xy.common.model.fire;

/** 被攻击敌方信息*/
public class EnemyData {
	
	private long enemyChaId; // 被攻击者
	private int actionId;   // 被攻击方式
	private int currentHp;
	private int totalHp;
	private int changeHp; // 血量该变量
		
	public long getEnemyChaId() {
		return enemyChaId;
	}
	public void setEnemyChaId(long enemyChaId) {
		this.enemyChaId = enemyChaId;
	}
	public int getActionId() {
		return actionId;
	}
	public void setActionId(int actionId) {
		this.actionId = actionId;
	}
	public int getCurrentHp() {
		return currentHp;
	}
	public void setCurrentHp(int currentHp) {
		this.currentHp = currentHp;
	}
	public int getTotalHp() {
		return totalHp;
	}
	public void setTotalHp(int totalHp) {
		this.totalHp = totalHp;
	}
	public int getChangeHp() {
		return changeHp;
	}
	public void setChangeHp(int changeHp) {
		this.changeHp = changeHp;
	}
}
