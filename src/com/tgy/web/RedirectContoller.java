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

/**
 * 配置某些例外情况
 * @author qq
 *
 */
@RestController
@RequestMapping( value = {"/"}  )
public class RedirectContoller extends HttpServlet {
	
	@RequestMapping(value = {"/jd_root.txt"}   )
	public void jd(HttpServletRequest req, HttpServletResponse res
			) {
 
		U.message(res, "e95d2f4a675fe6f26909a1b1390db96f");
	}
	@RequestMapping( value = {"/gome_5780.txt"}  )
	public void gome(HttpServletRequest req, HttpServletResponse res
			) {
 
		U.message(res, "20E588D3FE408A9B1B94EBC034731350");
	}
	 
	
}
