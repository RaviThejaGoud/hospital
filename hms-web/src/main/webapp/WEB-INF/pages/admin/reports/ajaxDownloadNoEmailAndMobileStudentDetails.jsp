<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Student without mobile number and email
				</div>
			</div>
			<div class="portlet-body" id="studRegister">
				<div class="tab-content">
					<s:form id="downloadTemplate"
						action="ajaxDownloadSelectedNoEmailAndMobileStudentDetails"
						enctype="multipart/form-data" method="post" theme="simple"
						cssClass="form-horizontal" namespace="/student"
						onsubmit="javascript:return generateClassIds();">
						<%@ include file="/common/messages.jsp"%>
						<h4 class="pageTitle bold">Download Students Details</h4>
						<s:hidden id="classNameIds" name="selectedId" />
						<s:hidden id="classNames" name="tempString" />
						<s:hidden id="selectedColumnNames" name="anyId" />
						<div class="panel-body col-md-12">
									<div class="col-md-1">
										<span class="label label-danger"> NOTE : </span>
									</div>
									<div class="col-md-10">
										<ul>
											<li>Please select the below options, and select the respective class & section by which you want to add information.</li>
											<li>Click "Download" button to download the template.</li>
											<li>The downloaded template contains the students information with respect to your selection.</li>
										</ul>
									</div>
								</div>
						<div class="form-body">
							<div class="form-group">
								<div class="col-md-12">
									<div class="checkbox-list">
										<label class="checkbox-inline"> <input type="checkbox"
											name="" value="" onClick="allStudentFileds()"
											class="checkbox studentFileds"> Select all
										</label>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-4">
									<input type="checkbox" value="mobileNumber"
										name="columnNameChkBoxSelectedIds" /> Mobile Number (Primary)
								</div>
								<div class="col-md-4">
									<input type="checkbox" value="secondaryMobileNumber"
										name="columnNameChkBoxSelectedIds" /> Mobile Number (Secondary)
								</div>
								<div class="col-md-4">
									<input type="checkbox" value="studentMobile"
										name="columnNameChkBoxSelectedIds" /> Student Mobile Number
								</div>
								<div class="col-md-4">
									<input type="checkbox" value="parentEmail"
										name="columnNameChkBoxSelectedIds" /> Parent Email
								</div>
								<div class="col-md-4">
									<input type="checkbox" value="studentEmail"
										name="columnNameChkBoxSelectedIds" /> Student Email
								</div>
							</div>
							<div class="spaceDiv"></div>
							<s:if
								test="%{studyClassList != null && !studyClassList.isEmpty()}">
								<div class="form-group">
									<div class="col-md-12">
										<div class="checkbox-list">
											<label class="checkbox-inline"> <input
												type="checkbox" name="" value="" onClick="checkAllClasses()"
												class="checkbox allClasses"> All Class & Sections
											</label>
										</div>
									</div>
								</div>

								<div class="form-group">
									<label class="conLable col-md-3 control-label"> Select class
										and section : </label>
									<div class="col-md-12">
										<div class="checkbox-list">
											<s:checkboxlist name="chkBoxSelectedIds"
												list="studyClassList" listKey="id"
												listValue="classAndSection" theme="ems" cssClass="small" />
										</div>
									</div>
								</div>
							</s:if>
							<s:else>
								<div class="alert alert-info">Currently there are no class
									with students.</div>
							</s:else>
							<div class="form-actions fluid">
								<div class="col-md-offset-3 col-md-9">
									<s:submit type="submit small" value="Download Excel"
										cssClass="submitBt btn blue long"
										title="Downlaod excel report" />
								</div>
							</div>
						</div>
					</s:form>
					<div class="form-body">
						<s:if test='%{#session.previousYear == "N"}'>
							<s:form action="ajaxUploadNoEmailAnaMobileStudentData"
								id="importStudentExcelSheet" cssClass="form-horizontal"
								namespace="/student" method="post" theme="simple"
								enctype="multipart/form-data" name="">
								<s:hidden name="tempString" value="Edit"></s:hidden>
								<h4 class="bold pageTitle">Upload students</h4>
								<div class="panel-body col-md-12">
									<div class="col-md-1">
										<span class="label label-danger"> NOTE : </span>
									</div>
									<div class="col-md-10">
										<ul>
											<li>The upload excel sheet must be downloaded from the
												above "Download" option.</li>
											<li>The system will not able to process the upload
												successfully <font color="red">if any columns are
													changed or inserted</font> in the template.
											</li>
										</ul>
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-md-4"> <span
										class="required">*</span>Select template (Excel) :
									</label>
									<div class="col-md-4">
										<div class="fileupload fileupload-new"
											data-provides="fileupload">
											<div class="input-append">
												<span class="btn default"> <s:file name="upload"
														type="file" value="" id="photoURL" cssClass="required">
													</s:file>
												</span>
											</div>
										</div>
									</div>
								</div>
								<div class="form-actions fluid">
									<div class="col-md-offset-4 col-md-9">
										<sj:submit targets="mainContentDiv" value="Upload template"
											indicator="indicator" cssClass="btn blue long"
											formIds="importStudentExcelSheet" validate="true" />
									</div>
								</div>
							</s:form>
						</s:if>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	$(document).ready(function() {
		$("input:checkbox, input:radio").uniform();
		changePageTitle("Student without mobile number and email");
	});
	function generateClassIds() {
		if (($("input[name=chkBoxSelectedIds]:checked").length == 0)
				&& ($("input[name=columnNameChkBoxSelectedIds]:checked").length == 0)) {
			alert("Please select at least one column names and one class name.");
			return false;
		} else if (($("input[name=chkBoxSelectedIds]:checked").length > 0) && ($("input[name=columnNameChkBoxSelectedIds]:checked").length == 0)) {
			alert("Please select at least one column name .");
			return false;
		} else if (($("input[name=chkBoxSelectedIds]:checked").length == 0) && ($("input[name=columnNameChkBoxSelectedIds]:checked").length > 0)) {
			alert("Please select at least one class name.");
			return false;
		} else {
			var classIds = $("input[name=chkBoxSelectedIds]:checked");
			var columnNameIds = $("input[name=columnNameChkBoxSelectedIds]:checked");
			var selectedClassIds = '';
			var selectedClassNames = '';
			var selectedColumnNameIds = [];
			if (classIds.length > 0) {
				selectedClassIds = '(';
				$(classIds).each(
						function() {
							selectedClassIds += $(this).val() + ', ';
							if (isNonEmpty($(this).parents('label').text()))
								selectedClassNames += $(this).parents('label').text().replace(/^\s+|\s+$/g, ' ');
						});
				selectedClassIds += '0)';
			}
			if (columnNameIds.length > 0) {
				$(columnNameIds).each(function() {
					if (isNonEmpty($(this).val()))
						selectedColumnNameIds.push($(this).val().trim());
				});
			}
			$("#classNameIds").val(selectedClassIds);
			$("#classNames").val(selectedClassNames);
			$("#selectedColumnNames").val(selectedColumnNameIds);
		}
	}
	function checkAllClasses() {
		if ($(".allClasses").is(':checked')) {
			$("[name='chkBoxSelectedIds']").each(function() {
				$(this).parent('span').addClass('checked');
				$(this).attr("checked", "true");
			});
		} else {
			$("[name='chkBoxSelectedIds']").each(function() {
				$(this).parent('span').removeClass('checked');
				$(this).removeAttr("checked");
			});
		}
	}
	$("input[name=chkBoxSelectedIds]").click(function() {
		if ($("input[name=chkBoxSelectedIds]:unchecked").length > 0) {
			$(".allClasses").parent('span').removeClass("checked");
			$(".allClasses").attr("checked", false);
		} else {
			$(".allClasses").parent('span').addClass("checked");
			$(".allClasses").attr("checked", true);
		}
	});
	function allStudentFileds() {
		if ($(".studentFileds").is(':checked')) {
			$("[name='columnNameChkBoxSelectedIds']").each(function() {
				$(this).parent('span').addClass('checked');
				$(this).attr("checked", "true");
			});
		} else {
			$("[name='columnNameChkBoxSelectedIds']").each(function() {
				$(this).parent('span').removeClass('checked');
				$(this).removeAttr("checked");
			});
		}
	}
	$("input[name=columnNameChkBoxSelectedIds]").click(function() {
		if ($("input[name=columnNameChkBoxSelectedIds]:unchecked").length > 0) {
			$(".studentFileds").parent('span').removeClass("checked");
			$(".studentFileds").attr("checked", false);
		} else {
			$(".studentFileds").parent('span').addClass("checked");
			$(".studentFileds").attr("checked", true);
		}
	});
</script>