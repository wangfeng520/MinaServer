package com.xy.common.model;

import com.xy.common.struct.Bean;

/** �����󷵻����ݵķ�װ
 * @param <T>*/
public class MyMessage<T> extends Bean {
 
	private static final long serialVersionUID = 1L;
	private boolean ok;    // �Ƿ�����ɹ�
	private int msgType;		   // ������
	private T data;	   // ��������
	

	public boolean isOk() {
		return ok;
	}

	public void setOk(boolean ok) {
		this.ok = ok;
	}


	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}

	public int getMsgType() {
		return msgType;
	}

	public void setMsgType(int msgType) {
		this.msgType = msgType;
	}	
}
