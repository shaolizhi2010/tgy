package com.tgy.entity;

import org.mongodb.morphia.annotations.Entity;

@Entity
public class Page extends BasePage {
	public String folderID;//v0.3开始，替代之前的pid
}
