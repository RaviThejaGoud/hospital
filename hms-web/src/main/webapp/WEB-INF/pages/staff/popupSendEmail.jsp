<%@ include file="/common/taglibs.jsp"%>
<div data-width="760" class="modal fade modal-overflow in"
	style="display: block; width: 760px; margin-left: -379px; margin-top: 150px;"
	aria-hidden="false">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true"></button>
		<h4 class="bold pageTitle">
			Send Mail To Parent
		</h4>
	</div>
	<div class="modal-body">
		<s:form action="ajaxSendEmailToParent" theme="simple"
			id="sendEmailToParent" cssClass="form-horizontal" namespace="/staff">
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
						<span class="required">*</span>E-Mail Subject : 
					</label>
					<div class="col-md-7">
						<s:textfield id="subject" maxlength="50"
							cssClass="form-control input-medium required" name="subject" />
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-md-3">
						<span class="required">*</span>E-Mail Content : 
					</label>
					<div class="col-md-7">
						<s:textarea rows="7" cols="40" cssClass="form-control required"
							name="description" />
					</div>
				</div>
				<div class="form-group">
						<label class="control-label col-md-3">
							<span class="required">*</span>Message Signature :
						</label>
						<div class="col-md-7">
							<sj:textarea value="Thank you from" id="messageDescription"
								rows="3" readonly="true" cssClass="form-control required"></sj:textarea>
						</div>
				</div>
				<div class="form-actions fluid">
					<div class="col-md-offset-3 col-md-5">
						<sj:submit   cssClass="submitBt btn blue" value="Submit" validate="true"
							targets="profileId" formIds="sendEmailToParent"
							onBeforeTopics="formValidationForsendEmailToParent" />
						<button type="button" data-dismiss="modal" class="btn default">
							Cancel
						</button>
					</div>
				</div>
			</div>
		</s:form>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function() {
	$.subscribe('formValidationForsendEmailToParent', function(event, data) {
			 $('button.close').click();
	});
});
</script>
