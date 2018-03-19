<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp"></jsp:include>
<s:form action="ajaxUploadMarksSheet" id="uploadMarkSheet"
	cssClass="form-horizontal" method="post" theme="simple"
	enctype="multipart/form-data" namespace="/exam">
	<div class="form-body">
		<h4 class="bold pageTitle">
			Upload students marks sheet
		</h4>
		<div class="panel-body col-md-12">
			<div class="col-md-1">
				<span class="label label-danger"> NOTE : </span>
			</div>
			<div class="col-md-10">
				<ul>
					<li>
						The upload marks sheet must be downloaded from the "Download
						Marks Sheet" option.
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
						processed due to data errors.
					</li>
				</ul>
			</div>
		</div>
		<div class="row">
			<div class="col-md-9">
				<div class="form-group">
					<label class="control-label col-md-4">
						<span class="required">*</span>Upload marks sheet (.xls) :
					</label>
					<div class="col-md-4">

						<s:file name="upload" id="photoURL" onchange="validateExcelSheet(this);"
							cssClass="btn default required">
						</s:file>
					</div>
				</div>
			</div>
		</div>
		<div class="form-actions fluid">
			<div class="row">
				<div class="col-md-6">
					<div class="col-md-offset-6 col-md-9">
						<sj:submit   targets="urlStudentMarksSheetDiv" value="Submit" formIds="uploadMarkSheet"
							indicator="indicator" cssClass="submit small btn blue"
							validate="true"/>
					</div>
				</div>
			</div>
		</div>
	</div>
</s:form>
