<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/messages.jsp"%>
<div class="form-body">
	<s:form id="addVoucherId" action="ajaxAddVoucher" method="post"
		theme="simple" cssClass="form-horizontal" namespace="/admin">
		<s:hidden name="tempString" cssClass='tempString' />
		<s:hidden name="voucher.id" id="%{voucherId}"></s:hidden>
		<s:hidden name="voucher.voucherNo" value="%{plTitle}"></s:hidden>
		<s:hidden name="financialYear.id" value="%{tempId1}" />
		<s:hidden name="tempId" value="%{tempId}" />
		<s:hidden name="attendanceDate" value="%{attendanceDate}" cssClass="attendanceDates" />
		<div class="form-group">
			<label class="control-label col-md-2"><span class="required">*</span>Pay To : </label>
			<div class="col-md-9" style="margin-left: 15px;">
				<div id='familyRelationGroup'>
					<div style="display: none;">
							<div id="familyRelationDiv1" class="familyRelationData">
							<span id='' class='familyMemberId'></span>
							<s:hidden name="voucherDetailsIds" value="%{voucherDetailsId}" id="voucherDetailsIds"></s:hidden>
								<div class="row">
									<div class="col-md-3">
										<label class="control-label"><span class="required">*</span> Account </label>
										 <s:select list="tempList" listKey="ledgerId" listValue="ledgerName"
											id="ledgersIds" name="ledgerId"
											theme="simple" 
											cssClass="required form-control accountNos" 
											/>
									</div>
									<div class="col-md-3">
											<label class="control-label"><span class="required">*</span> Narration </label>
												<sj:textarea rows="1" cols="20" maxlength="245" id="narration" name="narration"
													cssClass="form-control narrations required" cssStyle="padding:1px 12px;"></sj:textarea>
									</div>
									<div class="col-md-3">
										<label class="control-label"><span class="required">*</span> Amount </label>
											<sj:textfield id="amountId" name="amount" cssStyle="padding:1px 12px;"
											cssClass="voucherAmount form-control input-small numericDot required" maxlength="10"></sj:textfield>
									</div>
									<div class="col-md-3" style="margin-top: 35px;">
										&nbsp;
										<div class="col-md-4 removeUserFamilyDetails">
											<a class="btn btn-xs red removeDiv" onclick="removeContactValues(this);" id="removeId"> <i
												class="fa fa-times"></i> Delete
											</a>
										</div>
									</div>
								</div>
							</div>
					</div>
					<s:if test="%{viewVoucherDetailsList != null && !viewVoucherDetailsList.isEmpty()}">
						<s:iterator value="viewVoucherDetailsList" status="serStatus">
						<div id="familyRelationDiv<s:property value="#serStatus.count"/>" class="familyRelationData">
						<span id='<s:property value="voucherDetailsId"/>' class='familyMemberId'></span>
							<div class="row">
								<div class="col-md-3">
									<label class="control-label"><span class="required">*</span> Account </label>
										 <s:select list="tempList" listKey="ledgerId" listValue="ledgerName"
											id="ledgersIds" name="ledgerId"
											theme="simple" 
											cssClass="required form-control accountNos" 
											/>
								</div>
								<div class="col-md-3">
										<label class="control-label"><span class="required">*</span> Narration </label>
											<sj:textarea rows="1" cols="20" maxlength="245" id="narration" name="narration"
												cssClass="form-control narrations required" cssStyle="padding:1px 12px;"></sj:textarea>
								</div>
								<div class="col-md-3">
									<label class="control-label"><span class="required">*</span> Amount </label>
										<sj:textfield id="amountId" name="amount" cssStyle="padding:1px 12px;"
										cssClass="voucherAmount form-control input-small numericDot required" maxlength="10"></sj:textfield>
								</div>
								<div class="col-md-3" style="margin-top: 35px;">
									&nbsp;
										<div class="col-md-2 removeUserFamilyDetails">
										<a class="btn btn-xs red removeDiv" onclick="removeContactValues('<s:property value="voucherDetailsId"/>');" id="removeId"> <i
												class="fa fa-times"></i> Delete
											</a>
									</div>
							</div>
						</div>
						</div>
						</s:iterator>
					</s:if>
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
				<sj:submit value="Submit & Print" cssClass="submitBt btn green" onCompleteTopics="doEditPrintVoucherInvoice"
					onBeforeTopics="addVoucherForm" formIds="addVoucherId"
					targets="mainContentDiv" indicator="indicator" validate="true" />
						<s:url id="urlVoucher" action="ajaxDoGetDayBookDetails" namespace="/admin"/>
						<sj:a href="%{urlVoucher}"
							targets="mainContentDiv" indicator="indicator" cssClass="btn default">Cancel</sj:a>
			</div>
		</div>
	</s:form> 
</div>
<script type="text/javascript">	
$(document).ready(function() {
	$.destroyTopic('addVoucherForm');
	FormComponents.init();
	$('.numericDot').numeric( {
		allow : "."
	});
 });
$("a#insertValues").click(function() {
	var count = $("div#familyRelationGroup").find("div.familyRelationData").length;
	count++;
	var d = document.createElement("div");
	d.setAttribute("id", "familyRelationDiv" + count);
	d.setAttribute("class", "familyRelationData");
	$(d).html($("#familyRelationDiv1").html());
	$("#familyRelationGroup").append(d);
	//$("#TextBoxesGroup").append(d);
	$(d).find("div.removeUserFamilyDetails").show();
	$(d).find("span.familyMemberId").attr('id' ,0);
})
function removeContactValues(anchor) {
		//var selectedVal=$(anchor).parents('div.familyRelationData').find('span').attr('id');
		//alert(anchor);
		var selectedVal=anchor;
		if(isNonEmpty(selectedVal) && selectedVal>0){
			var url = jQuery.url.getChatURL('/admin/ajaxRemoveVoucherDetails.do');
			$.ajax({
				url : url,
				cache : false,
				data : "voucherDetails.id=" + selectedVal,
				success : function(response) {
					$('#mainContentDiv').show();
					$('#mainContentDiv').html(response);
				}
			});
		}else{
			$(anchor).parents('div.familyRelationData').remove();
		}
	}
$.subscribe('addVoucherForm', function(event, data) { 
	//var attendanceDates = $("#date0").val();
	//alert(attendanceDates);
	if ($('#addVoucherId').valid()) {
		var accountNo='';
		var title='';
		var narrations = '';
		var voucherAmount = '';
		var jsonObj = [];
	$("div#familyRelationGroup").find("div.familyRelationData").each(function() {
		title = $(this).find("span.familyMemberId").attr('id');
		accountNo =  $(this).find("select#ledgersIds option:selected").val();
		narrations = $(this).find('.narrations').val();
		voucherAmount = $( this).find('.voucherAmount') .val();
		
		if(isNonEmpty(accountNo) && isNonEmpty(title)){
			jsonObj.push({
				"accountNo" : accountNo,
				"narrations" : narrations,
				"voucherAmount" : voucherAmount,
				"title" : title
			});
		}
		//alert(JSON.stringify(jsonObj));
	});
	$('.tempString').val(JSON.stringify(jsonObj));
	var attendanceDates = $("#date0").val();
	$('.attendanceDates').val(attendanceDates);
	$('input[type="submit"]').prop('disabled', true);
		return true;
	} else {
		event.originalEvent.options.submit = false;
	}
});

$.subscribe('doEditPrintVoucherInvoice', function(event, data) {
	$('#printDaybookReport').submit();
	$.destroyTopic('doEditPrintVoucherInvoice');
});

</script>