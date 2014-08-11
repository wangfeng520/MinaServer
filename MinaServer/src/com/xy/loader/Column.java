package com.xy.loader;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * 导入器的一列
 * 
 * @author zuojie
 * 
 */
public class Column
{
	private static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd");

	private String name;
	private int index = -1;
	private boolean required;

	public Column(String name, boolean required)
	{
		this.name = name;
		this.required = required;
	}

	public String getString(String[] data) throws Exception
	{
		String s = content(data);
		if (s == null)
		{
			if (required) throw new Exception(String.format("列 %s(文本) 是必填项", name));

			return null;
		}

		return s;
	}

	public Integer getInteger(String[] data) throws Exception
	{
		String s = content(data);
		if (s == null)
		{
			if (required) throw new Exception(String.format("列 %s(整数) 是必填项", name));

			return null;
		}

		try
		{
			return new Integer(s);
		}
		catch (Exception e)
		{
			throw new Exception(String.format("列 %s 格式错误, 请填写整数: ", name, s));
		}
	}

	public Long getLong(String[] data) throws Exception
	{
		String s = content(data);
		if (s == null)
		{
			if (required) throw new Exception(String.format("列 %s(整数) 是必填项", name));

			return null;
		}

		try
		{
			return new Long(s);
		}
		catch (Exception e)
		{
			throw new Exception(String.format("列 %s 格式错误, 请填写整数: ", name, s));
		}
	}
	
	public Double getDouble(String[] data) throws Exception
	{
		String s = content(data);
		if (s == null)
		{
			if (required) throw new Exception(String.format("列 %s(浮点数) 是必填项", name));

			return null;
		}

		try
		{
			return new Double(s);
		}
		catch (Exception e)
		{
			throw new Exception(String.format("列 %s 格式错误, 请填写浮点数: ", name, s));
		}
	}
	
	
	public Date getDate(String[] data) throws Exception
	{
		String s = content(data);
		if (s == null)
		{
			if (required) throw new Exception(String.format("列 %s(日期) 是必填项", name));

			return null;
		}

		try
		{
			return FORMAT.parse(s);
		}
		catch (Exception e)
		{
			throw new Exception(String.format("列 %s 格式错误, 请填写正确格式yyyy-mm-dd: %s", name, s));
		}
	}

	protected String content(String[] data) throws Exception
	{
		String s = index < 0 ? null : data[index];
		if (s != null && s.isEmpty()) s = null;

		if (s == null && required) throw new Exception(String.format("列 %s 是必填项", name));

		return s;
	}

	void setIndex(int index)
	{
		this.index = index;
	}

	public String getName()
	{
		return name;
	}

}
