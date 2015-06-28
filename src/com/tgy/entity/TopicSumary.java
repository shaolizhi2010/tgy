package com.tgy.entity;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import com.tgy.util.U;

@Entity
public class TopicSumary   {
	@Id
	public ObjectId id;
	
	public String sumary;
	public String createrUserID;
	public String createrUserName;
	
	//日期跟踪
	public String createDate = U.dateTime();//添加日期
	
	public int length;//文本长度
}