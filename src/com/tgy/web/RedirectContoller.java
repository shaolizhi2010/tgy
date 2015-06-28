package com.tgy.web;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tgy.App;
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
	@RequestMapping( value = {"/root.txt"}  )
	public void alimama(HttpServletRequest req, HttpServletResponse res
			) {
 
		U.message(res, "7153a9607a767e7fe3c231da8ab64f91");
	}
	@RequestMapping( value = {"/Sitemap.txt","/sitemap.txt"}  )
	public void sitemap(HttpServletRequest req, HttpServletResponse res
			) {
		String sitemapString;
		try {
			sitemapString = FileUtils.readFileToString(new File(App.basePath+"Sitemap.txt"),"utf-8");
			U.message(res, sitemapString);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		U.message(res, "");
		
	}
	 
	
}
