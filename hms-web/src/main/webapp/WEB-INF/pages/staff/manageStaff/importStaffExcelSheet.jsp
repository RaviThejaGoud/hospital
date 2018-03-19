<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/messages.jsp"%>
<s:form action="ajaxUploadStaffData" theme="simple" namespace="/staff"
	enctype="multipart/form-data" id="importStaffExcelSheet" method="post"
	cssClass="form-horizontal">
	<s:hidden name="tempString" value="createStaff"></s:hidden>
	<h4 class="pageTitle bold">
		Download template to import staff
	</h4>
	<div class="form-body">
	<div class="form-group">
			<div class="panel-body col-md-12">
				<div class="col-md-1">
					<span class="label label-danger"> NOTE : </span>
				</div>
				<div class="col-md-10">
					<ul>
						<li>
							Please download the staff import excel sheet by clicking the "Download Template" button below. 
						</li>
						<li>
							This excel sheet has marked with set of column names that are supported by the system.
						</li>
						<li>	 
							<font color="red">Please do not add new columns or delete the marked columns</font>. If you want add more columns, please contact EazySchool support team(support@eazyschool.com). 
						</li>
						<li>
							Please fill the designated columns like Role, Name, Date of birth etc. for each row in the template.
						</li>
					</ul>
				</div>
			</div>
		</div>
		<div class="form-actions fluid">
			<div class="col-md-offset-2 col-md-9"
				style="float: right; width: 250px;">
				<a class="btn blue"
					href="${pageContext.request.contextPath}/staff/ajaxDownloadStaffsDetails.do">Download
					Template </a>
			</div>
			<div class="col-md-offset-0 col-md-1">
				<s:url id="doAddStaffListBackLink" action="ajaxDoManageStaff"
					includeParams="all" escapeAmp="false" namespace="/staff">
				</s:url>
				<sj:a href="%{doAddStaffListBackLink}" cssClass="btn default"
					targets="mainContentDiv" indicator="indicator"> Back</sj:a>
			</div>
		</div>
		<div class="clearfix">
		&nbsp;
	</div>
	<div class="form-body">
		<h4 class="pageTitle bold">
			Add Staff
		</h4>
		<div class="panel-body col-md-12">
			<div class="col-md-1">
				<span class="label label-danger"> NOTE : </span>
			</div>
			<div class="col-md-10">
				<ul>
					<li>
						The import excel sheet must be downloaded from the above "Download Template" option. 
					</li>
					<li>
						Please ensure that required columns are filled with necessary data.
					</li>
					<li>
						The system will not able to process the import successfully <font color="red">if any columns are changed or inserted</font> in the template.
					</li>
					<li>
						The system will generate the import status if any rows are not proceed due to data errors.
					</li>
					<li>
						<!-- <font color="red">Staff mandatory fields are like Role, First Name, Gender, Mobile Number, Employment Type, Marital Status, City, State. </font> -->
						<font color="red">The fields Roles, First Name and Mobile Number are mandatory to be filled. </font>
					</li>
				</ul>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-md-3">
				<span class="required">*</span>Select the filled staff template :
			</label>
			<div class="col-md-4">
				<div class="fileupload fileupload-new" data-provides="fileupload">
					<div class="input-append">
						<span class="btn default"> <s:file name="upload" onchange="validateExcelSheet(this);"
								id="photoURL" cssClass="required">
							</s:file> </span>
					</div>
				</div>
			</div>
		</div>
		<div class="form-actions fluid">
			<div class="col-md-offset-2 col-md-9">
				<sj:submit targets="staffsContent" value="Upload Template"
					formIds="importStaffExcelSheet" cssClass="submitBt btn blue"
					validate="true" />
			</div>
		</div>
	</div>
</s:form>
<script type="text/javascript">
	changePageTitle('Import Staff Template');
</script>
