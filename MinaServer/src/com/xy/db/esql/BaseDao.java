package com.xy.db.esql;

import zuojie.esql.Esql;

import com.xy.common.struct.IdBean;

/**
 * 所有数据访问对象的基类
 */
public abstract class BaseDao {
	protected Esql esql;

	protected BaseDao() {
	}

	public void setEsql(Esql esql) {
		this.esql = esql;
	}

	public long getGeneratedId(String table) throws Exception {
		String sql = "select get_generated_id(?) from dual";
		return esql.query(Long.class, sql, table);
	}

	public void setTransactionIsolation(int isolation) throws Exception {
		esql.setIsolation(isolation);
	}

	// =========================================================


	protected <T> T queryAllField(Class<T> type, String table, String where, Object... parameters) throws Exception {
		return esql.helper().query(type, table, null, null, where, parameters);
	}

	protected <T> T queryById(Class<T> type, String table, long id) throws Exception {
		return esql.helper().query(type, table, null, null, "id=?", id);
	}


	/** 帮助插入：include:null, exclude: "id" */
	protected long insertExcludeId(Object bean, String table) throws Exception {
		esql.helper().insert(bean, table, null, "id");
		return getGeneratedId(table);
	}

	/** 帮助插入:include:null, exclude: null */
	protected long insertAllField(Object bean, String table) throws Exception {
		esql.helper().insert(bean, table, null, null);
		return getGeneratedId(table);
	}
	
	/** 帮助更新:include:null, exclude:"id", where:id=bean.getId() */
	protected long updateExcludeId(IdBean bean, String table) throws Exception {
		esql.helper().update(bean, table, null, "id", "id=?", bean.getId());
		return bean.getId();
	}
	
	/** 帮助更新:include:null, exclude:"id", where:id=the id argument */
	protected long updateExcludeId(IdBean bean, String table, long id) throws Exception {
		esql.helper().update(bean, table, null, "id", "id=?", id);
		return bean.getId();
	}
	
	/** 帮助更新:include:null, exclude: null, where: id=bean.getId() */
	protected long updateAllField(IdBean bean, String table) throws Exception {
		esql.helper().update(bean, table, null, null, "id=?", bean.getId());
		return bean.getId();
	}
	
	/** 帮助更新:include:null, exclude: null, where: id=the id argument */
	protected long updateAllField(IdBean bean, String table, long id) throws Exception {
		esql.helper().update(bean, table, null, null, "id=?", id);
		return bean.getId();
	}

	/** 帮助删除 */
	protected void deleteById(String table, long id) throws Exception {
		esql.helper().delete(table, "id=?", id);
	}

	// =========================================================

	/** 只能用于单元测试中 */
	public Esql getEsql() {
		return esql;
	}
}
