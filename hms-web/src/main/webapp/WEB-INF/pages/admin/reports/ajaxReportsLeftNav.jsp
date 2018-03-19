<%@ include file="/common/taglibs.jsp"%>
<head>
	<title>SMS | School Library Details</title>
</head>
<body />
	<div class="wrapper container_18">
		<!-- wrapper begins -->
		<div class="wrapper">
			<div class="grid_18 block grid_18MarginLeft">
				<div class="grid_6 alpha">
					<div class="grid_6 alpha">
						<div class="block_head">
							<h2>
								Reports
							</h2>
						</div>
						<div class="block_content" id="sideMenu">
							<ul>
							<s:if  test='%{user.isMasterAdmin == "Y"}'>
								<li>
									<a href="${pageContext.request.contextPath}/reports/ajaxDoCustomerSmsWiseReportDetails.do?pdfId=pdf" id="masterAdmin" >Customer wise SMS count Report</a>
								</li>
							</s:if>
		                <s:else>
								<s:if
									test='%{(user.isOnlySchoolAdmin=="Y") || user.isHostelFinance=="Y" || user.isSchoolHostel=="Y" || user.isSchoolPrincipal=="Y" || user.isSchoolDirector == "Y"}'>
									<li>
										<a href="#" id="Admin">Admin</a>
									</li>
								</s:if>
								<s:if
									    test='%{(user.isOnlySchoolAdmin=="Y") || user.isParent=="Y" || user.isSchoolPrincipal=="Y" || user.isSchoolDirector == "Y" || user.isMasterAdmin=="Y" || user.isOnlySchoolHod=="Y" || user.isOnlySchoolTeacher=="Y" || user.isSchoolHostel=="Y" || user.isSchoolStudent=="Y" || user.isHostelFinance=="Y" || user.isSchoolTransport=="Y"}'>
								<li>
									<a href="#" id="Student">Student</a>
								</li>
								</s:if>
								<s:if
									    test='%{(user.isOnlySchoolAdmin=="Y") || user.isSchoolPrincipal=="Y" || user.isSchoolDirector == "Y" || user.isMasterAdmin=="Y" || user.isSchoolHostel=="Y" || user.isHostelFinance=="Y"}'>
								<li>
									<a href="#" id="Staff">Staff</a>
								</li>
								</s:if>
								<s:if
									test='%{(user.isOnlySchoolAdmin=="Y") || user.isHostelFinance=="Y" || user.isSchoolFinance=="Y" || user.isSchoolHostel=="Y" || user.isSchoolPrincipal=="Y" || user.isSchoolDirector == "Y" || user.isSchoolTransport=="Y" || user.isTransportFinance=="Y"}'>
								<li>
								<a href="#" id="Finance" >Fee Collections</a>
								</li>
								</s:if>
								<s:if test='%{(user.isOnlySchoolAdmin=="Y") || user.isHostelFinance=="Y" || user.isSchoolFinance=="Y" || user.isSchoolHostel=="Y" || user.isSchoolPrincipal=="Y" || user.isSchoolDirector == "Y" || user.isSchoolTransport=="Y" || user.isTransportFinance=="Y"}'>
									<li>
										<a href="#" id="FineFinance" >Fine Fee Collections</a>
									</li>
								</s:if>
						</s:else>
								 
							</ul>
						</div>
					</div>
					<div class="grid_6 alpha">
						&nbsp;
					</div>
					<s:if
						   test='%{(user.isOnlySchoolAdmin=="Y") || user.isHostelFinance=="Y" || user.isSchoolHostel=="Y" || user.isSchoolPrincipal=="Y" || user.isSchoolDirector == "Y" || user.isParent=="Y"}'>
					<div class="grid_6 alpha" id="AdminView" style="display: none;">
						<div class="block_head">
							<h2>
								Admin Reports
							</h2>
						</div>
						<div class="block_content" id="sideSubMenu"
							>
							<ul>
							 	<s:if
									test='%{(user.isOnlySchoolAdmin=="Y") || user.isHostelFinance=="Y" || user.isSchoolHostel=="Y" || user.isSchoolPrincipal=="Y" || user.isSchoolDirector == "Y"}'>
									<li>
										<s:url id="importStudCommunityExcelSheet"
											action="ajaxDoReligionWiseDetails" escapeAmp="false"
											includeParams="all" namespace="/reports">
											<s:param name="tempString">Student</s:param>
											<s:param name="plTitle">CommunityDetails</s:param>
										</s:url>
										<sj:a href="%{importStudCommunityExcelSheet}"
											targets="reportsList">Student Community Summary</sj:a>
									</li>
								<%-- 	<li id="studentDetails">
										<s:url id="importClassWiseStudentExcelSheet"
											action="ajaxDoClassWiseStudentDetails" escapeAmp="false"
											includeParams="all" namespace="/reports">
											<s:param name="tempString">Student</s:param>
											<s:param name="plTitle">ClassWiseStudentDetails</s:param>
										</s:url>
										<sj:a href="%{importClassWiseStudentExcelSheet}"
											targets="reportsList">Class Wise Student Details</sj:a>
									</li> --%>
									<li>
										<s:url id="importStudentReligion"
											action="ajaxDoReligionWiseDetails" namespace="/reports"
											includeParams="all" escapeAmp="false">
											<s:param name="tempString">Student</s:param>
											<s:param name="plTitle">ReligionDetails</s:param>
										</s:url>
										<sj:a href="%{importStudentReligion}" targets="reportsList">Student Religion Wise Summary</sj:a>
									</li>
							<%-- 		<li  id="mediumWiseDetails">
										<s:url id="importStudentReligion"
											action="ajaxDoReligionWiseDetails" includeParams="all"
											escapeAmp="false" namespace="/reports">
											<s:param name="tempString">Student</s:param>
											<s:param name="plTitle">MediumWiseDetails</s:param>
										</s:url>
										<sj:a href="%{importStudentReligion}" targets="reportsList">Medium Wise Summary</sj:a>
									</li> --%>
									<li>
										<s:url id="StudentsWithAllClasses"
											action="ajaxDoComunityDetails" namespace="/reports"
											escapeAmp="false" includeParams="all">
											<s:param name="plTitle">ClassWiseCommunityDetails</s:param>
											<s:param name="tempString">Student</s:param>
										</s:url>
										<sj:a href="%{StudentsWithAllClasses}" targets="reportsList">
										Class Wise Community Details
									</sj:a>
									</li>
									<s:if test='%{(user.isOnlySchoolAdmin=="Y") || user.isSchoolPrincipal=="Y" || user.isSchoolDirector == "Y"}'>
									<li>
										<s:url id="downloadMarksSheet"
											action="ajaxDoViewDownloadMarksSheets" namespace="/admin"/>
										<sj:a href="%{downloadMarksSheet}" targets="reportsList">
										Students Promotion report
									</sj:a>
									</li>
									<li>
										<s:url id="importStudentReligion" namespace="/reports"
											action="ajaxDoSelectStudyClasses" includeParams="all"
											escapeAmp="false">
											<s:param name="tempString">Student</s:param>
											<s:param name="plTitle">GenderwiseDetails</s:param>
										</s:url>
										<sj:a href="%{importStudentReligion}" targets="reportsList">Student Strength Report</sj:a>
									</li>
								<%-- 	<li>
										<s:url id="viewStudentCommunityClass" namespace="/reports"
											action="ajaxDoSelectStudyClasses" includeParams="all"
											escapeAmp="false">
											<s:param name="tempString">Student</s:param>
											<s:param name="plTitle">ViewStudentClassSectionDetails</s:param>
										</s:url>
										<sj:a href="%{viewStudentCommunityClass}" targets="reportsList">Manage Students Register Details</sj:a>
									</li> --%>
									<!--<li>
										<s:url id="importStudentReligion"
											action="ajaxDoViewDownloadSheets" includeParams="all"
											escapeAmp="false">
											<s:param name="tempString">Student</s:param>
											<s:param name="plTitle">TCGeneration</s:param>
										</s:url>
										<sj:a href="%{importStudentReligion}" targets="reportsList">TC Generation By Class Wise
									</sj:a>
									</li>
									--><!--<li>
										<s:url id="importStudentReligion"
											action="ajaxDoViewDownloadSheets" includeParams="all"
											escapeAmp="false">
											<s:param name="tempString">Student</s:param>
											<s:param name="plTitle">marksCardGeneration</s:param>
										</s:url>
										<sj:a href="%{importStudentReligion}" targets="reportsList">Marks Card Generation</sj:a>
									</li>
									
									<li>
										<s:url id="urlDownloadAttTemplate" action="ajaxDoDownloadStudAttendanceTemplate" namespace="/reports">
											</s:url>
										<sj:a id="downloadAttendanceTemplate"
											href="%{urlDownloadAttTemplate}" targets="reportsList"
											indicator="indicator">
											Manage Student Attendance 
									</sj:a>
									</li>-->
									<li>
										<s:url id="urlDownloadClsTimeTable" action="ajaxDoDownloadTimeTableReport" namespace="/reports">
											</s:url>
										<sj:a id="downloadClasTimeTable"
											href="%{urlDownloadClsTimeTable}" targets="reportsList"
											indicator="indicator">
											Timetable 
									</sj:a>
									</li>
									<li>
										<s:url id="urlDownloadStaffDetails" action="ajaxDoViewStaffRoles" namespace="/reports" />
										<sj:a id="staffDetails"
											href="%{urlDownloadStaffDetails}" targets="reportsList"
											indicator="indicator">
											Role Wise Staff Details 
									</sj:a>
									</li>
									</s:if>
								</s:if>
								<s:if test='%{(user.isOnlySchoolAdmin=="Y") }'>
								 <s:if test='%{(customer.transportModuleStatus ==true) }'>
									<li>
										<s:url id="urlDoViewMaintenanceMonths"
											action="ajaxDoViewMaintenanceMonths" namespace="/reports" />
										<sj:a id="transportMaintainenance"
											href="%{urlDoViewMaintenanceMonths}" targets="reportsList"
											indicator="indicator">
										Transport Maintenance
									</sj:a>
									</li>
								  
									</s:if>
								</s:if>
								<s:if test='%{(user.isOnlySchoolAdmin=="Y") || user.isHostelFinance=="Y" || user.isSchoolHostel=="Y" || user.isSchoolPrincipal=="Y" || user.isSchoolDirector == "Y"}'>
								 <s:if test='%{(customer.hostelModuleStatus==true) }'>
									<li>
										<s:url id="urlDoViewAllhostelReports"
											action="ajaxDoViewHostelReports" namespace="/reports" />
										<sj:a id="doViewHostelReports"
											href="%{urlDoViewAllhostelReports}" targets="reportsList"
											indicator="indicator">
										Hostel Student Details
									</sj:a>
									</li>
									<li>
									<s:url id="urlDoViewAllVisitors"
											action="ajaxDaysBetweenByVisitors" namespace="/reports">
											<s:param name="plTitle">Visitor</s:param>
									</s:url>
									<sj:a id="doViewVisitorsReports"
											href="%{urlDoViewAllVisitors}" targets="reportsList"
											indicator="indicator">
										Visitors In/Out
									</sj:a>
									</li>
									<li>
										<s:url id="urlDoViewAllStudentOutIn"
											action="ajaxDaysBetweenByVisitors" namespace="/reports">
											<s:param name="plTitle">Student</s:param>
										</s:url>
										<sj:a id="doViewStudentOutInReports"
											href="%{urlDoViewAllStudentOutIn}" targets="reportsList"
											indicator="indicator">
											Students In/Out
									    </sj:a>
									</li>
									</s:if>
								</s:if>
								<li>
									<s:url id="studentExcelSheet" namespace="/reports"
										action="ajaxStudentGenderAndComunituCountReport"
										escapeAmp="false" includeParams="all">
										<s:param name="plTitle">CategoryAndCommunityGenderWiseSummary</s:param>
									</s:url>
									<sj:a href="%{studentExcelSheet}" targets="reportsList"> Category & Comunity Class Gender Wise Summary</sj:a>
								</li>
								<li>
									<s:url id="studentExcelSheet" namespace="/reports"
										action="ajaxStudentGenderAndComunituCountReport"
										escapeAmp="false" includeParams="all">
										<s:param name="plTitle">ClsssAndCommunityWiseSummary</s:param>
									</s:url>
									<sj:a href="%{studentExcelSheet}" targets="reportsList">Gender And Comunity Class Wise Summary</sj:a>
								</li>
								<li>
									<s:url id="studentAndStaffCredentials" namespace="/reports"
										action="ajaxStudentAndStaffLoginCredentialsReport"
										escapeAmp="false" includeParams="all">
									</s:url>
									<sj:a href="%{studentAndStaffCredentials}" targets="reportsList">Student And Staff Login Credentials</sj:a>
								</li>
							</ul>
						</div>
					</div>
					</s:if>
					<s:if
					  test='%{(user.isOnlySchoolAdmin=="Y") || user.isSchoolPrincipal=="Y" || user.isMasterAdmin=="Y" || user.isOnlySchoolHod=="Y" || user.isOnlySchoolTeacher=="Y" || user.isSchoolHostel=="Y" || user.isSchoolStudent=="Y" || user.isHostelFinance=="Y" || user.isSchoolTransport=="Y" || user.isParent=="Y" || user.isSchoolDirector == "Y"}'>
					<div class="grid_6 alpha" id="StudentsView" style="display: none;">
						<div class="block_head">
							<h2>
								Student Reports  
							</h2>
						</div>
						<div class="block_content" id="sideSubMenu"
							>
							<ul>
							 		<s:if
									test='%{(user.isOnlySchoolAdmin=="Y") || user.isSchoolPrincipal=="Y" || user.isSchoolDirector == "Y"}'>
									<li>
										<s:url id="urlDoIdCardsGenerations" 
											action="ajaxIDCardsGenerations" />
										<sj:a id="doIdCardsGenerations" 
											href="%{urlDoIdCardsGenerations}" targets="reportsList"
											indicator="indicator">ID Cards Generation</sj:a>
									</li>
									</s:if>
									
								<s:if
									test='%{(user.isOnlySchoolAdmin=="Y") || user.isSchoolPrincipal=="Y" || user.isOnlySchoolHod=="Y" || user.isOnlySchoolTeacher=="Y" ||  user.isSchoolHostel=="Y" || user.isHostelFinance=="Y" || user.isSchoolTransport=="Y" || user.isSchoolDirector == "Y"}'>
									<li >
									<s:url id="dailyAttendance"
										action="ajaxDoViewClassesAndTodyDate" escapeAmp="false"
										includeParams="all" >
										<s:param name="tempString">Student</s:param>
										<s:param name="plTitle">Daily Attendance</s:param>
									</s:url>
									<sj:a href="%{dailyAttendance}" targets="reportsList">Attendance Report</sj:a>
								</li>
								<li>
									<s:url id="attendanceForMonthly"
										action="ajaxDoViewClassesAndMonths" escapeAmp="false"
										includeParams="all" >
										<s:param name="tempString">Student</s:param>
										<s:param name="plTitle">Attendance Summary Class Wise(Fully)</s:param>
									</s:url>
									<sj:a href="%{attendanceForMonthly}" targets="reportsList">Attendance Summary Class Wise(Fully)</sj:a>
								</li>
								<li>
									<s:url id="attendanceForMonthly1"
										action="ajaxDoViewClassesAndMonths" escapeAmp="false"
										includeParams="all" >
										<s:param name="tempString">Student</s:param>
										<s:param name="plTitle">Attendance Summary Class Wise(Monthly)</s:param>
									</s:url>
									<sj:a href="%{attendanceForMonthly1}" targets="reportsList">Attendance Summary Class Wise(Monthly)</sj:a>
								</li>	
								<%-- <li>
									<s:url id="urlStudMonthAttendanceReport"
										action="ajaxManageClassesMonthAttendaceReport" escapeAmp="false" namespace="/reports"
										includeParams="all" >
									</s:url>
									<sj:a href="%{urlStudMonthAttendanceReport}" targets="reportsList">Classes Monthly  Attendance Report</sj:a>
								</li>	 --%>					
							 	</s:if> 
								<s:if
									test='%{(user.isOnlySchoolAdmin=="Y") || user.isSchoolPrincipal=="Y" || user.isOnlySchoolHod=="Y" || user.isOnlySchoolTeacher=="Y" || user.isSchoolStudent=="Y" || user.isParent=="Y" || user.isSchoolHostel=="Y" || user.isHostelFinance=="Y" || user.isSchoolTransport=="Y" || user.isSchoolDirector == "Y"}'>
								<li>
										<s:url id="importCommunityExcelSheet"
											action="ajaxDoSelectExamSchedules" escapeAmp="false"
											includeParams="all" namespace="/reports">
											<s:param name="tempString">Student</s:param>
											<s:param name="plTitle">Exam Schedules</s:param>
										</s:url>
										<sj:a href="%{importCommunityExcelSheet}"
											targets="reportsList">Exam Schedules</sj:a>
								</li>
								<s:if test='%{customer.transportModuleStatus==true}'>
										<s:if test='%{user.isOnlySchoolAdmin=="Y" ||  user.isSchoolTransport == "Y" }'>
									    <li>
											<s:url id="urlManageTransportRouteWiseReport" action="ajaxManageTransportRouteWiseReport">
											<s:param name="title">Manage Route Wise Reports</s:param>
											</s:url>
											<sj:a href="%{urlManageTransportRouteWiseReport}"
												targets="reportsList" indicator="indicator">Manage Route Wise Reports</sj:a>
										</li>
										  <li>
											<s:url id="urlManageTransportVehicleWiseReport" action="ajaxManageTransportVehicleWiseReport">
											<s:param name="title">Manage Vehicle Wise Reports</s:param>
											</s:url>
											<sj:a href="%{urlManageTransportVehicleWiseReport}"
												targets="reportsList" indicator="indicator">Manage Vehicle Wise Reports</sj:a>
										</li>
									 </s:if>
								 </s:if>
								
								</s:if>
								<s:if
									test='%{(user.isOnlySchoolAdmin=="Y") || user.isSchoolPrincipal=="Y" || user.isOnlySchoolHod=="Y" || user.isOnlySchoolTeacher=="Y" || user.isSchoolStudent=="Y" || user.isParent=="Y" || user.isSchoolDirector == "Y"}'>
								<li>
									<s:url id="classWiseMarks"
										action="ajaxDoViewClassesAndTodyDate" escapeAmp="false"
										includeParams="all" >
										<s:param name="tempString">Student</s:param>
										<s:param name="plTitle">Class Wise Marks</s:param>
									</s:url>
									<sj:a href="%{classWiseMarks}" targets="reportsList">Term Wise Marks</sj:a>
								</li>
								</s:if>
								<s:if
									test='%{(user.isOnlySchoolAdmin=="Y") || user.isSchoolPrincipal=="Y" || user.isSchoolDirector == "Y"}'>
									<li>
									<s:url id="importCommunityMarks" namespace="/reports"
												action="ajaxClassAndComunityWiseMarks" escapeAmp="false"
												includeParams="all">
												<s:param name="tempString">Student</s:param>
												<s:param name="plTitle">classAndCommunityWise</s:param>
											</s:url>
											<sj:a href="%{importCommunityMarks}"
												targets="reportsList">Class and Community Wise Marks</sj:a>
										</li>
								     <li>
									   <s:url id="importCommunityMarks" namespace="/reports"
												action="ajaxClassAndComunityWiseMarks" escapeAmp="false"
												includeParams="all">
												<s:param name="tempString">Student</s:param>
												<s:param name="plTitle">ReligionWiseMarks</s:param>
											</s:url>
											<sj:a href="%{importCommunityMarks}"
												targets="reportsList">Religion Wise Marks</sj:a>
									</li>
									<li>
										<s:url id="studentExcelSheet"  namespace="/reports"
											action="ajaxDoSelectedStudentDetailsReport" escapeAmp="false"
											includeParams="all" >
										</s:url>
										<sj:a href="%{studentExcelSheet}" targets="reportsList">Student Report Details</sj:a>
								   </li>
								   <li >
									<s:url id="dailyAssignment"
										action="ajaxDoViewClassesAndTodyDate" escapeAmp="false"
										includeParams="all" >
										<s:param name="tempString">Student</s:param>
										<s:param name="plTitle">Daily Assignment</s:param>
									</s:url>
									<sj:a href="%{dailyAssignment}" targets="reportsList">Daily Assignment</sj:a>
								</li>
								</s:if>
								<s:if test='%{user.isSchoolStudent=="Y"}'>
									<li>
										<a href="${pageContext.request.contextPath}/reports/ajaxDownloadStudentTimeTableReport.do" id="urlDownloadStudTT" >Timetable</a>
									</li>
								</s:if>
								<s:if test='%{user.isParent=="Y"}'>
									<li>
										<s:url id="urlDownloadStudsTTable"
											action="ajaxDoDownloadStudentsTimeTableReport" escapeAmp="false"
											includeParams="all" namespace="/reports">
										</s:url>
										<sj:a href="%{urlDownloadStudsTTable}" targets="reportsList">Timetable</sj:a>
									</li>
								</s:if>
							</ul>
						</div>
					</div>
					<div class="grid_6 alpha" id="StaffsView" style="display: none;">
						<div class="block_head">
							<h2>
								Staff Reports
							</h2>
						</div>
						<div class="block_content" id="sideSubMenu"
							>
							<ul>
							<s:if
										test='%{(user.isOnlySchoolAdmin=="Y") || user.isSchoolPrincipal=="Y" || user.isSchoolDirector == "Y"}'>
										<li>
											<s:url id="urlStaffIdCardsGenerations"
												action="ajaxStaffIdCardsGenerations" namespace="/admin"
												includeParams="all"></s:url>
											<sj:a id="urlStaffIdCardsGenerations"
												href="%{urlStaffIdCardsGenerations}" targets="reportsList"
												indicator="indicator">ID Cards Generation</sj:a>
									</s:if>
						 		<s:if
									test='%{(user.isOnlySchoolAdmin=="Y") || user.isSchoolPrincipal=="Y" || user.isHostelFinance=="Y" || user.isSchoolHostel=="Y" || user.isSchoolDirector == "Y"}'>
										<li style="line-height: 0px">
											<s:url id="importCommunityExcelSheet" namespace="/reports"
												action="ajaxDoReligionWiseDetails" escapeAmp="false"
												includeParams="all">
												<s:param name="tempString">Staff</s:param>
												<s:param name="plTitle">CommunityDetails</s:param>
											</s:url>
											<sj:a href="%{importCommunityExcelSheet}"
												targets="reportsList">Staff Community Summary</sj:a>
										</li>
										<li>
											<s:url id="staffExcelSheet"  namespace="/reports"
												action="ajaxDoSelectedStaffDetailsReport" escapeAmp="false"
												includeParams="all" >
											</s:url>
											<sj:a href="%{staffExcelSheet}" targets="reportsList">Staff Report Details</sj:a>
										</li>
										<li style="line-height: 0px">
										<s:url id="StaffReligionsInPDF"
											action="ajaxDoReligionWiseDetails" namespace="/reports"
											includeParams="all" escapeAmp="false">
											<s:param name="tempString">Staff</s:param>
											<s:param name="plTitle">ReligionDetails</s:param>
										</s:url>
										<sj:a href="%{StaffReligionsInPDF}" targets="reportsList">Staff Religion Summary</sj:a>
									</li>
									<li style="line-height: 0px">
										<s:url id="StaffAttendanceMonthlyInPDF"
											action="ajaxViewTeachNonTeachStaffAttendDetails" namespace="/reports"
											includeParams="all" escapeAmp="false">
											<s:param name="tempString">Staff</s:param>
											<s:param name="plTitle">StaffAttendanceMonthly(Monthly)</s:param>
										</s:url>
										<sj:a href="%{StaffAttendanceMonthlyInPDF}" targets="reportsList">Staff Monthly Attendance Details</sj:a>
									</li>
									<li style="line-height: 0px">
										<s:url id="StaffDailyAttendanceInPDF"
											action="ajaxStaffDailyAttendDetails" namespace="/reports"
											includeParams="all" escapeAmp="false">
											<s:param name="tempString">Staff</s:param>
											<s:param name="plTitle">StaffDailyAttendance(Daily)</s:param>
										</s:url>
										<sj:a href="%{StaffDailyAttendanceInPDF}" targets="reportsList">Staff Daily Attendance Details</sj:a>
									</li>
								</s:if>
								<s:if
									test='%{(user.isOnlySchoolAdmin=="Y") || user.isSchoolPrincipal=="Y" || user.isHostelFinance=="Y" || user.isSchoolHostel=="Y" || user.isSchoolDirector == "Y"}'>
								<s:if test='%{(customer.hostelModuleStatus==true) }'>
									<li>
										<s:url id="StaffReligionsInPDF"
											action="ajaxDoViewStaffHostelReports" namespace="/reports"
											includeParams="all">
											<s:param name="tempString">Staff</s:param>
										</s:url>
										<sj:a href="%{StaffReligionsInPDF}" targets="reportsList">Hostel Staff Details</sj:a>
									</li>
								</s:if>
							  </s:if>
							</ul>
						</div>
					</div>
					</s:if>
					<div class="grid_6 alpha" id="FinanceView" style="display: none;">
						<div class="block_head">
							<h2>
								Fee-Collections
							</h2>
						</div>
						<div class="block_content" id="sideSubMenu">
							<ul>
							
							<s:iterator value="objectList">
								<li class="grid_14">
									<div class="grid_14" id="results">
										<div class="grid_3">
										<s:url id="StaffReligionsInPDF"
										action="ajaxFeeCollectionAndDues" includeParams="all"
										escapeAmp="false">
										<s:param name="title"><s:property value="settingName"/></s:param>
										<s:param name="tempId"><s:property value="id"/></s:param>
									</s:url>
									<sj:a href="%{StaffReligionsInPDF}" cssClass="showFeeHeader" 
										targets="reportsList"><s:property value="settingName" /></sj:a>
										
										</div>
									</div>
								</li>
							</s:iterator>
								<li style="line-height: 0px">
									<s:url id="StaffDailyAttendanceInPDF"
										action="ajaxStaffDailyAttendDetails" namespace="/reports"
										includeParams="all" escapeAmp="false">
										<s:param name="tempString">Staff</s:param>
										<s:param name="plTitle">DateWiseFeeCollection</s:param>
									</s:url>
									<sj:a href="%{StaffDailyAttendanceInPDF}" targets="reportsList">Date Wise Fee Collection</sj:a>
								</li>
							<li>
								<s:url id="viewStudentPaymentDefaultesLink" action="ajaxViewPaymentDefaulters"
									includeParams="all" escapeAmp="false" namespace="/schoolfee">
									<s:param name="title">Sree</s:param>
								</s:url>
								<sj:a href="%{viewStudentPaymentDefaultesLink}" targets="staffEditProfile"
									indicator="indicator">Class Wise Fee Defaulters</sj:a>		
							</li>
							<s:if test='%{(user.isOnlySchoolAdmin=="Y") || user.isSchoolPrincipal=="Y" || user.isSchoolFinance=="Y" || user.isSchoolDirector == "Y"}'>
								<li>
									<s:url id="dayBookExcelSheet" namespace="/reports"
										action="ajaxGetDayBookReports"
										escapeAmp="false" includeParams="all">										
									</s:url>
									<sj:a href="%{dayBookExcelSheet}" targets="reportsList">DayBook Reports</sj:a>
								</li>
							</s:if>
							</ul>
						</div>
					</div>
					<s:if test='%{(user.isOnlySchoolAdmin=="Y") || user.isHostelFinance=="Y" || user.isSchoolFinance=="Y" || user.isSchoolHostel=="Y" || user.isSchoolPrincipal=="Y" || user.isSchoolTransport=="Y" || user.isTransportFinance=="Y" || user.isSchoolDirector == "Y"}'>
					<div class="grid_6 alpha" id="FineFinanceView" style="display: none;">
						<div class="block_head">
							<h2>
								Fine Fee-Collections
							</h2>
						</div>
						<div class="block_content" id="sideSubMenu">
							<ul>
								<li id="fineFeeDetails">
									<s:url id="importClassWiseStudentExcelSheet"
										action="ajaxDoReligionWiseDetails" escapeAmp="false"
										includeParams="all" namespace="/reports">
										<s:param name="tempString">Finace</s:param>
										<s:param name="plTitle">ClassWiseFineFeeDetails</s:param>
									</s:url>
									<sj:a href="%{importClassWiseStudentExcelSheet}"
										targets="reportsList">Class Wise Fine Amount</sj:a>
								</li>
							</ul>
						</div>
					</div>	
					</s:if>
				</div>
				<div id="reportsList" class="block grid_12 alpha">
					<jsp:include
						page="/WEB-INF/pages/admin/reports/ajaxDoViewDownloadSheets.jsp"></jsp:include>
				</div>
			</div>
		</div>
		<span id="loginRoleName" style="display: none;"><s:property
				value="lastName" />
		</span>
	</div>
	<script Language="Javascript1.2" type="text/javascript">
