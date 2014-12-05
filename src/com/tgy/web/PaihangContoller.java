package com.tgy.web;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tgy.util.U;

@RestController
@RequestMapping( value = {"/paihang"}  )
public class PaihangContoller extends HttpServlet {
	
	@RequestMapping( method = RequestMethod.GET )
	public void index(HttpServletRequest req, HttpServletResponse res,@CookieValue(value = "lastViewUserName", defaultValue = "",required  = false) String userName) {
		
		U.forward(req, res, "/paihang.jsp");
	}
 
	 
	
}
