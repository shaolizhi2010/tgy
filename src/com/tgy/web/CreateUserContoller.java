package com.tgy.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tgy.dao.UserDao;
import com.tgy.entity.Folder;
import com.tgy.entity.Online;
import com.tgy.entity.Page;
import com.tgy.entity.User;
import com.tgy.exception.BaseException;
import com.tgy.service.FolderService;
import com.tgy.service.InitUserService;
import com.tgy.service.OnlineService;
import com.tgy.service.PageService;
import com.tgy.service.UserService;
import com.tgy.util.C;
import com.tgy.util.MD5Util;
import com.tgy.util.U;
import com.tgy.validator.CommonValidator;

@RestController
@RequestMapping(value = { "/" })
public class CreateUserContoller extends HttpServlet {

	@RequestMapping(value = { "/user/create" })
	protected void createUser(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		User user = new User();
		
		user.name = U.filterCharacter(req.getParameter("name")) ;
		user.password = U.filterCharacter(req.getParameter("password")) ;
		String authCreateStr = U.filterCharacter(req.getParameter("authCreate")) ;
		
		
		try {
			if(StringUtils.isNotBlank(authCreateStr)){
				user.authCreate = Integer.parseInt(authCreateStr);
			}
			new CommonValidator().isNotNull(user, null)
					.isLonger(user.name, 1, "用户名长度需大于1")
					.isShorter(user.name, 20, "用户名长度应小于20")
					.isLonger(user.password, 5, "密码长度需大于等于6")
					.isShorter(user.password, 30, "密码长度需小于30");
			User oldUser = new UserDao().getByName(user.name);
			if (oldUser != null) {
				U.message(res, "操作失败:用户名已注册了，请换一个试试");
				return;
			}

		} catch (Exception e) {
			U.message(res, e.getMessage());
			return;
		}

		user.password = MD5Util.toMD5(user.password);
		user.createDate = U.dateTime();

		UserService userService = new UserService();
		userService.save(user);
		req.getSession().setAttribute(C.userID, user.id.toString());
		req.getSession().setAttribute(C.user, user);
		
//		new InitUserService().initUser(user.id.toString());
		

		Cookie cookie = new Cookie("lastLoginUserID", user.id.toString());
		cookie.setPath("/");
		res.addCookie(cookie);
		
		
		Cookie cookie2 = new Cookie("lastPsCode",  user.password );
		cookie2.setPath("/");
		res.addCookie(cookie2);
		
		U.resSuccess(res);
		
		OnlineService os = new OnlineService();
		Online online = new Online();
		online.userID = user.id.toString();
		online.visitTimestamp = System.currentTimeMillis();
		os.save(online);
		
		user.loginTimes++;
		user.lastLoginDate = U.dateTime();
		
		userService.save(user);
	}

	@RequestMapping(value = { "/user/create/temp" })
	protected void createTempUser(HttpServletRequest req,
			HttpServletResponse res) throws ServletException, IOException {

		String userName =  "";
		UserDao uDao = new UserDao();
		while (true) {
			userName =  U.randomNum(4);
			User oldUser = uDao.getByName(userName);
			if (oldUser == null) {
				break;
			}
		}

		User user = new User();
		user.name = userName;
		user.isTemp = true;
		user.createDate = U.dateTime();

		uDao.save(user);
		req.getSession().setAttribute(C.user, user);

		Cookie cookie = new Cookie("lastLoginUserID", user.id.toString());
		cookie.setPath("/");
		res.addCookie(cookie);
		
		req.setAttribute("showUser", user);//当前正在查看哪个user的书签
		U.forward(req, res, "/index-2.jsp");

		// TODO 临时用户
		/*
		 * try {
		 * 
		 * User user = new UserDao().getByName(user.name); if(oldUser != null){
		 * U.message(res, "操作失败:用户名已注册了，请换一个试试"); return; }
		 * 
		 * 
		 * } catch (Exception e) { U.message(res, e.getMessage()); return; }
		 * 
		 * 
		 * new UserDao().save(user); req.getSession().setAttribute(C.user,
		 * user);
		 * 
		 * Cookie cookie = new Cookie("lastLoginUserID", user.id.toString());
		 * cookie.setPath("/"); res.addCookie(cookie);
		 * 
		 * 
		 * 
		 * U.resSuccess(res);
		 */
	}

}
