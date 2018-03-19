<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp"></jsp:include>
<head>
	
</head>
<body />
	<div class="wrapper container_16">
		<!-- wrapper begins -->
		<div class="block grid_4">
			<div class="block_head">
					<h2>Activities</h2>
			</div>
			<div class="block_content" id="sideMenu"
				style="padding-left: 0px; padding-right: 0px; padding-top: 0px;">
				<ul style="padding-left: 0px;">
				<li>
					<s:url id="ajaxClassAttendencelink" action="ajaxClassAttendence" />
					<sj:a href="%{ajaxClassAttendencelink}" targets="staffContect"
						indicator="indicator">Attendance</sj:a>
				</li>
				<li>
					<s:url id="urlMarkTempLink" action="ajaxDoAddClassExamSchedules" />
					<sj:a href="%{urlMarkTempLink}" targets="staffContect"
						indicator="indicator">Exams schedules & Results</sj:a>
				</li>
				<li>
					<s:url id="urlMyStudentsLink"
						action="ajaxDoGetStudentsByTeacherClass" />
					<sj:a id="myStudentsLink" href="%{urlMyStudentsLink}"
						targets="staffContect" indicator="indicator">My Students</sj:a>
				</li>
				<li>
					<s:url id="urlMyLeaveLink" action="ajaxStaffPerformance" />
					<sj:a href="%{urlMyLeaveLink}" targets="staffContect"
						indicator="indicator">Performance</sj:a>
				</li>
				<li class="active">
					<s:url id="urlLeaveLink" action="ajaxDoGetLeaveDetailsLeft" />
					<sj:a href="%{urlLeaveLink}" targets="staffContect"
						indicator="indicator">Leave Management</sj:a>
				</li>
				<li>
					<s:url id="urlEventsLink" action="ajaxStaffEvents" />
					<sj:a href="%{urlEventsLink}" targets="staffContect"
						indicator="indicator">Events</sj:a>
				</li>
				<li>
					<s:url id="urlQuizLink" action="ajaxDoGetCreateQuiz" />
					<sj:a href="%{urlQuizLink}" targets="staffContect"
						indicator="indicator">Quiz</sj:a>
				</li>
				<li>
					<s:url id="urlHomeWorkLink" action="ajaxDoHomeWork" />
					<sj:a href="%{urlHomeWorkLink}" targets="staffContect"
						indicator="indicator">Home Work & Assignment</sj:a>
				</li>
				<li>
					<s:url id="urlPromoteStudentsLink" action="ajaxDoPromoteStudents" />
					<sj:a href="%{urlPromoteStudentsLink}" targets="staffContect"
						indicator="indicator">Promote Students</sj:a>
				</li>
				<!--<li>
					<s:url id="urlStaffSubjectsLink" action="ajaxDoGetStaffSubjects" />
					<sj:a href="%{urlStaffSubjectsLink}"
						targets="staffContect" indicator="indicator">My Subjects</sj:a>
				</li>
				
			-->
			<!--	<li>
					<s:url id="urlApprovalLeaveLink" action="ajaxDoStaffLeaveApprovalsAsApprover" />
					<sj:a href="%{urlApprovalLeaveLink}" targets="applyLeave"
						indicator="indicator">Leave Approvals</sj:a>
				</li>
				-->
				</ul>
			</div>
			
		</div>
		<div id="staffContect" class="block grid_12">
			 <jsp:include page="/WEB-INF/pages/staff/leaves/leaveHome.jsp"></jsp:include>
		</div>
	</div>
	<script type="text/javascript">
	changePageTitle("leave Management Home");
    $('#classActivities').addClass('current');
    </script>