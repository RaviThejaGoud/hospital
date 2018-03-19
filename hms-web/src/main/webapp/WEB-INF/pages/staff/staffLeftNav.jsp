<%@ include file="/common/taglibs.jsp"%>
<div style="width: 180px;">
	<div class="block_head">
		<h2>
			Personal
		</h2>

	</div>
	<div class="block_content" id="sideMenu">
		<ul style="padding-left: 0px;">
			<li>
				<s:url id="urlStaffCalendarLink" action="ajaxStaffCalendar" />
				<sj:a id="staffCalendarLink" href="%{urlStaffCalendarLink}"
					targets="staffContect" indicator="indicator">My School</sj:a>
			</li>
			<!--<li>
				<s:url id="urlStaffProfileLink" action="ajaxStaffProfile" />
				<sj:a id="staffProfileLink" href="%{urlStaffProfileLink}"
					targets="staffContect" indicator="indicator">My School</sj:a>
			</li>
			-->
			<li>
				<s:url id="urlStaffActivitiesLink" action="ajaxDoGetStaffActivties" />
				<sj:a id="staffActivitiesLink" href="%{urlStaffActivitiesLink}"
					targets="staffContect" indicator="indicator">My Activities</sj:a>
			</li>
			<li>
				<s:url id="urlMyStudentsLink" action="ajaxDoGetStudentsByTeacherClass" />
				<sj:a id="myStudentsLink" href="%{urlMyStudentsLink}"
					targets="staffContect" indicator="indicator">My Students</sj:a>
			</li>
			<li>
				<s:url id="urlMyInboxlink" action="ajaxDoMyInbox" />
				<sj:a id="myInboxlink" href="%{urlMyInboxlink}"
					targets="staffContect" indicator="indicator">My Inbox</sj:a>
			</li>
			<li>
				<s:url id="urlLeaveLink" action="ajaxDoGetLeaveDetailsLeft" />
				<sj:a id="leaveLink" href="%{urlLeaveLink}" targets="staffContect"
					indicator="indicator">Leave Management</sj:a>
			</li>
			<li>
				<s:url id="urlStaffPerformance" action="ajaxStaffPerformance" />
				<sj:a id="sfaffPerformance" href="%{urlStaffPerformance}"
					targets="staffContect" indicator="indicator">Performance Evaluation</sj:a>
			</li>
			<!-- <li>
							<s:url id="urlGoalsLink" action="ajaxGetGoalsReport" />
							<sj:a id="goalsLink" href="%{urlGoalsLink}"
								targets="staffContect" indicator="indicator"> Marks</sj:a>
						</li>
						<li >
						<s:url id="doCreateResource" action="ajaxDoCreateResource" />
						<sj:a targets="staffContect" id="campaignsList"
							href="%{doCreateResource}" label="Campaigns" >Reports</sj:a>
					</li>
						
						<li>
							<a href="#">Study Material</a>
						</li> -->
			<!--<li>
				<s:url id="urlEventsLink" action="ajaxStaffEvents" />
				<sj:a id="eventsLink" href="%{urlEventsLink}" targets="staffContect"
					indicator="indicator">My Neighbours</sj:a>
			</li>
			
			<li>
				<s:url id="urlAddClassExamSchedulesLink"
					action="ajaxDoAddClassExamSchedules" />
				<sj:a id="markAddClassExamSchedulesLink"
					href="%{urlAddClassExamSchedulesLink}" targets="staffContect"
					indicator="indicator">K Bank</sj:a>
			</li>
			<li>
				<s:url id="urlEventsLink" action="ajaxStaffEvents" />
				<sj:a id="eventsLink" href="%{urlEventsLink}" targets="staffContect"
					indicator="indicator">Answer Questions</sj:a>
			</li>
			-->
		 
			<!--  <li>
							<a href="#">Change Password</a>
						</li>
						<li>
							<a href="#">Transport </a>
						</li> -->
		</ul>
	</div>
</div>