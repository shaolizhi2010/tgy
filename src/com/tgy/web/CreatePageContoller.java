package com.tgy.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tgy.entity.Page;
import com.tgy.exception.BaseException;
import com.tgy.service.PageService;
import com.tgy.util.U;

@WebServlet("/page/create/")
public class CreatePageContoller extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		Page page = U.fromReqJson(req, Page.class);
		PageService pService =new PageService();
		
		try {
			pService.save(page);
			U.refreshSession(req.getSession());
			U.resSuccess(res);
		} catch (BaseException e) {
			e.printStackTrace();
			U.message(res, e.getMessage());
		}
		

	}

}
