<%@ include file="/common/taglibs.jsp"%>
<s:if test='%{tempString == "driver"}'>

	<s:if test='%{tempString3 == "addRoute"}'>
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true"></button>
			<h4 class="modal-title">
				DRIVER INFORMATION
			</h4>
		</div>
	</s:if>
	<s:else>
		<h4 class="pageTitle bold form-section">
			DRIVER INFORMATION
		</h4>
	</s:else>
	
	<s:form id="ajaxAddVehicleDriver" action="ajaxAddDriverOrHelper"
		method="post" theme="simple" cssClass="form-horizontal" namespace="/admin">
		<s:hidden name="anyTitle" value="ROLE_DRIVER" />
		<s:hidden name="tempString3" />
		
		<s:hidden name="route.routePointEndTime" />
		<s:hidden name="route.routePointStartTime" />
		<s:hidden name="tempId2" />
		<s:hidden name="anyId" />
		
		<div class="form-body">
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4">
							<span class="required">*</span>First Name :
						</label>
						<div class="col-md-5">
							<sj:textfield name="person.firstName" id="firstName"
								label="First Name" cssClass="required form-control input-medium"
								maxlength="40"></sj:textfield>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4">
							Last Name :
						</label>
						<div class="col-md-5">
							<sj:textfield name="person.lastName" id="lastName"
								cssClass="form-control input-medium" maxlength="40"></sj:textfield>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4">
							Blood Group :
						</label>
						<div class="col-md-5">
							<s:select id="bloodGroup"
								list="#{'A+':'A+ve','A-':'A-ve','A1+':'A1+ve','A1-':'A1-ve','A1B+':'A1B+ve','A1B-':'A1B-ve','A2B+':'A2B+ve','A2B-':'A2B-ve','A2+':'A2+ve','A2-':'A2-ve','B+':'B+ve','B-':'B-ve','AB+':'AB+ve','AB-':'AB-ve','O+':'O+ve','O-':'O-ve'}"
								headerKey="" headerValue="- Select -" name="person.bloodGroup"
								cssClass="form-control input-medium" />
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4">
							Email Id :
						</label>
						<div class="col-md-5">
							<div class="input-group">
								<span class="input-group-addon"><i class="fa fa-envelope"></i>
								</span>
								<sj:textfield name="address.email" id="parentEmail"
									cssClass="form-control email" cssStyle="width:201px;" maxlength="40"></sj:textfield>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4">
							Date Of Birth :
						</label>
						<div class="col-md-5">
							<div data-date-format="mm/dd/yyyy" data-date-end-date="+0d"
								class="input-group input-medium date date-picker">
								<input type="text" id="date0" name="person.dateOfBirth"
									readonly="readonly" value=''
									onchange="javascript:driverVerifyDate();" class="form-control">
								<span class="input-group-btn">
									<button type="button" class="btn default">
										<i class="fa fa-calendar"></i>
									</button> </span>
							</div>
							<span class="help-block">(MM/DD/YYYY)</span>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4">
							Date Of Joining :
						</label>
						<div class="col-md-5">
							<div data-date-format="mm/dd/yyyy"
								class="input-group input-medium date date-picker">
								<input type="text" id="date1" name="person.dateOfJoining"
									readonly="readonly"
									value='<s:property value="attendanceDate"/>'
									onchange="javascript:driverVerifyDate();" class="form-control">
								<span class="input-group-btn">
									<button type="button" class="btn default">
										<i class="fa fa-calendar"></i>
									</button> </span>
							</div>
							<span class="help-block">(MM/DD/YYYY)</span>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4">
							<span class="required">*</span>Gender :
						</label>
						<div class="col-md-7">
							<div class="make-switch has-switch" data-id="M" data-value="F"
								style="width: 180px" data-off="warning" data-on="success"
								data-off-label="Female" data-on-label="Male">
								<input type="radio" class="toggle" checked="checked" id="gender">
								<input type="hidden" name="person.gender" value="M">
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4">
							<span class="required">*</span>Marital Status :
						</label>
						<div class="col-md-7">
							<div class="make-switch has-switch" data-id="M" data-value="UN"
								style="width: 180px" data-off="warning" data-on="success"
								data-off-label="Un-married" data-on-label="Married">
								<input type="radio" class="toggle" id="maritalStatus">
								<input type="hidden" name="person.maritalStatus" value="UN">
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4">
							<span class="required">*</span>License Number :
						</label>
						<div class="col-md-5">
							<sj:textfield name="person.licenseNumber" id="licenseNumber"
								cssClass="required form-control input-medium" maxlength="20"></sj:textfield>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4">
							<span class="required">*</span>License Expiry Date :
						</label>
						<div class="col-md-5">
							<div data-date-format="mm/dd/yyyy" data-date-start-date="+0d"
								class="input-group input-medium date date-picker">
								<input type="text" id="date2" name="person.licenseExpDate"
									readonly="readonly"
									value='<s:property value="person.licenseExpDate"/>'
									onchange="javascript:driverVerifyDate();" class="required form-control">
								<span class="dateInput input-group-btn">
									<button type="button" class="btn default">
										<i class="fa fa-calendar"></i>
									</button> </span>
							</div>
							<span class="help-block">(MM/DD/YYYY)</span>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4">
							Experience(in Years) :
						</label>
						<div class="col-md-5">
							<sj:textfield name="person.experience" id="experience"
								cssClass="numericDot form-control input-medium" maxlength="4"></sj:textfield>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4">
							Phone Number :
						</label>
						<div class="col-md-5">
							<sj:textfield name="person.phoneNumber" id="phoneNumber"
								cssClass="numeric form-control input-medium " maxlength="10"></sj:textfield>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4">
							<span class="required">*</span>Mobile Number :
						</label>
						<div class="col-md-5">
							<sj:textfield name="person.mobileNumber" id="mobileNumber"
								cssClass="required form-control input-medium numeric "
								maxlength="10" onblur="return validateMobNumber(this.value)"></sj:textfield>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4">
							City :
						</label>
						<div class="col-md-5">
							<sj:textfield name="address.city" id="city"
								cssClass="form-control input-medium" maxlength="40"></sj:textfield>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4">
							Street :
						</label>
						<div class="col-md-5">
							<sj:textfield name="address.addressLine1" id="streetName1"
								cssClass="form-control input-medium" maxlength="40"></sj:textfield>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4">
							Pincode :
						</label>
						<div class="col-md-5">
							<sj:textfield name="address.postalCode" id="pincode"
								cssClass="numeric form-control input-medium " maxlength="6"></sj:textfield>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4">
							State :
						</label>
						<div class="col-md-5">
							<s:select id="state" list="statesList"
								cssClass="form-control input-medium" listKey="stateCode"
								listValue="stateName" headerKey="" headerValue="- Select -"
								name="address.state" />
						</div>
					</div>
				</div>
			</div>
			<div class="spaceDiv">
			</div>
			<div class="form-actions fluid">
				<div class="col-md-6">
					<div class="col-md-offset-4 col-md-9">
						<sj:submit   targets="transportDriverOrHelper"
							cssClass="submitBt btn blue" value="Submit" onCompleteTopics="doClosePopup"
							onBeforeTopics="ajaxDriverFormValidation"
							formIds="ajaxAddVehicleDriver" validate="true" />
							
							<s:if test='%{tempString2 == "vehicle"}'>
									<s:url id="doManageDriverHelper"
										action="ajaxManageTransportVehicles" namespace="/admin" />
									<sj:a href="%{doManageDriverHelper}" targets="mainContentDiv"
										cssClass="btn default">
											Cancel</sj:a>
								</s:if>
								<s:else>
									<s:url id="doManageDriverHelper"
										action="ajaxAddDriverOrHelperStaff" namespace="/admin" />
									<sj:a href="%{doManageDriverHelper}" targets="mainContentDiv"
										cssClass="btn default">
											Cancel</sj:a>
								</s:else>
						
					</div>
				</div>
			</div>
		</div>
	</s:form>
