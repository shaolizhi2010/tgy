package com.tgy.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.tgy.dao.LinkDao;
import com.tgy.entity.VisitHistory;
import com.tgy.service.VisitHistoryService;
import com.tgy.statistic.entity.Link;
import com.tgy.util.U;

/**
 * 创建新网址的页面，
 * 用户输入网站名，自动提示出相关网站名和url，然后一键收藏
 * @author qq
 *
 */
@WebServlet("/visitHistory/requestUser")
public class VisitHistoryContoller extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
 
		String userID =req.getParameter("userID");
		
		try {
			VisitHistoryService vs = new VisitHistoryService();
			List<VisitHistory> list = vs.getByUser(userID,null, "times");
			if(list.size()>10){
				list = list.subList(0, 10);
			}
			
			
			String json = U.toJson(list);
			U.message(res,json);
		} catch (Exception e) {
			e.printStackTrace();
			U.resFailed(res, e.getMessage());
		}
		

	}

}
