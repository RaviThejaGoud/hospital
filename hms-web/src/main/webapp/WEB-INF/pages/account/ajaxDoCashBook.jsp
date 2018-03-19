<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/default/ems.js"></script>
<span id="startDateSpan" class="<s:property value='startDate'/>"></span>
<span id="endDateSpan" class="<s:property value='endDate'/>"></span>
<span id="toDateSpan" class="<s:property value='toDate'/>"></span>
<s:form action="ajaxSaveCashBook" theme="simple" id="financialCashBookForm" method="post" cssClass="form-horizontal"  namespace="/account">
	<s:hidden name="cashBookVo.entryType" value="M"></s:hidden>
	<s:hidden name="cashBookVo.bookType" id="bookType" value="C"></s:hidden>
	<div class="form-horizontal">
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4"><span
						class="required">*</span>Transaction Date : </label>
					<div class="col-md-5">
						<div data-date-format="mm/dd/yyyy"  data-date-end-date="+0d"
									class="input-group input-medium date date-picker">
									<input id="transactionDate" name="cashBookVo.transactionDate" readonly="readonly" value='<s:property value="cashBookVo.transactionDate"/>'
										 type="text" class="required form-control input-medium fdate">
									<span class="dateInput input-group-btn">
										<button type="button" class="btn default">
											<i class="fa fa-calendar"></i>
										</button> </span>
								</div>
						<span class="help-block">(MM/DD/YYYY)</span>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4"><span
						class="required">*</span>Account Name : </label>
					<div class="col-md-5">
						<s:select id="accountId" list="financialAccountDetailsList" cssClass="form-control required" listKey="id"
							listValue="AccountNameAndNum" headerValue="-Select Account-" headerKey="" name="cashBookVo.accountId" tabindex="2" />
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4"><span
						class="required">*</span>Voucher Number : </label>
					<div class="col-md-5">
						<sj:textfield name="cashBookVo.vocherNumber" id="voucherNumber" tabindex="3" cssClass="form-control required voucherNumberStr"></sj:textfield>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4"><span
						class="required">*</span>Narration : </label>
					<div class="col-md-5">
						<sj:textfield name="cashBookVo.narration" id="narration" tabindex="4" cssClass="form-control required"></sj:textfield>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4"><span
						class="required">*</span>Debit/Credit : </label>
					<div class="col-md-5">
						<s:select list="#{'D':'Debit','C':'Credit'}" cssClass="form-control required textfield" required="true"
							name="cashBookVo.transactionType" headerKey="" tabindex="5" id="transactionType">
						</s:select>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4"><span
						class="required">*</span>Amount : </label>
					<div class="col-md-5">
						<sj:textfield name="cashBookVo.amount" id="amount" tabindex="6" cssClass="form-control required numericDot"></sj:textfield>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<div class="form-group">
					<label class="col-md-2 control-label">
						Payment Type :
					</label>
					<div class="radio-list">
						<label class="radio-inline">
							<input type="radio" value="C" name="cashBookVo.paymentType"
								onclick="javascript:paymentTypeMethodChange(this.value)" checked>
							Cash 
						</label>
						<label class="radio-inline">
							<input type="radio" value="D" name="cashBookVo.paymentType"
								id="ddForm"
								onclick="javascript:paymentTypeMethodChange(this.value)">
							DD
						</label>
						<label class="radio-inline">
							<input type="radio" value="CH" name="cashBookVo.paymentType"
								id="cheque"
								onclick="javascript:paymentTypeMethodChange(this.value)">
							Cheque
						</label>
						<label class="radio-inline">
							<input type="radio" value="CS" name="cashBookVo.paymentType"
								id="cardSwipe"
								onclick="javascript:paymentTypeMethodChange(this.value)">
							Card Swipe
						</label>
						<label class="radio-inline">
							<input type="radio" value="NEFT" name="cashBookVo.paymentType"
								id="NEFT"
								onclick="javascript:paymentTypeMethodChange(this.value)">
							NEFT
						</label>
						<label class="radio-inline">
							<input type="radio" value="BD" name="cashBookVo.paymentType"
								id="bankDeposit"
								onclick="javascript:paymentTypeMethodChange(this.value)">
							Bank Deposit
						</label>
					</div>
				</div>
			</div>
		</div>
		<div class="row" style="display: none;" id="inputboxhideText">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4">
						<span class="required">*</span> Enter DD Number :
					</label>
					<div class="col-md-6">
						<sj:textfield name="cashBookVo.ddNumber" id="ddNumberFee"
							value="DD Number"
							cssClass="required numeric form-control input-medium defaultValue"
							maxlength="15"></sj:textfield>
					</div>
				</div>
			</div>
		</div>
		<div class="row" style="display: none;" id="checkinputboxhideText">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4">
						<span class="required">*</span> Enter Cheque Number :
					</label>
					<div class="col-md-6">
						<sj:textfield name="cashBookVo.chequeNumber" id="chequeNumber"
							value="Number"
							cssClass="required numeric form-control input-medium defaultValue"
							maxlength="15"></sj:textfield>
					</div>
				</div>
			</div>
		</div>
		<div class="row" style="display: none;" id="neftinputboxhideText">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4">
						<span class="required">*</span> Transaction Number / UTR No :
					</label>
					<div class="col-md-6">
						<sj:textfield name="cashBookVo.transactionNumber" id="transactionNumber"
							value="Transaction Number"
							cssClass="required numeric form-control input-medium defaultValue"
							maxlength="20"></sj:textfield>
					</div>
				</div>
			</div>
		</div>
		<div class="row" style="display: none;" id="accountboxhideText">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4">
						<span class="required">*</span> Account Number :
					</label>
					<div class="col-md-6">
						<sj:textfield name="cashBookVo.accountNumber" id="accountNumber"
							value="Account Number"
							cssClass="required numeric form-control input-medium defaultValue"
							maxlength="20"></sj:textfield>
					</div>
				</div>
			</div>
		</div>
		<div class="row" style="display: none;" id="bankinputboxhideText">
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4">
						<!--<span class="required">*</span>-->
						Select Bank Name :
					</label>
					<div class="col-md-6">
						<s:select list="objectList" id="bankName"
							name="cashBookVo.bankId" theme="simple"
							listValue="bankName" listKey="id"
							cssClass="required form-control input-medium" />
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group">
					<label class="control-label col-md-4">
						<!--<span class="required">*</span>-->
						Bank Branch Name :
					</label>
					<div class="col-md-6">
						<sj:textfield name="cashBookVo.branchName" id="branchName"
							value="Enter Bank Branch Name"
							cssClass="form-control input-medium defaultValue" maxlength="25"></sj:textfield>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="form-actions fluid">
		<div class="col-md-offset-2 col-md-9">
			<sj:submit value="Submit" cssClass="submitBt btn blue" onBeforeTopics="cashBookIdFormValidation" resetForm="true"
					formIds="financialCashBookForm" targets="mainContentDiv" indicator="indicator" validate="true"/>
			<s:url id="createAccountMaster" action="ajaxViewCashBookDetails" namespace="/account" />
			<sj:a href="%{createAccountMaster}" targets="mainContentDiv" tabindex="10" cssClass="btn default">Cancel</sj:a>
		</div>
	</div>
