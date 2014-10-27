package com.tgy.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/bookmark/todo")
public class BookmarkContoller extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		/*
		String userID = U.getUserID(req);
		String bid = req.getParameter("bid");
		 
		BookmarkService bkservice = new BookmarkService();
		BookmarkDao bkDao = new BookmarkDao();
		
		List<Bookmark> sysBookmarks = BookmarkUtil.defaultBookmarks();
		req.getSession().setAttribute("sysBookmarks", sysBookmarks);

		List<Bookmark> userBookmarks = U.paramList(req.getSession(),  "userBookmarks") ; 
		if(userBookmarks == null || userBookmarks.size()==0){
			userBookmarks = getBookmarks(userID);
			req.getSession().setAttribute("userBookmarks", userBookmarks);
		}
		
		Bookmark curBookmark = bkDao.get(new ObjectId(bid));
		
		BookmarkUtil.setCurrentBookmarkForRequest(curBookmark, req);
		
		U.forward(req, res, "/default.jsp");  
		
		ServletFileUpload upload = new ServletFileUpload();
		 
*/
	}

}
