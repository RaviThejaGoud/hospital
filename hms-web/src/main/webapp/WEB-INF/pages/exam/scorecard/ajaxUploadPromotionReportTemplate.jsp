<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp" />
<s:if test="%{(classList != null && !classList.isEmpty())}">
	<s:form action="ajaxUploadPromotionReportTemplate" theme="simple"
		cssClass="form-horizontal" id="uploadPromotionReportTemplate"
		method="post" enctype="multipart/form-data" namespace="/exam">
		<s:hidden id="classNames" name="anyId" />
		<div class="form-body">
			<div>
				<jsp:include
					page="/WEB-INF/pages/admin/common/ajaxClassAndSectionsList.jsp"></jsp:include>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-6">
							<span class="required">*</span>Upload promotion report template
							(.xls or .xlsx) :
						</label>
						<div class="col-md-6">
							<input type='file' name="uploadList[0]"
								class="btn default required fileNames" />
						</div>
					</div>
				</div>
			</div>
			<div class="form-actions fluid">
				<div class="col-md-offset-3 col-md-6">
					<sj:submit   targets="mainContentDiv" value="Submit"
						formIds="uploadPromotionReportTemplate" indicator="indicator"
						cssClass="submit small btn blue" validate="true"
						onBeforeTopics="validatePromotionReportForm" />
				</div>
			</div>
		</div>
	</s:form>
	<br/>
	<s:if test="%{objectList != null && !objectList.isEmpty()}">
		<h4 class="bold pageTitle">
			Promotion Report templates
		</h4>
		<table
			class="table table-striped table-bordered table-hover table-full-width"
			id="sample_2">
			<thead>
				<tr>
					<th>
						Class name
					</th>
					<th>
						Promotion Report Template
					</th>
					<th>
						Delete
					</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="objectList">
					<tr>
						<td>
							<s:property value="className" />
						</td>
						<td>
							<s:property value="templateFileName" />
							&nbsp;
						</td>
						<td>
							<s:url id="removePromotionReport"
								action="ajaxDeletePromotionReportTemplate" includeParams="all"
								escapeAmp="false" namespace="/exam">
								<s:param name="tempId" value="%{id}"></s:param>
							</s:url>
							<s:div cssStyle="margin-top:3px;" cssClass="btn btn-xs red"
								id='%{removePromotionReport}' theme="simple"
								title="Delete this template"
								onclick="javascript:confirmDialogWithTarget(this,'promotionReportContDiv');">
								<i class="fa fa-times"></i>Delete</s:div>
						</td>
					</tr>
				</s:iterator>
			</tbody>
		</table>
	</s:if>
	<s:else>
		<div class="alert alert-info">
			You have not uploaded promotion report template. Please upload
			promotion report template.
		</div>
	</s:else>
</s:if>
<s:else>
	<div class="alert alert-info">
		Classes are not available.
	</div>
</s:else>
<script type="text/javascript">
$(document)
		.ready(
				function() {
					$.destroyTopic('validatePromotionReportForm');
					$
							.subscribe(
									'validatePromotionReportForm',
									function(event, data) {
										if (!isNonEmpty($("input.fileNames")
												.val())) {
											//alert("Please browse your file to upload.");
											event.originalEvent.options.submit = false;
										} else if ($("input.fileNames").val()
												.lastIndexOf(".xls") > -1
												|| $("input.fileNames").val()
														.lastIndexOf(".xlsx") > -1) {
											if (prepareSelectedClassNames() == false)
												event.originalEvent.options.submit = false;
											else
												return prepareSelectedClassNames();
										} else {
											alert("File not acceped. Please upload your file in .xls, .xslx");
											event.originalEvent.options.submit = false;
										}

									});
				});

function prepareSelectedClassNames() {
	if ($("input[name=chkBoxSelectedIds]:checked").length > 0) {
		var classIds = $("input[name=chkBoxSelectedIds]:checked");
		var selectedClassNames = [];
		if (classIds.length > 0) {
			$(classIds).each(function(){
				if (isNonEmpty($(this).val())
						&& isNonEmpty($(this).parents('label').text())) {
					selectedClassNames.push($(this).parents('label').text().trim())
				}
			});
		}
		$("#classNames").val(selectedClassNames);
		return true;
	} else {
		alert("Please select at least one class.");
		return false;
	}
}

