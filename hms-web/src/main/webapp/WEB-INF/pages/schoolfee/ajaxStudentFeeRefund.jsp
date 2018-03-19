<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/messages.jsp"%>
<s:if test="%{(tempList != null && !tempList.isEmpty()) || (studentTransportTermsList != null && !studentTransportTermsList.isEmpty())}">
	<h4 class="bold pageTitle">
		Student Fee Refund 
	</h4>
	
	<s:form action="ajaxPayStudentRefundFee" id="addStudentPaymentFee"
						cssClass="form-horizontal" method="post" theme="simple"
						name="myform" namespace="/schoolfee">
						<div class="row" >
	<div class="col-md-6">
		<div class="form-group">
			<label class="control-label col-md-4">
				Student Name :
			</label>
			<div class="col-md-6" style="margin: 4px 0px 0px 0px">
				<b><s:property value="student.studentName" /> &nbsp; ( <s:property
					value="student.classSection.classAndSection" /> )</b>
			</div>
		</div>
	</div>
	<div class="col-md-6">
		<div class="form-group">
			<label class="control-label col-md-4">
				 Receipt No :
			</label>
			<div class="col-md-6" style="margin: 4px 0px 0px 0px">
				<b><s:property value="tempId" /></b>
			</div>
		</div>
	</div>
