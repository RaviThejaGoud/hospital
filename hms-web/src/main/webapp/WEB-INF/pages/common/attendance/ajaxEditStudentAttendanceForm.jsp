<%@ include file="/common/taglibs.jsp"%>
<div id="messageShow">
	<%@ include file="/common/messages.jsp"%>
</div>
<s:form id="editStudentAttendance" action="ajaxCreateAttendance"
	theme="simple" name="myform" cssClass="form-horizontal" namespace="/admin">
	<s:hidden name="anyTitle" id="attendanceData"></s:hidden>
	<s:hidden name="classId" value="%{classId}" id="classId"></s:hidden>
	<s:hidden name="attendanceDate" value="%{attendanceDate}" id="attendanceDate"></s:hidden>
	<s:hidden name="balance" id="anyId" value=""></s:hidden>
	<s:hidden name="email" id="emailId" value=""></s:hidden>
	
	<s:hidden name="eventId" value="%{eventId}" id="eventId"></s:hidden>
	<div class="form-body" id="emailSmsEnable">
		<s:if test="%{customer.checkMobileService == false}">
			<div class="panel-body col-md-13">
				<div class="col-md-1">
					<span class="label label-danger">ALERT :</span>
				</div>
				<div class="col-md-10">
					<ul>
						<li>SMS service disabled, enable service. <s:url
								id="urlAttendanceLink" action="ajaxDoSchoolInformation"
								includeParams="all" namespace="/admin" /> <sj:a
								href="%{urlAttendanceLink}" targets="mainContentDiv"
								indicator="indicator" cssClass="enableEmailService">Enable
						Service </sj:a>
						</li>
						<li>If mobile service is in disable, you can not send sms.</li>
					</ul>
				</div>
			</div>
		</s:if>
		<s:if test="%{studentsList != null && !studentsList.isEmpty()}">
			<p>
				<span class="label label-danger"> NOTE : </span> &nbsp;&nbsp;
				You can add or update the attendance for the selected date.
			</p>
			<s:if test="%{(smsAlloted == 0) || (smsAlloted <= smsCnt)}">
				<div style="color: red;" class="alert alert-info">
					You have been used all your allotted
					<s:property value="smsAlloted" />
					SMS. Please contact EazySchool support team (080-46620999) to recharge your SMS.
				</div>
			</s:if>
			<s:if test='%{academicYear.captureAttendanceBy == "O" || academicYear.captureAttendanceBy == null}'>
			Number Of Absentees :&nbsp;<b><s:property value="morningSessionAbsentees"/></b>
			</s:if>
			<s:elseif test='%{academicYear.captureAttendanceBy == "T"}'>
			Morning Session Absentees :&nbsp;<b><s:property value="morningSessionAbsentees"/></b>&nbsp;&nbsp;&nbsp;&nbsp;Afternoon Session Absentees :&nbsp;<b><s:property value="afternoonSessionAbsentees"/></b>
			</s:elseif>
			<table
				class="table table-striped table-bordered table-hover table-full-width"
				id="sample_7">
				<thead>
					<tr>
						<th style="display: none;">
						</th>
						<th style="width: 50px;">
							Roll Number
						</th>
						<th>
							Admission No#
						</th>
						<th>
							Student Name
						</th>
						<s:if test='%{!(user.isSchoolTeacher=="Y" || user.isSchoolAsstStaff=="Y" && #session.parentMobileNoVisibleToTeacher == "N" )}'>
							<th>
							Mobile Number
						</th>
						</s:if>
						<s:if test='%{academicYear.captureAttendanceBy == "T"}'>
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
					</tr>
				</thead>
				<tbody>
					<s:iterator value="studentsList">
						<%-- <tr class="attendance<s:property value="%{studentId}" />"> --%>
						<tr>
							<td style="display: none;">
								<s:property value="rollNumber" />
							</td>
							<td style="width: 50px;">
								<s:property value="rollNumber" />
							</td>
							<td>
								<s:property value="admissionNumber" />
							</td>
							<td>
								<s:property value="studentname" /> <s:property value="leaveRequest"/>
							</td>
							<s:if test='%{!(user.isSchoolTeacher=="Y" || user.isSchoolAsstStaff=="Y" && #session.parentMobileNoVisibleToTeacher == "N" )}'>
								<td class="mobileNumberClass"><s:property value="mobileNumber" /></td>
							</s:if>
							<s:if test='%{academicYear.captureAttendanceBy == "T"}'>
								<td class="MorningAtt">
									<s:if test="%{leaveRequest == 'A'}">
										<s:if test='%{leaveSessionType == "M"}'>
											<s:if test='%{!present}'>
												<div class="checkbox-list">
													<label class="checkbox-inline">
															<s:checkbox cssClass="checkbox studentAttendanceStatus1" title="%{studentId}"
																id="%{classId}-%{classSectionId}"
																name="%{studentId}_checkbox" disabled="true"></s:checkbox>
																<span id="suspend"></span>
															 <span class="hintMessage">(Leave Approved - Morning)</span>
													</label>
												</div>
											</s:if>	
										</s:if>
										<s:elseif test="%{leaveRequest == 'A' && present}">
											<div class="checkbox-list">
												<label class="checkbox-inline">
													<s:checkbox cssClass="%{studentId}_checkbox studentAttendanceStatus1" title="%{studentId}"
															id="%{classId}-%{classSectionId}" fieldValue="true"
															value="true" name="%{studentId}_checkbox"></s:checkbox> 
															<span id="suspend"></span> 
												</label>
											</div>
										</s:elseif>
										<s:elseif test="%{leaveRequest == 'A' && (!present && !afternoonSession && (leaveSessionType == null || leaveSessionType.isEmpty()))}">
											<div class="checkbox-list">
												<label class="checkbox-inline">
														<s:checkbox cssClass="checkbox studentAttendanceStatus1" title="%{studentId}"
															id="%{classId}-%{classSectionId}"
															name="%{studentId}_checkbox" disabled="true"></s:checkbox>
															<span id="suspend"></span>
														<span class="hintMessage">(Leave Approved)</span>
												</label>
												</div>
										</s:elseif>
										<s:else>
											<div class="checkbox-list">
												<label class="checkbox-inline">
													<s:checkbox cssClass="%{studentId}_checkbox studentAttendanceStatus1" title="%{studentId}"
														id="%{classId}-%{classSectionId}"
														name="%{studentId}_checkbox"></s:checkbox> 
												</label>
												</div>
										</s:else>
									</s:if>
									<s:elseif test='%{present}'>
											<div class="checkbox-list">
												<label class="checkbox-inline">
													<s:checkbox cssClass="%{studentId}_checkbox studentAttendanceStatus1 presentComAbsentMorning" title="%{studentId}"
														id="%{classId}-%{classSectionId}" fieldValue="true"
														value="true" name="%{studentId}_checkbox" onchange="javascript:prepareStudentAttendanceJson();"></s:checkbox> 
														<span id="suspend"></span>
												</label>
											</div>
									</s:elseif>
									<s:else>
										<div class="checkbox-list">
											<label class="checkbox-inline">
												<s:checkbox cssClass="%{studentId}_checkbox studentAttendanceStatus1 absentComPresentMorning" title="%{studentId}"
													id="%{classId}-%{classSectionId}"
													name="%{studentId}_checkbox" onchange="javascript:prepareStudentAttendanceJson();"></s:checkbox>
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
													<s:checkbox cssClass="checkbox studentAttendanceStatus2" title="%{studentId}"
														id="%{classId}-%{classSectionId}"
														name="%{studentId}_checkbox" disabled="true"></s:checkbox>
													 <span class="hintMessage">(Leave Approved - Afternoon)</span>
												</label>
											</div>
										</s:if>
									</s:if>
									<s:elseif test="%{leaveRequest == 'A' && afternoonSession}">
										<div class="checkbox-list">
											<label class="checkbox-inline">
												<s:checkbox cssClass="%{studentId}_checkbox studentAttendanceStatus2" title="%{studentId}"
														id="%{classId}-%{classSectionId}" fieldValue="true"
														value="true" name="%{studentId}_checkbox"></s:checkbox>
														<span id="suspend"></span>
											</label>
										</div>
									</s:elseif>
									<s:elseif test="%{leaveRequest == 'A' && (!present && !afternoonSession && (leaveSessionType == null || leaveSessionType.isEmpty()))}">
										<div class="checkbox-list">
											<label class="checkbox-inline">
													<s:checkbox cssClass="checkbox studentAttendanceStatus2" title="%{studentId}"
														id="%{classId}-%{classSectionId}"
														name="%{studentId}_checkbox" disabled="true"></s:checkbox>
														<span id="suspend"></span>
													<span class="hintMessage">(Leave Approved)</span>
											</label>
										</div>
									</s:elseif>
									<s:else>
										<div class="checkbox-list">
											<label class="checkbox-inline">
												<s:checkbox cssClass="%{studentId}_checkbox studentAttendanceStatus2" title="%{studentId}"
													id="%{classId}-%{classSectionId}"
													name="%{studentId}_checkbox"></s:checkbox>
											</label>
										</div>
									</s:else>
								</s:if>
								<s:elseif test='%{afternoonSession}'>
									<div class="checkbox-list">
										<label class="checkbox-inline">
											<s:checkbox cssClass="%{studentId}_checkbox studentAttendanceStatus2 presentComAbsentAfternoon" title="%{studentId}"
												id="%{classId}-%{classSectionId}" fieldValue="true"
												value="true" name="%{studentId}_checkbox" onchange="javascript:prepareStudentAttendanceJson();"></s:checkbox>
												<span id="suspend"></span>
										</label>
									</div>
								</s:elseif>
								<s:else>
									<div class="checkbox-list">
										<label class="checkbox-inline">
											<s:checkbox cssClass="%{studentId}_checkbox studentAttendanceStatus2 absentComPresentAfternoon" title="%{studentId}"
												id="%{classId}-%{classSectionId}"
												name="%{studentId}_checkbox" onchange="javascript:prepareStudentAttendanceJson();"></s:checkbox>
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
											<s:checkbox cssClass="checkbox studentAttendanceStatus" title="%{studentId}" id="%{classId}-%{classSectionId}" name="%{studentId}_checkbox" disabled="true"></s:checkbox>
											<span class="hintMessage">
												(Leave Approved  
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
												<s:checkbox cssClass="%{studentId}_checkbox studentAttendanceStatus presentComAbsent" title="%{studentId}" id="%{classId}-%{classSectionId}" fieldValue="true"
													value="true" name="%{studentId}_checkbox" onchange="javascript:prepareStudentAttendanceJson();"></s:checkbox>
												<span id="suspend"></span>
											</label>
										</div>
								</s:elseif>
								<s:else>
									<div class="checkbox-list">
										<label class="checkbox-inline">
											<s:checkbox cssClass="%{studentId}_checkbox studentAttendanceStatus absentComPresent" title="%{studentId}" id="%{classId}-%{classSectionId}"
												name="%{studentId}_checkbox" onchange="javascript:prepareStudentAttendanceJson();"></s:checkbox>
										</label>
									</div>
								</s:else>
							</td>
							</s:else>
						</tr>
					</s:iterator>
				</tbody>
				<s:if test='%{(academicYear.captureAttendanceBy == "O" || academicYear.captureAttendanceBy == null) && user.isSchoolTeacher=="Y" || user.isSchoolAsstStaff=="Y" && #session.parentMobileNoVisibleToTeacher == "Y"}'>
				    <tr>
				        <td colspan="4"><b>Absent Student Count for current selection</b></td>
				        <td><b><span id="currentAbsentCount"></span></b></td>
				   </tr>
			   </s:if>
			   <s:elseif test='%{(academicYear.captureAttendanceBy == "O" || academicYear.captureAttendanceBy == null) && user.isSchoolTeacher=="Y" || user.isSchoolAsstStaff=="Y" && #session.parentMobileNoVisibleToTeacher == "N"}'>
				    <tr>
				        <td colspan="3"><b>Absent Student Count for current selection</b></td>
				        <td><b><span id="currentAbsentCount"></span></b></td>
				   </tr>
			   </s:elseif>
			   <s:elseif test='%{(academicYear.captureAttendanceBy == "O" || academicYear.captureAttendanceBy == null) && user.isSchoolTeacher=="N"}'>
				    <tr>
				        <td colspan="4"><b>Absent Student Count for current selection</b></td>
				        <td><b><span id="currentAbsentCount"></span></b></td>
				   </tr>
			   </s:elseif>
				<s:elseif test='%{academicYear.captureAttendanceBy == "T" && user.isSchoolTeacher=="Y" && #session.parentMobileNoVisibleToTeacher == "Y"}'>
				        <tr>
					        <td colspan="4"><b>Absent Student Count for current selection</b></td>
					        <td><b><span id="currentMorningAbsentCount"></span></b></td>
					        <td><b><span id="currentAfternoonAbsentCount"></span></b></td>
				      </tr>
				</s:elseif>
				<s:elseif test='%{academicYear.captureAttendanceBy == "T" && user.isSchoolTeacher=="Y" || user.isSchoolAsstStaff=="Y" && #session.parentMobileNoVisibleToTeacher == "N"}'>
				        <tr>
					        <td colspan="3"><b>Absent Student Count for current selection</b></td>
					        <td><b><span id="currentMorningAbsentCount"></span></b></td>
					        <td><b><span id="currentAfternoonAbsentCount"></span></b></td>
				      </tr>
				</s:elseif>
		 		<s:elseif test='%{academicYear.captureAttendanceBy == "T" && user.isSchoolTeacher=="N"}'>
				        <tr>
					        <td colspan="4"><b>Absent Student Count for current selection</b></td>
					        <td><b><span id="currentMorningAbsentCount"></span></b></td>
					        <td><b><span id="currentAfternoonAbsentCount"></span></b></td>
				      </tr>
				</s:elseif>
			</table>
			<div class="form-actions fluid">
				<div class="col-md-offset-2 col-md-9">
					<sj:submit targets="createAttendenceDiv" value="Save"
						id="submitButton1" cssClass="btn blue" validate="false"
						formIds="editStudentAttendance" indicator="indicator"
						onBeforeTopics="editStudentAttendanceValidation" />
						
						<s:if test="%{customer.checkMobileService == true && customer.checkAttendanceEmailService == true}">
							<s:if test="%{(smsAlloted != 0) && (smsAlloted > smsCnt)}">
								<sj:submit targets="createAttendenceDiv" value="Save & Send SMS and Email"
									id="submitButton2" cssClass="btn blue" validate="false" onclick="reportType('SMS','Email')"
									formIds="editStudentAttendance" indicator="indicator"
									onBeforeTopics="editStudentAttendanceValidation" />
							</s:if>
							<s:else>
							<sj:submit targets="createAttendenceDiv" value="Save & Send Email"
									id="submitButton3" cssClass="btn blue" validate="false" onclick="reportType('null','Email')"
									formIds="editStudentAttendance" indicator="indicator"
									onBeforeTopics="editStudentAttendanceValidation" />
							</s:else>
							
						</s:if>
						<s:elseif test="%{customer.checkMobileService == true}">
							<s:if test="%{(smsAlloted != 0) && (smsAlloted > smsCnt)}">
								<sj:submit targets="createAttendenceDiv" value="Save & Send SMS "
									id="submitButton2" cssClass="btn blue" validate="false" onclick="reportType('SMS','null')"
									formIds="editStudentAttendance" indicator="indicator"
									onBeforeTopics="editStudentAttendanceValidation" />
							</s:if>
						</s:elseif>
						<s:elseif test="%{customer.checkAttendanceEmailService == true}">
								<sj:submit targets="createAttendenceDiv" value="Save & Send Email"
									id="submitButton3" cssClass="btn blue" validate="false" onclick="reportType('null','Email')"
									formIds="editStudentAttendance" indicator="indicator"
									onBeforeTopics="editStudentAttendanceValidation" />
						</s:elseif>
				</div>
			</div>
		</s:if>
		<s:else>
			<s:if test="%{bankName == 'todayHoliday' && studentsList.size == 0}">
				<!-- here we are showing holiday message when date have holiday-->
			</s:if>
			<s:else>
				<div class="alert alert-info">
					Oops! Currently there are no students for the selected class &
					section.
				</div>
			</s:else>
		</s:else>
	</div>
</s:form>
<script type="text/javascript">
$(document).ready(function() {
	disableStudents();
  $.destroyTopic('editStudentAttendanceValidation');
  $('input#submitButton1').show();
  $("input:checkbox, input:radio").uniform();
  TableAdvanced.init();
  $('#sample_7_info').css({"margin-bottom":"25px"});
  $(".dataTables_length").hide();
  $(".dataTables_paginate").hide();
 var $div = $('p.filter-table');
	if ($div.length > 1) {
   	   $div.not(':last').remove()
	}
	$.subscribe('editStudentAttendanceValidation', function(event, data) {
		//http://stackoverflow.com/questions/29541936/count-empty-or-not-empty-td-with-jquery
		//alert($('.mobileNumberClass:not(:empty)').length);
		var mobileNumbersLenght = $('.mobileNumberClass:not(:empty)').length;
		var isSMS = $('#anyId').val();
		
		     var jsona = [];
		     var status='';
		     var sectionId;
		     var studentId;
		     var attendanceDate = $("#attendanceDate").val();
		     var attType= '<s:property value="academicYear.captureAttendanceBy"/>';
		     if(attType =="T"){
		    	 var Mstatus='';
			     var astatus='';
		    	 $('tr').each(function(){
		    		 if($(this).find("input.studentAttendanceStatus1").is(':checked')){
		    			  studentId=$(this).find("input.studentAttendanceStatus1").attr('title');
		    			 Mstatus='P';
		    		 }else{
		    			 studentId=$(this).find("input.studentAttendanceStatus1").attr('title');
		    			 Mstatus='A';
		    		 }
		    		 if($(this).find("input.studentAttendanceStatus2").is(':checked')){
		    			 astatus='P';
		    		 }else{
		    			 astatus='A';
		    		 }
			    	 if(studentId>0){
			    		 jsona.push( {
								"status" : Mstatus,
								"astatus" : astatus,
								"studentId" : studentId
						});
			    	 }
		    	 }); 
		     }else{
		    	 $('input.studentAttendanceStatus').each(function () {
					   status='';
					   sectionId=$(this).attr('id');
					   studentId=$(this).attr('title');
						   if ($(this).is(':checked')) {
					           status='P';
					        }
					        else {
					            status='A';
					        }
						jsona.push( {
							"status" : status,
							"studentId" : studentId
						});
				}); 
		    	 
		     }
			var classAddendanceData = {
				"classAttendanceData": [ { "attendanceDate" : attendanceDate, "students": jsona } ]
			};
			if(isNonEmpty(classAddendanceData) ){//&& jsona.length > 0
				//var jsono={"attendanceDate" : $('#attendanceDate').val(), "data" : jsona}
				//alert(JSON.stringify(classAddendanceData));
				$('#attendanceData').val(JSON.stringify(classAddendanceData));
				
				return true;
				//event.originalEvent.options.submit=false;
			}
			else
				event.originalEvent.options.submit=false;
			//var selectedChkCount = $("input[name=eventId]").length;
			
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
function reportType(sms,email) { 
	
	if('null'!=sms ){
		$('#anyId').val('SMS');
	}
	if('null'!=email ){
		$('#emailId').val('Email');
	}
	
}	


/*$('p#enableServiceId').click(function(){
alert('hi');
//$('li#attendanceId').find('li.open').removeClass('active');
$('li.open').find('li#attendanceId').removeClass('active');
//$('li#attendanceId1').find('li.open').find('ul.sub-menu').addClass('active');
$('li.open').find('ul.sub-menu').find('li.active').child('a').addClass('active');
			alert('hi2');
			});*/

function disableStudents(){
	var sections = $('#eventId').val().split(',');
	if(isNonEmpty(sections)){
		for(var i=0;i< Number(sections.length);i++){
			$("."+sections[i]+"_checkbox").parent('span').parent('div').addClass('disabled');
			$("."+sections[i]+"_checkbox").attr('disabled', 'disabled');
			$("."+sections[i]+"_checkbox").parents('label').children('span#suspend').text("(Suspended)");
		}
	}
}

			
availableSMSCount = '<s:property value="availableSMSCount"/>';
			 
var i = 0;		
$('.studentAttendanceStatus').click(function()
{
	validateSMS($(this));
});

$('.studentAttendanceStatus1').click(function()
{
	validateSMS($(this));
});

$('.studentAttendanceStatus2').click(function()
{
	validateSMS($(this));
});
		
		
function validateSMS(smsEvent)
{
	if (!smsEvent.is(':checked')) 
    {
        i = i + 1;
        
        //alert(availableSMSCount + "  " + i);
        if(availableSMSCount < i)
        {
     	   	$('#submitButton2').attr('disabled','disabled');  
        }
    }
    else
    {
	   	   i = i - 1;
	   	   //alert(availableSMSCount + "  " + i);
	   	   if(availableSMSCount >= i)
	       {
	       	   $('#submitButton2').removeAttr('disabled');  
	       }
	  }
}

$(".presentComAbsent").click(function() {
	var absentCount='<s:property value="studentsList.size()"/>'-'<s:property value="morningSessionAbsentees"/>';
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
	var morningAbsentCount='<s:property value="studentsList.size()"/>'-'<s:property value="morningSessionAbsentees"/>';
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
	var afternoonAbsentCount='<s:property value="studentsList.size()"/>'-'<s:property value="afternoonSessionAbsentees"/>';
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
