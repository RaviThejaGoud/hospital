<%@ include file="/common/taglibs.jsp"%>
<div data-width="760" class="modal fade modal-overflow in"
	style="display: block; width: 760px; margin-left: -379px; margin-top: 150px;">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true"></button>
		<h4 class="modal-title">
			Add Leave
		</h4>
	</div>
	<span id="startDateSpan" class="<s:property value='startDate'/>"></span>
	<span id="endDateSpan" class="<s:property value='endDate'/>"></span>
	<span id="toDateSpan" class="<s:property value='toDate'/>"></span>
	<div class="modal-body">
	<div class="form-body">
	<%-- <s:if test='%{leaveManagement != null && tempString != "LNA"}'> --%>
	<s:if test='%{tempString != "LNA"}'>
	<s:form action="ajaxAddApplyStaffLeave" theme="simple" 
		cssClass="form-horizontal" id="updatedApplyStaffLeave" method="post"
		namespace="/admin">
		<s:hidden name="tempId1" value="%{tempId1}"></s:hidden>
			<div class="form-group">
				<label class="control-label col-md-3">
					<span class="required">*</span>Leave Type :
				</label>
				<div class="col-md-4">
					<s:select list="#{'CL':'Casual Leave','SL':'Sick Leave','EL':'Earned Leave'}"
						cssClass="required form-control" headerKey=""
						headerValue="- Select -" name="leave.leaveType"></s:select>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-md-3">
					<span class="required">*</span>Start Date :
				</label>
				<div class="col-md-5">
					<div data-date-start-date="+0d" data-date-format="mm/dd/yyyy"
						class="input-group input-medium date date-picker">
						<input type="text" id="startDate" readonly="" class="form-control required"
							name="leave.startDate">
						<span class="dateInput input-group-btn">
							<button type="button" class="btn default">
								<i class="fa fa-calendar"></i>
							</button> </span>
					</div>
					<span class="help-block">(MM/DD/YYYY)</span>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-md-3">
					<span class="required">*</span>End Date :
				</label>
				<div class="col-md-5">
					<div data-date-start-date="+0d" data-date-format="mm/dd/yyyy"
						class="input-group input-medium date date-picker">
						<input type="text" id="endDate" readonly="" class="form-control required"
							 name="leave.endDate">
						<span class="dateInput input-group-btn">
							<button type="button" class="btn default">
								<i class="fa fa-calendar"></i>
							</button> </span>
					</div>
					<span class="help-block">(MM/DD/YYYY)</span>
				</div>
			</div>
			<div id="dispaySelectedDate" style="display: none;">
			</div>
			<div class="form-group">
				<label class="control-label col-md-3">
					Half-day Leave :
				</label>
				<div class="col-md-5">
					<p class="form-control-static"> 
						<s:checkbox type="checkbox" name="leave.halfDay" id="halfdayleaveId" />
					</p>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-md-3">
					Leaves Count :
				</label>
				<div class="col-md-4">
					<sj:textfield id="leavesCount" name="leave.leavesCount"
						readonly="true" cssClass="form-control" />
				</div>
			</div>
			<div id="dispaySessionDate"  style="display: none;">
				<div class="form-group">
					<label class="control-label col-md-3"> <span
						class="required">*</span>Session :
					</label>
					<div class="col-md-5">
							<div class="make-switch has-switch" data-id="M" data-value="A"
								style="width: 180px" data-off="warning" data-on="success"
								data-off-label="Afternoon" data-on-label="Morning">
								<input type="radio" class="toggle" checked="checked"
									id="sessionTypeId" > <input type="hidden"
									name="leave.sessionType" value="M">
							</div>
					</div>
				</div>
			</div>	
			<div class="form-group">
				<label class="control-label col-md-3">
					<span class="required">*</span>Description :
				</label>
				<div class="col-md-8">
					<sj:textarea rows="3" cols="40" name="leave.description"
						cssClass="form-control  word_count required"  maxCharsData="2000"></sj:textarea>
					<div class="counter"></div>
				</div>
			</div>
			<div class="form-actions fluid">
				<div class="col-md-offset-3 col-md-4">
					<sj:submit   cssClass="submitBt btn blue" value="Submit" onBeforeTopics="applyLeaveFormValid"
						targets="stepStaffLeavesDiv" validate="true" />
					<button type="button" data-dismiss="modal" class="btn default">
						Cancel
					</button>
				</div>
			</div>
	</s:form>
	</s:if>
	<s:else>
		<div class="alert alert-info">
			Leaves are not defined.Please contact your administrator.
		</div>
	</s:else>
	</div>
  </div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/subscription/textcounter.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/ems.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/plugins/bootstrap-switch/static/js/bootstrap-switch.min.js"></script>
<script type="text/javascript">
$.subscribe('applyLeaveFormValid', function(event, data) {
		if ($('#updatedApplyStaffLeave').valid()) {
			$('button.close').click();
		} else {
			event.originalEvent.options.submit = false;
		}
	});
	
