<%@ include file="/common/taglibs.jsp"%>
<span id="createAdmissions" class="<s:property value='description'/>"></span>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Admission Settings
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
					<ul class="nav nav-tabs">
						<s:if test='%{(tempBoolean==true) }'>
							<s:if
								test='%{(#session.previousYear == null || #session.previousYear.isEmpty()) || (#session.previousYear == "N")}'>
								<li id="settingId">
									<s:url id="admissionSettings" namespace="/admin"
										action="manageAdmissionSettings" includeParams="all"
										escapeAmp="false">
									</s:url>
									<sj:a id="admissionSettings" href="%{admissionSettings}"
										targets="admissionSettingsContentDiv" data-toggle="tab">Add Settings</sj:a>
								</li>
							</s:if>
							<li class="active" id="closeId">
								<s:url id="viewAdmissionSettings"
									action="ajaxAdmissionSettingsHome" namespace="/admin" />
								<sj:a id="viewAdmissionSettings" href="%{viewAdmissionSettings}"
									targets="mainContentDiv" data-toggle="tab">View Admission Settings</sj:a>
							</li>
						</s:if>
					</ul>
					<div class="tab-content" id="admissionSettingsContentDiv">
						<%@ include file="/common/messages.jsp"%>
						<s:if
							test="%{academicYearList != null && !academicYearList.isEmpty}">
							<s:form id="selectStudentForm" action="#" theme="simple"
								cssClass="form-horizontal">
								<div class="form-group">
									<div class="col-md-9">
										<label class="col-md-5 control-label">
											<span class="required">*</span>Select Academic Year :
										</label>
										<div class="col-md-4">
											<s:select id="academicYearId" list="academicYearList"
												cssClass="required form-control input-medium" listKey="id"
												listValue="academicYear" headerKey="0"
												 name="usrChgedAcademicId"
												onchange="javascript:academicAdmissionDetails(this.value);" />
										</div>
									</div>
								</div>
							</s:form>
							<div class="clearfix">&nbsp;</div>
							<div id="academicSettingsContent">
								<jsp:include
									page="/WEB-INF/pages/admin/admission/ajaxAdmissionSettings.jsp"></jsp:include>
							</div>
						</s:if>
						<s:else>
							<div class="alert alert-info">
								Currently there are no admission settings created to any
								academic year.
								<s:url id="admissionSettings" namespace="/admin"
									action="manageAdmissionSettings" includeParams="all"
									escapeAmp="false">
								</s:url>
								<sj:a id="admissionSettingsId" href="%{admissionSettings}"
									targets="admissionSettingsContentDiv" data-toggle="tab"><b>Click here</b>
										</sj:a> to add Admission Settings
							</div>
						</s:else>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
changePageTitle('Admission Settings');
$(document).ready(function() {
	var createSettings = $('#createAdmissions').attr('class');
	if(isNonEmpty(createSettings)){
		$('#admissionSettings').click();
		$('li#admittedStudsNav').removeClass('active');
		$('li#applicationsNav').removeClass('active');
		$('li#approvedStudsNav').removeClass('active');
		$('li#admissoinSettingsNav').removeClass('active');
		$('li#admissionSettgs').addClass('active');
	}else{
		var academicYearId = $('#academicYearId').val();
		if (isNonEmpty(academicYearId)) {
			academicAdmissionDetails(academicYearId);
		}
	}
	/*$('li#settingId').addClass('active');
	$('li#closeId').removeClass('active');*/
});
function academicAdmissionDetails(academicYearId) {
	var pars = "academicYearId=" + academicYearId;
	var url = jQuery.url.getChatURL("/admin/ajaxAdmissionSettings.do");
	$("#academicSettingsContent")
			.html(
					'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	$.ajax( {
		url : url,
		cache : false,
		data : pars,
		success : function(html) {
			$("#academicSettingsContent").html(html);
			$("#academicSettingsContent").show();
		}
	});
}
</script>
