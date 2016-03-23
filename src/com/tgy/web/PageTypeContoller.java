package com.tgy.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
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
import com.tgy.service.PanSearchCacheService;
import com.tgy.service.cache.AppCache;
import com.tgy.statistic.service.TagService;
import com.tgy.util.BaidupanSearchSevice;
import com.tgy.util.BaseSearchService;
import com.tgy.util.C;
import com.tgy.util.ConditionMap;
import com.tgy.util.GoogleSearchSevice;
import com.tgy.util.PageType;
import com.tgy.util.U;

@RestController
@RequestMapping(value = { "/share", "/share/" })
public class PageTypeContoller extends HttpServlet {

	@RequestMapping(value = { "/", "" })
	public void type(HttpServletRequest req, HttpServletResponse res,@CookieValue(value = "lastLoginUserID", defaultValue = "",required  = false) String lastLoginUserID) {
		 User user = U.param(req, C.user, User.class);
		  if(user==null){ //还没登陆,尝试cooke 登录
			  String userID = "";
				if(StringUtils.isNotBlank(lastLoginUserID)  ){ //如有登录id和密码 默认使用登录userid，temp user的密码未空
					userID = lastLoginUserID;
					user = new UserDao().getByID(userID);
					req.getSession().setAttribute(C.user, user);
				}
		  }
		
		U.forward(req, res, "/share/" + PageType.resource);

		// int start = NumberUtils.toInt(req.getParameter("start"));
		// String tagName = req.getParameter("tagName");
		// if(StringUtils.isBlank(tagName)){
		// tagName = null;
		// }
		//
		// TagService ts = new TagService();
		// List<Tag> tags = ts.list(null, "-favScore", 0, 10);
		//
		//
		// PageService ps = new PageService();
		// List<Page> pages = ps.list(new ConditionMap().add("tagName",
		// tagName). add("isShare", true).add("tagName", PageType.resource) ,
		// "-createDate", start,10);
		// long count = ps.count(ps.createQuery(new
		// ConditionMap().add("tagName", tagName). add("isShare",
		// true).add("tagName", PageType.resource), "-createDate", 0,0));
		// req.setAttribute("pages", pages);
		// req.setAttribute("tags", tags);
		// req.setAttribute("tagName", tagName);
		// //req.setAttribute("type", null);
		// req.setAttribute("start", start);
		// req.setAttribute("count", count);
		// req.setAttribute("keywordForMeta",tagName+ "网址分享");

		// U.forward(req, res, "/index-all.jsp");
	}

