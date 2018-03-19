<%@ include file="/common/taglibs.jsp"%>
<div class="grid_14 commonFormTabs">
	<s:form id="editEvent" action="ajaxEditEvent" method="post"
		theme="css_xhtml" enctype="multipart/form-data" namespace="/admin">
		<s:hidden name="eventId" value="%{events.id}"></s:hidden>
		<h1>
			Update Event
		</h1>
		<p>
			to be updated
		</p>
		<fieldset>
			<div class="grid_13">
				<div class="grid_13">
					<div class="grid_7 left">
						<div class="grid_7">
							<s:select list="objectList" listKey="id" listValue="eventName" tabindex="1"
								label="Sms Events" name="events.smsEventId" headerKey="" headerValue="- Select Sms Event-"
								requiredposition="first" onchange="javascript:onClassChange(this.value);">
							</s:select>
						</div>
						<div class="grid_7">
							<sj:datepicker id="startDate" label="Event Start Date"
								readonly="true" name="events.startDate" tabindex="3"
								cssClass="textfield required" required="true" minDate="0" />
						</div>
						<div class="grid_7">
							<s:select name="events.startTime" label="Start Time"
								cssClass="textfield required" required="true" tabindex="5"
								list="#{'05:00 AM':'05:00 AM','05:15 AM':'05:15 AM','05:30 AM':'05:30 AM','05:45 AM':'05:45 AM', '06:00 AM':'06:00 AM', '06:15 AM':'06:15 AM','06:30 AM':'06:30 AM','06:45 AM':'06:45 AM','07:00 AM':'07:00 AM','07:15 AM':'07:15 AM','07:30 AM':'07:30 AM','07:45 AM':'07:45 AM','08:00 AM':'08:00 AM','08:15 AM':'08:15 AM','08:30 AM':'08:30 AM','08:45 AM':'08:45 AM','09:00 AM':'09:00 AM','09:15 AM':'09:15 AM','09:30 AM':'09:30 AM','09:45 AM':'09:45 AM','10:00 AM':'10:00 AM','10:15 AM':'10:15 AM','10:30 AM':'10:30 AM','10:45 AM':'10:45 AM','11:00 AM':'11:00 AM','11:15 AM':'11:15 AM','11:30 AM':'11:30 AM','11:45 AM':'11:45 AM','12:00 AM':'12:00 AM','12:15 AM':'12:15 AM','12:30 AM':'12:30 AM','12:45 AM':'12:45 AM','01:00 PM':'01:00 PM','01:15 PM':'01:15 PM','01:30 PM':'01:30 PM','01:45 PM':'01:45 PM','02:00 PM':'02:00 PM','02:15 PM':'02:15 PM','02:30 PM':'02:30 PM','02:45 PM':'02:45 PM','03:00 PM':'03:00 PM','03:15 PM':'03:15 PM','03:30 PM':'03:30 PM','03:45 PM':'03:45 PM','04:00 PM':'04:00 PM','04:15 PM':'04:15 PM','04:30 PM':'04:30 PM','04:45 PM':'04:45 PM','05:00 PM':'05:00 PM','05:15 PM':'05:15 PM','05:30 PM':'05:30 PM','05:45 PM':'05:45 PM','06:00 PM':'06:00 PM','06:15 PM':'06:15 PM','06:30 PM':'06:30 PM','06:45 PM':'06:45 PM','07:00 PM':'07:00 PM','07:15 PM':'07:15 PM','07:30 PM':'07:30 PM','07:45 PM':'07:45 PM','08:00 PM':'08:00 PM','08:15 PM':'08:15 PM','08:30 PM':'08:30 PM','08:45 PM':'08:45 PM','09:00 PM':'09:00 PM','09:15 PM':'09:15 PM','09:30 PM':'09:30 PM','09:45 PM':'09:45 PM','10:00 PM':'10:00 PM','10:15 PM':'10:15 PM','10:30 PM':'10:30 PM','10:45 PM':'10:45 PM','11:00 PM':'11:00 PM','11:15 PM':'11:15 PM','11:30 PM':'11:30 PM','11:45 PM':'11:45 PM'}"></s:select>
						</div>
						<div class="grid_6">
							<sj:textarea name="events.eventDescription" label="Event Description" tabindex="7"
							id="eventDescription" cssClass="text small  word_count"></sj:textarea>
						</div>
					</div>
					<div class="grid_6">
						<div class="grid_6">
							<sj:textfield name="events.eventName" id="title" label="Event Name" tabindex="2"
								cssClass="textfield required" required="true" maxlength="40"></sj:textfield>
						</div>
						<div class="grid_6">
							<sj:datepicker id="endDate" label="Event End Date"
								readonly="true" name="events.endDate" tabindex="4"
								onchange="verifyDate('eventEndTime');"
								cssClass="textfield required" required="true" minDate="0" />
						</div>
						<div class="grid_6 campaignText">
							<s:select name="events.endTime" label="End Time "
								cssClass="textfield required" required="true" tabindex="6"
								list="#{'05:00 AM':'05:00 AM','05:15 AM':'05:15 AM','05:30 AM':'05:30 AM','05:45 AM':'05:45 AM', '06:00 AM':'06:00 AM', '06:15 AM':'06:15 AM','06:30 AM':'06:30 AM','06:45 AM':'06:45 AM','07:00 AM':'07:00 AM','07:15 AM':'07:15 AM','07:30 AM':'07:30 AM','07:45 AM':'07:45 AM','08:00 AM':'08:00 AM','08:15 AM':'08:15 AM','08:30 AM':'08:30 AM','08:45 AM':'08:45 AM','09:00 AM':'09:00 AM','09:15 AM':'09:15 AM','09:30 AM':'09:30 AM','09:45 AM':'09:45 AM','10:00 AM':'10:00 AM','10:15 AM':'10:15 AM','10:30 AM':'10:30 AM','10:45 AM':'10:45 AM','11:00 AM':'11:00 AM','11:15 AM':'11:15 AM','11:30 AM':'11:30 AM','11:45 AM':'11:45 AM','12:00 AM':'12:00 AM','12:15 AM':'12:15 AM','12:30 AM':'12:30 AM','12:45 AM':'12:45 AM','01:00 PM':'01:00 PM','01:15 PM':'01:15 PM','01:30 PM':'01:30 PM','01:45 PM':'01:45 PM','02:00 PM':'02:00 PM','02:15 PM':'02:15 PM','02:30 PM':'02:30 PM','02:45 PM':'02:45 PM','03:00 PM':'03:00 PM','03:15 PM':'03:15 PM','03:30 PM':'03:30 PM','03:45 PM':'03:45 PM','04:00 PM':'04:00 PM','04:15 PM':'04:15 PM','04:30 PM':'04:30 PM','04:45 PM':'04:45 PM','05:00 PM':'05:00 PM','05:15 PM':'05:15 PM','05:30 PM':'05:30 PM','05:45 PM':'05:45 PM','06:00 PM':'06:00 PM','06:15 PM':'06:15 PM','06:30 PM':'06:30 PM','06:45 PM':'06:45 PM','07:00 PM':'07:00 PM','07:15 PM':'07:15 PM','07:30 PM':'07:30 PM','07:45 PM':'07:45 PM','08:00 PM':'08:00 PM','08:15 PM':'08:15 PM','08:30 PM':'08:30 PM','08:45 PM':'08:45 PM','09:00 PM':'09:00 PM','09:15 PM':'09:15 PM','09:30 PM':'09:30 PM','09:45 PM':'09:45 PM','10:00 PM':'10:00 PM','10:15 PM':'10:15 PM','10:30 PM':'10:30 PM','10:45 PM':'10:45 PM','11:00 PM':'11:00 PM','11:15 PM':'11:15 PM','11:30 PM':'11:30 PM','11:45 PM':'11:45 PM'}"></s:select>
						</div>
						<div class="grid_6">
							<div class="grid_4">
								<sj:radio list="#{'H':'Is Holiday','E':'Is Event'}" tabindex="8"
									name="events.status" cssClass="radio required" required="true" />
							</div>
						</div>
					</div>
				</div>
				<div class="grid_12">
					<s:url id="viewEventsUrl" action="ajaxViewEvents"
						includeParams="all" escapeAmp="false" namespace="/admin">
					</s:url>
					<sj:a href="%{viewEventsUrl}" cssClass="cancelButton"
						indicator="indicator" targets="stepEvents" button="false"
						buttonIcon="ui-icon-plus">Cancel</sj:a>
					<sj:submit   targets="stepEvents" value="Submit"
						cssClass="submit small" formIds="editEvent"
						indicator="indicator" onClickTopics="eventFormValidation" />
				</div>
			</div>
		</fieldset>
	</s:form>
</div>
<script type="text/javascript">
	$('.numeric').numeric();
	changePageTitle("Edit Event");
	function verifyDate(date) {
		var startDate = document.getElementById('date0').value;
		var endDate = document.getElementById('date1').value;
		if (endDate < startDate) {
			alert("Your end date is equal or more than your start date.");
		}
	}
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
	$.subscribe('editEventFormValidation',function(event, data) {
		var catId = document.getElementsByName("chkBoxSelectedIds");
		var fieldErrorString = '';
		var frequencyStatus = $("input[name='eventBelongs']:checked").val();
		if (frequencyStatus != 'ToALL') {
			var isSelected = false;
			for (i = 0; i < catId.length; i++) {
				if (catId[i].checked == true) {
					isSelected = true;
				}
			}
			if (!isSelected) {
				fieldErrorString = fieldErrorString+"<font style=\"color:red\">Please select at least one Class.<br /></font>";
				document.getElementById('checkBoxFieldErrors').innerHTML = fieldErrorString;
				document.getElementById('checkBoxFieldErrors').style.display = "block";
				return false;
			} else {
				return true;
			}
		}
		if ($('#editEvent').valid())
			return true;
		else
			return false;
	});
</script>
