package com.xy.game.message.handler;


import net.sf.json.JSONObject;

import org.apache.mina.core.session.IoSession;

import zizi.ejson.JSON;

import com.xy.common.model.MyMessage;
import com.xy.common.model.User;
import com.xy.common.struct.constants.MessageType;
import com.xy.db.dao.TestDAO;
import com.xy.db.dao.UserDao;
import com.xy.db.esql.DaoManager;
import com.xy.game.manager.Managers;

/**
 * ��ɫ�����Ϣ������
 */
public class LoginHandler extends AbstractHandler{
	
	private UserDao userDao = Managers.get(DaoManager.class).getDao(UserDao.class);
	
	public LoginHandler(){}
	
	public LoginHandler(IoSession session, JSONObject message){
		super(session,message);
	}
	
	@Override
	public void handle() {
		switch(message.getInt("msgType")){
		case MessageType.LOGIN_GET:
			getRoleInfo();
			break;
		default:
			break;
		}
	}
	
	/**
	 * ��ȡ��ɫȫ����Ϣ
	 */
	public void getRoleInfo(){
		//JSONObject sendJson = new JSONObject();
		try {
		
		JSONObject sendJson = message.getJSONObject("data");
		int megType = message.getInt("msgType");
		String login = sendJson.getString("login");
		String psw = sendJson.getString("psw");
		TestDAO tDao = new TestDAO();
		User u =  tDao.getUser(login, psw);//userDao.getUser(login, psw);

		MyMessage<User> m = new MyMessage<User>();
		m.setMsgType(megType);
		if (u != null){	
			m.setOk(true);
			m.setData(u);
		}else{
			m.setOk(false);
		}
		String s = JSON.string(m);
			//sendJson = JSONObject.fromObject(u);
		session.write(s);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return;
	}
}


