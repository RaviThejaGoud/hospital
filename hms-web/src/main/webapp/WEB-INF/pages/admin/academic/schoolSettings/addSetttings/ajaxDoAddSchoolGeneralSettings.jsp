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
						<s:select list="approvedLeavesList" id="academicId" name="academicYear.id" listKey="id" listValue="academicYear"
								headerKey="" onchange="javascript:loadYearRangeValue();" cssClass="required form-control input-medium"
								headerValue="- Select Year -"></s:select>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-md-3"><span class="required">*</span>School Start Date :</label>
				<div class="col-md-3">
					<div data-date-format="mm/dd/yyyy"
						class="input-group input-medium date date-picker">
						<input type="text" id="startDate" name="academicYear.startDate"
							readonly="readonly" value='<s:property value="academicYear.startDateStr"/>' onchange="verifyTime();"
							class="form-control required">
						<span class="input-group-btn">
							<button type="button" class="btn default">
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
						<input type="text" id="endDate" name="academicYear.endDate" value='<s:property value="academicYear.endDateStr"/>'
							readonly="readonly" onchange="verifyTime();"
							class="form-control required">
						<span class="input-group-btn">
							<button type="button" class="btn default">
								<i class="fa fa-calendar"></i>
							</button> </span>
					</div>
					<span class="help-block">(MM/DD/YYYY)</span>
				</div>
			</div>
			 
			<%-- <div id="sendBirthday">
				<div class="form-group">
					<label class="control-label col-md-3">
						<span class="required">*</span>Send Mobile Alerts for Student
						Birthdays :
					</label>
					<div class="col-md-3">
						<s:if test="%{(academicYear.sendBirthdayAlerts ==null) || (academicYear.sendBirthdayAlerts =='')}">
							<div class="make-switch has-switch" data-id="true"
								data-value="false" style="width: 180px" data-off="warning"
								data-on="success" data-off-label="No" data-on-label="Yes">
								<input type="radio" class="toggle" id="sendBirthday"
									checked="checked">
								<input type="hidden" name="academicYear.sendBirthdayAlerts"
									value="true">
							</div>
						</s:if>
						<s:else>
							<div class="make-switch has-switch" data-id="true"
								data-value="false" style="width: 180px" data-off="warning"
								data-on="success" data-off-label="No" data-on-label="Yes">
								<s:if test='%{academicYear.sendBirthdayAlerts=="true"}'>
									<input type="radio" class="toggle" id="transportFee"
										checked="checked" disabled="disabled" />
								</s:if>
								<s:else>
									<input type="radio" class="toggle" id="transportFee"
										disabled="disabled" />
								</s:else>
								<input type="hidden" name="academicYear.sendBirthdayAlerts"
									value="<s:property value="academicYear.sendBirthdayAlerts"/>">
							</div>
						</s:else>
					</div>
				</div>
			</div> --%>
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
	var startDate = $('#startDate').val();
	var endDate = $('#endDate').val();
	if (isNonEmpty(endDate) && isNonEmpty(startDate)) {
		startDate = new Date(startDate);
		endDate = new Date(endDate);
		if (endDate <= startDate) {
			$('#endDate').val('');
			alert("End date should be after start date.");
		}
		var totalDays = Number((endDate - startDate) / (1000 * 60 * 60 * 24) + 1);
		if (totalDays >= 365) {
			alert("School working days should be less than 365 days");
			$('#endDate').val('');
		}
		endDate = new Date(endDate);
		endDate.setDate(endDate.getDate() + 1);
	}
}
</script>