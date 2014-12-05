package com.tgy.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.tgy.dao.LinkDao;
import com.tgy.entity.Page;
import com.tgy.entity.User;
import com.tgy.service.PageService;
import com.tgy.statistic.entity.Link;
import com.tgy.util.C;
import com.tgy.util.U;

/**
 * 创建新网址的页面，
 * 用户输入网站名，自动提示出相关网站名和url，然后一键收藏
 * @author qq
 *
 */
@WebServlet("/god/prompt")
public class GodContoller extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
 
		String inputValue =req.getParameter("inputValue");
		List<Map> returnList = new ArrayList<>();
		
		try {
			
			//已登录，优先查询用户收藏
			User user = U.param(req, C.user, User.class);
			if(user!=null && user.id!=null){
				
				PageService pService = new PageService();
				//通过name查询
				List<Page> pages = pService.search(user.id.toString(), inputValue, null, null);
				for(Page p : pages){
					addToResult(returnList, pageToMap(p) );
				}
				
				//结果太少，则通过url继续查询
				if(returnList.size()<3){
					pages =  pService.search(user.id.toString(),null , inputValue, null);
					for(Page p : pages){
						addToResult(returnList, pageToMap(p) );
					}
				}
			 
			}
			//结果太少，则通过 link表 的 name字段查询
			if(returnList.size()<5){
				LinkDao lDao = new LinkDao();
				List<Link> links =  lDao.getByName(inputValue);
				
				for(Link l : links){
					addToResult(returnList, linkToMap(l));
				}
				//结果太少，则通过link表的 url字段查询
				if(returnList.size()<5){
					//get by url
					links =  lDao.searchByUrl(inputValue)   ;
					for(Link l : links){
						addToResult(returnList, linkToMap(l));
					}
				}
			}
			
			if(returnList.size()>10){
				returnList = returnList.subList(0, 10);
			}
			
			String json = U.toJson(returnList);
			U.message(res,json);
		} catch (Exception e) {
			e.printStackTrace();
			U.resFailed(res, e.getMessage());
		}
		

	}

	private String parseUrlShow(String urlShow) {
		if(urlShow == null){
			urlShow = "";
		}
		urlShow = urlShow.replace("http://", "");
		urlShow = urlShow.replace("https://", "");
		if (urlShow.length() > 24) {
			urlShow = urlShow.substring(0, 16) + "..."
					+ urlShow.substring(urlShow.length() - 8);
		}
		return urlShow;
	}

	private String parseUrl(String link) {
		if (link != null && !link.startsWith("http:") && !link.startsWith("https:")) {
			link = "http://" + link;
		}
		return link;
	}

	private String parseName(String name) {
		if(name==null){
			name= "";
		}
		if(name!=null && name.length()>30){
			name = StringUtils.substring(name, 0,30)+"..";
		}
		return name;
	}
	
	private boolean checkExist(List<Map> returnList, String url){
		
		for(Map m :  returnList ){
			String oldURL = String.valueOf( m.get("url") );
			if(StringUtils.equals(oldURL, url)){
				return true;
			}
		}
		return false;
	}
	
	private Map linkToMap(Link l){

		Map map = new HashMap<String, String>(); 
		
		String name = l.title;
		name = parseName(name);
		map.put("name", name);
		
		String link = l.url;
		link = parseUrl(link);
		map.put("url", link);
		
		String urlShow = l.url;
		urlShow = parseUrlShow(urlShow);
		map.put("urlShow", urlShow);
		
		String iconPath = l.iconPath;
		if(StringUtils.isBlank(iconPath)){
			iconPath = "http://www.webhezi.com/images/defaultFav.png";
		}
		map.put("iconPath", iconPath);
		
		return map;
	}
	
	private Map pageToMap(Page p){
		Map map = new HashMap<String, String>(); 
		
		String name = p.name;
		name = parseName(name);
		map.put("name", name);
		
		String link = p.url;
		link = parseUrl(link);
		map.put("url", link);
		
		
		String urlShow = p.url;
		urlShow = parseUrlShow(urlShow);
		
		map.put("urlShow", urlShow);
		return map;
	}
	
	private void addToResult(List<Map> returnList, Map m){
		if( !checkExist(returnList, (String)m.get("url"))){
			returnList.add(m);
		}
	}

}
