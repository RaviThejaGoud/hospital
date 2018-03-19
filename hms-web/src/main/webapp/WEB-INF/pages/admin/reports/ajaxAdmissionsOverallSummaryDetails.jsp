<%@ include file="/common/taglibs.jsp"%>


<s:form action="ajaxAdmissionsOverallSummaryDetails" theme="simple"
	cssClass="form-horizontal" id="admissionsSummaryReportsForm"
	method="post" namespace="/reports">
	<div class="form-body">
		<s:if test="%{academicYearList != null && !academicYearList.isEmpty}">
			<h4 class="pageTitle bold">Download Admissions Over all Summary Report</h4>

			<div class="form-group">
				<label class="col-md-2 control-label"> <span
					class="required">*</span>Select Academic Year :
				</label>
				<div class="col-md-3">
					<s:select id="academicYearId" list="academicYearList"
						cssClass="required form-control input-medium" listKey="id"
						listValue="academicYear" headerKey="0" name="tempId2"
						onchange="javascript:academicApplicationDetails();" />
				</div>
			</div>


			<div class="form-actions fluid">
				<div class="col-md-offset-2 col-md-9">
					<s:submit type="submit" value="Generate Pdf" onclick="reportType()"
						cssClass="submit btn blue" title="generate report"
						cssStyle="float:left;margin-left:10px;">
					</s:submit>
				</div>
			</div>


		</s:if>
		<s:else>
			<div class="alert alert-info">
				Currently there are no admission settings created to any academic
				year.
				<s:url id="admissionSettingsCreat" namespace="/admin"
					action="ajaxAdmissionSettingsHome">
					<s:param name="description">createSettings</s:param>
				</s:url>
				<sj:a id="createAdmissionSettings" href="%{admissionSettingsCreat}"
					targets="mainContentDiv" data-toggle="tab">
					<b>Click here </b>
				</sj:a>
				to add Admission Settings
			</div>
		</s:else>



	</div>
</s:form>


<span id="loginRoleName" style="display: none;"><s:property
		value="user.userRoleDescription" /> </span>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/plugins/bootstrap-switch/static/js/bootstrap-switch.min.js"></script>
<script type="text/javascript">
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