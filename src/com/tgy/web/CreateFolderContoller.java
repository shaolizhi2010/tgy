package com.tgy.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tgy.dao.FolderDao;
import com.tgy.entity.Folder;
import com.tgy.entity.User;
import com.tgy.exception.BaseException;
import com.tgy.service.FolderService;
import com.tgy.util.C;
import com.tgy.util.U;
import com.tgy.validator.CommonValidator;

@RestController
@RequestMapping("/folder/create")
public class CreateFolderContoller extends HttpServlet {

	@RequestMapping( method = RequestMethod.POST )
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		String name = U.filterCharacter(req.getParameter("name")) ;
		String folderUserID = U.filterCharacter(req.getParameter("userID")) ;
		
		Folder folder = new Folder();
		folder.name = name;
		folder.userID = folderUserID;
		
		try {
			User user = U.param(req, C.user, User.class);
			
			folder.name = StringUtils.trim(folder.name);
			new CommonValidator().isLogin(req, null).isNotNull(folder, null).isSameUser(user, folder, null)
				.isLonger(folder.name, 0, "名称不能为空")
				.isShorter(folder.name, 60, "名称需小于60");
			
			
			
			
			FolderService fService = new FolderService();
			
			String userID = U.getUserID(req);
			if(   !StringUtils.equals(folder.userID, userID)){
				U.resFailed(res, "无权限进行当前操作,请先登陆");
				return;
			}
			
			fService.save(folder);
			U.resSuccess(res);
		} catch (BaseException e) {
			U.resFailed(res, e.getMessage());
		}


	}

}
