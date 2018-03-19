<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Attendance Details
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
					<s:if test="%{academicYear.startDate !=null && academicYear.endDate !=null}">
						<s:if
							test="%{ 'ROLE_HOD' == user.userRole   || 'ROLE_ADMIN' == user.userRole || 'ROLE_PRINCIPAL' == user.userRole || 'ROLE_ADMINOFFICER' == user.userRole || user.schoolDirector}">
							<ul class="nav nav-tabs">
								<s:if test='%{academicYear.useBiometricForStaff == "N"}'>
									<s:if test="%{'ROLE_ADMIN' == user.userRole || 'ROLE_ADMINOFFICER' == user.userRole || 'ROLE_PRINCIPAL' == user.userRole || user.schoolDirector}">
										<li><s:url id="urlAttendanceStaffLink"
												action="ajaxDoGetStaffAttendanceForm" namespace="/admin"
												includeParams="all">
											</s:url> <sj:a href="%{urlAttendanceStaffLink}" data-toggle="tab"
												targets="attendanceCont">Add Staff Attendance </sj:a></li>
									</s:if>
								</s:if>
								<s:if test='%{academicYear.useBiometricForStudent == "N"}'>
									<s:if test="%{'ROLE_ADMIN' == user.userRole || 'ROLE_ADMINOFFICER' == user.userRole || 'ROLE_PRINCIPAL' == user.userRole || user.schoolDirector}">
										<li class="active"><s:if
												test='%{academicYear.manageAttendanceBy== "M"}'>
												<s:url id="urlAttendanceLink"
													action="ajaxDoGetSudentAttendanceByMonthOrWeek"
													includeParams="all" />
												<sj:a href="%{urlAttendanceLink}" targets="attendanceCont"
													data-toggle="tab" indicator="indicator">Add Student Attendance </sj:a>
											</s:if> <s:else>
												<s:url id="urlAttendanceLink"
													action="ajaxDoGetAttendanceFormImpl" includeParams="all"
													namespace="/admin" />
												<sj:a href="%{urlAttendanceLink}" targets="attendanceCont"
													data-toggle="tab" indicator="indicator">Add Student Attendance</sj:a>
											</s:else></li>
									</s:if>
								</s:if>
								<s:if test='%{academicYear.useBiometricForStaff == "Y"}'>
									<s:if test="%{'ROLE_ADMIN' == user.userRole || 'ROLE_ADMINOFFICER' == user.userRole || 'ROLE_PRINCIPAL' == user.userRole || 'ROLE_DIRECTOR' == user.userRole}">
										<li><s:url id="biometricStaffLink"
												action="ajaxDoStaffBiometricAttendanceForm"
												namespace="/admin">
											</s:url> <sj:a href="%{biometricStaffLink}" data-toggle="tab"
												targets="attendanceCont">View Staff Biometric Attendance </sj:a>
										</li>
									</s:if>
								</s:if>
							</ul>
						</s:if>
						<div class="tab-content" id="attendanceCont">
							<s:if test='%{academicYear.useBiometricForStudent == "N"}'>
								<s:if test='%{academicYear.manageAttendanceBy== "M"}'>
									<jsp:include
										page="/WEB-INF/pages/common/staffAttendance/ajaxStudentMonthlyAttendance.jsp"></jsp:include>
								</s:if>
								<s:elseif test='%{academicYear.manageAttendanceBy== "D"}'>
									<jsp:include
										page="/WEB-INF/pages/common/attendance/studentAttendanceForm.jsp"></jsp:include>
								</s:elseif>
							</s:if>
							<s:else>
								<div class="alert alert-info">You can not submit attendance
									because you have selected bio metric option.</div>
							</s:else>
						</div>
					</s:if>
					<s:else>
					<div class="alert alert-info">Please create academic details.</div>
					</s:else>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	$(document).ready(function(){
		FormComponents.init();
		$('li#asstStaffAttDiv').addClass('active');	
	});
	changePageTitle("Attendance Details");
	$('a.academicPlannerId').click(function(){
		window.location.hash="target=ES.ajaxify AAP";
		window.location.reload();
	});
	$('html, body').animate({ scrollTop: $(document).height() - $(window).height() }, 10, function() {
	    $(this).animate({ scrollTop: 0 }, 10);
	});
</script>