package com.tgy.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tgy.entity.Discuss;
import com.tgy.entity.Page;
import com.tgy.entity.Reply;
import com.tgy.entity.User;
import com.tgy.service.DiscussService;
import com.tgy.service.PageService;
import com.tgy.service.ReplyService;
import com.tgy.service.UserService;
import com.tgy.util.U;

@RestController
@RequestMapping("/admin/webhezi/")
public class AdminContoller extends HttpServlet {
 
	/*
	 * 把用户福利豆置为-1000000，并删除用户发布的最新的的10个资源 发言 和 回复 
	 */
	@RequestMapping("/punish/user")
	protected void punishUser(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		String username =  req.getParameter("username");
		
		if(StringUtils.isBlank(username)){
			U.resFailed(res, "未找到用户");
			return;
		}
		
		UserService us = new UserService();

		User u = us.getByName(username);
		if(u==null || u.id==null){
			U.resFailed(res, "未找到用户");
			return;
		}
		
		if( u.isAdmin==true || username.equalsIgnoreCase("aaa") || username.equalsIgnoreCase("网址盒子")){
			U.resFailed(res, "无法处理该用户");
			return;
		}
		
		u.fulidou = -1000000;
		us.save(u);
		
		PageService ps = new PageService();
		
		List<Page> pages = ps.list(u.id.toString(), "-createDate", 10);//最多10条 防止误删除
		
		for(Page p : pages ){
			ps.delete(p);
		}
		
		ReplyService rs = new ReplyService();
		
		List<Reply> replies = rs.byUserID(u.id.toString());
		
		int deleteRepliesCount = 0;
		for(Reply r : replies ){
			rs.delete(r);
			deleteRepliesCount++;
			if(deleteRepliesCount>10){
				break; //最多删10个
			}
		}
		
		DiscussService ds = new DiscussService();
		List<Discuss> discusses = ds.byUserID(u.id.toString());
		
		int deleteDiscussCount = 0;
		for(Discuss d : discusses ){
			ds.delete(d);
			deleteDiscussCount++;
			if(deleteDiscussCount>10){
				break; //最多删10个
			}
		}
		
		U.resSuccess(res);
	
		
		
		
	}
	 

}
