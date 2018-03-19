<%@ include file="/common/taglibs.jsp"%>
	 
<div class="form-body">
	<span id="startDateSpan" class="<s:property value='startDate'/>"></span>
	<span id="endDateSpan" class="<s:property value='endDate'/>"></span>
	<span id="toDateSpan" class="<s:property value='toDate'/>"></span>
	<s:form id="addNewEvent" action="ajaxAddEvent" method="post"
		theme="simple" cssClass="form-horizontal" namespace="/admin">
		
		<s:if test="%{tempId != 0}">
			<h4 class="modal-title">
				Update Event
			</h4>
			<s:hidden name="eventsVO.id" />
		</s:if>
		<s:else>
			<s:hidden name="eventsVO.id" value="0" /> 
		</s:else>

		
		<s:hidden id="classNameIds" name="eventsVO.studyClassIds" />
		<s:hidden id="nonTeachingRoleIds" name="eventsVO.nonTeachingRoleIds" />
		<s:hidden id="userAcademicYearId" name="eventsVO.academicYearId"  value="%{userAcademicYearId}"/>
		
		<s:hidden id="includeNonTeachingStaff" name="eventsVO.includeNonTeachingStaff"/>
		
		
		
			
				<%-- <s:if test='%{tempList2.size >0}'>
					<div class="form-group">
						<label class="conLable col-md-3 control-label">
							Class With Out Students :
						</label>
						<div class="col-md-12">
							<div class="checkbox-list">
								<s:checkboxlist name="chkBoxNotStudents"
									list="tempList2" listKey="id"
									listValue="classAndSection" theme="ems"
									cssClass="small" disabled="true" />
							</div>
						</div>
					</div>
				</s:if> --%>
											
											
		<s:hidden name="startDate" value="%{events.startDate}"></s:hidden>
		<div class="row">
			<%-- <div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4">
						Select Event :
					</label>
					<div class="col-md-6">
						<s:select list="objectList" listKey="id" listValue="eventName"
							tabindex="1" name="events.smsEventId" headerKey=""
							headerValue="- Select Sms Event-" cssClass="form-control"
							onchange="javascript:onClassChange(this.value);">
						</s:select>
					</div>
				</div>
			</div> --%>
			<div class="col-md-6">
				<div class="form-group">
						<label class="control-label col-md-4"><span class="required">*</span>Event Name :</label>
						<div class="col-md-6">
						<sj:textfield name="eventsVO.eventName" id="title"
							 tabindex="2" cssClass="form-control required"
							 maxlength="40"></sj:textfield>
						</div>
					</div>
			</div>
		</div>
		
		<div class="row">
			<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> Event for : </label>
						<div class="col-md-5">
							<s:select id="eventFor" headerKey="" tabindex="23"
								headerValue="- Select -" cssClass="form-control" onchange="getEventForFun(this.value)"
								name="eventsVO.eventFor"
								list="#{'all':'All','cs':'Class & Section','s':'Staff'}" />
						</div>
					</div>
				</div>
		</div>
		<div id="classSectionDivId" style="display: none;">
			<s:if test='%{studyClassList.size >1}'>
			<div class="form-group">
				<div class="col-md-12">
					<div class="checkbox-list">
						<label class="checkbox-inline">Select Class: 
								<input type="checkbox" name=""
										value="" onClick="checkAllClasses()"
										class="checkbox allClasses">
							(Select All)
						</label>
					</div>
				</div>
			</div>
		</s:if>
			<div class="form-group">
					<label class="conLable col-md-3 control-label">
						<!-- Class With Students : -->
					</label>
					<div class="col-md-12">
						<div class="checkbox-list">
							<s:checkboxlist name="chkBoxSelectedIds"
								list="studyClassList" listKey="id"
								listValue="classAndSection" theme="ems" cssClass="small" />
						</div>
					</div>
				</div>
				
				
				<div class="row form-group">
					<label class="col-md-4 control-label" >
						Do you need to include Non teaching Staff : </label>
					<div class="col-md-8">
						<div class="radio-list">
							<s:if test='%{eventsVO.includeNonTeachingStaff == "N"}'>
								<label class="radio-inline"> <input type="radio" checked="checked" value="N" onclick="showNonTeachingStaff(this.value);" name="includeNonTeachingStaff">
									No </label> 
								<label class="radio-inline"> <input type="radio" value="Y" onclick="showNonTeachingStaff(this.value);" name="includeNonTeachingStaff"> Yes
								</label>
							</s:if>
							<s:elseif test='%{eventsVO.includeNonTeachingStaff == "Y"}'>
								<label class="radio-inline"> <input type="radio"  value="N" onclick="showNonTeachingStaff(this.value);" name="includeNonTeachingStaff">
									No </label> 
								<label class="radio-inline"> <input type="radio" checked="checked" value="Y" onclick="showNonTeachingStaff(this.value);" name="includeNonTeachingStaff"> Yes
								</label>
							</s:elseif>
							<s:else>
								<label class="radio-inline"> <input type="radio" checked="checked" value="N" onclick="showNonTeachingStaff(this.value);" name="includeNonTeachingStaff">
									No </label> 
								<label class="radio-inline"> <input type="radio" value="Y" onclick="showNonTeachingStaff(this.value);" name="includeNonTeachingStaff"> Yes
								</label>
							</s:else>
						</div>
					</div>
				</div>
				
				<div class="form-group" id="nonTeachingStaffDivId" style="display: none;">
					<label class="conLable col-md-3 control-label">
						<!-- Class With Students : -->
					</label>
					<div class="col-md-12">
						<div class="checkbox-list">
							<s:checkboxlist name="chkBoxNonTeachingRoleIds" list="nonTeachingRoleMap" theme="ems" cssClass="small" />
						</div>
					</div>
				</div>

		</div>
		
		<div id="staffDivId" style="display: none;">
			<div class="row">
				<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"> Staff Type : </label>
							<div class="col-md-5">
								<s:select id="staffEvent" tabindex="23" cssClass="form-control" name="eventsVO.staffEvent"
									list="#{'A':'All Staff','T':'Teaching Staff','N':'Non Teaching Staff'}" />
							</div>
						</div>
					</div>
			</div>
		
		</div>
			<div class="row">
				<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4">
						<span class="required">*</span>Event Start Date :
					</label>
					<div class="col-md-6">
						<s:if test="%{tempId != 0}">
							<s:if test="%{toDate.compareTo(eventsVO.startDateTime) > 0}">
								<div data-date-format="mm/dd/yyyy"
									class="input-group input-medium date date-picker">
									<input type="text" id="startDate" readonly=""
										class="form-control required fdate"  name="eventsVO.startDateTime" value='<s:property value="eventsVO.eventStartDate"/>' />
									<span class="dateInput input-group-btn">
										<button type="button" class="btn default">
											<i class="fa fa-calendar"></i>
										</button> </span>
								</div>
								<span class="help-block">(MM/DD/YYYY)</span>
							</s:if>
							<s:else>
								<div data-date-format="mm/dd/yyyy"
									class="input-group input-medium date date-picker">
									<input type="text" id="startDate" readonly=""
										class="form-control required fdate"  
										onchange="verifyDate();"
										 name="eventsVO.startDateTime" value='<s:property value="eventsVO.eventStartDate"/>'>
									<span class="dateInput input-group-btn">
										<button type="button" class="btn default">
											<i class="fa fa-calendar"></i>
										</button> </span>
								</div>
								<span class="help-block">(MM/DD/YYYY)</span>
							</s:else>
						</s:if>
						<s:else>
							<div data-date-format="mm/dd/yyyy"
								class="input-group input-medium date date-picker">
								<input type="text" id="startDate" readonly=""
									class="form-control required"
									onchange="verifyDate();"
									 name="eventsVO.startDateTime">
								<span class="dateInput input-group-btn">
									<button type="button" class="btn default">
										<i class="fa fa-calendar"></i>
									</button> </span>
							</div>
							<span class="help-block">(MM/DD/YYYY)</span>
						</s:else>
					</div>
				</div>
				</div>
				<div class="col-md-6">
					<s:if test="%{tempId != 0}">
						<div class="form-group ">
							<label class="control-label col-md-4"><span class="required">*</span>Event End Date :</label>
							<div class="col-md-6">
								<div data-date-format="mm/dd/yyyy"
									class="input-group input-medium date date-picker">
									<input type="text" id="endDate" readonly=""
										class="form-control required fdate" value='<s:property value="eventsVO.eventEndDate"/>'
										onchange="verifyDate();"
										 name="eventsVO.endDateTime">
									<span class="dateInput input-group-btn">
										<button type="button" class="btn default">
											<i class="fa fa-calendar"></i>
										</button> </span>
								</div>
								<span class="help-block">(MM/DD/YYYY)</span>
							</div>
						</div>
					</s:if>
					<s:else>
						<div class="form-group">
							<label class="control-label col-md-4"><span class="required">*</span>Event End Date :</label>
							<div class="col-md-6">
								<div data-date-format="mm/dd/yyyy"
									class="input-group input-medium date date-picker">
									<input type="text" id="endDate" readonly="" class="form-control required" 
										onchange="verifyDate();"  name="eventsVO.endDateTime">
									<span class="dateInput input-group-btn">
										<button type="button" class="btn default">
											<i class="fa fa-calendar"></i>
										</button> </span>
								</div>
								<span class="help-block">(MM/DD/YYYY)</span>
							</div>
						</div>
					</s:else>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"><span class="required">*</span>Start Time :</label>
						<div class="col-md-6">
						<s:select name="eventsVO.startTime" 
							id="startTime" cssClass="form-control required" 
							tabindex="5" onchange="checkStartTimeEndTImeValidation();"
							headerKey="" headerValue="-Select-"
							list="#{'05:00 AM':'05:00 AM','05:15 AM':'05:15 AM','05:30 AM':'05:30 AM','05:45 AM':'05:45 AM', '06:00 AM':'06:00 AM', '06:15 AM':'06:15 AM','06:30 AM':'06:30 AM','06:45 AM':'06:45 AM','07:00 AM':'07:00 AM','07:15 AM':'07:15 AM','07:30 AM':'07:30 AM','07:45 AM':'07:45 AM','08:00 AM':'08:00 AM','08:15 AM':'08:15 AM','08:30 AM':'08:30 AM','08:45 AM':'08:45 AM','09:00 AM':'09:00 AM','09:15 AM':'09:15 AM','09:30 AM':'09:30 AM','09:45 AM':'09:45 AM','10:00 AM':'10:00 AM','10:15 AM':'10:15 AM','10:30 AM':'10:30 AM','10:45 AM':'10:45 AM','11:00 AM':'11:00 AM','11:15 AM':'11:15 AM','11:30 AM':'11:30 AM','11:45 AM':'11:45 AM','12:00 PM':'12:00 PM','12:15 PM':'12:15 PM','12:30 PM':'12:30 PM','12:45 PM':'12:45 PM','01:00 PM':'01:00 PM','01:15 PM':'01:15 PM','01:30 PM':'01:30 PM','01:45 PM':'01:45 PM','02:00 PM':'02:00 PM','02:15 PM':'02:15 PM','02:30 PM':'02:30 PM','02:45 PM':'02:45 PM','03:00 PM':'03:00 PM','03:15 PM':'03:15 PM','03:30 PM':'03:30 PM','03:45 PM':'03:45 PM','04:00 PM':'04:00 PM','04:15 PM':'04:15 PM','04:30 PM':'04:30 PM','04:45 PM':'04:45 PM','05:00 PM':'05:00 PM','05:15 PM':'05:15 PM','05:30 PM':'05:30 PM','05:45 PM':'05:45 PM','06:00 PM':'06:00 PM','06:15 PM':'06:15 PM','06:30 PM':'06:30 PM','06:45 PM':'06:45 PM','07:00 PM':'07:00 PM','07:15 PM':'07:15 PM','07:30 PM':'07:30 PM','07:45 PM':'07:45 PM','08:00 PM':'08:00 PM','08:15 PM':'08:15 PM','08:30 PM':'08:30 PM','08:45 PM':'08:45 PM','09:00 PM':'09:00 PM','09:15 PM':'09:15 PM','09:30 PM':'09:30 PM','09:45 PM':'09:45 PM','10:00 PM':'10:00 PM','10:15 PM':'10:15 PM','10:30 PM':'10:30 PM','10:45 PM':'10:45 PM','11:00 PM':'11:00 PM','11:15 PM':'11:15 PM','11:30 PM':'11:30 PM','11:45 PM':'11:45 PM','12:00 AM':'12:00 AM'}"></s:select>
					
						</div>
					</div>
				</div>
				<div class="col-md-6">
					 <div class="form-group">
						<label class="control-label col-md-4"><span class="required">*</span>End Time :</label>
						<div class="col-md-6">
						<s:select name="eventsVO.endTime"   id="endTime"
							cssClass="form-control required"   tabindex="6"
							onchange="checkStartTimeEndTImeValidation();" headerKey=""
							headerValue="-Select-"
							list="#{'05:00 AM':'05:00 AM','05:15 AM':'05:15 AM','05:30 AM':'05:30 AM','05:45 AM':'05:45 AM', '06:00 AM':'06:00 AM', '06:15 AM':'06:15 AM','06:30 AM':'06:30 AM','06:45 AM':'06:45 AM','07:00 AM':'07:00 AM','07:15 AM':'07:15 AM','07:30 AM':'07:30 AM','07:45 AM':'07:45 AM','08:00 AM':'08:00 AM','08:15 AM':'08:15 AM','08:30 AM':'08:30 AM','08:45 AM':'08:45 AM','09:00 AM':'09:00 AM','09:15 AM':'09:15 AM','09:30 AM':'09:30 AM','09:45 AM':'09:45 AM','10:00 AM':'10:00 AM','10:15 AM':'10:15 AM','10:30 AM':'10:30 AM','10:45 AM':'10:45 AM','11:00 AM':'11:00 AM','11:15 AM':'11:15 AM','11:30 AM':'11:30 AM','11:45 AM':'11:45 AM','12:00 PM':'12:00 PM','12:15 PM':'12:15 PM','12:30 PM':'12:30 PM','12:45 PM':'12:45 PM','01:00 PM':'01:00 PM','01:15 PM':'01:15 PM','01:30 PM':'01:30 PM','01:45 PM':'01:45 PM','02:00 PM':'02:00 PM','02:15 PM':'02:15 PM','02:30 PM':'02:30 PM','02:45 PM':'02:45 PM','03:00 PM':'03:00 PM','03:15 PM':'03:15 PM','03:30 PM':'03:30 PM','03:45 PM':'03:45 PM','04:00 PM':'04:00 PM','04:15 PM':'04:15 PM','04:30 PM':'04:30 PM','04:45 PM':'04:45 PM','05:00 PM':'05:00 PM','05:15 PM':'05:15 PM','05:30 PM':'05:30 PM','05:45 PM':'05:45 PM','06:00 PM':'06:00 PM','06:15 PM':'06:15 PM','06:30 PM':'06:30 PM','06:45 PM':'06:45 PM','07:00 PM':'07:00 PM','07:15 PM':'07:15 PM','07:30 PM':'07:30 PM','07:45 PM':'07:45 PM','08:00 PM':'08:00 PM','08:15 PM':'08:15 PM','08:30 PM':'08:30 PM','08:45 PM':'08:45 PM','09:00 PM':'09:00 PM','09:15 PM':'09:15 PM','09:30 PM':'09:30 PM','09:45 PM':'09:45 PM','10:00 PM':'10:00 PM','10:15 PM':'10:15 PM','10:30 PM':'10:30 PM','10:45 PM':'10:45 PM','11:00 PM':'11:00 PM','11:15 PM':'11:15 PM','11:30 PM':'11:30 PM','11:45 PM':'11:45 PM','12:00 AM':'12:00 AM'}"></s:select>
			
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
				<div class="form-group ">
						<label class="control-label col-md-4">Event Description :</label>
						<div class="col-md-6">
						<sj:textarea name="eventsVO.eventDescription"
							label="Event Description" tabindex="7" id="eventDescription"
							cssClass="form-control word_count"></sj:textarea>
						</div>
					</div>
				</div>
				
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> Event Address : </label>
						<div class="col-md-5">
							<sj:textarea name="eventsVO.address"
							label="Event Description" tabindex="7" id="eventAddress"
							cssClass="form-control word_count"></sj:textarea>
						</div>
					</div>
				</div>
				
				<%-- <div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4">Do you need holiday for this event :</label>
						<div class="col-md-5">
							<s:if test='%{events.status == "H"}'>
								<input type="checkbox" name="events.status" checked="checked" />
							</s:if>
							<s:else>
								<s:checkbox name="events.status" />
							</s:else>
							<span class="help-block">(If you want provide holiday for
								this event please select this check box.)</span>
						</div>
					</div>
				</div> --%>
			</div>
			
		   <div class="form-actions fluid">
			<div class="col-md-offset-2 col-md-9">
				<sj:submit   value="Submit" cssClass="submitBt btn blue" onBeforeTopics="newEventForm"
					formIds="addNewEvent" targets="mainContentDiv" indicator="indicator" validate="true"/>
				<s:url id="viewEventsUrl" action="ajaxViewEvents" includeParams="all" escapeAmp="false" namespace="/admin">
					<s:param name="tempId" value="0" />
				</s:url>
				<sj:a href="%{viewEventsUrl}" cssClass="btn default" targets="mainContentDiv">Cancel</sj:a>
			</div>
		</div>
	</s:form>
 </div>
