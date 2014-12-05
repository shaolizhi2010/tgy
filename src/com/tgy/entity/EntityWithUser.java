package com.tgy.entity;

import org.mongodb.morphia.annotations.Entity;

@Entity
public class EntityWithUser extends BaseEntity{

	public String userID;

}
