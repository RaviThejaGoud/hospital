<%@ include file="/common/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-globe"></i>Inbox
				</div>
			</div>
			<div class="portlet-body">
					<div id="subjectsContentDiv" class="tab-content">
						 <div class="row inbox">
								<div class="col-md-2">
									<ul class="inbox-nav margin-bottom-10">
										<s:if test='%{#session.previousYear=="N"}'>
											<s:if test='%{user.isOnlySchoolAdmin=="Y" || user.isSchoolPrincipal=="Y" || user.isSchoolAsstStaff=="Y" || user.isSchoolHostel=="Y" || user.isParent=="Y" || user.isSchoolDirector == "Y"}'>
												<li class="compose-btn">
													<s:url id="urlComposeScrapMsgLink" action="ajaxDoComposeScrapMessage" />
													<sj:a href="%{urlComposeScrapMsgLink}" targets="inboxContentDiv" data-title="Compose" cssClass="btn green"><i class="fa fa-edit"></i>Compose</sj:a>
												</li>
											</s:if>
										</s:if>
										<li class="inbox active">
												<s:url id="urlInxox" action="ajaxDoInboxGetScrapMessagesList" />
												<sj:a href="%{urlInxox}" targets="mainContentDiv"  cssClass="btn" data-title="Inbox"><i class="fa fa-envelope"></i>&nbsp;Inbox</sj:a>
											<b></b>
										</li>
									</ul>
								</div>
								<div class="col-md-10">
									<div id="inboxContentDiv"> 
									<jsp:include page="/common/messages.jsp" />
										<s:if test="%{scrapMessagesList != null && !scrapMessagesList.isEmpty()}">
											<h4 class="pageTitle bold">
													General Messages
												</h4>
											<table class="table table-striped table-hover table-bordered dataTable" id="sample_editable_1">
												<thead>
													<tr>
														<th>&nbsp;</th>
														<th>
															Sender
														</th>
														<th>
															Description
														</th>
														<th>
															Date
														</th>
													</tr>
												</thead>
												<tbody>
													<s:iterator value="scrapMessagesList">
														<tr>
															<td class="inbox-small-cells">
																<s:if test='%{isNewMessage == "UR" && receiverAccount.id == anyId }'>
																	<i class="fa fa-star inbox-started"></i>
																</s:if>
																<s:else>
																	<i class="fa fa-star"></i>
																</s:else>
															</td>
															<td>
																<s:url id="doReplayScrapMessages" action="ajaxDoReplayScrapMessages"
																		includeParams="all" escapeAmp="false">
																	<s:param name="id" value="%{id}"></s:param>
																</s:url>
																<sj:a href="%{doReplayScrapMessages}" targets="inboxContentDiv">
																	<s:if test="%{senderAccount.id != anyId}">
																		<s:property value="senderAccount.person.fullPersonName" />
																	</s:if>
																	<s:else>
																		<s:property value="receiverAccount.person.fullPersonName" />
																	</s:else>
																</sj:a>
															</td>
															<td>
																<s:property value="scrapDescription" /> 
															</td>
															<td>
																<s:property value="scrapMessageDateStr" />
															</td>
														</tr>
													</s:iterator>
												</tbody>
											</table>
										</s:if>
										<s:else>
											<div class="alert alert-info">
												Currently there are no general messages.
											</div>
										</s:else>&nbsp;
									 <div class="spaceDiv"></div>
										<s:if test="%{tempList != null && !tempList.isEmpty()}">
											<h4 class="pageTitle bold">
													Complaints Messages
												</h4>
											<table class="table table-striped table-hover table-bordered dataTable" id="sample_editable_2">
												<thead>
													<tr>
													<th></th>
														<th>
															Sender
														</th>
														<th>
															Description
														</th>
														<th>
															Date
														</th>
													</tr>
												</thead>
												<tbody>
													<s:iterator value="tempList">
														<tr>
															<td class="inbox-small-cells">
																<s:if test='%{isNewMessage == "UR" && receiverAccount.id == anyId }'>
																	<i class="fa fa-star inbox-started"></i>
																</s:if>
																<s:else>
																	<i class="fa fa-star"></i>
																</s:else>
															</td>
															<td>
																<s:url id="doReplayScrapMessages" action="ajaxDoReplayScrapMessages"
																		includeParams="all" escapeAmp="false">
																	<s:param name="id" value="%{id}"></s:param>
																</s:url>
																<sj:a href="%{doReplayScrapMessages}" targets="inboxContentDiv">
																	<s:if test="%{senderAccount.id != anyId}">
																		<s:property value="senderAccount.person.fullPersonName" />
																	</s:if>
																	<s:else>
																		<s:property value="receiverAccount.person.fullPersonName" />
																	</s:else>
																</sj:a>
															</td>
															<td>
																<s:if test='%{receiverAccount.id != anyId }'>
																		<span class="lertArrow"></span>&nbsp;
																</s:if>
																<s:property value="scrapDescription" />
															</td>
															<td>
																<s:property value="scrapMessageDateStr" />
															</td>
														</tr>
													</s:iterator>
												</tbody>
											</table>
										</s:if>
										<s:else>
											<div class="alert alert-info">
												Currently there are no complaints messages.
											</div>
										</s:else>
										<div class="spaceDiv"></div>
									 <s:if test="%{tempList1 != null && !tempList1.isEmpty()}">
											<h4 class="pageTitle bold">
													Suggestions Messages
												</h4>
											<table class="table table-striped table-hover table-bordered dataTable" id="sample_editable_3">
												<thead>
													<tr>
													<th></th>
														<th>
															Sender
														</th>
														<th>
															Description
														</th>
														<th>
															Date
														</th>
													</tr>
												</thead>
												<tbody>
													<s:iterator value="tempList1">
														<tr>
															<td class="inbox-small-cells">
																<s:if test='%{isNewMessage == "UR" && receiverAccount.id == anyId }'>
																	<i class="fa fa-star inbox-started"></i>
																</s:if>
																<s:else>
																	<i class="fa fa-star"></i>
																</s:else>
															</td>
															<td>
																<s:url id="doReplayScrapMessages" action="ajaxDoReplayScrapMessages"
																		includeParams="all" escapeAmp="false">
																	<s:param name="id" value="%{id}"></s:param>
																</s:url>
																<sj:a href="%{doReplayScrapMessages}" targets="inboxContentDiv">
																	<s:if test="%{senderAccount.id != anyId}">
																		<s:property value="senderAccount.person.fullPersonName" />
																	</s:if>
																	<s:else>
																		<s:property value="receiverAccount.person.fullPersonName" />
																	</s:else>
																</sj:a>
															</td>
															<td>
																<s:if test='%{receiverAccount.id != anyId }'>
																		<span class="lertArrow"></span>&nbsp;
																</s:if>
																<s:property value="scrapDescription" />
															</td>
															<td>
																<s:property value="scrapMessageDateStr" />
															</td>
														</tr>
													</s:iterator>
												</tbody>
											</table>
										</s:if>&nbsp;
										<s:else>
											<div class="alert alert-info">
												Currently there are no suggestions messages.
											</div>
										</s:else>
									</div>
								</div>
						</div>
					</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
