<%@ include file="/common/taglibs.jsp"%>
<div class="header navbar navbar-inverse navbar-fixed-top">
	<s:if test="%{(reminderDetails != null && !reminderDetails.isEmpty()) || (taskReminderDetails != null && !taskReminderDetails.isEmpty())}">
			<a id="showReminder" class="warningResponse" href="#responsiveReminder" data-toggle="modal">
				<jsp:include page="/WEB-INF/pages/staff/task/ajaxShowReminder.jsp"/>
			</a>
	</s:if>
	<s:if test='%{#session.customerStatus == "Y"}'>
		<s:if test='%{#session.newAcademicYear != "C"}'>
			<s:if test='%{#session.dateExceeded =="Y"}'>	
		 			<a id="showEndDate" class="warningResponse" href="#responsiveEndDate" data-toggle="modal"><jsp:include
						page="/WEB-INF/pages/admin/ajaxShowSchoolEndDate.jsp" />
					</a>
			</s:if> 	
		<s:if
				test="%{(#session.customerVision !=null && !#session.customerVision.isEmpty()) || (#session.customerMission !=null && !#session.customerMission.isEmpty())}">
				<a id="showVisionMission" class="response" href="#responsiveVision" data-toggle="modal"> <jsp:include
						page="/WEB-INF/pages/admin/ajaxShowCustomerVissionAndMission.jsp" />
				</a>
		</s:if> 
	</s:if>
		<s:if test='%{#session.previousYear=="N"}'>
			<s:if test='%{#session.passwordStatus}'>
				<s:if test='%{#session.passwordHints !="P"}'>
					<a id="showChangePassword" class="response" href="#responsivePassword" data-toggle="modal">
					 <jsp:include page="/WEB-INF/pages/user/ajaxChangeParentPassword.jsp" />
					</a>
				</s:if>
			</s:if>
		</s:if>
		<div class="header-inner">
			<a class="brand navbar-brand" href="#"> <img src='<s:property  value="#session.custImage"/>' border="0" alt="logo" class="logoHeader" id="customerLogoDiv"/> </a>
			  <div class="col-md-7 form-horizontal">
			 	<div class="col-md-10">
					<span class="siteLogo"><a href="#" style="color: #fff;text-align: center;" id="organizationName"><s:property value="#session.schoolName" /> </a> </span>
					<b><a href="#" style="color: #fff;margin-left: 15px;" id="organizationFullAddress"><s:property value="#session.schoolAddress" /> </a></b>
				</div>
				<div>
					<s:if test="%{#session.branches != null && !#session.branches.isEmpty()}">
						<div class="col-md-7">
							<div class="form-group"  style="margin-bottom:0px">
							      <a href="javascript:;" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse"> <img src="../img/menu-toggler.png" alt="" /> </a>
								<!-- <label class="col-md-4 control-label" style="color: #fff;width: 26.333%;">Your Branches :</label> -->
								<div class="themeBranch col-md-9">
									<a class="selectedBranch" href="javascript:;"><s:property value="#session.organization" /></a>
									<ul id="themes_selectorBranch" style="display: none;">
										<form name="branchesForm" action="${pageContext.request.contextPath}/subscription/userAccess.do">
											<input type="hidden" name="branch" id="customerId" value='' />
											<input type="hidden" name="requestURL" value="<%=request.getRequestURL()%>" />
											<s:iterator value="#session.branches">
												<li onclick="javascript:loadBranchData(this);" id="<s:property value='id' />" class="<s:property value='#session.branch' />">
													<s:property value="organization" />
												</li>
											</s:iterator>
										</form>
									</ul>
								</div>
							</div>
						</div>
					</s:if>
					<div class="col-md-5">
						<s:if test="%{#session.academicYearList != null && !#session.academicYearList.isEmpty()}">
							<s:if test="%{#session.academicYearList .size > 1}">
								<div class="form-group"  style="margin-bottom:0px">
									<label class="col-md-5 control-label" style="color: #fff;">
										Academic Year : </label>
									<div class="themes col-md-8">
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
													<input type="hidden" name="passwordHint" id="passwordHint" value='PW' />
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
								</div>
							</s:if>
						</s:if>
					</div>
				</div>
			</div>
			<ul class="nav navbar-nav pull-right">
				<li class="dropdown user">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown"
						data-hover="dropdown" data-close-others="true"> <s:if
							test="%{user.profileImage.adjustedAttachmentFilePath != null &&  user.profileImage.adjustedAttachmentFilePath != ''}">
							<img
								src='<c:url value="${user.profileImage.adjustedAttachmentFilePath}"/>' border="0"
								style="width: 55px; height: 60px;" id="userProfileImageDiv" class='<s:property  value="user.id"/>'/>
						</s:if> 
						<s:else>
							<img alt="" src="../img/avatar.png" / id="profileImagesDiv" style="width: 55px; height: 60px;" class='<s:property  value="user.id"/>'>
						</s:else> <span class="username hidden-480"> <s:property
								value="#session.firstName" /> <s:if
								test='%{user.isSchoolStudent=="Y"}'>
							 		(<s:property value="#session.studentClassSection" />)
							  	</s:if> </span> <i class="fa fa-angle-down"></i> </a>
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
		</div>
	</s:if>
</div>
<div class="clearfix"></div>
<div class="page-container">
	<div class="page-sidebar-wrapper">
		<div class="page-sidebar navbar-collapse collapse">
			<ul class="page-sidebar-menu">
				<li class="sidebar-toggler-wrapper">
					<div class="sidebar-toggler hidden-phone">
					</div>
					<div class="spaceDiv"></div>
				</li>
				<s:if test='%{user.isSecretary=="Y" || user.isSecretaryPA=="Y"}'>
					<li class="sidebar-search-wrapper" style="margin-left:10px;">
						<s:if test="%{#session.OrgcustomersList != null && !#session.OrgcustomersList.isEmpty()}">
							<s:select list="#session.OrgcustomersList" listKey="id"
								listValue="organization"  theme="simple"
								cssClass="form-control input-medium" name="customer.id" data-placeholder="Search for school name" id="SchoolName"
								onchange="$('input#organizationName').val($(this).find('option:selected').text());"
								headerKey="" headerValue="" cssStyle="padding: 3px 10px;"></s:select>
							<sj:textfield id="organizationName"
								name="customer.organization"
								cssClass="form-control promoteClass required"
								cssStyle="float: left; margin-left: 1px; margin-top: -31px; width: 207px;background-color : #FFF;"></sj:textfield>
						</s:if>
						<s:else>
							<sj:textfield id="organizationName" name="customer.organization" cssClass="form-control input-medium promoteClass"></sj:textfield>
						</s:else>
					</li>
					<li class="spaceDiv">&nbsp;</li>
				</s:if>
				<s:if test='%{customerByCustId.staffPermissionStatus=="Y"}'>
					<s:if test='%{user.isHostelFinance=="Y" || user.isSchoolClerk == "Y" || user.isSchoolTransport=="Y" || user.isSchoolFinance=="Y" 
					|| user.isSchoolHostel == "Y" || user.isTransportFinance=="Y" || user.isHostelFinance=="Y"}'>
					<li>
						<a href="javascript:;" id="MPER"> <i class="fa fa-gift"></i>
							<span class="title">Manage Permissions</span> <span class="selected"></span>
							<span class="arrow"></span> </a>
						<ul class="sub-menu">
							 <li>
								<s:url id="StaffRequest" action="ajaxViewStaffRequest" namespace="/staff"/> 
								<sj:a href="%{StaffRequest}" targets="mainContentDiv" cssClass="ajaxify MPER">Permissions</sj:a>
							</li>
						</ul>
					</li>
					</s:if>
				</s:if>
				
				<s:if test='%{user.isSchoolAdmin=="Y" || user.isSchoolPrincipal=="Y" || user.isSchoolTeacher == "Y" || user.isAdminCoordinator == "Y" || tempBoolean 
				|| user.isAdminCoordinator == "Y" || user.isSchoolDirector == "Y"}'>
					<li>
						<form class="sidebar-search" id="globalSearchFormId">
							<div class="input-box">
							<a href="javascript:;" class="remove"></a>
								<input type="text" id="searchString" name="searchWord" placeholder="Student Search">
								<input type="button" onclick="javascript:adminSearchResultsDiv();" value="" class="submit1" id="globalSearchButtonId">
							</div>
						</form>
					</li>
				</s:if>
				<s:if
					test='%{user.isOnlySchoolHod=="Y" || user.isOnlySchoolTeacher=="Y" || user.isSchoolAsstStaff=="Y" || user.isAdminCoordinator == "Y"}'>
					<s:if test='%{user.isSchoolAsstStaff=="Y"}'>
						<li id="asstStaffAttDiv">
							<s:url id="ajaxClassAttendencelink1" namespace="/admin"
								action="ajaxDoGetAttendanceForm" includeParams="all"
								escapeAmp="false">
							</s:url>
							<sj:a href="%{ajaxClassAttendencelink1}" targets="mainContentDiv"
								cssClass="ajaxify MATNC">Manage Attendance</sj:a>
						</li>
						<li>
							<a href="javascript:;" id="ASMS"><i class="fa fa-comment"></i>
								<span class="title">SMS</span> <span class="selected"></span> <span
								class="arrow"></span> </a>
							<ul class="sub-menu">
								<li id="adminSms">
									<s:url id="urlInboxesDetails"
										action="ajaxDoSendSchoolWideMessages" namespace="/common"
										includeParams="all" escapeAmp="false">
										<s:param value="#session.academicYear" name="academicYearId" />
									</s:url>
									<sj:a href="%{urlInboxesDetails}" targets="mainContentDiv"
										cssClass='ajaxify ASMEL'>
										Admin SMS or E-Mail</sj:a>
								</li>
						 </ul>
					 </li>
						<li>
							<a href="javascript:;" id="staffMyClass"> <i
								class="fa fa-gift"></i> <span class="title">My Class</span> <span
								class="selected"></span> <span class="arrow"></span> </a>
							<ul class="sub-menu">
								<li>
									<s:url id="urlTeacherLeaveLink"
										action="ajaxDoGetLeaveDetailsLeft" namespace="/staff" />
									<sj:a href="%{urlTeacherLeaveLink}" targets="mainContentDiv"
										cssClass="ajaxify SLMS">
										Leave Management</sj:a>
								</li>
								<li>
									<s:url id="studentAssignment"
										action="ajaxViewStaffClassAssignment" namespace="/admin" />
									<sj:a href="%{studentAssignment}" targets="mainContentDiv"
										cssClass="ajaxify HMCAN">
										Manage Class Assignment</sj:a>
								</li>
