<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp"></jsp:include>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>

<fieldset id="stepStaffStatutory" class="step13">
<div class="commonLeftspace">
	<s:form action="ajaxAddStaffStatutoryInfo" theme="css_xhtml"
		id="addStaffStatutoryInfo" method="post" namespace="/admin">
		<s:hidden name="tempId" value="%{tempId}" />
		<div class="grid_11">
			<div class="grid_6">
				<div class="grid_6">
					<sj:textfield name="staffStatutory.esiNo" id="esiNo" label="ESI No"
						tabindex="16" cssClass="textfield alphanumeric" maxlength="20"></sj:textfield>
				</div>
				<div class="grid_6">
					<sj:datepicker id="esiDateofJoin"
						name="staffStatutory.esiDateofJoin" label="Date Of Join"
						cssClass="textfield" changeMonth="true" readonly="true"
						changeYear="true" />
				</div>
				<div class="grid_6">
					<sj:textfield name="staffStatutory.esiPercentage"
						id="esiPercentage" label="ESI Percentage" tabindex="16"
						cssClass="textfield numericDot" maxlength="20"></sj:textfield>
				</div>
			</div>
			<div class="grid_5">
				<div class="grid_5">
					<sj:textfield name="staffStatutory.pfNo" id="pfNo" label="PF No"
						tabindex="16" cssClass="textfield alphanumeric" maxlength="20"></sj:textfield>
				</div>
				<div class="grid_5">
					<sj:datepicker id="pfDateofJoin" name="staffStatutory.pfDateofJoin"
						label="Date Of Join" cssClass="textfield" changeMonth="true"
						readonly="true" changeYear="true" />
				</div>
				<div class="grid_5">
					<sj:textfield name="staffStatutory.pfPercentage" id="pfPercentage"
						label="PF Percentage" tabindex="16" cssClass="textfield numericDot"
						maxlength="20"></sj:textfield>
				</div>
			</div>
		</div>
		<sj:submit   cssClass="submit small" value="Save"
			targets="stepStaffStatutory" formIds="addStaffStatutoryInfo" />
	</s:form>
 </div>
</fieldset>

<script type="text/javascript">
$(document).ready(function() {
$('.alphanumeric').alphanumeric();
	$('.numericDot').numeric( {
		allow : "."
	});
});
</script>