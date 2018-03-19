<%@ include file="/common/taglibs.jsp"%>
<!--  this page used for student and parent hearders --- HORIZONTAL AND SIDEBAR MENU FOR MOBILE & TABLETS-->
<ul class="page-sidebar-menu visible-sm visible-xs">
	  <s:if test='%{user.isParent=="Y" || user.isSchoolStudent=="Y"}'>
		<li class="start active">
	  	 <a href="${pageContext.request.contextPath}/student/studentHome.do?id=studentDashboard"
			id="studentDashboard" class="start"> <i class="fa fa-home"></i>
			<span class="title">Dashboard</span> <span class="selected"></span>
		  </a>
		</li>
		<li >
		<a href="javascript:;" id="ESR"> <i class="fa fa-gift"></i> <span class="title">My Class</span> <span class="selected"></span> <span class="arrow"></span> </a>
			<ul class="sub-menu">
				<li id="showExamSchedules">
					<s:url id="urlStudentAcademics" action="ajaxManageStudentExamSchedulesAndResults" namespace="/exam" />
					<sj:a href="%{urlStudentAcademics}" targets="mainContentDiv" cssClass="ajaxify ESR">Exam Schedules & Results</sj:a>
				</li>
				<li>
					<s:url id="urlStudentAttendanceLink" action="ajaxStudentAttendance" namespace="/student" />
					<sj:a  href="%{urlStudentAttendanceLink}" targets="mainContentDiv" cssClass="ajaxify AC"> Attendance</sj:a>
				</li>
				<li id="performanceDiv">
					<s:url id="urlPeDetails" action="ajaxViewMyRecommendations" namespace="/student" />
					<sj:a  href="%{urlPeDetails}" targets="mainContentDiv" cssClass="ajaxify PE">Performance Evaluation</sj:a>
				</li>
				<li id="performanceAttDiv">
					<s:url id="urlAttendance" action="ajaxViewMyAttendanceGraph" namespace="/student" />
					<sj:a href="%{urlAttendance}" targets="mainContentDiv" cssClass="ajaxify PCA">Performance Attendance</sj:a>
				</li>
				<s:if test='%{user.isParent=="Y"}'>
					<li>
						<s:url id="urlParentLeaveLink" action="ajaxDoGetStudentLeaveDetails" namespace="/student" />
						<sj:a href="%{urlParentLeaveLink}" targets="mainContentDiv" cssClass="ajaxify LM"> Leave Management</sj:a>
					</li>
					<li>
						<s:url id="urlChildClassMatesLink" action="ajaxGetMyChildrenClassMatesList" namespace="/student" />
						<sj:a href="%{urlChildClassMatesLink}" targets="mainContentDiv" cssClass="ajaxify CL"> Classmates</sj:a>
					</li>
					<li>
						<s:url id="urlDoRegisterStudentEventLink" action="ajaxStudentEvents" namespace="/student" />
						<sj:a  href="%{urlDoRegisterStudentEventLink}" targets="mainContentDiv" cssClass="ajaxify EV"> Events</sj:a>
					</li>
					<li>
						<s:url id="urlDoParentHolidayLink" action="ajaxViewSchoolSettingsHolidays" namespace="/admin" />
						<sj:a   href="%{urlDoParentHolidayLink}" targets="mainContentDiv" cssClass="ajaxify HVP"> Holidays</sj:a>
					</li>
					<s:if test='%{customerByCustId.preferences.visibleFeeInfoToParent=="Y"}'>
						<li id="studentPayments">
							<s:url id="urlDoGetChildsFeesLink" action="ajaxGetMyChildFees" namespace="/student" />
							<sj:a href="%{urlDoGetChildsFeesLink}" targets="mainContentDiv" cssClass="ajaxify UP"> Upcoming Payments</sj:a>
						</li>
					</s:if>
					<li>
						<s:url id="urlParentmanageClassAssignment" action="ajaxViewYourStudentAssignment" />
						<sj:a  cssClass="ajaxify PMCA" href="%{urlParentmanageClassAssignment}" targets="mainContentDiv">Manage Class Assignment</sj:a>
					</li>
				</s:if>
				<s:else>
					<li>
						<s:url id="urlStudentLeaveLink" action="ajaxDoGetLeaveDetailsForStudent" namespace="/student" />
						<sj:a  href="%{urlStudentLeaveLink}" targets="mainContentDiv" cssClass="ajaxify LM"> Leave Management</sj:a>
					</li>
					<li>
						<s:url id="urlDoViewEventLink" action="ajaxStudentEvents" namespace="/student" />
						<sj:a  href="%{urlDoViewEventLink}" targets="mainContentDiv" cssClass="ajaxify EVVH">
							Events</sj:a>
					</li>
					<li>
						<s:url id="urlDoViewHolidayLink" action="ajaxViewSchoolSettingsHolidays" namespace="/admin" />
						<sj:a href="%{urlDoViewHolidayLink}" targets="mainContentDiv" cssClass="ajaxify HVPV"> Holidays</sj:a>
					</li>
					<li>
						<s:url id="urlClassMatesLink" action="ajaxGetClassMatesList" namespace="/student" />
						<sj:a href="%{urlClassMatesLink}" targets="mainContentDiv" cssClass="ajaxify CL"> Classmates</sj:a>
					</li>
					<li>
						<s:url id="urlDoGetMyFeeDetailsLink" action="ajaxGetMyChildFees" namespace="/student" />
						<sj:a  href="%{urlDoGetMyFeeDetailsLink}" targets="mainContentDiv" cssClass="ajaxify PR"> Payment & Receipts</sj:a>
					</li>
				</s:else>
				<li>
					<s:url id="urlDoFeebackLink" action="ajaxDoFeedbackLink" namespace="/student" includeParams="all" escapeAmp="false">
						<s:param name="anyTitle">School</s:param>
					</s:url>
					<sj:a href="%{urlDoFeebackLink}" targets="mainContentDiv" cssClass="ajaxify PSFB">Feedback</sj:a>
				</li>
				<li>
					<s:url id="urlViewStudTimeTable" action="ajaxViewStudentTimeTable" namespace="/student" />
					<sj:a href="%{urlViewStudTimeTable}" 	targets="mainContentDiv" cssClass="ajaxify TT">Timetable</sj:a>
				</li>
				<li>
					<s:url id="urlmanageClassAssignment" action="ajaxViewStudentAssignment" namespace="/student" />
					<sj:a href="%{urlmanageClassAssignment}" cssClass='ajaxify SMCA'
						targets="mainContentDiv">Manage Class Assignment</sj:a>
				</li>
			</ul>
			<b class="caret-out"></b>  
		</li>
	  <li >
			<a data-toggle="dropdown" data-hover="dropdown" data-close-others="true" href="" id="HASE">
				<span class="text"></span>
				<i class="fa fa-envelope"></i> Inbox<span class="selected"></span>
				<i class="fa fa-angle-down"></i>     
			</a>
			<ul class="sub-menu">
				<li>
					<s:url id="urlInboxesDetails" action="ajaxDoInboxGetScrapMessagesList" namespace="/common"></s:url>
					<sj:a href="%{urlInboxesDetails}" targets="mainContentDiv" cssClass='ajaxify'> My Inbox</sj:a>
				</li>
				<li>
					<s:url id="urlDashboardDetails" action="ajaxDoGetSchoolWideAlertsList" namespace="/common"> </s:url>
					<sj:a href="%{urlDashboardDetails}" targets="mainContentDiv" cssClass='ajaxify DAM'> Dashboard Messages</sj:a>
				</li>
				<li>
					<s:url id="urlCircularMessagesAlerts" action="ajaxDoGetCircularMessagesList" namespace="/common" />
					<sj:a href="%{urlCircularMessagesAlerts}" targets="mainContentDiv" cssClass='ajaxify ACMIL'>Circular Messages</sj:a>
				</li>
			</ul>
			<b class="caret-out"></b>  
		</li>
		<s:if test='%{user.isSchoolStudent=="Y"}'>
			<li>
				<a
					href="${pageContext.request.contextPath}/library/getStudentLibrayHome.do?id=sLibrary"
					id="sLibrary"> <i class="fa fa-book"></i> <span
					class="title">Library</span> <span class="selected"></span> </a>
			</li>
		</s:if>
		<s:if test='%{user.isSchoolStudent=="Y"}'>
		   <li >
			<a data-toggle="dropdown" data-hover="dropdown" data-close-others="true" href="" id="MKBK">
				<span class="text"></span>
				<i class="fa fa-signal"></i> Manage K-Bank<span class="selected"></span>
				<i class="fa fa-angle-down"></i>     
			</a>
			<ul class="sub-menu" id="dynamicKvideosDiv">
				<li>
					<s:url id="urlMyFavouriteLink" action="ajaxGetKBankFavourites" namespace="/admin" />
					<sj:a href="%{urlMyFavouriteLink}" targets="mainContentDiv" cssClass="ajaxify MKBK">My Favourite</sj:a>
				</li>
				<li>
					<s:url id="urlMyFavouriteLinks" action="ajaxGetKhanPlayList" namespace="/admin" />
					<sj:a href="%{urlMyFavouriteLinks}" targets="mainContentDiv"
						cssClass="ajaxify MKWV">Knowledge Videos</sj:a>
				</li>
			</ul>
			<b class="caret-out"></b>  
		</li>
	</s:if>
	<s:if test='%{user.isSchoolLibrarian == "N" && user.isSecretary == "N"}'>
			<li  id="adminReportsDiv">
			<a data-toggle="dropdown" data-hover="dropdown" data-close-others="true" href="" id="GR">
				<span class="text"></span>
				<i class="fa fa-bar-chart-o"></i> Reports<span class="selected"></span>
				<i class="fa fa-angle-down"></i>     
			</a>
				<ul class="sub-menu">
					<li class="dropdown-submenu"  id="studentReportsDiv">
					<a   href="javascript:;"  id="SID"> <span class="title">Student</span> </a>
					<ul class="sub-menu">
							<li >
								<s:url id="dailyAttendance"
									action="ajaxDoViewClassesAndTodyDate" escapeAmp="false" namespace="/admin" includeParams="all">
									<s:param name="tempString">Student</s:param>
									<s:param name="plTitle">Daily Attendance</s:param>
								</s:url>
								<sj:a href="%{dailyAttendance}" targets="mainContentDiv" cssClass='ajaxify DA'>Attendance Report</sj:a>
							</li>
							<li>
								<s:url id="attendanceForMonthly" namespace="/admin" action="ajaxDoViewClassesAndMonths" escapeAmp="false" includeParams="all">
									<s:param name="tempString">Student</s:param>
									<s:param name="plTitle">Attendance Summary Class Wise(Fully)</s:param>
								</s:url>
								<sj:a href="%{attendanceForMonthly}"
									targets="mainContentDiv" cssClass='ajaxify ASC'>Student Month Wise Attendance</sj:a>
							</li>
							<li>
								<s:url id="importCommunityExcelSheet"
									action="ajaxDoSelectExamSchedules" escapeAmp="false"
									includeParams="all" namespace="/reports">
									<s:param name="tempString">Student</s:param>
									<s:param name="plTitle">Exam Schedules</s:param>
								</s:url>
								<sj:a href="%{importCommunityExcelSheet}"
									targets="mainContentDiv" cssClass='ajaxify ARES'>Exam Schedules</sj:a>
							</li>
							<li>
								<s:url id="classWiseMarks"
									action="ajaxDoViewClassesAndTodyDate" escapeAmp="false"
									namespace="/admin" includeParams="all">
									<s:param name="tempString">Student</s:param>
									<s:param name="plTitle">Class Wise Marks</s:param>
								</s:url>
								<sj:a href="%{classWiseMarks}" targets="mainContentDiv"
									cssClass='ajaxify TCD'>Term Wise Marks</sj:a>
							</li>
							<s:if test='%{user.isSchoolStudent=="Y"}'>
								<li>
									<a
										href="${pageContext.request.contextPath}/reports/ajaxDownloadStudentTimeTableReport.do"
										id="urlDownloadStudTT">Timetable</a>
								</li>
							</s:if>
							<s:if test='%{user.isParent=="Y"}'>
								<li>
									<s:url id="urlDownloadStudsTTable"
										action="ajaxDoDownloadStudentsTimeTableReport"
										escapeAmp="false" includeParams="all"
										namespace="/reports">
									</s:url>
									<sj:a href="%{urlDownloadStudsTTable}"
										targets="mainContentDiv" cssClass='ajaxify TT'>Timetable</sj:a>
								</li>
							</s:if>
						</ul>
						<b class="caret-out"></b>  
					</li>
					  <li class="dropdown-submenu"  id="studentReportsDiv">
						<a tabindex="-1" href="javascript:;"  id="AFC"> <span class="title">Fee</span></a>
						<ul class="sub-menu" id="dynamicFeeCollectionsDiv">
							<li id="allFeeReportsDiv">
								<s:url id="StaffDailyAttendanceInPDF"
									action="ajaxStaffDailyAttendDetails" namespace="/reports"
									includeParams="all" escapeAmp="false">
									<s:param name="tempString">Staff</s:param>
									<s:param name="plTitle">DateWiseFeeCollection</s:param>
								</s:url>
								<sj:a href="%{StaffDailyAttendanceInPDF}"
									targets="mainContentDiv" cssClass='ajaxify DWF'>All Fee Collections</sj:a>
							</li>
						</ul>
						<b class="caret-out"></b>  
					</li>
				</ul>
			</li>
		</s:if>	