<%-- 								<li>
									<s:url id="urlEventsLink" action="ajaxStaffEvents"
										namespace="/staff"></s:url>
									<sj:a href="%{urlEventsLink}" targets="mainContentDiv"
										cssClass="ajaxify EVNT">
										Events</sj:a>
								</li>
 --%>								<li>
									<s:url id="urlManageTaskInfo" action="ajaxTaskInformationHome"	namespace="/staff" />
									<sj:a id="urlManageTaskInfo" href="%{urlManageTaskInfo}"  targets="mainContentDiv" cssClass="ajaxify MSTID"> Task Management 
									<s:if test='%{#session.tempString1 == "ST"}'>
										<span class="badge badge-info" ><font color="white"><b>New (<s:property value="tempId"/>)</b></font></span>
									</s:if>
									 </sj:a> 
								</li>
								<li>
									<s:url id="urlUploadQuestionBankMaterials" action="ajaxViewQuestionBankMaterialList" namespace="/exam" />
									<sj:a id="urlUploadQuestionBankMaterials" href="%{urlUploadQuestionBankMaterials}" targets="mainContentDiv" cssClass="ajaxify AUDS">
									Question Bank </sj:a>
								</li>
							</ul>
						</li>
					</s:if>
					<s:else>
					<li class="start active">
						<a
							href="${pageContext.request.contextPath}/staff/staffHome.do?id=staff"
							id="staff" class="start"> <i class="fa fa-home"></i> <span
							class="title">Teacher Dashboard</span> <span class="selected"></span>
						</a>
					</li>
					<li>
						<a href="javascript:;" id="staffMyClass"> <i
							class="fa fa-gift"></i> <span class="title">My Class</span> <span
							class="selected"></span> <span class="arrow"></span> </a>
						<ul class="sub-menu">
							<li class="active">
								<s:url id="urlExamMarkTempLink" action="ajaxStaffExamSchedules"
									namespace="/exam" />
								<sj:a href="%{urlExamMarkTempLink}" targets="mainContentDiv"
									cssClass="ajaxify staffMyClass">
									Exams &amp; Results</sj:a>
							</li>
							
							<li>
								<s:url id="urlViewStaffTimetableLink" action="ajaxViewStaffTimetable"
									namespace="/staff" />
								<sj:a href="%{urlViewStaffTimetableLink}" targets="mainContentDiv"
									cssClass="ajaxify myTimetable">
									My Timetable</sj:a>
							</li>
							
							<s:if test='%{customerByCustId.parentPermissionStatus=="Y"}'>
								<s:if test='%{user.isOnlySchoolAdmin == "N" && user.isOnlySchoolHod=="N" && !#session.isClassTeacher}'>
									<li>
										 <s:url id="StaffRequest" action="ajaxViewStaffRequest" namespace="/staff"> </s:url>
										  <sj:a href="%{StaffRequest}" targets="mainContentDiv" cssClass="ajaxify MAPS">Manage Permissions</sj:a>
									  </li>
								</s:if>
						    </s:if>
							<s:if
								test='%{user.isOnlySchoolAdmin == "Y" || #session.isClassTeacher || user.isOnlySchoolHod=="Y" || user.isAdminCoordinator == "Y"}'>
								<li>
									<s:url id="ajaxClassAttendencelink1" namespace="/admin"
										action="ajaxDoGetAttendanceForm" includeParams="all"
										escapeAmp="false">
									</s:url>
									<sj:a href="%{ajaxClassAttendencelink1}"
										targets="mainContentDiv" cssClass="ajaxify MATNC">Manage Attendance</sj:a>
								</li>
								<s:if test='%{customerByCustId.staffPermissionStatus=="Y"}'>
									<s:if test='%{user.isOnlySchoolAdmin == "Y"}'>
										<li>
											 <s:url id="urlPermissions" action="ajaxViewAllPermissions" namespace="/student" includeParams="all" escapeAmp="false">
												<s:param name="bankName">isClassTeacher</s:param>
											</s:url>
											<sj:a id="urlPermissions" href="%{urlPermissions}" targets="mainContentDiv" cssClass="ajaxify MAPS"> Manage Permissions</sj:a> 
										  </li>
									</s:if>
									<s:elseif test='%{user.isOnlySchoolHod=="Y" || user.isAdminCoordinator == "Y"}'>
										<li>
											<s:url id="StaffRequest" action="ajaxViewStaffRequest" namespace="/staff"/> 
											<sj:a href="%{StaffRequest}" targets="mainContentDiv" cssClass="ajaxify MAPS">Manage Permissions</sj:a>
										</li>
									</s:elseif>
									<s:else>
										<li>
											<s:url id="StaffRequest" action="ajaxViewStaffRequest" namespace="/staff" includeParams="all" escapeAmp="false"> 
											<s:param name="bankName">isClassTeacher</s:param></s:url>
											<sj:a href="%{StaffRequest}" targets="mainContentDiv" cssClass="ajaxify MAPS">Manage Permissions</sj:a>
										</li>
									</s:else>
							   </s:if>
							</s:if>
							<s:if test='%{user.isOnlySchoolAdmin == "Y" || user.isOnlySchoolHod=="Y" || user.isAdminCoordinator =="Y" }'>
								<li>
									<s:url id="urlDoScoreCard"
										action="ajaxManageScorecardGeneration" namespace="/exam"
										escapeAmp="false" includeParams="all">
										<s:param name="anyTitle">Y</s:param>
									</s:url>
									<sj:a id="doScoreCardGen" href="%{urlDoScoreCard}"
										title="It generates score card based on templates."
										targets="mainContentDiv" cssClass="ajaxify MSCG">Score Card Generation</sj:a>
								</li>
							</s:if>
							<li>
								<s:url id="urlMyStudentsLink" action="ajaxGetStaffStudyClasses"
									namespace="/common">
									<s:param name="staff.id" value="0"></s:param>
								</s:url>
								<sj:a href="%{urlMyStudentsLink}" targets="mainContentDiv"
									id="myStudentsLink" cssClass="ajaxify MYS">
									My Students</sj:a>
							</li>
							
							<li id="PerformanceDIV">
								<s:url id="urlMyLeaveLink" action="ajaxStaffPerformance"
									namespace="/staff" />
								<sj:a href="%{urlMyLeaveLink}" targets="mainContentDiv"
									cssClass="ajaxify MYP">
									My Performance</sj:a>
							</li>
							<li>
								<s:url id="urlTeacherLeaveLink"
									action="ajaxDoGetLeaveDetailsLeft" namespace="/staff" />
								<sj:a href="%{urlTeacherLeaveLink}" targets="mainContentDiv"
									cssClass="ajaxify SLMS">
									Leave Management</sj:a>
							</li>
							<%-- <li>
								<s:url id="urlEventsLink" action="ajaxStaffEvents"
									namespace="/staff"></s:url>
								<sj:a href="%{urlEventsLink}" targets="mainContentDiv"
									cssClass="ajaxify EVNT">
									Events</sj:a>
							</li> --%>
							<li>
									<s:url id="urlDoRegisterStaffHolidaysLink"
										action="ajaxViewSchoolSettingsHolidays" namespace="/admin" />
									<sj:a id="registerStaffHolidayLink"
										href="%{urlDoRegisterStaffHolidaysLink}"
										targets="mainContentDiv" cssClass="ajaxify HVS">
										Holidays</sj:a>
							</li>
							<s:if test='%{user.isSchoolTeacher=="Y"  || user.isAdminCoordinator == "Y" || tempBoolean || user.isAdminCoordinator == "Y"}'>
								<!-- here tempBoolean is a check the is class teacher or not -->
								<li>
									<s:url id="studentAssignment"
										action="ajaxViewStaffClassAssignment" namespace="/admin" />
									<sj:a href="%{studentAssignment}" targets="mainContentDiv"
										cssClass="ajaxify HMCAN">
										Manage Class Assignment</sj:a>
								</li>
								<li>
									<s:url id="urlParentOnlineAppointment"
										action="ajaxViewOnlineAppointment" namespace="/student" />
									<sj:a  href="%{urlParentOnlineAppointment}"
										targets="mainContentDiv" cssClass="ajaxify OAL">
										Online Appointment</sj:a>
								</li>
								
							</s:if>
							<li>
								<s:url id="subjectsPlanner" action="ajaxViewSubPlannerDetails"
									namespace="/staff" />
								<sj:a href="%{subjectsPlanner}" targets="mainContentDiv"
									cssClass="ajaxify MSPSRT">
									Manage Subject Planners</sj:a>
							</li>
							<s:if test='%{#session.parentMobileNoVisibleToTeacher == "Y" }'>
							 <s:if test='%{user.isSchoolTeacher == "Y" && tempBoolean || user.isOnlySchoolHod=="Y" || user.isAdminCoordinator == "Y" || user.isAdminCoordinator == "Y"}'> 
								<li>
									<s:url id="dosendMarksToStudentsMobile"
										action="ajaxDoViewClassesHaveMarks" namespace="/exam" />
									<sj:a id="dosendMarksToStudentsMobile"
										href="%{dosendMarksToStudentsMobile}" targets="mainContentDiv"
										cssClass="ajaxify ESMTMS">
								 Send Marks To Mobile</sj:a>
								</li>
							 </s:if> 
							</s:if>
							<li>
								<s:url id="studentAssignment" action="ajaxDoGetCreateQuiz"
									namespace="/staff" />
								<sj:a href="%{studentAssignment}" targets="mainContentDiv"
									cssClass="ajaxify HQZ">
									Quiz</sj:a>
							</li>
							<%-- <li>
								<s:url id="staffTimeTable" action="ajaxDoViewStaffTimeTable"
									namespace="/staff"></s:url>
								<sj:a href="%{staffTimeTable}" targets="mainContentDiv"
									cssClass="ajaxify HTB">
								  Timetable</sj:a>
							</li> --%>
							<li>
								<s:url id="staffCurriculamActivities" action="ajaxViewStaffCurricularActivities" namespace="/staff">
									<s:param name="tempId" value="0"></s:param>
									</s:url>
								<sj:a href="%{staffCurriculamActivities}" targets="mainContentDiv"
									cssClass="ajaxify SCA">
								  Curricular Activities</sj:a>
							</li>
							<s:if test='%{user.isSchoolTeacher == "Y" && tempBoolean || user.isOnlySchoolHod=="N" || user.isAdminCoordinator == "Y" || user.isAdminCoordinator == "Y"}'>
							<li>
								<s:url id="urlViewStudyMaterials" action="ajaxViewStudyClassMaterialList"
									namespace="/admin" includeParams="all" escapeAmp="false">
								</s:url>
								<sj:a id="viewStudyMaterials" href="%{urlViewStudyMaterials}"
									targets="mainContentDiv" cssClass="ajaxify SSMT">
								   Study Material</sj:a>
							</li>
							</s:if>
							<li>
									<s:url id="urlManageTaskInfo" action="ajaxTaskInformationHome"	namespace="/staff" />
									<sj:a id="urlManageTaskInfo" href="%{urlManageTaskInfo}"  targets="mainContentDiv" cssClass="ajaxify MSTID"> Task Managment 
									<s:if test='%{#session.tempString1 == "ST"}'>
										<span class="badge badge-info" ><font color="white"><b>New (<s:property value="tempId"/>)</b></font></span>
									</s:if>
									 </sj:a> 
								</li>
								<%-- <li>
									<s:url id="urlManageTaskInfo" action="ajaxTaskInformationHome"	namespace="/staff" />
									<sj:a id="urlManageTaskInfo" href="%{urlManageTaskInfo}"  targets="mainContentDiv" cssClass="ajaxify MSTID">
									Task Managment </sj:a>
								</li> --%>
								<s:if test='%{user.isSchoolTeacher == "Y" && tempBoolean || user.isSchoolPrincipal=="Y" || user.isAdminCoordinator == "Y" || user.isOnlySchoolHod=="Y" || user.isOnlySchoolTeacher=="Y" || user.isSchoolAsstStaff=="Y" || user.isOnlySchoolAdmin == "Y"}'>
									<li>
										<s:url id="urlUploadQuestionBankMaterials" action="ajaxViewQuestionBankMaterialList" namespace="/exam" />
										<sj:a id="urlUploadQuestionBankMaterials" href="%{urlUploadQuestionBankMaterials}" targets="mainContentDiv" cssClass="ajaxify AUDS">
										Question Bank </sj:a>
									</li>
							 </s:if>
						</ul>
					</li>
				</s:else>
				</s:if>
				<s:if test='%{user.isOnlySchoolAdmin == "Y" || user.isSchoolPrincipal=="Y" || user.isSchoolDirector == "Y"}'>
					<%@ include file="/common/adminLeftNav.jsp"%>
				</s:if>
				<s:elseif test='%{user.isSchoolClerk == "Y"}'>
					<li class="start active">
                   	 <a
                            href="${pageContext.request.contextPath}/staff/clerkStaffDashboard.do?id=dashboard"
                            id="dashboard"> <i class="fa fa-home"></i>
                            <span class="title">Dashboard</span> <span class="selected"></span>
                        </a>
                    </li>
                    <li id="manageAtt">
						<a href="javascript:;" id="MSAT"> <i class="fa fa-calendar"></i>
							<span class="title">Manage Attendance</span> <span class="selected"></span>
							<span class="arrow "></span> </a>
						<ul class="sub-menu">
							<li id="attendanceId">
								<s:url action="ajaxDoGetAttendanceForm"
									id="ajaxClassAttendencelink" namespace="/admin"
									includeParams="all" escapeAmp="false">
									<s:param name="staffAtt">studentAt</s:param>
								</s:url>
								<sj:a href="%{ajaxClassAttendencelink}" targets="mainContentDiv"
									cssClass="ajaxify MSAT">Attendance</sj:a>
							</li>
						</ul>
					</li>
                    <li id="manageStudent">
						<a href="javascript:;" id="MSTI"> <i class="fa fa-group"></i>
							<span class="title">Manage Student</span> <span class="selected"></span>
							<span class="arrow "></span> </a>
						<ul class="sub-menu">
							<li id="studentDiv">
								<s:url id="urlmanageStudent" action="ajaxGetStudyClassList"
									namespace="/student" includeParams="all" escapeAmp="false" />
								<sj:a id="manageStudents" href="%{urlmanageStudent}"
									targets="mainContentDiv" cssClass="ajaxify MSTI">Students Information</sj:a>
							</li>
							<li>
								<s:url id="urlHallTicketGeneration"
									action="ajaxDoGetHallTicketGeneration" namespace="/exam" />
								<sj:a id="urlHallTicketGeneration"
									href="%{urlHallTicketGeneration}" targets="mainContentDiv"
									cssClass="ajaxify AHTG">
							HallTicket Generation</sj:a>
							</li>
							<li>
								<s:url id="urlGetCastSettings" action="ajaxCastSettingsHome"
									namespace="/admin" />
								<sj:a id="castDetails" href="%{urlGetCastSettings}"
									targets="mainContentDiv" cssClass="ajaxify CACS">Manage Community & Caste</sj:a>
							</li>
							<li>
								<s:url action="ajaxDoGenerateStudentRollNumber"
									id="ajaxDoGenerateStudentRollNumber" namespace="/admin"
									includeParams="all" escapeAmp="false">
								</s:url>
								<sj:a href="%{ajaxDoGenerateStudentRollNumber}" targets="mainContentDiv"
									cssClass="ajaxify MSRN">Manage Student Roll Numbers</sj:a>
							</li>
							
							<li>
								<a href="javascript:;" id="SSCS"> <span class="title">Manage Student Certificates</span> <span class="selected"></span> <span
									class="arrow"></span> </a>
								<ul class="sub-menu">
									<li>
										<s:url id="urlDoStudy"
											action="ajaxDoGetStudyAndBonafiedGeneration"
											includeParams="all" escapeAmp="false" namespace="/admin">
											<s:param name="anyTitle">studyCertificate</s:param>
										</s:url>
										<sj:a id="studyCert" href="%{urlDoStudy}"
											targets="mainContentDiv" cssClass="ajaxify SSCS">Study Certificate</sj:a>
									</li>
									<li>
										<s:url id="urlDoBonafied"
											action="ajaxDoGetStudyAndBonafiedGeneration"
											includeParams="all" escapeAmp="false" namespace="/admin">
											<s:param name="anyTitle">bonafiedCertificate</s:param>
										</s:url>
										<sj:a id="bonafied" href="%{urlDoBonafied}"
											targets="mainContentDiv" cssClass="ajaxify SSBS">Bonafied Certificate</sj:a>
									</li>
									<li>
										<s:url id="urlNoDue"
											action="ajaxDoGetStudyAndBonafiedGeneration"
											includeParams="all" escapeAmp="false" namespace="/admin">
											<s:param name="anyTitle">noDuesCertificate</s:param>
										</s:url>
										<sj:a id="noDue" href="%{urlNoDue}" targets="mainContentDiv"
											cssClass="ajaxify SSDDS">No Dues Certificate</sj:a>
									</li>
									<li>
										<s:url id="urlDoTcGeneration" action="ajaxDoGetTcGeneration"
											namespace="/admin" >
											<s:param name="anyTitle">templateSettings</s:param>
											</s:url>
										<sj:a id="doTcGeneration" href="%{urlDoTcGeneration}"
											targets="mainContentDiv" cssClass="ajaxify SSTCS">TC Generation</sj:a>
									</li>
									<li>
										<s:url id="urlDoLcGeneration" action="ajaxDoGetLcGeneration"
											namespace="/admin" >
											<s:param name="anyTitle">LC</s:param>
											</s:url>
										<sj:a id="doLcGeneration" href="%{urlDoLcGeneration}"
											targets="mainContentDiv" cssClass="ajaxify SSLCS">LC Generation</sj:a>
									</li>
								</ul>
							</li>
							<%-- <li>
								<s:url id="urlUploadStudentsDocuments"
									action="ajaxViewUploadOrDownloadDocumentsInfo"
									namespace="/student" />
								<sj:a id="urlUploadStudentsDocuments"
									href="%{urlUploadStudentsDocuments}" targets="mainContentDiv"
									cssClass="ajaxify AUDS"> Upload/Download Documents</sj:a>
							</li> --%>
						</ul>
					</li>
					
					<li id="ExaminationId">
						<a
							href="${pageContext.request.contextPath}/exam/manageExaminationDetails.do?id=admin"
							id="ESD"> <i class="fa fa fa-bell"></i> <span class="title">Examination
								Section</span> <span class="selected"></span> <span class="arrow"></span>
						</a>
						<ul class="sub-menu">
							<li id="gradesAndExamTypes">
								<s:url id="urlExamTypesSettings" action="ajaxManageExamSettings"
									namespace="/exam">
								</s:url>
								<sj:a href="%{urlExamTypesSettings}" targets="mainContentDiv"
									cssClass="ajaxify GAE"> Grades & Exam Types</sj:a>
							</li>
							<li id="examSchedulesDiv">
								<s:url id="urlExamShedules" action="ajaxDoExamShedules"
									namespace="/exam"></s:url>
								<sj:a href="%{urlExamShedules}" targets="mainContentDiv"
									cssClass="ajaxify ES"> Exam Schedules</sj:a>
							</li>
							<li>
								<s:url id="doStudActivitiesSettings"
									action="ajaxManageStudentActivities" namespace="/exam" />
								<sj:a id="doStudActivitiesSettings"
									href="%{doStudActivitiesSettings}" targets="mainContentDiv"
									cssClass="ajaxify MSA">
								 Manage Students Activities</sj:a>
							</li>						
							<li>
								<s:url id="urlStudentMarksSheet" action="ajaxManageStudentMarks"
									namespace="/exam" />
								<sj:a id="studentMarkSheet" href="%{urlStudentMarksSheet}"
									targets="mainContentDiv" cssClass="ajaxify DM">
								 Download / Upload Marks Sheet</sj:a>
							</li>	
							<li>
								<s:url id="urlDoScoreCard"
									action="ajaxManageScorecardGeneration" namespace="/exam" />
								<sj:a id="urlDoScoreCard" href="%{urlDoScoreCard}"
									title="It generates score card based on templates."
									targets="mainContentDiv" cssClass="ajaxify SCG">
							Scorecard</sj:a>
							</li>						
							<li>
								<s:url id="dosendMarksToMobile"
									action="ajaxDoViewClassesHaveMarks" namespace="/exam" />
								<sj:a id="dosendMarksToMobile" href="%{dosendMarksToMobile}"
									targets="mainContentDiv" cssClass="ajaxify ESMTM">
								 Send Marks To Mobile</sj:a>
							</li>
							<%--<li>
								<s:url id="doScoreCardGen"
									action="ajaxManageScorecardGeneration" namespace="/exam" />
								<sj:a id="doScoreCardGen" href="%{doScoreCardGen}"
									targets="mainContentDiv" cssClass="ajaxify SCG">
								 Score Card Generation</sj:a>
							</li>
							--%>
							<li>
								<s:url id="promotionReportNav"
									action="ajaxManagePromotionReports" namespace="/exam" />
								<sj:a id="promotionReportNav" href="%{promotionReportNav}"
									targets="mainContentDiv" cssClass="ajaxify MPR">
								 Manage Promotion Reports</sj:a>
							</li>
						</ul>
					</li>
					<%-- <li>
						<a href="javascript:;" id="hostelLeaves"> <i
							class="fa fa-clock-o"></i> <span class="title">Events</span> <span
							class="arrow"></span> <span class="selected"></span> </a>
						<ul class="sub-menu">
							<li>
									<s:url id="urlDoViewEventLink"
										action="ajaxStaffEvents" namespace="/staff" />
									<sj:a id="registerViewEventLink"
										href="%{urlDoViewEventLink}"
										targets="mainContentDiv" cssClass="ajaxify EVVH">
										Events</sj:a>
								</li>
						</ul>
					</li> --%>
					<li>
						<a href="javascript:;" id="holidaysview"> <i
							class="fa fa-calendar"></i> <span class="title">Holidays</span> <span
							class="arrow"></span> <span class="selected"></span> </a>
						<ul class="sub-menu">
							<li>
								<s:url id="urlDoViewHolidayLink"
									action="ajaxViewSchoolSettingsHolidays" namespace="/admin" />
								<sj:a id="viewHolidayLink"
									href="%{urlDoViewHolidayLink}"
									targets="mainContentDiv" cssClass="ajaxify HVPV">
									Holidays</sj:a>
							</li>
						</ul>
					</li>
					<li>
						<a
							href="${pageContext.request.contextPath}/staff/manageStaffLeaves.do?id=sManageLeaves"
							id="sManageLeaves"> <i class="fa fa-asterisk"></i> <span
							class="title">Leaves Management</span> <span class="selected"></span>
						</a>
					</li>
					<li id="admissionsNav">
						<a href="javascript:;" id="ADMS"> <i class="fa fa-sun-o"></i>
							<span class="title">Admissions</span> <span class="selected"></span>
							<span class="arrow"></span> </a>
						<ul class="sub-menu">
							<li id="applicationsNav">
								<s:url id="urlGetAdmissions" action="ajaxGetOnlineAdmissions"
									namespace="/admin" />
								<sj:a id="admissionDetails" href="%{urlGetAdmissions}"
									targets="mainContentDiv" cssClass="ajaxify ADMS">Application Details</sj:a>
							</li>
							<li id="approvedStudsNav">
								<s:url id="urlPendingApplications"
									action="ajaxApprovedApplicationsHome" namespace="/admin" />
								<sj:a id="urlPendingApplications"
									href="%{urlPendingApplications}" targets="mainContentDiv"
									cssClass="ajaxify SLAP">Shortlisted Applications</sj:a>
							</li>
							<li id="admissoinSettingsNav">
								<s:url id="urlRejectedApplications"
									action="ajaxRejectedApplications" namespace="/admin" />
								<sj:a id="urlRejectedApplications"
									href="%{urlRejectedApplications}" targets="mainContentDiv"
									cssClass="ajaxify RAP">Rejected Applications</sj:a>
							</li>
							<%-- <li id="admissionSettgs">
								<s:url id="urlGetAdmissionSettings"
									action="ajaxAdmissionSettingsHome" namespace="/admin" />
								<sj:a id="classDetails" href="%{urlGetAdmissionSettings}"
									targets="mainContentDiv" cssClass="ajaxify ADST">Admission Settings</sj:a>
							</li> --%>
							<li>
								<s:url id="urlGetCastSettings" action="ajaxCastSettingsHome"
									namespace="/admin" />
								<sj:a id="castDetails" href="%{urlGetCastSettings}"
									targets="mainContentDiv" cssClass="ajaxify CACS">Community &amp; Caste Settings</sj:a>
							</li>
							<li>
								<s:url id="urlGetAdmittedStudentsIdCard"
									action="ajaxIDCardsGenerationForAdmittedStu" namespace="/admin" />
								<sj:a id="getAdmittedStudentsIdCard"
									href="%{urlGetAdmittedStudentsIdCard}" targets="mainContentDiv"
									cssClass="ajaxify CACS">Admitted Students Id Cards</sj:a>
							</li>
							<li>
								<s:url id="urlGetEditAdmissions"
									action="ajaxGetEditOnlineAdmissions" namespace="/admin" />
								<sj:a id="editAdmissionDetails" href="%{urlGetEditAdmissions}"
									targets="mainContentDiv" cssClass="ajaxify UAP">Update Applications</sj:a>
							</li>
						</ul>
					</li>
					<li id="admissionsNav">
						<a href="javascript:;" id="LR"> <i class="fa fa-book"></i> <span
							class="title">Library</span> <span class="selected"></span> <span
							class="arrow"></span> </a>
						<ul class="sub-menu">
						<li>
								<s:url id="issuedAndRequestBooksList"
									action="ajaxDoIssuedAndRequestBooks" namespace="/library"
									includeParams="all" escapeAmp="false">
									<s:param name="anyId">RR</s:param>
								</s:url>
								<sj:a href="%{issuedAndRequestBooksList}"
									targets="mainContentDiv" cssClass='ajaxify LPR'>
											Student / Staff Requests</sj:a>
							</li>
						</ul>
					</li>
					
				</s:elseif>
				<s:elseif test='%{user.isSchoolFinance=="Y" || user.isTransportFinance=="Y" || user.isHostelFinance=="Y"}'>
					<li class="active">
						<a
							href="${pageContext.request.contextPath}/schoolfee/adminGetSchoolFee.do?id=sFinanceHome"
							id="sFinanceHome" class="start"> <i class="fa fa-money"></i>
							<span class="title">Student Invoice</span> </a>
					</li>
					<li>
						<!-- <a href="javascript:;" id="TFS"> <i class="fa fa-cogs"></i><span
							class="title">Fee Settings</span> <span class="selected"></span>
							<span class="arrow"></span> </a> -->
						<ul class="sub-menu">
							<li>
								<s:url id="urlExamSettings" action="ajaxSchoolFeeSetting"
									namespace="/schoolfee">
								</s:url>
								<sj:a href="%{urlExamSettings}" targets="mainContentDiv"
									cssClass="ajaxify TTF">Terms &amp; Fees</sj:a>
							</li>
							<li>
								<s:url id="urlExamSettings"
									action="ajaxAdminGetFourteenSchoolFee" namespace="/admin">
								</s:url>
								<sj:a href="%{urlExamSettings}" targets="mainContentDiv"
									cssClass="ajaxify TPD">Payment Defaulters</sj:a>
							</li>
						</ul>
					</li>
					<s:if test='%{customerByCustId.accountModuleUsing=="Y"}'>
						<li id="staffAccountsDiv">
							<a href="javascript:;" id="ADM"> <i class="fa fa-dribbble"></i>
								<span class="title">Accounts</span> <span class="selected"></span>
								<span class="arrow"></span> </a>
							<ul class="sub-menu">
								<li>
	                               <s:url id="createMaster" action="ajaxViewCreateAccountMaster" namespace="/account"></s:url>
	                               <sj:a href="%{createMaster}" targets="mainContentDiv" cssClass="ajaxify ADCM"> Master Accounts</sj:a>
	                           </li>
	                           <li>
	                               <s:url id="cashBookDetails" action="ajaxViewCashBookDetails" namespace="/account"></s:url>
	                               <sj:a href="%{cashBookDetails}" targets="mainContentDiv" cssClass="ajaxify CASHB"> Cash Book</sj:a>
	                           </li>
								<%-- <li>
								<s:url id="urtDayBookLink" action="ajaxDoGetDayBookDetails" namespace="/admin"></s:url>
								<sj:a href="%{urtDayBookLink}" targets="mainContentDiv" cssClass="ajaxify DBAF"> Manage Voucher</sj:a>
							</li>
							<li>
									<s:url id="urlViewLedger" action="ajaxViewLedgerVoucher" namespace="/admin"/>
										<sj:a href="%{urlViewLedger}"
										targets="mainContentDiv" indicator="indicator" cssClass="ajaxify AML">Manage Ledger</sj:a>
								</li>
								
								<li>
									<s:url id="urlViewgroup" action="ajaxViewGroupVoucher" namespace="/admin"/>
									<sj:a href="%{urlViewgroup}"
										targets="mainContentDiv" indicator="indicator" cssClass="ajaxify AMG">Manage groups</sj:a>
								</li> --%>
							</ul>
						</li>
					</s:if>
					
					<li>
						<a href="javascript:;" id="holidaysview"> <i
							class="fa fa-calendar"></i> <span class="title">Holidays</span> <span
							class="arrow"></span> <span class="selected"></span> </a>
						<ul class="sub-menu">
							<li>
								<s:url id="urlDoViewHolidayLink"
									action="ajaxViewSchoolSettingsHolidays" namespace="/admin" />
								<sj:a id="viewHolidayLink"
									href="%{urlDoViewHolidayLink}"
									targets="mainContentDiv" cssClass="ajaxify HVPV">
									Holidays</sj:a>
							</li>
						</ul>
					</li>
					<li>
						<s:url id="urlTeacherLeaveLink" action="ajaxDoGetLeaveDetailsLeft"
							namespace="/staff" />
						<sj:a href="%{urlTeacherLeaveLink}" targets="mainContentDiv"
							cssClass="ajaxify fINML">
							<i class="fa fa-asterisk"></i> 
							Leave Management</sj:a>
					</li>
					<s:if test='%{user.isSchoolFinance == "Y"}'>
						<%-- <li>
							<a href="javascript:;" id="hostelLeaves"> <i
								class="fa fa-clock-o"></i> <span class="title">Events</span> <span
								class="arrow"></span> <span class="selected"></span> </a>
							<ul class="sub-menu">
								<li>
									<s:url id="urlDoViewEventLink"
										action="ajaxStaffEvents" namespace="/staff" />
									<sj:a id="registerViewEventLink"
										href="%{urlDoViewEventLink}"
										targets="mainContentDiv" cssClass="ajaxify EVVH">
										Events</sj:a>
								</li>
							</ul>
						</li> --%>
						<li>
							<a href="javascript:;" id="LPRY"> <i class="fa fa-book"></i> <span
								class="title">Library</span> <span class="selected"></span> <span
								class="arrow "></span> </a>
							<ul class="sub-menu">
								<li>
									<s:url id="issuedAndRequestBooksList" action="ajaxDoIssuedAndRequestBooks" 
										 namespace="/library" includeParams="all" escapeAmp="false">
										<s:param name="anyId">RR</s:param>
									</s:url> 
									<sj:a href="%{issuedAndRequestBooksList}"
										targets="mainContentDiv" cssClass='ajaxify LPR'>
											Online Requested Books
											
									</sj:a>
								</li>
							</ul>
						</li>
					</s:if>
				</s:elseif>
				<s:elseif test='%{user.isTransportFinance=="Y"}'>
					<li>
						<a
							href="${pageContext.request.contextPath}/schoolfee/adminGetSchoolFee.do?id=tFinanceHome"
							id="tFinanceHome" class="start"> <i class="fa fa-money"></i>
							<span class="title">Student Invoice</span> </a>
					</li>
					<li>
						<!-- <a href="javascript:;" id="TFS"> <i class="fa fa-gamepad"></i>
							<span class="title">Fee Settings</span> <span class="selected"></span>
							<span class="arrow"></span> </a> -->
						<ul class="sub-menu">
							<li>
								<s:url id="urlStudyClass" action="ajaxDoFeeCategory"
									namespace="/schoolfee" />
								<sj:a href="%{urlStudyClass}" targets="mainContentDiv"
									cssClass='ajaxify AAFM'> Annual Fee</sj:a>
							</li>
							<li>
								<s:url id="urlExamSettings"
									action="ajaxAdminGetFourteenSchoolFee" namespace="/admin">
								</s:url>
								<sj:a href="%{urlExamSettings}" targets="mainContentDiv"
									cssClass="ajaxify TPD">Payment Defaulters</sj:a>
							</li>
							<li>
								<s:url id="urlExamSettings" action="ajaxSchoolFeeSetting"
									namespace="/schoolfee">
								</s:url>
								<sj:a href="%{urlExamSettings}" targets="mainContentDiv"
									cssClass="ajaxify TTF">Terms &amp; Fees</sj:a>
							</li>
						</ul>
					</li>
					<li>
						<a
							href="${pageContext.request.contextPath}/staff/manageStaffLeaves.do?fmanageLeaves"
							id="fmanageLeaves"> <i class="fa fa-asterisk"></i> <span
							class="title">Leaves Management</span> </a>
					</li>
				</s:elseif>
				<s:elseif test='%{user.isHostelFinance=="Y"}'>
					<li>
						<a
							href="${pageContext.request.contextPath}/schoolfee/adminGetSchoolFee.do?id=hFinanceHome"
							id="hFinanceHome" class="start"> <i class="fa fa-money"></i>
							<span class="title">Student Invoice</span> </a>
					</li>
					<li>
						<%-- <a
							href="${pageContext.request.contextPath}/schoolfee/adminAllSchoolFeeSettings.do?id=hFeeSetings"
							id="hFeeSetings"> <i class="fa fa-gamepad"></i> <span
							class="title">Fee Settings</span> </a> --%>
					</li>
					<li>
						<a
							href="${pageContext.request.contextPath}/staff/manageStaffLeaves.do?id=hLeaves"
							id="hLeaves"> <i class="fa fa-asterisk"></i> <span
							class="title">Leave Management</span> </a>
					</li>
				</s:elseif>

				<s:elseif test='%{user.isMasterAdmin == "Y"}'>
					<li class="start active">
						<a href="javascript:;" id="MMSR"> <i class="fa fa-home"></i> <span
							class="title">Master Admin</span> <span class="selected"></span>
							<span class="arrow "></span> </a>

						<ul class="sub-menu" id="dynamicKvideosDiv">
						<li class="active">
								<s:url id="customerDetails" action="ajaxEditCustomerSettings"
									namespace="/masterAdmin" />
								<sj:a href="%{customerDetails}" targets="mainContentDiv"
									cssClass='ajaxify MMSAR'>Customers</sj:a>
							</li>
						<li>
								<s:url id="urlAddPackageLink"
									action="ajaxDoAddNewCustomerRegister" namespace="/signup" />
								<sj:a href="%{urlAddPackageLink}" targets="mainContentDiv"
									cssClass='ajaxify MMSR'>
								Customer Registration</sj:a>
							</li>

							
