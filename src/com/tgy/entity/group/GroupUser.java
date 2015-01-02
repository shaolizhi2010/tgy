package com.tgy.entity.group;

import java.io.Serializable;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Reference;

import com.tgy.entity.BaseEntity;
import com.tgy.entity.User;
/**
 * group 和 user 的多对多对应
 * 一个group中的每个user 会对应一条这个记录，记录用户在这个group中的信息，如等级，经验值等等，发帖数。
 * @author qq
 *
 */

@Entity
public class GroupUser extends BaseEntity implements Serializable{
	
	private String userID;
	@Reference(ignoreMissing = true,lazy=true)
	public User user;
	
	private String groupID;
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

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
		if(user!=null && user.id!=null){
			this.userID = user.id.toString();
		}
	}

	public String getGroupID() {
		return groupID;
	}

	public void setGroupID(String groupID) {
		this.groupID = groupID;
	}

	public InterestGroup getGroup() {
		return group;
	}

	public void setGroup(InterestGroup group) {
		if(group!=null && group.id!=null){
			this.groupID = group.id.toString();
		}
		this.group = group;
	}
	
	

}
