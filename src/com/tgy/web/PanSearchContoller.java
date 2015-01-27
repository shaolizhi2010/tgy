package com.tgy.web;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tgy.dao.SearchHistoryDao;
import com.tgy.entity.Page;
import com.tgy.entity.SearchHistory;
import com.tgy.service.PanSearchCacheService;
import com.tgy.statistic.entity.Link;
import com.tgy.util.BaidupanSearchSevice;
import com.tgy.util.BingSearchSevice;
import com.tgy.util.GoogleSearchSevice;
import com.tgy.util.SougouSearchSevice;
import com.tgy.util.U;
import com.tgy.util.YahooSearchSevice;

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
			
			PanSearchCacheService pcs =new PanSearchCacheService();
			String key = keyword+start;
			
			List<Page>  results = pcs.ListByKey(key);
			if(CollectionUtils.isEmpty(results)){//无缓冲
				//BingSearchSevice bs = new BingSearchSevice();
				//SougouSearchSevice bs = new SougouSearchSevice();
//				YahooSearchSevice bs = new YahooSearchSevice();
//				GoogleSearchSevice bs = new GoogleSearchSevice();
				BaidupanSearchSevice bs = new BaidupanSearchSevice();
				
				results = bs.search(keyword,start);
				
				pcs.cacheAll(results,key);
			}
			
			for(Page p : results){
				p.name = StringUtils.replace(p.name, keyword, "<em>"+keyword+"</em>");//keyword高亮
				p.comment = StringUtils.replace(p.comment, keyword, "<em>"+keyword+"</em>");//keyword高亮
			}
			
			req.setAttribute("results", results);
			req.setAttribute("keyword", keyword);
			
		}
		req.setAttribute("start", start+"");
		//String userID = U.getUserID(req);
		//String ip = U.getIpAddr(req);
		
		U.forward(req, res, "/pan.jsp");
		
		 
		
	}

}
