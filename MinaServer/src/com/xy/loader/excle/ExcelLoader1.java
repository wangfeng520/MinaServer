package com.xy.loader.excle;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;


import com.xy.loader.Closer;
import com.xy.loader.LoadErrorHandler;
import com.xy.loader.LoadHandler1;
import com.xy.loader.Loader;

/**
 * Excel数据装载器
 * 
 * <p>
 * 第一行是标题行，以后是数据行。
 * 
 * <p>
 * {#link LoadHandler}是具体的数据处理器
 * 
 * @author zuojie
 * 
 */
@Deprecated
public class ExcelLoader1 implements Loader
{
	private static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

	private LoadHandler1 handler;
	private File file;

	private int successCount = 0;
	private int failCount = 0;
	private List<String> messages = new ArrayList<String>();

	public ExcelLoader1(LoadHandler1 handler, File file)
	{
		this.handler = handler;
		this.file = file;
	}

	public void load() throws Exception
	{
		load(null);
	}

	public void load(LoadErrorHandler err) throws Exception
	{
		InputStream input = null;
		try
		{
			input = new FileInputStream(file);
			HSSFWorkbook workbook = new HSSFWorkbook(input);
			HSSFSheet sheet = workbook.getSheetAt(0);

			String[] title;

			int rows = sheet.getLastRowNum() + 1;
			if (rows <= 1) throw new Exception("文件没有内容");

			// 先读标题
			HSSFRow row = sheet.getRow(0);
			int m = row.getFirstCellNum();
			int n = row.getLastCellNum();
			title = new String[n];
			for (int i = m; i < n; i++)
			{
				HSSFCell cell = row.getCell(i);
				if (cell == null || cell.getRichStringCellValue() == null) continue;

				title[i] = cell.getRichStringCellValue().getString();
			}

			// 再读入每行
			for (int i = 1; i < rows; i++)
			{
				row = sheet.getRow(i);
				if (row == null) continue;

				Map<String, String> map = new HashMap<String, String>();

				boolean notEmpty = false;
				for (int j = m; j < n; j++)
				{
					HSSFCell cell = row.getCell(j);
					if (cell == null) continue;

					String t = title[j];
					if (t == null) continue;

					String s = getCellString(cell);
					if (s != null) s = s.trim();
					if (s != null && !s.isEmpty()) notEmpty = true;
					map.put(t, s);
				}
				if (!notEmpty) continue;

				try
				{
					handler.onLoad(map);
					successCount++;
				}
				catch (Exception e)
				{
					failCount++;
					messages.add(String.format("导入错误: 第%d行: %s", i, e.getMessage()));
					if (err != null) err.onError(e);
				}
			}
		}
		finally
		{
			Closer.close(input);
		}
	}

	public List<String> getErrors()
	{
		if (messages.size() > 10)
		{
			List<String> errors = new ArrayList<String>();
			for (int i = 0; i < 10; i++)
			{
				errors.add(messages.get(i));
			}

			return errors;
		}
		else
		{
			return messages;
		}
	}

	public int getFailCount()
	{
		return failCount;
	}

	public int getSuccessCount()
	{
		return successCount;
	}

	private String getCellString(HSSFCell cell)
	{
		String value = null;

		int type = cell.getCellType();

		if (type == HSSFCell.CELL_TYPE_FORMULA) type = cell.getCachedFormulaResultType(); //若是公式格式，则读取公式结果的存放格式

		switch (type)
		{
			case HSSFCell.CELL_TYPE_BLANK:
				value = "";
				break;
			case HSSFCell.CELL_TYPE_BOOLEAN:
				value = Boolean.toString(cell.getBooleanCellValue());
				return "";
			case HSSFCell.CELL_TYPE_NUMERIC:
				// 判断当前的cell是否为Date
				if (HSSFDateUtil.isCellDateFormatted(cell))
				{
					// 如果是Date类型则，取得该Cell的Date值
					value = df.format(cell.getDateCellValue());
				}
				else
				{
					Double d = cell.getNumericCellValue();
					value = doubleToString(d);
				}
				break;
			case HSSFCell.CELL_TYPE_STRING:
				value = cell.getRichStringCellValue().getString();
				break;
			case HSSFCell.CELL_TYPE_ERROR:
				value = "#N/A";
				break;
			default:
				throw new IllegalStateException("未知的单元类型: " + type);
		}

		return value;
	}

	/**
	 * 把double类型的值转换为字符串
	 * 
	 * 若是直接转换，则会转换为科学计数法的形式，即 1.23E2 否则，则可以转换成 1234.0
	 * 
	 * 此方法将科学计数法的值还原，即1.23E2 = 123, 1.23E3 = 1230, 1.234E2 = 123.4
	 * 
	 * 但如果转换后，存在小数点后面的值为0，则去掉小数点和后面的0
	 * 
	 * @param d
	 * @return
	 */
	private String doubleToString(Double d)
	{
		if (d == null) return null;

		String v = d.toString().toUpperCase();
		int index = v.indexOf('E');
		if (index < 0)
		{
			int dot = v.indexOf('.');
			if (dot < 0)
				return v;
			else
			{
				String f = v.substring(dot + 1);
				Double x = new Double(f);
				if (x > 0) return v;
				return v.substring(0, dot);
			}
		}
		else
		//若是科学计数法，则转换
		{
			String n = v.substring(0, index);
			String ex = v.substring(index + 1);

			int nex = new Integer(ex);

			int dot = n.indexOf('.');
			if (dot < 0)
			{
				for (int i = 0; i < nex; i++)
					n = n + "0";
			}
			else if (n.length() <= dot + nex + 1)
			{
				n = n.replace(".", "");
				for (int i = 0; i < dot + nex - n.length(); i++)
					n = n + "0";
			}
			else
			{
				int i = dot + nex;
				n = n.replace(".", "");
				String s = n.substring(0, i);
				String e = n.substring(i);
				Double x = new Double(e);
				if (x > 0)
					n = s + "." + e;
				else
					n = s;
			}

			return n;
		}
	}
}
