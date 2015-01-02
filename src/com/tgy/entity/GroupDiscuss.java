package com.tgy.entity;


public class GroupDiscuss extends StateFulBaseEntity{
	
	public String groupID;
	public String soucrceIP;
	public String message;
//	public String type; //评论网址 回复别人留言等
	
	public String targetID; //link id, 用户对link发表评论
	
//	public List<GroupDiscuss> replies;//回复
	
}
