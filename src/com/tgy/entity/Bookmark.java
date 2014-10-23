package com.tgy.entity;

import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;

@Entity
public class Bookmark {
	@Id
	public ObjectId id;
	public String name;
	public String userID;
	public String createDate;// = new Date(U.dateTime());

	public boolean isDefault;

	@Reference(ignoreMissing = true)
	public List<Folder> folders;

	//@Reference(ignoreMissing = true) //TODO java.lang.ClassCastException: com.mongodb.BasicDBObject cannot be cast to com.mongodb.DBRef
	public List<Page> pages;

}
