package com.tgy.entity;

import org.mongodb.morphia.annotations.Entity;

/**
 * 归属与某一个用户的，如收藏夹，被收藏的网页，用户组。
 * @author qq
 *
 */
@Entity
public class StateFulBaseEntity extends BaseEntity{
	
	//所有人的userid， owner的userid
	public String userID;
}
