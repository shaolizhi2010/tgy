package com.tgy.entity;

public class PageContainer {
	
	public boolean isPublic;
	public boolean isTemp;
	
	public String bookmarkLastUpdateTime;//书签最后更新时间，用来跟客户端时间比对，如果比客户端新，则返回最新数据给客户端，否则不返回数据，客户端使用localstorage存的数据，
	//这样可以降低服务器压力 提示客户端速度
	
	public String createDate;
	public String lastLoginDate;
	
	public String openID;
	public String headImgUrl;
}
