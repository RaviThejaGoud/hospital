<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/messages.jsp"%>
<s:if test='%{#session.previousYear == "N"}'>
		<!--<jsp:include page="/WEB-INF/pages/exam/admin/ajaxDownloadMarkSheetTemplate.jsp"></jsp:include> -->
<div class="form-body">
	<s:form action="ajaxUploadMarksSheetForStaff" id="uploadMarkSheet" namespace="/exam" method="post" theme="simple" enctype="multipart/form-data" cssClass="form-horizontal">
		<h4 class="pageTitle bold">
			  Upload Students Marks Sheet
		</h4>
		<span class="label label-danger">NOTE :</span>
		<div class="panel-body">
			<ul>
				<li>
					Simple..! just click (Browse button) then upload Marks Sheet template excel sheet and hit (Upload marks sheet) button.
				</li>
				<li>
					System cannot process any records, if the data is not in specified
					format.
				</li>
			</ul>
		</div><div class="form-group">
			<label class="control-label col-md-2">
				<span class="required">*</span>Upload an Excel Sheet :
			</label>
			<div class="col-md-4">
				<div class="fileupload fileupload-new" data-provides="fileupload">
					<div class="input-append">
						<span class="btn default"> <s:file name="upload" type="file"  value=""
								id="photoURL" cssClass="required">
							</s:file> </span>
					</div>
				</div>
			</div>
		</div>
		<div class="form-actions fluid">
			<div class="col-md-offset-2 col-md-9">
				<sj:submit   targets="classContentDiv" value="Upload Marks Sheet" formIds="uploadMarkSheet"
						indicator="indicator" cssClass="submitBt btn blue" validate="true" />
						<s:url id="urlImportAndDownloadMarksSheet" action="ajaxStaffExamSchedules" includeParams="all" escapeAmp="false" namespace="/exam">
				</s:url>
				<sj:a href="%{urlImportAndDownloadMarksSheet}" cssClass="btn default"  targets="mainContentDiv">Cancel</sj:a>
			</div>
		</div>
	</s:form>
</div>
</s:if>
<s:else>
<div class="alert alert-info">
	You cann't import or download old academic year marks.
</div>
</s:else>
