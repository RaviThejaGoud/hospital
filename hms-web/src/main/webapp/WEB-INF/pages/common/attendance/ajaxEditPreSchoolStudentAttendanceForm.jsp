<%@ include file="/common/taglibs.jsp"%>
<div id="messageShow">
	<%@ include file="/common/messages.jsp"%>
</div>
<s:form id="editStudentAttendance" action=""
	theme="simple" name="myform" cssClass="form-horizontal" namespace="/admin">
	<s:hidden name="anyTitle" id="attendanceData"></s:hidden>
	<s:hidden name="classId"  value="%{classId}" id="classId"></s:hidden>
	<s:hidden name="attendanceDate" value="%{attendanceDate}" id="attendanceDate"></s:hidden>
	<s:hidden name="balance" id="anyId" value=""></s:hidden>
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
				You can add student in and out for the selected date.
			</p>
			<s:if test="%{(smsAlloted == 0) || (smsAlloted <= smsCnt)}">
				<div style="color: red;" class="alert alert-info">
					You have been used all your allotted
					<s:property value="smsAlloted" />
					SMS. Please contact EazySchool support team (080-46620999) to recharge your SMS.
				</div>
			</s:if>
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
						<s:if test='%{!(user.isSchoolTeacher=="Y"  || user.isSchoolAsstStaff=="Y" && #session.parentMobileNoVisibleToTeacher == "N" )}'>
							<th>
							Mobile Number
						</th>
						</s:if>
		   			     <th>
							Attendance
						</th> 
					</tr>
				</thead>
				<tbody>
					<s:iterator value="studentsList">
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
								<s:property value="studentname" /> 
							</td>
							<s:if test='%{!(user.isSchoolTeacher=="Y" || user.isSchoolAsstStaff=="Y" && #session.parentMobileNoVisibleToTeacher == "N" )}'>
								<td class="mobileNumberClass"><s:property value="mobileNumber" />
								</td>
							</s:if>
							<td style="width: 200px;">
								<s:if test='%{inTime == null}'>
									 <a   class="btn btn-xs green" title="IN"  onclick="javascript:submitAttendance('IN','<s:property value="studentId"/>');">
												 				  &nbsp;&nbsp;&nbsp;IN&nbsp;&nbsp;&nbsp;<i class="m-icon-swapleft m-icon-white"></i>  
									</a>
								</s:if>
								<s:else>
									<s:if test="%{outTime == null}">
										 <a class="btn btn-xs purple" title="OUT"  onclick="javascript:submitAttendance('OUT','<s:property value="studentId"/>');">
													<i class="m-icon-swapright m-icon-white"></i>&nbsp;OUT&nbsp;
										</a>
									</s:if>
									<s:else>
									    Student Left For The Day.
									</s:else>
								</s:else>
							</td>
						</tr>
					</s:iterator>
				</tbody>
			</table>
		
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
  TableAdvanced.init();
  $('#sample_7_info').css({"margin-bottom":"25px"});
  $(".dataTables_length").hide();
  $(".dataTables_paginate").hide();
 var $div = $('p.filter-table');
	if ($div.length > 1) {
   	   $div.not(':last').remove()
	}
  
});

$('a.enableEmailService').click(function(){
	window.location.hash="target=ES.ajaxify AMCS";
	window.location.reload();
});
function reportType() { 
	$('#anyId').val('SMS');
}	

function submitAttendance(type,studentNumber){
	
	   var pars = "attendanceType=" + type+"&studentNumber="+studentNumber+"&classId="+$("input#classId").val()+"&attendanceDate="+$("input#attendanceDate").val();
        $.ajax( {
	        url : jQuery.url.getChatURL("/admin/ajaxSubmitAttendance.do"),
			cache : true,
			data : pars,
			success : function(html) {
				$("#createAttendenceDiv").html(html);
			}
		}); 
 	}
			
	
</script>
