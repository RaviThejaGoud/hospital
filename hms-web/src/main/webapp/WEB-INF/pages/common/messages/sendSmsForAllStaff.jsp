<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Send SMS and E-mail
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
					<ul class="nav nav-tabs">
					<s:if test='%{anyTitle !="TR"}'>
							<s:if test='%{anyId =="M"}'>
								<li class="dropdown active">
									<a data-hover="dropdown" data-toggle="dropdown"
										class="dropdown-toggle js-activated" href="#"> View SMS <b class="caret"></b> </a>
									<ul role="menu" class="dropdown-menu pull-right">
										<li id="viewEmail" class="active">
											<s:url id="urlClassWideMessages"
												action="ajaxDoGetChairMessagesList" includeParams="all" escapeAmp="false" namespace="/common">
												<s:param name="anyId">M</s:param>
											</s:url>
											<sj:a href="%{urlClassWideMessages}"
												targets="mainContentDiv" data-toggle="tab">View SMS</sj:a>
										</li>
										<li>
											<s:url id="urlSchoolmessagesHistorys" action="ajaxViewSmsHistory" namespace="/staff">
											<s:param name="plTitle">M</s:param>
											</s:url>
											<sj:a href="%{urlSchoolmessagesHistorys}"
												targets="classTeacherMessagesHomeDiv" data-toggle="tab">View SMS History</sj:a>
										</li>
									</ul>
								</li>
							</s:if>
								<li id="classTeacherSmsDiv">
									<s:url id="urlChairManMessagesLink"
										action="ajaxDoSendChaiarManMessages" includeParams="all" escapeAmp="false" namespace="/common">
										<%-- <s:param name="tempString">stepSWMessage</s:param>
										<s:param name="selectedId">classTeacher</s:param> --%>
									</s:url>
									<sj:a href="%{urlChairManMessagesLink}" targets="classTeacherMessagesHomeDiv" data-toggle="tab" >Send SMS</sj:a>
								</li>
								<li  id="classTeacherEmailDiv">
									<s:url id="urlChairManEmails"
										action="ajaxDoSendChairManEmails" includeParams="all" escapeAmp="false" namespace="/common">
										<%-- <s:param name="tempString">stepSWMessage</s:param>
										<s:param name="selectedId">classTeacher</s:param> --%>
									</s:url>
									<sj:a href="%{urlChairManEmails}" targets="classTeacherMessagesHomeDiv" data-toggle="tab" >Send Email</sj:a>
								</li>
						</s:if>
					</ul>
					<div class="tab-content">
						<div id="classTeacherMessagesHomeDiv">
							<jsp:include page="/WEB-INF/pages/common/messages/sendClassWideMessagesList.jsp"></jsp:include>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script language="JavaScript" type="text/javascript">
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
/*	$.ajax( {
		url : "ajaxSchoolEmailWideMessages.do",
		//  data : "classId="+ classId +  "&staffId=" + staffId,
		cache : false,
		data : pars,
		success : function(response) {
			$('#classTeacherMessagesHomeDiv').html(response);
		}
	});*/
}
</script>