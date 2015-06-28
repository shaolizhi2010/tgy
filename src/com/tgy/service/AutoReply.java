package com.tgy.service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.tgy.entity.Discuss;
import com.tgy.util.ConditionMap;
import com.tgy.util.U;

public class AutoReply {
	
	public void autoReplyAll(){
		
	}
	
	/**
	 * 自动回复 求资源的帖子，
	 * 如帖子内容是 【权利的游戏】
	 * 回复帖子内容为 【www.webhezi.com/pan/权利的游戏】
	 * 
	 * @param primaryDiscussID
	 */
	public void autoReply(String primaryDiscussID,String keyword){
		DiscussService ds = new DiscussService();
		List<Discuss> subDiscuss = ds.list(new ConditionMap().add("primaryDiscussID", primaryDiscussID), "createDate", 0,0);
		if(CollectionUtils.isEmpty(subDiscuss)){//没有回复
			
			String message = "http://www.webhezi.com/pan/";
			if(StringUtils.isNotBlank(message)){
				try {
					message +=  URLEncoder.encode(keyword, "UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}	
			}
			
			Discuss dc = new Discuss();
			dc.lastModifyDate = U.dateTime();
			dc.message = message;
			if(StringUtils.isNotBlank(primaryDiscussID)){
				dc.isPrimary = false;
				dc.isReply = true;
			}
			else{
				dc.isPrimary = true;
				dc.isReply = false;
			}
			
			//从哪来
			dc.fromIP = "127.0.0.1";
			dc.fromUser = null;
			dc.userID = "";
			
			//所属
			dc.sourceBoardName = "首页";
			dc.sourceBoardType = null;
			dc.sourceBoardID = null;
			
			//到哪去
			if(StringUtils.isNotBlank(primaryDiscussID)){
				Discuss replyToDiscuss = ds.byID(primaryDiscussID);
				dc.replyToDiscuss = replyToDiscuss;
				dc.replyToDiscussID = primaryDiscussID;
				
				dc.replyToUser = replyToDiscuss.fromUser;
				dc.replyToUserID = replyToDiscuss.fromUser!=null&&replyToDiscuss.fromUser.id!=null?replyToDiscuss.fromUser.id.toString():null;
				
				//不是主贴，需 记录 primaryDiscuss，primaryDiscussID
				if(replyToDiscuss.isPrimary){//回复的是主题，则primarydiscuss就是主贴本身
					dc.primaryDiscuss = dc.replyToDiscuss;
					dc.primaryDiscussID = primaryDiscussID;
				}
				else{//回复的不是主贴，primarydiscuss是回复贴的主贴
					dc.primaryDiscuss = replyToDiscuss.primaryDiscuss;
					dc.primaryDiscussID = replyToDiscuss.primaryDiscussID;
				}
				ds.save(dc);
				
				//被回复消息计数++ 回复list +1
				replyToDiscuss.replyTimes++;
				replyToDiscuss.add(dc);
				ds.save(replyToDiscuss);
				
				//如果被回复消息 不是主消息， 主消息 计数++
				if(!replyToDiscuss.isPrimary && replyToDiscuss.primaryDiscuss!=null){
					replyToDiscuss.primaryDiscuss.replyTimes++;
					replyToDiscuss.primaryDiscuss.lastModifyDate = U.dateTime();
					ds.save(replyToDiscuss.primaryDiscuss);
				}
				else if(  replyToDiscuss.primaryDiscuss!=null){//是主贴
					replyToDiscuss.primaryDiscuss.lastModifyDate = U.dateTime();
					ds.save(replyToDiscuss.primaryDiscuss);
				}
			}//end if
		
			
			
		}//end if
		
	}
}
