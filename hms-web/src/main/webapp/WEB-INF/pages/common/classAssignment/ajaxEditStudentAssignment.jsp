<%@ include file="/common/taglibs.jsp"%>
<jsp:include page="/common/messages.jsp" />
<div class="row">
	<div class="col-md-12">
		<div id="senSmsDiv">
			<s:form id="editStudentAssignment" action="ajaxEditStudentAssignment"
				namespace="/admin" theme="simple" name="editStudentAssignment"
				cssClass="form-horizontal">
				<s:hidden name="attendanceType" id="attendanceType" />
				<s:hidden name="classId" id="classId" />
				<s:hidden name="balance" id="anyId" value=""></s:hidden>
				<s:hidden name="anyTitle" id="anyTitle" value=""></s:hidden>
				<s:hidden name="eventId" id="assignmentId"></s:hidden>
				<s:if test="%{customer.checkMobileService == false}">
					<span class="label label-danger">ALERT :</span>&nbsp;&nbsp;<b>SMS
						service disabled, enable service. <s:url id="urlSendSmsLink"
							action="ajaxDoSchoolInformation" includeParams="all" /> <sj:a
							href="%{urlSendSmsLink}" targets="mainContentDiv"
							indicator="indicator">Enable
						Service </sj:a>
					</b>
					<div class="spaceDiv"></div>
				</s:if>
				<s:if test="%{studentsList != null && !studentsList.isEmpty()}">
					<span class="label label-danger">NOTE :</span>
					<div class="panel-body">
						<ul>
							<li>By default system consider all students has not done
								assignment.</li>
							<li>Please check the students who has done the
								assignment.</li>
							<li>Click on send sms & save. So that system will send the
								sms to parents Mobile who has not done the assignment.</li>
						</ul>
					</div>
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
								<th>Roll Number</th>
								<th>Student Name</th>
								<th>Submitted Assignment</th>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="studentsList">
								<tr>
									<td><s:property value="rollNumber" /></td>
									<td><s:property value="firstName" /> &nbsp; <s:property
											value="lastName" /></td>
									<td align="center">
									
									<s:if test="%{tempString == 'assignment'}">
										<s:if test='%{present}'>
											<s:checkbox cssClass="checkbox" title="%{studentId}" disabled="true"
												id="%{eventId}" fieldValue="true" value="true"
												name="%{studentId}_checkbox"></s:checkbox>
										</s:if> <s:else>
											<s:checkbox cssClass="checkbox" title="%{studentId}"
												id="%{eventId}" name="%{studentId}_checkbox"></s:checkbox>
										</s:else>
									</s:if>
									<s:else>
										<s:checkbox cssClass="checkbox" title="%{studentId}"
												id="%{eventId}" fieldValue="true"
												name="%{studentId}_checkbox"></s:checkbox>
									</s:else>
									
									</td>
								</tr>
							</s:iterator>
						</tbody>
					</table>
					<div class="form-body">
						<div class="form-actions fluid">
							<div class="col-md-offset-3 col-md-5">
								<s:if test="%{customer.checkAssignmentSmsService == true && customer.checkMobileService == true}">
									<s:if test="%{(smsAlloted != 0) && (smsAlloted > smsCnt)}">
										<sj:submit targets="senSmsDiv" value="Send SMS & Save"
											cssClass="submitBt btn blue" onclick="reportType()"
											id="submitButton" validate="true"
											formIds="editStudentAssignment" cssStyle="float:left;"
											onBeforeTopics="editStudentAssignmentValidation"
											resetForm="true" />
										</s:if>
									</s:if>
								<sj:submit targets="senSmsDiv" value="Save" id="submitButton1"
									cssClass="submitBt btn blue" validate="true"
									formIds="editStudentAssignment" indicator="indicator"
									cssStyle="float:left;margin-left:10px;"
									onBeforeTopics="editStudentAssignmentValidation"
									resetForm="true" />
									
									<s:if test='%{user.isParent=="Y" || user.isSchoolTeacher=="Y" || user.isSchoolAsstStaff=="Y" || user.isSchoolStudent=="Y"}'>
										<s:url id="doSendSms" action="ajaxViewStaffClassAssignment"
											includeParams="all" escapeAmp="false">
										</s:url>
										<sj:a href="%{doSendSms}" onCompleteTopics="doSendSms1"
											cssClass="btn default" onBeforeTopics="cleanOpenDivs"
											cssStyle="float:left;margin-left:10px;" indicator="indicator"
											targets="mainContentDiv" button="false">Cancel</sj:a>
									</s:if>
									<s:else>
										<s:url id="doSendSms" action="ajaxViewAllClassAssignment"
											includeParams="all" escapeAmp="false">
										</s:url>
										<sj:a href="%{doSendSms}" onCompleteTopics="doSendSms1"
											cssClass="btn default" onBeforeTopics="cleanOpenDivs"
											cssStyle="float:left;margin-left:10px;" indicator="indicator"
											targets="mainContentDiv" button="false">Cancel</sj:a>
									</s:else>
								


							</div>
						</div>
					</div>
				</s:if>
				<s:else>
					<div class="alert alert-info">Oops! Currently there are no
						students for the selected class & section in the system.</div>
				</s:else>
			</s:form>
		</div>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function() {
$("input:checkbox, input:radio").uniform();
//$('button.close').click();
TableAdvanced.init();
 	$.subscribe('editStudentAssignmentValidation', function(event, data) {
	     var jsona = [];
	     var status='';
	     var assignmentId;
	     var studentId; 
		$('input.checkbox').each(function () {
			   status='';			  
			   assignmentId=$(this).attr('id');
			   studentId=$(this).attr('title');
			    if ($(this).is(':checked')) {
		           status='P';
		        }
		        else {
		            status='A';
		        }
				jsona.push( {
					"assignmentId" : assignmentId,
					"status" : status,
					"studentId" : studentId
				});
		});
		var jsono={"attendanceDate" : $('#attendanceDate').val(), "data" : jsona}
		$('input[type=submit]').attr("disabled", "true");
		if(jsona.length > 0)
		{
		var attendanceType=document.getElementById("attendanceType").value;
		var balance=document.getElementById("anyId").value;
		if(balance=='SMS')
		{
			$('#submitButton').val('Saving...');
		}
		else
		{
			$('#submitButton1').val('Saving...');
		}			
			/* $.ajax( {
				url :jQuery.url.getChatURL("/admin/ajaxEditStudentAssignment.do"),
				cache : false,
				data : "anyTitle=" + JSON.stringify(jsono)+"&attendanceType="+attendanceType+"&balance="+balance,
				success : function(response) {
					$('#studentDetails'+assignmentId).html(response);
				}
			}); */
		$('#anyTitle').val(JSON.stringify(jsono));
		$('#attendanceType').val(attendanceType);
		$('#balance').val(balance);
		}
		else
		{
			alert('Something gone wrong! Unable to read the response. Please reload the screen and try or contact system administrator');
		} 
		return false;
	});
});
function reportType() { 
	$('#anyId').val('SMS');
}
</script>
