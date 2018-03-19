<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<%-- <div class="col-lg-0 col-md-3 col-sm-6 col-xs-12">
		<div class="mainDashboard-stat mediumPurple">
                                <div class="stat-header">
									<b>Fee Summary</b>
								</div>
                                    <div class="visual" style="width: 315px; font-size: 13px; line-height: 0px; color: white; margin-left: -100px;margin-top: -0%;">
                                        <table style="width: 100%;">
                                                	<s:iterator value="schoolFeeList" status="stat">
                                            <tr style="height: 15px;">
                                                    <td style="text-align: right;">Total Amt&nbsp;:&nbsp; </td>
                                                    <td style="text-align: left;">&nbsp;  <b><s:property value="schoolFeeList[#stat.count-1][0]" /></b></td>
                                                </tr>
                                                <tr style="height: 15px;">
                                                    <td style="text-align: right;">Collected Amt&nbsp;:&nbsp;</td>
                                                    <td style="text-align: left;">&nbsp; <b><s:property value="schoolFeeList[#stat.count-1][1]" /></b></td>
                                                </tr>
                                                <tr style="height: 15px;">
                                                    <td style="text-align: right;">Balance Amt&nbsp;:&nbsp; </td>
                                                    <td style="text-align: left;">&nbsp; <b><s:property value="schoolFeeList[#stat.count-1][6]" /></b></td>
                                                </tr>
                                                <tr style="height: 15px;">
                                                    <td style="text-align: right;">Today Collection&nbsp;:&nbsp;</td>
                                                    <td style="text-align: left;"><b><s:property value="schoolFeeList[#stat.count-1][5]" /></b></td>
                                                </tr>
                                                
                                                <tr style="height: 15px;">
                                                    <td style="text-align: right;">Today Other Fee&nbsp;:&nbsp; </td>
                                                    <td style="text-align: left;"><b><s:property value="schoolFeeList[#stat.count-1][7]" /></b></td>
                                                </tr>
                                                </s:iterator>
                                        </table>
                                    </div>
									<a data-toggle="modal" href="#popupFeeDetailsDiv" class="more" onclick="javascript:popupFeeSummary(this.value);" >
			View more <i class="m-icon-swapright m-icon-white"></i>
			</a>  
			</div>
         </div> --%>
         <div id="popupFeeDetailsDiv"></div>

	<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
		<div class="mainDashboard-stat mediumPurple">
		<div class="stat-header">
				<b>Today Fee Summary</b>
			</div>
			<div class="visual">
				<s:if test="%{countryId == 99}">
					<i class="fa fa-inr"></i>
				</s:if>
				<s:elseif test="%{countryId == 156}">
					<i class="fa fa-nsn"></i>
				</s:elseif>
				<s:else>
					<i class="fa fa-inr"></i>
				</s:else>
				<!-- <i class="fa fa-inr"></i> -->
			</div>
			<div class="details">
			<div class="number"></div>
			<s:iterator value="schoolFeeList" status="stat">
				<div class="desc">  
					Fee Amount&nbsp;:&nbsp;<b><s:property value="schoolFeeList[#stat.count-1][5]" /></b>
				</div>
				<div class="desc">                           
					Other Fee&nbsp;:&nbsp;<b><s:property value="schoolFeeList[#stat.count-1][7]" /></b>
				</div>
				</s:iterator>
			</div>
			<a data-toggle="modal" href="#popupFeeDetailsDiv" class="more" onclick="javascript:popupFeeSummary(this.value);" >
			View more <i class="m-icon-swapright m-icon-white"></i>
			</a>  
		</div>
	</div>
	<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
		<div class="mainDashboard-stat purple">
			<div class="stat-header">
				<b>Today Absentees</b>
			</div>
			<div class="visual">
				<i class="fa fa-clock-o"></i>
			</div>
			<div class="details">
			<div class="number"></div>
				<div class="desc">  
					Students&nbsp;:&nbsp;<b><s:property value="objectList.size" /></b>
				</div>
				<div class="desc">                           
					Staff&nbsp;:&nbsp;<b><s:property value="tempList.size" /></b>
				</div>
				<s:if test='%{customerByCustId.standardType !="P"}'>
					<s:url id="urlInboxesDetails" action="ajaxDoSendSchoolWideMessages" namespace="/common" includeParams="all" escapeAmp="false">
						<s:param value="#session.academicYear" name="academicYearId" />
						<s:param name="plTitle">absenteesMessages</s:param>
						<s:param name="anyTitle">P</s:param>
					</s:url>
					<sj:a href="%{urlInboxesDetails}" targets="mainContentDiv" cssStyle="color:white; padding-left: 130px;">
					<i class="fa fa-comment"></i>&nbsp;&nbsp;<b>Send SMS</b></sj:a>
				</s:if>
			</div>
				<a class="more" data-toggle="modal" href="#responsive">View more <i class="m-icon-swapright m-icon-white"></i></a>
		</div>
	</div>
	<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
		<div class="mainDashboard-stat mediumPurple">
			<div class="stat-header">
				<b>Attendance Not Recorded </b>
			</div>
			<div class="visual">
				<i class="fa fa-calendar"></i>
			</div>
			<div class="details">
			<div class="number"></div>
				<div class="desc">  
					Class Count&nbsp;:&nbsp;<b><s:property value="attendanceSubmittedClassesCount" /></b>
				</div>
				
			</div>
			<a class="more" data-toggle="modal" href="#attendanceRecordDiv">View more <i class="m-icon-swapright m-icon-white"></i></a>               
		</div>
	</div>
	<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
		<div class="mainDashboard-stat green">
		<div class="stat-header">
				<b>SMS Details</b>
			</div>
			<div class="visual">
				<i class="fa fa-comment"></i>
			</div>
			<div class="details">
			<div class="number"></div>
				<div class="desc">  
					Used&nbsp;:&nbsp;<b><s:property value="smsCnt" /></b>
				</div>
				<div class="desc">                           
					Remaining&nbsp;:&nbsp;<b><s:if test="%{(smsAlloted)-(smsCnt) > 0 }">
							 <s:property value="(smsAlloted)-(smsCnt)" />
						</s:if>
						<s:else>
							0 
						</s:else></b>
				</div>
			</div>
			<b  class="more" style="padding: 12px;">
			</b>               
		</div>
	</div>
	<div class="col-md-12">			 
	</div>
	<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
		<div class="mainDashboard-stat blue">
			<div class="stat-header">
				<b>School Info</b>
			</div>
			<div class="visual">
				<a class="glyphicons no-js group" href="#"><i></i></a>
			</div>
			<div class="details">
				<div class="number"></div>
				<div class="desc">  
					Students&nbsp;:&nbsp;<b><s:property value="tempId1" /></b>
				</div>
				<div class="desc">                           
					Staff&nbsp;:&nbsp;<b><s:property value="tempId2" /></b>
				</div>
				
			</div>
			<b  class="more" style="padding: 12px;">
			</b>                 
		</div>
	</div>
		<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
		<div class="mainDashboard-stat green">
			<div class="stat-header">
				<b>Transport Students</b>
			</div>
			<div class="visual">
				<a class="glyphicons no-js group" href="#"><i></i></a>
			</div>
			<div class="details">
				<div class="number"></div>
				
				<s:if test='%{#session.customerTransportStauts==true}'>
					<div class="desc">
						Transport Students&nbsp;:&nbsp; <b><s:property value="studentsTransportCount" /></b>
					</div>
				</s:if>
				
			</div>
			<b  class="more" style="padding: 12px;">
			</b>                 
		</div>
	</div>
	<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
		<div class="mainDashboard-stat yellow">
			<div class="stat-header">
				<b>Today Birthdays</b>
			</div>
			<div class="visual">
				<i class="fa fa-gift"></i>
			</div>
			<div class="details">
			<div class="number"></div>
				<div class="desc">  
					Students&nbsp;:&nbsp;<b><s:property value="tempList1.size" /></b>
				</div>
				<div class="desc">                           
					Staff&nbsp;:&nbsp;<b><s:property value="tempList2.size" /></b>
				</div>
			</div>
			<a class="more" data-toggle="modal" href="#birthdayDiv">View more <i class="m-icon-swapright m-icon-white"></i></a>               
		</div>
	</div>
	 <div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
	 	<div class="adminDashboard-stat green" id="adminAttendanceDiv">
	 		<div class="stat-header" >
				<div align="center" style="font-size: 12px;"><b>Attendance</b></div>
			</div>
			<div class="visual">
				<i class="fa fa-calendar"></i>
			</div>
			<div class="details">
				<div class="boxTemplate">
					<s:url action="ajaxDoGetAttendanceForm" id="ajaxClassAttendencelink" namespace="/admin" includeParams="all" escapeAmp="false">
						<s:param name="staffAtt">studentAt</s:param>
					</s:url>
					<sj:a href="%{ajaxClassAttendencelink}" targets="mainContentDiv" cssClass="ajaxify title MSAT "></sj:a>
				</div>
			</div>
		</div>
	</div>
	<div class="col-md-12">			 
	</div>
	<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
		<div class="adminDashboard-stat purple" id="sendSms">
			<div class="stat-header" >
				<div align="center" style="font-size: 12px;"><b>SMS</b></div>
			</div>
			<div class="visual">
				<i class="fa fa-comment"></i>
			</div>
			<div class="details">
				<div class="boxTemplate">
					<s:url id="urlSMSDetails" action="ajaxDoGetSchoolWideMessagesList" namespace="/common" includeParams="all" escapeAmp="false">
						<s:param value="#session.academicYear" name="academicYearId" />
					</s:url>
					<sj:a href="%{urlSMSDetails}" targets="mainContentDiv" cssClass='ajaxify title ASMEL'></sj:a>
				</div>
			</div>
		</div>
	</div>
	<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
		<div class="adminDashboard-stat mediumPurple" id="invoiceDiv">
			<div class="stat-header" >
				<div align="center" style="font-size: 12px;"><b>Student Invoice</b></div>
			</div>
		     <div class="visual">
				<i class="fa fa-money"></i>
			</div>
			<div class="details">
				<div class="boxTemplate">
					<s:url id="urlDoAdminSchoolFee" action="ajaxDoAdminSchoolFee" namespace="/schoolfee">
						<s:param value='<s:property value="#session.academicYear" />' name="academicYearId"></s:param>
					</s:url>
					<sj:a href="%{urlDoAdminSchoolFee}" targets="mainContentDiv" 
						cssClass="ajaxify SMP"></sj:a>
				</div>
			</div>
		</div>
	</div>
	<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
		<div class="adminDashboard-stat green" id="exminationDiv">
			<div class="stat-header" >
				<div align="center" style="font-size: 12px;"><b>Examination</b></div>
			</div>
		     <div class="visual">
				<i class="fa fa fa-bell"></i>
			</div>
			<div class="details">
				<div class="boxTemplate">
					<s:url id="urlDoAdminExamination" action="manageExaminationDetails" namespace="/exam">
						
					</s:url>
					<sj:a href="%{urlDoAdminExamination}" targets="mainContentDiv" 
						cssClass="ajaxify GAE"></sj:a>
				</div>
			</div>
		</div>
	</div>
	<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
		<div class="adminDashboard-stat mediumSlateBlue" id="assginmentDiv">
			<div class="stat-header" >
				<div align="center" style="font-size: 12px;"><b>Student Assignment</b></div>
			</div>
			<div class="visual">
				<i class="fa fa-file"></i>
			</div>
			<div class="details">
				<div class="boxTemplate">
					<s:url id="urlClassAssignment" action="ajaxViewAllClassAssignment" namespace="/admin" />
					<sj:a href="%{urlClassAssignment}" targets="mainContentDiv" cssClass="ajaxify title AMCA "></sj:a>
				</div>
			</div>
		</div>
	</div>
	<div class="col-md-12">			 
	</div>
	<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
		<div class="adminDashboard-stat red-inteBlue" id="studentInfo">
			<div class="stat-header" >
				<div align="center" style="font-size: 12px;"><b>Student Info</b></div>
			</div>
			<div class="visual">
				<a class="glyphicons no-js group" href="#"><i></i></a>
			</div>
			<div class="details">
				<div class="boxTemplate">
					<s:url id="urlmanageStudent" action="ajaxGetStudyClassList" namespace="/student" includeParams="all" escapeAmp="false" />
					<sj:a  href="%{urlmanageStudent}" targets="mainContentDiv" cssClass="ajaxify title MSTI "></sj:a>
				</div>
			</div>
		</div>
	</div>
	<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
		<div class="adminDashboard-stat purple" id="staffInfo">
			<div class="stat-header" >
				<div align="center" style="font-size: 12px;"><b>Staff Info</b></div>
			</div>
			<div class="visual">
				<i class="fa fa-user"></i>
			</div>
			<div class="details">
				<div class="boxTemplate">
					<s:url id="urlManageStaff" action="ajaxDoManageStaff" namespace="/staff" />
					<sj:a href="%{urlManageStaff}" targets="mainContentDiv" cssClass="ajaxify title MSF "></sj:a>
				</div>
			</div>

		</div>
	</div>
	<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
		<div class="adminDashboard-stat inkBright" id="manageLeaves">
			<div class="stat-header" >
				<div align="center" style="font-size: 12px;"><b>Manage Leaves</b></div>
			</div>
			<div class="visual">
				<i class="fa fa-clock-o"></i>
			</div>
			<div class="details">
				<div class="boxTemplate">
					<s:url id="urlmanageLeaves" action="ajaxViewAllManageLeaves" namespace="/admin" />
					<sj:a href="%{urlmanageLeaves}" targets="mainContentDiv" cssClass="ajaxify MMLS title"></sj:a>
				</div>
			</div>
		</div>
	</div>
	<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
		<div class="adminDashboard-stat darkSlateGray" id="IdCardGeneration">
			<div class="stat-header" >
				<div align="center" style="font-size: 12px;"><b>Id Card Generation</b></div>
			</div>
			<div class="visual">
				<i class="fa fa-credit-card"></i>
			</div>
			<div class="details">
				<div class="boxTemplate">
					<s:url id="urlDoIdCardsGenerations" namespace="/admin" action="ajaxIDCardsGenerations" />
					<sj:a  href="%{urlDoIdCardsGenerations}" targets="mainContentDiv" cssClass='ajaxify title ID'></sj:a>
				</div>
			</div>
		</div>
	</div>
	<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
		<div class="adminDashboard-stat steelBlue" id="admissionsDiv">
			<div class="stat-header" >
				<div align="center" style="font-size: 12px;"><b>Admissions</b></div>
			</div>
			<div class="visual">
				<i class="fa fa-pencil-square-o"></i>
			</div>
			<div class="details">
				<div class="boxTemplate">
					<s:url id="urlGetAdmissions" action="ajaxGetOnlineAdmissions" namespace="/admin" />
					<sj:a href="%{urlGetAdmissions}" targets="mainContentDiv" cssClass="ajaxify title ADMS "></sj:a>
				 </div>
			</div>
		</div>
	</div>
	<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
		<div class="adminDashboard-stat darkSlateGray" id="reportsDiv">
			<div class="stat-header" >
				<div align="center" style="font-size: 12px;"><b>Reports</b></div>
			</div>
			<div class="visual">
				<i class="fa fa-bar-chart-o"></i>
			</div>
			<div class="details">
				<div class="boxTemplate">
					<s:url id="importStudCommunityExcelSheet" action="ajaxDoReligionWiseDetails" escapeAmp="false" includeParams="all" namespace="/reports">
						<s:param name="tempString">Student</s:param>
						<s:param name="plTitle">CommunityDetails</s:param>
					</s:url>
					<sj:a href="%{importStudCommunityExcelSheet}" cssClass='ajaxify title SCS ' targets="mainContentDiv"></sj:a>
				</div>
			</div>
		</div>
	</div>
	<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
		<div class="adminDashboard-stat green .more" id="help">
			<div class="stat-header" >
				<div align="center" style="font-size: 12px;"><b>Help</b></div>
			</div>
			<div class="visual">
				<i class="fa fa-file-text"></i>
			</div>
			<div class="details">
				<div class="boxTemplate">
					<s:url id="urlHelpDocuments" namespace="/reports" action="ajaxAadminGetHelpDocuments" />
					<sj:a  href="%{urlHelpDocuments}" targets="mainContentDiv" cssClass='HelpDoc.ajaxify Helps'></sj:a>
				</div>
			</div>
		</div>
	</div>
	<div id="responsive" class="modal fade form-horizontal" data-width="960" style="margin-top: 100px;">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true"></button>
			<h4 class="modal-title">Today Absentees</h4>
		</div>
		<div class="modal-body">
				<div class="form-body">
					<div class="col-md-12">
						<div class="col-md-6">
							<div><b>Students</b></div>
							<s:if test="%{objectList != null && !objectList.isEmpty()}">
							<div id="studentAbsentDiv">
							<table
								class="table table-striped table-bordered table-hover table-full-width">
								<thead>
								<s:if test='%{currentAcademicYear.captureAttendanceBy=="O"}'>
									<tr>
										<th>Name [AdmissionNo]</th>
										<th>Class</th>
									</tr>
							   </s:if>
							   <s:else>
							   		<tr>
							   			<th>Admission Number</th>
							   			<th>Student Name</th>
							   			<th>Class & Section</th>
							   			<th>Absent Session</th>
							   		</tr>
							   </s:else>
								</thead>
								<tbody>
									<s:iterator value="objectList" status="stat">
										<s:if test='%{currentAcademicYear.captureAttendanceBy=="O"}'>
											<tr>
												<td><s:property value="objectList[#stat.count-1][0]" />&nbsp;[<s:property value="objectList[#stat.count-1][1]" />]</td>
												<td><s:property value="objectList[#stat.count-1][2]" /></td>
											</tr>
										</s:if>
										<s:else>
											<tr>
												<td><s:property value="objectList[#stat.count-1][1]" /></td>
												<td><s:property value="objectList[#stat.count-1][0]" /></td>
												<td><s:property value="objectList[#stat.count-1][2]" /></td>
												<s:if test="%{(objectList[#stat.count-1][3] != 'Y') && (objectList[#stat.count-1][4] != 'Y')}">
												<td>Morning & Afternoon</td>
												</s:if>
												<s:elseif test="%{objectList[#stat.count-1][3] !='Y'}">
												<td>Morning</td>
												</s:elseif>
												<s:elseif test="%{objectList[#stat.count-1][4] !='Y'}">
												<td>Afternoon</td>
												</s:elseif>
										    </tr>
									    </s:else>
									</s:iterator>
								</tbody>
							</table>
							</div>
							</s:if>
							<s:else>
								Today all students are present.
							</s:else>
						</div>
						<div class="col-md-6">
						<div><b>Staff</b></div>
						<s:if test="%{tempList != null && !tempList.isEmpty()}">
						<div id="staffAbsentDiv">
							<table
								class="table table-striped table-bordered table-hover table-full-width">
								<thead>
									<s:if test='%{currentAcademicYear.captureAttendanceForStaff=="O"}'>
									<tr>
										<th>Name</th>
										<th>Role</th>
									</tr>
									</s:if>
									<s:else>
									<tr>
										<th>Staff Name</th>
										<th>Role</th>
										<th>Absent Session</th>
									</tr>
									</s:else>
								</thead>
								<tbody>
									<s:iterator value="tempList" status="stat">
									<s:if test='%{currentAcademicYear.captureAttendanceForStaff=="O"}'>
										<tr>
											<td><s:property value="tempList[#stat.count-1][1]" /></td>
											<td><s:property value="tempList[#stat.count-1][2]" /></td>
										</tr>
									</s:if>
									<s:else>
										<tr>
											<td><s:property value="tempList[#stat.count-1][1]" /></td>
											<td><s:property value="tempList[#stat.count-1][2]" /></td>
											<s:if test="%{(tempList[#stat.count-1][3] != 'Y') && (tempList[#stat.count-1][4] != 'Y')}">
											<td>Morning & Afternoon</td>
											</s:if>
											<s:elseif test="%{tempList[#stat.count-1][3] != 'Y'}">
											<td>Morning </td>
											</s:elseif>
											<s:elseif test="%{tempList[#stat.count-1][4] != 'Y'}" >
											<td>Afternoon</td>
											</s:elseif>
										</tr>
									</s:else>
									</s:iterator>
								</tbody>
							</table>
							</div>
							</s:if>
							<s:else>
								Today all staff are present.
							</s:else>
						</div>
					</div>
			</div>
		</div>
	</div>
	<div id="birthdayDiv" class="modal fade form-horizontal" data-width="960" style="margin-top: 100px;">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true"></button>
			<h4 class="modal-title">Today's Birthday</h4>
		</div>
		<div class="modal-body">
				<div class="form-body">
					<div class="col-md-12">
						<div class="col-md-6">
							<div><b>Students</b></div>
							<s:if test="%{tempList1 != null && !tempList1.isEmpty()}">
							<div id="studentBirthdaysDiv">
								<table
									class="table table-striped table-bordered table-hover table-full-width">
									<thead>
										<tr>
											<th>Name</th>
											<th>Class</th>
										</tr>
									</thead>
									<tbody>
										<s:iterator value="tempList1" status="stat">
											<tr>
												<td><s:property value="tempList1[#stat.count-1][1]" /></td>
												<td><s:property value="tempList1[#stat.count-1][2]" /></td>
											</tr>
										</s:iterator>
									</tbody>
								</table>
							</div>
							</s:if>
							<s:else>
							<div class="alert alert-info">No students have birthday today.</div>
							</s:else>
						</div>
						<div class="col-md-6">
						<div><b>Staff</b></div>
						<s:if test="%{tempList2 != null && !tempList2.isEmpty()}">
						<div id="staffBirthdaysDiv">
							<table
								class="table table-striped table-bordered table-hover table-full-width">
								<thead>
									<tr>
										<th>Name</th>
										<th>Role</th>
									</tr>
								</thead>
								<tbody>
									<s:iterator value="tempList2" status="stat">
										<tr>
											<td><s:property value="tempList2[#stat.count-1][0]" /></td>
											<td><s:property value="tempList2[#stat.count-1][1]" /></td>
										</tr>
									</s:iterator>
								</tbody>
							</table>
							</div>
							</s:if>
							<s:else>
							<div class="alert alert-info">No Staff have birthday today.</div>
							</s:else>
						</div>
					</div>
			</div>
		</div>
	</div>
	<div id="attendanceRecordDiv" class="modal fade form-horizontal" data-width="550" style="margin-top: 100px;">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true"></button>
			<h4 class="modal-title">Today Attendance Not Recorded Classes</h4>
		</div>
		<div class="modal-body">
				<div class="form-body">
					<div class="col-md-24">
						<div class="col-md-12">
							
							<s:if test="%{studyClassList != null && !studyClassList.isEmpty()}">
							<div id="attendanceClassRecordDiv">
								<table
									class="table table-striped table-bordered table-hover table-full-width">
									<thead>
										<tr>
											<th align="center"> SNO</th>
											<th align="center"> Class</th>
											<th align="center"> Class Teacher</th>
										</tr>
									</thead>
									<tbody>
										<s:iterator value="studyClassList" status="stat1">
											<tr>
												<td><s:property value="#stat1.count"/> </td>
												<td><s:property value="classAndSection" /> </td>  
												<td> <s:property value="groupNumber" /> 	</td>											
												
											</tr>
										</s:iterator>
									</tbody>
								</table>
							</div>
							</s:if>
							<s:else>
							<div class="alert alert-info">All Classes Attendance Recorded Today.</div>
							</s:else>
						</div>
					</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/onload.js">
