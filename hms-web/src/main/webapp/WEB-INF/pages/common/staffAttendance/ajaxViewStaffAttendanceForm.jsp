<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp" />
<s:if test='%{academicYear.useBiometricForStaff == "N"}'>
	<s:if test='%{#session.previousYear=="N"}'>
		<s:if test='%{startDate != null && endDate != empty}'>
		<s:form id="addStaffAttendance" action="ajaxCreateStaffAttendance" theme="simple" cssClass="form-horizontal" name="staffAttendanceform" namespace="/admin">
		<s:hidden name="anyTitle" id="attendanceData"/>
		<s:hidden name="attendanceDate" value="%{attendanceDate}"></s:hidden>
		<s:hidden name="balance" id="anyId" value=""></s:hidden>
		<div class="form-body">	
		<s:if test="%{customer.checkMobileService == false}">
		<p>
		<span class="label label-danger">ALERT :</span>&nbsp;&nbsp;<b>SMS service disabled, enable service. 
			<s:url id="urlAttendanceLink" action="ajaxDoSchoolInformation"
				includeParams="all" namespace="/admin" /> <sj:a
				href="%{urlAttendanceLink}" targets="mainContentDiv"
				indicator="indicator" cssClass="enableEmailService">Enable Service </sj:a></b>
		</p>
		<h4 class="pageTitle bold">Attendance for : Staff</h4>
		<p>
		</s:if>
		<s:if test="%{(smsAlloted == 0) || (smsAlloted <= smsCnt)}">
			<div style="color: red;" class="alert alert-info">
				You have been used all your allotted
				<s:property value="smsAlloted" />
				SMS. Please contact EazySchool support team (080-46620999) to recharge your SMS.
			</div>
		</s:if>
			<s:if test="%{viewStaffPersonAccountDetailsList != null && !viewStaffPersonAccountDetailsList.isEmpty()}">
				<p>
				<span class="label label-danger">
		   			 NOTE :
		   		 </span> &nbsp;&nbsp;
					You can add or update the attendance for the selected date.
				</p>
				<s:if test='%{academicYear.captureAttendanceForStaff == "O" || academicYear.captureAttendanceForStaff == null}'>
					Number Of Absentees:&nbsp;<b><s:property value="morningSessionAbsentees"/></b>
				</s:if>
				<s:elseif test='%{academicYear.captureAttendanceForStaff == "T"}'>
					Morning Session Absentees :&nbsp;<b><s:property value="morningSessionAbsentees"/></b>&nbsp;&nbsp;&nbsp;&nbsp;Afternoon Session Absentees:&nbsp;<b><s:property value="afternoonSessionAbsentees"/></b>
				</s:elseif>
				<table class="table table-striped table-bordered table-hover table-full-width" id="sample_7">
				<thead>
					<tr>
					<th>
						Role 
					</th>
					<th>
						User Name
					</th>
					<th>
						Staff Name
					</th>
					<th>
						Mobile Number
					</th>
					<s:if test='%{academicYear.captureAttendanceForStaff == "T"}'>
					<th>
						 Morning Session
					</th>
					<th>
						 Afternoon Session
					</th>
					</s:if>
					<s:else>
						<th>
						Attendance
						</th> 
					</s:else>
				</thead>
        		<tbody>
				<s:iterator value="viewStaffPersonAccountDetailsList">
				<tr>
					<td>
					<s:property value="roleDescription" />
					</td>
					<td>
						<s:property value="username" />
					</td>
					<td>
						<s:property value="firstName" />
						&nbsp;
						<s:property value="lastName" />
					</td>
					<td>
						<s:property value="mobileNumber" />
					</td>
					<s:if test='%{academicYear.captureAttendanceForStaff == "T"}'>
						<td class="MorningAtt">
						<s:if test="%{leaveRequest == 'A'}">
							<s:if test='%{leaveSessionType == "M"}'>
								<s:if test='%{!present}'>
									<div class="checkbox-list">
										<label class="checkbox-inline">
												<s:checkbox cssClass="%{accountId}_checkbox staffAttendanceStatusMorning" title="%{accountId}" id="%{accountId}" name="%{accountId}_checkbox" disabled="true"></s:checkbox>
												<span class="hintMessage">(Leave Approved - Morning)
												</span>
										</label>
									</div>
								</s:if>
							</s:if>					
							<s:elseif test="%{leaveRequest == 'A' && present}">
								<div class="checkbox-list">
									<label class="checkbox-inline">
										<s:checkbox cssClass="%{accountId}_checkbox staffAttendanceStatusMorning" title="%{accountId}" id="%{accountId}" fieldValue="true" value="true" name="%{accountId}_checkbox"></s:checkbox>
									</label>
								</div>
							</s:elseif>
							<s:elseif test="%{leaveRequest == 'A' && (!present && !afternoonSession && (leaveSessionType == null || leaveSessionType.isEmpty()))}">
								<div class="checkbox-list">
									<label class="checkbox-inline">
											<s:checkbox cssClass="checkbox staffAttendanceStatusMorning" title="%{accountId}" id="%{accountId}" name="%{accountId}_checkbox" disabled="true"></s:checkbox>
											<span class="hintMessage">(Leave Approved)</span>
									</label>
								</div>
							</s:elseif>
							<s:else>
							<div class="checkbox-list">
								<label class="checkbox-inline">
									<s:checkbox cssClass="%{accountId}_checkbox staffAttendanceStatusMorning" title="%{accountId}" id="%{accountId}"  name="%{accountId}_checkbox"></s:checkbox>
								</label>
							</div>
							</s:else>
						</s:if>
						<s:elseif test='%{present}'>
							<div class="checkbox-list">
								<label class="checkbox-inline">
									<s:checkbox cssClass="%{accountId}_checkbox staffAttendanceStatusMorning presentComAbsentMorning" title="%{accountId}" id="%{accountId}" fieldValue="true" value="true" name="%{accountId}_checkbox" onChange="prepareStaffAttendanceJson();"></s:checkbox>
								</label>
							</div>
						</s:elseif>
						<s:else>
							<div class="checkbox-list">
								<label class="checkbox-inline">
									<s:checkbox cssClass="%{accountId}_checkbox staffAttendanceStatusMorning absentComPresentMorning" title="%{accountId}" id="%{accountId}"  name="%{accountId}_checkbox" onChange="prepareStaffAttendanceJson();"></s:checkbox>
								</label>
							</div>
						</s:else>
						</td>
						<td class="AffterNoonAtt">
						<s:if test="%{leaveRequest == 'A'}">
							<s:if test='%{leaveSessionType == "A"}'>
								<s:if test='%{!afternoonSession}'>
									<div class="checkbox-list">
										<label class="checkbox-inline">
											<s:checkbox cssClass="checkbox staffAttendanceStatusAfternoon" title="%{accountId}" id="%{accountId}" name="%{accountId}_checkbox" disabled="true"></s:checkbox>
											<span class="hintMessage">(Leave Approved - Afternoon)
											</span>
										</label>
									</div>
								</s:if>
							</s:if>
							<s:elseif test="%{leaveRequest == 'A' && afternoonSession}">
								<div class="checkbox-list">
									<label class="checkbox-inline">	
										<s:checkbox cssClass="%{accountId}_checkbox staffAttendanceStatusAfternoon" title="%{accountId}" id="%{accountId}" fieldValue="true" value="true" name="%{accountId}_checkbox"></s:checkbox>
									</label>
								</div>
							</s:elseif>
							<s:elseif test='%{afternoonSession}'>
									<div class="checkbox-list">
										<label class="checkbox-inline">	
									 		<s:checkbox cssClass="checkbox staffAttendanceStatusAfternoon" title="%{accountId}" id="%{accountId}" fieldValue="true" value="true" name="%{accountId}_checkbox"></s:checkbox>
										</label>
									</div>
							</s:elseif>
							<s:elseif test="%{leaveRequest == 'A' && (!present && !afternoonSession && (leaveSessionType == null || leaveSessionType.isEmpty()))}">
								<div class="checkbox-list">
									<label class="checkbox-inline">
											<s:checkbox cssClass="checkbox staffAttendanceStatusAfternoon" title="%{accountId}" id="%{accountId}" name="%{accountId}_checkbox" disabled="true"></s:checkbox>
											<span class="hintMessage">(Leave Approved)</span>
									</label>
									</div>
							</s:elseif>
							<s:else>
									<div class="checkbox-list">
										<label class="checkbox-inline">	
											<s:checkbox cssClass="checkbox staffAttendanceStatusAfternoon" title="%{accountId}" id="%{accountId}"  name="%{accountId}_checkbox"></s:checkbox> 
										</label>
									</div>
							</s:else>
						</s:if>	
						<s:elseif test='%{afternoonSession}'>
								<div class="checkbox-list">
									<label class="checkbox-inline">	
								 		<s:checkbox cssClass="checkbox staffAttendanceStatusAfternoon presentComAbsentAfternoon" title="%{accountId}" id="%{accountId}" fieldValue="true" value="true" name="%{accountId}_checkbox" onChange="prepareStaffAttendanceJson();"></s:checkbox>
									</label>
								</div>
						</s:elseif>
						<s:else>
							<div class="checkbox-list">
								<label class="checkbox-inline">	
									<s:checkbox cssClass="checkbox staffAttendanceStatusAfternoon absentComPresentAfternoon" title="%{accountId}" id="%{accountId}"  name="%{accountId}_checkbox" onChange="prepareStaffAttendanceJson();"></s:checkbox> 
								</label>
							</div>
					</s:else>
						</td>
					</s:if>
					<s:else>
						<td>
							<s:if test="%{leaveRequest == 'A'}">
								 <div class="checkbox-list">
									 <label class="checkbox-inline">	
										<s:checkbox cssClass="%{accountId}_checkbox staffAttendanceStatus" title="%{accountId}" id="%{accountId}" name="%{accountId}_checkbox" disabled="true"></s:checkbox>
									    <span class="hintMessage">(Leave Approved
											<s:if test="%{!afternoonSession && !present}">
												)
											</s:if>
											<s:elseif test='%{present}'>
												- Afternoon)
											</s:elseif>
											<s:elseif test='%{afternoonSession}'>
												- Morning)
											</s:elseif>
											</span>
										</label>
								</div>
							</s:if>
							<s:elseif test='%{present}'>
								<div class="checkbox-list">
									<label class="checkbox-inline">	
										<s:checkbox cssClass="%{accountId}_checkbox staffAttendanceStatus presentComAbsent" title="%{accountId}" id="%{accountId}" fieldValue="true" value="true" name="%{accountId}_checkbox" onChange="prepareStaffAttendanceJson();"></s:checkbox>
									</label>
								</div>
							</s:elseif>
							<s:else>
								<div class="checkbox-list">
									<label class="checkbox-inline">	
										<s:checkbox cssClass="%{accountId}_checkbox staffAttendanceStatus absentComPresent" title="%{accountId}" id="%{accountId}"  name="%{accountId}_checkbox" onChange="prepareStaffAttendanceJson();"></s:checkbox>
									</label>
								</div>
							</s:else>
						</td>
					</s:else>
				 </tr>
				</s:iterator>
				</tbody>
				<s:if test='%{academicYear.captureAttendanceForStaff == "O" || academicYear.captureAttendanceForStaff == null}'>
				    <tr>
				       <td colspan="4"><b>Absent Staff Count for current selection</b></td>
				       <td><b><span id="currentAbsentCount"></span></b></td>
				   </tr>
				</s:if>
				<s:elseif test='%{academicYear.captureAttendanceForStaff == "T"}'>
				       <tr>
					       <td colspan="4"><b>Absent Staff Count for current selection</b></td>
					       <td><b><span id="currentMorningAbsentCount"></span></b></td>
					       <td><b><span id="currentAfternoonAbsentCount"></span></b></td>
				     </tr>
				</s:elseif>
				</table>
				<div class="space10"></div>
				<div class="form-actions fluid">
				<div class="col-md-offset-3 col-md-9">
					<sj:submit  targets="studentAttendanceResults" value="Save" id="submitButton1"
						 cssClass="submit btn blue" validate="false"
						formIds="addStaffAttendance" indicator="indicator"
						onBeforeTopics="addStaffAttendanceValidation" />
					<s:if test="%{customer.checkMobileService == true}">
						<s:if test="%{(smsAlloted != 0) && (smsAlloted > smsCnt)}">
								<sj:submit targets="studentAttendanceResults" value="Save & Send Sms"
								id="submitButton2" cssClass="btn blue" validate="false" onclick="reportType()"
								formIds="addStaffAttendance" indicator="indicator"
								onBeforeTopics="addStaffAttendanceValidation" />
							
					</s:if>
				</s:if>
				</div>
				</div>
			</s:if>
			<s:else>
				<s:if test="%{bankName == 'todayHoliday' && viewStaffPersonAccountDetailsList.size == 0}">
					<!-- here we are showing holiday message when date have holiday-->
				</s:if>
				<s:else>
					<div class="alert alert-info">
						Oops! Staff not found
					</div>
				</s:else>
	 		</s:else>
			</div>
		</s:form>
		</s:if>
		<s:else>
			<div class="alert alert-info">
				Please add academic planner details. Then only you can submit
				attendance for staff and student.
				<s:url id="urlSendSmsLink" action="ajaxAcademicSchoolSettings"
					includeParams="all" namespace="/admin" />
				<sj:a href="%{urlSendSmsLink}" targets="mainContentDiv"
					indicator="indicator" cssClass="academicPlannerId">Click here</sj:a>
				to add academic planner details.
			</div>
		</s:else>
	</s:if>
	<s:else>
		<div class="alert alert-info">
			You can't submit attendance for past academic year.
		</div>
	</s:else>
