<%@ include file="/common/taglibs.jsp"%>
<div class="grid_12 commomnTabs" id="editFoodType"> 
	<s:form id="updateFoodType" action="ajaxUpdateFoodTypes" method="post" theme="css_xhtml" namespace="/hostel">
		<s:hidden name="type" value="%{'MessFoodTypes'}" />
		<s:hidden name="tempId1" />
		<fieldset>
			<div class="grid_7">
				<div class="grid_7">
					<sj:textfield name="foodTypes.foodTypeName" id="foodName"
						label="Food Type Name" required="true" tabindex="1"
						cssClass="required textfield small" maxlength="60"></sj:textfield>
				</div>
			</div>
			<div class="grid_12">
				<s:url id="doAddFoodList" action="ajaxLoadMessInfoByStatus"
					includeParams="all" escapeAmp="false" namespace="/hostel">
				</s:url>
				<sj:a href="%{doAddFoodList}" cssClass="cancelButton"
					indicator="indicator" targets="viewMessSteps">Cancel</sj:a>
					
				<sj:submit   cssClass="submit small" value="Submit" validate="true" 
					onClickTopics="editFoodValidation" indicator="indicator" 
					targets="viewMessSteps" />
			</div>
		</fieldset>
	</s:form>
</div>
<script type="text/javascript">
$.subscribe('editFoodValidation', function(event, data) {
	if ($('#updateFoodType').valid())
		return true;
	else
		return false;
});
$.subscribe('doInitFoodTypes', function(event, data) {
	if ($('#' + data.id).is(":hidden")) {
		$('#' + data.id).show();
	} else {
		$('#' + data.id).hide();
	}
});
</script>
