package com.xy.common.model.fire;

import java.util.List;


public class FireDatas{

	private boolean result; // ս�����
	private int star;  // ս������
	
	private List<StartData> startDatas;  // ��ʼ��ʼ����
	private List<StepData> stepDatas;   // ÿ�غ�����
	
	public boolean isResult() {
		return result;
	}
	public void setResult(boolean result) {
		this.result = result;
	}
	public int getStar() {
		return star;
	}
	public void setStar(int star) {
		this.star = star;
	}
	public List<StartData> getStartDatas() {
		return startDatas;
	}
	public void setStartDatas(List<StartData> startDatas) {
		this.startDatas = startDatas;
	}
	public List<StepData> getStepDatas() {
		return stepDatas;
	}
	public void setStepDatas(List<StepData> stepDatas) {
		this.stepDatas = stepDatas;
	}		
}
