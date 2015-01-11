package com.tgy.tools;
import java.io.StringReader;
import java.net.URLEncoder;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.SimpleXmlSerializer;
import org.htmlcleaner.TagNode;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;

import com.tgy.entity.Folder;
import com.tgy.entity.Page;
import com.tgy.entity.User;
import com.tgy.entity.group.InterestGroup;
import com.tgy.entity.group.InterestGroupFolder;
import com.tgy.entity.group.InterestGroupPage;
import com.tgy.exception.BaseException;
import com.tgy.service.FolderService;
import com.tgy.service.PageService;
import com.tgy.service.UserService;
import com.tgy.service.group.InterestGroupFolderService;
import com.tgy.service.group.InterestGroupPageService;
import com.tgy.service.group.InterestGroupService;
import com.tgy.util.C;
import com.tgy.util.SimpleConnecter;
import com.tgy.util.U;
import com.tgy.util.X;

public class CovertDataTool {

	public static void main(String[] args) {
		System.out.println("start");
		try {
			
			String userID = "5462c7dd7890e2d092e823ac";
			String groupID = "54a111b782101ca241e2c79f";
			
			userToGroup(userID, groupID);;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("end");
	}

	public static void userToGroup(String userID, String groupID){
		UserService us = new UserService();
		InterestGroupService gs = new InterestGroupService();
		FolderService fs = new FolderService();
		InterestGroupFolderService gfs = new InterestGroupFolderService();
		
		InterestGroup group = gs.byID(groupID);
		if(StringUtils.equals(group.convertDataFlag, C.true_boolean)){
			System.out.println("已转移过数据,请修改flag后再转");
			return;
		}
		User user = us.getByID(userID);
		List<Folder> folders =  fs.getFoldersByUserID(userID);
		
		for(Folder folder : folders){
			InterestGroupPageService pageService =   new InterestGroupPageService();
			
			InterestGroupFolder targetFolder = new InterestGroupFolder();
			targetFolder.name = folder.name;
			targetFolder.groupID = groupID;
			targetFolder.userID  = userID;
			targetFolder.copyBasic(folder);
			targetFolder.showTimes = folder.clicks;
			
			for(Page p : folder.pages){
				
				InterestGroupPage newPage = new InterestGroupPage();
				newPage.name = p.name;
				newPage.description = p.description;
				newPage.url = p.url;
				newPage.iconPath = p.iconPath;
				newPage.folderID = p.pid;
				newPage.copyBasic(p);
				newPage.showTimes = p.clicks;
				
				newPage.userID = userID;
				newPage.groupID = groupID;
				
				pageService.save(newPage);
				
				targetFolder.add(newPage);
			}
			gfs.save(targetFolder);
			group.add(targetFolder);
			group.pageCount += folder.pages.size();
			group.convertDataFlag = C.true_boolean;
			
		}
		gs.save(group);
	}
	
}
