package com.tgy.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tgy.entity.User;
import com.tgy.service.UserService;
import com.tgy.util.C;
import com.tgy.util.MD5Util;
import com.tgy.util.U;
import com.tgy.validator.CommonValidator;

@WebServlet("/user/quickLogin")
public class QuickLoginContoller extends HttpServlet {

	UserService service = new UserService();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		String name = U.filterCharacter(req.getParameter("quick-name")) ;
		String password = U.filterCharacter(req.getParameter("quick-password"));
		
		try {
			new CommonValidator().isNotNull(name, null)
				.isNotNull(password, null)
				.isShorter(name, 20, "用户名过长")
				.isLonger(name, 0, "请输入用户名")
				.isShorter(password, 40, "密码过长")
				.isLonger(password, 0, "请输入密码");
		} catch (Exception e) {
			U.resFailed(res, e.getMessage());
			return;
		}
		password = MD5Util.toMD5(password);
		
		if(service.getByName(name)==null){ //新用户名，用户注册
			User user = new User();
			user.name = name;
			user.password = password;
			user.createDate = U.dateTime();
			service.save(user);
			req.getSession().setAttribute(C.user, user);

			Cookie cookie = new Cookie("lastLoginUserID", user.id.toString());
			cookie.setPath("/");
			res.addCookie(cookie);
			
			
			Cookie cookie2 = new Cookie("lastPsCode",  user.password );
			cookie2.setPath("/");
			res.addCookie(cookie2);
			
			U.resSuccess(res);
			return;
		}
		
		//老登录
		User loginUser = service.login(name,password);
		 
		if (loginUser != null) {

			Cookie cookie = new Cookie("lastLoginUserID", loginUser.id.toString());
			cookie.setPath("/");
			res.addCookie(cookie);
			
			Cookie cookie2 = new Cookie("lastPsCode",  loginUser.password );
			cookie2.setPath("/");
			res.addCookie(cookie2);
 
			// login success
			//U.refreshSession(req.getSession());
			req.getSession().invalidate();
			//req.getSession().setAttribute(C.userID, loginUser.id);
			req.getSession().setAttribute(C.user, loginUser);
 
			
			//U.forward(req, res, "/"+loginUser.name);
			 
			U.resSuccess(res);
		} else {
			U.message(res, "用户名或密码错误");
			return;
		}
	}

}
