package com.xy.db.esql.esql;

import java.sql.Connection;
import java.util.List;

import javax.sql.DataSource;

import zuojie.esql.Esql;
import zuojie.esql.EsqlIterator;
import zuojie.esql.PreparedEsql;
import zuojie.esql.Rewriter;
import zuojie.esql.helper.EsqlHelper;
import zuojie.esql.parameter.ParameterFilter;
import zuojie.esql.util.EsqlTool;

public class EsqlWrap implements Esql {
	private Esql esql;


	public EsqlHelper helper() {
		return esql.helper();
	}

	public EsqlTool tool() {
		return esql.tool();
	}

	public Connection getConnection() throws Exception {
		return esql.getConnection();
	}

	public void begin(int isolation1, DataSource source1) throws Exception {
		esql.begin(isolation1, source1);
	}

	public boolean commit() throws Exception {
		boolean b = esql.commit();
		return b;
	}

	public boolean end() {
		boolean b = esql.end();
		return b;
	}

	public void setIsolation(int isolation1) throws Exception {
		esql.setIsolation(isolation1);
	}

	public void setReadOnly() throws Exception {
		esql.setReadOnly();
	}

	public <T> T query(Class<T> type, String sql, Object... parameters) throws Exception {
		try {
			return esql.query(type, sql, parameters);
		} finally {
		}
	}

	public <T> List<T> list(Class<T> type, String sql, Object... parameters) throws Exception {
		try {
			return esql.list(type, sql, parameters);
		} finally {
		}
	}

	public <T> List<T> page(Class<T> type, int offset, int limit, String sql, Object... parameters) throws Exception {
		try {
			return esql.page(type, offset, limit, sql, parameters);
		} finally {
		}
	}

	public int update(String sql, Object... parameters) throws Exception {
		try {
			return esql.update(sql, parameters);
		} finally {
		}
	}

	public <T> EsqlIterator<T> iterator(Class<T> type, String sql, Object... parameters) throws Exception {
		return esql.iterator(type, sql, parameters);
	}

	public PreparedEsql prepare(String sql) throws Exception {
		return esql.prepare(sql);
	}

	public String getTableInfoSql(String table) throws Exception {
		return esql.getTableInfoSql(table);
	}

	public void addRewriter(Rewriter rewriter) {
		esql.addRewriter(rewriter);
	}

	public void filterParameters(Object... parameters) {
		esql.filterParameters(parameters);
	}

	public void addParameterFilter(ParameterFilter filter) {
		esql.addParameterFilter(filter);
	}

	public boolean didUpdate() {
		return esql.didUpdate();
	}

}
