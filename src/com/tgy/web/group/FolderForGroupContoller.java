package com.tgy.web.group;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tgy.entity.User;
import com.tgy.entity.group.InterestGroup;
import com.tgy.entity.group.InterestGroupFolder;
import com.tgy.service.IndexService;
import com.tgy.service.UserService;
import com.tgy.service.group.InterestGroupFolderService;
import com.tgy.service.group.InterestGroupService;
import com.tgy.util.U;
import com.tgy.web.vo.BookmarkData;

@RestController
@RequestMapping(value = { "/" })
public class FolderForGroupContoller extends HttpServlet {

	IndexService indexService = new IndexService();

	@RequestMapping(value = { "/group/folder/{fid}/{name}","/group/folder/{fid}" })
	public void index(HttpServletRequest req, HttpServletResponse res,
			@PathVariable("fid") String fid) {
		
		if("favicon".equals(fid)){
			return;
		}
		
		InterestGroupFolderService gfs = new InterestGroupFolderService();
		InterestGroupService gs = new InterestGroupService();
		InterestGroupFolder folder = gfs.byID(fid);
		if(folder!=null && StringUtils.isNotBlank(folder.groupID)){
			req.setAttribute("showFolder", folder);
			InterestGroup group = gs.byID(folder.groupID);
			if(group!=null){
				group.showTimes++;
				group.favScore++;
				gs.save(group);
				req.setAttribute("group", group);
			}
		}

//		BookmarkData data =  indexService.getBookmarkDataByFolderID(fid);
		
		
//		BreadCrumb bread = BreadCrumbUtil.build(
//				data.folder,
//				req.getContextPath());
//		req.setAttribute("bread", bread);
		U.forward(req, res, "/group/group.view.jsp");
		
	}

}
