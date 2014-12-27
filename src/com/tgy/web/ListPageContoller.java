package com.tgy.web;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.tgy.dao.FolderDao;
import com.tgy.entity.Folder;
import com.tgy.entity.Page;
import com.tgy.exception.BaseException;
import com.tgy.service.PageService;
import com.tgy.util.U;
import com.tgy.validator.CommonValidator;

//TODO
@WebServlet("/page/list")
public class ListPageContoller extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		String pid = U.filterCharacter(req.getParameter("pid"));
		
		try {
			
			new CommonValidator().isNotNull(pid, "未找到网址");
			
			U.resSuccess(res);
		} catch (BaseException e) {
			e.printStackTrace();
			U.resFailed(res, e.getMessage());
		}
		

	}

}