	@RequestMapping(value = { "/{type}" })
	public void tag(HttpServletRequest req, HttpServletResponse res,
			@PathVariable("type") String type,@CookieValue(value = "lastLoginUserID", defaultValue = "",required  = false) String lastLoginUserID) {

		if ("favicon".equals(type)) {
			return;
		}


		 User user = U.param(req, C.user, User.class);
		  if(user==null){ //还没登陆,尝试cooke 登录
			  String userID = "";
				if(StringUtils.isNotBlank(lastLoginUserID)  ){ //如有登录id和密码 默认使用登录userid，temp user的密码未空
					userID = lastLoginUserID;
					user = new UserDao().getByID(userID);
					req.getSession().setAttribute(C.user, user);
				}
		  }
		
		int pageStart = NumberUtils.toInt(req.getParameter("pageStart"));
		String orderStr = req.getParameter("orderStr");
		if (StringUtils.isBlank(orderStr)) {
			orderStr = "-lastModifyDate,-createDate";
		} else if (StringUtils.equalsIgnoreCase(orderStr, "fav")) {
			orderStr = "-favScore";
		} else {
			orderStr = "-createDate";
		}

		int days = NumberUtils.toInt(req.getParameter("days"));
		if (days == 0) { // 无参数，取一年的
			days = -365;
		}

		PageType TypeEnum = null;
		if (StringUtils.isBlank(type)
				|| !EnumUtils.isValidEnum(PageType.class, type)) {
			type = null;
			TypeEnum = PageType.resource;// default;
		} else {
			TypeEnum = PageType.valueOf(type);
		}

		if (TypeEnum.equals(PageType.commodity)) {
			orderStr = "-clicks";
		}

		String tagName = req.getParameter("tagName");
		if (StringUtils.isBlank(tagName)) {
			tagName = null;
		}

		String firstLetter = req.getParameter("letter");
		if (StringUtils.isBlank(firstLetter)) {
			firstLetter = null;
		}
		
		// TagService ts = new TagService();
		// List<Tag> tags = ts.list(new ConditionMap().add("type", TypeEnum),
		// "-favScore", 0, 20);

		List<Tag> tags = AppCache.tags(TypeEnum);

		List<Page> pages = new ArrayList<Page>();
		PageService ps = new PageService();
		
		ConditionMap conditions = new ConditionMap();

		conditions.add("type", TypeEnum).add("tagName", tagName)
				.add("firstLetter", firstLetter)
				.add("createDate >", U.dateTime(days)) // n天以内的，链接太久了容易失效
				.add("isShare", true);

		int needFulidou = NumberUtils.toInt(req.getParameter("needfulidou"));
		if( needFulidou >0){
			conditions.add("needFulidou >= ", needFulidou);
		}
		
		// 缓存初始页链接
		if (TypeEnum.equals(PageType.resource) && tagName == null && needFulidou ==0
				&& firstLetter == null && days == -365
				&& orderStr.equals("-lastModifyDate,-createDate")
				&& pageStart==0) {
			pages = AppCache.defaultPages();
		}
		else if(TypeEnum.equals(PageType.resource)  
				&& firstLetter == null && days == -365
				&& orderStr.equals("-lastModifyDate,-createDate")
				&& pageStart==0 && StringUtils.isNotBlank(tagName)){
			pages = AppCache.topicPages(tagName);
		}
		else if(TypeEnum.equals(PageType.resource)  
				&& firstLetter == null && days == -365
				&& orderStr.equals("-lastModifyDate,-createDate")
				&& pageStart==0 &&  needFulidou>0){
			pages = AppCache.fuliPages( );
		}
		else{
			 pages = ps.list(conditions, orderStr, pageStart, 10);
		}


		// 如果结果太少（小于10个），根据tagname（如‘海贼王’），自动按关键字（海贼王）搜索资源
		if (StringUtils.isNotBlank(tagName) && pages != null
				&& pages.size() < 10) {
			// webhezi 里搜索的结果
			List<Page> localResults = ps.searchSharePage(tagName, null,
					PageType.resource, null, pageStart, 10);
			pages.addAll(localResults);
			if (pages.size() < 10) {// 结果仍然不够，google搜索资源
				PanSearchCacheService pcs = new PanSearchCacheService();
				String key = tagName + pageStart;

				List<Page> remoteResults = pcs.ListByKey(key); // 利用第三方搜索引擎（如google）搜来的结果，先看本地缓存的结果

//				if (CollectionUtils.isEmpty(remoteResults)) {// 无缓存
//					BaseSearchService bs = new GoogleSearchSevice(); // 用
//																		// google搜
//
//					remoteResults = bs.search(tagName, pageStart);
//					if (CollectionUtils.isEmpty(remoteResults)) { // 没结果，用baidupan.net
//																	// 搜
//						bs = new BaidupanSearchSevice();
//						remoteResults.addAll(bs.search(tagName, pageStart));
//					}
//					// 把第三方搜来的结果 放入缓存中，加快搜索时间，减小被屏蔽概率
//					pcs.cacheAll(remoteResults, key);
//				}
				pages.addAll(remoteResults);
			}

		}

		long count = ps.count(ps.createQuery(conditions, orderStr, 0, 0));
		req.setAttribute("pages", pages);
		req.setAttribute("tags", tags);
		req.setAttribute("tagName", tagName);
		req.setAttribute("firstLetter", firstLetter);
		req.setAttribute("type", type);
		req.setAttribute("pageStart", pageStart);
		req.setAttribute("count", count);
		req.setAttribute("orderStr", req.getParameter("orderStr"));
		req.setAttribute("keywordForMeta", (tagName != null ? tagName : "")
				+ PageType.valueOf(type).value() + "网址分享");
		
		if(needFulidou > 0 ){
			U.forward(req, res, "/index-fuli.jsp");
			return;
		}
		else{
			U.forward(req, res, "/index-all.jsp");
			return;
		}
		

	}

	// @RequestMapping(value = {"/baidupan"} )
	// public void baidupan(HttpServletRequest req, HttpServletResponse res) {
	// int start = NumberUtils.toInt(req.getParameter("start"));
	// PageService ps = new PageService();
	// List<Page> pages = ps.list(new ConditionMap().add("type",
	// PageType.baidupan).add("isShare", true), "-createDate", start,10);
	// long count = ps.count(ps.createQuery(new ConditionMap().add("type",
	// PageType.baidupan).add("isShare", true), "-createDate", 0,0));
	// req.setAttribute("pages", pages);
	// req.setAttribute("type", PageType.baidupan);
	// req.setAttribute("start", start);
	// req.setAttribute("count", count);
	// req.setAttribute("keywordForMeta", PageType.baidupan.value()+"网址分享");
	// U.forward(req, res, "/index-all.jsp");
	// }
}
