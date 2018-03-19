<%@ include file="/common/taglibs.jsp"%>
<div id="sendMsgFormCont" style="display: none;">
	<s:form id="sendAdmissionsMessageForm"
		action="ajaxSendMsgsToAdmissionsAppliedStudents" method="post"
		theme="simple" cssClass="form-horizontal" namespace="/admin">
		<s:hidden id="mobileNos" name="messages.messageType" />
		<div id="onlineApplicationsMessageContent" style="display: none;">
			<div id="sendMessageDiv" class="modal fade" data-width="760">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true"></button>
					<h4 class="text-primary">
						Send Message
					</h4>
				</div>
				<div class="modal-body">
				<div class="form-body form-horizontal">
						<div class="form-group" id="messageSalutation">
							<label class="control-label col-md-4">
								<span class="required">*</span>Message Salutation :
							</label>
							<div class="col-md-5">
								<sj:textfield name="" maxlength="2" readonly="true" size="5"
									value="Dear Parent, Your Son/Daughter" id="messageDescription"
									cssClass="required form-control" />
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-4">
								<span class="required">*</span>Message Subject :
							</label>
							<div class="col-md-5">
								<sj:textarea rows="2" cols="30" maxCharsData="1000" 
									name="messages.messageDescription" cssClass="smsWord_count form-control"
									id="offLineMsgsForStudents"></sj:textarea>
									<span class="smsCounter"></span>
							</div>
						</div>
						<div class="form-group" id="parentSignature">
							<label class="control-label col-md-4">
								<span class="required">*</span>Message Signature :
							</label>
							<div class="col-md-6">
								<sj:textfield name="" maxlength="2" readonly="true" size="5"
									value="Thank you principal" id="messageDescription"
									cssClass="required  form-control input-medium" />
							</div>
						</div>
						<div class="form-actions fluid">
							<div class="col-md-offset-4 col-md-6">
								<sj:submit   cssClass="submitBt btn blue" value="Submit"
									targets="sendMsgForAdmissionsFormCont"
									onBeforeTopics="smsSendingFormValidation" indicator="indicator"
									onCompleteTopics="unCheckStudchkboxes"
									formIds="sendAdmissionsMessageForm" validate="true" />
								<!--<sj:submit   targets="sendMsgForAdmissionsFormCont" value="Submit"
								indicator="indicator" formIds="sendAdmissionsMessageForm"
								cssClass="submitBt btn blue" validate="true"
								onBeforeTopics="smsSendingFormValidation"
								onCompleteTopics="unCheckStudchkboxes" />
								-->
								<a onclick="javascript:showOrHideMessageContent();"
									data-dismiss="modal" class="btn default">Cancel</a>
							</div>
						</div>
				</div>
			</div>
			</div>
		</div>
	</s:form>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/subscription/textcounter.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	$.subscribe('smsSendingFormValidation', function(event, data) {
			var dise = $('textarea#offLineMsgsForStudents').val();
			if (dise.length == 0) {
				alert("Please fill the message subject.");
				event.originalEvent.options.submit=false;
			} else {
				if ($("input[name=chkBoxSelectedIds]").parent('span.checked').length > 0) {
				var selectedonlineStuIds = [];
				$("input[name=chkBoxSelectedIds]").parent('span.checked').each(function(){
					selectedonlineStuIds.push($(this).find('input').val());
				});
				
				$("#mobileNos").val(selectedonlineStuIds);
				$('button.close').click(); //close the popup
				return true;
			  } else if ($("input[name=chkBoxSelectedIds]").parent('span.checked').length == 0) {
					alert("Please select at least one student");
					event.originalEvent.options.submit=false;
				} else {
					event.originalEvent.options.submit=false;
				}
			}
		 
	});
	
	$.subscribe('unCheckStudchkboxes', function(event, data) {
		unselectStudentFields();
	});
	
	
	$("input[name=chkBoxSelectedIds]").click(function() {
		if ($("input[name=chkBoxSelectedIds]:unchecked").length > 0) {
			$(".allonlineStudents").attr("checked", false);
		} else {
			$(".allonlineStudents").attr("checked", true);
		}
	});
	
});


function showOrHideMessageContent() {
	if ($('#sendMsgFormCont').is(':hidden'))
		$('#sendMsgFormCont').show();
	else {
		unselectStudentFields();
		$('#sendMsgFormCont').hide()
	}
}

function unselectStudentFields(){
	$("[name='chkBoxSelectedIds']").removeAttr('checked');
	$("input[name='selectAll']").removeAttr('checked');
}
</script>