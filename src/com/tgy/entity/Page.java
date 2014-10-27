package com.tgy.entity;

import java.io.Serializable;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

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
}
