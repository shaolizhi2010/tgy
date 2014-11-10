package com.tgy.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tgy.dao.UserDao;
import com.tgy.entity.User;
import com.tgy.util.C;
import com.tgy.util.U;
import com.tgy.validator.CommonValidator;

@WebServlet("/user/create/")
public class CreateUserContoller extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		User user = U.fromReqJson(req, User.class);
		try {
			new CommonValidator().isNotNull(user, null)
					.isLonger(user.name, 1, "用户名长度需大于1")
					.isShorter(user.name, 20, "用户名长度应小于20")
					.isLonger(user.password, 5, "密码长度需大于等于6")
					.isShorter(user.password, 30, "密码长度需小于30");
			User oldUser = new UserDao().getByName(user.name);
			if(oldUser != null){
				U.message(res, "操作失败:用户名已注册了，请换一个试试");
				return;
			}
			
			
		} catch (Exception e) {
			U.message(res, e.getMessage());
			return;
		}

		new UserDao().save(user);
		req.getSession().setAttribute(C.userID, user.id);
		req.getSession().setAttribute("user", user);
		
		
		Cookie cookie = new Cookie("lastLoginUserID", user.id.toString());
		cookie.setPath("/");
		res.addCookie(cookie);

		
		
		U.resSuccess(res);
	}

}
