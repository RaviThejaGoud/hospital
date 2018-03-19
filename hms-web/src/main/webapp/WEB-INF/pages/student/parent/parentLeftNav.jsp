<%@ include file="/common/taglibs.jsp"%>
<div class="grid_4 alpha">
	<div class="block_head">
		<h2>
			Personal
		</h2>
	</div>
	<div class="block_content" id="sideMenu">
		<ul>
			<li>
				<s:url id="urlStaffProfileLink" action="ajaxStaffProfile" />
				<sj:a id="staffProfileLink" href="%{urlStaffProfileLink}"
					targets="staffContect" indicator="indicator">My Profile</sj:a>
			</li>

			<li>
				<s:url id="urlStaffSubjectsLink" action="ajaxDoGetStaffSubjects" namespace="/admin"/>
				<sj:a id="staffSubjectLink" href="%{urlStaffSubjectsLink}"
					targets="staffContect" indicator="indicator">My Subjects</sj:a>
			</li>
			<li>
				<s:url id="ajaxClassAttendencelink" action="ajaxClassAttendence" />
				<sj:a id="goalsLink1" href="%{ajaxClassAttendencelink}"
					targets="staffContect" indicator="indicator">Attendance</sj:a>
			</li>
			<li>
				<s:url id="urlLeaveLink" action="ajaxDoGetLeaveDetails" namespace="/staff"/>
				<sj:a id="leaveLink" href="%{urlLeaveLink}" targets="studentContent"
					indicator="indicator">My leaves</sj:a>
			</li>
			<li>
				<s:url id="urlEventsLink" action="ajaxStaffEvents" namespace="/staff"/>
				<sj:a id="eventsLink" href="%{urlEventsLink}" targets="staffContect"
					indicator="indicator">My Events</sj:a>
			</li> 
		</ul>
	</div>
</div>