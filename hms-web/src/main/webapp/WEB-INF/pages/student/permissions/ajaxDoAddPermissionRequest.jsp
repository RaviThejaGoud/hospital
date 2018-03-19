<%@ include file="/common/taglibs.jsp"%>
  <div class="form-body">
<s:form id="addPermissions" action="ajaxAddStudentPermissionsRequest" method="post"
		theme="simple" cssClass="form-horizontal" namespace="/student">
		<s:hidden name="tempString" id='stuPermissionData' />
		<span id="studentSpan" class="<s:property value='tempId'/>"></span>
		<div class="row">
			<div class="col-md-6">
			    <div class="form-group">
					<label class="control-label col-md-4">
						<span class="required">*</span>Student Name :
					</label>
					<div class="col-md-4">
						<s:if
							test="%{viewStudentPersonAccountDetailsList !=null && !viewStudentPersonAccountDetailsList.isEmpty()}">
							<div>
								<s:select id="sectionId" list="viewStudentPersonAccountDetailsList"
									cssClass="form-control required  input-medium"
									listKey="studentIdClassSectionIdAcademicYearIdAndCustId"
									listValue="idAndName" name="anyId" />
							</div>
						</s:if>
						<s:else>
								Oops! No Students find for you to apply leave.
						</s:else>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4"> <span
						class="required">*</span>Start Date :
					</label>
					<div class="col-md-6">
						<div data-date-format="mm/dd/yyyy"
							class="input-group input-medium date date-picker">
							<input type="text" id="startDate" readonly=""
								class="form-control required" onchange="verifyDate();"
								name="startDate"> <span
								class="dateInput input-group-btn">
								<button type="button" class="btn default">
									<i class="fa fa-calendar"></i>
								</button>
							</span>
						</div>
						<span class="help-block">(MM/DD/YYYY)</span>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4"><span
						class="required">*</span>End Date :</label>
					<div class="col-md-6">
						<div data-date-format="mm/dd/yyyy"
							class="input-group input-medium date date-picker">
							<input type="text" id="endDate" readonly=""
								class="form-control required" onchange="verifyDate();"
								name="endDate"> <span
								class="dateInput input-group-btn">
								<button type="button" class="btn default">
									<i class="fa fa-calendar"></i>
								</button>
							</span>
						</div>
						<span class="help-block">(MM/DD/YYYY)</span>
					</div>
				</div>
			</div>
		</div>
		<table class="table">
			<thead>
				<tr>
					<th>Day</th>
					<th>Start Time</th>
					<th>End Time</th>
					<th>If you want all timings Same</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="weekDayList">
					<tr class="dayList"  id='<s:property value="id" />'>
						<td id="day_<s:property value="id" />"><s:property value="dayName" /></td>
						<td class="startDiv"><div class="input-group bootstrap-timepicker col-md-5" id='<s:property value="id" />'>
								<input type="text" class="form-control timepicker-default startTimeChange" value="" name="permissions.startTime" id='startTime_<s:property value="id" />'>
								<span class="input-group-btn">
									<button class="btn default" type="button">
										<i class="fa fa-clock-o"></i>
									</button>
								</span>
							</div>
						</td>
						<td class="endDiv"><div class="input-group bootstrap-timepicker col-md-5" id='<s:property value="id" />'>
								<input type="text" class="form-control timepicker-default startTimeChange"  value="" name="permissions.endTime" id='endTime_<s:property value="id" />'>
								<span class="input-group-btn">
									<button class="btn default" type="button">
										<i class="fa fa-clock-o"></i>
									</button>
								</span>
							</div>
					  </td>
					  <td class="col-md-2"><input type="checkbox" name="copyPaste" class="copyPaste" /></td>
					</tr>
				</s:iterator>
			</tbody>
		</table>
		<div class="row">
			<div class="col-md-8">
				<div class="form-group ">
					<label class="control-label col-md-4"><span class="required">*</span>Reason For Permission :</label>
					<div class="col-md-6">
						<sj:textarea name="comments"
							label="Event Description" id="comments"
							cssClass="form-control required"></sj:textarea>
					</div>
				</div>
			</div>
		</div>
		<div class="form-actions fluid">
			<div class="col-md-offset-2 col-md-9">
				<sj:submit value="Submit" cssClass="submitBt btn blue"
					onBeforeTopics="newPermissionsForm" formIds="addPermissions" 
					targets="mainContentDiv" indicator="indicator" validate="true" />
					
					<s:url id="urlPermissions" action="ajaxViewParentPermissions"  namespace="/student"/>
					<sj:a  cssClass="btn default"
						href="%{urlPermissions}" targets="mainContentDiv">Cancel</sj:a>
			</div>
		</div>
	</s:form>