<s:if test="%{tempId != 0}">
	</div>
</s:if>
<script type="text/javascript">
$("input:checkbox, input:radio").uniform();
$(document).ready(
		function() {
		//FormComponents.init();
		$.destroyTopic('newEventForm');
			changePageTitle("Create Event");
			/*var startDate = $('span#startDateSpan').attr("class");
			var endDate = $('span#endDateSpan').attr("class");
			var toDayDate = $('span#toDateSpan').attr("class");
			if (isNonEmpty(startDate) && isNonEmpty(endDate) && isNonEmpty(toDayDate)) {
				if (Date.parse(toDayDate) >= Date.parse(startDate)
						&& Date.parse(toDayDate) <= Date.parse(endDate)) {
					$('#startDate').datepicker("option", 'minDate', toDayDate);
					$('#startDate').datepicker("option", 'maxDate', endDate);
					$('#endDate').datepicker("option", 'minDate', toDayDate);
					$('#endDate').datepicker("option", 'maxDate', endDate);
				} else if (Date.parse(toDayDate) < Date.parse(startDate)) {
					$('#startDate').datepicker("option", 'minDate', startDate);
					$('#startDate').datepicker("option", 'maxDate', endDate);
					$('#endDate').datepicker("option", 'minDate', startDate);
					$('#endDate').datepicker("option", 'maxDate', endDate);
				} else if (Date.parse(toDayDate) > Date.parse(endDate)) {
					$("#startDate").datepicker('disable');
					$("#endDate").datepicker('disable');
				}
			}*/
			
			eventsFor = '<s:property value="eventsVO.eventFor"/>';
			getEventForFun(eventsFor);
			
			includeNonTeachingStaff = '<s:property value="eventsVO.includeNonTeachingStaff"/>';
			showNonTeachingStaff(includeNonTeachingStaff);
			
			//alert("studyClass Length:" +events.studyClass.length);
			
			var unchecked = $("input[name=chkBoxSelectedIds]:unchecked").length;
			if (unchecked >0) {
				$(".allClasses").parent('span').removeClass("checked");
				$(".allClasses").attr("checked", false);
			}else{
				$(".allClasses").parent('span').addClass("checked");
				$(".allClasses").attr("checked",true);
			}
		
			
			var startDate = $('span#startDateSpan').attr("class");
			var endDate = $('span#endDateSpan').attr("class");
			dateRestrictionWithinAcademicYear(startDate,endDate);
			FormComponents.init();
			
					
		});
		
