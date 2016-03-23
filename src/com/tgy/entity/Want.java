package com.tgy.entity;

import java.io.Serializable;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Reference;

import com.tgy.util.U;

/**
 * 求购资源
 * @author qq
 *
 */
@Entity
public class Want extends SimpleBaseEntity  implements Serializable{
	
	public String userID; //谁发的这条回复
	
	public String userName; //谁发的这条回复,如匿名回复，这个字段是某个系统设定的名字
	
	//发消息人的ip地址 
	public String fromIP;
	
	//发消息的user
	@Reference(ignoreMissing = true)
	public User fromUser;
			
	public String name; //需要资源的名称
	public String commont; //其他说明信息
	public int fulidou; //多少个福利豆
	
	public String tagName; //资源类别
	 
	public String lastReplyDate = U.dateTime();//最后回复日期
	
	public int replyTimes;//回复次数
	
}
