<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/messages.jsp"%>
<s:if test="%{objectList != null && !objectList.isEmpty()}">
	<table
		class="table table-striped table-bordered table-hover table-full-width" id="sample_2">
		<thead>
			<tr>
				<th>
					Date
				</th>
				<th>
					Type
				</th>
				<th style="white-space: normal;">
					Description
				</th>
				<th>
					Amount
				</th>
				<th>
					Edit
				</th>
				<th>
					Delete
				</th>
				<th>
					Download
				</th>
			</tr>
		</thead>
		<tbody>
		<s:iterator value="objectList">
			<tr>
				<td>
					<s:property value="billPaymentDate" />
				</td>
				<td>
					<s:property value="dayBookType" />
				</td>
				<td style="white-space: normal;">
					<s:property value="description" />
				</td>
				<td>
					<s:property value="amount" />
				</td>
				<td>
					<s:if test='%{#session.previousYear == "N"}'>
						<a data-toggle="modal"  href="#responsive"  class="btn btn-xs purple"
							onclick="javascript:PopupDayBookDetials(<s:property value="%{id}" />);"><i class="fa fa-edit"></i>Edit
						</a>
					</s:if>
				</td>
				<td>
					<s:if test='%{#session.previousYear == "N"}'>
						<s:url id="removeDayBook" action="ajaxRemoveDayBook"
							includeParams="all" escapeAmp="false" namespace="/admin">
							<s:param name="dayBook.id" value="%{id}" />
						</s:url>
						<s:div cssClass="btn btn-xs red"
							onclick="javascript:confirmDialogWithTarget(this,'mainContentDiv');"
							id='%{removeDayBook}' title="Delete this subject">
							<i class="fa fa-times"></i>Delete
						</s:div>
					</s:if>
				</td>
				<td id="<s:property value="amount"/>" class="convertNoToValue">
				<s:if test='%{type=="E"}'>
					<s:form action="ajaxPrintDayBooksReport" theme="simple" id="downLoadPrintDaybookReport%{id}"
						cssClass="form-horizontal" namespace="/reports" method="post">
						<s:hidden name="tempId" value="%{id}"></s:hidden>
						<s:hidden name="tempString" id="convertAmtToWords" value="%{amount}"></s:hidden>
						<sj:submit cssClass="btn btn-xs green" value='Payment Voucher' 
						 onCompleteTopics="downLoadPrintDayBookDetails"></sj:submit>
					</s:form>
				</s:if>
				<s:else>
					-
				</s:else>
				</td>
			</tr>
		</s:iterator>
	</tbody>
</table>
</s:if>
<s:else>
	<div class="alert alert-info">
		Currently there are no records.
	</div>
</s:else>
  <s:form action="ajaxPrintDayBooksReport" theme="simple" id="printDaybookReport"
	cssClass="form-horizontal form-body" namespace="/reports" method="post">
	<s:hidden name="tempId" value="%{tempId}"></s:hidden>
	<s:hidden name="plTitle" id="amountWords" value=""></s:hidden>
</s:form> 
<div id="responsive"></div>
<script type="text/javascript">
$.subscribe('downLoadPrintDayBookDetails', function(event, data) {
	$('#downLoadPrintDaybookReport').submit();
	$.destroyTopic('downLoadPrintDayBookDetails');
});

   var amountdiv='<s:property value="totalAmount"/>';
	var amountWordval=feeAmountInWords(amountdiv);
	$('#amountWords').val(amountWordval);
	
	
	$('td.convertNoToValue').each(function(){
		var amount= $(this).attr("id");
		var amountWord=feeAmountInWords(amount);
		$(this).find("input#convertAmtToWords").val(amountWord);
	});
	
	changePageTitle('DayBook');
	$(document).ready(function() {
		TableAdvanced.init();
		UIExtendedModals.init();
	});
		function PopupDayBookDetials(id) {
		var url = jQuery.url.getChatURL("/admin/ajaxDoAddDayBook.do");
		$('#responsive').html('<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		$.ajax( {
				url : url,
				cache : false,
				data : "dayBook.id=" + id,
				success : function(html) {
					$("#responsive").html(html);
				}
			});
		}
</script>
 