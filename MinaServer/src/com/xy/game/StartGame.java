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
 * 游戏主线程，服务端启动程序
 */
public class StartGame {
	private static Logger log = Logger.getLogger(StartGame.class);

	public static void main(String[] args) throws Exception {
		log.info("服务端启动!");
		/** 硬盘数据库初始化配置信息 */
		ConnectionPoolManager.JDBCInit();

		/** 消息信息初始化 */
		MessageManager.init();

		// /**内存数据库初始化*/
		// System.out.println("等待内存数据库初始化...");
		// DBManager.dbInit();
		// System.out.println("ok！！！");
		Managers.addManager(new DaoManagerImpl());
		// 初始化管理器
		DaoManagerImpl dm = Managers.get(DaoManagerImpl.class);
		dm.initialize();
		

		/** TCP长连接服务，即游戏服务器（含登陆服务器） */
		MinaNetworkManager network = new MinaNetworkManager();
		network.startNetwork();

		// System.out.println(test("你好", "你好"));
		// test1();
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
