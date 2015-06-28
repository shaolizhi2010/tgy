package com.tgy.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.tgy.entity.Discuss;
import com.tgy.entity.User;
import com.tgy.entity.group.InterestGroup;
import com.tgy.exception.BaseException;
import com.tgy.service.DiscussService;
import com.tgy.service.IndexService;
import com.tgy.service.cache.AppCache;
import com.tgy.service.group.InterestGroupFolderService;
import com.tgy.service.group.InterestGroupService;
import com.tgy.statistic.entity.Link;
import com.tgy.statistic.service.LinkService;
import com.tgy.util.C;
import com.tgy.util.ConditionMap;
import com.tgy.util.DiscussUtils;
import com.tgy.util.IpUtils;
import com.tgy.util.U;
import com.tgy.validator.CommonValidator;

@RestController
@RequestMapping(value = { "/discuss" })
public class DiscussContoller extends HttpServlet {

	@RequestMapping(value = { "/list" })
	public void list(HttpServletRequest req, HttpServletResponse res) {
		
		String primaryDiscussID =  U.filterCharacter(req.getParameter("primaryDiscussID")) ;
		String startStr =  U.filterCharacter(req.getParameter("start")) ;
		int start = 0;
		try {
			start = Integer.parseInt(startStr);
		} catch (Exception e) {
			 
		}
		if(StringUtils.isBlank(primaryDiscussID)){
			U.message(res, "");
			return;
		}
		
		DiscussService dcs = new DiscussService();
		
		List<Discuss> discusses = dcs.list(new ConditionMap().add("primaryDiscussID", primaryDiscussID), "createDate",start,10);
		
		List<Map<String, String>> discussForJson = new ArrayList<>();
		for(Discuss d : discusses){
			 //初始化 discuss 和用户数据
			String fromUserName = DiscussUtils.fromUserName(d) ;
			String toUserName = DiscussUtils.toUserName(d) ;

			String fromUserHeadUrl = "";
			String fromUserUrl = "#";
			String fromUserID = "";

			if(d.fromUser!= null && d.fromUser.id!=null){
				fromUserHeadUrl = d.fromUser.headImgUrl;
				fromUserID = d.fromUser.id.toString();
				fromUserUrl = "/u/"+fromUserID;
				
			}
			if(StringUtils.isBlank(fromUserHeadUrl)){
				int radNum = IpUtils.lastPartIp(d.fromIP)/2+1 ;
				fromUserHeadUrl = "/images/ava/ava"+radNum+".png";
			}
			
			Map<String,String> m = new HashMap<String,String>();
			m.put("id", d.id.toString());
			m.put("message", d.message) ;
			m.put("fromUserName", fromUserName) ;
			m.put("toUserName", toUserName) ;
			m.put("fromUserHeadUrl", fromUserHeadUrl) ;
			m.put("fromUserUrl", fromUserUrl) ;
			m.put("fromUserID", fromUserID) ;
			
			discussForJson.add(m);
			
		}
		 String json = U.toJson(discussForJson);
		 U.message(res, json);
	}

	@RequestMapping(value = { "/pagination/ajax" })
	public void ajaxPagination(HttpServletRequest req, HttpServletResponse res){
		
		String primaryDiscussID =  U.filterCharacter(req.getParameter("primaryDiscussID")) ;
		if(StringUtils.isBlank(primaryDiscussID)){
			U.message(res, "");
			return;
		}
		
		DiscussService dcs = new DiscussService();
		long count = dcs.count();
		int start = 0;
		String url = "#"+primaryDiscussID;
		
		U.forward(req, res, "part/pagination.jsp?count="+count+"&start="+start+"&url="+url);
	}

	@RequestMapping(value = { "/link/{linkID}" })
	public void byLinkID(HttpServletRequest req, HttpServletResponse res,
			@PathVariable("linkID") String linkID) {

		LinkService ls = new LinkService();
		Link link = ls.getByID(linkID);
		
		List<Discuss> discusses = link.discusses;
		if(discusses!=null && discusses.size()>20){
			discusses = discusses.subList(discusses.size()-20, discusses.size());
		}
		
		req.setAttribute("target", link);
		req.setAttribute("discusses", discusses);
		
		U.forward(req, res, "/discuss.jsp");
	}

