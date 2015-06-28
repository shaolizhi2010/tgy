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

import com.tgy.dao.PageDao;
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
		String showType = req.getParameter("t");//1 竖版显示 2横板（个人导航）显示 3 横板 公共导航页
		User showUser = new UserDao().getByID(userID);
		service(req, res, lastLoginUserID, lastPsCode, showUser,showType);//default 2, 导航形式显示
	}

	@RequestMapping(value = { "/{userName}" })
	protected void dealByName(
			HttpServletRequest req,
			HttpServletResponse res,
			@PathVariable("userName") String userName,
			@CookieValue(value = "lastLoginUserID", defaultValue = "", required = false) String lastLoginUserID,
			@CookieValue(value = "lastPsCode", defaultValue = "", required = false) String lastPsCode)
			throws ServletException, IOException {
		
		
		
		String showType = req.getParameter("t");//1 竖版显示 2横板（个人导航）显示 3 横板 公共导航页
		//redirect
		if(StringUtils.equals(userName, "pan") || StringUtils.equals(userName, "pan/")){
			U.forward(req, res, "/pan.jsp");
			return;
		}
		if(StringUtils.equals(userName, "commodity")){
			return;
		}
		if(StringUtils.equals(userName, "null") || StringUtils.isBlank(userName)){
			return;
		}
		if("bdunion".equals(userName)	){
			U.message(res, "e15d3593647ad0fda081e443d32b215b");
			return;
		}
		User showUser = new UserDao().getByName(userName);
		service(req, res, lastLoginUserID, lastPsCode, showUser,showType);
	}

	protected void service(HttpServletRequest req, HttpServletResponse res,
			String lastLoginUserID, String lastPsCode, User showUser,String showType)
			throws ServletException, IOException {
		
		UserService userService = new UserService();
		
		if (showUser != null) {
			showUser.showTimes+=C.scoreClick;
			showUser.favScore+=C.scoreClick;
			userService.save(showUser);
			
			BookmarkData data = indexService
					.getBookmarkDataByUserID(showUser.id.toString());
			
			long pageCount = new PageDao().count("userID", showUser.id.toString());
			
			req.setAttribute("bookmarkData", data);
			req.setAttribute("pageCount", pageCount);
			

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
				//loginUser = new UserDao().getByID(lastLoginUserID);
//				if (loginUser != null && loginUser.isTemp) {
//
//				} else {
//					loginUser = null;// 如果不是temp用户 又没有密码，则不自动登陆
//				}

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
		boolean isSelf = false;
		if(loginUser!=null && showUser!=null && loginUser.id!=null && showUser.id!=null
				&&StringUtils.equals(loginUser.id.toString(), showUser.id.toString())){//查看自己的收藏夹
			isSelf = true;
		}
		//之前登陆过这个收藏夹，说明是自己的收藏夹，只是没有登录
		else if(StringUtils.isNotBlank(lastLoginUserID)&& showUser!=null&& showUser.id!=null&&StringUtils.equals(lastLoginUserID, showUser.id.toString()) ){
			isSelf = true;
		}
		req.setAttribute("isSelf", isSelf);
		
		if(StringUtils.equals(showType, "3")){
			U.forward(req, res, "/index-3.jsp");
		}
		else if(StringUtils.equals(showType, "2")){
			U.forward(req, res, "/index-2.jsp");
		}
		else if(StringUtils.equals(showType, "1")){
			U.forward(req, res, "/index-1.jsp");
		}
		else{
			//已登录 正在查看自己的收藏夹
			if(isSelf){
				U.forward(req, res, "/index-2.jsp");
			}
			else{
				U.forward(req, res, "/index-1.jsp");
			}
		}
		

	}
}
