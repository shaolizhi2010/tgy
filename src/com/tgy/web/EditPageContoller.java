package com.tgy.web;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.tgy.dao.FolderDao;
import com.tgy.dao.PageDao;
import com.tgy.entity.Folder;
import com.tgy.entity.Page;
import com.tgy.service.FolderService;
import com.tgy.util.U;

@WebServlet("/page/edit/")
public class EditPageContoller extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		//TODO 改Tag 和 Link的计数

		try {
			Map<String, String> map = U.requestToMap(req);

			String id = map.get("id");
			String name = map.get("name");
			String url = map.get("url");
			String pid = map.get("pid");

			PageDao pDao = new PageDao();
			if(StringUtils.isNotBlank(id)){
				Page page = pDao.getByID(id);	
				page.name = name;
				page.url = url;
				page.pid = pid;
				
				pDao.save(page);
			}
			 U.refreshSession(req.getSession());
			 U.resSuccess(res);
		} catch (Exception e) {
			e.printStackTrace();
			U.message(res, e.getMessage());
		}

	}

}
