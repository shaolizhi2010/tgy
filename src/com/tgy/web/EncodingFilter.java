package com.tgy.web;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter("/*")
public class EncodingFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {

		//System.out.println("do filter");

		// only userful when it is a post request
		// if it is a get request, the charset is set by
		// container(tomcat,default iso-8859-1)
		req.setCharacterEncoding("UTF-8");
		
		//depends on container, sometime not work 
		res.setCharacterEncoding("UTF-8");
		
		//very useful
		//res.setContentType("text/plain; charset=utf-8");
		
		chain.doFilter(req, res);

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
