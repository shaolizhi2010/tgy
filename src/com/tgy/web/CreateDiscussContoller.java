package com.tgy.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tgy.dao.FolderDao;
import com.tgy.entity.Discuss;
import com.tgy.entity.Folder;
import com.tgy.entity.User;
import com.tgy.exception.BaseException;
import com.tgy.service.DiscussService;
import com.tgy.service.FolderService;
import com.tgy.statistic.entity.Link;
import com.tgy.statistic.service.LinkService;
import com.tgy.util.C;
import com.tgy.util.U;
import com.tgy.validator.CommonValidator;

@RestController
@RequestMapping("/discuss/create")
public class CreateDiscussContoller extends HttpServlet {

	@RequestMapping( method = RequestMethod.POST )
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		DiscussService dcs = new DiscussService();
		String message = U.filterCharacter(req.getParameter("message")) ;
		//String sourceID = U.filterCharacter(req.getParameter("sourceID")) ;
		String targetID = U.filterCharacter(req.getParameter("targetID")) ;
		String type = U.filterCharacter(req.getParameter("type")) ;
		
		if(StringUtils.isBlank(type)){
			type = "comment_link";
		}
		else if(StringUtils.equals(type, "share_want")){
			
		}
		
		try {
			new CommonValidator()
				.isLonger(message, 1, "信息长度需大于1")
				.isShorter(message, 300, "信息需小于150个字符");
			
			Discuss dc = new Discuss();
			dc.message = message;
			
			dc.createDate = U.dateTime();
			dc.sourceID = U.getUserID(req);
			dc.soucrceIP = U.getIpAddr(req);
			dc.type = type;
			dc.targetID = targetID;
			dcs.save(dc);
			
			if(StringUtils.equals(type, "comment_link")){
				
				LinkService ls = new LinkService();
				Link lk = ls.getByID(targetID);
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

}
