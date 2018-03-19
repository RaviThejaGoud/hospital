<%@ include file="/common/taglibs.jsp"%>
<div id="steps">
	<div id="commonTabContent" class="grid_13">
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/scripts/default/ems.js">
</script>
		<s:form action="ajaxSaveCustomerDetails" id="signupForm" method="post"
			theme="css_xhtml">
			<%@ include file="/common/messages.jsp"%>
			<s:hidden name="packageId" value="%{tempString}"></s:hidden>
			<div class='error' style='display: none;'>
				<span></span>
			</div>
			<div class="grid_11">
				<div class="grid_3">
					<h3>
						Personal Details
					</h3>
				</div>
			</div>
			<div class="grid_11">
				<div class="grid_3 labelRight">
					<span class="required">*</span>First Name:
				</div>
				<div class="grid_6">
					<sj:textfield id="firstName" name="customer.firstName" size="50"
						maxlength="50" requiredposition="first" required="true"
						cssClass="required text_right" />
				</div>
			</div>

			<div class="grid_11">
				<div class="grid_3 labelRight">
					<span class="required">*</span>Last Name:
				</div>
				<div class="grid_6">
					<sj:textfield id="lastName" name="customer.lastName" size="50"
						maxlength="50" requiredposition="first" required="true"
						cssClass="required text_right" />
				</div>
			</div>
			<div class="grid_11">
				<div class="grid_3 labelRight">
					<span class="required">*</span>Customer Short Name:
				</div>
				<div class="grid_6">
					<sj:textfield id="sender" name="customer.customerShortName"
						size="10" maxlength="10" requiredposition="first" required="true"
						cssClass="required text_right" />
				</div>
			</div>
			<div class="grid_11">
				<div class="grid_3 labelRight">
					<span class="required">*</span>Street:
				</div>
				<div class="grid_6">
					<sj:textfield id="custStreet" name="customer.address.streetName"
						size="130" maxlength="60" requiredposition="first" required="true"
						cssClass="required text_right" />
				</div>
			</div>
			<div class="grid_11">
				<div class="grid_3 labelRight">
					<span class="required">*</span>City:
				</div>
				<div class="grid_6">
					<sj:textfield id="custCity" name="customer.address.city"
						maxlength="30" required="true" requiredposition="first"
						cssClass="required text_right" />
				</div>
			</div>
			<div class="grid_11">
				<div class="grid_3 labelRight">
					<span class="required">*</span>State:
				</div>
				<div class="grid_6">
					<s:select id="custState" list="statesList"
						cssClass="required stateSelect" required="true"
						listKey="stateCode" listValue="stateName" headerKey=""
						headerValue="- Select -" name="customer.address.state" />
				</div>
			</div>
			<div class="grid_11">
				<div class="grid_3 labelRight">
					<span class="required">*</span>Pincode:
				</div>
				<div class="grid_6">
					<sj:textfield name="customer.address.postalCode" size="6"
						maxlength="6" id="custZipcode" required="true"
						requiredposition="first" cssClass="required numeric text_right" />
				</div>
			</div>
			<div class="grid_11">
				<div class="grid_3 labelRight">
					Allowed SMS:
				</div>
				<div class="grid_6">
					<sj:textfield name="customer.allowedTotalSms" size="6"
						maxlength="6" id="allowedTotalSms" requiredposition="first"
						cssClass="numeric text_right" />
				</div>
			</div>
			<div class="grid_11">
				<div class="grid_3 labelRight">
					<span class="required">*</span>Modify Invoice Password:
				</div>
				<div class="grid_6">
					<sj:textfield name="customer.modifyInvoicePassword" size="20"
						maxlength="20" id="mip" requiredposition="first" tabindex="20"
						cssClass="  textfield text_right" />
				</div>
			</div>
			<div class="grid_11">
				<div class="grid_3 labelRight">
					<span class="required">*</span>Delete Invoice Password:
				</div>
				<div class="grid_6">
					<sj:textfield name="customer.deleteInvoicePassword" size="18"
						maxlength="20" id="dip" requiredposition="first" tabindex="20"
						cssClass="textfield text_right" />
				</div>
			</div>
			<div class="grid_11 border"></div>
			<div class="grid_11">
				<div class="grid_3 labelRight">
					<h3>
						Organization Address
					</h3>
				</div>
			</div>

			<div class="grid_11">
				<div class="grid_3 labelRight">
					<span class="required">*</span>Organization Name:
				</div>
				<div class="grid_6">
					<sj:textfield id="churchName" name="customer.organization"
						size="50" maxlength="50" requiredposition="first" required="true"
						cssClass="required text_right" />
				</div>
			</div>
			<div class="grid_11">
				<div class="grid_3 labelRight">
					<span class="required">*</span>Organization Type:
				</div>
				<s:property value="organizationType.size" />
				<div class="grid_6">
					<s:select id="organizationType" list="organizationTypesList"
						cssClass="required SelectOrganizationType" required="true"
						listKey="id" listValue="organizationType" headerKey=""
						headerValue="- Select -" name="OrganizationTypes.id" />
				</div>
			</div>
			<div class="grid_11">
				<div class="grid_3 labelRight">
					AddressLine1:
				</div>
				<div class="grid_6">
					<sj:textfield id="addressLine1" name="address.addressLine1"
						size="130" maxlength="60" tabindex="15" requiredposition="first"
						cssClass="textfield" />
				</div>
			</div>
			<div class="grid_11">
				<div class="grid_3 labelRight">
					<span class="required">*</span>Street:
				</div>
				<div class="grid_6">
					<sj:textfield id="street" name="address.streetName" size="130"
						maxlength="60" requiredposition="first" required="true"
						cssClass="required text_right" />
				</div>
			</div>
			<div class="grid_11">
				<div class="grid_3 labelRight">
					<span class="required">*</span>City:
				</div>
				<div class="grid_6">
					<sj:textfield id="city" name="address.city" maxlength="30"
						required="true" requiredposition="first"
						cssClass="required text_right" />
				</div>
			</div>
			<div class="grid_11">
				<div class="grid_3 labelRight">
					<span class="required">*</span>State:
				</div>
				<div class="grid_6">
					<s:select id="state" list="statesList"
						cssClass="required stateSelect" required="true"
						listKey="stateCode" listValue="stateName" headerKey=""
						headerValue="- Select -" name="address.state" />
				</div>
			</div>
			<div class="grid_11">
				<div class="grid_3 labelRight">
					<span class="required">*</span>Pincode:
				</div>
				<div class="grid_6">
					<sj:textfield name="address.postalCode" size="6" maxlength="6"
						id="zipcode" required="true" requiredposition="first"
						cssClass="required numeric text_right" />
				</div>
			</div>
			<div class="grid_11">
				<div class="grid_3 labelRight">
					<span class="required">*</span>Phone Number:
				</div>
				<div class="grid_6">
					<sj:textfield name="customer.contactNumber" size="40"
						id="phoneNumber" required="true" maxlength="14"
						requiredposition="first" cssClass="required numeric text_right" />
				</div>
			</div>

			<div class="grid_11">
				<div class="grid_3 labelRight">
					<span class="required">*</span>Mobile Number:
				</div>
				<div class="grid_6">
					<sj:textfield name="customer.mobileNumber" id="mobileNumber"
						required="true" cssClass="numeric required textfield small"
						onblur="return validateMobNumber(this.value)" maxlength="14"
						onkeypress="return formatPhoneNumber(this,event);"></sj:textfield>
				</div>
			</div>
			<div class="grid_11 border"></div>
			<div class="grid_11">
				<div class="grid_3 labelRight">
					<h3>
						Login Credentials
					</h3>
				</div>
			</div>
			<div class="grid_11">
				<div class="grid_3 labelRight">
					<span class="required">*</span>Email:
				</div>
				<div class="grid_6">
					<sj:textfield id="email" size="30" maxlength="60"
						requiredposition="first" name="customer.custEmail" required="true"
						cssClass="required email text small" />
				</div>
			</div>
			<div class="grid_11">
				<div class="grid_3 labelRight">
					<span class="required">*</span>Password:
				</div>
				<div class="grid_5 labelRight">
					<s:password id="password" name='customer.password'
						cssClass="required password textfield small" required="true"
						cssStyle="width:70%" requiredposition="first" />
					<div id="passwordStrength" class="strength0" style="display: none;"></div>
				</div>
			</div>
			<div class="grid_11">
				<div class="grid_3 labelRight">
					&nbsp;
				</div>
				<div class="grid_6">
					<input type="checkbox" name="isHostelModuleStatus" id="yesCheck"
						class="checkbox">
					HMS
					<span class="hintMessage">(If you want to enable this type
						please check this check box.)</span>
				</div>
			</div>
			<div class="grid_11">
				<div class="grid_3 labelRight">
					&nbsp;
				</div>
				<div class="grid_6">
					<input type="checkbox" name="isTransportModuleStatus" id="yesCheck"
						class="checkbox">
					TMS
					<span class="hintMessage">(If you want to enable this type
						please check this check box.)</span>
				</div>
			</div>
			<div class="clear"></div>

			<div class="grid_11">
				<sj:submit   targets="signupContent" value="Submit"
					cssClass="button2 submit small" indicator="indicator"
					validate="true" onCompleteTopics="doCustomRegistartion" />
				<img style="display: none;" alt="Loading..."
					src="${pageContext.request.contextPath}/images/indicator.gif"
					id="indicator">
				<!--<sj:submit   id="submitButton" targets="signupStep1" value="Next"
					cssClass="submit small" indicator="indicator"
					onSuccessTopics="firstRegStep" onClickTopics="formValidation"
					cssStyle="float:right;margin:0px;padding:0px" />
					-->
			</div>
		</s:form>
	</div>
</div>
<script type="text/javascript">
$(document)
		.ready(
				function() {
					var validator;
					jQuery.validator
							.addMethod(
									"password",
									function(value, element) {
										var result = this.optional(element)
												|| value.length >= 6
												&& /\d/.test(value)
												&& /[a-z]/i.test(value);
										if (!result) {
											var validator = this;
										}
										return result;
									},
									"Your password must be at least 6 characters long and contain at least one number and one character.");
					$.subscribe('doCustomRegistartion', function(event, data) {
						if ($('#' + data.id).is(":hidden")) {
							$('#' + data.id).show();
						} else {
							$('#' + data.id).show();
						}
					});
					document.title = 'Eazy School SIGN UP ';
					$("input[id=email]")
							.autoCheck(
									"${pageContext.request.contextPath}/common/ajaxCheckEmailId.do",
									{
										minChars : 3,
										min : "no"
									});
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