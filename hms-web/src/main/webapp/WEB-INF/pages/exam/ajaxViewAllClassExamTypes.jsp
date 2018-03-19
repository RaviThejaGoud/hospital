<%@ include file="/common/taglibs.jsp"%>
<s:if test="%{examTypeList != null && !examTypeList.isEmpty()}">
<s:if test='%{plTitle == "Class Wise Marks"}'>
		<div class="form-group">
			<label class="conLable col-md-11 control-label"> Select Exam Type : </label>
			<div class="col-md-11">
				<div class="checkbox-list">
					<label class="checkbox-inline"> <input type="checkbox"
						name="" value="" onClick="checkAllExamTypes()"
						class="checkbox allExamTypes"> All ExamTypes
					</label>
				</div>
			</div>
			<div class="col-md-11">
				<div class="checkbox-list">
					<s:checkboxlist name="chkBoxExamTypeIds" list="examTypeList"  onchange="javascript:getsubtypes()"
					listKey="id" listValue="examType" theme="ems" cssClass="small" />
				</div>
			</div>
		</div>
		<span class="label label-danger">NOTE :</span> If you want to calculate the student marks for percentage wise please give the percentage value for the respected subtypes. It will be applicable for the respected subtypes.
		<div>&nbsp;</div>
		
		<div id="displaySubtypesList"></div>
	</s:if>
	<s:else>
		<div class="form-group">
			<label class="control-label col-md-4">
				<span class="required">*</span>Select Exam Type :
			</label>
			<div class="col-md-6">
				<s:select list="examTypeList" listKey="id" listValue="examType"
					onchange="javascript:changeExamType(this.value);"
					cssClass="form-control input-medium required" name="examType"
					id="examTypeListId" theme="simple">
				</s:select>
			</div>
		</div>
		<div id="diplaySelectedTypeByExamType"></div>
	</s:else>
</s:if>
<s:else>
	<div id="noExamTypesDiv" class="alert alert-info"> You do not have any active
		Exam Types. Please create <b>Exam Type</b> </div>
</s:else>
<script type="text/javascript">
$(document).ready(function() {
	var examTypesId = $("#examTypeListId").val();
	if(isNonEmpty(examTypesId))
		changeExamType(examTypesId);
});
$("input[name=chkBoxExamTypeIds]").click(function() {
	if ($("input[name=chkBoxExamTypeIds]:unchecked").length > 0) {
	   $(".allExamTypes").parent('span').removeClass("checked");
		$(".allExamTypes").attr("checked", false);
	} else {
	    $(".allExamTypes").parent('span').addClass("checked");
		$(".allExamTypes").attr("checked", true);
	}
});
function checkAllExamTypes() {
	if ($(".allExamTypes").is(':checked')){
	    $("[name='chkBoxExamTypeIds']").each(function(){
		   $(this).parent('span').addClass('checked');
		    $(this).attr("checked", "true");
		});
	    getsubtypes();
	}
	else{
	 $("[name='chkBoxExamTypeIds']").each(function(){
		   $(this).parent('span').removeClass('checked');
		    $(this).removeAttr("checked");
		    $('#displaySubtypesList').hide();
		});
	}	
}
function getsubtypes() {
	var examTypes=[] ;
	if (($("input[name=chkBoxExamTypeIds]:checked").length) > 0){
		$("input:checkbox[name=chkBoxExamTypeIds]:checked").each(function()
		{
			examTypes.push($(this).val());
		});
	}else{
		$('#displaySubtypesList').hide();
	}
	var studyClassId = '<s:property value="classId "/>';
	if(isNonEmpty(examTypes)){
	$('#displaySubtypesList').html('<div align="center" style="padding:30px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	var url = jQuery.url.getChatURL("/exam/ajaxGetSubTypeByExamTypeId.do");
	var pars = "chkBoxSelectedAccountIds="+examTypes+"&studyClassId="+studyClassId;
	//alert("cvs"+pars);
	$.ajax( {
		type : "POST",
		url : url,
		data : pars,
		cache : false,
		success : function(html) {
			$("#displaySubtypesList").html(html);
			$("#displaySubtypesList").show();
		}
	});
  }
  else{
     alert('Please select atleat one examTypes.');
	 return false;
  }
}
function changeExamType(examTypesId) {
	var studyClassId = '<s:property value="classId "/>';
	if(isNonEmpty(examTypesId)){
	$('#diplaySelectedTypeByExamType').html('<div align="center" style="padding:30px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	var url = jQuery.url.getChatURL("/exam/ajaxExamTypeDetails.do");
	var pars = "tempId="+examTypesId+"&studyClassId="+studyClassId;
	$.ajax( {
		type : "POST",
		url : url,
		data : pars,
		cache : false,
		success : function(html) {
			$("#diplaySelectedTypeByExamType").html(html);
			$("#diplaySelectedTypeByExamType").show();
			if($("#diplaySelectedTypeByExamType").find("div.alert-info").hasClass("alert")){
				$("div#submitDiv").hide();
			}
			else{
				$("div#submitDiv").show();
			}
		}
	});
  }
}
</script>
