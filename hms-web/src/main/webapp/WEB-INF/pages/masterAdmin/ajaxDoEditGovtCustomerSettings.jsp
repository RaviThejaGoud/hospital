<%@ include file="/common/taglibs.jsp"%>
<div data-width="760" class="modal fade modal-overflow in"
	style="display: block; width: 760px; margin-left: -379px; margin-top: 150px;"
	aria-hidden="false">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true"></button>
		<h4 class="bold pageTitle">
			Update GovtCustomer's Details
		</h4>
	</div>
	<div class="modal-body">
		<div class="form-body">
			<s:form id="customer" action="ajaxEditGovtCustomerSettings" method="post"
				theme="simple" cssClass="form-horizontal">
				<s:hidden name="custId" value="%{customer.id}"></s:hidden>
				<div class="form-group">
					<label class="control-label col-md-3">
						<span class="required">*</span>Organization Name :
					</label>
					<div class="col-md-9">
						<sj:textfield name="customer.organization" id="customerName"
							cssClass="required form-control input-medium"
							maxlength="40" />
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-md-3">
						<span class="required">*</span>Email Id :
					</label>
					<div class="col-md-9">
						<sj:textfield name="customer.custEmail" id="customerEmail"
							cssClass="required form-control input-medium"
							maxlength="40" />
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-md-3">
						<span class="required">*</span>Contact Number :
					</label>
					<div class="col-md-9">
						<sj:textfield name="customer.contactNumber"
							id="customerContactNumber"
							cssClass="required form-control input-medium numeric"
							maxlength="11" />
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-md-3">
						<span class="required">*</span>Allowed SMS :
					</label>
					<div class="col-md-9">
						<sj:textfield id="allowedsms" name="customer.allowedTotalSms"
							maxlength="6" cssClass="numeric required form-control input-medium as-input" />
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
<script language="JavaScript" type="text/javascript">
$(document).ready(function() {
	$("input:checkbox, input:radio").uniform();
	changePageTitle('Master Admin Details');
	$.subscribe('customerFormValidation', function(event, data) {
		if ($('#customer').valid())
			$('button.close').click();
		else
			event.originalEvent.options.submit = false;
	});
	$('.numeric').numeric();
});
</script>
