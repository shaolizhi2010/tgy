package com.tgy.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tgy.dao.UserDao;
import com.tgy.entity.Online;
import com.tgy.entity.User;
import com.tgy.service.OnlineService;
import com.tgy.service.UserService;
import com.tgy.util.C;
import com.tgy.util.MD5Util;
import com.tgy.util.U;
import com.tgy.validator.CommonValidator;

@WebServlet("/user/login")
public class LoginContoller extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		String name = U.filterCharacter(req.getParameter("name")) ;
		String password = U.filterCharacter(req.getParameter("password"));
		
		try {
			new CommonValidator().isNotNull(name, null)
				.isNotNull(password, null)
				.isShorter(name, 20, "用户名过长")
				.isLonger(name, 0, "请输入用户名")
				.isShorter(password, 40, "密码过长")
				.isLonger(password, 0, "请输入密码");
		} catch (Exception e) {
			U.resFailed(res, e.getMessage());
		}
		
		password = MD5Util.toMD5(password);
		
		UserService userService = new UserService();
		User loginUser = userService.login(name,password);
		 
		if (loginUser != null) {

			Cookie cookie = new Cookie("lastLoginUserID", loginUser.id.toString());
			cookie.setPath("/");
			cookie.setMaxAge(Integer.MAX_VALUE);
			res.addCookie(cookie);
			
			Cookie cookie2 = new Cookie("lastPsCode",  loginUser.password );
			cookie2.setPath("/");
			cookie2.setMaxAge(Integer.MAX_VALUE);
			res.addCookie(cookie2);
 
			// login success
			//U.refreshSession(req.getSession());
			req.getSession().invalidate();
			req.getSession().setAttribute(C.userID, loginUser.id.toString());
			req.getSession().setAttribute(C.user, loginUser);
 
			
			//U.forward(req, res, "/"+loginUser.name);
			 
			U.resSuccess(res);
			
			OnlineService os = new OnlineService();
			Online online = new Online();
			online.userID = loginUser.id.toString();
			online.visitTimestamp = System.currentTimeMillis();
			os.save(online);
			
			loginUser.loginTimes++;
			loginUser.lastLoginDate = U.dateTime();
			userService.save(loginUser);
			
		} else {
			U.message(res, "用户名或密码错误");
			return;
		}
	}

}
