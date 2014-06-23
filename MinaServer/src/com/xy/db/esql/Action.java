package com.xy.db.esql;

import java.io.Serializable;

public class Action implements Serializable {
	private static final long serialVersionUID = 1L;

	public final static int TRANSACTION_NONE = 0; // =
													// Connection.TRANSACTION_NONE
	public final static int TRANSACTION_READ_COMMITTED = 2; // =
															// Connection.TRANSACTION_READ_COMMITTED
	public final static int TRANSACTION_SERIALIZABLE = 8; // =
															// Connection.TRANSACTION_SERIALIZABLE

	/** 只读操作 */
	protected static final int LV_READ = 0;
	/** 独立写操作：action中做写操作，但是接下来的action不读取此action写入的数据 */
	protected static final int LV_WRITE = 1;
	/** 有影响写操作：action中做写操作，并且接下来的action会读取此action写入的数据 */
	protected static final int LV_WRITE_CASCADE = 2;

	protected int level = LV_READ;

	/** 事务隔离度 */
	public int getIsolation() {
		return TRANSACTION_READ_COMMITTED;
	}

	/** 是否只读动作 */
	public boolean isReadonly() {
		return level == LV_READ;
	}

	/** 是否是独立的写操作 */
	public boolean isWriteIndependently() {
		return level == LV_WRITE;
	}

	/** 是否是有影响的写操作 */
	public boolean isWriteInfluentially() {
		return level == LV_WRITE_CASCADE;
	}
}
