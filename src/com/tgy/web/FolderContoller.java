package com.tgy.web;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tgy.util.C;
import com.tgy.util.U;

@WebServlet("/folder/todo")
public class FolderContoller extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
//		String folderID = req.getParameter("folderID");
//		
//		String userID = U.getUserID(req);
//		if(userID==null){
//			//userID = U.randomNum(6);//随机生成一个6位数字，类似qq号
//			//U.cookie(req, res, C.userID, userID); 
//			//U.cookie(req, res, "password", "");//密码默认为空
//			
//			new DefaultBookmarkService().defaultBookmark(req, res);
//			
//		}
//		else{//
//			req.setAttribute(C.userID, userID);
//			U.forward(req, res, "/"+userID);
//		}
	}

}
