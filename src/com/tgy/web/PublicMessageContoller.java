package com.tgy.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tgy.dao.FolderDao;
import com.tgy.entity.Folder;
import com.tgy.entity.User;
import com.tgy.exception.BaseException;
import com.tgy.service.FolderService;
import com.tgy.service.UserService;
import com.tgy.util.C;
import com.tgy.util.U;
import com.tgy.validator.CommonValidator;

@RestController
@RequestMapping("/public/message")
public class PublicMessageContoller extends HttpServlet {

	@RequestMapping( method = RequestMethod.POST )
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		String message = U.filterCharacter(req.getParameter("message")) ;
		String userID = U.filterCharacter(req.getParameter("userID")) ;
		
		try {
			UserService us = new UserService();
			User user = U.param(req, C.user, User.class);
			
			new CommonValidator().isLogin(req, null).isNotNull(userID, null).eq(user.id.toString(), userID, "无权限进行此操作");
			User operateUser = us.getByID(userID);
			user.publicMessage = message;
			us.save(user);
			
			U.resSuccess(res);
		} catch (BaseException e) {
			U.resFailed(res, e.getMessage());
		}

	}

}
