<%@ page language="java" pageEncoding="UTF-8"%>

<!-- create root folder model -->
<div class="modal fade" id="createRootFolderModel" tabindex="-1"
	role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<jsp:include page="createRootFolder.jsp" />
</div>

<!-- upload root folder model -->
<div class="modal fade" id="uploadBookmarkModel" tabindex="-1"
	role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<jsp:include page="uploadBookmark.jsp" />
</div>

<!-- create folder model -->
<div class="modal fade" id="createFolderModel" tabindex="-1"
	role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<jsp:include page="createFolder.jsp" />

</div>
<!-- edit folder model -->
<div class="modal fade" id="editFolderModel" tabindex="-1"
	role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<jsp:include page="editFolder.jsp" />

</div>


<!-- create page model -->
<div class="modal fade" id="createPageModel" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<jsp:include page="createPage.jsp" />

</div>
<!-- edit page model -->
<div class="modal fade" id="editPageModel" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<jsp:include page="editPage.jsp" />

</div>

<!-- create user Modal -->
<div class="modal fade" id="createUserModel" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">

	<jsp:include page="createUser.jsp" />
</div>


<!-- login Modal -->
<div class="modal fade" id="loginModel" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">

	<jsp:include page="login.jsp" />
</div>