</s:if>
<s:else>
	<div class="alert alert-info">
		You can't submit attendance because you have selected bio metric
		process.
	</div>
</s:else>		  
<script type="text/javascript">
$(document).ready(function() {
 $("input:checkbox, input:radio").uniform();
 TableAdvanced.init();
  $('#sample_7_info').css({"margin-bottom":"25px"});
  $(".dataTables_length").hide();
  $(".dataTables_paginate").hide();
	$.subscribe('addStaffAttendanceValidation', function(event, data) {
	     var jsona = [];
	     var status='';
	     var accountId;
	     var attType= '<s:property value="academicYear.captureAttendanceForStaff"/>';
	     if(attType =="T"){
	    	 var Mstatus='';
		     var astatus='';
	    	 $('tr').each(function(){
	    		 if($(this).find("input.staffAttendanceStatusMorning").is(':checked')){
	    			 accountId=$(this).find("input.staffAttendanceStatusMorning").attr('title');
	    			 Mstatus='P';
	    		 }else{
	    			 accountId=$(this).find("input.staffAttendanceStatusMorning").attr('title');
	    			 Mstatus='A';
	    		 }
	    		 if($(this).find("input.staffAttendanceStatusAfternoon").is(':checked')){
	    			 astatus='P';
	    		 }else{
	    			 accountId=$(this).find("input.staffAttendanceStatusAfternoon").attr('title');
	    			 astatus='A';
	    		 }
		    	 if(accountId>0){
		    		 jsona.push( {
							"status" : Mstatus,
							"astatus" : astatus,
							"accountId" : accountId
					});
		    	 }
	    	 }); 
	     }else{
			$('input.staffAttendanceStatus').each(function () {
				   accountId=$(this).attr('title');
			        if ($(this).is(':checked')) {
			           status='P';
			        }
			        else {
			            status='A';
			        }
					jsona.push( {
						"status" : status,
						"accountId" : accountId
					});
			});
	     }
		if(isNonEmpty(jsona) && jsona.length > 0){
			var jsono={"attendanceDate" : $('#attendanceDate').val(), "data" : jsona}
			//alert(jsona);
			$('#attendanceData').val(JSON.stringify(jsono));
			return true;
		}else{
			alert('Something gone wrong! Unable to read the response. Please reload the screen and try or contact system administrator');
			event.originalEvent.options.submit=false;
		}
	});
	
	var tempString3 = '<s:property value="tempString3"/>';
	if(isNonEmpty(tempString3))
	{
		$('#submitButton2').attr('disabled','disabled');
	}
});
$('a.enableEmailService').click(function(){
	window.location.hash="target=ES.ajaxify AMCS";
	window.location.reload();
});
function reportType() { 
	$('#anyId').val('SMS');
}

