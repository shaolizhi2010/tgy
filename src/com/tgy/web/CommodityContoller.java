package com.tgy.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tgy.dao.UserDao;
import com.tgy.entity.Page;
import com.tgy.entity.Tag;
import com.tgy.entity.User;
import com.tgy.service.PageService;
import com.tgy.service.commodity.AmazonCommodityService;
import com.tgy.service.commodity.CommonCommodityService;
import com.tgy.statistic.service.TagService;
import com.tgy.util.ConditionMap;
import com.tgy.util.PageType;
import com.tgy.util.U;

@RestController
@RequestMapping( value = {"/commodity","/commodity/"}  )
public class CommodityContoller extends HttpServlet {
	
	
	@RequestMapping(value = {"/ad"} )
	public void tag(HttpServletRequest req, HttpServletResponse res) {
		
		String tagName = req.getParameter("tagName");
		String countStr = req.getParameter("count"); //前台需要几个
		if(StringUtils.isBlank(tagName)){
			tagName = null;
		} 
		if(StringUtils.isBlank(countStr)){
			countStr = "1";
		}
 
		PageService ps = new PageService();
		List<Page> pages = ps.list(new ConditionMap().add("type", PageType.commodity).add("tagName", tagName).add("isShare", true), null, 0,10);
		
		if(pages==null || pages.size()==0){//取通用 （网购发现）
			// new AmazonCommodityService().search(tagName, pages, null, null, null);
			//new CommonCommodityService().search(tagName, "jingdong");
			pages = ps.list(new ConditionMap().add("type", PageType.commodity).add("tagName", "网购发现").add("isShare", true), null, 0,10);
		}
		if(CollectionUtils.isEmpty(pages)){ //empty
			U.message(res, "");
			return;
		}
//		int count = Integer.parseInt(countStr);
//		if(count==1){
//			ArrayList<Page> returnList = new ArrayList<>();
//			  returnList.add( pages.get(new Random().nextInt(pages.size())));  //get random one
//			  pages = returnList;
//		}
//		else if(pages.size()>count){			//sub 
//			pages =  pages.subList(0, count);
//		}
//		for(Page p : pages){
//			p.idStr = p.id.toString();
//		}
		Page p = pages.get(new Random().nextInt(pages.size()));
		//U.message(res, U.toJson(pages)); //response json 
		U.forward(req, res, "/part/share/shareElement2.jsp?pageID="+p.id.toString());
	}
	
//	@RequestMapping(value = {"/baidupan"} )
//	public void baidupan(HttpServletRequest req, HttpServletResponse res) {
//		int start = NumberUtils.toInt(req.getParameter("start"));
//		PageService ps = new PageService();
//		List<Page> pages = ps.list(new ConditionMap().add("type", PageType.baidupan).add("isShare", true), "-createDate", start,10);
//		long count = ps.count(ps.createQuery(new ConditionMap().add("type", PageType.baidupan).add("isShare", true), "-createDate", 0,0));
//		req.setAttribute("pages", pages);
//		req.setAttribute("type", PageType.baidupan);
//		req.setAttribute("start", start);
//		req.setAttribute("count", count);
//		req.setAttribute("keywordForMeta", PageType.baidupan.value()+"网址分享");
//		U.forward(req, res, "/index-all.jsp");
//	}
}
