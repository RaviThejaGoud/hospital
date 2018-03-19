<%@ include file="/common/taglibs.jsp"%>
		<div id="onlineApplicationsMessageContent" style="background: infoBackground;">
		<s:form id="dosendMsgForPendStud" action="ajaxSendMsgsToOnlineStudents"
		method="post" theme="simple" cssClass="form-horizontal" namespace="/admin">
		<s:hidden id="mobileNos" name="messages.messageType" value="%{messages.messageType}"/>
		<s:hidden name="status" value="%{status}"></s:hidden>
				<div class="modal-body" style="border: 2px solid #ccc;">
					<div class="form-body form-horizontal">
						<div class="form-group" id="messageSalutation">
							<label class="control-label col-md-4">
								<span class="required">*</span>Message Salutation :
							</label>
							<div class="col-md-5">
								<sj:textfield name="" value="Dear Parent, Your Son/Daughter"
									id="messageDescription" readonly="true"
									cssClass="required form-control"></sj:textfield>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-4">
								<span class="required">*</span>Message Subject :
							</label>
							<div class="col-md-5">
								<sj:textarea name="messages.messageDescription"
									id="offLineMsgsForStudents" rows="3" maxCharsData="1000" 
									cssClass="smsWord_count form-control" cols="20"></sj:textarea>
									<span class="smsCounter"></span>
							</div>
						</div>
						<div class="form-group" id="parentSignature">
							<label class="control-label col-md-4">
								<span class="required">*</span>Message Signature :
							</label>
							<div class="col-md-5">
								<sj:textfield name="" value="Thank you principal"
									id="messageDescription" readonly="true"
									cssClass="required form-control input-medium as-input"></sj:textfield>
							</div>
						</div>
						<div class="form-actions fluid">
							<div class="col-md-offset-4 col-md-6">
							<s:if test="%{(smsAlloted != 0) && (smsAlloted > smsCnt)}">
								<sj:submit   cssClass="btn blue small" value="Submit" targets="mainContentDiv"
									formIds="dosendMsgForPendStud" validate="true" onBeforeTopics="onlinePendingStudentsMsgs"/>
							</s:if>
								<button type="button" data-dismiss="modal" class="btn btn-default">Cancel</button>
							</div>
						</div>
					</div>
					</div>
			</s:form>
		</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/subscription/textcounter.js"></script>
<script type="text/javascript"> 
$(document).ready(function() {
	  $.destroyTopic('onlinePendingStudentsMsgs');
$.subscribe('onlinePendingStudentsMsgs', function(event, data) {
   	var desc = $('#offLineMsgsForStudents').val();
   	if (desc == null || desc == ''){
   		alert("Please enter message subject.");
   		 event.originalEvent.options.submit=false;
   	}else{
   		return true;
   	}
}); 
}); 
	</script>