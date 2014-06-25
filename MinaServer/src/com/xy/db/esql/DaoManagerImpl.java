package com.xy.db.esql;

import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import zuojie.esql.Esql;

import com.xy.common.JavaPackageExplorer;
import com.xy.db.esql.Dao.DB;
import com.xy.db.esql.source.DataSourceSelector;

public class DaoManagerImpl implements DaoManager {
	public static final String DATABASE_KEY = "database";
	private static Logger log = LoggerFactory.getLogger(DaoManager.class);
	private static final Class<BaseDao> BASE_DAO_CLASS = BaseDao.class;

	private String database;
	private Esql esql;

	private DataSourceSelector selector;
	private Random random;

	private Map<Class<?>, Object> daos = new HashMap<Class<?>, Object>();

	public void initialize() throws Exception {
		database = Esql.POSTGRESQL;

		selector = new DataSourceSelector(database);
		load();
	}

	public void destroy() {
	}

	public void setEsql(Esql e){
		esql = e;
	}
	
	public <T> T getDao(Class<T> type, Esql esql) {
		Object dao = daos.get(type);
		this.esql = esql;
		
		if (dao == null) throw new RuntimeException("指定的DAO不存在: " + type.getCanonicalName());
		try {
			 begin();
			((BaseDao) dao).setEsql(esql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return type.cast(dao);
	}

	protected void load() throws Exception {
		JavaPackageExplorer explorer = new JavaPackageExplorer("com.xy.db.dao");
		List<String> list = explorer.listAllClasses();
		for (String s : list) {
			register(s);
		}
	}

	/** 注册一个DAO对象 */
	private void register(String name) {
		try {
			Class<?> clazz = this.getClass().getClassLoader().loadClass(name);

			if (Modifier.isAbstract(clazz.getModifiers())) return;
			if (!BASE_DAO_CLASS.isAssignableFrom(clazz)) return;

			// 查看支持的数据库类型
			Dao sp = clazz.getAnnotation(Dao.class);
			if (sp != null) {
				DB[] types = sp.support();

				boolean has = false;
				for (DB t : types) {
					has = t.name().equalsIgnoreCase(database);
					if (has) break;
				}

				if (!has) return;
			}

			log.trace("注册DAO类: " + name);

			BaseDao dao = (BaseDao) clazz.newInstance();

			Class<?>[] cs = clazz.getInterfaces();

			for (Class<?> c : cs) {
				daos.put(c, dao);
			}
			daos.put(clazz, dao);
		} catch (Exception e) {
			log.error("注册DAO类失败: " + name, e);
		}
	}

	/** 注意：必须调用 begin后，此方法才能返回正确的值 */
	public Esql getEsql() {
		return esql;
	}

	/** 开始事务 */
	public void begin(int isolation, boolean master) throws Exception {
		Integer n = null;
		if (!master && selector.getSlaveCount() > 0) n = random.nextInt(selector.getSlaveCount());

		DataSource source = selector.getDataSource(n);

		try {
			esql.begin(isolation, source);

			// 注：此处不采用 (n != null) 判断，而是用 !master 判断，原因为：
			// 若begin的时候，master为false，则有可能会采用Slave数据源
			// 这里就必须保证不管采不采用Slave数据源，action中设置的level是正确的，带有检查action中的只读设置的作用
			// 若切换环境，也能保证action能正确执行
			if (!master) esql.setReadOnly(); // Slave数据源只支持只读查询
		} catch (Exception e) {
			throw new Exception("开始事务错误", e);
		}
	}

	/** 开始事务 */
	public void begin(int isolation) throws Exception {
		begin(isolation, true);
	}

	public void begin() throws Exception {
		begin(Action.TRANSACTION_READ_COMMITTED);
	}

	/** 提交当前事务 */
	public void commit() throws Exception {
		try {
			esql.commit();
		} catch (Exception e) {
			throw new Exception("提交事务错误", e);
		}
	}

	/** 结束当前事务。如果没有提交，就回滚 */
	public void end() {
		try {
			esql.end();
		} catch (Exception e) {
		}
	}
}
