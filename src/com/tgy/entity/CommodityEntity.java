package com.tgy.entity;

import org.apache.commons.lang3.StringUtils;
import org.mongodb.morphia.annotations.Entity;

@Entity
public class CommodityEntity extends BaseEntity{
	
	String name;
	String url;
	String price;
	String imgUrl;
	String commentCount;
	String commentUrl;
	String source;
	String keyword;
	String timestamp;
	 
	
}
