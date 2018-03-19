<%@ include file="/common/taglibs.jsp"%>


	<div class="grid_3 alpha" style="text-align: left">
		<b>Search Staff:</b>
	</div>
	<br/>
	<!--<div>
	<input type="radio" value="R" onclick=frequencyChange('R'); name="selected" style="vertical-align: top;" checked>Roll Number
<input type="radio" value="N" onclick=frequencyChange('N'); name="selected" style="vertical-align: sub;">First Name
	</div>
	--><br/>

	<div id="autoCompleterRollText">
	<s:form id="findStudentUsingId" action="ajaxFindStaffUsingNameOrId"
					method="post" theme="css_xhtml" namespace="/student">
					<s:hidden name = "viewStudentPersonAccountDetails.rollNumber"></s:hidden>
					<s:select id="sectionId" list="allStudentsList" listKey="username"
				listValue="personFullName" label="Child Name" cssClass="required" theme="simple"
				required="true" name="username" />
	<sj:submit   targets="findStudent" formIds="findStudentUsingId"
		cssClass="submit small" value="Find" indicator="indicator1"
		onClickTopics="findStudentUsingIdFormValidation" />
</s:form>
</div>
<!--<div style="display: none;" id="autoCompleterNameText">
<s:form id="findStudentUsingName" action="ajaxFindStaffUsingNameOrId"
					method="post" theme="css_xhtml">

		<sj:autocompleter id="studentsList" name="staffFirstName"
			list="%{studentsList}" selectBox="true" loadMinimumCount="1" 
			required="true" requiredposition="left"
			cssClass="text small required" cssStyle="width :160px" />
	
	<sj:submit   targets="findStudent"
		cssClass="submit small" value="Find" indicator="indicator2"
		onClickTopics="findStudentUsingNameFormValidation" />
		<img style="display: none;" alt="Loading..."
							src="${pageContext.request.contextPath}/images/indicator.gif"
							id="indicator2">
</s:form>
</div>
--><br/><br/><br/>
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