var startDate = $('span#startDateSpan').attr("class");
var endDate = $('span#endDateSpan').attr("class");
dateRestrictionWithinAcademicYear(startDate,endDate);
$('.date-picker').datepicker().on('changeDate', function(ev){
	var startDate = $("#startDate").val();
	var endDate = $("#endDate").val();
	$('#dispaySelectedDate').hide();
if(isNonEmpty(startDate) && isNonEmpty(endDate)){
			if (Date.parse(endDate) < Date.parse(startDate)) {
			$('#dispaySelectedDate').html("<div class='alert alert-info'><div style='display: block'>Invalid Date Range!\nStart Date cannot be after End Date!</div></div>");
				loadCloseIcon();
				$('#dispaySelectedDate').show();
				$("#endDate").val('');	
				$('input#leavesCount').val("");
				return false;
			}else{
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
				$.ajax( {
					url : jQuery.url.getChatURL("/staff/ajaxCheckLeaveDates.do?leave.startDate="+startDate+"&leave.endDate="+endDate+"&tempId2="+<s:property value="tempId"/>),
					cache : true,
					dataType : 'json',
					success : function(response) {
						if(isNonEmpty(response)){
							//alert("rrrr"+response+"==="+response.holidays);
							 if(isNonEmpty(response.holidays)){
									var hol= (daysDiff-((response.holidays).length));
									$('input#leavesCount').val(hol);  
									$('#dispaySelectedDate').html("<div class='alert alert-info'><div style='display: block'>" + response.holidays + " day(s) have holiday(s).</div></div>");
									$('#dispaySelectedDate').show();
									//alert("rrrr="+startDate+"=="+response.holidays+"==="+(response.holidays).length);
									 if((response.holidays).length==1){
										  if((startDate==response.holidays) && (endDate==response.holidays)){
											  $('input#leavesCount').val('0'); 
											  $("#startDate").val('');
											  $("#endDate").val('');
										  }
	  								}
									return false;
						}else if(isNonEmpty(response.leaveDesc)){
							$('#dispaySelectedDate').html("<div class='alert alert-info'><div style='display: block'>" + response.leaveDesc + "</div></div>");
												$('#dispaySelectedDate').show();
												$("#startDate").val('');
												$("#endDate").val('');
												$('input#leavesCount').val("");
												return false;
						}else if(isNonEmpty(response.noOfDays)){
							var leaveType=$('#leaveType').val();
								if(isNonEmpty(leaveType)){
								var noOfDays=response.noOfDays;	
									if(leaveType == "CL"){
										var casualLeaves = $("span#casualLeavesSpan").attr("class");
										if(isNonEmpty(casualLeaves) && isNonEmpty(noOfDays)){
											if(Number(casualLeaves) < Number(noOfDays)){
												$('#dispaySelectedDate').html("<div class='alert alert-info'><div style='display: block'>You leave days are exceded than number of casual days. Please change dates.</div></div>");
												$('#dispaySelectedDate').show();
												$("#startDate").val('');
												$("#endDate").val('');
												$('input#leavesCount').val("");
												return false;
											}else
											return true;
										}else
											return true;	
									}else if(leaveType == "SL"){
										var sickLeaves = $("span#sickLeavesSpan").attr("class");	
										if(isNonEmpty(sickLeaves) && isNonEmpty(noOfDays)){
											if(Number(sickLeaves) < Number(noOfDays)){
												$('#dispaySelectedDate').html("<div class='alert alert-info'><div style='display: block'>You leave days are exceded than number of sick days. Please change dates.</div></div>");
												$('#dispaySelectedDate').show();
												$("#startDate").val('');
												$("#endDate").val('');
												$('input#leavesCount').val("");
												return false;
											}else
											return true;
										}else
											return true;								
									}else
										return true;
								}
						}
						}
					}
				});	
			}
		}
});
	
	$.subscribe('displayDate', function(event, data) {
	var startDate = $('#startDate').val();
	var endDate = $('#endDate').val();
	if (isNonEmpty(startDate) && isNonEmpty(endDate)) {
		startDate = Date.parse(startDate);
		endDate = Date.parse(endDate);
		if (endDate <= startDate) {
			if(endDate < startDate){
				alert("Your end date is more than your start date.");
				$('#endDate').val("");
				$('input#leavesCount').val("");
				return false;
			}else{
				var daysDiff = (endDate - startDate) / 1000 / 60 / 60 / 24 + 1;
    			$('input#leavesCount').val(daysDiff);
    			return true;
			}
		} else {
			$('#admissEndDate').val("");
			/*var minutes = 1000*60;
    		var hours = minutes*60;
    		var days = hours*24;
    		var daysDiff = Math.round((endDate - startDate)/days);*/
			var daysDiff = (endDate - startDate) / 1000 / 60 / 60 / 24 + 1;
    		$('input#leavesCount').val(daysDiff);
    		return true;
		}
	}
});
$(document).ready(function() {
	FormComponents.init();
	FormAdvanced.init();
	$("input:checkbox, input:radio:not('.toggle')").uniform();	
	/* var startDate=$('span#startDateSpan').attr("class");
	var endDate=$('span#endDateSpan').attr("class");
	var toDayDate=$('span#toDateSpan').attr("class");
	if(isNonEmpty(startDate) && isNonEmpty(endDate) && isNonEmpty(toDayDate)){
		if (Date.parse(toDayDate) >=  Date.parse(startDate) && Date.parse(toDayDate) <=  Date.parse(endDate)) {
			$('#startDate').datepicker( "option" , 'minDate',startDate);
			$('#startDate').datepicker( "option" , 'maxDate',endDate);
			$('#endDate').datepicker( "option" , 'minDate',startDate);
			$('#endDate').datepicker( "option" , 'maxDate',endDate);
		}else if(Date.parse(toDayDate) <  Date.parse(startDate)){
			$('#startDate').datepicker( "option" , 'minDate',startDate);
			$('#startDate').datepicker( "option" , 'maxDate',endDate);
			$('#endDate').datepicker( "option" , 'minDate',startDate);
			$('#endDate').datepicker( "option" , 'maxDate',endDate);
		}else if(Date.parse(toDayDate) >  Date.parse(endDate)){
			$("#startDate").datepicker('disable');
			$("#endDate").datepicker('disable');
		}
	} */
}); 

$('input#halfdayleaveId').click( function(){
	if ($(this).is(':checked')){ 
		$('div#dispaySessionDate').show();
	}else{
		$('div#dispaySessionDate').hide();
	}
});
</script>
