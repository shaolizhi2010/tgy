package com.tgy.web.group;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tgy.entity.GroupDiscuss;
import com.tgy.entity.User;
import com.tgy.entity.group.GroupUser;
import com.tgy.entity.group.InterestGroup;
import com.tgy.entity.group.InterestGroupPage;
import com.tgy.exception.BaseException;
import com.tgy.service.group.GroupDiscussService;
import com.tgy.service.group.GroupUserService;
import com.tgy.service.group.InterestGroupPageService;
import com.tgy.service.group.InterestGroupService;
import com.tgy.util.AuthManager;
import com.tgy.util.C;
import com.tgy.util.ConditionMap;
import com.tgy.util.U;
import com.tgy.validator.CommonValidator;

@RestController
@RequestMapping(value = { "/g/" })
public class GroupContoller extends HttpServlet {
	
	//group index
	@RequestMapping(value = { "/{groupID}" })
	protected void index(HttpServletRequest req, HttpServletResponse res,
			@PathVariable("groupID") String groupID) throws ServletException,
			IOException {
		if ("favicon".equals(groupID)) {
			return;
		}

		InterestGroup group = new InterestGroupService().byID(groupID);
		
		if(group==null){
			U.forward(req, res, "/not-found.jsp");
			return;
		}
		InterestGroupPageService ps = new InterestGroupPageService();
		List<InterestGroupPage> pages =ps.list(new ConditionMap().add("groupID", groupID), null, 30);
		
		
		if(group!=null){
			req.setAttribute("group", group);
			req.setAttribute("pages", pages);
			//req.setAttribute("pages", group.pages);
			
		}
		
		U.forward(req, res, "/group/group.view.jsp");
	}
	
	//加入群组
	@RequestMapping(value = { "/join" })
	protected void join(HttpServletRequest req, HttpServletResponse res) 
			  throws ServletException,
			IOException {

		User loginUser = U.param(req, C.user, User.class);
		if(loginUser==null || loginUser.id == null){
			U.resFailed(res, "请先登录");
			return;
		}
		
		InterestGroupService groupService = new InterestGroupService();
		String groupID = U.filterCharacter(req.getParameter("groupID"));
		InterestGroup group = groupService.byID(groupID);
		
		if(group==null){
			U.resFailed(res, "群组不存在");
			return;
		}
		group.userCount++;
		groupService.save(group);
		
		GroupUserService gus = new GroupUserService();
		GroupUser groupUser = gus.get(new ConditionMap().add("userID", loginUser.id.toString())
				.add("groupID",groupID));
		if(groupUser!=null){ //已经是成员了
			U.resFailed(res, "您已经是群组成员了");
			return;
		}
		groupUser = new GroupUser();
		groupUser.setUser(loginUser);
		groupUser.setGroup(group);
		
		gus.save(groupUser);
		U.resSuccess(res);
	}
	
	@RequestMapping("/create")
	protected void create(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		String name = U.filterCharacter(req.getParameter("name")) ;
		//String userID = U.filterCharacter(req.getParameter("userID")) ;
		
		String authCreateStr = U.filterCharacter(req.getParameter("authCreate")) ;
		if(StringUtils.isBlank(authCreateStr) || NumberUtils.isNumber(authCreateStr)){
			authCreateStr = C.authGroupUser+"";
		}
		String authUpdateStr = U.filterCharacter(req.getParameter("authUpdate")) ;
		if(StringUtils.isBlank(authUpdateStr) || NumberUtils.isNumber(authUpdateStr) ){
			authUpdateStr = C.authOwner+"";
		}
		
		try {
			User user = U.param(req, C.user, User.class);
			
			name = StringUtils.trim(name);
			new CommonValidator().isLogin(req, null).isLonger(name, 0, "名称不能为空")
				.isShorter(name, 60, "名称需小于60");
			
			GroupUserService guService = new GroupUserService();
			InterestGroupService gService = new InterestGroupService();
			
			GroupUser groupuser = new GroupUser();
			groupuser.setUser(user);
			groupuser.isSuperAdmin = true;
			groupuser.isAdmin = true;
			groupuser.useAble =true;
			guService.save(groupuser);
			
			InterestGroup group = new InterestGroup();
			group.name = name;
			group.authCreate = Integer.parseInt(authCreateStr);
			group.authUpdate = Integer.parseInt(authUpdateStr);
			group.userID = U.getUserID(req);
			//group.createDate = U.dateTime();
			//group.useAble = true;
			group.add(groupuser);
			
			gService.save(group);
			groupuser.setGroup(group);
			guService.save(groupuser);
			
			U.resSuccess(res);
		} catch (BaseException e) {
			U.resFailed(res, e.getMessage());
		}
	}
	
	@RequestMapping(value = { "/users/{groupID}" })
	protected void users(HttpServletRequest req, HttpServletResponse res,
			@PathVariable("groupID") String groupID)
			throws ServletException, IOException {
		
		try{
			
			U.resSuccess(res);
		} catch (Exception e) {
			U.resFailed(res, e.getMessage());
		}
	}
	
	@RequestMapping(value = { "/discuss/create" })
	protected void createDiscuss(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		try{
			String groupID = U.filterCharacter(req.getParameter("groupID")) ;
			String msg = U.filterCharacter(req.getParameter("msg")) ;
			String userID = U.getUserID(req);
			String soucrceIP = U.getIpAddr(req);
			
			CommonValidator validator = new CommonValidator();
			validator.isNotNull(msg, null)
				.isShorter(msg, 600, "你发的消息有点长，请长话短说").isLonger(msg,1, "需大于1个字");
			
			InterestGroupService s = new InterestGroupService();
			
			InterestGroup group = s.byID(groupID);	//get group by id
			validator.isNotNull(group, null);	//valide group
			validator.isNotNull(group.id, null);	//valide group

			//loginUser 创建folder 的人
			User loginUser = U.param(req, C.user, User.class);
			String loginUserID = U.getUserID(req);
			
			//权限检查
			if(new AuthManager().auth(loginUser, group,group.authCreateDiscuss) == false){
				U.resFailed(res, "无权限进行此操作");
				return;
			}
			
			GroupDiscussService gds = new GroupDiscussService();
			GroupDiscuss discuss = new GroupDiscuss();
			discuss.userID = userID;
			discuss.groupID = groupID;
			discuss.message = msg;
			discuss.soucrceIP = soucrceIP;
			
			gds.save(discuss);
			
			U.resSuccess(res);
		} catch (Exception e) {
			U.resFailed(res, e.getMessage());
		}
	}

}
