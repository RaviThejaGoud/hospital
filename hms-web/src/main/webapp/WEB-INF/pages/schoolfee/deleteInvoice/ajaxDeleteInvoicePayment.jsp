<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/messages.jsp"%>
<div data-width="760" class="modal fade modal-overflow in" data-focus-on="input:first"
	style="display: block; width: 760px; margin-left: -379px; margin-top: 100px;">
	<s:form id="deleteInvoicePayment" action="ajaxDeleteInvoicePaymentByMasterAdmin" theme="simple"
		cssClass="form-horizontal" namespace="/schoolfee">
		<!--studnet payment Id  -->
		<s:hidden name="deleteStudentPaymentVo.studentPaymentId" value="%{selectedId}"></s:hidden> 
		<s:hidden name="tempString2" value="%{academicYearId}"></s:hidden>
		<!-- custId -->
		<s:hidden name="deleteStudentPaymentVo.custId" value="%{tempString}"></s:hidden>
		<!--student id  -->
		<s:hidden name="studentNumber" value="%{studentNumber}"></s:hidden>
		<!--delete type : "S" studentPayment "O" fineFee -->
		<s:hidden name="tempString1" value="%{tempString1}"></s:hidden>
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
			<h4 class="modal-title">Delete Student Invoice</h4>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label"> 
				<span class="required">*</span> Delete Remarks : </label>
			<div class="col-md-9">
				<sj:textarea name="deleteStudentPaymentVo.deleteRemarks" id="deleteRemarks" cssClass="form-control input-large required" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label"> <span class="required">*</span> Reported Person : </label>
			<div class="col-md-8">
				<div class="input-group">
					<sj:textfield name="deleteStudentPaymentVo.reportedPerson" id="reportedPerson" cssClass="form-control input-large required" />
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label"> <span class="required">*</span> Support Person : </label>
			<div class="col-md-8">
				<div class="input-group">
					<sj:textfield name="deleteStudentPaymentVo.supportPersonName" id="supportPersonName" cssClass="form-control input-large required" />
					<span class="input-group-btn"> 
						<sj:submit targets="packageDetailsDiv" value="Delete Invoice" validate="true" cssClass="submitBt btn blue long"
							cssStyle="float:none;" onBeforeTopics="deleteInvoicePaymentForm" formIds="deleteInvoicePayment" /></span>
				</div>
			</div>
		</div>
	</s:form>
</div>
<script type="text/javascript">
$.subscribe('deleteInvoicePaymentForm', function(event, data) {
	if ($('#deleteInvoicePayment').valid()) {
		$('button.close').click();
		event.originalEvent.options.submit = true;
	} else {
		event.originalEvent.options.submit = false;
	}
});
</script>
