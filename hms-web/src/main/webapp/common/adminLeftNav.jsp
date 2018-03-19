<%@ include file="/common/taglibs.jsp"%>
					<li class="start active">
                   	 <a href="${pageContext.request.contextPath}/admin/manageAcademics.do?id=dashboard"
                            id="dashboard"> <i class="fa fa-home"></i>
                            <span class="title">Dashboard</span> <span class="selected"></span>
                        </a>
                    </li>
                    <li id="manageAtt">
						<a href="javascript:;" id="MSAT"> <i class="fa fa-calendar"></i>
							<span class="title">Manage Attendance / Leaves</span> <span class="selected"></span>
							<span class="arrow "></span> </a>
						<ul class="sub-menu">
							<li id="attendanceId">
								<s:url action="ajaxDoGetAttendanceForm"
									id="ajaxClassAttendencelink" namespace="/admin"
									includeParams="all" escapeAmp="false">
									<s:param name="staffAtt">studentAt</s:param>
								</s:url>
								<sj:a href="%{ajaxClassAttendencelink}" targets="mainContentDiv"
									cssClass="ajaxify MSAT">Add Student & Staff Attendance</sj:a>
							</li>
							<li>
								<s:url action="ajaxDoGetStaffAndStudenTLeaveReq"
									id="ajaxDoGetLeaveDetailsLeftLink" namespace="/staff"
									includeParams="all" escapeAmp="false">
									<s:param name="tempString">student</s:param>
								</s:url>
								<sj:a href="%{ajaxDoGetLeaveDetailsLeftLink}" targets="mainContentDiv"
									cssClass="ajaxify MSATLR">Student & Staff Leave Request</sj:a>
							</li>
						</ul>
					</li>
                   	<li id="manageStudent">
						<a href="javascript:;" id="MSTI"> <i class="fa fa-user"></i>
							<span class="title">Manage Student</span> <span class="selected"></span>
							<span class="arrow "></span> </a>
						<ul class="sub-menu">
							<li id="studentDiv">
								<s:url id="urlmanageStudent" action="ajaxGetStudyClassList"
									namespace="/student" includeParams="all" escapeAmp="false" />
								<sj:a id="manageStudents" href="%{urlmanageStudent}"
									targets="mainContentDiv" cssClass="ajaxify MSTI">Students Information</sj:a>
							</li>
							<%-- <li>
								<s:url id="ViewFeedbackList" action="ajaxViewFeedbackList"
									namespace="/admin" includeParams="all" escapeAmp="false">
									<s:param name="tempString">School Feedback Questions</s:param>
								</s:url>
								<sj:a id="ViewFeedbackList" href="%{ViewFeedbackList}"
									targets="mainContentDiv" cssClass="ajaxify AFBL">Feedback</sj:a>
							</li> --%>
							<li>
								<s:url id="urlStudentTransfer" action="ajaxStudentTransfer"
									namespace="/student" />
								<sj:a id="urlStudentTransfer" href="%{urlStudentTransfer}"
									targets="mainContentDiv" cssClass="ajaxify SSTS">
							Student Transfer</sj:a>
							</li>
							<li id="classAssignmentDiv">
								<s:url id="urlClassAssignment"
									action="ajaxViewAllClassAssignment" namespace="/admin" />
								<sj:a id="urlClassAssignment" href="%{urlClassAssignment}"
									targets="mainContentDiv" cssClass="ajaxify AMCA">
							Manage Class Assignment</sj:a>
							</li>
						   <li>
								<s:url id="urlGetCastSettings" action="ajaxCastSettingsHome"
									namespace="/admin" />
								<sj:a id="castDetails" href="%{urlGetCastSettings}"
									targets="mainContentDiv" cssClass="ajaxify CACS">Manage Community & Caste</sj:a>
							</li>
							<li>
								<s:url id="urlPromoteStudents"
									action="ajaxManagePromoteStudents" namespace="/admin" />
								<sj:a id="urlPromoteStudents" href="%{urlPromoteStudents}"
									targets="mainContentDiv" cssClass="ajaxify AMPS">
							Manage Promote Students</sj:a>
							</li>
							<li>
								<a href="javascript:;" id="SSCS"> <span class="title">Student Certificates</span> <span class="selected"></span> <span
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
										<s:url id="urlDoFee"
											action="ajaxDoGetStudyAndBonafiedGeneration"
											includeParams="all" escapeAmp="false" namespace="/admin">
											<s:param name="anyTitle">feeCertificate</s:param>
										</s:url>
										<sj:a id="doFee" href="%{urlDoFee}" targets="mainContentDiv"
											cssClass="ajaxify SSFC">Fee Certificate</sj:a>
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
									cssClass="ajaxify AUDS">
							Upload/Download Documents</sj:a>
							</li> --%>
							<li>
								<s:url id="urlUploadstudyMaterials" action="ajaxViewStudyClassMaterialList" namespace="/admin" />
								<sj:a id="urlUploadMaterials" href="%{urlUploadstudyMaterials}" targets="mainContentDiv" cssClass="ajaxify AUDS">
							Study Material </sj:a>
							</li>
							<!--<li>
								<s:url id="urlUploadNoEmailAndMobileStudent" action="ajaxDownloadNoEmailAndMobileStudentDetails" namespace="/admin" />
								<sj:a id="urlUploadNoEmailAndMobile" href="%{urlUploadNoEmailAndMobileStudent}" targets="mainContentDiv" cssClass="ajaxify AUEMS">
								No Email & Mobile Student </sj:a>
							</li>  -->	
							<li>
								<s:url action="ajaxApproveOrRejectOnlineAppointment"
									id="ajaxDoApproveOrRejectOnlineAppointment" namespace="/student"
									includeParams="all" escapeAmp="false">
								</s:url>
								<sj:a href="%{ajaxDoApproveOrRejectOnlineAppointment}" targets="mainContentDiv"
									cssClass="ajaxify AROAP">Online Appointments</sj:a>
							</li>
							<li>
								<s:url action="ajaxDoSelectStudentOptionalSubject"
									id="ajaxDoSelectStudentOptionalSubject" namespace="/student"
									includeParams="all" escapeAmp="false">
								</s:url>
								<sj:a href="%{ajaxDoSelectStudentOptionalSubject}" targets="mainContentDiv"
									cssClass="ajaxify ADSOP">Student Optional Subject</sj:a>
							</li>
							<s:if test='%{customerByCustId.alphaNumericRollNumber == "N" || customerByCustId.alphaNumericRollNumber == null || customerByCustId.alphaNumericRollNumber == ""}'>
							<li>
								<s:url action="ajaxDoGenerateStudentRollNumber"
									id="ajaxDoGenerateStudentRollNumber" namespace="/admin"
									includeParams="all" escapeAmp="false">
								</s:url>
								<sj:a href="%{ajaxDoGenerateStudentRollNumber}" targets="mainContentDiv"
									cssClass="ajaxify MSRN">Manage Student Roll Numbers</sj:a>
							</li>
							</s:if>
							
						</ul>
					</li> 
					<li id="manageStaff">
						<a href="javascript:;" id="MSF"> <i class="fa fa-user"></i> <span
							class="title">Manage Staff</span> <span class="selected"></span>
							<span class="arrow "></span> </a>
						<ul class="sub-menu">
							<li id="staffInfoDiv">
								<s:url id="urlManageStaff" action="ajaxDoManageStaff"
									namespace="/staff" />
								<sj:a id="urlManageStaff" href="%{urlManageStaff}"
									targets="mainContentDiv" cssClass="ajaxify MSF">
								Staff Information</sj:a>
							</li>
							<li>
								<s:url id="urlLeaves" action="ajaxViewAllManageLeaves"
									namespace="/admin" />
								<sj:a id="urlLeaves" href="%{urlLeaves}"
									targets="mainContentDiv" cssClass="ajaxify MMLS">
								Manage Leaves</sj:a>
							</li>
							<s:if test='%{customerByCustId.staffPermissionStatus=="Y"}'>
								<li>
									<s:url id="urlPermissions" action="ajaxViewAllPermissions"
										namespace="/student" />
									<sj:a id="urlPermissions" href="%{urlPermissions}"
										targets="mainContentDiv" cssClass="ajaxify MAPS">
									Manage Permissions</sj:a>
								</li>
							</s:if>
							<li>
								<s:url id="lessionPlanner" action="ajaxViewSubPlannerDetails"
									namespace="/staff" />
								<sj:a href="%{lessionPlanner}" targets="mainContentDiv"
									cssClass="ajaxify MSPS">
									Manage Subject Planner</sj:a>
							</li>
							<li>
								<s:url id="urlStaffApplicableSubjects" action="ajaxViewStaffEligibleSubjects" namespace="/reports"/>
								<sj:a id="urlStaffApplicableSubjects" href="%{urlStaffApplicableSubjects}" targets="mainContentDiv" cssClass="ajaxify STFES">Manage Staff Class Subjects</sj:a>
							</li>
							<%-- <s:if test='%{#session.enableSchoolShift !="N"}'> --%>
								<li>
									<s:url id="urlViewSchoolShiftInfo" action="ajaxViewSchoolShiftInfo" namespace="/staff"/>
									<sj:a id="urlViewSchoolShiftInfo" href="%{urlViewSchoolShiftInfo}" targets="mainContentDiv" cssClass="ajaxify MSSI">Manage School Shift Info</sj:a>
								</li>
								<li>
									<s:url id="urlManageStaffPaySlips" action="ajaxDoManageStaffPaySlips"
										namespace="/staff" />
									<sj:a id="urlManageStaffPaySlips" href="%{urlManageStaffPaySlips}"
										targets="mainContentDiv" cssClass="ajaxify MSFP">PaySlip</sj:a>
							 	</li>
							 	<li>
									<s:url id="urlManageTaskInfo" action="ajaxTaskInformationHome"	namespace="/staff" />
									<sj:a id="urlManageTaskInfo" href="%{urlManageTaskInfo}"  targets="mainContentDiv" cssClass="ajaxify MSTID"> Task Management 
									<s:if test='%{#session.tempString1 == "ST"}'>
										<span class="badge badge-info" ><font color="white"><b>New (<s:property value="tempId"/>)</b></font></span>
									</s:if>
									 </sj:a> 
								</li>
								<%-- <li>
									<s:url id="urlManageCalendarInfo" action="ajaxCalendarInformationHome"	namespace="/staff" />
									<sj:a id="urlManageCalendarInfo" href="%{urlManageCalendarInfo}"  targets="mainContentDiv" cssClass="ajaxify MSCAL"> My Calendar  
									 </sj:a> 
								</li> --%>
								<%-- <li>
									<s:url id="urlManageTaskInfo" action="ajaxTaskInformationHome"	namespace="/staff" />
									<sj:a id="urlManageTaskInfo" href="%{urlManageTaskInfo}"  targets="mainContentDiv" cssClass="ajaxify MSTID">
									Task Managment </sj:a>
								</li> --%>
							<%-- </s:if> --%>
							<%--<li>
								<s:url id="urlManageRooms" action="#" namespace="/hostel">
									<s:param name="anyTitle">rooms</s:param>
								</s:url>
								<sj:a id="manageRooms" href="%{urlManageRooms}"
									targets="mainContentDiv" cssClass="ajaxify SD">
								Salary Details</sj:a>
							</li>
							<li>
								<s:url id="urlAssignStudents" action="#" namespace="/hostel">
									<s:param name="anyTitle">beds</s:param>
								</s:url>
								<sj:a id="assignStudents" href="%{urlAssignStudents}"
									targets="mainContentDiv" cssClass="ajaxify RP">
									Recruitment Process</sj:a>
							</li>
							<li>
								<s:url id="urlMyLeaveLink" action="#" namespace="/admin" />
								<sj:a href="%{urlMyLeaveLink}" targets="mainContentDiv"
									cssClass="ajaxify TP">
								Teaching Performance</sj:a>
							</li>
							<li>
								<s:url id="urlMyLeaveLink" action="#" namespace="/admin" />
								<sj:a href="%{urlMyLeaveLink}" targets="mainContentDiv"
									cssClass="ajaxify SS">
								Staff Search</sj:a>
							</li>
							<li>
								<s:url id="StaffAttendanceMonthlyInPDF" action="#"
									namespace="/reports" includeParams="all" escapeAmp="false">
									<s:param name="tempString">Staff</s:param>
									<s:param name="plTitle">Staff Attendance Monthly(Monthly)</s:param>
								</s:url>
								<sj:a href="%{StaffAttendanceMonthlyInPDF}"
									targets="mainContentDiv" cssClass='ajaxify ASTAD'>
								Staff Attendance Report</sj:a>
							</li>
						--%>
						</ul>
					</li>
					<li id="manageTimeTable">
						<a href="javascript:;" id="MSF"> <i class="fa fa-table"></i> <span
							class="title">Manage Timetable</span> <span class="selected"></span>
							<span class="arrow "></span> </a>
						<ul class="sub-menu">
							<s:if test="%{#session.timetableGenerationByManual}">
								<li>
									<s:url id="urlPeriods" action="ajaxViewTimeTableDetails" namespace="/timeTable" />
									<sj:a href="%{urlPeriods}" targets="mainContentDiv" cssClass="ajaxify MTPS" id="managePeriods">Timetable</sj:a>
								</li>
							</s:if>
							<s:else>
								<li>
									<s:url id="urlPeriods" action="ajaxGetSchoolPeriods" namespace="/admin" />
									<sj:a href="%{urlPeriods}" targets="mainContentDiv" cssClass="ajaxify MTPS" id="managePeriods">Manage Periods</sj:a>
								</li>
								<li>
									<s:url id="urlSubjectsSettings"
										action="ajaxGetTimeTableClassSubjectsSettings"
										namespace="/admin" />
									<sj:a href="%{urlSubjectsSettings}" targets="mainContentDiv" cssClass="ajaxify MTSS">
										Subjects Settings</sj:a>
								</li>
								<li>
									<s:url id="urlClassSubjects" action="ajaxViewTeacherSubjects"
										namespace="/admin" />
									<sj:a href="%{urlClassSubjects}" targets="mainContentDiv"
										cssClass="ajaxify MTCS">
								Staff Class Subjects</sj:a>
								</li>
								<li>
									<s:url id="urlCombined" action="ajaxViewCombinedClassSubjects"
										namespace="/admin" />
									<sj:a href="%{urlCombined}" targets="mainContentDiv"
										cssClass="ajaxify MTCB">
								Combined Class Subjects</sj:a>
								</li>
								<%--<li>
									<s:url id="urlGenerateTimetable"
										action="ajaxViewManageTimeTable" namespace="/admin" />
									<sj:a href="%{urlGenerateTimetable}" targets="mainContentDiv"
										cssClass="ajaxify MTGT">
								Generate Timetable</sj:a>
								</li>
								--%>
								<li>
									<s:url id="urlFETTimetable" action="ajaxManageFETTimeTable"
										namespace="/timeTable" />
									<sj:a href="%{urlFETTimetable}" targets="mainContentDiv"
										cssClass="ajaxify MTFET">
								Generate Timetable Request</sj:a>
								</li>
								<li>
									<s:url id="urlFETTimetable" action="ajaxUploadTimetableDocuments"
										namespace="/staff" includeParams="all" escapeAmp="false">
										<s:param name="tempString">uploadTimetable</s:param>
										<s:param name="plTitle">Staff Details</s:param>
									</s:url>
									<sj:a href="%{urlFETTimetable}" targets="mainContentDiv" cssClass="ajaxify MTFET">
										Download Staff wise Timetable</sj:a>
								</li>
								<li>
									<s:url id="urlFETTimetable" action="ajaxClassWiseTimetable"
										namespace="/staff" includeParams="all" escapeAmp="false">
										<s:param name="tempString">uploadClassWiseTimetable</s:param>
										<s:param name="plTitle">Staff Details</s:param>
									</s:url>
									<sj:a href="%{urlFETTimetable}" targets="mainContentDiv" cssClass="ajaxify MTFET">
										Download Class Wise Timetable</sj:a>
								</li>
								<li>
									<s:url id="urlViewTimeTable" action="ajaxViewTimetable"
										namespace="/timeTable" includeParams="all" escapeAmp="false">
									</s:url>
									<sj:a href="%{urlViewTimeTable}" targets="mainContentDiv" cssClass="ajaxify VTT">
										View Timetable</sj:a>
								</li>
							</s:else>
						</ul>
					</li>
					<li id="studentInvoiceNav">
						<%-- <a
							href="${pageContext.request.contextPath}/schoolfee/adminGetSchoolFee.do?id=Fee"
							id="Fee"> <i class="fa fa-money"></i> <span class="title">Student
								Invoice</span> <span class="selected"></span> </a> --%>
						<a href="javascript:;" id="ESI"> <i class="fa fa-money"></i> <span
							class="title">Student Invoice</span> <span class="selected"></span> <span
							class="arrow"></span> </a>
							<ul class="sub-menu">
							<li id="adminSchoolFee">
								<s:url id="urlDoAdminSchoolFee" action="ajaxDoAdminSchoolFee" namespace="/schoolfee">
									<s:param value='<s:property value="#session.academicYear" />' name="academicYearId"></s:param>
								</s:url>
								<sj:a id="urlDoAdminSchoolFee" href="%{urlDoAdminSchoolFee}" targets="mainContentDiv" 
									cssClass="ajaxify SMP">Make Payment</sj:a>
							</li>
							<li id="feeConcession">
								<s:url id="urlDoSchoolFeeConcession"
									action="ajaxDoSchoolFeeConcession" namespace="/schoolfee">
								</s:url>
								<sj:a id="urlDoSchoolFeeConcession" href="%{urlDoSchoolFeeConcession}"
									targets="mainContentDiv" cssClass='ajaxify SFC'>
									Manage Concessions</sj:a>
							</li>
							<li id="todayFeeCollection">
								<s:url id="todayFeeCollection"
									action="ajaxGetTodayFeeCollection" namespace="/schoolfee">
								</s:url>
								<sj:a id="todayFeeCollection" href="%{todayFeeCollection}"
									targets="mainContentDiv" cssClass='ajaxify TFD'>
									Day Wise Fee Collection </sj:a>
							</li>
							<li>
								<a href="javascript:;" id="SSCS"> <span class="title">Deleted Invoices</span> <span class="selected"></span>  </a>
								<ul class="sub-menu">
									<li>
										<s:url id="deleteInvoiceCollection"
											action="ajaxGetDeleteInvoiceDetails"
											includeParams="all" escapeAmp="false" namespace="/schoolfee">
											<s:param name="anyTitle">RegularFee Invoice</s:param>
										</s:url>
										<sj:a id="deleteInvoiceCollection" href="%{deleteInvoiceCollection}"
											targets="mainContentDiv" cssClass="ajaxify DIC">Regular Fee Invoices</sj:a>
									</li>
									<li>
										<s:url id="deleteFineFeeInvoiceList"
											action="ajaxGetDeleteOtherFeeDetails"
											includeParams="all" escapeAmp="false" namespace="/schoolfee">
											<s:param name="anyTitle">Other Fee Invoices</s:param>
										</s:url>
										<sj:a id="deleteFineFeeInvoiceList" href="%{deleteFineFeeInvoiceList}"
											targets="mainContentDiv" cssClass="ajaxify DFIL">Other Fee Invoices</sj:a>
									</li>
								</ul>
							</li>
							<s:if test='%{customerByCustId.preferences.feeRefund==true}'>
								<li id="feeRefund">
									<s:url id="urlDoSchoolFeeRefund"
										action="ajaxDoSchoolFeeRefund" namespace="/schoolfee">
									</s:url>
									<sj:a id="urlDoSchoolFeeRefund" href="%{urlDoSchoolFeeRefund}"
										targets="mainContentDiv" cssClass='ajaxify SFC'>
										View Fee Refund Details</sj:a>
								</li>
							</s:if>
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
							<li>
								<s:url id="ViewMarkPersent" action="ajaxPassAndFailPersentGraphMoreDetails" namespace="/admin"> </s:url>
								<sj:a id="markPersent" href="%{ViewMarkPersent}"
									targets="mainContentDiv" cssClass="ajaxify ACWMP">Class Wise Exam Results</sj:a>
							</li>
							<li>
								<s:url id="urlHallTicketGeneration"
									action="ajaxDoGetHallTicketGeneration" namespace="/exam" />
								<sj:a id="urlHallTicketGeneration"
									href="%{urlHallTicketGeneration}" targets="mainContentDiv"
									cssClass="ajaxify AHTG">
							Hall Ticket Generation</sj:a>
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
							<li>
								<s:url id="performanceUrl"
									action="ajaxStudentPerformance" namespace="/exam" />
								<sj:a id="performanceId" href="%{performanceUrl}"
									targets="mainContentDiv" cssClass="ajaxify MSP">
								 Student Performance</sj:a>
							</li>
							<li>
								<s:url id="urlUploadQuestionBankMaterials" action="ajaxViewQuestionBankMaterialList" namespace="/exam" />
								<sj:a id="urlUploadQuestionBankMaterials" href="%{urlUploadQuestionBankMaterials}" targets="mainContentDiv" cssClass="ajaxify AUDS">
								Question Bank </sj:a>
							</li>
						</ul>
					</li>
					<li id="smsEmailDiv">
						<a href="javascript:;" id="ASMS"><i class="fa fa-envelope-o"></i>
							<span class="title">Email / SMS</span> <span class="selected"></span> <span
							class="arrow"></span> </a>
						<ul class="sub-menu">
							<s:if
								test='%{user.isSchoolTransport=="N" && user.isTransportFinance!="Y"}'>
								<s:if test='%{(user.isSchoolTeacher=="Y"|| user.isAdminCoordinator == "Y") && tempBoolean }'>
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
								<s:elseif test='%{user.isSchoolAdmin=="Y" || user.isSchoolPrincipal=="Y" || user.isSchoolDirector == "Y"}'>
									<li id="adminSms">
										<s:url id="urlSchoolWideSMSAndMessagesLink"
											action="ajaxDoSendSchoolWideMessages" namespace="/common"
											includeParams="all" escapeAmp="false">
											<s:param value="#session.academicYear" name="academicYearId" />
										</s:url>
										<sj:a href="%{urlSchoolWideSMSAndMessagesLink}" targets="mainContentDiv"
											cssClass='ajaxify ASMEL'>
												Send SMS or E-Mail</sj:a>
									</li>
									<!--  Commented by Siva , check EAZ-3151 -->
									<%-- <li id="buySmsLiId">
										<s:url id="buySmsDiv" action="ajaxViewBuySmsDetails" namespace="/admin"
											includeParams="all" escapeAmp="false">
										</s:url>
										<sj:a href="%{buySmsDiv}" targets="mainContentDiv" cssClass='ajaxify ABSM'> Buy SMS</sj:a>
									</li> --%>
								</s:elseif>
							</s:if>
							<s:if 
								test='%{(#session.customerTransportStauts==true) && (user.isSchoolTransport=="Y" || user.isSchoolAdmin=="Y" || user.isSchoolPrincipal=="Y" || user.isSchoolDirector == "Y")}'>
								<li>
									<s:url id="urlTransport"
										action="ajaxDoSendSchoolWideMessages" namespace="/common"
										includeParams="all" escapeAmp="false">
										<s:param value="#session.academicYear" name="academicYearId" />
										<s:param name="status">TR</s:param>
										<s:param name="plTitle">TRMessages</s:param>
									</s:url>
									<sj:a id="urlTransport" href="%{urlTransport}"
										targets="mainContentDiv" cssClass='ajaxify ATSMS'>
										Transport SMS</sj:a>
								</li>
							</s:if>
						</ul>
					</li>
				<s:if test='%{#session.customerTransportStauts==true}'>
					<li id="manageTransportDiv">
						<a href="/admin/transportDashboard.do" id="MTP"><i
							class="fa fa-road"></i> <span class="title">Manage
								Transport</span> <span class="selected"></span> <span class="arrow"></span>
						</a>
						<ul class="sub-menu">
							<li id="routeDiv">
								<s:url id="urlManageRoute" action="ajaxManageRoutes"
									namespace="/admin" />
								<sj:a id="manageRoute" href="%{urlManageRoute}"
									targets="mainContentDiv" cssClass="ajaxify MTP">
								 Manage Routes</sj:a>
							</li>
							<li>
								<s:url id="doManageDriverHelper"
									action="ajaxAddDriverOrHelperStaff" namespace="/admin">
									<s:param name="anyTitleName">ROLE_DRIVER</s:param>
								</s:url>
								<sj:a id="manageDriverHelper" href="%{doManageDriverHelper}"
									targets="mainContentDiv" cssClass="ajaxify MDH">
								 Manage Driver/Helper</sj:a>
							</li>
							<li>
								<s:url id="doVehicleMaintenance"
									action="ajaxManageTransportVehicles" namespace="/admin" />
								<sj:a id="vehicleMaintenance" href="%{doVehicleMaintenance}"
									targets="mainContentDiv" cssClass="ajaxify MVT">
								 Manage Vehicles</sj:a>
							</li>
							<li>
								<s:url id="doManageVehicleRoutes"
									action="ajaxManageVehicleRoutes" namespace="/admin" />
								<sj:a id="manageVehicleRoutes" href="%{doManageVehicleRoutes}"
									targets="mainContentDiv" cssClass="ajaxify MVR">
								 Manage Vehicle Routes</sj:a>
							</li>
							<li id="transDiv">
									<a href="/admin/transportDashboard.do" id="AMTP"> <span
											class="title">Manage Students Transport</span> <span
											class="selected"></span> <span class="arrow"></span>
									</a>
									<ul class="sub-menu">
										<!-- <li id="assignStudentsDiv"><s:url id="doAssignStudents"
												action="ajaxDoAssignStudent" namespace="/admin" /> <sj:a
												id="doTransportAssignStudents" href="%{doAssignStudents}"
												targets="mainContentDiv" cssClass="ajaxify AMTP">
										 Assign Students To Vehicles</sj:a></li> -->
										 <li id="assignStudentsDiv">
											<s:url id="doAssignStudentsForm" action="ajaxDoAssignStudentForm" namespace="/admin" /> 
											<sj:a id="doTransportAssignStudentsForm" href="%{doAssignStudentsForm}" 
												targets="mainContentDiv" cssClass="ajaxify ADASF"> Assign Students To Vehicles</sj:a>
										</li>
										<li><s:url id="downloadStudentsApplication"
												action="ajaxStudentTransportApplication" namespace="/admin" />
											<sj:a href="%{downloadStudentsApplication}"
												targets="mainContentDiv" cssClass="ajaxify MASR">
										 Download Transport Request Form</sj:a></li>
									</ul>
								</li>
								<s:if test="%{#session.AllExpiryDates == true}">
								<li>
									<s:url id="urlManageTransportsWarnings"
										action="ajaxDoGetVehiclesInformation" namespace="/admin">
										<s:param name="tempBoolean" value="true"></s:param>
									</s:url>
									<sj:a href="%{urlManageTransportsWarnings}" id="expiryDate"
										targets="mainContentDiv" cssClass="ajaxify TRWS">Transport Warnings</sj:a>
								</li>
							</s:if>
						</ul>
					</li>
				</s:if>
				<li id="adminInboxDiv">
						<a href="javascript:;" id="AMAS"><i class="fa fa-envelope"></i>
							<span class="title">Inbox</span> <span class="selected"></span> <span
							class="arrow"></span> </a>
						<ul class="sub-menu">
							<li id="inboxesDetailsDiv">
								<s:url id="urlSmsDetails"
									action="ajaxDoInboxGetScrapMessagesList" namespace="/common">
								</s:url>
								<s:param value="#session.academicYear" name="academicYearId" />
								<sj:a href="%{urlSmsDetails}" targets="mainContentDiv"
									cssClass='ajaxify AMAS'>
									Messages & Alerts</sj:a>
							</li>
							<li>
								<s:url id="urlInboxesDetails"
									action="ajaxDoGetSchoolWideAlertsList" namespace="/common">
								</s:url>
								<s:param value="#session.academicYear" name="academicYearId" />
								<sj:a href="%{urlInboxesDetails}" targets="mainContentDiv"
									cssClass='ajaxify DMS'>
									Dashboard Alerts</sj:a>
							</li>
							<li>
								<s:url id="urlCircularDetails" action="ajaxDoGetCircularMessagesList" namespace="/common" />
								<sj:a href="%{urlCircularDetails}" targets="mainContentDiv"
									cssClass='ajaxify ACM'>Circular Messages</sj:a>
							</li>
						</ul>
					</li>	
					<li id="academicsDiv">
						<a href="javascript:;" id="ADM"> <i class="fa fa-group"></i>
							<span class="title">Academics</span> <span class="selected"></span>
							<span class="arrow"></span> </a>
						<ul class="sub-menu">
							<li>
								<s:url id="urlStudyClassSubjects"
									action="ajaxGetStudyClassSubjects" namespace="/admin"></s:url>
								<sj:a id="classSubjects" href="%{urlStudyClassSubjects}"
									targets="mainContentDiv" cssClass="ajaxify ADM"> Subjects</sj:a>
							</li>
							<li id="studyClassId">
								<s:url id="urlStudyClass" action="ajaxDoManageClassSections"
									namespace="/admin"></s:url>
								<sj:a href="%{urlStudyClass}" targets="mainContentDiv"
									cssClass='ajaxify YARS'> Class &amp; Sections</sj:a>
							</li>	
					
							<li id="adminFeeDiv">
								<s:url id="urlFeeSetting" action="ajaxSchoolFeeSetting"
									namespace="/schoolfee"></s:url>
								<sj:a href="%{urlFeeSetting}" targets="mainContentDiv"
									cssClass="ajaxify FS"> Fee Settings</sj:a>
							</li>
							<%--<li>
								<s:url id="urlSchoolTimingsDetails"
									action="ajaxDoInboxGetScrapMessages" namespace="/common"></s:url>
								<sj:a href="%{urlSchoolTimingsDetails}" targets="mainContentDiv"
									cssClass='ajaxify CST'> School Timings</sj:a>
							</li>
							<li>
								<s:url id="urlTransportSettingsDetails"
									action="ajaxDoInboxGetScrapMessages" namespace="/common"></s:url>
								<sj:a href="%{urlTransportSettingsDetails}"
									targets="mainContentDiv" cssClass='ajaxify CTS'> Transport Settings</sj:a>
							</li>
						--%>
						</ul>
					</li>
					<li id="manageStoreInventory">
						<a href="javascript:;" id="SI"> <i class="fa fa-book"></i> 
							<span class="title">Store and Inventory</span> 
							<span class="selected"></span> 
							<span class="arrow"></span> 
						</a>
						<ul class="sub-menu">
						   <li id="storeInfo">
								<s:url id="urlManageStore" action="ajaxDoManageStores" 	namespace="/store" includeParams="all" escapeAmp="false"/>
								<sj:a id="urlManageStore" href="%{urlManageStore}"	targets="mainContentDiv" cssClass="ajaxify SI">
								Store Information</sj:a>
							</li>
							<li id="ItemTypes">
								<s:url id="urlManageItemTypes" action="ajaxViewItemTypes" namespace="/store" includeParams="all" escapeAmp="false"/>
								<sj:a id="urlManageItems" href="%{urlManageItemTypes}"	targets="mainContentDiv" cssClass="ajaxify IT">
								Item Types</sj:a>
							</li>
							<li id="Item">
								<s:url id="urlManageItems" action="ajaxManageStoreItems" namespace="/store" includeParams="all" escapeAmp="false"/>
								<sj:a id="urlManagestoreItems" href="%{urlManageItems}"	targets="mainContentDiv" cssClass="ajaxify AII">
								Add / Issue Items</sj:a>
							</li>
							<li id="Supplier">
								<s:url id="urlManageSuppliers" action="ajaxManageSuppliers"	namespace="/store" includeParams="all" escapeAmp="false"/>
								<sj:a id="urlManageSuppliersData" href="%{urlManageSuppliers}"	targets="mainContentDiv" cssClass="ajaxify SIS">
								Suppliers </sj:a>
							</li>
							<li id="reports">
								<a href="javascript:;" class="ajaxify" id="SIR"> 
									<span class="title">Reports</span> 
									<span class="selected"></span>
									<span class="arrow"></span> 
								</a>
								<ul class="sub-menu">
								   	<li id="storeWiseReport">
										<s:url id="urlStoreWiseReport" action="ajaxGetStores" 	namespace="/store" includeParams="all" escapeAmp="false">
										<s:param name="reportType">Store Wise Details Report</s:param>
										</s:url>
										<sj:a id="urlStoreWiseReport" href="%{urlStoreWiseReport}"	targets="mainContentDiv" cssClass="ajaxify SIRS">
										Store Wise Details</sj:a>
									</li>
									<li id="itemIssueReport">
										<s:url id="urlItemIssueReport" action="ajaxGetStores"	namespace="/store" includeParams="all" escapeAmp="false" >
										<s:param  name="reportType">Issued Items Details Report</s:param>
										</s:url>
										<sj:a id="urlItemIssueReport" href="%{urlItemIssueReport}"	targets="mainContentDiv" cssClass="ajaxify IIDR">
										Issued Items Details</sj:a>
									</li>
									<li id="supplierWiseReport">
										<s:url id="urlsupplierWiseReport" action="ajaxGetSuppliers" 	namespace="/store" includeParams="all" escapeAmp="false" >
										<s:param  name="reportType">Supplier Details Report</s:param>
										</s:url>
										<sj:a id="urlsupplierWiseReport" href="%{urlsupplierWiseReport}" targets="mainContentDiv" cssClass="ajaxify SDR">
										Supplier Details Report</sj:a>
									</li>
							   </ul>
							</li>
						</ul>
					</li>	 				
					
					<li id="schoolSettingsDiv" class="settingsDiv">
						<a href="javascript:;" id="ES"> <i class="fa fa-cogs"></i> <span
							class="title">Settings</span> <span class="selected"></span> <span
							class="arrow"></span> </a>
						<ul class="sub-menu">
							<li id="academicSettingsDiv">
								<s:url id="urlSchoolSettings"
									action="ajaxAcademicSchoolSettings" namespace="/admin">
									<s:param value='<s:property value="#session.academicYear" />'
										name="academicYearId">
									</s:param>
								</s:url>
								<sj:a id="urlSchoolSettings" href="%{urlSchoolSettings}"
									targets="mainContentDiv" cssClass="ajaxify AAP">
									Academic Planner</sj:a>
							</li>
							<li id="manageSchool">
								<s:url id="urlSchoolInformation"
									action="ajaxDoSchoolInformation" namespace="/admin">
								</s:url>
								<sj:a id="urlSchoolInformation" href="%{urlSchoolInformation}"
									targets="mainContentDiv" cssClass='ajaxify AMCS'>
									Manage School</sj:a>
							</li>
							<li id="schoolAccountDetails">
								<s:url id="urlSchoolAccountInformation" action="ajaxDoSchoolAccountInformation" namespace="/account"> </s:url>
								<sj:a id="urlSchoolAccountInformation" href="%{urlSchoolAccountInformation}" targets="mainContentDiv" cssClass='ajaxify SAI'>School Account Details</sj:a>
							</li>
							<s:if test="%{customerByCustId.hostelModuleStatus}">
								<li>
									<s:url id="urlHostelDetails" action="ajaxViewHostelDetails"
										namespace="/admin">
										<s:param value='<s:property value="#session.academicYear" />'
											name="academicYearId">
										</s:param>
									</s:url>
									<sj:a href="%{urlHostelDetails}" targets="mainContentDiv"
										cssClass='ajaxify AMH'>
										Manage Hostel</sj:a>
								</li>
							</s:if>
							<li id="contactEmail">
								<s:url id="urlSchoolFromEmailInfo"
									action="ajaxDoSchoolFromEmailInfo" namespace="/admin">
								</s:url>
								<sj:a id="urlSchoolFromEmailInfo" href="%{urlSchoolFromEmailInfo}"
									targets="mainContentDiv" cssClass='ajaxify CFE'>
									Email Configuration</sj:a>
							</li>
							<li>
								<s:url id="urlConfigureAutoReports" action="ajaxDoConfigureAutoReports" namespace="/admin"></s:url>
								<sj:a id="urlConfigureAutoReports" href="%{urlConfigureAutoReports}" targets="mainContentDiv" 
									cssClass='ajaxify CFE'> Configure Auto Reports</sj:a>
							</li>
							
							<li>
								<s:url id="viewDepartmentHead" action="ajaxViewDepartmentHead"
									namespace="/admin" />
								<sj:a href="%{viewDepartmentHead}" targets="mainContentDiv"
									id="viewDepartmentHead" cssClass="ajaxify CDH">
										Create Department
									</sj:a>
							</li>
							<li>
								<s:url id="SchoolSettingsHolidays"
									action="ajaxViewSchoolSettingsHolidays" namespace="/admin">
								</s:url>
								<sj:a href="%{SchoolSettingsHolidays}" targets="mainContentDiv"
									cssClass='ajaxify AHD'>
									Holidays</sj:a>
							</li>
							<%-- <li>
								<s:url id="feedbackSettings" action="ajaxGetFeedbackSettings"
									namespace="/admin" includeParams="all" escapeAmp="false">
									<s:param value="#session.academicYear" name="academicYearId" />
								</s:url>
								<sj:a href="%{feedbackSettings}" targets="mainContentDiv"
									cssClass='ajaxify AFBS'>
									Feedback Settings</sj:a>
							</li> --%>
							<li>
								<s:url id="uploadEnrollmentSheet"
									action="ajaxDoUploadCustomerEnrollmentSheet" namespace="/admin" />
								<sj:a href="%{uploadEnrollmentSheet}" targets="mainContentDiv"
									cssClass='ajaxify AUES'>
									Upload Enrollment Sheet</sj:a>
							</li>
							<li>
								<s:url id="loginAccessForRole"
									action="ajaxManageLoginAccessbilityForRoles" namespace="/admin" />
								<sj:a href="%{loginAccessForRole}" targets="mainContentDiv"
									cssClass='ajaxify LAFR'>
									Login Accessibility</sj:a>
							</li>
							<li>
								<s:url id="manageMailchimpId"
									action="ajaxMailchimpAccountSettings" namespace="/admin" />
								<sj:a href="%{manageMailchimpId}" targets="mainContentDiv"
									cssClass='ajaxify MMC'>
									Mailchimp</sj:a>
							</li>
							
						</ul>
					</li>
					<li id="admissionsNav">
						<a href="javascript:;" id="ADMS"> <i class="fa fa-laptop"></i>
							<span class="title">Admissions</span> <span class="selected"></span>
							<span class="arrow"></span> </a>
						<ul class="sub-menu">
							<li id="applicationsNav">
								<s:url id="urlGetAdmissions" action="ajaxGetOnlineAdmissions" namespace="/admin" />
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
							<li id="admissionSettgs">
								<s:url id="urlGetAdmissionSettings"
									action="ajaxAdmissionSettingsHome" namespace="/admin" />
								<sj:a id="classDetails" href="%{urlGetAdmissionSettings}"
									targets="mainContentDiv" cssClass="ajaxify ADST">Admission Settings</sj:a>
							</li>
							<li id="admittedStudsNav">
								<s:url id="urlGetAdmittedStudents"
									action="ajaxViewAdmittedStudents" namespace="/admin" />
								<sj:a id="urlGetAdmittedStudents"
									href="%{urlGetAdmittedStudents}" targets="mainContentDiv"
									cssClass="ajaxify AMS">Admitted Students</sj:a>
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
							<li>
								<s:url id="onlineAdmissions" action="ajaxGetOnlineAdmissionsCode" namespace="/admin" />
								<sj:a id="onlineAdmissions" href="%{onlineAdmissions}"
									targets="mainContentDiv" cssClass="ajaxify OADMS">Online Admissions</sj:a>
							</li>
						</ul>
					</li>
				

					

				
			