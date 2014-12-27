<%@ page language="java" pageEncoding="UTF-8"%>

<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal">
				<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
			</button>
			<h4 class="modal-title" id="myModalLabel">创建书签</h4>
		</div>
		<div class="modal-body">

			<!-- create bookmark begin-->


			<div>
				<label for="createBookmark_bookmarkName">书签名</label><input
					class="form-control"
					ng-keyup="$event.keyCode == 13 ? createBookmarkFunction() : null"
					id="createBookmark_bookmarkName" type="text" />
			</div>


			<div style="margin-top: 20px;">
				<button onclick="createBookmarkFunction()" type="button"
					class="btn btn-primary">确定 </button>
				<button type="button" class="btn btn-default" data-dismiss="modal">取消 </button>
			</div>

			<!-- create bookmark end-->

		</div>

	</div>
</div>