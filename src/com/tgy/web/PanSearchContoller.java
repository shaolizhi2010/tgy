package com.tgy.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tgy.dao.SearchHistoryDao;
import com.tgy.entity.Page;
import com.tgy.entity.SearchHistory;
import com.tgy.service.PageService;
import com.tgy.service.PanSearchCacheService;
import com.tgy.util.BaidupanSearchSevice;
import com.tgy.util.BaseSearchService;
import com.tgy.util.BingSearchSevice;
import com.tgy.util.GoogleSearchSevice;
import com.tgy.util.PageType;
import com.tgy.util.U;

@RestController
@RequestMapping(value = { "/pan/","/pan"})
public class PanSearchContoller extends HttpServlet {

	@RequestMapping(value = {"/{keyword}","/"  })
	public void search(HttpServletRequest req, HttpServletResponse res,
			@PathVariable("keyword") String keyword) {
		
		
		if("favicon".equals(keyword)){
			return;
		}
		
		int start = 0;
		String startStr = req.getParameter("start");
		if(!StringUtils.isBlank(startStr)){
			try {
				start = Integer.parseInt(startStr);
			} catch (Exception e) {
				start = 0;
			}
		}
		
		if(StringUtils.isNotBlank(keyword)){
			
			//keyword search history
			SearchHistoryDao dao = new SearchHistoryDao();
			SearchHistory sh = dao.byKeyword(keyword);
			if(sh==null){
				sh = new SearchHistory();
				sh.keyword = keyword;
				sh.createDate = U.dateTime();
				sh.lastModifyDate = U.dateTime();
				sh.times=1; 
				//sh.userID = user
			}
			else{
				sh.lastModifyDate = U.dateTime();
				sh.times++;
			}
			dao.save(sh);
			
			List<Page> allResults = new ArrayList<>();
			
			//webhezi 里搜索的结果
			PageService ps = new PageService();
			List<Page>  localResults = ps.searchSharePage(keyword,null,PageType.resource, null, start, 10);
			
			PanSearchCacheService pcs =new PanSearchCacheService();
			String key = keyword+start;
			
			List<Page>  remoteResults = pcs.ListByKey(key); //利用第三方搜索引擎（如google）搜来的结果，先看本地缓存的结果
			
			if(CollectionUtils.isEmpty(remoteResults)){//无缓存
				//BingSearchSevice bs = new BingSearchSevice();
				//SougouSearchSevice bs = new SougouSearchSevice();
//				YahooSearchSevice bs = new YahooSearchSevice();
//				GoogleSearchSevice bs = new GoogleSearchSevice();
				
				
				//频繁抓取google bing会被封ip。。。。暂使用页面iframe形式，
				//TODO 解决这个问题，如多个ip 或只抓取 热门keyword
				/*
				BaseSearchService bs = new GoogleSearchSevice(); //用 google搜
				
				 remoteResults = bs.search(keyword,start); 
				if(CollectionUtils.isEmpty(remoteResults)){ //没结果，用bing 搜
					bs = new BingSearchSevice();
					remoteResults.addAll( bs.search(keyword,start)); 
				}
//				if(CollectionUtils.isEmpty(remoteResults)){ //没结果，用baidupan.net 搜
//					bs = new BaidupanSearchSevice();
//					remoteResults.addAll( bs.search(keyword,start)); 
//				}
				//把第三方搜来的结果 放入缓存中，加快搜索时间，减小被屏蔽概率
				pcs.cacheAll(remoteResults,key);
				*/
			}
			
			allResults.addAll(localResults);
			allResults.addAll(remoteResults);
			
			for(Page p : allResults){
				p.name = StringUtils.replace(p.name, keyword, "<em>"+keyword+"</em>");//keyword高亮
				p.comment = StringUtils.replace(p.comment, keyword, "<em>"+keyword+"</em>");//keyword高亮
				if(StringUtils.isNotBlank(p.url) && StringUtils.contains(p.url, "pan.baidu.com/wap/link")){
					p.url = StringUtils.replace(p.url, "pan.baidu.com/wap/link", "pan.baidu.com/share/link");
				}
			}
			
			req.setAttribute("results", allResults);
			req.setAttribute("keyword", keyword);
			
		}
		req.setAttribute("start", start+"");
		//String userID = U.getUserID(req);
		//String ip = U.getIpAddr(req);
 
//		if(req==null){
//			System.out.println("panSearch debug : req is null");
//		}
//		if(res==null){
//			System.out.println("panSearch debug : res is null");
//		}
//		if(req.getRequestDispatcher("/pan.jsp")==null){
//			System.out.println("panSearch debug : req.getRequestDispatcher is null");
//		}
		U.forward(req, res, "/pan.jsp");
		
		 
		
	}

}
