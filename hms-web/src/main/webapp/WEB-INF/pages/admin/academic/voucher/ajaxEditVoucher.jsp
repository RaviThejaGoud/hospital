<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/messages.jsp"%>
<div class="form-horizontal">
		<h4 class="modal-title">
				Update Voucher
			</h4>
		<div class="form-group">
			<label class="control-label col-md-2"> Financial Year : </label>
			<div class="col-md-2">
			<s:select id="finanacial" list="financialYearList"
			cssClass="form-control input-medium" listKey="id" 
			listValue="yearName" name="financialYear.id" />
		</div>
		</div>
		<div class="form-group">
			<label class="control-label col-md-2"><span class="required">*</span>Voucher
				Date : </label>
			<div class="col-md-2">
				<div class="input-group input-medium date date-picker"
					data-date-format="mm/dd/yyyy">
					<input type="text" readonly="readonly"
						class="form-control fdate" id="date0"
						value='<s:property value="empId"/>'
						name="voucher.voucherDate"> <span
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
			<label class="control-label col-md-2">Voucher No : </label>
			<div class="col-md-2">
				<sj:textfield  value="%{plTitle}" id="voucherNo" disabled="true"  
					cssClass="form-control"></sj:textfield>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-md-2"><span class="required">*</span>Pay From : </label>
			<div class="col-md-2">
				<s:select id="groupId" list="tempList1" listKey="id" disabled="true" 
					listValue="groupName" onchange="javascript:getCashAndBankLedgerDetails(this.value);"
					name="tempId" theme="simple" cssClass="form-control required " headerKey=""/>
		   </div>
</div>
</div>
<div id="viewVoucherInfoDiv">
		</div>
<script type="text/javascript">	
$(document).ready(function() {
	$.destroyTopic('addVoucherForm');
	FormComponents.init();
	$('.numericDot').numeric( {
		allow : "."
	});
	var tempId='<s:property value="tempId"/>';
	var yearId = $("#finanacial").val();
	var attendanceDate = $("#date0").val();
	var vouNo = '<s:property value="plTitle"/>';
	var tempId2 = '<s:property value="tempId2"/>';
	/* alert(yearId);
	alert(attendanceDate);
	alert(vouNo);
	alert(tempId);
	alert(tempId2); */
	if(isNonEmpty(attendanceDate) || isNonEmpty(vouNo) || isNonEmpty(tempId)){
		getCashAndBankLedgerDetails(tempId);
	}
 });
function getCashAndBankLedgerDetails(groupId){
	var yearId = $("#finanacial").val();
	var attendanceDate = $("#date0").val();
	var vouNo = '<s:property value="plTitle"/>';
	//var test = '<s:property value="voucherDetailsId"/>';	
	//alert(test);
	if(isNonEmpty(groupId)){
		var pars="tempId1=" + yearId+"&plTitle="+'<s:property value="plTitle" />'+"&attendanceDate="+attendanceDate+"&tempId="+groupId+"&tempId2="+'<s:property value="tempId2"/>';
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
		alert("Please select at least one account group.");
		 return false;
	}
}

</script>