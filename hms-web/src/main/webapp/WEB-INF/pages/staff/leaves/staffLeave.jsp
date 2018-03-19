<%@ include file="/common/taglibs.jsp"%>
<%-- <s:if test="%{studySubject.id != 0}">
<div data-width="760"  class="modal fade modal-overflow in" id="responsive2" style="display: block; width: 760px; margin-left: -379px; margin-top: 200px;" aria-hidden="false">
<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
		<h4 class="bold pageTitle">
			Update Subject
		</h4>
	</div>
	<div class="modal-body">
	</s:if> --%>
<s:if test="%{anyId != null && !anyId.isEmpty()}">
 <div data-width="760" class="modal fade modal-overflow in" id="popupPendingLeavesDiv" style="display: block; width: 800px; margin-left: -379px; margin-top: 100px;" aria-hidden="false">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
		<h4 class="modal-title">
			Edit Pending Leaves
		</h4>
	</div>
	<div class="modal-body">
</s:if>
<div class="form-body">
<span id="startDateSpan" class="<s:property value='startDate'/>"></span>
<span id="endDateSpan" class="<s:property value='endDate'/>"></span>
<span id="toDateSpan" class="<s:property value='toDate'/>"></span>
<span id="casualLeavesSpan" class="<s:property value='casualLeave'/>"></span>
<span id="sickLeavesSpan" class="<s:property value='sickLeave'/>"></span>
<s:if
	test="%{(startDate != null && startDate != empty) && (endDate != null && endDate != empty)}">
	<s:if test="%{(leaveManagement != null && leaveManagement != empty) && tempString !='LNA'}">
		<s:form action="ajaxAddleaveReport" theme="simple"
			cssClass="form-horizontal" id="addLeaveReport" namespace="/staff">
			<s:hidden name="anyId" value="%{anyId}" />
			<s:hidden name="academicYearId" value="%{academicYear.id}" />
			<s:hidden name="academicYear.startTime"
				value="%{academicYear.startTime}" />
			<s:hidden name="academicYear.endTime" value="%{academicYear.endTime}" />
			<jsp:include page="/common/messages.jsp"></jsp:include>
			<p>
				<span class="label label-danger"> NOTE : </span> &nbsp;&nbsp;
				 You can apply leave by keying the below fields.
			</p>
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4">
								<span class="required">*</span>Leave Type :
							</label>
							<div class="col-md-5">
								<s:if test="%{leaveTypes != null && !leaveTypes.isEmpty()}">
									<s:select list="leaveTypes" label="Leave Type" id="leaveType"
										headerKey="" headerValue="- Select -" name="leave.leaveType"
										cssClass="required form-control input-medium"></s:select>
								</s:if>
								<s:else>
									 No leaveTypes are available.
								</s:else>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4">
								<span class="required">*</span>Approver :
							</label>
							<div class="col-md-5">
								<s:if test="%{selectboxMap != null && !selectboxMap.isEmpty()}">
									<s:select id="teachStaffList" list="selectboxMap"
										label="Approver" headerKey="" headerValue="- Select -"
										cssClass="required form-control input-medium"
										name="leave.supervisorId" />
								</s:if>
								<s:else>
									No Approver available to apply leave. 
								</s:else>
							</div>
						</div>
					</div>
				</div>
				<!--<h4 class="pageTitle bold">
					Duration :
				</h4>
				--><div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4">
								<span class="required">*</span>Start Date :
							</label>
							<div class="col-md-5">
								<div data-date-format="mm/dd/yyyy"
									class="input-group input-medium date date-picker">
									<input id="startDate" name="leave.startDate" readonly="" type="text"
										class="form-control required"
										value='<s:property value="leave.startDateStr"/>'>
									<span class="dateInput input-group-btn">
										<button type="button" class="btn default">
											<i class="fa fa-calendar"></i>
										</button> </span>
								</div>
								<span class="help-block">(MM/DD/YYYY)</span>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4">
								<span class="required">*</span> End Date :
							</label>
							<div class="col-md-5">
								<div  data-date-format="mm/dd/yyyy"
									class="input-group input-medium date date-picker">
									<input id="endDate" name="leave.endDate" readonly=""  type="text"
										class="form-control required"
										value='<s:property value="leave.endDateStr"/>'>
									<span class="dateInput input-group-btn">
										<button type="button" class="btn default">
											<i class="fa fa-calendar"></i>
										</button> </span>
								</div>
								<span class="help-block">(MM/DD/YYYY)</span>
							</div>
						</div>
					</div>
				</div>
				<div id="dispaySelectedDate"  style="display: none;"> </div>
				<div class="row">
				<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4">
								Half-day Leave :
							</label>
							<div class="form-control-static">
									<s:checkbox type="checkbox" name="leave.halfDay" id="halfdayleaveId" />
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4">
								Leaves Count :
							</label>
							<div class="col-md-5">
								<sj:textfield id="leavesCount" name="leave.leavesCount"
									readonly="true" cssClass="required form-control input-medium" />
							</div>
						</div>
					</div>
				</div>
				<div id="dispaySessionDate"  style="display: none;">
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4"> <span
								class="required">*</span>Session :
							</label>
							<div class="col-md-5">
								<s:if test="%{anyId != null && !anyId.isEmpty()}">
									<div class="make-switch has-switch" data-id="M" data-value="A"
										style="width: 180px" data-off="warning" data-on="success"
										data-off-label="Afternoon" data-on-label="Morning">
										<s:if test='%{leave.sessionType =="M"}'>
											<input type="radio" class="toggle" checked="checked"
												id="halfDayIds">
											<input type="hidden"
												name="leave.sessionType"
												value='<s:property value="leave.sessionType"/>'>
										</s:if>
										<s:elseif test='%{leave.sessionType=="A"}'>
											<input type="radio" class="toggle" id="halfDayIds">
											<input type="hidden"
												name="leave.sessionType"
												value='<s:property value="leave.sessionType"/>'>
										</s:elseif>
										<s:else>
											<input type="radio" class="toggle" checked="checked"
												id="halfDayIds">
											<input type="hidden"
												name="leave.sessionType" value='M'>
										</s:else>
									</div>
								</s:if>
								<s:else>
									<div class="make-switch has-switch" data-id="M" data-value="A"
										style="width: 180px" data-off="warning" data-on="success"
										data-off-label="Afternoon" data-on-label="Morning">
										<input type="radio" class="toggle" checked="checked"
											id="sessionTypeId" > <input type="hidden"
											name="leave.sessionType" value="M">
									</div>
								</s:else>
							</div>
						</div>
					</div>
				</div>
				</div>
				<div class="row">
					<div class="col-md-8">
						<div class="form-group">
							<label class="control-label col-md-3">
							<span class="required">*</span>	Description :
							</label>
							<div class="col-md-7">
								<sj:textarea rows="4" cols="30" name="leave.description"  maxCharsData="2000"
									cssClass="form-control word_count required" readonly="readonly" ></sj:textarea>
								<span class="help-block">
									<div class="counter"></div> </span>
							</div>
						</div>
					</div>
				</div>
				<div class="form-actions fluid">
					<div class="col-md-offset-2 col-md-9">
						<sj:submit cssClass="submitBt btn blue" value="Submit"
							indicator="indicator"
							targets="stepStaffLeavesDiv" validate="true"
							onBeforeTopics="formValidationForLeaves" />
						<s:if test="%{anyId != null && !anyId.isEmpty()}">
							<button type="button" data-dismiss="modal" class="btn default">
								Cancel
							</button>
						</s:if>
						<s:else>
							<s:url id="doCancelLeave" action="ajaxViewStaffLeaves"
								includeParams="all" namespace="/staff"></s:url>
							<sj:a href="%{doCancelLeave}" cssClass="btn default"
								targets="stepStaffLeavesDiv">Cancel</sj:a>
						</s:else>
					</div>
				</div>
		</s:form>
	</s:if>
	<s:else>
		<div class="alert alert-info">
			Leaves are not defined.Please contact your administrator.
		</div>
	</s:else>
