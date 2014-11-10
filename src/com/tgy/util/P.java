package com.tgy.util;

import java.util.Properties;


public class P {

	static Properties properties = new Properties();
	static boolean loaded = false;

	static {
		loadProperties();
	}

	public static Properties getProperties() {
		loadProperties();
		return properties;
	}

	public static String getProperty(String key) {
		loadProperties();
		return U.toString(properties.get(key));
	}

	private static void loadProperties() {
		if (!loaded) {
			try {
				properties.load(P.class
						.getResourceAsStream("/config.properties"));
				loaded = true;
			} catch (Exception e) {
				System.out.println(	"load property file error : " + e.getMessage());
			}
		}
	}
}
