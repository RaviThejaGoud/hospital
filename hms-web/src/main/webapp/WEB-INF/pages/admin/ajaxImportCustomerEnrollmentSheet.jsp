
<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Upload Customer Enrollment Sheet
				</div>
			</div>
			<div class="portlet-body">
				<div class="tab-content" id="urlStudentMarksSheetDiv">
					<jsp:include page="/common/messages.jsp"></jsp:include>
					<s:form action="ajaxUploadSchoolData"
						id="importCustomerEnrollement" cssClass="form-horizontal"
						method="post" theme="simple" enctype="multipart/form-data"
						namespace="/madmin">
						<div class="form-body">
							<h4 class="pageTitle bold">Download Customer Enrollment Sheet
							</h4>
							<div class="form-body">
								<div class="form-group">
									<div class="panel-body col-md-12">
										<div class="col-md-1">
											<span class="label label-danger"> NOTE : </span>
										</div>
										<div class="col-md-10">
											<ul>
												<li>Please download the customer enrollment excel sheet by
													clicking the "Download Template" button below.</li>
											</ul>
										</div>
									</div>
								</div>
								<div class="form-actions fluid">
									<div class="col-md-offset-2 col-md-9">
										<a class="btn blue"
											href="${pageContext.request.contextPath}/userFiles/Enrollmentsheet/Enrollmentsheet.xls"
											title="Download">Download Template</a>
										<div class="spaceDiv"></div>
									</div>
								</div>
							</div>
							<h4 class="bold pageTitle">Upload Customer Enrollment Sheet
							</h4>
							<p>
								<span class="label label-danger"> NOTE : </span> &nbsp;&nbsp;
								This sheet contains entire school information.
							</p>
							<div class="row">
								<div class="col-md-9">
									<div class="form-group">
										<label class="control-label col-md-4"> <span
											class="required">*</span>Select Enrollment Sheet:
										</label>
										<div class="col-md-4">

											<s:file name="upload" id="enrollmentForm" onchange="validateExcelSheet(this);"
												cssClass="btn default required">
											</s:file>
										</div>
									</div>
								</div>
							</div>
							<div class="form-actions fluid">
								<s:if test='%{#session.previousYear == "N"}'>
									<div class="row">
										<div class="col-md-6">
											<div class="col-md-offset-6 col-md-9">
												<sj:submit targets="mainContentDiv" value="Submit"
													formIds="importCustomerEnrollement" indicator="indicator"
													cssClass="submit small btn blue" validate="true" />
											</div>
										</div>
									</div>
								</s:if>
							</div>
						</div>
					</s:form>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
changePageTitle('Import Customer Enrollment Sheet');
</script>
