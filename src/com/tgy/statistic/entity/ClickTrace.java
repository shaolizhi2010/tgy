package com.tgy.statistic.entity;

import java.io.Serializable;

import org.mongodb.morphia.annotations.Entity;

import com.tgy.entity.BaseEntity;

/**
 * 收集每一次用户在页面的点击事件
 * 包括
 * 用户ip
 * 点击元素id
 * 点击元素name
 * 点击元素类型 
 * @author qq
 *
 */
@Entity
public class ClickTrace extends BaseEntity  implements Serializable  {
	
	String ip;
	String userID;
	String elementID;
	String ext; //其他信息
 
	 
}
