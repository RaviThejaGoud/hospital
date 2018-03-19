<%@ include file="/common/taglibs.jsp"%> 
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>My Timetable 
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
					<div id="classContentDiv" class="tab-content">
						<s:hidden name="staff.id" id="staffId"></s:hidden>
						<jsp:include page="/WEB-INF/pages/admin/academic/timeTable/ajaxViewStaffWiseTimetable.jsp"></jsp:include>
					</div>
					
				</div>
			</div>
			
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
					<div id="classContentDiv" class="tab-content">
					<h4 class="bold pageTitle">
						My Substitutions
							<h5 class="bold pageTitle"></h5>
							<hr />
					</h4>
	
						<div class="form-body">
							<div class="row">
								<div class="col-md-6">
									<div class="form-group">
										<label class="control-label col-md-4" style="width: 25.333%">
											Select Date :
										</label>
										<div class="col-md-8">
											<div data-date-start-date="-2d"
												data-date-format="yyyy-mm-dd"
												class="input-group input-medium date date-picker">
												<input type="text" id="attendanceDate" readonly="readonly"
													name="attendanceDate"
													value='<s:property value="attendanceDate"/>'
													onchange="javascript:getSubstitutionListByDate();"
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
						<div id="viewSubstitutionTimetableDivId"></div>
					</div>
					
				</div>
			</div>	
						
		</div>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function() {
	FormComponents.init();
	changePageTitle("My Timetable");
	getSubstitutionListByDate();
	});
	
function getSubstitutionListByDate() {
	var attendanceDate = $('#attendanceDate').val();
	var staffId = $('#staffId').val();
	if(isNonEmpty(attendanceDate) && isNonEmpty(staffId)){
		var pars = "attendanceDate=" + attendanceDate + "&staffId="+staffId;
		$("#viewSubstitutionTimetableDivId").html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		$.ajax( {
			url : jQuery.url.getChatURL("/timeTable/ajaxViewSubstitutionStaffTimetable.do"),
			cache : false,
			data : pars,
			success : function(response) {
				$('#viewSubstitutionTimetableDivId').html(response);
			}
		});	
	}else{
		$('#viewSubstitutionTimetableDivId').html('Please select date.');
	}
}
</script>
