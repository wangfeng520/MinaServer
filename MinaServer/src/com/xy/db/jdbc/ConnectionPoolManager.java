package com.xy.db.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;

/**
 * 连接池管理者（封装数据库连接池），提供从连接池中获取连接和返还连接的方法
 */
public class ConnectionPoolManager {
	
	static Logger log = Logger.getLogger(ConnectionPoolManager.class);
	/**
	 * 初始化数据库配置信息
	 */
	public static void JDBCInit(){
		log.info("初始化数据库配置信息");
		ConnectionPool.JDBCInit();
	}
	/**
	 *从连接池中获取可用连接
	 */
	public static Connection getConnection(){
		Connection conn = ConnectionPool.getConnection();
		System.out.println("Connection pop: " + conn);
		try {
			System.out.println("Connection autocommit: " + conn.getAutoCommit());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	/**
	 * 将用完的连接返回到连接池中以待分配
	 */
	public static void pushBackConnection(Connection conn){
		System.out.println("Connection push: " + conn);
		ConnectionPool.pushConnectionBackToPool(conn);
	}
	
	
}
