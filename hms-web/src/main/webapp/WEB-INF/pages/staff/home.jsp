<%@ include file="/common/taglibs.jsp"%>
<div class="grid_9">
	<div class="grid_4" style="width:235px" id="stId">
		<jsp:include page="/WEB-INF/pages/staff/staffPerformance.jsp" />
	</div>
	<div class="grid_4" style="width:235px" id="stId">
		<jsp:include page="/WEB-INF/pages/staff/classTeacherExamSchedules.jsp" />
	</div><br/>
	<div class="grid_4" style="width:235px" id="stId">
		<jsp:include page="/WEB-INF/pages/staff/myInbox.jsp" />
	</div>
	<div class="grid_4" style="width:235px" id="stId">
		<jsp:include page="/WEB-INF/pages/staff/leaveApprovalsCount.jsp" />
	</div>
</div>
