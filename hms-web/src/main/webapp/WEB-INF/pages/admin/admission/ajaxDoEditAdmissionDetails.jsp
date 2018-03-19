<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Update Applications
				</div>
			</div>
			<div class="portlet-body">
				<div class="tab-content">
					<%@ include file="/common/messages.jsp"%>
					<s:if
						test="%{academicYearList != null && !academicYearList.isEmpty}">
						<s:form id="selectStudentForm" action="#" theme="simple"
							cssClass="form-horizontal">
							<div class="form-group">
								<label class="control-label col-md-2">
									<span class="required">*</span>Select Academic Year :
								</label>
								<div class="col-md-4">
									<s:select id="academicYearId" list="academicYearList"
										cssClass="required form-control input-medium" listKey="id"
										listValue="academicYear" headerKey="0"
										headerValue="- Select Academic Year -" name="tempId2"
										onchange="javascript:academicApplicationDetails(this.value);" />
								</div>
							</div>
						</s:form>
						<div id="updateApplicationContentDiv">
							<%@ include file="/common/messages.jsp"%>
							<jsp:include
								page="/WEB-INF/pages/admin/admission/ajaxCheckStausOfDetails.jsp"></jsp:include>
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
							<sj:a id="createAdmissionSettings"
								href="%{admissionSettingsCreat}" targets="mainContentDiv"
								data-toggle="tab">Click here</sj:a>
							to add Admission Settings
						</div>
					</s:else>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function() {
	changePageTitle('Download Student Details');
	var academicYearId = $('#academicYearId').val();
	if (isNonEmpty(academicYearId)) {
		academicApplicationDetails(academicYearId);
	}
	$.subscribe('doInitClassDetails', function(event, data) {
		if ($('#' + data.id).is(":hidden")) {
			$('#' + data.id).show();
		} else {
			$('#' + data.id).hide();
		}
	});
});
function academicApplicationDetails(academicYearId) {
	var pars = "academicYearId=" + academicYearId;
	$("#updateApplicationContentDiv")
			.html(
					'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	var url = jQuery.url.getChatURL("/admin/ajaxCheckStausOfApplications.do");
	$.ajax( {
		url : url,
		cache : false,
		data : pars,
		success : function(html) {
			$("#updateApplicationContentDiv").html(html);
			$("#updateApplicationContentDiv").show();
		}
	});
}
</script>