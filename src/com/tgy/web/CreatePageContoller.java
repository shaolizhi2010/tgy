package com.tgy.web;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.stuxuhai.jpinyin.PinyinHelper;
import com.tgy.dao.FolderDao;
import com.tgy.entity.Folder;
import com.tgy.entity.Page;
import com.tgy.entity.PanSearchCache;
import com.tgy.entity.Tag;
import com.tgy.entity.User;
import com.tgy.exception.BaseException;
import com.tgy.service.FolderService;
import com.tgy.service.PageService;
import com.tgy.service.PanSearchCacheService;
import com.tgy.service.UserService;
import com.tgy.service.cache.AppCache;
import com.tgy.statistic.service.TagService;
import com.tgy.util.C;
import com.tgy.util.FuliDou;
import com.tgy.util.PageType;
import com.tgy.util.U;
import com.tgy.util.URLUtils;
import com.tgy.validator.CommonValidator;

@RestController
@RequestMapping("/page/create")
public class CreatePageContoller extends HttpServlet {

	@RequestMapping()
	protected void createPage(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		User user = U.param(req, C.user, User.class);

		Page page = new Page();
		String comment = U.filterCharacter(req.getParameter("comment"));
		String userID = U.filterCharacter(req.getParameter("userID"));
		String pid = U.filterCharacter(req.getParameter("pid"));
		String name = U.filterCharacter(req.getParameter("name"));
		String pageUrl = U.filterCharacter(req.getParameter("url"));
		page.comment = comment;
		page.userID = userID;
		page.pid = pid;
		page.name = name;
		page.url = pageUrl;
		page.folderID = pid;
		page.lastModifyDate = U.dateTime();
		try {
			CommonValidator validator = new CommonValidator();
			validator.isLonger(page.url, 1, "需要填写网址");//isLogin(req, null).isSameUser(user, page,null).
			User showUser = new UserService().getByID(userID);
			if(showUser!=null && showUser.authCreate==0){//只有自己可以添加
				validator.isLogin(req, null).isSameUser(user, page,null);
			}
		} catch (BaseException e) {
			U.resFailed(res, e.getMessage());
		}

		String errMsg = "";
		List<String> urlList = getUrlList(page.url);
		if(urlList.size()<=0){
			U.resFailed(res, "不能识别网址");
			return;
		}
		
		if (urlList.size() > 1) {
			for (String url : urlList) {
				Page p = new Page();
				p.url = url;
				p.comment = page.comment;
				p.userID = page.userID;
				p.pid = page.pid;
				errMsg += saveOnePage(page, user);
			}
		} else {
			page.url = urlList.get(0);
			errMsg = saveOnePage(page, user);
		}

		U.resSuccess(res);

	}

	private String saveOnePage(Page page, User user) {
		try {
			page.name = StringUtils.trim(page.name);

			page.url = StringUtils.trim(page.url);

			new CommonValidator()
					// .isLonger(page.name, 0, "网站名称不能为空")
					.isShorter(page.name, 100, "网站名称需小于100")
					.isLonger(page.url, 0, "网址不能为空")
					.isShorter(page.url, 300, "网址长度需小于300");

			if (StringUtils.isNotBlank(page.pid)) {
				FolderDao fDao = new FolderDao();
				Folder pFolder = fDao.getByID(page.pid);
				if (pFolder == null) {
					// U.resFailed(res, "所属分类未找到");
					return "所属分类未找到";
				}
				User targetUser = new UserService().getByID(pFolder.userID);
				if(targetUser!=null && targetUser.authCreate==0){
					if (!StringUtils.equals(pFolder.userID, user.id.toString())) {
						return "无权限进行此操作";
					}
				}
				
				pFolder.lastModifyDate = U.dateTime();
				fDao.save(pFolder);
			}
			PageService pService = new PageService();
			pService.save(page);
			return "";

		} catch (BaseException e) {
			// e.printStackTrace();
			// U.resFailed(res, e.getMessage());
			return e.getMessage();
		}
	}

	private List<String> getUrlList(String urls) {
		if (urls == null) {
			return new ArrayList<>();
		}

		String[] strArray = urls.split("[\r\n]");

		List<String> list = new ArrayList<>();
		for (String line : strArray) {
			line = line.trim();
			if (line.contains(" ")) { // 如果有空格，取第一个空格前面的
				line = StringUtils.substringBefore(line, " ");
			}
			if (line.matches("^.+[.].+$")) {// 是url
				list.add(line);
			}
		}

		return list;

	}
	
