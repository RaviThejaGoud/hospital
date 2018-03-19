<%@ include file="/common/taglibs.jsp"%>
<%-- <s:if test="%{(studyClassList != null && !studyClassList.isEmpty()) && (smsCnt) > 0}"> --%>
<div class="panel-body col-md-12">
	<div class="col-md-1">
		<span class="label label-danger"> NOTE : </span>
	</div>
	<div class="col-md-10">
		<ul>
			<li>
				'+' icon in the 'Add Periods' cell Indicates that you need to Create the periods for that class.
			</li>
			<li>
				'-' icon in the 'Add Periods' cell indicates that you have created periods to that class for all working days.
			</li>
		</ul>
	</div>
</div>
<div class="stepSchoolPeriods">
	<table class="table table-striped table-bordered table-hover table-full-width" id="sample_2">
		<thead>
			<tr>
				<th>
					Class Name
				</th>
				<th>
					Total Periods
				</th>
				<th>
					<s:if test='%{#session.previousYear=="N"}'>
						Add Periods
					</s:if>
				</th>
				<th>
					<s:if test='%{#session.previousYear=="N"}'>
						Edit
					</s:if>
				</th>
				<th>
					<s:if test='%{#session.previousYear=="N"}'>
						Delete Day Wise Periods
					</s:if>
				</th>
				<th>
					<s:if test='%{#session.previousYear=="N"}'>
						Delete Class Wise Periods
					</s:if>
				</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="studyClassList">
				<s:if test="%{totalPeriods > 0}">
					<tr>
						<td>
							<s:property value="classAndSection" />
						</td>
						<td>
							<ul class="tooltipDiv">
								<li>
									<a href="#"><s:property value="totalPeriods" /> </a>
									<ul class="tooltipSubDiv">
										<div class="popover bottom " style="display: none;">
											<div class="arrow"></div>
											<h3 class="popover-title">
												Total Periods
											</h3>
											<div class="popover-content">
												<s:if
													test="%{timeTablePeriodsDetails != null && !timeTablePeriodsDetails.isEmpty()}">
													<s:iterator value="timeTablePeriodsDetails">
														<li>
															<s:property value="dayName" />
															:
															<s:property value="periodsCount" />
														</li>
													</s:iterator>
												</s:if>
											</div>
										</div>
									</ul>
								</li>
							</ul>
						</td>
						<td>
							<s:if test='%{#session.previousYear=="N"}'>
								<s:if test="%{numberOfDays > timeTablePeriodsDetails.size}">
									<s:url id="doAddPeriodsToWrkDay"
										action="ajaxDoAddPeriod" escapeAmp="false" namespace="/admin">
										<s:param name="studyClassId" value="%{id}" />
										<s:param name="anyId" value="%{dayIds}" />
										<s:param name="tempString" value="%{classAndSection}" />
									</s:url>
									<sj:a href="%{doAddPeriodsToWrkDay}" targets="sclPeriodsContDiv"
										cssClass="btn btn-xs green"
										title="Add Periods to remaining working days"><i class="fa fa-plus"></i>
									</sj:a>
								</s:if>
								<s:else>
										<p class="btn btn-xs green"><i class="fa fa-minus"></i>
										
								</s:else>
							</s:if>
						</td>
						<td>
							<s:if test='%{#session.previousYear=="N"}'>
								<ul class="tooltipDiv">
									<li>
										<a href="#" class="btn btn-xs purple"> <i class="fa fa-edit"></i>Edit</a>
										<ul class="tooltipSubDiv">
											<div class="popover bottom " style="display: none;">
												<div class="arrow"></div>
												<h3 class="popover-title">
													Edit Periods
												</h3>
												<div class="popover-content">
													<s:if test="%{timeTablePeriodsDetails != null && !timeTablePeriodsDetails.isEmpty()}">
															<s:iterator value="timeTablePeriodsDetails">
																<li>
																<s:url id="doEditTimeTableperiod" action="ajaxDoEditSchoolPeriods" escapeAmp="false" namespace="/admin">
																	<s:param name="studyClassId" value="%{classSectionId}" />
																	<s:param name="anyId" value="%{dayId}" />
																	<s:param name="academicYearId" value="%{academicYearId}" />
																	<s:param name="tempString" value="%{classAndSection}" />
																</s:url>
																<sj:a href="%{doEditTimeTableperiod}" targets="sclPeriodsContDiv" title="Edit">
																	<s:property value="dayName" />
																</sj:a>
															</li>
														</s:iterator>
													</s:if>
												</div>
											</div>
										</ul>
									</li>
								</ul>
							</s:if>
						</td>
						<td>
							<s:if test='%{#session.previousYear=="N"}'>
								<ul class="tooltipDiv">
									<li>
										<a href="#" class="btn btn-xs red"> <i class="fa fa-times"></i>Delete</a>
										<ul class="tooltipSubDiv">
											<div class="popover bottom " style="display: none;">
												<div class="arrow"></div>
												<h3 class="popover-title">
													Delete Periods
												</h3>
												<div class="popover-content">
													<s:if test="%{timeTablePeriodsDetails != null && !timeTablePeriodsDetails.isEmpty()}">
														<s:iterator value="timeTablePeriodsDetails">
															<li>
																<s:url id="removeClassPeriods"
																	action="ajaxRemoveDayWiseClassPeriods" escapeAmp="false" namespace="/admin">
																	<s:param name="classSectionId" value="%{classSectionId}" />
																	<s:param name="anyId" value="%{dayId}" />
																</s:url>
																<s:div 
																	onclick="javascript:confirmDialogWithTarget(this,'mainContentDiv');"
																	id='%{removeClassPeriods}' theme="simple" cssStyle="width:20px;float:left;cursor:pointer;"
																	title="Delete periods"><i class="fa fa-trash-o"></i>
																</s:div>
																<s:property value="dayName" />
															</li>
														</s:iterator>
													</s:if>
												</div>
											</div>
										</ul>
									</li>
								</ul>
							</s:if>
						</td>
						<td>
							<s:if test='%{#session.previousYear=="N"}'>
								<s:url id="removeClassWisePeriods"
									action="ajaxRemoveClassWisePeriods" escapeAmp="false" namespace="/admin">
									<s:param name="classSectionId" value="%{id}" />
								</s:url>
								<s:div cssClass="btn btn-xs red"
									onclick="javascript:confirmDialogWithTarget(this,'mainContentDiv');"
									id='%{removeClassWisePeriods}' theme="simple"
									title="Delete Entire Class Periods"><i class="fa fa-times"></i>Delete
								</s:div>
							</s:if>
						</td>
					</tr>
				</s:if>
			</s:iterator>
		</tbody>
	</table>
	</div>
<%-- </s:if><div class="spaceDiv">&nbsp;</div><div class="spaceDiv">&nbsp;</div>
<s:else>
	<div class="alert alert-info">
		You have not created periods.
	</div>
</s:else>
 --%><script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/onload.js">
</script>
<script type="text/javascript">
TableAdvanced.init();
</script>