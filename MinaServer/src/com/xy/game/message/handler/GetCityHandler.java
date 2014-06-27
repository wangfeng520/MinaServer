package com.xy.game.message.handler;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.mina.core.session.IoSession;

import zizi.ejson.JSON;
import zuojie.esql.Esql;

import com.xy.common.model.MyMessage;
import com.xy.common.model.city.UserCity;
import com.xy.common.model.city.UserCityInfo;
import com.xy.common.model.user.User;
import com.xy.common.struct.constants.MessageType;
import com.xy.db.dao.CityDao;
import com.xy.db.esql.DaoManager;
import com.xy.game.manager.Managers;

/**
 * 角色相关信息处理器
 */
public class GetCityHandler extends AbstractHandler {

	private Logger log = Logger.getLogger(GetCityHandler.class);
	private CityDao cityDao;
	

	public GetCityHandler() {
	}

	public GetCityHandler(IoSession session, JSONObject message) {
		super(session, message);
	}

	@Override
	public void initDaoEsql(Esql e) {
		cityDao = Managers.get(DaoManager.class).getDao(CityDao.class, e);
	}

	@Override
	public void handle() {
		switch (message.getInt("msgType")) {
		case MessageType.CITY_GIT:
			getUserCityInfo();
			break;
		default:
			break;
		}
	}

	/**
	 * 获取角色全部信息
	 */
	public void getUserCityInfo() {

		try {
			MyMessage<UserCityInfo> m = new MyMessage<UserCityInfo>();
						
			JSONObject data = message.getJSONObject("data");
			long userId = data.getLong("userId");			
			m.setMsgType(message.getInt("msgType"));
		
			
			List<UserCity> citys =cityDao.getUserCitys(userId);
			UserCityInfo uci = new UserCityInfo(userId);
			uci.setUserCitylists(citys);

			if (citys.isEmpty()){
				m.setOk(false);
			}else{
				m.setOk(true);
			}
			m.setData(uci);
			String s = JSON.string(m);
			
			log.info("getUserCityInfo 返回数据:" + s);

			session.write(s);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return;
	}
}
