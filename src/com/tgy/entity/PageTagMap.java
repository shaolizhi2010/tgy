package com.tgy.entity;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import com.tgy.util.U;

/**
 * Page 和 Tag 的中间表
 * 
 * 
 * @author qq
 *
 */

@Entity
public class PageTagMap {
	
	@Id
	public ObjectId id;
	
	public ObjectId pageID;
	public ObjectId tagID;
	
	public String createDate = U.dateTime();
}
