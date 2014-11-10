package com.tgy.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.tgy.dao.FolderDao;
import com.tgy.entity.Folder;
import com.tgy.entity.Page;
import com.tgy.exception.BaseException;
import com.tgy.service.PageService;
import com.tgy.util.U;
import com.tgy.validator.CommonValidator;

@WebServlet("/page/create/")
public class CreatePageContoller extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		Page page = U.fromReqJson(req, Page.class);
		PageService pService =new PageService();
		
		try {
			
			new CommonValidator().isLogin(req, null)
			.isLonger(page.name, 0, "网站名称不能为空")
			.isShorter(page.name, 100, "网站名称需小于100")
			.isLonger(page.url, 0, "网址不能为空")
			.isShorter(page.url, 300, "网址长度需小于300");
			
			if(StringUtils.isNotBlank( page.pid)){
				Folder pFolder = new FolderDao().getByID(page.pid);
				if(pFolder==null){
					U.resFailed(res, "所属分类未找到");
				}
				if (!StringUtils.equals(pFolder.userID, U.getUserID(req)) ){
					U.resFailed(res, "请登录后操作");
				}
			}

			
			pService.save(page);
			U.refreshSession(req.getSession());
			U.resSuccess(res);
		} catch (BaseException e) {
			e.printStackTrace();
			U.resFailed(res, e.getMessage());
		}
		

	}

}
