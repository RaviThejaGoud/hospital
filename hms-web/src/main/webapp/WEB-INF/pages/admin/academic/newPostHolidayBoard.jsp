<%@ include file="/common/taglibs.jsp"%>
<td style="padding: 20px">
	<s:form id="schoolHolidaysForm" action="ajaxPostHolidayBoard" method="post" theme="css_xhtml" namespace="/admin">
	<fieldset>
		<table width="628px">
			<tr>
				<td>
					<label class="block">
						<span class="required">*</span>&nbsp;Select Holidays For :
					</label>
				</td>
			</tr>
			<div>
				<sj:datepicker id="date0" label="Start Date" name="schoolHolidays.startDate" cssClass="text small required" required="true" readonly="true"  minDate="0" cssStyle="width:167px;height:15px;" />
				<sj:datepicker id="date1" label="End Date" name="schoolHolidays.endDate" minDate="0" cssClass="text small required" required="true" readonly="true"  cssStyle="width:167px;height:15px;" />
			</div>
			<tr>
				<td style="padding-bottom: 5px;">
					<input type="radio" value="ToALL" onclick="frequencyChangeClass('ToALL')" name="eventBelongs" style="vertical-align: top;" checked>To All
					<input type="radio" value="Class" onclick="frequencyChangeClass('Class')" name="eventBelongs" style="vertical-align: top;">Classes
				</td>
			</tr>
			<tr>
				<td align="left" style="display: none;" id="clickClass">
					<div id="checkBoxFieldErrors"></div>
					<s:checkboxlist name="chkBoxSelectedIds" id="chkBoxClassIds" list="studyClassList" listKey="className" listValue="className" />
				</td>
			</tr>
			<tr>
				<td>
					<sj:textarea name="schoolHolidays.description" label="Description" id="eventDescription" cssClass="text small required" required="true"></sj:textarea>
				</td>
			</tr>
			<tr>
				<td style="float: right;" class="grid_4">
					<sj:submit   targets="noticeBoardContent" value="Submit" cssClass="submit small" validate="true" formIds="schoolHolidaysForm" indicator="indicator" onClickTopics="eventFormValidation" />
					<s:url id="cancelNoticeBoardUrl" action="ajaxCancelHolidayBoard" includeParams="all" escapeAmp="false" namespace="/admin"></s:url>
					<sj:a href="%{cancelNoticeBoardUrl}" cssClass="cancelButton" indicator="indicator" targets="noticeBoardContent" button="false" buttonIcon="ui-icon-plus">Cancel</sj:a>
				</td>
			</tr>
		</table>
		</fieldset>
	</s:form>
</td>
<script type="text/javascript">
changePageTitle("Create Holidays");
function frequencyChangeClass(clickButton) {
	var frequency = clickButton;
	if (frequency == 'ToALL') {
		$("#clickClass").hide();
	} else {
		if (frequency == 'Class') {
			$("#clickClass").show();
		}
	}
}
function frequencyChange(clickButton) {
	var frequency = clickButton;
	if (frequency == 'N') {
		$("#frequencyhideText").hide();
	} else {
		if (frequency == 'R') {
			$("#frequencyhideText").show();
		}
	}
}
$
		.subscribe(
				'eventFormValidation',
				function(event, data) {
					var catId = document.getElementsByName("chkBoxSelectedIds");
					var fieldErrorString = '';
					var frequencyStatus = $(
							"input[name='eventBelongs']:checked").val();
					if (frequencyStatus != 'ToALL') {
						var isSelected = false;
						for (i = 0; i < catId.length; i++) {
							if (catId[i].checked == true) {
								isSelected = true;
							}
						}
						if (!isSelected) {
							fieldErrorString = fieldErrorString
									+ "<font style=\"color:red\">Please select at least one Class.<br /></font>";
							document.getElementById('checkBoxFieldErrors').innerHTML = fieldErrorString;
							document.getElementById('checkBoxFieldErrors').style.display = "block";
							return false;
						} else {
							return true;
						}
					}
					if ($('#schoolHolidaysForm').valid())
						return true;
					else
						return false;
				});
</script>
