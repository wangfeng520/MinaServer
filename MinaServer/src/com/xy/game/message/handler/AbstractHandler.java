package com.xy.game.message.handler;

import net.sf.json.JSONObject;

import org.apache.mina.core.session.IoSession;

/**
 * �����Ự����Ϣ�������������ĳ�����Ϣ������
 */
public abstract class AbstractHandler {
	/**�Ự*/
	public IoSession session;
	
	/**��Ϣ*/
	public JSONObject message;
	
	public AbstractHandler(){}
	
	public AbstractHandler(IoSession session, JSONObject message){
		this.session = session;
		this.message = message;
	}
	
	public abstract void handle();
}
