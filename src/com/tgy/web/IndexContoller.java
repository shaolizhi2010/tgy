package com.tgy.web;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tgy.dao.UserDao;
import com.tgy.entity.User;
import com.tgy.util.U;

@RestController
@RequestMapping( value = {"/"}  )
public class IndexContoller extends HttpServlet {
	
	@RequestMapping(  )
	public void index(HttpServletRequest req, HttpServletResponse res,
			@CookieValue(value = "lastViewUserID", defaultValue = "",required  = false) String lastViewUserID,
			@CookieValue(value = "lastLoginUserID", defaultValue = "",required  = false) String lastLoginUserID,
			@CookieValue(value = "lastPsCode", defaultValue = "",required  = false) String lastPsCode
			) {
		
//		if(StringUtils.isBlank(lastLoginUserID)){
//			//用户从没登录过 也没创建过快速体验书签，显示 ‘创建收藏夹按钮’ 和 ‘体验一下按钮’
//			req.setAttribute("isNewUser", "true");
//		}
		
		if(StringUtils.isBlank(lastLoginUserID) && StringUtils.isBlank(lastViewUserID)){//第一次访问
			
			//U.forward(req, res, "/公用导航");
			U.forward(req, res, "/index-hot-user.jsp");
			return;
		}
		else{
			try {
				String userID = "";
				if(StringUtils.isNotBlank(lastLoginUserID)  ){ //如有登录id和密码 默认使用登录userid，temp user的密码未空
					userID = lastLoginUserID;
					
				}
				else if(StringUtils.isNotBlank(lastViewUserID)){//如没有登录userid，使用 上次看过的userid
					userID = lastViewUserID;
				}
				
				User user = new UserDao().getByID(userID);
				if(user==null){
					U.forward(req, res, "/index-hot-user.jsp");
					//U.forward(req, res, "/公用导航"); 
					return;
				}
				else{
					U.forward(req, res, "/"+user.name);
					return;
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		 
	}
 
	 
	
}
