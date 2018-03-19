<%@ include file="/common/taglibs.jsp"%>
<div id="uploadLibraryBooks">
	<jsp:include page="/common/messages.jsp"></jsp:include>
	<s:form id="importSchoolBooks" action="ajaxUploadLibraryBooksTemplate" enctype="multipart/form-data"
		method="post" theme="simple" cssClass="form-horizontal" namespace="/library">
		<s:hidden name="anyId" id="selectedType" value="AG"></s:hidden>
		<s:hidden name="anyTitle" id="selectedType" value="template"></s:hidden>
		<s:hidden name="alertSendType" id="uploadType" value="edit"></s:hidden>
		<h4 class="pageTitle bold">
			Download book details
		</h4>
		<div class="form-body">
			<div class="panel-body col-md-12">
				<div class="col-md-1">
					<span class="label label-danger"> NOTE : </span>
				</div>
				<div class="col-md-10">
					<ul>
						<li>
							Please download the template by clicking the "Download Template" button below. 
						</li>
						<li>
							This excel sheet has marked with set of column names that are supported by the system.
						</li>
						<li>	 
							<font color="red">Please do not add new columns or delete the marked columns</font>. If you want add more columns, please contact EazySchool support team(support@eazyschool.com). 
						</li>
					</ul>
				</div>
			</div>
			<div>
				&nbsp;
			</div>
			
			<div class="form-actions fluid">
				<div class="col-md-offset-2 col-md-9">
				
								<a class="btn blue"
								href="${pageContext.request.contextPath}/library/ajaxDownloadTemplateBooks.do?anyId='AG'&anyTitle=data"
								id="libraryDownloadLink">Download Template</a>
							
					<!--<a class="btn blue"
						href="${pageContext.request.contextPath}/library/ajaxDownloadTemplateBooks.do?anyId='AG'&anyTitle=template"
						id="libraryDownloadLink">Download Excel</a>
				--></div>
			</div>
		</div>
		<div class="form-body">
			<h4 class="pageTitle bold">
				Upload book details
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
				</ul>
			</div>
		</div>
			<div class="form-group">
				<label class="control-label col-md-4">
					<span class="required">*</span>Select  Books Import
					Template(Excel) :
				</label>
				<div class="col-md-0">
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
				<div class="col-md-offset-3 col-md-9">
					<sj:submit   targets="uploadLibraryBooks" value="Upload Books"
						formIds="importSchoolBooks" cssClass="submitBt btn blue"
						validate="true" />
					<s:url id="doCancelBook" action="ajaxLibraryHome"
						includeParams="all" escapeAmp="false" namespace="/library">
					</s:url>
					<sj:a href="%{doCancelBook}" cssClass="btn default"
						  targets="mainContentDiv">Cancel</sj:a>
				</div>
			</div>
		</div>
	</s:form>
</div>
<script type="text/javascript">
$("input:checkbox, input:radio").uniform();
changePageTitle("Import Library Books");
$.subscribe('importLibraryBooks', function(event, data) {
	if ($('#importSchoolBooks').valid()) {
		//alert($('input[name="bookNumberGenerate"]:checked').val());
	}
});
$('input[name="bookNumberGenerate"]').click(
		function() {
			$('input#selectedType').val($(this).val());
			var hrefVal = $('a#libraryDownloadLink').attr('href').split("?");
			hrefVal[1] = $('input[name="bookNumberGenerate"]:checked').val();
			$('a#libraryDownloadLink').attr('href',
					hrefVal[0] + "?" + "anyId=" + hrefVal[1]);
		});
</script>