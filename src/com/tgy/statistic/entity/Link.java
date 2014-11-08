package com.tgy.statistic.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;

import com.tgy.entity.User;

/**
 * 每个url 对应一个link对象
 * Link对象用来记录每个url的各种信息，如：
 * title、收藏次数等
 * 
 * 于Page的区别：
 * Page：用户没收藏一次某页面，都会生成一条page的记录，
 * 保存一些用户相关的信息，如userID、用户为page起的名字等
 * Link： 一个url只会对应一个Link对象，主要用于统计分析和推荐
 *
 * 
 * 
 * @author qq
 *
 */
@Entity
public class Link implements Serializable,Comparable  {
	
	public static final int keepScore=10; //收藏一次得多少分
	
	@Id
	public ObjectId id;
	
	public String title; //网页的 meta title
	public String description; //网页的 meta desc
	public String iconPath; //网站小图标路径
	public String logoPath; //网站logo
	
	public String url;

	public String createDate;//创建日期

	@Reference(ignoreMissing = true,lazy=true)
	public List<Tag> tags; //收藏了这个link的所有标签
	
	//收藏此网站的用户
	@Reference(ignoreMissing = true,lazy=true)
	public List<User> users; //收藏了这个link的所有用户
	
	@Reference(ignoreMissing = true,lazy=true)
	public User firstCreateBy;//第一个创建人
	
	public long clicks; //点击次数
	public long keeps; //收藏次数
	
	public long favScore; //收欢迎程度得分

	public void add(Tag tag) {
		if (tags == null) {
			tags = new ArrayList<>();
		}
		tags.add(tag);
	}
	public void add(User user) {
		if (users == null) {
			users = new ArrayList<>();
		}
		users.add(user);
	}
	
	@Override
	public boolean equals(Object obj) {

		if (obj != null && obj instanceof Link) {
			Link f = (Link) obj;
			if (StringUtils.equals(this.title, f.title)) {
				return true;
			}
		}
		return false;
	}
	@Override
	public int compareTo(Object o2) {
		
		Link p2 = (Link)o2;
		
		
		if(this.favScore > p2.favScore){
			return 1;
		}
		
		else if(this.favScore == p2.favScore) {
			return 0;
		}
		else{
			return -1;
		}
		
	}
}
