package com.tgy.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/sample/")
public class SampleContoller extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		//res.setContentType("text/plain; charset=utf-8");
		
		String p1 = req.getParameter("p1");
		
		res.getOutputStream().write("例子".getBytes());
		res.getOutputStream().write(("p1 ： " + p1) .getBytes());
		res.getOutputStream().flush();
		res.getOutputStream().close();
	}

}
