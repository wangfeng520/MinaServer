package com.xy.db.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;

/**
 * ���ӳع����ߣ���װ���ݿ����ӳأ����ṩ�����ӳ��л�ȡ���Ӻͷ������ӵķ���
 */
public class ConnectionPoolManager {
	
	static Logger log = Logger.getLogger(ConnectionPoolManager.class);
	/**
	 * ��ʼ�����ݿ�������Ϣ
	 */
	public static void JDBCInit(){
		log.info("��ʼ�����ݿ�������Ϣ");
		ConnectionPool.JDBCInit();
	}
	/**
	 *�����ӳ��л�ȡ��������
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
	 * ����������ӷ��ص����ӳ����Դ�����
	 */
	public static void pushBackConnection(Connection conn){
		System.out.println("Connection push: " + conn);
		ConnectionPool.pushConnectionBackToPool(conn);
	}
	
	
}