	@RequestMapping("/link/create")
	protected void createForLink(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		DiscussService dcs = new DiscussService();
		String message = U.filterCharacter(req.getParameter("message")) ;
		//String sourceID = U.filterCharacter(req.getParameter("sourceID")) ;
		String linkID = U.filterCharacter(req.getParameter("linkID")) ;
		String replyToDiscussID = U.filterCharacter(req.getParameter("replyToDiscussID")) ;
		
		try {
			new CommonValidator()
				.isLonger(message, 1, "信息长度需大于1")
				.isShorter(message, 300, "信息需小于150个字符");
			
			Link lk = null;
			LinkService ls = new LinkService();
			
			//create disccuss for link
			if(StringUtils.isNotBlank(linkID)){
				lk = ls.getByID(linkID);
			}
			
			if(lk==null){
				return;
			}
			
			User user = U.param(req, C.user, User.class);
			
			//消息内容 属性
			Discuss dc = new Discuss();
			dc.lastModifyDate = U.dateTime();
			dc.message = message;
			if(StringUtils.isNotBlank(replyToDiscussID)){
				dc.isPrimary = false;
				dc.isReply = true;
			}
			else{
				dc.isPrimary = true;
				dc.isReply = false;
			}
			
			//从哪来
			dc.fromIP = U.getIpAddr(req);
			dc.fromUser = user;
			dc.userID = U.getUserID(req);
			
			//到哪去
			if(StringUtils.isNotBlank(replyToDiscussID)){
				Discuss replyToDiscuss = dcs.byID(replyToDiscussID);
				dc.replyToDiscuss = replyToDiscuss;
				dc.replyToDiscussID = replyToDiscussID;
				
				dc.replyToUser = replyToDiscuss.fromUser;
				dc.replyToUserID = replyToDiscuss.fromUser!=null?replyToDiscuss.fromUser.id.toString():null;
				
				dcs.save(dc);
				
				//被回复消息技术++ 回复list +1
				replyToDiscuss.replyTimes++;
				replyToDiscuss.add(dc);
				dcs.save(replyToDiscuss);
				
				//如果被回复消息 不是主消息， 主消息 计数++
				if(!replyToDiscuss.isPrimary && replyToDiscuss.primaryDiscuss!=null){
					replyToDiscuss.primaryDiscuss.lastModifyDate = U.dateTime();
					replyToDiscuss.primaryDiscuss.replyTimes++;
					dcs.save(replyToDiscuss.primaryDiscuss);
				}
			}
			
			//所属
			dc.sourceBoardName = lk.title;
			dc.sourceBoardType = C.sourceBoardType_link;
			dc.sourceBoardID = linkID;
			
			dcs.save(dc);
			
			//保存评论到 link 里
			if(dc.isPrimary){
				lk.commentsCount++;
				lk.lastDiscuss = message;
				lk.add(dc);
				ls.save(lk);
			}
			
			U.resSuccess(res);
		} catch (BaseException e) {
			U.resFailed(res, e.getMessage());
		}
	}
	
