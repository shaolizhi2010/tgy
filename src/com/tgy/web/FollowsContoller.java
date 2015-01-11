package com.tgy.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tgy.entity.Follows;
import com.tgy.entity.User;
import com.tgy.service.FollowsService;
import com.tgy.service.UserService;
import com.tgy.util.C;
import com.tgy.util.ConditionMap;
import com.tgy.util.U;
import com.tgy.validator.CommonValidator;

/**
 * 关注 粉丝
 * @author qq
 *
 */

@RestController
@RequestMapping(value = { "/follow" })
public class FollowsContoller extends HttpServlet {

	@RequestMapping(value = { "/add"  })
	protected void add(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		UserService us = new UserService();
		FollowsService fs = new FollowsService();
		CommonValidator validator = new CommonValidator();
		
		try {
			User fromUser = U.param(req, C.user, User.class);
			validator.isNotNull(fromUser, "请先登录").isNotNull(fromUser.id, "请先登录");
		
			//被关注人userid
			String toUserID =req.getParameter("toUserID");
			User toUser = us.getByID(toUserID);
			validator.isNotNull(toUser, "不能关注TA").isNotNull(toUser.id, "不能关注TA");
			if(StringUtils.equals(fromUser.id.toString(), toUserID)){
				U.resFailed(res, "不能关注自己");
				return;
			}
			
			if(fs.get(new ConditionMap().add("fromUserID", fromUser.id.toString()).add("toUserID", toUserID))!=null){
				U.resSuccess(res);
				return; //已经关注过，不用再关注了。
			}
			
			toUser.fansCount++;//粉丝加一
			us.save(toUser);
			
			fromUser.followsCount++;//关注加一
			us.save(fromUser);
			
			Follows f =new Follows();
			f.fromUserID = fromUser.id.toString();
			f.toUserID = toUserID;
			fs.save(f);
			
			U.resSuccess(res);
		 
		} catch (Exception e) {
			U.resFailed(res, e.getMessage());
		}
		

	}

}
