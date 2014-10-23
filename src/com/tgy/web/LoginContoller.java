package com.tgy.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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

@WebServlet("/user/login/")
public class LoginContoller extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		User user = U.fromReqJson(req, User.class);
		 
		User loginUser = new UserDao().login(user);
		 
		if (loginUser != null) {
			// login success
			req.getSession().invalidate();
			req.getSession().setAttribute(C.userID, loginUser.id);
			req.getSession().setAttribute("user", loginUser);
			U.message(resp, "登录成功");
		} else {
			U.message(resp, "用户名或密码错误");
		}
	}

}
