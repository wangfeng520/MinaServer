package com.xy.common.model.fire;

/** ��ʼ�Ŀ���λ�õ���Ϣ*/
public class StartData {
  	private int pos; // ����˫���佫���ھŹ���λ��
  	private int direction;  // ��Ӫ1��2��
  	private int level;      // �ȼ�
  	private  String icon;    // ͼ��
   
  	private String name;    // ����
  	private  int totalHp;    // ��Ѫ��
  	private  int currentHp;  // ��ǰѪ��
  	private  long chaId;      // ��ɫId
  	private  long skillId;    // ����Id
  	private  int quality;     // Ʒ��
  	
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
