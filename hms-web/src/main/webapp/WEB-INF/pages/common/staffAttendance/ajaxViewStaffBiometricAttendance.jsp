<%@ include file="/common/taglibs.jsp"%>
<div class="tab-content">
				<span id="startDateSpan" class="<s:property value='startDate'/>"></span>
				<span id="endDateSpan" class="<s:property value='endDate'/>"></span>
				<span id="toDateSpan" class="<s:property value='toDate'/>"></span>
				<span id="academicStartDateSpan" class="<s:property value='fromDate'/>"></span>
				<span id="academicEndDateSpan" class="<s:property value='anyTitle'/>"></span>
				<div class="tab-content">
						<div class="form-body">
							<div class="row">
								<div class="col-md-6">
									<div class="form-group">
										<label class="control-label col-md-4" style="width: 25.333%">
											Attendance Date :
										</label>
										<div class="col-md-8">
											<div data-date-start-date="-2d" data-date-end-date="+0d"
												data-date-format="yyyy-mm-dd"
												class="input-group input-medium date date-picker">
												<input type="text" id="attendanceDate" readonly="readonly"
													name="attendanceDate"
													value='<s:property value="attendanceDate"/>'
													onchange="javascript:getStaffByDate();"
													class="form-control">
												<span class="input-group-btn">
													<button type="button" class="btn default">
														<i class="fa fa-calendar"></i>
													</button> </span>
											</div>
											<span class="help-block">(YYYY-MM-DD)</span>
										</div>
									</div>
								</div>
							</div>
						</div>
					<div id="staffBiometricAttendanceDiv">
						<jsp:include
							page="/WEB-INF/pages/common/staffAttendance/ajaxViewStaffBiometricAttendanceDetails.jsp"></jsp:include>
					</div>
				</div>
</div>
<script type="text/javascript">
$(document).ready(function() {
	FormComponents.init();
	changePageTitle("View Staff Attendance");
	getStaffByDate();
});
function getStaffByDate() {
	var attendanceDate = $('#attendanceDate').val();
	if(isNonEmpty(attendanceDate)){
		var pars = "attendanceDate=" + attendanceDate+"&anyId=staffBiometric";
		$("#staffBiometricAttendanceDiv").html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		$.ajax( {
			url : jQuery.url
					.getChatURL("/admin/ajaxViewStaffAttendanceForm.do"),
			cache : false,
			data : pars,
			success : function(response) {
				$('#staffBiometricAttendanceDiv').html(response);
			}
		});	
	}else{
		$('#staffBiometricAttendanceDiv').html('Please select Attendance date.');
	}
}

$('a.academicPlannerId').click(function() {
	window.location.hash = "target=ES.ajaxify AAP";
	window.location.reload();
});
</script>
