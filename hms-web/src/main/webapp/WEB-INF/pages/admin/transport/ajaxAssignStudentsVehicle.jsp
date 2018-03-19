<%@ include file="/common/taglibs.jsp"%>
<s:if test="%{((studyClassList != null || !studyClassList.isEmpty()) || (tempList2 != null || !tempList2.isEmpty()))}">
	<s:form id="downloadTemplate" action="ajaxDownloadStudentsForTransport"
		enctype="multipart/form-data" method="post" theme="simple"
		cssClass="form-horizontal" namespace="/admin"
		onsubmit="javascript:return generateClassIds();">
		<div class="form-body">
			<jsp:include page="/common/messages.jsp"></jsp:include>
			<s:hidden id="classNameIds" name="selectedId" />
			<s:hidden id="classNames" name="tempString" />
			<h4>Download students details sheet to assign students to vehicles </h4>
				<div class="panel-body col-md-12">
					<div class="col-md-1">
						<span class="label label-danger"> NOTE : </span>
					</div>
					<div class="col-md-10">
						<ul>
							<li>
								Please select class and download the students excel sheet by clicking the
								"Download Students" button below.
							</li>
							<li>
								This excel sheet has marked with set of column names that are
								supported by the system.
							</li>
							<li>
								<font color="red">Please do not add new columns or delete
									the marked columns</font>. If you want add more columns, please
								contact EazySchool support team(support@eazyschool.com).
							</li>
						</ul>
					</div>
				</div>
			<div class="checkbox-list">
				<div class="form-group">
					<label class="control-label">
						<span class="required">*</span> Available Classes :
					</label>
				</div>
				<div class="form-group">
					<h4>
						Class With Students:
					</h4>
					<div>
						<s:if test='%{studyClassList.size >1}'>
							<input type="checkbox" name="" value=""
								onClick="checkAllClasses()" class="checkbox allClasses">
							All Class & Sections
					</s:if>
						<%-- <s:else>
							<font color="#1AB7EA">ClassName:</font>
						</s:else> --%>
					</div>
					<s:checkboxlist name="chkBoxSelectedIds" list="studyClassList"
						listKey="id" listValue="classAndSection" theme="ems"
						cssClass="form-control small" />
					<s:if test='%{tempList2.size >0}'>
						<h4>
							Class With Out Students:
						</h4>
						<s:checkboxlist name="chkBoxNotSelectedIds" list="tempList2"
							disabled="true" listKey="id" listValue="classAndSection"
							theme="ems" cssClass="form-control small" />
					</s:if>
				</div>
			</div>
			 
			<div id="templateExamTypesContent">
			</div>
			<div class="form-actions fluid">
				<s:if test='%{#session.previousYear == "N"}'>
					<div class="col-md-6">
						<div class="col-md-offset-5 col-md-9">
							<s:submit value="Download Students" cssClass="submit long btn blue" />
						</div>
					</div>
				</s:if>
			</div>
		</div>
	</s:form>
</s:if>
<s:else>
	<div class="alert alert-info">
		No classes found for assigning students to vehicles.
	</div>
</s:else>
<div class="spaceDiv"></div>
<div class="spaceDiv"></div>
<s:form action="ajaxUploadStudentTransportData"
	id="importStudentExcelSheet" namespace="/admin" method="post"
	theme="simple" cssClass="form-horizontal" enctype="multipart/form-data" name="">
	<h4>Upload students details sheet to assign students to vehicles </h4>
	<div class="form-body">
		<div class="panel-body col-md-12">
			<div class="col-md-1">
				<span class="label label-danger"> NOTE : </span>
			</div>
			<div class="col-md-10">
				<ul>
					<li>
						The import excel sheet must be downloaded from the above "Download
						Students" option.
					</li>
					<li>
						Please ensure that required columns are filled with necessary data.
					</li>
					<li>
						The system will not able to process the import successfully
						<font color="red">if any columns are changed or inserted</font> in
						the template.
					</li>
					<li>
						The system will generate the import status if any rows are not
						proceed due to data errors.
					</li>
				</ul>
			</div>
		</div>
		<div class="spaceDiv">
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-7">
						<span class="required">*</span>Select Students Import
						Template(Excel) :
					</label>
					<div class="col-md-5">
						<s:file name="upload" id="photoURL" onchange="validateExcelSheet(this);"
							cssClass="btn default required">
						</s:file>
					</div>
				</div>
			</div>
			</div>
			<div class="form-actions fluid">
				<s:if test='%{#session.previousYear == "N"}'>
				  <div class="col-md-6">
					 <div class="col-md-offset-5 col-md-9">
						<sj:submit   targets="transportVehicles" value="Submit"
							indicator="indicator" cssClass="submit small btn blue"
							formIds="importStudentExcelSheet" validate="true" />
					 </div>
				  </div>
				</s:if>
			</div>
		</div>
</s:form>
<script type="text/javascript">
changePageTitle('Download Student Details');
$(document).ready(function() {
	$("input:checkbox, input:radio").uniform();
});
function getMarksSubmitErrors() {
	var classSectionId = $("#classSection").val();
	if (isNonEmpty(classSectionId)) {
		$('#selectedClassName').val(
				$("select[id='classSection'] option:selected").text());
		return true;
	} else {
		alert("Please select class.");
		return false;
	}
}
/*
function checkAllClasses() {
	if ($(".allClasses").is(':checked'))
		$("input[name='chkBoxSelectedIds']").attr("checked", "true");
	else
		$("input[name='chkBoxSelectedIds']").removeAttr("checked");
}*/
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


/*
$("input[name=chkBoxSelectedIds]").click(function() {
	if ($("input[name=chkBoxSelectedIds]:unchecked").length > 0) {
		$(".allClasses").attr("checked", false);
	} else {
		$(".allClasses").attr("checked", true);
	}
});
*/
function generateClassIds() {
	if ($("input[name=chkBoxSelectedIds]:checked").length > 0) {
		var classIds = $("input[name=chkBoxSelectedIds]:checked");
		var selectedClassIds = '';
		var selectedClassNames = '';
		if (classIds.length > 0) {
			selectedClassIds = '(';
			$(classIds).each(function(){
				selectedClassIds += $(this).val() + ', ';
				if (isNonEmpty($(this).parents('label').text()))
					selectedClassNames += $(this).parents('label').text().replace(/^\s+|\s+$/g, ' ');
			});
			selectedClassIds += '0)';
		}
		$("#classNameIds").val(selectedClassIds);
		$("#classNames").val(selectedClassNames);
		return true;
	} else if ($("input[name=chkBoxSelectedIds]:checked").length == 0) {
		alert("Please select at least one Class");
		return false;
	} else {
		return false;
	}
}
</script>