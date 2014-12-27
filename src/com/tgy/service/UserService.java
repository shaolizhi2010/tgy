package com.tgy.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.DatastoreImpl;
import org.mongodb.morphia.Key;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.QueryResults;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;

import com.mongodb.DBCollection;
import com.mongodb.WriteConcern;
import com.mongodb.WriteResult;
import com.tgy.App;
import com.tgy.dao.UserDao;
import com.tgy.entity.User;
import com.tgy.exception.BaseException;
import com.tgy.util.U;
 
public class UserService {
	
	public User byOpenID(String openID) {
		return uDao.byOpenID(openID);
	}

	public User login(String name, String password) {
		User user = new User();
		user.name = name;
		user.password = password;
		return login(user);
	}
	
	public User login(User user) {
		return uDao.login(user);
	}

	public User checkUserByPsCode(String userID, String psCode) {
		return uDao.checkUserByPsCode(userID, psCode);
	}

	public User getByID(String userID) { 
		return uDao.getByID(userID);
	}

	public int hashCode() {
		return uDao.hashCode();
	}

	public User getByName(String userName) {
		return uDao.getByName(userName);
	}

	public DatastoreImpl getDs() {
		return uDao.getDs();
	}

	public Class<User> getEntityClazz() {
		return uDao.getEntityClazz();
	}

	public boolean equals(Object obj) {
		return uDao.equals(obj);
	}

	public DBCollection getCollection() {
		return uDao.getCollection();
	}

	public Query<User> createQuery() {
		return uDao.createQuery();
	}

	public UpdateOperations<User> createUpdateOperations() {
		return uDao.createUpdateOperations();
	}

	public Class<User> getEntityClass() {
		return uDao.getEntityClass();
	}

	public Key<User> save(User entity) {
		return uDao.save(entity);
	}

	public Key<User> save(User entity, WriteConcern wc) {
		return uDao.save(entity, wc);
	}

	public UpdateResults updateFirst(Query<User> q, UpdateOperations<User> ops) {
		return uDao.updateFirst(q, ops);
	}

	public UpdateResults update(Query<User> q, UpdateOperations<User> ops) {
		return uDao.update(q, ops);
	}

	public WriteResult delete(User entity) {
		return uDao.delete(entity);
	}

	public WriteResult delete(User entity, WriteConcern wc) {
		return uDao.delete(entity, wc);
	}

	public WriteResult deleteById(ObjectId id) {
		return uDao.deleteById(id);
	}

	public WriteResult deleteByQuery(Query<User> q) {
		return uDao.deleteByQuery(q);
	}

	public User get(ObjectId id) {
		return uDao.get(id);
	}

	public List<ObjectId> findIds() {
		return uDao.findIds();
	}

	public List<ObjectId> findIds(Query<User> q) {
		return uDao.findIds(q);
	}

	public List<ObjectId> findIds(String key, Object value) {
		return uDao.findIds(key, value);
	}

	public Key<User> findOneId() {
		return uDao.findOneId();
	}

	public Key<User> findOneId(String key, Object value) {
		return uDao.findOneId(key, value);
	}

	public Key<User> findOneId(Query<User> query) {
		return uDao.findOneId(query);
	}

	public boolean exists(String key, Object value) {
		return uDao.exists(key, value);
	}

	public boolean exists(Query<User> q) {
		return uDao.exists(q);
	}

	public long count() {
		return uDao.count();
	}

	public long count(String key, Object value) {
		return uDao.count(key, value);
	}

	public long count(Query<User> q) {
		return uDao.count(q);
	}

	public User findOne(String key, Object value) {
		return uDao.findOne(key, value);
	}

	public User findOne(Query<User> q) {
		return uDao.findOne(q);
	}

	public QueryResults<User> find() {
		return uDao.find();
	}

	public QueryResults<User> find(Query<User> q) {
		return uDao.find(q);
	}

	public Datastore getDatastore() {
		return uDao.getDatastore();
	}

	public void ensureIndexes() {
		uDao.ensureIndexes();
	}

	public String toString() {
		return uDao.toString();
	}

	UserDao uDao = new UserDao();
	
	//拿到一个openid 系统已有，说明是老用户，返回user 如没有，说明新用户，创建新user并返回
	public User dealWithOpenID(String openID,String userName,String headImgUrl) throws BaseException{
		if(StringUtils.isBlank(openID )  || openID.equalsIgnoreCase("null")){
			throw new BaseException(this,"需要open ID");
		}
		User user = uDao.byOpenID(openID);
		if(user == null){//new user
			user = new User();
			user.createDate = U.dateTime();
			user.openID = openID;
			user.headImgUrl = headImgUrl;
			
			int times = 1;
			while (times<6) {
				User oldUser = uDao.getByName(userName);
				if (oldUser == null) {
					break;
				}
				//重名 用户名后加随机数
				userName =  userName + U.randomNum( 1 ) ;//第一次循环 abc, 第二次 abc[0]，第三次 abc[0][0]
				times++;
			}
			user.name = userName;
		}
		//old user
		else if(StringUtils.isNotBlank(headImgUrl) && StringUtils.isBlank( user.headImgUrl) ){
			user.headImgUrl = headImgUrl;
		}
		if(user!=null){
			user.loginTimes++;
			user.lastLoginDate = U.dateTime();
			uDao.save(user);
			
		}
		return user;
	}
	
	public User checkAndGetUser(String userID) throws BaseException{
		// check user exsit
		if (StringUtils.isBlank(userID)) {
			throw new BaseException(this, "操作失败,需要用户");
		}
		UserDao uDao = new UserDao();
		User user = uDao.getByID(userID);
		if (user == null) {
			throw new BaseException(this, "操作失败,需要用户");
		}
		return user;
	}
	
	public List<User> list(){
		return list(null,0);
	}
	
	public List<User> list( String sortStr,int limit){
		
		Query<User> query = App.getInstance().getDatastore().createQuery(User.class);
		
 
		if(StringUtils.isNotBlank(sortStr)){
			query.order(sortStr);
		}else{
			query.order("favScore");
		}
		if(limit!=0){
			query.limit(limit);
		}
		
		return uDao.find(query).asList();
	}
	
}
