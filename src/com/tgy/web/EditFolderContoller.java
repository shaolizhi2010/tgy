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
import com.tgy.entity.Folder;
import com.tgy.entity.User;
import com.tgy.exception.BaseException;
import com.tgy.service.FolderService;
import com.tgy.service.UserService;
import com.tgy.util.C;
import com.tgy.util.U;
import com.tgy.validator.CommonValidator;

@WebServlet("/folder/edit")
public class EditFolderContoller extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		//TODO 改Tag 和 Link的计数

		try {
			String id = U.filterCharacter(req.getParameter("id"));
			String name = U.filterCharacter(req.getParameter("name"));
			
			User user = U.param(req, C.user, User.class);
			
			new CommonValidator().isLogin(req, null).isNotNull(id,  "未找到要编辑的分类")
			.isLonger(name, 0, "名称不能为空")
			.isShorter(name, 60, "名称需小于60")
			.isLength(id, 24,  "数据错误:id") ;
			//.isShorter(id, 20, "未找到要编辑的收藏夹");//oid invalid
			
			UserService uService = new UserService();

			FolderService fService = new FolderService();
			FolderDao fDao = new FolderDao();
			Folder folder = fDao.getByID(id);
			if (folder != null) {
				//判断用户id和要操作的文件夹所属用户 是否一致，否则报错
				new CommonValidator().isSameUser(user, folder, null);
				folder.name = name;
				folder.lastModifyDate = U.dateTime();
				fDao.save(folder);
			}
			// fService.save(folder);
			 U.refreshSession(req.getSession());
			 U.resSuccess(res);
		} catch (Exception e) {
			e.printStackTrace();
			U.message(res, e.getMessage());
		}

	}

}
