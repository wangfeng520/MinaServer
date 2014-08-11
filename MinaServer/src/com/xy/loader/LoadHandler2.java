package com.xy.loader;

import java.util.HashMap;
import java.util.Map;



/**
 * 导入处理器
 * 
 * @author zuojie
 * 
 */
public abstract class LoadHandler2
{
	private Map<String, Column> map = new HashMap<String, Column>();
	private Column[] columns;

	protected Column add(String name, boolean required)
	{
		Column column = new Column(name, required);
		map.put(name, column);
		
		return column;
	}

	/** 读取标题行 */
	public void onTitle(String[] titles) throws Exception
	{
		columns = new Column[titles.length];

		for (int i = 0; i < titles.length; i++)
		{
			Column column = map.get(titles[i]);
			if (column == null) continue;

			columns[i] = column;
			column.setIndex(i);
		}
	}

	/** 载入一行数据 */
	public abstract void onData(String[] data) throws Exception;
}
