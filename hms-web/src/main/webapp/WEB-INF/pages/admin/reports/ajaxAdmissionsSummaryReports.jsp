<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>
					<span class="hidden-481">Admission Summary </span>
				</div>
			</div>
			<div class="portlet-body">
			
			<div class="tabbable tabbable-custom tabbable-full-width">
				<ul class="nav nav-tabs">
						<s:if
							test='%{(#session.previousYear == null || #session.previousYear.isEmpty()) || (#session.previousYear=="N")}'>
							
							
							<li>
								<s:url id="urlReAdmissionFeeReport" namespace="/reports"
									action="ajaxDoReAdmissionFeeReport" />
								<sj:a id="urlReAdmissionFeeReport" href="%{urlReAdmissionFeeReport}"
									targets="admissionsContent" data-toggle="tab">Re-Amission Fee Report</sj:a>
							</li>
							
							<li class="dropdown">
								<a data-hover="dropdown" data-toggle="dropdown"
									class="dropdown-toggle js-activated" href="#"> Admission Summary <b class="caret"></b>
								</a>
								<ul role="menu" class="dropdown-menu pull-right">
									<li>
										<s:url id="urlDayWiseDetails" namespace="/reports"
											action="ajaxDoAdmissionDayWiseGenderWiseReport" />
										<sj:a id="urlDayWiseDetails" href="%{urlDayWiseDetails}"
											targets="admissionsContent" data-toggle="tab">Summary Report</sj:a>
									</li>
									 <li>
										<s:url id="doAdmissionOverallSummary" action="ajaxDoAdmissionsOverallSummaryDetails" includeParams="all" escapeAmp="false">
										</s:url>
										<sj:a href="%{doAdmissionOverallSummary}" indicator="indicator"
											targets="admissionsContent" button="false">Overall Summary Report</sj:a>
									</li>
								</ul>
							</li>
						
							<li class="dropdown active">
								<a data-hover="dropdown" data-toggle="dropdown"
									class="dropdown-toggle js-activated" href="#"> Enquiry Summary <b class="caret"></b>
								</a>
								<ul role="menu" class="dropdown-menu pull-right">
									<li>
										<s:url id="urlAdmissionsSummaryDetails" namespace="/reports"
										action="ajaxAdmissionsSummaryDetails" />
									<sj:a id="urlAdmissionsSummaryDetails" href="%{urlAdmissionsSummaryDetails}"
										targets="mainContentDiv" data-toggle="tab">Admitted Enquiries</sj:a>
									</li>
									 <li>
											<s:url id="urlNotAdmittedStudentsDetails" namespace="/reports"
										action="ajaxNotAdmittedStudentsDetails" />
									<sj:a id="urlNotAdmittedStudentsDetails" href="%{urlNotAdmittedStudentsDetails}"
										targets="admissionsContent" data-toggle="tab">Not Admitted Enquiries</sj:a>
										</li> 
								</ul>
							</li>
						</s:if>
					</ul>
					<div class="tab-content" id="admissionsContent">
							<s:form action="ajaxAdmissionsSummaryReports" theme="simple"
								cssClass="form-horizontal"
								id="admissionsSummaryReportsForm" method="post" namespace="/reports">
								<div class="form-body">
									<s:if test="%{academicYearList != null && !academicYearList.isEmpty}">
										<h4 class="pageTitle bold">Download Admitted Enquiries details</h4>
											
										<div class="form-group">
												<label class="col-md-2 control-label">
													<span class="required">*</span>Select Academic Year :
												</label>
												<div class="col-md-3">
													<s:select id="academicYearId" list="academicYearList"
														cssClass="required form-control input-medium" listKey="id"
														listValue="academicYear" headerKey="0" 
														name="tempId2"
														onchange="javascript:academicApplicationDetails();" />
												</div>
											</div>
											
											
											<div class="form-actions fluid">
												<div class="col-md-offset-2 col-md-9">
													<s:submit type="submit" value="Generate Pdf" 
																onclick="reportType()" cssClass="submit btn blue"
																title="generate report" cssStyle="float:left;margin-left:10px;">
															</s:submit>
											</div>
										</div>
										
										
									</s:if>
									<s:else>
											<div class="alert alert-info">
												Currently there are no admission settings created to any academic
												year.
												<s:url id="admissionSettingsCreat" namespace="/admin"
													action="ajaxAdmissionSettingsHome" >
													<s:param name="description">createSettings</s:param>
												</s:url>
												<sj:a id="createAdmissionSettings" href="%{admissionSettingsCreat}"
													targets="mainContentDiv" data-toggle="tab"><b>Click here </b></sj:a> to add Admission Settings
											</div>
										</s:else>
									
										
									
								</div>
							</s:form>
					</div>
			</div>
					
				</div>
			</div>
		</div>
	</div>
<span id="loginRoleName" style="display: none;"><s:property
		value="user.userRoleDescription" /> </span>
<script  type="text/javascript" src="${pageContext.request.contextPath}/plugins/bootstrap-switch/static/js/bootstrap-switch.min.js"></script>
<script  type="text/javascript">
$(document).ready(function() {
	FormAdvanced.init();
	
		});
	$('html, body').animate({ scrollTop: $(document).height() - $(window).height() }, 10, function() {
	    $(this).animate({ scrollTop: 0 }, 10);
	});
	function reportFormate() {
		$('.anyId').val('Excel');
	}
	function reportType() {
		$('.anyId').val('PDF');
	}
	
	

	
	
</script>