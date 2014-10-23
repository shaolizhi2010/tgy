package com.tgy.entity;

import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;
import org.mongodb.morphia.annotations.Transient;
@Entity
public class Folder {
	 @Id
	public ObjectId  id;
	public String name;
	public String bookmarkID;
	public String userID;
	
	public String pid;
	
	@Transient 
	public boolean isSelected;
	
	@Reference(ignoreMissing=true)
	public List<Folder> folders;
	
	@Reference(ignoreMissing=true)
	public List<Page> pages;

}
