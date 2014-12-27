package com.tgy.web;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

@WebFilter("/*")
public class CommonFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;

		//301 redirect
		String requestPage = req.getRequestURI();   
		String queryString = (req.getQueryString() == null ? "" : "?"+req.getQueryString());   

		//attempt to merge non-www urls   
		if(req.getRequestURL().indexOf("http://webhezi.com") >=0){
			res.setStatus(301);   
			res.setHeader( "Location", "http://www.webhezi.com"+requestPage+queryString);   
			res.setHeader( "Connection", "close" );   
		}
		
		//System.out.println("EncodingFilter : default charset is "+Charset.defaultCharset());
		
		//System.out.println("do filter");

		// only userful when it is a post request
		// if it is a get request, the charset is set by
		// container(tomcat,default iso-8859-1)
		req.setCharacterEncoding("UTF-8");
		/*
		Map<String, String[]> map = req.getParameterMap();
		for(String key : map.keySet()){
			String values[] = map.get(key);
			
			for( int i=0 ;i<values.length;i++){
				values[i] = StringUtils.replace(values[i], "(","");
				values[i] = StringUtils.replace(values[i], ")","");
				values[i] = StringUtils.replace(values[i], "<","");
				values[i] = StringUtils.replace(values[i], ">","");
				values[i] = StringUtils.replace(values[i], "'","");
				values[i] = StringUtils.replace(values[i], ";","");
				//values[i] = StringUtils.replace(values[i], "%","");
				//values[i] = StringUtils.replace(values[i], "&","");
			}
		}
		*/
		
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
