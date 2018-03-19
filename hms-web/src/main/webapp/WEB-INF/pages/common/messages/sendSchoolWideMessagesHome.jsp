<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
				<s:if test='%{anyTitle !="TR"}'>
					<i class="fa fa-globe"></i>School & Class Wide Messages 
				</s:if>
				<s:else>
					<i class="fa fa-globe"></i>Transport SMS
				</s:else>
				</div>
			</div>
			<div class="portlet-body">
				<div class="tabbable tabbable-custom tabbable-full-width">
					<ul class="nav nav-tabs">
						<s:if test='%{anyTitle !="TR"}'>
							<s:if test='%{anyId =="E"}'>
								<li id="viewEmail">
									<s:url id="urlSchoolEmailWideMessageses" action="ajaxSchoolEmailWideMessages" namespace="/common"> 
										<s:param name="anyId" value="'E'" />
									</s:url>
									<sj:a href="%{urlSchoolEmailWideMessageses}" targets="schoolWideMessagesHomeDiv" data-toggle="tab">View Emails</sj:a>
								</li>
							</s:if>
							<s:else>
								<!--<li id="smsDiv" class="active">
									<s:url id="urlSchoolEmailWideMessages" action="ajaxDoGetSchoolWideMessagesList" namespace="/common">
									</s:url>
									<sj:a href="%{urlSchoolEmailWideMessages}" targets="mainContentDiv" data-toggle="tab" >View (<s:property value="smsCnt" />) SMS</sj:a>
								</li>
								-->
								<%-- <s:if test='%{user.isSchoolAsstStaff=="N"}'> --%>
									<li class="dropdown active" id="smsDiv">
										<a data-hover="dropdown" data-toggle="dropdown"
											class="dropdown-toggle js-activated" href="#"> View (<s:property value="smsCnt" />) SMS <b class="caret"></b> </a>
										<ul role="menu" class="dropdown-menu pull-right">
											<li id="smsDiv">
												<s:url id="urlSchoolEmailWideMessages"
													action="ajaxDoGetSchoolWideMessagesList" namespace="/common">
												</s:url>
												<sj:a href="%{urlSchoolEmailWideMessages}" targets="schoolWideMessagesHomeDiv"
													data-toggle="tab">View (<s:property value="smsCnt" />) SMS</sj:a>
											</li>
											<li>
												<s:url id="urlSchoolmessagesHistory" action="ajaxViewSmsHistory" namespace="/staff">
												</s:url>
												<sj:a href="%{urlSchoolmessagesHistory}" cssClass="smsHistoryId"
													targets="schoolWideMessagesHomeDiv" data-toggle="tab">View SMS History</sj:a>
											</li>
										</ul>
									</li>
								<%-- </s:if> --%>
								<!--<li id="smsDiv" class="active">
									<s:url id="urlSchoolEmailWideMessages" action="ajaxDoGetSchoolWideMessagesList" namespace="/common">
									</s:url>
									<sj:a href="%{urlSchoolEmailWideMessages}" targets="mainContentDiv" data-toggle="tab" >View (<s:property value="smsCnt" />) SMS</sj:a>
								</li>
								--><!--<li id="viewEmail"><s:url id="urlSchoolEmailWideMessages" action="ajaxSchoolEmailWideMessages">
									<s:param name="anyId" value="'M'" />
								</s:url>
								<sj:a href="%{urlSchoolEmailWideMessages}" targets="schoolWideMessagesHomeDiv" data-toggle="tab" >View SMS</sj:a> </li> -->
							</s:else>
							<s:if test='%{#session.previousYear=="N"}'>
								<s:if test='%{user.isSchoolAsstStaff=="N" && user.isReceptionist == "N" }'>
									<li id="loginCredentials">
										<s:url id="urlcrendentialsMessagesLink"
											action="ajaxDoSendLoginCredentialsMessages" includeParams="all"
											escapeAmp="false" namespace="/staff">
										</s:url>
										<sj:a href="%{urlcrendentialsMessagesLink}"
											targets="schoolWideMessagesHomeDiv" data-toggle="tab">Send Login Credentials </sj:a>
									</li>
								</s:if>
									<li id="sendSchoolwideEmailDiv">
										<s:url id="urlSchoolWideEmailsLink"
											action="ajaxDoSendSchoolWideEmails" includeParams="all"
											escapeAmp="false" namespace="/common">
											<s:param name="tempString">stepSWMessage</s:param>
										</s:url>
										<sj:a href="%{urlSchoolWideEmailsLink}"
											targets="schoolWideMessagesHomeDiv" data-toggle="tab">Send E-Mail</sj:a>
									</li>
									<%-- <s:if test='%{user.isReceptionist == "N" || user.isSchoolDirector == "N" }'>
										<li>
											<s:url id="urlcrendentialsMessagesLink"
												action="ajaxReadSMSEnquiriesDetails" includeParams="all"
												escapeAmp="false" namespace="/staff">
											</s:url>
											<sj:a href="%{urlcrendentialsMessagesLink}"
												targets="schoolWideMessagesHomeDiv" data-toggle="tab">SMS Inquiry </sj:a>
										</li>
								   </s:if> --%>
									<li id="CreateSMSEmail" class="active">
										<s:if test='%{user.isSchoolAdmin == "Y" || user.isSchoolPrincipal=="Y" || user.isSchoolClerk=="Y" || user.isSchoolAsstStaff=="Y" || user.isSchoolDirector == "Y" || user.isReceptionist == "Y" }'>
										<%-- <s:if test='%{user.isSchoolAsstStaff=="Y"}'>
											<li class="active">
										</s:if>
										<s:else>
											<li id="adminSmsDiv">
										</s:else> --%>
											<s:url id="urlSchoolWideSMSAndMessagesLink"
												action="ajaxDoSendSchoolWideMessages" includeParams="all"
												escapeAmp="false" namespace="/common">
												<s:param name="tempString">stepSWMessage</s:param>
											</s:url>
											<sj:a href="%{urlSchoolWideSMSAndMessagesLink}"
												targets="mainContentDiv" data-toggle="tab">Send SMS</sj:a>
										</li>
										<!--<li>
											<s:url id="totalSmsCount" action="ajaxTotalSmsCount" namespace="/admin" >
											</s:url>
											<sj:a href="%{totalSmsCount}" targets="schoolWideMessagesHomeDiv" data-toggle="tab">
												Used (<s:property value="smsCnt" />) SMS</sj:a>
										</li>
									--></s:if>
									<s:else>
										<s:if test='%{user.isSchoolHostel=="N"}'>
											<s:url id="urlSchoolWideMessagesLink"
												action="ajaxDoSendSchoolWideMessages" includeParams="all" escapeAmp="false" namespace="/common">
												<s:param name="tempString">stepSWMessage</s:param>
											</s:url>
											<sj:a href="%{urlSchoolWideMessagesLink}" targets="mainContentDiv" data-toggle="tab" >Create SMS </sj:a>
											</s:if>
									</s:else>
								</li>
							</s:if>
						</s:if>
						<s:else>
							<s:if test='%{#session.previousYear=="N"}'>
								<li id="CreateSMSEmail" class="active">
									<s:url id="urlSchoolWideSMSLink" action="ajaxDoSendSchoolWideMessages" includeParams="all" escapeAmp="false" namespace="/common">
										<s:param name="tempString">stepTRMessage</s:param>
										<s:param name="plTitle">TRMessages</s:param>
									</s:url>
									<sj:a href="%{urlSchoolWideSMSLink}" targets="mainContentDiv" data-toggle="tab" >Create SMS</sj:a>
								</li>
								<li class="dropdown active" id="trDiv">
									<a data-hover="dropdown" data-toggle="dropdown"
										class="dropdown-toggle js-activated" href="#"> View SMS <b class="caret"></b> </a>
									<ul role="menu" class="dropdown-menu pull-right">
										<li>
											<s:url id="urlSchoolTransportWideMessages"
												action="ajaxDoGetSchoolWideMessagesList" namespace="/common" includeParams="all" escapeAmp="false">
												<s:param value="#session.academicYear" name="academicYearId" />
										        <s:param name="status">TR</s:param>
											</s:url>
											<sj:a href="%{urlSchoolTransportWideMessages}" targets="schoolWideMessagesHomeDiv" data-toggle="tab">
										     View SMS</sj:a>
										</li>
										<li>
											<s:url id="urlSchoolmessagesHistoryTransport" action="ajaxViewSmsHistory" includeParams="all" escapeAmp="false" namespace="/staff">
											<s:param value="#session.academicYear" name="academicYearId" />
											<s:param name="anyTitle">TR</s:param>
											</s:url>
											<sj:a href="%{urlSchoolmessagesHistoryTransport}"  cssClass="smsHistoryId"
												targets="schoolWideMessagesHomeDiv" data-toggle="tab">View SMS History</sj:a>
										</li>
									</ul>
								</li>	
							</s:if>
						</s:else>
					</ul>
					<div class="tab-content">
						<div id="schoolWideMessagesHomeDiv">
							<jsp:include page="/common/messages.jsp"></jsp:include>
							<jsp:include page="/WEB-INF/pages/common/messages/sendSchoolWideMessage.jsp"></jsp:include>
						</div>
				</div>
			</div>
		</div>
	</div>
