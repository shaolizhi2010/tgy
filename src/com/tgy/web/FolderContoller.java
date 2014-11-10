package com.tgy.web;

import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tgy.dao.FolderDao;
import com.tgy.entity.Folder;
import com.tgy.entity.Page;
import com.tgy.service.FolderService;
import com.tgy.service.IndexService;
import com.tgy.util.BreadCrumbUtil;
import com.tgy.util.FolderUtil;
import com.tgy.util.U;
import com.tgy.vo.BreadCrumb;
import com.tgy.web.vo.BookmarkData;

@RestController
@RequestMapping(value = { "/" })
public class FolderContoller extends HttpServlet {

	IndexService indexService = new IndexService();

	@RequestMapping(value = { "/folder/{fid}/", "/folder/{fid}/{name}/","/folder/{fid}", "/folder/{fid}/{name}" }, method = RequestMethod.GET)
	public void index(HttpServletRequest req, HttpServletResponse res,
			@PathVariable("fid") String fid) {

		BookmarkData data =  indexService.getBookmarkDataByFolderID(fid);
		req.setAttribute("bookmarkData", data);
		
		BreadCrumb bread = BreadCrumbUtil.build(
				data.curFolder,
				req.getContextPath());
		req.setAttribute("bread", bread);
		U.forward(req, res, "/index-1.jsp");
		
	}

}
