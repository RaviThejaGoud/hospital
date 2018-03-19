<%@ include file="/common/taglibs.jsp"%>
<div class="grid_14 commonFormTabs">
<span id="startDateSpan" class="<s:property value='startDate'/>"></span>
<span id="endDateSpan" class="<s:property value='endDate'/>"></span>
<span id="toDateSpan" class="<s:property value='toDate'/>"></span>
	<s:form id="addNewHostelEvent" action="ajaxAddHostelEvent" method="post"
		theme="css_xhtml" namespace="/hostel">
		<s:hidden name="startDate" value="%{hostelEvents.startDate}"></s:hidden>
		<s:hidden name="tempId" />
		<h1>
			<s:if test="%{tempId != 0}">
				Update Event
			</s:if>
			<s:else>
				Create Event
			</s:else>
		</h1>
		<fieldset>
			<div class="grid_13">
				<div class="grid_13">
					<div class="grid_7 left">
						<div class="grid_7">
							<s:select list="weekDayList" listKey="id" listValue="dayName"
								label="select Day" tabindex="1" name="hostelEvents.eventDayId"
								headerKey="" headerValue="- Select Event Day-"
								requiredposition="first">
							</s:select>
						</div>
						<div class="grid_7">
							<s:if test="%{tempId != 0}">
								<s:if test="%{toDate.compareTo(hostelEvents.startDate) > 0}">
								<s:hidden name="hostelEvents.startDate"></s:hidden>	
									<sj:datepicker id="startDate" label="Event Start Date" showOn="true"
										name="hostelEvents.startDate" cssClass="textfield date_picker" disabled="true"/>
								</s:if>
								<s:else>
									<sj:datepicker id="startDate" label="Event Start Date"
										onchange="verifyDate();checkStartTimeEndTImeValidation();" readonly="true" 
										name="hostelEvents.startDate" tabindex="3" 
										cssClass="textfield required" required="true" minDate="1" />
								</s:else>
							</s:if>
							<s:else>
								<sj:datepicker id="startDate" label="Event Start Date"
									onchange="verifyDate();checkStartTimeEndTImeValidation();"  readonly="true"
									name="hostelEvents.startDate" tabindex="3"
									cssClass="textfield required" required="true" minDate="1" />
							</s:else>
						</div>
						<div class="grid_7">
							<s:select name="hostelEvents.startTime" label="Start Time" id="startTime"
								cssClass="textfield required" required="true" tabindex="5" onchange="checkStartTimeEndTImeValidation()" headerKey="" headerValue="-Select-"
								list="#{'05:00 AM':'05:00 AM','05:15 AM':'05:15 AM','05:30 AM':'05:30 AM','05:45 AM':'05:45 AM', '06:00 AM':'06:00 AM', '06:15 AM':'06:15 AM','06:30 AM':'06:30 AM','06:45 AM':'06:45 AM','07:00 AM':'07:00 AM','07:15 AM':'07:15 AM','07:30 AM':'07:30 AM','07:45 AM':'07:45 AM','08:00 AM':'08:00 AM','08:15 AM':'08:15 AM','08:30 AM':'08:30 AM','08:45 AM':'08:45 AM','09:00 AM':'09:00 AM','09:15 AM':'09:15 AM','09:30 AM':'09:30 AM','09:45 AM':'09:45 AM','10:00 AM':'10:00 AM','10:15 AM':'10:15 AM','10:30 AM':'10:30 AM','10:45 AM':'10:45 AM','11:00 AM':'11:00 AM','11:15 AM':'11:15 AM','11:30 AM':'11:30 AM','11:45 AM':'11:45 AM','12:00 AM':'12:00 PM','12:15 PM':'12:15 PM','12:30 PM':'12:30 PM','12:45 PM':'12:45 PM','01:00 PM':'01:00 PM','01:15 PM':'01:15 PM','01:30 PM':'01:30 PM','01:45 PM':'01:45 PM','02:00 PM':'02:00 PM','02:15 PM':'02:15 PM','02:30 PM':'02:30 PM','02:45 PM':'02:45 PM','03:00 PM':'03:00 PM','03:15 PM':'03:15 PM','03:30 PM':'03:30 PM','03:45 PM':'03:45 PM','04:00 PM':'04:00 PM','04:15 PM':'04:15 PM','04:30 PM':'04:30 PM','04:45 PM':'04:45 PM','05:00 PM':'05:00 PM','05:15 PM':'05:15 PM','05:30 PM':'05:30 PM','05:45 PM':'05:45 PM','06:00 PM':'06:00 PM','06:15 PM':'06:15 PM','06:30 PM':'06:30 PM','06:45 PM':'06:45 PM','07:00 PM':'07:00 PM','07:15 PM':'07:15 PM','07:30 PM':'07:30 PM','07:45 PM':'07:45 PM','08:00 PM':'08:00 PM','08:15 PM':'08:15 PM','08:30 PM':'08:30 PM','08:45 PM':'08:45 PM','09:00 PM':'09:00 PM','09:15 PM':'09:15 PM','09:30 PM':'09:30 PM','09:45 PM':'09:45 PM','10:00 PM':'10:00 PM','10:15 PM':'10:15 PM','10:30 PM':'10:30 PM','10:45 PM':'10:45 PM','11:00 PM':'11:00 PM','11:15 PM':'11:15 PM','11:30 PM':'11:30 PM','11:45 PM':'11:45 PM'}"></s:select>
						</div>
						<div class="grid_7 campaignText">
							<sj:textarea name="hostelEvents.eventDescription" 
								label="Event Description" tabindex="7" id="discription"
								cssClass="text small  word_count"></sj:textarea>
						</div>
					</div>
					<div class="grid_6">
						<div class="grid_6">
							<sj:textfield name="hostelEvents.eventName" id="title"
								label="Event Name" tabindex="2" cssClass="textfield required"
								required="true" maxlength="40"></sj:textfield>
						</div>
						<div class="grid_6">
							<sj:datepicker id="endDate" label="Event End Date"
								readonly="true" name="hostelEvents.endDate"
								onchange="verifyDate();checkStartTimeEndTImeValidation();" tabindex="4"
								cssClass="textfield required" required="true" minDate="1" />
						</div>
						<div class="grid_6">
							<s:select name="hostelEvents.endTime" label="End Time " id="endTime"
								cssClass="textfield required" required="true" tabindex="6" onchange="checkStartTimeEndTImeValidation()" headerKey="" headerValue="-Select-"
								list="#{'05:00 AM':'05:00 AM','05:15 AM':'05:15 AM','05:30 AM':'05:30 AM','05:45 AM':'05:45 AM', '06:00 AM':'06:00 AM', '06:15 AM':'06:15 AM','06:30 AM':'06:30 AM','06:45 AM':'06:45 AM','07:00 AM':'07:00 AM','07:15 AM':'07:15 AM','07:30 AM':'07:30 AM','07:45 AM':'07:45 AM','08:00 AM':'08:00 AM','08:15 AM':'08:15 AM','08:30 AM':'08:30 AM','08:45 AM':'08:45 AM','09:00 AM':'09:00 AM','09:15 AM':'09:15 AM','09:30 AM':'09:30 AM','09:45 AM':'09:45 AM','10:00 AM':'10:00 AM','10:15 AM':'10:15 AM','10:30 AM':'10:30 AM','10:45 AM':'10:45 AM','11:00 AM':'11:00 AM','11:15 AM':'11:15 AM','11:30 AM':'11:30 AM','11:45 AM':'11:45 AM','12:00 PM':'12:00 PM','12:15 PM':'12:15 PM','12:30 PM':'12:30 PM','12:45 PM':'12:45 PM','01:00 PM':'01:00 PM','01:15 PM':'01:15 PM','01:30 PM':'01:30 PM','01:45 PM':'01:45 PM','02:00 PM':'02:00 PM','02:15 PM':'02:15 PM','02:30 PM':'02:30 PM','02:45 PM':'02:45 PM','03:00 PM':'03:00 PM','03:15 PM':'03:15 PM','03:30 PM':'03:30 PM','03:45 PM':'03:45 PM','04:00 PM':'04:00 PM','04:15 PM':'04:15 PM','04:30 PM':'04:30 PM','04:45 PM':'04:45 PM','05:00 PM':'05:00 PM','05:15 PM':'05:15 PM','05:30 PM':'05:30 PM','05:45 PM':'05:45 PM','06:00 PM':'06:00 PM','06:15 PM':'06:15 PM','06:30 PM':'06:30 PM','06:45 PM':'06:45 PM','07:00 PM':'07:00 PM','07:15 PM':'07:15 PM','07:30 PM':'07:30 PM','07:45 PM':'07:45 PM','08:00 PM':'08:00 PM','08:15 PM':'08:15 PM','08:30 PM':'08:30 PM','08:45 PM':'08:45 PM','09:00 PM':'09:00 PM','09:15 PM':'09:15 PM','09:30 PM':'09:30 PM','09:45 PM':'09:45 PM','10:00 PM':'10:00 PM','10:15 PM':'10:15 PM','10:30 PM':'10:30 PM','10:45 PM':'10:45 PM','11:00 PM':'11:00 PM','11:15 PM':'11:15 PM','11:30 PM':'11:30 PM','11:45 PM':'11:45 PM'}"></s:select>
						</div>
					</div>
				</div>
				<div class="grid_12">
					 <s:url id="viewEventsUrl" action="ajaxViewHostelEvents"
						includeParams="all" escapeAmp="false" namespace="/hostel">
						<s:param name="tempId" value="0" />
					</s:url>
					<sj:a href="%{viewEventsUrl}" cssClass="cancelButton"
						indicator="indicator" targets="stepEvents">Cancel</sj:a>
					<sj:submit    value="Submit"
						cssClass="submit small" formIds="addNewHostelEvent" targets="stepEvents"
						indicator="indicator" validate="true" resetForm="true"/>
				</div>
			</div>
		</fieldset>
	</s:form>
