package com.tgy.web;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.tgy.dao.PageDao;
import com.tgy.entity.Page;
import com.tgy.util.U;
import com.tgy.validator.CommonValidator;

@WebServlet("/page/delete/")
public class DeletePageContoller extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		//TODO 改Tag 和 Link的计数

		try {
			Map<String, String> map = U.requestToMap(req);

			String id = map.get("id");
			
			new CommonValidator().isLogin(req, null).isNotNull(id, "未找到要编辑的网址")
			.isLength(id, 24,  "数据错误:id");

			String userID = U.getUserID(req);
			
			PageDao pDao = new PageDao();
			if(StringUtils.isNotBlank(id)){
				Page page = pDao.getByID(id);	
				
				//check if page belong to user
				if(!StringUtils.equals(page.userID, userID)){
					U.resFailed(res, "请登录后操作");
				}
				pDao.deleteWithRef(page);
				
			}
			 U.refreshSession(req.getSession());
			 U.resSuccess(res);
		} catch (Exception e) {
			e.printStackTrace();
			U.message(res, e.getMessage());
		}

	}

}
