package com.xy.db.initData;

import zuojie.esql.Esql;

import com.xy.common.model.user.User;
import com.xy.loader.Column;
import com.xy.loader.LoadHandler2;

/**
 * 导入基础数据
 * 
 */
public class LogLoadHandler extends LoadHandler2
{
	private Column cName = add("名称", true);
	private Column cGold = add("金币", true);
	private Column cSilver = add("银币", true);
	private Column cPower = add("能量", true);
	private int level = 1;
	private int exp = 0;
	private int maxExp = 100;

	private Esql esql;

	public LogLoadHandler(Esql esql) throws Exception
	{
		this.esql = esql;

	}

	public void onData(String[] data) throws Exception
	{
		String sName = cName.getString(data);
		int sGold = cGold.getInteger(data);
		int sSilver = cSilver.getInteger(data);
		int sPower = cPower.getInteger(data);
		
		User u = new User();
		u.setName(sName);
		u.setGold(sGold);
		u.setSilver(sSilver);
		u.setLevel(level);
		u.setExp(exp);
		u.setMaxexp(maxExp);
		u.setPower(sPower);
		long id = addUser(u);
		System.out.println(id);
	}

	public long addUser(User user) throws Exception {
		esql.helper().insert(user, "t_user", null, "id");
		return getGeneratedId("t_user");
	}

	public long getGeneratedId(String table) throws Exception
	{
		String sql = "select get_generated_id(?) from dual";
		return esql.query(Long.class, sql, table);
	}

}
