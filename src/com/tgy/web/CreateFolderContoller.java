package com.tgy.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tgy.dao.FolderDao;
import com.tgy.entity.Folder;
import com.tgy.util.U;

@WebServlet("/folder/create/")
public class CreateFolderContoller extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		  Folder folder = U.fromReqJson(req, Folder.class);

			new FolderDao().saveWithRef(folder);
			
			U.resSuccess(res);
	}

}
