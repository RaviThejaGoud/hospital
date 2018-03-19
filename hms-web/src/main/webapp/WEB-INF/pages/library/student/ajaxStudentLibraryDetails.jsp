<%@ include file="/common/taglibs.jsp"%>
<div id="studentLibraryContent">
	<h4 class="pageTitle bold">
		Staff Search Books
	</h4>
	<div>
		<jsp:include
			page="/WEB-INF/pages/library/student/ajaxStudentSearchWords.jsp" />
	</div>
	<div id="resultsDiv1" >
		<div id="schoolBooksList">
			<div>
				<!--<jsp:include page="/common/messages.jsp"></jsp:include>-->
			</div>
			<div>
				<jsp:include
					page="/WEB-INF/pages/library/student/ajaxStudentGetBooks.jsp"></jsp:include>
			</div>
			<div class="spaceDiv"></div>
			<div>
				<jsp:include
					page="/WEB-INF/pages/library/student/ajaxStudentRequestBooks.jsp"></jsp:include>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
<script type="text/javascript">
$(document) .ready( function() {
	TableAdvanced.init();
	changePageTitle("Library Management");
});
</script>

