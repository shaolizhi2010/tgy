package com.tgy.web.group;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tgy.entity.group.InterestGroup;
import com.tgy.entity.group.InterestGroupFolder;
import com.tgy.exception.BaseException;
import com.tgy.service.group.GroupUserService;
import com.tgy.service.group.InterestGroupFolderService;
import com.tgy.service.group.InterestGroupService;
import com.tgy.util.U;
import com.tgy.validator.CommonValidator;

@RestController
@RequestMapping("/group/folder")
public class GroupFolderContoller extends BaseGroupContoller {

	@RequestMapping(value = { "/{fid}/{name}","/{fid}" })
	public void list(HttpServletRequest req, HttpServletResponse res,
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
 
		U.forward(req, res, "/group/group.view.jsp");
		
	}
	
	@RequestMapping("/create/pre")
	protected void preCreate(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		String groupID = U.filterCharacter(req.getParameter("groupID"));
		
		try {
		 	InterestGroupService s = new InterestGroupService();
			GroupUserService gus = new GroupUserService();
			
			CommonValidator validator = new CommonValidator();
			
			InterestGroup group = s.byID(groupID);	//get group by id
			validator.isNotNull(group, null);	//validate group
			validator.isNotNull(group.id, null);	//validate group

			//权限检查
			if(commonAuth(req, group,group.authCreate) == false){
				U.forward(req, res, "/login.jsp");
				return;
			}
			//通过权限检查
			req.setAttribute("groupID", groupID);
			
			U.forward(req, res, "/group/group.folder.create.jsp");
			return;
		} catch (BaseException e) {
			U.resFailed(res, e.getMessage());
		}
		 
	} 
	@RequestMapping("/create")
	protected void create(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		String name = U.filterCharacter(req.getParameter("name")) ;
		String groupID = U.filterCharacter(req.getParameter("groupID")) ;
		
		try {
			//validate input
			CommonValidator validator = new CommonValidator();
			validator.isNotNull(groupID, null).isLonger(name, 0, "名称不能为空")
			.isShorter(name, 60, "名称需小于60");
			
			InterestGroupFolderService  fs = new InterestGroupFolderService();
			InterestGroupService s = new InterestGroupService();
			
			InterestGroup group = s.byID(groupID);	//get group by id
			validator.isNotNull(group, null);	//valide group
			validator.isNotNull(group.id, null);	//valide group
			
			//权限检查
			if(commonAuth(req, group,group.authCreate) == false){
				U.resFailed(res, "无权限进行此操作");
				return;
			}
			//通过权限检查
			
			//初始化 folder
			InterestGroupFolder folder = new InterestGroupFolder(); 
			folder.name = StringUtils.trim(name);
			folder.groupID = groupID;
			fs.save(folder); //save folder
			
			//为 group 添加 folder 索引
			group.add(folder);
			s.save(group); //save folder into group
			
			U.resSuccess(res);
		} catch (BaseException e) {
			U.resFailed(res, e.getMessage());
		}
	}
	
	@RequestMapping("/edit")
	protected void edit(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		String folderID = U.filterCharacter(req.getParameter("folderID")) ;
		String folderName = U.filterCharacter(req.getParameter("folderName")) ;
		
		try {
			//validate input
			CommonValidator validator = new CommonValidator();
			validator.isNotNull(folderName, null).isLonger(folderName, 0, "名称不能为空")
			.isShorter(folderName, 60, "名称需小于60");
			
			InterestGroupFolderService  fs = new InterestGroupFolderService();
			InterestGroupService gs = new InterestGroupService();
			
			InterestGroupFolder folder =  fs.byID(folderID);
			validator.isNotNull(folder, null);
			validator.isNotNull(folder.id, null);
			
			String groupID = folder.groupID;
			InterestGroup group = gs.byID(groupID);
			validator.isNotNull(group, null);	//valide group
			validator.isNotNull(group.id, null);	//valide group
			
			//权限检查
			if(commonAuth(req, group,group.authUpdate) == false){
				U.resFailed(res, "无权限进行此操作");
				return;
			}
			//通过权限检查
			
			//初始化 folder
			folder.name = StringUtils.trim(folderName);
			fs.save(folder); //save folder
			
			U.resSuccess(res);
		} catch (BaseException e) {
			U.resFailed(res, e.getMessage());
		}
	}
	
	@RequestMapping("/copy")
	protected void copy(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		InterestGroupFolderService fs = new InterestGroupFolderService();
		
		try {
			new CommonValidator().isLogin(req, null) ;
			
			String folderID = U.filterCharacter(req.getParameter("folderID")) ;
			String userID = U.getUserID(req);
			fs.copy(folderID, userID);
			
			U.resSuccess(res);
		} catch (BaseException e) {
			U.resFailed(res, e.getMessage());
		}
		
	
	}

}
