<%@ include file="/common/taglibs.jsp"%>
	<div class="header navbar navbar-inverse navbar-fixed-top">
	   <div class="header-inner">
		<s:if test='%{#session.customerStatus == "Y"}'>
		  <a class="brand navbar-brand" href="#"> <img
					src='<s:property  value="#session.custImage"/>' border="0"
					alt="logo" class="logoHeader" id="customerLogoDiv"/> </a>
			<%-- <span class="siteLogo"><a href="#"><s:property value="#session.schoolName" /> </a> </span> --%>
			<a href="javascript:;" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse"> <img src="../img/menu-toggler.png" alt="" /> </a>
			<div class="hor-menu hidden-sm hidden-xs">
				<ul class="nav navbar-nav">
				  <s:if test='%{user.isParent=="Y" || user.isSchoolStudent=="Y"}'>
					<li class="start active">
				  	 <a href="${pageContext.request.contextPath}/student/studentHome.do?id=studentDashboard"
						id="studentDashboard" class="start"> <i class="fa fa-home"></i>
						<span class="title">Dashboard</span> <span class="selected"></span>
					  </a>
					</li>
					<li class="disabled-link">
						<a data-toggle="dropdown" data-hover="dropdown" data-close-others="true" href="" id="ESR">
							<span class="text"></span>
							<i class="fa fa-gift"></i>
							<s:if test='%{user.isParent=="Y"}'> 
								 Class
							</s:if>
							<s:else>
								My Class
							</s:else>
							<span class="selected"></span>
							<i class="fa fa-angle-down"></i>     
						</a>
						<ul class="dropdown-menu">
							<li id="showExamSchedules">
								<s:url id="urlStudentAcademics" action="ajaxManageStudentExamSchedulesAndResults" namespace="/exam" />
								<sj:a id="studentAcedemics" href="%{urlStudentAcademics}" targets="mainContentDiv" cssClass="ajaxify ESR">Exam Schedules & Results</sj:a>
							</li>
							<li>
								<s:url id="urlStudentTimetableLink"
									action="ajaxViewStudentTimetable" namespace="/student" />
								<sj:a id="studentTimetableLink"
									href="%{urlStudentTimetableLink}" targets="mainContentDiv"
									cssClass="ajaxify MT">
									<s:if test='%{user.isParent=="Y"}'> 
										 Timetable
									</s:if>
									<s:else>
										My Timetable
									</s:else>
								</sj:a>
							</li>
							<li>
								<s:url id="urlStudentAttendanceLink"
									action="ajaxStudentAttendance" namespace="/student" />
								<sj:a id="studentAttendanceLink"
									href="%{urlStudentAttendanceLink}" targets="mainContentDiv"
									cssClass="ajaxify AC">
									Attendance</sj:a>
							</li>
							<li id="performanceDiv">
								<s:url id="urlPeDetails" action="ajaxViewMyRecommendations" namespace="/student" />
								<sj:a id="urlPeDetail" href="%{urlPeDetails}" targets="mainContentDiv" cssClass="ajaxify PE">Performance Evaluation</sj:a>
							</li>
							<li id="performanceAttDiv">
								<s:url id="urlAttendance" action="ajaxViewMyAttendanceGraph" namespace="/student" />
								<sj:a id="urlAttendanceDiv" href="%{urlAttendance}" targets="mainContentDiv" cssClass="ajaxify PCA">Performance Attendance</sj:a>
							</li>
							<s:if test='%{user.isParent=="Y"}'>
								<li>
									<s:url id="urlParentLeaveLink"
										action="ajaxDoGetStudentLeaveDetails" namespace="/student" />
									<sj:a id="leaveLink" href="%{urlParentLeaveLink}"
										targets="mainContentDiv" cssClass="ajaxify LM">
										Leave Management</sj:a>
								</li>
								<li>
									<s:url id="urlParentOnlineAppointment"
										action="ajaxViewOnlineAppointment" namespace="/student" />
									<sj:a  href="%{urlParentOnlineAppointment}"
										targets="mainContentDiv" cssClass="ajaxify OAL">
										Online Appointment</sj:a>
								</li>
								<%-- <li>
									<s:url id="urlChildClassMatesLink" action="ajaxGetMyChildrenClassMatesList" namespace="/student" />
									<sj:a id="urlChildClassMatesLink" href="%{urlChildClassMatesLink}" targets="mainContentDiv" cssClass="ajaxify CL"> Classmates</sj:a>
								</li> --%>
								
								<li>
									<s:url id="urlDoParentHolidayLink"
										action="ajaxViewSchoolSettingsHolidays" namespace="/admin" />
									<sj:a id="parentHolidayLink"
										href="%{urlDoParentHolidayLink}"
										targets="mainContentDiv" cssClass="ajaxify HVP">
										Holidays</sj:a>
								</li>
								<s:if test='%{customerByCustId.preferences.visibleFeeInfoToParent=="Y"}'>
									<li id="studentPayments">
										<s:url id="urlDoGetChildsFeesLink" action="ajaxGetMyChildFees"
											namespace="/student" />
										<sj:a id="doGetChildsDetailsLink"
											href="%{urlDoGetChildsFeesLink}" targets="mainContentDiv"
											cssClass="ajaxify UP">
											Upcoming Payments</sj:a>
									</li>
								</s:if>
								<li>
									<s:url id="urlParentmanageClassAssignment"
										action="ajaxViewYourStudentAssignment" />
									<sj:a id="urlParentmanageClassAssignment"
										cssClass="ajaxify PMCA"
										href="%{urlParentmanageClassAssignment}"
										targets="mainContentDiv">
										<s:if test='%{user.isParent=="Y"}'> 
											 Class Assignment
										</s:if>
										<s:else>
											Manage Class Assignment
										</s:else>
									</sj:a>
								</li>
								<s:if test='%{customerByCustId.parentPermissionStatus=="Y"}'>
									<li>
										<s:url id="urlPermissions" action="ajaxViewParentPermissions"  namespace="/student"/>
										<sj:a id="urlPermissions" cssClass="ajaxify PPFR"
											href="%{urlPermissions}" targets="mainContentDiv">Permission Request</sj:a>
									</li>
								</s:if>
							</s:if>
							<s:else>
								<li>
									<s:url id="urlStudentLeaveLink"
										action="ajaxDoGetLeaveDetailsForStudent" namespace="/student" />
									<sj:a id="leaveLink" href="%{urlStudentLeaveLink}"
										targets="mainContentDiv" cssClass="ajaxify LM">
										Leave Management</sj:a>
								</li>
								<%-- <li>
									<s:url id="urlDoViewEventLink"
										action="ajaxStudentEvents" namespace="/student" />
									<sj:a id="registerViewEventLink"
										href="%{urlDoViewEventLink}"
										targets="mainContentDiv" cssClass="ajaxify EVVH">
										Events</sj:a>
								</li> --%>
								
								<%-- <li>
									<s:url id="urlEventsLink" action="ajaxStudentEvents"
										namespace="/student"></s:url>
									<sj:a href="%{urlEventsLink}" targets="mainContentDiv"
										cssClass="ajaxify EVNT">
										Events</sj:a>
								</li> --%>
								
								<li>
									<s:url id="urlDoViewHolidayLink"
										action="ajaxViewSchoolSettingsHolidays" namespace="/admin" />
									<sj:a id="viewHolidayLink"
										href="%{urlDoViewHolidayLink}"
										targets="mainContentDiv" cssClass="ajaxify HVPV">
										Holidays</sj:a>
								</li>
								<li>
									<s:url id="urlClassMatesLink" action="ajaxGetClassMatesList"
										namespace="/student" />
									<sj:a id="classMatesLink" href="%{urlClassMatesLink}"
										targets="mainContentDiv" cssClass="ajaxify CL">
										Classmates</sj:a>
								</li>
								<s:if test='%{customerByCustId.preferences.visibleFeeInfoToParent=="Y"}'>
									<li>
										<s:url id="urlDoGetMyFeeDetailsLink"
											action="ajaxGetMyChildFees" namespace="/student" />
										<sj:a id="doGetMyFeeDetailsLink"
											href="%{urlDoGetMyFeeDetailsLink}" targets="mainContentDiv"
											cssClass="ajaxify PR">
											Payment & Receipts</sj:a>
									</li>
								</s:if>
								<li>
								<s:url id="urlmanageClassAssignment"
									action="ajaxViewStudentAssignment" namespace="/student" />
								<sj:a id="manageClassAssignment"
									href="%{urlmanageClassAssignment}" cssClass='ajaxify SMCA'
									targets="mainContentDiv">Manage Class Assignment</sj:a>
							</li>
							</s:else>
							<%-- <li>
								<s:url id="urlDoFeebackLink" action="ajaxDoFeedbackLink"
									namespace="/student" includeParams="all" escapeAmp="false">
									<s:param name="anyTitle">School</s:param>
								</s:url>
								<sj:a id="urlDoFeebackLink" href="%{urlDoFeebackLink}"
									targets="mainContentDiv" cssClass="ajaxify PSFB">Feedback</sj:a>
							</li> --%>
							<li>
								<s:url id="urlViewStudTimeTable" action="ajaxViewStudentTimeTable" namespace="/student" />
								<sj:a id="timeTableUrlLink" href="%{urlViewStudTimeTable}" 	targets="mainContentDiv" cssClass="ajaxify TT">Timetable</sj:a>
							</li>
							<li>
								<s:url id="urlCommunicationDetailsLink" action="ajaxAddressToCommunication" namespace="/student" />
								<sj:a href="%{urlCommunicationDetailsLink}" targets="mainContentDiv" cssClass="ajaxify PRC">
									Address to Communication</sj:a>
							</li>
							<li>
								<s:url id="viewVideosurl" action="ajaxDoViewVideos" namespace="/common"></s:url>
								<sj:a href="%{viewVideosurl}" targets="mainContentDiv" cssClass='ajaxify PVVEO'> View Videos</sj:a>
							</li>
						</ul>
						<b class="caret-out"></b>  
					</li>
					<s:if test='%{user.isSchoolStudent=="Y"}'>
					
					<li class="disabled-link">
						<a data-toggle="dropdown" data-hover="dropdown" data-close-others="true" href="" id="USTM">
							<span class="text"></span>
							<i class="fa fa-envelope"></i> Study Materials<span class="selected"></span>
							<i class="fa fa-angle-down"></i>     
						</a>
						<ul class="dropdown-menu">
							<li>
								<s:url id="urlstudyMaterial" action="ajaxViewStudyClassMaterialList" namespace="/admin"></s:url>
								<sj:a id="studyMaterialLink" href="%{urlstudyMaterial}" targets="mainContentDiv" cssClass='ajaxify'> Study Materials</sj:a>
							</li>
							
						</ul>
						<b class="caret-out"></b>  
					</li>
					<li class="disabled-link">
						<a data-toggle="dropdown" data-hover="dropdown" data-close-others="true" href="" id="UTM">
							<span class="text"></span>
							<i class="fa fa-envelope"></i> Question Bank<span class="selected"></span>
							<i class="fa fa-angle-down"></i>     
						</a>
						<ul class="dropdown-menu">
							<li >
								<s:url id="urlviewQuestionBankMaterials" action="ajaxViewQuestionBankMaterialList" namespace="/exam"></s:url>
								<sj:a id="viewQuestionBankMaterials" href="%{urlviewQuestionBankMaterials}" targets="mainContentDiv" cssClass='ajaxify'>Question Bank</sj:a> 	
						 	</li>
						</ul>
						<b class="caret-out"></b>  
					</li>
						<%-- <li>
							<a
								href="${pageContext.request.contextPath}/admin/viewStaffStudyClassMaterialList.do?id=studyMaterial"
								id="studyMaterial"> <i class="fa fa-book"></i> <span
								class="title">Study Materials</span> <span class="selected"></span> </a>
						</li> --%>
					</s:if>
				  <li class="disabled-link">
						<a data-toggle="dropdown" data-hover="dropdown" data-close-others="true" href="" id="HASE">
							<span class="text"></span>
							<i class="fa fa-envelope"></i> Inbox<span class="selected"></span>
							<i class="fa fa-angle-down"></i>     
						</a>
						<ul class="dropdown-menu">
							<li>
								<s:url id="urlInboxesDetails" action="ajaxDoInboxGetScrapMessagesList" namespace="/common"></s:url>
								<sj:a id="inboxLink" href="%{urlInboxesDetails}" targets="mainContentDiv" cssClass='ajaxify'> My Inbox</sj:a>
							</li>
							<li>
								<s:url id="urlDashboardDetails"
									action="ajaxDoGetSchoolWideAlertsList" namespace="/common">
								</s:url>
								<sj:a href="%{urlDashboardDetails}" targets="mainContentDiv"
									cssClass='ajaxify'>
									Dashboard Messages</sj:a>
							</li>
							<li>
								<s:url id="urlCircularMessagesAlerts" action="ajaxDoGetCircularMessagesList" namespace="/common" />
								<sj:a href="%{urlCircularMessagesAlerts}" targets="mainContentDiv"
									cssClass='ajaxify ACMIL'>Circular Messages</sj:a>
							</li>
						</ul>
						<b class="caret-out"></b>  
					</li>
					<s:if test='%{user.isSchoolStudent=="Y" || user.isParent=="Y"}'>
						<li>
							<a
								href="${pageContext.request.contextPath}/library/getStudentLibrayHome.do?id=sLibrary"
								id="sLibrary"> <i class="fa fa-book"></i> <span
								class="title">Library</span> <span class="selected"></span> </a>
						</li>
					</s:if>
					<%-- <s:if test='%{user.isSchoolStudent=="Y"}'>
					   <li class="disabled-link">
						<a data-toggle="dropdown" data-hover="dropdown" data-close-others="true" href="" id="MKBK">
							<span class="text"></span>
							<i class="fa fa-signal"></i> Manage K-Bank<span class="selected"></span>
							<i class="fa fa-angle-down"></i>     
						</a>
						<ul class="dropdown-menu" id="dynamicKvideosDiv">
							<li>
								<s:url id="urlMyFavouriteLink" action="ajaxGetKBankFavourites"
									namespace="/admin" />
								<sj:a href="%{urlMyFavouriteLink}" targets="mainContentDiv"
									cssClass="ajaxify MKBK">My Favourite</sj:a>
							</li>
							<li>
								<s:url id="urlMyFavouriteLinks" action="ajaxGetKhanPlayList" namespace="/admin" />
								<sj:a href="%{urlMyFavouriteLinks}" targets="mainContentDiv"
									cssClass="ajaxify MKWV">Knowledge Videos</sj:a>
							</li>
						</ul>
						<b class="caret-out"></b>  
					</li>
				</s:if>  --%>
				<s:if test='%{user.isSchoolStudent=="Y" || user.isParent=="Y"}'>
					<li class="disabled-link" id="adminReportsDiv">
						<a data-toggle="dropdown" data-hover="dropdown" data-close-others="true" href="" id="ST" > 
							<i class="fa fa-dribbble"></i> <span class="text"></span>
							Sports<span class="selected"></span> <i class="fa fa-angle-down"></i>
						</a> 
						<ul class="dropdown-menu">
							<li class="disabled-link">
								<s:url id="urlManageTournament" action="ajaxTournamentInformationHome"	namespace="/sports" />
								<sj:a id="urlManageTournament" href="%{urlManageTournament}" targets="mainContentDiv" cssClass="ajaxify MSPT">
								Tournament</sj:a>
							</li>
							<li>
								<s:url id="urlSportsAchievements" action="ajaxSportAchievementInfoHome"  namespace="/sports" />
								<sj:a id="urlSportsAchievements" href="%{urlSportsAchievements}" targets="mainContentDiv" cssClass="ajaxify SPA">
								Achievements</sj:a>
							</li>
						</ul>
					</li>
					<li class="disabled-link" id="parentEventId">
						<s:url id="urlDoRegisterStudentEventLink"	action="ajaxStudentEvents" namespace="/student" />
							<sj:a id="registerStudentEventLink"	href="%{urlDoRegisterStudentEventLink}"	targets="mainContentDiv" cssClass="ajaxify EV">
								<i class="fa fa-music"></i>		
									Events
							</sj:a>
					</li>
				</s:if>
				<s:if test='%{user.isSchoolLibrarian == "N" && user.isSecretary == "N"}'>
						<li class="disabled-link" id="adminReportsDiv">
						<a data-toggle="dropdown" data-hover="dropdown" data-close-others="true" href="" id="GR">
							<span class="text"></span>
							<i class="fa fa-bar-chart-o"></i> Reports<span class="selected"></span>
							<i class="fa fa-angle-down"></i>     
						</a>
							<ul class="dropdown-menu">
								<li class="dropdown-submenu"  id="studentReportsDiv">
								<a   href="javascript:;"  id="SID"> <span class="title">Student</span> </a>
								<ul class="dropdown-menu">
										<li class="disabled-link">
											<s:url id="dailyAttendance"
												action="ajaxDoViewClassesAndTodyDate" escapeAmp="false" namespace="/admin" includeParams="all">
												<s:param name="tempString">Student</s:param>
												<s:param name="plTitle">Daily Attendance</s:param>
											</s:url>
											<sj:a id="atteReport" href="%{dailyAttendance}" targets="mainContentDiv" cssClass='ajaxify DA'>Attendance Report</sj:a>
										</li>
										<s:if test='%{currentAcademicYear.manageAttendanceBy=="M"}'>
										<li>
											<s:url id="attendanceForMonthly" namespace="/admin"
												action="ajaxDoViewClassesAndMonths" escapeAmp="false"
												includeParams="all">
												<s:param name="tempString">Student</s:param>
												<s:param name="plTitle">Attendance Summary Class Wise(Fully)</s:param>
											</s:url>
											<sj:a href="%{attendanceForMonthly}"
												targets="mainContentDiv" cssClass='ajaxify ASC'>Student Month Wise Attendance</sj:a>
										</li>
										</s:if>
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
										<%--<s:if test='%{user.isSchoolStudent=="Y"}'>
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
										</s:if> --%>
									</ul>
									<b class="caret-out"></b>  
								</li>
								  <%-- <li class="dropdown-submenu"  id="studentReportsDiv">
									<a tabindex="-1" href="javascript:;"  id="AFC"> <span class="title">Fee</span></a>
									<ul class="dropdown-menu" id="dynamicFeeCollectionsDiv">
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
								</li> --%>
							</ul>
						</li>
					</s:if>	
			</s:if>
			<s:if test='%{user.isSchoolManager=="Y"}'>
				<li class="start active">
					<a
						href="${pageContext.request.contextPath}/admin/schoolManagerDashboard.do"
						id="secretary" class="start"> <i class="fa fa-home"></i> <span
						class="title">Dashboard</span> <span class="selected"></span>
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
								class="title">Dashboard</span> <span class="selected"></span>
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
						<li class="disabled-link">
							<a data-toggle="dropdown" data-hover="dropdown" data-close-others="true" href="" id="PDH">
								<span class="selected"></span>
								<i class="fa fa-flask"></i>Settings
								<i class="fa fa-angle-down"></i>     
							</a>
							<ul class="dropdown-menu">
								<li>
									<s:url id="urlParticularDetailsHomeLink" action="ajaxParticularDetailsHome" namespace="/admin" />
									<sj:a href="%{urlParticularDetailsHomeLink}" targets="mainContentDiv"
										cssClass='ajaxify PDH'>Perticular Details<span class="selected"></span></sj:a>
								</li>
								<li>
									<s:url id="urlFinancialYearHomeLink" action="ajaxFinancialYearHome" namespace="/admin" />
									<sj:a href="%{urlFinancialYearHomeLink}" targets="mainContentDiv"
									cssClass='ajaxify FYH'><i class="fa fa-gift"></i><span class="selected"></span>
									Financial Year Details<span class="selected"></span></sj:a>
								</li>
							</ul>
					    </li>
					</s:else>
						<li>
							<s:url id="urlOrganizationMemberDetailsLink" action="ajaxOrganizationMemberDetails" namespace="/masterAdmin" />
							<sj:a href="%{urlOrganizationMemberDetailsLink}" targets="mainContentDiv"
								cssClass='ajaxify OMD'><i class="fa fa-group"></i>
							Organization<span class="selected"></span></sj:a>
						</li>
						<li class="disabled-link">
							<a data-toggle="dropdown" data-hover="dropdown" data-close-others="true" href="" id="SMD">
								<span class="selected"></span>
								<i class="fa fa-laptop"></i> Meetings
								<i class="fa fa-angle-down"></i>     
							</a>
							<ul class="dropdown-menu">
								<li>
									<s:url id="urlMeetingDetailsLink" action="ajaxMeetingDetailsHome" namespace="/admin" />
									<sj:a  id="meetingDetailsLinkDiv" href="%{urlMeetingDetailsLink}" targets="mainContentDiv" cssClass='ajaxify SMD'>Meetings Details</sj:a>
								</li>
								<li>
									<s:url id="upcommingMetings" action="ajaxUpcommingMetingsDasboard" namespace="/admin" />
									<sj:a  id="urlupcommingMetingsDiv" href="%{upcommingMetings}" targets="mainContentDiv" cssClass="ajaxify PUMD"> Upcoming Meetings</sj:a>
								</li>
								<li>
									<s:url id="meetingMinu" action="ajaxDoGetMeetingMinutesDashboard" namespace="/admin" />
									<sj:a id="urlmeetingMinuDiv" href="%{meetingMinu}" targets="mainContentDiv" cssClass="ajaxify PMMD"> Meeting Minutes</sj:a> 
								</li>
							 </ul>
						</li>
						<li class="disabled-link">
							<a data-toggle="dropdown" data-hover="dropdown" data-close-others="true" href="" id="SMDB">
								<span class="selected"></span>
								<i class="fa fa-sitemap"></i> Budgets
								<i class="fa fa-angle-down"></i>     
							</a>
							<ul class="dropdown-menu">
								<li>
									<s:url id="budgetRequest" action="ajaxBudgetRequestDashboard" namespace="/admin" />
									<sj:a  id="urlbudgetRequestDiv" href="%{budgetRequest}" targets="mainContentDiv" cssClass="ajaxify SMDB">Budget Accepts/Rejects</sj:a>
								</li>
								<li>
									<s:url id="requestsBudget" action="ajaxDoBudgetRequestDashboard" namespace="/admin"></s:url>
									<sj:a id="urlrequestsBudgetDiv" href="%{requestsBudget}" targets="mainContentDiv" cssClass='ajaxify PBRD'>Budget Requests</sj:a>
								</li>
							 </ul>
						</li>
						<li class="disabled-link">
							<a data-toggle="dropdown" data-hover="dropdown" data-close-others="true" href="" id="SCSN">
								<span class="selected"></span>
								<i class="fa fa-gift"></i> School Names
								<i class="fa fa-angle-down"></i>     
							</a>
							<ul class="dropdown-menu">
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
			</div>
			
			<ul class="nav navbar-nav pull-right" style="margin-top: 5px;">
				<s:if
					test="%{#session.academicYearList != null && !#session.academicYearList.isEmpty()}">
					<s:if test="%{#session.academicYearList .size > 1}">
						<li>
							<span
								style="color: #fff;  position: inherit;">
								<b>Academic Year :</b> </span>
							<div class="themes">
								<a class="selected academicYearsId" href="javascript:;"></a>
								<ul id="themes_selector" style="display: none;">
									<form name="myform" action="${pageContext.request.contextPath}/subscription/userAccess.do">
										<input type="hidden" name="academicYearId" id="acedemicId"
											value='' />
										<input type="hidden" name="academicYearName"
											id="academicYearName" value='' />
										<input type="hidden" name="requestURL"
											value="<%=request.getRequestURL()%>" />
										<input type="hidden" id="currentSelectedAcademicYear"
											value='<s:property value="#session.academicYear" />' />
										<s:iterator value="#session.academicYearList">
											<li onclick="javascript:getAcademicYearView(this);"
												id="<s:property value='id' />"
												class="<s:property value='academicYear' />">
												<s:property value="academicYear" />
											</li>
										</s:iterator>
									</form>
								</ul>
							</div>
						</li>
					</s:if>
				</s:if>
				<s:if test="%{#session.vwusersList != null && !#session.vwusersList.isEmpty()}">
				<s:if test="%{#session.vwusersList .size > 1}">
				<li>
							<span
								style="color: #fff;  position: inherit;">
								<b>Select Student :</b> </span>
							<div class="themes">
								<a class="studentId" href="javascript:;"></a>
								<ul id="themes_selector_student" style="display: none;">
								<form name="studentMyform" action="${pageContext.request.contextPath}/subscription/userAccess.do">
									<input type="hidden" name="selectedStudentId" id="selectedStudentId"
										value='' />
									<input type="hidden" name="studentName"
										id="studentName" value='' />
										<input type="hidden" name="passwordHint" id="passwordHint" value='PW' />
									<input type="hidden" id="currentSelectedStudent"
										value='<s:property value="#session.selectedStudentId" />' />
									<s:iterator value="#session.vwusersList">
										<li onclick="javascript:getStudentView(this);"
											id="<s:property value='accountId' />"
											class="<s:property value='fullPersonName' />">
											<s:property value="fullPersonName" /> 
										</li>
									</s:iterator>
								</form>
							</ul>
						</div>
					</li>
				</s:if>
			</s:if>
				<li class="dropdown user">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true" style="padding-top: 7px;"> 
							<s:if test="%{user.profileImage.adjustedAttachmentFilePath != null &&  user.profileImage.adjustedAttachmentFilePath != ''}">
								<img src='<c:url value="${user.profileImage.adjustedAttachmentFilePath}"/>' border="0" style="float: left; margin-right: 5px; height: 60px; width: 70px;" id="userProfileImageDiv"/>
							</s:if> 
							<s:elseif
								test="%{#session.studentImg != null &&  #session.studentImg != ''}">
								  <img src='<s:property  value="#session.studentImg"/>' border="0" style="float: left; margin-right: 5px; height: 60px; width: 70px;" id="studentProfileImageDiv"/> 
							</s:elseif> 
							<s:else>
								<img src="../img/avatar.png" id="profileImagesDiv" border="0" style="float: left; margin-right: 5px; height: 60px; width: 70px;">
							</s:else> 
							<s:if test='%{user.isSchoolManager=="Y" || user.isSecretary=="Y" || user.isSecretaryPA=="Y"}'>
								<p class="username hidden-480" style="white-space: normal;width: 175px;"> &nbsp;&nbsp;<s:property value="user.person.firstName"/>
									  <i class="fa fa-angle-down"></i>
							    </p> 
							</s:if>
							<s:else>
								<p class="username hidden-480" style="white-space: normal;width: 175px;"> &nbsp;&nbsp;<s:property value="#session.firstName" />
									 <s:if test='%{user.isSchoolStudent=="Y"}'> 
									 	<small>(<s:property value="#session.studentClassSection" />)</small>
									  </s:if> 
									  <i class="fa fa-angle-down"></i>
							    </p> 
						    </s:else>
					     </a>
						<ul class="dropdown-menu">
						<li id="profileLink">
							<s:url id="signupStep1" action="../user/ajaxDoEditProfile" includeParams="all"></s:url>
							<sj:a href="%{signupStep1}" targets="mainContentDiv">
								<i class="fa fa-user"></i>My Profile 
							</sj:a>
						</li>
						<li>
							<a href="javascript:;" id="trigger_fullscreen"><i
								class="icon-move"></i> Full Screen</a>
						</li>
						<li>
							<a href="<c:url value="/j_spring_security_logout"/>"><i
								class="icon-key"></i>Logout</a>
						</li>
					</ul>
				</li>
			</ul>
			<s:if test='%{#session.dateExceeded =="Y"}'>	
	 			<a id="showEndDate" class="warningResponse" href="#responsiveEndDate" data-toggle="modal">
	 			<jsp:include page="/WEB-INF/pages/admin/ajaxShowSchoolEndDate.jsp" />
		 </s:if> 	
		 <s:if test='%{#session.passwordHint != "PW"}'>
			 <s:if test='%{#session.passwordStatus}'>
					<a id="showChangePassword" class="response" href="#responsivePassword" data-toggle="modal">
					 <jsp:include page="/WEB-INF/pages/user/ajaxChangeParentPassword.jsp" />
					</a>
			  </s:if>
		 </s:if>
		<s:if
			test="%{(#session.customerVision !=null && !#session.customerVision.isEmpty()) || (#session.customerMission !=null && !#session.customerMission.isEmpty())}">
			<a id="showVisionMission" class="response" href="#responsiveVision" data-toggle="modal"> <jsp:include
					page="/WEB-INF/pages/admin/ajaxShowCustomerVissionAndMission.jsp" />
			</a>
		</s:if>
			<!-- END TOP NAVIGATION MENU -->
	  </s:if>
	  </div>
		<!-- END TOP NAVIGATION BAR -->
	</div>
	<div class="container">
	<div class="page-container">
		<div class="page-sidebar navbar-collapse collapse">
		<!--  below page used for student and parent hearders --- HORIZONTAL AND SIDEBAR MENU FOR MOBILE & TABLETS-->
		 	<jsp:include page="/common/tabletAndMobileHeaderPage.jsp" />
		</div>
	</div>
	<script>
	$('a.studentId').html($('ul#themes_selector_student').find('li#'+ $('input#currentSelectedStudent').val()).attr('class'));
	function getStudentView(liObj) {
		$("#selectedStudentId").val($(liObj).attr('id'));
		var studentName = $(liObj).html().trim();
		if (isNonEmpty(studentName)) {
			$("#studentName").val(studentName);
		}
		document.studentMyform.submit();
	}
	
	</script>
	
