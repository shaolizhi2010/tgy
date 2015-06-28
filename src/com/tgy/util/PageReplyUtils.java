package com.tgy.util;

import org.apache.commons.lang3.StringUtils;

import com.tgy.entity.Discuss;
import com.tgy.entity.Reply;

public class PageReplyUtils {
	
	public static String fromUserName(Reply d){
		return fromUserName(d,20);
	}
	
	public static String fromUserName(Reply d,int length){
		
		String userName = "";
		
		if(d.fromUser == null || StringUtils.isBlank( d.fromUser.name)){
		 
			userName +=  "游侠"+IpUtils.lastPartIpStr(d.fromIP);
		}
		else{
			userName = d.fromUser.name;
		}
	 
		if(userName.length()>length){
			userName = StringUtils.substring(userName, 0, length)+"..";
		}
		
		return userName;
	}
	 
}
