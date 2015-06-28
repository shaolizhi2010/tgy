package com.tgy.entity;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import com.tgy.util.U;

@Entity
public class TopicPic    {
	@Id
	public ObjectId id;
	
	public String picUrl;
	public String createrUserID;
	public String createrUserName;
	
	//日期跟踪
	public String createDate = U.dateTime();//添加日期
	
	public int size;//图片大小
}