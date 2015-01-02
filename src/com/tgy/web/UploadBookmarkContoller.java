//package com.tgy.web;
//
//import java.io.IOException;
//import java.util.Date;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.MultipartConfig;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.Part;
//
//import org.apache.commons.collections.CollectionUtils;
//import org.apache.commons.io.IOUtils;
//import org.apache.commons.lang3.StringUtils;
//
//import com.tgy.dao.FolderDao;
//import com.tgy.dao.PageDao;
//import com.tgy.entity.Folder;
//import com.tgy.entity.Page;
//import com.tgy.exception.BaseException;
//import com.tgy.service.FolderService;
//import com.tgy.service.PageService;
//import com.tgy.util.U;
//import com.tgy.util.UploadBookmarkUtil;
//
//@MultipartConfig(  maxFileSize = 1024 * 1024 * 10)
//@WebServlet("/bookmark/upload")
//public class UploadBookmarkContoller extends HttpServlet {
//
//	@Override
//	protected void service(HttpServletRequest req, HttpServletResponse res)
//			throws ServletException, IOException {
//		
//		String userID = U.getUserID(req);
// 
//		if(StringUtils.isBlank(userID)){
//			U.message(res, "操作失败:请先登录");
//			return;
//		}
//		
//		FolderDao fDao = new FolderDao();
//		PageDao pDao = new PageDao();
//		
//		
//		Part part = req.getPart("bookmarkFile");
//		if(part == null){
//			//no file..  
//			U.message(res, "操作成功:但没有文件");
//		}
//		String source = IOUtils.toString(part.getInputStream(),"UTF-8");
//		String bookmarkName = new Date().toString();
//		
//		//从上传的书签文本中解析出来的folder
//		Folder uploadFolder = UploadBookmarkUtil.toFolder(source);
//		
////		//去掉没有意义的下层文件夹
////		while(uploadFolder!=null && uploadFolder.pages==null && uploadFolder.folders.size()==1){
////			uploadFolder = uploadFolder.folders.get(0);
////		}
//		
//		Folder rootFolder = new Folder();
//		rootFolder.name = bookmarkName;
//		rootFolder.userID = userID;
//		rootFolder.createDate = U.dateTime();
//		//rootFolder.isRoot = true;
//		fDao.save(rootFolder);
//		//保存root文件夹中的网页
//		if(uploadFolder.pages!=null){
//			for(Page p : uploadFolder.pages){
//				p.userID = userID;
//				p.pid = rootFolder.id.toString();
//				p.createDate = U.dateTime();
//				pDao.save(p);
//	 
//				rootFolder.add(p);
//			}
//			fDao.save(rootFolder);
//		}
//		//保存子文件夹
//		if(uploadFolder.folders!=null){
//			for(Folder f : uploadFolder.folders){
//				saveFolder(f,userID,rootFolder);
//			}
//		}
//		
//		
//		//System.out.println(new Gson().toJson(folder));
//		//res.sendRedirect("localhost/tgy/");
//		//U.forward(req, res, "/");
//		U.resSuccess(res);
//
//	}
//	
//	public void saveFolder(Folder uploadFolder,   String userID,Folder rootFolder){
//		FolderService fService = new FolderService();
//		PageService pService = new PageService();
//		
//		if(uploadFolder!=null){// &&  uploadFolder.name!=null
//			
//			//保存文件夹
//			Folder folder = new Folder();
//			folder.name = uploadFolder.name;
//			folder.userID = userID;
//			folder.pid = rootFolder.id.toString();
//			try {
//				long tempTime = System.currentTimeMillis();
//				fService.save(folder);
//				//System.out.println("fService.save(folder) " + folder.name+ " - " + (System.currentTimeMillis()-tempTime));
//			} catch (BaseException e) {
//				System.out.println("导入书签:保存Folder："+e.getMessage());
//			}
//			
//			//save pages
//			if( !CollectionUtils.isEmpty(uploadFolder.pages )   ){
//				for(Page p : uploadFolder.pages){
//					p.pid = folder.id.toString();
//					p.userID = userID;
//					try {
//						long tempTime = System.currentTimeMillis();
//						pService.save(p);
//						//System.out.println("pService.save(p) " + p.name+ " - " + (System.currentTimeMillis()-tempTime));
//					} catch (BaseException e) {
//						System.out.println("导入书签:保存网页："+e.getMessage());
//					}
// 
//					folder.add(p);
//				}
//			}
//			 
//				new FolderDao().save(folder);
// 
//			
//			//设置父文件的folders 并保存
//			//pFolder.add(folder);
//			//fDao.save(pFolder);
//			
//			//有过有子文件夹，则继续递归遍历 子文件夹
//			if( !CollectionUtils.isEmpty( uploadFolder.folders )  ){
//				for(Folder f : uploadFolder.folders){
//					saveFolder(f,userID,rootFolder);
//				}
//			}
//			
//		}
//	}
//	
//	
//	 
//
//}
