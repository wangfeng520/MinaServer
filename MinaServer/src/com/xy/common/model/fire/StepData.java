package com.xy.common.model.fire;

import java.util.List;

/** ÿһ�غϵ���Ϣ*/
public class StepData {
	
	private long skillId; // ����Id
	private long charId;  // ������Id
	private int actionId;   // ������ʽ
	private List<EnemyData> enemyDatas; // �з���Ϣ
	
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
