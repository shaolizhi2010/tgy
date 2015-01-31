//package com.tgy.web;
//
//import java.util.List;
//
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.commons.lang3.StringUtils;
//import org.apache.commons.lang3.math.NumberUtils;
//import org.springframework.web.bind.annotation.CookieValue;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.tgy.dao.UserDao;
//import com.tgy.entity.Page;
//import com.tgy.entity.User;
//import com.tgy.service.PageService;
//import com.tgy.util.ConditionMap;
//import com.tgy.util.PageType;
//import com.tgy.util.U;
//
//@RestController
//@RequestMapping( value = {"/share","/share/"}  )
//public class ShareContoller extends HttpServlet {
//	
//	
////	@RequestMapping(value = {"/all"} )
////	public void all(HttpServletRequest req, HttpServletResponse res) {
////		int start = NumberUtils.toInt(req.getParameter("start"));
////		PageService ps = new PageService();
////		List<Page> pages = ps.list(new ConditionMap().add("isShare", true), "-createDate", start,10);
////		long count = ps.count(ps.createQuery(new ConditionMap().add("isShare", true), "-createDate", 0,0));
////		req.setAttribute("pages", pages);
////		//req.setAttribute("type", PageType.all);
////		req.setAttribute("tagName", "全部");
////		req.setAttribute("start", start);
////		req.setAttribute("count", count);
////		req.setAttribute("keywordForMeta", "全部网址分享");
////		U.forward(req, res, "/index-homepage.jsp");
////	}
//	
//	@RequestMapping(value = {"/",""} )
//	public void all(HttpServletRequest req, HttpServletResponse res) {
//		
//		int start = NumberUtils.toInt(req.getParameter("start"));
//		
//		PageService ps = new PageService();
//		List<Page> pages = ps.list(new ConditionMap(). add("isShare", true), "-createDate", start,10);
//		long count = ps.count(ps.createQuery(new ConditionMap(). add("isShare", true), "-createDate", 0,0));
//		req.setAttribute("pages", pages);
//		//req.setAttribute("tagName", tagName);
//		req.setAttribute("type", req.getParameter("type"));
//		req.setAttribute("start", start);
//		req.setAttribute("count", count);
//		req.setAttribute("keywordForMeta", "网址分享");
//	 
//		U.forward(req, res, "/index-homepage.jsp");
//	}
//			
//	
//	@RequestMapping(value = {"/{tagName}"} )
//	public void tag(HttpServletRequest req, HttpServletResponse res,
//			@PathVariable("tagName") String tagName) {
//		
//		int start = NumberUtils.toInt(req.getParameter("start"));
//		if(StringUtils.isBlank(tagName)){
//			tagName = null;
//		}
//		
//		PageService ps = new PageService();
//		List<Page> pages = ps.list(new ConditionMap().add("tagName", tagName).add("isShare", true), "-createDate", start,10);
//		long count = ps.count(ps.createQuery(new ConditionMap().add("tagName", tagName).add("isShare", true), "-createDate", 0,0));
//		req.setAttribute("pages", pages);
//		req.setAttribute("tagName", tagName);
//		req.setAttribute("type", req.getParameter("type"));
//		req.setAttribute("start", start);
//		req.setAttribute("count", count);
//		req.setAttribute("keywordForMeta", tagName+"网址分享");
//		
//		if(StringUtils.isBlank(tagName)){
//			U.forward(req, res, "/index-homepage.jsp");
//		}else{
//			U.forward(req, res, "/index-all.jsp");	
//		}
//			
//		
//	}
//	
////	@RequestMapping(value = {"/baidupan"} )
////	public void baidupan(HttpServletRequest req, HttpServletResponse res) {
////		int start = NumberUtils.toInt(req.getParameter("start"));
////		PageService ps = new PageService();
////		List<Page> pages = ps.list(new ConditionMap().add("type", PageType.baidupan).add("isShare", true), "-createDate", start,10);
////		long count = ps.count(ps.createQuery(new ConditionMap().add("type", PageType.baidupan).add("isShare", true), "-createDate", 0,0));
////		req.setAttribute("pages", pages);
////		req.setAttribute("type", PageType.baidupan);
////		req.setAttribute("start", start);
////		req.setAttribute("count", count);
////		req.setAttribute("keywordForMeta", PageType.baidupan.value()+"网址分享");
////		U.forward(req, res, "/index-all.jsp");
////	}
//}
