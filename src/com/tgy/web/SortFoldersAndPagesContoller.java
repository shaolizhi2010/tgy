package com.tgy.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tgy.dao.FolderDao;
import com.tgy.dao.PageDao;
import com.tgy.entity.Folder;
import com.tgy.entity.Page;
import com.tgy.entity.User;
import com.tgy.exception.BaseException;
import com.tgy.util.C;
import com.tgy.util.U;
import com.tgy.validator.CommonValidator;

@RestController
@RequestMapping(value = { "/sort/FoldersAndPages" })
public class SortFoldersAndPagesContoller extends HttpServlet {

	/* 
	 * 数据如下
	 * [{"folderID":"5462c888789039f3fc2c0262","pages":["5462c893789039f3fc2c0263","5462c8b1789039f3fc2c0265","5462c99e789039f3fc2c0267","5462c9ae789039f3fc2c0269","5462ca40789039f3fc2c026b","5464057d7890053f141f2805","54641881789022a68c8ccd06","5479ae503ed0c84c2afc6ad8","547c2dde3ed02fbf116d483d"]},
	 * {"folderID":"5462ce0b7890698c4a09400e","pages":["5462ce0b7890698c4a09400f","5462ce0b7890698c4a094010","5462ce0b7890698c4a094011","5462ce0b7890698c4a094012","5462ce0b7890698c4a094013","5462ce0b7890698c4a094014","5462ce0b7890698c4a094015","5462ce0b7890698c4a094016","5462ce0b7890698c4a094017","5462ce0b7890698c4a094018","5465e82b7890cd2b0e76c91f"]},
	 * {"folderID":"546abc6678905f92022fb0c3","pages":["546d41e078901735978236f9","54704e7659964e9ee4ec0c71","54704f3759964e9ee4ec0c73","5470519c5996e80ac670a6ac","547053f059969229893192d4","5470540659969229893192d5","54705aa35996668e7c520195","54705b3a5996ed33905484d0","54705e2e5996763ca2945fb4","54705f38599684513a2cb254","54705f6a599684513a2cb255","547073bd599651a34c3123bc","54709d6a59963b78589a9af8","5471a6c17890687ce1811ee0","5471a6d97890687ce1811ee1","5471a6e67890687ce1811ee2","5471dd0e78903cf1f42b5420","5475231b3ed0e81ef2b14b1f","547523cd3ed0e81ef2b14b20","5477d1a83ed0dc8e25bd95d2","547958db3ed0dbbae36b7c5d","547c2e873ed021ab87acfba2","547c2ee33ed080dce5502c62","547c2f373ed080dce5502c64","547c2fe93ed08856e6fae0c3","547c303e3ed08856e6fae0c4","547c32923ed0bc06796f3fa5","547c34c63ed0bc06796f3fa7","547c34f83ed0bc06796f3fa9","54868b700750aea2a1fd07b1","54868b750750aea2a1fd07b2","548adfe40750a8bc37d99760","548adffd0750a8bc37d99761","548b04530750ecd31e07efaf","5493ec661a9063ab9661a1ed","5493ec6d1a9063ab9661a1ee","5493f3851a9002387b880ca5","5493f3951a9002387b880ca6","5493f40f1a9002387b880ca7"]},{"folderID":"5462ce0b7890698c4a09401d","pages":["5462ce0b7890698c4a09401e","5462ce0b7890698c4a09401f"]},
	 * {"folderID":"5462ce0b7890698c4a094019","pages":["5462ce0b7890698c4a09401a","5462ce0b7890698c4a09401b","5462ce0b7890698c4a09401c"]},{"folderID":"5462ce0b7890698c4a094021","pages":["5462ce0b7890698c4a094022","5462ce0b7890698c4a094024","5462ce0b7890698c4a094025"]},{"folderID":"546406067890053f141f2806","pages":["5464066f7890053f141f2809","546406877890053f141f280b","54893b0412d0ddb3668c91dc","54893b9012d0ddb3668c91dd"]},{"folderID":"5462ce0b7890698c4a094020","pages":[]},{"folderID":"5462ce0b7890698c4a094026","pages":["5462ce0b7890698c4a094027"]},{"folderID":"5462ce0b7890698c4a09400b","pages":["5462ce0b7890698c4a09400c","5462ce0b7890698c4a09400d","5487936307500d34a0f7422b","5487936307500d34a0f7422b","548793a707500d34a0f7422c","548793b807500d34a0f7422d"]},{"folderID":"54631e6e789075dff04b54ed","pages":[]},{"folderID":"5481bde107509e37a7fd5f72","pages":[]}]
	 * */
	@RequestMapping( )
	public void sort(HttpServletRequest req, HttpServletResponse res ) {
		long start = System.currentTimeMillis();
		List<Map > folders = U.fromReqJson(req, List.class);
		FolderDao fd = new FolderDao();
		PageDao pd = new PageDao();
		
		User user = U.param(req, C.user, User.class);
		
		try {
			CommonValidator validator = new CommonValidator();
			validator.isLogin(req, "需要登陆").isNotNull(user.id,"需要登陆");
			
			int folderSortIndex = 1;
			for(Map  folderMap: folders){
				String folderID = (String)folderMap.get("folderID");
				Folder folderEntity = fd.getByID(folderID);
				if(folderEntity!=null && folderEntity.sortOrder != folderSortIndex){
					try {
						validator.isSameUser(user, folderEntity, "");
					} catch (Exception e) {//没有权限，忽略
						continue;
					}
					folderEntity.sortOrder = folderSortIndex;	
					fd.save(folderEntity);
				}
				folderSortIndex++;
				
				List<String> pages = (List<String>)folderMap.get("pages");
				int pageSortIndex = 1;
				for(String pageID : pages){
					Page p = pd.getByID(pageID);
					if(p!=null && p.sortOrder != pageSortIndex){
						try {
							validator.isSameUser(user, p, "");
						} catch (Exception e) {//没有权限，忽略
							continue;
						}
						p.sortOrder = pageSortIndex;
						pd.save(p);
					}
					pageSortIndex++;
				}
			}
			System.out.println("重排序用时 ： "+ (System.currentTimeMillis()-start));
		} catch (BaseException e) {
			//U.message(res, e.getMessage());
			return;
		}
		
		
			 
//			for( Map.Entry<String, List<String>> folderElement : folderMap.entrySet()){ 
//				System.out.println(folderElement.getKey());
//				for(String pageID :folderElement.getValue()){
//					System.out.println(pageID);
//				}
//			}
			
			//System.out.println(folder.entrySet().toArray());

		
		
//		Map<String, List<String> > folders2 = U.requestToMap(req);
//		
//		for( Map.Entry<String, List<String>> folder : folders.entrySet()){
//			System.out.println(folder.getKey());
//			for( String pageID : folder.getValue()){
//				System.out.println(pageID);
//			}
//			
//		}
		
		//U.forward(req, res, "/index-1.jsp");
		
	}

}
