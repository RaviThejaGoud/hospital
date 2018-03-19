<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/messages.jsp"%>

<div id="admissionSettingsContentDiv">
<s:if test="%{academicYearList != null && !academicYearList.isEmpty}">
	<s:form action="ajaxAddAdmissionNumberSettings" theme="simple" id="addAdmissionNumberSettings" method="post" cssClass="form-horizontal" namespace="/admin">
		<p>
			<span class="label label-danger"> NOTE : </span> &nbsp;&nbsp;
			1. This functionality is related to fetching admission number in admission module only.<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2. Once the admission number setting is been added even for one class, then the setting would not be editable.
		</p>
			
		<div class="form-group">
			<div class="col-md-9">
				<label class="col-md-5 control-label">
					<span class="required">*</span>Select Academic Year :
				</label>
				<div class="col-md-4">
					<s:select id="academicYearId" list="academicYearList"
						cssClass="required form-control input-medium" listKey="id"
						listValue="academicYear" headerKey="0"
						 name="academicYearId"
						onchange="javascript:academicAdmissionDetails(this.value);" />
				</div>
			</div>
		</div>
	
		<div class="clearfix">&nbsp;</div>
		<div id="academicSettingsContent">
			<jsp:include page="/WEB-INF/pages/admin/ajaxDoGetAdmissionNumberSettings.jsp"></jsp:include>
		</div>
	
	</s:form>
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
	var url = jQuery.url.getChatURL("/admin/ajaxDoGetAdmissionNumberSettings.do");
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
