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
import com.tgy.service.FolderService;
import com.tgy.service.UserService;
import com.tgy.util.C;
import com.tgy.util.U;
import com.tgy.validator.CommonValidator;

@WebServlet("/folder/delete/")
public class DeleteFolderContoller extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		//TODO 改Tag 和 Link的计数

		try {
			Map<String, String> map = U.requestToMap(req);
			
			String id = map.get("id");
			
			new CommonValidator().isLogin(req, null).isNotNull(id,  "未找到要编辑的收藏夹")
			.isLength(id, 24,  "数据错误:id") ;
			
			String userID = U.paramString(req.getSession(), C.userID);
			UserService uService = new UserService();
			User user = uService.checkAndGetUser(userID);

			FolderService fService = new FolderService();
			FolderDao fDao = new FolderDao();
			Folder folder = fDao.getByID(id);
			if (folder != null) {
				//判断用户id和要操作的文件夹所属用户 是否一致，否则报错
				if(!StringUtils.equals(user.id.toString(), folder.userID)){
					U.message(res, "操作失败:请登陆后再编辑");
				}
				fDao.deleteWithRef(folder);
			}
			 U.refreshSession(req.getSession());
			 U.resSuccess(res);
		} catch (Exception e) {
			e.printStackTrace();
			U.message(res, e.getMessage());
		}

	}

}
