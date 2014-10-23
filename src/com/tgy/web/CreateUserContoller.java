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

@WebServlet("/user/create/")
public class CreateUserContoller extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		  User user = U.fromReqJson(req, User.class);

			new UserDao().save(user);
			
			U.resSuccess(res);
	}

}
