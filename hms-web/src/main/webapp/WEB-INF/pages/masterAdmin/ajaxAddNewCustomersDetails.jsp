<%@ include file="/common/taglibs.jsp"%>
<s:form id="addSmsProvider" action="ajaxAddNewCustomersDetails"
	method="post" theme="simple" cssClass="form-horizontal" namespace="/masterAdmin">
	<s:hidden name="inviteCustomerEnrollment.id" value="%{inviteCustomerEnrollment.id}"></s:hidden>
	<div class="form-body">
		<h4 class="bold pageTitle">
			<s:if test="%{inviteCustomerEnrollment.id != 0}">
			Update New Customers
		</s:if>
			<s:else>
			Create New Customers
		</s:else>
		</h4>
		<div class="form-group">
			<label class="control-label col-md-3">
				<span class="required">*</span>School Name :
			</label>
			<div class="col-md-9">
				<sj:textfield name="inviteCustomerEnrollment.schoolName"
					id="providerName"
					cssClass="required form-control input-medium as-input"
					maxlength="120"></sj:textfield>
			</div>
		</div>
		
		<div class="form-group">
			<label class="control-label col-md-3">
				<span class="required">*</span>Total Students :
			</label>
			<div class="col-md-9">
			
				<s:select id="state" list="packageDetailsList"
												cssClass="required form-control input-medium as-input" theme="simple" 
												listKey="id" listValue="studentsRange" headerKey=""
												headerValue="- Select -" name="inviteCustomerEnrollment.packageDetails.id" />
												
												
				<%-- <sj:textfield name="inviteCustomerEnrollment.totalStudents"
					id="providerName"
					cssClass="required form-control input-medium as-input"
					maxlength="120"></sj:textfield> --%>
			</div>
		</div>
		
		<div class="form-group">
			<label class="control-label col-md-3">
				<span class="required">*</span>Email Address :
			</label>
			<div class="col-md-9">
				<sj:textfield name="inviteCustomerEnrollment.email"
					id="providerName"
					cssClass="required form-control input-medium as-input"
					maxlength="120"></sj:textfield>
			</div>
		</div>
		
		
		<div class="form-group">
			<label class="control-label col-md-3">
				Account Type : </label>
			<div class="col-md-9">
				<div class="radio-list">
					<label class="radio-inline"> <input type="radio" checked="checked" onclick="showTrailPeriod(this.value);"
						value="Free" name="inviteCustomerEnrollment.accountType">Free
					</label>
					<label class="radio-inline"> <input type="radio" onclick="showTrailPeriod(this.value);"
						value="Trail" name="inviteCustomerEnrollment.accountType">Trail
					</label>
					<label class="radio-inline"> <input type="radio" onclick="showTrailPeriod(this.value);"
						value="Live" name="inviteCustomerEnrollment.accountType">Live
					</label>
				</div>
			</div>
		</div>
		<div id="showTrailPeriodDivId" style="display: none;">	
			<div class="form-group">
				<label class="control-label col-md-3"> Start Date : </label>
				<div class="col-md-9">
					<div data-date-format="mm/dd/yyyy" data-date-start-date="+0d"
						class="input-group input-medium date date-picker">
						<input type="text" id="contractStartDate" readonly=""
							class="form-control" onchange="verifyStaffContactDate();"
							tabindex="3" name="inviteCustomerEnrollment.trailStartDate"> <span
							class="input-group-btn">
							<button type="button" class="btn default">
								<i class="fa fa-calendar"></i>
							</button>
						</span>
					</div>
					<span class="help-block">(MM/DD/YYYY)</span>
				</div>
			</div>
							
			<div class="form-group">
				<label class="control-label col-md-3"> End Date : </label>
				<div class="col-md-9">
					<div data-date-format="mm/dd/yyyy" data-date-start-date="+0d"
						class="input-group input-medium date date-picker">
						<input type="text" id="staffContractEndDate" readonly=""
							class="form-control" onchange="verifyStaffContactDate();"
							tabindex="4" name="inviteCustomerEnrollment.trailEndDate"> <span
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
		<div class="form-actions fluid">
			<div class="col-md-6">
				<div class="col-md-offset-6 col-md-9">
					<sj:submit   cssClass="submitBt btn blue" formIds="addSmsProvider"
						value="Submit"  targets="newCustomerDetailsDiv" validate="true"
						onBeforeTopics="formValidationForAddSmsProvider" />
					<s:url id="doCloseSmsProvider" action="ajaxViewNewCustomersDetails" namespace="/masterAdmin"></s:url>
					<sj:a href="%{doCloseSmsProvider}" cssClass="btn default"  targets="mainContentDiv">Cancel</sj:a>
				</div>
			</div>
		</div>
	</div>
</s:form>
<script type="text/javascript">

FormComponents.init();
FormAdvanced.init();

$("input:checkbox, input:radio").uniform();

function showTrailPeriod(accountType)
{
	if("Trail" == accountType)
	{
		$('#showTrailPeriodDivId').show();
	}
	else
	{
		$('#showTrailPeriodDivId').hide();
	}
}

function verifyStaffContactDate() {
	var contractStartDate = $('#contractStartDate').val();
	var staffContractEndDate = $('#staffContractEndDate').val();
	if (isNonEmpty(staffContractEndDate) && isNonEmpty(contractStartDate)) {
		contractStartDate = new Date(contractStartDate);
		staffContractEndDate = new Date(staffContractEndDate);
		if (staffContractEndDate < contractStartDate) {
			$('#staffContractEndDate').val('');
			alert("End Date should be more than start date.");
		}
	}
}

</script>
