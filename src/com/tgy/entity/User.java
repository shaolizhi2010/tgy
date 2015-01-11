package com.tgy.entity;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity
public class User extends BaseEntity implements Serializable{
 
	public String name; //用户名
	public String password; //密码
	public String sex;//性别
	public String email;//
	public String qq;
	public String phone;
	//public String type; //用户类型
	
	//
	//public long experience; //经验值，评定用户级别，v2.0 开始使用，发帖等都可以增加经验。
	//public long money; //虚拟货币，预计v2.0开始用
	
	public String showType;//显示方式，导航方式或用户方式，2.0版会弃用。
	public boolean isPublic;//是否公共用户，即别的用户是否可以添加网址。兴趣组功能出来以后，这个功能逐步取消。
	public String publicMessage;//显示在页面上方的公告信息。
	public boolean isTemp;//是否是临时用户，2.0版会弃用
	public boolean isAdmin;
	
	//public String bookmarkLastUpdateTime;//书签最后更新时间，用来跟客户端时间比对，如果比客户端新，则返回最新数据给客户端，否则不返回数据，客户端使用localstorage存的数据，
	//这样可以降低服务器压力 提示客户端速度,客户端缓存会用到，暂时不启用
	
	public long pageCount;//收藏网页的个数
	public long fansCount;//粉丝个数
	public long followsCount;//关注数
	
	public int loginTimes; //登录次数
	public String lastLoginDate;//最后一次登录的时间 
	
	public String openID; //如果用qq登录，会有这个id
	public String headImgUrl; //如如果qq登录，用户头像
	
	@Override
	public boolean equals(Object obj) {//只根据id判断
		if(obj instanceof User){
			User user = (User)obj;
			if(id!=null && id.equals(user.id)){
				return true;
			}
			
		}
		return false;
	}
	
	

}
