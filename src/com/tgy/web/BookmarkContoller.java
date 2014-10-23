package com.tgy.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.bson.types.ObjectId;

import com.tgy.dao.BookmarkDao;
import com.tgy.entity.Bookmark;
import com.tgy.service.BookmarkService;
import com.tgy.util.BookmarkUtil;
import com.tgy.util.C;
import com.tgy.util.U;

@WebServlet("/bookmark/todo")
public class BookmarkContoller extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		String userID = U.getUserID(req);
		String bid = req.getParameter("bid");
		 
		BookmarkService bkservice = new BookmarkService();
		BookmarkDao bkDao = new BookmarkDao();
		
		bkservice.setBookmarksForRequest(userID, req);
		
		Bookmark curBookmark = bkDao.get(new ObjectId(bid));
		
		BookmarkUtil.setCurrentBookmarkForRequest(curBookmark, req);
		
		U.forward(req, res, "/default.jsp");  
		
		ServletFileUpload upload = new ServletFileUpload();
		 

	}

}
