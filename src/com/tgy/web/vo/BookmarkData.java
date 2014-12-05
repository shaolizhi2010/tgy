package com.tgy.web.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.tgy.entity.Folder;

public class BookmarkData implements Serializable {

	//public User curUser;
	
	public List<Folder> rootFolders; // 用户的所有跟收藏夹
	
	public Folder folder = null; // 用户当前查看的收藏夹
	
	//public Folder curFolder = null;// 用户当前选中的分类或收藏夹
	//public Folder rootFolder = null;// 用户当前选中的收藏夹（屏幕左侧的） ,根收藏夹


	public List<Folder> getRootFolders() {
		if(rootFolders == null){
			return new ArrayList<>();
		}
		return rootFolders;
	}
 

}
