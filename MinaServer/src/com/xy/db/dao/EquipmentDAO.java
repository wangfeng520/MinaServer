package com.xy.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.xy.common.Tools;
import com.xy.db.jdbc.ConnectionPoolManager;

/**
 * װ���������Ӷ��� 
 */
// TODO ��ʱδ�õ�
public class EquipmentDAO {
	/**
	 * װ������
	 * @return �����ɹ�����0��ʧ�ܷ���-1��װ���ȼ��Ѵﵽ����ȼ�������ǿ��������-2����Ǯ���㷵��-3,װ����������-4
	 */
	public int upgradeEquipment(int roleId, int equipAttrId, int cost){
		/**��������*/
		if(equipAttrId % 100 % 10 == 0){
			return -4;
		}

		Connection conn = ConnectionPoolManager.getConnection();
		try {
			String sql_1 = "select roleAttrId,gold from role_info where roleId = ?";
			PreparedStatement preStat_1 = conn.prepareStatement(sql_1);
			Tools.setPreStatementItems(preStat_1, roleId);
			ResultSet rs_1 = preStat_1.executeQuery();
			if(!rs_1.next()){
				rs_1.close();
				preStat_1.close();
				return -1;
			}
			if((rs_1.getInt("roleAttrId") % 1000) <= (equipAttrId % 100) ){
				rs_1.close();
				preStat_1.close();
				return -2;
			}
			if(rs_1.getInt("gold") < cost){
				rs_1.close();
				preStat_1.close();
				return -3;
			}
			
			conn.setAutoCommit(false);
			sql_1 = "update role_equips set equipAttrId = equipAttrId + 1 where equipAttrId = ? and roleId = ?";
			preStat_1 = conn.prepareStatement(sql_1);
			Tools.setPreStatementItems(preStat_1, equipAttrId, roleId);
			String sql_2 = "update role_info set gold = gold - ? where roleId = ?";
			PreparedStatement preStat_2 = conn.prepareStatement(sql_2);
			Tools.setPreStatementItems(preStat_2, cost, roleId);
			try{
				if((preStat_2.executeUpdate() != 0) && (preStat_1.executeUpdate() != 0)){
					conn.commit();
					conn.setAutoCommit(true);
					preStat_1.close();
					preStat_2.close();
					return 0;
				}
				throw new NullSQLHandleException();
			}catch(NullSQLHandleException ex){
				try {
					if(!conn.getAutoCommit() && null != conn){
						conn.rollback();
						conn.commit();
						conn.setAutoCommit(true);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				ex.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			ConnectionPoolManager.pushBackConnection(conn);
		}
		return -1;
	}
}
