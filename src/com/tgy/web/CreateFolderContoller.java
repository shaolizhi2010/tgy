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
import com.tgy.exception.BaseException;
import com.tgy.service.FolderService;
import com.tgy.util.U;
import com.tgy.validator.CommonValidator;

@WebServlet("/folder/create/")
public class CreateFolderContoller extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		Folder folder = U.fromReqJson(req, Folder.class);
		
		try {
			new CommonValidator().isLogin(req, null).isNotNull(folder, null)
				.isLonger(folder.name, 0, "名称不能为空")
				.isShorter(folder.name, 60, "名称需小于60");
			
			FolderService fService = new FolderService();
			if(StringUtils.isNotBlank(folder.pid)){
				Folder pFolder = new FolderDao().getByID(folder.pid);
				if(pFolder==null){
					U.resFailed(res, "收藏夹未找到");
				}
				if (!StringUtils.equals(pFolder.userID, U.getUserID(req)) ){
					U.resFailed(res, "请登录后操作");
				}
			}
		
			
			fService.save(folder);
			U.refreshSession(req.getSession());
			U.resSuccess(res);
		} catch (BaseException e) {
			e.printStackTrace();
			U.resFailed(res, e.getMessage());
		}


	}

}
