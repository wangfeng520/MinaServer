package com.xy.game.message.handler;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.mina.core.session.IoSession;

import zizi.ejson.JSON;
import zuojie.esql.Esql;

import com.xy.common.model.MyMessage;
import com.xy.common.model.user.User;
import com.xy.common.struct.constants.MessageType;
import com.xy.db.dao.UserDao;
import com.xy.db.esql.DaoManager;
import com.xy.game.manager.Managers;

/**
 * 角色相关信息处理器
 */
public class LoginHandler extends AbstractHandler {

	private Logger log = Logger.getLogger(LoginHandler.class);
	private UserDao userDao;

	public LoginHandler() {
	}

	public LoginHandler(IoSession session, JSONObject message) {
		super(session, message);
	}

	@Override
	public void initDaoEsql(Esql e) {
		userDao = Managers.get(DaoManager.class).getDao(UserDao.class, e);
	}

	@Override
	public void handle() {
		switch (message.getInt("msgType")) {
		case MessageType.LOGIN_GET:
			getRoleInfo();
			break;
		default:
			break;
		}
	}

	/**
	 * 获取角色全部信息
	 */
	public void getRoleInfo() {

		try {
			MyMessage<User> m = new MyMessage<User>();
			
			
			JSONObject data = message.getJSONObject("data");
			String login = data.getString("login");
			String psw = data.getString("psw");
			
			m.setMsgType(message.getInt("msgType"));
			User u = userDao.getUserByLogin(login, psw);

			if (u == null){
				m.setOk(false);
			}else{
				m.setOk(true);
			}
			m.setData(u);
			String s = JSON.string(m);
			
			log.info("getRoleInfo 返回数据:" + s);

			session.write(s);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return;
	}
}
