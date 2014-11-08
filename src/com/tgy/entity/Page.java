package com.tgy.entity;

import java.io.Serializable;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import com.tgy.statistic.entity.Link;

@Entity
public class Page implements Serializable {
	 @Id
	public ObjectId  id;
	
	public String name;
	public String userID;
	public String pid;
	public String url;
	public String createDate;
	
	public Link refLink;
	
	
	//stastics
	public int clicks;
	public int scanTimes; //呗后台分析程序扫描的次数，0标识没扫描过
}
