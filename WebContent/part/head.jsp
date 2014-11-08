<%@ page language="java" pageEncoding="UTF-8"%>

<!-- 菜单 开始 -->
<div id="menu" class="container col-md-12 "
	style="padding: 3px; background-color: #f5f5f5; " > 
	<div id="logo" class="col-md-4">
		<span style="font-size: 26px;">网址盒子</span> 
		<span style=" font-size: 14px; color: #666;">网站收藏、网站推荐</span>
	</div>

	<!-- 
	<div class="col-md-2">
		<div class="input-group">
			<input   id="search-input-baidu" placeholder="百度一下"
				ng-keyup="$event.keyCode == 13 ? searchBaidu() : null" type="text"
				class="form-control">  <span  ng-click="searchBaidu( )" class="input-group-addon glyphicon glyphicon-search" >  </span>
		</div>
	</div>
	<div class="col-md-2">
		<div class="input-group">
			<input id="search-input-google" placeholder="Google一下"
				ng-keyup="$event.keyCode == 13 ? searchGoogle() : null" type="text"
				class="form-control"> <span ng-click="searchGoogle( )"
				class="input-group-addon glyphicon glyphicon-search"></span>
		</div>
	</div>
 -->
	<div id="searchSite-div" class="col-md-offset-4 col-md-3" style="padding:3px;   ">
		<div class="input-group">
			<input id="searchSite-input" placeholder="网站类别"
				ng-keyup="$event.keyCode == 13 ? searchSite() : null" type="text"
				class="form-control"> <span ng-click="searchSite()"
				class="input-group-addon ">找网站</span>
			<!-- glyphicon glyphicon-chevron-right -->
		</div>
	</div>

	<div class="col-md-1" style="padding:3px;  ">
		<a href="#" class="btn btn-default  dropdown-toggle"
			data-toggle="dropdown">
			<span>菜单</span><span class="caret"></span>
		</a>
		<ul class="dropdown-menu" role="menu">
			<li><a ng-click="preLoginFunction()" href="#">登录</a></li>
			<li><a ng-click="preAddUserFunction()" href="#">注册</a></li>
			<li class="divider"></li>
		</ul>
	</div>
	<!-- 
	<div class="col-md-2">

		<button ng-click="preLoginFunction( )"
			class="btn btn-default col-md-4">登录</button>
		<button ng-click="preAddUserFunction( )"
			class="btn btn-default col-md-4">注册</button>
	</div>
	
	 -->
</div>

<!-- 菜单 结束 -->

<!-- 分割线  -->
<hr class="col-sm-12"
	style="border-top: 2px solid #eee; padding: 0px;margin: 0px; ">