package com.xy.game.message.handler;


import net.sf.json.JSONObject;

import org.apache.mina.core.session.IoSession;

import zizi.ejson.JSON;
import zuojie.esql.Esql;

import com.xy.common.model.MyMessage;
import com.xy.common.model.User;
import com.xy.common.struct.constants.MessageType;
import com.xy.db.dao.UserDao;
import com.xy.db.esql.DaoManager;
import com.xy.game.manager.Managers;

/**
 * ��ɫ�����Ϣ������
 */
public class RoleHandler extends AbstractHandler{
	
	private UserDao userDao ;
	
	public RoleHandler(){}
	
	public RoleHandler(IoSession session, JSONObject message){
		super(session,message);
	}
	
	@Override
	public void initDaoEsql(Esql e) {
		userDao = Managers.get(DaoManager.class).getDao(UserDao.class, e);
	}
	
	@Override
	public void handle() {
		switch(message.getInt("msgType")){
		case MessageType.ROLE_GET:
			try {
				getRoleInfo();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		default:
			break;
		}
	}
	
	/**
	 * ��ȡ��ɫȫ����Ϣ
	 * @throws Exception 
	 */
	public void getRoleInfo() throws Exception{
		//JSONObject sendJson = new JSONObject();
		String login = message.getString("login");
		String psw = message.getString("psw");
		User user = userDao.getUser(login, psw);
		MyMessage<User> m = new MyMessage<User>();
		m.setOk(true);
		m.setMsgType(1);
		m.setData(user);
		String s = JSON.string(m);
			//sendJson = JSONObject.fromObject(u);
		session.write(s);
			
		
		return;
	}
}


