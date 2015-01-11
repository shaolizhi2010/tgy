package com.tgy.web;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.tgy.dao.UserDao;
import com.tgy.entity.User;
import com.tgy.exception.BaseException;
import com.tgy.util.C;
import com.tgy.util.MD5Util;
import com.tgy.util.U;
import com.tgy.validator.CommonValidator;

@WebServlet("/private/setting")
public class EditUserContoller extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {


		String publicMessage = U.filterCharacter(req.getParameter("publicMessage")) ;
		String email = U.filterCharacter(req.getParameter("email")) ;
		String password = U.filterCharacter(req.getParameter("password")) ;
		String authQueryStr = U.filterCharacter(req.getParameter("authQuery")) ;
		String headImgUrl = U.filterCharacter(req.getParameter("headImgUrl")) ;
		User loginUser = U.param(req, C.user, User.class);
		
		try {
			CommonValidator validator =  new CommonValidator();
			validator.isNotNull(loginUser, "需要登陆").isNotNull(loginUser.id, "需要登陆");
			
			validator.isShorter( publicMessage, 200, "签名太长了，需小于100哦");
			loginUser.publicMessage = publicMessage;
			if(StringUtils.isNotBlank(headImgUrl)){
				loginUser.headImgUrl = headImgUrl;
			}
			if(StringUtils.isNotBlank(email)){ //更改了 user password
				validator.isShorter( email, 200, "密码长度需小于200");
				loginUser.email = email;
			}
			if(StringUtils.isNotBlank(password)){ //更改了 user password
				validator.isLonger( password, 5, "密码长度需大于等于6")
					.isShorter( password, 30, "密码长度需小于30");
				loginUser.password = MD5Util.toMD5(password);
			}
			if(StringUtils.isNotBlank(authQueryStr)){
				loginUser.authQuery = Integer.parseInt(authQueryStr);
			}
			
			new UserDao().save(loginUser);
			req.getSession().setAttribute(C.user, loginUser);
			
			
			U.resSuccess(res);
			
		} catch (Exception e) {
			U.message(res, e.getMessage());
			return;
		}

		
	}

}
