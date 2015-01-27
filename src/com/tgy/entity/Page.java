package com.tgy.entity;

import org.mongodb.morphia.annotations.Entity;

import com.tgy.util.PageType;

@Entity
public class Page extends BasePage {
	public String folderID;//v0.3开始，替代之前的pid
	
	public PageType type;
	
	//type is article
	public String title; //文章标题
	public String imgSrc; //文章配图
	public String summry; //文章概要
	public String content; //文章内容
	public String orignDate; //文章发表时间，（不是抓取时间）
	//public String url; //文章链接

	public String authorName; //作者名字 
	public String authorUrl; //做着url，如新浪微博的文章，authro url就是做着新浪账号url
	public String authorHearImgSrc; //author 头像图像链接
	
}
