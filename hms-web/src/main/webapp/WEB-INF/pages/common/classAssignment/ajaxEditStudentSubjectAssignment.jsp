<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div id="senSmsDiv">
			<s:form id="editStudentAssignment" action="ajaxEditStudentAssignment"
				theme="simple" name="editStudentAssignment"
				cssClass="form-harizontal" namespace="/admin">
				<s:hidden name="attendanceType" id="attendanceType" />
				<s:hidden name="classId" id="classId" />
				<s:hidden name="balance" id="anyId" value=""></s:hidden>
				<s:hidden name="anyTitle" id="stuData"></s:hidden>
				<s:hidden name="eventId" id="assignmentId"></s:hidden>
				<s:if test="%{customer.checkMobileService == false}">
				<s:if test='%{user.IsSchoolAdmin=="Y"}'>
					<div class="form-body">
						<div class="panel-body col-md-12">
							<div class="col-md-1">
								<span class="label label-danger">ALERT :</span>
							</div>
							&nbsp;&nbsp;SMS service disabled, enable service.
							<s:url id="urlSendSmsLink" action="ajaxDoSchoolInformation"
								includeParams="all" namespace="/admin" />
							<sj:a href="%{urlSendSmsLink}" targets="mainContentDiv"
								indicator="indicator">Enable 
						Service </sj:a>
							</b>
							<!--<a
				href="${pageContext.request.contextPath}/admin/schoolSettings.do?mc=true">Enable
					Service</a> </b>-->
						</div>
					</div>
					</s:if>
				</s:if>
				<s:if test="%{studentsList != null && !studentsList.isEmpty()}">
					<jsp:include page="/common/messages.jsp"></jsp:include>
					<div class="form-body">
						<div class="panel-body col-md-12">
							<div class="col-md-1">
								<span class="label label-danger"> NOTE : </span>
							</div>
							&nbsp;&nbsp;
							<div class="spaceDiv"></div>
							<div class="spaceDiv">
								<ul>
									<li>By default system consider all students has not done
										assignment.</li>
									<li>Please check the students who has done the
										assignment.</li>
									<li>Click on send sms & save. So that system will send the
										sms to parents Mobile who has not done the assignment.</li>
								</ul>
							</div>
						</div>
						<%-- <s:if test='%{studyClassList.size >1}'>
							<div class="form-group">
								<div class="col-md-12">
									<div class="checkbox-list">
										<label class="checkbox-inline">
											<input type="checkbox" name=""
													value="" onClick="checkAllClasses()"
													class="checkbox allClasses">
											All Class & Sections
										</label>
									</div>
								</div>
							</div>
						</s:if> --%>
						
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
										<td><s:if test='%{present}'>
												<s:checkbox cssClass="checkbox" title="%{studentId}"
													id="%{eventId}" fieldValue="true" value="true"
													name="%{studentId}_checkbox"></s:checkbox>
											</s:if> <s:else>
												<s:checkbox cssClass="checkbox" title="%{studentId}"
													id="%{eventId}" name="%{studentId}_checkbox"></s:checkbox>
											</s:else></td>
									</tr>
								</s:iterator>

							</tbody>
						</table>
						<div class="form-actions fluid">
							<div class="col-md-offset-3 col-md-9">
								<sj:submit targets="senSmsDiv" value="Send SMS & Save"
									cssClass="submitBt btn green" onclick="reportType()"
									id="submitButton" validate="true"
									formIds="editStudentAssignment"
									onBeforeTopics="editStudentAssignmentValidation"
									resetForm="true" />
								<sj:submit targets="senSmsDiv" value="Save" id="submitButton1"
									cssClass="submitBt btn blue" validate="true"
									formIds="editStudentAssignment"
									onBeforeTopics="editStudentAssignmentValidation"
									resetForm="true" />
								<s:url id="doSendSms" action="ajaxViewStaffClassAssignment"
									includeParams="all" escapeAmp="false" namespace="/admin">
								</s:url>
								<sj:a href="%{doSendSms}" cssClass="btn default"
									targets="mainContentDiv" button="false">Cancel</sj:a>
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
	TableAdvanced.init();
	//$('button.close').click();
$.subscribe('editStudentAssignmentValidation',function(event, data) {
	var jsona = [];
	var status = '';
	var assignmentId;
	var studentId;
	$('input.checkbox').each(function() {
		status = '';
		assignmentId = $(this).attr('id');
		studentId = $(this).attr('title');
		if ($(this).is(':checked')) {
			status = 'P';
		} else {
			status = 'A';
		}
		jsona.push( {
			"assignmentId" : assignmentId,
			"status" : status,
			"studentId" : studentId
		});
	});
	var jsono = {"attendanceDate" : $('#attendanceDate').val(),"data" : jsona}
	$('input[type=submit]').attr(
			"disabled", "true");
	if (jsona.length > 0) {
		var attendanceType = document
				.getElementById("attendanceType").value;
		var balance = document
				.getElementById("anyId").value;
		if (balance == 'SMS') {
			$('#submitButton').val(
					'Saving...');
		} else {
			$('#submitButton1').val(
					'Saving...');
		}
		/* $.ajax( {
					url : jQuery.url.getChatURL("/admin/ajaxEditStudentAssignment.do"),
					cache : false,
					data : "anyTitle="
							+ JSON.stringify(jsono)
							+ "&attendanceType="
							+ attendanceType
							+ "&balance="
							+ balance,
					success : function(
							response) {
						$(
								'#studentDetails' + assignmentId)
								.html(
										response);
										if (balance == 'SMS') {
			$('#submitButton').val(
					'Send SMS & Save');
		} else {
			$('#submitButton1').val(
					'Save');
		}
					}
				}); */
				
		$('#stuData').val(JSON.stringify(jsono));
		$('#anyId').val(balance);
		$('#attendanceType').val(attendanceType);
				
	} else {
		alert('Something gone wrong! Unable to read the response. Please reload the screen and try or contact system administrator');
	}
});
});
function reportType() {
	$('#anyId').val('SMS');
}


/* function checkAllClasses() {
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
} */


</script>
