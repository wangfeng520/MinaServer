package com.xy.db.dao;

//TODO ‘› ±Œ¥”√µΩ
public class NullSQLHandleException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public NullSQLHandleException(){
		super();
	}
	
	public NullSQLHandleException(String message){
		super(message);
	}
}
