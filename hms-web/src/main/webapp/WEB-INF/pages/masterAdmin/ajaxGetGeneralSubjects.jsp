<%@ include file="/common/taglibs.jsp"%>
<s:if test="%{tempList2 != null && !tempList2.isEmpty()}">
<div class="">
	<div class="form-group">
		<label class="conLable col-md-3 control-label">
			<span class="required">*</span> Available Default Subjects :
		</label>
		<div class="col-md-12">
			<div class="checkbox-list">
				<label class="checkbox-inline">
						<input type="checkbox" name=""
								value="" onClick="checkAllSubjectTypeSyllabus()"
								class="checkbox allSubjectTypeSyllabus">
					All Default Subjects
				</label>
			</div>
			<s:checkboxlist list="tempList2" name="chkBoxClassSelectedIds" listKey="id" listValue="name" theme="ems" />
		</div>
	</div>
</div>
</s:if>
<s:else>
<!-- <div class="noteFont grid_12">
	<br />
	<div class="grid_1">
		<span class="noteMassage"> Note :</span>
	</div>
	<div class="grid_11">
		Currently there are no default subjects or if you need please select at least one subject type..
	</div>
</div> -->
</s:else>
<script type="text/javascript">
var selectedValues=[];
$("input:checkbox, input:radio").uniform();
$("input[name=chkBoxClassSelectedIds]").click(function() {
	if ($("input[name=chkBoxClassSelectedIds]:unchecked").length > 0) {
	   $(".allSubjectTypeSyllabus").parent('span').removeClass("checked");
		$(".allSubjectTypeSyllabus").attr("checked", false);
	} else {
	    $(".allSubjectTypeSyllabus").parent('span').addClass("checked");
		$(".allSubjectTypeSyllabus").attr("checked", true);
	}
	selectedValues=$("input[name=chkBoxClassSelectedIds]:checked").map(function () {return this.value;}).get().join(",");
	$('.anyId').val(selectedValues);
});
function checkAllSubjectTypeSyllabus() {
	if ($(".allSubjectTypeSyllabus").is(':checked')){
	    $("[name='chkBoxClassSelectedIds']").each(function(){
		   $(this).parent('span').addClass('checked');
		    $(this).attr("checked", "true");
		});
	}
	else{
	 $("[name='chkBoxClassSelectedIds']").each(function(){
		   $(this).parent('span').removeClass('checked');
		    $(this).removeAttr("checked");
		});
	}
	selectedValues=$("input[name=chkBoxClassSelectedIds]:checked").map(function () {return this.value;}).get().join(",");
	$('.anyId').val(selectedValues);
}
</script>
