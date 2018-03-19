<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
<div class="block grid_14 omega" id="eventsResults">
	<div class="block_head" id="topMenu">
		<h2>
			New Events
		</h2>
		<ul>
			<li>
				<s:url id="urlDoAddStaffEvent"
					action="ajaxRegisterStudentEventByStaff" />
				<sj:a id="doAddStaffEvent" href="%{urlDoAddStaffEvent}"
					targets="eventsResults" indicator="indicator">View Event</sj:a>
			</li>
		</ul>
	</div>
	<div class="block_content">
		<div id="steps13">
			<div id="tabWrapper13">
				<s:form id="addStaffNewEvent" action="ajaxAddStaffEvent"
					method="post" name="myform" theme="css_xhtml">
					<fieldset>
						<s:hidden name="calendarEvent.organizerId"
							value="%{user.username}"></s:hidden>
						<div class="grid_12">
							<div class="grid_6">
								<div class="grid_6">
									<label>
										<span class="required">*</span>Event Name:
									</label>
								</div>
								<div class="grid_6">
									<sj:textfield name="calendarEvent.title" id="eventName" tabindex="1"
										cssClass="textfield required" required="true" maxlength="40"></sj:textfield>
								</div>
							</div>
							<div class="grid_6">
								&nbsp;
							</div>
						</div>
						<div class="grid_12">
							<div class="grid_6 left">
								<div class="grid_6">
									<div class="grid_6">
										
										<label>
											<span class="required">*</span>Start Time:
										</label>
									</div>
									<div class="grid_6">
										<s:select name="calendarEvent.startTime" cssClass="textfield"
											required="true" tabindex="2"
											list="#{'05:00 AM':'05:00 AM','05:15 AM':'05:15 AM','05:30 AM':'05:30 AM','05:45 AM':'05:45 AM', '06:00 AM':'06:00 AM', '06:15 AM':'06:15 AM','06:30 AM':'06:30 AM','06:45 AM':'06:45 AM','07:00 AM':'07:00 AM','07:15 AM':'07:15 AM','07:30 AM':'07:30 AM','07:45 AM':'07:45 AM','08:00 AM':'08:00 AM','08:15 AM':'08:15 AM','08:30 AM':'08:30 AM','08:45 AM':'08:45 AM','09:00 AM':'09:00 AM','09:15 AM':'09:15 AM','09:30 AM':'09:30 AM','09:45 AM':'09:45 AM','10:00 AM':'10:00 AM','10:15 AM':'10:15 AM','10:30 AM':'10:30 AM','10:45 AM':'10:45 AM','11:00 AM':'11:00 AM','11:15 AM':'11:15 AM','11:30 AM':'11:30 AM','11:45 AM':'11:45 AM','12:00 AM':'12:00 AM','12:15 AM':'12:15 AM','12:30 AM':'12:30 AM','12:45 AM':'12:45 AM','01:00 PM':'01:00 PM','01:15 PM':'01:15 PM','01:30 PM':'01:30 PM','01:45 PM':'01:45 PM','02:00 PM':'02:00 PM','02:15 PM':'02:15 PM','02:30 PM':'02:30 PM','02:45 PM':'02:45 PM','03:00 PM':'03:00 PM','03:15 PM':'03:15 PM','03:30 PM':'03:30 PM','03:45 PM':'03:45 PM','04:00 PM':'04:00 PM','04:15 PM':'04:15 PM','04:30 PM':'04:30 PM','04:45 PM':'04:45 PM','05:00 PM':'05:00 PM','05:15 PM':'05:15 PM','05:30 PM':'05:30 PM','05:45 PM':'05:45 PM','06:00 PM':'06:00 PM','06:15 PM':'06:15 PM','06:30 PM':'06:30 PM','06:45 PM':'06:45 PM','07:00 PM':'07:00 PM','07:15 PM':'07:15 PM','07:30 PM':'07:30 PM','07:45 PM':'07:45 PM','08:00 PM':'08:00 PM','08:15 PM':'08:15 PM','08:30 PM':'08:30 PM','08:45 PM':'08:45 PM','09:00 PM':'09:00 PM','09:15 PM':'09:15 PM','09:30 PM':'09:30 PM','09:45 PM':'09:45 PM','10:00 PM':'10:00 PM','10:15 PM':'10:15 PM','10:30 PM':'10:30 PM','10:45 PM':'10:45 PM','11:00 PM':'11:00 PM','11:15 PM':'11:15 PM','11:30 PM':'11:30 PM','11:45 PM':'11:45 PM'
												}"></s:select>
									</div>
								</div>
								<div class="grid_6">
									<div class="grid_6">
										
										<label>
											<span class="required">*</span>Event Start Date:
										</label>
									</div>
									<div class="grid_6">
										<sj:datepicker id="date0" readonly="true" tabindex="4"
											name="calendarEvent.startDate" cssClass="textfield required"
											required="true" minDate="0" />
									</div>
								</div>
								<div class="grid_6">
									<div class="grid_6">
										<label>
											<span class="required">*</span>Place:
										</label>

									</div>
									<div class="grid_6">
										<sj:textfield name="calendarEvent.place" id="eventPlace" tabindex="6"
											cssClass="textfield alphanumeric required" required="true"></sj:textfield>
									</div>
								</div>
								<div class="grid_6">
									<div class="grid_6">
										<label>
											<span class="required">*</span>Is Parent Meeting :
										</label>
									</div>
									<div class="grid_6">
										<input type="radio" value="N"
											onclick="javascript:parentMeetingFun('N');" tabindex="8"
											name="calendarEvent.isParentMeeting" checked="checked">
										No&nbsp;&nbsp;
										<input type="radio" value="Y"
											onclick="javascript:parentMeetingFun('Y');"
											name="calendarEvent.isParentMeeting">
										Yes
									</div>
								</div>
								<div id="selectEventFor">
									<div class="grid_6">
										<div class="grid_6">
											<label>
												<span class="required">*</span>Select Event For:
											</label>
										</div>
										<div class="grid_6">
											<input type="radio" id="frequencyStatus" value="ToALL"  tabindex="10"
												onclick="frequencyChangeClass('ToALL');" name="eventBelongs"
												checked="checked">
											To All&nbsp;&nbsp;
											<input type="radio" id="frequencyStatus" value="Class"
												onclick="frequencyChangeClass('Class');" name="eventBelongs">
											Classes
										</div>
									</div>
								</div>
								<div class="grid_10" style="display: none;" id="clickClass">
								<div class="grid_6">
									<label>
									<span class="required">*</span>	Classes:
									</label>
								</div>
								<div class="grid_6">
									<div id="checkBoxFieldErrors"></div>
									<s:checkboxlist name="chkBoxSelectedIds" id="chkBoxClassIds"
										list="studyClassList" listKey="id" listValue="classAndSection"
										label="Classes" theme="ems" cssClass="text small required"
										required="true" />
								</div>
							</div>
							</div>
							<div class="grid_6">
								<div class="grid_6">
									<div class="grid_6">
										<div class="grid_6">
											<label>
												<span class="required">*</span>End Time:
											</label>
										</div>
										<div class="grid_6">
											<s:select name="calendarEvent.endTime" cssClass="textfield"
												required="true" tabindex="3"
												list="#{'05:00 AM':'05:00 AM','05:15 AM':'05:15 AM','05:30 AM':'05:30 AM','05:45 AM':'05:45 AM', '06:00 AM':'06:00 AM', '06:15 AM':'06:15 AM','06:30 AM':'06:30 AM','06:45 AM':'06:45 AM','07:00 AM':'07:00 AM','07:15 AM':'07:15 AM','07:30 AM':'07:30 AM','07:45 AM':'07:45 AM','08:00 AM':'08:00 AM','08:15 AM':'08:15 AM','08:30 AM':'08:30 AM','08:45 AM':'08:45 AM','09:00 AM':'09:00 AM','09:15 AM':'09:15 AM','09:30 AM':'09:30 AM','09:45 AM':'09:45 AM','10:00 AM':'10:00 AM','10:15 AM':'10:15 AM','10:30 AM':'10:30 AM','10:45 AM':'10:45 AM','11:00 AM':'11:00 AM','11:15 AM':'11:15 AM','11:30 AM':'11:30 AM','11:45 AM':'11:45 AM','12:00 AM':'12:00 AM','12:15 AM':'12:15 AM','12:30 AM':'12:30 AM','12:45 AM':'12:45 AM','01:00 PM':'01:00 PM','01:15 PM':'01:15 PM','01:30 PM':'01:30 PM','01:45 PM':'01:45 PM','02:00 PM':'02:00 PM','02:15 PM':'02:15 PM','02:30 PM':'02:30 PM','02:45 PM':'02:45 PM','03:00 PM':'03:00 PM','03:15 PM':'03:15 PM','03:30 PM':'03:30 PM','03:45 PM':'03:45 PM','04:00 PM':'04:00 PM','04:15 PM':'04:15 PM','04:30 PM':'04:30 PM','04:45 PM':'04:45 PM','05:00 PM':'05:00 PM','05:15 PM':'05:15 PM','05:30 PM':'05:30 PM','05:45 PM':'05:45 PM','06:00 PM':'06:00 PM','06:15 PM':'06:15 PM','06:30 PM':'06:30 PM','06:45 PM':'06:45 PM','07:00 PM':'07:00 PM','07:15 PM':'07:15 PM','07:30 PM':'07:30 PM','07:45 PM':'07:45 PM','08:00 PM':'08:00 PM','08:15 PM':'08:15 PM','08:30 PM':'08:30 PM','08:45 PM':'08:45 PM','09:00 PM':'09:00 PM','09:15 PM':'09:15 PM','09:30 PM':'09:30 PM','09:45 PM':'09:45 PM','10:00 PM':'10:00 PM','10:15 PM':'10:15 PM','10:30 PM':'10:30 PM','10:45 PM':'10:45 PM','11:00 PM':'11:00 PM','11:15 PM':'11:15 PM','11:30 PM':'11:30 PM','11:45 PM':'11:45 PM'
													}"></s:select>
										</div>
									</div>
									<div class="grid_6">
										<div class="grid_6">
											<label>
												<span class="required">*</span>Event End Date:
											</label>
										</div>
										<div class="grid_6">
											<sj:datepicker id="date1" readonly="true" tabindex="5"
												name="calendarEvent.endDate" cssClass="textfield required"
												required="true" minDate="0"
												onchange="verifyDate('eventEndTime');" />
										</div>
									</div>
									<div class="grid_6" id="noOfPartispents">
										<div class="grid_6">
											<label>
												<span class="required">*</span>No of Participants:
											</label>
										</div>
										<div class="grid_6">
											<sj:textfield name="calendarEvent.noOfPartispents" tabindex="7"
												id="noOfPartispents" cssClass="textfield numeric"
												required="true" maxlength="40"></sj:textfield>
										</div>
									</div>
									<div class="grid_6" id="eventFrequencyDesc">
										<div class="grid_6">
											<label>
												<span class="required">*</span>Event Frequency:
											</label>
										</div>
										<div class="grid_6">
											<input type="radio" value="N"
												onclick="javascript:frequencyChange('N');" tabindex="9"
												name="calendarEvent.eventType" checked>
											One Time Event&nbsp;&nbsp;
											<input type="radio" value="R"
												onclick="javascript:frequencyChange('R');"
												name="calendarEvent.eventType">
											Recurring Event
										</div>
									</div>
									<div class="grid_6" style="display: none;"
										id="frequencyhideText">
										<div class="grid_6">
											<label>
											<span class="required">*</span>	This Event Repeat:
											</label>
										</div>
										<div class="grid_6">
											<s:select name="eventFrequency" id="repeateEvent"
												cssClass="textfield" required="true"
												list="#{'daily':'Daily','weekly':'Weekly','monthly':'Monthly','yearly':'yearly'}"></s:select>
										</div>
									</div>
								</div>
							</div>
							<div>
								<div class="grid_12">
									<s:url id="viewStaffEventsUrl" action="ajaxStaffCancelEvent"
										includeParams="all" escapeAmp="false">
									</s:url>
									<sj:a href="%{viewStaffEventsUrl}" cssClass="cancelButton"
										indicator="indicator" targets="eventsResults" button="false"
										buttonIcon="ui-icon-plus">Cancel</sj:a>
									<sj:submit   targets="staffContect" value="Submit"
										cssClass="submit small" formIds="addStaffNewEvent"
										indicator="indicator" onClickTopics="eventFormValidation1" />
								</div>
							</div>
						</div>
					</fieldset>
				</s:form>

			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
changePageTitle("Create New Event");
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
function parentMeetingFun(ispParentMeeting) {
	var frequency = ispParentMeeting;
	if (frequency == 'Y') {
		$("#clickClass").show();
		$("#frequencyhideText").hide();
		$("#eventFrequency").hide();
		$("#eventFrequencyDesc").hide();
		$("#noOfPartispents").hide();
		$("#selectClass").hide();
		$("#selectEventFor").hide();
	} else {
		if (frequency == 'N') {
			$("#frequencyhideText").show();
			$("#eventFrequency").show();
			$("#eventFrequencyDesc").show();
			$("#noOfPartispents").show();
			$("#selectClass").show();
		}
	}
}
$
		.subscribe(
				'eventFormValidation1',
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
					if ($('#addStaffNewEvent').valid())
						return true;
					else
						return false;
				});

$('.numeric').numeric();
$('.alphanumeric').alphanumeric();
</script>
