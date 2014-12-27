package com.tgy.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Reference;

/**
 * 兴趣组
 * @author qq
 *
 */
@Entity
public class InterestGroup extends BaseEntity implements Serializable{
 
	public String name; //名称
	//public String type; //类型
	
	public String userCount;//用户数
	
	@Reference(ignoreMissing = true, lazy = true)
	public List<GroupUser> users;//所有用户
	
	@Reference(ignoreMissing = true, lazy = true)
	public List<Folder> folders;// 所有子文件夹

	@Reference(ignoreMissing = true, lazy = true)
	public List<Page> pages; // 文件夹包含的页面
 
	
	public String publicMessage;//显示在页面上方的公告信息。
	
	public void add(Folder folder) {
		if (folders == null) {
			folders = new ArrayList<>();
		}
		folders.add(folder);
	}

	public void remove(Folder folder){
		if (folders != null) {
			folders.remove(folder);
		}
	}
	
	public void add(GroupUser user) {
		if (users == null) {
			users = new ArrayList<>();
		}
		users.add(user);
	}

	public void remove(GroupUser user){
		if (users != null) {
			users.remove(user);
		}
	}
	
	public void add(Page page) {
		if (pages == null) {
			pages = new ArrayList<>();
		}
		pages.add(page);
	}
	
	public void remove(Page page){
		if (pages != null) {
			for(int i=0;i<pages.size();i++){
				if(pages.get(i).id!=null &&
						page.id!= null &&
						StringUtils.equals(pages.get(i).id.toString(), page.id.toString())   ){
					pages.remove(i);
				}
			}
		}
	}
	

}
