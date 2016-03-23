 
package com.tgy.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
 

@Repository
public class CookieInteceptor extends HandlerInterceptorAdapter {
 

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		System.out.println("in CookieInteceptor");
		
		return super.preHandle(request, response, handler);
	}

}
