<%@ include file="/common/taglibs.jsp"%>
<div class="portlet box blue" style="margin-bottom:0px;border-width:0px 1px;">
	<div class="portlet-title">
		<div class="caption">
			<i class="fa fa-globe"></i>General Settings
		</div>
	</div>
	<div class="portlet-body form">
		<div class="form-horizontal form-bordered form-body">
			<div id="teachingList">
				<div class="form-group">
					<label class="control-label col-md-3">
						<span class="required">*</span>Academic Year :
					</label>
					<div class="col-md-3">
						<s:select list="academicYear" id="academicId"
							name="academicYear.id" listKey="id" listValue="academicYear"
							headerKey="" cssClass="required  form-control"
							headerValue="- Select Year -"></s:select>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-md-3"><span class="required">*</span>School Start Date :</label>
				<div class="col-md-3">
					<div data-date-start-date="+0d" data-date-format="mm/dd/yyyy"
						class="input-group input-medium date date-picker">
						<input type="text" id="startDate" readonly="readonly" value='<s:property value="academicYear.startDateStr"/>' disabled="disabled"
							class="form-control required" name="academicYear.startDate">
						<span class="input-group-btn">
							<button type="button" class="btn default" style="display: none">
								<i class="fa fa-calendar"></i>
							</button> </span>
					</div>
					<span class="help-block">(MM/DD/YYYY)</span>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-md-3"><span class="required">*</span>School Close Date :</label>
				<div class="col-md-3">
					<div data-date-start-date="+0d" data-date-format="mm/dd/yyyy"
								class="input-group input-medium date date-picker">
						<input type="text" id="endDate" readonly="readonly" class="form-control required" value='<s:property value="academicYear.endDateStr"/>'
							onchange="daydiff();"
							name="academicYear.endDate">
						<span class="input-group-btn">
							<button type="button" class="btn default">
								<i class="fa fa-calendar"></i>
							</button> </span>
					</div>
					<span class="help-block">(MM/DD/YYYY)</span>
				</div>
			</div>
			<div id="transportFee">
				<div id="teachingList">
					<div class="form-group">
						<label class="control-label col-md-3"><span
							class="required">*</span>Assign Transport Fee By BoardingPoint :</label>
						<div class="col-md-3">
							<s:if test="%{(academicYear.transportFeeByBoardingPoint)}">
								<div class="make-switch has-switch" data-id="true"
									data-value="false" style="width: 180px" data-off="warning"
									data-on="success" data-off-label="No" data-on-label="Yes">
									<input type="radio" class="toggle" id="transportFee"
										checked="checked"> <input type="hidden"
										name="academicYear.transportFeeByBoardingPoint" value="true">
								</div>
							</s:if>
							<s:else>
								<div class="make-switch has-switch" data-id="true"
									data-value="false" style="width: 180px" data-off="warning"
									data-on="success" data-off-label="No" data-on-label="Yes">
									<s:if test='%{academicYear.manageAttendanceBy=="true"}'>
										<input type="radio" class="toggle" id="transportFee"
											checked="checked" disabled="disabled" />
									</s:if>
									<s:else>
										<input type="radio" class="toggle" id="transportFee"
											disabled="disabled" />
									</s:else>
									<input type="hidden"
										name="academicYear.transportFeeByBoardingPoint"
										value="<s:property value="academicYear.transportFeeByBoardingPoint"/>">
								</div>
							</s:else>
						</div>
					</div>
				</div>
			</div>
			
			<div id="assignmentSMSLoginUserDivId">
				<div class="form-group">
					<label class="control-label col-md-3"> <span
						class="required">*</span>Assignment SMS for login user :
					</label>
					<div class="col-md-3">
						<div class="make-switch has-switch" data-id="true"
							data-value="false" style="width: 180px" data-off="warning"
							data-on="success" data-off-label="No" data-on-label="Yes">
							<s:if test='%{academicYear.assignmentSMSLoginUser}'>
								<input type="radio" class="toggle" id="assignmentSMSLoginUser"
									checked="checked" />
							</s:if>
							<s:else>
								<input type="radio" class="toggle" id="assignmentSMSLoginUser" />
							</s:else>
							<input type="hidden"
								name="academicYear.assignmentSMSLoginUser"
								value="<s:property value="academicYear.assignmentSMSLoginUser"/>">
						</div>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-md-3"> 
					<span class="required">*</span>Timetable Generation By Manual:
				</label>
				<div class="col-md-3">
					<div class="make-switch has-switch" data-id="true" data-value="false" style="width: 180px" data-off="warning"
						data-on="success" data-off-label="No" data-on-label="Yes">
						<s:if test='%{academicYear.timetableGenerationByManual}'>
							<input type="radio" class="toggle" id="timetableGenerationByManual" checked="checked" />
						</s:if>
						<s:else>
							<input type="radio" class="toggle" id="timetableGenerationByManual" />
						</s:else>
						<input type="hidden" name="academicYear.timetableGenerationByManual" value="<s:property value="academicYear.timetableGenerationByManual"/>">
					</div>
				</div>
			</div>
			<div id="studeadmissions" style="display: none;">
				<div id="teachingList">
						<div class="form-group">
							<label class="control-label col-md-3">
								Do you want Activity Description Field in Students Activities Template :
							</label>
							<div class="col-md-3">
								<div class="make-switch has-switch" data-id="true"
									data-value="false" style="width: 180px" data-off="warning"
									data-on="success" data-off-label="No" data-on-label="Yes">
									<input type="radio" class="toggle" id="activityDescr"
										checked="checked">
									<input type="hidden" name="academicYear.dispActivityDescField"
										value="true">
								</div>
							</div>
						</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
function verifyTime(date0, date1) {
	var startDateId = document.getElementsByName('startDate');
	startDate = startDateId.value;
	var endDateId = document.getElementsByName('endDate');
	alert(endDateId);
	endDate = endDateId.value;
	if (endDate != '') {
		if (endDate < startDate) {
			document.getElementById(endDateIdName).value = '';
			alert("Your end date is before your start date.");
		}
	}
} 
function daydiff() {
	var startDate = $('#startDate').val();
	var endDate = $('#endDate').val();
	if (isNonEmpty(startDate) && isNonEmpty(endDate)) {
		startDate = new Date(startDate);
		endDate = new Date(endDate);
		var totalDays = Number((endDate - startDate) / (1000 * 60 * 60 * 24)
				+ 1);
		if (totalDays >= 365) {
			alert("School working days should be less than 365 days");
			$('#endDate').val('');
		}
	} 
}
function downloadAcademicPlanSettings(){
	var acedmicYearId = $('#academicId').val();
	var selectedId = $('input[name="schoolTimings"]').val();
	var dayTimings = $('input[name="dayTimings"]:checked').val();
	var mainimgurl = jQuery.url.getChatURL("/admin/printAcademicPlanSettings.do?academicYearId=" + acedmicYearId +"&selectedId="+ selectedId+"&tempString="+dayTimings);
	$('.acdId').attr("href", mainimgurl);
} 
</script>