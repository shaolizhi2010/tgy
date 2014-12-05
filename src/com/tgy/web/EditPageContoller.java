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
import com.tgy.entity.User;
import com.tgy.service.FolderService;
import com.tgy.util.C;
import com.tgy.util.U;
import com.tgy.validator.CommonValidator;

@WebServlet("/page/edit")
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
			
			User user = U.param(req, C.user, User.class);
			
			new CommonValidator().isLogin(req, null).isNotNull(id, "未找到要编辑的网址")
			.isLonger(name, 0, "网站名称不能为空")
			.isShorter(name, 100, "网站名称需小于100")
			.isLonger(url, 0, "网址不能为空")
			.isShorter(url, 300, "网址长度需小于300")
			.isLength(id, 24,  "数据错误:id");

			String userID = U.getUserID(req);
			
			PageDao pDao = new PageDao();
			if(StringUtils.isNotBlank(id)){
				Page page = pDao.getByID(id);	
				
				//check if page belong to user
				new CommonValidator().isSameUser(user, page, null);
				
				if(StringUtils.isNotBlank( page.pid)){
					FolderDao fDao = new FolderDao();
					Folder pFolder = fDao.getByID(page.pid);
					if(pFolder==null){
						U.resFailed(res, "所属分类未找到");
						return;
					}
					new CommonValidator().isSameUser(user, pFolder, null);
					 
					pFolder.updateDate = U.dateTime();
					fDao.save(pFolder);
				}
				
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
