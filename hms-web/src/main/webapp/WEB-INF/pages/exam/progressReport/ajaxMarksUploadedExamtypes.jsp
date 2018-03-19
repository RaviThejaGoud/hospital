<%@ include file="/common/taglibs.jsp"%>
<div class="checkbox-list">
	<s:if test="%{examTypeList != null && !examTypeList.isEmpty()}">
		<div class="form-group">
		<div class="checkbox">
			<label class="col-md-3 control-label">
				Select All Exam Types :
			</label>
			<lable class="checkbox">
				<input type="checkbox" name="" value=""
					onClick="checkAllClassesForReports()" class="checkbox allClasses">
			</lable>
		</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">
				<span class="required">*</span> Select Exam Types :
			</label>
			<div class="col-md-9">
				<div class="checkbox-list">
				<lable class="checkbox">
					<s:checkboxlist list="examTypeList" listKey="id"
						listValue="examType" name="chkBoxSelectedIds" cssClass="required"
						theme="ems" />
					</lable>
				</div>
			</div>
		</div>
	</s:if>
	<s:else>
		<div class="alert alert-info">
			 Please upload the marks for selected
				class.
		</div>
	</s:else>
</div>
<script type="text/javascript">
$(document).ready(function() {
	$("input:checkbox, input:radio").uniform();

	var examsList = '<s:property value="examTypeList.size"/>';
		if(isNonEmpty(examsList)){
			$("div#ExamingDates").show();
		}else{
			$("div#ExamingDates").hide();
		}

});
$("input[name=chkBoxSelectedIds]").click(function() {
	if ($("input[name=chkBoxSelectedIds]:unchecked").length > 0) {
		//$(".allClasses").removeAttr("checked");
	    $(".allClasses").parent('span').removeClass('checked');
		$(".allClasses").attr("checked", false);
	} else {
		$(".allClasses").parent('span').addClass('checked');
		$(".allClasses").attr("checked", true);
	}
});
function checkAllClassesForReports() {
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
/*function checkAllClassesForReports() {
	if ($(".allClasses").is(':checked'))
		$("input[name='chkBoxSelectedIds']").attr("checked", "true");
	else
		$("input[name='chkBoxSelectedIds']").removeAttr("checked");
}*/
</script>