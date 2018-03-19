<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/messages.jsp"%>
<s:if test="%{tempList != null && !tempList.isEmpty()}">
	<s:form id="addVoucherId" action="ajaxAddVoucher" method="post"
		theme="simple" cssClass="form-horizontal" namespace="/admin">
		<s:hidden name="tempString" cssClass='tempString' />
		<s:hidden name="financialYear.id" value="%{tempId1}" />
		<s:hidden name="attendanceDate" value="%{attendanceDate}" cssClass="attendanceDates" />
		<s:hidden name="voucher.voucherNo" value="%{plTitle}"></s:hidden>
		<s:hidden name="tempId" value="%{tempId}" />
		<div class="form-body">
		<div class="form-group">
			<label class="control-label col-md-2"><span class="required">*</span>Pay To : </label>
			<div class="col-md-9" style="margin-left: 15px;">
			<div id='TextBoxesGroup'>
				<div id="TextBoxDiv1" class="addNewData">
					<span id='' class='eventContactId'></span>
					<div class="row">
						<div class="col-md-3">
							<label class="control-label"><span class="required">*</span> Account </label>
							 <s:select list="tempList" listKey="ledgerId" listValue="ledgerName"
								id="ledgersId" name="ledgerName"
								theme="simple"
								cssClass="required form-control accountNos"
								/>
						</div>
						<div class="col-md-3">
							<label class="control-label"><span class="required">*</span> Narration </label>
								<sj:textarea rows="1" cols="20" maxlength="245" id="narration"
									cssClass="form-control narrations required" cssStyle="padding:1px 12px;"></sj:textarea>
						</div>
						<div class="col-md-3">
							<label class="control-label"><span class="required">*</span> Amount </label>
								<sj:textfield id="amountId" cssStyle="padding:1px 12px;"
								cssClass="voucherAmount form-control input-small numericDot required" maxlength="10"></sj:textfield>
						</div>
						<div class="col-md-3" style="margin-top: 35px;">
							&nbsp;
							<div class="col-md-4 removeContact" style="display: none;">
								<a class="btn btn-xs red" onclick="removeContactValues(this);"> <i
									class="fa fa-times"></i> Delete
								</a>
							</div>
						</div>
						</div>
				</div>
			</div>
			<div class="col-md-1" style="margin-top: 17px;margin-left: -15px;">
				<a class="btn btn-xs purple" id="insertValues"> <i
					class="fa fa-plus"></i> Add New
				</a>
			</div>
			</div>
		</div>
		<div class="form-actions fluid">
			<div class="col-md-offset-2 col-md-5">
				<sj:submit value="Submit" cssClass="submitBt btn blue"
					onBeforeTopics="addVoucherForm" formIds="addVoucherId"
					targets="mainContentDiv" indicator="indicator" validate="true" />
				<sj:submit value="Submit & Print" cssClass="submitBt btn green" 
					onBeforeTopics="addVoucherForm" formIds="addVoucherId" onCompleteTopics="doPrintVoucherInvoice"
					targets="mainContentDiv" indicator="indicator" validate="true" />
				<s:url id="urlVoucher" action="ajaxDoGetDayBookDetails" namespace="/admin"/>
				<sj:a href="%{urlVoucher}"
					targets="mainContentDiv" indicator="indicator" cssClass="btn default">Cancel</sj:a>
			</div>
		</div>
		</div>
	</s:form> 
	</s:if>
	<s:else>
		<div class="alert alert-info">
			Current there is no ledgers.
		</div>
	</s:else>
<script type="text/javascript">
$.subscribe('doPrintVoucherInvoice', function(event, data) {
	$('#printDaybookReport').submit();
	$.destroyTopic('doPrintVoucherInvoice');
});

$(document).ready(function() {
	$.destroyTopic('addVoucherForm');
	FormComponents.init();
	$('.numericDot').numeric( {
		allow : "."
	});
	
 });
$("a#insertValues").click(function(){
	var count=$("div#TextBoxesGroup").find("div.addNewData").length;
	count++; 
	var d = document.createElement("div");
	 d.setAttribute("id", "TextBoxDiv"+count);
	 d.setAttribute("class", "addNewData");
	 $(d).html($("#TextBoxDiv1").html());
	 $("#TextBoxesGroup").append(d);
	 $(d).find("div.removeContact").show();
	 $(d).find("span.eventContactId").attr('id' ,0);
});
function removeContactValues(anchor) {
	$(anchor).parents('div.addNewData').remove();
}

$.subscribe('addVoucherForm', function(event, data) { 
	var amountVal = $("#amountId").val();
	if(amountVal == 0.0 || amountVal=='' ){
		alert('Please enter amout');
		event.originalEvent.options.submit=false;
	}else{
		if ($('#addVoucherId').valid()) {
		//var eventContactId='';
		var jsonObj = [];
		$("div#TextBoxesGroup").find("div.addNewData").each(function() {
			accountNo = $(this).find('.accountNos').val();
			narrations = $(this).find('.narrations').val();
			voucherAmount = $( this).find('.voucherAmount') .val();
			if(isNonEmpty(accountNo)){
				jsonObj.push({
					"accountNo" : accountNo,
					"narrations" : narrations,
					"voucherAmount" : voucherAmount
				});
			}
			//alert(JSON.stringify(jsonObj));
		});
		var attendanceDates = $("#date0").val();
		$('.attendanceDates').val(attendanceDates);
		$('.tempString').val(JSON.stringify(jsonObj));
		//$('button.close').click();
		$('input[type="submit"]').prop('disabled', true);
		} else {
			event.originalEvent.options.submit = false;
		}
	}
});
</script>