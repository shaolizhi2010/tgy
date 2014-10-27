package com.tgy.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;

import com.tgy.dao.FolderDao;
import com.tgy.entity.Folder;
import com.tgy.entity.Page;
import com.tgy.util.BreadCrumbUtil;
import com.tgy.util.FolderUtil;
import com.tgy.util.U;
import com.tgy.vo.BreadCrumb;

@WebServlet(urlPatterns = { "", "/bookmark/", "/folder/" })
public class IndexContoller extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		String showType = req.getParameter("show");
		
		String userID = U.getUserID(req);
		FolderDao fDao = new FolderDao();

		Folder curFolder = null;
		List<Folder> sysFolders = null;
		List<Folder> userFolders = null;
		List<Page> pages = null;

		// init sys folders if not exit in user session
//		if (CollectionUtils.isEmpty( U.paramList(req.getSession(), "sysFolders") ) ) {
//			sysFolders = FolderUtil.buildDefaultFolders();
//			req.getSession().setAttribute("sysFolders", sysFolders);
//			curFolder = FolderUtil.getDefaultFolder(sysFolders);
//		}

		// logined user, init user folders if not exit in user session
		if (userID != null
				&& CollectionUtils.isEmpty(U.paramList(req.getSession(),
						"userFolders"))) {
			userFolders = fDao.getFoldersByUserID(userID);
			req.getSession().setAttribute("userFolders", userFolders);
			curFolder = FolderUtil.getDefaultFolder(userFolders);
		}

		String fid = req.getParameter("fid");
		if (StringUtils.isNotBlank(fid)) {
			String fidInSession = U.paramString(req.getSession(), "fid");
			
			// get new folder info if fid not exist in session or different from
			// session,
			if (StringUtils.isBlank(fidInSession)
					|| !StringUtils.equals(fid, fidInSession) || req.getSession().getAttribute("curFolder")==null   ) {
				curFolder = fDao.get(new ObjectId(fid));
				req.getSession().setAttribute("fid", fid);
				//req.getSession().setAttribute("fid", fid);
			}
		}

		FolderUtil.setFolderForSession(curFolder, req);

		// set bread crumb
		// BreadCrumbUtil.add(bread, BreadCrumbUtil.build( curBookmark));
		BreadCrumb bread = BreadCrumbUtil
				.build(req.getSession().getAttribute("curFolder"), req.getContextPath());
		req.setAttribute("bread", bread);

		if(StringUtils.equals(showType, "h")){
			U.forward(req, res, "/heng.jsp");
		}
		else{
			U.forward(req, res, "/default.jsp");
		}
		
	}

}
