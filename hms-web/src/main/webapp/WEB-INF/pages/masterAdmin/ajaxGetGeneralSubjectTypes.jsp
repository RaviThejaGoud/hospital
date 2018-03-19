<%@ include file="/common/taglibs.jsp"%>
<!-- <h4 class="pageTitle bold form-section">
	Create Default Subjects
</h4> -->
<s:if test="%{syllabusTypeList != null && !syllabusTypeList.isEmpty()}">
<div class="form-group">
	<label class="conLable col-md-3 control-label">
		<span class="required">*</span> Available Syllabus Types :
	</label>
	<div class="col-md-12">
		<div class="checkbox-list">
			<label class="checkbox-inline">
					<input type="checkbox" name=""
							value="" onClick="checkAllSubjectType()"
							class="checkbox allSubjectType">
				All Syllabus Types
			</label>
		</div>
		<s:checkboxlist name="chkBoxSelectedIds" list="syllabusTypeList" listKey="id" listValue="syllabusTypeName" theme="ems" />
			<!-- <span class="help-block">(Select above check boxes which already provided the default subject for current academicYear.)</span>	 -->	
		<%-- <s:checkboxlist list="tempList1" name="chkBoxSelectedIds" theme="ems" />
			 --%>
	</div>
</div>
</s:if>
<script type="text/javascript">
$(document).ready(function() {
	$( "#chkBoxSelectedIds-1" ).prop( "checked", true );
	var selectedVal=$("input[name=chkBoxSelectedIds]:checked").val();
	getSelectedSubjectTypeSyllabus(selectedVal);
});

var selectedValues=[];
$("input[name=chkBoxSelectedIds]").click(function() {
	if ($("input[name=chkBoxSelectedIds]:unchecked").length > 0) {
	   $(".allSubjectType").parent('span').removeClass("checked");
		$(".allSubjectType").attr("checked", false);
	} else {
	    $(".allSubjectType").parent('span').addClass("checked");
		$(".allSubjectType").attr("checked", true);
	}
	selectedValues=$("input[name=chkBoxSelectedIds]:checked").map(function () {return this.value;}).get().join(",");
	getSelectedSubjectTypeSyllabus(selectedValues);
	$('.anyTitle').val(selectedValues);
});
function checkAllSubjectType() {
	if ($(".allSubjectType").is(':checked')){
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
	selectedValues=$("input[name=chkBoxSelectedIds]:checked").map(function () {return this.value;}).get().join(",");
	getSelectedSubjectTypeSyllabus(selectedValues);
	$('.anyTitle').val(selectedValues);
}

function getSelectedSubjectTypeSyllabus(selectedValues) {
	var url = jQuery.url.getChatURL("/signup/ajaxGetGeneralSubjects.do");
	$("#displaySubjectTypes").html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	var pars = "anyTitle=" + selectedValues;
	$.ajax( {
	url : url,
	cache : false,
	data : pars,
	success : function(html) {
		$("#displaySubjectTypes").html(html);
		document.getElementById('displaySubjectTypes').style.display = "block";
	}
	});
}
</script>