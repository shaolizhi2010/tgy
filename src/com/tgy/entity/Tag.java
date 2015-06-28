package com.tgy.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.mongodb.morphia.annotations.Entity;

import com.tgy.entity.BaseEntity;
import com.tgy.util.PageType;

/**
 * 1 Tag表示网站的标签、如 “美剧” “电影” 等
 * 2 Tag 的type，即此tag的类型，如 “文字” “图片” “资源”
 * 3 如果一个 tag，name是美剧 type是资源， 那么这个tag就表示美剧有关的下载资源。
 * 4 用户每新建一个Folder，就会新增一个Tag，Tag的name 就是folder的name，type默认为link
 * 
 * @author qq
 *
 */
@Entity
public class Tag extends BaseEntity  implements Serializable {
	
	public static final int keepScore=10; //收藏一次得多少分
 
	public String name;
	public PageType type;//类型 文字？图片？默认是link
	
	public boolean isTopLevel;//顶层tag？ 在首页显示
	
	public List<String> subTags = new ArrayList<String>();

//	@Reference(ignoreMissing = true,lazy=true)
//	public User firstCreateBy;//第一个创建人
	
	
//	@Reference(ignoreMissing = true, lazy = true)
//	public List<Link> links; //此tag下所有的link

	//拥有此分类的用户
//	@Reference(ignoreMissing = true,lazy=true)
//	public List<User> users;  //有此tag对应folder 的所有用户
	
//	public void add(Link link) {
//		if (links == null) {
//			links = new ArrayList<>();
//		}
//		links.add(link);
//	}
//	public void add(User user) {
//		if (users == null) {
//			users = new ArrayList<>();
//		}
//		users.add(user);
//	}

	@Override
	public boolean equals(Object obj) {

		if (obj != null && obj instanceof Tag) {
			Tag t = (Tag) obj;
			if (StringUtils.equals(this.name, t.name) && this.type.equals(t.type)) { //by name and type
				return true;
			}
		}
		return false;
	}

}
