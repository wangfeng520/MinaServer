package com.xy.game;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import zuojie.esql.Esql;
import zuojie.esql.build.EsqlBuilder;

import com.xy.common.Tools;
import com.xy.db.esql.DaoManagerImpl;
import com.xy.db.jdbc.ConnectionPoolManager;
import com.xy.game.manager.Managers;
import com.xy.game.message.MessageManager;
import com.xy.game.message.handler.LoginHandler;
import com.xy.net.mina.service.MinaNetworkManager;

/**
 * ��Ϸ���̣߳��������������
 */
public class StartGame {
	private static Logger log = Logger.getLogger(StartGame.class);

	public static void main(String[] args) throws Exception {
		log.info("���������!");
		/** Ӳ�����ݿ��ʼ��������Ϣ */
		ConnectionPoolManager.JDBCInit();

		/** ��Ϣ��Ϣ��ʼ�� */
		MessageManager.init();

		// /**�ڴ����ݿ��ʼ��*/
		// System.out.println("�ȴ��ڴ����ݿ��ʼ��...");
		// DBManager.dbInit();
		// System.out.println("ok������");
		Managers.addManager(new DaoManagerImpl());
		// ��ʼ��������
		DaoManagerImpl dm = Managers.get(DaoManagerImpl.class);
		dm.initialize();
		

		/** TCP�����ӷ��񣬼���Ϸ������������½�������� */
		MinaNetworkManager network = new MinaNetworkManager();
		network.startNetwork();

		// System.out.println(test("���", "���"));
		test1();
	}

	
	public static void test1(){
		LoginHandler handler = new LoginHandler();
		try {
			Esql esql = EsqlBuilder.build(Esql.POSTGRESQL);
			try {
				handler.initDaoEsql(esql);
				handler.getRoleInfo();
				esql.commit();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				esql.end();
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	public static int test(String playerName, String password) {
		try {
			Connection conn = ConnectionPoolManager.getConnection();
			String sql = "select * from players where playerName = ?";
			PreparedStatement preStat = conn.prepareStatement(sql);
			Tools.setPreStatementItems(preStat, playerName);
			ResultSet rs = preStat.executeQuery();
			if (rs.next()) {
				rs.close();
				preStat.close();
				ConnectionPoolManager.pushBackConnection(conn);
				return 1;
			}
			sql = new String(
					"insert into players(playerName, password) values(?,?)");
			preStat = conn.prepareStatement(sql);
			Tools.setPreStatementItems(preStat, playerName, password);
			if (preStat.executeUpdate() != 0) {
				preStat.close();
				ConnectionPoolManager.pushBackConnection(conn);
				return 0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 2;
	}
}
