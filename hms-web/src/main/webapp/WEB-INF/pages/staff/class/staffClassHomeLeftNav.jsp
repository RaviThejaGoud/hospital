<%@ include file="/common/taglibs.jsp"%>
<div class="grid_4 alpha">
		<div class="block_head">
			<h2>
				Activities
			</h2>
		</div>
		<div class="block_content" id="sideMenu" >
			<ul>
				<s:if test='%{tempBoolean}'>
				<li>
					<s:if test='%{academicYear.manageAttendanceBy == "D"}'>
						<s:url id="ajaxClassAttendencelink1" action="ajaxDoGetAttendanceForm" 
							includeParams="all" escapeAmp="false">
						</s:url>
							<sj:a href="%{ajaxClassAttendencelink1}"
								targets="staffContect" indicator="indicator">Manage Attendance</sj:a>
			            </s:if>
			            <s:else>
			            <s:url id="ajaxClassAttendencelink" action="ajaxDoGetSudentAttendanceByMonthOrWeek" namespace="/student"
							includeParams="all" escapeAmp="false">
						</s:url>
							<sj:a href="%{ajaxClassAttendencelink}"
								targets="staffContect" indicator="indicator">Manage Attendance</sj:a>
			            </s:else> 
				</li>
				<li>
					 <s:url id="urlDoScoreCard" action="ajaxManageScorecardGeneration"  namespace="/exam" escapeAmp="false" includeParams="all">
							<s:param name="anyTitle">Y</s:param>
					 </s:url>
					<sj:a id="doScoreCardGen" href="%{urlDoScoreCard}" title="It generates score card based on templates."
						targets="staffContect" indicator="indicator">Score Card Generation</sj:a>
				</li>
				</s:if>
				<li id="examSchedulesNav">
					<s:url id="urlMarkTempLink" action="ajaxStaffExamSchedules" namespace="/exam"/>
					<sj:a href="%{urlMarkTempLink}" targets="staffContect"
						indicator="indicator">Exams &amp; Results</sj:a>
				</li>
				<s:if test='%{user.isSchoolPrincipal == "Y" || user.isSchoolDirector == "Y"}'>
					<li>
						<s:url id="urlMyStaffLink"
							action="ajaxDoManageStaff" namespace="%{pageContext.request.contextPath}/staff">
							<s:param name="staff.id" value="0"> </s:param>
						</s:url>
						<sj:a id="myStaffLink" href="%{urlMyStaffLink}"
							targets="staffContect" indicator="indicator">My Staff</sj:a>
					</li>
					<li>
						<s:url id="urlMyStudentsLink"
							action="ajaxGetStudyClassList" namespace="%{pageContext.request.contextPath}/student">
							<s:param name="staff.id" value="0"> </s:param>
						</s:url>
						<sj:a id="myStudentsLink" href="%{urlMyStudentsLink}"
							targets="staffContect" indicator="indicator">My Students</sj:a>
					</li>
				</s:if>
				<s:else>
					<li>
						<s:url id="urlMyStudentsLink"
							action="ajaxGetStaffStudyClasses" namespace="%{pageContext.request.contextPath}/common">
							<s:param name="staff.id" value="0"> </s:param>
						</s:url>
						<sj:a id="myStudentsLink" href="%{urlMyStudentsLink}"
							targets="staffContect" indicator="indicator">My Students</sj:a>
					</li>
				</s:else>
				<li id="myPerformanceNav">
					<s:url id="urlMyLeaveLink" action="ajaxStaffPerformance" />
					<sj:a href="%{urlMyLeaveLink}" targets="staffContect"
						indicator="indicator">My Performance</sj:a>
				</li>
				<li>
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
					<s:url id="urlViewClasTimetable" action="ajaxDoViewStaffTimeTable" />
					<sj:a href="%{urlViewClasTimetable}" targets="staffContect"
						indicator="indicator">Timetable</sj:a>
				</li>
				<li id="studentAssignment">
					<s:url id="urlmanageClassAssignment"
						action="ajaxViewStaffClassAssignment" namespace="/admin" />
					<sj:a id="manageClassAssignment" href="%{urlmanageClassAssignment}"
						targets="staffContect" indicator="indicator">Manage Class Assignment</sj:a>
				</li>
				<s:if test="%{tempBoolean == true}">
				<li>
						<s:url id="urlDosendMarksToMobile" action="ajaxDoViewClassesTeacherHaveMarks" namespace="/exam"/>
						<sj:a id="dosendMarksToMobile" href="%{urlDosendMarksToMobile}"
							targets="staffContect" indicator="indicator">Send Marks To Mobile</sj:a>
				</li>
				</s:if>
				<!--<li>
					<s:url id="urlHomeWorkLink" action="ajaxDoHomeWork" />
					<sj:a href="%{urlHomeWorkLink}" targets="staffContect"
						indicator="indicator">Home Work & Assignment</sj:a>
				</li>
				<li>
					<s:url id="urlPromoteStudentsLink" action="ajaxDoPromoteStudents" />
					<sj:a href="%{urlPromoteStudentsLink}" targets="staffContect"
						indicator="indicator">Promote Students</sj:a>
				</li>-->
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