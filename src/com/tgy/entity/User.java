package com.tgy.entity;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity
public class User implements Serializable{
	 @Id
	public ObjectId id;
	public String name;
	public String password;
	public String type;
	public int loginTimes;
	public long score;
	
	@Override
	public boolean equals(Object obj) {//只根据id判断
		if(obj instanceof User){
			User user = (User)obj;
			if(id!=null && id.equals(user.id)){
				return true;
			}
			
		}
		return false;
	}
	
	

}
