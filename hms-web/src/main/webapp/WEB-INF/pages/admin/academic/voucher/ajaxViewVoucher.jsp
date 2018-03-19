<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/messages.jsp"%>
<div class="row">
	<div class="col-md-12">
		<s:if test="%{viewVoucherDetailsList != null && !viewVoucherDetailsList.isEmpty()}">
			<table	class="table table-striped table-bordered table-hover table-full-width"	id="sample_2">
			<thead>
					<tr>
						<th>Voucher No</th>
						<th>Voucher Date</th>
						<th>Group Name</th>
						<!-- <th>Ledger Name</th> -->
						<!-- <th>Total Amount</th> -->
						<th>Invoice</th>
						<th>Edit</th>
						<%-- <s:if test='%{#session.previousYear == "N"}'>
							<th>Edit</th>
							<th>Delete</th>
						</s:if> --%>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="viewVoucherDetailsList">
						<tr>
							<td><s:property value="voucherNo" /></td>
							<td><s:property value="voucherDateFormet" /></td>
							<td><s:property value="groupName" /></td>
							<%-- <td><s:property value="ledgerName" /></td>
							<td><s:property value="thirtyTotalAmount" /></td> --%>
							<td id="<s:property value="totalAmount"/>" class="convertNoToValue">
									<s:form action="ajaxPrintDayBooksReport" theme="simple" id="downLoadPrintDaybookReport%{voucherId}"
										cssClass="form-horizontal" namespace="/reports" method="post">
										<s:hidden name="tempId" value="%{voucherId}"></s:hidden>
										<s:hidden name="tempString" id="convertAmtToWords" value="%{totalAmount}"></s:hidden>
										<sj:submit cssClass="btn btn-xs green" value='Payment Voucher' 
										 onCompleteTopics="downLoadPrintDayBookDetails"></sj:submit>
									</s:form>
						     </td> 
							<s:if test='%{#session.previousYear == "N"}'>
								<td>
									<s:url id="doEditDaybookDetails" action="ajaxDoVoucher"
										includeParams="all" escapeAmp="false" namespace="/admin">
										<s:param name="voucher.id" value="voucherId" />
										<s:param name="voucherDetails.id" value="voucherDetailsId" />
									</s:url>
									<sj:a href="%{doEditDaybookDetails}" indicator="indicator"
										targets="voucherContentDiv" cssClass="btn btn-xs purple"
										title="Edit">
										<i class="fa fa-edit"></i>Edit
									</sj:a>
								<%-- <a data-toggle="modal" href="#voucherDiv"
									class="btn btn-xs purple"
									onclick="javascript:popupEditVoucher(<s:property value="objectList[#stat.count-1][5]" />,<s:property value="objectList[#stat.count-1][6]" />);"><i
										class="fa fa-edit"></i>Edit </a> --%></td>
								<%-- <td><s:url id="removeDayBook" action="ajaxRemoveDayBook"
										includeParams="all" escapeAmp="false" namespace="/admin">
										<s:param name="voucherId" value="%{voucherId}" />
									</s:url> <s:div cssClass="btn btn-xs red"
										onclick="javascript:confirmDialogWithTarget(this,'mainContentDiv');"
										id='%{removeDayBook}' title="Delete this subject">
										<i class="fa fa-times"></i>Delete
								</s:div></td> --%>
							</s:if>
						</tr>
					</s:iterator>
				</tbody>
			</table>
		</s:if>
		<s:else>
			<div class="alert alert-info">Currently there are no voucher.</div>
		</s:else>
	</div>
</div>
<div id="voucherDiv"></div>
 <s:form action="ajaxPrintDayBooksReport" theme="simple" id="printDaybookReport"
	cssClass="form-horizontal form-body" namespace="/reports" method="post">
	<s:hidden name="tempId" value="%{tempId}"></s:hidden>
	<s:hidden name="plTitle" id="amountWords" value=""></s:hidden>
</s:form> 
<script type="text/javascript">
$.subscribe('downLoadPrintDayBookDetails', function(event, data) {
	$('#downLoadPrintDaybookReport').submit();
	$.destroyTopic('downLoadPrintDayBookDetails');
});
var amountdiv='<s:property value="totalAmount"/>';
var amountWordval=feeAmountInWords(amountdiv);
$('#amountWords').val(amountWordval);

TableAdvanced.init();
	$.subscribe('addVoucherForm', function(event, data) {
		if ($('#addVoucherId').valid()) 
				return true;
			  else
				event.originalEvent.options.submit = false;
	});
	
	/* function popupEditVoucher(id,voucherDetailsId) {
		var url = jQuery.url.getChatURL("/admin/ajaxDoVoucher.do");
		$('#voucherDiv').html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		$.ajax( {
				url : url,
				cache : false,
				data : "voucher.id=" + id+"&voucherDetails.id="+voucherDetailsId,
				success : function(html) {
					$("#voucherDiv").html(html);
				}
			});
		} */
	  $('td.convertNoToValue').each(function(){
		var amount= $(this).attr("id");
		var amountWord=feeAmountInWords(amount);
		$(this).find("input#convertAmtToWords").val(amountWord);
	}); 
</script>
