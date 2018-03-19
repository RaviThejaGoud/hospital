<%@ include file="/common/taglibs.jsp"%>
<div class="grid_4 alpha">
	<div class="block_head">
		<h2>
			ACTIVITIES
		</h2>
	</div>
	<div class="block_content" id="sideMenu" >
		<ul class="activities">
			<li>
				<s:url id="urlStudentAttendanceLink" action="ajaxStudentAttendance"  namespace="/student"/>
				<sj:a id="studentAttendanceLink" href="%{urlStudentAttendanceLink}"
					targets="studentContent" indicator="indicator">Attendance</sj:a>
			</li>
			<li class="active">
					<s:url id="urlStudentAcademics" action="ajaxManageStudentExamSchedulesAndResults" namespace="/exam"/>
					<sj:a id="studentAcedemics" href="%{urlStudentAcademics}"
						targets="studentContent" indicator="indicator">Exam Schedules & Results</sj:a>
			</li>
			<li id="evaluationNav">
					<s:url id="urlFeeDetails" action="ajaxViewMyRecommendations" namespace="/student" />
					<sj:a id="urlFeeDetails1" href="%{urlFeeDetails}"
						targets="studentContent" indicator="indicator">Performance Evaluation</sj:a>
			</li>
			<s:if test='%{user.isParent=="Y" && user.isSchoolStudent=="N"}'>
				<li>
						<s:url id="urlLeaveLink" action="ajaxDoGetStudentLeaveDetails" namespace="/student"/>
						<sj:a id="leaveLink" href="%{urlLeaveLink}"
							targets="studentContent" indicator="indicator">Leave Management</sj:a>
				<li>
					<s:url id="urlChildClassMatesLink"
						action="ajaxGetMyChildrenClassMatesList" namespace="/student"/>
					<sj:a id="urlChildClassMatesLink" href="%{urlChildClassMatesLink}"
						targets="studentContent" indicator="indicator">Classmates</sj:a>
				</li>
				<li>
					<s:url id="urlDoRegisterStudentEventLink"
						action="ajaxStudentEvents" namespace="/student" />
					<sj:a id="registerStudentEventLink"
						href="%{urlDoRegisterStudentEventLink}" targets="studentContent"
						indicator="indicator">Events</sj:a>
				</li>
				<li>
					<s:url id="urlParentFeedbackLink"
						action="ajaxselectChildForFeedback" namespace="/student"/>
					<sj:a id="subjectLink" href="%{urlParentFeedbackLink}"
						targets="studentContent" indicator="indicator">Feedback</sj:a>
				</li>
				<s:if test='%{customerByCustId.preferences.visibleFeeInfoToParent=="Y"}'>
					<li id="studentPayments">
						<s:url id="urlDoGetChildsFeesLink" action="ajaxGetMyChildFees" namespace="/student"/>
						<sj:a id="doGetChildsDetailsLink" href="%{urlDoGetChildsFeesLink}"
							targets="studentContent" indicator="indicator">Upcoming Payments</sj:a>
					</li>
				</s:if>
				<li>
						<s:url id="urlmanageClassAssignment"
							action="ajaxViewYourStudentAssignment" namespace="/student" />
						<sj:a id="manageClassAssignment" href="%{urlmanageClassAssignment}"
							targets="studentContent" indicator="indicator">Manage Class Assignment</sj:a>
				</li>
			</s:if>
			<s:else>
				<!--<li>
					<s:url id="urlStudentAttendanceLink" action="ajaxStudentAttendance" />
					<sj:a id="studentAttendanceLink" href="%{urlStudentAttendanceLink}"
						targets="studentContent" indicator="indicator">Attendance</sj:a>
				</li>
				<li class="active">
					<s:url id="urlStudentAcademics" action="ajaxStudentExamSchedules" />
					<sj:a id="studentAcedemics" href="%{urlStudentAcademics}"
						targets="studentContent" indicator="indicator">Exam Schedules & Results</sj:a>
				</li>-->
				<li>
					<s:url id="urlLeaveLink" action="ajaxDoGetLeaveDetailsForStudent" namespace="/student"/>
					<sj:a id="leaveLink" href="%{urlLeaveLink}"
						targets="studentContent" indicator="indicator">Leave Management</sj:a>
				</li>
				<li>
					<s:url id="urlDoRegisterStudentEventLink"
						action="ajaxStudentEvents" namespace="/student" />
					<sj:a id="registerStudentEventLink"
						href="%{urlDoRegisterStudentEventLink}" targets="studentContent"
						indicator="indicator">Events</sj:a>
				</li>
				<li>
					<s:url id="urlClassMatesLink" action="ajaxGetClassMatesList" namespace="/student"/>
					<sj:a id="classMatesLink" href="%{urlClassMatesLink}"
						targets="studentContent" indicator="indicator">Classmates</sj:a>
				</li>
				<li>
					<s:url id="urlDoGetMyFeeDetailsLink" action="ajaxGetMyChildFees" namespace="/student"/>
					<sj:a id="doGetMyFeeDetailsLink" href="%{urlDoGetMyFeeDetailsLink}"
						targets="studentContent" indicator="indicator">Payment & Receipts</sj:a>
				</li>
				<li>
					<s:url id="urlParentFeedbackLink"
						action="ajaxselectChildForFeedback" namespace="/student"/>
					<sj:a id="subjectLink" href="%{urlParentFeedbackLink}"
						targets="studentContent" indicator="indicator">Feedback</sj:a>
				</li>
			</s:else>
			<li>
				<s:url id="urlViewStudTimeTable"
					action="ajaxViewStudentTimeTable" namespace="/student"/>
				<sj:a id="timeTableUrlLink" href="%{urlViewStudTimeTable}"
					targets="studentContent" indicator="indicator">Timetable</sj:a>
			</li>
			<s:if test='%{user.isParent=="N" && user.isSchoolStudent=="Y"}'>
				<li>
					<s:url id="urlmanageClassAssignment"
						action="ajaxViewStudentAssignment" namespace="/student" />
					<sj:a id="manageClassAssignment" href="%{urlmanageClassAssignment}"
						targets="studentContent" indicator="indicator">Manage Class Assignment</sj:a>
				</li>
			</s:if>
			<li>
				<s:url id="urlUploadOrDownloadDocuments"
					action="ajaxViewUploadOrDownloadDocumentsInfo"  namespace="/student"/>
				<sj:a id="uploadDownloadDocuments" href="%{urlUploadOrDownloadDocuments}"
					targets="studentContent" indicator="indicator">Upload/Download Documents</sj:a>
			</li>
			<s:if test='%{user.isParent=="N" && user.isSchoolStudent=="Y"}'>

			</s:if>
			<s:else>
			</s:else>
			 
		</ul>
	</div>
</div>
