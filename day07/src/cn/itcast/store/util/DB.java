package cn.itcast.store.util;

import java.util.LinkedHashMap;
import java.util.Map;

public class DB {
	private static Map<String, Book> map = new LinkedHashMap();
	static{
		map.put("1", new Book("1","JavaWeb","老张","j2ee"));
		map.put("2", new Book("2","Spring","老张","j2ee"));
		map.put("3", new Book("3","Hibernate","老张","j2ee"));
		map.put("4", new Book("4","Strusts2","老张","j2ee"));
		map.put("5", new Book("5","Ajax","老张","j2ee"));
	}
	public static Map getAll(){
		return map;
	}
}
