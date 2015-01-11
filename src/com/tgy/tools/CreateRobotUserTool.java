package com.tgy.tools;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import com.tgy.dao.UserDao;
import com.tgy.entity.Folder;
import com.tgy.entity.Page;
import com.tgy.entity.User;
import com.tgy.service.FolderService;
import com.tgy.service.PageService;
import com.tgy.service.UserService;
import com.tgy.util.BingSearchSevice;
import com.tgy.util.MD5Util;

public class CreateRobotUserTool{

	public static void main(String[] args) {
		
		
		
		
		
//		BingSearchSevice bings  = new BingSearchSevice();
//		List<Page> pages =  bings.search2("时尚");
//		for(Page p : pages){
//			System.out.println(p.name);
//			System.out.println(p.url);
//		}

	}
	
	public List<String> createRobotUser(int userCount){
		List<String> createdUsers = new ArrayList<String>();
		for(int i = 0 ;i<userCount;i++){
			try {
				//创建user
				UserService us = new UserService();
				FolderService fs = new FolderService();
				PageService ps = new PageService();
				BingSearchSevice bings  = new BingSearchSevice();
				
				List<String> usernames = FileUtils.readLines(new File( "c:/data/name.txt"),"GBK" );
				int userIndex = new Random().nextInt( usernames.size());
				String username = usernames.get(userIndex);
				if(StringUtils.isBlank(username)){
					return createdUsers;
				}
				if(new UserDao().getByName(username)==null){ //用户名未被注册
					User user = new User();
					user.name = username;
					user.password   = MD5Util.toMD5("rrrrrr");;
					user.clicks = new Random().nextInt(5000);
					user.showTimes = user.clicks;
					user.isRobot=true;
					user.loginTimes = user.clicks;
					user.followsCount = new Random().nextInt(15);
					user.fansCount = new Random().nextInt(20);
					user.favScore = user.clicks;
					user.keeps = new Random().nextInt(10);
					
					//设置个性签名
					if(new Random().nextInt(4)==1){
						List<String> dess = FileUtils.readLines(new File( "c:/data/des.txt"),"GBK" );
						int desIndex = new Random().nextInt( dess.size());
						String des = dess.get(desIndex);
						user.publicMessage = des; 
					}
					
					//设置头像
					if(new Random().nextInt(2)==1){
						List<String> heads = FileUtils.readLines(new File( "c:/data/headimg.txt"),"GBK" );
						int headIndex = new Random().nextInt( heads.size());
						String head = heads.get(headIndex);
						user.headImgUrl = head; 
					}
					us.save(user);
					
					int catTimes = new Random().nextInt(3)+1;//创建1到4个目录
					int catTimeIndex = 0;
					while(catTimeIndex++ < catTimes){
						//create folder
						List<String> cats = FileUtils.readLines(new File( "c:/data/cat.txt"),"GBK" );//目录大全
						int catIndex = new Random().nextInt( cats.size());
						String catname = cats.get(catIndex);
						if(StringUtils.isBlank(catname)){
							return createdUsers;
						}
						
						Folder folder = new Folder();
						folder.name = catname;
						folder.userID = user.id.toString();
						folder.clicks = new Random().nextInt(100);
						folder.favScore = folder.clicks;
						folder.scanTimes = folder.clicks;
						
						folder.isRobot = true;
						
						fs.save(folder);
						
						//save pages
						List<Page> pages =  bings.search2(catname);
						for(Page p: pages){//一半概率添加
							if(new Random().nextBoolean()){
								continue;
							}
							p.userID = user.id.toString();
							p.folderID = folder.id.toString();
							p.pid = folder.id.toString();
							p.showTimes = new Random().nextInt(100);
							p.favScore = p.showTimes;
							p.clicks = p.showTimes;
							p.isRobot = true;
							
							ps.save(p);
							
							user.pageCount++;
							us.save(user);
						}
					}
					System.out.println(user.name);
					createdUsers.add(user.name);
				}//end if
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return createdUsers;
	}

}
