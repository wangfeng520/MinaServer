package com.xy.loader;

import java.util.List;


/**
 * 数据装载器
 * 
 * <p>
 * {#link LoadHandler2}是具体的数据处理器
 * 
 * @author zuojie
 * 
 */
public interface Loader
{
	/** 导入 */
	public void load() throws Exception;

	/** 获取导入错误数量 */
	public int getFailCount();
	
	/** 获取成功导入数量 */
	public int getSuccessCount();
	
	/** 获取错误信息 */
	public List<String> getErrors();
}
