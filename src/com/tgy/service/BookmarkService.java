package com.tgy.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.mongodb.morphia.query.Query;

import com.tgy.App;
import com.tgy.dao.BookmarkDao;
import com.tgy.entity.Bookmark;
import com.tgy.entity.Page;
import com.tgy.util.BookmarkUtil;
import com.tgy.util.U;

public class BookmarkService {

	public List<Bookmark> getBookmarks(String userID) {

		BookmarkDao dao = new BookmarkDao();

		Query<Bookmark> query = App.getInstance().getDatastore()
				.createQuery(Bookmark.class).filter("userID", userID)
				.order("-createDate");

		List<Bookmark> bookmarks = dao.find(query).asList();
		// query = null;
		return bookmarks;
	}

	public void setBookmarksForRequest(String userID, HttpServletRequest req) {
		List<Bookmark> sysBookmarks = BookmarkUtil.defaultBookmarks();
		req.getSession().setAttribute("sysBookmarks", sysBookmarks);

		List<Bookmark> userBookmarks = U.paramList(req.getSession(),  "userBookmarks") ; 
		if(userBookmarks == null || userBookmarks.size()==0){
			userBookmarks = getBookmarks(userID);
			req.getSession().setAttribute("userBookmarks", userBookmarks);
		}
		//req.setAttribute("userBookmarks", userBookmarks);//TODO session


	}

	public Page buildPage(String name, String link) {
		Page page = new Page();
		page.name = name;
		page.link = link;
		return page;
	}

}
