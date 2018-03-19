<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/messages.jsp"%>
<span id="startDateSpan" class="<s:property value='startDate'/>"></span>
<span id="endDateSpan" class="<s:property value='endDate'/>"></span>
<span id="toDateSpan" class="<s:property value='toDate'/>"></span>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i> <span class="hidden-title">Final Statements Report</span>
				</div>
			</div>
			<div class="portlet-body">
				<div class="dropdown tabbable tabbable-custom tabbable-full-width">
					<div class="tab-content">
						<s:form action="#" theme="simple" id="#" method="post" cssClass="form-horizontal">
							<div class="form-group">
								<label class="col-md-2 control-label">
									<span class="required">*</span>Select Report Type :
								</label>
								<div class="col-md-9">
									<div class="radio-list">
										<label class="radio-inline"> <input type="radio"
											checked="checked" value="trail"
											onclick="changeReportType(this.value);" name="reportType" id="reportType">
											Trail Balance
										</label> 
										<label class="radio-inline"> <input type="radio"
											value="income" onclick="changeReportType(this.value);"
											name="reportType" id="reportType"> Income & Expenditure
										</label>
										<label class="radio-inline"> <input type="radio"
											value="balance" onclick="changeReportType(this.value);"
											name="reportType" id="reportType"> Balance Sheet
										</label>
									</div>
								</div>
							</div>
						</s:form>
						<div id="trail" style="display: block;">
						<s:form action="ajaxDownloadAllAccountTrailBalance" theme="simple" id="downloadAllAccountTrailBalance" method="post"
								cssClass="form-horizontal" onsubmit="javascript:return validateCashBookDetails();" namespace="/account">
								<div class="form-body">
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-4">
													<span class="required">*</span> Select Financial Year :
												</label>
												<div class="col-md-4">
													<s:select list="financialYearList" id="finYearId" cssClass="form-control input-medium required" name="financialYearVO.id" listKey="id"
														listValue="yearName" headerKey="" headerValue="- Select Financial Year -" theme="simple" />
												</div>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-2 control-label">
											<span class="required">*</span>Select Report Type :
										</label>
										<div class="col-md-9">
											<div class="radio-list">
												<label class="radio-inline"> 
													<input type="radio" checked="checked" value="OTB" onclick="changeTrailBalanceType(this.value);" 
														name="trailBalanceType" id="trailBalanceType" > Opening Trail Balance
												</label> 
												<label class="radio-inline"> 
													<input type="radio" value="RDTB" onclick="changeTrailBalanceType(this.value);" 
														name="trailBalanceType" id="trailBalanceType"> Range Of Dates Trail Balance
												</label>
												<label class="radio-inline"> 
													<input type="radio" value="CBS" onclick="changeTrailBalanceType(this.value);"
														name="trailBalanceType" id="trailBalanceType"> Closing Balance Sheet
												</label>
											</div>
										</div>
									</div>
									<div class="row" id="RDTB">
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-4">
													<span class="required">*</span> From Date :
												</label>
												<div class="col-md-4">
													<div  data-date-format="mm/dd/yyyy" class="input-group input-medium date date-picker" data-date-end-date="+0d">
														<input type="text" id="startDate" readonly="readonly" name="startDate" onchange="feeDatesValidation()"
															class="required form-control input-medium" />
														<span class="input-group-btn"> 
														<button type="button" class="btn default"><i class="fa fa-calendar"></i></button> </span>
													</div>
													<span class="help-block">(MM/DD/YYYY)</span>
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-4">
													<span class="required">*</span> To Date :
												</label>
												<div class="col-md-4">
													<div data-date-format="mm/dd/yyyy" class="input-group input-medium date date-picker" data-date-end-date="+0d">
														<input type="text" id="endDate" readonly="readonly" name="endDate" onchange="feeDatesValidation()"
															class="required form-control input-medium" />
														<span class="input-group-btn">
														<button type="button" class="btn default"><i class="fa fa-calendar"></i></button> </span>
													</div>
													<span class="help-block">(MM/DD/YYYY)</span>
												</div>
											</div>
										</div>
									</div>	
									<div class="form-actions fluid">
										<div class="col-md-offset-3 col-md-9">
											<s:submit type="submit small" value="Generate Excel" cssClass="submitBt btn blue long"
												title="generate report" />
										</div>
									</div>
								</div>
							</s:form>
							</div>
							<div id="income">
									<s:form action="ajaxDownloadIncomeAndExpAccountWiseDetails" theme="simple" id="downloadIncomeAndExpAccountWiseDetails" method="post"
										cssClass="form-horizontal" onsubmit="javascript:return validateIncomeDetails();" namespace="/account">
								<s:hidden id="accountIds" name="tempString" />
								<div class="form-body">
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-4">
													<span class="required">*</span> Select Financial Year :
												</label>
												<div class="col-md-4">
													<s:select list="financialYearList" id="incomeFinYearId" cssClass="form-control input-medium required" name="financialYearVO.id" listKey="id"
														listValue="yearName" headerKey="" headerValue="- Select Financial Year -" theme="simple" />
												</div>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-2 control-label">
											<span class="required">*</span>Select Report Type :
										</label>
										<div class="col-md-9">
											<div class="radio-list">
												<label class="radio-inline"> <input type="radio"
													checked="checked" value="OS"
													onclick="changeTrailBalanceType(this.value);" name="incomeAndExpType" id="incomeAndExpType">
														Opening Statement
												</label> 
												<label class="radio-inline"> <input type="radio"
													value="SBRD" onclick="changeTrailBalanceType(this.value);"
													name="incomeAndExpType" id="incomeAndExpType"> Statement Between Range Of Dates
												</label>
												<label class="radio-inline"> <input type="radio"
													value="YES" onclick="changeTrailBalanceType(this.value);"
													name="incomeAndExpType" id="incomeAndExpType"> Year End Statement
												</label>
											</div>
										</div>
									</div>
									<div class="row" id="SBRD">
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-4">
													<span class="required">*</span> From Date :
												</label>
												<div class="col-md-4">
													<div  data-date-format="mm/dd/yyyy" class="input-group input-medium date date-picker" data-date-end-date="+0d">
														<input type="text" id="incomeStartDate" readonly="readonly" name="startDate" onchange="incomeDatesValidation()"
															class="required form-control input-medium" />
														<span class="input-group-btn"> 
														<button type="button" class="btn default"><i class="fa fa-calendar"></i></button> </span>
													</div>
													<span class="help-block">(MM/DD/YYYY)</span>
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-4">
													<span class="required">*</span> To Date :
												</label>
												<div class="col-md-4">
													<div data-date-format="mm/dd/yyyy" class="input-group input-medium date date-picker" data-date-end-date="+0d">
														<input type="text" id="incomeEndDate" readonly="readonly" name="endDate" onchange="incomeDatesValidation()"
															class="required form-control input-medium" />
														<span class="input-group-btn">
														<button type="button" class="btn default"><i class="fa fa-calendar"></i></button> </span>
													</div>
													<span class="help-block">(MM/DD/YYYY)</span>
												</div>
											</div>
										</div>
									</div>
									<div class="form-actions fluid">
										<div class="col-md-offset-3 col-md-9">
											<s:submit type="submit small" value="Generate Excel" cssClass="submitBt btn blue long"
												title="generate report" />
										</div>
									</div>
								</div>
							</s:form>
							</div>
							<div id="balance">
									<s:form action="ajaxDownloadBalanceSheetAccountWiseDetails" theme="simple" id="downloadBalanceSheetAccountWiseDetails" method="post"
										cssClass="form-horizontal" onsubmit="javascript:return validateAccountDetails();" namespace="/account">
								<s:hidden id="accountIds" name="tempString" />
								<div class="form-body">
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-4">
													<span class="required">*</span> Select Financial Year :
												</label>
												<div class="col-md-4">
													<s:select list="financialYearList" id="balFinYearId" cssClass="form-control input-medium required" name="financialYearVO.id" listKey="id"
														listValue="yearName" headerKey="" headerValue="- Select Financial Year -" theme="simple" />
												</div>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-2 control-label">
											<span class="required">*</span>Select Report Type :
										</label>
										<div class="col-md-9">
											<div class="radio-list">
												<label class="radio-inline"> <input type="radio"
													checked="checked" value="BOS"
													onclick="changeTrailBalanceType(this.value);" name="balanceType" id="balanceType">
														Opening Statement
												</label> 
												<label class="radio-inline"> <input type="radio"
													value="BSBR" onclick="changeTrailBalanceType(this.value);"
													name="balanceType" id="balanceType"> Statement Between Range Of Dates
												</label>
												<label class="radio-inline"> <input type="radio"
													value="BYS" onclick="changeTrailBalanceType(this.value);"
													name="balanceType" id="balanceType"> Year End Statement
												</label>
											</div>
										</div>
									</div>
									<div class="row" id="BSBR">
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-4">
													<span class="required">*</span> From Date :
												</label>
												<div class="col-md-4">
													<div  data-date-format="mm/dd/yyyy" class="input-group input-medium date date-picker" data-date-end-date="+0d">
														<input type="text" id="balStartDate" readonly="readonly" name="startDate" onchange="balanceDatesValidation()"
															class="required form-control input-medium" />
														<span class="input-group-btn"> 
														<button type="button" class="btn default"><i class="fa fa-calendar"></i></button> </span>
													</div>
													<span class="help-block">(MM/DD/YYYY)</span>
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-4">
													<span class="required">*</span> To Date :
												</label>
												<div class="col-md-4">
													<div data-date-format="mm/dd/yyyy" class="input-group input-medium date date-picker" data-date-end-date="+0d">
														<input type="text" id="balEndDate" readonly="readonly" name="endDate" onchange="balanceDatesValidation()"
															class="required form-control input-medium" />
														<span class="input-group-btn">
														<button type="button" class="btn default"><i class="fa fa-calendar"></i></button> </span>
													</div>
													<span class="help-block">(MM/DD/YYYY)</span>
												</div>
											</div>
										</div>
									</div>
									<div class="form-actions fluid">
										<div class="col-md-offset-3 col-md-9">
											<s:submit type="submit small" value="Generate Excel" cssClass="submitBt btn blue long"
												title="generate report" />
										</div>
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
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/ems.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	$("input:checkbox, input:radio:not('.toggle')").uniform();
	var startDate = $('span#startDateSpan').attr("class");
	var endDate = $('span#endDateSpan').attr("class");
	dateRestrictionWithinAcademicYear(startDate,endDate);
	FormComponents.init();
	$("input:checkbox, input:radio:not('.toggle')").uniform();
	var reportType=$('#reportType').val();
	changeReportType(reportType);
	var trailBalanceType=$('#trailBalanceType').val();
	changeTrailBalanceType(trailBalanceType);
	
});
function feeDatesValidation(){
	var startDate = $('input[name=startDate]').val();//$('#startDate').val();
	var endDate = $('input[name=endDate]').val();//$('#endDate').val();
	if (isNonEmpty(startDate) || isNonEmpty(endDate)) {
		validateDate(startDate,endDate);
	}
}
function incomeDatesValidation(){
	var startDate = $('#incomeStartDate').val();
	var endDate = $('#incomeEndDate').val();
	if (isNonEmpty(startDate) || isNonEmpty(endDate)) {
		validateDate(startDate,endDate);
	}
}
function balanceDatesValidation(){
	var startDate = $('#balStartDate').val();
	var endDate = $('#balEndDate').val();
	if (isNonEmpty(startDate) || isNonEmpty(endDate)) {
		validateDate(startDate,endDate);
	}
}
function validateDate(startDate,endDate){
	var startDate = Date.parse(startDate);
	var endDate = Date.parse(endDate);
	if (endDate < startDate) {
		alert("Your To Date is always greater than From Date.");
		$('input[name=endDate]').val("");
	}
}
function validateCashBookDetails() {
	var trailBalanceType= $('input[name=trailBalanceType]:radio:checked').val()
	var finYearId = $('#finYearId').val();
	if(finYearId ==''){
		alert("Please select finacial year");
		return false;
	}else{
		if (trailBalanceType == 'RDTB') {
			var startDate = $('#startDate').val();
			var endDate = $('#endDate').val();
			return validateBetweenDate(startDate,endDate,finYearId);
		}
	}
}
function changeReportType(reportType) {
	if (reportType == 'trail') {
		$('#trail').show();
		$('#income').hide();
		$('#balance').hide();
		$('.close').click();
		$('input[name=startDate]').val("");
		$('input[name=endDate]').val("");
		$("input[name=trailBalanceType][value=OTB]").prop('checked', 'checked');
	}else if (reportType == 'income') {
		$('.close').click();
		$('#trail').hide();
		$('#income').show();
		$('#balance').hide();
		$('input[name=startDate]').val("");
		$('input[name=endDate]').val("");
		$("input[name=trailBalanceType][value=OS]").prop('checked', 'checked');
	}else if (reportType == 'balance') {
		$('.close').click();
		$('#trail').hide();
		$('#income').hide();
		$('#balance').show();
		$('#startDate').val("");
		$('#endDate').val("");
		$("input[name=trailBalanceType][value=BOS]").prop('checked', 'checked');
	}
}
function changeTrailBalanceType(reportType) {
	if (reportType == 'RDTB') {
		$('#RDTB').show();
		$('.close').click();
	}else if(reportType == 'SBRD'){
		$('#SBRD').show();
		$('.close').click();
	}else if(reportType == 'BSBR'){
		$('#BSBR').show();
		$('.close').click();
	}
	else{
		$('.close').click();
		$('#RDTB').hide();
		$('#SBRD').hide();
		$('#BSBR').hide();
	}
}

