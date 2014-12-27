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

@WebServlet("/user/edit")
public class EditUserContoller extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {


		String id = U.filterCharacter(req.getParameter("id")) ;
		String name = U.filterCharacter(req.getParameter("name")) ;
		String password = U.filterCharacter(req.getParameter("password")) ;
		String authCreateStr = U.filterCharacter(req.getParameter("authCreate")) ;
		
		User loginUser = U.param(req, C.user, User.class);
		
		try {
			CommonValidator validator =  new CommonValidator();
			validator.isNotNull(loginUser, null)
					.isNotNull(id, "无法找到要编辑的用户");
					
			
			if(!StringUtils.equals(loginUser.id.toString(), id)){
				throw new BaseException(this, "无权限进行此操作");
			}
			
			if(StringUtils.isNotBlank(name)){ //更改了user name
				validator.isLonger( name, 1, "用户名长度需大于1")
						.isShorter( name, 20, "用户名长度应小于20");
				loginUser.name = name;
			}
			if(StringUtils.isNotBlank(password)){ //更改了 user password
				validator.isLonger( password, 5, "密码长度需大于等于6")
					.isShorter( password, 30, "密码长度需小于30");
				loginUser.password = MD5Util.toMD5(password);
			}
			if(StringUtils.isNotBlank(authCreateStr)){
				loginUser.authCreate = Integer.parseInt(authCreateStr);
			}
			loginUser.isTemp=false;
			
			new UserDao().save(loginUser);
			req.getSession().setAttribute(C.user, loginUser);
			
			
			U.resSuccess(res);
			
		} catch (Exception e) {
			U.message(res, e.getMessage());
			return;
		}

		
	}

}
