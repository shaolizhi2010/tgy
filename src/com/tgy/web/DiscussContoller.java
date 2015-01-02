package com.tgy.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tgy.entity.Discuss;
import com.tgy.exception.BaseException;
import com.tgy.service.DiscussService;
import com.tgy.service.IndexService;
import com.tgy.statistic.entity.Link;
import com.tgy.statistic.service.LinkService;
import com.tgy.util.U;
import com.tgy.validator.CommonValidator;

@RestController
@RequestMapping(value = { "/discuss" })
public class DiscussContoller extends HttpServlet {

	IndexService indexService = new IndexService();

	@RequestMapping(value = { "/link/{linkID}" })
	public void byLinkID(HttpServletRequest req, HttpServletResponse res,
			@PathVariable("linkID") String linkID) {

		LinkService ls = new LinkService();
		Link link = ls.getByID(linkID);
		
		List<Discuss> discusses = link.discusses;
		if(discusses!=null && discusses.size()>20){
			discusses = discusses.subList(discusses.size()-20, discusses.size());
		}
		
		req.setAttribute("target", link);
		req.setAttribute("discusses", discusses);
		
		U.forward(req, res, "/discuss.jsp");
	}

	@RequestMapping("/link/create")
	protected void createForLink(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		DiscussService dcs = new DiscussService();
		String message = U.filterCharacter(req.getParameter("message")) ;
		//String sourceID = U.filterCharacter(req.getParameter("sourceID")) ;
		String linkID = U.filterCharacter(req.getParameter("linkID")) ;
		
		try {
			new CommonValidator()
				.isLonger(message, 1, "信息长度需大于1")
				.isShorter(message, 300, "信息需小于150个字符");
			
			Discuss dc = new Discuss();
			dc.message = message;
			
			dc.userID = U.getUserID(req);
			dc.soucrceIP = U.getIpAddr(req);
			dc.targetLinkID = linkID;
			dcs.save(dc);
			
			//create disccuss for link
			if(StringUtils.isNotBlank(linkID)){
				
				LinkService ls = new LinkService();
				Link lk = ls.getByID(linkID);
				if(lk!=null){
					lk.commentsCount++;
					lk.add(dc);
					ls.save(lk);
				}
			}
			U.resSuccess(res);
		} catch (BaseException e) {
			U.resFailed(res, e.getMessage());
		}
	}
	
	//网盘资源搜索页面的评论
	@RequestMapping("/search/create")
	protected void createForSearch(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		DiscussService dcs = new DiscussService();
		String message = U.filterCharacter(req.getParameter("message")) ;
		//String sourceID = U.filterCharacter(req.getParameter("sourceID")) ;
		
		try {
			new CommonValidator()
				.isLonger(message, 1, "信息长度需大于1")
				.isShorter(message, 300, "信息需小于150个字符");
			
			Discuss dc = new Discuss();
			dc.message = message;
			
			dc.userID = U.getUserID(req);
			dc.soucrceIP = U.getIpAddr(req);
			dc.targetIsSearchPage = "true"; //
			dcs.save(dc);
			
			U.resSuccess(res);
		} catch (BaseException e) {
			U.resFailed(res, e.getMessage());
		}
	}
}
