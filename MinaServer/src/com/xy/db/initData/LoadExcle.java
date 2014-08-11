package com.xy.db.initData;

import java.io.File;

import org.postgresql.jdbc3.Jdbc3PoolingDataSource;

import zuojie.esql.Esql;
import zuojie.esql.EsqlPgsqlImpl;

import com.xy.loader.LoadHandler2;
import com.xy.loader.Loader;
import com.xy.loader.excle.ExcelLoader3;


public class LoadExcle
{

	
	public static void main(String[] args) throws Exception
	{
		
//		File file = new File("./user.xls");
		File file=new File("resource" + java.io.File.separator +"doc"+java.io.File.separator+ "user.xls"); //File.separator表示根目录，比如现在就表示在D盘下。 
		
		Jdbc3PoolingDataSource source = new Jdbc3PoolingDataSource();
		source.setServerName("localhost");
		source.setPortNumber(5432);
		source.setDatabaseName("lvshuiqiao");
		source.setUser("wangfeng");
		source.setPassword("123");

		Esql esql = new EsqlPgsqlImpl();

		try
		{
			esql.begin(Esql.READ_COMMITTED, source);

			LoadHandler2 handler = new LogLoadHandler(esql);

			Loader loader = new ExcelLoader3(handler, file);
			loader.load();

			if (loader.getFailCount() == 0)
			{
				esql.commit();
			}
			else
			{
				for (String error : loader.getErrors())
				{
					System.err.println(error);
				}
			}
		}
		finally
		{
			esql.end();
		}
		
		System.exit(0);
	}
}
