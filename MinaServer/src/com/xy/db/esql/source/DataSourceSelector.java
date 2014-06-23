package com.xy.db.esql.source;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import zuojie.esql.build.DataSourceBuilder;
import zuojie.esql.build.EsqlConfig;


public class DataSourceSelector {
	public static final String SERVER_KEY = "server";
	public static final String PORT_KEY = "port";
	public static final String NAME_KEY = "name";
	public static final String USER_KEY = "user";
	public static final String PASSWORD_KEY = "password";
	public static final String MAX_ACTIVE_KEY = "max-active";

	public static final String ORACLE_SID_KEY = "oracle.sid";

	public static final String SLAVE_COUNT_KEY = "slave.count";

	public static final String MASTER_PREFIX = "master.";
	public static final String SLAVE_PREFIX = "slave.";

	private String database; // 数据库类型
	private DataSource master; // 主数据源，支持所有类型的操作
	private List<DataSource> slaves = new ArrayList<DataSource>(); // 从数据源，只支持只读操作

	public DataSourceSelector(String database) throws Exception {
		this.database = database;

		// 读取master
		EsqlConfig config = readConfig(null);
		master = DataSourceBuilder.build(config);

	}

	public int getSlaveCount() {
		return slaves.size();
	}

	public DataSource getDataSource(Integer n) {
		return n == null ? this.master : slaves.get(n);
	}

	private EsqlConfig readConfig( Integer n) throws Exception {
		EsqlConfig config = new EsqlConfig();

		String server = "localhost";
		Integer port = 5432;
		String name = "lvshuiqiao";
		String user = "root";
		String password = "123";
		Integer max = 300;

		config.setDatabase(database);
		config.setServer(server);
		config.setPort(port);
		config.setName(name);
		config.setUser(user);
		config.setPassword(password);
		config.setMax(max);

		return config;
	}

}