function generatePromotionReport() {
	$('a#promotionReportNav').click();
}
</script>
<!--<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Upload Promotion Report Template
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
					<ul class="nav nav-tabs">
						<li class="active">
							<a href="#" onclick="javascript:generatePromotionReport()">Back
								To Promotion Report Generation</a>
						</li>
					</ul>
					<div class="tab-content" id="uploadPromotionReportCont">
						<jsp:include page="/common/messages.jsp" />
						<s:if test="%{(classList != null && !classList.isEmpty())}">
							<s:form action="ajaxUploadPromotionReportTemplate" theme="simple"
								cssClass="form-horizontal" id="uploadPromotionReportTemplate"
								method="post" enctype="multipart/form-data">
								<s:hidden id="classNames" name="anyId" />
								<div class="form-body">
										<div>
											<jsp:include
												page="/WEB-INF/pages/admin/common/ajaxClassAndSectionsList.jsp"></jsp:include>
										</div>
									<div class="row">
										<div class="col-md-8">
											<div class="form-group">
												<label class="control-label col-md-4">
													<span class="required">*</span>Upload promotion report
													template (.xls or .xlsx) :
												</label>
												<div class="col-md-5">
													<input type='file' name="uploadList[0]"
														class="btn default required fileNames" />
												</div>
											</div>
										</div>
									</div>
									<div class="form-actions fluid">
										<div class="col-md-6">
											<div class="col-md-offset-4 col-md-9">
												<sj:submit   targets="mainContentDiv" value="Submit"
													formIds="uploadPromotionReportTemplate"
													indicator="indicator" cssClass="submit small btn blue"
													validate="true"
													onBeforeTopics="validatePromotionReportForm" />
											</div>
										</div>
									</div>
								</div>
							</s:form>
							<s:if test="%{objectList != null && !objectList.isEmpty()}">
								<h4 class="bold pageTitle">
									Promotion Report templates
								</h4>
								<table
									class="table table-striped table-bordered table-hover table-full-width"
									id="sample_2">
									<thead>
										<tr>
											<th>
												Class name
											</th>
											<th>
												Promotion Report Template
											</th>
											<th>
												Delete
											</th>
										</tr>
									</thead>
									<tbody>
										<s:iterator value="objectList">
											<tr>
												<td>
													<s:property value="className" />
												</td>
												<td>
													<s:property value="templateFileName" />
													&nbsp;
												</td>
												<td>
													<s:url id="removePromotionReport"
														action="ajaxDeletePromotionReportTemplate"
														includeParams="all" escapeAmp="false">
														<s:param name="tempId" value="%{id}"></s:param>
													</s:url>
													<s:div cssStyle="margin-top:3px;" cssClass="btn btn-xs red"
														id='%{removePromotionReport}' theme="simple"
														title="Delete this template"
														onclick="javascript:confirmDialogWithTarget(this,'mainContentDiv');"><i class="fa fa-times"></i>Delete</s:div>
												</td>
											</tr>
										</s:iterator>
									</tbody>
								</table>
							</s:if>
							<s:else>
								<div class="alert alert-info">
									You have not uploaded promotion report template. Please upload
									promotion report template.
								</div>
							</s:else>
						</s:if>
						<s:else>
							<div class="alert alert-info">
								Classes are not available.
							</div>
						</s:else>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
$(document)
		.ready(
				function() {
					$
							.subscribe(
									'validatePromotionReportForm',
									function(event, data) {
										if (!isNonEmpty($("input.fileNames")
												.val())) {
											//alert("Please browse your file to upload.");
											event.originalEvent.options.submit = false;
										} else if ($("input.fileNames").val()
												.lastIndexOf(".xls") > -1
												|| $("input.fileNames").val()
														.lastIndexOf(".xlsx") > -1) {
											if (prepareSelectedClassNames() == false)
												event.originalEvent.options.submit = false;
											else
												return prepareSelectedClassNames();
										} else {
											alert("File not acceped. Please upload your file in .xls, .xslx");
											event.originalEvent.options.submit = false;
										}

									});
				});

function prepareSelectedClassNames() {
	if ($("input[name=chkBoxSelectedIds]:checked").length > 0) {
		var classIds = $("input[name=chkBoxSelectedIds]:checked");
		var selectedClassNames = [];
		if (classIds.length > 0) {
			$(classIds).each(function(){
				if (isNonEmpty($(this).val())
						&& isNonEmpty($(this).parents('label').text())) {
					selectedClassNames.push($(this).parents('label').text().trim())
				}
			});
		}
		$("#classNames").val(selectedClassNames);
		return true;
	} else {
		alert("Please select at least one class.");
		return false;
	}
}

function generatePromotionReport() {
	$('a#promotionReportNav').click();
}
</script>-->