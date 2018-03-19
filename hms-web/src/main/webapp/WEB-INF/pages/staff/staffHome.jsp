<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<s:if test="%{#session.timetableGenerationByManual}">
			<div class="portlet box blue">
				<div class="portlet-title">
					<div class="caption">
						<i class="fa fa-globe"></i>Timetable
					</div>
				</div>
				<div class="portlet-body">
					<div class="tab-content">
						<jsp:include page="/WEB-INF/pages/admin/manualTimeTable/ajaxViewStaffTimeTableDetails.jsp" />
					</div>
				</div>
			</div>
		</s:if>
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Search Profile Info
				</div>
			</div>
			<div class="portlet-body">
				<div id="staffProfileContectDiv" class="tab-content">
					<jsp:include page="/WEB-INF/pages/staff/ajaxStaffProfileDetails.jsp"></jsp:include>
				</div>
			</div>
		</div>
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Upcoming Exam Schedules Info
				</div>
			</div>
			<div class="portlet-body">
				<div id="upcomingExamSchedulesDiv" class="tab-content">
					<jsp:include
						page="/WEB-INF/pages/exam/ajaxManageUpcomingExamSchedules.jsp" />
				</div>
			</div>
		</div>
		<%-- <div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>My Performance
				</div>
			</div>
			<div class="portlet-body">
				<div id="performanceContentDiv" class="tab-content">
					<div id="stId">
						<jsp:include
							page="/WEB-INF/pages/staff/classTeacherExamSchedules.jsp" />
					</div>
				</div>
			</div>
		</div> --%>

		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Student Attendance Summary 
				</div>
			</div>
			<div class="portlet-body">
				<div id="myStudentsAttendancediv" class="tab-content">
					<jsp:include page="/WEB-INF/pages/staff/leaveApprovalsCount.jsp" />
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function() {
	changePageTitle("Teacher Dashboard");
});
</script>