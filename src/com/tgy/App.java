package com.tgy;

import java.net.UnknownHostException;
import java.util.Arrays;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.tgy.util.P;

//singleton share by whole server
public class App {
	
	MongoClient client;
	Datastore ds;
	
	private static final String mogoUserName = P.getProperty("mogoUserName");
	private static final String mogoPassword = P.getProperty("mogoPassword");
	
	private App() {
	}

	private static App app = new App();

	public static App getInstance() {
		return app;
	}

	public Datastore getDatastore() {

		try {
			if(client == null){
				MongoCredential credential = MongoCredential.createMongoCRCredential(mogoUserName, "admin", mogoPassword.toCharArray());
				ServerAddress address = new ServerAddress("127.0.0.1");
				client = new MongoClient(address,Arrays.asList(credential));
			}
			if(ds==null){
//				new Morphia().createDatastore(mongoClient, dbName)
				ds= new Morphia().createDatastore(client, "tgy" );
				
			}
			 
			return ds;
		} catch (UnknownHostException e) {
			System.out.println("找不到Mongo数据库");
			return null;
		}
	}

}
