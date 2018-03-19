<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/messages.jsp"%>
<div class="row">
	<div class="col-md-12">
		<s:form id="downloadStudentForm"
			action="ajaxDoDownloadAdmissionStudents" theme="simple"
			onsubmit="return generateImportStudentExcelSheet();"
			cssClass="form-horizontal" method="post" namespace="/student">
			<s:hidden name="selectedId" value="(0)"></s:hidden>
			<s:hidden name="anyTitle" value="AdmissionProcess"></s:hidden>
			<div class="form-body">
			<label>Download admissions template for adding students through admissions</label>
				<div class="panel-body col-md-12">
					<div class="col-md-1">
						<span class="label label-danger"> NOTE : </span>
					</div>
					<div class="col-md-10">
						<ul>
							<li> 
								<font color="red">In a downloaded excel sheet<b> Admission number, First name, Class & 
  										 Section fields are </b>mandatory</font>.
							</li>
							<li>
								Please select academic year and  download the students admissions excel sheet by clicking the
								"Download Template" button below.
							</li>
							<li>
								This excel has marked with set of column names that are
								supported by the system.
							</li>
							<li>
								<font color="red">Please do not add new columns or delete
									the marked columns</font>. If you want add more columns, please
								contact EazySchool support team(support@eazyschool.com).
							</li>
						</ul>
					</div>
				</div>
					<label class="control-label col-md-3">
						<span class="required">*</span>Select Academic Year :
					</label>
						<s:select id="academicYearId" list="academicYearList"
							cssClass="required form-control input-medium" listKey="id"
							listValue="academicYear" headerKey="" headerValue="- Select -"
							name="academicYearId" />
				<div class="spaceDiv">
					&nbsp;
				</div>
				<div class="form-actions fluid">
					<div class="col-md-offset-3 col-md-9">
						<s:submit value="Download Template"
							cssClass="btn blue long" />
						<s:url id="doGetOnlineAdmissions" action="ajaxGetOnlineAdmissions"
							includeParams="all" escapeAmp="false" namespace="/admin"></s:url>
						<sj:a href="%{doGetOnlineAdmissions}" cssClass="btn default"
							indicator="indicator" targets="mainContentDiv">Cancel</sj:a>
					</div>
				</div>
			</div>
		</s:form>
		<div class="spaceDiv">
					&nbsp;
				</div>
		<s:form action="ajaxUploadNewAdmissionStudentsDetails"
			cssClass="form-horizontal" id="uploadStudentAdmissions"
			namespace="/student" method="post" theme="simple"
			enctype="multipart/form-data" name="">
			<div class="form-body">
			<s:hidden name="tempString" value="add"></s:hidden>
			<label>Upload admissions template</label>
				 <div class="form-group">
				<div class="panel-body col-md-12">
			<div class="col-md-1">
				<span class="label label-danger"> NOTE : </span>
			</div>
			<div class="col-md-10">
				<ul>
					<li>
						The import excel sheet must be downloaded from the above "Download
						Template" option.
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
			</div>
				<div class="form-group">
					<label class="control-label col-md-4">
						<span class="required">*</span>Upload admissions template (.xls):
					</label>
					<span class="btn default"><s:file name="upload" onchange="validateExcelSheet(this);"
							id="photoURL" cssClass="btn default required">
						</s:file> </span>
				</div>
				<div class="form-actions fluid">
					<div class="col-md-offset-3 col-md-9">
						<sj:submit   targets="mainContentDiv"
							value="Upload Template" resetForm="true"
							formIds="uploadStudentAdmissions" cssClass="btn blue long"
							validate="true" indicator="indicator"/>
						<s:url id="doGetOnlineAdmissions" action="ajaxGetOnlineAdmissions"
							includeParams="all" escapeAmp="false" namespace="/admin"></s:url>
						<sj:a href="%{doGetOnlineAdmissions}" cssClass="btn default"
							indicator="indicator" targets="mainContentDiv">Cancel</sj:a>
					</div>
				</div>
			</div>
		</s:form>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function() {
	changePageTitle('Application Details');
});
function generateImportStudentExcelSheet() {
	var academicYearId = $("#academicYearId").val();
	if (isNonEmpty(academicYearId)) {
		return true;
	} else {
		alert("Please select academic year to generate admission excel sheet.");
		return false;
	}
}
</script>