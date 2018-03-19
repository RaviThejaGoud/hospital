<%@ include file="/common/taglibs.jsp"%>
<div data-width="760" class="modal fade modal-overflow in"
	style="display: block; width: 1000px; margin-left: -505px; margin-top: 100px;"
	aria-hidden="false">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
		<h4 class="modal-title">
			<s:if test='%{newUser.isDriver == "Y"}'>
		Edit Driver
		</s:if>
			<s:else>
		Edit Helper
		</s:else>
		</h4>
	</div>
	<div class="modal-body">
		<div class="form-body">
			<s:form id="editDriverOrHelper" action="ajaxEditDriverOrHelper"
				method="post" theme="simple" cssClass="form-horizontal" namespace="/admin">
				<s:hidden name="staff.id" />
				<span class="boardingPointsCount" id="<s:property value='tempId1'/>"></span>
				<div class="form-body">
					<div class="row">
						<div class="col-md-6">
							<div class="form-group">
								<label class="control-label col-md-4">
									<span class="required">*</span>First Name :
								</label>
								<div class="col-md-5">
									<sj:textfield name="person.firstName" id="firstName"
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
										cssClass="form-control input-medium" headerKey=""
										headerValue="- Select -" name="person.bloodGroup" />
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
											cssStyle="width:201px;" cssClass="form-control email"
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
									<div data-date-format="mm/dd/yyyy"
										class="input-group input-medium date date-picker">
										<input type="text" id="date0" name="person.dateOfBirth" value='<s:property value="person.dateOfBirthStr"/>'
											readonly="readonly" 
											onchange="javascript:driverVerifyDate();"
											class="form-control ">
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
											readonly="readonly" onchange="javascript:driverVerifyDate();" value='<s:property value="person.dateOfJoiningStr"/>' 
											class="form-control">
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
										style="width: 120px" data-off="warning" data-on="success"
										data-off-label="Female" data-on-label="Male">
										<s:if test='%{person.gender =="M"}'>
											<input type="radio" class="toggle" checked="checked"
												id="gender" >
										</s:if>
										<s:else>
											<input type="radio" class="toggle" id="gender">
										</s:else>
										<input type="hidden" name="person.gender"
											value='<s:property value="person.gender"/>' >
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label class="control-label col-md-4">
									<span class="required">*</span>Marital Status :
								</label>
								<div class="col-md-5">
									<div class="make-switch has-switch" data-id="M" data-value="UN"
										style="width: 180px" data-off="warning" data-on="success"
										data-off-label="Un-Married" data-on-label="Married">
										<s:if
											test='%{person.maritalStatus =="M"}'>
											<input type="radio" class="toggle" checked="checked"
												id="maritalStatus" >
										</s:if>
										<s:else>
											<input type="radio" class="toggle" id="maritalStatus">
										</s:else>
										<input type="hidden" name="person.maritalStatus"
											value='<s:property value="person.maritalStatus"/>'
											>
									</div>
								</div>
							</div>
						</div>
					</div>
					<!--<div class="row">
						<div class="col-md-6">
							<div class="form-group">
								<label class="control-label col-md-4">
									<span class="required">*</span>Gender:
								</label>
								<div class="col-md-6">
									<s:radio list="#{'M':'Male','F':'Female'}" name="person.gender"
										id="gender" cssClass="required" />
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group ">
								<label class="control-label col-md-4">
									<span class="required">*</span> Marital Status :
								</label>
								<div class="col-md-5">
									<s:radio list="#{'M':'Married','UN':'Un-married'}"
										name="person.maritalStatus" id="maritalStatus" tabindex="24"
										required="true" />
								</div>
							</div>
						</div>
					</div>
					--><div class="row">
						<s:if test='%{newUser.isDriver == "Y"}'>
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
											<input type="text" id="date1" name="person.licenseExpDate"
												readonly="readonly" value='<s:property value="person.licenseExpDate"/>'
												onchange="javascript:driverVerifyDate();"
												class="required form-control">
											<span class="dateInput input-group-btn">
												<button type="button" class="btn default">
													<i class="fa fa-calendar"></i>
												</button> </span>
										</div>
										<span class="help-block">(MM/DD/YYYY)</span>
									</div>
								</div>
							</div>
						</s:if>
					</div>
					<div class="row">
						<div class="col-md-6">
							<div class="form-group">
								<s:if test='%{newUser.isDriver == "Y"}'>
									<label class="control-label col-md-4">
										Experience(in Years) :
									</label>
									<div class="col-md-5">
										<sj:textfield name="person.experience" id="experience"
											cssClass="numericDot form-control input-medium" maxlength="4"></sj:textfield>
									</div>
								</s:if>
								<s:else>
									<label class="control-label col-md-4">
										Experience(in Monthly) :
									</label>
									<div class="col-md-5">
										<sj:textfield name="person.experience" id="experience"
											cssClass="numericDot form-control input-medium" maxlength="4"></sj:textfield>
									</div>
								</s:else>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label class="control-label col-md-4">
									Phone Number :
								</label>
								<div class="col-md-5">
									<sj:textfield name="person.phoneNumber" id="phoneNumber"
										cssClass="numeric form-control input-medium numeric" maxlength="10"></sj:textfield>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-6">
							<div class="form-group">
								<label class="control-label col-md-4">
									<span class="required">*</span> Mobile Number :
								</label>
								<div class="col-md-5">
									<sj:textfield name="person.mobileNumber" id="mobileNumber"
										cssClass="required form-control input-medium numeric" maxlength="10"
										onblur="return validateMobNumber(this.value)"></sj:textfield>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label class="control-label col-md-4">
									City :
								</label>
								<div class="col-md-5">
									<sj:textfield name="address.city" id="city" labelposition="top"
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
									<sj:textfield name="address.addressLine1" id="streetName"
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
										tabindex="6" cssClass="numeric form-control input-medium"
										maxlength="6"></sj:textfield>
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
				</div>
				<div class="form-actions fluid">
					<div class="col-md-6">
						<div class="col-md-offset-4 col-md-9">
							<sj:submit   formIds="editDriverOrHelper" targets="transportDriverOrHelper"
								value="Submit" onBeforeTopics="editDriverOrHelpers"
								validate="true" cssClass="submitBt btn blue"
								indicator="indicator" />
							<button class="btn default" data-dismiss="modal" type="button">
								Cancel
							</button>
						</div>
					</div>
				</div>
			</s:form>
		</div>
	</div>
</div> 
<script  type="text/javascript" src="${pageContext.request.contextPath}/plugins/bootstrap-switch/static/js/bootstrap-switch.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/ems.js"></script>
<script type="text/javascript">
changePageTitle('Edit Driver/Helper Information');
$(document).ready(function() {
	FormComponents.init();
	FormAdvanced.init();
	$("input:checkbox, input:radio:not('.toggle')").uniform(); 
$.subscribe('editDriverOrHelpers', function(event, data) {
	if ($('#editDriverOrHelper').valid()){
	 $('button.close').click();	
		return true;
	}else{
		event.originalEvent.options.submit = false;
		}
});
$('.numeric').numeric();
$('.numericDot').numeric( {allow : "."});
});

function changeStaffTypeDiv(roleId) {
	alert('SCHOOL_DRIVER:' + roleId)
	if (roleId == 'SCHOOL_DRIVER') {
		$('#staffTypeDiv').show();
	} else if (roleId == 'SCHOOL_HELPER') {
		$('#staffTypeDiv').hide();
	}
}
function driverVerifyDate() {
	var date0 = $('#date0').val();
	var date1 = $('#date1').val();
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
}
</script>
