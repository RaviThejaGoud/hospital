<%@ include file="/common/taglibs.jsp"%>
<%-- <jsp:include page="/common/messages.jsp"></jsp:include> --%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Class Wide Messages
				</div>
			</div>
			<div class="portlet-body" id="smsTeacherMessagesHomeDiv">
				<div class="tabbable tabbable-custom tabbable-full-width">
				<s:if test="%{studyClassList != null && !studyClassList.isEmpty()}">
					<ul class="nav nav-tabs">
						<s:if test='%{anyTitle !="TR"}'>
							<s:if test='%{anyId =="M"}'>
								<li class="dropdown active">
									<a data-hover="dropdown" data-toggle="dropdown"
										class="dropdown-toggle js-activated" href="#"> View SMS <b class="caret"></b> </a>
									<ul role="menu" class="dropdown-menu pull-right">
										<li id="viewEmail" class="active">
											<s:url id="urlClassWideMessages"
												action="ajaxDoGetClassWideMessagesList" includeParams="all" escapeAmp="false" namespace="/common">
												<s:param name="anyId">M</s:param>
												<s:param name="tempBoolean" value="tempBoolean"></s:param>
											</s:url>
											<sj:a href="%{urlClassWideMessages}"
												targets="mainContentDiv" data-toggle="tab">View SMS</sj:a>
										</li>
										<li>
											<s:url id="urlSchoolmessagesHistorys" action="ajaxViewSmsHistory" namespace="/staff">
											<s:param name="plTitle">M</s:param>
											</s:url>
											<sj:a href="%{urlSchoolmessagesHistorys}" cssClass="smsHistoryId"
												targets="classTeacherMessagesHomeDiv" data-toggle="tab">View SMS History</sj:a>
										</li>
									</ul>
								</li>
							</s:if>
							<s:if test='%{#session.previousYear=="N"}'>
								<li id="classTeacherSmsDiv">
									<s:url id="urlClassWideMessagesLink"
										action="ajaxDoSendSchoolWideMessages" includeParams="all" escapeAmp="false" namespace="/common">
										<s:param name="tempString">stepSWMessage</s:param>
										<s:param name="selectedId">classTeacher</s:param>
									</s:url>
									<sj:a href="%{urlClassWideMessagesLink}" targets="classTeacherMessagesHomeDiv" data-toggle="tab" >Send SMS</sj:a>
								</li>
								<li  id="classTeacherEmailDiv">
									<s:url id="urlClassWideEmails"
										action="ajaxDoSendSchoolWideEmails" includeParams="all" escapeAmp="false" namespace="/common">
										<s:param name="tempString">stepSWMessage</s:param>
										<s:param name="selectedId">classTeacher</s:param>
									</s:url>
									<sj:a href="%{urlClassWideEmails}" targets="classTeacherMessagesHomeDiv" data-toggle="tab" >Send Email</sj:a>
								</li>
							</s:if>
						</s:if>
					</ul>
					<div class="tab-content" id="classTeacherMessagesHomeDiv">
						<jsp:include page="/WEB-INF/pages/common/messages/sendClassWideMessagesList.jsp"></jsp:include>
					</div>
					</s:if>
					<s:else>
						<div class="alert alert-info">
							Currently there are no classes assigned for you. 
						</div>
					</s:else>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function() {
  $('.js-activated').dropdownHover().dropdown();
 });
function messagesChange(clickButton) {
	var pars = '';
	var message = clickButton;
	if (message == 'N') {
		$("#emailId").show();
		$("#smsId").hide();
		pars = "anyId=" + "M";
	} else {
		if (message == 'R') {
			$("#smsId").show();
			$("#emailId").hide();
			pars = "anyId=" + "E";
		}
	}
}
</script>