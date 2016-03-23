package com.tgy.entity;

import org.mongodb.morphia.annotations.Entity;

@Entity
public class Online extends SimpleBaseEntity {

	 public Long visitTimestamp;
	 public  String userID;

}

