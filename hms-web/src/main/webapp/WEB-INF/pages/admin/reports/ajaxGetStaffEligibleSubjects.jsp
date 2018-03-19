<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Manage Staff Subjects Details
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
					<s:if test='%{user.isOnlySchoolAdmin=="Y"}'>
						<ul class="nav nav-tabs">
							<li class="active">
								<s:url id="viewSubj" action="ajaxViewStaffEligibleSubjects"
									namespace="/reports">
								</s:url>
								<sj:a id="viewSubj" href="%{viewSubj}" targets="mainContentDiv"
									data-toggle="tab">Download & Upload</sj:a>
							</li>
						</ul>
					</s:if>
					<div id="staffsSubjectsContent" class="tab-content">
						<%@ include file="/common/messages.jsp"%>
						<s:if test="%{objectList != null && !objectList.isEmpty()}">
						<div>
							<s:form action="ajaxDownloadStaffSubjectsDetails" theme="simple"
								namespace="/reports" method="post" cssClass="form-horizontal">
								<h4 class="pageTitle bold">
									Download staff subjects details
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
														Please download the staff class subject details by
														clicking the "Download Staff Subjects" button below.The
														downloaded excel sheet contains all staff subjects and class
														details in the system.
													</li>
													<li>
														<font color="red">Please do not add new columns or
															delete the marked columns</font>. If you want add more columns,
														please contact EazySchool support
														team(support@eazyschool.com).
													</li>
												</ul>
											</div>
										</div>
									</div>
									<div class="form-actions fluid">
										<div class="col-md-offset-2 col-md-9">
											<s:submit type="submit" value="Download Staff Subjects"
												cssClass="submitBt btn blue" title="generate report"
												onclick="reportFormate()" cssStyle="cursor:pointer">
											</s:submit>
										</div>
									</div>
								</div>
							</s:form>
						</div>
						<div class="clearfix">
							&nbsp;
						</div>
						<div class="form-body">
							<s:form action="ajaxUploadStaffSubjectsData"
								id="importStaffSubjectsExcelSheet" namespace="/reports"
								method="post" theme="simple" enctype="multipart/form-data"
								cssClass="form-horizontal">
								<s:hidden name="tempString" value="update"></s:hidden>
								<h4 class="pageTitle bold">
									Upload staff subjects details
								</h4>
								<div class="panel-body col-md-12">
									<div class="col-md-1">
										<span class="label label-danger"> NOTE : </span>
									</div>
									<div class="col-md-10">
										<ul>
											<li>
												The import excel sheet must be downloaded from the above
												"Download Staff Subjects" option.
											</li>
											<li>
												Please ensure that required columns are filled with
												necessary data.
											</li>
											<li>
												The system will not able to process the upload successfully
												<font color="red">if any columns are changed or
													inserted</font> in the template.
											</li>
											<li>
												The system will generate the upload status if any rows are
												not proceed due to data errors.
											</li>
										</ul>
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-md-2">
										<span class="required">*</span>Select Staff Subjects
										Template(Excel) :
									</label>
									<div class="col-md-4">
										<s:file name="upload" id="photoURL" onchange="validateExcelSheet(this);"
											cssClass="btn default required">
										</s:file>
									</div>
								</div>
								<div class="form-actions fluid">
									<div class="col-md-offset-2 col-md-9">
										<sj:submit targets="mainContentDiv" indicator="indicator" validate="true"
											value="Upload Staff Subjects" cssClass="submitBt btn blue"
											formIds="importStaffSubjectsExcelSheet" />
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
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
function reportFormate() {
	$('.anyId').val('Excel');
}
</script>