</s:if>
<s:elseif test='%{tempString == "helper"}'>
	<h4 class="pageTitle bold form-section">
		HELPER INFORMATION
	</h4>
	<div id="helperForm">
		<s:form id="addVehicleHelper" action="ajaxAddDriverOrHelper"
			method="post" theme="simple" cssClass="form-horizontal" namespace="/admin">
			<s:hidden name="anyTitle" value="ROLE_HELPER" />

			<div class="form-body">
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4">
								<span class="required">*</span>First Name :
							</label>
							<div class="col-md-5">
								<sj:textfield name="person.firstName" id="driverfName"
									cssClass="required form-control input-medium" maxlength="40"></sj:textfield>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4">
								Last Name :
							</label>
							<div class="col-md-5">
								<sj:textfield name="person.lastName" id="driverlName"
									cssClass="form-control input-medium" maxlength="40"></sj:textfield>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4">
								Blood Group :
							</label>
							<div class="col-md-5">
								<s:select id="bloodGroup"
									list="#{'A+':'A+ve','A-':'A-ve','A1+':'A1+ve','A1-':'A1-ve','A1B+':'A1B+ve','A1B-':'A1B-ve','A2B+':'A2B+ve','A2B-':'A2B-ve','A2+':'A2+ve','A2-':'A2-ve','B+':'B+ve','B-':'B-ve','AB+':'AB+ve','AB-':'AB-ve','O+':'O+ve','O-':'O-ve'}"
									headerKey="" headerValue="- Select -" name="person.bloodGroup"
									cssClass="form-control input-medium" />
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4">
								Email Id :
							</label>
							<div class="col-md-5">
								<div class="input-group">
									<span class="input-group-addon"><i
										class="fa fa-envelope"></i> </span>
									<sj:textfield name="address.email" id="parentEmail"
										cssClass="form-control email" cssStyle="width:201px;"
										maxlength="40"></sj:textfield>
								</div>
							</div>
						</div>
					</div>
				</div>

				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4">
								Date Of Birth :
							</label>
							<div class="col-md-5">
								<div data-date-format="mm/dd/yyyy" data-date-end-date="+0d"
									class="input-group input-medium date date-picker">
									<input type="text" id="date2" name="person.dateOfBirth"
										readonly="readonly" value=''
										onchange="javascript:driverVerifyDate();" class="form-control">
									<span class="input-group-btn">
										<button type="button" class="btn default">
											<i class="fa fa-calendar"></i>
										</button> </span>
								</div>
								<span class="help-block">(MM/DD/YYYY)</span>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4">
								Date Of Joining :
							</label>
							<div class="col-md-5">
								<div data-date-format="mm/dd/yyyy"
									class="input-group input-medium date date-picker">
									<input type="text" id="date3" name="person.dateOfJoining"
										readonly="readonly" value=''
										onchange="javascript:driverVerifyDate();" class="form-control">
									<span class="input-group-btn">
										<button type="button" class="btn default">
											<i class="fa fa-calendar"></i>
										</button> </span>
								</div>
								<span class="help-block">(MM/DD/YYYY)</span>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4">
								<span class="required">*</span>Gender :
							</label>
							<div class="col-md-7">
								<div class="make-switch has-switch" data-id="M" data-value="F"
									style="width: 180px" data-off="warning" data-on="success"
									data-off-label="Female" data-on-label="Male">
									<input type="radio" class="toggle" checked="checked"
										id="gender">
									<input type="hidden" name="person.gender" value="M">
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4">
								<span class="required">*</span>Marital Status :
							</label>
							<div class="col-md-7">
								<div class="make-switch has-switch" data-id="M" data-value="UN"
									style="width: 180px" data-off="warning" data-on="success"
									data-off-label="Un-married" data-on-label="Married">
									<input type="radio" class="toggle" id="maritalStatus">
									<input type="hidden" name="person.maritalStatus" value="UN">
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<!--<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4">
								<span class="required">*</span>License Number :
							</label>
							<div class="col-md-5">
								<sj:textfield name="person.licenseNumber" id="licenseNumber"
									cssClass="required form-control input-medium" maxlength="20"></sj:textfield>
							</div>
						</div>
					</div>
					-->
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4">
								Experience(in Years) :
							</label>
							<div class="col-md-5">
								<sj:textfield name="person.experience" id="experience"
									cssClass="numericDot form-control input-medium" maxlength="4"></sj:textfield>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4">
								Phone Number :
							</label>
							<div class="col-md-5">
								<sj:textfield name="person.phoneNumber" id="phoneNumber"
									cssClass="numeric form-control input-medium " maxlength="10"></sj:textfield>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4">
								<span class="required">*</span>Mobile Number :
							</label>
							<div class="col-md-5">
								<sj:textfield name="person.mobileNumber" id="mobileNumber"
									cssClass="required form-control input-medium numeric "
									maxlength="10" onblur="return validateMobNumber(this.value)"></sj:textfield>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4">
								City :
							</label>
							<div class="col-md-5">
								<sj:textfield name="address.city" id="city"
									cssClass="form-control input-medium" maxlength="40"></sj:textfield>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4">
								Street :
							</label>
							<div class="col-md-5">
								<sj:textfield name="address.addressLine1" id="streetName1"
									cssClass="form-control input-medium" maxlength="40"></sj:textfield>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4">
								Pincode :
							</label>
							<div class="col-md-5">
								<sj:textfield name="address.postalCode" id="pincode"
									cssClass="numeric form-control input-medium " maxlength="6"></sj:textfield>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-md-4">
								State :
							</label>
							<div class="col-md-5">
								<s:select id="state" list="statesList"
									cssClass="form-control input-medium" listKey="stateCode"
									listValue="stateName" headerKey="" headerValue="- Select -"
									name="address.state" />
							</div>
						</div>
					</div>
				</div>
				<div class="spaceDiv">
				</div>
				<div class="form-actions fluid">
					<div class="col-md-6">
						<div class="col-md-offset-4 col-md-5">
							<sj:submit   targets="transportDriverOrHelper"
								cssClass="submitBt btn blue" value="Submit"
								onBeforeTopics="ajaxHelperFormValidation" 
								formIds="addVehicleHelper" validate="true" />
							<s:url id="doAddDriverOrHelperCancel"
								action="ajaxLoadDriverOrHelperByRoleName" includeParams="all"
								escapeAmp="false" namespace="/admin">
								<s:param name="anyTitle">ROLE_HELPER</s:param>
							</s:url>
							<sj:a href="%{doAddDriverOrHelperCancel}" cssClass="btn default"
								targets="transportDriverOrHelper" button="false"
								buttonIcon="ui-icon-plus">Cancel</sj:a>
						</div>
					</div>
				</div>
			</div>
		</s:form>
	</div>
