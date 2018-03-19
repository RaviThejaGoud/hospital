<%@ include file="/common/taglibs.jsp"%>
<div class="grid_6 alpha omega">
	<div class="block_head">
		<h2>
			Student profile
		</h2>
	</div>
	<div class="block_content"> 
		<div id="profileAttachment">
			<jsp:include page="/WEB-INF/pages/common/ajaxStafAndStudentProfileImage.jsp" />
		</div>
		<div>
			<jsp:include page="/WEB-INF/pages/student/profileSearch.jsp" />
		</div>
	</div>
</div>
