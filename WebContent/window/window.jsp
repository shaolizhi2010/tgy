<%@ page language="java" pageEncoding="UTF-8"%>

<!-- create root folder model -->
<div class="modal " id="createRootFolderModel" tabindex="-1"
	role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<jsp:include page="createRootFolder.jsp" />
</div>

<!-- upload root folder model -->
<div class="modal " id="uploadBookmarkModel" tabindex="-1"
	role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<jsp:include page="uploadBookmark.jsp" />
</div>

<!-- create folder model -->
<div class="modal " id="createFolderModel" tabindex="-1"
	role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<jsp:include page="createFolder.jsp" />

</div>
<!-- edit folder model -->
<div class="modal " id="editFolderModel" tabindex="-1"
	role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<jsp:include page="editFolder.jsp" />

</div>

<!-- create page model -->
<div class="modal " id="createPageModel" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<jsp:include page="createPage.jsp" />

</div>
<!-- edit page model -->
<div class="modal " id="editPageModel" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<jsp:include page="editPage.jsp" />

</div>

<!-- create user Modal -->
<div class="modal " id="createUserModel" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">

	<jsp:include page="createUser.jsp" />
</div>

<!-- edit user Modal -->
<div class="modal " id="editUserModel" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">

	<jsp:include page="editUser.jsp" />
</div>
<!-- edit public message Modal -->
<div class="modal " id="editPublicMessageModel" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">

	<jsp:include page="editPublicMessage.jsp" />
</div>
<!-- temp To Normal UserModel -->
<div class="modal " id="editTempUserModel" tabindex="-1"
	role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<jsp:include page="editTempUserModel.jsp" />

</div>

<!-- login Modal -->
<div class="modal " id="loginModel" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">

	<jsp:include page="login.jsp" />
</div>

<!-- first try -->
<div class="modal " id="firstTryModel" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">

	<jsp:include page="firstTry.jsp" />
</div>

<!-- create comment -->
<div class="modal " id="createCommentForLink" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">

	<jsp:include page="createCommentForLink.jsp" />
</div>