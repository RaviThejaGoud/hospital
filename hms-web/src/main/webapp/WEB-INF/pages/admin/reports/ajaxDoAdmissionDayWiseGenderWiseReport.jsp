<%@ include file="/common/taglibs.jsp"%> 
<s:form action="ajaxAdmissionDayWiseGenderWiseReport" theme="simple" cssClass="form-horizontal" onsubmit="return generateClassCommunityIds();"
								id="notAdmittedStudentsReports" method="post" namespace="/reports">
								<div class="form-body">
									<s:if test="%{academicYearList != null && !academicYearList.isEmpty}">
										<h4 class="pageTitle bold">Download Report Of Day wise report gender wise Details</h4>
										<div class="form-group">
												<label class="col-md-2 control-label">
													<span class="required">*</span>Select Academic Year :
												</label>
												<div class="col-md-3">
													<s:select id="academicYearId" list="academicYearList"
														cssClass="required form-control input-medium" listKey="id"
														listValue="academicYear" headerKey="0" 
														name="tempId2"
														onchange="javascript:academicApplicationDetails(this.value);" />
												</div>
											</div>
											<div id="getDayWiseGenderWiseDivId"></div>
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
<span id="loginRoleName" style="display: none;"><s:property
		value="user.userRoleDescription" /> </span>
<script  type="text/javascript" src="${pageContext.request.contextPath}/plugins/bootstrap-switch/static/js/bootstrap-switch.min.js"></script>
<script  type="text/javascript">
$(document).ready(function() {
	FormAdvanced.init();
	academicApplicationDetails($('#academicYearId').val());
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
	
	
	function academicApplicationDetails(academicYearId) {
		var pars = "academicYearId=" + academicYearId;
		var url = jQuery.url.getChatURL("/reports/ajaxDoGetAdmissionDayWiseGenderWiseReport.do");
		$.ajax( {
			url : url,
			cache : false,
			data : pars,
			success : function(html) {
				$("#getDayWiseGenderWiseDivId").html(html);
			}
		});
		
	}
	

	
	
</script>