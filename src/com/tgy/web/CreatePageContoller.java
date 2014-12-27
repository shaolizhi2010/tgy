package com.tgy.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tgy.dao.FolderDao;
import com.tgy.entity.Folder;
import com.tgy.entity.Page;
import com.tgy.entity.User;
import com.tgy.exception.BaseException;
import com.tgy.service.PageService;
import com.tgy.service.UserService;
import com.tgy.util.C;
import com.tgy.util.U;
import com.tgy.validator.CommonValidator;

@WebServlet("/page/create")
public class CreatePageContoller extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
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
				p.createDate = U.dateTime();
				errMsg += saveOnePage(page, user);
			}
		} else {
			page.url = urlList.get(0);
			page.createDate = U.dateTime();
			errMsg = saveOnePage(page, user);
		}

		U.refreshSession(req.getSession());
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

}
