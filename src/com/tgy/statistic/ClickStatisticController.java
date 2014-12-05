package com.tgy.statistic;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tgy.dao.FolderDao;
import com.tgy.dao.PageDao;
import com.tgy.entity.Folder;
import com.tgy.entity.Page;
import com.tgy.exception.BaseException;
import com.tgy.util.U;
import com.tgy.validator.CommonValidator;

@WebServlet("/statistic/click")
public class ClickStatisticController extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		HashMap param = U.fromReqJson(req, HashMap.class);
		String id = String.valueOf(param.get("id"));
		String type = String.valueOf(param.get("type"));

		try {
			new CommonValidator().isLogin(req, null).isNotNull(id, null)
					.isNotNull(type, null);

			if ("page".equals(type)) {
				PageDao pDao = new PageDao();
				Page page = pDao.getByID(id);
				if (page != null) {
					page.clicks++;
					page.favScore++;
					pDao.save(page);
					
					FolderDao fDao = new FolderDao();
					Folder folder = fDao.getByID(page.pid);
					if (folder != null) {
						folder.clicks+=1;
						folder.favScore+=1;
						fDao.save(folder);
	 
					} 

//					LinkDao lDao = new LinkDao();
//					Link link = lDao.getByUrl(page.url);
//					if (link != null) {
//						link.clicks++;
//						page.favScore++;
//						lDao.save(link);
//					}

				}

			}
			else if ("folder".equals(type)) {
				FolderDao fDao = new FolderDao();
				Folder folder = fDao.getByID(id);
				if (folder != null) {
					folder.clicks+=2;
					folder.favScore+=2;
					fDao.save(folder);
 
				} 

			}

		} catch (BaseException e) {
			//e.printStackTrace();
			// do nothing
			// 此类用户统计分析，即使出现异常，也不要影响用户的正常操作
		}

	}

}
