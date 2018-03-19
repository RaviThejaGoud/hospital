<%@ include file="/common/taglibs.jsp"%>


	<div class="grid_3 alpha" style="text-align: left">
		<b>Search Student:</b>
	</div>
	<br/>
	<input type="radio" value="R" onclick=frequencyChange('R'); name="selected" style="vertical-align: top;" checked>Roll Number
<input type="radio" value="N" onclick=frequencyChange('N'); name="selected" style="vertical-align: sub;">First Name
	
	<br/>

	<div id="autoCompleterRollText" style="display: block;">
	<s:form id="findStudentUsingId" action="ajaxFindExamStudentUsingNameOrId"
					method="post" theme="css_xhtml">
	
	<sj:autocompleter id="allStudentsList" name="username" 
		list="%{allStudentsList}" selectBox="true" loadMinimumCount="1"
		required="true" requiredposition="left"
		cssClass="text small required" cssStyle="width :160px" />
	
	<sj:submit   targets="findStudent" formIds="findStudentUsingId"
		cssClass="submit small" value="Find" indicator="indicator1"
		onClickTopics="findStudentUsingIdFormValidation" />
</s:form>
</div>
<div style="display: none;" id="autoCompleterNameText">
<s:form id="findStudentUsingName" action="ajaxFindStudentUsingNameOrId"
					method="post" theme="css_xhtml">

		<sj:autocompleter id="studentsList" name="stuFirstName"
			list="%{studentsList}" selectBox="true" loadMinimumCount="1" 
			required="true" requiredposition="left"
			cssClass="text small required" cssStyle="width :160px" />
	
	<sj:submit   targets="findStudent"
		cssClass="submit small" value="Find" indicator="indicator2"
		onClickTopics="findStudentUsingNameFormValidation" />
</s:form>
</div>
<br/><br/><br/>
<div id="findStudent"></div>

<script type="text/javascript">
	
	
	$(document).ready(function() {
			
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
		var frequency=clickButton;
		if (frequency=='R') {
		$("#autoCompleterRollText").show();
			$("#autoCompleterNameText").hide();
		}
		else if(frequency=='N') {
		$("#autoCompleterNameText").show();
			$("#autoCompleterRollText").hide();
		}
	}
			


</script>