</s:form>
<script type="text/javascript">
	UIExtendedModals.init();
	var now = new Date();
	var startDate = $('span#startDateSpan').attr("class");
	var endDate = $('span#endDateSpan').attr("class");
	dateRestrictionWithinAcademicYear(startDate,endDate);
	FormComponents.init();
	$("input:checkbox, input:radio:not('.toggle')").uniform();
	var day = ("0" + now.getDate()).slice(-2);
	var month = ("0" + (now.getMonth() + 1)).slice(-2);
	var today = (month) + "/" + (day) + "/" + now.getFullYear();
	$(document).ready(function(){
		$('.numeric').numeric();
		$('.numericDot').numeric( {allow : "."});
		
		$('#transactionDate').val(today);
	});
	$("#voucherNumber").autoCheck("${pageContext.request.contextPath}/account/ajaxVouchernumberAvailableOrNot.do",
	{
		minChars : 1,
		min : "no",
	});
	$.subscribe('cashBookIdFormValidation',function(event, data) {
		var voucherNumberStr = $('input.voucherNumberStr').parents('div').next('div').find('p.word-taken').html();
		if (voucherNumberStr == 'Already taken!!!') {
			event.originalEvent.options.submit = false;
		}
	});
	function paymentTypeMethodChange(clickButton) {
		if (clickButton == 'D') {
			$("#bookType").val("B");
			$("#inputboxhideText").show();
			$("#checkinputboxhideText").hide();
			$("#bankinputboxhideText").show();
			$('#neftinputboxhideText').hide();
			$('#accountboxhideText').hide();
		} else if (clickButton == 'CH') {
			$("#bookType").val("B");
			$("#checkinputboxhideText").show();
			$("#inputboxhideText").hide();
			$("#bankinputboxhideText").show();
			$('#neftinputboxhideText').hide();
			$('#accountboxhideText').hide();
		} else if (clickButton == 'C') {
			$("#bookType").val("C");
			$("#inputboxhideText").hide();
			$("#checkinputboxhideText").hide();
			$("#bankinputboxhideText").hide();
			$('#neftinputboxhideText').hide();
			$('#accountboxhideText').hide();
		}else if (clickButton == 'BD') {
			$("#bookType").val("B");
			$("#inputboxhideText").hide();
			$("#checkinputboxhideText").hide();
			$("#bankinputboxhideText").show();
			$('#neftinputboxhideText').hide();
			$('#accountboxhideText').show();
		}else if (clickButton == 'CS' || clickButton == 'NEFT') {
			$("#bookType").val("B");
			$("#inputboxhideText").hide();
			$("#checkinputboxhideText").hide();
			$("#bankinputboxhideText").hide();
			$('#neftinputboxhideText').show();
			$('#accountboxhideText').hide();
		}
	}
</script>