package com.tgy.web.group;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import com.tgy.entity.User;
import com.tgy.entity.group.InterestGroup;
import com.tgy.util.AuthManager;
import com.tgy.util.C;
import com.tgy.util.U;

public abstract class BaseGroupContoller extends HttpServlet {
 
	//公用权限检查模块
	public boolean commonAuth(HttpServletRequest req, InterestGroup group,int authCode) {
		AuthManager authManager = new AuthManager();
		User loginUser =  U.param(req, C.user, User.class);
		return authManager.auth(loginUser, group, authCode);
	
	}

}
