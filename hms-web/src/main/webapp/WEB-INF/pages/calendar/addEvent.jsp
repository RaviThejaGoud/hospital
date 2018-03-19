<%@ include file="/common/taglibs.jsp"%>
<html>
	<head>
		<script type="text/javascript"
			src="<c:url value='/scripts/default/dateFormat.js'/>"></script>
		<title>URT Apps | Calendar</title>


		<style type="text/css">
			.eventRecurrence {
				width: 480px;
				margin-top: 5px;
				margin-bottom: 5px;
				border: 1px solid #CCCCCC;
				padding: 10px;
				background-color: #F5F7F7;
			}
	</style>
	</head>
	<body>

		<div class="block grid_11" id="resultsDiv" style="display: block;">
			<div class="block_head">
				<h2>
					Create Event
				</h2>

			</div>
			<div class="block_content">
				<s:form action="/calendar/addEvent.do" id="calendarEventForm"
					method="post" onsubmit="javascript:return saveEventsErrors();"
					theme="css_xhtml">
					<div id="saveFieldErrors"></div>

					<p>
						<label>
							Event Title:
						</label>
						<br />
						<input type="text" name="events.title" id="eventTitle"
							class="text small" />
					</p>

					<p>
						<label>
							start date:
						</label>
						<br />
						<s:hidden name="events.startDate" id="eventDate" />
						<input type="text" class="text date_picker" id="eventDateStr"
							readonly="readonly" />
					</p>
					<p>
						<label>
							Start Time:
						</label>
						<br />
						<select name="events.startTime" id="eventStartTime">
							
							<option value="">
								- Select -
							</option>
							<option value="05:00 AM">
								05:00 AM
							</option>

							<option value="05:15 AM">
								05:15 AM
							</option>
							<option value="05:30 AM">
								05:30 AM
							</option>
							<option value="05:45 AM">
								05:45 AM
							</option>
							<option value="06:00 AM">
								06:00 AM
							</option>
							<option value="06:15 AM">
								06:15 AM
							</option>
							<option value="06:30 AM">
								06:30 AM
							</option>

							<option value="06:45 AM">
								06:45 AM
							</option>
							<option value="07:00 AM">
								07:00 AM
							</option>
							<option value="07:15 AM">
								07:15 AM
							</option>
							<option value="07:30 AM">
								07:30 AM
							</option>
							<option value="07:45 AM">
								07:45 AM
							</option>
							<option value="08:00 AM">
								08:00 AM
							</option>

							<option value="08:15 AM">
								08:15 AM
							</option>
							<option value="08:30 AM">
								08:30 AM
							</option>
							<option value="08:45 AM">
								08:45 AM
							</option>
							<option value="09:00 AM">
								09:00 AM
							</option>
							<option value="09:15 AM">
								09:15 AM
							</option>
							<option value="09:30 AM">
								09:30 AM
							</option>

							<option value="09:45 AM">
								09:45 AM
							</option>
							<option value="10:00 AM">
								10:00 AM
							</option>
							<option value="10:15 AM">
								10:15 AM
							</option>
							<option value="10:30 AM">
								10:30 AM
							</option>
							<option value="10:45 AM">
								10:45 AM
							</option>
							<option value="11:00 AM">
								11:00 AM
							</option>

							<option value="11:15 AM">
								11:15 AM
							</option>
							<option value="11:30 AM">
								11:30 AM
							</option>
							<option value="11:45 AM">
								11:45 AM
							</option>
							<option value="12:00 PM">
								12:00 PM
							</option>
							<option value="12:15 PM">
								12:15 PM
							</option>
							<option value="12:30 PM">
								12:30 PM
							</option>

							<option value="12:45 PM">
								12:45 PM
							</option>
							<option value="01:00 PM">
								01:00 PM
							</option>
							<option value="01:15 PM">
								01:15 PM
							</option>
							<option value="01:30 PM">
								01:30 PM
							</option>
							<option value="01:45 PM">
								01:45 PM
							</option>
							<option value="02:00 PM">
								02:00 PM
							</option>

							<option value="02:15 PM">
								02:15 PM
							</option>
							<option value="02:30 PM">
								02:30 PM
							</option>
							<option value="02:45 PM">
								02:45 PM
							</option>
							<option value="03:00 PM">
								03:00 PM
							</option>
							<option value="03:15 PM">
								03:15 PM
							</option>
							<option value="03:30 PM">
								03:30 PM
							</option>

							<option value="03:45 PM">
								03:45 PM
							</option>
							<option value="04:00 PM">
								04:00 PM
							</option>
							<option value="04:15 PM">
								04:15 PM
							</option>
							<option value="04:30 PM">
								04:30 PM
							</option>
							<option value="04:45 PM">
								04:45 PM
							</option>
							<option value="05:00 PM">
								05:00 PM
							</option>

							<option value="05:15 PM">
								05:15 PM
							</option>
							<option value="05:30 PM">
								05:30 PM
							</option>
							<option value="05:45 PM">
								05:45 PM
							</option>
							<option value="06:00 PM">
								06:00 PM
							</option>
							<option value="06:15 PM">
								06:15 PM
							</option>
							<option value="06:30 PM">
								06:30 PM
							</option>

							<option value="06:45 PM">
								06:45 PM
							</option>
							<option value="07:00 PM">
								07:00 PM
							</option>
							<option value="07:15 PM">
								07:15 PM
							</option>
							<option value="07:30 PM">
								07:30 PM
							</option>
							<option value="07:45 PM">
								07:45 PM
							</option>
							<option value="08:00 PM">
								08:00 PM
							</option>

							<option value="08:15 PM">
								08:15 PM
							</option>
							<option value="08:30 PM">
								08:30 PM
							</option>
							<option value="08:45 PM">
								08:45 PM
							</option>
							<option value="09:00 PM">
								09:00 PM
							</option>
							<option value="09:15 PM">
								09:15 PM
							</option>
							<option value="09:30 PM">
								09:30 PM
							</option>

							<option value="09:45 PM">
								09:45 PM
							</option>
							<option value="10:00 PM">
								10:00 PM
							</option>
							<option value="10:15 PM">
								10:15 PM
							</option>
							<option value="10:30 PM">
								10:30 PM
							</option>
							<option value="10:45 PM">
								10:45 PM
							</option>
							<option value="11:00 PM">
								11:00 PM
							</option>

							<option value="11:15 PM">
								11:15 PM
							</option>
							<option value="11:30 PM">
								11:30 PM
							</option>
							<option value="11:45 PM">
								11:45 PM
							</option>


						</select>
					</p>
					<p>

						<label>
							End Time:
						</label>
						<br />
						<select name="events.endTime" id="eventEndTime">
							
							<option value="">
								- Select -
							</option>
							<option value="05:00 AM">
								05:00 AM
							</option>
							<option value="05:15 AM">
								05:15 AM
							</option>
							<option value="05:30 AM">
								05:30 AM
							</option>

							<option value="05:45 AM">
								05:45 AM
							</option>
							<option value="06:00 AM">
								06:00 AM
							</option>
							<option value="06:15 AM">
								06:15 AM
							</option>
							<option value="06:30 AM">
								06:30 AM
							</option>
							<option value="06:45 AM">
								06:45 AM
							</option>
							<option value="07:00 AM">
								07:00 AM
							</option>

							<option value="07:15 AM">
								07:15 AM
							</option>
							<option value="07:30 AM">
								07:30 AM
							</option>
							<option value="07:45 AM">
								07:45 AM
							</option>
							<option value="08:00 AM">
								08:00 AM
							</option>
							<option value="08:15 AM">
								08:15 AM
							</option>
							<option value="08:30 AM">
								08:30 AM
							</option>

							<option value="08:45 AM">
								08:45 AM
							</option>
							<option value="09:00 AM">
								09:00 AM
							</option>
							<option value="09:15 AM">
								09:15 AM
							</option>
							<option value="09:30 AM">
								09:30 AM
							</option>
							<option value="09:45 AM">
								09:45 AM
							</option>
							<option value="10:00 AM">
								10:00 AM
							</option>

							<option value="10:15 AM">
								10:15 AM
							</option>
							<option value="10:30 AM">
								10:30 AM
							</option>
							<option value="10:45 AM">
								10:45 AM
							</option>
							<option value="11:00 AM">
								11:00 AM
							</option>
							<option value="11:15 AM">
								11:15 AM
							</option>
							<option value="11:30 AM">
								11:30 AM
							</option>

							<option value="11:45 AM">
								11:45 AM
							</option>
							<option value="12:00 PM">
								12:00 PM
							</option>
							<option value="12:15 PM">
								12:15 PM
							</option>
							<option value="12:30 PM">
								12:30 PM
							</option>
							<option value="12:45 PM">
								12:45 PM
							</option>
							<option value="01:00 PM">
								01:00 PM
							</option>

							<option value="01:15 PM">
								01:15 PM
							</option>
							<option value="01:30 PM">
								01:30 PM
							</option>
							<option value="01:45 PM">
								01:45 PM
							</option>
							<option value="02:00 PM">
								02:00 PM
							</option>
							<option value="02:15 PM">
								02:15 PM
							</option>
							<option value="02:30 PM">
								02:30 PM
							</option>

							<option value="02:45 PM">
								02:45 PM
							</option>
							<option value="03:00 PM">
								03:00 PM
							</option>
							<option value="03:15 PM">
								03:15 PM
							</option>
							<option value="03:30 PM">
								03:30 PM
							</option>
							<option value="03:45 PM">
								03:45 PM
							</option>
							<option value="04:00 PM">
								04:00 PM
							</option>

							<option value="04:15 PM">
								04:15 PM
							</option>
							<option value="04:30 PM">
								04:30 PM
							</option>
							<option value="04:45 PM">
								04:45 PM
							</option>
							<option value="05:00 PM">
								05:00 PM
							</option>
							<option value="05:15 PM">
								05:15 PM
							</option>
							<option value="05:30 PM">
								05:30 PM
							</option>

							<option value="05:45 PM">
								05:45 PM
							</option>
							<option value="06:00 PM">
								06:00 PM
							</option>
							<option value="06:15 PM">
								06:15 PM
							</option>
							<option value="06:30 PM">
								06:30 PM
							</option>
							<option value="06:45 PM">
								06:45 PM
							</option>
							<option value="07:00 PM">
								07:00 PM
							</option>

							<option value="07:15 PM">
								07:15 PM
							</option>
							<option value="07:30 PM">
								07:30 PM
							</option>
							<option value="07:45 PM">
								07:45 PM
							</option>
							<option value="08:00 PM">
								08:00 PM
							</option>
							<option value="08:15 PM">
								08:15 PM
							</option>
							<option value="08:30 PM">
								08:30 PM
							</option>

							<option value="08:45 PM">
								08:45 PM
							</option>
							<option value="09:00 PM">
								09:00 PM
							</option>
							<option value="09:15 PM">
								09:15 PM
							</option>
							<option value="09:30 PM">
								09:30 PM
							</option>
							<option value="09:45 PM">
								09:45 PM
							</option>
							<option value="10:00 PM">
								10:00 PM
							</option>

							<option value="10:15 PM">
								10:15 PM
							</option>
							<option value="10:30 PM">
								10:30 PM
							</option>
							<option value="10:45 PM">
								10:45 PM
							</option>
							<option value="11:00 PM">
								11:00 PM
							</option>
							<option value="11:15 PM">
								11:15 PM
							</option>
							<option value="11:30 PM">
								11:30 PM
							</option>

							<option value="11:45 PM">
								11:45 PM
							</option>


						</select>
						<!--<input type="text"  name="eventDetails.eventEndTime" class="text small" />-->
					</p>


					<p>
						<label>
							Recurring Events:
						</label>
					</p>
					<div class="wrap">
						<p>
							<input type="radio" checked="checked"
								onclick="javascript:checkEventType(this);" value="N"
								name="events.eventType" id="eventTypeId" />
							This is a one-time event
						</p>
						<p>
							<input type="radio" onclick="javascript:checkEventType(this);" value="R" name="events.eventType" id="eventTypeId" />
							Set up a recurring event (Example: Every Friday)
						</p>
						<div id="showRecuringDiv" style="display: none;">
							<input type="radio" onclick="javascript: frequencyChange(this);" name="events.eventFrequency" value="daily"
								id="eventDateDailyId" />
							<label>
								Daily
							</label>
							<input type="radio" onclick="javascript: frequencyChange(this);" name="events.eventFrequency" value="weekly"
								id="eventDateWeeklyId" />
							<label>
								Weekly
							</label>
							<input type="radio" onclick="javascript: frequencyChange(this);" name="events.eventFrequency" value="monthly"
								id="eventDateMonthlyId" />
							<label>
								Monthly
							</label>
							<input type="radio" onclick="javascript: frequencyChange(this);" name="events.eventFrequency" value="yearly"
								id="eventDateYearlyId" />
							<label>
								Yearly
							</label>
							<!--<br/>
					   <div>
					  		<label>End Date:</label><br />
							<s:hidden name="eventEndDate" id="eventEndDate" />
							<input type="text" class="text date_picker" id="eventEndDateStr" readonly="readonly"/>
					   </div> -->
						</div>
						<br />
					</div>
					<div id="dailyRecId" class="eventRecurrence" style="display: none;">
						<div>
							<label>
								End Date:
							</label>
							<s:hidden name="events.endDate" id="eventEndDate" />
							<input type="text" class="text date_picker"
								id="eventEndDateDailyStr" readonly="readonly" />
						</div>
					</div>
					<div id="weekRepeatId" class="eventRecurrence"
						style="display: none">
						<div style="float: left;">
							<b>Repeat Every:&nbsp;</b>
						</div>
						<div>
							<s:select name="weekCount"
								list="#{'1':'1', '2':'2', '3':'3','4':'4','5':'5', '6':'6', '7':'7','8':'8','9':'9','10':'10','11':'11','12':'12'}"
								theme="simple" />
							Week(s)
						</div>
						<br />
						<div style="float: left;">
							<b>Repeat On:&nbsp;</b>
						</div>
						<div>
							<s:checkboxlist name="chkBoxSelectedIds" id="chkBoxIds"
								list="#{'Monday':'Mo', 'Tuesday':'Tu','Wednesday':'We','Thursday':'Th', 'Friday':'Fr', 'Saturday':'Sa','Sunday':'Su'}"
								theme="simple" />
						</div>
						<br />
						<div>
							<label>
								End Date:
							</label>
							<input type="text" class="text date_picker"
								id="eventEndDateWeeklyStr" readonly="readonly" />
						</div>
					</div>
					<div id="RepeatByMonthId" class="eventRecurrence"
						style="display: none">
						<div>
							<label>
								<b>Repeat By:</b>
							</label>
							<s:radio name="events.monthlyBy"
								list="#{'DayOfMonth':'Day of the Month', 'DayOfWeek':'Day of the Week'}"
								value="DayOfMonth" theme="simple" />
						</div>
						<br />
						<div>
							<label>
								End Date:
							</label>
							<input type="text" class="text date_picker"
								id="eventEndDateMonthlyStr" readonly="readonly" />
						</div>
					</div>
					<div class="clear"></div>
					<div id="yearRepeatId" class="eventRecurrence"
						style="display: none">
						<div>
							<label>
								<b>Repeat Every:</b>
							</label>
							<s:select name="yearCount"
								list="#{'1':'1', '2':'2', '3':'3','4':'4','5':'5'}"
								theme="simple" />
							Year(s)
						</div>
						<br />
						<div>
							<label>
								End Date:
							</label>
							<input type="text" class="text date_picker"
								id="eventEndDateYearlyStr" readonly="readonly" />
						</div>
					</div>
					<p>
						<label>
							Address 1:
						</label>
						<br />
						<input type="text" name="events.eventAddress.addressLine1"
							id="addressLine1" class="text small" />
					</p>
					<p>
						<label>
							Address 2:
						</label>
						<br />
						<input type="text" name="events.eventAddress.addressLine2"
							id="addressLine2" class="text small" />
					</p>
					<p>
						<label>
							City:
						</label>
						<br />
						<input type="text" name="events.eventAddress.city" id="city"
							class="text small" />
						<br />
					</p>
					<p>
						<label>
							State:
						</label>
						<br />
						<select name="events.eventAddress.state" id="state">

							<option value="N">
								- Select -
							</option>
							<option value="AL">
								Alabama
							</option>
							<option value="AK">
								Alaska
							</option>
							<option value="AR">
								Arkansas
							</option>
							<option value="AZ">
								Arizona
							</option>
							<option value="CA">
								California
							</option>

							<option value="CO">
								Colorado
							</option>
							<option value="CT">
								Connecticut
							</option>
							<option value="DE">
								Delaware
							</option>
							<option value="DC">
								District of Columbia
							</option>
							<option value="FL">
								Florida
							</option>
							<option value="GA">
								Georgia
							</option>

							<option value="HI">
								Hawaii
							</option>
							<option value="IA">
								Iowa
							</option>
							<option value="ID">
								Idaho
							</option>
							<option value="IL">
								Illinois
							</option>
							<option value="IN">
								Indiana
							</option>
							<option value="KS">
								Kansas
							</option>

							<option value="KY">
								Kentucky
							</option>
							<option value="LA">
								Louisiana
							</option>
							<option value="MA">
								Massachusetts
							</option>
							<option value="MD">
								Maryland
							</option>
							<option value="ME">
								Maine
							</option>
							<option value="MI">
								Michigan
							</option>

							<option value="MN">
								Minnesota
							</option>
							<option value="MS">
								Mississippi
							</option>
							<option value="MO">
								Missouri
							</option>
							<option value="MT">
								Montana
							</option>
							<option value="NC">
								North Carolina
							</option>
							<option value="ND">
								North Dakota
							</option>

							<option value="NE">
								Nebraska
							</option>
							<option value="NH">
								New Hampshire
							</option>
							<option value="NJ">
								New Jersey
							</option>
							<option value="NV">
								Nevada
							</option>
							<option value="NM">
								New Mexico
							</option>
							<option value="NY">
								New York
							</option>

							<option value="OH">
								Ohio
							</option>
							<option value="OK">
								Oklahoma
							</option>
							<option value="OR">
								Oregon
							</option>
							<option value="PA">
								Pennsylvania
							</option>
							<option value="RI">
								Rhode Island
							</option>
							<option value="SC">
								South Carolina
							</option>

							<option value="SD">
								South Dakota
							</option>
							<option value="TN">
								Tennessee
							</option>
							<option value="TX">
								Texas
							</option>
							<option value="UT">
								Utah
							</option>
							<option value="VA">
								Virginia
							</option>
							<option value="VT">
								Vermont
							</option>

							<option value="WA">
								Washington
							</option>
							<option value="WI">
								Wisconsin
							</option>
							<option value="WV">
								West Virginia
							</option>
							<option value="WY">
								Wyoming
							</option>
							<option value="DC">
								Washington DC
							</option>
							<option value="NA">
								----
							</option>

						</select>
					</p>
				    <p>
						<label>
							Zip:
						</label>
						<br />
						<input type="text" name="events.eventAddress.zipCodeSupplement" id="zipCode"
							class="text small" maxlength="5"
							onkeypress="return disableAlphas(event);" />
					</p>
					<p>
						<label>
							Description:
						</label>
						<br />
						<textarea id="wysiwygsmall" name="events.message" class="wysiwyg"></textarea>
						(Maximum of 10,000 characters)
					</p>

					<div class="clear"></div>
					<s:submit type="submit" method="addEvent" cssClass="submit small"
						value="Save" align="left" onclick="javascript:selectDate();" />
				</s:form>
			</div>
		</div>
		
		<script type="text/javascript" language="javascript">
	$('#calendar').addClass('on');
	function selectDate() {
		document.getElementById('eventDate').value = document
				.getElementById('eventDateStr').value;
		var eventEndDate = '';
		if (document.getElementById('eventDateDailyId').value == 'daily'
				&& document.getElementById('eventDateDailyId').checked == true) {
			eventEndDate = document.getElementById('eventEndDateDailyStr').value;
		} else if (document.getElementById('eventDateWeeklyId').value == 'weekly'
				&& document.getElementById('eventDateWeeklyId').checked == true) {
			eventEndDate = document.getElementById('eventEndDateWeeklyStr').value;
		} else if (document.getElementById('eventDateMonthlyId').value == 'monthly'
				&& document.getElementById('eventDateMonthlyId').checked == true) {
			eventEndDate = document.getElementById('eventEndDateMonthlyStr').value;
		} else if (document.getElementById('eventDateYearlyId').value == 'yearly'
				&& document.getElementById('eventDateYearlyId').checked == true) {
			eventEndDate = document.getElementById('eventEndDateYearlyStr').value;
		}
		var eventDate = document.getElementById('eventDate').value;
		var objDate = new Date(eventDate);
		var objDate1 = new Date(eventEndDate);
		document.getElementById('eventDate').value = objDate
				.format('isoDateTime');
		document.getElementById('eventEndDate').value = objDate1
				.format('isoDateTime');
	}
	function checkEventType(eventType) {
		var typeValue = eventType.value;
		var dailyReccurnt = $('dailyRecId');
		var weekRepeat = $('weekRepeatId');
		var monthRepeat = $('RepeatByMonthId');
		var yearRepeat = $('yearRepeatId');
		if (typeValue == 'N') {
			document.getElementById('showRecuringDiv').style.display = 'none';
			document.getElementById('dailyRecId').style.display = 'none';
			document.getElementById('weekRepeatId').style.display = 'none';
			document.getElementById('RepeatByMonthId').style.display = 'none';
			document.getElementById('yearRepeatId').style.display = 'none';
		} else {
			if (typeValue == 'R') {
				document.getElementById('showRecuringDiv').style.display = 'block';
				//document.getElementById('showRecuringDiv').checked=false;
			}
		}
	}
</script>

	</body>
</html>