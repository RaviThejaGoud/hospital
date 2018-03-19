<%@ include file="/common/taglibs.jsp"%>
<span id="accountAcategoryId" class="<s:property value='financialAccountDetailsVO.financialAccountCategoryVO.id'/>"></span>
<span id="voucherCount" class="<s:property value='tempList2.size'/>"></span>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i> Bank Account Details
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">

					<div id="voucherContentDiv" class="tab-content">
						<jsp:include page="/common/messages.jsp" />
						<div class="form-body">
							<s:form id="bankAccountDetails" action="ajaxAddBankAccountDetails" method="post" theme="simple" cssClass="form-horizontal" namespace="/account">
								<s:hidden name="bankAccountDetailsVO.id"></s:hidden>
								<s:hidden name="bankAccountDetailsVO.addressVO.id"></s:hidden>
								<div class="row">
									<div class="col-md-6">
										<div class="form-group ">
											<label class="control-label col-md-4">
												<span class="required">*</span> Account Holder Name : </label>
											<div class="col-md-6">
												<sj:textfield name="bankAccountDetailsVO.accountName" id="accountName" cssClass="form-control required"></sj:textfield>
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group ">
											<label class="control-label col-md-4">
												<span class="required">*</span> Account Number : 
											</label>
											<div class="col-md-6">
												<sj:textfield name="bankAccountDetailsVO.accountNumber" id="accountNumber" cssClass="form-control required"></sj:textfield>
											</div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-6">
										<div class="form-group ">
											<label class="control-label col-md-4">
												<span class="required">*</span> Bank Name : 
											</label>
											<div class="col-md-6">
												<sj:textfield name="bankAccountDetailsVO.bankName" id="bankName" cssClass="form-control required"></sj:textfield>
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group ">
											<label class="control-label col-md-4">
												<span class="required">*</span> IFSC Code : 
											</label>
											<div class="col-md-6">
												<sj:textfield name="bankAccountDetailsVO.ifscCode" id="ifscCode" cssClass="form-control required"></sj:textfield>
											</div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-6">
										<div class="form-group ">
											<label class="control-label col-md-4">
												<span class="required">*</span> Bank Email : 
											</label>
											<div class="col-md-6">
												<sj:textfield name="bankAccountDetailsVO.addressVO.email" id="email" cssClass="form-control required"></sj:textfield>
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group ">
											<label class="control-label col-md-4">
												<span class="required">*</span> Bank Phone Number : 
											</label>
											<div class="col-md-6">
												<sj:textfield name="bankAccountDetailsVO.mobileNumber" id="mobileNumber" cssClass="form-control required numaric" maxlength="10"></sj:textfield>
											</div>
										</div>
									</div>
								</div>

								<h5>ADDRESS</h5>
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											<label class="control-label col-md-4"> Street : </label>
											<div class="col-md-6">
												<sj:textfield name="bankAccountDetailsVO.addressVO.addressLine1"
													id="addressLines" cssClass="form-control " maxlength="55"></sj:textfield>
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group ">
											<label class="control-label col-md-4"> 
												<span class="required">*</span>City :
											</label>
											<div class="col-md-6">
												<sj:textfield name="bankAccountDetailsVO.addressVO.city"
													id="city" cssClass="required form-control"></sj:textfield>
											</div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											<label class="control-label col-md-4"> 
												<span class="required">*</span>Country :
											</label>
											<div class="col-md-6">
												<s:select id="country" list="countryList" cssClass="required form-control countrySelect"
													listKey="countryCode" listValue="countryName" headerKey="" headerValue="- Select -"
													name="bankAccountDetailsVO.addressVO.country"
													onchange="javascript:getCountryState(this.value);" />
											</div>
										</div>
									</div>
									<div class="col-md-6" id="countryState" style="display: none">
										<div class="form-group">
											<label class="control-label col-md-4"> <span
												class="required">*</span>State :
											</label>
											<div class="col-md-6">
												<s:select id="state" list="statesList" cssClass="required form-control stateSelect"
													listKey="stateCode" listValue="stateName" headerKey="" headerValue="- Select -"
													name="bankAccountDetailsVO.addressVO.state" />
											</div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-6">
										<div class="form-group ">
											<label class="control-label col-md-4"> Pincode : </label>
											<div class="col-md-6">
												<sj:textfield name="bankAccountDetailsVO.addressVO.postalCode"
													id="pincode" label="Pincode" cssClass="numeric form-control" maxlength="6"></sj:textfield>
											</div>
										</div>
									</div>
									<div class="col-md-6"></div>
								</div>


								<div class="form-actions fluid">
									<div class="col-md-offset-2 col-md-9">
										<sj:submit value="Submit" cssClass="submitBt btn blue" onBeforeTopics="financialCustomerDetailsForm"
											resetForm="true" formIds="bankAccountDetails" targets="mainContentDiv" indicator="indicator" validate="true" />
										<%-- <s:url id="createAccountMaster" action="ajaxViewCreateAccountMaster" namespace="/account" />
										<sj:a href="%{createAccountMaster}" targets="mainContentDiv" cssClass="btn default">Cancel</sj:a> --%>
									</div>
								</div>
							</s:form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<script>
	$(document).ready(function() {
		$('.numeric').numeric();
		$('.numericDot').numeric({
			allow : "."
		});
		changePageTitle("Add Customer Account Details.");
		var countryId = $("select#country").val();
		getCountryState(countryId);
	});

	/* $.subscribe('financialCustomerDetailsForm', function(event, data) {
		if ($('#financialCustomerDetailsId').valid()) {
			return true;
		} else {
			event.originalEvent.options.submit = false;
		}
	}); */

	function getCountryState(selectBox) {
		var countryId = selectBox;
		if (countryId == "IN") {
			$("#countryState").show();
		} else {
			$("#countryState").hide();
			$('#state').val('');
		}
	}
</script>