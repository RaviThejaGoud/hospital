<%@ include file="/common/taglibs.jsp"%>
	<s:if test="%{tempId1 != 0}">
		<div data-width="760"  class="modal fade modal-overflow in"  style="display: block; width: 760px; margin-left: -379px; margin-top: 50px;" aria-hidden="false">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
			<h4 class="modal-title">
				Update Leave
			</h4>
		</div>
		<div class="modal-body">
	</s:if>
<div class="form-body">
	<span id="startDateSpan" class="<s:property value='startDate'/>"></span>
	<span id="endDateSpan" class="<s:property value='endDate'/>"></span>
	<span id="toDateSpan" class="<s:property value='toDate'/>"></span>
	<span id="custIdSpan" class="<s:property value='custId'/>"></span> 
	
	<s:if test="%{(startDate != null && startDate != empty) && (endDate != null && endDate != empty)}">
		<s:if test='%{viewStudentLeaveDetails.status=="Y" || viewStudentPersonAccountDetails.status=="Y"}'>
			<s:form action="ajaxApplyLeaveForm" theme="simple" 
			cssClass="form-horizontal" id="addLeaveReportForStudent" namespace="/student">
			<s:hidden name="academicYearId" value="%{academicYearId}" id="academicYear" />
			<s:hidden name="tempId1" value="%{tempId1}" />
			<p>
				<span class="label label-danger"> NOTE :</span> &nbsp;It's simple
				process. Select start date and end date, provide description and
				press submit. Your child leave is applied to class teacher.
			</p>
			<div class="form-group">
				<label class="control-label col-md-2">
					Student Name :
				</label>
				<div class="col-md-3">
					<s:if
						test="%{viewStudentPersonAccountDetailsList !=null && !viewStudentPersonAccountDetailsList.isEmpty()}">
						<div>
							<s:select id="sectionId"
								list="viewStudentPersonAccountDetailsList"
								cssClass="form-control required  input-medium"
								onchange="javascript:loadStudentAcademicYearDetails(this.value);"
								listKey="accountIdClassSectionIdAcademicYearIdAndCustId"
								listValue="idAndName" name="anyId" />
						</div>
					</s:if>
					<s:elseif test='%{viewStudentLeaveDetails != null && ViewStudentLeaveDetails != empty}'>
 							<p class="form-control-static"> <strong><s:property value="viewStudentLeaveDetails.idAndName" /> </strong></p>
							<s:hidden name="anyId" value="%{viewStudentLeaveDetails.accountIdClassSectionIdAcademicYearIdAndCustId}" id="sectionId"></s:hidden>
					</s:elseif>
					<s:else>
							Oops! No Students find for you to apply leave. <!-- Please contact <a href="http://support.eazyschool.com">Submit Ticket</a> -->
					</s:else>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-md-2">
					<span class="required">*</span>Approver :
				</label>
				<div class="col-md-3">
					<s:if
						test="%{classTeacherList != null && !classTeacherList.isEmpty()}">
						<s:select id="id" list="classTeacherList"
							cssClass="form-control input-medium required" listKey="staff.account.id"
							listValue="classTeatherName" headerKey=""
							 name="leave.supervisorId" />
					</s:if>
					<s:elseif test="%{selectboxMap != null && !selectboxMap.isEmpty()}">
						<s:select id="teachStaffList" list="selectboxMap" headerKey=""
							 cssClass="form-control input-medium required"
							name="leave.supervisorId" />
					</s:elseif>
					<s:else>
						 No Approver available to apply leave. 
					 </s:else>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-md-2">
					<span class="required">*</span>Leave Type :
				</label>
				<div class="col-md-3">
					<s:select list="#{'CL':'Casual Leave','SL':'Sick Leave'}"
						cssClass="required form-control  input-medium" headerKey=""
						headerValue="- Select -" name="viewStudentLeaveDetails.leaveType"></s:select>
				</div>
			</div>
			<div class="form-group ">
				<label class="control-label col-md-2">
					<span class="required">*</span>Start Date :
				</label>
				<div class="col-md-5">
					<div data-date-start-date="-4d" data-date-format="mm/dd/yyyy"
						class="input-group input-medium date date-picker">
						<s:if test="%{tempId1 != 0}">
							<input type="text" id="startDate" readonly=""
								class="form-control fdate required"
								value='<s:property value="viewStudentLeaveDetails.startDateFormat"/>'
								name="viewStudentLeaveDetails.startDate">
						</s:if>
						<s:else>
							<input type="text" id="startDate" readonly=""
								class="form-control fdate required"
								name="viewStudentLeaveDetails.startDate">
						</s:else>
						<span class="dateInput input-group-btn">
							<button type="button" class="btn default">
								<i class="fa fa-calendar"></i>
							</button> </span>
					</div>
					<span class="help-block">(MM/DD/YYYY)</span>
				</div>
			</div>
			<div class="form-group ">
				<label class="control-label col-md-2">
					<span class="required">*</span>End Date :
				</label>
				<div class="col-md-5">
					<div data-date-start-date="-4d" data-date-format="mm/dd/yyyy"
						class="input-group input-medium date date-picker">
						<s:if test="%{tempId1 != 0}">
							<input type="text" id="endDate" readonly=""
								class="form-control fdate required"
								value='<s:property value="viewStudentLeaveDetails.endDateFormat"/>'
								name="viewStudentLeaveDetails.endDate">
						</s:if>
						<s:else>
							<input type="text" id="endDate" readonly=""
								class="form-control fdate required"
								name="viewStudentLeaveDetails.endDate">
						</s:else>
						<span class="dateInput input-group-btn">
							<button type="button" class="btn default">
								<i class="fa fa-calendar"></i>
							</button> </span>
					</div>
					<span class="help-block">(MM/DD/YYYY)</span>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-md-2">
					Half-day Leave :
				</label>
				<div class="col-md-3 form-control-static">
						<s:checkbox name="viewStudentLeaveDetails.halfDay" id="halfdayleaveId" />
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-md-2">
					Leaves Count :
				</label>
				<div class="col-md-3">
					<sj:textfield id="leavesCount"  name="viewStudentLeaveDetails.leavesCount"
						readonly="true" cssClass="form-control  input-medium" />
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
								<s:if test="%{tempId1 != 0}">
									<div class="make-switch has-switch" data-id="M" data-value="A"
										style="width:180px" data-off="warning" data-on="success"
										data-off-label="Afternoon" data-on-label="Morning">
										<s:if test='%{viewStudentLeaveDetails.sessionType =="M"}'>
											<input type="radio" class="toggle" checked="checked"
												id="halfDayIds">
											<input type="hidden"
												name="viewStudentLeaveDetails.sessionType"
												value='<s:property value="viewStudentLeaveDetails.sessionType"/>'>
										</s:if>
										<s:elseif test='%{viewStudentLeaveDetails.sessionType=="A"}'>
											<input type="radio" class="toggle" id="halfDayIds">
											<input type="hidden"
												name="viewStudentLeaveDetails.sessionType"
												value='<s:property value="viewStudentLeaveDetails.sessionType"/>'>
										</s:elseif>
										<s:else>
											<input type="radio" class="toggle" checked="checked"
												id="halfDayIds">
											<input type="hidden"
												name="viewStudentLeaveDetails.sessionType" value='M'>
										</s:else>
									</div>
								</s:if>
								<s:else>
									<div class="make-switch has-switch" data-id="M" data-value="A"
										style="width: 180px" data-off="warning" data-on="success"
										data-off-label="Afternoon" data-on-label="Morning">
										<input type="radio" class="toggle" checked="checked"
											id="sessionTypeId" > <input type="hidden"
											name="viewStudentLeaveDetails.sessionType" value="M">
									</div>
								</s:else>
							</div>
						</div>
					</div>
				</div>
				</div>
			<s:if test='%{viewStudentPersonAccountDetails.hostelMode == "H"}'>
				<div class="form-group">
					<label class="control-label col-md-2">
						Apply this leave for hostel :
					</label>
					<div class="col-md-3 form-control-static">
							<s:checkbox name="hostelLeave" id="hostelLeave" />
					</div>
				</div>
			</s:if>
			<div id="dispaySelectedDate" class="grid_12" style="display: none;">
			</div>
			<div class="form-group">
				<label class="control-label col-md-2">
					<span class="required">*</span>Description :
				</label>
				<div class="col-md-6">
					<sj:textarea rows="3" cols="40"
						name="viewStudentLeaveDetails.description"
						cssClass="form-control  word_count required"  maxCharsData="2000"></sj:textarea>
					<div class="counter"></div>
				</div>
			</div>
			<div class="form-actions fluid">
				<div class="col-md-offset-2 col-md-9">
					<sj:submit   cssClass="submitBt btn blue" value="Submit" onBeforeTopics="parentLeaveForm" formIds="addLeaveReportForStudent"
						indicator="indicator" targets="mainContentDiv" validate="true" />
					<s:if test="%{tempId1 != 0}">
							<button type="button" data-dismiss="modal" class="btn default">
								Cancel
							</button>
					</s:if>
					<s:else>
						<s:url id="doCancelLeave" action="ajaxDoGetStudentLeaveDetails"
							includeParams="all" escapeAmp="false">
							<s:param name="tempId1" value="0" />
							<s:param name="tempId" value="0" />
						</s:url>
						<sj:a href="%{doCancelLeave}" cssClass="btn default"
							indicator="indicator" targets="mainContentDiv">Cancel</sj:a>
					</s:else>
				</div>
			</div>
		</s:form>
		</s:if>	
		<s:else>
			<div class="alert alert-info">
				Currently there are no active academic schedules for this student.
			</div>
		</s:else>
	</s:if>
	<s:else>
		<div class="alert alert-info">
			Academic planner not defined.Please contact your school
			administrator.
		</div>
	</s:else>
