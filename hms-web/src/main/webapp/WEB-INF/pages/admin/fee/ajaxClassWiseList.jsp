<%@ include file="/common/taglibs.jsp"%>
<s:if test="%{classList != null && !classList.isEmpty()}">
<label class="control-label"> Available Classes </label>
	<div class="form-group">
		<label class="conLable col-md-3 control-label">
		</label>
		<div class="col-md-12">
			<div class="checkbox-list">
				<label class="checkbox-inline">
					<input type="checkbox" name="" value=""
						onClick="checkAllClassesForReports()" class="checkbox allClasses">
					All Classes
				</label>
			</div>
			<s:checkboxlist name="chkBoxSelectedIds" list="classList"
				listKey="id" listValue="className" theme="ems" />
		</div>
	</div>
</s:if>
<s:else>
	<div class="alert alert-info">
		You have not created any class.
	</div>
</s:else>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
<script type="text/javascript">
 $("input:checkbox, input:radio").uniform();
function checkAllClassesForReports() {
	if ($(".allClasses").is(':checked')) {
		$("input[name='chkBoxSelectedIds']").parent().addClass('checked');
		$("input[name='chkBoxSelectedIds']").attr("checked", "true");
		generateReportsWithClassIds();
	} else {
		$("input[name='chkBoxSelectedIds']").removeAttr("checked");
		$("input[name='chkBoxSelectedIds']").parent().removeClass('checked');
		generateReportsWithClassIds();
	}
}
$("input[name=chkBoxSelectedIds]").click(function() {
	if ($("input[name=chkBoxSelectedIds]:unchecked").length > 0) {
		$(".allClasses").attr("checked", false);
		$(".allClasses").parent().removeClass('checked');
		generateReportsWithClassIds();
	} else {
		$(".allClasses").attr("checked", true);
		$(".allClasses").parent().addClass('checked');
		generateReportsWithClassIds();

	}
});
</script>
