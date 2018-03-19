<%@ include file="/common/taglibs.jsp"%>
<div class="form-body">
	<s:form id="downloadTemplate" action="ajaxDownloadStudentsForHostel"
		enctype="multipart/form-data" method="post" theme="simple"
		cssClass="form-horizontal" namespace="/hostel"
		onsubmit="javascript:return generateClassIds();">
		<jsp:include page="/common/messages.jsp"></jsp:include>
		<s:hidden id="classNameIds" name="selectedId" />
		<s:hidden id="classNames" name="tempString" />
		<s:hidden id="floorName" name="tempId1" />
		<p>
			<span class="label label-danger"> NOTE : </span>&nbsp;If you do not
			have a student-details template (xls), please select class and
			download students sheet. System can't process any records, if the
			data is not in specified format.
		</p>
		<s:if test='%{studyClassList.size >1}'>
			<div class="form-group">
				<label class="col-md-2 control-label">Select all classes :</label>
				<div class="col-md-4">
					<div class="checkbox-list">
						<label class="checkbox-inline">
						<input type="checkbox" name="" value="" onClick="checkAllClasses()" class="checkbox allClasses"></label>
 					</div>
				</div>
			</div>
		</s:if>
		<%-- <s:else>
			<font color="#1AB7EA">ClassName :</font>
		</s:else> --%>
		<s:if test='%{studyClassList.size >0}'>
			<div class="form-group">
			<label class="control-label col-md-2">
				Class With Students :
			</label>
			<div class="col-md-9">
				<div class="checkbox-list">
					<s:checkboxlist name="chkBoxSelectedIds" list="studyClassList"
						listKey="id" listValue="classAndSection" theme="ems"
						cssClass="small" />
				</div>
			</div>
		</div>
		<s:if test='%{tempList2.size >0}'>
			<div class="form-group">
				<label class="control-label col-md-2">
					Class With Out Students :
				</label>
				<div class="col-md-9">
					<div class="checkbox-list">
						<s:checkboxlist name="chkBoxNotSelectedIds" list="tempList2"
							disabled="true" listKey="id" listValue="classAndSection"
							theme="ems" cssClass="small" />
					</div>
				</div>
			</div>
		</s:if>
		<div id="templateExamTypesContent">
		</div>
		<div class="form-actions fluid">
			<div class="col-md-offset-3 col-md-6">
			<s:submit value="Download Students Template" cssClass="submitBt btn blue" />
			<s:url id="urlAssignStudents" action="ajaxDoAssignStudent"
				namespace="/hostel" includeParams="all" escapeAmp="false">
				<s:param name="anyTitle">beds</s:param>
			</s:url>
			<sj:a   href="%{urlAssignStudents}"
				targets="mainContentDiv" cssClass="btn default">
								Cancel</sj:a>
			</div>
		</div>
		</s:if>
		<s:else>
			<div class="alert alert-info">
					Currently their are no hostelers present.
			</div>
		</s:else>
	</s:form>
</div>
<div class="form-body">&nbsp;</div>
<div class="form-body">
	<s:form action="ajaxUploadStudentHostelData"
		id="importStudentExcelSheet" namespace="/hostel" method="post"
		theme="simple" cssClass="form-horizontal"
		enctype="multipart/form-data">
		<p>
			<span class="label label-danger"> NOTE : </span>&nbsp; If you want to
			upload the student-details template (xls) then select (browse button)
			the student details template excel sheet.
		</p>
		<div class="form-group ">
			<label class="control-label col-md-3">
				<span class="required">*</span>Select Students Upload Template
				(Excel) :
			</label>
			<div class="col-md-5">
				<s:file name="upload" id="photoURL" cssClass="btn default required">
				</s:file>
			</div>
		</div>
		<div class="form-actions fluid">
			<div class="col-md-offset-3 col-md-6">
				<sj:submit   targets="mainContentDiv" value="Upload Students Template"
					cssClass="submitBt btn blue" formIds="importStudentExcelSheet"
					validate="true" />
			</div>
		</div>
	</s:form>
</div>
<script type="text/javascript">
changePageTitle('Download Student Details');
$(document).ready(function() {
$("input:checkbox, input:radio").uniform();
$("input[name=chkBoxSelectedIds]").click(function() {
				if ($("input[name=chkBoxSelectedIds]:unchecked").length > 0) {
				   $(".allClasses").parent('span').removeClass("checked");
					$(".allClasses").attr("checked", false);
				} else {
				    $(".allClasses").parent('span').addClass("checked");
					$(".allClasses").attr("checked", true);
				}
			});
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

function generateClassIds() {
	if ($("input[name=chkBoxSelectedIds]:checked").length > 0) {
		var floorName = $("select#floorId").val();
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
		$("#floorName").val(floorName);
		return true;
	} else if ($("input[name=chkBoxSelectedIds]:checked").length == 0) {
		alert("Please select at least one Class");
		return false;
	} else {
		return false;
	}
}
 function checkAllClasses() {
		if ($(".allClasses").is(':checked')){
		    $("[name='chkBoxSelectedIds']").each(function(){
			   $(this).parent('span').addClass('checked');
			    $(this).attr("checked", "true");
			});
		}
		else{
		 $("[name='chkBoxSelectedIds']").each(function(){
			   $(this).parent('span').removeClass('checked');
			    $(this).removeAttr("checked");
			});
		}	
	}
</script>