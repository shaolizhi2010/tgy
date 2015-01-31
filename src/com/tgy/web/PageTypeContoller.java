package com.tgy.web;

import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tgy.dao.UserDao;
import com.tgy.entity.Page;
import com.tgy.entity.Tag;
import com.tgy.entity.User;
import com.tgy.service.PageService;
import com.tgy.statistic.service.TagService;
import com.tgy.util.ConditionMap;
import com.tgy.util.PageType;
import com.tgy.util.U;

@RestController
@RequestMapping( value = {"/share","/share/"}  )
public class PageTypeContoller extends HttpServlet {
	
	@RequestMapping(value = {"/",""} )
	public void type(HttpServletRequest req, HttpServletResponse res) {
		
		int start = NumberUtils.toInt(req.getParameter("start"));
		String tagName = req.getParameter("tagName");
		if(StringUtils.isBlank(tagName)){
			tagName = null;
		}
		
		TagService ts = new TagService();
		List<Tag> tags = ts.list(null, "-favScore", 0, 20);
		
		
		PageService ps = new PageService();
		List<Page> pages = ps.list(new ConditionMap().add("tagName", tagName). add("isShare", true) , "-createDate", start,10);
		long count = ps.count(ps.createQuery(new ConditionMap().add("tagName", tagName). add("isShare", true), "-createDate", 0,0));
		req.setAttribute("pages", pages);
		req.setAttribute("tags", tags);
		req.setAttribute("tagName", tagName);
		//req.setAttribute("type", null);
		req.setAttribute("start", start);
		req.setAttribute("count", count);
		req.setAttribute("keywordForMeta",tagName+ "网址分享");
	 
		U.forward(req, res, "/index-all.jsp");
	}
	
	
	@RequestMapping(value = {"/{type}"} )
	public void tag(HttpServletRequest req, HttpServletResponse res,
			@PathVariable("type") String type) {
		
		int start = NumberUtils.toInt(req.getParameter("start"));
		if(StringUtils.isBlank(type) || !EnumUtils.isValidEnum(PageType.class, type)){
			type = null;
		}
		
		String tagName = req.getParameter("tagName");
		if(StringUtils.isBlank(tagName)){
			tagName = null;
		}
		TagService ts = new TagService();
		List<Tag> tags = ts.list(new ConditionMap().add("type", type), "-favScore", 0, 20);
		
		PageService ps = new PageService();
		List<Page> pages = ps.list(new ConditionMap().add("type", type).add("tagName", tagName).add("isShare", true), "-createDate", start,10);
		long count = ps.count(ps.createQuery(new ConditionMap().add("type", type).add("tagName", tagName).add("isShare", true), "-createDate", 0,0));
		req.setAttribute("pages", pages);
		req.setAttribute("tags", tags);
		req.setAttribute("tagName", tagName);
		req.setAttribute("type", type);
		req.setAttribute("start", start);
		req.setAttribute("count", count);
		req.setAttribute("keywordForMeta",(tagName!=null?tagName:"") + PageType.valueOf(type).value() +"网址分享");
	 
		U.forward(req, res, "/index-all.jsp");	
			
		
	}
	
//	@RequestMapping(value = {"/baidupan"} )
//	public void baidupan(HttpServletRequest req, HttpServletResponse res) {
//		int start = NumberUtils.toInt(req.getParameter("start"));
//		PageService ps = new PageService();
//		List<Page> pages = ps.list(new ConditionMap().add("type", PageType.baidupan).add("isShare", true), "-createDate", start,10);
//		long count = ps.count(ps.createQuery(new ConditionMap().add("type", PageType.baidupan).add("isShare", true), "-createDate", 0,0));
//		req.setAttribute("pages", pages);
//		req.setAttribute("type", PageType.baidupan);
//		req.setAttribute("start", start);
//		req.setAttribute("count", count);
//		req.setAttribute("keywordForMeta", PageType.baidupan.value()+"网址分享");
//		U.forward(req, res, "/index-all.jsp");
//	}
}
