package com.tgy.entity;

import java.util.List;

public class Discuss extends StateFulBaseEntity{
	
	public String message;
	public String type; //评论网址 回复别人留言等
	
	public String sourceID; //发评论的人的id
	public String soucrceIP;
	
	public String targetID; //网址id 或别人浏览的id
	
	public List<Discuss> replies;//回复
	
}
