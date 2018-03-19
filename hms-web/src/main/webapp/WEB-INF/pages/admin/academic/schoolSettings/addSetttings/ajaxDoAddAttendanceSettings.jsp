<%@ include file="/common/taglibs.jsp"%>
<div class="portlet box blue"
	style="margin-bottom: 0px; border-width: 0px 1px;">
	<div class="portlet-title">
		<div class="caption">
			<i class="fa fa-globe"></i>Attendance Settings 
		</div>
	</div>
	<div class="portlet-body">
		<div class="form-horizontal form-bordered form-body">
			<b>Student</b>
			<div class="spaceDiv"></div>
			<div style="border: 1px dotted #000">
				<div class="form-group">
					<label class="control-label col-md-3">Minimum Attendance
						for Promote Student (%) :</label>
					<div class="col-md-3">
						<s:textfield id="minAttendance" maxlength="3"
							name="academicYear.attendancePercentage"
							cssClass="textfield form-control input-small"></s:textfield>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-md-3"><span
						class="required">*</span>Attendance By BioMetric :</label>
					<div class="col-md-3">
						<s:if
							test="%{(academicYear.useBiometricForStudent ==null) || (academicYear.useBiometricForStudent =='')}">
							<div class="make-switch has-switch" data-id="Y" data-value="N"
								style="width: 180px" data-off="warning" data-on="success"
								data-off-label="No" data-on-label="Yes">
								<input type="radio" class="toggle" id="studentBio"> <input
									type="hidden" name="academicYear.useBiometricForStudent"
									value="N">
							</div>
						</s:if>
						<s:else>
							<div class="make-switch has-switch" data-id="Y" data-value="N"
								style="width: 180px" data-off="warning" data-on="success"
								data-off-label="No" data-on-label="Yes">
								<s:if test='%{academicYear.useBiometricForStudent=="Y"}'>
									<input type="radio" class="toggle" id="studentBio"
										disabled="disabled" checked="checked">
								</s:if>
								<s:else>
									<input type="radio" class="toggle" id="studentBio"
										disabled="disabled">
								</s:else>
								<input type="hidden" name="academicYear.useBiometricForStudent"
									value="<s:property value="academicYear.useBiometricForStudent"/>">
							</div>
						</s:else>
					</div>
				</div>
				<div id="studentAttBy">
					<div id="teachingList">
						<div class="form-group">
							<label class="control-label col-md-3"><span
								class="required">*</span>Attendance By :</label>
							<div class="col-md-3">
								<s:if
									test="%{(academicYear.manageAttendanceBy ==null) || (academicYear.manageAttendanceBy =='')}">
									<div class="col-md-6">
										<div class="make-switch has-switch" data-id="D" data-value="M"
											style="width: 180px" data-off="warning" data-on="success"
											data-off-label="Monthly" data-on-label="Daily">
											<input type="radio" class="toggle" id="manageAttendanceBy"
												checked="checked"> <input type="hidden"
												name="academicYear.manageAttendanceBy" value="D">
										</div>
									</div>
								</s:if>
								<s:else>
									<div class="make-switch has-switch" data-id="D" data-value="M"
										style="width: 180px" data-off="warning" data-on="success"
										data-off-label="Monthly" data-on-label="Daily">
										<s:if test='%{academicYear.manageAttendanceBy=="D"}'>
											<input type="radio" class="toggle" id="manageAttendanceBy"
												disabled="disabled" checked="checked">
										</s:if>
										<s:else>
											<input type="radio" class="toggle" id="manageAttendanceBy"
												disabled="disabled">
										</s:else>
										<input type="hidden" name="academicYear.manageAttendanceBy"
											value="<s:property value="academicYear.manageAttendanceBy"/>">
									</div>
								</s:else>
							</div>
						</div>
					</div>
				</div>
				<div >
					<div>
						<div class="form-group">
							<label class="control-label col-md-3"><span
								class="required">*</span>Capture Attendance By :</label>
							<div class="col-md-3">
								<s:if
									test="%{(academicYear.captureAttendanceBy ==null) || (academicYear.captureAttendanceBy =='')}">
									<div class="col-md-6">
										<div class="make-switch has-switch" data-id="O" data-value="T"
											style="width: 180px" data-off="warning" data-on="success"
											data-off-label="Two Times" data-on-label="One Time">
											<input type="radio" class="toggle" id="captureAttendanceBy"
												checked="checked"> <input type="hidden"
												name="academicYear.captureAttendanceBy" value="O">
										</div>
									</div>
								</s:if>
								<s:else>
									<div class="make-switch has-switch" data-id="O" data-value="T"
										style="width: 180px" data-off="warning" data-on="success"
										data-off-label="Two Times" data-on-label="One Time">
										<s:if test='%{academicYear.captureAttendanceBy=="O"}'>
											<input type="radio" class="toggle" id="captureAttendanceBy"
												 checked="checked">
										</s:if>
										<s:else>
											<input type="radio" class="toggle" id="captureAttendanceBy">
										</s:else>
										<input type="hidden" name="academicYear.captureAttendanceBy"
											value="<s:property value="academicYear.captureAttendanceBy"/>">
									</div>
								</s:else>
							</div>
						</div>
					</div>
				</div>
				<div id="studeadmissions">
					<div id="teachingList">
						<div class="form-group">
							<label class="control-label col-md-3"><span
								class="required">*</span>Admissions By Fee :</label>
							<div class="col-md-3">
								<s:if
									test="%{(academicYear.manageStudentsAdmissionsByFee ==null) || (academicYear.manageStudentsAdmissionsByFee =='')}">
									<div class="make-switch has-switch" data-id="Y" data-value="N"
										style="width: 180px" data-off="warning" data-on="success"
										data-off-label="No" data-on-label="Yes">
										<input type="radio" class="toggle" id="studeadmissions"
											checked="checked"> <input type="hidden"
											name="academicYear.manageStudentsAdmissionsByFee" value="Y">
									</div>
								</s:if>
								<s:else>
									<div class="make-switch has-switch" data-id="Y" data-value="N"
										style="width: 180px" data-off="warning" data-on="success"
										data-off-label="No" data-on-label="Yes">
										<s:if
											test='%{academicYear.manageStudentsAdmissionsByFee=="Y"}'>
											<input type="radio" class="toggle" id="studeadmissions"
												checked="checked">
										</s:if>
										<s:else>
											<input type="radio" class="toggle" id="studeadmissions">
										</s:else>
										<input type="hidden"
											name="academicYear.manageStudentsAdmissionsByFee"
											value="<s:property value="academicYear.manageStudentsAdmissionsByFee"/>">
									</div>
								</s:else>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="spaceDiv"></div>
			<b>Staff</b>
			<div class="spaceDiv"></div>
			<div style="border: 1px dotted #000">
				<div id="staffBiometric">
					<div class="form-group">
						<label class="control-label col-md-3"><span
							class="required">*</span>Attendance By BioMetric :</label>
						<div class="col-md-3">
							<s:if
								test="%{(academicYear.useBiometricForStaff ==null) || (academicYear.useBiometricForStaff =='')}">
								<div class="make-switch has-switch" data-id="Y" data-value="N"
									style="width: 180px" data-off="warning" data-on="success"
									data-off-label="No" data-on-label="Yes">
									<input type="radio" class="toggle" id="staffBio"> <input
										type="hidden" name="academicYear.useBiometricForStaff"
										value="N">
								</div>
							</s:if>
							<s:else>
								<div class="make-switch has-switch" data-id="Y" data-value="N"
									style="width: 180px" data-off="warning" data-on="success"
									data-off-label="No" data-on-label="Yes">
									<s:if test='%{academicYear.useBiometricForStaff=="Y"}'>
										<input type="radio" class="toggle" id="staffBio"
											disabled="disabled" checked="checked">
									</s:if>
									<s:else>
										<input type="radio" class="toggle" id="staffBio"
											disabled="disabled">
									</s:else>
									<input type="hidden" name="academicYear.useBiometricForStaff"
										value="<s:property value="academicYear.useBiometricForStaff"/>">
								</div>
							</s:else>
						</div>
					</div>
				</div>
				<div id="staffAttBy">
					<div class="form-group">
						<label class="control-label col-md-3"><span
							class="required">*</span>Attendance By :</label>
						<div class="col-md-3">
							<s:if
								test="%{(academicYear.manageStaffAttendanceBy ==null) || (academicYear.manageStaffAttendanceBy =='')}">
								<div class="make-switch has-switch" data-id="D" data-value="M"
									style="width: 180px" data-off="warning" data-on="success"
									data-off-label="Monthly" data-on-label="Daily">
									<input type="radio" class="toggle" id="staffAttMode"
										checked="checked"> <input type="hidden"
										name="academicYear.manageStaffAttendanceBy" value="D">
								</div>
							</s:if>
							<s:else>
								<div class="make-switch has-switch" data-id="D" data-value="M"
									style="width: 180px" data-off="warning" data-on="success"
									data-off-label="Monthly" data-on-label="Daily">
									<s:if test='%{academicYear.manageAttendanceBy=="D"}'>
										<input type="radio" class="toggle" id="staffAttMode"
											disabled="disabled" checked="checked">
									</s:if>
									<s:else>
										<input type="radio" class="toggle" id="staffAttMode"
											disabled="disabled">
									</s:else>
									<input type="hidden"
										name="academicYear.manageStaffAttendanceBy"
										value="<s:property value="academicYear.manageStaffAttendanceBy"/>">
								</div>
							</s:else>
						</div>
					</div>
				</div>
				<div>
						<div class="form-group">
							<label class="control-label col-md-3"><span
								class="required">*</span>Capture Attendance By :</label>
							<div class="col-md-3">
								<s:if
									test="%{(academicYear.captureAttendanceForStaff ==null) || (academicYear.captureAttendanceForStaff =='')}">
									<div class="col-md-6">
										<div class="make-switch has-switch" data-id="O" data-value="T"
											style="width: 180px" data-off="warning" data-on="success"
											data-off-label="Two Times" data-on-label="One Time">
											<input type="radio" class="toggle" id="captureAttendanceForStaff"
												checked="checked"> <input type="hidden"
												name="academicYear.captureAttendanceforStaff" value="O">
										</div>
									</div>
								</s:if>
								<s:else>
									<div class="make-switch has-switch" data-id="O" data-value="T"
										style="width: 180px" data-off="warning" data-on="success"
										data-off-label="Two Times" data-on-label="One Time">
										<s:if test='%{academicYear.captureAttendanceForStaff=="O"}'>
											<input type="radio" class="toggle" id="captureAttendanceForStaff"
												 checked="checked">
										</s:if>
										<s:else>
											<input type="radio" class="toggle" id="captureAttendanceForStaff">
										</s:else>
										<input type="hidden" name="academicYear.captureAttendanceForStaff"
											value="<s:property value="academicYear.captureAttendanceForStaff"/>">
									</div>
								</s:else>
							</div>
						</div>
					</div>
			</div>
			<div class="spaceDiv"></div>
			<b>Birthday Alerts</b>
			<div class="spaceDiv"></div>
			<div style="border: 1px dotted #000">

				<div id="sendBirthday">
					<div class="form-group">
						<label class="control-label col-md-3"> <span
							class="required">*</span>Send Mobile Alerts for Student Birthdays
							:
						</label>
						<div class="col-md-3">
							<s:if
								test="%{(academicYear.sendBirthdayAlerts ==null) || (academicYear.sendBirthdayAlerts =='')}">
								<div class="make-switch has-switch" data-id="true"
									data-value="false" style="width: 180px" data-off="warning"
									data-on="success" data-off-label="No" data-on-label="Yes">
									<input type="radio" class="toggle" id="sendBirthday"
										checked="checked"> <input type="hidden"
										name="academicYear.sendBirthdayAlerts" value="true">
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
				</div>
				<div id="sendBirthdayEmail">
					<div class="form-group">
						<label class="control-label col-md-3"> <span
							class="required">*</span>Send Email Alerts for Student Birthdays
							:
						</label>
						<div class="col-md-3">
							<s:if
								test="%{(academicYear.sendBirthdayAlertsByEmail ==null) || (academicYear.sendBirthdayAlertsByEmail =='')}">
								<div class="make-switch has-switch" data-id="true"
									data-value="false" style="width: 180px" data-off="warning"
									data-on="success" data-off-label="No" data-on-label="Yes">
									<input type="radio" class="toggle" id="sendBirthdayEmail"
										checked="checked"> <input type="hidden"
										name="academicYear.sendBirthdayAlertsByEmail" value="true">
								</div>
							</s:if>
							<s:else>
								<div class="make-switch has-switch" data-id="true"
									data-value="false" style="width: 180px" data-off="warning"
									data-on="success" data-off-label="No" data-on-label="Yes">
									<s:if test='%{academicYear.sendBirthdayAlertsByEmail=="true"}'>
										<input type="radio" class="toggle" id="sendBirthdayEmail"
											checked="checked" />
									</s:if>
									<s:else>
										<input type="radio" class="toggle" id="sendBirthdayEmail"
											disabled="disabled" />
									</s:else>
									<input type="hidden"
										name="academicYear.sendBirthdayAlertsByEmail"
										value="<s:property value="academicYear.sendBirthdayAlertsByEmail"/>">
								</div>
							</s:else>
						</div>
					</div>
				</div>

				<div class="spaceDiv"></div>
				<div id="sendStaffBirthday">
					<div class="form-group">
						<label class="control-label col-md-3"> <span
							class="required">*</span>Send Mobile Alerts for Staff Birthdays :
						</label>
						<div class="col-md-3">
							<s:if
								test="%{(academicYear.sendStaffBirthdayAlerts ==null) || (academicYear.sendStaffBirthdayAlerts =='')}">
								<div class="make-switch has-switch" data-id="true"
									data-value="false" style="width: 180px" data-off="warning"
									data-on="success" data-off-label="No" data-on-label="Yes">
									<input type="radio" class="toggle" id="sendStaffBirthday"
										checked="checked"> <input type="hidden"
										name="academicYear.sendStaffBirthdayAlerts" value="true">
								</div>
							</s:if>
							<s:else>
								<div class="make-switch has-switch" data-id="true"
									data-value="false" style="width: 180px" data-off="warning"
									data-on="success" data-off-label="No" data-on-label="Yes">
									<s:if test='%{academicYear.sendStaffBirthdayAlerts=="true"}'>
										<input type="radio" class="toggle" id="sendStaffBirthday"
											checked="checked" disabled="disabled" />
									</s:if>
									<s:else>
										<input type="radio" class="toggle" id="sendStaffBirthday"
											disabled="disabled" />
									</s:else>
									<input type="hidden"
										name="academicYear.sendStaffBirthdayAlerts"
										value="<s:property value="academicYear.sendStaffBirthdayAlerts"/>">
								</div>
							</s:else>
						</div>
					</div>
				</div>
				<div class="spaceDiv"></div>
				<div id="sendStaffBirthday">
					<div class="form-group">
						<label class="control-label col-md-3"> <span
							class="required">*</span>Send Email Alerts for Staff Birthdays :
						</label>
						<div class="col-md-3">
							<s:if
								test="%{(academicYear.sendStaffBirthdayAlertsByEmail ==null) || (academicYear.sendStaffBirthdayAlertsByEmail =='')}">
								<div class="make-switch has-switch" data-id="true"
									data-value="false" style="width: 180px" data-off="warning"
									data-on="success" data-off-label="No" data-on-label="Yes">
									<input type="radio" class="toggle" id="sendStaffBirthdayEmail"
										checked="checked"> <input type="hidden"
										name="academicYear.sendStaffBirthdayAlertsByEmail"
										value="true">
								</div>
							</s:if>
							<s:else>
								<div class="make-switch has-switch" data-id="true"
									data-value="false" style="width: 180px" data-off="warning"
									data-on="success" data-off-label="No" data-on-label="Yes">
									<s:if
										test='%{academicYear.sendStaffBirthdayAlertsByEmail=="true"}'>
										<input type="radio" class="toggle" id="sendStaffBirthdayEmail"
											checked="checked" disabled="disabled" />
									</s:if>
									<s:else>
										<input type="radio" class="toggle" id="sendStaffBirthdayEmail"
											disabled="disabled" />
									</s:else>
									<input type="hidden"
										name="academicYear.sendStaffBirthdayAlertsByEmail"
										value="<s:property value="academicYear.sendStaffBirthdayAlertsByEmail"/>">
								</div>
							</s:else>
						</div>
					</div>
				</div>
			</div>
			<div class="spaceDiv"></div>
			<div class="form-group">
				<label class="control-label col-md-3"><span class="required">*</span>Assign
					Transport Fee By BoardingPoint :</label>
				<div class="col-md-3">
					<s:if
						test="%{(academicYear.transportFeeByBoardingPoint ==null) || (academicYear.transportFeeByBoardingPoint =='')}">
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
			<div class="spaceDiv"></div>
			<div id="staffAttBy">
				<div class="form-group">
					<label class="control-label col-md-3"><span
						class="required">*</span>Generate fee receipt number by :</label>
					<div class="col-md-3">
						<s:if
							test="%{(academicYear.receiptGenerationType ==null) || (academicYear.receiptGenerationType =='')}">
							<div class="make-switch has-switch" data-id="A" data-value="M"
								style="width: 180px" data-off="warning" data-on="success"
								data-off-label="Manual" data-on-label="Automation">
								<input type="radio" class="toggle" id="feeReceiptMode"
									checked="checked"> <input type="hidden"
									name="academicYear.receiptGenerationType" value="A">
							</div>
						</s:if>
						<s:else>
							<div class="make-switch has-switch" data-id="A" data-value="M"
								style="width: 180px" data-off="warning" data-on="success"
								data-off-label="Manual" data-on-label="Automation">
								<s:if test='%{academicYear.receiptGenerationType=="M"}'>
									<input type="radio" class="toggle" id="feeReceiptMode"
										disabled="disabled" checked="checked">
								</s:if>
								<s:else>
									<input type="radio" class="toggle" id="feeReceiptMode"
										disabled="disabled">
								</s:else>
								<input type="hidden" name="academicYear.receiptGenerationType"
									value="<s:property value="academicYear.receiptGenerationType"/>">
							</div>
						</s:else>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function() {
	$('#minAttendance').numeric();
 });
 
$('div.make-switch').find("label[for='studentBio']").click(function(){
	  if($(this).parent().hasClass('switch-on')==true){
	   $('div#studentAttBy').show();
	  }
	  else{
	     $('div#studentAttBy').hide();
	  }
});
	 
$('div.make-switch').find("label[for='staffBio']").click(function(){
	  if($(this).parent().hasClass('switch-on')==true){
	    $('div#staffAttBy').show();
	  }
	  else{
	     $('div#staffAttBy').hide();
	  }
});
	 
 
</script>