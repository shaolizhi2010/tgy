<%@page import="com.tgy.dao.FolderDao"%>
<%@page import="com.tgy.util.URLUtils"%>
<%@page import="org.apache.commons.collections.CollectionUtils"%>
<%@page import="com.tgy.web.vo.BookmarkData"%>
<%@page import="com.tgy.entity.Folder"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="com.tgy.util.U"%>
<%@page import="java.util.List"%>
<%@page import="com.tgy.entity.Page"%>
<%@ page language="java" pageEncoding="UTF-8"%>
	<div  id="foot-prompt" class="col-md-12  " style="z-index: 888; "> 
		<div class="col-md-4 col-md-offset-1 no-padding foot-prompt-element"  >
			<div id="foot-prompt-username-div" class="input-group">
			  <span class="input-group-addon " >http://webhezi.com/</span>
			  <input id="foot-prompt-username-input" type="text" class="form-control no-outline" placeholder="个性后缀(用户名)">
			</div> 
		</div>
		<div class="col-md-4 no-padding foot-prompt-element">
			<input id="foot-prompt-password-input" type="password" class="col-md-12" placeholder="密码" />
		</div>
		<div class="col-md-2 no-padding foot-prompt-element" >
			<a id="foot-prompt-ok" class="btn" ><span>马上开始体验</span></a>
		</div>
		<div><a id="foot-prompt-close"  >X</a> </div>
	</div>
