package com.tgy.entity;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity
public class User {
	 @Id
	public ObjectId id;
	public String name;
	public String password;
	public String type;

}
