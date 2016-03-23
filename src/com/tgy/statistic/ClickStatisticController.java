package com.tgy.statistic;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.tgy.dao.FolderDao;
import com.tgy.dao.LinkDao;
import com.tgy.dao.PageDao;
import com.tgy.dao.TagDao;
import com.tgy.entity.Folder;
import com.tgy.entity.Page;
import com.tgy.entity.Tag;
import com.tgy.entity.Topic;
import com.tgy.exception.BaseException;
import com.tgy.service.TopicService;
import com.tgy.statistic.entity.Link;
import com.tgy.statistic.service.TagService;
import com.tgy.util.U;
import com.tgy.validator.CommonValidator;

@WebServlet("/statistic/click")
public class ClickStatisticController extends HttpServlet {
	
	CommonValidator validator = new CommonValidator();
	TagService ts = new TagService();
	PageDao pDao = new PageDao();
	FolderDao fDao = new FolderDao();
	LinkDao lDao = new LinkDao();
	TagDao tDao = new TagDao();
	TopicService topicService = new TopicService(); 

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		String id = U.filterCharacter(req.getParameter("id")) ;
		String type = U.filterCharacter(req.getParameter("type")) ;

		try {
			validator.isNotNull(id, null)
					.isNotNull(type, null);//isLogin(req, null).

			if ("page".equals(type)) {
				
				Page page = pDao.byID(id);
				if (page != null) {
					page.clicks++;
					page.showTimes++;
					page.favScore++;
					pDao.save(page);
					
					
//					Folder folder = fDao.getByID(page.pid);
//					if (folder != null) {
//						folder.showTimes+=1;
//						folder.clicks++;
//						folder.favScore+=1;
//						fDao.save(folder);
//	 
//					} 

					
//					Link link = lDao.getByUrl(page.url);
//					if (link != null) {
//						link.clicks++;
//						link.showTimes++;
//						link.favScore++;
//						lDao.save(link);
//					}
					if(StringUtils.isNotBlank(page.tagName)){
						
						Tag t = ts.getByName(page.tagName, page.type);
						if (t != null) {
							t.clicks ++;
							t.showTimes++;
							t.favScore++;
							ts.save(t);
						}

					}
				}
			}
			else if ("tag".equals(type)) {
				 
				 Tag t = ts.byID(id);
				 if (t != null) {
					 t.clicks ++;
					 t.showTimes++;
					 t.favScore++;
					 ts.save(t);
				 }
			}
			else if ("topic".equals(type)) {
			 Topic t=	topicService.byID(id);
				 if (t != null) {
					 t.clicks ++;
					 t.showTimes++;
					 t.favScore++;
					 topicService.save(t);
				 }
				 if(StringUtils.isNotBlank(t.parentTopicID)){
					 Topic pt =	topicService.byID(t.parentTopicID);
					 if (pt != null) {
						 pt.clicks ++;
						 pt.showTimes++;
						 pt.favScore++;
						 topicService.save(pt);
					 }
				 }
			}
			else if ("folder".equals(type)) {
			 
				Folder folder = fDao.getByID(id);
				if (folder != null) {
					folder.showTimes+=2;
					folder.favScore+=2;
					fDao.save(folder);
					
					
					Tag tag = tDao.getByName(folder.name);
					tag.clicks+=1;
					tag.favScore+=1;
					tDao.save(tag);
 
				} 
			}

		} catch (BaseException e) {
			//e.printStackTrace();
			// do nothing
			// 此类用户统计分析，即使出现异常，也不要影响用户的正常操作
		}

	}

}
