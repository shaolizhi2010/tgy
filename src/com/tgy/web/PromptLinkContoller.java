//package com.tgy.web;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.commons.lang3.StringUtils;
//
//import com.tgy.dao.LinkDao;
//import com.tgy.statistic.entity.Link;
//import com.tgy.util.U;
//
///**
// * 创建新网址的页面，
// * 用户输入网站名，自动提示出相关网站名和url，然后一键收藏
// * @author qq
// *
// */
//@WebServlet("/link/prompt")
//public class PromptLinkContoller extends HttpServlet {
//
//	@Override
//	protected void service(HttpServletRequest req, HttpServletResponse res)
//			throws ServletException, IOException {
// 
//		String linkName =req.getParameter("linkname");
//		
//		try {
//			
//			LinkDao lDao = new LinkDao();
//			List<Link> links =  lDao.getByName(linkName);
//			
//			int i=0;
//			List<Map> returnList = new ArrayList<>();
//			for(Link l : links){
//				Map map = new HashMap<String, String>(); 
//				
//				String name = l.title;
//				if(name!=null && name.length()>30){
//					name = StringUtils.substring(name, 0,30)+"..";
//				}
//				map.put("name", name);
//				
//				String urlShow = l.url;
//				if(urlShow == null){
//					urlShow = "";
//				}
//				if (urlShow.length() > 24) {
//					urlShow = urlShow.substring(0, 16) + "..."
//							+ urlShow.substring(urlShow.length() - 8);
//				}
//				
//				map.put("url", l.url);
//				map.put("urlShow", urlShow);
//				
//				returnList.add(map);
//				if(i++>3){
//					break;
//				}
//			}
//			
//			String json = U.toJson(returnList);
//			U.message(res,json);
//		} catch (Exception e) {
//			e.printStackTrace();
//			U.resFailed(res, e.getMessage());
//		}
//		
//
//	}
//
//}
