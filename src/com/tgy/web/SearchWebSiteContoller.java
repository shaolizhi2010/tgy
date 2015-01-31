//package com.tgy.web;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.commons.lang3.StringUtils;
//
//import com.tgy.entity.Page;
//import com.tgy.statistic.entity.Link;
//import com.tgy.statistic.service.LinkService;
//import com.tgy.statistic.service.TagService;
//import com.tgy.util.U;
//
//@WebServlet("/site")
//public class SearchWebSiteContoller extends HttpServlet {
//
//	@Override
//	protected void service(HttpServletRequest req, HttpServletResponse res)
//			throws ServletException, IOException {
//		 
//		String tagName = req.getParameter("tag");
//		if(StringUtils.isNotBlank(tagName)){
//			List<Link> list = new LinkService().searchByTagName(tagName);
//			List<Page> pages = new ArrayList<>();
//			for(Link l : list){
//				Page p = new Page();
//				p.name = l.title;
//				p.url = l.url;
//				pages.add(p);
//			}
//			req.setAttribute("tagName", tagName);
//			req.setAttribute("pages", pages);
//			
//			U.forward(req, res, "/searchPage.jsp");
//		}
//		else{
//			
//		}
//		
//		
//	}
//
//}