<%-- 							<li id="govtCustomersList">
								<s:url id="urlGovtCustomersLink" action="ajaxGovtCustomersList"
									namespace="/signup" />
								<sj:a href="%{urlGovtCustomersLink}" targets="mainContentDiv"
									cssClass='ajaxify MGC'>Govt Customers</sj:a>
							</li>
 --%>							<li>
								<s:url id="urlDoCreateMasterLink" action="ajaxDoCreateMaster"
									namespace="/masterAdmin" />
								<sj:a href="%{urlDoCreateMasterLink}" targets="mainContentDiv"
									cssClass='ajaxify MMSAR'>Multi Branch Admin Register</sj:a>
							</li>
<%-- 							<li>
								<s:url id="urlSMSUrl" action="ajaxManageSMSProviders"
									namespace="/masterAdmin" />
								<sj:a href="%{urlSMSUrl}" targets="mainContentDiv"
									cssClass='ajaxify MMSP'>Manage SMS Providers</sj:a>
							</li>
 --%>							
							<li>
								<s:url id="urlOrganizationCustomerDetailsLink"
									action="ajaxOranizationCustomerView" namespace="/masterAdmin" />
								<sj:a href="%{urlOrganizationCustomerDetailsLink}" targets="mainContentDiv"
									cssClass='ajaxify OCD'>
								Organization Customer Details</sj:a>
							</li>
							<li>
								<s:url id="urlOrganizationMemberDetailsLink"
									action="ajaxOrganizationMemberDetails" namespace="/masterAdmin" />
								<sj:a href="%{urlOrganizationMemberDetailsLink}" targets="mainContentDiv"
									cssClass='ajaxify OMD'>
								Organization Members Details</sj:a>
							</li>
							<li>
								<s:url id="urlSendSMSToALLCustomers"
									action="ajaxSendSMSToALLCustomersDetails" namespace="/masterAdmin" />
								<sj:a href="%{urlSendSMSToALLCustomers}" targets="mainContentDiv"
									cssClass='ajaxify OMD'>
								Send SMS To ALL Customers</sj:a>
							</li>
							<li>
								<s:url id="urlNewFeturesCustomers" action="ajaxDoSendSMSNewFeatures" namespace="/masterAdmin" />
								<sj:a href="%{urlNewFeturesCustomers}" targets="mainContentDiv"
									cssClass='ajaxify OMD'>
								Send New Feautures</sj:a>
							</li>
							<%-- <li>
								<a 	href="${pageContext.request.contextPath}/masterAdmin/ajaxCreateCustomerRecordToStaffTable.do?id=masterAdminStaffCretaeCustomer"
									id="masterAdminStaffCretaeCustomer">
									<span class="title">Create Customer To Staff</span> 
								</a>
							</li> --%>
							<li>
								<s:url id="urlSendSMSToALLCustomers"
									action="ajaxSMSToALLCustomersLoginDetails" namespace="/masterAdmin" />
								<sj:a href="%{urlSendSMSToALLCustomers}" targets="mainContentDiv"
									cssClass='ajaxify SEMD'>
									Send Login Credentials To Customers</sj:a>
							</li>
							
<%-- 							<li>
								<s:url id="urlNewCustomers"
									action="ajaxViewNewCustomersDetails" namespace="/masterAdmin" />
								<sj:a href="%{urlNewCustomers}" targets="mainContentDiv"
									cssClass='ajaxify VNCD'>
									New Customers</sj:a>
							</li>
							
							<li>
								<s:url id="urlManageCustomerRegistrationDetails"
									action="ajaxManageCustomerRegistrationDetails" namespace="/masterAdmin" />
								<sj:a href="%{urlManageCustomerRegistrationDetails}" targets="mainContentDiv"
									cssClass='ajaxify VNCD'>
									Manage Customer Registration</sj:a>
							</li>
 --%>							
							
							
							
						</ul>
					</li>
					<li>
						<a
							href="${pageContext.request.contextPath}/schoolfee/deleteInvoiceCustomerStudentWiseDetails.do?id=masterAdminDeleteInvoice"
							id="masterAdminDeleteInvoice"> <i class="fa fa-home"></i> <span
							class="title">Delete Invoice</span> </a>
					</li>
