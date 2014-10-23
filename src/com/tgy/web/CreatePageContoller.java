package com.tgy.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tgy.dao.PageDao;
import com.tgy.entity.Page;
import com.tgy.util.U;

@WebServlet("/page/create/")
public class CreatePageContoller extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		  Page page = U.fromReqJson(req, Page.class);

			new PageDao().saveWithRef(page);
			
			U.resSuccess(res);
	}

}
