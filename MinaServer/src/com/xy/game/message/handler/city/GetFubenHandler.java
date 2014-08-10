package com.xy.game.message.handler.city;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.mina.core.session.IoSession;

import zuojie.esql.Esql;

import com.xy.common.model.MyMessage;
import com.xy.common.model.city.Fuben;
import com.xy.common.model.info.UserFubenInfo;
import com.xy.common.struct.constants.MessageType;
import com.xy.db.dao.city.CityDao;
import com.xy.db.esql.DaoManager;
import com.xy.game.manager.Managers;
import com.xy.game.message.handler.AbstractHandler;

/**
 * ���������Ϣ������
 */
public class GetFubenHandler extends AbstractHandler {

	private Logger log = Logger.getLogger(GetFubenHandler.class);
	private CityDao cityDao;
	

	public GetFubenHandler() {
		
	}

	public GetFubenHandler(IoSession session, JSONObject message) {
		super(session, message);
	}

	@Override
	public void initDaoEsql(Esql e) {
		cityDao = Managers.get(DaoManager.class).getDao(CityDao.class, e);
	}

	@Override
	public void handle() {
		switch (message.getInt("msgType")) {
		case MessageType.CITY_FUBEN:
			getUserFuben();
			break;
		default:
			break;
		}
	}

	/**
	 * ��ȡ��ɫȫ����Ϣ
	 */
	public void getUserFuben() {

		try {
			MyMessage<UserFubenInfo> m = new MyMessage<UserFubenInfo>();
						
			JSONObject data = message.getJSONObject("data");
			long userId = data.getLong("userId");	
			int type = data.getInt("type");
			m.setMsgType(message.getInt("msgType"));
		
			
			List<Fuben> fubens =cityDao.ListFubens(type);
			UserFubenInfo ufi = new UserFubenInfo(userId);
			ufi.setFubens(fubens);

			if (fubens.isEmpty()){
				m.setOk(false);
			}else{
				m.setOk(true);
			}
			m.setData(ufi);
			
			JSONObject json = JSONObject.fromObject(m);
			String s = json.toString();
			
			
			log.info("getUserFuben ��������:" + s);

			session.write(s);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return;
	}
}
