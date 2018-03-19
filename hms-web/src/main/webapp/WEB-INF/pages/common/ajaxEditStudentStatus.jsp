<%@ include file="/common/taglibs.jsp"%>
<div data-width="760" class="modal fade modal-overflow in"
	id="inactiveStudenstDiv"
	style="display: block; width: 650px; margin-left: -279px; margin-top: 100px;"
	aria-hidden="false">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true"></button>
		<h4 class="bold pagetilte">
			 Student Status
		</h4>
	</div>
	<span id="startDateSpan" class="<s:property value='startDate'/>"></span>
	<span id="endDateSpan" class="<s:property value='endDate'/>"></span>
	<div class="modal-body">
		<s:form id="editStudentDisable" action="ajaxDisableStudent"
			method="post" theme="simple" namespace="/student"
			cssClass="form-horizontal">
			<s:hidden name="tempId" value="%{tempId}" />
			<s:hidden name="balance" id="anyId" value=""></s:hidden>
			<s:hidden name="student.status" value="%{suspendAndBlacklistStudents.status}"/>
			<s:hidden name="suspendAndBlacklistStudents.id" value="%{suspendAndBlacklistStudents.id}"/>
			<%-- <s:hidden name="todayDate" value="%{suspendAndBlacklistStudents.blackedOrSuspendToDateStr}"/>
			<s:hidden name="suspendAndBlacklistStudents.blackedOrSuspendFromDate}" value="%{suspendAndBlacklistStudents.blackedOrSuspendFromDateStr}"/> --%>
			<div class="form-body">
				<s:if test='%{user.isOnlySchoolAdmin=="Y" || user.isSchoolPrincipal=="Y" || user.isSchoolClerk=="Y" || user.isSchoolDirector == "Y"}'>
				<div class="form-group">
					<label class="control-label col-md-3">
						<span class="required">*</span>Status :
					</label>
					<div class="col-md-9">
						<s:select list="#{'Y':'Active','N':'Inactive','B':'Blacklist','S':'Suspend'}"
							cssClass="required form-control  input-medium" id="studentStatusId" disabled="true"
							name="suspendAndBlacklistStudents.status" onchange="javascript:getAjaxDoGetStudentsStatusDetails(this.value);" />
					</div>
				</div>
				<div class="form-group">
				<label class="control-label col-md-3">
					<span class="required">*</span>Start Date :
				</label>
				<div class="col-md-9">
					<s:if test="%{todayDate.compareTo(suspendAndBlacklistStudents.blackedOrSuspendFromDateStr) >=0 }">
						<div data-date-format="mm/dd/yyyy"
							class="input-group input-medium date date-picker">
							<input id="startDate" name="suspendAndBlacklistStudents.blackedOrSuspendFromDate" disabled="disabled"
								readonly="readonly" type="text" 
								value='<s:property value="suspendAndBlacklistStudents.blackedOrSuspendFromDateStr"/>'
								class="required form-control input-medium ">
								<span class="input-group-btn">
								<button type="button" class="btn default" style="display: none">
									<i class="fa fa-calendar"></i>
								</button> </span>
						</div>
						<span class="help-block">(MM/DD/YYYY)</span>
					</s:if>
					<s:else>
						<div data-date-format="mm/dd/yyyy"
							class="input-group input-medium date date-picker">
							<input id="startDate" name="suspendAndBlacklistStudents.blackedOrSuspendFromDate" 
								readonly="readonly" type="text"
								value='<s:property value="suspendAndBlacklistStudents.blackedOrSuspendFromDateStr"/>'
								class="required form-control input-medium "> <span
								class="dateInput input-group-btn">
								<button type="button" class="btn default">
									<i class="fa fa-calendar"></i>
								</button>
							</span>
						</div>
						<span class="help-block">(MM/DD/YYYY)</span>
					</s:else>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-md-3">
					<span class="required">*</span>End Date :
				</label>
				<div class="col-md-9">
					<s:if test="%{todayDate.compareTo(suspendAndBlacklistStudents.blackedOrSuspendToDateStr) > 0 }">
						<div data-date-format="mm/dd/yyyy"
							class="input-group input-medium date date-picker">
							<input id="endDate" name="suspendAndBlacklistStudents.blackedOrSuspendToDate"
								readonly="readonly" type="text" 
								value='<s:property value="suspendAndBlacklistStudents.blackedOrSuspendToDateStr"/>'
								class="required form-control input-medium "> 
								<span class="input-group-btn">
							<button type="button" class="btn default" style="display: none">
								<i class="fa fa-calendar"></i>
							</button> </span>
						</div>
						<span class="help-block">(MM/DD/YYYY)</span>
					</s:if>
					<s:else>
						<div data-date-format="mm/dd/yyyy"
							class="input-group input-medium date date-picker">
							<input id="endDate" name="suspendAndBlacklistStudents.blackedOrSuspendToDate"
								readonly="readonly" type="text"
								value='<s:property value="suspendAndBlacklistStudents.blackedOrSuspendToDateStr"/>'
								class="required form-control input-medium "> <span
								class="dateInput input-group-btn">
								<button type="button" class="btn default">
									<i class="fa fa-calendar"></i>
								</button>
							</span>
						</div>
						<span class="help-block">(MM/DD/YYYY)</span>
					</s:else>
				</div>
		</div>
		<div id="dispaySelectedDate"  style="display: none;"> </div>
			<div class="form-group">
			<label class="control-label col-md-3">
				No.Of Days :
			</label>
			<div class="col-md-9">
				<sj:textfield id="suspendDaysCount"  name="suspendAndBlacklistStudents.suspendDays"
					readonly="true" cssClass="form-control  input-medium" />
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-md-3">
				<span class="required">*</span> Cause/Reason :
			</label>
			<div class="col-md-9">
				<s:textarea cols="5" rows="5" id="messageDescription"
					maxCharsData="1000" cssClass="required form-control word_count"
					name="suspendAndBlacklistStudents.description"></s:textarea>
				<span class="counter"></span>
			</div>
		</div>
		<div class="form-actions fluid">
			<div class="col-md-offset-3 col-md-6">
				<s:if test='%{#session.previousYear == "N"}'>
					<img src="../img/bg/bigWaiting.gif" alt="Loading..."  title="Loading..." id="indicator"
						style="display: none; background-repeat: no-repeat; position: relative; z-index: 1000; bottom: 200px; margin-left: 240px;" />
					<sj:submit cssClass="submitBt btn blue" value="Save" id="submitButton1" indicator="indicator" 
						targets="studentEditContentDiv" formIds="editStudentDisable" validate="true"
						onBeforeTopics="changeStudentDisableValidation" />
							<s:if test='%{student.status =="S"}'>
								<s:if test="%{customer.checkMobileService == true}">
									<s:if test="%{(smsAlloted != 0) && (smsAlloted > smsCnt)}">
										<sj:submit targets="studentEditContentDiv" value="Save & Send SMS"
											cssClass="submitBt btn green" indicator="indicator"
											onclick="submitType()" id="submitButton" validate="true"
											formIds="editStudentDisable" onBeforeTopics="changeStudentDisableValidation" />
									</s:if>
							 	</s:if>
						 	</s:if>
					 	<button type="button" data-dismiss="modal" class="btn default">
							Cancel
						</button>
				</s:if>
			</div>
		</div>
				</s:if>
			</div>
		</s:form>
	</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/subscription/textcounter.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/ems.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	var startDate = $('span#startDateSpan').attr("class");
	var endDate = $('span#endDateSpan').attr("class");
	dateRestrictionWithinAcademicYear(startDate,endDate);
	FormComponents.init(); 
	$.destroyTopic('changeStudentDisableValidation');
	FormComponents.init();
	$.subscribe('changeStudentDisableValidation', function(event, data) {
		var msgdesValueActive = $('#messageDescription').val();
		if(isNonEmpty(msgdesValueActive))
			$('button.close').click();
		else
			event.originalEvent.options.submit=false;
	});
});

