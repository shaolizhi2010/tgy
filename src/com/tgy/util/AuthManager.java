package com.tgy.util;

import org.apache.commons.lang3.StringUtils;

import com.tgy.entity.StateFulBaseEntity;
import com.tgy.entity.User;
import com.tgy.entity.group.GroupUser;
import com.tgy.entity.group.InterestGroup;
import com.tgy.exception.BaseException;
import com.tgy.service.group.GroupUserService;
import com.tgy.service.group.InterestGroupService;
import com.tgy.validator.CommonValidator;

/**
 * 负责权限验证
 * @author qq
 *
 */
public class AuthManager {
	
	//是否是登录用户
	public static boolean checkLogin(User user){
		if(user!=null && user.id!=null ){
			return true;
		}
		return false;
	}
	
	//是否是owner
	public static boolean checkJoined(User user, InterestGroup group){
		if(group==null || group.id == null){//没有目标，不能操作
			return false;
		}
 
		//未登录 不可以操作
		if(user==null || user.id==null ){
			return false;
		}
		GroupUserService gus = new GroupUserService();
		GroupUser groupUser = gus.get(new ConditionMap().add("userID", user.id.toString())
				.add("groupID",group.id.toString()));
		if(groupUser!=null){  
			return true;
		}
 
		return false;
	}
	
	//是否是owner
	public static boolean checkOwner(User user, StateFulBaseEntity entity){
		if(entity==null){//没有目标，不能操作
			return false;
		}
		//目标没有owner 可以操作
		if(entity.userID == null  ){
			return true;
		}
		//未登录 不可以操作
		if(user==null || user.id==null ){
			return false;
		}
		//是owner
		if(StringUtils.equals(user.id.toString(), entity.userID)){
			return true;
		}
		return false;
	}
	
	//是否是管理员
	public static boolean checkAdmin(User user){
		if(user!=null && user.id!=null && user.isAdmin ){
			return true;
		}
		return false;
	}
	
	//是否是管理员
	public static boolean checkAdmin(GroupUser user){
		if(user!=null && user.id!=null && user.isAdmin ){
			return true;
		}
		return false;
	}
	
	//是否是超级管理员
	public static boolean checkSuperAdmin(GroupUser user){
		if(user!=null && user.id!=null && user.isSuperAdmin ){
			return true;
		}
		return false;
	}
	
	//create 权限
	public boolean groupCreateAuth(User loginUser, InterestGroup group ){
		return auth(loginUser, group, group.authCreate);
	}
	
	
	
	//update 权限
	public boolean groupUpdateAuth(User loginUser, InterestGroup group ){
		 return auth(loginUser, group, group.authUpdate);
	}
		
	//auth 权限
	public boolean auth(User loginUser, InterestGroup group, int authCode){
		try {
			GroupUserService gus = new GroupUserService();
			
			CommonValidator validator = new CommonValidator();
			
			//权限检查
			if( authCode == C.authOwner ){ //是否是同一个用户
				
				if(loginUser == null || loginUser.id ==null){
					return false;
				}
				//检查是否shi管理员
				String loginUserID = loginUser.id.toString();
				GroupUser groupUser = gus.get(
						new ConditionMap()
							.add("userID",loginUserID)
							.add("groupID", group.id.toString())
						);
				if(groupUser!=null ){
					 if(  checkAdmin(groupUser) || checkSuperAdmin(groupUser)){//是管理员，则有权限
						 return true;
					 }
				}
				//检查是否shi管理员 结束
				
				//不是管理员，检查是否是同一个用户
				validator.isSameUser(loginUser, group, null);
			}
			else if(authCode == C.authLoginUser){
				if(loginUser!=null && loginUser.id!=null){
					return true;
				}
			}
			else if(authCode == C.authEveryOne){
				return true;
			}
			else if(authCode == C.authGroupUser){ //group 成员
				 
				if(loginUser == null || loginUser.id ==null){
					return false;
				}
				String loginUserID = loginUser.id.toString();
				
				GroupUser groupUser = gus.get(
						new ConditionMap()
							.add("userID",loginUserID)
							.add("groupID", group.id.toString())
						);
				if(groupUser==null){
					throw new BaseException(this, "无权限进行此操作");	
				}
			}
			else if(authCode == C.authAdmin){ //admin
				 
				if(loginUser == null || loginUser.id ==null){
					return false;
				}
				String loginUserID = loginUser.id.toString();
				
				GroupUser groupUser = gus.get(
						new ConditionMap()
							.add("userID",loginUserID)
							.add("groupID", group.id.toString())
						);
				if(!AuthManager.checkAdmin( groupUser) && !AuthManager.checkSuperAdmin( groupUser)){
					throw new BaseException(this, "无权限进行此操作");	
				}
			}
			else if(group.authUpdate == C.authSuperAdmin){ //admin
				 
				if(loginUser == null || loginUser.id ==null){
					return false;
				}
				String loginUserID = loginUser.id.toString();
				
				GroupUser groupUser = gus.get(
						new ConditionMap()
							.add("userID",loginUserID)
							.add("groupID", group.id.toString())
						);
				if(!AuthManager.checkSuperAdmin( groupUser)){
					throw new BaseException(this, "无权限进行此操作");	
				}
			}
		} catch (BaseException e) {
			return false;
		}
	
		//权限检查 end
		return true;
	}

}