</div>
	<s:set name="schoolTermsId" value=""></s:set>
	<s:set name="schoolFeeSettingId" value=""></s:set>
	<s:set name="feeParticularId" value=""></s:set>
	<div class="row">
			<div class="col-md-12">
				<div class="form-group">
					<label class="col-md-2 control-label">
						Payment Type :
					</label>
					<div class="radio-list">
						<label class="radio-inline" style="margin: 4px 0px 0px 10px;">
							<input type="radio" value="C" name="studentFeeRefund.paymentMode"
								onclick="javascript:paymentTypeMethodChange(this.value)" checked>
							Cash 
						</label>
						<label class="radio-inline" style="margin: 4px 0px 0px 10px;">
							<input type="radio" value="CH" name="studentFeeRefund.paymentMode"
								id="chForm"
								onclick="javascript:paymentTypeMethodChange(this.value)">
							Cheque
						</label>
						<!-- <label class="radio-inline">
							<input type="radio" value="NEFT" name="studentFeeRefund.paymentType"
								id="neftForm"
								onclick="javascript:paymentTypeMethodChange(this.value)">
							NEFT
						</label> -->
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
						<sj:textfield name="studentFeeRefund.chequeNumber" id="chequeNumber"
							value="Number"
							cssClass="required numeric form-control input-medium defaultValue"
							maxlength="15"></sj:textfield>
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
							name="studentFeeRefund.bankMaster.id" theme="simple"
							listValue="bankName" listKey="id"
							cssClass="required form-control input-medium" />
					</div>
				</div>
			</div>
			<div class="col-md-6" id="bankibranchnputboxhideText">
				<div class="form-group">
					<label class="control-label col-md-4">
						<!--<span class="required">*</span>-->
						Bank Branch Name :
					</label>
					<div class="col-md-6">
						<sj:textfield name="studentFeeRefund.branchName" id="branchName"
							value="Enter Bank Branch Name"
							cssClass="form-control input-medium defaultValue" maxlength="25"></sj:textfield>
					</div>
				</div>
			</div>
		</div>
	<table class="table table-striped table-bordered table-hover table-full-width dataTable" id="sample_2">
		<thead>
			<tr>
				<td>
				<strong> Particular Name</strong>
				</td>
				<td>
				<strong> Total Amount</strong>
				</td>
				<td>
				<strong> Paid Amount</strong>
				</td>
				<td>
				<strong> Discount Amount</strong>
				</td>
				<s:if test="%{tempBoolean}">
				<td>
					<strong> Concession Amount</strong>
				</td>
				</s:if>
				<td>
				<strong>  Balance Amount </strong>
				</td>
				<!-- <td>
				<strong> Payment Status</strong>
				</td> -->
			</tr>
		</thead>
		<tbody>
		<s:if test="%{tempList != null && !tempList.isEmpty()}">
			<s:iterator value="tempList">
				<s:set name="schoolTermsId" value="%{schoolTermId}"></s:set>
				<tr>
					<td colspan="7">
						<s:if test="%{feeSettingId != #schoolFeeSettingId}">
							<div class="grid_12 row">
								<h4>
									<center>
										<s:property value="settingName" />
									</center>
								</h4>
							</div>
						</s:if>
					</td>
				</tr>
				<s:set name="termPayableAmount" value="payableAmount"></s:set>
				<tr>
					<td colspan="7">
						<center>
							<strong> <span id='<s:property value="schoolTermId" />'><s:property
										value="termName" /> </span> </strong>
						</center>
					</td>
				</tr>
				<s:iterator value="pendingStudentList">
					<tr>
						<td>
							<s:property value="feeType" />
						</td>
						<td>
							<s:property value="feeAmount" />
						</td>
						<td class="paidAmount">
							<s:property value="paymentAmount" />
						</td>
						<td>
							<s:property value="discountAmount" />
						</td>
						<s:if test="%{tempBoolean}">
						<td>
							<s:property value="concessionAmount" />
						</td>
						</s:if>
						<td>
							<s:property value="payableAmount" />
						</td>
						
						<%-- <td>
							<s:if test='%{deleteStatus == "C"}'>
								<font color="red"><b>Not Paid</b> </font>
							</s:if>
							<s:elseif test="%{(paymentAmount+discountAmount+paymentConcessionAmount) == feeAmount}">
								<font color="green"><b>Paid</b> </font>
							</s:elseif>
							<s:elseif test="%{(paymentAmount > 0) && payableAmount > 0 }">
								<font color="green"><b>Partial Payment</b> </font>
							</s:elseif>
							<s:else>
								<font color="red"><b>Not Paid</b> </font>
							</s:else>
						</td> --%>
					</tr>
				</s:iterator>
				<s:set name="schoolFeeSettingId" value="%{feeSettingId}"></s:set>
			</s:iterator>
			</s:if>
			<s:if test="%{studentTransportTermsList != null && !studentTransportTermsList.isEmpty()}">
			<s:iterator value="studentTransportTermsList">
				<s:set name="schoolTermsId" value="%{schoolTermId}"></s:set>
				<tr>
					<td colspan="7">
						<s:if test="%{feeSettingId != #schoolFeeSettingId}">
							<div class="grid_12 row">
								<h4>
									<center>
										<s:property value="settingName" />
									</center>
								</h4>
							</div>
						</s:if>
					</td>
				</tr>
				<s:set name="termPayableAmount" value="payableAmount"></s:set>
				<tr>
					<td colspan="7">
						<center>
							<strong> <span id='<s:property value="schoolTermId" />'><s:property
										value="termName" /> </span> </strong>
						</center>
					</td>
				</tr>
				<s:if test="%{studentTransportFeeList != null && !studentTransportFeeList.isEmpty()}">
				<s:iterator value="studentTransportFeeList">
					<tr>
						<td>
							<s:property value="feeType" />
						</td>
						<td>
							<s:property value="feeAmount" />
						</td>
						<td class="paidAmount">
							<s:property value="paymentAmount" />
						</td>
						<td>
							<s:property value="discountAmount" />
						</td>
						<s:if test="%{tempBoolean}">
						<td>
							<s:property value="concessionAmount" />
						</td>
						</s:if>
						<td>
							<s:property value="payableAmount" />
						</td>
						
						<%-- <td>
							<s:if test="%{(paymentAmount+discountAmount+paymentConcessionAmount) == feeAmount}">
								<font color="green"><b>Paid</b> </font>
							</s:if>
							<s:elseif test="%{(paymentAmount > 0) && payableAmount > 0 }">
								<font color="green"><b>Partial Payment</b> </font>
							</s:elseif>
							<s:else>
								<font color="red"><b>Not Paid</b> </font>
							</s:else>
						</td> --%>
					</tr>
				</s:iterator>
				</s:if>
				<s:set name="schoolFeeSettingId" value="%{feeSettingId}"></s:set>
			</s:iterator>
			</s:if>
			</tbody>
			</table>
		
						<s:hidden name="studentNumber" value='%{student.id}' />
						<s:hidden name="tempId1" value="%{student.academicYearId}" />
						<s:hidden name="description" value="%{description}" />
						<s:hidden id="paymentType" name="paymentType"></s:hidden>
						<s:hidden id="paybtn" name="plSubTopic"></s:hidden>
						<s:hidden id="totalFeePaidAmount" name="totalAmount"></s:hidden>
						<s:set name="schoolTermsId" value=""></s:set>
						<s:set name="feeTypeId" value=""></s:set>
						<s:hidden name="tempId" />
		<table class="table table-striped table-bordered table-hover table-full-width dataTable" id="sample_2">	
		<thead>
			<th>
				Total Paid Amount
			</th>
			<!-- <th>
				Settlement (%) | Settlement Amount 
			</th> -->
			<th style="padding-left: 18px;" >
				Refund (%) | Amount 
			</th>
			<th>
				Refund Date
			</th>
		</thead>
		<tbody>
		<tr>
			<td>
				<sj:textfield name=""
						id="totalPaidAmount"
						cssClass="numeric form-control input-medium"
						onchange="javascript:onChangeTotal(this.value);" disabled="true"></sj:textfield>
			</td>
			<td class="discAndPayableAmt">
			 <div class="col-md-2">
				<sj:textfield name="partclrDiscPerc"
					id="particularDiscPer"
					title="%{feeTypeId}_%{payableAmount}_%{schoolTermId}_%{concessionAmount}"
					onkeyup="calculateParticularDiscountByPrctg(this.value)"
					cssClass="form-control input-small numeric term_%{schoolTermId}_PartclrDiscPerc partclrWiseField"></sj:textfield>
			</div>
			<%-- <div class="col-md-4">
				<sj:textfield name="partclrDiscAmt"
					id="particularDiscAmt"
					onkeyup="calclatePrtclrPaybleAmt(this.value)"
					cssClass="form-control input-small numeric term_%{schoolTermId}_PartclrDiscAmt partclrWiseField"></sj:textfield>
			</div> --%>
			<div class="col-md-4">
				<sj:textfield name="studentFeeRefund.refundAmount"
					id="particularDiscAmt"
					onkeyup="calclatePrtclrPaybleAmt(this.value)"
					cssClass="form-control input-small numeric term_%{schoolTermId}_PartclrDiscAmt partclrWiseField"></sj:textfield>
			</div>
			</td>
			<td>
				<div data-date-start-date="<s:property value="tempId1"/>d" data-date-end-date="+0d"
					data-date-format="mm/dd/yy" id="feeRefundDate"
					class="input-group input-medium date date-picker">
					<input type="text" id="paymentDate"
						name="studentFeeRefund.refundDate" readonly="readonly"
						value='<s:property value="startDate"/>'
						class="required form-control input-medium" /> <span
						class="input-group-btn">
						<button type="button" class="btn default">
							<i class="fa fa-calendar"></i>
						</button>
					</span>
				</div>
				<span class="help-block">(MM/DD/YYYY)</span>
			</td>
			<%-- <td>
					<sj:textfield name="studentFeeRefund.refundAmount"
						title="%{feeTypeId}_%{payableAmount}_%{schoolTermId}_%{concessionAmount}" id="partRemainingPayAmt"
						onkeyup="calculateAmountAndDiscountByRefundAmount(this.value)"
						cssClass="form-control input-small numeric term_%{schoolTermId}_PayableAmt partclrWiseField"
						cssStyle="width:75%" ></sj:textfield>
			</td> --%>
		</tr>
		</tbody>
		</table>

				<div class="form-actions fluid">
					<div class="col-md-6">
						<div class="col-md-offset-4 col-md-12">
							<sj:submit cssClass="submitBt btn blue" value="Refund" onclick="refundfees()"
								indicator="indicator" targets="staffList"
								onBeforeTopics="studentRefundFormValidation" validate="true"
								formIds="addStudentPaymentFee"/>
							<sj:submit  cssClass="submitBt btn green long" onclick="refundPrintfees()"
								value="Refund & Print" indicator="indicator"
								targets="staffList" formIds="addStudentPaymentFee"
								onBeforeTopics="studentRefundFormValidation"
								onCompleteTopics="doPrintStudentRefundInvoice" validate="true"/>
								
								<s:url id="urlViewGetAdmitted" action="ajaxViewExpiredStudents" namespace="/student"/>
								<sj:a href="%{urlViewGetAdmitted}" 
									targets="staffList" cssClass="btn default">Cancel</sj:a>
						</div>
					</div>
				</div>		
		</s:form>	