function validateAccountDetails() {
	var balanceType=$('#balanceType').val();
	var finYearId = $('#balFinYearId').val();
	if(finYearId ==''){
		alert("Please select finacial year");
		return false;
	}else{
		if('BSBR'==trailBalanceType){
			var startDate = $('#balStartDate').val();
			var endDate = $('#balEndDate').val();
			return validateBetweenDate(startDate,endDate,finYearId);
		}
	}
}
function validateIncomeDetails() {
	var incomeAndExpType=$("input[name='incomeAndExpType']:checked").val();
	var finYearId = $('#incomeFinYearId').val();
	if(finYearId ==''){
		alert("Please select finacial year");
		return false;
	}else{
		if('SBRD'==incomeAndExpType){
			var startDate = $('#incomeStartDate').val();
			var endDate = $('#incomeEndDate').val();
			return validateBetweenDate(startDate,endDate,finYearId);
		}
	}
}
function validateBetweenDate(startDate,endDate,finYearId){
	if(finYearId ==''){
		alert("Please select finacial year");
		return false;
	}
	else if (startDate == '' && endDate == '') {
		alert("Please select From and To date.");
		return false;
	} else if (startDate == '') {
		alert("Please select From date.");
		return false;
	} else if (endDate == '') {
		alert("Please select To date.");
		return false;
	}
}

</script>