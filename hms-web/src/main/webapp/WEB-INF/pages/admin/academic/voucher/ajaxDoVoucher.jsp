<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/messages.jsp"%>
<div class="form-horizontal">
		<h4 class="modal-title">
				Add Voucher
			</h4>
		<div class="form-group">
			<label class="control-label col-md-2"> Financial Year : </label>
			<div class="col-md-2">
			<s:select id="finanacial" list="financialYearList"
			cssClass="form-control input-medium" listKey="id" 
			listValue="yearName" name="financialYearid" />
		</div>
		</div>
		<div class="form-group">
			<label class="control-label col-md-2"><span class="required">*</span>Voucher
				Date : </label>
			<div class="col-md-2">
				<div data-date-start-date="" data-date-end-date="+0d" data-date-format="mm/dd/yyyy"
					class="input-group input-medium date date-picker">
					<input type="text" id="date0" name="voucher.voucherDate" 
							readonly="readonly" value='<s:property value="attendanceDate"/>'
						class="form-control">
					<span class="input-group-btn">
						<button type="button" class="btn default">
							<i class="fa fa-calendar"></i>
						</button> </span>
				</div>
			<span class="help-block">(MM/DD/YYYY)</span>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-md-2">Voucher No : </label>
			<div class="col-md-2">
				<sj:textfield  value="%{plTitle}" id="voucherNo" disabled="true"  
					cssClass="form-control"></sj:textfield>
			</div>
		</div>
		<s:if test="%{tempList1 != null && !tempList1.isEmpty()}">
		<div class="form-group">
			<label class="control-label col-md-2"><span class="required">*</span>Pay From : </label>
			<div class="col-md-2">
			<s:select id="groupId" list="tempList1" listKey="id"
				listValue="groupName" onchange="javascript:getCashAndBankLedgerDetails(this.value);"
				name="tempId" theme="simple" cssClass="form-control required " headerKey=""></s:select>
			<%-- <s:select list="tempList1" listKey="id" listValue="groupName"
					id="ledgersId" name="voucher.fromAccountId" onchange="getCashAndBankLedgerDetails(this.value);"
					theme="simple"
					cssClass="required form-control"
					/> --%>
			</div>
		</div>
			<div id="viewVoucherInfoDiv"></div>
		</s:if>
		<s:else>
				<div class="alert alert-info">Currently there are no cash/bank groups .</div>
		</s:else>
</div>
<script type="text/javascript">
$(document).ready(function() {
	$('.numeric').numeric();
	var tempId = $('#groupId option:first').val(); 
	var yearId = $("#finanacial").val();
	var attendanceDate = $("#date0").val();
	var vouNo = '<s:property value="plTitle"/>';
	//alert(tempId);
	/* alert(yearId);
	alert(attendanceDate);
	alert(vouNo);
	alert(tempId); */
	if(isNonEmpty(attendanceDate) || isNonEmpty(vouNo) || isNonEmpty(tempId)){
		getCashAndBankLedgerDetails(tempId);
	}
	
});
function getCashAndBankLedgerDetails(groupId){
	var yearId = $("#finanacial").val();
	var attendanceDate = $("#date0").val();
	var vouNo = '<s:property value="plTitle"/>';
	//alert(attendanceDate);
	if(isNonEmpty(groupId)){
		var pars="tempId1=" + yearId+"&plTitle="+'<s:property value="plTitle" />'+"&attendanceDate="+attendanceDate+"&tempId="+groupId;
			var url = jQuery.url.getChatURL("/admin/ajaxGetLedgerDetailsByledgerId.do");
			$('#viewVoucherInfoDiv')
					.html('<div align="center" style="margin: 150px;"><img src="../assets/layouts/layout2/img/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
			$.ajax({
				url : url,
				cache : false,
				data : pars,
				success : function(html) {
					$("#viewVoucherInfoDiv").html(html);
				}
			});
	}else{
		$('#viewVoucherInfoDiv').html('<div class="alert alert-info">Currently there are no cash/bank groups .'+'</div>');
		//alert("Please select at least one account group.");
		 return false;
	}
}
</script>