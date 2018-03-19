<%@ include file="/common/taglibs.jsp"%>
<div class="row ">
	<div class="col-md-6 col-sm-6">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-calendar"></i> Dashboard
				</div>
			</div>
			<div class="portlet-body">
				<div class="row">
					<div class="col-md-4">
						<div class="easy-pie-chart" id="admissionsDiv">
							<s:url id="urlGetAdmissions" action="ajaxGetOnlineAdmissions"
								namespace="/admin" />
							<sj:a href="%{urlGetAdmissions}" targets="mainContentDiv"
								cssClass="ajaxify ADMS title">
								<div data-percent="55" class="number">
									<img src="../img/bg/01Admission.png" class="loginImg" />
								</div>Admissions<i class="m-icon-swapright"></i>
							</sj:a>
						</div>
					</div>
					<div class="margin-bottom-10 visible-sm">
					</div>
					<%--<div class="col-md-4">
						<div class="easy-pie-chart" id="timeTable">
							<s:url id="urlPeriods" action="ajaxGetSchoolPeriods"
								namespace="/admin" />
							<sj:a href="%{urlPeriods}" targets="mainContentDiv"
								cssClass="ajaxify MTPS title">
								<div data-percent="55" class="number">
									<img src="../img/bg/04Timetable.png" class="loginImg" />
								</div> Timetable <i class="m-icon-swapright"></i>
							</sj:a>
						</div>
					</div>
					--%><div class="margin-bottom-10 visible-sm">
					</div>
					<div class="col-md-4">
						<div class="easy-pie-chart" id="sendSms">
							<s:url id="urlSMSDetails"
								action="ajaxDoGetSchoolWideMessagesList" namespace="/common"
								includeParams="all" escapeAmp="false">
								<s:param value="#session.academicYear" name="academicYearId" />
							</s:url>
							<sj:a href="%{urlSMSDetails}" targets="mainContentDiv"
								cssClass='ajaxify ASMEL title'>
								<div data-percent="55" class="number">
									<img src="../img/bg/07Communication.png" class="loginImg" />
								</div> SMS<i class="m-icon-swapright"></i>
							</sj:a>
						</div>
					</div>
					<div class="margin-bottom-10 visible-sm">
					</div>
					<div class="col-md-4">
						<div class="easy-pie-chart" id="studentInfo">
							<s:url id="urlmanageStudent" action="ajaxGetStudyClassList"
								namespace="/student" includeParams="all" escapeAmp="false" />
							<sj:a  href="%{urlmanageStudent}"
								targets="mainContentDiv" cssClass="ajaxify MSTI title">
								<div data-percent="55" class="number">
									<img src="../img/bg/05StudentStaff.png" class="loginImg" />
								</div> Student Info <i class="m-icon-swapright"></i>
							</sj:a>
						</div>
					</div>
				</div>
				<div class="spaceDiv">
				</div>
				<div class="row">
					<div class="col-md-4">
						<div class="easy-pie-chart" id="adminAttendanceDiv">
							<s:url action="ajaxDoGetAttendanceForm"
								id="ajaxClassAttendencelink" namespace="/admin"
								includeParams="all" escapeAmp="false">
								<s:param name="staffAtt">studentAt</s:param>
							</s:url>
							<sj:a href="%{ajaxClassAttendencelink}" targets="mainContentDiv"
								cssClass="ajaxify MSAT title">
								<div data-percent="55" class="number">
									<img src="../img/bg/06Attendance.png" class="loginImg" />
								</div>Attendance <i class="m-icon-swapright"></i>
							</sj:a>
						</div>
					</div>
					<div class="margin-bottom-10 visible-sm">
					</div>
					<div class="col-md-4">
						<div class="easy-pie-chart" id="IdCardGeneration">
							<s:url id="urlDoIdCardsGenerations" namespace="/admin"
								action="ajaxIDCardsGenerations" />
							<sj:a  href="%{urlDoIdCardsGenerations}"
								targets="mainContentDiv" cssClass='ajaxify ID title'>
								<div data-percent="55" class="number">
									<img class="loginImg" src="../img/bg/10Reports.png">
								</div> Reports <i class="m-icon-swapright"></i>
							</sj:a>
						</div>
					</div>
				</div>
				
			</div>
		</div>
	</div>
	<div class="col-md-6" style="padding-left: 0px;">
			<div class="portlet box blue">
				<div class="portlet-title">
					<div class="caption"><i class="fa fa-calendar"></i>Today Attendance</div>
				</div>
				<div class="portlet-body">
					<div class="table-responsive">
					   <div data-always-visible="1" data-rail-visible="0">
							<ul class="feeds">
								<li>
									<div class="col1" style="width: 84%">
										<div class="cont">
											<div class="cont-col1">
												<div class="label label-sm label-success">
													<i class="fa fa-bolt"></i>
												</div>
											</div>
											<div class="cont-col2">
												<div class="desc">
												No.Of <b>staff</b> Absent
													<!-- Today
													<b>staff</b> absence -->
												</div>
											</div>
										</div>
									</div>
									<div class="col2">
										<div class="leaves">
											<ul class="tooltipDiv" style="float: right;">
												<li>
													<span class="label label-sm label-success"> <a
														href="#" style="color: #fff;"><s:property value="tempList.size" /> View
															action <i class="fa fa-share"></i> </a> </span>
													<ul class="tooltipSubDiv">
														<div class="popover bottom " style="display: none;">
															<div class="arrow"></div>
															<h3 class="popover-title">
																View Staff Leave Details
															</h3>
															<div class="popover-content">
																<s:if test="%{tempList != null && !tempList.isEmpty()}">
																	<s:iterator value="tempList" status="stat">
																		<li>
																			<span class="col-md-8" style="padding-left: 0px;padding-right: 0px;"><s:property value="tempList[#stat.count-1][1]" /></span>
																			:
																			<s:property value="tempList[#stat.count-1][2]" />
																		</li>
																	</s:iterator>
																</s:if>
																<s:else>
																	Today all staff are present.
																</s:else>
															</div>
														</div>
													</ul>
												</li>
											</ul>
										</div>
									</div>
								</li>
								<li>
									<div class="col1" style="width: 84%">
										<div class="cont">
											<div class="cont-col1">
												<div class="label label-sm label-success">
													<i class="fa fa-bolt"></i>
												</div>
											</div>
											<div class="cont-col2">
												<div class="desc">
												No.Of <b>students</b> Absent
													<!-- Today
													<b>students</b> absence -->
												</div>
											</div>
										</div>
									</div>
									<div class="col2">
										<div class="leaves">
											<ul class="tooltipDiv" style="float: right;">
												<li>
													<span class="label label-sm label-success">
													 <a href="#" style="color: #fff;" ><s:property value="objectList.size" /> View
															action <i class="fa fa-share"></i> </a> </span>
													<ul class="tooltipSubDiv">
														<div class="popover bottom " style="display: none;">
															<div class="arrow"></div>
															<h3 class="popover-title">
																View Students Leave Details
															</h3>
															<div class="popover-content">
																<s:if
																	test="%{objectList != null && !objectList.isEmpty()}">
																	<s:iterator value="objectList" status="stat">
																		<li>
																			<span class="col-md-8" style="padding-left: 0px;padding-right: 0px;"><s:property value="objectList[#stat.count-1][0]" /></span>
																			:
																			<s:property value="objectList[#stat.count-1][3]" />
																		</li>
																	</s:iterator>
																</s:if>
																<s:else>
																	Today all students are present.
																</s:else>
															</div>
														</div>
													</ul>
												</li>
											</ul>
										</div>
									</div>
								</li>
							</ul>
						</div>
					</div>
				</div>
			</div>
			
		<s:if test='%{admissionSettings.admissionEndDate.compareTo(toDate) > 0}'>
			<div class="portlet box blue">
				<div class="portlet-title">
					<div class="caption"><i class="fa fa-calendar"></i>Recent Admissions</div>
				</div>
				<div class="portlet-body">
					<div class="table-responsive">
						 <div class="scroller" style="height: 290px;" data-always-visible="1" data-rail-visible1="1">
							<s:if
								test="%{studentsList != null && !studentsList.isEmpty()}">
								<s:iterator value="studentsList">
									<div class="user-info" class="col-md-12">
										<div class="col-md-2">
											<s:if test="%{originalAttachmentFilePath != null &&  originalAttachmentFilePath != '' && imageId >0}">
													<img src='<c:url value="${originalAttachmentFilePath}"/>' border="0" class="img-responsive" />
											</s:if>
											<s:else>
												<img alt="" src="../img/avatar.png" class="img-responsive"
													 />
											</s:else>
										</div>
										<div class="col-md-10">
											<div class="form-horizontal">
												<div class="form-group" style="margin-bottom: 0px;">
													<label class="control-label col-md-4">
														Name :
													</label>
													<div class="col-md-8">
														<p class="form-control-static">
															<s:property value="firstName" />&nbsp;<span class="label label-sm label-info">Pending</span>
														</p>
													</div>
													<div class="form-group" style="margin-bottom: 0px;">
														<label class="control-label col-md-4">
															Mobile :
														</label>
														<div class="col-md-8">
															<p class="form-control-static">
																<s:property value="mobileNumber" />
															</p>
														</div>
													</div>
													<div class="form-group" style="margin-bottom: 0px;">
														<label class="control-label col-md-4">
															className :
														</label>
														<div class="col-md-8">
															<p class="form-control-static">
																<s:property value="className" />
															</p>
														</div>
													</div>
													<div class="form-group" style="margin-bottom: 0px;">
														<label class="control-label col-md-4">
															Application No :
														</label>
														<div class="col-md-8">
															<p class="form-control-static">
																<s:property value="applicationNumber" />
															</p>
														</div>
													</div>
												</div>
												<hr/>
											</div>
										</div>
									</div>
								</s:iterator>
							</s:if>
							<s:else>
								<div class="alert alert-info">
									Currently there is no pending applications.
								</div>
							</s:else>
						</div>
					</div>
				</div>
			</div>
		</s:if>
		</div>
