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
import com.tgy.entity.InterestGroup;
import com.tgy.entity.User;
import com.tgy.entity.VisitHistory;
import com.tgy.service.IndexService;
import com.tgy.service.InterestGroupService;
import com.tgy.service.UserService;
import com.tgy.service.VisitHistoryService;
import com.tgy.util.C;
import com.tgy.util.U;
import com.tgy.web.vo.BookmarkData;

@RestController
@RequestMapping(value = { "/g/" })
public class GroupContoller extends HttpServlet {

	IndexService indexService = new IndexService();

	@RequestMapping(value = { "/{groupID}" })
	protected void dealByID(
			HttpServletRequest req,
			HttpServletResponse res,
			@PathVariable("groupID") String groupID
	) throws ServletException, IOException {
		if ("favicon".equals(groupID)) {
			return;
		}
 

		InterestGroup group = new InterestGroupService().byID(groupID);
		req.setAttribute("group", group);
		U.forward(req, res, "/index-group.jsp");
	}
 
}
