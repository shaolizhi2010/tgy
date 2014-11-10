package com.tgy.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.tgy.dao.FolderDao;
import com.tgy.entity.Folder;
import com.tgy.vo.BreadCrumb;

public class BreadCrumbUtil {
	
	public static BreadCrumb build(Object obj,String contextPath){
		if(obj == null || !(obj instanceof Folder)){
			return null;
		}
		Folder folder = (Folder)obj;
		FolderDao folderDao = new FolderDao();
		
		List<BreadCrumb> list = new ArrayList<>();
		while(folder!=null ){
			//TODO tgy is hardcode
			BreadCrumb bread = new BreadCrumb(folder.name, contextPath+"/folder/"+folder.id);
			list.add(bread);
			if(StringUtils.isNotBlank( folder.pid)){
				folder = folderDao.getByID(folder.pid);
			}
			else{
				folder = null;
			}
		}
		 Collections.reverse(list);
		return build(list);
	}
	
	public static BreadCrumb build(List<BreadCrumb> list){
		
		if(list==null || list.size()==0){
			return new BreadCrumb("糖果云书签", "#");
		}
		
		BreadCrumb finalBread =null;
		for(BreadCrumb bread : list){
			if(finalBread == null){
				finalBread = bread;
			}
			else{
				add(finalBread, bread.name, bread.link);
			}
		}
		return finalBread;
		 
	}
	
	public static void add(BreadCrumb bread, String name, String link) {
		if (bread == null) {
			return;
		}
		add(bread, new BreadCrumb(name, link));
	}
	
	public static void add(BreadCrumb bread, BreadCrumb subBread) {
		if (bread == null || subBread ==null) {
			return;
		}
		if (bread.child == null) {
			bread.child = subBread;
		} else if (bread.child != null) { 
			add(bread.child, subBread);// 递归子孙节点 寻找最后一级（child==null）并设置
		}
	}
	
	
	
//	public static BreadCrumb next(){
//		
//	}
	
}