</div>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/onload.js">
</script>
<script Language="Javascript" type="text/javascript">
	$(document).ready(function() {
		/*	$('span.studentLeavesCount').each(function(){
		 totalLeavesCount+=Number($(this).html());
		 });
		 $('div#lavesStudsCnt').html(totalLeavesCount);
		 */
		changePageTitle("Admin Dashboard");
		$('div#planner').click(function() {
			window.location.hash = "target=ES.ajaxify AAP";
			//window.location.reload();
			$('a#dashboard').parent('li').removeClass('start active');
			$('li#schoolSettingsDiv').addClass('open active');
			$('li#academicSettingsDiv').addClass('active');
		});
		$('div#schoolSettings').click(function() {
			window.location.hash = "target=ES.ajaxify AMCS";
			//window.location.reload();
			$('a#dashboard').parent('li').removeClass('start active');
			$('li#schoolSettingsDiv').addClass('open active');
			$('li#manageSchool').addClass('active');
		});
		$('div#sendSms').click(function() {
			window.location.hash = "target=ASMS.ajaxify ASMEL";
			$('a#dashboard').parent('li').removeClass('start active');
			$('li#smsDiv').addClass('open active');
			$('li#adminSms').addClass('active');
		});
		$('div#timeTable').click(function() {
			window.location.hash = "target=MTM.ajaxify MTPS";
			//window.location.reload();
			$('a#dashboard').parent('li').removeClass('active');
			$('li#manageStaff').addClass('open active');
			$('li#timeTableDiv').addClass('active');
		});
		$('div#reportsDiv').click(function() {
			window.location.hash = "target=SCS.ajaxify SCS";
			//window.location.reload();
			$('a#dashboard').parent('li').removeClass('active');
			$('li#adminReportsDiv').addClass('open active');
			$('li#adminReports').addClass('active');
			$('li#communityDiv').addClass('active');
		});
		$('div#invoiceDiv').click(function() {
			var url = jQuery.url.getChatURL("/schoolfee/adminGetSchoolFee.do?id=Fee");
			window.location.href = url;
			});
		$('div#IdCardGeneration').click(function() {
			window.location.hash = "target=SID.ajaxify ID";
			//window.location.reload();
			$('a#dashboard').parent('li').removeClass('active');
			$('li#adminReportsDiv').addClass('open active');
			$('li#studentReportsDiv').addClass('open active');
			$('li#idCardsDiv').addClass('active');
		});
		$('div#studentInfo').click(function() {
			window.location.hash = "target=MSTI.ajaxify MSTI";
			//window.location.reload();
			$('a#dashboard').parent('li').removeClass('active');
			$('li#manageStudent').addClass('open active');
			$('li#studentDiv').addClass('active');
		});
		$('div#staffInfo').click(function() {
			window.location.hash = "target=MSF.ajaxify MSF";
			$('a#dashboard').parent('li').removeClass('active');
			$('li#manageStaff').addClass('open active');
			$('li#staffInfoDiv').addClass('active');
		});
		$('div#admissionsDiv').click(function() {
			window.location.hash = "target=ADMS.ajaxify ADMS";
			//window.location.reload();
			$('a#dashboard').parent('li').removeClass('start active');
			$('li#admissionsNav').addClass('open active');
		});
		$('div#feeSettingsDiv').click(function() {
			window.location.hash = "target=ADM.ajaxify FS";
			//window.location.reload();
			$('a#dashboard').parent('li').removeClass('start active');
			$('li#academicsDiv').addClass('open active');
			$('li#adminFeeDiv').addClass('active');
		});
		$('div#libraryDiv').click(function() {
			window.location.hash = "target=LR.ajaxify LSM";
			$('a#dashboard').parent('li').removeClass('start active');
			$('li#adminLibraryDiv').addClass('open active');
			$('li#viewBookResults').addClass('active');
		});
		$('div#transportDiv').click(function() {
			window.location.hash = "target=MTP.ajaxify MTP";
			$('a#dashboard').parent('li').removeClass('start active');
			$('li#manageTransportDiv').addClass('open active');
			$('li#routeDiv').addClass('active');
		});
		$('div#adminAttendanceDiv').click(function() {
			window.location.hash = "target=MSTI.ajaxify MSAT";
			$('a#dashboard').parent('li').removeClass('start active');
			$('li#manageAtt').addClass('open active');
			$('li#attendanceId').addClass('active');
		});
		$('div#assginmentDiv').click(function() {
			window.location.hash = "target=MSTI.ajaxify AMCA";
			$('a#dashboard').parent('li').removeClass('start active');
			$('li#manageStudent').addClass('open active');
			$('li#classAssignmentDiv').addClass('active');
		});
		$('a.pendingSmsDiv').click(function() {
			window.location.hash = "target=AMAS.ajaxify AMAS";
			$('a#dashboard').parent('li').removeClass('start active');
			$('li#adminInboxDiv').addClass('open active');
			$('li#inboxesDetailsDiv').addClass('active');
		});
	});
</script>
