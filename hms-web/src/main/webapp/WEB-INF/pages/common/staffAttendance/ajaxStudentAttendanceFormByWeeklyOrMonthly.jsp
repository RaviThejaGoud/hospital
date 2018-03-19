<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>
					<s:if test='%{StaffRoleName == "staffAtt"}'>
							Staff Attendance Details
					</s:if>
					<s:else>
							Student Attendance Details
					</s:else>
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
					<s:if
						test="%{ 'ROLE_HOD' == user.userRole   || 'ROLE_ADMIN' == user.userRole || 'ROLE_PRINCIPAL' == user.userRole || 'ROLE_ADMINOFFICER' == user.userRole}">
						<ul class="nav nav-tabs">
							<s:if test='%{academicYear.useBiometricForStudent == "N"}'>
								<s:if test="%{'ROLE_ADMIN' == user.userRole || 'ROLE_ADMINOFFICER' == user.userRole}">
									<s:if test='%{StaffRoleName == "staffAtt"}'>
										<li>
									</s:if>
									<s:elseif test='%{anyId == "Mail To Parent"}'>
										<li>
									</s:elseif>
									<s:else>
										<li class="active">
									</s:else>
									<s:if test='%{academicYear.manageAttendanceBy== "M"}'>
										<s:url id="urlAttendanceLink" namespace="/student"
											action="ajaxDoGetSudentAttendanceByMonthOrWeek" />
										<sj:a href="%{urlAttendanceLink}" targets="staffContent"
											indicator="indicator">Create Student Attendance</sj:a>
									</s:if>
									<s:else>
										<s:url id="urlAttendanceLink" namespace="/admin"
											action="ajaxDoGetAttendanceForm" />
										<sj:a href="%{urlAttendanceLink}" targets="staffContent"
											indicator="indicator">Create Student Attendance</sj:a>
									</s:else>
									<s:if test='%{anyId == "Mail To Parent"}'>
										<li class="active">
									</s:if>
									<s:else>
										<li>
									</s:else>
									<s:if test='%{academicYear.manageAttendanceBy== "M"}'>
										<s:url id="urlAttendanceLink" namespace="/student"
											action="ajaxDoGetSudentAttendanceByMonthOrWeek">
											<s:param name="anyId">Mail To Parent</s:param>
										</s:url>
										<sj:a href="%{urlAttendanceLink}" targets="staffContent"
											indicator="indicator">Monthly Attendance To Mail</sj:a>
									</s:if>
								</s:if>
							</s:if>
							<s:if test='%{academicYear.useBiometricForStaff == "N"}'>
								<s:if test="%{'ROLE_ADMIN' == user.userRole || 'ROLE_ADMINOFFICER' == user.userRole}">
									<s:if test='%{StaffRoleName == "staffAtt"}'>
										<li class="active">
									</s:if>
									<s:else>
										<li>
									</s:else>
									<s:if test='%{academicYear.manageStaffAttendanceBy == "M"}'>
										<s:url id="urlAttendanceStaffLink"
											action="ajaxDoGetSudentAttendanceByMonthOrWeek"
											namespace="/student" includeParams="all">
											<s:param name="staffAtt">staffAtt</s:param>
										</s:url>
										<sj:a href="%{urlAttendanceStaffLink}" targets="staffContent"
											indicator="indicator">Create Staff Attendance</sj:a>
									</s:if>
									<s:else>
										<s:url id="urlAttendanceStaffLink"
											action="ajaxDoGetStaffAttendanceForm" namespace="/admin"
											includeParams="all">
										</s:url>
										<sj:a href="%{urlAttendanceStaffLink}" targets="staffContent"
											indicator="indicator">Create Staff Attendance</sj:a>
									</s:else>
								</s:if>
							</s:if>
						</ul>
					</s:if>
					<div id="studentAttendanceDiv" class="tab-content">
						<%@ include file="/common/messages.jsp"%>
						<s:if test='%{StaffRoleName == "staffAtt"}'>
							<div class="block_content" id="studentAt">
								<jsp:include
									page="/WEB-INF/pages/common/staffAttendance/staffAttendanceFormByWeeklyOrMonthly.jsp"></jsp:include>
							</div>
						</s:if>
						<s:else>
							<div class="tab_content" id="studentAt">
								<s:if test='%{anyId == "Mail To Parent"}'>
									<jsp:include
										page="/WEB-INF/pages/common/staffAttendance/ajaxStudentMonthlyAttendanceMailsToParent.jsp"></jsp:include>
								</s:if>
								<s:else>
									<jsp:include
										page="/WEB-INF/pages/common/staffAttendance/ajaxStudentMonthlyAttendance.jsp"></jsp:include>
								</s:else>
							</div>
						</s:else>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!--<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>
					<s:if test='%{StaffRoleName == "staffAtt"}'>
						<h4>
							Staff Attendance Details
						</h4>
					</s:if>
					<s:else>
						<h4>
							Student Attendance Details
						</h4>
					</s:else>
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
					<s:if
						test="%{ 'ROLE_HOD' == user.userRole   || 'ROLE_ADMIN' == user.userRole || 'ROLE_PRINCIPAL' == user.userRole}">
						<ul class="nav nav-tabs">
							<s:if test='%{academicYear.useBiometricForStudent == "N"}'>
								<s:if test="%{'ROLE_ADMIN' == user.userRole}">
									<s:if test='%{StaffRoleName == "staffAtt"}'>
										<li>
									</s:if>
									<s:elseif test='%{anyId == "Mail To Parent"}'>
										<li>
									</s:elseif>
									<s:else>
										<li class="active">
									</s:else>
									<s:if test='%{academicYear.manageAttendanceBy== "M"}'>
										<s:url id="urlAttendanceLink" namespace="/student"
											action="ajaxDoGetSudentAttendanceByMonthOrWeek" />
										<sj:a href="%{urlAttendanceLink}" targets="staffContent"
											indicator="indicator">Create Student Attendance</sj:a>
									</s:if>
									<s:else>
										<s:url id="urlAttendanceLink" namespace="/admin"
											action="ajaxDoGetAttendanceForm" />
										<sj:a href="%{urlAttendanceLink}" targets="staffContent"
											indicator="indicator">Create Student Attendance</sj:a>
									</s:else>
									<s:if test='%{anyId == "Mail To Parent"}'>
										<li class="active">
									</s:if>
									<s:else>
										<li>
									</s:else>
									<s:if test='%{academicYear.manageAttendanceBy== "M"}'>
										<s:url id="urlAttendanceLink" namespace="/student"
											action="ajaxDoGetSudentAttendanceByMonthOrWeek">
											<s:param name="anyId">Mail To Parent</s:param>
										</s:url>
										<sj:a href="%{urlAttendanceLink}" targets="staffContent"
											indicator="indicator">Monthly Attendance To Mail</sj:a>
									</s:if>
								</s:if>
							</s:if>
							<s:if test='%{academicYear.useBiometricForStaff == "N"}'>
								<s:if test="%{'ROLE_ADMIN' == user.userRole}">
									<s:if test='%{StaffRoleName == "staffAtt"}'>
										<li class="active">
									</s:if>
									<s:else>
										<li>
									</s:else>
									<s:if test='%{academicYear.manageStaffAttendanceBy == "M"}'>
										<s:url id="urlAttendanceStaffLink"
											action="ajaxDoGetSudentAttendanceByMonthOrWeek"
											namespace="/student" includeParams="all">
											<s:param name="staffAtt">staffAtt</s:param>
										</s:url>
										<sj:a href="%{urlAttendanceStaffLink}" targets="staffContent"
											indicator="indicator">Create Staff Attendance</sj:a>
									</s:if>
									<s:else>
										<s:url id="urlAttendanceStaffLink"
											action="ajaxDoGetStaffAttendanceForm" namespace="/admin"
											includeParams="all">
										</s:url>
										<sj:a href="%{urlAttendanceStaffLink}" targets="staffContent"
											indicator="indicator">Create Staff Attendance</sj:a>
									</s:else>
								</s:if>
							</s:if>
						</ul>
					</s:if>
				</div>
				<s:if test='%{StaffRoleName == "staffAtt"}'>
					<div class="block_content" id="studentAt">
						<jsp:include
							page="/WEB-INF/pages/common/staffAttendance/staffAttendanceFormByWeeklyOrMonthly.jsp"></jsp:include>
					</div>
				</s:if>
				<s:else>
					<div class="tab_content" id="studentAt">
						<s:if test='%{anyId == "Mail To Parent"}'>
							<jsp:include
								page="/WEB-INF/pages/common/staffAttendance/ajaxStudentMonthlyAttendanceMailsToParent.jsp"></jsp:include>
						</s:if>
						<s:else>
							<jsp:include
								page="/WEB-INF/pages/common/staffAttendance/ajaxStudentMonthlyAttendance.jsp"></jsp:include>
						</s:else>
					</div>
				</s:else>
			</div>
		</div>
	</div>
