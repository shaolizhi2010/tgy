package com.tgy.web.group;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tgy.dao.group.InterestGroupPageDao;
import com.tgy.entity.User;
import com.tgy.entity.group.InterestGroup;
import com.tgy.entity.group.InterestGroupFolder;
import com.tgy.entity.group.InterestGroupPage;
import com.tgy.exception.BaseException;
import com.tgy.service.group.GroupUserService;
import com.tgy.service.group.InterestGroupFolderService;
import com.tgy.service.group.InterestGroupPageService;
import com.tgy.service.group.InterestGroupService;
import com.tgy.util.C;
import com.tgy.util.U;
import com.tgy.validator.CommonValidator;

@RestController
@RequestMapping("/group/page")
public class GroupPageContoller extends BaseGroupContoller {

	@RequestMapping("/create/pre")
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		String groupID = U.filterCharacter(req.getParameter("groupID"));
		String folderID = U.filterCharacter(req.getParameter("folderID"));

		try {
			InterestGroupService s = new InterestGroupService();
			GroupUserService gus = new GroupUserService();

			CommonValidator validator = new CommonValidator();

			InterestGroup group = s.byID(groupID); // get group by id
			validator.isNotNull(group, null); // valide group
			validator.isNotNull(group.id, null); // valide group

			// 权限检查
			if (commonAuth(req, group, group.authCreate) == false) {
				U.forward(req, res, "/login.jsp");
				return;
			}
			// 通过权限检查
			req.setAttribute("groupID", groupID);
			req.setAttribute("folderID", folderID);

			U.forward(req, res, "/group/group.page.create.jsp");
			return;
		} catch (BaseException e) {
			U.resFailed(res, e.getMessage());
		}

	}

	@RequestMapping("/create")
	protected void create(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		String name = U.filterCharacter(req.getParameter("name"));
		String pageUrl = U.filterCharacter(req.getParameter("url"));
		String folderID = U.filterCharacter(req.getParameter("folderID"));
		String groupID = U.filterCharacter(req.getParameter("groupID"));

		try {
			// validate input
			CommonValidator validator = new CommonValidator();
			validator.isLonger(pageUrl, 1, "需要填写网址").isShorter(pageUrl, 2048,
					"网址太长");// isLogin(req, null).isSameUser(user, page,null).

			InterestGroupService s = new InterestGroupService();

			InterestGroup group = s.byID(groupID); // get group by id
			validator.isNotNull(group, null); // valide group
			validator.isNotNull(group.id, null); // valide group

			// loginUser 创建folder 的人
			User loginUser = U.param(req, C.user, User.class);
			String loginUserID = U.getUserID(req);

			// 权限检查
			if (commonAuth(req, group, group.authCreate) == false) {
				U.resFailed(res, "无权限进行此操作");
				return;
			}

			InterestGroupPage page = new InterestGroupPage();
			page.name = StringUtils.trim(name);

			// page.url = pageUrl;
			page.folderID = folderID;
			page.groupID = groupID;
			page.userID = U.getUserID(req);// creator

			String errMsg = "";
			List<String> urlList = getUrlList(pageUrl);
			if (urlList.size() <= 0) {
				U.resFailed(res, "不能识别网址");
				return;
			}

			InterestGroupFolder folder = new InterestGroupFolderService()
					.byID(folderID);

			if (urlList.size() > 1) {
				// for (String url : urlList) {
				// Page p = new Page();
				// p.url = url;
				// p.comment = page.comment;
				// p.userID = page.userID;
				// p.pid = page.pid;
				// p.createDate = U.dateTime();
				// errMsg += saveOnePage(page, user);
				// }
			} else {
				page.url = urlList.get(0);

				errMsg = saveOnePage(page, group, folder);
			}
			if (StringUtils.isNotBlank(errMsg)) {
				U.resFailed(res, errMsg);
			}

		} catch (BaseException e) {
			U.resFailed(res, e.getMessage());
		}
		U.resSuccess(res);
	}
	
	@RequestMapping("/edit")
	protected void edit(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		try {
			String id = U.filterCharacter(req.getParameter("pageID"));
			String name = U.filterCharacter(req.getParameter("PageName"));
			String url = U.filterCharacter(req.getParameter("pageUrl"));
			
			//validate input
			CommonValidator validator = new CommonValidator();
			
			validator.isLogin(req, null).isNotNull(id, "未找到要编辑的网址")
			.isLonger(name, 0, "网站名称不能为空")
			.isShorter(name, 100, "网站名称需小于100")
			.isLonger(url, 0, "网址不能为空")
			.isShorter(url, 300, "网址长度需小于300")
			.isLength(id, 24,  "数据错误:id");

			InterestGroupService gs = new InterestGroupService();
			
			InterestGroupPageDao pDao = new InterestGroupPageDao();
			InterestGroupPage page = pDao.byID(id);	
			validator.isNotNull(page, null);
			validator.isNotNull(page.id, null);
			
			InterestGroup group = gs.byID(page.groupID);
			validator.isNotNull(group, null);	//valide group
			validator.isNotNull(group.id, null);	//valide group
			
			//权限检查
			if(commonAuth(req, group,group.authUpdate) == false){
				U.resFailed(res, "无权限进行此操作");
				return;
			}
			
			page.name = name;
			page.url = url;
				
			pDao.save(page);
			U.resSuccess(res);
		} catch (Exception e) {
			e.printStackTrace();
			U.message(res, e.getMessage());
		}

	}
	
	

	private String saveOnePage(InterestGroupPage page, InterestGroup group,
			InterestGroupFolder folder) {
		try {
			page.url = StringUtils.trim(page.url);

			new CommonValidator()
					// .isLonger(page.name, 0, "网站名称不能为空")
					.isShorter(page.name, 100, "网站名称需小于100")
					.isLonger(page.url, 0, "网址不能为空")
					.isShorter(page.url, 300, "网址长度需小于300");

			// save page
			InterestGroupPageService pService = new InterestGroupPageService();
			pService.save(page);

			// save group data
			if (folder == null && group != null) { // 未指定folder 直接村group里
				InterestGroupService gs = new InterestGroupService();
				group.add(page);
				group.lastModifyDate = U.dateTime();
				gs.save(group);
			}

			// save folder data
			if (folder != null) {
				InterestGroupFolderService fs = new InterestGroupFolderService();
				folder.add(page);
				folder.lastModifyDate = U.dateTime();
				fs.save(folder);
			}

			return "";

		} catch (BaseException e) {
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
