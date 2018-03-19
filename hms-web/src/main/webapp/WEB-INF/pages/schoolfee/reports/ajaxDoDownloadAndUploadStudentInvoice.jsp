<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp"></jsp:include>
<s:form id="downloadTemplate" action="ajaxDownloadStudentsInvoiceExcel"
	cssClass="form-horizontal" enctype="multipart/form-data" method="post"
	theme="simple" namespace="/schoolfee">
	<h4 class="bold pageTitle">Download Students Payment Template</h4>
	<div class="form-body">
		<div class="form-group">
			<div class="panel-body col-md-12">
				<div class="col-md-1">
					<span class="label label-danger"> NOTE : </span>
				</div>
				<div class="col-md-10">
					<ul>
						<li>Please download template by clicking the "Download Template" button below. The downloaded excel template contains admission number,invoice number,payment date,term name,paid amount and discount amount details.You need to enter payment details in excel template.</li>
					</ul>
				</div>
			</div>
		</div>
		<div class="form-actions fluid">
			<div class="col-md-offset-2 col-md-9"
				style="float: right; width: 250px;">
				<s:submit value="Download Template" cssClass="btn blue long" />
			</div>
			<div class="col-md-offset-0 col-md-1">
				<s:url id="doAddCollectFeeBackLink" action="ajaxDoAdminSchoolFee"
					includeParams="all" escapeAmp="false" namespace="/schoolfee">
				</s:url>
				<sj:a href="%{doAddCollectFeeBackLink}" cssClass="btn default"
					targets="mainContentDiv" indicator="indicator">
				Back</sj:a>
			</div>

		</div>
	</div>


</s:form>
<div class="form-body">
	<s:if test='%{#session.previousYear == "N"}'>
		<s:form action="ajaxGenerateStudentsInvoiceData"
			id="generateStudentsInvoiceData" cssClass="form-horizontal"
			namespace="/schoolfee" method="post" theme="simple"
			enctype="multipart/form-data" name="">
			<s:hidden name="tempString" value="Edit"></s:hidden>
			<h4 class="bold pageTitle"> Import Students Payment Template</h4>
			<div class="panel-body col-md-12">
				<div class="col-md-1">
					<span class="label label-danger"> NOTE : </span>
				</div>
				<div class="col-md-10">
					<ul>
						<li>Admission number,invoice number,payment date,term name
							and paid amount are mandatory columns.</li>
						<li>Payment date format has to be (DD-MMM-YYYY) EX:- (01-Jul-16).</li>
						<li>If you did not enter any of the above columns system will not accept those records.</li>
					</ul>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-md-4"> <span
					class="required">*</span>Select students payment template :
				</label>
				<div class="col-md-4">
					<div class="fileupload fileupload-new" data-provides="fileupload">
						<div class="input-append">
							<span class="btn default"> <s:file name="upload" onchange="validateExcelSheet(this);"
									type="file" value="" id="photoURL" cssClass="required">
								</s:file>
							</span>
						</div>
					</div>
				</div>
			</div>
			<div class="form-actions fluid">
				<div class="col-md-offset-4 col-md-9">
					<sj:submit targets="searchStudentsForm112"
						value="Import Students Payment" indicator="indicator"
						cssClass="btn blue long" formIds="generateStudentsInvoiceData"
						validate="true" />
				</div>
			</div>
		</s:form>
	</s:if>
</div>
<script type="text/javascript">
	$(document).ready(function() {
		changePageTitle('Download / Upload Generate Student Invoice Details');
	});
	
</script>