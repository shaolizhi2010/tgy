package com.tgy.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;

/**
 * Tag表示网站的标签、类别
 * 用户每新建一个Folder，就会新增一个Tag，Tag的name 就是folder的name，
 * 如果Tag已存在，Tag的计数（keeps）就会加一
 * 
 * @author qq
 *
 */
@Entity
public class Tag implements Serializable {
	
	public static final int keepScore=10; //收藏一次得多少分
	
	@Id
	public ObjectId id;
	public String name;

	public String createDate;
	@Reference(ignoreMissing = true,lazy=true)
	public User firstCreateBy;//第一个创建人
	
	public int keeps; //收藏次数
	public long favScore; //受欢迎得分
	
	@Reference(ignoreMissing = true, lazy = true)
	public List<Link> links; //此tag下所有的link

	//拥有此分类的用户
	@Reference(ignoreMissing = true,lazy=true)
	public List<User> users;  //有此tag对应folder 的所有用户
	
	public void add(Link link) {
		if (links == null) {
			links = new ArrayList<>();
		}
		links.add(link);
	}
	public void add(User user) {
		if (users == null) {
			users = new ArrayList<>();
		}
		users.add(user);
	}

	@Override
	public boolean equals(Object obj) {

		if (obj != null && obj instanceof Tag) {
			Tag t = (Tag) obj;
			if (StringUtils.equals(this.name, t.name)) { //by name
				return true;
			}
		}
		return false;
	}

}
