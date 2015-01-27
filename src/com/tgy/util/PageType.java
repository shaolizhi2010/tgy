package com.tgy.util;

public enum PageType {
	
	all("全部"),
	common("普通"),
	article("文章"),
	baidupan("百度盘");
	
	private String value;
    
    // 构造方法   
    private  PageType(String value ) {
        this.value = value;
    } 
    public String value(){
    	return this.value;
    }
  
    //覆盖方法   
//    @Override   
//    public  String toString() {
//        return this.name ;
//    }  
}