	@RequestMapping("/share")
	protected void createSharePage(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		String pageUrl = U.filterCharacter(req.getParameter("url"));
		try {
			CommonValidator validator = new CommonValidator();
			validator.isLonger(pageUrl, 1, "需要填写网址");
		} catch (BaseException e) {
			U.resFailed(res, e.getMessage());
		}
		if(!URLUtils.isValid(pageUrl)){
			U.resFailed(res, "无法识别网址");
		}
		
		UserService us = new UserService();
		
		Page page = new Page();
		
		String tagName = U.filterCharacter(req.getParameter("tagName"));
		String type = U.filterCharacter(req.getParameter("type"));
		
		String name = U.filterCharacter(req.getParameter("name"));
		String comment = U.filterCharacter(req.getParameter("comment"));
		String imgSrc = U.filterCharacter(req.getParameter("imgSrc"));
		
		page.title = name;page.name = name;
		
		page.comment = comment;page.description = comment;page.summary=comment;
		page.imgSrc = imgSrc;
		
		String firstLetter = PinyinHelper.getShortPinyin(name);
		page.firstLetter = firstLetter;
		page.lastModifyDate = U.dateTime();
		
		if(StringUtils.isBlank(type) || !EnumUtils.isValidEnum(PageType.class, type)){
			type = PageType.resource.name();
		}
		PageType pType = PageType.valueOf(type);
		
		
		//user
		User user = U.param(req, C.user, User.class);
//		if(user == null){
//			user = us.getByName("游客");//if not login, use guest
//		}
		
		if(StringUtils.isBlank(tagName)){
			tagName = "百度网盘";
		}
		//create tag if not exist.
		TagService ts = new TagService();
		Tag t = ts.getByName( tagName , pType);
		if(t==null || t.id==null){ //create tag if not exist
			t = new Tag();
			t.name = tagName;
			t.type = pType;
			ts.save(t);
		}
		
		//folder
//		FolderService fs = new FolderService();
//		if(user==null || user.id==null){
//			System.out.println(this.getClass().getName()+ " 无法找到 文件夹 " + tagName +" ");
//			return;
//		}
//		Folder f  = fs.ByUserIDAndName(tagName,user.id.toString());
//		if(f==null||f.id==null){ //if not exist, create it
//			f = new Folder();
//			f.name = tagName;
//			f.userID = user.id.toString();
//			try {
//				fs.save(f);
//			} catch (BaseException e) {
//				System.out.println(this.getClass().getName()+ ": 文件夹 " + tagName +" 无法创建");
//				return;
//			}
//		}
		
		page.url = pageUrl;
		page.tagName = tagName;
		page.type = pType;
		if(user!=null && user.id!=null){
			page.userID = user.id.toString();
		}
		
//		page.pid = f.id.toString();
//		page.folderID = f.id.toString();
		page.isShare = true;
		page.orignDate = U.dateTime();
		try {
			PageService ps = new PageService();
			ps.save(page);
		} catch (BaseException e) {
			System.out.println(this.getClass().getName()+" : 保存网址失败 " + page.ups );
			System.out.println(e.getMessage());
			return;
		}

		U.resSuccess(res);
		
		if(user!=null){
			user.fulidou += FuliDou.createSharePageScore;
			new UserService().save(user);
		}
		
		//重置缓存
		AppCache.defaultPagesClear();
		
	}
	
	@RequestMapping("/cache")
	protected void createCache(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		String name = U.filterCharacter(req.getParameter("name"));
		String comment = U.filterCharacter(req.getParameter("comment"));
		String imgSrc = U.filterCharacter(req.getParameter("imgSrc"));
		String url = U.filterCharacter(req.getParameter("url"));
		String key = U.filterCharacter(req.getParameter("key"));
		
		if(StringUtils.isNotBlank(name)){
			name = URLDecoder.decode(name, "utf-8");
		}
		if(StringUtils.isNotBlank(comment)){
			comment = URLDecoder.decode(comment, "utf-8");
		}
		if(StringUtils.isNotBlank(imgSrc)){
			imgSrc = URLDecoder.decode(imgSrc, "utf-8");
		}
		if(StringUtils.isNotBlank(url)){
			url = URLDecoder.decode(url, "utf-8");
		}
		if(StringUtils.isNotBlank(key)){
			key = URLDecoder.decode(key, "utf-8");
		}
		PanSearchCacheService s = new PanSearchCacheService();
		
		PanSearchCache cache = new PanSearchCache();
		
		cache.title = name;
		cache.name = name;
		
		cache.comment = comment;
		cache.description = comment;
		cache.summary=comment;
		cache.imgSrc = imgSrc;
		
		cache.url = url;
		
		cache.key = key;
		
		s.save(cache);
		
	}

}
