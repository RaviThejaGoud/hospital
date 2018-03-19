<%@ include file="/common/taglibs.jsp"%>
<div data-width="760" class="modal fade modal-overflow in"
	style="display: block; width: 760px; margin-left: -379px; margin-top: 150px;"
	aria-hidden="false">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true"></button>
		<h4 class="bold pageTitle">
			Send Sms To Parent
		</h4>
	</div>
	<div class="modal-body">
		<s:form action="ajaxSendSmsToParent" theme="simple"
			id="sendSmsToParent" cssClass="form-horizontal" namespace="/staff">
			<div class="form-body">
				<s:hidden name="tempString" />
				<s:hidden name="tempId" />
				<s:hidden name="tempId1" />
				<s:hidden name="stuFirstName" value="%{anyId}"/>
				<s:hidden name="anyTitle"/>
				<div class="form-group">
					<label class="control-label col-md-3">
						<span class="required">*</span>Message Salutation :
					</label>
					<div class="col-md-7">
						<sj:textfield name="" value="Dear Parent, "
							id="messageDescription" readonly="true"	cssClass="form-control required input-medium" />
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-md-3">
						<span class="required">*</span>SMS Text :
					</label>
					<div class="col-md-7">
						<s:textarea rows="7" cols="40" cssClass="smsWord_count form-control required" id="sendMessageDiv" maxCharsData="1000"
							name="messages.messageDescription" />
							<span class="smsCounter"></span>
					</div>
				</div>
				<div class="form-group">
						<label class="control-label col-md-3">
							<span class="required">*</span>Message Signature :
						</label>
						<div class="col-md-7">
							<sj:textarea value="Thank you from" 
								rows="3" readonly="true" cssClass="form-control required"></sj:textarea>
						</div>
				</div>
				<div class="form-actions fluid">
					<div class="col-md-offset-3 col-md-5">
						<sj:submit cssClass="submitBt btn blue" value="Submit" validate="true"
							targets="profileId" formIds="sendSmsToParent"
							onBeforeTopics="formValidationForsendSmsToParent" resetForm="true"/>
						<button type="button" data-dismiss="modal" class="btn default">
							Cancel
						</button>
					</div>
				</div>
			</div>
		</s:form>
	</div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/subscription/textcounter.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	FormAdvanced.init();
	$.subscribe('formValidationForsendSmsToParent', function(event, data) {
		var sendParentMess = $('#sendMessageDiv').val();
		if(isNonEmpty(sendParentMess))
			$('button.close').click();
		else
			event.originalEvent.options.submit=false;
	});
});
</script>
