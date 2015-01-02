package com.tgy.util;

import java.util.HashMap;

public class ConditionMap extends HashMap<String, String>{
//	Map<String,String> conditions = new HashMap<>();
	
	public ConditionMap add(String key, String value){
		this.put(key, value);
		return this;
	}
}
