<%@ page language="java" pageEncoding="UTF-8"%>

<!-- 菜单 开始 -->
<div id="menu" class="container col-md-12 "
	style="padding-top: 10px; padding-bottom: 10px;">
	<div id="logo" class="col-md-3">
		<span style="font-size: 26px;">网址盒子</span> <span
			style="display: block; font-size: 16px; color: #999; padding-bottom: 4px;">网站收藏、网站推荐</span>
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
	<div id="searchSite-div" class="col-md-offset-5 col-md-3" style="padding-top:10px;  ">
		<div class="input-group">
			<input id="searchSite-input" placeholder="网站类别"
				ng-keyup="$event.keyCode == 13 ? searchSite() : null" type="text"
				class="form-control"> <span ng-click="searchSite()"
				class="input-group-addon ">找网站</span>
			<!-- glyphicon glyphicon-chevron-right -->
		</div>
	</div>

	<div class="col-md-1" style="padding-top:10px;  "> 
		<a href="#" class="btn btn-default  dropdown-toggle"
			data-toggle="dropdown"> <span>菜单</span><span class="caret"></span>
		</a>
		<ul class="dropdown-menu" role="menu">
			<li><a ng-click="preLoginFunction( )" href="#">登录</a></li>
			<li><a ng-click="preAddUserFunction( )" href="#">注册</a></li>
			<li class="divider"></li>
			<li><a ng-click="preEditAll()" href="#">编辑</a></li>
			<li class="divider"></li>
			<li><a
				href="http://localhost/tgy/folder/?fid=5448792a7890c8799d303726">水平显示书签</a></li>
			<li><a
				href="http://localhost/tgy/folder/?fid=5448792a7890c8799d303726&show=h">垂直显示书签</a></li>
			<li class="divider"></li>

			<li><a href="#">Separated link</a></li>
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

<!-- 分割线 -->
<hr class="divider col-sm-12"
	style="border-top: 4px solid #eee; margin-top: 1px;">