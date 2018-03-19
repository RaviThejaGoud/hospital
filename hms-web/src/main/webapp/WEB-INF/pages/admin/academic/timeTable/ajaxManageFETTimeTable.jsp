<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Timetable
				</div>
			</div>
			<div class="portlet-body" id="subjectsContentDiv">
			<%@ include file="/common/messages.jsp"%>
				<s:form action="ajaxDownLoadSheetForTimetable" theme="simple"
					namespace="/timeTable" id="classAndCommunity" method="post"
					cssClass="form-horizontal">
					<div class="form-body">
						<div class="row">
							<div class="col-md-5">
								<h4 class="pageTitle bold">
									Download timetable template sheet
								</h4>
							</div>
							<div align="center" style="margin: 0px 30px;" class="col-md-6">
								<s:url id="urlGenarateTimeTable" action="ajaxGenerateFetTimetable" includeParams="all" escapeAmp="false" namespace="/timeTable" />
								<sj:a id="generateTimeTable" href="%{urlGenarateTimeTable}" targets="mainContentDiv" cssClass="btn green">Send Request To  <br/>Generate Timetable</sj:a> 
							</div>
						</div>
						<div class="form-group">
							<div class="panel-body col-md-12">
								<div class="col-md-1">
									<span class="label label-danger"> NOTE : </span>
								</div>
								<div class="col-md-10">
									<ul>
										<li>
											Please click on "Download Timetable" button to download
											timetable template.
										</li>
										<li>
											Fill up the respective timetable data from all staff
											subjects.
										</li>
									</ul>
								</div>
							</div>
						</div>
						<%--<p>
							<span class="label label-danger">NOTE :</span>&nbsp;&nbsp;
							Please Download Timetable sheet and enter the respective
							Timetable data,if the data is not in the specified
							format,System can't process any records.
						</p>
						--%>
						<div class="form-actions fluid">
							<s:if test='%{#session.previousYear == "N"}'>
								<div class="col-md-offset-4 col-md-4">
									<s:submit type="submit small" value="Download Timetable"
										cssClass="submitBt btn blue" title="generate report"
										cssStyle="cursor:pointer">
									</s:submit>
								</div>
							</s:if>
						</div>
					</div>
				</s:form>
				<s:form id="importTimetableExcelSheet"
					action="ajaxUploadTimetableData" namespace="/timeTable"
					method="post" theme="simple" cssClass="form-horizontal"
					onsubmit="javascript:return getSubmitErrors();"
					enctype="multipart/form-data">
					<s:hidden name="anyId" value="supportTeam"/>
					<div class="form-body">
						<h4 class="pageTitle bold">
							Upload timetable details sheet
						</h4>
						<div class="panel-body col-md-12">
							<div class="col-md-1">
								<span class="label label-danger"> NOTE : </span>
							</div>
							<div class="col-md-10">
								<ul>
									<li>
										Click on browse button and select the template.
									</li>
									<li>
										Click on submit button to upload the template into the
										system.
									</li>
									<li>
										System cannot process any data, If the data is not in
										specified  format in the template.
									</li>
								</ul>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-4">
								<span class="required">*</span>Upload Timetable
								Template(Excel) :
							</label>
							<div class="col-md-4">
								<div class="fileupload fileupload-new"
									data-provides="fileupload">
									<div class="input-append">
										<span class="btn default"> <s:file name="upload"
												id="photoURL" cssClass="required">
											</s:file> </span>
									</div>
								</div>
							</div>
						</div>
						<div class="form-actions fluid">
							<s:if test='%{#session.previousYear == "N"}'>
								<div class="col-md-offset-4 col-md-5">
									<s:submit cssClass="submitBt btn blue" />
								</div>
							</s:if>
						</div>
					</div>
				</s:form>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
function getSubmitErrors() {
	if (isNonEmpty($('#photoURL').val())) {
		return true;
	} else {
		alert("Please select template file.");
		return false;
	}

}
</script>