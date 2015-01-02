package com.tgy.entity.group;

import org.mongodb.morphia.annotations.Entity;

import com.tgy.entity.BasePage;

@Entity
public class InterestGroupPage extends BasePage {
	public String groupID;
	public String folderID;
}
