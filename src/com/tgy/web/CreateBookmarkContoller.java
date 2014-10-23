package com.tgy.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tgy.dao.BookmarkDao;
import com.tgy.entity.Bookmark;
import com.tgy.util.U;

@WebServlet("/bookmark/create/")
public class CreateBookmarkContoller extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
        Bookmark bookmark = U.fromReqJson(req, Bookmark.class);

        bookmark.createDate = U.dateTime();
		new BookmarkDao().save(bookmark);
		
		U.resSuccess(res);

	}

}
