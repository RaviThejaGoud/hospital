<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
<s:form action="ajaxMasterAdminCustomerDetails" id="addMultiBranchCustomer" method="post" theme="simple" cssClass="form-horizontal" namespace="/masterAdmin">
	<s:hidden id="custIds" name="anyId" />
	<s:hidden name="tempId" value="%{masterCustomer.id}" />
	<div class="form-body">
	<div class="col-md-11">
		<h4 class="pageTitle bold">
			Multi Branch Admin Personal Details
		</h4>
	</div>
	<div class="row">
		<div class="col-md-6">
			<div class="form-group">
				<label class="control-label col-md-5">
					<span class="required">*</span>First Name :
				</label>
				<div class="col-md-7">
					<sj:textfield name="masterCustomer.firstName" id="firstName"
						cssClass="required form-control input-medium as-input" size="50"
						maxlength="50"   />
				</div>
			</div>
		</div>
		<div class="col-md-6">
			<div class="form-group">
				<label class="control-label col-md-4">
					<span class="required">*</span>Last Name :
				</label>
				<div class="col-md-8">
					<sj:textfield name="masterCustomer.lastName" id="lastName"
						cssClass="required form-control input-medium as-input" size="50"
						maxlength="50"   />

				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-6">
			<div class="form-group">
				<label class="control-label col-md-5">
					<span class="required">*</span>Organization Name :
				</label>
				<div class="col-md-7">
					<sj:textfield name="masterCustomer.organization" id="churchName"
						cssClass="required form-control input-medium as-input" size="50"
						maxlength="50"   />
				</div>
			</div>
		</div>
		<div class="col-md-6">
			<div class="form-group">
				<label class="control-label col-md-4">
					<span class="required">*</span>Email :
				</label>
				<div class="col-md-8">
					<sj:textfield name="masterCustomer.custEmail" id="email"
						cssClass="required form-control input-medium as-input" size="50"
						maxlength="50"   />

				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-6">
			<div class="form-group">
				<label class="control-label col-md-5">
					<span class="required">*</span>Phone Number :
				</label>
				<div class="col-md-7">
					<sj:textfield name="masterCustomer.contactNumber" id="phoneNumber"
						cssClass="required form-control input-medium numeric" size="12"
						maxlength="12"   />
				</div>
			</div>
		</div>
		<div class="col-md-6">
			<div class="form-group">
				<label class="control-label col-md-4">
					<span class="required">*</span>Mobile Number :
				</label>
				<div class="col-md-8">
					<sj:textfield name="masterCustomer.mobileNumber" id="mobileNumber"
						cssClass="required form-control input-medium numeric"
						 maxlength="14" onkeypress="return formatPhoneNumber(this);"
						onblur="return validateMobNumber(this.value)" />
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-6">
			<div class="form-group">
				<label class="control-label col-md-5">
					<span class="required">*</span>Street :
				</label>
				<div class="col-md-7">
					<sj:textfield name="masterCustomer.address.streetName"
						id="custStreet"
						cssClass="required form-control input-medium as-input" size="60"
						maxlength="50"   />
				</div>
			</div>
		</div>
		<div class="col-md-6">
			<div class="form-group">
				<label class="control-label col-md-4">
					<span class="required">*</span>City :
				</label>
				<div class="col-md-8">
					<sj:textfield name="masterCustomer.address.city" id="custCity"
						cssClass="required form-control input-medium as-input"
						 maxlength="30" />
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-6">
			<div class="form-group">
				<label class="control-label col-md-5">
					<span class="required">*</span>State :
				</label>
				<div class="col-md-7">
					<s:select id="custState" list="statesList"
						cssClass="required form-control input-medium as-input"
						 listKey="stateCode" listValue="stateName"
						headerKey="" headerValue="- Select -"
						name="masterCustomer.address.state" theme="simple" />
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-10">
			<div class="form-group">
				<label class="control-label col-md-3">
					Select Multi Branch School :
				</label>
				<div class="col-md-9">
					<s:checkboxlist list="customersList" name="tempList"
						listKey="%{id}" listValue="organization" id="classes"
						label="Select Organization" theme="ems"
						cssClass="checkbox required form-control"></s:checkboxlist>
				</div>
			</div>
		</div>
	</div>
	<div class="form-actions fluid">
		<div class="col-md-6">
			<div class="col-md-offset-5 col-md-9">
				<sj:submit   targets="mainContentDiv" value="Submit"
					cssClass="submitBt btn blue" indicator="indicator" validate="true"
					onBeforeTopics="doCustomRegistartion"
					formIds="addMultiBranchCustomer" />
				<s:url id="doCancelMultiBranchCust" action="ajaxDoCreateMaster"
					includeParams="all" escapeAmp="false" namespace="/masterAdmin"></s:url>
				<sj:a href="%{doCancelMultiBranchCust}" cssClass="btn default"
					indicator="indicator" targets="mainContentDiv">Cancel</sj:a>
			</div>
		   </div>
		</div>
	</div>
</s:form>
<script type="text/javascript">
$("input:checkbox, input:radio").uniform();
$ .subscribe( 'doCustomRegistartion',
				function(event, data) {
					if ($('#addMultiBranchCustomer').valid()) {
						var selectedChkCount = $("input[name=tempList]:checked").length;
						if (selectedChkCount > 0) {
							var subjectIds = [];
							$("input:checkbox[name=tempList]:checked").each(
									function() {
										subjectIds.push($(this).val());
									});
							$('#custIds').val(subjectIds);
							return true;
							document.title = 'Multi Branch Admin Registration';
							$("input[id=email]")
									.autoCheck(
											"${pageContext.request.contextPath}/common/ajaxCheckEmailId.do",
											{
												minChars : 3,
												min : "no"
											});
						} else {
							alert("Please select at least one organization.");
							event.originalEvent.options.submit=false;
						}
					} else
						event.originalEvent.options.submit=false;
				});
$('.numeric').numeric();
function formatPhoneNumber(object) {
	var phoneString = object.value;
	if (phoneString.length == 1) {
		phoneString = "+91-" + phoneString;
	}
	object.value = phoneString;
}
</script>