<%-- 					<li>
						<a
							href="${pageContext.request.contextPath}/masterAdmin/masterAdminHome.do?id=masterAdminPackages"
							id="masterAdminPackages"> <i class="fa fa-home"></i> <span
							class="title">Packages</span> </a>
					</li> --%>
					<li>
						<a
							href="${pageContext.request.contextPath}/masterAdmin/feedbackMasterAdminHome.do?id=masterAdminFeedBack"
							id="masterAdminFeedBack"> <i class="fa fa-home"></i> <span
							class="title">Support Ticket</span> </a>
					</li>
				</s:elseif>
				
				<s:if test="%{customerByCustId.hostelModuleStatus}">
					<s:if test='%{user.isOnlySchoolAdmin == "Y" || user.isSchoolHostel == "Y"}'>
						<li>
							<a href="javascript:;" id="AMM"> <i class="fa fa-bookmark-o"></i>
								<span class="title">Mess Management</span> <span class="selected"></span>
								<span class="arrow"></span> </a>
							<ul class="sub-menu">
								<li>
									<s:url id="urlMessManagement"
										action="ajaxViewMessManagementHome" namespace="/hostel"></s:url>
									<sj:a id="messManagement" href="%{urlMessManagement}"
										targets="mainContentDiv" cssClass="ajaxify AMM">Mess Management</sj:a>
								</li>
								 <li>
									<s:url id="urlManageProvisionStore"
										action="ajaxManageProvisionStoreHome" namespace="/hostel"></s:url>
									<sj:a id="manageProvisionStore" href="%{urlManageProvisionStore}"
										targets="mainContentDiv" cssClass="ajaxify MPS">Manage Provision Store</sj:a>
								</li>
							</ul>
						</li>
					</s:if>
				</s:if>
				<s:if test='%{user.isOnlySchoolAdmin == "Y" }'>
					<li id="adminLibraryDiv">
					<a href="javascript:;" id="LR"> <i class="fa fa-book"></i> <span
						class="title">Library</span> <span class="selected"></span> <span
						class="arrow"></span> </a>
					<ul class="sub-menu">
						<li id="viewBookResults" class="active">
							<s:url id="viewBookResults" action="ajaxLibraryHome"
								namespace="/library">
							</s:url>
							<sj:a href="%{viewBookResults}" id="stockMaitenance"
								targets="mainContentDiv" cssClass='ajaxify LSM'>
										Stock Maintenance</sj:a>
						</li>
						<li>
							<s:url id="issuedBookDetail" action="ajaxDoIssuedBookDetail"
								namespace="/library">
							</s:url>
							<sj:a href="%{issuedBookDetail}" targets="mainContentDiv"
								cssClass='ajaxify LRB'>
										Issue / Return Books</sj:a>
						</li>
						<li>
							<s:url id="issuedAndRequestBooksList"
								action="ajaxDoIssuedAndRequestBooks" namespace="/library"
								includeParams="all" escapeAmp="false">
								<s:param name="anyId">RR</s:param>
							</s:url>
							<sj:a href="%{issuedAndRequestBooksList}"
								targets="mainContentDiv" cssClass='ajaxify LPR'>
										Student / Staff Requests</sj:a>
						</li>
						<li>
							<s:url id="librarySettings" action="ajaxViewLibrarySettings"
								namespace="/library">
							</s:url>
							<sj:a href="%{librarySettings}" targets="mainContentDiv"
								cssClass='ajaxify LLS'>
										Library Settings</sj:a>
						</li>
						<%--
							<li>
								<s:url id="viewAllIssuedBooks" action="ajaxDoViewAllIssuedBooks"
									namespace="/library">
								</s:url>
								<sj:a href="%{viewAllIssuedBooks}" targets="mainContentDiv"
									cssClass='ajaxify LLB'>
										Issued Books</sj:a>
							</li>
							--%>
						<li>
							<s:url id="manageRacks" action="ajaxDoBlockDetails"
								namespace="/library">
							</s:url>
							<sj:a href="%{manageRacks}" targets="mainContentDiv"
								cssClass='ajaxify LMB'>
										Manage Blocks & Racks</sj:a>
						</li>
						<li id="viewStudLibIdCards">
							<s:url id="viewStudLibIdCards" action="ajaxViewStudentLibraryIdCasrds" namespace="/library">
							</s:url>
							<sj:a href="%{viewStudLibIdCards}" id="StudLibIdCards"
								targets="mainContentDiv" cssClass='ajaxify SLIG'>
										Id Card Generation</sj:a>
						</li>
					</ul>
					</li>
					
				</s:if>
				<s:else>
					<s:if
						test='%{user.isOnlySchoolHod=="Y" || user.isOnlySchoolTeacher=="Y" || user.isSchoolClerk=="Y" || user.isSchoolFinance=="Y" || user.isAdminCoordinator == "Y"}'>
						 <li>
							<a href="javascript:;" id="HASE"> <i class="fa fa-envelope"></i> <span class="title">Inbox</span> <span class="selected"></span> <span class="arrow"></span>
							 </a>
							<ul class="sub-menu">
								<li>
									<s:url id="urlInboxesDetails" action="ajaxDoInboxGetScrapMessagesList" namespace="/common"> </s:url>
									<sj:a href="%{urlInboxesDetails}" targets="mainContentDiv" cssClass='ajaxify'>My Inbox</sj:a>
								</li>
								<li>
									<s:url id="urlDashboardDetails" action="ajaxDoGetSchoolWideAlertsList" namespace="/common"> </s:url>
									<sj:a href="%{urlDashboardDetails}" targets="mainContentDiv" cssClass='ajaxify'> Dashboard Messages</sj:a>
								</li>
								<li>
									<s:url id="urlCircularMessagesAlerts" action="ajaxDoGetCircularMessagesList" namespace="/common" />
									<sj:a href="%{urlCircularMessagesAlerts}" targets="mainContentDiv" cssClass='ajaxify ACMIL'>Circular Messages</sj:a>
								</li>
							</ul>
						</li>
						<s:if test='%{user.isOnlySchoolTeacher=="Y" && #session.parentMobileNoVisibleToTeacher == "Y"  && tempBoolean  || user.isOnlySchoolHod=="Y" || user.isAdminCoordinator == "Y"}'>
							<li>
								<a href="javascript:;" id="ASMS"><i class="fa fa-comment"></i>
									<span class="title">SMS</span> <span class="selected"></span> <span
									class="arrow"></span> </a>
								<ul class="sub-menu">
									<li>
										<s:url id="urlInboxesDetails" action="ajaxDoGetClassWideMessagesList" namespace="/common" includeParams="all" escapeAmp="false">
											<s:param value="#session.academicYear" name="academicYearId" />
										</s:url>
										<sj:a href="%{urlInboxesDetails}" id="adminSendSms" targets="mainContentDiv" cssClass='ajaxify ASMEL'> Send SMS or E-Mail</sj:a>
									 </li>
							 </ul>
						 </li>
					 </s:if>
					</s:if>
					<s:if
						test='%{user.isOnlySchoolHod=="Y" || user.isAdminCoordinator ="Y"}'>
						<li>
							<a
								href="${pageContext.request.contextPath}/library/getStaffLibrayHome.do?id=staffLibrary"
								id="staffLibrary"> <i class="fa fa-book"></i> <span
								class="title">Library</span> <span class="selected"></span> </a>
						</li>
					</s:if>
					<s:if test='%{user.isSchoolPrincipal=="Y" || user.isOnlySchoolTeacher=="Y" || user.isAdminCoordinator == "Y" || user.isSchoolAsstStaff=="Y" || user.isSchoolDirector == "Y" }'>
						<li>
							<a href="javascript:;" id="VLB"> <i class="fa fa-home"></i> <span
								class="title">Library</span> <span class="selected"></span> <span
								class="arrow "></span> </a>
							<ul class="sub-menu">
								<li>
									<s:url id="urlViewLibraryBooksHistroty" action="ajaxGetStaffLibrayHome"
										namespace="/library" includeParams="all" escapeAmp="false">
									</s:url>
									<sj:a id="viewBookshistory" href="%{urlViewLibraryBooksHistroty}"
										targets="mainContentDiv" cssClass="ajaxify VLB">
									   Issue/Return Books</sj:a>
								</li>
								<li>
									<s:url id="issuedAndRequestBooksList" action="ajaxDoIssuedAndRequestBooks" 
										 namespace="/library" includeParams="all" escapeAmp="false">
										<s:param name="anyId">RR</s:param>
									</s:url> 
									<sj:a href="%{issuedAndRequestBooksList}"
										targets="mainContentDiv" cssClass='ajaxify LPR'>
											Online Requested Books
											
									</sj:a>
								</li>
							</ul>
					</li>
					</s:if>
					<s:if test='%{user.isSchoolHostel == "Y"}'>
						<li class="start active">
							<a href="javascript:;" id="HMB"> <i class="fa fa-home"></i> <span
								class="title">Hostel</span> <span class="selected"></span> <span
								class="arrow "></span> </a>
							<ul class="sub-menu">
								<li class="start active">
									<s:url id="urlManageBuildings"
										action="ajaxViewSchoolBuildingSettings" namespace="/hostel"
										includeParams="all" escapeAmp="false">
										<s:param name="anyTitle">buildings</s:param>
									</s:url>
									<sj:a id="manageBuildings" href="%{urlManageBuildings}"
										targets="mainContentDiv" cssClass='ajaxify HMB'>
									Manage Buildings</sj:a>
								</li>
								<li>
									<s:url id="urlManageFloors" action="ajaxLoadManageInfoByStatus"
										namespace="/hostel" includeParams="all" escapeAmp="false">
										<s:param name="anyTitle">floors</s:param>
									</s:url>
									<sj:a id="manageFloors" href="%{urlManageFloors}"
										targets="mainContentDiv" cssClass="ajaxify HMF">
									Manage Floors</sj:a>
								</li>
								<li>
									<s:url id="urlManageRooms" action="ajaxLoadRoomsBysStatus"
										namespace="/hostel" includeParams="all" escapeAmp="false">
										<s:param name="anyTitle">rooms</s:param>
									</s:url>
									<sj:a id="manageRooms" href="%{urlManageRooms}"
										targets="mainContentDiv" cssClass="ajaxify HMR">
									Manage Rooms</sj:a>
								</li>
								<li>
									<s:url id="urlAssignStudents" action="ajaxDoAssignStudent"
										namespace="/hostel" includeParams="all" escapeAmp="false">
										<s:param name="anyTitle">beds</s:param>
									</s:url>
									<sj:a id="assignStudents" href="%{urlAssignStudents}"
										targets="mainContentDiv" cssClass="ajaxify HAS">
									Assign Students</sj:a>
								</li>
							</ul>
						</li>
						<li>
							<a
								href="${pageContext.request.contextPath}/schoolfee/adminGetSchoolFee.do?id=HostelFee"
								id="HostelFee"> <i class="fa fa-money"></i> <span
								class="title">Student Invoice</span> <span class="selected"></span>
							</a>
						</li>
						<li>
							<a
								href="${pageContext.request.contextPath}/hostel/manageStudentAndStaff.do?id=ManageHostelStudentAndStaff"
								id="ManageHostelStudentAndStaff"> <i class="fa fa-user"></i>
								<span class="title">Manage Student</span> <span
								class="selected"></span> </a>
						</li>
						
						<li id="manageAtt">
							<a href="javascript:;" id="MSAT"> <i class="fa fa-calendar"></i>
								<span class="title">Manage Attendance</span> <span class="selected"></span>
								<span class="arrow "></span> </a>
							<ul class="sub-menu">
							
							<li>
									<s:url id="ajaxClassAttendencelink1" namespace="/admin"
										action="ajaxDoGetAttendanceForm" includeParams="all"
										escapeAmp="false">
									</s:url>
									<sj:a href="%{ajaxClassAttendencelink1}"
										targets="mainContentDiv" cssClass="ajaxify MATNC">Manage Attendance</sj:a>
								</li>
								
						</ul>
					</li>
					<%-- 	<li>
							<a href="javascript:;" id="hostelLeaves"> <i
								class="fa fa-clock-o"></i> <span class="title">Events</span> <span
								class="arrow"></span> <span class="selected"></span> </a>
							<ul class="sub-menu">
								<li>
									<s:url id="urlDoViewEventLink"
										action="ajaxStaffEvents" namespace="/staff" />
									<sj:a id="registerViewEventLink"
										href="%{urlDoViewEventLink}"
										targets="mainContentDiv" cssClass="ajaxify EVVH">
										Events</sj:a>
								</li>
							</ul>
						</li> --%>
						<li>
							<a href="javascript:;" id="holidaysview"> <i
								class="fa fa-calendar"></i> <span class="title">Holidays</span> <span
								class="arrow"></span> <span class="selected"></span> </a>
							<ul class="sub-menu">
								<li>
									<s:url id="urlDoViewHolidayLink"
										action="ajaxViewSchoolSettingsHolidays" namespace="/admin" />
									<sj:a id="viewHolidayLink"
										href="%{urlDoViewHolidayLink}"
										targets="mainContentDiv" cssClass="ajaxify HVPV">
										Holidays</sj:a>
								</li>
							</ul>
						</li>
						<li>
							<a href="javascript:;" id="hostelLeaves"> <i
								class="fa fa-asterisk"></i> <span class="title">Leaves</span> <span
								class="arrow"></span> <span class="selected"></span> </a>
							<ul class="sub-menu">
								<li>
									<s:url id="urlmanageStaffLeaves"
										action="ajaxDoGetLeaveDetailsLeft" namespace="/staff" />
									<sj:a id="urlmanageStaffLeaves" href="%{urlmanageStaffLeaves}"
										targets="mainContentDiv" cssClass="ajaxify hostelLeaves">
									Leaves Management</sj:a>
								</li>
							</ul>
						</li>
						<li>
							<a href="javascript:;" id="HostelInbox"> <i
								class="fa fa-envelope"></i> <span class="title">Inbox</span> <span
								class="selected"></span> <span class="arrow"></span> </a>
							<ul class="sub-menu">
								<li>
									<s:url id="urlInboxesDetails"
										action="ajaxDoInboxGetScrapMessagesList" namespace="/common">
									</s:url>
									<s:param value="#session.academicYear" name="academicYearId" />
									<sj:a href="%{urlInboxesDetails}" targets="mainContentDiv"
										cssClass='ajaxify HMAS'>
									Messages & Alerts</sj:a>
								</li>
								<li>
									<s:url id="urlInboxesDetails"
										action="ajaxDoGetSchoolWideAlertsList" namespace="/common">
									</s:url>
									<s:param value="#session.academicYear" name="academicYearId" />
									<sj:a href="%{urlInboxesDetails}" targets="mainContentDiv"
										cssClass='ajaxify HDMS'>
									Dashboard Messages</sj:a>
								</li>
								<li>
									<s:url id="urlCircularMessages"
										action="ajaxDoGetCircularMessagesList" namespace="/staff">
									</s:url>
									<sj:a href="%{urlCircularMessages}" targets="mainContentDiv"
										cssClass='ajaxify ACMH'>Circular Messages</sj:a>
								</li>
							</ul>
						</li>
						<li>
							<a href="javascript:;" id="ASMS"><i class="fa fa-comment"></i>
								<span class="title">SMS</span> <span class="selected"></span> <span
								class="arrow"></span> </a>
							<ul class="sub-menu">
								<li>
									<s:url id="urlInboxesDetails"
										action="ajaxDoGetSchoolWideMessagesList" namespace="/common"
										includeParams="all" escapeAmp="false">
										<s:param value="#session.academicYear" name="academicYearId" />
									</s:url>
									<sj:a href="%{urlInboxesDetails}" targets="mainContentDiv"
										cssClass='ajaxify ASMEL'>
											Send SMS or E-Mail</sj:a>
							 </li>
						 </ul>
					 </li>
					</s:if>
					<s:if test='%{user.isSchoolHostel !=  "Y"}'>
						<s:if test='%{user.isSchoolTransport == "Y"}'>
							<li class="start active">
								<a href="/admin/transportDashboard.do" id="TMTP"><i
									class="fa fa-truck"></i> <span class="title">Transport</span> <span
									class="selected"></span> <span class="arrow"></span> </a>
								<ul class="sub-menu">
									<li class="start active">
										<s:url id="urlManageRoute" action="ajaxManageRoutes"
											namespace="/admin" />
										<sj:a id="manageRoute" href="%{urlManageRoute}"
											targets="mainContentDiv" cssClass="ajaxify TMTP">
									 Manage Route</sj:a>
									</li>
									<li>
										<s:url id="doManageDriverHelper"
											action="ajaxAddDriverOrHelperStaff" namespace="/admin" />
										<sj:a id="manageDriverHelper" href="%{doManageDriverHelper}"
											targets="mainContentDiv" cssClass="ajaxify MDH">
									 Manage Driver/Helper</sj:a>
									</li>
									<li>
										<s:url id="doVehicleMaintenance"
											action="ajaxManageTransportVehicles" namespace="/admin" />
										<sj:a id="vehicleMaintenance" href="%{doVehicleMaintenance}"
											targets="mainContentDiv" cssClass="ajaxify MVT">
									 Vehicle Maintenance</sj:a>
									</li>
									<li>
										<s:url id="doManageVehicleRoutes"
											action="ajaxManageVehicleRoutes" namespace="/admin" />
										<sj:a id="manageVehicleRoutes" href="%{doManageVehicleRoutes}"
											targets="mainContentDiv" cssClass="ajaxify MVR">
									 Manage Vehicle Routes</sj:a>
									</li>
									<li>
										<s:url id="doAssignStudents" action="ajaxDoAssignStudent"
											namespace="/admin" />
										<sj:a id="doTransportAssignStudents"
											href="%{doAssignStudents}" targets="mainContentDiv"
											cssClass="ajaxify MAS">
									 Assign Students</sj:a>
									</li>
									<s:if test="%{#session.AllExpiryDates == true}">
										<li>
											<s:url id="urlManageTransports"
												action="ajaxDoGetVehiclesInformation" namespace="/admin">
												<s:param name="tempBoolean" value="true"></s:param>
											</s:url>
											<sj:a href="%{urlManageTransports}" id="expiryDates"
												targets="mainContentDiv" cssClass="ajaxify TRW">Transport Warnings</sj:a>
										</li>
									</s:if>
								</ul>
							</li>
						<%-- 	<li>
							<a href="javascript:;" id="hostelLeaves"> <i
								class="fa fa-clock-o"></i> <span class="title">Events</span> <span
								class="arrow"></span> <span class="selected"></span> </a>
							<ul class="sub-menu">
								<li>
									<s:url id="urlDoViewEventLink"
										action="ajaxStaffEvents" namespace="/staff" />
									<sj:a id="registerViewEventLink"
										href="%{urlDoViewEventLink}"
										targets="mainContentDiv" cssClass="ajaxify EVVH">
										Events</sj:a>
								</li>
							</ul>
						</li> --%>
						<li>
							<a href="javascript:;" id="holidaysview"> <i
								class="fa fa-calendar"></i> <span class="title">Holidays</span> <span
								class="arrow"></span> <span class="selected"></span> </a>
							<ul class="sub-menu">
								<li>
									<s:url id="urlDoViewHolidayLink"
										action="ajaxViewSchoolSettingsHolidays" namespace="/admin" />
									<sj:a id="viewHolidayLink"
										href="%{urlDoViewHolidayLink}"
										targets="mainContentDiv" cssClass="ajaxify HVPV">
										Holidays</sj:a>
								</li>
							</ul>
						</li>
							<li>
								<a
									href="${pageContext.request.contextPath}/schoolfee/adminGetSchoolFee.do?id=hInvoice"
									id="hInvoice"> <i class="fa fa-money"></i> <span
									class="title">Student Invoice</span> <span class="selected"></span>
								</a>
							</li>
							<li>
								<!-- <a href="javascript:;" id="TPD"> <i class="fa fa-gamepad"></i>
									<span class="title">Fee Settings</span> <span class="selected"></span>
									<span class="arrow"></span> </a> -->
								<ul class="sub-menu">
									<%-- <li>
										<s:url id="urlStudyClass" action="ajaxDoFeeCategory"
											namespace="/schoolfee" />
										<sj:a href="%{urlStudyClass}" targets="mainContentDiv"
											cssClass='ajaxify TFS'>
										Annual Fee</sj:a>
									</li> --%>
									<li>
										<s:url id="urlExamSettings"
											action="ajaxAdminGetFourteenSchoolFee" namespace="/admin">
										</s:url>
										<sj:a href="%{urlExamSettings}" targets="mainContentDiv"
											cssClass="ajaxify TPD">
										Payment Defaulters</sj:a>
									</li>
									<li>
										<s:url id="urlExamSettings" action="ajaxSchoolFeeSetting"
											namespace="/schoolfee">
										</s:url>
										<sj:a href="%{urlExamSettings}" targets="mainContentDiv"
											cssClass="ajaxify TTF">
										Terms &amp; Fees</sj:a>
									</li>
									<%-- <li>
										<s:url id="urtTransportAdminDayBookLink"
											action="ajaxDoGetDayBookDetails" namespace="/admin"></s:url>
										<sj:a href="%{urtTransportAdminDayBookLink}"
											targets="mainContentDiv" cssClass="ajaxify TDBAF"> Manage Voucher</sj:a>
									</li> --%>
								</ul>
							</li>
						</s:if>
					</s:if>
					<s:if test='%{user.isSchoolTransport == "Y"}'>
						<li>
							<a
								href="${pageContext.request.contextPath}/staff/manageStaffLeaves.do?id=tManageLeaves"
								id="tManageLeaves"> <i class="fa fa-asterisk"></i> <span
								class="title">Leaves Management</span> <span class="selected"></span>
							</a>
						</li>
					</s:if>
					<s:if test='%{user.isSchoolHostel != "Y" && user.isSchoolAsstStaff == "N"}'>
					   <s:if test='%{user.isMEO != "Y" && user.isDEO != "Y" && user.isSEO != "Y" && user.isSchoolPrincipal=="N" && user.isSchoolDirector == "N"}'>
						 <li>
							<a href="javascript:;" id="HASE"> <i class="fa fa-envelope"></i>
								<span class="title">Inbox</span> <span class="selected"></span>
								<span class="arrow"></span> </a>
							<ul class="sub-menu">
								<s:if
									test='%{user.isSchoolAdmin == "Y" || user.isOnlySchoolHod=="Y" || user.isSchoolTeacher == "Y" || user.isAdminCoordinator == "Y"}'>
									<li>
										<s:url id="urlInboxesDetails"
											action="ajaxDoInboxGetScrapMessagesList" namespace="/common">
										</s:url>
										<s:param value="#session.academicYear" name="academicYearId" />
										<sj:a href="%{urlInboxesDetails}" targets="mainContentDiv"
											cssClass='ajaxify HASE'>
									Messages & Alerts</sj:a>
									</li>
									<li>
										<s:url id="urlInboxesDetails"
											action="ajaxDoGetSchoolWideAlertsList" namespace="/common">
										</s:url>
										<s:param value="#session.academicYear" name="academicYearId" />
										<sj:a href="%{urlInboxesDetails}" targets="mainContentDiv"
											cssClass='ajaxify ADMS'>
									Dashboard Messages</sj:a>
									</li>
									<li>
										<s:url id="urlCircularsMessagesLink" action="ajaxDoGetCircularMessagesList" namespace="/common" />
										<sj:a href="%{urlCircularsMessagesLink}" targets="mainContentDiv"
											cssClass='ajaxify SMCM'>Circular Messages</sj:a>
									</li>
								</s:if>
								<s:else>
									<li>
										<s:url id="urlInboxesDetails"
											action="ajaxDoInboxGetScrapMessagesList" namespace="/common">
										</s:url>
										<sj:a href="%{urlInboxesDetails}" targets="mainContentDiv"
											cssClass='ajaxify'>
										My Inbox </sj:a>
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
										<s:url id="urlCircularMessagesLink"
											action="ajaxDoGetCircularMessagesList" namespace="/comman" />
										<sj:a href="%{urlCircularMessagesLink}" targets="mainContentDiv"
											cssClass='ajaxify CMAD'>Circular Messages</sj:a>
									</li>
								</s:else>
							</ul>
						</li>
						</s:if>
						<s:if test='%{(user.isSchoolTeacher=="N" && !tempBoolean && user.isSchoolPrincipal=="N" && user.isSchoolDirector == "N") 
						&& user.isOnlySchoolHod=="N" && user.isSchoolClerk=="N" && user.isSecretary=="N" && user.isSecretaryPA=="N" 
						&& user.isChairMan =="N" && user.isMasterAdmin=="N" && user.isMessManager=="N" && user.isSchoolTransport!="Y" && user.isSchoolFinance=="N" && user.isAdminCoordinator == "N" && user.isStoreKeeper == "N" && user.isReceptionist == "N" && user.isSchoolLibrarian == "N"  }'>
							<li>
								<a href="javascript:;" id="ASMS"><i class="fa fa-comment"></i>
									<span class="title">SMS</span> <span class="selected"></span> <span
									class="arrow"></span> </a>
								<ul class="sub-menu">
									<s:if test='%{(user.isSchoolTeacher=="N" || user.isAdminCoordinator == "N") && !tempBoolean}'>
										<li>
											<s:url id="urlInboxesDetails"
												action="ajaxDoGetClassWideMessagesList" namespace="/common"
												includeParams="all" escapeAmp="false">
												<s:param value="#session.academicYear" name="academicYearId" />
											</s:url>
											<sj:a href="%{urlInboxesDetails}" targets="mainContentDiv"
												cssClass='ajaxify ACWSM'>
												Class Wide SMS</sj:a>
										</li>
									</s:if>
									<s:elseif test='%{user.isSchoolAdmin=="Y"'>
										<li>
											<s:url id="urlInboxesDetails"
												action="ajaxDoGetSchoolWideMessagesList" namespace="/common"
												includeParams="all" escapeAmp="false">
												<s:param value="#session.academicYear" name="academicYearId" />
											</s:url>
											<sj:a href="%{urlInboxesDetails}" targets="mainContentDiv"
												cssClass='ajaxify ACWSM'>
												Class Wide SMS</sj:a>
										</li>
									</s:elseif>
								</ul>
							</li>
						</s:if>
						<s:elseif test='%{(user.isSchoolPrincipal=="N" && user.isSchoolDirector == "N") && (user.isSchoolAdmin=="Y" || user.isSchoolClerk=="Y" || user.isSchoolAsstStaff=="Y")}'>
							<li id="smsDiv">
								<a href="javascript:;" id="ASMS"><i class="fa fa-comment"></i>
									<span class="title">SMS</span> <span class="selected"></span> <span
									class="arrow"></span> </a>
								<ul class="sub-menu">
									<li id="adminSms">
										<s:url id="urlInboxesDetails"
											action="ajaxDoSendSchoolWideMessages" namespace="/common"
											includeParams="all" escapeAmp="false">
											<s:param value="#session.academicYear" name="academicYearId" />
										</s:url>
										<sj:a href="%{urlInboxesDetails}" targets="mainContentDiv"
											cssClass='ajaxify ASMEL'>
											Admin SMS or E-Mail</sj:a>
									</li>
									<s:if test='%{#session.customerTransportStauts==true}'>
										<s:if test='%{user.isSchoolAdmin=="Y" || user.isSchoolClerk=="Y"}'>
											<li>
												<s:url id="urlTransport"
													action="ajaxDoGetSchoolWideMessagesList" namespace="/common"
													includeParams="all" escapeAmp="false">
													<s:param value="#session.academicYear" name="academicYearId" />
													<s:param name="status">TR</s:param>
												</s:url>
												<sj:a id="urlTransport" href="%{urlTransport}"
													targets="mainContentDiv" cssClass='ajaxify ATSMS'>
											Transport SMS</sj:a>
											</li>
										</s:if>
									</s:if>
								</ul>
							</li>
						</s:elseif>
						<s:elseif test='%{(user.isSchoolTransport=="Y" || user.isSchoolAdmin=="Y") && (user.isSchoolPrincipal=="N" && user.isSchoolDirector == "N")}'>
							<li>
								<a href="javascript:;" id="ASMS"><i class="fa fa-comment"></i>
									<span class="title">SMS</span> <span class="selected"></span> <span
									class="arrow"></span> </a>
								<ul class="sub-menu">
								<s:if test='%{user.isSchoolTransport!="Y"}'>
								<li>
										<s:url id="urlInboxesDetails"
											action="ajaxDoGetSchoolWideMessagesList" namespace="/common"
											includeParams="all" escapeAmp="false">
											<s:param value="#session.academicYear" name="academicYearId" />
										</s:url>
										<sj:a href="%{urlInboxesDetails}" targets="mainContentDiv"
											cssClass='ajaxify ASMEL'>
												Send SMS or E-Mail</sj:a>
									</li>
									</s:if>
									<li>
										<s:url id="urlTransport"
											action="ajaxDoGetSchoolWideMessagesList" namespace="/common"
											includeParams="all" escapeAmp="false">
											<s:param value="#session.academicYear" name="academicYearId" />
											<s:param name="status">TR</s:param>
										</s:url>
										<sj:a id="urlTransport" href="%{urlTransport}"
											targets="mainContentDiv" cssClass='ajaxify ATSMS'>
										Transport SMS</sj:a>
									</li>
								</ul>
							</li>
						</s:elseif>
					</s:if>
				</s:else>
				<s:if test='%{user.schoolAdmin || user.schoolTeacher || user.schoolAsstStaff || user.schoolStudent || user.parent}'>
					<li id="manageSports">
						<a href="javascript:;" id="MSRPTS"><i class="fa fa-user"></i><span class="title">
							Manage Sports</span><span class="selected"></span><span class="arrow "></span></a>
						<ul class="sub-menu">
							<s:if test='%{user.schoolAdmin || user.schoolTeacher || user.schoolAsstStaff}'>
								<li id="sportsInfoDiv">
									<s:url id="urlManageSports" action="ajaxSportsInformationHome"	namespace="/sports" />
									<sj:a id="urlManageSport" href="%{urlManageSports}"  targets="mainContentDiv" cssClass="ajaxify MSRPTS">
										Sports</sj:a>
								</li>
								<li>
									<s:url id="urlManageTeam" action="ajaxTeamInformationHome" namespace="/sports" />
									<sj:a id="urlManageTeam" href="%{urlManageTeam}" targets="mainContentDiv" cssClass="ajaxify MSPRTT">
										Team</sj:a>
								</li>
							</s:if>
							<li>
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
				</s:if>
				<s:if test='%{user.isStoreKeeper == "N" && user.isReceptionist=="N" && user.isSchoolLibrarian == "N" }'>
				<li id="manageEvents">
					<a href="javascript:;" id="EVNTS"><i class="fa fa-music"></i><span class="title">
							Manage Events</span><span class="selected"></span><span class="arrow "></span></a>
					<ul class="sub-menu">
						<li id="eventsInfo">
							<s:if test='%{user.isOnlySchoolAdmin == "Y" || user.isSchoolPrincipal=="Y" || user.isSchoolDirector == "Y" }'>
									<s:url id="viewEvents" action="ajaxViewEvents"	namespace="/admin" />
										<sj:a href="%{viewEvents}" targets="mainContentDiv"	id="viewEvents" cssClass="ajaxify AEV"> 
											<i class="fa fa-music"></i>
												Events
										</sj:a>
							</s:if>
							<s:elseif test='%{user.isSchoolAsstStaff=="Y" || user.isOnlySchoolHod=="Y" || user.isOnlySchoolTeacher=="Y" 
										    || user.isAdminCoordinator == "Y" }'>
									<s:url id="urlEventsLink" action="ajaxStaffEvents"	namespace="/staff" />
										<sj:a href="%{urlEventsLink}" targets="mainContentDiv"	cssClass="ajaxify EVNT">
											<i class="fa fa-music"></i>
												Events
										</sj:a>
							  </s:elseif>
							  <s:elseif test='%{user.isSchoolClerk == "Y" || user.isSchoolFinance == "Y" ||  user.isSchoolHostel == "Y" || user.isSchoolTransport == "Y" }'>
									<s:url id="urlDoViewEventLink"	action="ajaxStaffEvents" namespace="/staff" />
										<sj:a id="registerViewEventLink" href="%{urlDoViewEventLink}"	targets="mainContentDiv" cssClass="ajaxify EVVH">
												<i class="fa fa-music"></i> Events
										</sj:a>
							  </s:elseif>
						</li>	
					</ul>
				</li>
				</s:if> 
				<s:if test='%{customerByCustId.accountModuleUsing=="Y"}'>
					<s:if test='%{user.isSchoolAdmin=="Y" || user.isSchoolPrincipal=="Y" || user.isSchoolDirector == "Y"}'>
						<li id="AccountsDiv">
							<a href="javascript:;" id="ADM"> <i class="fa fa-dribbble"></i>
								<span class="title">Accounts</span> <span class="selected"></span>
								<span class="arrow"></span> </a>
							<ul class="sub-menu">
								<li>
	                               <s:url id="createMaster" action="ajaxViewCreateAccountMaster" namespace="/account"></s:url>
	                               <sj:a href="%{createMaster}" targets="mainContentDiv" cssClass="ajaxify ADCM"> Master Accounts</sj:a>
	                           </li>
	                           <li>
	                               <s:url id="cashBookDetails" action="ajaxViewCashBookDetails" namespace="/account"></s:url>
	                               <sj:a href="%{cashBookDetails}" targets="mainContentDiv" cssClass="ajaxify CASHB"> Cash Book</sj:a>
	                           </li>
							</ul>
						</li>
					</s:if>
				</s:if>
				
				<s:if test='%{user.isCEO != "Y" && user.isDEO != "Y" && user.isSEO != "Y" && user.isBEO != "Y" && user.isMasterAdmin != "Y" && user.isOnlySchoolAdmin == "N" || user.isSchoolPrincipal=="N" || user.isSchoolDirector == "N">}'>
					<li id="addPaySlipsDiv">
						<a href="javascript:;" id="HelpDoc"><i class="fa fa-file-text"></i>
							<span class="title">PaySlips</span> <span class="selected"></span> <span
							class="arrow"></span> </a>
						<ul class="sub-menu">
							<li>
								<s:url id="urlPaySlips" action="ajaxViewStaffPaySlips" namespace="/staff"></s:url>
									<sj:a href="%{urlPaySlips}" targets="mainContentDiv" cssClass='ajaxify SPAS'>PaySlips</sj:a>
							</li>
						</ul>
					</li>
				</s:if>
				
				<%-- <s:if
					test='%{user.isOnlySchoolAdmin=="Y" || user.isSchoolAdmin=="Y" || user.isOnlySchoolHod=="Y" || user.isOnlySchoolTeacher=="Y" || user.isSchoolPrincipal=="Y" || user.isSchoolStudent=="Y"}'>
					<li>
						<a href="javascript:;" id="MKBK"> <i class="fa fa-signal"></i>
							<span class="title">Manage K-Bank</span> <span class="selected"></span>
							<span class="arrow "></span> </a>
						<ul class="sub-menu" id="dynamicKvideosDiv">
							<li>
								<s:url id="urlMyFavouriteLink" action="ajaxGetKBankFavourites"
									namespace="/admin" />
								<sj:a href="%{urlMyFavouriteLink}" targets="mainContentDiv"
									cssClass="ajaxify MKBK">My Favourite</sj:a>
							</li>
							<li>
								<s:url id="urlMyFavouriteLinks" action="ajaxGetKhanPlayList"
									namespace="/admin" />
								<sj:a href="%{urlMyFavouriteLinks}" targets="mainContentDiv"
									cssClass="ajaxify MKWV">Knowledge Videos</sj:a>
							</li>
						</ul>
					</li>
				</s:if> --%>
				<s:if test='%{user.isDEO == "Y" || user.isBEO == "Y" || user.isSEO == "Y" || user.isCEO == "Y"}'>
				
					<li class="start active">
						<s:url id="urlGovtstaffCustomersLink" action="ajaxGovtStaffCustomersList"
							namespace="/signup" />
						<sj:a href="%{urlGovtstaffCustomersLink}" targets="mainContentDiv"
							cssClass='ajaxify MGC'>Govt Customers</sj:a>
					</li>
							
					<li>
						<s:url id="urlAddPackageLink"
							action="ajaxAddGovtStaffRegister" namespace="/signup" />
						<sj:a href="%{urlAddPackageLink}" targets="mainContentDiv"
							cssClass='ajaxify MMSR'>
						Gov Staff Registration</sj:a>
					</li>
					<li>
						<s:url id="urlAddPackageLink"
							action="ajaxDoAddNewCustomerRegister" namespace="/signup" />
						<sj:a href="%{urlAddPackageLink}" targets="mainContentDiv"
							cssClass='ajaxify MMSR'>
						Customer Register</sj:a>
					</li>
					<li>
						<a href="${pageContext.request.contextPath}/govt/govtReports.do"
							id="commonInbox" class="current"> <i class="fa fa-home"></i>
							<span class="title">Reports</span> </a>
					</li>
				</s:if>
				<s:else>
					<s:if test='%{user.isSchoolLibrarian == "N" && user.isSecretary == "N" && user.isChairMan == "N" && user.isMessManager=="N" &&  user.isStoreKeeper == "N" &&  user.isReceptionist == "N"}'>
						<li id="adminReportsDiv">
							<a href="javascript:;" id="GR"> <i class="fa fa-bar-chart-o"></i>
								<span class="title">Reports</span> <span class="selected"></span>
								<span class="arrow"></span> </a>
							<ul class="sub-menu">
								<s:if test='%{user.isMasterAdmin == "Y"}'>
									<li>
										<a href="${pageContext.request.contextPath}/reports/ajaxDoCustomerSmsWiseReportDetails.do?pdfId=pdf" id="MSCS">
											Customer wise SMS count Report</a>
									</li>
									<li>
										<s:url id="urlDoCreateMasterLink" action="ajaxGetUserIpAddressLocationDetails" namespace="/masterAdmin" />
										<sj:a href="%{urlDoCreateMasterLink}" targets="mainContentDiv" cssClass='ajaxify UIPALT'>
											User Login Details</sj:a>
									</li>
