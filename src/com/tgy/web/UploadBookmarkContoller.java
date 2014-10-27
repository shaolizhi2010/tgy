package com.tgy.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.ldap.Rdn;
import javax.print.attribute.standard.PDLOverrideSupported;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Key;

import com.google.gson.Gson;
import com.tgy.dao.FolderDao;
import com.tgy.dao.PageDao;
import com.tgy.entity.Folder;
import com.tgy.entity.Page;
import com.tgy.util.FolderUtil;
import com.tgy.util.C;
import com.tgy.util.U;
import com.tgy.util.UploadBookmarkUtil;

@MultipartConfig(  maxFileSize = 1024 * 1024 * 10)
@WebServlet("/bookmark/upload/")
public class UploadBookmarkContoller extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		String userID = U.paramString(req.getSession(), C.userID);
 
		if(StringUtils.isBlank(userID)){
			U.message(res, "操作失败:请先登录");
		}
		
		FolderDao fDao = new FolderDao();
		PageDao pDao = new PageDao();
		
		
		Part part = req.getPart("bookmarkFile");
		if(part == null){
			//no file..  
			U.message(res, "操作成功:但没有文件");
		}
		String source = IOUtils.toString(part.getInputStream(),"UTF-8");
		String bookmarkName = new Date().toString();
		
		//从上传的书签文本中解析出来的folder
		Folder uploadFolder = UploadBookmarkUtil.toFolder(source);
		
		//去掉没有意义的下层文件夹
		while(uploadFolder!=null && uploadFolder.pages==null && uploadFolder.folders.size()==1){
			uploadFolder = uploadFolder.folders.get(0);
		}
		
		Folder rootFolder = new Folder();
		rootFolder.name = bookmarkName;
		rootFolder.userID = userID;
		fDao.save(rootFolder);
		
		if(uploadFolder.pages!=null){
			for(Page p : uploadFolder.pages){
				p.userID = userID;
				p.pid = rootFolder.id.toString();
				pDao.save(p);
	 
				rootFolder.add(p);
			}
			fDao.save(rootFolder);
		}
		if(uploadFolder.folders!=null){
			for(Folder f : uploadFolder.folders){
				saveFolder(f,userID,rootFolder);
			}
		}
		
		U.refreshSession(req.getSession());
		
		//System.out.println(new Gson().toJson(folder));
		
		U.message(res, "操作成功");

	}
	
	public void saveFolder(Folder uploadFolder, String userID,Folder pFolder){
		FolderDao fDao = new FolderDao(); 
		PageDao pDao = new PageDao();
		
		if(uploadFolder!=null){// &&  uploadFolder.name!=null
			
			//保存文件夹
			Folder folder = new Folder();
			folder.name = uploadFolder.name;
			folder.userID = userID;
			folder.pid = pFolder.id.toString();
			fDao.save(folder);
 
			if( !CollectionUtils.isEmpty(uploadFolder.pages )   ){
				for(Page p : uploadFolder.pages){
					p.pid = folder.id.toString();
					p.userID = userID;
					pDao.save(p);
 
					folder.add(p);
				}
			}
			fDao.save(folder);
			
			//设置父文件的folders 并保存
			pFolder.add(folder);
			fDao.save(pFolder);
			
			//有过有子文件夹，则继续递归遍历 子文件夹
			if( !CollectionUtils.isEmpty( uploadFolder.folders )  ){
				for(Folder f : uploadFolder.folders){
					saveFolder(f,userID,folder);
				}
			}
			
		}
	}
	
	
	 

}
