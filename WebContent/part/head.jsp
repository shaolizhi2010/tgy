<%@ page language="java" pageEncoding="UTF-8"%>


<!-- 菜单 开始 -->
<div id="menu" class="container col-md-12 "
	style="padding-top: 10px; padding-bottom: 10px;">
	<div id="logo" class="col-md-3">糖果云</div>

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

	<div id="goto-div" class="col-md-3">
		<div class="input-group">
			<input id="goto-input" placeholder="网站类别"
				ng-keyup="$event.keyCode == 13 ? goTo() : null" type="text"
				class="form-control"> <span ng-click="goTo( )"
				class="input-group-addon ">找网站</span> 
			<!-- glyphicon glyphicon-chevron-right -->
		</div>
	</div>

	<div class="col-md-2">

		<button ng-click="preLoginFunction( )"
			class="btn btn-default col-md-4">登录</button>
		<button ng-click="preAddUserFunction( )"
			class="btn btn-default col-md-4">注册</button>
	</div>
</div>

<!-- 菜单 结束 -->

<!-- 分割线 -->
<hr class="divider col-sm-12"
	style="border-top: 4px solid #eee; margin-top: 1px;">