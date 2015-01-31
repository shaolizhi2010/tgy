package com.tgy.util;

/**
 * 内容的类型 文字？ 图片？ 视频？
 * @author qq
 *
 */
public enum PageType {
	
	//all("全部"),
	common("普通"),
	word("热文"),
	resource("资源"),//下载地址之类
	link("链接"),
	pic("图片"),
	video("视频"),
	website("网站"),
	//article("文章"),
	//baidupan("百度盘"),
	
	;
	
	
	
	
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
