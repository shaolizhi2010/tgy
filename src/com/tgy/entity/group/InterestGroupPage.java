package com.tgy.entity.group;

import org.mongodb.morphia.annotations.Entity;

import com.tgy.entity.BasePage;
import com.tgy.util.PageType;

@Entity
public class InterestGroupPage extends BasePage {
	public String groupID;
	public String folderID;
	
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