function submitType() {
	$('#anyId').val('SMS');
}
$('.date-picker').datepicker().on('changeDate', function(ev){
	var startDate = $("#startDate").val();
	var endDate = $("#endDate").val();
	$('#dispaySelectedDate').hide();
	if (isNonEmpty(startDate) && isNonEmpty(endDate)) {
		if (Date.parse(endDate) < Date.parse(startDate)) {
			$('#dispaySelectedDate').html("<div class='alert alert-info'><div style='display: block'>Invalid Date Range!\nStart Date cannot be after End Date!</div></div>");
			$('#dispaySelectedDate').show();
			$('input#suspendDaysCount').val("");
			$("#endDate").val('');
			return false;
		} else {
			var custId=$('span#custIdSpan').attr("class");
			var daysDiff =0;
			$('input#halfdayleaveId').click( function(){
				if ($(this).is(':checked')){ 
					  daysDiff = (Date.parse(endDate) - Date.parse(startDate)) / 1000 / 60 / 60 / 24 + 1-0.5;
				 }else{
					  daysDiff = (Date.parse(endDate) - Date.parse(startDate)) / 1000 / 60 / 60 / 24 + 1;
				}
				$('input#suspendDaysCount').val(daysDiff);
			});
			  daysDiff = (Date.parse(endDate) - Date.parse(startDate)) / 1000 / 60 / 60 / 24 + 1;
			  $('input#suspendDaysCount').val(daysDiff);
		}
	}
});
</script>