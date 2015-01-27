package com.tgy.entity;

import java.io.Serializable;
import java.util.Date;

import org.mongodb.morphia.annotations.Entity;

//继承page 因为search出的都是page
@Entity
public class PanSearchCache extends Page implements Serializable{
	public String key;
	public Date createDateTTL = new Date();
}
