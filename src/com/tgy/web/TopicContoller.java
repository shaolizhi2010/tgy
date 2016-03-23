package com.tgy.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.tgy.entity.Page;
import com.tgy.entity.Reply;
import com.tgy.entity.Topic;
import com.tgy.entity.TopicPic;
import com.tgy.entity.TopicSumary;
import com.tgy.entity.User;
import com.tgy.service.PageService;
import com.tgy.service.TopicService;
import com.tgy.service.UserService;
import com.tgy.service.cache.AppCache;
import com.tgy.util.C;
import com.tgy.util.ConditionMap;
import com.tgy.util.FuliDou;
import com.tgy.util.PageType;
import com.tgy.util.U;

@RestController
@RequestMapping(value = { "/topic" })
public class TopicContoller extends HttpServlet {
	
 
		@RequestMapping(value = { "","/" })
		public void index(HttpServletRequest req, HttpServletResponse res) {
			 
			U.forward(req, res, "/index-topic.jsp");
		}

	// get by id
	@RequestMapping(value = { "/id/{topicID}" })
	public void id(HttpServletRequest req, HttpServletResponse res,
			@PathVariable("topicID") String topicID) {

		Topic topic = new TopicService().byID(topicID);
		if (topic == null) {
			U.forward(req, res, "/topic/topic.jsp");
			return;
		}

		List<Page> links = new PageService().searchSharePage(topic.title, null,
				PageType.resource, "-createDate", 0, 10);

		List<Reply> replies = AppCache.repliesByToID(topicID);

		req.setAttribute("topic", topic);
		req.setAttribute("links", links);
		req.setAttribute("replies", replies);

		U.forward(req, res, "/topic/topic.jsp");
	}

	// get by title
	@RequestMapping(value = { "/title/{title}" })
	public void title(HttpServletRequest req, HttpServletResponse res,
			@PathVariable("title") String title) {

		Topic topic = new TopicService().byTitle(title);

		List<Page> links = new PageService().searchSharePage(title, null,
				PageType.resource, "-createDate", 0, 10);

		List<Reply> replies = new ArrayList<Reply>();
		if (topic != null) {
			replies = AppCache.repliesByToID(topic.id.toString());
		}

		req.setAttribute("title", title);
		req.setAttribute("topic", topic);
		req.setAttribute("pages", links);
		req.setAttribute("replies", replies);

		U.forward(req, res, "/topic/topic.jsp");
	}

	// create or update
	@RequestMapping(value = { "/update" })
	public void update(HttpServletRequest req, HttpServletResponse res) {
		String title = req.getParameter("title");
		String sumary = req.getParameter("sumary");
		String picUrl = req.getParameter("picUrl");
		
		String parentTopicID = req.getParameter("parentTopicID");

		if(StringUtils.isBlank(title)){
			U.resFailed(res, "需专题名称");
			return;
		}
		
		User user = U.param(req, C.user, User.class);
		if (user == null) {
			// need logon
			U.resFailed(res, "需要登陆");
			return;
		}
//		if(user.totalOnlineTime< 60){//累计在线时长n分钟以上用户
//			U.resFailed(res, "新用户暂不能操作哦亲~ 在站内多逛会吧");
//			return;
//		}

		String userID = U.getUserID(req);

		TopicService ts = new TopicService();

		Topic topic = ts.byTitle(title);

		if (topic == null) { // no exist then create new one
			topic = new Topic();
			topic.title = title;
			topic.createrUserID = userID;
			topic.createrUserName = user.name;
		}

		if (StringUtils.isNotBlank(sumary)) { // add sumary
			TopicSumary sumaryObj = new TopicSumary();
			sumaryObj.createrUserID = userID;
			sumaryObj.createrUserName = user.name;
			sumaryObj.sumary = sumary;
			topic.sumaries.add(sumaryObj);
		}
		if (StringUtils.isNotBlank(picUrl)) { //add pic
			TopicPic picObj = new TopicPic();
			picObj.createrUserID = userID;
			picObj.createrUserName = user.name;
			picObj.picUrl = picUrl;
			topic.pics.add(picObj);
		}
		if (StringUtils.isNotBlank(parentTopicID)) { //add pic
			topic.parentTopicID = parentTopicID;
		}
		
		ts.save(topic);
		
		U.resSuccess(res);
		
		//送n 福利豆
		if(user!=null){
			user.fulidou += FuliDou.createTopicInfoScore;
			new UserService().save(user);
		}
	}


	@RequestMapping(value = { "/topLevels" })
	public void topLevels(HttpServletRequest req, HttpServletResponse res) {
		TopicService ts = new TopicService();
		List<Topic> topics = ts.list(Topic.class, new ConditionMap().add("isTopLevel", true), "favScore", 5);
		U.message(res, new Gson().toJson(topics));
	}
	
	@RequestMapping(value = { "/list" })
	public void list(HttpServletRequest req, HttpServletResponse res) {
		
		int start = 0;
		if( StringUtils.isNotBlank( req.getParameter("start") ) ){
			start = Integer.parseInt( req.getParameter("start") );
		}
		
		TopicService ts = new TopicService();
		List<Topic> topics = ts.list(Topic.class, new ConditionMap().add("isTopLevel", false), "-favScore",start, 10);
		
		U.message(res, new Gson().toJson(topics));
		
	}
	
	

}
