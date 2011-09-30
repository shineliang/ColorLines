package com.shine.app.game.colorlines.obj;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class NameRegister {
	private static NameRegister instance = new NameRegister();
	private ConcurrentMap<String, Object> map = new ConcurrentHashMap<String, Object>();

	private NameRegister() {

	}

	public static NameRegister getInstance() {
		return instance;
	}

	public void addObj(String name, Object obj) {
		map.put(name, obj);
	}

	public Object getObj(String name) {
		Object obj = map.get(name);

		return obj;
	}

}
