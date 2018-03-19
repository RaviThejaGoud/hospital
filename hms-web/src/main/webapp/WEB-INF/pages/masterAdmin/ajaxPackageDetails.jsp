<%@ include file="/common/taglibs.jsp"%>
<s:form action="ajaxGetMasterPackageDetails" id="pakageForm"
	method="post" namespace="/signup" theme="simple"
	cssClass="form-horizontal">
	<div class="form-group">
		<s:if
			test="%{packageDetailsList != null && !packageDetailsList.isEmpty()}">
			<label class="col-md-3 control-label">
				Students Range(Upto) :
			</label>
			<div class="col-md-9">
				<s:radio name="packageDetails_" cssClass="studentRange"
					list="packageDetailsList" listKey="id" theme="ems"
					listValue="studentsRange" />
			</div>
		</s:if>
	</div>
	<div class="form-body">
		<div class="form-actions fluid">
			<div class="col-md-offset-2 col-md-6">
				<sj:submit   targets="customerDetails" value="Next"
					cssClass="btn blue" formIds="pakageForm" validate="true"
					onBeforeTopics="packageDetailsFormValidation" />
			</div>
		</div>
	</div>
</s:form>
<s:else>
	<div class="alert alert-info">
		Currently there are no packages.
	</div>
</s:else>
<script type="text/javascript">
$(document).ready(function() {
$.destroyTopic('packageDetailsFormValidation');
$("input:checkbox, input:radio").uniform();
	$.subscribe('packageDetailsFormValidation', function(event, data) {
		if ($("input.studentRange:checked").val() > 0) {
		} else {
			alert("Please Select Students Range");
			event.originalEvent.options.submit=false;
		}	
	});
});
</script>
