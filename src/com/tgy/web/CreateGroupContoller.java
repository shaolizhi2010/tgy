package com.tgy.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tgy.entity.GroupUser;
import com.tgy.entity.InterestGroup;
import com.tgy.entity.User;
import com.tgy.exception.BaseException;
import com.tgy.service.GroupUserService;
import com.tgy.service.InterestGroupService;
import com.tgy.util.C;
import com.tgy.util.U;
import com.tgy.validator.CommonValidator;

@RestController
@RequestMapping("/group/create")
public class CreateGroupContoller extends HttpServlet {

	@RequestMapping( method = RequestMethod.POST )
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		String name = U.filterCharacter(req.getParameter("name")) ;
		//String userID = U.filterCharacter(req.getParameter("userID")) ;
		
		String authCreateStr = U.filterCharacter(req.getParameter("authCreate")) ;
		if(StringUtils.isBlank(authCreateStr) || NumberUtils.isNumber(authCreateStr)){
			authCreateStr = "3";
		}
		String authUpdateStr = U.filterCharacter(req.getParameter("authUpdate")) ;
		if(StringUtils.isBlank(authUpdateStr) || NumberUtils.isNumber(authUpdateStr) ){
			authUpdateStr = "3";
		}
		
		try {
			User user = U.param(req, C.user, User.class);
			
			name = StringUtils.trim(name);
			new CommonValidator().isLogin(req, null).isLonger(name, 0, "名称不能为空")
				.isShorter(name, 60, "名称需小于60");
			
			GroupUserService guService = new GroupUserService();
			InterestGroupService gService = new InterestGroupService();
			
			GroupUser groupuser = new GroupUser();
			groupuser.user = user;
			groupuser.isSuperAdmin = true;
			groupuser.isAdmin = true;
			groupuser.useAble =true;
			guService.save(groupuser);
			
			InterestGroup group = new InterestGroup();
			group.name = name;
			group.authCreate = Integer.parseInt(authCreateStr);
			group.authUpdate = Integer.parseInt(authUpdateStr);
			group.createDate = U.dateTime();
			group.useAble = true;
			group.add(groupuser);
			
			gService.save(group);
			groupuser.group = group;
			guService.save(groupuser);
			
			U.resSuccess(res);
		} catch (BaseException e) {
			U.resFailed(res, e.getMessage());
		}
	}

}
