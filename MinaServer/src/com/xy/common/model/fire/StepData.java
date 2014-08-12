package com.xy.common.model.fire;

import java.util.List;

/** 每一回合的信息*/
public class StepData {
	
	private long skillId; // 技能Id
	private long charId;  // 攻击者Id
	private int actionId;   // 攻击方式
	private List<EnemyData> enemyDatas; // 敌方信息
	
	public long getSkillId() {
		return skillId;
	}
	public void setSkillId(long skillId) {
		this.skillId = skillId;
	}
	public long getCharId() {
		return charId;
	}
	public void setCharId(long charId) {
		this.charId = charId;
	}
	public int getActionId() {
		return actionId;
	}
	public void setActionId(int actionId) {
		this.actionId = actionId;
	}
	public List<EnemyData> getEnemyDatas() {
		return enemyDatas;
	}
	public void setEnemyDatas(List<EnemyData> enemyDatas) {
		this.enemyDatas = enemyDatas;
	}
}
