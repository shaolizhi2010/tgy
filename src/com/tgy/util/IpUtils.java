package com.tgy.util;

import org.apache.commons.lang3.StringUtils;

public class IpUtils {
	
	 //123.123.123.11 ip最后一段
	public static int lastPartIp(String ip){
		int shortIp = 1;
		
		if(StringUtils.isNotBlank(ip)){
			ip = ip.trim();
			String shortIpStr = StringUtils.substringAfterLast(ip, ".");
			if(StringUtils.isBlank(shortIpStr)){
				 shortIpStr = StringUtils.substringAfterLast(ip, ":");
			}
			if(StringUtils.isBlank(shortIpStr)){
				 //empty
			}
			else{
				try {
					shortIp = Integer.parseInt(shortIpStr);
				} catch (Exception e) {
					 //empty
				}
				
			}
		}
		
		return shortIp;
	}
}
