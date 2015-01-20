package com.tgy.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tgy.entity.SearchHistory;
import com.tgy.service.SearchHistoryService;
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
		 
			List<SearchHistory> histories =  ss.list(null, "-times", 20);
			String json = U.toJson(histories);
			U.message(res, json);
			 
		} catch (Exception e) {
			//U.resFailed(res, e.getMessage());
		}
	}
}
