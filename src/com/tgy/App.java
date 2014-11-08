package com.tgy;

import java.net.UnknownHostException;

import org.apache.http.client.HttpClient;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.MongoClient;

//singleton share by whole server
public class App {
	
	MongoClient client;
	Datastore ds;
	
	private App() {
	}

	private static App app = new App();

	public static App getInstance() {
		return app;
	}

	public Datastore getDatastore() {

		try {
			if(client == null){
				client = new MongoClient("localhost");
			}
			if(ds==null){
				ds= new Morphia().createDatastore(client, "tgy");
			}
			 
			return ds;
		} catch (UnknownHostException e) {
			System.out.println("找不到Mongo数据库");
			return null;
		}
	}

}
