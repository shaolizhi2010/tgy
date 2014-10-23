package com.tgy.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;

import com.tgy.dao.BookmarkDao;
import com.tgy.dao.FolderDao;
import com.tgy.entity.Bookmark;
import com.tgy.entity.Folder;
import com.tgy.entity.Page;
import com.tgy.service.BookmarkService;
import com.tgy.util.BookmarkUtil;
import com.tgy.util.BreadCrumbUtil;
import com.tgy.util.C;
import com.tgy.util.U;
import com.tgy.vo.BreadCrumb;

@WebServlet(urlPatterns = { "", "/bookmark/", "/folder/" })
public class IndexContoller extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		String userID = U.getUserID(req);
		BookmarkDao bkDao = new BookmarkDao();
		FolderDao fDao = new FolderDao();

		BookmarkService bookmarkService = new BookmarkService();
		
		Bookmark curBookmark = null;
		Folder curFolder = null;
		List<Folder> folders = null;
		List<Page> pages =null;
		BreadCrumb bread = new BreadCrumb("", "#");
		
		List<Bookmark> sysBookmarks = BookmarkUtil.defaultBookmarks();
		req.setAttribute("sysBookmarks", sysBookmarks);
		
		// not login
		if (userID == null) {
			curBookmark = BookmarkUtil
					.getDefaultBookmark(sysBookmarks);
			//BookmarkUtil.setCurrentBookmarkForRequest(curBookmark, req);
		} else {//
			
			//先看session里有没有，有标识以前取过了，没有就取 
			List<Bookmark> userBookmarks = U.paramList(req.getSession(),  "userBookmarks") ; 
			if(userBookmarks == null || userBookmarks.size()==0){
				userBookmarks = bookmarkService.getBookmarks(userID);
				req.getSession().setAttribute("userBookmarks", userBookmarks);
			}
			
			if (userBookmarks != null && userBookmarks.size() > 0) {
				curBookmark = BookmarkUtil.getDefaultBookmark(userBookmarks);
			} else {
				curBookmark = BookmarkUtil.getDefaultBookmark(sysBookmarks);
			}

			//BookmarkUtil.setCurrentBookmarkForRequest(curBookmark, req);
		}

		String bid = req.getParameter("bid");
		if (StringUtils.isNotBlank(bid)) {
			curBookmark = bkDao.get(new ObjectId(bid));
			//BookmarkUtil.setCurrentBookmarkForRequest(curBookmark, req);
		}
		

		String fid = req.getParameter("fid");
		if (StringUtils.isNotBlank(fid)) {
			curFolder = fDao.get(new ObjectId(fid));
			if(curFolder!=null){
				curBookmark = bkDao.get(new ObjectId(curFolder.bookmarkID));
				//BookmarkUtil.setCurrentBookmarkForRequest(curBookmark, req);
			}
		}
		
		if(curFolder!=null){
			folders = curFolder.folders;
			pages = curFolder.pages;
		}
		else{
			folders = curBookmark.folders;
			pages = curBookmark.pages;
		}
		
		req.setAttribute("curBookmark", curBookmark);
		//req.setAttribute("curFolder", curFolder);
		req.setAttribute("folders", folders);
		req.setAttribute("pages", pages);
		
		//set bread crumb
		BreadCrumbUtil.add(bread,  BreadCrumbUtil.build( curBookmark));
		BreadCrumbUtil.add(bread,  BreadCrumbUtil.build(curFolder));
		req.setAttribute("bread", bread);
		
		U.forward(req, res, "/default.jsp");

	}

}
