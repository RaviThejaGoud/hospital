<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Application Details
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
					<ul class="nav nav-tabs">
						<s:if
							test='%{(#session.previousYear == null || #session.previousYear.isEmpty()) || (#session.previousYear=="N")}'>
							<s:if
								test="%{academicYearList != null && !academicYearList.isEmpty}">
								<li>
									<s:url id="urlDownloadApplications" namespace="/admin"
										action="ajaxDownloadApplications" includeParams="all"
										escapeAmp="false">
									</s:url>
									<sj:a id="doDownloadApplications"
										href="%{urlDownloadApplications}" targets="admissionsContent"
										data-toggle="tab">Download / Upload Admissions</sj:a>
								</li>
								<!--<li>
									<s:url id="urlPendingApplications" namespace="/admin"
										action="ajaxPendingApplications">
									</s:url>
									<sj:a id="doPendingApplications"
										href="%{urlPendingApplications}" targets="admissionsContent"
										data-toggle="tab">Pending</sj:a>
								</li>
								--><li>
									<s:url id="urlApplyAdmission" namespace="/admin"
										action="ajaxApplyOfflineAdmission" />
									<sj:a id="urlApplyAdmission" href="%{urlApplyAdmission}"
										targets="admissionsContent" data-toggle="tab">Add Application</sj:a>
								</li>
								
								<li>
									<s:url id="urlAdmissionInquiry" namespace="/admin"
										action="ajaxAdmissionInquiryDetails" />
									<sj:a id="urlAdmissionInquiry" href="%{urlAdmissionInquiry}"
										targets="admissionsContent" data-toggle="tab">Admission Enquiry</sj:a>
								</li>
								
							</s:if>
							<%--<li>
							<a
							href="${pageContext.request.contextPath}/signup/downloadApplicationForm.do?custId=<s:property value="userCustId" />"
							 target="_new">Print Online Application</a>
							</li>
							--%><li class="active">
									<s:url id="urlgetApplications" namespace="/admin"
										action="ajaxGetOnlineAdmissions" />
									<sj:a id="urlgetApplications" href="%{urlgetApplications}"
										targets="mainContentDiv" data-toggle="tab">View Pending Applications</sj:a>
								</li>
						</s:if>
					</ul>
					<div class="tab-content" id="admissionsContent">
						<jsp:include page="/WEB-INF/pages/admin/admission/ajaxSearchApplications.jsp"></jsp:include>
					</div>
					
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function() {
	changePageTitle('Application Details');
});
$('html, body').animate({ scrollTop: $(document).height() - $(window).height() }, 10, function() {
	    $(this).animate({ scrollTop: 0 }, 10);
	});
</script>