package com.tgy.entity.group;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Reference;

import com.tgy.entity.Page;
import com.tgy.entity.StateFulBaseEntity;
import com.tgy.util.C;

/**
 * 兴趣组
 * @author qq
 *
 */
@Entity
public class InterestGroup extends StateFulBaseEntity implements Serializable{
 
	public String name; //名称
	public String headImgUrl;//group img
	//public String type; //类型
	
	public int userCount;//用户数
	
	@Reference(ignoreMissing = true, lazy = true)
	public List<GroupUser> users;//所有用户
	
	@Reference(ignoreMissing = true, lazy = true)
	public List<InterestGroupFolder> folders;//所有子文件夹

	@Reference(ignoreMissing = true, lazy = true)
	public List<InterestGroupPage> pages; //包含的页面
 
	
	public String publicMessage;//显示在页面上方的公告信息。
	
	public int authCreateDiscuss = C.authEveryOne;
	
	public void add(InterestGroupFolder folder) {
		if (folders == null) {
			folders = new ArrayList<>();
		}
		folders.add(folder);
	}

	public void remove(InterestGroupFolder folder){
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
	
	public void add(InterestGroupPage page) {
		if (pages == null) {
			pages = new ArrayList<>();
		}
		pages.add(page);
	}
	
	public void remove(InterestGroupPage page){
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
