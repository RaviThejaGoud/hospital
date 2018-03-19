<%@ include file="/common/taglibs.jsp"%>
<s:form id="addNewVehicle" action="ajaxAddVehicle" method="post"
	theme="css_xhtml" namespace="/admin">
	<!--<div class='error' style='display: none;'>
		<span></span>
	</div> -->
	<sj:textfield name="vehicle.vehicleType" id="vehicleType"
		label="Vehicle Type " cssClass="text small required" required="true"
		maxlength="40"></sj:textfield>

	<sj:submit   targets="transportContent" value="Submit"
		cssClass="submit small" formIds="addNewVehicle" indicator="indicator"
		onClickTopics="vehicleValidation" />
	<s:url id="doAddVehicles" action="ajaxDoAddVehicles"
		includeParams="all" escapeAmp="false"namespace="/admin">
	</s:url>
	<sj:a href="%{doAddVehicles}" onCompleteTopics="doInitEditVehicle"
		cssClass="cancelButton" indicator="indicator" targets="addTransport"
		button="false" buttonIcon="ui-icon-plus">Cancel</sj:a>
</s:form>
<div class="clear"></div>
<script type="text/javascript">
$(document).ready(function() {
	$.subscribe('vehicleValidation', function(event, data) {
		if ($('#addNewVehicle').valid())
			return true;
		else
			return false;
	});
});

$("input[name=campusDetails.name]").autoCheck(
		"${pageContext.request.contextPath}/metrics/ajaxCheckCampusName.do", {
			minChars : 3,
			min : "no"
		});
</script>