$(document).ready(
		function() {
		changePageTitle("Manage Admin");
			var loginName = $('span#loginRoleName').html();
			loginName = jQuery.trim(loginName);
			if (loginName != 'Undefined') {
				if (loginName == "HOSTEL" || loginName == "HOSTELFINANCE" || loginName == "Transport") {
					$('#sideMenu li:contains("Fee Collections")').insertBefore("#sideMenu li:first");
					$('#FinanceView').show();
					$('#FineFinanceView').show();
					$('#AdminReports').html('Fee Collections');
					$('div#FinanceView  div#sideSubMenu li:first').next('li').children('a').click();
					$('div#FineFinanceView div#sideSubMenu li:first').next('li').children('a').click();
				}else if(loginName == "TransportFinance" || loginName == "Finance" || loginName == "FineFinance"){
					$('#FinanceView').show();
					$('#FineFinanceView').show();
					$('#AdminReports').html('Fee Collections');
					$('div#FinanceView  div#sideSubMenu li:first').next('li').children('a').click();
					$('div#FineFinanceView  div#sideSubMenu li:first').next('li').children('a').click();
				}
				 else if(loginName == "Teacher" || loginName == "Hod") {
					$('#sideMenu li:contains("Staff")').insertBefore("#sideMenu li:first");
					if($('#StaffsView').empty()){
					$('#StaffsView').hide();
					$('#StudentsView').show();
					$('div#StudentsView  div#sideSubMenu li:first').children('a').click();
					}else{
					$('#StaffsView').show();
					}
					$('#AdminReports').html('Student');
				}else if(loginName == "Student" || loginName == "Parent"){
					$('#StudentsView').show();
					$('#AdminReports').html('Student');
					$('div#StudentsView  div#sideSubMenu li:first').children('a').click();
				}else if(loginName=="Administrator" || loginName=="Principal"){
				  	$('#AdminView').show();
					$('#AdminReports').html('Admin');
					$('div#sideSubMenu li:first').next('li').children('a').click();
				}
				$('#sideMenu li:first').addClass("active");
			}
			$('div#sideSubMenu li').click(function() {
					$('h2#AdminReports').text($('div#sideMenu li.active a').text()+"-->"+$('div#sideSubMenu li.active a').text());
				});
			$('#reportsContent').addClass('current');
		});

