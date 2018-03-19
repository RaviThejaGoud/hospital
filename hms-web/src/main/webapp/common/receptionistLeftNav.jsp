<li id="manageStudent" class="start active">
	<a href="javascript:;"	id="MSTI">
		<i class="fa fa-user"></i> 
			<span class="title">Manage Student</span> 
			<span class="selected"></span> 
			<span class="arrow "></span>
	</a>
	<ul class="sub-menu">
		<li id="studentDiv" class="start active">
			<s:url id="urlmanageStudent" action="ajaxGetStudyClassList"	namespace="/student" includeParams="all" escapeAmp="false" /> 
			<sj:a	id="manageStudents" href="%{urlmanageStudent}"	targets="mainContentDiv" cssClass="ajaxify MSTI">Students Information</sj:a>
		</li>
	</ul>
</li>
<li id="manageStaff">
	<a href="javascript:;" id="MSF"> 
		<i	class="fa fa-user"></i> 
		<span class="title">Manage Staff</span> 
		<span class="selected"></span> 
		<span class="arrow "></span>
	</a>
	<ul class="sub-menu">
		<li id="staffInfoDiv">
			<s:url id="urlManageStaff"	action="ajaxDoManageStaff" namespace="/staff" /> 
				<sj:a	id="urlManageStaff" href="%{urlManageStaff}" targets="mainContentDiv" cssClass="ajaxify MSF">
								Staff Information
				</sj:a>
		</li>
	</ul>
</li>
<li id="admissionsNav">
	<a href="javascript:;" id="ADMS"> 
		<i class="fa fa-laptop"></i> 
		<span class="title">Admissions</span> 
		<span class="selected"></span> 
		<span class="arrow"></span>
	</a>
	<ul class="sub-menu">
		<li id="applicationsNav">
			<s:url id="urlGetAdmissions" action="ajaxGetOnlineAdmissions" namespace="/admin" /> 
			<sj:a id="admissionDetails" href="%{urlGetAdmissions}" targets="mainContentDiv" cssClass="ajaxify ADMS">Application Details</sj:a>
		</li>
		<li id="approvedStudsNav">
			<s:url id="urlPendingApplications"	action="ajaxApprovedApplicationsHome" namespace="/admin" /> 
			<sj:a  id="urlPendingApplications" href="%{urlPendingApplications}"	targets="mainContentDiv" cssClass="ajaxify SLAP">Shortlisted Applications</sj:a>
		</li>
		<li id="admissoinSettingsNav">
			<s:url id="urlRejectedApplications"	action="ajaxRejectedApplications" namespace="/admin" />
			<sj:a id="urlRejectedApplications" href="%{urlRejectedApplications}" targets="mainContentDiv" cssClass="ajaxify RAP">Rejected Applications</sj:a>
		</li>
		<li id="admittedStudsNav">
			<s:url id="urlGetAdmittedStudents" action="ajaxViewAdmittedStudents" namespace="/admin" /> 
			<sj:a id="urlGetAdmittedStudents" href="%{urlGetAdmittedStudents}" targets="mainContentDiv" cssClass="ajaxify AMS">Admitted Students</sj:a>
		</li>
		<li>
			<s:url id="urlGetAdmittedStudentsIdCard" action="ajaxIDCardsGenerationForAdmittedStu" namespace="/admin" />
			<sj:a id="getAdmittedStudentsIdCard" href="%{urlGetAdmittedStudentsIdCard}" targets="mainContentDiv" cssClass="ajaxify CACS">Admitted Students Id Cards</sj:a>
		</li>
		<li>
			<s:url id="urlGetEditAdmissions" action="ajaxGetEditOnlineAdmissions" namespace="/admin" /> 
			<sj:a  id="editAdmissionDetails" href="%{urlGetEditAdmissions}"	targets="mainContentDiv" cssClass="ajaxify UAP">Update Applications</sj:a>
		</li>
	</ul>
</li>
<li>
	<a href="javascript:;" id="ASMS">
		<i class="fa fa-comment"></i>
		<span class="title">SMS</span> 
		<span class="selected"></span> 
		<span class="arrow"></span> 
	</a>
	<ul class="sub-menu">
		<li>
			<s:url id="urlInboxesDetails"	action="ajaxDoGetSchoolWideMessagesList" namespace="/common"	includeParams="all" escapeAmp="false">
				<s:param value="#session.academicYear" name="academicYearId" />
			</s:url> 
			<sj:a href="%{urlInboxesDetails}" targets="mainContentDiv"	cssClass='ajaxify ASMEL'>
											Send SMS or E-Mail
			</sj:a>
		</li>
	</ul>
</li>
<li>
	<a href="${pageContext.request.contextPath}/staff/manageStaffLeaves.do?id=tManageLeaves" id="tManageLeaves"> 
		<i class="fa fa-asterisk"></i> 
		<span class="title">Leaves Management</span> 
		<span class="selected"></span>
	</a>
</li>

<li id="manageEvents">
	<a href="javascript:;" id="EVNTS"><i class="fa fa-music"></i><span class="title">
			Manage Events</span><span class="selected"></span><span class="arrow "></span>
	</a>
	<ul class="sub-menu">
		<li id="eventsInfo">
			<s:url id="urlEventsLink" action="ajaxStaffEvents"	namespace="/staff" />
			<sj:a href="%{urlEventsLink}" targets="mainContentDiv"	cssClass="ajaxify EVNT">
				<i class="fa fa-music"></i>
					Events
			</sj:a>
		</li>	
	</ul>
</li>