$("input[name=chkBoxSelectedIds]").click(function() {
	if ($("input[name=chkBoxSelectedIds]:unchecked").length > 0) {
		$(".allClasses").parent('span').removeClass("checked");
		$(".allClasses").attr("checked", false);
	} else {	
		$(".allClasses").parent('span').addClass("checked");
		$(".allClasses").attr("checked", true);
	}
});

 function verifyDate() {
	var startDate = $('#startDate').val();
	var endDate =  $('#endDate').val();
	if (isNonEmpty(startDate) && isNonEmpty(endDate)) {
		if (endDate < startDate) {
			alert("Your end date is equal or more than your start date.");
			$('#endDate').val("");
		}
	}
}
function onClassChange(serviceId) {
	var smsEventId = serviceId;
	var metricsAdminURL = jQuery.url
			.getChatURL("/admin/ajaxGetAcademicSmsEvents.do?smsEventId="
					+ smsEventId);
	$.ajax( {
		url : metricsAdminURL,
		cache : false,
		dataType : 'json',
		success : function(response) {

			if (response.data) {
				var data = response.data;
				var title;
				var smsEventId;
				var startDate;
				var endDate;
				var inputObj;
				if (data.length >= 1) {
					for ( var i = 0; i < data.length; i++) {
						smsEventId = data[i].smsEventId;
						title = data[i].title;
						startDate = data[i].startDate;
						endDate = data[i].endDate;
						$("#title").val(title);
						$("#startDate").val(startDate);
						$("#endDate").val(endDate);
					}
				}
			}
		}
	});
}
function checkStartTimeEndTImeValidation() {
	var startDate = $('#startDate').val();
	var endDate = $('#endDate').val();
	if (isNonEmpty(startDate) && isNonEmpty(endDate)) {
		var startTime = $('#startTime').val();
		var endTime = $('#endTime').val();
		if (isNonEmpty(startTime) && isNonEmpty(endTime)) {
		var selectedStartTime = new Date(startDate+" " + startTime);
		var selectEndTime = new Date(endDate+" " + endTime);
			if (selectEndTime <= selectedStartTime) {
				alert("Start time should be less than end time.");
				$('#endTime').val('');
			}
		}

	}else{
		if(startDate == ''){
			alert("Please select start date");
		}else if(endDate == ''){
			alert("Please select end date");
		}
		$('#startTime').val('');
		$('#endTime').val('');
	}
}