</div>
<script type="text/javascript">
$(document).ready(function() {
	var startDate=$('span#startDateSpan').attr("class");
	var endDate=$('span#endDateSpan').attr("class");
	var toDayDate=$('span#toDateSpan').attr("class");
	if(isNonEmpty(startDate) && isNonEmpty(endDate) && isNonEmpty(toDayDate)){
		if (Date.parse(toDayDate) >=  Date.parse(startDate) && Date.parse(toDayDate) <=  Date.parse(endDate)) {
			$('#startDate').datepicker( "option" , 'minDate',toDayDate);
			$('#startDate').datepicker( "option" , 'maxDate',endDate);
			$('#endDate').datepicker( "option" , 'minDate',toDayDate);
			$('#endDate').datepicker( "option" , 'maxDate',endDate);
		}else if(Date.parse(toDayDate) <  Date.parse(startDate)){
			$('#startDate').datepicker( "option" , 'minDate',startDate);
			$('#startDate').datepicker( "option" , 'maxDate',endDate);
			$('#endDate').datepicker( "option" , 'minDate',startDate);
			$('#endDate').datepicker( "option" , 'maxDate',endDate);
		}else if(Date.parse(toDayDate) >  Date.parse(endDate)){
			$("#startDate").datepicker('disable');
			$("#endDate").datepicker('disable');
		}
	}
});
function verifyDate() {
	var startDate = document.getElementById('startDate').value;
	var endDate = document.getElementById('endDate').value;
	if(isNonEmpty(startDate) && isNonEmpty(endDate)){
		if (endDate < startDate) {
			alert("Your end date is equal or more than your start date.");
			$('#endDate').val("");
		}	
	}
}
function checkStartTimeEndTImeValidation(){
	var startDate = $('#startDate').val();
	var endDate = $('#endDate').val();
	if(isNonEmpty(startDate) && isNonEmpty(endDate)){
		var startTime = $('#startTime').val();
		var endTime= $('#endTime').val();
		if(isNonEmpty(startTime) && isNonEmpty(endTime)){
			var startDate = new Date(startDate +' '+ startTime);
		   	var endDate=new Date(endDate +' '+ endTime);
			if(endDate <= startDate){
			     alert("Start time should be less than end time.");
			     $('#endTime').val('');
			}
		}		
	}
}
changePageTitle("Create Hosetel Event");
</script>
