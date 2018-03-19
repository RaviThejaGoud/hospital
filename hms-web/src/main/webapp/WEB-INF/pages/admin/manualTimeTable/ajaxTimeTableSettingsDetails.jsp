<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<%@ include file="/common/messages.jsp"%>
	<s:form action="ajaxAddTimeTableSettingsDetails" theme="simple" namespace="/timeTable" id="addOrEditTmeTableSettingsInfo" method="post" cssClass="form-horizontal">
		<s:if test="%{timeTableSettings != null}">
			<s:hidden name="timeTableSettingsId" value="%{timeTableSettings.id}"/>
		</s:if>	
		<s:else>
			<s:hidden name="timeTableSettingsId" value="0"/>
		</s:else>
		<div class="space10"></div>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4">
						<span class="required">*</span>Start Time :
					</label>
					<div class="col-md-7">
						<sj:textfield name="timeTableSettings.collegeStartTime" id="collegeStartTime"
							cssClass="required form-control input-medium" maxlength="10" />
					</div>
				</div>
			</div>
			<div class="col-md-5">
				<div class="form-group">
					<label class="control-label col-md-5">
						<span class="required">*</span>End Time :
					</label>
					<div class="col-md-7">
						<sj:textfield name="timeTableSettings.collegeEndTime" id="collegeEndTime" 
							cssClass="required form-control input-medium" maxlength="10" />
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4">
						<span class="required">*</span>No Of Periods Per Day :
					</label>
					<div class="col-md-7">
						<sj:textfield name="timeTableSettings.noOfPeriodsPerDay" id="noOfPeriodsPerDay"
							cssClass="required form-control input-medium" maxlength="10" />
						<span class="help-block">(Enter Period No as 1, 2, 3 ...)</span>
					</div>
				</div>
			</div>
			<div class="col-md-5">
				<div class="form-group">
					<label class="control-label col-md-5">
						<span class="required">*</span>Duration Of Each Period :
					</label>
					<div class="col-md-7">
						<sj:textfield name="timeTableSettings.durationOfEachPeriod" id="durationOfEachPeriod" 
							cssClass="required form-control input-medium" maxlength="10" />
						<span class="help-block">(Enter Minutes as 45, 50, 60 ...)</span>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4">
						Short Break Morning Occurance After Period :
					</label>
					<div class="col-md-7">
						<sj:textfield name="timeTableSettings.shortBreakAfterNoOfPeriodsMorningSession" id="shortBreakAfterNoOfPeriodsMorningSession"
							cssClass="form-control input-medium" maxlength="10" />
						<span class="help-block">(Enter Period No as 1, 2, 3 ...)</span>
					</div>
				</div>
			</div>
			<div class="col-md-5">
				<div class="form-group">
					<label class="control-label col-md-5">
						Short Break Morning Duration :
					</label>
					<div class="col-md-7">
						<sj:textfield name="timeTableSettings.shortBreakMorningSessionDuration" id="shortBreakMorningSessionDuration" 
							cssClass="form-control input-medium" maxlength="10" />
						<span class="help-block">(Enter Minutes as 5, 10, 15 ...)</span>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4">
						Lunch Break After No Of Periods :
					</label>
					<div class="col-md-7">
						<sj:textfield name="timeTableSettings.lunchBreakAfterNoOfPeriods" id="lunchBreakAfterNoOfPeriods"
							cssClass="form-control input-medium" maxlength="10" />
						<span class="help-block">(Enter Period No as 1, 2, 3 ...)</span>
					</div>
				</div>
			</div>
			<div class="col-md-5">
				<div class="form-group">
					<label class="control-label col-md-5">
						Duration Of Lunch Break :
					</label>
					<div class="col-md-7">
						<sj:textfield name="timeTableSettings.durationOfLunchBreak" id="durationOfLunchBreak" 
							cssClass="form-control input-medium" maxlength="10" />
						<span class="help-block">(Enter Minutes as 45, 50, 60 ...)</span>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4">
						Short Break Afternoon Occurance After Period :
					</label>
					<div class="col-md-7">
						<sj:textfield name="timeTableSettings.shortBreakAfterNoOfPeriodsAfternoonSession" id="shortBreakAfterNoOfPeriodsAfternoonSession"
							cssClass="form-control input-medium" maxlength="10" />
						<span class="help-block">(Enter Period No as 1, 2, 3 ...)</span>
					</div>
				</div>
			</div>
			<div class="col-md-5">
				<div class="form-group">
					<label class="control-label col-md-5">
						Short Break Afternoon Duration :
					</label>
					<div class="col-md-7">
						<sj:textfield name="timeTableSettings.shortBreakAfternoonSessionDuration" id="shortBreakAfternoonSessionDuration" 
							cssClass="form-control input-medium" maxlength="10" />
						<span class="help-block">(Enter Minutes as 5, 10, 15 ...)</span>
					</div>
				</div>
			</div>
		</div>
		<div class="space10"></div>
		<s:if test="%{timeTableSettings == null}">
			<div class="form-actions fluid">
				<div class="col-md-offset-2 col-md-6">
					<sj:submit cssClass="btn blue" formIds="addOrEditTmeTableSettingsInfo" value="Submit" onBeforeTopics="timeTableSettingsInfoDiv"
						targets="assignSubjectsToPeriods" validate="true" />
				</div>
			</div>
		</s:if>
	</s:form>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/timeEntry/jquery.timeentry.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		//$('.numeric').numeric();
		$.destroyTopic('timeTableSettingsInfoDiv');
		$("input#collegeStartTime").timeEntry();
		$("input#collegeEndTime").timeEntry();
		$("input#collegeStartTime").focusout(function(){
			if(isNonEmpty($("input#collegeStartTime").val())){
				validateTime();
			}
		}); 
		$("input#collegeEndTime").focusout(function(){
			if($("input#collegeStartTime").val()!=""){
				if(isNonEmpty($("input#collegeEndTime").val())){
					validateTime();
				}
			}
		});
	});
	$.subscribe('timeTableSettingsInfoDiv', function(event, data){
		if ($('#addOrEditTmeTableSettingsInfo').valid()){
			var noOfPeriodsPerDay=$("input#noOfPeriodsPerDay").val();
			var durationOfEachPeriod=$("input#durationOfEachPeriod").val();
			var shortBreakAfterNoOfPeriodsMorningSession=$("input#shortBreakAfterNoOfPeriodsMorningSession").val();
			var shortBreakMorningSessionDuration=$("input#shortBreakMorningSessionDuration").val();
			var lunchBreakAfterNoOfPeriods=$("input#lunchBreakAfterNoOfPeriods").val();
			var durationOfLunchBreak=$("input#durationOfLunchBreak").val();
			var shortBreakAfterNoOfPeriodsAfternoonSession=$("input#shortBreakAfterNoOfPeriodsAfternoonSession").val();
			var shortBreakAfternoonSessionDuration=$("input#shortBreakAfternoonSessionDuration").val();
			if(noOfPeriodsPerDay==0){
				alert("Period number should be not zero.");
    	    	$("input#noOfShortBreaks").val("");
    	    	event.originalEvent.options.submit = false;
				e.preventDefault();
			}
			if(noOfPeriodsPerDay>0){
				if(durationOfEachPeriod==0){
					alert("Period duration should not be zero.");
	    	    	event.originalEvent.options.submit = false;
					e.preventDefault();
				}
			}
			if(noOfPeriodsPerDay<shortBreakAfterNoOfPeriodsMorningSession || noOfPeriodsPerDay<lunchBreakAfterNoOfPeriods || noOfPeriodsPerDay<shortBreakAfterNoOfPeriodsAfternoonSession){
				alert("No of Periods should be greater than morning occurance short break period number, lunch break period number and afternoon occurance short break period number.");
    	    	$("input#noOfShortBreaks").val("");
    	    	event.originalEvent.options.submit = false;
				e.preventDefault();
			}
			if(((shortBreakAfterNoOfPeriodsMorningSession>0) && (lunchBreakAfterNoOfPeriods>0 && lunchBreakAfterNoOfPeriods<=shortBreakAfterNoOfPeriodsMorningSession) || (shortBreakAfterNoOfPeriodsAfternoonSession>0 && shortBreakAfterNoOfPeriodsAfternoonSession<=shortBreakAfterNoOfPeriodsMorningSession))){
				alert("Morning occurance short break period number less than lunch break period number and afternoon occurance short break period number.");
    	    	$("input#shortBreakAfterNoOfPeriodsMorningSession").val("");
    	    	event.originalEvent.options.submit = false;
				e.preventDefault();
			}
			if((lunchBreakAfterNoOfPeriods>0) && ((shortBreakAfterNoOfPeriodsMorningSession>0 && shortBreakAfterNoOfPeriodsMorningSession>=lunchBreakAfterNoOfPeriods) || (shortBreakAfterNoOfPeriodsAfternoonSession>0 && shortBreakAfterNoOfPeriodsAfternoonSession<=lunchBreakAfterNoOfPeriods))){
				alert("Lunch break period number should be greater than morning occurance short break period number and less than afternoon occurance short break period number.");
    	    	$("input#lunchBreakAfterNoOfPeriods").val("");
    	    	event.originalEvent.options.submit = false;
				e.preventDefault();
			}
			if((shortBreakAfterNoOfPeriodsAfternoonSession>0) && ((lunchBreakAfterNoOfPeriods>0 && lunchBreakAfterNoOfPeriods>=shortBreakAfterNoOfPeriodsAfternoonSession) || (shortBreakAfterNoOfPeriodsMorningSession>0 && shortBreakAfterNoOfPeriodsMorningSession>=shortBreakAfterNoOfPeriodsAfternoonSession))){
				alert("Afternoon occurance short break period number more than lunch break period number and morning occurance short break period number.");
    	    	$("input#shortBreakAfterNoOfPeriodsAfternoonSession").val("");
    	    	event.originalEvent.options.submit = false;
				e.preventDefault();
			}
			if(shortBreakAfterNoOfPeriodsMorningSession>0){
				if(shortBreakMorningSessionDuration==0){
					alert("Morning occurance short break duration should not be zero.");
	    	    	event.originalEvent.options.submit = false;
					e.preventDefault();
				}
			}
			if(lunchBreakAfterNoOfPeriods>0){
				if(durationOfLunchBreak==0){
					alert("Lunch break duration should not be zero.");
	    	    	event.originalEvent.options.submit = false;
					e.preventDefault();
				}
			}
			if(shortBreakAfterNoOfPeriodsAfternoonSession>0){
				if(shortBreakAfternoonSessionDuration==0){
					alert("Afternoon occurance short break duration should not be zero.");
	    	    	event.originalEvent.options.submit = false;
					e.preventDefault();
				}
			}
		}else{
			event.originalEvent.options.submit = false;
			e.preventDefault();
		}
	});
	function validateTime(){
	    var startTime = $("input#collegeStartTime").val();
	    var endTime = $("input#collegeEndTime").val();
	    var startHour = parseInt(startTime.substring(0, 2));
	    var startMin = parseInt(startTime.substring(3)+startTime.substring(4));
	    var endHour = parseInt(endTime.substring(0, 2));
	    var endMin = parseInt(endTime.substring(3)+endTime.substring(4));
	    var startAMPM=startTime.substring(6);
	    var endAMPM=endTime.substring(6);
	    var time = new Date(0, 0, 10, startHour, startMin, 0, 0);
	    var time1 = new Date(0, 0, 10, endHour, endMin, 0, 0);
	   	// alert(time+'----'+time1);
	    if(isNonEmpty(startTime) && isNonEmpty(endTime)){
	    	if(startTime == endTime) {
		    	alert("Start Time and End Time should not be same.");
		    	$("input#collegeStartTime").val("");
		    	$("input#collegeEndTime").val("");
		    }else if(startAMPM=="PM" && endAMPM=="AM"){
	 	    	alert("End time should be greater than start time.");
	 	    	$("input#collegeStartTime").val("");
		    	$("input#collegeEndTime").val("");
	    	}else if(startAMPM=="PM" && endAMPM=="PM"){
	    		if(time > time1) {
	     	    	alert("End time should be greater than start time.");
	     	    	$("input#collegeStartTime").val("");
	    	    	$("input#collegeEndTime").val("");
	     	    }
	    	}else if(startAMPM=="AM" && endAMPM=="AM"){
	    		if(time > time1) {
					alert("End time should be greater than start time.");
	     	    	$("input#collegeStartTime").val("");
	    	    	$("input#collegeEndTime").val("");
	     	    }
	    	}
	    }
	}
</script>