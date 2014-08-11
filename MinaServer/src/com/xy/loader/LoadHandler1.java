package com.xy.loader;

import java.util.Map;

/**
 * 导入处理器
 * 
 * @author zuojie
 *
 */
public interface LoadHandler1
{
	public void onLoad(Map<String, String> data) throws Exception;
}
