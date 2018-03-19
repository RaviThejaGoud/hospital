<%@ include file="/common/taglibs.jsp"%>
<span id="accountAcategoryId" class="<s:property value='financialAccountDetailsVO.financialAccountCategoryVO.id'/>"> </span>
<span id="voucherCount" class="<s:property value='tempList2.size'/>"> </span>
<div class="form-body">
	<div class="row">
		<div class="col-md-6 form-horizontal">
			<div class="form-group">
				<label class="control-label col-md-4"><span class="required">*</span>Account Type :</label>  
				<div class="col-md-6">
					<s:if test="%{tempList2.size > 0}">
						<s:select id="accountTypeId" list="objectList" cssClass="form-control " listKey="id" listValue="accountType"
								headerKey="" name="financialAccountDetailsVO.financialAccountTypeVO.id" onchange="getAccountType(this.value)" disabled="true"/>
								<s:hidden name="financialAccountDetailsVO.financialAccountTypeVO.id"></s:hidden>
					</s:if>
					<s:else>
						<s:select id="accountTypeId" list="objectList" cssClass="form-control " listKey="id" listValue="accountType"
								headerKey="" name="financialAccountDetailsVO.financialAccountTypeVO.id" onchange="getAccountType(this.value)" />					
					</s:else>
				</div>
			</div>
		</div>
		<div class="col-md-6 form-horizontal">
			<div class="form-group">
				<label class="control-label col-md-4"><span class="required">*</span>
					Select Financial Year : </label>
				<div class="col-md-5">
					<s:select id="finanacial" list="financialYearList" 
							cssClass="form-control input-medium required" listKey="id"
							listValue="yearName"/>
				</div>
			</div>
		</div>
	</div>
	<div id="accountDiv">
		<s:form id="createAccountMasterId" action="ajaxAddFinancialAccountDetails" method="post" theme="simple" cssClass="form-horizontal" namespace="/account">
		<s:hidden name="financialAccountDetailsVO.financialAccountTypeVO.id" id="accountIdDiv"></s:hidden>
		<s:hidden name="financialAccountDetailsVO.financialYearVO.id" id="financeIdDiv"></s:hidden>
		<s:hidden name="financialAccountDetailsVO.id"></s:hidden>
		<s:hidden name="financialAccountTotalsVO.id"></s:hidden>
		<h4 class="pageTitle bold form-section">Account Settings</h4>
			<div class="row">
				<div class="col-md-6">
					<div id="accountTypeDiv"></div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"><span class="required">*</span>Account Name :</label>
						<div class="col-md-6">
							<sj:textfield name="financialAccountDetailsVO.accountName"
								id="accountName" cssClass="form-control required"></sj:textfield>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"><span class="required">*</span>Transaction Type :</label>
						<div class="col-md-6">
							<s:if test="%{tempList2.size > 0}">
								<s:select id="Opening" headerKey="" cssClass="form-control required"  headerValue="- Select -" 
										name="financialAccountTotalsVO.transactionType" list="#{'D':'Debit','C':'Credit'}" disabled="true"/>
										<s:hidden name="financialAccountTotalsVO.transactionType"></s:hidden>
							</s:if>
							<s:else>
								<s:select id="Opening" headerKey="" cssClass="form-control required"  headerValue="- Select -" 
										name="financialAccountTotalsVO.transactionType" list="#{'D':'Debit','C':'Credit'}"/>
							</s:else>
							
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4">Opening Balance :</label>
						<div class="col-md-6">
							<s:if test="%{tempList2.size > 0}">
								<sj:textfield name="financialAccountTotalsVO.balanceAmount" id="Banlance" cssClass="form-control" disabled="true"></sj:textfield>
								<s:hidden name="financialAccountTotalsVO.balanceAmount"></s:hidden>
							</s:if>
							<s:else>
								<sj:textfield name="financialAccountTotalsVO.balanceAmount" id="Banlance" cssClass="form-control"></sj:textfield>
							</s:else>
							
						</div>
					</div>
				</div>
			</div>
			
			<div class="form-actions fluid">
				<div class="col-md-offset-2 col-md-9">
					<sj:submit value="Submit" cssClass="submitBt btn blue"
						onBeforeTopics="createAccountMasterForm" resetForm="true"
						formIds="createAccountMasterId" targets="accountMasterContentDiv"
						indicator="indicator" validate="true" />
					<s:url id="createAccountMaster" action="ajaxViewCreateAccountMaster"
						namespace="/account" />
					<sj:a href="%{createAccountMaster}" targets="mainContentDiv"
						cssClass="btn default">Cancel</sj:a>
				</div>
			</div>
		</s:form>
	</div>
	<div id="vendorDiv" style="display: none;">
		<s:form id="financialCustomerDetailsId" action="ajaxAddFinancialAccountDetails" method="post" theme="simple" cssClass="form-horizontal" namespace="/account">
		<s:hidden name="financialAccountDetailsVO.financialAccountTypeVO.id" id="vendorDivId"></s:hidden>
		<s:hidden name="financialAccountDetailsVO.financialYearVO.id" id="tempId1"></s:hidden>
		<s:hidden name="financialAccountDetailsVO.id"></s:hidden>
		<s:hidden name="financialAccountTotalsVO.id"></s:hidden>
			<h4 class="pageTitle bold form-section">General Settings</h4>
			<div class="row">
				<div class="col-md-6">
					<div id="vendorTypeDiv"></div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"><span
							class="required">*</span>Vendor Name :</label>
						<div class="col-md-6">
							<sj:textfield name="financialAccountDetailsVO.financialCustomerDetailsVO.customerName"
								id="eventLocation" cssClass="form-control required"></sj:textfield>
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
							<sj:textfield name="financialAccountDetailsVO.financialCustomerDetailsVO.addressVO.addressLine1" id="addressLines"
								 cssClass="form-control " maxlength="55"></sj:textfield>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group ">
						<label class="control-label col-md-4"> <span
							class="required">*</span>City :
						</label>
						<div class="col-md-6">
							<sj:textfield name="financialAccountDetailsVO.financialCustomerDetailsVO.addressVO.city" id="city" 
								cssClass="required form-control"></sj:textfield>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> <span
							class="required">*</span>Country :
						</label>
						<div class="col-md-6">
							<s:select id="country" list="countryList"
								cssClass="required form-control countrySelect"
								listKey="countryCode" listValue="countryName" headerKey=""
								headerValue="- Select -" name="financialAccountDetailsVO.financialCustomerDetailsVO.addressVO.country"
								onchange="javascript:getCountryState(this);" />
						</div>
					</div>
				</div>
				<div class="col-md-6" id="countryState" style="display: none">
					<div class="form-group">
						<label class="control-label col-md-4"> <span
							class="required">*</span>State :
						</label>
						<div class="col-md-6">
							<s:select id="state" list="statesList"
								cssClass="required form-control stateSelect"
								listKey="stateCode" listValue="stateName" headerKey=""
								headerValue="- Select -" name="financialAccountDetailsVO.financialCustomerDetailsVO.addressVO.state" />
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group ">
						<label class="control-label col-md-4"> Pincode : </label>
						<div class="col-md-6">
							<sj:textfield name="financialAccountDetailsVO.financialCustomerDetailsVO.addressVO.postalCode" id="pincode"
								label="Pincode" cssClass="numeric form-control"
								maxlength="6"></sj:textfield>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> Mobile Number : </label>
						<div class="col-md-6">
							<sj:textfield name="financialAccountDetailsVO.financialCustomerDetailsVO.mobileNumber" id="phoneNumber"
								 cssClass="numeric form-control " maxlength="14"></sj:textfield>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group ">
						<label class="control-label col-md-4"> Email : </label>
						<div class="col-md-6">
							<sj:textfield name="financialAccountDetailsVO.financialCustomerDetailsVO.addressVO.email" id="email"
								 cssClass="form-control email"></sj:textfield>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> Contact Name : </label>
						<div class="col-md-6">
							<sj:textfield name="financialAccountDetailsVO.financialCustomerDetailsVO.contactName" id="contactName"
								 cssClass="form-control "></sj:textfield>
						</div>
					</div>
				</div>
			</div>
			<h4 class="pageTitle bold form-section">Account Settings</h4>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group ">
						<label class="control-label col-md-4"><span class="required">*</span> Account Name : </label>
						<div class="col-md-6">
							<sj:textfield name="financialAccountDetailsVO.accountName" id="officeNumber"
								 cssClass="form-control required"></sj:textfield>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> Transaction Type : </label>
						<div class="col-md-6">
							<s:if test="%{tempList2.size > 0}">
								<s:select id="Opening" headerKey="" cssClass="form-control" headerValue="- Select -" 
										name="financialAccountTotalsVO.transactionType" list="#{'D':'Debit','C':'Credit'}" disabled="true"/>
										<s:hidden name="financialAccountTotalsVO.transactionType"></s:hidden>
							</s:if>
							<s:else>
								<s:select id="Opening" headerKey="" cssClass="form-control" headerValue="- Select -" 
										name="financialAccountTotalsVO.transactionType" list="#{'D':'Debit','C':'Credit'}"/>
							</s:else>
							
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group ">
						<label class="control-label col-md-4"> Opening Balance : </label>
						<div class="col-md-6">
							<s:if test="%{tempList2.size > 0}">
								<sj:textfield name="financialAccountTotalsVO.balanceAmount" id="Balance" cssClass="form-control numericDot" maxlength="11" disabled="true"></sj:textfield>
								<s:hidden name="financialAccountTotalsVO.balanceAmount"></s:hidden>
							</s:if>
							<s:else>
								<sj:textfield name="financialAccountTotalsVO.balanceAmount" id="Balance" cssClass="form-control numericDot" maxlength="11"></sj:textfield>
							</s:else>
							
						</div>
					</div>
				</div>
				<div class="col-md-6">
					
				</div>
			</div>
			<h5>Tax Details</h5>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> TIN No: </label>
						<div class="col-md-6">
							<sj:textfield name="financialAccountDetailsVO.tinNumber" id="TIN"
								 cssClass="form-control "></sj:textfield>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group ">
						<label class="control-label col-md-4">TIN Issue Date : </label>
						<div class="col-md-6">
							<div data-date-format="mm/dd/yyyy"
									class="input-group input-medium date date-picker">
									<input type="text" id="tinIssueDate" readonly=""
										class="form-control"   name="financialAccountDetailsVO.tinIssueDate" value='<s:property value="financialAccountDetailsVO.tinIssueDateString"/>'> <span
										class="input-group-btn">
										<button type="button" class="btn default">
											<i class="fa fa-calendar"></i>
										</button>
									</span>
								</div>
								<span class="help-block">(MM/DD/YYYY)</span>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> GST No : </label>
						<div class="col-md-6">
							<sj:textfield name="financialAccountDetailsVO.gstNumber" id="number"
								 cssClass="form-control "></sj:textfield>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group ">
						<label class="control-label col-md-4">GST Issue Date : </label>
						<div class="col-md-6">
							<div data-date-format="mm/dd/yyyy"
									class="input-group input-medium date date-picker">
									<input type="text" id="Issue" readonly=""
										class="form-control"   name="financialAccountDetailsVO.gstIssueDate" value='<s:property value="financialAccountDetailsVO.gstIssueDateString"/>' > <span
										class="input-group-btn">
										<button type="button" class="btn default">
											<i class="fa fa-calendar"></i>
										</button>
									</span>
								</div>
								<span class="help-block">(MM/DD/YYYY)</span>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label class="control-label col-md-4"> I.T.PAN.No : </label>
						<div class="col-md-6">
							<sj:textfield name="financialAccountDetailsVO.itPanNumber" id="itPanNumber"
								 cssClass="form-control"></sj:textfield>
						</div>
					</div>
				</div>
			</div>
		   <div class="form-actions fluid">
			<div class="col-md-offset-2 col-md-9">
				<sj:submit value="Submit" cssClass="submitBt btn blue" onBeforeTopics="financialCustomerDetailsForm" 
					resetForm="true" formIds="financialCustomerDetailsId" targets="accountMasterContentDiv" indicator="indicator" validate="true" />
				<s:url id="createAccountMaster" action="ajaxViewCreateAccountMaster" namespace="/account" />
				<sj:a href="%{createAccountMaster}" targets="mainContentDiv" cssClass="btn default">Cancel</sj:a>
			</div>
		</div>
	</s:form>
	</div>
	</div>
