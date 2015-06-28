package com.tgy.entity;

import java.io.Serializable;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Reference;

@Entity
public class Reply extends BaseEntity implements Serializable{
  
	public String toID; //回复哪个网页或主题
	
	public String userID; //谁发的这条回复
	
	public String userName; //谁发的这条回复,如匿名回复，这个字段是某个系统设定的名字
	
	public String content; //回复的内容
	
	//发消息人的ip地址 
	public String fromIP;
	
	//发消息的user
	@Reference(ignoreMissing = true)
	public User fromUser;
	
	
}
