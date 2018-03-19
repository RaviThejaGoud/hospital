<%@ include file="/common/taglibs.jsp"%>
	<s:if test="%{objectList != null && !objectList.isEmpty()}">
		<s:form id="selectStudentForm" action="#" theme="simple"
			cssClass="form-horizontal">
			<s:hidden name="status" cssClass="status"></s:hidden>
		<div class="col-md-12">
			<span class="label label-danger"> NOTE : </span> &nbsp; Please select
				any one application type to download the applications data to update.
		</div>
		<div class="form-group">
				<label class="control-label col-md-2">
					Choose Applications Type :
				</label>
				<div class="radio-list">
					<div class="radio-inline">
						<s:radio list="objectList" listKey="status" listValue="status"
							name="anyId" id="admissionStatus"
							onclick="prepareAdmissionTypeData()" />
					</div>
				</div>
			</div>
		</s:form>
	</s:if>
	<s:else>
		<div class="alert alert-info">
			No application status options found.
		</div>
	</s:else>
	<div id="displayAdmissionClasss">
		<jsp:include
			page="/WEB-INF/pages/admin/admission/ajaxCheckStausOfClassListDetails.jsp"></jsp:include>
	</div>
<div class="form-body">
	<s:if test='%{#session.previousYear == "N"}'>
		<s:form action="ajaxUploadAdmissionStudentsDetails"
			id="importStudentExcelSheetWithAdmission" namespace="/student"
			method="post" theme="simple" name="" cssClass="form-horizontal" enctype="multipart/form-data">
			<s:hidden name="tempString" value="Edit"></s:hidden>
			<h4 class="pageTitle bold">
				Upload students details
			</h4>
			<div class="panel-body col-md-12">
			<div class="col-md-1">
				<span class="label label-danger"> NOTE : </span>
			</div>
			<div class="col-md-10">
				<ul>
					<li>
						The import excel sheet must be downloaded from the selection of above options [admitted (or) pending (or) shortlisted (or) rejected] and "Download
						Student" option.
					</li>
					<li>
						Please ensure that required columns are filled with necessary data.
					</li>
					<li>
						The system will not able to process the upload successfully
						<font color="red">if any columns are changed or inserted</font> in
						the template.
					</li>
					<li>
						The system will generate the upload status if any rows are not
						proceed due to data errors.
					</li>
				</ul>
			</div>
		</div>
			<div class="form-group">
				<label class="control-label col-md-4">
					<span class="required">*</span>Select students import template (Excel) :
				</label>
				<div class="col-md-6">
					<s:file name="upload" id="photoURL" cssClass="btn default required" onchange="validateExcelSheet(this);">
					</s:file>
				</div>
			</div>
			<div class="form-actions fluid">
				<div class="col-md-offset-2 col-md-9">
					<sj:submit   targets="mainContentDiv" value="Upload Student" cssClass="btn blue"
						formIds="importStudentExcelSheetWithAdmission" validate="true" />
						<s:url id="urlOnlineAdmissions" action="ajaxGetEditOnlineAdmissions" namespace="/admin" includeParams="all" escapeAmp="false"/>
								<sj:a href="%{urlOnlineAdmissions}" targets="mainContentDiv" cssClass="btn default">Cancel</sj:a>
				</div>
			</div>
		</s:form>
	</s:if>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/ems.js"> </script>
<script type="text/javascript">
$(document).ready(function() {
	$("#displayAdmissionClasss").hide();
});
		
	function prepareAdmissionTypeData(){
		var academicYearId = $('#academicYearId').val();
		var includeIds=[];
		includeIds=$("input[name=anyId]:checked").map(function () {return this.value;}).get().join(",");
		statusWithApplicationDetails(academicYearId,includeIds);
	}
		
function statusWithApplicationDetails(academicYearId,includeIds) {
	$('.status').val(includeIds);
	if(isNonEmpty(includeIds)){
		var pars = "anyTitle=" + includeIds +"&academicYearId="+academicYearId;
		$("#displayAdmissionClasss").html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		$("#displayAdmissionClasss").show();
		var url = jQuery.url.getChatURL("/admin/ajaxCheckStausOfClassesAppList.do");
		$.ajax( {
			url : url,
			cache : false,
			data : pars,
			success : function(html) {
				$("#displayAdmissionClasss").html(html);
			}
		});		
	}else{
		$("#displayAdmissionClasss").hide();
	}
}
</script>