</script>
<script type="text/javascript">
	$(document).ready(function() {
		$('#studentBirthdaysDiv').slimScroll({
	        height: '550px'
	    });
		$('#staffBirthdaysDiv').slimScroll({
	        height: '550px'
	    });
		$('#studentAbsentDiv').slimScroll({
	        height: '550px'
	    });
		$('#staffAbsentDiv').slimScroll({
	        height: '550px'
	    });
		var usedSms='<s:property value="(smsCnt)" />';
		var allotedSms='<s:property value="smsAlloted"/>';
		var used=(usedSms/allotedSms)*100;
		if(allotedSms-usedSms > 0){
		var avaiSms=((allotedSms-usedSms)/allotedSms)*100;
		}
		var exceedSms=0;
		$('#usedSmsDiv').attr('data-percent',used);
		$('#avaSmsDiv').attr('data-percent',avaiSms);
		if(usedSms-allotedSms > 0){
			exceedSms=((usedSms-allotedSms)/allotedSms)*100;
		}
		$('#exceedSmsDiv').attr('data-percent',exceedSms);
		var classId=$('span#classIdSpan').attr("class");
  		if(isNonEmpty(classId)){
  			getExamTypesDetails(classId);
  		}
		Index.initMiniCharts();
		changePageTitle("Admin Dashboard");
		$('div#planner').click(function() {
			window.location.hash = "target=ES.ajaxify AAP";
			//window.location.reload();
			$('a#dashboard').parent('li').removeClass('start active');
			$('li#schoolSettingsDiv').addClass('open active');
			$('li#academicSettingsDiv').addClass('active');
			$('a#urlSchoolSettings').click();
		});
		$('div#schoolSettings').click(function() {
			window.location.hash = "target=ES.ajaxify AMCS";
			//window.location.reload();
			$('a#dashboard').parent('li').removeClass('start active');
			$('li#schoolSettingsDiv').addClass('open active');
			$('li#manageSchool').addClass('active');
			$('a#urlSchoolInformation').click();
		});
		$('div#sendSms').click(function() {
			window.location.hash = "target=ASMS.ajaxify ASMEL";
			$('a#dashboard').parent('li').removeClass('start active');
			$('li#smsDiv').addClass('open active');
			$('li#adminSms').addClass('active');
			$('li#adminSms a').click();
			
		});
		$('div#manageLeaves').click(function() {
			window.location.hash = "target=MSF.ajaxify MMLS";
			$('a#dashboard').parent('li').removeClass('active');
			$('li#manageStaff').addClass('open active');
			$('a#urlLeaves').click();
		});
		$('div#reportsDiv').click(function() {
			window.location.hash = "target=SCS.ajaxify SCS";
			//window.location.reload();
			$('a#dashboard').parent('li').removeClass('active');
			$('li#adminReportsDiv').addClass('open active');
			$('li#adminReports').addClass('active');
			$('li#communityDiv').addClass('active');
			$('li#communityDiv a').click();
		});
		$('div#invoiceDiv').click(function() {
			window.location.hash = "#target=ESI.ajaxify SMP";
			$('a#dashboard').parent('li').removeClass('start active');
			$('li#studentInvoiceNav').addClass('open active');
			$('li#adminSchoolFee').addClass('active');
			$('li#adminSchoolFee a').click();
			});
		$('div#exminationDiv').click(function() {
			window.location.hash = "#target=ESD.ajaxify GAE";
			$('a#dashboard').parent('li').removeClass('start active');
			$('li#ExaminationId').addClass('open active');
			$('li#gradesAndExamTypes').addClass('active');
			$('li#gradesAndExamTypes a').click();
			});
		$('div#help').click(function() {
			window.location.hash = "#target=HelpDoc.ajaxify Helps";
			$('a#dashboard').parent('li').removeClass('start active');
			$('li#helpDocument').addClass('open active');
			$('li#helpDcoumnetsId').addClass('active');
			$('li#helpDcoumnetsId a').click();
			});
		
		$('div#principalLibraryDiv').click(function(){
			var url = jQuery.url.getChatURL("/library/getStaffLibrayHome.do?id=staffLibrary");
			window.location.href = url;
		});
		$('div#IdCardGeneration').click(function() {
			window.location.hash = "target=SID.ajaxify ID";
			//window.location.reload();
			$('a#dashboard').parent('li').removeClass('active');
			$('li#adminReportsDiv').addClass('open active');
			$('li#studentReportsDiv').addClass('open active');
			$('li#idCardsDiv').addClass('active');
			$('a#doIdCardsGenerations').click();
		});
		$('div#studentInfo').click(function() {
			window.location.hash = "target=MSTI.ajaxify MSTI";
			//window.location.reload();
			$('a#dashboard').parent('li').removeClass('active');
			$('li#manageStudent').addClass('open active');
			$('li#studentDiv').addClass('active');
			$('a#manageStudents').click();
		});
		$('div#staffInfo').click(function() {
			window.location.hash = "target=MSF.ajaxify MSF";
			$('a#dashboard').parent('li').removeClass('active');
			$('li#manageStaff').addClass('open active');
			$('li#staffInfoDiv').addClass('active');
			$('a#urlManageStaff').click();
		});
		$('div#admissionsDiv').click(function() {
			window.location.hash = "target=ADMS.ajaxify ADMS";
			$('a#dashboard').parent('li').removeClass('start active');
			$('li#admissionsNav').addClass('open active');
			$('a#admissionDetails').click();
		});
		$('div#adminAttendanceDiv').click(function() {
			window.location.hash = "target=MSAT.ajaxify MSAT";
			$('a#dashboard').parent('li').removeClass('start active');
			$('li#manageAtt').addClass('open active');
			$('li#attendanceId').addClass('active');
			$('li#attendanceId a').click();
		});
		$('div#assginmentDiv').click(function() {
			window.location.hash = "target=MSTI.ajaxify AMCA";
			$('a#dashboard').parent('li').removeClass('start active');
			$('li#manageStudent').addClass('open active');
			$('li#classAssignmentDiv').addClass('active');
			$('li#classAssignmentDiv a').click();
		});
		$('a.pendingSmsDiv').click(function() {
			window.location.hash = "target=AMAS.ajaxify AMAS";
			$('a#dashboard').parent('li').removeClass('start active');
			$('li#adminInboxDiv').addClass('open active');
			$('li#inboxesDetailsDiv').addClass('active');
			$('li#urlClassAssignment a').click();
		});
		$('a.todayFeeDetailsDiv').click(function() {
			window.location.hash = "#target=ESI.ajaxify TFD";
			$('a#dashboard').parent('li').removeClass('start active');
			$('li#studentInvoiceNav').addClass('open active');
			$('li#todayFeeCollection').addClass('active');
			$('li#todayFeeCollection a').click();
		});
	});
	
	
	function popupFeeSummary(id){
		  var pars = null;
	      $.ajax( {
		        url : jQuery.url.getChatURL("/schoolfee/ajaxPopUpFeeDetails.do"),
				cache : true,
				data : pars,
				success : function(html) {
				$("#popupFeeDetailsDiv").html(html);
				}
			});
	}
</script>
