package com.tgy.statistic;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tgy.exception.BaseException;
import com.tgy.statistic.entity.Link;
import com.tgy.statistic.service.LinkService;
import com.tgy.util.U;
import com.tgy.validator.CommonValidator;

/**
 * 对某个link点赞
 * @author qq
 *
 */
@WebServlet("/link/up")
public class LinkUpController extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		String id = U.filterCharacter(req.getParameter("id")) ;

		try {
			new CommonValidator().isNotNull(id, null);//isLogin(req, null).

				LinkService linkService = new LinkService();
				Link link = linkService.getByID(id);
					if (link != null) {
						link.ups++;
						link.favScore+=5;
						linkService.save(link);
					}
		} catch (BaseException e) {
			//e.printStackTrace();
			// do nothing
			System.out.println(e.getMessage());
			// 此类用户统计分析，即使出现异常，也不要影响用户的正常操作
		}
	}
}