</s:if>
<s:else>
 	Fees not configured for this student class or you have paid fee for this student.
 </s:else>
 <script type="text/javascript">
 $(document).ready(function() {
	 FormComponents.init(); 
 	var amount = 0;
	var concessionAmount =0;
	var termPaidAmount = 0;
	$(".paidAmount").each(function() {
			termPaidAmount = termPaidAmount+parseFloat($(this).html());
	});
	//alert(termPaidAmount);
	if(termPaidAmount > 0){
	
	$("#totalPaidAmount").val(termPaidAmount);
	$("#totalFeePaidAmount").val(termPaidAmount);
	//$("#partRemainingPayAmt").val(termPaidAmount);
	}
	 var formattedDate = $.datepicker.formatDate('mm/dd/yy',new Date());
		$("#paymentDate").val(formattedDate);
		
	$.destroyTopic('studentRefundFormValidation');
 });
 function calculateParticularDiscountByPrctg(discountPerc) {
		if (isNonEmpty(discountPerc)) {
			discountPerc = parseFloat(discountPerc);
			var totalPaidAmount = parseFloat($('#totalPaidAmount').val());
			if (discountPerc <= 100) {
				//if ($('#totalPartclrAmt_' + feeTypeId + '_' + schoolTermId).is(':checked')) {
					var discAmt = Math.round(((discountPerc / 100) * totalPaidAmount) * 100) / 100;
					$('#particularDiscAmt').val(discAmt);
					//$('#partRemainingPayAmt').val(totalPaidAmount - discAmt);
					//disablePartclrPayableAmountFields(feeTypeId, schoolTermId);
				/* } else {
					$('#particularDiscAmt_' + feeTypeId + '_' + schoolTermId).val('');
					$('#partRemainingPayAmt_' + feeTypeId + '_' + schoolTermId).val(payableAmount);
					enablePartclrPayableAmountFields(feeTypeId, schoolTermId);
				} */
			} else {
				alert('Refund percentage should be less than or equal to 100.');
				$('#particularDiscPer').val('');
				$('#particularDiscAmt').val('');
				//$('#partRemainingPayAmt').val(totalPaidAmount);
				//enablePartclrPayableAmountFields(feeTypeId, schoolTermId);
			}
		} else {
			$('#particularDiscAmt').val('');
			//$('#partRemainingPayAmt').val(payableAmount);
			//enablePartclrPayableAmountFields(feeTypeId, schoolTermId);
		}
		//getSelectedTermsTotalAmount();
	}
 function calclatePrtclrPaybleAmt(partTotDiscAmt) {
		var partclrDiscPerc = 0;
		var prtclrPayableAmount = parseFloat($('#totalPaidAmount').val());
		if (isNonEmpty(partTotDiscAmt)) {
			if (prtclrPayableAmount >= partTotDiscAmt) {
				partclrDiscPerc = (parseFloat(partTotDiscAmt) / parseFloat(prtclrPayableAmount) * 100);
				$('#particularDiscPer').val(partclrDiscPerc);
				//$('#partRemainingPayAmt').val((prtclrPayableAmount - partTotDiscAmt));
				//$('#partRemainingPayAmt').attr('readonly', 'true');
				/* $('#totalPayAmountAfterDiscount').attr('readonly', 'true');
				$('#reasonForDiscount').show();
				$('#studentDiscount').addClass('required'); */
			} else {
				alert('Refund amount should be less than or equal to ' + prtclrPayableAmount);
				$('#particularDiscAmt').val('');
				$('#particularDiscPer').val('');
				//$('#partRemainingPayAmt').val(prtclrPayableAmount);
				//$('#partRemainingPayAmt').removeAttr('readonly');
				/* $('#totalPayAmountAfterDiscount').removeAttr('readonly');
				$('#reasonForDiscount').hide(); */
			}
		} else {
			$('#particularDiscPer').val('');
			//$('#partRemainingPayAmt_').val('');
			//$('#partRemainingPayAmt').val((prtclrPayableAmount));
			//$('#partRemainingPayAmt').removeAttr('readonly');
			/* $('#totalPayAmountAfterDiscount').removeAttr('readonly');
			$('#reasonForDiscount').hide(); */
			
			
		}
		getSelectedTermsTotalAmount();

	}
 
 function calculateAmountAndDiscountByRefundAmount(refundAmount) {
		var partclrDiscPerc = 0;
		var prtclrPayableAmount = parseFloat($('#totalPaidAmount').val());
		if (isNonEmpty(refundAmount)) {
			if (refundAmount <= prtclrPayableAmount) {
				partclrDiscPerc = (parseFloat(refundAmount) / parseFloat(prtclrPayableAmount) * 100);
				$('#particularDiscPer').val(partclrDiscPerc);
				$('#particularDiscAmt').val((prtclrPayableAmount - refundAmount));
				//$('#particularDiscAmt').attr('readonly', 'true');
				/* $('#totalPayAmountAfterDiscount').attr('readonly', 'true');
				$('#studentDiscount').addClass('required'); */
			} else {
				alert('Refund amount should be less than or equal to ' + prtclrPayableAmount);
				$('#particularDiscAmt').val(0);
				$('#particularDiscPer').val(100);
				//$('#partRemainingPayAmt').val(prtclrPayableAmount);
				//$('#partRemainingPayAmt').removeAttr('readonly'); 
				/* $('#totalPayAmountAfterDiscount').removeAttr('readonly');
				$('#reasonForDiscount').hide(); */ 
			}
		} else {
			$('#particularDiscPer').val('');
			//$('#partRemainingPayAmt_').val('');
			//$('#partRemainingPayAmt').val((prtclrPayableAmount));
			//$('#partRemainingPayAmt').removeAttr('readonly');
			/* $('#totalPayAmountAfterDiscount').removeAttr('readonly');
			$('#reasonForDiscount').hide(); */
			
			
		}
		getSelectedTermsTotalAmount();

}
 function refundfees() {
		$('#paybtn').val('Refund');
	}
 function refundPrintfees() {
		$('#paybtn').val('Print');
	}
 $.subscribe('studentRefundFormValidation',function(event, data) {
	var partRemainingPayAmt = $('#particularDiscAmt').val();
	if(!isNonEmpty(partRemainingPayAmt) || parseFloat(partRemainingPayAmt) <= 0.0){
		alert("Refund Amount should be greater than 0");
		$('.submitBt').removeAttr('disabled');
		event.originalEvent.options.submit=false;
	}
	else if ($('#chForm').is(':checked')) {
		var chequeNum = $("#chequeNumber").val();
		if (chequeNum == null || chequeNum == "" || chequeNum == "Number") {
			alert("Enter Cheque Number");
			$('.submitBt').removeAttr('disabled');
			event.originalEvent.options.submit=false;
		}
	}
});
 $.subscribe('doPrintStudentRefundInvoice', function(event, data) {
		$('#printRefundReport').submit();
		$.destroyTopic('doPrintStudentRefundInvoice');
	});
 function paymentTypeMethodChange(clickButton) {
		payMode=clickButton;
		if (clickButton == 'D') {
			$("#inputboxhideText").show();
			$("#checkinputboxhideText").hide();
			$("#bankinputboxhideText").show();
			$("#bankibranchnputboxhideText").show();
			$('#neftinputboxhideText').hide();
			$("#studentChallanNumberDivId").hide();
		} else if (clickButton == 'CH') {
			$("#checkinputboxhideText").show();
			$("#inputboxhideText").hide();
			$("#bankinputboxhideText").show();
			$("#bankibranchnputboxhideText").show();
			$('#neftinputboxhideText').hide();
			$("#studentChallanNumberDivId").hide();
		} else if (clickButton == 'C') {
			$("#inputboxhideText").hide();
			$("#checkinputboxhideText").hide();
			$("#bankinputboxhideText").hide();
			$('#neftinputboxhideText').hide();
			$("#studentChallanNumberDivId").hide();
		}else if (clickButton == 'CS' || clickButton == 'NEFT') {
			$("#inputboxhideText").hide();
			$("#checkinputboxhideText").hide();
			$("#bankinputboxhideText").show();
			$("#bankibranchnputboxhideText").hide();
			$('#neftinputboxhideText').show();
			$("#studentChallanNumberDivId").hide();
		}else if (clickButton == 'CL') {
			$("#inputboxhideText").hide();
			$("#checkinputboxhideText").hide();
			$("#bankinputboxhideText").hide();
			$('#neftinputboxhideText').hide();
			$("#studentChallanNumberDivId").show();
		}
	}
 </script>
