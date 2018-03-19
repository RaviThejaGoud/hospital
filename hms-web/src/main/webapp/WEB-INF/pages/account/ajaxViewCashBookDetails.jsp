<%@ include file="/common/taglibs.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div>
	<jsp:include page="/common/messages.jsp" />

		<div class="row">
	<s:form action="#" theme="simple" id="#" method="post" cssClass="form-horizontal">
			<div class="col-md-12">
				<div class="form-group">
					<label class="col-md-2 control-label"> Select Type : </label>
					<div class="radio-list">
						<label class="radio-inline"> <input type="radio" value="C"
							name="cashBookVo.paymentType"
							onclick="javascript:paymentType(this.value)" checked>
							Cash Book
						</label> <label class="radio-inline"> <input type="radio"
							value="B" name="cashBookVo.paymentType" id="ddForm"
							onclick="javascript:paymentType(this.value)"> Bank Book
						</label>
					</div>
				</div>
			</div>
		</div>
	</s:form>
	<s:if
		test="%{financialCashBookList != null && !financialCashBookList.isEmpty()}">
		<div id="cashBook">
			<table
				class="table table-striped table-bordered table-hover table-full-width"
				id="sample_5">
				<thead>
					<tr>
						<th>Voucher Date</th>
						<th>Account Name</th>
						<th>Narration</th>
						<th>Voucher No.</th>
						<th>Transaction Type</th>
						<th>Cash Amount</th>
						<th>Voucher</th>

					</tr>
				</thead>
				<tbody>
					<s:iterator value="financialCashBookList">

						<tr>
							<td><fmt:formatDate value="${transactionDate}"
									pattern="dd-MMM-YYYY" /></td>
							<td><s:property value="financialAccountDetails.accountName" /></td>
							<td><s:property value="narration" /></td>
							<td><s:if test='%{entryType == "A"}'>
								-
							</s:if> <s:else>
									<s:property value="vocherNumber" />
								</s:else>
							<td><s:property value="transactionTypeStr" /></td>
							<td><s:property value="amount" /></td>
							<td id="<s:property value="amount"/>" class="convertNoToValue">
								<s:if test='%{entryType == "A"}'>
								-
							</s:if> <s:else>
									<s:form action="ajaxPrintDayBooksReport" theme="simple"
										id="downLoadPrintDaybookReport%{id}"
										cssClass="form-horizontal" namespace="/account" method="post">
										<s:hidden name="tempId" value="%{id}"></s:hidden>
										<s:hidden name="tempString" id="convertAmtToWords"
											value="%{amount}"></s:hidden>
										<sj:submit cssClass="btn btn-xs green" value='Payment Voucher'
											onCompleteTopics="downLoadPrintDayBookDetails"></sj:submit>
									</s:form>
								</s:else>

							</td>
						</tr>
					</s:iterator>
				</tbody>
			</table>
		</div>

	</s:if>
	<s:else>
		<div class="alert alert-info">There are no cash book entries.</div>
	</s:else>
	<s:if test="%{objectList != null && !objectList.isEmpty()}">
		<div id="bankBook" style="display: none;">
			<table
				class="table table-striped table-bordered table-hover table-full-width"
				id="sample_5">
				<thead>
					<tr>
						<th>Voucher Date</th>
						<th>Account Name</th>
						<th>Narration</th>
						<th>Voucher No.</th>
						<th>Transaction Type</th>
						<th>Cash Amount</th>
						<th>Voucher</th>

					</tr>
				</thead>
				<tbody>
					<s:iterator value="objectList">

						<tr>
							<td><fmt:formatDate value="${transactionDate}"
									pattern="dd-MMM-YYYY" /></td>
							<td><s:property value="financialAccountDetails.accountName" /></td>
							<td><s:property value="narration" /></td>
							<td><s:if test='%{entryType == "A"}'>
								-
							</s:if> <s:else>
									<s:property value="vocherNumber" />
								</s:else>
							<td><s:property value="transactionTypeStr" /></td>
							<td><s:property value="amount" /></td>
							<td id="<s:property value="amount"/>" class="convertNoToValue">
								<s:if test='%{entryType == "A"}'>
								-
							</s:if> <s:else>
									<s:form action="ajaxPrintDayBooksReport" theme="simple"
										id="downLoadPrintDaybookReport%{id}"
										cssClass="form-horizontal" namespace="/account" method="post">
										<s:hidden name="tempId" value="%{id}"></s:hidden>
										<s:hidden name="tempString" id="convertAmtToWords" value="%{amount}"></s:hidden>
										<sj:submit cssClass="btn btn-xs green" value='Payment Voucher'
											onCompleteTopics="downLoadPrintDayBookDetails"></sj:submit>
									</s:form>
								</s:else>

							</td>
						</tr>
					</s:iterator>
				</tbody>
			</table>
		</div>

	</s:if>
	<s:else>
		<div class="alert alert-info">There are no bank book entries.</div>
	</s:else>
</div>
<script type="text/javascript">
	$.subscribe('downLoadPrintDayBookDetails', function(event, data) {
		$('#downLoadPrintDaybookReport').submit();
		$.destroyTopic('downLoadPrintDayBookDetails');
	});
	var amountdiv = '<s:property value="totalAmount"/>';
	var amountWordval = feeAmountInWords(amountdiv);
	$('#amountWords').val(amountWordval);
	$('td.convertNoToValue').each(function() {
		var amount = $(this).attr("id");
		var amountWord = feeAmountInWords(amount);
		$(this).find("input#convertAmtToWords").val(amountWord);
	});
	TableAdvanced.init();
	FormComponents.init();
	$("input:checkbox, input:radio:not('.toggle')").uniform();
	function paymentType(clickButton) {
		if (clickButton == 'C') {
			$("#cashBook").show();
			$("#bankBook").hide();
		} else if (clickButton == 'B') {
			$("#cashBook").hide();
			$("#bankBook").show();
		}
	}
</script>