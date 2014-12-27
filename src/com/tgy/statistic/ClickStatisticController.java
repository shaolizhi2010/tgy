package com.tgy.statistic;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tgy.dao.FolderDao;
import com.tgy.dao.LinkDao;
import com.tgy.dao.PageDao;
import com.tgy.dao.TagDao;
import com.tgy.entity.Folder;
import com.tgy.entity.Page;
import com.tgy.exception.BaseException;
import com.tgy.statistic.entity.Link;
import com.tgy.statistic.entity.Tag;
import com.tgy.util.U;
import com.tgy.validator.CommonValidator;

@WebServlet("/statistic/click")
public class ClickStatisticController extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		String id = U.filterCharacter(req.getParameter("id")) ;
		String type = U.filterCharacter(req.getParameter("type")) ;

		try {
			new CommonValidator().isNotNull(id, null)
					.isNotNull(type, null);//isLogin(req, null).

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
						folder.showTimes+=1;
						folder.favScore+=1;
						fDao.save(folder);
	 
					} 

					LinkDao lDao = new LinkDao();
					Link link = lDao.getByUrl(page.url);
					if (link != null) {
						link.clicks++;
						link.favScore++;
						lDao.save(link);
					}
				}
			}
			else if ("folder".equals(type)) {
				FolderDao fDao = new FolderDao();
				Folder folder = fDao.getByID(id);
				if (folder != null) {
					folder.showTimes+=2;
					folder.favScore+=2;
					fDao.save(folder);
					
					TagDao tDao = new TagDao();
					Tag tag = tDao.getByName(folder.name);
					tag.clicks+=1;
					tag.favScore+=1;
					tDao.save(tag);
 
				} 
				
				

			}

		} catch (BaseException e) {
			//e.printStackTrace();
			// do nothing
			// 此类用户统计分析，即使出现异常，也不要影响用户的正常操作
		}

	}

}