	@RequestMapping("/group/create")
	protected void createForGroup(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		DiscussService dcs = new DiscussService();
		String message = U.filterCharacter(req.getParameter("message")) ;
		//String sourceID = U.filterCharacter(req.getParameter("sourceID")) ;
		String groupID = U.filterCharacter(req.getParameter("groupID")) ;
		String replyToDiscussID = U.filterCharacter(req.getParameter("replyToDiscussID")) ;
		
		try {
			new CommonValidator()
				.isLonger(message, 1, "信息长度需大于1")
				.isShorter(message, 300, "信息需小于150个字符");
			
			InterestGroup g = null;
			InterestGroupService  gs = new InterestGroupService();
			
			//create disccuss for link
			if(StringUtils.isNotBlank(groupID)){
				g = gs.byID(groupID);
			}
			
			if(g==null){
				return;
			}
			
			User user = U.param(req, C.user, User.class);
			
			//消息内容 属性
			Discuss dc = new Discuss();
			dc.lastModifyDate = U.dateTime();
			dc.message = message;
			if(StringUtils.isNotBlank(replyToDiscussID)){
				dc.isPrimary = false;
				dc.isReply = true;
			}
			else{
				dc.isPrimary = true;
				dc.isReply = false;
			}
			
			//从哪来
			dc.fromIP = U.getIpAddr(req);
			dc.fromUser = user;
			dc.userID = U.getUserID(req);
			
			//到哪去
			if(StringUtils.isNotBlank(replyToDiscussID)){
				Discuss replyToDiscuss = dcs.byID(replyToDiscussID);
				dc.replyToDiscuss = replyToDiscuss;
				dc.replyToDiscussID = replyToDiscussID;
				
				dc.replyToUser = replyToDiscuss.fromUser;
				dc.replyToUserID = replyToDiscuss.fromUser!=null?replyToDiscuss.fromUser.id.toString():null;
				
				dcs.save(dc);
				
				//被回复消息技术++ 回复list +1
				replyToDiscuss.replyTimes++;
				replyToDiscuss.add(dc);
				dcs.save(replyToDiscuss);
				
				//如果被回复消息 不是主消息， 主消息 计数++
				if(!replyToDiscuss.isPrimary && replyToDiscuss.primaryDiscuss!=null){
					replyToDiscuss.primaryDiscuss.lastModifyDate = U.dateTime();
					replyToDiscuss.primaryDiscuss.replyTimes++;
					dcs.save(replyToDiscuss.primaryDiscuss);
				}
			}
			
			//所属
			dc.sourceBoardName = g.name;
			dc.sourceBoardType = C.sourceBoardType_group;
			dc.sourceBoardID = groupID;
			
			dcs.save(dc);
			
//			//保存评论到 link 里
//			if(dc.isPrimary){
//				lk.commentsCount++;
//				lk.lastDiscuss = message;
//				lk.add(dc);
//				ls.save(lk);
//			}
			
			U.resSuccess(res);
		} catch (BaseException e) {
			U.resFailed(res, e.getMessage());
		}
	}
	
	//网盘资源搜索页面的评论
	@RequestMapping("/search/create")
	protected void createForSearch(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		DiscussService dcs = new DiscussService();
		String message = U.filterCharacter(req.getParameter("message")) ;
		String replyToDiscussID = U.filterCharacter(req.getParameter("replyToDiscussID")) ;
		
		try {
			new CommonValidator()
				.isLonger(message, 1, "信息长度需大于1")
				.isShorter(message, 300, "信息需小于150个字符");
 
			User user = U.param(req, C.user, User.class);
			
			//消息内容 属性
			Discuss dc = new Discuss();
			dc.lastModifyDate = U.dateTime();
			dc.message = message;
			if(StringUtils.isNotBlank(replyToDiscussID)){
				dc.isPrimary = false;
				dc.isReply = true;
			}
			else{
				dc.isPrimary = true;
				dc.isReply = false;
			}
			
			//从哪来
			dc.fromIP = U.getIpAddr(req);
			dc.fromUser = user;
			dc.userID = U.getUserID(req);
			
			//到哪去
			if(StringUtils.isNotBlank(replyToDiscussID)){
				Discuss replyToDiscuss = dcs.byID(replyToDiscussID);
				dc.replyToDiscuss = replyToDiscuss;
				dc.replyToDiscussID = replyToDiscussID;
				
				dc.replyToUser = replyToDiscuss.fromUser;
				dc.replyToUserID = replyToDiscuss.fromUser!=null?replyToDiscuss.fromUser.id.toString():null;
				
				dcs.save(dc);
				
				//被回复消息计数++ 回复list +1
				replyToDiscuss.replyTimes++;
				replyToDiscuss.add(dc);
				dcs.save(replyToDiscuss);
				
				//如果被回复消息 不是主消息， 主消息 计数++
				if(!replyToDiscuss.isPrimary && replyToDiscuss.primaryDiscuss!=null){
					replyToDiscuss.primaryDiscuss.lastModifyDate = U.dateTime();
					replyToDiscuss.primaryDiscuss.replyTimes++;
					dcs.save(replyToDiscuss.primaryDiscuss);
				}
			}
			
			//所属
			dc.sourceBoardName = "百度盘找资源";
			dc.sourceBoardType = C.sourceBoardType_pan;
			dc.sourceBoardID = C.sourceBoardType_pan;
			
			dcs.save(dc);
			
			U.resSuccess(res);
		} catch (BaseException e) {
			U.resFailed(res, e.getMessage());
		}
	}
	
