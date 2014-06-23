package com.xy.db.esql;

import com.xy.game.manager.Manager;

import zuojie.esql.Esql;


public interface DaoManager  extends Manager{
	public <T> T getDao(Class<T> type);

	public Esql getEsql();

	/** 开始事务, 指定隔离度与数据源类型 */
	public void begin(int isolation, boolean master) throws Exception;

	/** 开始事务, 指定隔离度, 默认主数据源 */
	public void begin(int isolation) throws Exception;

	/** 开始事务，使用默认隔离度, 默认主数据源(读已提交) */
	public void begin() throws Exception;

	/** 提交当前事务 */
	public void commit() throws Exception;

	/** 结束当前事务。如果没有提交，就回滚 */
	public void end();
}