</s:elseif>
<script language="JavaScript" type="text/javascript"
	src="${pageContext.request.contextPath}/plugins/bootstrap-switch/static/js/bootstrap-switch.min.js">
</script>
<script language="JavaScript" type="text/javascript">
changePageTitle('Create New Driver/Helper');
$(document).ready(function() {
	$('.numericDot').numeric( {allow : "."});
	FormComponents.init();
	FormAdvanced.init();
	$("input:checkbox, input:radio:not('.toggle')").uniform(); 
	$("#firstName").focus();
	$("#driverfName").focus();
	$('.numeric').numeric();
	$('.alphabet').alpha();
});

$.subscribe('doClosePopup', function(event, data) {
	$.destroyTopic('doClosePopup');
});	

function driverVerifyDate() {
	var date0 = $('#date0').val();
	var date1 = $('#date1').val();
	var date2 = $('#date2').val(); //License Expiry Date 
	if (isNonEmpty(date1) && isNonEmpty(date0)) {
		date0 = new Date(date0);
		date1 = new Date(date1);
		var birthYear = new Date(date0.setYear(date0.getFullYear() + 2));
		if (date1 < birthYear) {
			$('#date1').val('');
			alert("Date of joining should be after 2 years of birth date.");
		}
		date1 = new Date(date1);
		date1.setDate(date1.getDate() + 1);
		$('#nextYrDate').datepicker("option", 'minDate', date1);
	}
	
	if (isNonEmpty(date2) && isNonEmpty(date0)) {
		date0 = new Date(date0);
		date2 = new Date(date2);
		
		if(date0.getTime() == date2.getTime())
		{
			$('#date2').val('');
			alert("License Expiry Date should not equal of date of birth.");
		}
	}
	
}
function helperVerifyDate() {
	var date2 = $('#date2').val();
	var date3 = $('#date3').val();
	if (isNonEmpty(date3) && isNonEmpty(date2)) {
		date2 = new Date(date2);
		date3 = new Date(date3);
		var birthYear = new Date(date2.setYear(date2.getFullYear() + 2));
		if (date3 < birthYear) {
			$('#date3').val('');
			alert("Date of joining should be after 2 years of birth date.");
		}
		date3 = new Date(date3);
		date3.setDate(date3.getDate() + 1);
		$('#nextYrDate').datepicker("option", 'minDate', date3);
	}
}

$.subscribe('ajaxDriverFormValidation', function(event, data) {
	if($('#ajaxAddVehicleDriver').valid()){
		$('button.close').click();
		$('#resultsDiv3').hide();
		return true;					
	}else
		event.originalEvent.options.submit=false;
});
	
</script>
