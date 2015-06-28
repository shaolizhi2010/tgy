package com.tgy.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;


public class PriceUtil {
	 
	/* 从字符串中提取价格数字 */
	public static String getPrice(String str){
		str = StringUtils.trimToEmpty(str).replaceAll(" ", "");
		Pattern pattern = Pattern.compile("\\d{1,6}(\\.\\d{1,2})?");
		Matcher matcher = pattern.matcher(str);
		if(matcher.find()){
			return matcher.group();
		}
		return "";
	}
	
}
