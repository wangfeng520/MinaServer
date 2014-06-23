package com.xy.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import net.sf.json.JSONObject;

import com.xy.common.Tools;
import com.xy.common.model.User;
import com.xy.db.jdbc.ConnectionPoolManager;

/**
 * 角色信息连接对象
 */
public class TestDAO {

	/**
	 * 	获取角色相应等级属性，用于添加到内存数据库中
	 */
	public User getUser(String login, String psw) {
		Connection conn = ConnectionPoolManager.getConnection();
		try {
			String sql = "select * from t_user where login = ? and psw = ?";
			PreparedStatement preStat = conn.prepareStatement(sql);
			Tools.setPreStatementItems(preStat,login, psw);
			User u = null;
			ResultSet rs = preStat.executeQuery();
			while (rs.next()) {
				u = new User();
				u.setId(rs.getLong("id"));
				u.setLogin(rs.getString("login"));
				u.setQq(rs.getString("qq"));
				u.setPsw(rs.getString("psw"));
			}
			rs.close();
			preStat.close();
			return u;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPoolManager.pushBackConnection(conn);
		}
		return null;
	}

	public JSONObject getRoleInformation() {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("name", "kevin");
		jsonObject.put("Max.score", new Integer(100));
		jsonObject.put("Min.score", new Integer(50));
		jsonObject.put("nickname", "picglet");
		return jsonObject;
	}
}
