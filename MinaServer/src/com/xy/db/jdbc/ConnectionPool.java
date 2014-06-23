package com.xy.db.jdbc;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Properties;

import org.apache.log4j.Logger;


import com.xy.common.Tools;


/**
 *JDBC���ӳػ��ƣ��ṩ��ȡ���Ӻͷ������ӵķ���
 */
public class ConnectionPool {
	
	/**��������*/
	private static LinkedList<Connection> m_notUsedConnection = new LinkedList<Connection>();
	
	/**�Ѿ�ʹ�õ�����*/
	private static HashSet<Connection> m_usedConnection = new HashSet<Connection>();
	
	/**�����ļ�ʵ��*/
	private static Tools utility;
	
	/**���ݿ�url*/
	private static String sql_url = null;
	
	/**���ݿ��û�����������Ϣ*/
	private static Properties connInfo;
	
	/**���������*/
	private static int MAX_CONNECTION = 4;
	
	/**�����־*/
	static final boolean DEBUG = false;
	
	/**����������ӳص�ʱ��*/
	static private long m_lastClearm_lastClearClosedConnection = System.currentTimeMillis();
	
	/**������ӹرյ�ʱ����Ϊ5��*/
	public static long CHECK_CLOSED_CONNECTION_TIME = 5000;
	
	private static Logger log = Logger.getLogger(ConnectionPool.class);
	
	static {
        try {
            initDriver();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
	
	
	
	/**
	 * ��ʼ��JDBC����
	 */
	private static void initDriver() throws InstantiationException,IllegalAccessException, ClassNotFoundException {
		Driver driver = (Driver) Class.forName("org.postgresql.Driver").newInstance();
		try {
			DriverManager.registerDriver(driver);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ��ʼ��JDBC��������Ϣ
	 */
	public static void JDBCInit(){
		log.info("��ʼ��JDBC��������Ϣ");
		utility = new Tools();
		connInfo = new Properties();
		utility.getProperties("resource" + java.io.File.separator + "system.ini");
		sql_url = utility.getStringFromProperty("jdbc_url");
		connInfo.put("user", utility.getStringFromProperty("jdbc_user"));
		connInfo.put("password", utility.getStringFromProperty("jdbc_pwd"));
	
		/**���ӳس�ʼͶ��������ӻ��壬�ӿ��һ�η������Ӧ�ٶ�*/
		for(int i = 0; i < 10; i++){
			try {
				Connection conn = DriverManager.getConnection(sql_url,connInfo);
				m_notUsedConnection.addLast(conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * ��������
	 */
	public synchronized static Connection getConnection() {
		log.info("��������");
		// �ر�������������
		clearClosedConnection();

		// �����ǰ��������
		if(DEBUG)
			System.out.println("��ǰ����������" + getConnectionCount());

		// Ѱ�ҿ��е�����
		while (m_notUsedConnection.size() > 0) {
			try {
				Connection con = (Connection) m_notUsedConnection.removeFirst();

				if (con.isClosed()) {
					continue;
				}

				m_usedConnection.add(con);
				if (DEBUG) {
					// System.out.println("���ӳ�ʼ���ɹ�");
				}
				return con;
			} catch (SQLException e) {
			}
		}

		// û���ҵ�������һЩ�µ������Թ�ʹ��
		int newCount = getIncreasingConnectionCount();
		LinkedList<Connection> list = new LinkedList<Connection>();
		Connection con = null;

		for (int i = 0; i < newCount; i++) {
			con = getNewConnection();
			if (con != null) {
				list.add(con);
			}
		}

		// û�гɹ��������ӣ�����ʧ��
		if (list.size() == 0)
			return null;

		// �ɹ��������ӣ�ʹ�õļ���used���У�ʣ�µļ���notUsed����
		con = (Connection) list.removeFirst();
		m_usedConnection.add(con);
		m_notUsedConnection.addAll(list);
		list.clear();

		return con;
	}

	/**
	 * ����������
	 */
	public static Connection getNewConnection() {
		try {
			Connection con = DriverManager.getConnection(sql_url,connInfo);
			return con;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * ��ʹ��������ӷŻص����ӳ���
	 */
	static synchronized void pushConnectionBackToPool(Connection con) {
		boolean exist = m_usedConnection.remove(con);
		if (exist) {
			m_notUsedConnection.addLast(con);
		}
	}
	
	/**
	 * �ر���������
	 */
	public static int close() {
		int count = 0;

		Iterator<Connection> iterator = m_notUsedConnection.iterator();
		while (iterator.hasNext()) {
			try {
				((Connection) iterator.next()).close();
				count++;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		m_notUsedConnection.clear();

		iterator = m_usedConnection.iterator();
		while (iterator.hasNext()) {
			try {
				((Connection) iterator.next()).close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		m_usedConnection.clear();

		return count;
	}
	
	/**
	 * ����Ѿ��رյ�����
	 */
	private static void clearClosedConnection(){
		long time = System.currentTimeMillis();

		/**ʱ�����*/
		if(time < m_lastClearm_lastClearClosedConnection){
			return;
		}

		/**ʱ����ڼ��ʱ����*/
		if(time - m_lastClearm_lastClearClosedConnection < CHECK_CLOSED_CONNECTION_TIME){
			return;
		}

		m_lastClearm_lastClearClosedConnection = time;

		Iterator<Connection> iterator = m_notUsedConnection.iterator();

		while(iterator.hasNext()){
			Connection conn = (Connection)iterator.next();

			try{
				if(conn.isClosed())
					iterator.remove();
			}catch(SQLException e){
				iterator.remove();
				//e.printStackTrace();
				if(DEBUG){
					System.out.println("���������⣬�ѶϿ�");
				}
			}
		}

		// ��������Connection
		int decrease = getDecreasingConnectionCount();

		while (decrease > 0 && m_notUsedConnection.size() > 0) {
			Connection con = (Connection) m_notUsedConnection.removeFirst();

			try {
				con.close();
			} catch (SQLException e) {

			}

			decrease--;
		}
	}

	/**
	 * ������Ҫ���ӵ���������
	 */
	public static int getIncreasingConnectionCount() {
		int count = 1;
		count = getConnectionCount() / 4;

		if (count < 1)
			count = 1;

		return count;
	}

	/**
	 * ������Ҫ�������������
	 */
	public static int getDecreasingConnectionCount() {
		int count = 0;

		if (getConnectionCount() > MAX_CONNECTION) {
			count = getConnectionCount() - MAX_CONNECTION;
		}
		
		return count;
	}

	/**
	 * ���ػ�û��ʹ�õ��������������ڷ���
	 */
	public static synchronized int getNotUsedConnectionCount() {
		return m_notUsedConnection.size();
	}

	/**
	 * �����Ѿ�ʹ�õ���������
	 */
	public static synchronized int getUsedConnectionCount() {
		return m_usedConnection.size();
	}

	/**
	 * ����ȫ����������
	 */
	public static synchronized int getConnectionCount() {
		return m_notUsedConnection.size() + m_usedConnection.size();
	}

}