<script>
	$(document).ready(function() {
		FormComponents.init();
		$('.numeric').numeric();
		$('.numericDot').numeric( {allow : "."});
		changePageTitle("Add New Account Details.");
		var type=$('select#accountTypeId').val();
		getAccountType(type);
		 var finanacialYear=$('#finanacial').val();
		 $('#tempId1').val(finanacialYear);
		 $('#financeIdDiv').val(finanacialYear);
		 
		 var countryId = $("select#country").val();
		 
		 getCountryState(countryId);
	});
	
	/* $('#finanacial').change(function(){
		 $('#tempId1').val($(this).val());
		 $('#financeIdDiv').val($(this).val());
	})
	 */
	$.subscribe('financialCustomerDetailsForm', function(event, data) {
		if ($('#financialCustomerDetailsId').valid()) {
			 $('input[type="submit"]').prop('disabled', true);
			return true;
		} else {
			event.originalEvent.options.submit = false;
		}
	});
	$.subscribe('createAccountMasterForm', function(event, data) {
		if ($('#createAccountMasterId').valid()) {
			 $('input[type="submit"]').prop('disabled', true);
			return true;
		} else {
			event.originalEvent.options.submit = false;
		}
	});
	function getAccountType(type) {
		var accountAcategoryId = $('#accountAcategoryId').attr('class');
		var voucherCount= $('#voucherCount').attr('class');
		var pars =null;
		if(accountAcategoryId >0)
			pars = "bankId=" + type+"&anyId="+accountAcategoryId+"&tempId="+voucherCount;
		else
			pars = "bankId=" + type+"&tempString="+voucherCount;
		var url = jQuery.url.getChatURL("/account/ajaxGetAccountTypeByaccountId.do");
		if (type == 1) {
			$('#accountDiv').show();
			$('#vendorDiv').hide();
		    $('#accountIdDiv').val(type);
			$("#accountTypeDiv") .html( '<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
			$.ajax( {
					url : url,
					cache : false,
					data : pars,
					success : function(html) {
						$("#accountTypeDiv").html(html);
						$("#accountTypeDiv").show();
					}
				});
		} else {
			$('#vendorDiv').show();
			$('#accountDiv').hide();
			$('#vendorDivId').val(type);
			$("#vendorTypeDiv") .html( '<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
			$.ajax( {
					url : url,
					cache : false,
					data : pars,
					success : function(html) {
						$("#vendorTypeDiv").html(html);
						$("#vendorTypeDiv").show();
					}
				});
		}
		
		
			
	}
	function getCountryState(selectBox) {
		var countryId = $("select#country").val();
		if(countryId=="IN"){
			$("#countryState").show();
		}else{
			$("#countryState").hide();
			$('#state').val('');
		}
	}
</script>