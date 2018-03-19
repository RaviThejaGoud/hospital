<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/messages.jsp"%>
<s:if test="%{objectList != null && !objectList.isEmpty()}">
<div>
	<s:form action="ajaxDownloadStaffsDetails" theme="simple"
		namespace="/staff" id="classAndCommunity" method="post"
		cssClass="form-horizontal">
		<h4 class="pageTitle bold">
			Download staff 
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
								Please download the staff details by clicking the "Download
								Staff" button below.The downloaded excel sheet contains all staff
								details in the system.
							</li>
							<li>
								<font color="red">Please do not add new columns or delete
									the marked columns</font>. If you want to add more columns, please
								contact EazySchool support team(support@eazyschool.com).
							</li>
							<li>
								You can update the existing staff information or add the new staff information into the downloaded sheet
							</li>
							<li>
								You can not update staff role into the downloaded sheet.
							</li>
						</ul>
					</div>
				</div>
			</div>
			<s:hidden name="anyTitle" value="update"></s:hidden>
			<div class="form-body"></div>
				<div class="form-actions fluid">
					<div class="col-md-offset-2 col-md-9" style="float:right;width:250px;">
						<s:submit type="submit" value="Download Staff"
							cssClass="submitBt btn blue" title="generate report"
							onclick="reportFormate()" cssStyle="cursor:pointer">
						</s:submit>

					</div>
					<div class="col-md-offset-0 col-md-1">
						<s:url id="doAddStaffListBackLink" action="ajaxDoManageStaff"
							includeParams="all" escapeAmp="false" namespace="/staff">
						</s:url>
						<sj:a href="%{doAddStaffListBackLink}" cssClass="btn default"
							targets="mainContentDiv" indicator="indicator"> Back</sj:a>
					</div>
				</div>
			</div>
	</s:form>
</div>
<div class="clearfix">
	&nbsp;
</div>
<div class="form-body">
	<s:form action="ajaxUploadStaffData" id="importStudentExcelSheet"
		namespace="/staff" method="post" theme="simple"
		enctype="multipart/form-data" cssClass="form-horizontal">
		<h4 class="pageTitle bold">
			Upload staff 
		</h4>
		<div class="panel-body col-md-12">
			<div class="col-md-1">
				<span class="label label-danger"> NOTE : </span>
			</div>
			<div class="col-md-10">
				<ul>
					<li>
						The upload excel sheet must be downloaded from the above "Download
						staff" option.
					</li>
					<li>
						The system will not able to process the import successfully
						<font color="red">if any columns are changed or inserted</font> in
						the template.
					</li>
					<li>
						The system will generate the update status if any rows are not
						proceed due to data errors.
					</li>
					<li>
						<font color="red">The fields Roles, First Name and Mobile Number are mandatory to be filled. </font>
					</li>
				</ul>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-md-3">
				<span class="required">*</span>Select updated staff template (Excel) :
			</label>
			<div class="col-md-4">
				<s:file name="upload" id="photoURL" cssClass="btn default required" onchange="validateExcelSheet(this);">
				</s:file>
			</div>
		</div>
		<div class="form-actions fluid">
			<div class="col-md-offset-2 col-md-9">
				<sj:submit targets="staffsContent" value="Upload Staff"
					cssClass="submitBt btn blue" formIds="importStudentExcelSheet"
					validate="true" />
			</div>
		</div>
	</s:form>
</div>
</s:if>
		<s:else>
			<div class="alert alert-info">
				Currently there are no staff.
			</div>
		</s:else>
<script type="text/javascript">
function reportFormate() {
	$('.anyId').val('Excel');
}
</script>