	//目前所有评论都集中在一起，理论上不区分板块
	//为了区分评论所属板块，把板块名称从前台传过来
	//评论也不保存进所属板块，也不做板块的统计。
	@RequestMapping("/all/create")
	protected void createForAll(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		DiscussService dcs = new DiscussService();
		String message = U.filterCharacter(req.getParameter("message")) ;
		String replyToDiscussID = U.filterCharacter(req.getParameter("replyToDiscussID")) ;
		
		String sourceBoardType =  U.filterCharacter(req.getParameter("sourceBoardType")) ;
		String sourceBoardName=  U.filterCharacter(req.getParameter("sourceBoardName")) ;
		String sourceBoardID=  U.filterCharacter(req.getParameter("sourceBoardID")) ;
		
		try {
			new CommonValidator()
				.isLonger(message, 1, "信息长度需大于1")
				.isShorter(message, 300, "信息需小于150个字符");
 
			User user = U.param(req, C.user, User.class);
			
			//消息内容 属性
			Discuss dc = new Discuss();
			dc.lastModifyDate = U.dateTime();
			dc.message = message;
			if(StringUtils.isNotBlank(replyToDiscussID)){
				dc.isPrimary = false;
				dc.isReply = true;
			}
			else{
				dc.isPrimary = true;
				dc.isReply = false;
			}
			
			//从哪来
			dc.fromIP = U.getIpAddr(req);
			dc.fromUser = user;
			dc.userID = U.getUserID(req);
			
			//所属
			dc.sourceBoardName = sourceBoardName;
			dc.sourceBoardType = sourceBoardType;
			dc.sourceBoardID = sourceBoardID;
			
			//到哪去
			if(StringUtils.isNotBlank(replyToDiscussID)){
				Discuss replyToDiscuss = dcs.byID(replyToDiscussID);
				dc.replyToDiscuss = replyToDiscuss;
				dc.replyToDiscussID = replyToDiscussID;
				
				dc.replyToUser = replyToDiscuss.fromUser;
				dc.replyToUserID = replyToDiscuss.fromUser!=null&&replyToDiscuss.fromUser.id!=null?replyToDiscuss.fromUser.id.toString():null;
				
				//不是主贴，需 记录 primaryDiscuss，primaryDiscussID
				if(replyToDiscuss.isPrimary){//回复的是主题，则primarydiscuss就是主贴本身
					dc.primaryDiscuss = dc.replyToDiscuss;
					dc.primaryDiscussID = replyToDiscussID;
				}
				else{//回复的不是主贴，primarydiscuss是回复贴的主贴
					dc.primaryDiscuss = replyToDiscuss.primaryDiscuss;
					dc.primaryDiscussID = replyToDiscuss.primaryDiscussID;
				}
				dcs.save(dc);
				
				//被回复消息计数++ 回复list +1
				replyToDiscuss.replyTimes++;
				replyToDiscuss.add(dc);
				dcs.save(replyToDiscuss);
				
				//如果被回复消息 不是主消息， 主消息 计数++
				if(!replyToDiscuss.isPrimary && replyToDiscuss.primaryDiscuss!=null){
					replyToDiscuss.primaryDiscuss.replyTimes++;
					replyToDiscuss.primaryDiscuss.lastModifyDate = U.dateTime();
					dcs.save(replyToDiscuss.primaryDiscuss);
				}
				else if(  replyToDiscuss.primaryDiscuss!=null){//是主贴
					replyToDiscuss.primaryDiscuss.lastModifyDate = U.dateTime();
					dcs.save(replyToDiscuss.primaryDiscuss);
				}
			}
			
			dcs.save(dc);
			
			//U.resSuccess(res);
			if(dc.isPrimary){
				U.forward(req, res, "/part/discuss/discuss-element-group.jsp?primaryDiscussID="+dc.id.toString());
			}else{
				U.forward(req, res, "/part/discuss/discuss-element.jsp?discussID="+dc.id.toString());
			}
			AppCache.discussesClear();//清空缓存 这样用户会实时看到自己发的帖子，如果发帖频率很频繁的话 就需改进
			
		} catch (BaseException e) {
			U.resFailed(res, e.getMessage());
		}
		 
	}
}