<%-- 									<li>
										<s:url id="urlStudyClassLink" action="ajaxUpdateStudyClassIdInsteadOfClassId" namespace="/masterAdmin" />
										<sj:a href="%{urlStudyClassLink}" targets="mainContentDiv" cssClass='ajaxify UIPALT'>
											Insert StudyClass Data</sj:a>
									</li>
 --%>									<li>
										<s:url id="urlBarcodeLink" action="ajaxGenerateBarcodeExistingStudents" namespace="/masterAdmin" escapeAmp="false" includeParams="all"> <s:param name="plTitle">Barcode</s:param> </s:url>
										<sj:a href="%{urlBarcodeLink}" targets="mainContentDiv" cssClass='ajaxify BARCODE'>
											Generate Barcode</sj:a>
									</li>
								</s:if>
								<s:else>
									<s:if
										test='%{user.isSchoolAdmin=="Y" || user.isHostelFinance=="Y" || user.isSchoolHostel=="Y" || user.isSchoolTransport == "Y" || user.isSchoolDirector == "Y"}'>
										<li id="adminReports">
											<a href="javascript:;" class="ajaxify" id="SCS"> <span
												class="title">Admin</span> <span class="selected"></span> <span
												class="arrow "></span> </a>
											<ul class="sub-menu">
												<s:if
													test='%{(user.isOnlySchoolAdmin=="Y") || user.isHostelFinance=="Y" || user.isSchoolPrincipal=="Y" || user.isSchoolDirector == "Y"}'>
													
													<%-- <li id="autogenarateDiv">
														<s:url id="genateMonthlyExcelSheet"
															action="ajaxDoMonthlyReportDetails" escapeAmp="false"
															includeParams="all" namespace="/reports">
														</s:url>
														<sj:a href="%{genateMonthlyExcelSheet}"
															cssClass='ajaxify AGR' targets="mainContentDiv">Monthly Repor</sj:a>
													</li> --%>
													
													<li id="communityDiv">
														<s:url id="importStudCommunityExcelSheet"
															action="ajaxDoReligionWiseDetails" escapeAmp="false"
															includeParams="all" namespace="/reports">
															<s:param name="tempString">Student</s:param>
															<s:param name="plTitle">CommunityDetails</s:param>
														</s:url>
														<sj:a href="%{importStudCommunityExcelSheet}"
															cssClass='ajaxify SCS' targets="mainContentDiv">Student Community Summary</sj:a>
													</li>
													<li id="communityDiv">
														<s:url id="importStudCommunityAndCasteExcelSheet"
															action="ajaxCommunityAndCasteWiseDetails" escapeAmp="false"
															includeParams="all" namespace="/reports">
															<s:param name="tempString">Student</s:param>
															<s:param name="plTitle">CommunityAndCasteDetails</s:param>
														</s:url>
														<sj:a href="%{importStudCommunityAndCasteExcelSheet}"
															cssClass='ajaxify SCS' targets="mainContentDiv"> Caste & Community Statistics</sj:a>
													</li>
													<%-- <li id="failurePromoteStudentDiv">
														<s:url id="failurePromoteStudent"
															action="ajaxDoFailurePromoteStudentDetails" escapeAmp="false"
															includeParams="all" namespace="/reports">
														</s:url>
														<sj:a href="%{failurePromoteStudent}"
															cssClass='ajaxify FPSD' targets="mainContentDiv">List of Failed Students</sj:a>
													</li> --%>
													<%-- <li id="studentDetails">
														<s:url id="importClassWiseStudentExcelSheet"
															action="ajaxDoClassWiseStudentDetails" escapeAmp="false"
															includeParams="all" namespace="/reports">
															<s:param name="tempString">Student</s:param>
															<s:param name="plTitle">ClassWiseStudentDetails</s:param>
														</s:url>
														<sj:a href="%{importClassWiseStudentExcelSheet}"
															cssClass='ajaxify CSWD' targets="mainContentDiv">Class Wise Student Details</sj:a>
													</li> --%>
													<li>
														<s:url id="importStudentReligion"
															action="ajaxDoReligionWiseDetails" namespace="/reports"
															includeParams="all" escapeAmp="false">
															<s:param name="tempString">Student</s:param>
															<s:param name="plTitle">ReligionDetails</s:param>
														</s:url>
														<sj:a href="%{importStudentReligion}"
															targets="mainContentDiv" cssClass='ajaxify SRWS'>Student Religion Wise Summary</sj:a>
													</li>
													<%-- <li id="mediumWiseDetails">
														<s:url id="mediumWiseSummaryDetails"
															action="ajaxDoReligionWiseDetails" includeParams="all"
															escapeAmp="false" namespace="/reports">
															<s:param name="tempString">Student</s:param>
															<s:param name="plTitle">MediumWiseDetails</s:param>
														</s:url>
														<sj:a href="%{mediumWiseSummaryDetails}"
															targets="mainContentDiv" cssClass='ajaxify MWS'>Medium Wise Summary</sj:a>
													</li> --%>
													<li>
														<s:url id="StudentsWithAllClasses"
															action="ajaxDoComunityDetails" namespace="/reports"
															escapeAmp="false" includeParams="all">
															<s:param name="plTitle">ClassWiseCommunityDetails</s:param>
															<s:param name="tempString">Student</s:param>
														</s:url>
														<sj:a href="%{StudentsWithAllClasses}"
															targets="mainContentDiv" cssClass='ajaxify CCD'>
											  	         Class Wise Community Details
											          </sj:a>
													</li>
													<%-- 
													<li>
														<s:url id="StudentsCasteDetailsWithAllClasses"
															action="ajaxDoCastDetails" namespace="/reports"
															escapeAmp="false" includeParams="all">
															<s:param name="plTitle">ClassWiseCasteDetails</s:param>
															<s:param name="tempString">Student</s:param>
														</s:url>
														<sj:a href="%{StudentsCasteDetailsWithAllClasses}"
															targets="mainContentDiv" cssClass='ajaxify CCSD'>
											  	         Class Wise Caste Details
											          </sj:a>
													</li> --%>
													<li>
														<s:url id="attendanceForMonthly" namespace="/admin"
																	action="ajaxDoViewClassesAndMonths" escapeAmp="false"
																	includeParams="all">
																	<s:param name="tempString">Student</s:param>
																	<s:param name="bankName">Monthly</s:param>
																	<s:param name="plTitle">Attendance Not Submitted Details</s:param>
																</s:url>
																<sj:a href="%{attendanceForMonthly}"
																	targets="mainContentDiv" cssClass='ajaxify ASC'>Attendance Not Submitted Details</sj:a>
															</li>
													<li>
														<s:url id="DoGeneratedTCReport"
															action="ajaxDoGeneratedTCReport" namespace="/reports"
															escapeAmp="false" includeParams="all">
															
														</s:url>
														<sj:a href="%{DoGeneratedTCReport}"
															targets="mainContentDiv" cssClass='ajaxify GTCR'>
											  	         Generated TC Report
											          </sj:a>
													</li>
													
													<s:if test='%{(user.isOnlySchoolAdmin=="Y") || user.isSchoolPrincipal=="Y" || user.isSchoolDirector == "Y"}'>
														<li>
															<s:url id="downloadMarksSheet"
																action="ajaxDoViewDownloadMarksSheets"
																namespace="/admin" />
															<sj:a href="%{downloadMarksSheet}"
																targets="mainContentDiv" cssClass='ajaxify SPR'>
																Students Promotion report
															</sj:a>
														</li>
														<li>
															<s:url id="genderWiseSummaryDetails" namespace="/reports"
																action="ajaxDoSelectStudyClasses" includeParams="all"
																escapeAmp="false">
																<s:param name="tempString">Student</s:param>
																<s:param name="plTitle">GenderwiseDetails</s:param>
															</s:url>
															<sj:a href="%{genderWiseSummaryDetails}"
																targets="mainContentDiv" cssClass='ajaxify GD'>Student Strength Report</sj:a>
														</li>
														
														<li>
															<s:url id="motherTongueWiseSummaryDetails" namespace="/reports"
																action="ajaxDoMotherTongueWiseSummaryDetails" includeParams="all"
																escapeAmp="false">
																<s:param name="tempString">Student</s:param>
																<s:param name="plTitle">MotherTongueWiseStudentSummary</s:param>
															</s:url>
															<sj:a href="%{motherTongueWiseSummaryDetails}"
																targets="mainContentDiv" cssClass='ajaxify MTWSS'>Mother Tongue Wise Student Summary</sj:a>
														</li>
														<%-- <li>
															<s:url id="viewStudentCommunityClass"
																namespace="/reports" action="ajaxDoSelectStudyClasses"
																includeParams="all" escapeAmp="false">
																<s:param name="tempString">Student</s:param>
																<s:param name="plTitle">ViewStudentClassSectionDetails</s:param>
															</s:url>
															<sj:a href="%{viewStudentCommunityClass}"
																targets="mainContentDiv" cssClass='ajaxify VCD'>Manage Students Register Details</sj:a>
														</li> --%>
														<li>
															<s:url id="urlDownloadStaffDetails"
																action="ajaxDoViewStaffRoles" namespace="/reports" />
															<sj:a id="staffDetails" href="%{urlDownloadStaffDetails}"
																targets="mainContentDiv" cssClass='ajaxify RSD'>
													             Role Wise Staff Details 
										                	</sj:a>
														</li>
														
														<li>
															<s:url id="admissionsSummaryDetails" namespace="/reports"
																action="ajaxAdmissionsSummaryDetails" includeParams="all"
																escapeAmp="false">
															</s:url>
															<sj:a href="%{admissionsSummaryDetails}"
																targets="mainContentDiv" cssClass='ajaxify ADS'>Admissions Summary</sj:a>
														</li>
													</s:if>
												</s:if>
												<s:if test='%{(user.isOnlySchoolAdmin=="Y") || user.isSchoolTransport == "Y"}'>
													<s:if
														test='%{(customerByCustId.transportModuleStatus ==true) }'>
														<li>
															<s:url id="urlDoViewMaintenanceMonths"
																action="ajaxDoViewMaintenanceMonths"
																namespace="/reports" />
															<sj:a id="transportMaintainenance" href="%{urlDoViewMaintenanceMonths}"
																targets="mainContentDiv" cssClass='ajaxify TM'>Transport Maintenance
															</sj:a>
														</li>

													</s:if>
												</s:if>
												<s:if
													test='%{(user.isOnlySchoolAdmin=="Y") || user.isHostelFinance=="Y" || user.isSchoolHostel=="Y" || user.isSchoolPrincipal=="Y" || user.isSchoolDirector == "Y"}'>
													<s:if
														test='%{(customerByCustId.hostelModuleStatus==true) }'>
														<li>
															<s:url id="urlDoViewAllhostelReports"
																action="ajaxDoViewHostelReports" namespace="/reports" />
															<sj:a id="doViewHostelReports" href="%{urlDoViewAllhostelReports}"
																targets="mainContentDiv" cssClass='ajaxify HD'>	Hostel Student Details
															</sj:a>
														</li>
														<s:if test='%{user.isHostelFinance=="Y" || user.isSchoolHostel=="Y" || user.isSchoolPrincipal=="Y" || user.isSchoolDirector == "Y"}'>
														<li>
															<s:url id="genateMonthlyExcelSheet"
																	action="ajaxDoMonthlyReportDetails" escapeAmp="false"
																	includeParams="all" namespace="/reports">
																</s:url>
																<sj:a href="%{genateMonthlyExcelSheet}"
															cssClass='ajaxify AGR' targets="mainContentDiv">Auto Monthly Report</sj:a>
														</li>
														</s:if>
														<%-- <li>
															<s:url id="urlDoViewAllVisitors"
																action="ajaxDaysBetweenByVisitors" namespace="/reports">
																<s:param name="plTitle">Visitor</s:param>
															</s:url>
															<sj:a id="doViewVisitorsReports" href="%{urlDoViewAllVisitors}" targets="mainContentDiv"
																cssClass='ajaxify VIO'> Visitors In/Out
															</sj:a>
														</li>
														<li>
															<s:url id="urlDoViewAllStudentOutIn"
																action="ajaxDaysBetweenByVisitors" namespace="/reports">
																<s:param name="plTitle">Student</s:param>
															</s:url>
															<sj:a id="doViewStudentOutInReports" href="%{urlDoViewAllStudentOutIn}"
																targets="mainContentDiv" cssClass='ajaxify SI'>Students In/Out
											   				 </sj:a>
														</li> --%>
													</s:if>
												</s:if>
												<s:if test='%{user.isOnlySchoolAdmin=="Y" || user.isSchoolTransport || user.isSchoolHostel}'>
												<li>
													<s:url id="studentExcelSheet" namespace="/reports"
														action="ajaxStudentGenderAndComunituCountReport"
														escapeAmp="false" includeParams="all">
														<s:param name="plTitle">CategoryAndCommunityGenderWiseSummary</s:param>
													</s:url>
													<sj:a href="%{studentExcelSheet}" targets="mainContentDiv"
														cssClass='ajaxify CCC'> Category And Community Class Gender Wise Summary</sj:a>
												</li>
												<li>
													<s:url id="studentExcelSheet" namespace="/reports"
														action="ajaxStudentGenderAndComunituCountReport"
														escapeAmp="false" includeParams="all">
														<s:param name="plTitle">ClsssAndCommunityWiseSummary</s:param>
													</s:url>
													<sj:a href="%{studentExcelSheet}" targets="mainContentDiv"
														cssClass='ajaxify GCD'>Gender And Community Class Wise Summary</sj:a>
												</li>
												<li>
													<s:url id="studentAndStaffCredentials" namespace="/reports"
														action="ajaxStudentAndStaffLoginCredentialsReport"
														escapeAmp="false" includeParams="all">
													</s:url>
													<sj:a href="%{studentAndStaffCredentials}"
														targets="mainContentDiv" cssClass='ajaxify SAS'>Student And Staff Login Credentials</sj:a>
												</li>
												<li>
													<s:url id="studentMarksUpdateDetails" namespace="/reports"
														action="ajaxStudentMarksUpdateDetails"
														escapeAmp="false" includeParams="all">
													</s:url>
													<sj:a href="%{studentMarksUpdateDetails}"
														targets="mainContentDiv" cssClass='ajaxify MUT'>Marks Upload Tracking</sj:a>
												</li>
												<li>
													<s:url id="onlineAppointments" namespace="/reports"
														action="ajaxOnlineAppointments"
														escapeAmp="false" includeParams="all">
													</s:url>
													<sj:a href="%{onlineAppointments}"
														targets="mainContentDiv" cssClass='ajaxify OAT'>Online Appointments</sj:a>
												</li>
												<li>
													<s:url id="genateMonthlyExcelSheet"
															action="ajaxDoMonthlyReportDetails" escapeAmp="false"
															includeParams="all" namespace="/reports">
														</s:url>
														<sj:a href="%{genateMonthlyExcelSheet}"
													cssClass='ajaxify AGR' targets="mainContentDiv">Auto Monthly Report</sj:a>
												</li>
												<%-- <s:if test='%{(user.isOnlySchoolAdmin=="Y") || user.isSchoolPrincipal=="Y" || user.isSchoolFinance=="Y"}'>
													<li><s:url id="dayBookExcelSheet" namespace="/reports"
															action="ajaxGetDayBookReports" escapeAmp="false"
															includeParams="all">
														</s:url> <sj:a href="%{dayBookExcelSheet}"
															targets="mainContentDiv" data-toggle="tab">DayBook Reports</sj:a>
													</li>
													</s:if> --%>
													<%-- <li>
													<s:url id="staffRatinfInfo" namespace="/admin"
														action="ajaxDoRatingInformation" escapeAmp="false"
														includeParams="all">
													</s:url>
													<sj:a href="%{staffRatinfInfo}" targets="mainContentDiv"
														cssClass='ajaxify SAS'>Staff Feedback Information</sj:a>
												</li> --%>
												</s:if>
												<s:if test = '%{user.isSchoolAdmin=="Y" || user.isSchoolDirector == "Y"}'>
												 <li>
															<s:url id="urlDoParentsOccupationReport"
																action="ajaxGetStudyClassesWithStudents" namespace="/reports">
																<s:param name="plTitle">Parents Occupation Report</s:param>
															</s:url>
															<sj:a id="parentsOccupationReport" href="%{urlDoParentsOccupationReport}" targets="mainContentDiv"
																cssClass='ajaxify VIO'> Parents Occupation Report
															</sj:a>
														</li>
												</s:if>
											</ul>
									</s:if>
									<s:if test = '%{user.isSchoolClerk=="Y" || user.isSchoolAsstStaff=="Y"}'>
										<li id="studentReportsDiv">
											<a href="javascript:;" id="SID" class="ajaxify"> <span
												class="title">Student</span> <span class="selected"></span>
												<span class="arrow "></span> </a>
											<ul class="sub-menu">
												<li id="idCardsDiv">
														<s:url id="urlDoIdCardsGenerations" namespace="/admin"
															action="ajaxIDCardsGenerations" />
														<sj:a id="doIdCardsGenerations"
															href="%{urlDoIdCardsGenerations}"
															targets="mainContentDiv" cssClass='ajaxify ID'>ID Cards Generation</sj:a>
												</li>
												<li>
													<s:url id="dailyAttendance"
														action="ajaxDoViewClassesAndTodyDate" escapeAmp="false"
														namespace="/admin" includeParams="all">
														<s:param name="tempString">Student</s:param>
														<s:param name="plTitle">Daily Attendance</s:param>
													</s:url>
													<sj:a href="%{dailyAttendance}" targets="mainContentDiv"
														cssClass='ajaxify DA'>Attendance Report</sj:a>
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
												<li>
													<s:url id="attendanceForMonthly1" namespace="/admin"
														action="ajaxDoViewClassesAndMonths" escapeAmp="false"
														includeParams="all">
														<s:param name="tempString">Student</s:param>
														<s:param name="plTitle">Attendance Summary Class Wise(Monthly)</s:param>
													</s:url>
													<sj:a href="%{attendanceForMonthly1}"
														targets="mainContentDiv" cssClass='ajaxify ASCW'>Class Wise Attendance Summary</sj:a>
												</li>
										  </s:if>
												<%-- <li>
													<s:url id="urlStudMonthAttendanceReport"
														action="ajaxManageClassesMonthAttendaceReport"
														escapeAmp="false" namespace="/reports"
														includeParams="all">
													</s:url>
													<sj:a href="%{urlStudMonthAttendanceReport}"
														targets="mainContentDiv" cssClass='ajaxify CMD'>Classes Monthly  Attendance Report</sj:a>
												</li> --%>
												<li>
													<s:url id="studentExcelSheet" namespace="/reports"
														action="ajaxDoSelectedStudentDetailsReport"
														escapeAmp="false" includeParams="all">
													</s:url>
													<sj:a href="%{studentExcelSheet}" targets="mainContentDiv"
														cssClass='ajaxify SRD'>Student Report Details</sj:a>
												</li>
												<li>
													<s:url id="urlUploadNoEmailAndMobileStudent" action="ajaxDownloadNoEmailAndMobileStudentDetails" namespace="/admin" />
													<sj:a id="urlUploadNoEmailAndMobile" href="%{urlUploadNoEmailAndMobileStudent}" targets="mainContentDiv" cssClass="ajaxify AUEMS">
													Students Without Emails and Mobile Numbers</sj:a>
												</li> 
												<li id="suspendedStudentStudentDiv">
														<s:url id="suspendedStudents"
															action="ajaxDoFailurePromoteStudentDetails" escapeAmp="false"
															includeParams="all" namespace="/reports">
															<s:param name="plTitle">S</s:param>
														</s:url>
														<sj:a href="%{suspendedStudents}"
															cssClass='ajaxify SSSD' targets="mainContentDiv">List of Suspend Students</sj:a>
												</li>
												<li id="blacklistedStudentStudentDiv">
														<s:url id="blacklistedStudent"
															action="ajaxDoFailurePromoteStudentDetails" escapeAmp="false"
															includeParams="all" namespace="/reports">
															<s:param name="plTitle">B</s:param>
														</s:url>
														<sj:a href="%{blacklistedStudent}"
															cssClass='ajaxify BLACKS' targets="mainContentDiv">List of Blacklist Students</sj:a>
												</li>
												<li>
													<s:url id="genateMonthlyExcelSheet"
															action="ajaxDoMonthlyReportDetails" escapeAmp="false"
															includeParams="all" namespace="/reports">
														</s:url>
														<sj:a href="%{genateMonthlyExcelSheet}"
													cssClass='ajaxify AGR' targets="mainContentDiv">Auto Monthly Report</sj:a>
												</li>
												<li>
													<s:url id="doStudentAssignmentReport"
														action="ajaxDoStudentAssignmentReport" escapeAmp="false"
														namespace="/admin" includeParams="all">
														<s:param name="tempString">Student</s:param>
														<s:param name="plTitle">Daily Assignment</s:param>
													</s:url>
													<sj:a href="%{doStudentAssignmentReport}" targets="mainContentDiv"
														cssClass='ajaxify SDD'>Student Assignment Report</sj:a>
												</li>
														
											</ul>
										</li>
									</s:if>
									<s:if
										test='%{user.isSchoolAdmin=="Y" || user.isOnlySchoolHod=="Y" || user.isOnlySchoolTeacher=="Y" || user.isSchoolHostel=="Y" ||  user.isHostelFinance=="Y" 
										|| user.isSchoolTransport=="Y" || user.isAdminCoordinator == "Y" || user.isSchoolDirector == "Y"}'>
										<li id="studentReportsDiv">
											<a href="javascript:;" id="SID" class="ajaxify"> <span
												class="title">Student</span> <span class="selected"></span>
												<span class="arrow "></span> </a>
											<ul class="sub-menu">
												<s:if
													test='%{(user.isOnlySchoolAdmin=="Y") || user.isSchoolPrincipal=="Y" || user.isSchoolDirector == "Y" || user.isSchoolHostel}'>
													<li id="idCardsDiv">
														<s:url id="urlDoIdCardsGenerations" namespace="/admin"
															action="ajaxIDCardsGenerations" />
														<sj:a id="doIdCardsGenerations"
															href="%{urlDoIdCardsGenerations}"
															targets="mainContentDiv" cssClass='ajaxify ID'>ID Cards Generation</sj:a>
													</li>
												</s:if>
												<s:if
													test='%{(user.isOnlySchoolAdmin=="Y") || user.isSchoolPrincipal=="Y" || user.isOnlySchoolHod=="Y" || user.isOnlySchoolTeacher=="Y" 
													||  user.isSchoolHostel=="Y" || user.isHostelFinance=="Y" || user.isSchoolTransport=="N" || user.isSchoolTransport!="Y" || user.isAdminCoordinator == "Y" || user.isSchoolDirector == "Y"}'>
													<s:if
														test='%{user.isSchoolStudent=="N" || user.isParent=="N" || user.isSchoolHostel=="Y"}'>
														
														<s:if test='%{user.isSchoolHostel!="Y"}'> 
														<s:if test='%{currentAcademicYear.manageAttendanceBy=="D"}'> 
															<li>
																<s:url id="dailyAttendance"
																	action="ajaxDoViewClassesAndTodyDate" escapeAmp="false"
																	namespace="/admin" includeParams="all">
																	<s:param name="tempString">Student</s:param>
																	<s:param name="plTitle">Daily Attendance</s:param>
																</s:url>
																<sj:a href="%{dailyAttendance}" targets="mainContentDiv"
																	cssClass='ajaxify DA'>Attendance Report</sj:a>
															</li>
															<li>
																<s:url id="attendanceForMonthly" namespace="/admin"
																	action="ajaxDoViewClassesAndMonths" escapeAmp="false"
																	includeParams="all">
																	<s:param name="tempString">Student</s:param>
																	<s:param name="bankName">Monthly</s:param>
																	<s:param name="plTitle">Attendance Summary Class Wise(Fully)</s:param>
																</s:url>
																<sj:a href="%{attendanceForMonthly}"
																	targets="mainContentDiv" cssClass='ajaxify ASC'>Student Month Wise Attendance</sj:a>
															</li>
															</s:if>
															<s:if test='%{currentAcademicYear.manageAttendanceBy=="M"}'> 
															<li>
																<s:url id="attendanceForMonthly" namespace="/admin"
																	action="ajaxDoViewClassesAndMonths" escapeAmp="false"
																	includeParams="all">
																	<s:param name="tempString">Student</s:param>
																	<s:param name="bankName">Monthly</s:param>
																	<s:param name="plTitle">Attendance Summary Class Wise(Fully)</s:param>
																</s:url>
																<sj:a href="%{attendanceForMonthly}"
																	targets="mainContentDiv" cssClass='ajaxify ASC'>Class Wise Attendance Summary</sj:a>
															</li>
														</s:if>
													<%-- 	<li>
																<s:url id="urlStudMonthAttendanceReport"
																	action="ajaxManageClassesMonthAttendaceReport"
																	escapeAmp="false" namespace="/reports"
																	includeParams="all">
																</s:url>
																<sj:a href="%{urlStudMonthAttendanceReport}"
																	targets="mainContentDiv" cssClass='ajaxify CMD'>Classes Monthly  Attendance Report</sj:a>
															</li>  
														<li>
															<s:url id="attendanceForMonthly1" namespace="/admin"
																action="ajaxDoViewClassesAndMonths" escapeAmp="false"
																includeParams="all">
																<s:param name="tempString">Student</s:param>
																<s:param name="plTitle">Attendance Summary Class Wise(Monthly)</s:param>
															</s:url>
															<sj:a href="%{attendanceForMonthly1}"
																targets="mainContentDiv" cssClass='ajaxify ASCW'>Class Wise Attendance Summary</sj:a>
														</li>
														<li>
															<s:url id="urlStudMonthAttendanceReport"
																action="ajaxManageClassesMonthAttendaceReport"
																escapeAmp="false" namespace="/reports"
																includeParams="all">
															</s:url>
															<sj:a href="%{urlStudMonthAttendanceReport}"
																targets="mainContentDiv" cssClass='ajaxify CMD'>Classes Monthly  Attendance Report</sj:a>
														</li> --%>
														</s:if>
													</s:if>
												</s:if>
												<s:if
													test='%{(user.isOnlySchoolAdmin=="Y") || user.isSchoolPrincipal=="Y" || user.isOnlySchoolHod=="Y" || user.isOnlySchoolTeacher=="Y"  
													|| user.isSchoolHostel=="Y" || user.isHostelFinance=="Y" || user.isSchoolTransport=="Y" || user.isAdminCoordinator == "Y" || user.isSchoolDirector == "Y"}'>
													<s:if test='%{user.isSchoolTransport=="N" &&  user.isSchoolHostel!="Y"}'>
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
													</s:if>
													<s:if test='%{#session.customerTransportStauts==true}'>
														<s:if
															test='%{user.isOnlySchoolAdmin=="Y" ||  user.isSchoolTransport == "Y" }'>
															<li>
																<s:url id="urlManageTransportRouteWiseReport"
																	action="ajaxManageTransportRouteWiseReport"
																	namespace="/admin">
																	<s:param name="title">Route Wise Report</s:param>
																</s:url>
																<sj:a href="%{urlManageTransportRouteWiseReport}"
																	targets="mainContentDiv" cssClass='ajaxify MRP'>Route Wise Report</sj:a>
															</li>
															<li>
																<s:url id="urlManageTransportVehicleWiseReport"
																	action="ajaxManageTransportVehicleWiseReport"
																	namespace="/admin">
																	<s:param name="title">Vehicle Wise Report</s:param>
																</s:url>
																<sj:a href="%{urlManageTransportVehicleWiseReport}"
																	targets="mainContentDiv" cssClass='ajaxify MVD'>Vehicle Wise Report</sj:a>
															</li>
														</s:if>
													</s:if>
												</s:if>
												<s:if
													test='%{(user.isOnlySchoolAdmin=="Y") || user.isSchoolPrincipal=="Y" || user.isOnlySchoolHod=="Y" || user.isOnlySchoolTeacher=="Y" 
													|| user.isAdminCoordinator == "Y" || user.isSchoolDirector == "Y"}'>
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
													<s:if test='%{user.isOnlySchoolHod=="Y" || user.isOnlySchoolTeacher=="Y" || user.isAdminCoordinator == "Y"}'>
														<li>
															<s:url id="genateMonthlyExcelSheet"
																	action="ajaxDoMonthlyReportDetails" escapeAmp="false"
																	includeParams="all" namespace="/reports">
																</s:url>
																<sj:a href="%{genateMonthlyExcelSheet}"
															cssClass='ajaxify AGR' targets="mainContentDiv">Auto Monthly Report</sj:a>
														</li>
													</s:if>
													 <%-- <s:if test='%{user.isOnlySchoolAdmin=="Y" || tempBoolean || user.isOnlySchoolHod=="Y"}'>
														<li>
															<s:url id="urlDownloadStudsTTable"
																action="ajaxDoDownloadStudentsTimeTableReport"
																escapeAmp="false" includeParams="all" namespace="/reports">
															</s:url>
															<sj:a href="%{urlDownloadStudsTTable}"
																targets="mainContentDiv" cssClass='ajaxify TT'>Timetable</sj:a>
														</li>
													</s:if>  --%>
													
														<li>
															<s:url id="doStudentAssignmentReport"
																action="ajaxDoStudentAssignmentReport" escapeAmp="false"
																namespace="/admin" includeParams="all">
																<s:param name="tempString">Student</s:param>
																<s:param name="plTitle">Daily Assignment</s:param>
															</s:url>
															<sj:a href="%{doStudentAssignmentReport}" targets="mainContentDiv"
																cssClass='ajaxify SDD'>Student Assignment Report</sj:a>
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
															targets="mainContentDiv" cssClass='ajaxify CCW'>Class and Community Wise Marks</sj:a>
													</li>
													<li>
														<s:url id="importCommunityMarks" namespace="/reports"
															action="ajaxClassAndComunityWiseMarks" escapeAmp="false"
															includeParams="all">
															<s:param name="tempString">Student</s:param>
															<s:param name="plTitle">ReligionWiseMarks</s:param>
														</s:url>
														<sj:a href="%{importCommunityMarks}"
															targets="mainContentDiv" cssClass='ajaxify RWD'>Religion Wise Marks</sj:a>
													</li>
													<li>
														<s:url id="studentExcelSheet" namespace="/reports"
															action="ajaxDoSelectedStudentDetailsReport"
															escapeAmp="false" includeParams="all">
														</s:url>
														<sj:a href="%{studentExcelSheet}" targets="mainContentDiv"
															cssClass='ajaxify SRD'>Student Report Details</sj:a>
													</li>
													<li>
														<s:url id="urlUploadNoEmailAndMobileStudent" action="ajaxDownloadNoEmailAndMobileStudentDetails" namespace="/admin" />
														<sj:a id="urlUploadNoEmailAndMobile" href="%{urlUploadNoEmailAndMobileStudent}" targets="mainContentDiv" cssClass="ajaxify AUEMS">
														Students Without Emails and Mobile Numbers</sj:a>
													</li> 
													<li id="suspendedStudentStudentDiv">
														<s:url id="suspendedStudents"
															action="ajaxDoFailurePromoteStudentDetails" escapeAmp="false"
															includeParams="all" namespace="/reports">
															<s:param name="plTitle">S</s:param>
														</s:url>
														<sj:a href="%{suspendedStudents}"
															cssClass='ajaxify SSSD' targets="mainContentDiv">List of Suspend Students</sj:a>
												</li>
												<li id="blacklistedStudentStudentDiv">
														<s:url id="blacklistedStudent"
															action="ajaxDoFailurePromoteStudentDetails" escapeAmp="false"
															includeParams="all" namespace="/reports">
															<s:param name="plTitle">B</s:param>
														</s:url>
														<sj:a href="%{blacklistedStudent}"
															cssClass='ajaxify BLACKS' targets="mainContentDiv">List of Blacklist Students</sj:a>
												</li>
												
												</s:if>
											</ul>
										</li>
									</s:if>
									<s:if
										test='%{user.isSchoolAdmin=="Y" || user.isSchoolHostel=="Y" || user.isHostelFinance=="Y" || user.isSchoolDirector == "Y"}'>
										<li>
											<a href="javascript:;" id="ASTCS" class="ajaxify"> <span
												class="title">Staff</span> <span class="selected"></span> <span
												class="arrow "></span> </a>
											<ul class="sub-menu">
												<s:if
													test='%{(user.isOnlySchoolAdmin=="Y") || user.isSchoolPrincipal=="Y" || user.isSchoolDirector == "Y"}'>
													<li id="urlStaffIdCardsGeneration">
														<s:url id="urlStaffIdCardsGenerations"
															action="ajaxStaffIdCardsGenerations" namespace="/admin"
															includeParams="all"></s:url>
														<sj:a id="urlStaffIdCardsGenerations"
															href="%{urlStaffIdCardsGenerations}"
															targets="mainContentDiv" cssClass='ajaxify ASTCS'>ID Cards Generation</sj:a>
												</s:if>
												<s:if
													test='%{(user.isOnlySchoolAdmin=="Y") || user.isSchoolPrincipal=="Y" || user.isHostelFinance=="Y" || user.isSchoolHostel=="Y" || user.isSchoolDirector == "Y"}'>
													<s:if test='%{user.isSchoolHostel!="Y"}'>
													<li>
														<s:url id="importCommunityExcelSheet" namespace="/reports"
															action="ajaxDoReligionWiseDetails" escapeAmp="false"
															includeParams="all">
															<s:param name="tempString">Staff</s:param>
															<s:param name="plTitle">CommunityDetails</s:param>
														</s:url>
														<sj:a href="%{importCommunityExcelSheet}"
															targets="mainContentDiv" cssClass='ajaxify SSS'>Staff Community Summary</sj:a>
													</li>
													<li>
														<s:url id="staffExcelSheet" namespace="/reports"
															action="ajaxDoSelectedStaffDetailsReport"
															escapeAmp="false" includeParams="all">
														</s:url>
														<sj:a href="%{staffExcelSheet}" targets="mainContentDiv"
															cssClass='ajaxify SDR'>Staff Report Details</sj:a>
													</li>
													<li>
														<s:url id="StaffReligionsInPDF"
															action="ajaxDoReligionWiseDetails" namespace="/reports"
															includeParams="all" escapeAmp="false">
															<s:param name="tempString">Staff</s:param>
															<s:param name="plTitle">ReligionDetails</s:param>
														</s:url>
														<sj:a href="%{StaffReligionsInPDF}"
															targets="mainContentDiv" cssClass='ajaxify SR'>Staff Religion Summary</sj:a>
													</li>
													</s:if>
													<li>
														<s:url id="StaffAttendanceMonthlyInPDF" action="ajaxViewTeachNonTeachStaffAttendDetails" namespace="/reports" includeParams="all" escapeAmp="false">
															<s:param name="tempString">Staff</s:param>
															<s:param name="bankName">Monthly</s:param>
															<s:param name="plTitle">StaffAttendanceMonthly(Monthly)</s:param>
														</s:url>
														<sj:a href="%{StaffAttendanceMonthlyInPDF}"
															targets="mainContentDiv" cssClass='ajaxify SM'>Staff Monthly Attendance Details</sj:a>
													</li>
													<li>
														<s:url id="StaffDailyAttendanceInPDF"
															action="ajaxStaffDailyAttendDetails" namespace="/reports"
															includeParams="all" escapeAmp="false">
															<s:param name="tempString">Staff</s:param>
															<s:param name="plTitle">StaffDailyAttendance(Daily)</s:param>
														</s:url>
														<sj:a href="%{StaffDailyAttendanceInPDF}"
															targets="mainContentDiv" cssClass='ajaxify SDA'>Staff Daily Attendance Details</sj:a>
													</li>
												</s:if>
												<s:if
													test='%{(user.isOnlySchoolAdmin=="Y") || user.isSchoolPrincipal=="Y" || user.isHostelFinance=="Y" || user.isSchoolHostel=="Y" || user.isSchoolDirector == "Y"}'>
													<s:if
														test='%{(customerByCustId.hostelModuleStatus==true) }'>
														<li>
															<s:url id="StaffReligionsInPDF"
																action="ajaxDoViewStaffHostelReports"
																namespace="/reports" includeParams="all">
																<s:param name="tempString">Staff</s:param>
															</s:url>
															<sj:a href="%{StaffReligionsInPDF}"
																targets="mainContentDiv" cssClass='ajaxify HSD'>Hostel Staff Details</sj:a>
														</li>
													</s:if>
												</s:if>
											</ul>
										</li>
									</s:if>
									<s:if
										test='%{user.isOnlySchoolAdmin=="Y" || user.isSchoolPrincipal=="Y" || user.isHostelFinance=="Y"  || user.isSchoolHostel=="Y" || user.isTransportFinance=="Y" || user.isSchoolStudent=="Y" || user.isParent=="Y" || user.isSchoolFinance=="Y" || user.isSchoolDirector == "Y"}'>
										<li>
											<a href="javascript:;" id="AFC" class="ajaxify"> <span
												class="title">Fee</span> <span class="selected"></span>
												<span class="arrow "></span> </a>
											<ul class="sub-menu" id="dynamicFeeCollectionsDiv">
												 
												<li id="allFeeReportsDiv">
													<s:url id="dateWiseFeeCollection"
														action="ajaxDateWiseFeeCollection" namespace="/reports"
														includeParams="all" escapeAmp="false">
														<s:param name="tempString">Staff</s:param>
														<s:param name="plTitle">DateWiseFeeCollection</s:param>
													</s:url>
													<sj:a href="%{dateWiseFeeCollection}" targets="mainContentDiv" cssClass='ajaxify DWF'>All Fee Collections</sj:a>
												</li>
											</ul>
										</li>
									</s:if>
									<s:if
										test='%{(user.isOnlySchoolAdmin=="Y") || user.isHostelFinance=="Y"  || user.isSchoolHostel=="Y" || user.isSchoolPrincipal=="Y" || user.isTransportFinance=="Y" || user.isSchoolFinance=="Y" || user.isSchoolDirector == "Y"}'>
										<li>
											<a href="javascript:;" id="AFF" class="ajaxify"> <span
												class="title">Other Fee-Collections</span> <span
												class="selected"></span> <span class="arrow "></span> </a>
											<ul class="sub-menu">
												<li id="fineFeeDetails">
													<s:url id="importClassWiseOtherFeeExcelSheet"
														action="ajaxDoOtherFeeDetails" escapeAmp="false"
														includeParams="all" namespace="/reports">
														<s:param name="tempString">Finace</s:param>
														<s:param name="plTitle">ClassWiseFineFeeDetails</s:param>
													</s:url>
													<sj:a href="%{importClassWiseOtherFeeExcelSheet}"
														targets="mainContentDiv" cssClass='ajaxify AFF'>Class Wise Other Fee Amount</sj:a>
												</li>
											</ul>
											</li>
											<s:if test='%{customerByCustId.accountModuleUsing=="Y"}'>
												<li>
													<a href="javascript:;" id="AFF" class="ajaxify"> 
														<span class="title">Accounts</span> <span class="selected"></span> <span class="arrow "></span> 
													</a>
													<ul class="sub-menu">
														<li id="accountDetails">
															<s:url id="downloadAccountDetails" action="ajaxDownloadAllAccountCategory" escapeAmp="false"
																includeParams="all" namespace="/account">
															</s:url> 
															<sj:a href="%{downloadAccountDetails}" targets="mainContentDiv" cssClass='ajaxify ADAC'>Master Accounts</sj:a>
														</li>
														<li>
															<s:url id="downloadAccountTransactions" action="ajaxDownloadTransactions" escapeAmp="false"
																includeParams="all" namespace="/account">
															</s:url> 
															<sj:a href="%{downloadAccountTransactions}" targets="mainContentDiv" cssClass='ajaxify ADAT'>Transactions</sj:a>
														</li>
														<li>
															<s:url id="downloadFinalStatements" action="ajaxDownloadFinalStatements" escapeAmp="false"
																includeParams="all" namespace="/account">
															</s:url> 
															<sj:a href="%{downloadFinalStatements}" targets="mainContentDiv" cssClass='ajaxify ADAT'>Final Statements</sj:a>
														</li>
													</ul>
												 </li>
											</s:if>
									</s:if>
								</s:else>
								<s:if test='%{user.isSchoolFinance=="Y" || user.isTransportFinance=="Y"}'>
									<li>
										<s:url id="genateMonthlyExcelSheet"
												action="ajaxDoMonthlyReportDetails" escapeAmp="false"
												includeParams="all" namespace="/reports">
											</s:url>
											<sj:a href="%{genateMonthlyExcelSheet}"
										cssClass='ajaxify AGR' targets="mainContentDiv">Auto Monthly Report</sj:a>
									</li>
								</s:if>
							</ul>
						</li>
					</s:if>
				</s:else>
				
				<s:if test='%{user.isOnlySchoolAdmin == "Y"  || user.isSchoolPrincipal=="Y" || user.isSchoolTeacher == "Y"  || user.isSchoolAsstStaff=="Y"  || user.isAdminCoordinator == "Y" || user.isSchoolDirector == "Y"}'>
				
				
					<li id="helpDocument">
					<a href="javascript:;" id="HelpDoc"><i class="fa fa-file-text"></i>
						<span class="title">Help</span> <span class="selected"></span> <span
						class="arrow"></span> </a>
					<ul class="sub-menu">
					
					<s:if test='%{user.isOnlySchoolAdmin=="Y"}'> 
						<li id ="helpDcoumnetsId">
							<s:url id="helpDcoumnetsUrl"
								action="ajaxAadminGetHelpDocuments" namespace="/reports"></s:url>
								<sj:a href="%{helpDcoumnetsUrl}" targets="mainContentDiv"
								cssClass='ajaxify Helps'>
								Help documents</sj:a>
						</li>
						<li>
							<s:url id="reports1"
								action="ajaxScorecardsdoc" namespace="/reports"></s:url>
							<sj:a href="%{reports1}"
								targets="mainContentDiv" cssClass="ajaxify Scorecard">Scorecard Templates</sj:a>
						</li>
						<li>
							<s:url id="desktopSoftware" action="ajaxDoDesktopSoftwareDownload" namespace="/reports"></s:url>
							<sj:a href="%{desktopSoftware}"
								targets="mainContentDiv" cssClass="ajaxify desktop">Desktop Softwares</sj:a>
						</li>
						</s:if>
						<li>
							<s:url id="desktopSoftwareTickets" action="ajaxInternalSoftwareTicketDetails" namespace="/admin"></s:url>
							<sj:a href="%{desktopSoftwareTickets}"
								targets="mainContentDiv" cssClass="ajaxify tickets">Tickets</sj:a>
						</li>
					</ul>
					</li>
				</s:if>
				<s:if test='%{user.isCEO != "Y" && user.isDEO != "Y" && user.isSEO != "Y" && user.isBEO != "Y" && user.isMasterAdmin != "Y" && user.isStoreKeeper != "Y" && user.isReceptionist != "Y" && user.isSchoolLibrarian != "Y"}'>
					<li id="addVideosDiv">
						<a href="javascript:;" id="HelpDoc"><i class="fa fa-file-text"></i>
							<span class="title">Videos</span> <span class="selected"></span> <span
							class="arrow"></span> </a>
						<ul class="sub-menu">
							<li>
								<s:url id="viewVideosurl" action="ajaxDoViewVideos" namespace="/common"></s:url>
									<sj:a href="%{viewVideosurl}" targets="mainContentDiv" cssClass='ajaxify VVEO'> View Videos</sj:a>
							</li>
						</ul>
					</li>
				</s:if>
				<s:if test='%{user.isChairMan=="Y"}'>
				<li class="start active">
					<a
						href="${pageContext.request.contextPath}/staff/chairManDashboard.do"
						id="secretary" class="start"> <i class="fa fa-home"></i> <span
						class="title">Dashboard</span> <span class="selected"></span>
					</a>
				</li>
				<li>
					<a href="javascript:;" id="ACWSMS"><i class="fa fa-comment"></i>
						<span class="title">SMS</span> <span class="selected"></span> <span
						class="arrow"></span> </a>
					<ul class="sub-menu">
						<li>
							<s:url id="urlInboxesDetailsForChairMan"
								action="ajaxDoGetChairMessagesList" namespace="/common"
								includeParams="all" escapeAmp="false">
								<s:param value="#session.academicYear" name="academicYearId" />
							</s:url>
							<sj:a href="%{urlInboxesDetailsForChairMan}" targets="mainContentDiv"
								cssClass='ajaxify ACWSMS'>
								Sent SMS or E-Mail</sj:a>
						</li>
					 </ul>
				 </li>
			</s:if>
			<s:if test='%{user.isMessManager=="Y"}'>
				<li class="start active">
					<a
						href="${pageContext.request.contextPath}/staff/messManagerDashboard.do"?
						id="messManager" class="start"> <i class="fa fa-home"></i> <span
						class="title">Staff & Student Attendance Info</span> <span class="selected"></span>
					</a>
				</li>
				<li>
					<a href="javascript:;" id="staffMyClass"> <i
						class="fa fa-gift"></i> <span class="title">My Class</span> <span
						class="selected"></span> <span class="arrow"></span> </a>
					<ul class="sub-menu">
						<li>
							<s:url id="urlTeacherLeaveLink"
								action="ajaxDoGetLeaveDetailsLeft" namespace="/staff" />
							<sj:a href="%{urlTeacherLeaveLink}" targets="mainContentDiv"
								cssClass="ajaxify SLMS">
								Leave Management</sj:a>
						</li>
					</ul>
				</li>
			</s:if>
			<s:if test='%{user.isStoreKeeper=="Y"}'>
				<%@ include file="/common/storeKeeperLeftNav.jsp"%>
			</s:if>
			<s:if test='%{user.isReceptionist=="Y"}'>
					<%@ include file="/common/receptionistLeftNav.jsp"%>
			</s:if>
			<s:if test='%{user.isSchoolLibrarian=="Y"}'>
					<%@ include file="/common/librarianLeftNav.jsp"%>
			</s:if>
			<s:property value="#session.academicYear.startDate" />
			<%-- <s:if test='%{user.academicYear.startDate == null}'></s:if> --%>
			</ul>
		</div>
	</div>