<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/subscription/textcounter.js">
</script>
<div class="form-body">
	<s:form id="sendSchoolWideAlerts" action="ajaxSendSchoolWideAlerts" method="post" theme="simple" cssClass="form-horizontal" namespace="/common">
		<s:hidden name="messages.messageType" value="B"></s:hidden>
		<h4>Add Alert</h4>
		<div class="form-group">
				<label class="col-md-2 control-label">Select Alert For :</label>
				<div class="col-md-9">
					<div class="radio-list">
						<label class="radio-inline">
						 <input type="radio" checked="" value="SF"  onclick="javascript:changeType(this.value)" name="messages.receiverType">  Staff</label>
						<label class="radio-inline">
						 <input type="radio" checked="" value="ST"  onclick="javascript:changeType(this.value)" name="messages.receiverType"> Parents/Students</label>
					</div>
				</div>
			</div>
		 
		<div class="form-group">
			<label class="control-label col-md-2">
				<span class="required">*</span>Title:
			</label>
			<div class="col-md-4">
				<s:textfield name="messages.title" id="title" label="" size="40"
					labelposition="top" cssClass="form-control required"
					 maxlength="80"></s:textfield>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-md-2">
				<span class="required">*</span>Message :
			</label>
			<div class="col-md-4">
				<sj:textarea name="messages.messageDescription"
					id="messageDescription" maxCharsData="1000"
					cssClass="form-control  word_count required" cssStyle="1045"
					 rows="3" cols="40"></sj:textarea>
				<div class="counter"></div>
			</div>
		</div>
		<div class="form-actions fluid">
			<div class="col-md-offset-2 col-md-9">
				<sj:submit   cssClass="submitBt btn blue" value="Submit"
					validate="true" onBeforeTopics="periodFormValidation"
					targets="schoolWideAlertsHome" formIds="sendSchoolWideAlerts" />
				<s:url id="doViewAlertsList" action="ajaxDoGetSchoolWideAlertsList"
					includeParams="all" escapeAmp="false" namespace="/common"></s:url>
				<sj:a href="%{doViewAlertsList}" cssClass="btn default"
					targets="mainContentDiv">Cancel</sj:a>
			</div>
		</div>
	</s:form>
</div>

<script type="text/javascript">
$(document).ready(function() {
	$('#title').focus();	
 $("input:checkbox, input:radio").uniform();
	changePageTitle("Send School Wide Alert");
});
function changeType() {
	$('#title').focus();
}
</script>
