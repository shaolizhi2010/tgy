package com.tgy.web;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tgy.entity.User;
import com.tgy.service.IndexService;
import com.tgy.service.UserService;
import com.tgy.util.U;
import com.tgy.web.vo.BookmarkData;

@RestController
@RequestMapping(value = { "/" })
public class FolderContoller extends HttpServlet {

	IndexService indexService = new IndexService();

	@RequestMapping(value = { "/folder/{fid}/{name}","/folder/{fid}" })
	public void index(HttpServletRequest req, HttpServletResponse res,
			@PathVariable("fid") String fid) {
		
		if("favicon".equals(fid)){
			return;
		}

		BookmarkData data =  indexService.getBookmarkDataByFolderID(fid);
		req.setAttribute("bookmarkData", data);
		
//		BreadCrumb bread = BreadCrumbUtil.build(
//				data.folder,
//				req.getContextPath());
//		req.setAttribute("bread", bread);
		
		User showUser = indexService.getUserByFolderID(fid);
		if(showUser!=null){
			showUser.showTimes++;
			showUser.score++;
			new UserService().save(showUser);
		}
		
		req.setAttribute("showUser", showUser);
		
		U.forward(req, res, "/index-1.jsp");
		
	}

}
