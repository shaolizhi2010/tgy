package com.tgy.web;

import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tgy.entity.Discuss;
import com.tgy.entity.User;
import com.tgy.service.IndexService;
import com.tgy.service.UserService;
import com.tgy.statistic.entity.Link;
import com.tgy.statistic.service.LinkService;
import com.tgy.util.U;
import com.tgy.web.vo.BookmarkData;

@RestController
@RequestMapping(value = { "/" })
public class DiscussContoller extends HttpServlet {

	IndexService indexService = new IndexService();

	@RequestMapping(value = { "/discuss/link/{linkID}" })
	public void discuss(HttpServletRequest req, HttpServletResponse res,
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

}
