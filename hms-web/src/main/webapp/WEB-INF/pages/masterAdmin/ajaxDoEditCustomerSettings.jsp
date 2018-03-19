<%@ include file="/common/taglibs.jsp"%>
<div data-width="760" class="modal fade modal-overflow in"
	style="display: block; width: 760px; margin-left: -379px; margin-top: 50px;"
	aria-hidden="false">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true"></button>
		<h4 class="bold pageTitle">
			Update Customer's Details
		</h4>
	</div>
	<div class="modal-body">
		<div class="form-body">
			<jsp:include page="/common/messages.jsp" />
			<s:form id="customer" action="ajaxEditCustomerSettings" method="post"
				theme="simple" cssClass="form-horizontal" namespace="/masterAdmin">
				<s:hidden name="custId" value="%{customer.id}"></s:hidden>
				<s:hidden name="academicYearId" value="%{academicYear.id}"></s:hidden>
				<s:hidden name="personVo.id" value="%{viewAllUsers.personId}"></s:hidden>
				<s:hidden name="addressVo.id" value="%{viewAllUsers.addressId}"></s:hidden>
			<div class="form-group">
				<label class="control-label col-md-3">
					<span class="required">*</span>Organization Name :
				</label>
				<div class="col-md-6">
					<sj:textfield id="organizationName"
							name="customer.organization" readonly="true"
							cssClass="form-control"></sj:textfield>
					<%-- <s:if test="%{objectList != null && !objectList.isEmpty()}">
						<s:select list="objectList" listKey="id"
							listValue="organizationName" id="organizationId" theme="simple"
							cssClass="form-control" name="customer.orgId"
							headerKey="" headerValue="" cssStyle="padding: 3px 10px;"></s:select>
						 <sj:textfield id="organizationName"
							name="plTitle"
							cssClass="form-control promoteClass"
							cssStyle="float: left; margin-left: 1px; margin-top: -31px; width: 343px;background-color : #FFF;"></sj:textfield>
					</s:if>
					<s:else>
						<sj:textfield id="organizationName" name="customer.organization" cssClass="form-control input-medium promoteClass"></sj:textfield>
					</s:else> --%>
				</div>
			</div>
			<%-- <div class="form-group">
				<label class="control-label col-md-3">
					<span class="required">*</span>School Name :
				</label>
				<div class="col-md-6">
					 <sj:textfield id="organizationName"
							name="customer.organization"
							cssClass="form-control promoteClass required"></sj:textfield>
				</div>
			</div> --%>
		
				<%-- <div class="form-group">
					<label class="control-label col-md-3">
						<span class="required">*</span>Organization Name :
					</label>
					<div class="col-md-9">
						<sj:textfield name="customer.organization" id="customerName"
							cssClass="required form-control input-medium"
							maxlength="40" />
					</div>
				</div> --%>
				<div class="form-group">
					<label class="control-label col-md-3">
						<span class="required">*</span>Email Id :
					</label>
					<div class="col-md-9">
						<sj:textfield name="customer.custEmail" id="customerEmail"
							cssClass="required form-control input-medium email"
							maxlength="40" />
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-md-3">
						<span class="required">*</span>Mobile Number :
					</label>
					<div class="col-md-9">
						<sj:textfield name="customer.mobileNumber"
							id="customerMobileNumber"
							cssClass="required form-control input-medium numeric"
							maxlength="10" />
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-md-3">
						<span class="required">*</span>Admin Mobile Number :
					</label>
					<div class="col-md-9">
						<sj:textfield name="viewAllUsers.mobileNumber"
							id="adminMobileNumber"
							cssClass="required form-control input-medium numeric"
							maxlength="10" />
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-md-3">
						<span class="required">*</span>Admin Email :
					</label>
					<div class="col-md-9">
						<sj:textfield name="viewAllUsers.staffEmail"
							id="adminEmail"
							cssClass="required form-control input-medium email"/>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-md-3">
						<span class="required">*</span>Free SMS :
					</label>
					<div class="col-md-9">
						<sj:textfield id="allowedsms" name="academicYear.allotedsms"
							maxlength="6" cssClass="numeric required form-control input-medium as-input" />
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-md-3">
						<span class="required">*</span>Paid SMS :
					</label>
					<div class="col-md-9">
						<sj:textfield id="paidsmsCount" name="tempId"
							maxlength="6" cssClass="paidsmsCount numeric required form-control input-medium as-input" />
							Total Paid SMS Count:<span id="totalsmsCount">
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-md-3">
						<span class="required">*</span>Paid SMS Cost :
					</label>
					<div class="col-md-9">
						<sj:textfield id="smsCost" name="customer.smsCost" maxlength="6" cssClass="numericSmsCost required form-control input-medium as-input" />
					</div>
				</div>
				
				<s:if test='%{smsServiceProvider.isCustomerSpecific == "Y"}'>
					<div class="form-group">
						<label class="control-label col-md-3">
							<span class="required">*</span>Service Provider Name :
						</label>
						<div class="col-md-9">
							<sj:textfield id="smsCost" name="smsServiceProvider.serviceProvider" cssClass="numericSmsCost required form-control input-medium as-input" disabled="true"/>
						</div>
					</div>
				</s:if>
				
				<div class="form-group">
					<label class="control-label col-md-3">
						<span class="required">*</span>SMSServiceProviders :
					</label>
					<div class="col-md-9">
					<s:if test="%{smsServiceProvidersList != null && !smsServiceProvidersList.isEmpty()}">
					<s:select id="state" list="smsServiceProvidersList"
							cssClass="form-control input-medium required" listKey="id"
							listValue="serviceProvider" headerKey="" headerValue="- Select -"
							name="customer.smsServiceProviderId" />
					</s:if>
					</div>
				</div>
				<div class="row">
				<div class="col-md-3"></div>
					<div class="col-md-9">
						<div class="form-group">
							<s:if test="%{customer.hostelModuleStatus}">
								<input type="checkbox" name="customer.hostelModuleStatus"
									id="yesCheck" class="checkbox" checked="checked">
							</s:if>
							<s:else>
								<input type="checkbox" name="customer.hostelModuleStatus"
									id="yesCheck" class="checkbox">
							</s:else>
							HMS
							<span class="hintMessage">(If you want to enable this type
								please check this check box.)</span>
						</div>
						<div class="form-group">
							<s:if test="%{customer.transportModuleStatus}">
								<input type="checkbox" name="customer.transportModuleStatus"
									id="yesCheck" class="checkbox" checked="checked">
							</s:if>
							<s:else>
								<input type="checkbox" name="customer.transportModuleStatus"
									id="yesCheck" class="checkbox">
							</s:else>
							TMS
							<span class="hintMessage">(If you want to enable this type
								please check this check box.)</span>
						</div>
					</div>
				</div>
				<div class="form-actions fluid">
					<div class="col-md-offset-3 col-md-9">
						<sj:submit   cssClass="submitBt btn blue" value="Submit"
							onBeforeTopics="customerFormValidation" validate="true"
							indicator="indicator" targets="mainContentDiv" formIds="customer" />
						<button type="button" data-dismiss="modal" class="btn default">
							Cancel
						</button>
					</div>
				</div>
			</s:form>
		</div>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function() {
	var beforeCount = '<s:property value="academicYear.paidSms" />';
	if(beforeCount > 0)
		$("#totalsmsCount").text(beforeCount);
	else
		$("#totalsmsCount").text(0);
	$("input:checkbox, input:radio").uniform();
	changePageTitle('Master Admin Details');
	$.subscribe('customerFormValidation', function(event, data) {
		if ($('#customer').valid())
			$('button.close').click();
		else
			event.originalEvent.options.submit = false;
	});
	$('.numeric').numeric();
	$('.numericSmsCost').numeric( {
		allow : "."
	});
	$(".paidsmsCount").keyup(function(){
		var oldSmsCount = 0;
		var newSmsCount = 0;
		var beforeCount = '<s:property value="academicYear.paidSms" />';
		if(beforeCount > 0){
			oldSmsCount = beforeCount;
		}else{
			oldSmsCount = 0;
		}
		var latestCount = $("#paidsmsCount").val();
		if(latestCount > 0){
			newSmsCount = latestCount;
		}else{
			newSmsCount = 0;
		}
		$("#totalsmsCount").text(parseInt(oldSmsCount) + parseInt(newSmsCount));
  });
});
</script>
