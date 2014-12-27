package com.tgy.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tgy.dao.UserDao;
import com.tgy.entity.User;
import com.tgy.entity.VisitHistory;
import com.tgy.service.IndexService;
import com.tgy.service.UserService;
import com.tgy.service.VisitHistoryService;
import com.tgy.util.C;
import com.tgy.util.U;
import com.tgy.web.vo.BookmarkData;

@RestController
@RequestMapping(value = { "/" })
public class UserContoller extends HttpServlet {

	IndexService indexService = new IndexService();

	@RequestMapping(value = { "/u/{userID}" })
	protected void dealByID(
			HttpServletRequest req,
			HttpServletResponse res,
			@PathVariable("userID") String userID,
			@CookieValue(value = "lastLoginUserID", defaultValue = "", required = false) String lastLoginUserID,
			@CookieValue(value = "lastPsCode", defaultValue = "", required = false) String lastPsCode
	) throws ServletException, IOException {
		if ("favicon".equals(userID)) {
			return;
		}
		// if(StringUtils.isBlank(userID) || userID.length()!=24){
		// U.resFailed(res, "用户id不正确");
		// }

		User showUser = new UserDao().getByID(userID);
		service(req, res, lastLoginUserID, lastPsCode, showUser,"1");
	}

	@RequestMapping(value = { "/{userName}" })
	protected void dealByName(
			HttpServletRequest req,
			HttpServletResponse res,
			@PathVariable("userName") String userName,
			@CookieValue(value = "lastLoginUserID", defaultValue = "", required = false) String lastLoginUserID,
			@CookieValue(value = "lastPsCode", defaultValue = "", required = false) String lastPsCode)
			throws ServletException, IOException {
		
		String indexType = req.getParameter("t");
		//redirect
		if(StringUtils.equals(userName, "pan")){
			U.forward(req, res, "/pan.jsp");
			return;
		}
		User showUser = new UserDao().getByName(userName);
		service(req, res, lastLoginUserID, lastPsCode, showUser,indexType);
	}

	protected void service(HttpServletRequest req, HttpServletResponse res,
			String lastLoginUserID, String lastPsCode, User showUser,String indexType)
			throws ServletException, IOException {
		
		UserService userService = new UserService();
		
		if (showUser != null) {
			showUser.showTimes++;
			userService.save(showUser);
			
			BookmarkData data = indexService
					.getBookmarkDataByUserID(showUser.id.toString());
			req.setAttribute("bookmarkData", data);
			
			

			// BreadCrumb bread = BreadCrumbUtil.build(
			// data.folder,
			// req.getContextPath());
			// req.setAttribute("bread", bread);
			req.setAttribute("showUser", showUser);// 当前正在查看哪个user的书签

		}

		// 自动登陆
		User loginUser = U.param(req.getSession(), C.user, User.class);

		if (loginUser == null && StringUtils.isNotBlank(lastLoginUserID)) { // 还未登录
																				// 尝试自动登陆
			if (StringUtils.isBlank(lastPsCode)) { // temp user的情况， 密码为空
				loginUser = new UserDao().getByID(lastLoginUserID);
				if (loginUser != null && loginUser.isTemp) {

				} else {
					loginUser = null;// 如果不是temp用户 又没有密码，则不自动登陆
				}

			} else { // 有密码 正常登录过的用户
				loginUser = new UserDao().checkUserByPsCode(lastLoginUserID,
						lastPsCode);

			}

			if (loginUser != null) {
				
				BookmarkData loginUserBookmarkData = indexService
						.getBookmarkDataByUserID(loginUser.id.toString());
				req.setAttribute("loginUserBookmarkData", loginUserBookmarkData);
				
				
				Cookie cookie = new Cookie("lastViewUserID",
						loginUser.id.toString());
				cookie.setPath("/");
				res.addCookie(cookie);

				req.getSession().invalidate();
				req.getSession().setAttribute(C.user, loginUser);

			}
		}
		// 记录访问历史 访问者(userid)-被访问者(userid)
		if (loginUser!=null &&  loginUser.id != null && showUser != null
				&& showUser.id != null
				&& StringUtils.isNotBlank(showUser.name)) {
			VisitHistoryService vService = new  VisitHistoryService();
			VisitHistory vh = new VisitHistory();
			vh.requestUserID = loginUser.id.toString();
			vh.responseUserID = showUser.id.toString();
			vh.responseUserName = showUser.name;
			vh.lastVisitDateTime = U.dateTime();
			vh.times++;
			vService.save(vh);
		}
		U.forward(req, res, "/index-1.jsp");

	}
}