</div>
--><script type="text/javascript">
$(document).ready(function() {
	$("input[name=chkBoxMonthIds]").click(function() {
		if ($("input[name=chkBoxMonthIds]:unchecked").length > 0) {
			//$(".allReligions").removeAttr("checked");
			$(".allmonths").attr("checked", false);
		} else {
			$(".allmonths").attr("checked", true);
		}
	});
});
function checkAllMonths() {
	if ($(".allmonths").is(':checked'))
		$("input[name='chkBoxMonthIds']").attr("checked", "true");
	else
		$("input[name='chkBoxMonthIds']").removeAttr("checked");
}
function generateMonthNames() {

	var selectedClassId = $("select#classId option:selected").val();
	if (($("input[name=chkBoxMonthIds]:checked").length > 0)
			&& (selectedClassId > 0)) {
		var monthIds = $("input[name=chkBoxMonthIds]:checked");
		var selectedMonthIds = '';
		if (monthIds.length > 0) {
			selectedMonthIds = '';
			for ( var i = 0; i < monthIds.length; i++) {
				selectedMonthIds += monthIds[i].value + ',';
			}
			selectedMonthIds += '';
		}
		$("#monthNameIds").val(selectedMonthIds);
	} else if (($("input[name=chkBoxMonthIds]:checked").length == 0)
			&& (selectedClassId > 0)) {
		alert("Please select at least one month.");
		event.originalEvent.options.submit = false;
	} else if (($("input[name=chkBoxMonthIds]:checked").length > 0)
			&& (selectedClassId == 0)) {
		alert("Please select at least one class.");
		event.originalEvent.options.submit = false;
	} else if (($("input[name=chkBoxMonthIds]:checked").length == 0)
			&& (selectedClassId == 0)) {
		alert("Please select at least one class and month.");
		event.originalEvent.options.submit = false;
	}
}
</script>