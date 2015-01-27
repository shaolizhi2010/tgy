package com.tgy.util;

import org.apache.commons.lang3.StringUtils;

import com.tgy.entity.Discuss;

public class DiscussUtils {
	
	public static String fromUserName(Discuss d){
		return fromUserName(d,20);
	}
	
	public static String fromUserName(Discuss d,int length){
		
		String userName =  d.sourceBoardName ;
		if(d.sourceBoardName==null){
			System.out.println(d.message);
		}
		
		
		if(d.fromUser == null || StringUtils.isBlank( d.fromUser.name)){
			int shortIp = 1;//123.123.123.11 ip最后一段
			shortIp = IpUtils.lastPartIp(d.fromIP);
			userName += shortIp;
		}
		else{
			userName = d.fromUser.name;
		}
	 
		if(userName.length()>length){
			userName = StringUtils.substring(userName, 0, length)+"..";
		}
		
		return userName;
	}
	
	public static String toUserName(Discuss d){
		return toUserName(d,20);
	}
	
	public static String toUserName(Discuss d,int length){
		
		String userName = d.sourceBoardName ;
		
		if(d.replyToUser == null  || StringUtils.isBlank( d.replyToUser.name)){
			int shortIp = 1;	//123.123.123.11 ip最后一段
			if(d.replyToDiscuss==null){
				shortIp = 1;
			}else{
				shortIp = IpUtils.lastPartIp(d.replyToDiscuss.fromIP);
			}
			
			userName += shortIp;
		}
		else{
			userName = d.replyToUser.name;
		}
		if(userName.length()>length){
			userName = StringUtils.substring(userName, 0, length)+"..";
		}
		return userName;
	}
}
