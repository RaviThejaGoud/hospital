<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>My Attendance
				</div>
			</div>
			<div class="portlet-body">
				<div id="studentContent" class="tab-content">
					<s:if test="%{user.parent}">
						<div id="studentsListContent">
							<jsp:include
								page="/WEB-INF/pages/student/class/ajaxMyChildList.jsp"></jsp:include>
						</div>
					</s:if>
					<div class="spaceDiv"></div>
					<div id="attendanceConntent">
						<jsp:include
							page="/WEB-INF/pages/student/class/ajaxAttendanceDetails.jsp"></jsp:include>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
