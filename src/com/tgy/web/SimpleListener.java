package com.tgy.web;

import java.util.Calendar;
import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.springframework.scheduling.annotation.Scheduled;

import com.tgy.App;
import com.tgy.timer.CreateRobotUserTask;
import com.tgy.timer.DigArticleTask;
import com.tgy.timer.DigBaiduYunTask;
import com.tgy.timer.GetPageInfoTask;
import com.tgy.timer.StatisticTask;

@WebListener
public class SimpleListener implements ServletContextListener {

	Timer timer1 = new Timer();
	Timer timer2 = new Timer();
	Timer timer3 = new Timer();
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		timer1.cancel();

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("app init");
		
		String basePath = arg0.getServletContext().getRealPath("/");
		App.basePath = basePath;
		
		// 取page info
		GetPageInfoTask getPageInfoTask = new GetPageInfoTask();
 	
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR, 23);// 
		calendar.set(Calendar.MINUTE,49);//  
		
		timer1.schedule(getPageInfoTask, calendar.getTime(),
				24 * 60 * 60 * 1000); // 24小时执行一次
		//timer1.schedule(getPageInfoTask, 0);// 立刻开始
		
		
		//统计
		StatisticTask statisticTask = new StatisticTask();
		
		Calendar calendar2 = Calendar.getInstance();
		calendar2.set(Calendar.HOUR, 23);// 
		calendar2.set(Calendar.MINUTE,54);//  
		
		timer2.schedule(statisticTask, calendar2.getTime(),
				24 * 60 * 60 * 1000); // 24小时执行一次
		//timer2.schedule(statisticTask, 0);// 立刻开始
	
		//自动抓取文章
		DigArticleTask articleTask = new DigArticleTask();
		
		Calendar articleTaskCalendar = Calendar.getInstance();
		articleTaskCalendar.set(Calendar.HOUR, 10);// 
		articleTaskCalendar.set(Calendar.MINUTE,0);//
		
		new Timer().schedule(articleTask, articleTaskCalendar.getTime(),
				2 * 67 * 60 * 1000); // 2小时左右执行一次
		
		//自动抓取百度云资源
		DigBaiduYunTask baiduYunTask = new DigBaiduYunTask();
		
		Calendar baiduYunTaskCalendar = Calendar.getInstance();
		baiduYunTaskCalendar.set(Calendar.HOUR, 18);// 
		baiduYunTaskCalendar.set(Calendar.MINUTE,10);//
		
		new Timer().schedule(baiduYunTask, baiduYunTaskCalendar.getTime(),
				2 * 67 * 60 * 1000); // 2小时左右执行一次
		
//		//创建robot user 丰富网站数据
//		CreateRobotUserTask createRobotUserTask  = new CreateRobotUserTask(); 
//		Calendar calendar3 = Calendar.getInstance();
//		calendar3.set(Calendar.HOUR, 23);// 
//		calendar3.set(Calendar.MINUTE,59);//  
//		
//		timer3.schedule(createRobotUserTask, calendar3.getTime(),
//				24 * 60 * 60 * 1000); // 24小时执行一次
		
		
		
		//timer2.schedule(statisticTask, 0);// 立刻开始
	}

}