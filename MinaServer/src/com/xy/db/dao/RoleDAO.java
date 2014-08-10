package com.xy.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import net.sf.json.JSONObject;

import com.xy.common.model.RoleAttribute;
import com.xy.db.jdbc.ConnectionPoolManager;

/**
 * ��ɫ��Ϣ���Ӷ���
 */
//TODO ��ʱδ�õ�
public class RoleDAO {

	/**
	 * 	��ȡ��ɫ��Ӧ�ȼ����ԣ�������ӵ��ڴ����ݿ���
	 */
	public ArrayList<RoleAttribute> getAllRoleAttr() {
		Connection conn = ConnectionPoolManager.getConnection();
		try {
			String sql = "select * from attr_role";
			PreparedStatement preStat = conn.prepareStatement(sql);
			ArrayList<RoleAttribute> roleAttrList = new ArrayList<RoleAttribute>();
			//Tools.setPreStatementItems(preStat);
			RoleAttribute roleAttr = new RoleAttribute();
			ResultSet rs = preStat.executeQuery();
			while (rs.next()) {
				roleAttr.setId(rs.getInt("roleAttrId"));
				roleAttr.setHp(rs.getInt("hp"));
				roleAttr.setAtk(rs.getInt("atk"));
				roleAttr.setpDef(rs.getInt("pDef"));
				roleAttr.setmDef(rs.getInt("mDef"));
				roleAttr.setHit(rs.getInt("hit"));
				roleAttr.setAntiHit(rs.getInt("antiHit"));
				roleAttr.setCrt(rs.getInt("crt"));
				roleAttr.setSpeed(rs.getInt("speed"));

				roleAttrList.add(roleAttr);
				roleAttr = new RoleAttribute();
			}
			rs.close();
			preStat.close();
			return roleAttrList;
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
