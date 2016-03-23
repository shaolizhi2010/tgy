package com.tgy.web;

import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tgy.entity.Discuss;
import com.tgy.entity.Page;
import com.tgy.entity.Reply;
import com.tgy.entity.User;
import com.tgy.exception.BaseException;
import com.tgy.service.ReplyService;
import com.tgy.service.PageService;
import com.tgy.service.UserService;
import com.tgy.util.C;
import com.tgy.util.ConditionMap;
import com.tgy.util.FuliDou;
import com.tgy.util.U;

@RestController
@RequestMapping( value = {"/reply"}  )
public class ReplyContoller extends HttpServlet {
	
	@RequestMapping(value = {"/create"} )
	public void create(HttpServletRequest req, HttpServletResponse res) {
		
		String message = req.getParameter("message");
		String replyToPageID = req.getParameter("replyToPageID");
		
		User user = U.param(req, C.user, User.class);
		
		if(user==null || user.id == null ){
			U.resFailed(res, "需登录哦亲");
			return;
		}
		
		ReplyService svs = new ReplyService();
		
		if(user.totalOnlineTime< 30){//累计在线时长n分钟以上用户
//			List<Reply> list = svs.byUserID(user.id.toString());
//			
//			if(list.size()>=3){
//				U.resFailed(res, "新用户暂时最多回复三次哦亲~ 在站内多逛会吧");
//				return;
//			}
			
			U.resFailed(res, "您累计在线时间为"+user.totalOnlineTime+"分钟,需大于30分钟才能发布哦亲");
			return;
			
		}

		Reply entity = new Reply();
		entity.content = message;
		entity.toID = replyToPageID;
		
		//从哪来
		
		entity.fromIP = U.getIpAddr(req);
		entity.fromUser = user;
		entity.userID = U.getUserID(req);
		
		
		svs.save(entity);
		
		List<Reply> list = svs.list(Reply.class, new ConditionMap().add("toID", replyToPageID), "-createDate", 3);

		String json = U.toJson(list);
		
		U.message(res, json);
		
		
		//update page
		PageService ps = new PageService();
		Page p = ps.byID(replyToPageID);
		p.lastReplyTime = U.dateTime();
		p.lastModifyDate = U.dateTime();
		
		try {
			ps.save(p);
		} catch (BaseException e) {
		}
		
		//回复一次 送n 福利豆
		if(user!=null){
			user.fulidou += FuliDou.replyScore;
			new UserService().save(user);
		}
		
		 
	}
	
	@RequestMapping(value = {"/list"} )
	public void list(HttpServletRequest req, HttpServletResponse res) {
		
		String pageID = req.getParameter("pageID");
		String limit = req.getParameter("limit");
		
		int limitInt = 0;
		try {
			limitInt = Integer.parseInt(limit);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		ReplyService svs = new ReplyService();
		List<Reply> list = svs.list(Reply.class, new ConditionMap().add("pageID", pageID), "-createDate", limitInt);

		String json = U.toJson(list);
		
		U.message(res, json);
		 
	}
	
	  
}
