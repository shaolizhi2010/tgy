package com.tgy.entity;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity
public class User extends BaseEntity implements Serializable{
 
	public String name;
	public String password;
	public String type;
	public int loginTimes;
	public long score;
	
	public boolean isTemp;
	
	public String bookmarkLastUpdateTime;//书签最后更新时间，用来跟客户端时间比对，如果比客户端新，则返回最新数据给客户端，否则不返回数据，客户端使用localstorage存的数据，
	//这样可以降低服务器压力 提示客户端速度
	
	public String createDate;
	
	public String openID;
	
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
