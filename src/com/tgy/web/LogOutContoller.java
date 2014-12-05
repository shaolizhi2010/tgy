package com.tgy.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tgy.util.MD5Util;
import com.tgy.util.U;

@WebServlet("/user/logout")
public class LogOutContoller extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		try { 
			

			Cookie cookie = new Cookie("lastLoginUserID", "");
			cookie.setPath("/");
			res.addCookie(cookie);
			
			//cancel auto login
			Cookie cookie2 = new Cookie("lastPsCode", "");
			cookie2.setPath("/");
			res.addCookie(cookie2);
			
			req.getSession().invalidate();
			U.message(res, "操作成功");
		} catch (Exception e) {
		}
		  
	}

}
