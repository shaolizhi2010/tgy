package com.tgy.web;

import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.tgy.App;
import com.tgy.entity.Online;
import com.tgy.service.OnlineService;
import com.tgy.service.sitemap.SitemapGenerator;
import com.tgy.service.ziyuan.BaiduPanDigService;

@WebListener
public class SimpleListener implements ServletContextListener {

	Timer timer1 = new Timer();
	Timer timer2 = new Timer();
	Timer timer3 = new Timer();
	Timer timer4 = new Timer();
	Timer timer5 = new Timer();
	Timer timer6 = new Timer();
	Timer timer7 = new Timer();
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		timer1.cancel();
		timer2.cancel();
		timer3.cancel();
		timer4.cancel();
		timer5.cancel();
		timer6.cancel();
		timer7.cancel();

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("app init");
		
		String basePath = arg0.getServletContext().getRealPath("/");
		App.basePath = basePath;
		
		OnlineService os = new OnlineService(); //clear 在线用户记录
		List<Online> list =  os.list(Online.class, null, null, 0);
		for(Online ol : list){
			os.delete(ol);
		}
		
		// 取page info
		/*
		GetPageInfoTask getPageInfoTask = new GetPageInfoTask();
 	
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR, 23);// 
		calendar.set(Calendar.MINUTE,49);//  
		
		timer1.schedule(getPageInfoTask, calendar.getTime(),
				24 * 60 * 60 * 1000); // 24小时执行一次
				*/
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
	
//		// 抓360 首页
//		Calendar calendarArticle = Calendar.getInstance();
//		calendarArticle.set(Calendar.HOUR, 6);// 
//		calendarArticle.set(Calendar.MINUTE,5);//
//		timer3.schedule(new TimerTask() {
//			@Override
//			public void run() {
//				int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
//				if(1<hour && hour<5){//凌晨1-5点休息
//					return;
//				}
//				new HaoSouWemediaService().digAndSave("自媒体","http://wemedia.haosou.com/"); //自动抓取文章
//			}
//		}, calendarArticle.getTime(), 60 * 60 * 1000); // 
	
//		// 抓360
//		Calendar calendarArticle2 = Calendar.getInstance();
//		calendarArticle2.set(Calendar.HOUR, 7);// 
//		calendarArticle2.set(Calendar.MINUTE,5);//
//		timer4.schedule(new TimerTask() {
//			@Override
//			public void run() {
//				int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
//				if( (16<hour && hour<24)){//高峰时段休息休息
//				//if( (1<hour && hour<8) ){//半夜不用抓了，没人发帖
//					return;
//				}
//				HaoSouWemediaService ms = new HaoSouWemediaService();
//				
//				ms.digAndSaveAllFiled("教育","母婴","健康","摄影","财经","科技","命理","旅游"
//						,"法律","美食","科普","幽默","游戏","文化","动漫","情感","两性","体育"
//						,"时评","娱乐");
//			}
//		}, calendarArticle2.getTime()); // 
		 
		
//		//抓虎嗅
//		timer5.schedule(new TimerTask() {
//			@Override
//			public void run() {
//				int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
//				if(1<hour && hour<5){//凌晨1-5点休息
//					return;
//				}
//				new HuxiuDigService().digAndSave("互联网"); //自动抓取文章
//			}
//		}, 5*60*1000, 8*60 * 60 * 1000); // 
		
		// n小时左右执行一次
		timer6.schedule(new TimerTask() {
			@Override
			public void run() {
				int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
				//if( (16<hour && hour<18)||(20<hour && hour<24)){//高峰时段休息休息
				//if( (1<hour && hour<7) ){//半夜抓
					new BaiduPanDigService().digAndSave(  "资源-百度网盘","动漫网盘-动漫", "美剧资源站-美剧","动漫资源-动漫",   "美剧-美剧", "电影-电影");//自动抓取百度云资源
				//}
				//"小说下载-小说", "动漫","云盘资源共享-百度网盘","高清下载", 动漫资源 "腐女动漫-动漫","英剧","行尸走肉","后宫动漫-动漫","权利的游戏","斯巴达克斯","日剧","生活大爆炸",
			}
		}, 1 *1000, 4*60 * 60 * 1000);
		
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
		
		
		// 自动生成sitemap
		Calendar calendarArticle7 = Calendar.getInstance();
		calendarArticle7.set(Calendar.HOUR, 2);// 
		calendarArticle7.set(Calendar.MINUTE,5);//
		timer7.schedule(new TimerTask() {
			@Override
			public void run() {
				SitemapGenerator sg = new SitemapGenerator();
				sg.gen();
			}
		}, calendarArticle7.getTime()); // 
		
		//timer2.schedule(statisticTask, 0);// 立刻开始
	}

}