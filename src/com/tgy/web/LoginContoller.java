package com.tgy.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;

import com.mongodb.DBObject;
import com.tgy.App;
import com.tgy.dao.UserDao;
import com.tgy.entity.User;
import com.tgy.util.C;
import com.tgy.util.U;
import com.tgy.validator.CommonValidator;

@WebServlet("/user/login/")
public class LoginContoller extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		User user = U.fromReqJson(req, User.class);
		
		try {
			new CommonValidator().isNotNull(user, null)
				.isShorter(user.name, 20, "用户名过长")
				.isLonger(user.name, 0, "请输入用户名")
				.isShorter(user.password, 40, "密码过长")
				.isLonger(user.password, 0, "请输入密码");
		} catch (Exception e) {
			U.resFailed(res, e.getMessage());
		}
		
		User loginUser = new UserDao().login(user);
		 
		if (loginUser != null) {

			Cookie cookie = new Cookie("lastLoginUserID", loginUser.id.toString());
			cookie.setPath("/");
			res.addCookie(cookie);
 
			// login success
			U.refreshSession(req.getSession());
			req.getSession().invalidate();
			req.getSession().setAttribute(C.userID, loginUser.id);
			req.getSession().setAttribute("user", loginUser);
			
			//U.forward(req, res, "/"+loginUser.name);
			 
			U.message(res, "登录成功");
		} else {
			U.message(res, "用户名或密码错误");
			return;
		}
	}

}
