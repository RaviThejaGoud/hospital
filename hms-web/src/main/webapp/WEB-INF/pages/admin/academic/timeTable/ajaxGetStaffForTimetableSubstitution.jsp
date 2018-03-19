<%@ include file="/common/taglibs.jsp"%>

<s:hidden name="anyTitle" id="staffIdsId"></s:hidden>
<div class="tab-content">
<s:if test='%{viewStaffPersonAccountDetailsList !=null || !viewStaffPersonAccountDetailsList=empty}'>

				<div class="tab-content">
						<div class="form-body">
							<div class="row">
								<div class="col-md-6">
									<div class="form-group">
										<label class="control-label col-md-4" style="width: 25.333%">
											Select Staff :
										</label>
										<div class="col-md-8">
											<s:select list="viewStaffPersonAccountDetailsList" id="staffId"
													headerKey="" headerValue="- Select -"
													cssClass="form-control input-medium" name="actualStaffId"
													listKey="staffId" listValue="fullName" theme="simple"
													onchange="javascript:getAjaxDoGetStaffTimetable(this.value);">
												</s:select>
										</div>
									</div>
								</div>
							</div>
						</div>
					<div id="displayStaffTimetableDiv"></div>
				</div>
				</s:if>
				<s:else>
				<div class="alert alert-info">Only Absent staff on selected date will be displayed.</div>
				</s:else>
</div>
<script type="text/javascript">
$(document).ready(function() {
	FormComponents.init();
	changePageTitle("View Staff Attendance");
	//getStaffByDate();
});
function getAjaxDoGetStaffTimetable(staffId) {
	var attendanceDate = $('#attendanceDate').val();
	if(isNonEmpty(staffId)){
		var pars = "attendanceDate=" + attendanceDate+"&staffId="+staffId;
		$("#displayStaffTimetableDiv").html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		$.ajax( {
			url : jQuery.url.getChatURL("/timeTable/ajaxStaffTimetableForSubstitutionTimetable.do"),
			cache : false,
			data : pars,
			success : function(response) {
				$('#displayStaffTimetableDiv').html(response);
			}
		});	
	}else{
		$('#displayStaffTimetableDiv').html('Please select staff.');
	}
}

</script>
