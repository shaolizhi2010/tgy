package com.tgy.entity;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity
public class SearchHistory extends BaseEntity implements Serializable{
	
	public String userID;//访问者
	public String userIP;
	
	public String keyword;
	
	public long times;//访问次数
	
	public String lastDigTime;
	
	
}
