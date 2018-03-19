<%@ include file="/common/taglibs.jsp"%>
<div class="wrapper container_18">
	<div class="wrapper">
		<div class="grid_18 block grid_18MarginLeft">
			<div class="grid_4 alpha">
				<div class="block_head">
					<h2>
						Administration
					</h2>
				</div>
				<div class="block_content" id="sideMenu">
					<ul>
						<li class="active">
							<s:url id="urlmanageStaff" action="ajaxGetStudyClassList" namespace="/student"/>
							<sj:a id="manageStaff" href="%{urlmanageStaff}"
								targets="staffContent" indicator="indicator">Manage Student</sj:a>
						</li>
						
						<li>
					<s:if test='%{academicYear.useBiometricForStudent == "N" || academicYear.useBiometricForStaff == "N"}'>
						<s:if test='%{academicYear.useBiometricForStudent == "N"}'>
							<s:if test='%{academicYear.manageAttendanceBy == "D"}'>
							<s:url id="urlAttendanceLink" action="ajaxDoGetAttendanceForm" 
								includeParams="all" namespace="/admin">
							<s:param name="adminAtt" value="adminAtt" /> 
							</s:url>
								<sj:a href="%{urlAttendanceLink}"
									targets="staffContent" indicator="indicator">Manage Attendance</sj:a>
				            </s:if>
				            <s:else>
				            <s:url id="urlAttendanceLink" action="ajaxDoGetSudentAttendanceByMonthOrWeek" namespace="/student"
								includeParams="all" >
							<s:param name="adminAtt" value="adminAtt" /> 
							</s:url>
								<sj:a href="%{urlAttendanceLink}"
									targets="staffContent" indicator="indicator">Manage Attendance</sj:a>
				            </s:else>
						</s:if>
						<s:else>
						<s:if test='%{academicYear.useBiometricForStaff == "N"}'>
						<s:if test='%{academicYear.manageStaffAttendanceBy == "D"}'>
							<s:url id="urlAttendanceLink" action="ajaxDoGetStaffAttendanceForm" 
								includeParams="all" namespace="/admin" >
							<s:param name="adminAtt" value="adminAtt" /> 
							</s:url>
								<sj:a href="%{urlAttendanceLink}"
									targets="staffContent" indicator="indicator">Manage Attendance</sj:a>
				            </s:if>
				            <s:else>
				            <s:url id="urlAttendanceLink" action="ajaxDoGetSudentAttendanceByMonthOrWeek" namespace="/student"
								includeParams="all" escapeAmp="false">
							<s:param name="staffAtt"> staffAtt</s:param>
							</s:url>
								<sj:a href="%{urlAttendanceLink}"
									targets="staffContent" indicator="indicator">Manage Attendance</sj:a>
				            </s:else>
						</s:if>
						</s:else>
						
						</s:if>
							
						</li>
						<li id="backTostaffDetails">
							<s:url id="urlmanageLeavesLink" action="ajaxDoManageStaff" namespace="/staff"/>
							<sj:a id="manageLeavesLink" href="%{urlmanageLeavesLink}"
								targets="staffContent" indicator="indicator">Manage Staff</sj:a>
						</li>
						<li>
							<a href="${pageContext.request.contextPath}/exam/manageExaminationDetails.do" id='examsSection'>Examination Section</a>
						</li>
						<li>
							<s:url id="urlLeaveManagementLink" action="ajaxViewAllManageLeaves" namespace="/admin"/>
							<sj:a id="ViewleavesLink" href="%{urlLeaveManagementLink}"
								targets="staffContent" indicator="indicator">Manage Leaves</sj:a>
						</li>
						<s:if test='%{user.isSchoolTransport=="Y" || user.isOnlySchoolAdmin=="Y"}'>
							<s:if test='%{(customer.transportModuleStatus==true) }'>
							
								<li>
									<a href="${pageContext.request.contextPath}/admin/transportDashboard.do" id='transport'>Manage Transport</a>
								</li>
							</s:if>
						</s:if>
						<li>
							 <s:url id="urlDoViewFeedback" action="ajaxViewFeedbackList" namespace="/admin"/>
							<sj:a id="doViewFeedback" href="%{urlDoViewFeedback}"
								targets="staffContent" indicator="indicator">Feedback</sj:a>
						</li>
						<!--<li>
							 <s:url id="urlDoViewAllReports" action="ajaxDoViewAllReports" />
							<sj:a id="doViewReports" href="%{urlDoViewAllReports}"
								targets="staffContent" indicator="indicator">School Reports</sj:a>
						</li>
						<s:if test="%{customer.hostelModuleStatus}">
							<li>
								 <s:url id="urlDoViewAllhostelReports" action="ajaxDoViewHostelReports" namespace="/reports"/>
								<sj:a id="doViewHostelReports" href="%{urlDoViewAllhostelReports}"
									targets="staffContent" indicator="indicator">Hostel Reports</sj:a>
							</li>
						</s:if>
						--><!--<s:if test='%{#session.previousYear == "N"}'>
							<li>
								 <s:url id="urlDoIdCardsGenerations" action="ajaxIDCardsGenerations" />
								<sj:a id="doIdCardsGenerations" href="%{urlDoIdCardsGenerations}"
									targets="staffContent" indicator="indicator">ID Cards Generation</sj:a>
							</li>
						</s:if>
						-->
						<li>
							 <s:url id="urlDoPromoteStudents" action="ajaxManagePromoteStudents" namespace="/admin"/>
							<sj:a id="doPromoteStudents" href="%{urlDoPromoteStudents}"
								targets="staffContent" indicator="indicator">Manage Promote Students</sj:a>
						</li>
						<!--<li>
							 <s:url id="urlDoExaminationRooms" action="ajaxDoAddExaminationSettings"/>
							<sj:a id="doExaminationRooms" href="%{urlDoExaminationRooms}"
								targets="staffContent" indicator="indicator">Examination Rooms</sj:a>
						</li>-->
						
						<li>
								<a href="${pageContext.request.contextPath}/admin/manageTimeTable.do" id='timeTableLink'>Manage Timetable</a>
						</li>
						<li>
							   <s:url id="urlDoHallTicketneration" action="ajaxDoGetHallTicketGeneration" namespace="/admin"/>
								  <sj:a id="doHallTicketGeneration" href="%{urlDoHallTicketneration}"
									targets="staffContent" indicator="indicator">HallTicket Generation</sj:a>
						</li>
						<li >
							<s:url id="urlmanageClassAssignment" action="ajaxViewAllClassAssignment" namespace="/admin"/>
							<sj:a id="manageClassAssignment" href="%{urlmanageClassAssignment}"
								targets="staffContent" indicator="indicator">Manage Class Assignment</sj:a>
						</li>
						<s:if test="%{customer.masterCustomer != null}">
							<li>
							 <s:url id="urlmanageStudentTransfer" action="ajaxStudentTransfer" namespace="/student"/>
							<sj:a id="manageStudentTransfer" href="%{urlmanageStudentTransfer}"
								targets="staffContent" indicator="indicator">Student Transfer</sj:a>
							</li>
						</s:if>
						<li>
							<a href="${pageContext.request.contextPath}/admin/generateStudentsCertificates.do" id='studsCertificateGen'>Manage Students Certificates</a>
						</li>
						<li>
							<s:url id="urlUploadOrDownloadDocuments" namespace="/student"
								action="ajaxViewUploadOrDownloadDocumentsInfo" />
							<sj:a id="uploadDownloadDocuments" href="%{urlUploadOrDownloadDocuments}"
								targets="staffContent" indicator="indicator">Upload/Download Documents</sj:a>
						</li>
					</ul>
				</div>
			</div>
			<div id="staffContent" class="grid_14 alpha">
				<jsp:include page="/WEB-INF/pages/admin/common/ajaxViewStudyClassList.jsp"></jsp:include>
			</div>
		</div>
	</div>
</div>
<script Language="Javascript1.2" type="text/javascript">

$(document).ready( function() {
			if(getUrlVars()["value"]=="staffDetails"){
				$('li#backTostaffDetails a').click();
			}
			changePageTitle("Manage Student");
			$('#adminStaffAndStudent').addClass('current');
			if(getUrlVars()["value"]=="false"){
				$('a#manageStudentMarks').click();
			}
			if(getUrlVars()["value"]=="leaves"){
				$('a#ViewleavesLink').click();
			}
		});
		
</script>