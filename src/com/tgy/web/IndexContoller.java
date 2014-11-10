package com.tgy.web;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tgy.dao.UserDao;
import com.tgy.entity.User;
import com.tgy.util.U;

@RestController
@RequestMapping( value = {"/"}  )
public class IndexContoller extends HttpServlet {
	
	@RequestMapping( method = RequestMethod.GET )
	public void index(HttpServletRequest req, HttpServletResponse res,@CookieValue(value = "lastViewUserID", defaultValue = "",required  = false) String lastViewUserID
			,@CookieValue(value = "lastLoginUserID", defaultValue = "",required  = false) String lastLoginUserID) {
		
		if(StringUtils.isBlank(lastLoginUserID) && StringUtils.isBlank(lastViewUserID)){//第一次访问
			U.forward(req, res, "/常用网址"); 
			return;
		}
		else{
			try {
				String userID = "";
				if(StringUtils.isNotBlank(lastLoginUserID)){ //如有登录id 默认使用登录userid
					userID = lastLoginUserID;
				}
				else if(StringUtils.isNotBlank(lastViewUserID)){//如没有登录userid，使用 上次看过的userid
					userID = lastViewUserID;
				}
				
				User user = new UserDao().getByID(userID);
				if(user==null){
					U.forward(req, res, "/常用网址"); 
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
 
	 
	
}
