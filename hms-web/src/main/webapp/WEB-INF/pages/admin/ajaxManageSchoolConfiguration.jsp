<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript">
$(document).ready(function() {
	var reload ='<s:property value="title" />';
	if( reload == "R"){
		window.location.reload();
	}
});
</script>
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
								<li>
									<a href="" id="futureAcademicOpt"
										onclick="javascript:confirmation()">Switch To Future
										Academic Year</a>
								</li>
							</s:if>
							<s:if
								test='%{academicYear == null && (#session.previousYear == "N")}'>
								<li>
									<s:url id="doCreateSchoolSettings"
										action="ajaxDoCreateAcademicDetails" includeParams="all" namespace="/admin"></s:url>
									<sj:a href="%{doCreateSchoolSettings}" indicator="indicator"
										data-toggle="tab" targets="subjectsContentDiv" button="false">Add Academic Planner</sj:a>
								</li>
							</s:if>
						</s:if>
					</ul>
					<div id="subjectsContentDiv" class="tab-content">
						<jsp:include page="/common/messages.jsp" />
						<s:if test='%{academicYear != null && academicYear != empty}'>
							<table
								class="table table-striped table-bordered table-hover table-full-width"
								id="sample_2">
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
											<a data-toggle="modal" href="#responsive2"
												class="btn default popUpResponse"
												id='<s:property value="academicYear.id" />'>School
												Timings </a>
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
													targets="subjectsContentDiv" cssClass="btn btn-xs purple"
													title="Edit">
													<i class="fa fa-edit"></i>Edit</sj:a>
											</td>
										</s:if>
									</tr>
								</tbody>
							</table>
							<div data-width="760" class="modal fade modal-overflow in"
								style="display: none; width: 760px; margin-left: -379px; margin-top: 150px;">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-hidden="true"></button>
									<h4 class="modal-title">
										View School Timings
									</h4>
								</div>
								<div class="modal-body">
									<s:if
										test="%{tempList2 != null || !tempList2.isEmpty && tempList2!= null || !tempList2.isEmpty}">
										<div id="timingValues" class="grid_14">

											<s:set var="classIds" value="-1"></s:set>
											<s:set var="countNum" value="0"></s:set>
											<s:iterator value="tempList2" status="itStatus">
												<s:if test='%{status == "SD"}'>
													<s:if test='%{#itStatus.count == 1}'>
														<input type="hidden" name="hiddenVar" id="hiddenVar"
															value="SD" />
														<div class="grid_12 th"
															style="width: 400px; margin-left: -4px;">
															<div class="grid_2">
																Day
															</div>
															<div class="grid_2">
																Start Time
															</div>
															<div class="grid_2">
																End Time
															</div>
														</div>
													</s:if>

													<div class="grid_12"
														style="width: 400px; margin-left: -4px;">
														<div class="grid_2"
															style="color: blue; font-weight: bold;">
															<s:property value="weekName" />
														</div>
														<div class="grid_2">
															<s:property value="startTime" />
														</div>
														<div class="grid_2">
															<s:property value="endTime" />
														</div>
													</div>
												</s:if>
												<s:elseif test='%{status == "CD"}'>
													<s:if test='%{#itStatus.count == 1}'>
														<input type="hidden" name="hiddenVar" id="hiddenVar"
															value="CD" />
														<div class="grid_12 th"
															style="width: 1036px; margin-left: -4px;">
															<div class="grid_2">
																Class/Day
															</div>
															<s:iterator value="weekDayList">
																<div class="grid_2" style="width: 115px;">
																	<s:property value="dayName" />
																</div>
															</s:iterator>
														</div>
													</s:if>
													<s:if test="%{classId == #classIds}">
														<s:if test="%{weekDay == 1}">
															<div class="grid_2" style="width: 115px;">
																<s:property value="startTime" />
																-
																<s:property value="endTime" />
															</div>
														</s:if>
														<s:elseif test="%{weekDay == 2}">
															<div class="grid_2" style="width: 115px;">
																<s:property value="startTime" />
																-
																<s:property value="endTime" />
															</div>
														</s:elseif>
														<s:elseif test="%{weekDay == 3}">
															<div class="grid_2" style="width: 115px;">
																<s:property value="startTime" />
																-
																<s:property value="endTime" />
															</div>
														</s:elseif>
														<s:elseif test="%{weekDay == 4}">
															<div class="grid_2" style="width: 115px;">
																<s:property value="startTime" />
																-
																<s:property value="endTime" />
															</div>
														</s:elseif>
														<s:elseif test="%{weekDay == 5}">
															<div class="grid_2" style="width: 115px;">
																<s:property value="startTime" />
																-
																<s:property value="endTime" />
															</div>
														</s:elseif>
														<s:elseif test="%{weekDay == 6}">
															<div class="grid_2" style="width: 115px;">
																<s:property value="startTime" />
																-
																<s:property value="endTime" />
															</div>
														</s:elseif>
														<s:elseif test="%{weekDay == 7}">
															<div class="grid_2" style="width: 115px;">
																<s:property value="startTime" />
																-
																<s:property value="endTime" />
															</div>
														</s:elseif>
													</s:if>
													<s:else>
														<br />
														<br />
														<br />
														<div class="grid_2 classNameShow"
															style="color: blue; font-weight: bold;">
															<s:property value="className" />
														</div>
														<s:if test="%{weekDay == 1}">
															<div class="grid_2" style="width: 115px;">
																<s:property value="startTime" />
																-
																<s:property value="endTime" />
															</div>
														</s:if>
														<s:elseif test="%{weekDay == 2}">
															<div class="grid_2" style="width: 115px;">
																<s:property value="startTime" />
																-
																<s:property value="endTime" />
															</div>
														</s:elseif>

														<s:elseif test="%{weekDay == 3}">
															<div class="grid_2" style="width: 115px;">
																<s:property value="startTime" />
																-
																<s:property value="endTime" />
															</div>
														</s:elseif>

														<s:elseif test="%{weekDay == 4}">
															<div class="grid_2" style="width: 115px;">
																<s:property value="startTime" />
																-
																<s:property value="endTime" />
															</div>
														</s:elseif>

														<s:elseif test="%{weekDay == 5}">
															<div class="grid_2" style="width: 115px;">
																<s:property value="startTime" />
																-
																<s:property value="endTime" />
															</div>
														</s:elseif>

														<s:elseif test="%{weekDay == 6}">
															<div class="grid_2" style="width: 115px;">
																<s:property value="startTime" />
																-
																<s:property value="endTime" />
															</div>
														</s:elseif>

														<s:elseif test="%{weekDay == 7}">
															<div class="grid_2" style="width: 115px;">
																<s:property value="startTime" />
																-
																<s:property value="endTime" />
															</div>
														</s:elseif>
													</s:else>
													<s:set var="classIds" value="%{classId}"></s:set>
												</s:elseif>
												<s:elseif test='%{status == "CT"}'>
													<s:if test='%{#itStatus.count == 1}'>
														<input type="hidden" name="hiddenVar" id="hiddenVar"
															value="CT" />
														<div class="grid_12 th"
															style="width: 400px; margin-left: -4px;">
															<div class="grid_2">
																Class
															</div>
															<div class="grid_2">
																Start Time
															</div>
															<div class="grid_2">
																End Time
															</div>
														</div>
													</s:if>
													<div class="grid_12"
														style="width: 400px; margin-left: -4px;">
														<div class="grid_2"
															style="color: blue; font-weight: bold;">
															<s:property value="className" />
														</div>
														<div class="grid_2">
															<s:property value="startTime" />
														</div>
														<div class="grid_2">
															<s:property value="endTime" />
														</div>
													</div>
												</s:elseif>
												<s:else>
													<input type="hidden" name="hiddenVar" id="hiddenVar"
														value="ST" />
													<div class="grid_4" style="margin-left: 90px;">
														<s:property value="startTime" />
														-
														<s:property value="endTime" />
													</div>
												</s:else>
											</s:iterator>
										</div>
										<s:set var="classIds" value="%{classId}"></s:set>
									</s:if>
						</s:if>
						<s:else>
							<div class="alert alert-info">
								There are no academic details created.
							</div>
						</s:else>
						<!--
						<div class="modal-footer">
							<button type="button" data-dismiss="modal"
								class="btn default cancel">
								Cancel
							</button>
						</div>
					-->
					</div>
				</div>
				<!--<div id="toPopup" style="width: 1036px;">
						 <div class="closePopUp"></div>
						      	<span class="ecs_tooltip">Press Esc to close <span class="arrow"></span></span>
							<div id="popup_content"> your content start
						           <p id="timeTableSelect"></p>
						       </div> your content end
						</div>
						<div class="loader"></div>
						<div id="backgroundPopup"></div>
						-->
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
$('a.popUpResponse').click(function() {
	$('div.modal-overflow').show();
});
$('button.close,.cancel').click(function() {
	$('div.modal-overflow').hide();
});

function confirmation() {
	var answer = confirm("Are you sure you want to switch to future academic year ?");
	if (!answer) {
		$('#futureAcademicOpt').attr("href", "javascript:void(0);");
	} else {
		$('#futureAcademicOpt')
				.attr("href",
						"${pageContext.request.contextPath}/admin/ajaxChangeAcademicYearStatus.do");
	}
}
/*$.subscribe('ajaxDoAddHolidays', function(event, data) {
 if ($('#' + data.id).is(":hidden")) {
 $('#' + data.id).show();
 } else {
 $('#' + data.id).hide();
 }
 });
 $.subscribe('ajaxDoAddExamTypes', function(event, data) {
 if ($('#' + data.id).is(":hidden")) {
 $('#' + data.id).show();
 } else {
 $('#' + data.id).hide();
 }
 });*/
</script>