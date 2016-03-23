package com.tgy.entity;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import com.tgy.util.U;

@Entity
public class SimpleBaseEntity {
	@Id
	public ObjectId id;

	// 日期跟踪
	public String createDate = U.dateTime();// 添加日期

	// system
	public String vesion_ = "0.3";// 数据对应的系统版本，每个版本所产生的数据格式可能不同，加此标记，便于处理数据。

}
