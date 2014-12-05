<%@ page language="java" pageEncoding="UTF-8"%>
 
	<div class="col-sm-12"
		style="padding: 3px; margin-bottom: 10px; border-bottom: 1px solid #eee;">
		<a class="btn col-sm-2   " title="每个分类里存放多个网址"
			ng-click="preCreateFolderFunction()"
			href="#"
			style="background-color: #eee; border: 1px solid #ddd; margin-top: 2px; margin-bottom: 2px;">
			<span style="color: #ccc;font-size: 12px;">  +分类 
		</span>
		</a>
		<div class="col-sm-10"> 
			<a title="添加网址" class="btn col-sm-3   " href="#"
				ng-click="preAddPageFunction('','','') "
				style="border: 1px solid #ddd; margin-top: 5px; margin-bottom: 5px; display: block; color: #222;">
				<span style="color: #ddd; font-size: 12px;  "> +网址 </span> 
			</a>
			<a title="添加网址" class="btn col-sm-3   " href="#"
				ng-click="preAddPageFunction('','','') "
				style="border: 1px solid #ddd; margin-top: 5px; margin-bottom: 5px; display: block; color: #222;">
				<span style="color: #ddd;font-size: 12px;  "> +网址</span>
			</a>
			<a title="添加网址" class="btn col-sm-3   " href="#"
				ng-click="preAddPageFunction('','','') " 
				style="border: 1px solid #ddd; margin-top: 5px; margin-bottom: 5px; display: block; color: #222;">
				<span style="color: #ddd; font-size: 12px;  "> +网址</span>
			</a>
			<a title="添加网址" class="btn col-sm-3   " href="#"
				ng-click="preAddPageFunction('','','') "
				style="border: 1px solid #ddd; margin-top: 5px; margin-bottom: 5px; display: block; color: #222;">
				<span style="color: #ddd; font-size: 12px;  "> +网址</span> 
			</a>
		</div>
	</div>