</s:if>
<s:else>
	<div class="alert alert-info">
		Academic planner not created.Please contact your administrator.
	</div>
</s:else>
</div>
<s:if test="%{anyId != null && !anyId.isEmpty()}">
</div>
</div>
</s:if>
<script type="text/javascript"src="${pageContext.request.contextPath}/scripts/subscription/textcounter.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/plugins/bootstrap-switch/static/js/bootstrap-switch.min.js"></script>
<script type="text/javascript">
changePageTitle('Apply Leaves');
$(document).ready(function() {
	FormComponents.init();
	FormAdvanced.init();
	$.destroyTopic('formValidationForLeaves');
	$("input:checkbox, input:radio:not('.toggle')")
	.uniform();
	var leaveId='<s:property value="anyId"/>';
	if(isNonEmpty(leaveId) && leaveId > 0){
		var count = $('input#leavesCount').val();
		var costpart = String(count).split(".");
		if (costpart[1] == '.5') {
			  $('input#halfdayleaveId').parent('span').addClass('checked');
		}
		/* if(count.contains(".5")){
		  $('input#halfdayleaveId').parent('span').addClass('checked');
		 } */	
	}
    $.subscribe('formValidationForLeaves', function(event, data) {
   	 	//$('button.close').click();
		if (<s:property value="selectboxMap.size()"/>==0){
			alert("Please select approver.");
			event.originalEvent.options.submit=false;
		}
		else
		{
			if ($('#addLeaveReport').valid()) {
				$('button.close').click();
				return true;
			} else {
				event.originalEvent.options.submit = false;
			}
		}
   });
    
    var halfDayleave = '<s:property value="leave.halfDay"/>';
    if(isNonEmpty(halfDayleave) && halfDayleave=="true") {
    	$('#halfdayleaveId').parent('span').addClass('checked');
		$('#halfdayleaveId').attr("checked", true);
		$('div#dispaySessionDate').show();
    } else {
    	$('#halfdayleaveId').parent('span').removeClass('checked');
		$('#halfdayleaveId').attr("checked", false);
    }
    $('input#halfdayleaveId').click( function(){
    	if ($(this).is(':checked')){ 
    		$('div#dispaySessionDate').show();
    	}else{
    		$('div#dispaySessionDate').hide();
    	}
    	checkLeaveDates();
    });
 	
});
  //  FormComponents.init();
