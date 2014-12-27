package com.tgy.entity;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;
/**
 * group 和 user 的多对多对应
 * 一个group中的每个user 会对应一条这个记录，记录用户在这个group中的信息，如等级，经验值等等，发帖数。
 * @author qq
 *
 */

@Entity
public class GroupUser extends BaseEntity implements Serializable{
 
	@Reference(ignoreMissing = true,lazy=true)
	public User user;
	
	@Reference(ignoreMissing = true,lazy=true)
	public InterestGroup group;
	
	public boolean isSuperAdmin;//主管理员
	public boolean isAdmin;//附管理员

	
	
	@Override
	public boolean equals(Object obj) {//只根据id判断
		if(obj instanceof GroupUser){
			GroupUser user = (GroupUser)obj;
			if(id!=null && id.equals(user.id)){
				return true;
			}
			
		}
		return false;
	}
	
	

}
