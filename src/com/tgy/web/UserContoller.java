package com.tgy.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tgy.dao.FolderDao;
import com.tgy.dao.UserDao;
import com.tgy.entity.Folder;
import com.tgy.entity.Page;
import com.tgy.entity.User;
import com.tgy.service.FolderService;
import com.tgy.service.IndexService;
import com.tgy.service.UserService;
import com.tgy.util.BreadCrumbUtil;
import com.tgy.util.FolderUtil;
import com.tgy.util.U;
import com.tgy.vo.BreadCrumb;
import com.tgy.web.vo.BookmarkData;

@RestController
@RequestMapping( value = {"/"}  )
public class UserContoller extends HttpServlet {

	IndexService indexService = new IndexService();
	
	@RequestMapping(value = { "/{userName}", "/{userName}/" })
	protected void service(HttpServletRequest req, HttpServletResponse res,
			@PathVariable("userName") String userName) throws ServletException,
			IOException {
		
		User user = new UserDao().getByName(userName);
		if(user!=null){
			Cookie cookie = new Cookie("lastViewUserID", user.id.toString());
			cookie.setPath("/");
			res.addCookie(cookie);
			
			BookmarkData data =  indexService.getBookmarkDataByUserID(user.id.toString());
			req.setAttribute("bookmarkData", data);
			
			BreadCrumb bread = BreadCrumbUtil.build(
					data.curFolder,
					req.getContextPath());
			req.setAttribute("bread", bread);
		}
		
		
		
		U.forward(req, res, "/index-1.jsp");
		
	}
}
