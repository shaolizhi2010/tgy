package com.tgy.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tgy.entity.Page;
import com.tgy.entity.SearchHistory;
import com.tgy.service.PanSearchCacheService;
import com.tgy.service.SearchHistoryService;
import com.tgy.util.ConditionMap;
import com.tgy.util.U;

@RestController
@RequestMapping(value = { "/search/history" })
public class SearchHistoryContoller extends HttpServlet {
	
	/**
	 * 查询 list
	 */
	@RequestMapping("/top")
	protected void createForAll(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		SearchHistoryService ss = new SearchHistoryService();
		
		try {
			ConditionMap conditions = new ConditionMap();
			conditions.add("lastModifyDate >", U.dateTime(-2)); //n天以内的
			
			List<SearchHistory> histories =  ss.list(conditions, "-times", 20);
			String json = U.toJson(histories);
			U.message(res, json);
			
		} catch (Exception e) {
			//U.resFailed(res, e.getMessage());
		}
	}
	
	/**
	 * 查询 list
	 */
	@RequestMapping("/baiduWangpan")
	protected void baiduwangpan(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		int pageStart = NumberUtils.toInt(req.getParameter("pageStart"));
		
		SearchHistoryService ss = new SearchHistoryService();
		
		try {
			ConditionMap conditions = new ConditionMap();
			//conditions.add("lastModifyDate >", U.dateTime(-2)); //n天以内的
			
			List<SearchHistory> histories =  ss.list(conditions, "-createDate", pageStart *20, 200);
			
			req.setAttribute("histories", histories);
			req.setAttribute("pageStart", pageStart);
			
			U.forward(req, res, "/searchHistory.jsp");	
			
		} catch (Exception e) {
			//U.resFailed(res, e.getMessage());
		}
	}
	
	
	/**
	 * 查询 list
	 */
	@RequestMapping("/needDig")
	protected void needDig(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		

List<SearchHistory> needDigs = new ArrayList<SearchHistory>();

SearchHistoryService ss = new SearchHistoryService();

try {
	ConditionMap conditions = new ConditionMap();
	conditions.add("lastModifyDate >", U.dateTime(-30)); //n天以内的
	conditions.add("times >", 2); //搜索2次以上
	
	List<SearchHistory> histories =  ss.list(conditions, "-times", 5);
	
	PanSearchCacheService cacheSvs = new PanSearchCacheService();
	for(SearchHistory h : histories ){
		List<Page> caches = cacheSvs.ListByKey(h.keyword+"0"); //key = keyword+"0"
		if(CollectionUtils.isEmpty(caches)){ //没有缓存
			needDigs.add(h);
			if(needDigs.size()>5){ //每次取出5个需要搜索的关键字
				break;
			}
		}
		else{
			System.out.print(h.keyword + " exist ! ");
		}
		
	}
	
	String json = U.toJson(needDigs);
	U.message(res , json);
	
} catch (Exception e) {
	//U.resFailed(res, e.getMessage());
}
	}
}
