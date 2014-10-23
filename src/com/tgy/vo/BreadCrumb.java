package com.tgy.vo;

public class BreadCrumb {
	
	public BreadCrumb(String name, String link){
		this.name = name;
		this.link = link;
	}
	
	public String name;
	public String link;
	public BreadCrumb child ;
	
}