</div>
<script type="text/javascript">
	$("input:checkbox, input:radio").uniform();
	$(document).ready(function() {
		FormComponents.init();
		changePageTitle("Parent Permission Request");
		$("input:checkbox, input:radio:not('.toggle')").uniform();  
	});
	$.destroyTopic('newPermissionsForm');
	function verifyDate() {
		var startDate = $('#startDate').val();
		var endDate = $('#endDate').val();
		if (isNonEmpty(startDate) && isNonEmpty(endDate)) {
			if (endDate < startDate) {
				alert("Your end date is equal or more than your start date.");
				$('#endDate').val("");
			}
		}
	}
	 
	function checkStartTimeEndTImeValidation() {
		var startDate = $('#startDate').val();
		var endDate = $('#endDate').val();
		if (isNonEmpty(startDate) && isNonEmpty(endDate)) {
			var startTime = $('#startTime').val();
			var endTime = $('#endTime').val();
			if (isNonEmpty(startTime) && isNonEmpty(endTime)) {
				var selectedStartTime = new Date(startDate + " " + startTime);
				var selectEndTime = new Date(endDate + " " + endTime);
				if (selectEndTime <= selectedStartTime) {
					alert("Start time should be less than end time.");
					$('#endTime').val('');
				}
			}

		} else {
			alert("Please select date");
			$('#startTime').val('');
			$('#endTime').val('');
		}
	}
	$('input.copyPaste').click(function() {
		if ($(this).is(":checked")) {
		var answer = confirm('Do you want to copy and paste for next day ?');
		if (answer) {
			$(this).parents('td').prevAll('td:not(:last)').each(function(index) {
				$(this).parents('tr.dayList').next('tr.dayList').find('input.copyPaste')
				 .parents('td').prevAll('td:eq(' + index + ')').find('input.startTimeChange').val($(this).find('input.startTimeChange ').val());
				});
				 } else {
				 	console.log('cancel');
			 }
		}
	}); 
	
	$.subscribe('newPermissionsForm', function(event, data) {
		if((($("input.startTimeChange").val()).length) <= 0)
		{
			alert("Please select at least one permission");
			 event.originalEvent.options.submit=false;	
		}
	     var jsona = [];
	     var days='';
	     var startTime;
	     var endTime;
	     var startDate = $("input#startDate").val();
	     var endDate = $("input#endDate").val();
	     var comments = $("textarea#comments").val();
	     var studentAndClassId=$('select#sectionId').val();
	     var stuStr = studentAndClassId.split("_");
	    // var startDate = dateStr[2]+"-"+dateStr[0]+"-"+dateStr[1]
		$('tr.dayList').each(function () {
			   startTime = $("#startTime_"+$(this).attr("id")).val();
			   endTime = $("#endTime_"+$(this).attr("id")).val();
			   if(isNonEmpty(startTime)){
				   days = $("td#day_"+$(this).attr("id")).text();
				   //alert(day+"---"+startTime+"==="+endTime+"==="+$(this).attr("id"));
				   jsona.push( {
						"days" : days.substring(0,2),
						"startTime" : startTime,
						"endTime" : endTime
					});
			   }
		});
		var permissionData = {
				"permissionData": [ {"startDate" : startDate,"endDate":endDate,"studentId":stuStr[0],"studyClassId":stuStr[1],"custId":stuStr[3],"academicYearId":stuStr[2],"comments":comments, "dayTimes" : jsona} ]
			};
		if(isNonEmpty(permissionData) ){ 
			//alert(JSON.stringify(permissionData));
			$('#stuPermissionData').val(JSON.stringify(permissionData));	
			return true;		
		}
		else{
			alert('Something gone wrong! Unable to read the response. Please reload the screen and try or contact system administrator');
			event.originalEvent.options.submit=false;
		}
	});
	
</script>
