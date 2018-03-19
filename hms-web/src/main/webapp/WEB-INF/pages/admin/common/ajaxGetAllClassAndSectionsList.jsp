<%@ include file="/common/taglibs.jsp"%>
	<s:if test="%{(studyClassList != null && !studyClassList.isEmpty())}">
	<div class="row">
	<div class="form-group">
		<label class="col-md-3 control-label">
				<span class="required">*</span> All Class & Sections :
			</label>
		<div class="col-md-7">
			<div class="checkbox-list">
				<label class="checkbox-inline">
					<input type="checkbox" name="" value=""
				onClick="checkAllClassesForReports()" class="allClasses" />
				</label>
			</div>
		</div>
		</div>
	</div>
	<div class="row">
		<div class="form-group">
			<label class="col-md-3 control-label">
				Classes :
			</label>
			<div class="col-md-9">
				<div class="checkbox-list">
					<s:checkboxlist name="chkBoxSelectedIds" list="studyClassList"
						listKey="id" listValue="classAndSection" theme="ems"></s:checkboxlist>
				</div>
			</div>
		</div>
	</div>
</s:if>
	<s:else>
	<div class="alert alert-info">
			Currently classes are not defined.
		</div>
	</s:else>
<script type="text/javascript">
$(document).ready(function(){
	$("input:checkbox, input:radio").uniform();
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
</script>