jQuery(function($) {
	$('#Admin').click(function() {
		 changePageTitle("Manage "+$(this).text());
		$('#AdminReports').html('Admin');
		$('#classAndCommunity').show();
		$('#hostelReports').show();
		$('#AdminView').show();
		$('#StudentsView').hide();
		$('#commonStep13').hide();
		$('#StaffsView').hide();
		$('#FinanceView').hide();
		$('#FineFinanceView').hide();
		$('div#sideSubMenu li:first').next('li').children('a').click();
	});
	$('#Student').click(function() {
	changePageTitle("Manage "+$(this).text());
		$('#AdminReports').html('Student');
		$('#StudentsView').show();
		$('#commonStep13').hide();
		$('#AdminView').hide();
		$('#StaffsView').hide();
		$('#FinanceView').hide();
		$('#FineFinanceView').hide();
		$('div#StudentsView div#sideSubMenu li:first').next('li').children('a').click();
	});
	$('#Staff').click(function() {
	changePageTitle("Manage "+$(this).text());
		$('#AdminReports').html('Staff');
		$('#StaffsView').show();
		$('#commonStep13').hide();
		$('#AdminView').hide();
		$('#StudentsView').hide();
		$('#FinanceView').hide();
		$('#FineFinanceView').hide();
		$('div#StaffsView div#sideSubMenu li:first').next('li').children('a').click();
	});
	$('#Finance').click(function() {
	changePageTitle("Manage "+$(this).text());
		$('#AdminReports').html('Fee Collections');
		$('#commonStep13').show();
		$('#FinanceView').show();
		$('#FineFinanceView').hide();
		$('#AdminView').hide();
		$('#classAndCommunity').hide();
		$('#hostelReports').hide();
		$('#StudentsView').hide();
		$('#StaffsView').hide();
		$('div#FinanceView div#sideSubMenu li:first').find('a').click();
	});
	$('#FineFinance').click(function() {
	changePageTitle("Manage "+$(this).text());
		$('#AdminReports').html('Fee Collections');
		$('#commonStep13').show();
		$('#FinanceView').hide();
		$('#FineFinanceView').show();
		$('#AdminView').hide();
		$('#classAndCommunity').hide();
		$('#hostelReports').hide();
		$('#StudentsView').hide();
		$('#StaffsView').hide();
		$('div#FineFinanceView div#sideSubMenu li:first').find('a').click();
	})
});
function functionOne() {

 alert('You clicked the top text'); 
 
 }
</script>