$.subscribe('newEventForm', function(event, data) {
	if ($('#addNewEvent').valid())
	{
		var eventFor = $('#eventFor').val();
		
		if("cs" == eventFor)
		{
			if ($("input[name=chkBoxSelectedIds]:checked").length > 0)
			{
				var classIds = $("input[name=chkBoxSelectedIds]:checked");
				var selectedClassIds = '';
				if (classIds.length > 0) {
					selectedClassIds = '(';
					for ( var i = 0; i < classIds.length; i++) {
						selectedClassIds += classIds[i].value + ', ';
					}
					selectedClassIds += '0)';
				}
				$("#classNameIds").val(selectedClassIds);
				$('button.close').click();
			} else {
				if ($("input[name=chkBoxSelectedIds]:checked").length == 0) {
					alert("Please select at least one Class");
					event.originalEvent.options.submit=false;
				}
			}
			
			var includeNonTeachingStaffId=$('input:radio[name=includeNonTeachingStaff]:checked').val();
			
			if("Y" == includeNonTeachingStaffId)
			{
				if ($("input[name=chkBoxNonTeachingRoleIds]:checked").length > 0)
				{
					var nonTeachingRoleIds = $("input[name=chkBoxNonTeachingRoleIds]:checked");
					var selectedNonTeachingRoleIds = '';
					if (nonTeachingRoleIds.length > 0) {
						selectedNonTeachingRoleIds = '(';
						for ( var i = 0; i < nonTeachingRoleIds.length; i++) {
							selectedNonTeachingRoleIds +="'"+ nonTeachingRoleIds[i].value +"'"+ ', ';
						}
						selectedNonTeachingRoleIds += "'0')";
					}
					$("#nonTeachingRoleIds").val(selectedNonTeachingRoleIds);
					$('button.close').click();
				} else {
					if ($("input[name=chkBoxNonTeachingRoleIds]:checked").length == 0) {
						alert("Please select at least one non teaching role");
						event.originalEvent.options.submit=false;
					}
				}
			}
			$("#includeNonTeachingStaff").val(includeNonTeachingStaffId);
			
			return true;
		}
		
		$('button.close').click();
		return true;
	}else{
		event.originalEvent.options.submit=false;
	}
});

function checkAllClasses() {
	if ($(".allClasses").is(':checked')){
	    $("[name='chkBoxSelectedIds']").each(function(){
		   $(this).parent('span').addClass('checked');
		    $(this).attr("checked", "true");
		});
	}
	else{
	 $("[name='chkBoxSelectedIds']").each(function(){
		   $(this).parent('span').removeClass('checked');
		    $(this).removeAttr("checked");
		});
	}	
}

function getEventForFun(eventFor)
{
	if("cs" == eventFor)
	{
		$('#staffDivId').hide();
		$('#classSectionDivId').show();
	}
	else if("s" == eventFor)
	{
		$('#classSectionDivId').hide();
		$('#staffDivId').show();
	}
	else
	{
		$('#staffDivId').hide();
		$('#classSectionDivId').hide();
	}
	
}

function showNonTeachingStaff(staffType) {
	
	if(isNonEmpty(staffType))
	{
		if (staffType == 'N') {
			$('#nonTeachingStaffDivId').hide();
		}else {
			$('#nonTeachingStaffDivId').show();
		}
	}
	
}

</script>
