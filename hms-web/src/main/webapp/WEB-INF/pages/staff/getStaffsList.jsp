<%@ include file="/common/taglibs.jsp"%>
	<div class="grid_3 alpha" style="text-align: left">
		<b>Search Staff:</b>
	</div>
	<br/>
	<input type="radio" value="R" onclick=selectRollNumberORName('R'); name="selected" style="vertical-align: top;" checked>Roll Number
<input type="radio" value="N" onclick=selectRollNumberORName('N'); name="selected" style="vertical-align: sub;">First Name
	
	<br/>

	<div id="autoCompleterRollText" style="display: block;">
	<s:form id="findStaffUsingId" action="ajaxFindStaffUsingNameOrId"
					method="post" theme="css_xhtml">
	
	<sj:autocompleter id="allStaffList" name="username" 
		list="%{allStaffList}" selectBox="true" loadMinimumCount="1"
		required="true" requiredposition="left"
		cssClass="textfield required" cssStyle="width :160px" />
	<br/>
	<sj:submit   targets="findStaff" formIds="findStaffUsingId"
		cssClass="submit small" value="Find" indicator="indicator1"
		onClickTopics="findStaffUsingIdFormValidation" />
</s:form>
</div>
<div style="display: none;" id="autoCompleterNameText">
<s:form id="findStaffUsingName" action="ajaxFindStaffUsingNameOrId"
					method="post" theme="css_xhtml">
		<sj:autocompleter id="staffsList" name="staffFirstName"
			list="%{staffsList}" selectBox="true" loadMinimumCount="1" 
			required="true" requiredposition="left"
			cssClass="textfield required" cssStyle="width :160px" />
	<br/>
	<sj:submit   targets="findStaff"
		cssClass="submit small" value="Find" indicator="indicator2"
		onClickTopics="findStaffUsingNameFormValidation" />
</s:form>
</div>
<br/><br/><br/>
<div id="findStaff"></div>
<script type="text/javascript">
	$(document).ready(function() {
			
		$.subscribe('findStaffUsingIdFormValidation', function(event, data) {
				if ($('#findStaffUsingId').valid())
					return true;
				else
					return false;
			});
			$.subscribe('findStaffUsingNameFormValidation', function(event, data) {
				if ($('#findStaffUsingName').valid())
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
function selectRollNumberORName(clickButton) {
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