<%@ include file="/common/taglibs.jsp"%>
<s:if test="%{(examTypeList != null && !examTypeList.isEmpty())}">
<div class="row">
	<div class="form-group">
		<label class="col-md-3 control-label">
			<span class="required">*</span>ExamTypes :
		</label>
		<div class="col-md-9">
			<div class="checkbox-list">
			<label class="checkbox-inline">
				<input type="checkbox" name="" value=""
						onClick="checkAllExamTypes()" class="checkbox allExamTypes">
					All Exam Types
				</label>
			</div>
			<s:checkboxlist name="examTypesChkBoxes" list="examTypeList"
				listKey="id" listValue="examType" theme="ems" cssClass="small" />
		</div>
	</div>
</div>
<s:if test="%{(studyClassList != null && !studyClassList.isEmpty())}">
	<div class="row">
		<div class="form-group">
			<label class="col-md-3 control-label">
				<span class="required">*</span>Classes :
			</label>
			<div class="col-md-9">
				<div class="checkbox-list">
				<label class="checkbox-inline">
					<input type="checkbox" name="" value=""
							onClick="checkAllClassesForReports()" class="allClasses" />
						All Classes
					</label>
				</div>
				<s:checkboxlist name="chkBoxSelectedIds" list="studyClassList"
					listKey="id" listValue="classAndSection" theme="ems"></s:checkboxlist>
			</div>
		</div>
	</div>
</s:if>
<s:else>
	<div class="alert alert-info">
		Currently classes are not defined.
	</div>
</s:else>
</s:if>
<s:else>
	<div class="alert alert-info">
		Currently examTypes are not defined.
	</div>
</s:else>
<script type="text/javascript">
$(document).ready(function(){
	$("input:checkbox, input:radio").uniform();
	//getStudentDetails($('#examTypeId').val())
});
$("input[name=chkBoxSelectedIds]").click(function() {
		if ($("input[name=chkBoxSelectedIds]:unchecked").length > 0) {
		    $(".allClasses").parent('span').removeClass("checked");
			$(".allClasses").attr("checked", false);
		} else {
			$(".allClasses").attr("checked", true);
			$(".allClasses").parent('span').addClass("checked");
		}
});
function checkAllClassesForReports() {
	if ($(".allClasses").is(':checked')){
	    $("[name='chkBoxSelectedIds']:not(':disabled')").each(function(){
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
$("input[name=examTypesChkBoxes]").click(function() {
	if ($("input[name=examTypesChkBoxes]:unchecked").length > 0) {
	   $(".allExamTypes").parent('span').removeClass("checked");
		$(".allExamTypes").attr("checked", false);
	} else {
	    $(".allExamTypes").parent('span').addClass("checked");
		$(".allExamTypes").attr("checked", true);
	}
});
function checkAllExamTypes() {
	if ($(".allExamTypes").is(':checked')){
	    $("[name='examTypesChkBoxes']").each(function(){
		   $(this).parent('span').addClass('checked');
		    $(this).attr("checked", "true");
		});
	}
	else{
	 $("[name='examTypesChkBoxes']").each(function(){
		   $(this).parent('span').removeClass('checked');
		    $(this).removeAttr("checked");
		});
	}	
}
/*function getStudentDetails(examTypeId) {
	if (isNonEmpty(examTypeId)) {
		var params = "examTypes.id=" + examTypeId;
		$('#scoreCardTemplatesListId').html(
						'<div align="center"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		$.ajax( {
			url : jQuery.url.getChatURL("/exam/ajaxScoreCardTemplatesList.do"),
			cache : true,
			data : params,
			success : function(response) {
				$('#scoreCardTemplatesListId').html(response);
			}
		});
	}
	else
	{
		$('#scoreCardTemplatesListId').html("");
	  alert("Please select exam type.");
	}
}*/

</script>