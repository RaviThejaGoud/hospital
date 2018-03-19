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
					<i class="fa fa-globe"></i> <span class="hidden-title">Transactions Report</span>
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
											checked="checked" value="cash"
											onclick="changeReportType(this.value);" name="reportType" id="reportType">
											Cash Book
										</label> <label class="radio-inline"> <input type="radio"
											value="ledger" onclick="changeReportType(this.value);"
											name="reportType" id="reportType"> Ledger Book
										</label>
									</div>
								</div>
							</div>
						</s:form>
						<div id="cash" style="display: block;">
						<s:form action="ajaxDownloadAccountTransactions" theme="simple" id="downloadAccountTransactions" method="post"
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
										<br/>
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-4">
													<span class="required">*</span> From Date :
												</label>
												<div class="col-md-4">
													<div  data-date-format="mm/dd/yyyy" class="input-group input-medium date date-picker" data-date-end-date="+0d">
														<input type="text" id="cashFromtDate" readonly="readonly" name="startDate" onchange="feeDatesValidation()"
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
														<input type="text" id="cashToDate" readonly="readonly" name="endDate" onchange="feeDatesValidation()"
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
							<div id="ledger">
									<s:form action="ajaxDownloadLedgerAccountWiseDetails" theme="simple" id="downloadLedgerAccountWiseDetails" method="post"
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
													<s:select list="financialYearList" id="ledgerFinYearId" cssClass="form-control input-medium required" name="financialYearVO.id" listKey="id"
														listValue="yearName" headerKey="" headerValue="- Select Financial Year -" theme="simple" />
												</div>
											</div>
										</div>
										</div>
										<br/>
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label col-md-4">
													<span class="required">*</span> From Date :
												</label>
												<div class="col-md-4">
													<div  data-date-format="mm/dd/yyyy" class="input-group input-medium date date-picker" data-date-end-date="+0d">
														<input type="text" id="ledgerFromtDate" readonly="readonly" name="startDate" onchange="ledgerDatesValidation()"
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
														<input type="text" id="ledgerToDate" readonly="readonly" name="endDate" onchange="ledgerDatesValidation()"
															class="required form-control input-medium" />
														<span class="input-group-btn">
														<button type="button" class="btn default"><i class="fa fa-calendar"></i></button> </span>
													</div>
													<span class="help-block">(MM/DD/YYYY)</span>
												</div>
											</div>
										</div>
									</div>
									<s:if test="%{financialAccountDetailsList!=null && !financialAccountDetailsList.isEmpty()}">
										<div class="form-group">
											<label class="col-md-2 control-label">
												<span class="required">*</span>Select All Account :
											</label>
											<div class="col-md-9">
												<input type="checkbox" name="" value=""
															onClick="checkAllClasses()" class="checkbox allClasses">
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-2 control-label">
												<span class="required">*</span>Select Accounts :
											</label>
											<div class="col-md-9">
												<s:checkboxlist name="chkBoxSelectedIds" list="financialAccountDetailsList"
													listKey="id" listValue="AccountNameAndNum" theme="ems" />
											</div>
										</div>
									</s:if>
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
	//FormComponents.init();
	$("input:checkbox, input:radio:not('.toggle')").uniform();
	var startDate = $('span#startDateSpan').attr("class");
	var endDate = $('span#endDateSpan').attr("class");
	dateRestrictionWithinAcademicYear(startDate,endDate);
	FormComponents.init();
	var reportType=$('#reportType').val();
	changeReportType(reportType);
	
	 $("input[name=chkBoxSelectedIds]").click(function() {
			if ($("input[name=chkBoxSelectedIds]:unchecked").length > 0) {
			   $(".allClasses").parent('span').removeClass("checked");
				$(".allClasses").attr("checked", false);
			} else {
			    $(".allClasses").parent('span').addClass("checked");
				$(".allClasses").attr("checked", true);
			}
		});
});
function feeDatesValidation(){
	var startDate = $('#cashFromtDate').val();
	var endDate = $('#cashToDate').val();
	if (isNonEmpty(startDate) || isNonEmpty(endDate)) {
		startDate = Date.parse(startDate);
		endDate = Date.parse(endDate);
		if (endDate < startDate) {
			alert("To Date is always greater than From Date.");
			$('#cashToDate').val("");
		}
	}
}
function ledgerDatesValidation(){
	var startDate = $('#ledgerFromtDate').val();
	var endDate = $('#ledgerToDate').val();
	if (isNonEmpty(startDate) || isNonEmpty(endDate)) {
		startDate = Date.parse(startDate);
		endDate = Date.parse(endDate);
		if (endDate < startDate) {
			alert("To Date is always greater than From Date.");
			$('#ledgerToDate').val("");
		}
	}
}
function validateCashBookDetails() {
	var startDate = $('#cashFromtDate').val();
	var endDate = $('#cashToDate').val();
	var finYearId = $('#finYearId').val();
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
function changeReportType(reportType) {
	if (reportType == 'cash') {
		$('#cash').show();
		$('#ledger').hide();
		$('.close').click();
	}else if (reportType == 'ledger') {
		$('.close').click();
		$('#cash').hide();
		$('#ledger').show();
	}
}
function validateAccountDetails() {
	var startDate = $('#ledgerFromtDate').val();
	var endDate = $('#ledgerToDate').val();
	var finYearId = $('#ledgerFinYearId').val();
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
	} else {
		if ($("input[name=chkBoxSelectedIds]:checked").length > 0) {
			generateAccountIds();
			return true;
		} else {
			alert("Please select at least one Account");
			return false;
		}
	}
}
function generateAccountIds() {
	if ($("input[name=chkBoxSelectedIds]:checked").length > 0) {
		var accountIds = $("input[name=chkBoxSelectedIds]:checked");
		var selectedAccountIds = '';
		if (accountIds.length > 0) {
			selectedAccountIds = '(';
			for ( var i = 0; i < accountIds.length; i++) {
				selectedAccountIds += accountIds[i].value + ', ';
			}
			selectedAccountIds += '0)';
		}
		$("#accountIds").val(selectedAccountIds);
		return true;
	} else if ($("input[name=chkBoxSelectedIds]:checked").length == 0) {
		alert("Please select at least one Account");
		return false;
	} else {
		return false;
	}
}
function checkAllClasses() {
	if ($(".allClasses").is(':checked')){
	    $("[name='chkBoxSelectedIds']").each(function(){
		   $(this).parent('span').addClass('checked');
		    $(this).attr("checked", "true");
		});
	}
	else{
	 $("[name='chkBoxSelectedIds']").each(function(){
		   $(this).parent('span').removeClass('checked');
		    $(this).removeAttr("checked");
		});
	}	
}
</script>