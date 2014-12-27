package com.tgy.entity;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity
public class BaseEntity {
	@Id
	public ObjectId id;
	
	//统计
	public int clicks; //被点击次数
	public long keeps; //收藏次数
	public int showTimes; //展现次数
	public long ups; //推荐次数 被顶次数
	public long downs;//被踩次数
	public long favScore; //松鹤被喜爱程度评分
	public long score;//系统评分 得分

	
	//日期跟踪
	public String createDate;//添加日期
	public String lastModifyDate;//最后修改日期
	public String deleteDate;//删除日期
	
	public boolean deleteFlag;//删除标志，有些不执行真的删除
	public boolean useAble;//是否可用
	
	//权限控制
	//0 只有自己  
	//3 任意登录用户     
	//9 所有人,包括未登录
	public int authCreate;
	public int authDelete;
	public int authUpdate;
	public int authQuery;

}
