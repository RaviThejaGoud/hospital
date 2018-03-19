<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Academic Year Details
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
					<ul class="nav nav-tabs">
						<s:if test='%{#session.previousYear == "N"}'>
							<s:if
								test='%{academicYear.futureAcademicData && (#session.previousYear == "N")}'>
								<!-- <li>
									<a href="" id="futureAcademicDiv" class="btn default"  data-toggle="tab" 
										onclick="javascript:confirmation()">Switch To Future Academic Year</a>
								</li> -->
								<li id="switchAcademicPlan">
									<a id="doSwithFeatureAcademicYear"  
										class="btn default"
										onclick="javascript:popupSwithFeatureAcademicYear();">
											Switch To Future Academic Year
									 </a>
								 <li>
								<%-- <li>
									<s:url id="doSwithFeatureAcademicYear" action="ajaxDoSwithFeatureAcademicYear" includeParams="all" namespace="/admin" >
									</s:url>
									<sj:a href="%{doSwithFeatureAcademicYear}" indicator="indicator" cssClass="btn default"
										data-toggle="tab" targets="subjectsContentDiv" button="false">Switch To Future Academic Year</sj:a>
								</li> --%>
							</s:if>
							<s:if
								test='%{academicYear == null && (#session.previousYear == "N")}'>
								<li class="active" id="academicPlan">
									<s:url id="doCreateSchoolSettings"
										action="ajaxDoCreateAcademicDetails" includeParams="all" namespace="/admin" >
									</s:url>
									<sj:a href="%{doCreateSchoolSettings}" indicator="indicator" cssClass="btn default" 
										data-toggle="tab" targets="mainContentDiv" button="false">Add Academic Planner</sj:a>
								</li>
							</s:if>
							<s:if
								test='%{tempString == "swithch"}'>
								<li class="active" id="updateAcademicPlan">
									<s:url id="doUpateAcademicDetails" action="ajaxDoUpateAcademicDetails" includeParams="all" namespace="/admin" >
									<s:param name="academicYearId" value="%{academicYear.id}"></s:param>
									</s:url>
									<sj:a href="%{doUpateAcademicDetails}" indicator="indicator" cssClass="btn default" 
										data-toggle="tab" targets="mainContentDiv" button="false">Update Academic Planner</sj:a>
								</li>
							</s:if>
							
							<s:if test='%{customer.staffPermissionStatus == "Y"}'>
								<li class="active" id="viewAcademicPla">
									<s:url id="urlSchoolSettings" action="ajaxAcademicSchoolSettings" namespace="/admin" includeParams="all" escapeAmp="false">
										<s:param value='<s:property value="#session.academicYear" />'
											name="academicYearId">
										</s:param>
									</s:url>
									<sj:a  href="%{urlSchoolSettings}" data-toggle="tab" targets="mainContentDiv" cssClass="ajaxify AAP">
										Academic Planner</sj:a>
								 </li>
								 <li>
									<s:url id="lateSettings"
										action="ajaxDoAddStaffLateSettings" namespace="/admin" includeParams="all" escapeAmp="false" >
										<s:param name="tempString">L</s:param>
									</s:url>
									
									<sj:a href="%{lateSettings}" indicator="indicator" 
										data-toggle="tab" targets="subjectsContentDiv">Add Late Settings</sj:a>
								</li>
								<li>
									<s:url id="permissionSettings"
										action="ajaxDoAddStaffPermissionSettings" namespace="/admin" includeParams="all" escapeAmp="false">
										<s:param name="tempString">P</s:param>
									</s:url>
									<sj:a href="%{permissionSettings}" indicator="indicator"  
										data-toggle="tab" targets="subjectsContentDiv">Add Permission Settings</sj:a>
								</li>
							</s:if>
						</s:if>
					</ul>
					<div id="subjectsContentDiv" class="tab-content">
					<jsp:include page="/common/messages.jsp" />
						<s:if test='%{academicYear != null && academicYear != empty}'>
							<table
								class="table table-striped table-bordered table-hover table-full-width">
								<thead>
									<tr>
										<th>
											Academic Year
										</th>
										<th>
											School Duration
											<b>Open </b>-
											<b> Close</b>
										</th>
										<th>
											School Timings
											<b>Start </b>-
											<b> End</b>
										</th>
										<s:if
											test='%{(#session.previousYear == null || #session.previousYear.isEmpty()) || (#session.previousYear == "N")}'>
											<th>
												Edit
											</th>
										</s:if>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>
											<s:property value="academicYear.academicYear" />
										</td>
										<td>
											<s:property value="academicYear.startDateStr" />
											-
											<s:property value="academicYear.endDateStr" />
										</td>
										<td>
										<s:if test='%{plTitle == "SH"}'>
											<a data-toggle="modal" href="#responsive2"
													class="btn default popUpResponse"
													id='<s:property value="academicYear.id" />'>School
													Timings </a>
												<div data-width="1060" class="modal fade modal-overflow in"
													id="responsive2" aria-hidden="false">
													<div class="modal-header">
														<button type="button" class="close" data-dismiss="modal"
															aria-hidden="true"></button>
														<h4 class="modal-title">View School Timings</h4>
													</div>
													<div class="modal-body"></div>
														<table class="table table-striped table-bordered table-hover table-full-width">
															<thead>
																<tr>
																	<th>School StartTime</th>
																	<th>Morning Break StartTime</th>
																	<th>Morning Break EndTime</th>
																	<th>Lunch StartTime</th>
																	<th>Lunch EndTime</th>
																	<th>Evening Break StartTime</th>
																	<th>Evening Break EndTime</th>
																	<th>School EndTime</th>
																</tr>
															</thead>
															<tbody>
																<tr>
																<td>
																<s:if test="%{academicYearTimings.startTime != null && academicYearTimings.startTime !=''}">
																	<s:property value="academicYearTimings.startTime" />
																</s:if>
																<s:else>
																	-
																</s:else>
																</td>
																<td>
																<s:if test="%{academicYearTimings.morningBreakStartTime != null && academicYearTimings.morningBreakStartTime !=''}">
																		<s:property value="academicYearTimings.morningBreakStartTime" />
																	</s:if>
																	<s:else>
																		-
																	</s:else>
																</td>
																<td>
																<s:if test="%{academicYearTimings.morningBreakEndTime != null && academicYearTimings.morningBreakEndTime !=''}">
																		<s:property value="academicYearTimings.morningBreakEndTime" />
																	</s:if>
																	<s:else>
																		-
																	</s:else>
																</td>
																<td>
																<s:if test="%{academicYearTimings.lunchStartTime != null && academicYearTimings.lunchStartTime !=''}">
																		<s:property value="academicYearTimings.lunchStartTime" />
																	</s:if>
																	<s:else>
																		-
																	</s:else>
																</td>
																<td>
																<s:if test="%{academicYearTimings.lunchEndTime != null && academicYearTimings.lunchEndTime !=''}">
																		<s:property value="academicYearTimings.lunchEndTime" />
																	</s:if>
																	<s:else>
																		-
																	</s:else>
																</td>
																<td>
																<s:if test="%{academicYearTimings.eveningBreakStartTime != null && academicYearTimings.eveningBreakStartTime !=''}">
																		<s:property value="academicYearTimings.eveningBreakStartTime" />
																	</s:if>
																	<s:else>
																		-
																	</s:else>
																</td>
																<td>
																<s:if test="%{academicYearTimings.eveningBreakEndTime != null && academicYearTimings.eveningBreakEndTime !=''}">
																		<s:property value="academicYearTimings.eveningBreakEndTime" />
																	</s:if>
																	<s:else>
																		-
																	</s:else>
																</td>
																<td>
																<s:if test="%{academicYearTimings.endTime != null && academicYearTimings.endTime !=''}">
																		<s:property value="academicYearTimings.endTime" />
																	</s:if>
																	<s:else>
																		-
																	</s:else>
																</td>
																</tr>
															</tbody>
														</table>
												</div>
										</s:if>
										<s:else>
											<s:if
												test="%{tempList2 != null || !tempList2.isEmpty && tempList2!= null || !tempList2.isEmpty}">
												<a data-toggle="modal" href="#responsive2"
													class="btn default popUpResponse"
													id='<s:property value="academicYear.id" />'>School
													Timings </a>

												<div data-width="1060" class="modal fade modal-overflow in"
													id="responsive2" aria-hidden="false">
													<div class="modal-header">
														<button type="button" class="close" data-dismiss="modal"
															aria-hidden="true"></button>
														<h4 class="modal-title">
															View School Timings
														</h4>
													</div>
													<div class="modal-body">
														<div id="timingValues">
															<s:set var="classIds" value="-1"></s:set>
															<s:set var="countNum" value="0"></s:set>
															<table class="table table-striped table-bordered table-hover table-full-width weekDayTimeSpace"
																id="sample_2">
																<s:iterator value="tempList2" status="itStatus">
																	<s:if test='%{status == "SD"}'>
																		<s:if test='%{#itStatus.count == 1}'>
																			<input type="hidden" name="hiddenVar" id="hiddenVar"
																				value="SD" />
																			<thead>
																				<tr>
																					<th>
																						Day
																					</th>
																					<th>
																						StartTime
																					</th>
																					<th>
																						EndTime
																					</th>
																				</tr>
																			</thead>
																		</s:if>
																		<tbody>
																			<tr>
																				<td>
																					<s:property value="weekName" />
																				</td>
																				<td>
																					<s:property value="startTime" />
																				</td>
																				<td>
																					<s:property value="endTime" />
																				</td>
																			</tr>
																		</tbody>
																	</s:if>
																	<s:elseif test='%{status == "CD"}'>
																		<s:if test='%{#itStatus.count == 1}'>
																			<div>
																				<div class="schoolTimeSpace">
																					<div class="col-sm-1">
																						Class/Day
																					</div>
																					<s:iterator value="weekDayList">
																						<div class="col-md-2">
																							<s:property value="dayName" />
																						</div>
																					</s:iterator>
																				</div>
																			</div>
																		</s:if>
																		<div>
																			<div>
																				<s:if test="%{classId == #classIds}">
																					<s:if test="%{weekDay == 1}">
																						<td class="weekDayTimeSpace">
																							<s:property value="startTime" />
																							-
																							<s:property value="endTime" />
																						</td>
																					</s:if>
																					<s:elseif test="%{weekDay == 2}">
																						<td class="weekDayTimeSpace">
																							<s:property value="startTime" />
																							-
																							<s:property value="endTime" />
																						</td>
																					</s:elseif>
																					<s:elseif test="%{weekDay == 3}">
																						<td class="weekDayTimeSpace">
																							<s:property value="startTime" />
																							-
																							<s:property value="endTime" />
																						</td>
																					</s:elseif>
																					<s:elseif test="%{weekDay == 4}">
																						<td class="weekDayTimeSpace">
																							<s:property value="startTime" />
																							-
																							<s:property value="endTime" />
																						</td>
																					</s:elseif>
																					<s:elseif test="%{weekDay == 5}">
																						<td class="weekDayTimeSpace">
																							<s:property value="startTime" />
																							-
																							<s:property value="endTime" />
																						</td>
																					</s:elseif>
																					<s:elseif test="%{weekDay == 5}">
																						<td class="weekDayTimeSpace">
																							<s:property value="startTime" />
																							-
																							<s:property value="endTime" />
																						</td>
																					</s:elseif>
																					<s:elseif test="%{weekDay == 6}">
																						<td class="weekDayTimeSpace">
																							<s:property value="startTime" />
																							-
																							<s:property value="endTime" />
																						</td>
																					</s:elseif>
																					<s:elseif test="%{weekDay == 7}">
																						<td class="weekDayTimeSpace">
																							<s:property value="startTime" />
																							-
																							<s:property value="endTime" />
																						</td>
																					</s:elseif>
																				</s:if>
																				<s:else>
																				</tr>
																					<td>
																						<s:property value="className" />
																					</td>
																					<s:if test="%{weekDay == 1}">
																						<td class="weekDayTimeSpace">
																							<s:property value="startTime" />
																							-
																							<s:property value="endTime" />
																						</td>
																					</s:if>
																					<s:elseif test="%{weekDay == 2}">
																						<td class="weekDayTimeSpace">
																							<s:property value="startTime" />
																							-
																							<s:property value="endTime" />
																						</td>
																					</s:elseif>
																					<s:elseif test="%{weekDay == 3}">
																						<td class="weekDayTimeSpace">
																							<s:property value="startTime" />
																							-
																							<s:property value="endTime" />
																						</td>
																					</s:elseif>
																					<s:elseif test="%{weekDay == 4}">
																						<td class="weekDayTimeSpace">
																							<s:property value="startTime" />
																							-
																							<s:property value="endTime" />
																						</td>
																					</s:elseif>
																					<s:elseif test="%{weekDay == 5}">
																						<td class="weekDayTimeSpace">
																							<s:property value="startTime" />
																							-
																							<s:property value="endTime" />
																						</td>
																					</s:elseif>
																					<s:elseif test="%{weekDay == 5}">
																						<td>
																							<s:property value="startTime" />
																							-
																							<s:property value="endTime" />
																						</td>
																					</s:elseif>
																					<s:elseif test="%{weekDay == 6}">
																						<td class="weekDayTimeSpace">
																						<s:property value="startTime" />
																							-
																							<s:property value="endTime" />
																						</td>
																					</s:elseif>
																					<s:elseif test="%{weekDay == 7}">
																						<td class="weekDayTimeSpace">
																							<s:property value="startTime" />
																							-
																							<s:property value="endTime" />
																						</td>
																					</s:elseif>
																				</s:else>
																			</div>
																		</div>
																		<s:set var="classIds" value="%{classId}"></s:set>
																	</s:elseif>
																	<s:elseif test='%{status == "CT"}'>
																		<s:if test='%{#itStatus.count == 1}'>
																			<thead>
																				<tr>
																					<th>
																						Class
																					</th>
																					<th>
																						StartTime
																					</th>
																					<th>
																						EndTime
																					</th>
																				</tr>
																			</thead>
																		</s:if>
																		<tbody>
																			<tr>
																				<td>
																					<s:property value="className" />
																				</td>
																				<td>
																					<s:property value="startTime" />
																				</td>
																				<td>
																					<s:property value="endTime" />
																				</td>
																			</tr>
																		</tbody>
																	</s:elseif>
																	<s:else>
																		<input type="hidden" name="hiddenVar" id="hiddenVar"
																			value="ST" />
																		<s:property value="startTime" />
																		-
																		<s:property value="endTime" />
																	</s:else>
																</s:iterator>
															</table>
														</div>
														<s:set var="classIds" value="%{classId}"></s:set>
													</div>
												</div>
											</s:if>
											</s:else>
										</td>
										<s:if
											test='%{(#session.previousYear == null || #session.previousYear.isEmpty()) || (#session.previousYear == "N")}'>
											<td>
												<s:url id="doEditSchoolSettings"
													action="ajaxDoEditSchoolSettings" includeParams="all"
													escapeAmp="false" namespace="/admin">
													<s:param name="academicYearId" value="%{academicYear.id}"></s:param>
												</s:url>
												<sj:a href="%{doEditSchoolSettings}"
													targets="mainContentDiv" cssClass="btn btn-xs purple"
													title="Edit">
													<i class="fa fa-edit"></i>Edit</sj:a>
											</td>
										</s:if>
									</tr>
								</tbody>
							</table>
						</s:if>
						<s:else>
							<div class="alert alert-info">
								There are no academic details created.
							</div>
						</s:else>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<div id="responsive2"></div>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/plugins/bootstrap-modal/js/bootstrap-modalmanager.js">
