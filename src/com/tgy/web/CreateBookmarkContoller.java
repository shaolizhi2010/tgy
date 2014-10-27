package com.tgy.web;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet("/bookmark/create/todo")
public class CreateBookmarkContoller extends HttpServlet {
/*
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
        Bookmark bookmark = U.fromReqJson(req, Bookmark.class);

        bookmark.createDate = U.dateTime();
		new BookmarkDao().save(bookmark);
		
		U.resSuccess(res);

	}
*/
}
