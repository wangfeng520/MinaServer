package com.xy.game.manager;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Managers {
	private static final Logger log = LoggerFactory.getLogger(Managers.class);

	private static Map<Class<?>, Manager> map = new HashMap<Class<?>, Manager>();

	public static <T> T get(Class<T> type) {
		Manager manager = map.get(type);
		if (manager == null) log.warn("管理器不存在: {}", type.getCanonicalName());
		return type.cast(manager);
	}

	public static void addManager(Manager manager) throws Exception {
		Class<? extends Manager> type = manager.getClass();

		log.info("注册Manager:" + type);
		for (Class<?> c : type.getInterfaces()) {
			if (!Manager.class.isAssignableFrom(c)) continue;
			if (Manager.class.equals(c)) continue;

			map.put(c, manager);
		}
		for (Class<?> c = type;; c = c.getSuperclass()) {
			if (!Manager.class.isAssignableFrom(c)) break;
			if (Manager.class.equals(c)) continue;

			map.put(c, manager);
		}

		map.put(type, manager);
	}
}
