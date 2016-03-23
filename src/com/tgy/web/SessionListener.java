package com.tgy.web;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.commons.lang3.StringUtils;

import com.tgy.entity.Online;
import com.tgy.entity.User;
import com.tgy.service.OnlineService;
import com.tgy.service.UserService;
import com.tgy.util.C;
import com.tgy.util.ConditionMap;

@WebListener
public class SessionListener implements HttpSessionListener {
	private UserService us = new UserService();
	OnlineService os = new OnlineService();

	public void sessionCreated(HttpSessionEvent event) {
	}

	public void sessionDestroyed(HttpSessionEvent event) {
		if (event.getSession() != null
				&& event.getSession().getAttribute(C.userID) != null) {
			String userID = (String) event.getSession().getAttribute(C.userID);

			Online online = os.get(Online.class,
					new ConditionMap().add("userID", userID));
			if (online != null) {
				User user = us.getByID(userID);
				if (user != null) {//总共在线时长累加 单位分钟
					long onlineTime = (System.currentTimeMillis()-online.visitTimestamp) / (1000*60) -30; //30分钟过期
					if(onlineTime<0) onlineTime =0;
					user.totalOnlineTime+=  onlineTime ;
					us.save(user);
				}
				os.delete(online);
			}
		}

	}
}