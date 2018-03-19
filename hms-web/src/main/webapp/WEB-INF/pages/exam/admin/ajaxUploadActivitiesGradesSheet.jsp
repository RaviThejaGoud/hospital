<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp" />
<s:form action="ajaxUploadStudentsActivitiesMarksSheet"
	id="uploadMarkSheet" method="post" theme="simple"
	enctype="multipart/form-data" cssClass="form-horizontal" namespace="/exam">
	<div class="form-body">
		<h4 class="bold pageTitle">
			Upload activities sheet
		</h4>
		<div class="panel-body col-md-12">
			<div class="col-md-1">
				<span class="label label-danger"> NOTE : </span>
			</div>
			<div class="col-md-10">
				<ul>
					<li>
						The upload activities sheet must be downloaded from the "Download excel
						Sheet" option.
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
		<div class="row">
			<div class="col-md-12">
				<label class="control-label col-md-3">
					<span class="required">*</span>Upload activities sheet (.xls) :
				</label>
				<div class="col-md-9">
					<s:file name="upload" id="photoURL" cssClass="btn default required">
					</s:file>
				</div>
			</div>
		</div>
		&nbsp;
		<div class="spaceDiv"></div>
		<div class="form-actions fluid">
			<div class="col-md-offset-3 col-md-9">
				<s:if test='%{#session.previousYear == "N"}'>
				<sj:submit   targets="studentsActivitiesContent" value="Submit"
						indicator="indicator" cssClass="submit small btn blue"
						validate="true" />
				</s:if>
				<s:url id="urlViewActivitiesLink" action="ajaxDownloadActivitiesTemplate"
					includeParams="all" escapeAmp="false" namespace="/exam">
				</s:url>
				<sj:a href="%{urlViewActivitiesLink}" cssClass="btn default"
					onCompleteTopics="highlight" targets="studentsActivitiesContent">Cancel</sj:a>
				<s:else>
					<sj:submit   targets="studentActMarksContent" value="Submit"
						disabled="true" indicator="indicator"
						cssClass="submit small btn blue" validate="true" />
				</s:else>
			</div>
		</div>
	</div>
</s:form>
