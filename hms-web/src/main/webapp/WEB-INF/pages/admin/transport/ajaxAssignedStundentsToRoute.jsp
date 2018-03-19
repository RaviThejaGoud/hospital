<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/subscription/textcounter.js">
</script>
<s:if test="%{objectList != null && !objectList.isEmpty()}">
	<div class="form-group">
		<label class="control-label col-md-2">
			Total Students :
		</label>
		<div class="col-md-3">
			<p class="form-control-static">
				<s:property value="objectList.size" />
			</p>
		</div>
		<s:iterator value="objectList">
			<input name="chkBoxSelectedAccountIds" type="hidden"
				value="<s:property value="accountId" />" />
		</s:iterator>
	</div>
	<div class="grid_8" id="msgDescDiv">
		<div class="form-group">
			<label class="control-label col-md-2">
				<span class="required">*</span>Message Subject :
			</label>
			<div class="col-md-3">
				<sj:textarea name="messages.messageDescription" maxCharsData="1000" 
					id="messageDescription1" rows="3" cssClass="smsWord_count form-control required"
					cols="20"></sj:textarea>
					<span class="smsCounter"></span>
			</div>
		</div>
	</div>
	<s:if test="%{(smsAlloted == 0) || (smsAlloted <= smsCnt)}">
		<div class="form-actions fluid">
			<div class="col-md-offset-2 col-md-9">
				<sj:submit  cssClass="submitBt btn blue" value="Submit" onBeforeTopics="transportWideStudentsMsgs"
					 targets="schoolWideMessagesHomeDiv"
					formIds="addTransportWideMessage" validate="true" />
				<s:if test='%{tempString == "stepSWMessage"}'>
					<s:url id="doCancelTransportWDMessage1"
						action="ajaxDoGetSchoolWideMessagesList" namespace="/common"
						includeParams="all" escapeAmp="false">
					</s:url>
					<sj:a href="%{doCancelTransportWDMessage1}" cssClass="btn default"
						 targets="mainContentDiv">Cancel</sj:a>
				</s:if>
				<s:else>
					<s:url id="doCancelTransportWDMessage1"
						action="ajaxDoGetTransportMessagesList" namespace="/common"
						includeParams="all" escapeAmp="false">
						<s:param name="status">TR</s:param>
					</s:url>
					<sj:a href="%{doCancelTransportWDMessage1}" cssClass="btn default"
						 targets="schoolWideMessagesHomeDiv">Cancel</sj:a>
				</s:else>
			</div>
		</div>
	</s:if>
</s:if>
<s:else>
	<div class="alert alert-info">
		Oops! No students found for the selected vehicle. Please contact admin if already assigned.
	</div>
</s:else>
<script type="text/javascript">
changePageTitle('Manage Transport Route');

$.subscribe('transportWideStudentsMsgs', function(event, data) {
	 $('.submitBt').attr('disabled', 'disabled');
	 if ($('#addTransportWideMessage').valid()) {
		 return true; 
	 } else
		 $('.submitBt').removeAttr('disabled');
			return false;
	});
	
</script>

