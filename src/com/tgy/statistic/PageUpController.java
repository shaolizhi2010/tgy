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
import com.tgy.statistic.service.LinkService;
import com.tgy.util.U;
import com.tgy.validator.CommonValidator;

/**
 * 对某个page点赞
 * @author qq
 *
 */
@WebServlet("/page/up")
public class PageUpController extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		String id = U.filterCharacter(req.getParameter("id")) ;

		try {
			new CommonValidator().isNotNull(id, null);//isLogin(req, null).

			PageDao pDao = new PageDao();
			Page page = pDao.getByID(id);
			if (page != null) {
				page.ups++;
				page.favScore+=5;
				pDao.save(page);
				
				LinkDao lDao = new LinkDao();
				Link link = lDao.getByUrl(page.url);
				if (link != null) {
					link.ups++;
					link.favScore+=5;
					lDao.save(link);
				}
			}
				
		} catch (BaseException e) {
			//e.printStackTrace();
			// do nothing
			System.out.println(e.getMessage());
			// 此类用户统计分析，即使出现异常，也不要影响用户的正常操作
		}
	}
}
