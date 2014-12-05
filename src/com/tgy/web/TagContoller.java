package com.tgy.web;

import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tgy.dao.TagDao;
import com.tgy.entity.Folder;
import com.tgy.service.FolderService;
import com.tgy.statistic.entity.Tag;
import com.tgy.util.U;

@RestController
@RequestMapping( value = {"/"}  )
public class TagContoller extends HttpServlet {
	
	@RequestMapping(value = { "/tag/{tagName}"}, method = RequestMethod.GET )
	public void tag(HttpServletRequest req, HttpServletResponse res,@PathVariable(value = "tagName") String tagName) {
		
		FolderService fService = new FolderService();
		List<Folder> folders =  fService.ByName(tagName);
		req.setAttribute("folders", folders);
		
		Tag tag = new TagDao().getByName(tagName);
		if(tag !=null){
		 	//req.setAttribute("users", tag.users);
		 	req.setAttribute("links", tag.links);
		 	req.setAttribute("tag", tag);
		}
		
	
	 
	 	
		U.forward(req, res, "/tag.jsp");
	}
 
	 
	
}
