package com.tgy.web;

import java.util.Calendar;
import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.springframework.scheduling.annotation.Scheduled;

import com.tgy.App;
import com.tgy.timer.GetPageInfoTask;
import com.tgy.timer.StatisticTask;

@WebListener
public class SimpleListener implements ServletContextListener {

	Timer timer1 = new Timer();
	Timer timer2 = new Timer();

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		timer1.cancel();

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("app init");
		
		String basePath = arg0.getServletContext().getRealPath("/");
		App.basePath = basePath;

		GetPageInfoTask getPageInfoTask = new GetPageInfoTask();
		StatisticTask statisticTask = new StatisticTask();
 	
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR, 23);// 
		calendar.set(Calendar.MINUTE,49);//  
		
//		timer1.schedule(getPageInfoTask, calendar.getTime(),
//				24 * 60 * 60 * 1000); // 24小时执行一次
//		timer1.schedule(getPageInfoTask, 0);// 立刻开始
		
		
		Calendar calendar2 = Calendar.getInstance();
		calendar2.set(Calendar.HOUR, 23);// 
		calendar2.set(Calendar.MINUTE,59);//  
		
		timer2.schedule(statisticTask, calendar2.getTime(),
				24 * 60 * 60 * 1000); // 24小时执行一次
		//timer2.schedule(statisticTask, 0);// 立刻开始
	

	}

}