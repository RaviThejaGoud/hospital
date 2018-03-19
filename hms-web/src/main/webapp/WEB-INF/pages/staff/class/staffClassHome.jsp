<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp"></jsp:include>
<div id="staffContect">
	<jsp:include page="/WEB-INF/pages/exam/manageStaffExamSchedules.jsp"></jsp:include>
</div>
<script type="text/javascript">
	changePageTitle("Manage Staff Activities");
	$('#classActivities').addClass('current');
	$("#examSchedulesNav").addClass('active');
</script>