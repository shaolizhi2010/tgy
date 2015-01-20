package com.tgy.web;

import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tgy.dao.SearchHistoryDao;
import com.tgy.entity.Page;
import com.tgy.entity.SearchHistory;
import com.tgy.statistic.entity.Link;
import com.tgy.util.BaidupanSearchSevice;
import com.tgy.util.BingSearchSevice;
import com.tgy.util.GoogleSearchSevice;
import com.tgy.util.SougouSearchSevice;
import com.tgy.util.U;
import com.tgy.util.YahooSearchSevice;

@RestController
@RequestMapping(value = { "/"})
public class PanSearchContoller extends HttpServlet {


	@RequestMapping(value = {"/pan/{keyword}"  })
	public void search(HttpServletRequest req, HttpServletResponse res,
			@PathVariable("keyword") String keyword) {
		
		
		if("favicon".equals(keyword)){
			return;
		}
		
		if(StringUtils.isNotBlank(keyword)){
			//BingSearchSevice bs = new BingSearchSevice();
			//SougouSearchSevice bs = new SougouSearchSevice();
//			YahooSearchSevice bs = new YahooSearchSevice();
//			GoogleSearchSevice bs = new GoogleSearchSevice();
			BaidupanSearchSevice bs = new BaidupanSearchSevice();
			List<Page>  results = bs.search(keyword);
			
			req.setAttribute("results", results);
			req.setAttribute("keyword", keyword);
		}
		
		//String userID = U.getUserID(req);
		//String ip = U.getIpAddr(req);
		
		U.forward(req, res, "/pan.jsp");
		
	}

}
