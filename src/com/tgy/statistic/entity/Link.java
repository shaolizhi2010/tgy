package com.tgy.statistic.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Reference;

import com.tgy.entity.BaseEntity;
import com.tgy.entity.Discuss;
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
public class Link extends BaseEntity  implements Serializable,Comparable  {
	
	public static final int keepScore=10; //收藏一次得多少分
 
	public String title; //网页的 meta title
	public String description; //网页的 meta desc
	public String iconPath; //网站小图标路径
	public String logoPath; //网站logo
	
	public String url;

	@Reference(ignoreMissing = true,lazy=true)
	public List<Tag> tags; //收藏了这个link的所有标签
	
	//收藏此网站的用户
	@Reference(ignoreMissing = true,lazy=true)
	public List<User> users; //收藏了这个link的所有用户
	
	//评论，（只保存直接评论，简介评论不保存）
	@Reference(ignoreMissing = true,lazy=true)
	public List<Discuss> discusses; //link的所有评论
	
	public String lastDiscuss;//最后一条评论内容，便于页面显示.
	
	@Reference(ignoreMissing = true,lazy=true)
	public User firstCreateBy;//第一个创建人
	
	//public String iconAvailable;//icon 图片是否可用  'true' 'false' or null
	
	public long commentsCount;//点评数
	
	public int digTimes; //抓取网页信息（title description） 次数，如超过一定次数（如3次）就不再抓取了

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
	public void add(Discuss discuss) {
		if (discusses == null) {
			discusses = new ArrayList<>();
		}
		discusses.add(discuss);
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
