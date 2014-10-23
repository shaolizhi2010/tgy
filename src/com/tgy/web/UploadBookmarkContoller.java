package com.tgy.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.print.attribute.standard.PDLOverrideSupported;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Key;

import com.google.gson.Gson;
import com.tgy.dao.BookmarkDao;
import com.tgy.dao.FolderDao;
import com.tgy.dao.PageDao;
import com.tgy.entity.Bookmark;
import com.tgy.entity.Folder;
import com.tgy.entity.Page;
import com.tgy.service.BookmarkService;
import com.tgy.util.BookmarkUtil;
import com.tgy.util.C;
import com.tgy.util.U;
import com.tgy.util.UploadBookmarkUtil;

@MultipartConfig(  maxFileSize = 1024 * 1024 * 10)
@WebServlet("/bookmark/upload/")
public class UploadBookmarkContoller extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		String userID = null;
		if(req.getSession().getAttribute(C.userID) != null){
			 userID = String.valueOf(req.getSession().getAttribute(C.userID));
		}
		if(StringUtils.isBlank(userID)){
			//not login TODO
		}
		 
		BookmarkService bkservice = new BookmarkService();
		BookmarkDao bkDao = new BookmarkDao();
		PageDao pDao = new PageDao();
		
		
		Part part = req.getPart("bookmarkFile");
		if(part == null){
			//no file.. TODO
		}
		String source = IOUtils.toString(part.getInputStream(),"UTF-8");
		String bookmarkName = new Date().toString();
		 
		Folder uploadFolder = UploadBookmarkUtil.toFolder(source);
		
		Bookmark bookmark = new Bookmark();
		bookmark.name = bookmarkName;
		bookmark.userID = userID;
		bookmark.createDate = U.dateTime();
		String bookmarkID = bkDao.save(bookmark).getId().toString();
		
		//去掉没有意义的下层文件夹
		while(uploadFolder!=null && uploadFolder.pages==null && uploadFolder.folders.size()==1){
			uploadFolder = uploadFolder.folders.get(0);
		}
		
		for(Page p : uploadFolder.pages){
			p.userID = userID;
			p.bookmarkID = bookmarkID;
			pDao.save(p);
			if(bookmark.pages==null){
				bookmark.pages = new ArrayList<>();
			}
			bookmark.pages.add(p);
			bkDao.save(bookmark);
			
		}
		
		for(Folder f : uploadFolder.folders){
			saveFolder(f, null,userID,bookmark);
		}
		
		
		//从新设置用户session中 bookmark数据
		req.getSession().setAttribute("userBookmarks", bkservice.getBookmarks(userID));
		
		//System.out.println(new Gson().toJson(folder));
		
		U.forward(req, res, "/default.jsp");  

	}
	
	public void saveFolder(Folder uploadFolder, Folder parentFolder, String userID,Bookmark bookmark){
		FolderDao fDao = new FolderDao(); 
		BookmarkDao bkDao = new BookmarkDao();
		PageDao pDao = new PageDao();
		
		if(uploadFolder!=null){// &&  uploadFolder.name!=null
			
			//保存文件夹
			Folder folder = new Folder();
			folder.name = uploadFolder.name;
			folder.userID = userID;
			folder.bookmarkID = bookmark.id.toString();
			
			//设置pid
			if(parentFolder!=null){
				folder.pid = parentFolder.id.toString();	
			}
			if(uploadFolder.pages!=null && uploadFolder.pages.size()>0){
				for(Page p : uploadFolder.pages){
					p.bookmarkID = bookmark.id.toString();
					p.userID = userID;
					
					pDao.save(p);
					if(folder.pages==null){
						folder.pages = new ArrayList<>();
					}
					folder.pages.add(p);
				}
			}
			
			 fDao.save(folder);
			
			//设置父文件的folders 并保存
			if(parentFolder !=null ){
				if(parentFolder.folders==null){
					parentFolder.folders = new ArrayList<>();
				}
				parentFolder.folders.add(folder);
				fDao.save(parentFolder);
			}
			else{//顶级文件夹
				if(bookmark.folders==null){
					bookmark.folders = new ArrayList<>();
				}
				bookmark.folders.add(folder);
				bkDao.save(bookmark);
			}
			
			//继续递归遍历 upload文件夹
			if(uploadFolder.folders !=null && uploadFolder.folders.size()>0){
				for(Folder f : uploadFolder.folders){
					saveFolder(f, folder,userID,bookmark);
				}
			}
			
		}
	}
	
	
	 

}
