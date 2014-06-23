package com.xy.common.model;

import com.xy.common.struct.Bean;

/** 对请求返回数据的封装
 * @param <T>*/
public class MyMessage<T> extends Bean {
 
	private static final long serialVersionUID = 1L;
	private boolean ok;    // 是否请求成功
	private int type;		   // 请求标记
	private T data;	   // 返回数据
	

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
