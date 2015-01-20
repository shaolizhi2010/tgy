package com.tgy.entity;

import java.util.List;

public class Discuss extends StateFulBaseEntity{
	
	public String message;
	public String type; //评论网址 回复别人留言等
	
	//public String sourceID; //发评论的人的id v0.3后不适用，直接用userID
	public String soucrceIP;
	
	//public String targetID; //网址id 或别人浏览的id v0.3后不使用
	
	public String targetLinkID; //评论的link的id，评论link的时候有这个
	public String targetIsSearchPage; //网盘搜索页面内评论，这个字段设置为"true"
	public String targetUserID; //对某人进行回复，或者聊天 TODO
	public String targetIsAllSite; //网盘搜索页面内评论，这个字段设置为"true"
	
	public String sourceName;//评论的来源，如讨论组的名称
	
	public List<Discuss> replies;//回复
	
}
