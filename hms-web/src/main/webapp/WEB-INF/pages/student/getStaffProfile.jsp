<%@ include file="/common/taglibs.jsp"%>
<s:if test="%{staffsList !=null}">
	<s:form id="findStudentUsingName"
		action="ajaxFindStaffProfileUsingNameOrId" method="post"
		theme="css_xhtml" namespace="/student">
		<div class="grid_4">

			<sj:autocompleter id="staffsList" name="staffFirstName"
				list="%{staffsList}" selectBox="true" loadMinimumCount="1"
				required="true" cssClass="textfield required" />
		</div>
		<div class="grid_4">
			&nbsp;
		</div>

		<div class="grid_4">
			<sj:submit   targets="studentContent" cssClass="submit small"
				value="Find" indicator="indicator2" validate="true" />
		</div>
	</s:form>
</s:if>
<s:else>
	Currently there are no staff
</s:else>
<div id="findStudent"></div>
<script type="text/javascript">
$(document).ready(function() {
	$.subscribe('findStudentUsingIdFormValidation', function(event, data) {
		if ($('#findStudentUsingId').valid())
			return true;
		else
			return false;
	});

	$.subscribe('cancelRegistration', function(event, data) {
		if ($('#' + data.id).is(":hidden")) {
			$('#' + data.id).show();
		} else {
			$('#' + data.id).hide();
		}
	});

});
function frequencyChange(clickButton) {
	var frequency = clickButton;
	if (frequency == 'R') {
		$("#autoCompleterRollText").show();
		$("#autoCompleterNameText").hide();
	} else if (frequency == 'N') {
		$("#autoCompleterNameText").show();
		$("#autoCompleterRollText").hide();
	}
}
</script>