</div> 
</div>
<script type="text/javascript">
$(document).ready(function() {
	/* var isAssStaff='<s:property value="user.isSchoolAsstStaff"/>';
	 if(isAssStaff=="Y"){
		 var stepSWMessage="stepSWMessage";
		 $.ajax( {
		    url : jQuery.url.getChatURL("/common/ajaxDoSendSchoolWideMessages.do"),
			cache : false,
			data :"tempString="+stepSWMessage,
			success : function(html) {
			$("#schoolWideMessagesHomeDiv").html(html);
			}
		});
		 
	 }
	 $('.js-activated').dropdownHover().dropdown(); */
	var messageType ='<s:property value="plTitle"/>';
	var recieverType ='<s:property value="anyTitle"/>';
	var pars ="";
	if(messageType == 'absenteesMessages'){
		changeQualification(messageType);
		if(recieverType == 'P'){
		//	$('#absenteesDiv1').show();
				$("div#absenteesList")
				.html(
						'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
				var url = jQuery.url.getChatURL("/common/ajaxGetAbsenteesListByType.do?anyTitle="+recieverType);
				$.ajax( {
					url : url,
					cache : false,
					data : pars,
					success : function(html) {
						$("div#absenteesList").html(html);
						$("div#absenteesList").show();
						$('#msgReceiverTypeId').val('P');
						$('#messageSalutationId').show();
						$('#parentSalutationText').show();
						$('#staffSalutationText').hide();
					}
				});
		}
	}
	if((<s:property value="smsAlloted"/> == 0) || (<s:property value="smsAlloted"/>) <= (<s:property value="smsCnt"/>)){
		$('#absenteesDiv').hide();
	}
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
	$.ajax( {
		url : "ajaxSchoolEmailWideMessages.do",
		cache : false,
		data : pars,
		success : function(response) {
			$('#schoolWideMessagesHome').html(response);
		}
	});
}

$('html, body').animate({ scrollTop: $(document).height() - $(window).height() }, 10, function() {
	    $(this).animate({ scrollTop: 0 }, 10);
	});
</script>