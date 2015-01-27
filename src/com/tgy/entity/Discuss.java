package com.tgy.entity;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Reference;

/**
 * 聊天系统 综合百度贴吧和普通聊天室的功能
 * 1 用户 A 发一条消息,这条消息视为主消息，类似于bbs的帖子，id 为123，
 * 2 用户B 回复了这条消息， id为124， 那么 replyTO 是A的userid，discussID 是123 
 * 3 用户C 回复了用户B的消息，id为125，那么 replyID为B的userid， discussID 仍是123
 * 4 页面显示的时候，会显示A发的那条主题消息，以及最后三条条回复 //
 * 
 * 另外
 * 1 消息可以跨板块，即美剧群发的一条消息，电影群也可以看到并且回复
 * 2 消息有一个属性，表明消息的来源（美剧群，电影群，等待）
 * @author qq
 *
 */
@Entity
public class Discuss extends StateFulBaseEntity{
	
	/*************** 消息内容和属性 *****************/
	//消息内容
	public String message;
	
	//是否是 主消息 
	public boolean isPrimary;//主消息，即不是回复别人，而是直接发消息。
	
	//是否是回复
	public boolean isReply;//是否是回复
	
	/*************** 从哪来 *****************/
	//发消息人的ip地址 
	public String fromIP;
	
	//发消息的user
	@Reference(ignoreMissing = true)
	public User fromUser;
	
	//userID 父类有这个属性，不再这定义了
	
	/*************** 到哪去 *****************/
	//回复给哪个user
	@Reference(ignoreMissing = true)
	public User replyToUser;
	
	public String replyToUserID;
	
	//回复给哪条discuss
	@Reference(ignoreMissing = true)
	public Discuss replyToDiscuss;
	
	public String replyToDiscussID;
	
	
	/*************** 属于哪 *****************/
	//所属主discuss
	@Reference(ignoreMissing = true)
	public Discuss primaryDiscuss;//这条回复属于哪个discuss 如果这个消息本身就是主消息，此字段为空
	
	public String primaryDiscussID;

	//所属板块
	public String sourceBoardType;//评论的来源板块，如哪个讨论组（美剧，电影），哪个link，或来自于资源搜索页
	public String sourceBoardName;//评论所属的板块名称，如讨论组的名称（美剧，电影），或来自于资源查找page，或来自于哪个link的评论等
	public String sourceBoardID;//评论的来源，如讨论组的id
	
	public boolean targetIsAllSite = true;//全站集中,默认是
	
	/*************** 有什么 *****************/
	
	//所有回复，直接回复这条消息的回复，不包括间接回复，即使是主消息也不包括间接回复。
	public List<Discuss> replies;
	
	public void add(Discuss d) {
		if (replies == null) {
			replies = new ArrayList<>();
		}
		replies.add(d);
	}
	
	public void remove(Discuss d){
		if (replies != null) {
			for(int i=0;i<replies.size();i++){
				if(replies.get(i).id!=null &&
						d.id!= null &&
						StringUtils.equals(replies.get(i).id.toString(), d.id.toString())   ){
					replies.remove(d);
				}
			}
		}
	}
	
	/*************** 统计信息 *****************/
	
	
	//这条消息 被回复了多少次，如果是主消息，则表示這条主消息下边的所有的回复，就算不是直接回复这条消息，也会计数
	public int 	replyTimes;
	
	//public String targetID; //网址id 或别人浏览的id v0.3后不使用
	//public String sourceID; //发评论的人的id v0.3后不适用，直接用userID
	
	 
	
}
