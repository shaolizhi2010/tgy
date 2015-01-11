package com.tgy.entity;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
/**
 * 关注表
 * A关注B，那么A是B的粉丝，B是A的关注
 * @author qq
 *
 */
@Entity
public class Follows extends BaseEntity implements Serializable{
 
	public String fromUserID;//访问者
	public String toUserID;//被访问者
	
}
