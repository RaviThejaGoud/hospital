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
							cssClass="form-control small"></s:textfield>
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
									<div class="col-md-6" id="studentAttBy">
										<div class="make-switch has-switch" data-id="D" data-value="M"
											style="width: 180px" data-off="warning" data-on="success"
											data-off-label="Monthly" data-on-label="Daily">
											<input type="radio" class="toggle" id="manageAttendanceBy"
												checked="checked"
												onclick="javascript:GetAttendanceType(this.value);">
											<input type="hidden" name="academicYear.manageAttendanceBy"
												value="D">
										</div>
									</div>
								</s:if>
								<s:else>
									<div class="make-switch has-switch" data-id="D" data-value="M"
										style="width: 180px" data-off="warning" data-on="success"
										data-off-label="Monthly" data-on-label="Daily">
										<s:if test='%{academicYear.manageAttendanceBy=="D"}'>
											<input type="radio" class="toggle" checked="checked"
												disabled="disabled" id="manageAttendanceBy">
											<input type="hidden" name="academicYear.manageAttendanceBy"
												value='<s:property value="academicYear.manageAttendanceBy"/>'>
										</s:if>
										<s:elseif test='%{academicYear.manageAttendanceBy=="M"}'>
											<input type="radio" class="toggle" id="manageAttendanceBy"
												disabled="disabled">
											<input type="hidden" name="academicYear.manageAttendanceBy"
												value='<s:property value="academicYear.manageAttendanceBy"/>'>
										</s:elseif>
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
									<div class="col-md-6" id="captureAttBy">
										<div class="make-switch has-switch" data-id="O" data-value="T"
											style="width: 180px" data-off="warning" data-on="success"
											data-off-label="Two Times" data-on-label="One Time">
											<input type="radio" class="toggle" id="captureAttendanceBy"
												checked="checked"
												onclick="javascript:GetAttendanceType(this.value);">
											<input type="hidden" name="academicYear.captureAttendanceBy"
												value="O">
										</div>
									</div>
								</s:if>
								<s:else>
									<div class="make-switch has-switch" data-id="O" data-value="T"
										style="width: 180px" data-off="warning" data-on="success"
										data-off-label="Two Times" data-on-label="One Time">
										<s:if test='%{academicYear.captureAttendanceBy=="O"}'>
											<input type="radio" class="toggle" checked="checked" disabled="disabled" 
												 id="captureAttendanceBy">
											<input type="hidden" name="academicYear.captureAttendanceBy"
												value='<s:property value="academicYear.captureAttendanceBy"/>'>
										</s:if>
										<s:elseif test='%{academicYear.captureAttendanceBy=="T"}'>
											<input type="radio" class="toggle" id="captureAttendanceBy" disabled="disabled" 
												>
											<input type="hidden" name="academicYear.captureAttendanceBy"
												value='<s:property value="academicYear.captureAttendanceBy"/>'>
										</s:elseif>
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
						<div class="col-md-2">
							<s:if
								test="%{(academicYear.manageStaffAttendanceBy ==null) || (academicYear.manageStaffAttendanceBy =='')}">
								<div class="grid_6" id="staffAttBy" style="float: right">
									<div class="make-switch has-switch" data-id="D" data-value="M"
										style="width: 180px" data-off="warning" data-on="success"
										data-off-label="Monthly" data-on-label="Daily">
										<input type="radio" class="toggle" id="staffAttMode"
											checked="checked"> <input type="hidden"
											name="academicYear.manageStaffAttendanceBy" value="D">
									</div>
								</div>
							</s:if>
							<s:else>
								<div class="make-switch has-switch" data-id="D" data-value="M"
									style="width: 180px" data-off="warning" data-on="success"
									data-off-label="Monthly" data-on-label="Daily">
									<s:if test='%{academicYear.manageStaffAttendanceBy=="D"}'>
										<input type="radio" class="toggle" checked="checked"
											disabled="disabled" id="staffAttMode">
										<input type="hidden"
											name="academicYear.manageStaffAttendanceBy"
											value='<s:property value="academicYear.manageStaffAttendanceBy"/>'>
									</s:if>
									<s:elseif test='%{academicYear.manageStaffAttendanceBy=="M"}'>
										<input type="radio" class="toggle" id="staffAttMode"
											disabled="disabled">
										<input type="hidden"
											name="academicYear.manageStaffAttendanceBy"
											value='<s:property value="academicYear.manageStaffAttendanceBy"/>'>
									</s:elseif>
								</div>
							</s:else>
						</div>
					</div>
				</div>
				<div >
					<div class="form-group">
							<label class="control-label col-md-3"><span
								class="required">*</span>Capture Attendance By :</label>
							<div class="col-md-3">
								<s:if
									test="%{(academicYear.captureAttendanceForStaff ==null) || (academicYear.captureAttendanceForStaff =='')}">
									<div class="col-md-6" id="captureAttBy">
										<div class="make-switch has-switch" data-id="O" data-value="T"
											style="width: 180px" data-off="warning" data-on="success"
											data-off-label="Two Times" data-on-label="One Time">
											<input type="radio" class="toggle" id="captureAttendanceForStaff"
												checked="checked"
												onclick="javascript:GetAttendanceType(this.value);">
											<input type="hidden" name="academicYear.captureAttendanceForStaff"
												value="O">
										</div>
									</div>
								</s:if>
								<s:else>
									<div class="make-switch has-switch" data-id="O" data-value="T"
										style="width: 180px" data-off="warning" data-on="success"
										data-off-label="Two Times" data-on-label="One Time">
										<s:if test='%{academicYear.captureAttendanceForStaff=="O"}'>
											<input type="radio" class="toggle" checked="checked" disabled="disabled" 
												 id="captureAttendanceForStaff">
											<input type="hidden" name="academicYear.captureAttendanceForStaff"
												value='<s:property value="academicYear.captureAttendanceForStaff"/>'>
										</s:if>
										<s:elseif test='%{academicYear.captureAttendanceForStaff=="T"}'>
											<input type="radio" class="toggle" id="captureAttendanceForStaff" disabled="disabled" 
												>
											<input type="hidden" name="academicYear.captureAttendanceForStaff"
												value='<s:property value="academicYear.captureAttendanceForStaff"/>'>
										</s:elseif>
									</div>
								</s:else>
							</div>
						</div>
				</div>
				<div id="enableStaffInfo">
					<div class="form-group">
						<label class="control-label col-md-3"><span
							class="required">*</span>Enable School shift :</label>
						<div class="col-md-3">
							<s:if
								test="%{(academicYear.enableSchoolShift ==null) || (academicYear.enableSchoolShift =='')}">
								<div class="make-switch has-switch" data-id="Y" data-value="N"
									style="width: 180px" data-off="warning" data-on="success"
									data-off-label="No" data-on-label="Yes">
									<input type="radio" class="toggle" id="enableStaffInfo"
										checked="checked"> <input type="hidden"
										name="academicYear.enableSchoolShift" value="Y">
								</div>
							</s:if>
							<s:else>
								<div class="make-switch has-switch" data-id="Y" data-value="N"
									style="width: 180px" data-off="warning" data-on="success"
									data-off-label="No" data-on-label="Yes">
									<s:if test='%{academicYear.enableSchoolShift=="Y"}'>
										<input type="radio" class="toggle" id="enableStaffInfo"
											checked="checked">
									</s:if>
									<s:else>
										<input type="radio" class="toggle" id="enableStaffInfo">
									</s:else>
									<input type="hidden" name="academicYear.enableSchoolShift"
										value="<s:property value="academicYear.enableSchoolShift"/>">
								</div>
							</s:else>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="spaceDiv"></div>
		<b>Birthday Alerts</b>
		<div class="spaceDiv"></div>
		<div style="border: 1px dotted #000">
			<div class="spaceDiv"></div>
			<div id="sendBirthday">
				<div class="form-group">
					<label class="control-label col-md-3"> <span
						class="required">*</span>Send Mobile Alerts for Student Birthdays
						:
					</label>
					<div class="col-md-3">
						<div class="make-switch has-switch" data-id="true"
							data-value="false" style="width: 180px" data-off="warning"
							data-on="success" data-off-label="No" data-on-label="Yes">
							<s:if test='%{academicYear.sendBirthdayAlerts}'>
								<input type="radio" class="toggle" id="birthdaySet"
									checked="checked" />
							</s:if>
							<s:else>
								<input type="radio" class="toggle" id="birthdaySet" />
							</s:else>
							<input type="hidden" name="academicYear.sendBirthdayAlerts"
								value="<s:property value="academicYear.sendBirthdayAlerts"/>">
						</div>
					</div>
				</div>
			</div>
			<div class="spaceDiv"></div>
			<div id="sendBirthdayEmail">
				<div class="form-group">
					<label class="control-label col-md-3"> <span
						class="required">*</span>Send Email Alerts for Student Birthdays :
					</label>
					<div class="col-md-3">
						<div class="make-switch has-switch" data-id="true"
							data-value="false" style="width: 180px" data-off="warning"
							data-on="success" data-off-label="No" data-on-label="Yes">
							<s:if test='%{academicYear.sendBirthdayAlertsByEmail}'>
								<input type="radio" class="toggle" id="emailbirthdaySet"
									checked="checked" />
							</s:if>
							<s:else>
								<input type="radio" class="toggle" id="emailbirthdaySet" />
							</s:else>
							<input type="hidden"
								name="academicYear.sendBirthdayAlertsByEmail"
								value="<s:property value="academicYear.sendBirthdayAlertsByEmail"/>">
						</div>
					</div>
				</div>
			</div>

			<div class="spaceDiv"></div>
			<div id="sendBirthday">
				<div class="form-group">
					<label class="control-label col-md-3"> <span
						class="required">*</span>Send Mobile Alerts for Staff Birthdays :
					</label>
					<div class="col-md-3">
						<div class="make-switch has-switch" data-id="true"
							data-value="false" style="width: 180px" data-off="warning"
							data-on="success" data-off-label="No" data-on-label="Yes">
							<s:if test='%{academicYear.sendStaffBirthdayAlerts}'>
								<input type="radio" class="toggle" id="staffBirthdaySet"
									checked="checked" />
							</s:if>
							<s:else>
								<input type="radio" class="toggle" id="staffBirthdaySet" />
							</s:else>
							<input type="hidden" name="academicYear.sendStaffBirthdayAlerts"
								value="<s:property value="academicYear.sendStaffBirthdayAlerts"/>">
						</div>
					</div>
				</div>
			</div>
			<div class="spaceDiv"></div>
			<div id="sendEmialBirthday">
				<div class="form-group">
					<label class="control-label col-md-3"> <span
						class="required">*</span>Send Email Alerts for Staff Birthdays :
					</label>
					<div class="col-md-3">
						<div class="make-switch has-switch" data-id="true"
							data-value="false" style="width: 180px" data-off="warning"
							data-on="success" data-off-label="No" data-on-label="Yes">
							<s:if test='%{academicYear.sendStaffBirthdayAlertsByEmail}'>
								<input type="radio" class="toggle" id="staffEmailBirthdaySet"
									checked="checked" />
							</s:if>
							<s:else>
								<input type="radio" class="toggle" id="staffEmailBirthdaySet" />
							</s:else>
							<input type="hidden"
								name="academicYear.sendStaffBirthdayAlertsByEmail"
								value="<s:property value="academicYear.sendStaffBirthdayAlertsByEmail"/>">
						</div>
					</div>
				</div>
			</div>
		</div>
		
		
		<div class="spaceDiv"></div>
		<b>Events Communication Setting</b>
		<div class="spaceDiv"></div>
		<div style="border: 1px dotted #000">
			<div class="spaceDiv"></div>
			<div id="sendBirthday">
				<div class="form-group">
					<label class="control-label col-md-3"> <span
						class="required">*</span>Send "SMS" whenever the event is created/Updated
						:
					</label>
					<div class="col-md-3">
						<div class="make-switch has-switch" data-id="true"
							data-value="false" style="width: 180px" data-off="warning"
							data-on="success" data-off-label="No" data-on-label="Yes">
							<s:if test='%{academicYear.sendEventAlertSMS}'>
								<input type="radio" class="toggle" id="sendEventAlertSMS"
									checked="checked" />
							</s:if>
							<s:else>
								<input type="radio" class="toggle" id="sendEventAlertSMS" />
							</s:else>
							<input type="hidden" name="academicYear.sendEventAlertSMS"
								value="<s:property value="academicYear.sendEventAlertSMS"/>">
						</div>
					</div>
				</div>
			</div>
			<div class="spaceDiv"></div>
			<div id="sendBirthdayEmail">
				<div class="form-group">
					<label class="control-label col-md-3"> <span
						class="required">*</span>Send "EMAIL" whenever the event is created/Updated :
					</label>
					<div class="col-md-3">
						<div class="make-switch has-switch" data-id="true"
							data-value="false" style="width: 180px" data-off="warning"
							data-on="success" data-off-label="No" data-on-label="Yes">
							<s:if test='%{academicYear.sendEventAlertsByEmail}'>
								<input type="radio" class="toggle" id="sendEventsAlertsByEmail"
									checked="checked" />
							</s:if>
							<s:else>
								<input type="radio" class="toggle" id="sendEventsAlertsByEmail" />
							</s:else>
							<input type="hidden"
								name="academicYear.sendEventAlertsByEmail"
								value="<s:property value="academicYear.sendEventAlertsByEmail"/>">
						</div>
					</div>
				</div>
			</div>
			
		</div>
		
		
		<div class="spaceDiv"></div>
	</div>
</div>

<script type="text/javascript">
$(document).ready(function() {
	$('#minAttendance').numeric();
 });
 
$('div.make-switch').find("label[for='manageAttendanceBy']").click(function(){	
	if($(this).parent().hasClass('switch-on')==true){
		GetAttendanceType("M");
		alert("M")
	}
	else{
	alert("D")
		GetAttendanceType("D");
	}
}); 
</script>