$('.date-picker').datepicker().on('changeDate', function(ev){
	checkLeaveDates();
});

function checkLeaveDates(){
	var startDate = $("#startDate").val();
	var endDate = $("#endDate").val();
	var sStartDate = $('span#startDateSpan').attr("class");
	var sEndDate = $('span#endDateSpan').attr("class");
	var stDate = new Date(sStartDate);
	var enDate = new Date(sEndDate);
	var yyyy ='';
	var ayyyy ='';			
	//alert(strDate+"===="+endDate);
	var dd = stDate.getDate();
    var mm = stDate.getMonth()+1; //January is 0!
    var edd = enDate.getDate();
    var emm = enDate.getMonth()+1; //January is 0!
    $.browser.chrome = /chrom(e|ium)/.test(navigator.userAgent.toLowerCase()); 
    if($.browser.chrome) {
    		yyyy = stDate.getFullYear();
    		ayyyy = enDate.getFullYear();
    	} else if ($.browser.mozilla) {
    		if($.browser.version >=49){
	    		yyyy = stDate.getFullYear();
	    		ayyyy = enDate.getFullYear();
    		}else{
    			yyyy = stDate.getFullYear()+100;
	    		ayyyy = enDate.getFullYear()+100;
    		}
    	} else if ($.browser.msie) {
    		yyyy = stDate.getFullYear()+100;
    		ayyyy = enDate.getFullYear()+100;
    	}
    if(dd<10){
        dd='0'+dd
    } 
    if(mm<10){
        mm='0'+mm
    } 
    if(edd<10){
        edd='0'+edd
    } 
    if(emm<10){
        emm='0'+emm
    } 
    var sday = mm+'/'+dd+'/'+yyyy;
    var eday = emm+'/'+edd+'/'+ayyyy;
    
	$('#dispaySelectedDate').hide();
	if (isNonEmpty(startDate) && isNonEmpty(endDate)) {
		if ((Date.parse(eday) < Date.parse(endDate)) || (Date.parse(sday) > Date.parse(startDate))) {
			$('#dispaySelectedDate').html("<div class='alert alert-info'><div style='display: block'>You can apply leave only within Academic year duration.</div></div>");
			$('#dispaySelectedDate').show();
			$("#endDate").val('');
			$("#startDate").val('');
			$('input#leavesCount').val("");
			return false;
	    }else{
	    	if (Date.parse(endDate) < Date.parse(startDate)) {
				$('#dispaySelectedDate').html("<div class='alert alert-info'><div style='display: block'>Invalid Date Range!\nStart Date cannot be after End Date!</div></div>");
				$('#dispaySelectedDate').show();
				$("#endDate").val('');
				$('input#leavesCount').val("");
				return false;
			}else {
				var daysDiff =0;
				$('input#halfdayleaveId').click( function(){
					if ($(this).is(':checked')){ 
						  daysDiff = (Date.parse(endDate) - Date.parse(startDate)) / 1000 / 60 / 60 / 24 + 1-0.5;
					 }else{
						  daysDiff = (Date.parse(endDate) - Date.parse(startDate)) / 1000 / 60 / 60 / 24 + 1;
					}
					$('input#leavesCount').val(daysDiff);
				});
				  daysDiff = (Date.parse(endDate) - Date.parse(startDate)) / 1000 / 60 / 60 / 24 + 1;
				  if ($('input#halfdayleaveId').is(':checked')){ 
				   daysDiff=daysDiff-0.5;
				  }
				  $('input#leavesCount').val(daysDiff);
				  var leavesCount = $('input#leavesCount').val();
				  var leaveId='<s:property value="anyId"/>';
				 if(isNonEmpty(leaveId)){
					  var url = jQuery.url.getChatURL("/staff/ajaxCheckLeaveDates.do?leave.startDate=" + startDate + "&leave.endDate=" + endDate + "&leave.id="+leaveId +"&leave.leavesCount="+leavesCount);
				  }else{
					  var url = jQuery.url.getChatURL("/staff/ajaxCheckLeaveDates.do?leave.startDate=" + startDate + "&leave.endDate=" + endDate+"&leave.leavesCount="+leavesCount);
				  } 
				$.ajax( { url : url,
							cache : true,
							dataType : 'json',
							success : function(response) {
								if (isNonEmpty(response)) {
									if (isNonEmpty(response.holidays)) {
										var hol= (daysDiff-((response.holidays).length));
										$('input#leavesCount').val(hol);  
										$('#dispaySelectedDate').html("<div class='alert alert-info'><div style='display: block'>" + response.holidays + " day(s) have holiday(s).</div></div>");
										$('#dispaySelectedDate').show();
										$('input#leavesCount').val('0'); 
										  $("#startDate").val('');
										  $("#endDate").val('');
										return false;
									} else if (isNonEmpty(response.leaveDesc)) {
										$('#dispaySelectedDate').html(
												"<div class='alert alert-info'><div style='display: block'>"
														+ response.leaveDesc
														+ "</div></div>");
										$('#dispaySelectedDate').show();
										$("#startDate").val('');
										$("#endDate").val('');
										$('input#leavesCount').val("");
										return false;
									} else if (isNonEmpty(response.noOfDays)) {
										var leaveType = $('#leaveType').val();
										if (isNonEmpty(leaveType)) {
											var noOfDays = response.noOfDays;
											if (leaveType == "CL") {
												var casualLeaves = $(
														"span#casualLeavesSpan")
														.attr("class");
												if (isNonEmpty(casualLeaves)
														&& isNonEmpty(noOfDays)) {
													if (Number(casualLeaves) < Number(noOfDays)) {
														$('#dispaySelectedDate')
																.html(
																		"<div class='alert alert-info'><div style='display: block'>You leave days are exceded than number of casual days. Please change dates.</div></div>");
														$('#dispaySelectedDate')
																.show();
														//$("#startDate").val('');
														$("#endDate").val('');
														$('input#leavesCount').val(
																"");
														return false;
													} else
														return true;
												} else
													return true;
											} else if (leaveType == "SL") {
												var sickLeaves = $(
														"span#sickLeavesSpan")
														.attr("class");
												if (isNonEmpty(sickLeaves)
														&& isNonEmpty(noOfDays)) {
													if (Number(sickLeaves) < Number(noOfDays)) {
														$('#dispaySelectedDate')
																.html(
																		"<div class='alert alert-info'><div style='display: block'>You leave days are exceded than number of sick days. Please change dates.</div></div>");
														$('#dispaySelectedDate')
																.show();
														//$("#startDate").val('');
														$("#endDate").val('');
														$('input#leavesCount').val(
																"");
														return false;
													} else
														return true;
												} else
													return true;
											} else
												return true;
										}
									}
								}
							}
						});
			}
	    }
  }
}

</script>
