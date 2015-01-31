package com.tgy.web;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.tgy.App;
import com.tgy.service.article.HaoSouWemediaService;
import com.tgy.service.article.HuxiuDigService;
import com.tgy.service.ziyuan.BaiduPanDigService;
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
//		StatisticTask statisticTask = new StatisticTask();
//		
//		Calendar calendar2 = Calendar.getInstance();
//		calendar2.set(Calendar.HOUR, 23);// 
//		calendar2.set(Calendar.MINUTE,54);//  
//		
//		timer2.schedule(statisticTask, calendar2.getTime(),
//				24 * 60 * 60 * 1000); // 24小时执行一次
		//timer2.schedule(statisticTask, 0);// 立刻开始
	
		// 抓360
		Calendar calendarArticle = Calendar.getInstance();
		calendarArticle.set(Calendar.HOUR, 6);// 
		calendarArticle.set(Calendar.MINUTE,5);//
		new Timer(false).schedule(new TimerTask() {
			@Override
			public void run() {
				int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
				if(1<hour && hour<5){//凌晨1-5点休息
					return;
				}
				new HaoSouWemediaService().digAndSave("自媒体"); //自动抓取文章
			}
		}, calendarArticle.getTime(), 5 * 60 * 1000); //10分钟一次 
		
		 
		new Timer(false).schedule(new TimerTask() {
			@Override
			public void run() {
				int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
				if(1<hour && hour<5){//凌晨1-5点休息
					return;
				}
				new HuxiuDigService().digAndSave("互联网"); //自动抓取文章
			}
		}, 5*60, 8*60 * 60 * 1000); // 
		
		// n小时左右执行一次
		new Timer(false).schedule(new TimerTask() {
			@Override
			public void run() {
				int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
				if(1<hour && hour<8){//凌晨1-8点休息
					return;
				}
				new BaiduPanDigService().digAndSave("小说下载-小说","百度网盘", "云盘资源共享-百度网盘", "资源-百度网盘","高清下载", "动漫", "英剧", "美剧资源站-美剧",   "美剧-美剧",   "电影-电影");//自动抓取百度云资源
			}
		}, 11*60, 2*60 * 60 * 1000); 
		
		//自动抓取百度云资源
//		DigBaiduYunTask baiduYunTask = new DigBaiduYunTask();
//		
//		Calendar baiduYunTaskCalendar = Calendar.getInstance();
//		baiduYunTaskCalendar.set(Calendar.HOUR, 18);// 
//		baiduYunTaskCalendar.set(Calendar.MINUTE,10);//
//		
//		new Timer(false).schedule(baiduYunTask, baiduYunTaskCalendar.getTime(),
//				1 * 60 * 60 * 1000); // 1小时左右执行一次
		
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