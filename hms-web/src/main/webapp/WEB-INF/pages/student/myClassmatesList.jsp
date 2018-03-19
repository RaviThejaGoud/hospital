<%@ include file="/common/taglibs.jsp"%>
<s:if test="%{studentsList !=null}">
	<s:form id="findStudentUsingName" action="ajaxFindStudentUsingNameOrId"
		method="post" theme="css_xhtml" namespace="/student">
		<div class="grid_4">
			<sj:autocompleter id="studentsList" name="stuFirstName"
				list="%{studentsList}" selectBox="true" loadMinimumCount="1"
				required="true" requiredposition="left"
				cssClass="textfield required" />
			<div class="grid_4">
				&nbsp;
			</div>
			<sj:submit   targets="studentContent" cssClass="submit small"
				value="Find" indicator="indicator2"
				onClickTopics="findStudentUsingNameFormValidation" />
		</div>
	</s:form>
</s:if>
<s:else>
	Currently there are no students
</s:else>

<div id="findStudent"></div>
<script type="text/javascript">
$(document).ready(function() {
$('#studentsList').focus();
	$.subscribe('findStudentUsingIdFormValidation', function(event, data) {
		if ($('#findStudentUsingId').valid())
			return true;
		else
			return false;
	});
	$.subscribe('findStudentUsingNameFormValidation', function(event, data) {
		if ($('#findStudentUsingName').valid())
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