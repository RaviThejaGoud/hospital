<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
<div class="block grid_12">
	<div class="block_head" id="topMenu">
		<h2>
			Attendance Details
		</h2>
			<ul>
				<li>
					<s:url id="urlAttendanceLink" action="ajaxDoGetAttendanceForm"
						includeParams="all" namespace="/admin"/>
					<sj:a href="%{urlAttendanceLink}"
						targets="studentAttendanceResults" indicator="indicator">Create Attendance</sj:a>
				</li>
			</ul>
	</div>
	<div class="block_content" id="studentAttendanceResults">
		<jsp:include page="/WEB-INF/pages/common/attendance/ajaxViewStudentAttendance.jsp"></jsp:include>
	</div>
</div>
<script type="text/javascript">
changePageTitle("Manage Attendance");
$.subscribe('ajaxStudentAttendance', function(event, data) {
	if ($('#' + data.id).is(":hidden")) {
		$('#' + data.id).show();
	} else {
		$('#' + data.id).hide();
	}
});
</script>