package com.tgy.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tgy.entity.Page;
import com.tgy.service.PageService;
import com.tgy.util.U;

@RestController
@RequestMapping("/page")
public class PageContoller extends HttpServlet {
 
	@RequestMapping("/delete")
	protected void delete(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		PageService ps = new PageService();
		
		String url = req.getParameter("url");
		 
		 if(StringUtils.isNotBlank( url)){
			 List<Page> list = ps.byUrl(url);
			 if(CollectionUtils.isNotEmpty(list)){
				 ps.deleteById(list.get(0).id);
				 U.resSuccess(res);
				 return;
			 }
		 }
		 U.resFailed(res, "未找到资源");
		
	
		
		
		
	}
	 

}
