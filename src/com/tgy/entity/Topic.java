package com.tgy.entity;

import java.util.ArrayList;
import java.util.List;

import org.mongodb.morphia.annotations.Entity;

@Entity
public class Topic extends BaseEntity {

	public String title;

	public List<TopicSumary> sumaries = new ArrayList<TopicSumary>(); //简介
	public List<TopicPic> pics = new ArrayList<TopicPic>(); //图片

	public String createrUserID;
	public String createrUserName;

}

