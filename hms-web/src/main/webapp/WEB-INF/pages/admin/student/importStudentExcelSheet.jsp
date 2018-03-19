<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp"></jsp:include>
<s:form action="ajaxUploadNewStudentData" id="importStudentExcelSheet"
	namespace="/student" method="post" theme="simple"
	cssClass="form-horizontal" enctype="multipart/form-data" name="">
	<s:hidden name="eventId" value="Create"></s:hidden>
	<h4 class="bold pageTitle">
		Download template to import students
	</h4>
	<div class="form-body">
		<div>
			<div class="form-group">
				<div class="panel-body col-md-12">
					<div class="col-md-1">
						<span class="label label-danger"> NOTE : </span>
					</div>
					<div class="col-md-10">
						<ul>
							<li>
								Please download the students import excel sheet by clicking the
								"Download Template" button below.
							</li>
							<li>
								This excel sheet has marked with set of column names that are
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
			</div>
			<div class="form-actions fluid">
				<div class="col-md-offset-2 col-md-9" style="float:right;width:250px;">
					<a class="btn blue"
						href="${pageContext.request.contextPath}/student/ajaxDownloadStudent.do?selectedId=(0)">Download
						Template </a>
				</div>
				<div class="col-md-offset-0 col-md-1">
					<s:url id="doAddStudentListBackLink" action="ajaxGetStudyClassList"
						includeParams="all" escapeAmp="false" namespace="/student">
					</s:url>
					<sj:a href="%{doAddStudentListBackLink}" cssClass="btn default"
						targets="mainContentDiv" indicator="indicator"> Back</sj:a>
				</div>
			</div>
		</div>
		<div class="clearfix">
			&nbsp;
		</div>
		<h4 class="pageTitle bold">
			Add students
		</h4>
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
						The system will not able to process the import successfully
						<font color="red">if any columns are changed or inserted</font> in
						the template.
					</li>
					<li>
						The system will generate the import status if any rows are not
						proceed due to data errors.
					</li>
				</ul>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-md-4">
				<span class="required">*</span>Select the filled students template :
			</label>
			<div class="col-md-4">
				<div class="fileupload fileupload-new" data-provides="fileupload">
					<div class="input-append">
						<span class="btn default"> <s:file name="upload" onchange="validateExcelSheet(this);"
								type="file" value="" id="photoURL" cssClass="required">
							</s:file> </span>
					</div>
				</div>
			</div>
		</div>
		<!--<div class="form-group">
			<div class="col-md-5 ">
				<label class="control-label col-md-10">
					<span class="required">*</span>Select Students Import
					Template(Excel) :
				</label>
				<div>
					<input id="photoURL" class="btn default required" type="file"
						value="" name="upload">
				</div>
			</div>
		</div>
		-->
		<div class="form-actions fluid">
			<div class="col-md-offset-4 col-md-9">
				<sj:submit targets="staffList" value="Upload Template" indicator="indicator" 
					cssClass="btn blue long" formIds="importStudentExcelSheet" onCompleteTopics="doUploadStudents"
					validate="true" />
			</div>
		</div>
	</div>
</s:form>

<div id="errorFileDownload" style="display: none;">
<s:form action="ajaxGetNotUploadedStudents" id="sendStudentExcelSheet"
	namespace="/student" method="post" theme="simple"
	cssClass="form-horizontal" enctype="multipart/form-data" name="">
	<sj:submit  value="Upload Template" indicator="indicator" 
					cssClass="btn blue long" formIds="sendStudentExcelSheet"
					validate="true" />
	</s:form>
</div>
<script type="text/javascript">
$(document).ready(function() {
	changePageTitle('Import Students');
	$("#searchStudentsList").hide();
	//$("div#errorFileDownload").hide();
	$.destroyTopic("doUploadStudents");
	
	$.subscribe('doUploadStudents', function(event, data) {
		if($("div.alert-danger").is(":visible")){
			$("form#sendStudentExcelSheet").submit();	
		}
	});
});
</script>
 