</div>
<s:if test="%{tempId1 != 0}">
	</div>
	</div>
</s:if>
<script type="text/javascript" src="${pageContext.request.contextPath}/plugins/bootstrap-switch/static/js/bootstrap-switch.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/subscription/textcounter.js"> </script>
<script type="text/javascript">
$(document).ready(function() {
	$("input:checkbox, input:radio:not('.toggle')").uniform();
	changePageTitle('Apply Leave');
	var leaveId='<s:property value="tempId1"/>';
	if(isNonEmpty(leaveId) && leaveId > 0){
		 var count = $('input#leavesCount').val();
		// alert(count);
		 if(count>0){
			 if(count.toString().split(".")[1]==5){
				 //alert(count.toString().split(".")[1]);
			 	 $('input#halfdayleaveId').parent('span').addClass('checked');
			 } 
		 }
	}
});

var halfDayleave = '<s:property value="viewStudentLeaveDetails.halfDay"/>';
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

var startDate = $('span#startDateSpan').attr("class");
var endDate = $('span#endDateSpan').attr("class");
var stDate = new Date(startDate);
var enDate = new Date(endDate);
var yyyy ='';
var ayyyy ='';			
//alert(strDate+"===="+endDate);
var dd = stDate.getDate();
var mm = stDate.getMonth()+1; //January is 0!
var sday = mm+'/'+dd+'/'+yyyy;
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
//var sday = mm+'/'+dd+'/'+yyyy;
var eday = emm+'/'+edd+'/'+ayyyy;
$('div.date-picker').datepicker({
    startDate: sday,
    endDate: eday
}); 
FormAdvanced.init();
FormComponents.init();
$('.date-picker').datepicker().on('changeDate', function(ev){
	checkLeaveDates();
});
function checkLeaveDates(){
	var startDate = $("#startDate").val();
	var endDate = $("#endDate").val();
	var multiIds=$('#sectionId').val();
	$('#dispaySelectedDate').hide();
	if (isNonEmpty(startDate) && isNonEmpty(endDate) && isNonEmpty(multiIds)) {
	    	if (Date.parse(endDate) < Date.parse(startDate)) {
				$('#dispaySelectedDate').html("<div class='alert alert-info'><div style='display: block'>Invalid Date Range!\nStart Date cannot be after End Date!</div></div>");
				$('#dispaySelectedDate').show();
				$('input#leavesCount').val("");
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
					$('input#leavesCount').val(daysDiff);
				});
				  daysDiff = (Date.parse(endDate) - Date.parse(startDate)) / 1000 / 60 / 60 / 24 + 1;
				  if ($('input#halfdayleaveId').is(':checked')){ 
				   daysDiff=daysDiff-0.5;
				  }
				  $('input#leavesCount').val(daysDiff);
				  var leaveId='<s:property value="tempId1"/>';
				  if(isNonEmpty(leaveId)){
					  var url = jQuery.url.getChatURL("/student/ajaxCheckLeaveDatesForHolidays.do?leave.id="+ leaveId + "&leave.startDate="+ startDate + "&leave.endDate="+ endDate+"&custId="+custId+"&anyTitle="+(multiIds.split('_'))[0]+"&academicYearId="+$('#academicYear').val()+"&studyClassId="+(multiIds.split('_'))[1]);
				  }else{
					  var url = jQuery.url.getChatURL("/student/ajaxCheckLeaveDatesForHolidays.do?leave.startDate="+ startDate + "&leave.endDate="+ endDate+"&custId="+custId+"&anyTitle="+(multiIds.split('_'))[0]+"&academicYearId="+$('#academicYear').val()+"&studyClassId="+(multiIds.split('_'))[1]);
				  } 
				$.ajax( {
						url : url,
						cache : false,
						dataType : 'json',
						success : function(response) {
							if (isNonEmpty(response)) {
								if(isNonEmpty(response.holidays)){
										var hol= (daysDiff-((response.holidays).length));
										$('input#leavesCount').val(hol); 
										$('#dispaySelectedDate').html("<div class='alert alert-info'><div style='display: block'>" + response.holidays + " day(s) have general holiday(s).</div></div>");
										$('#dispaySelectedDate').show();
										$('input#leavesCount').val(""); 
										$("#startDate").val('');
										$("#endDate").val('');
										return false;
								  }
								  if(isNonEmpty(response.leaveDesc)){
									$('#dispaySelectedDate').html("<div class='alert alert-info'><div style='display: block'>" + response.leaveDesc + "</div></div>");
									$('#dispaySelectedDate').show();
									$("#startDate").val('');
									$("#endDate").val('');
									$('input#leavesCount').val("");
									return false;
								}
						}
					}
				});
	    }
	}
}
function loadStudentAcademicYearDetails(ids){
  if(isNonEmpty(ids)){
		$('#parentLeaveContent').html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		$.ajax( {
				url : jQuery.url.getChatURL("/student/ajaxGetStudentAcademicYearDetails.do?anyId="+ids),
				cache : true,
				success : function(response) {
				 	if(isNonEmpty(response)){
				 		$('#parentLeaveContent').html(response);
					}
				}
			});				
 		 }
  }

  $.subscribe('parentLeaveForm', function(event, data) {
		$('button.close').click();
	});
</script>