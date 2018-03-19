<%@ include file="/common/taglibs.jsp"%>
<s:if test="%{staffsList !=null}">
<div class="row">
	<div class="col-md-12">
		<s:form id="findStudentUsingName"
			action="ajaxFindStaffProfileUsingNameOrId" method="post"
			theme="simple" cssClass="form-horizontal" namespace="/staff">
			<s:hidden name="tempId1" id="tempId1" value="%{tempId1}"/>
			<s:hidden name="anyTitle" id="anyTitle" value="%{anyTitle}"/>
			<div class="form-group">
				<label class="col-md-2 control-label" style="width: 120px;">
				</label>
					<div class="col-md-3">
						<select name="staffFirstName" id="select2_sample4"
							class="required form-control select2">
							<s:iterator value="staffsList">
								<option value='<s:property />'>
									<s:property />
								</option>
							</s:iterator>
						</select>
						<span class="input-group-btn"> </span>
						<span class="hintMessage">(Key at least 3 chars and hit
							submit to get closer match.)</span>
					</div>
					<sj:submit   targets="profileId" cssClass="btn blue" value="Find"
					validate="true" />
			</div>

		</s:form>
	</div>
</div>
</s:if>
<s:else>
	<div class="alert alert-info">
		Currently there are no staff.
	</div>
</s:else>
<div id="findStudent"></div>
<script type="text/javascript">
$(document).ready(function() {
	$('#staffsList').focus();
	 FormComponents.init();
	 $('input.btn').click();
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