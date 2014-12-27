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
@RequestMapping( value = {"/public"}  )
public class IndexPublicContoller extends HttpServlet {
	
	@RequestMapping()
	public void index(HttpServletRequest req, HttpServletResponse res) {
			U.forward(req, res, "/index-public.jsp");
	}
 
	 
	
}
