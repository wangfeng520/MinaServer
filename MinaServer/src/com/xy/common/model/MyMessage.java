package com.xy.common.model;

import com.xy.common.struct.Bean;

/** �����󷵻����ݵķ�װ
 * @param <T>*/
public class MyMessage<T> extends Bean {
 
	private static final long serialVersionUID = 1L;
	private boolean ok;    // �Ƿ�����ɹ�
	private int type;		   // ������
	private T data;	   // ��������
	

	public boolean isOk() {
		return ok;
	}

	public void setOk(boolean ok) {
		this.ok = ok;
	}

	public int getType() {
		return type;
	}
	
	public void setType(int type) {
		this.type = type;
	}
	
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}	
}
