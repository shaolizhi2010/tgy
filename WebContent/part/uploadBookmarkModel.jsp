<%@ page language="java" pageEncoding="UTF-8"%>



<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal">
				<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
			</button>
			<h4 class="modal-title" id="myModalLabel">上传书签</h4>
		</div>
		<div class="modal-body">

			<!-- upload bookmark begin-->

			<form action="<%=request.getContextPath()%>/bookmark/upload/"
				method="post" enctype="multipart/form-data">

				<span class="btn btn-success fileinput-button"> <i
					class="glyphicon glyphicon-plus"></i> <span>添加文件</span> <input
					type="file" name="bookmarkFile" value="">
				</span>


				<!-- 
				<div>
					<label for="uploadBookmark_bookmarkName">书签名</label><input
						class="form-control"
						ng-keyup="$event.keyCode == 13 ? uploadBookmarkFunction() : null"
						id="uploadBookmark_bookmarkName" type="text" />
				</div>
 -->

				<div style="margin-top: 20px;">
					<button type="submit" class="btn btn-primary">确定</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">取消
					</button>
				</div>

				<!--  ng-click="uploadBookmarkFunction()" -->
				<!-- upload bookmark end-->
			</form>
		</div>

	</div>
</div>