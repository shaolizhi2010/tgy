package com.tgy.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.buf.UDecoder;
import org.bson.types.ObjectId;

import com.tgy.dao.FolderDao;
import com.tgy.dao.UserDao;
import com.tgy.entity.Folder;
import com.tgy.entity.User;
import com.tgy.util.BreadCrumbUtil;
import com.tgy.util.FolderUtil;
import com.tgy.util.U;
import com.tgy.vo.BreadCrumb;
import com.tgy.web.vo.BookmarkData;

public class IndexService {

	UserDao uDao = new UserDao();
	FolderDao fDao = new FolderDao();
	FolderService fService = new FolderService();

	public BookmarkData getBookmarkDataByUserName(String userName) {

		User user = uDao.getByName(userName);
		if(user == null){
			return new BookmarkData(); 
		}

		return getBookmarkDataByUserID(user.id.toString());
	}

	public BookmarkData getBookmarkDataByUserID(String userID) {

		BookmarkData data = new BookmarkData();
		data.rootFolders = fDao.getFoldersByUserID(userID);
		data.curFolder = FolderUtil.getDefaultFolder(data.rootFolders);
		data.rootFolder = data.curFolder;
		data.curUser = uDao.getByID(userID);
		return data;

	}

	public BookmarkData getBookmarkDataByFolderID(String fid) {
		if(StringUtils.isBlank(fid))return new BookmarkData();
		Folder curFolder = fDao.get(new ObjectId(fid));// 用户当前选中的分类
		Folder rootFolder = fService.rootFolder(fid);// 用户当前选中的收藏夹
		List<Folder> folders = null;
		
		if(curFolder == null){//未找到文件夹
			return new BookmarkData();
		}

		BookmarkData data = new BookmarkData();
		data.curFolder = curFolder;
		data.rootFolder = rootFolder;
		data.curUser = uDao.getByID(data.curFolder.userID);
				
		if (curFolder != null) {
			folders = fDao.getFoldersByUserID(curFolder.userID);
			data.rootFolders = folders;
		}
		return data;
	}

	public void service(HttpServletRequest req, HttpServletResponse res,
			String fid) {
		long start = System.currentTimeMillis();

		String showType = req.getParameter("show");

		String userID = U.getUserID(req);
		// User user = uService.checkAndGetUser(userID);
		FolderDao fDao = new FolderDao();
		Folder curFolder = null;// 用户当前选中的分类
		Folder rootFolder = null;// 用户当前选中的收藏夹
		List<Folder> userFolders = null;
		// String rootFolderID;

		// init sys folders if not exit in user session
		// if (CollectionUtils.isEmpty( U.paramList(req.getSession(),
		// "sysFolders") ) ) {
		// sysFolders = FolderUtil.buildDefaultFolders();
		// req.getSession().setAttribute("sysFolders", sysFolders);
		// curFolder = FolderUtil.getDefaultFolder(sysFolders);
		// }

		// logined user, init user folders if not exit in user session
		if (userID != null
				&& CollectionUtils.isEmpty(U.paramList(req.getSession(),
						"userFolders"))) {
			userFolders = fDao.getFoldersByUserID(userID);
			req.getSession().setAttribute("userFolders", userFolders);
			curFolder = FolderUtil.getDefaultFolder(userFolders);
			rootFolder = curFolder;

		}
		// String fid = req.getParameter("fid");
		if (StringUtils.isNotBlank(fid)) {
			String fidInSession = U.paramString(req.getSession(), "fid");

			// get new folder info if fid not exist in session or different from
			// session,
			if (StringUtils.isBlank(fidInSession)
					|| !StringUtils.equals(fid, fidInSession)
					|| req.getSession().getAttribute("curFolder") == null) {
				curFolder = fDao.get(new ObjectId(fid));
				rootFolder = new FolderService().rootFolder(fid);
				req.getSession().setAttribute("fid", fid);

				// req.getSession().setAttribute("fid", fid);
			}
		}

		FolderUtil.setFolderForSession(curFolder, req);
		if (rootFolder != null) {
			req.getSession().setAttribute("folders", rootFolder.folders);
			req.getSession().setAttribute("rootFolder", rootFolder);
			req.getSession().setAttribute("rid", rootFolder.id.toString());
		}

		// set bread crumb
		// BreadCrumbUtil.add(bread, BreadCrumbUtil.build( curBookmark));
		BreadCrumb bread = BreadCrumbUtil.build(
				req.getSession().getAttribute("curFolder"),
				req.getContextPath());
		req.setAttribute("bread", bread);

		System.out.println("IndexController : "
				+ (System.currentTimeMillis() - start));

		if (StringUtils.equals(showType, "h")) {
			U.forward(req, res, "/heng.jsp");
		} else {
			U.forward(req, res, "/index-1.jsp");
		}
	}
}
