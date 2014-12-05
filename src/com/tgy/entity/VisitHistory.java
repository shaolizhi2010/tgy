package com.tgy.entity;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity
public class VisitHistory extends BaseEntity implements Serializable{
 
	public String requestUserID;//访问者
	public String responseUserID;//被访问者
	
	public String lastVisitDateTime;//上次访问时间
	public String responseUserName; //被访者 name
	
	public long times;//访问次数
	public long timesByOther;// 被其他用户访问，非本用户自己访问
}
