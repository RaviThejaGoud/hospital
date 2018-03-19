<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/messages.jsp"%>
	<s:form action="ajaxCreateFinancialYear" theme="simple" id="addOrEditFinancialYear" method="post" cssClass="form-horizontal" namespace="/admin">
		<s:hidden name="financialYear.id" value="%{financialYear.id}"></s:hidden>
		<h4 class="bold pageTitle">
			<s:if test="%{financialYear.id != 0}">
				Update Financial Year
			</s:if>
			<s:else>
				Add Financial Year
			</s:else>
		</h4>
		<hr/>
	<div class="form-body">
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4"><span class="required">*</span> Start Date : </label>
					<div class="col-md-5">
						<div data-date-format="mm/dd/yyyy"
							class="input-group input-medium date date-picker">
							<input type="text" id="contractStartDate" readonly="" value='<s:property value="financialYear.startDateStr"/>'
								class="form-control required" onchange="verifyStaffContactDate();"
								tabindex="1" name="financialYear.startDate"> <span
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
					<label class="col-md-4 control-label">
						<span class="required">*</span>End Date :
					</label>
					<div class="col-md-5">
						<div data-date-format="mm/dd/yyyy"
							class="input-group input-medium date date-picker">
							<input type="text" id="staffContractEndDate" readonly="" value='<s:property value="financialYear.endDateStr"/>'
								class="form-control required" onchange="verifyStaffContactDate();"
								tabindex="2" name="financialYear.endDate"> <span
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
					<label class="control-label col-md-4"> Description : </label>
					<div class="col-md-5">
						<sj:textarea rows="3" cols="20" name="financialYear.description"
							maxCharsData="1000" tabindex="3"
							cssClass="form-control word_count"></sj:textarea>
						<span class="help-block">
							<div class="counter"></div>
						</span>
					</div>
				</div>
			</div>
		</div>
		<div class="form-actions fluid">
			<div class="col-md-6">
				<div class="col-md-offset-4 col-md-12">
					<sj:submit   cssClass="submitBt btn blue" value="Submit"
						 validate="true"  formIds="addOrEditFinancialYear"
						indicator="indicator" targets="financialYearDivId" />
						
						<s:url id="doCancel" action="ajaxFinancialYearHome"
					includeParams="all" namespace="/admin"></s:url>
				<sj:a href="%{doCancel}" cssClass="btn default"
					indicator="indicator" targets="mainContentDiv" button="false">Cancel</sj:a>
				</div>
			</div>
		</div>
	</div>
</s:form>
<script type="text/javascript">
changePageTitle("Add Peticular Type");

$(document).ready(function() {
	FormComponents.init();
	FormAdvanced.init();
});

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