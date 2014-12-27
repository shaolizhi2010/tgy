package com.tgy.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tgy.entity.Folder;
import com.tgy.entity.User;
import com.tgy.exception.BaseException;
import com.tgy.service.FolderService;
import com.tgy.util.C;
import com.tgy.util.U;
import com.tgy.validator.CommonValidator;

@RestController
@RequestMapping("/folder/copy")
public class CopyFolderContoller extends HttpServlet {

	@RequestMapping( method = RequestMethod.POST )
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		String folderID = U.filterCharacter(req.getParameter("folderID")) ;
		try {
			new CommonValidator().isLogin(req, null) ;
			
			FolderService fService = new FolderService();
			
			String userID = U.getUserID(req);
			fService.copy(folderID, userID);
			
			U.resSuccess(res);
		} catch (BaseException e) {
			U.resFailed(res, e.getMessage());
		}


	}

}
