package com.tgy.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tgy.dao.UserDao;
import com.tgy.entity.User;
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
					.isLonger(user.name, 2, "用户名长度需大于2")
					.isShorter(user.name, 20, "用户名长度应小于20")
					.isLonger(user.password, 6, "密码长度需大于6")
					.isShorter(user.password, 30, "密码长度需小于30");
		} catch (Exception e) {
			U.message(res, e.getMessage());
		}

		new UserDao().save(user);

		U.resSuccess(res);
	}

}