</s:if>
<s:if test='%{user.isSchoolManager=="Y"}'>
	<li class="start active">
		<a
			href="${pageContext.request.contextPath}/admin/schoolManagerDashboard.do"
			id="secretary" class="start"> <i class="fa fa-home"></i> <span
			class="title">Manager Dashboard</span> <span class="selected"></span>
		</a>
	</li>
	<li>
		<s:url id="urlmanagerBudgetRequestDetailsLink" action="ajaxManagerBudgetRequestDetails" namespace="/admin" />
		<sj:a href="%{urlmanagerBudgetRequestDetailsLink}" targets="mainContentDiv"
			cssClass='ajaxify BD'>
		Budget Details</sj:a>
	</li>
</s:if>
<s:if test='%{user.isSecretary=="Y" || user.isSecretaryPA=="Y" || user.isSecretary=="Y"}'>
		<s:if test='%{user.isSecretary=="Y"}'>
			<li class="start active">
				<a
					href="${pageContext.request.contextPath}/admin/secretaryDashboard.do?id=secretary"
					id="secretary" class="start"> <i class="fa fa-home"></i> <span
					class="title">Secretary Dashboard</span> <span class="selected"></span>
				</a>
			</li>
		</s:if>
		<s:else>
			<li class="start active">
				<a
					href="${pageContext.request.contextPath}/admin/secretaryPADashboard.do?id=secretary"
					id="secretary" class="start"> <i class="fa fa-home"></i> <span
					class="title">Dashboard</span> <span class="selected"></span>
				</a>
			</li>
			<li>
				<s:url id="urlParticularDetailsHomeLink" action="ajaxParticularDetailsHome" namespace="/admin" />
				<sj:a href="%{urlParticularDetailsHomeLink}" targets="mainContentDiv"
					cssClass='ajaxify PDH'><i class="fa fa-flask"></i>
				Perticular Details<span class="selected"></span></sj:a>
			</li>
			<li>
				<s:url id="urlFinancialYearHomeLink" action="ajaxFinancialYearHome" namespace="/admin" />
				<sj:a href="%{urlFinancialYearHomeLink}" targets="mainContentDiv"
					cssClass='ajaxify FYH'><i class="fa fa-gift"></i><span class="selected"></span>
				Financial Year Details<span class="selected"></span></sj:a>
			</li>
		</s:else>
			<li>
				<s:url id="urlOrganizationMemberDetailsLink" action="ajaxOrganizationMemberDetails" namespace="/masterAdmin" />
				<sj:a href="%{urlOrganizationMemberDetailsLink}" targets="mainContentDiv" cssClass='ajaxify OMD'><i class="fa fa-group"></i> Organization Members<span class="selected"></span></sj:a>
			</li>
			<li >
				<a data-toggle="dropdown" data-hover="dropdown" data-close-others="true" href="" id="SMD">
					<span class="selected"></span>
					<i class="fa fa-laptop"></i> Meetings
					<i class="fa fa-angle-down"></i>     
				</a>
				<ul class="sub-menu">
					<li>
						<s:url id="urlMeetingDetailsLink" action="ajaxMeetingDetailsHome" namespace="/admin" />
						<sj:a href="%{urlMeetingDetailsLink}" targets="mainContentDiv" cssClass='ajaxify SMD'>Meetings Details</sj:a>
					</li>
					<li>
						<s:url id="upcommingMetings" action="ajaxUpcommingMetingsDasboard" namespace="/admin" />
						<sj:a   href="%{upcommingMetings}" targets="mainContentDiv" cssClass="ajaxify PUMD"> Upcoming Meetings</sj:a>
					</li>
					<li>
						<s:url id="budgetRequest" action="ajaxBudgetRequestDashboard" namespace="/admin" />
						<sj:a   href="%{budgetRequest}" targets="mainContentDiv" cssClass="ajaxify PABRD">Budget Accepts/Rejects</sj:a>
					</li>
					<li>
						<s:url id="requestsBudget" action="ajaxDoBudgetRequestDashboard" namespace="/admin"></s:url>
						<sj:a href="%{requestsBudget}" targets="mainContentDiv" cssClass='ajaxify PBRD'>Budget Requests</sj:a>
					</li>
					<li>
						<s:url id="meetingMinu" action="ajaxDoGetMeetingMinutesDashboard" namespace="/admin" />
						<sj:a href="%{meetingMinu}" targets="mainContentDiv" cssClass="ajaxify PMMD"> Meeting Minutes</sj:a> 
					</li>
				 </ul>
			</li>
			<li >
				<a data-toggle="dropdown" data-hover="dropdown" data-close-others="true" href="" id="SCSN">
					<span class="selected"></span>
					<i class="fa fa-gift"></i> School Names
					<i class="fa fa-angle-down"></i>     
				</a>
				<ul class="sub-menu">
					<s:if test="%{#session.OrgcustomersList != null && !#session.OrgcustomersList.isEmpty()}">
					<s:iterator value="#session.OrgcustomersList">
						<li>
							<s:url id="urlMyFavouriteLinks" action="ajaxViewSchoolDetailsHome"
								namespace="/admin" escapeAmp="false" includeParams="all">
								<s:param name="schoolName"><s:property value='organization' /></s:param>
								<s:param name="custId"><s:property value='id' /></s:param>
							</s:url>
							<sj:a href="%{urlMyFavouriteLinks}" targets="mainContentDiv"
								cssClass="ajaxify SVMSC"><s:property value='organization' /></sj:a>
						</li>
					</s:iterator>
				</s:if>
			</ul>
		</li>
	</s:if>
</ul>