</script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/newScripts/ui-extended-modals.js">
</script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
<script type="text/javascript">
changePageTitle('Manage Academic Planner');
$(document).ready(function() {
	TableAdvanced.init();
	UIExtendedModals.init();
	if ($('input#hiddenVar').val() == "ST") {
		$('a#showTimingsHref').remove();
		$("div#timingValues").show();
		$('div#timingValues').removeClass('grid_14');
	}
	if (isNonEmpty(getUrlVars()["mc"])) {
		$('li.manageSchoolId a').click();
	}
	$.subscribe('schoolSettingsValidation', function(event, data) {
		if ($('#addSchoolSettings').valid())
			return true;
		else
			return false;
	});
});
$("li#academicPlan a").click();
$("li#updateAcademicPlan a").click();
$('a.popUpResponse').click(function() {
	$('div.modal-overflow').show();
});
$('button.close,.cancel').click(function() {
	$('div.modal-overflow').hide();
});

function popupSwithFeatureAcademicYear() {
	var answer = confirm("Are you sure you want to switch to future academic year ?");
	$('.btn').attr('disabled','disabled');
	$("li#switchAcademicPlan").addClass("active");
	$("li#viewAcademicPla").removeClass("active");
	if (!answer) {
		var url = jQuery.url.getChatURL("/admin/ajaxAcademicSchoolSettings.do");
		$('#mainContentDiv').html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		$.ajax( {
			url : url,
			cache : false,
			success : function(html) {
				$("#mainContentDiv").html(html);
				
			}
		});
		return false;
	} else {
		var url = jQuery.url.getChatURL("/admin/ajaxDoSwithFeatureAcademicYear.do");
		$('#subjectsContentDiv').html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		$.ajax( {
			url : url,
			cache : false,
			success : function(html) {
				$("#subjectsContentDiv").html(html);
			}
		});
	}
}


/* function confirmation() {
	var answer = confirm("Are you sure you want to switch to future academic year ?");
	if (!answer) {
		$('#futureAcademicDiv').attr("href", "javascript:void(0);");
	} else {
		var url = jQuery.url.getChatURL("/admin/ajaxChangeAcademicYearStatus.do");
		$.ajax( {
			url : url,
			cache : false,
			success : function(html) {
				$("#mainContentDiv").html(html);
			}
		});
	}
} */
$('html, body').animate({ scrollTop: $(document).height() - $(window).height() }, 10, function() {
    $(this).animate({ scrollTop: 0 }, 10);
});
</script>