changePageTitle('View Teacher Messages');
TableEditable.init();
function viewReadMoreMin(id) {
	$("#resultsDiv") .html( '<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
	var pars = "id=" + id;
	var url = jQuery.url
			.getChatURL("/student/ajaxViewReadMoreBirthDayMessagesForStudent.do");
	$.ajax( {
		url : url,
		cache : false,
		data : pars,
		success : function(html) {
			$("#myMessagesContent").html(html);
			$('#formattedMsg').html($('#unFormattedMsg').text());
		}

	});
}
function getAjaxRemoveUserInquiry(id) {
	var pars = "id=" + id;
	var urt = confirm("Are you sure you want to delete this topic?");
	if (urt == true) {
		$("#studentContent")
				.html(
						'<div align="center" style="margin: 150px;"><img src="../images/icons/bigWaiting.gif" style="background-repeat: no-repeat;" border="none" title="Processing..." alt="Processing..." align="absmiddle">&nbsp;Loading ...</div>');
		var url = jQuery.url.getChatURL("/group/ajaxDeleteUserInquiry.do");
		$.ajax( {
			url : url,
			cache : false,
			data : pars,
			success : function(html) {
				$("#userInquiryList").html(html);
			}
		});
	}
}

$(document).ready(function() {
	$.subscribe('staffBirthDayDetails', function(event, data) {
		$("#myBirthDayHome").show();
		$("#inboxHome").hide();
	})
});
$(document).ready(function() {
	$.subscribe('myInboxDetails', function(event, data) {
		$("#myBirthDayHome").hide();
		$("#inboxHome").show();
	})
});

$('html, body').animate({ scrollTop: $(document).height() - $(window).height() }, 10, function() {
	    $(this).animate({ scrollTop: 0 }, 10);
	});
</script>
