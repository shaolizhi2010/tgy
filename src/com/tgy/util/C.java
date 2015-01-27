package com.tgy.util;

import java.math.BigDecimal;

public interface C {
	 int scoreCreatePage = 5;//添加
	 int scoreCreatefolder = 5;//添加
	 int scoreClick = 1; //点击
	 int scoreShoucang = 20;//收藏得分
	 int scoreUp = 10; //顶一下 得分
	 int scoreDown = -10;//踩一下 得分
	 int scoreNewUser = 20; //for group
	 
	 
	 String sourceBoardType_link = "link";
	 String sourceBoardType_group= "group";
	 String sourceBoardType_pan = "pan";
	
	//权限控制
	//0 自己，默认值
	//1O 超级管理员
	//2O 管理员
	//5O 任意登录用户     
	//9O 所有人,包括未登录
	public static int authOwner = 0;//只有所有者才能操作
	public static int authSuperAdmin = 10;//超级管理员
	public static int authAdmin = 20;//普通管理员
	public static int authGroupUser = 30;//群组成员
	public static int authLoginUser = 50;//登录用户
	public static int authEveryOne = 90;//所有人，不登陆的游客也可以
	
	String domain = ".webhezi.com";
	String userID = "userID";
	String user = "loginUser";
	String utf8 = "UTF-8";

	String title = "网址盒子-网址收藏-网址导航-网站分享";
	String errorMsg = "errorMsg";
	String htmlRefSearchA = "<a target='_blank' href='http://www.baidu.com/s?wd=zwanggou%20%E8%BF%94%E5%88%A9%E9%82%80%E8%AF%B7%E7%A0%81' title='百度一下'>搜索邀请码?</a>";

	String true_boolean = "true";
	String false_boolean = "false";

	String operationFailed = "操作未成功";
	String operationSuccess = "操作成功";
	String link = "link"; // 介绍人

	String deft = "default";
	String inValid = "inValid";// 失效
	BigDecimal inValidNum = new BigDecimal(-9999);

	String trace_init_value = "00000001";
	String amazon_name = "亚马逊中国";

	String ADT_benefit = "benefit";// account detail type 返利
	String ADT_takeMoney = "takeMoney";// account detail type 体现
	String ADT_linkReward = "linkReward";// account detail type 推广奖励

	String default_search_value = "";
	String unknow = "未知";

	String linkReward = "5";// 推荐一个用户 奖励 n 元

	String keywordVar = "$keyword$";

	// ------------------------------------------------------------------ keys
	// -----

	String baseURL = "baseURL";
	String encoding = "encoding";

	String loglevel = "loglevel";

	// ---------------------------------------------------------------- system
	// -----
	int BUFFER_SIZE = 1024;
	String file_separator = "\\";

	// --------------------------------------------------------------- path ---
	String nodata = "暂无数据";

	// ----------------------------------------------------------返利

}
