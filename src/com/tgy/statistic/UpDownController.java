package com.tgy.statistic;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tgy.dao.LinkDao;
import com.tgy.dao.PageDao;
import com.tgy.entity.Page;
import com.tgy.entity.group.InterestGroupPage;
import com.tgy.exception.BaseException;
import com.tgy.service.group.InterestGroupPageService;
import com.tgy.statistic.entity.Link;
import com.tgy.util.C;
import com.tgy.util.U;
import com.tgy.validator.CommonValidator;

/**
 * 对某个page点赞
 * @author qq
 *
 */
@RestController
@RequestMapping(value = { "/statistics" })
public class UpDownController extends HttpServlet {
	
	/**
	 * common page
	 * @param req
	 * @param res
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value = { "/up/page" })
	protected void upPage(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		String id = U.filterCharacter(req.getParameter("id")) ;

		try {
			new CommonValidator().isNotNull(id, null);//isLogin(req, null).

			PageDao pDao = new PageDao();
			Page page = pDao.byID(id);
			if (page != null) {
				page.ups++;
				page.favScore+=C.scoreUp;
				pDao.save(page);
				
				LinkDao lDao = new LinkDao();
				Link link = lDao.getByUrl(page.url);
				if (link != null) {
					link.ups++;
					link.favScore+=C.scoreUp;
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
	
	@RequestMapping(value = { "/down/page" })
	protected void downPage(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		String id = U.filterCharacter(req.getParameter("id")) ;

		try {
			new CommonValidator().isNotNull(id, null);//isLogin(req, null).

			PageDao pDao = new PageDao();
			Page page = pDao.byID(id);
			if (page != null) {
				page.downs++;
				page.favScore+=C.scoreDown;
				pDao.save(page);
				
				LinkDao lDao = new LinkDao();
				Link link = lDao.getByUrl(page.url);
				if (link != null) {
					link.downs++;
					link.favScore+=C.scoreDown;
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
	
	/**
	 * group page
	 * @param req
	 * @param res
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value = { "/up/group/page" })
	protected void upGroupPage(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		String id = U.filterCharacter(req.getParameter("id")) ;

		try {
			new CommonValidator().isNotNull(id, null);//isLogin(req, null).

			InterestGroupPageService s = new InterestGroupPageService();
			InterestGroupPage page = s.byID(id);
			if (page != null) {
				page.ups++;
				page.favScore+=C.scoreUp;
				s.save(page);
				
				LinkDao lDao = new LinkDao();
				Link link = lDao.getByUrl(page.url);
				if (link != null) {
					link.ups++;
					link.favScore+=C.scoreUp;
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
	
	@RequestMapping(value = { "/down/group/page" })
	protected void downGroupPage(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		String id = U.filterCharacter(req.getParameter("id")) ;

		try {
			new CommonValidator().isNotNull(id, null);//isLogin(req, null).

			InterestGroupPageService s = new InterestGroupPageService();
			InterestGroupPage page = s.byID(id);
			if (page != null) {
				page.downs++;
				page.favScore+=C.scoreDown;
				s.save(page);
				
				LinkDao lDao = new LinkDao();
				Link link = lDao.getByUrl(page.url);
				if (link != null) {
					link.downs++;
					link.favScore+=C.scoreDown;
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
