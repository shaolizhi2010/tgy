package com.tgy.entity;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity
public class Page {
	 @Id
	public ObjectId  id;
	
	public String name;
	public String userID;
	public String bookmarkID;
	public String pid;
	public String link;

}
