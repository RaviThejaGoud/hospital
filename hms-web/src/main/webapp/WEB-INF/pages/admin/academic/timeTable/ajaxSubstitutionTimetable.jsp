<%@ include file="/common/taglibs.jsp"%>

<div class="tab-content">
<%@ include file="/common/messages.jsp"%>
	<s:form id="substitutionTimetableFormId" action="ajaxAddSubstitutionTimetable" method="post" theme="simple" cssClass="form-horizontal" namespace="/timeTable">
	<p>
		<span class="label label-danger"> NOTE : </span>&nbsp;Only Absent staff on selected date will be displayed.
	</p>
			<div class="form-body">
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4" style="width: 25.333%">
								Select Date :
							</label>
							<div class="col-md-8">
								<div data-date-start-date="-2d" data-date-format="yyyy-mm-dd"
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
		<div id="staffsListDiv"></div>
		
		</s:form>
</div>


<script type="text/javascript">
$(document).ready(function() {
	FormComponents.init();
	changePageTitle("Substitution Timetable");
	getStaffByDate();
});
function getStaffByDate() {
	var attendanceDate = $('#attendanceDate').val();
	if(isNonEmpty(attendanceDate)){
		var pars = "attendanceDate=" + attendanceDate;
		$("#staffsListDiv").html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		$.ajax( {
			url : jQuery.url.getChatURL("/timeTable/ajaxGetStaffForTimetableSubstitution.do"),
			cache : false,
			data : pars,
			success : function(response) {
				$('#staffsListDiv').html(response);
			}
		});	
	}else{
		$('#staffsListDiv').html('Please select date.');
	}
}

</script>
