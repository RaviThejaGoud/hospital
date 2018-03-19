<%@ include file="/common/taglibs.jsp"%>
<div data-width="760" class="modal fade modal-overflow in"
	style="display: block; width: 760px; margin-left: -379px; margin-top: 200px;"
	aria-hidden="false">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true"></button>
		<h4 class="modal-title">Update Message</h4>
	</div>
	<div class="modal-body">
		<s:form id="editMessagesValidationInfo"
			action="ajaxSendSMSNewFeaturesToAllCustomers" method="post"
			theme="simple" cssClass="form-horizontal" namespace="/masterAdmin">
			<s:hidden name="tempId" value="%{sendNotifications.id}"></s:hidden>
			<div class="form-body form-horizontal">
				<div class="row">
					<div class="col-md-10">
						<div class="form-group">
							<label class="control-label col-md-3"> <span class="required">*</span> New Features
								Message : </label>
							<div class="col-md-9">
								<sj:textarea rows="10" cols="20"
									name="sendNotifications.description" maxCharsData="2000"
									cssClass="required form-control word_count"></sj:textarea>
								<span class="counter"></span>
							</div>
						</div>
					</div>
				</div>
				<div class="form-actions fluid">
					<div class="col-md-offset-3 col-md-6">
						<sj:submit cssClass="submitBt btn green" value="Submit"
							indicator="indicator" targets="mainContentDiv"
							onBeforeTopics="editMessagesValidation"
							formIds="editMessagesValidationInfo" validate="true" />
						<button type="button" data-dismiss="modal" class="btn default">Cancel</button>
					</div>
				</div>
			</div>
		</s:form>
	</div>
</div>
<script>
$.subscribe('editMessagesValidation', function(event, data) {
	if ($('#editMessagesValidationInfo').valid()){
		$('button.close').click();
		return true;
	}else{
		event.originalEvent.options.submit=false;
	}
});
</script>
