package com.tgy.web;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tgy.entity.Page;
import com.tgy.entity.User;
import com.tgy.service.PageService;
import com.tgy.service.UserService;
import com.tgy.util.U;

@RestController
@RequestMapping( value = {"/fulidou" }  )
public class FulidouContoller extends HttpServlet {
	
	
	@RequestMapping(value = {"/consume"} )
	public void consume(HttpServletRequest req, HttpServletResponse res) {
		
		String pageID = req.getParameter("pageID");
		if(StringUtils.isBlank(pageID)  ){
			return;
		}
		
		PageService ps = new PageService();
		
		Page page = ps.byID(pageID);
		if(page==null || page.id==null){
			return;
		}
		
		UserService us = new UserService();
		
		//查看改链接的用户， 登陆用户 
		String viewUserID = U.getUserID(req);
		
		
		if(StringUtils.isNotBlank(viewUserID)){ 
			
			if(viewUserID.equals(page.userID)){//同一个用户
				return;
			}
			
			//登录用户 扣掉福利豆
			User viewuser = us.getByID(viewUserID);
			viewuser.fulidou -= page.needFulidou;
			us.save(viewuser);
		}
		
		//发布分享者得到福利豆
		if(StringUtils.isNotBlank(page.userID)){
			User shareUser = us.getByID(page.userID);
			shareUser.fulidou += page.needFulidou;
			us.save(shareUser);
		}
		
		
	}
}
