package com.xy.common.model.fire;

/** �������з���Ϣ*/
public class EnemyData {
	
	private long enemyChaId; // ��������
	private int actionId;   // ��������ʽ
	private int currentHp;
	private int totalHp;
	private int changeHp; // Ѫ���ñ���
		
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
