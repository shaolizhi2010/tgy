package com.tgy.web;

import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tgy.dao.UserDao;
import com.tgy.entity.User;
import com.tgy.entity.group.InterestGroupPage;
import com.tgy.service.group.InterestGroupPageService;
import com.tgy.util.ConditionMap;
import com.tgy.util.PageType;
import com.tgy.util.U;

@RestController
@RequestMapping( value = {"/"}  )
public class IndexContoller extends HttpServlet {
	
	@RequestMapping()
	public void index(HttpServletRequest req, HttpServletResponse res,
			@CookieValue(value = "lastViewUserID", defaultValue = "",required  = false) String lastViewUserID,
			@CookieValue(value = "lastLoginUserID", defaultValue = "",required  = false) String lastLoginUserID,
			@CookieValue(value = "lastPsCode", defaultValue = "",required  = false) String lastPsCode
			) {
		
//		if(StringUtils.isBlank(lastLoginUserID)){
//			//用户从没登录过 也没创建过快速体验书签，显示 ‘创建收藏夹按钮’ 和 ‘体验一下按钮’
//			req.setAttribute("isNewUser", "true");
//		}
		
		if(StringUtils.isBlank(lastLoginUserID) ){//第一次访问 && StringUtils.isBlank(lastViewUserID)
			
			//U.forward(req, res, "/公用导航");
			U.forward(req, res, "/index-hot-user.jsp");
			return;
		}
		else{
			try {
				String userID = "";
				if(StringUtils.isNotBlank(lastLoginUserID)  ){ //如有登录id和密码 默认使用登录userid，temp user的密码未空
					userID = lastLoginUserID;
					
				}
//				else if(StringUtils.isNotBlank(lastViewUserID)){//如没有登录userid，使用 上次看过的userid
//					userID = lastViewUserID;
//				}
				
				User user = new UserDao().getByID(userID);
				if(user==null){
					U.forward(req, res, "/index-hot-user.jsp");
					//U.forward(req, res, "/公用导航"); 
					return;
				}
				else{
					U.forward(req, res, "/"+user.name);
					return;
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		 
	}
	
	/*
	 * 用户点击了 我的收藏 按钮，
	 * 吧用户分为以下几种
	 * 1 从未登录过，点击 ‘我的收藏’
	 * 2 登录过，但现在没有登录，点击我的收藏
	 * 3 已经登陆了，点击 我的收藏
	 * 
	 */
	@RequestMapping(value = {"/me"} )
	public void me(HttpServletRequest req, HttpServletResponse res,
			@CookieValue(value = "lastViewUserID", defaultValue = "",required  = false) String lastViewUserID,
			@CookieValue(value = "lastLoginUserID", defaultValue = "",required  = false) String lastLoginUserID,
			@CookieValue(value = "lastPsCode", defaultValue = "",required  = false) String lastPsCode
			) {
		
		if(StringUtils.isBlank(lastLoginUserID)){
			//用户从没登录过 
			req.setAttribute("isNewUser", "true");
		}
		String showType = req.getParameter("t");//1 竖版显示 2横板（个人导航）显示 3 横板 公共导航页
		if(StringUtils.isBlank(showType)){
			showType = "1";
		}
		if(StringUtils.isBlank(lastLoginUserID) ){//没登陆过 && StringUtils.isBlank(lastViewUserID)
			
			//U.forward(req, res, "/公用导航");
			U.forward(req, res, "/index-"+showType+".jsp");
			return;
		}
		else{
			try {
				String userID = "";
				if(StringUtils.isNotBlank(lastLoginUserID)  ){ //如有登录id和密码 默认使用登录userid，temp user的密码未空
					userID = lastLoginUserID;
					
				}
//				else if(StringUtils.isNotBlank(lastViewUserID)){//如没有登录userid，使用 上次看过的userid
//					userID = lastViewUserID;
//				}
				
				User user = new UserDao().getByID(userID);
				if(user==null){
					U.forward(req, res, "/index-"+showType+".jsp");
					//U.forward(req, res, "/公用导航"); 
					return;
				}
				else{
					U.forward(req, res, "/"+user.name);
					return;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		 
	}
	
	@RequestMapping(value = {"/article"} )
	public void article(HttpServletRequest req, HttpServletResponse res) {
		int start = NumberUtils.toInt(req.getParameter("start"));
		InterestGroupPageService ps = new InterestGroupPageService();
		List<InterestGroupPage> pages = ps.list(new ConditionMap().add("type", PageType.article).add("isShare", true), "-createDate",  10);
		req.setAttribute("pages", pages);
		req.setAttribute("type", "article");
		U.forward(req, res, "/index-all.jsp");
	}
	
}
