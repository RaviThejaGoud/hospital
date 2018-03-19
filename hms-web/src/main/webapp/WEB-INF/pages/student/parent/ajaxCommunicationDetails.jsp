<%@ include file="/common/taglibs.jsp"%>
<s:form action="ajaxSaveCommunicationDetails" theme="simple"
		cssClass="form-horizontal" id="editStudentPersonalInfo" method="post"
		enctype="multipart/form-data" namespace="/student">
		<s:hidden name="student.id" />
		<div class="form-body">
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-5">Address For Communication : </label>
					<div class="col-md-7">
						<div class="make-switch has-switch" data-id="R" data-value="C"
							style="width: 237px" data-off="warning" data-on="success"
							data-off-label="Correspondence" data-on-label="Residential">
							<s:if test='%{student.account.person.addressType =="R"}'>
								<input type="radio" class="toggle" checked="checked"
									id="residence">
								<input type="hidden" name="student.account.person.addressType"
									value='<s:property value="student.account.person.addressType"/>'>
							</s:if>
							<s:elseif test='%{student.account.person.addressType =="C"}'>
								<input type="radio" class="toggle" id="residence">
								<input type="hidden" name="student.account.person.addressType"
									value='<s:property value="student.account.person.addressType"/>'>
							</s:elseif>
							<s:else>
								<input type="radio" class="toggle" checked="checked"
									id="residence">
								<input type="hidden" name="student.account.person.addressType" value='R'>
							</s:else>
						</div>
					</div>
				</div>
			</div>
		</div>
		<h5 class="control-label col-md-3">RESIDENTIAL ADDRESS</h5>
		<div class="clearfix">&nbsp;</div>
		<div class="clearfix">&nbsp;</div>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-5"> <span
						class="required">*</span>Primary Mobile Number :
					</label>
					<div class="col-md-7">
						<sj:textfield name="student.account.person.mobileNumber" 
							id="mobileNumber"
							cssClass="numeric required form-control input-medium as-input"
							maxlength="14"
							onkeypress="return formatMobileNumber(this,event);" readonly="true"
							onchange="javascript:validateMobNumbers(this.value);"></sj:textfield>
							<span class="help-block">(This is considered as Parent Login Id)</span>
					</div>
				</div>
			</div>
			<div class="col-md-6">
			<div class="form-group">
				<label class="control-label col-md-5">Secondary Mobile Number :
				</label>
				<div class="col-md-7">
					<sj:textfield name="student.account.person.secondaryMobileNumber" id="secondmobileNumber"
						cssClass="numeric form-control input-medium as-input" readonly="true"
						maxlength="14"
						onkeypress="return formatMobileNumber(this,event);"></sj:textfield>
				</div>
			</div> 
		</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-5"> Parent E-Mail : </label>
					<div class="col-md-7">
						<sj:textfield name="student.account.person.parentEmail"
							id="email" label="Parent E-Mail " maxlength="60" readonly="true"
							cssClass="form-control input-medium as-input email">
						</sj:textfield>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-5"> Street : </label>
					<div class="col-md-7">
						<sj:textfield name="student.account.primaryAddress.addressLine1" readonly="true"
							cssClass="form-control input-medium as-input" id="streetName"
							maxlength="200"></sj:textfield>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-5"> City : </label>
					<div class="col-md-7">
						<sj:textfield name="student.account.primaryAddress.city" id="city" readonly="true"
							cssClass="form-control input-medium as-input" maxlength="20"></sj:textfield>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-5"> State : </label>
					<div class="col-md-7">
						<s:select id="state" list="statesList"
							cssClass="form-control input-medium" listKey="stateCode" readonly="true"
							listValue="stateName" headerKey="" headerValue="- Select -"
							name="student.account.primaryAddress.state" tabindex="33"
							onchange="javascript:getCastDetailsByState(this);" />
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-5"> Pincode : </label>
					<div class="col-md-7">
						<sj:textfield name="student.account.primaryAddress.postalCode" readonly="true"
							id="pincode" tabindex="34"
							cssClass="numeric form-control input-medium as-input"
							maxlength="6"></sj:textfield>
					</div>
				</div>
			</div>
		</div>
		<div class="clearfix">&nbsp;</div>
		<h5 class="control-label col-md-3">CORRESPONDENCE ADDRESS</h5>
		<div class="clearfix">&nbsp;</div>
		<div class="clearfix">&nbsp;</div>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-5"> <span
						class="required">*</span>Primary Mobile Number :
					</label>
					<div class="col-md-7">
						<sj:textfield name="student.account.person.anotherMobileNumber" id="tstumobileNumber" readonly="true"
							cssClass="numeric required form-control input-medium as-input"
							maxlength="14"
							onkeypress="return formatMobileNumber(this,event);"
							onchange="javascript:validateMobNumbers(this.value)"></sj:textfield>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-5">Secondary Mobile Number :
					</label>
					<div class="col-md-7">
						<sj:textfield name="student.account.person.anotherSecondaryMobileNumber" id="tstumobileNumbers" readonly="true"
							cssClass="numeric form-control input-medium as-input"
							maxlength="14"
							onkeypress="return formatMobileNumber(this,event);"
							onchange="javascript:validateMobNumbers(this.value)"></sj:textfield>
					</div>
				</div>
			</div>
			</div>
			<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-5">Contact E-Mail : </label>
					<div class="col-md-7">
						<sj:textfield name="student.account.person.anotherParentEmail" id="temailAddress" readonly="true"
							cssClass="form-control input-medium as-input" maxlength="50"></sj:textfield>
					</div>
				</div>
			</div>
			</div>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-5"> Street : </label>
					<div class="col-md-7">
						<sj:textfield name="student.account.tempararyAddress.addressLine1" readonly="true"
							id="streetName1" cssClass="form-control input-medium as-input"
							maxlength="255"></sj:textfield>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-5"> City : </label>
					<div class="col-md-7">
						<sj:textfield name="student.account.tempararyAddress.city" readonly="true"
							id="city1" cssClass="form-control input-medium as-input"
							maxlength="22"></sj:textfield>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-5"> State : </label>
					<div class="col-md-7">
						<s:select id="state1" list="statesList" label="State" readonly="true"
							cssClass="form-control input-medium" listKey="stateCode"
							listValue="stateName" headerKey="" headerValue="- Select -"
							name="student.account.tempararyAddress.state"
							onchange="javascript:getCastDetailsByState(this);" />
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-5"> Pincode : </label>
					<div class="col-md-7">
						<sj:textfield name="student.account.tempararyAddress.postalCode" readonly="true"
							id="pincode1"
							cssClass="numeric form-control input-medium as-input"
							maxlength="6"></sj:textfield>
					</div>
				</div>
			</div>
		</div>
		<s:if
			test='%{(#session.previousYear == null || #session.previousYear.isEmpty()) || (#session.previousYear == "N")}'>
			<div class="form-actions fluid">
				<div class="col-md-offset-3 col-md-9">
					<sj:submit cssClass="btn blue" value="Save" validate="true"
						indicator="indicator" targets="mainContentDiv"
						onBeforeTopics="validateStudentPersonalInfo"
						formIds="editStudentPersonalInfo" />
				</div>
			</div>
		</s:if>
	 </div>
</s:form>
<script language="JavaScript" type="text/javascript" src="${pageContext.request.contextPath}/plugins/bootstrap-switch/static/js/bootstrap-switch.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	FormComponents.init();
	FormAdvanced.init();
	UIExtendedModals.init();
	$("input:checkbox, input:radio:not('.toggle')").uniform();
});
</script>