availableSMSCount = '<s:property value="availableSMSCount"/>';

var i = 0;		
$('.staffAttendanceStatus').click(function()
{
	validateSMS($(this));
});

$('.staffAttendanceStatusMorning').click(function()
{
	validateSMS($(this));
});

$('.staffAttendanceStatusAfternoon').click(function()
{
	validateSMS($(this));
});
		
function validateSMS(smsEvent)
{
	if (!smsEvent.is(':checked')) 
    {
        i = i + 1;
        
        if(availableSMSCount < i)
        {
     	   	$('#submitButton2').attr('disabled','disabled');  
        }
    }
    else
    {
	   	   i = i - 1;
	   	   if(availableSMSCount >= i)
	       {
	       	   $('#submitButton2').removeAttr('disabled');  
	       }
	  }
}

$(".presentComAbsent").click(function() {
	var absentCount='<s:property value="viewStaffPersonAccountDetailsList.size()"/>'-'<s:property value="morningSessionAbsentees"/>';
	var currentAbsentCount=$(".presentComAbsent:checked").length;
	var previousAbsentCount='<s:property value="morningSessionAbsentees"/>';
	var totalAbsentCount=absentCount-currentAbsentCount;
	$("#currentAbsentCount").text(totalAbsentCount);
	if ($(".presentComAbsent:unchecked").length > 0){
		$(".allStudents").attr("checked", false);
		$(".allStudents").parent().removeClass('checked');
	}else{
			if(previousAbsentCount >0){
				$(".allStudents").attr("checked", true);
				$(".allStudents").parent().addClass('checked');
			}
		}
});
$(".absentComPresent").click(function(){
	if ($(".absentComPresent:unchecked").length > 0){
		$(".allStudents").attr("checked", false);
		$(".allStudents").parent().removeClass('checked');
	}else{
		$(".allStudents").attr("checked", true);
		$(".allStudents").parent().addClass('checked');
	}
});
$(".presentComAbsentMorning").click(function() {
	var morningAbsentCount='<s:property value="viewStaffPersonAccountDetailsList.size()"/>'-'<s:property value="morningSessionAbsentees"/>';
	var previousMorningAbsentCount='<s:property value="morningSessionAbsentees"/>';
	var currentMorningAbsentCount=$(".presentComAbsentMorning:checked").length;
	var totalMorningAbsentCount=morningAbsentCount-currentMorningAbsentCount;
	$("#currentMorningAbsentCount").text(totalMorningAbsentCount);
	if ($(".presentComAbsentMorning:unchecked").length > 0){
		$(".allStudents").attr("checked", false);
		$(".allStudents").parent().removeClass('checked');
	}else{
			if(previousMorningAbsentCount >0){
				$(".allStudents").attr("checked", true);
				$(".allStudents").parent().addClass('checked');
			}
		}
});
$(".absentComPresentMorning").click(function(){
	if ($(".absentComPresentMorning:unchecked").length > 0){
		$(".allStudents").attr("checked", false);
		$(".allStudents").parent().removeClass('checked');
	}else{
		$(".allStudents").attr("checked", true);
		$(".allStudents").parent().addClass('checked');
	}
});
$(".presentComAbsentAfternoon").click(function() {
	var afternoonAbsentCount='<s:property value="viewStaffPersonAccountDetailsList.size()"/>'-'<s:property value="afternoonSessionAbsentees"/>';
	var previousAfternoonAbsentCount='<s:property value="afternoonSessionAbsentees"/>';
	var currentAfternoonAbsentCount=$(".presentComAbsentAfternoon:checked").length;
	var totalAfternoonAbsentCount=afternoonAbsentCount-currentAfternoonAbsentCount;
	$("#currentAfternoonAbsentCount").text(totalAfternoonAbsentCount);
	if ($(".presentComAbsentAfternoon:unchecked").length > 0){
		$(".allStudents").attr("checked", false);
		$(".allStudents").parent().removeClass('checked');
	}else{
			if(previousAfternoonAbsentCount >0){
				$(".allStudents").attr("checked", true);
				$(".allStudents").parent().addClass('checked');
			}
		}
});
$(".absentComPresentAfternoon").click(function(){
	if ($(".absentComPresentAfternoon:unchecked").length > 0){
		$(".allStudents").attr("checked", false);
		$(".allStudents").parent().removeClass('checked');
	}else{
		$(".allStudents").attr("checked", true);
		$(".allStudents").parent().addClass('checked');
	}
});
</script>
 
